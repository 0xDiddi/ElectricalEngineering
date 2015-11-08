package gmail.theultimatehose.electricalengineering.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
import gmail.theultimatehose.electricalengineering.network.sync.IPacketSyncerToClient;
import gmail.theultimatehose.electricalengineering.network.sync.PacketSyncerToClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCoilWinder extends TileEntityInventoryBase implements IEnergyReceiver, IPacketSyncerToClient {

    public static final int SLOT_WIRE_IN = 0;
    public static final int SLOT_COIL_IN = 1;
    public static final int SLOT_COIL_OUT = 2;
    public String name;
    public EnergyStorage storage = new EnergyStorage(50000, 1000);
    public int coilAmount, currCoilAmount;
    public int windTime, currWindTime;
    private int lastEnergyStored, lastCoilAmount, lastWindTime;

    public TileEntityCoilWinder() {
        slots = new ItemStack[3];
        name = "container.electricalengineering.coilWinder";

        windTime = 10;
        coilAmount = 2048;
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) {

            if (storage.getEnergyStored() != lastEnergyStored || lastWindTime != currWindTime || lastCoilAmount != currCoilAmount) {
                lastEnergyStored = storage.getEnergyStored();
                lastWindTime = currWindTime;
                lastCoilAmount = currCoilAmount;
                this.sendUpdate();
            }

            if (this.storage.getEnergyStored() >= 10 && canWind()) {
                ItemStack input = slots[SLOT_COIL_IN];
                currWindTime++;
                if (currWindTime == windTime) {
                    currWindTime = 0;
                    currCoilAmount--;
                    input.setItemDamage(input.getItemDamage() - 1);
                }
                if (input.getItemDamage() <= 0) {
                    slots[SLOT_COIL_OUT] = slots[SLOT_COIL_IN].copy();
                    slots[SLOT_COIL_IN] = null;
                }
            } else if (slots[SLOT_COIL_IN] == null) {
                currWindTime = 0;
            }

            if (slots[SLOT_WIRE_IN] != null) {
                if (slots[SLOT_WIRE_IN].getItem() == ItemManager.wireCopper && currCoilAmount + 1 <= coilAmount) {
                    currCoilAmount++;
                    slots[SLOT_WIRE_IN].stackSize--;
                    if (slots[SLOT_WIRE_IN].stackSize <= 0) {
                        slots[SLOT_WIRE_IN] = null;
                    }
                }
            }

        }
    }

    public boolean canWind() {

        if (slots[SLOT_COIL_IN] != null) {
            if (slots[SLOT_COIL_IN].getItem() == ItemManager.coil && currCoilAmount > 0) {
                if (slots[SLOT_COIL_OUT] == null) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        storage.readFromNBT(compound);
        this.currWindTime = compound.getInteger("currWindTime");
        this.currCoilAmount = compound.getInteger("currCoilAmount");
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        storage.writeToNBT(compound);
        compound.setInteger("currWindTime", currWindTime);
        compound.setInteger("currCoilAmount", currCoilAmount);
        super.writeToNBT(compound);
    }

    @SideOnly(Side.CLIENT)
    public int getEnergyToScale(int i) {
        return storage.getEnergyStored() * i / storage.getMaxEnergyStored();
    }

    @SideOnly(Side.CLIENT)
    public int getCoilAmountToScale(int i) {
        return currCoilAmount * i / coilAmount;
    }

    @SideOnly(Side.CLIENT)
    public int getWindTimeToScale(int i) {
        return currWindTime * i / windTime;
    }

    @SideOnly(Side.CLIENT)
    public int getCoilProgressToScale(int i) {
        return (2048 - slots[SLOT_COIL_IN].getItemDamage()) * i / slots[SLOT_COIL_IN].getMaxDamage();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot == SLOT_WIRE_IN && stack.getItem() == ItemManager.wireCopper) {
            return true;
        }
        if (slot == SLOT_COIL_IN && stack.getItem() == ItemManager.coil) {
            return true;
        }
        return false;
    }

    @Override
    public String getInventoryName() {
        return "Coil Winder";
    }

    @Override
    public int getSizeInventory() {
        return slots.length;
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
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int[] getValues() {
        return new int[]{storage.getEnergyStored(), currWindTime, currCoilAmount};
    }

    @Override
    public void setValues(int[] values) {
        this.storage.setEnergyStored(values[0]);
        currWindTime = values[1];
        currCoilAmount = values[2];
    }

    @Override
    public void sendUpdate() {
        PacketSyncerToClient.sendPacket(this);
    }
}
