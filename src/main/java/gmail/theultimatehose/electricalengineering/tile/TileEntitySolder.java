package gmail.theultimatehose.electricalengineering.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gmail.theultimatehose.electricalengineering.ElectricalEngineering;
import gmail.theultimatehose.electricalengineering.network.sync.IPacketSyncerToClient;
import gmail.theultimatehose.electricalengineering.network.sync.PacketSyncerToClient;
import gmail.theultimatehose.electricalengineering.recipe.SolderRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySolder extends TileEntityInventoryBase implements IEnergyReceiver, IPacketSyncerToClient {

    public static final int SLOT_CRAFT_1 = 0;
    public static final int SLOT_CRAFT_2 = 1;
    public static final int SLOT_CRAFT_3 = 2;
    public static final int SLOT_CRAFT_4 = 3;
    public static final int SLOT_CRAFT_5 = 4;
    public static final int SLOT_CRAFT_6 = 5;
    public static final int SLOT_CRAFT_7 = 6;
    public static final int SLOT_CRAFT_8 = 7;
    public static final int SLOT_CRAFT_9 = 8;
    public static final int SLOT_CRAFT_RES = 9;
    public static final int SLOT_PCB_IN = 11;
    public static final int SLOT_PCB_OUT = 12;
    public String name;
    public int desolderTime, currDesolderTime;
    public int solderTime, currSolderTime;
    public EnergyStorage storage = new EnergyStorage(50000, 1000);
    public boolean isSoldering;
    public ItemStack nextSolderOut;
    private int lastEnergyStored, lastDesolderTime, lastSolderTime;

    public TileEntitySolder() {
        slots = new ItemStack[13];
        name = "container.electricalengineering.solder";

        this.desolderTime = 100;
        this.solderTime = 200;
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) {

            if (storage.getEnergyStored() != lastEnergyStored || lastDesolderTime != currDesolderTime || lastSolderTime != currSolderTime) {
                lastEnergyStored = storage.getEnergyStored();
                lastDesolderTime = currDesolderTime;
                lastSolderTime = currSolderTime;
                this.sendUpdate();
            }

            if (storage.getEnergyStored() >= 100 && canDesolder()) {
                currDesolderTime++;
                storage.extractEnergy(100, false);
                if (currDesolderTime == desolderTime) {
                    finishDesolder();
                    currDesolderTime = 0;
                    this.markDirty();
                }
            } else if (slots[SLOT_PCB_IN] == null) {
                currDesolderTime = 0;
            }

            if (storage.getEnergyStored() >= 100 && isSoldering && canSolder()) {
                currSolderTime++;
                storage.extractEnergy(100, false);
                if (currSolderTime == solderTime) {
                    finishSolder();
                    currSolderTime = 0;
                    this.markDirty();
                }
            } else if (!isSoldering) {
                currSolderTime = 0;
            }
        }
    }

    private boolean canDesolder() {
        if (slots[SLOT_PCB_IN] != null) {
            ItemStack in = slots[SLOT_PCB_IN];
            ItemStack out = slots[SLOT_PCB_OUT];
            if (in.getItem().equals(ElectricalEngineering.pcbLVBurnt)) {
                if (out == null) {
                    return true;
                } else if (out.getItem().equals(ElectricalEngineering.pcbScrapLV)) {
                    if (out.stackSize + 1 < out.getMaxStackSize()) {
                        return true;
                    }
                }
            } else if (in.getItem().equals(ElectricalEngineering.pcbHVBurnt)) {
                if (out == null) {
                    return true;
                } else if (out.getItem().equals(ElectricalEngineering.pcbScrapHV)) {
                    if (out.stackSize + 1 < out.getMaxStackSize()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void finishDesolder() {
        ItemStack in = slots[SLOT_PCB_IN];
        ItemStack out = slots[SLOT_PCB_OUT];
        if (in.getItem().equals(ElectricalEngineering.pcbLVBurnt)) {
            if (out == null) slots[SLOT_PCB_OUT] = new ItemStack(ElectricalEngineering.pcbScrapLV, 1);
            else if (out.getItem().equals(ElectricalEngineering.pcbScrapLV)) slots[SLOT_PCB_OUT].stackSize++;
        } else if (in.getItem().equals(ElectricalEngineering.pcbHVBurnt)) {
            if (out == null) slots[SLOT_PCB_OUT] = new ItemStack(ElectricalEngineering.pcbScrapHV, 1);
            else if (out.getItem().equals(ElectricalEngineering.pcbScrapHV)) slots[SLOT_PCB_OUT].stackSize++;
        }
        slots[SLOT_PCB_IN].stackSize--;
        if (slots[SLOT_PCB_IN].stackSize <= 0) slots[SLOT_PCB_IN] = null;
    }

    public void startSolder(SolderRecipe sr) {
        if (sr != null) {
            isSoldering = true;
            nextSolderOut = sr.getRecipeOutput();
        } else {
            isSoldering = false;
            nextSolderOut = null;
        }
    }

    private boolean canSolder() {
        ItemStack out = slots[SLOT_CRAFT_RES];
        if (slots[SLOT_CRAFT_RES] == null) {
            return true;
        } else {
            if (slots[SLOT_CRAFT_RES].getItem() == nextSolderOut.getItem()) {
                return (out.stackSize + 1 < out.getMaxStackSize());
            }
        }
        return false;
    }

    public void finishSolder() {
        ItemStack out = slots[SLOT_CRAFT_RES];
        if (out == null) {
            slots[SLOT_CRAFT_RES] = nextSolderOut.copy();
        } else {
            out.stackSize++;
        }

        if (slots[SLOT_CRAFT_RES].getItem() == ElectricalEngineering.gaussGun) {
            ItemStack coil = slots[SLOT_CRAFT_1].copy();
            int windings = 2048 - coil.getItemDamage();
            double i = windings / 8;
            double multiplier = i / 1000;
            if (!slots[SLOT_CRAFT_RES].hasTagCompound()) {
                slots[SLOT_CRAFT_RES].stackTagCompound = new NBTTagCompound();
            }
            NBTTagCompound compound = slots[SLOT_CRAFT_RES].getTagCompound();
            compound.setInteger("windings", windings);
            compound.setDouble("multiplier", multiplier);
        }

        for (int i = 0; i < 9; i++) {
            if (slots[i] != null) {
                slots[i].stackSize--;
                if (slots[i].stackSize <= 0) slots[i] = null;
            }
        }

        isSoldering = false;

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        compound.setInteger("SolderTime", this.currSolderTime);
        compound.setInteger("DeSolderTime", this.currDesolderTime);
        storage.writeToNBT(compound);
        super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.currSolderTime = compound.getInteger("SolderTime");
        this.currDesolderTime = compound.getInteger("DeSolderTime");
        storage.readFromNBT(compound);
        super.readFromNBT(compound);
    }

    @SideOnly(Side.CLIENT)
    public int getEnergyToScale(int i) {
        return storage.getEnergyStored() * i / storage.getMaxEnergyStored();
    }

    @SideOnly(Side.CLIENT)
    public int getSolderTimeToScale(int i) {
        return this.currSolderTime * i / this.solderTime;
    }

    @SideOnly(Side.CLIENT)
    public int getDesolderTimeToScale(int i) {
        return this.currDesolderTime * i / this.desolderTime;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == SLOT_PCB_IN && (stack.getItem() == ElectricalEngineering.pcbHVBurnt || stack.getItem() == ElectricalEngineering.pcbLVBurnt);
    }

    @Override
    public String getInventoryName() {
        return "Soldering Table";
    }

    @Override
    public int getSizeInventory() {
        return slots.length;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
    }

    @Override
    public int[] getValues() {
        return new int[]{storage.getEnergyStored(), currDesolderTime, currSolderTime};
    }

    @Override
    public void setValues(int[] values) {
        this.storage.setEnergyStored(values[0]);
        currDesolderTime = values[1];
        currSolderTime = values[2];
    }

    @Override
    public void sendUpdate() {
        PacketSyncerToClient.sendPacket(this);
    }

}