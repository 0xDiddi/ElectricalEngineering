package gmail.theultimatehose.electricalengineering.tile;

import cofh.api.energy.IEnergyReceiver;
import gmail.theultimatehose.electricalengineering.network.sync.IPacketSyncerToClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPcbFrame extends TileEntityInventoryBase implements IEnergyReceiver, IPacketSyncerToClient {

    private boolean isPowerModuleInstalled;
    private boolean isControlModuleInstalled;
    private boolean isRedstoneModuleInstalled;
    private boolean isRemoteModuleInstalled;

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
        return new int[0];
    }

    @Override
    public void setValues(int[] values) {

    }

    @Override
    public void sendUpdate() {

    }

}
