package gmail.theultimatehose.electricalengineering.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.network.sync.IPacketSyncerToClient;
import gmail.theultimatehose.electricalengineering.network.sync.PacketSyncerToClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPcbFrame extends TileEntityInventoryBase implements IEnergyReceiver, IPacketSyncerToClient {

    public EnergyStorage storage = new EnergyStorage(50000, 1000);
    public String name;

    private boolean isPowerModuleInstalled, lastPwrInstalled;
    private boolean isControlModuleInstalled, lastCtrlInstalled;
    private boolean isRedstoneModuleInstalled, lastRsInstalled;
    private boolean isRemoteModuleInstalled, lastRcInstalled;
    private int lastMeta;

    public TileEntityPcbFrame() {
        slots = new ItemStack[0];
        name = "container." + Util.MOD_ID_LOWER + "pcbFrame";

    }

    @Override
    public void updateEntity() {
        if (isPowerModuleInstalled != lastPwrInstalled || isControlModuleInstalled != lastCtrlInstalled || isRedstoneModuleInstalled != lastRsInstalled || isRemoteModuleInstalled != lastRcInstalled || lastMeta != blockMetadata) {
            lastPwrInstalled = isPowerModuleInstalled;
            lastCtrlInstalled = isControlModuleInstalled;
            lastRsInstalled = isRedstoneModuleInstalled;
            lastRcInstalled = isRemoteModuleInstalled;
            lastMeta = blockMetadata;
            sendUpdate();
        }
    }

    public void setIsPowerModuleInstalled(boolean isPowerModuleInstalled) {
        this.isPowerModuleInstalled = isPowerModuleInstalled;
    }

    public boolean isPowerModuleInstalled() {
        return isPowerModuleInstalled;
    }

    public void setIsControlModuleInstalled(boolean isControlModuleInstalled) {
        this.isControlModuleInstalled = isControlModuleInstalled;
    }

    public boolean isControlModuleInstalled() {
        return isControlModuleInstalled;
    }

    public void setIsRedstoneModuleInstalled(boolean isRedstoneModuleInstalled) {
        this.isRedstoneModuleInstalled = isRedstoneModuleInstalled;
    }

    public boolean isRedstoneModuleInstalled() {
        return isRedstoneModuleInstalled;
    }

    public void setIsRemoteModuleInstalled(boolean isRemoteModuleInstalled) {
        this.isRemoteModuleInstalled = isRemoteModuleInstalled;
    }

    public boolean isRemoteModuleInstalled() {
        return isRemoteModuleInstalled;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("PWR_MOD", isPowerModuleInstalled);
        compound.setBoolean("CTRL_MOD", isControlModuleInstalled);
        compound.setBoolean("RS_MOD", isRedstoneModuleInstalled);
        compound.setBoolean("RC_MOD", isRemoteModuleInstalled);
        super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        isPowerModuleInstalled = compound.getBoolean("PWR_MOD");
        isControlModuleInstalled = compound.getBoolean("CTRL_MOD");
        isRedstoneModuleInstalled = compound.getBoolean("RS_MOD");
        isRemoteModuleInstalled = compound.getBoolean("RC_MOD");
        super.readFromNBT(compound);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public String getInventoryName() {
        return "Pcb Frame";
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return false;
    }

    @Override
    public int[] getValues() {
        return new int[] {(isPowerModuleInstalled ? 1 : 0), (isControlModuleInstalled ? 1 : 0), (isRedstoneModuleInstalled ? 1 : 0), (isRemoteModuleInstalled ? 1 : 0), blockMetadata};
    }

    @Override
    public void setValues(int[] values) {
        isPowerModuleInstalled = values[0] == 1;
        isControlModuleInstalled = values[1] == 1;
        isRedstoneModuleInstalled = values[2] == 1;
        isRemoteModuleInstalled = values[3] == 1;
        blockMetadata = values[4];
    }

    @Override
    public void sendUpdate() {
        PacketSyncerToClient.sendPacket(this);
    }

}
