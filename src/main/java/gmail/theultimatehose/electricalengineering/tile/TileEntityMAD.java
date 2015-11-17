package gmail.theultimatehose.electricalengineering.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
import gmail.theultimatehose.electricalengineering.network.sync.IPacketSyncerToClient;
import gmail.theultimatehose.electricalengineering.network.sync.PacketSyncerToClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class TileEntityMAD extends TileEntityInventoryBase implements IEnergyReceiver, IPacketSyncerToClient {

    public static final int SLOT_SCRAB_IN = 0;
    public static final int SLOT_CAPACITOR_LV = 1;
    public static final int SLOT_TRANSISTOR = 2;
    public static final int SLOT_RESISTOR = 3;
    public static final int SLOT_CAPACITOR_HV = 4;
    public static final int SLOT_TRANSFORMER = 5;
    public static final int SLOT_ACID_IN = 6;
    public static final int SLOT_BUCKET_OUT = 7;
    public static final int SLOT_CB_IN = 8;
    public static final int SLOT_PCB_OUT = 9;
    public String name;
    public int sortTime, currSortTime;
    public int etchTime, currEtchTime;
    public int acidAmount, currAcidAmount;
    public EnergyStorage storage = new EnergyStorage(50000, 1000);
    private int lastEnergyStored = 0, lastSortTime, lastEtchTime, lastAcidAmount;
    private int nextOut;

    public TileEntityMAD() {
        slots = new ItemStack[10];
        name = "container." + Util.MOD_ID_LOWER + ".mad";

        sortTime = 100;
        etchTime = 1000;
        acidAmount = 4000;
        generateNextOutput(false);
    }

    private void generateNextOutput(boolean HV) {
        Random rng = new Random();
        if (HV) {
            nextOut = rng.nextInt(5) + 1;
        } else {
            nextOut = rng.nextInt(3) + 1;
        }
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) {

            if (storage.getEnergyStored() >= 20 && canSort()) {
                currSortTime++;
                storage.extractEnergy(20, false);
                if (currSortTime == sortTime) {
                    finishSort();
                    currSortTime = 0;
                    this.markDirty();
                }
            } else if (slots[SLOT_SCRAB_IN] == null) {
                currSortTime = 0;
            }

            if (storage.getEnergyStored() >= 100 && canEtch() && currAcidAmount > 0) {
                currEtchTime++;
                storage.extractEnergy(100, false);
                currAcidAmount -= 1;
                if (currEtchTime == etchTime) {
                    finishEtch();
                    currEtchTime = 0;
                    this.markDirty();
                }
            } else if (slots[SLOT_CB_IN] == null) {
                currEtchTime = 0;
            }

            if (slots[SLOT_ACID_IN] != null) {
                if (slots[SLOT_ACID_IN].getItem() == ItemManager.bucketAcid && currAcidAmount + 1000 <= acidAmount) {
                    currAcidAmount += 1000;
                    slots[SLOT_ACID_IN] = null;
                    if (slots[SLOT_BUCKET_OUT] == null) {
                        slots[SLOT_BUCKET_OUT] = new ItemStack(Items.bucket);
                    } else {
                        slots[SLOT_BUCKET_OUT].stackSize++;
                    }
                }
            }

            if (lastEnergyStored != storage.getEnergyStored() || lastSortTime != currSortTime || lastEtchTime != currEtchTime || lastAcidAmount != currAcidAmount) {
                lastEnergyStored = storage.getEnergyStored();
                lastSortTime = currSortTime;
                lastEtchTime = currEtchTime;
                lastAcidAmount = currAcidAmount;
                this.sendUpdate();
            }

        }
    }

    private boolean canSort() {
        if (slots[SLOT_SCRAB_IN] != null) {
            if (slots[SLOT_SCRAB_IN].getItem() == ItemManager.pcbScrapLV || slots[SLOT_SCRAB_IN].getItem() == ItemManager.pcbScrapHV) {
                if (canSlotHandle(nextOut, 1)) {
                    return true;
                } else {
                    generateNextOutput(slots[SLOT_SCRAB_IN].getItem() == ItemManager.pcbScrapHV);
                    return false;
                }
            }
        }
        return false;
    }

    private void finishSort() {
        if (slots[nextOut] == null) {
            switch (nextOut) {
                case SLOT_CAPACITOR_LV:
                    slots[nextOut] = new ItemStack(ItemManager.capacitorLV, 1);
                    break;
                case SLOT_CAPACITOR_HV:
                    slots[nextOut] = new ItemStack(ItemManager.capacitorHV, 1);
                    break;
                case SLOT_TRANSISTOR:
                    slots[nextOut] = new ItemStack(ItemManager.transistor, 1);
                    break;
                case SLOT_RESISTOR:
                    slots[nextOut] = new ItemStack(ItemManager.resistor, 1);
                    break;
                case SLOT_TRANSFORMER:
                    slots[nextOut] = new ItemStack(ItemManager.transformer, 1);
                    break;
            }
        } else {
            slots[nextOut].stackSize++;
        }
        slots[SLOT_SCRAB_IN].stackSize--;
        if (slots[SLOT_SCRAB_IN].stackSize <= 0) slots[SLOT_SCRAB_IN] = null;
        if (slots[SLOT_SCRAB_IN] != null) generateNextOutput(slots[SLOT_SCRAB_IN].getItem() == ItemManager.pcbScrapHV);
    }

    private boolean canEtch() {
        if (slots[SLOT_CB_IN] != null) {
            if (canSlotHandle(SLOT_PCB_OUT, 1)) {
                return true;
            }
        }
        return false;
    }

    private void finishEtch() {
        if (slots[SLOT_PCB_OUT] == null) {
            slots[SLOT_PCB_OUT] = new ItemStack(ItemManager.pcbEtched);
        } else {
            slots[SLOT_PCB_OUT].stackSize++;
        }
        slots[SLOT_CB_IN].stackSize--;
        if (slots[SLOT_CB_IN].stackSize <= 0) slots[SLOT_CB_IN] = null;
    }

    public boolean canSlotHandle(int slot, int amount) {
        if (slots[slot] != null) {
            ItemStack stack = slots[slot];
            return stack.stackSize + amount <= stack.getMaxStackSize();
        }
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        currSortTime = compound.getInteger("SortTime");
        currEtchTime = compound.getInteger("EtchTime");
        currAcidAmount = compound.getInteger("AcidAmount");
        storage.readFromNBT(compound);
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        compound.setInteger("SortTime", currSortTime);
        compound.setInteger("EtchTime", currEtchTime);
        compound.setInteger("AcidAmount", currAcidAmount);
        storage.writeToNBT(compound);
        super.writeToNBT(compound);
    }

    @Override
    public String getInventoryName() {
        return "MAD (Mechanical Arm Device)";
    }

    @Override
    public int getSizeInventory() {
        return slots.length;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack item) {
        if (slot == SLOT_SCRAB_IN && (item.getItem() == ItemManager.pcbScrapLV || item.getItem() == ItemManager.pcbScrapHV)) {
            return true;
        } else if (slot == SLOT_ACID_IN && item.getItem() == ItemManager.bucketAcid) {
            return true;
        }
        return false;
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
        return new int[]{storage.getEnergyStored(), currSortTime, currEtchTime, currAcidAmount};
    }

    @Override
    public void setValues(int[] values) {
        storage.setEnergyStored(values[0]);
        currSortTime = values[1];
        currEtchTime = values[2];
        currAcidAmount = values[3];
    }

    @Override
    public void sendUpdate() {
        PacketSyncerToClient.sendPacket(this);
    }

    public int getEnergyToScale(int i) {
        return storage.getEnergyStored() * i / storage.getMaxEnergyStored();
    }

    public int getSortTimeToScale(int i) {
        return currSortTime * i / sortTime;
    }

    public int getEtchTimeToScale(int i) {
        return currEtchTime * i / etchTime;
    }

    public int getAcidAmountToScale(int i) {
        return currAcidAmount * i / acidAmount;
    }
}
