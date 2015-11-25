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

    public String channelIn, channelOut;
    public String compare;
    public boolean[] rsIn = new boolean[6], rsOut = new boolean[6];

    public TileEntityPcbFrame() {
        slots = new ItemStack[0];
        name = "container." + Util.MOD_ID_LOWER + "pcbFrame";

        channelIn = "";
        channelOut = "";
        compare = "AND";
        rsIn = new boolean[6];
        rsOut = new boolean[6];

    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (isPowerModuleInstalled != lastPwrInstalled || isControlModuleInstalled != lastCtrlInstalled || isRedstoneModuleInstalled != lastRsInstalled || isRemoteModuleInstalled != lastRcInstalled || lastMeta != blockMetadata) {
                lastPwrInstalled = isPowerModuleInstalled;
                lastCtrlInstalled = isControlModuleInstalled;
                lastRsInstalled = isRedstoneModuleInstalled;
                lastRcInstalled = isRemoteModuleInstalled;
                lastMeta = blockMetadata;
                sendUpdate();
            }
        }
    }

    @Override
    public void closeInventory() {
        super.closeInventory();
        if (worldObj.isRemote) {
            Util.LOGGER.info("channel in: " + channelIn);
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

        compound.setString("CH_IN", channelIn);
        compound.setString("CH_OUT", channelOut);
        compound.setString("CMP", compare);
        compound.setIntArray("RS_IN", Util.baToIa(rsIn));
        compound.setIntArray("RS_OUT", Util.baToIa(rsOut));
        super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        isPowerModuleInstalled = compound.getBoolean("PWR_MOD");
        isControlModuleInstalled = compound.getBoolean("CTRL_MOD");
        isRedstoneModuleInstalled = compound.getBoolean("RS_MOD");
        isRemoteModuleInstalled = compound.getBoolean("RC_MOD");

        channelIn = compound.getString("CH_IN");
        channelOut = compound.getString("CH_OUT");
        compare = compound.getString("CMP");
        rsIn = Util.iaToBa(compound.getIntArray("RS_IN"));
        rsOut = Util.iaToBa(compound.getIntArray("RS_OUT"));
        super.readFromNBT(compound);
    }

    @Override
    public void writeSyncDataToNBT(NBTTagCompound compound) {
        compound.setString("CH_IN", channelIn);
        compound.setString("CH_OUT", channelOut);
        compound.setString("CMP", compare);
        compound.setIntArray("RS_IN", Util.baToIa(rsIn));
        compound.setIntArray("RS_OUT", Util.baToIa(rsOut));
    }

    @Override
    public void readSyncDataFromNBT(NBTTagCompound compound) {
        channelIn = compound.getString("CH_IN");
        channelOut = compound.getString("CH_OUT");
        compare = compound.getString("CMP");
        rsIn = Util.iaToBa(compound.getIntArray("RS_IN"));
        rsOut = Util.iaToBa(compound.getIntArray("RS_OUT"));
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
        return from == ForgeDirection.DOWN;
    }

    @Override
    public int[] getValues() {
        int[] ia = new int[48];
        ia[0] = isPowerModuleInstalled ? 1 : 0;
        ia[1] = isControlModuleInstalled ? 1 : 0;
        ia[2] = isRedstoneModuleInstalled ? 1 : 0;
        ia[3] = isRemoteModuleInstalled ? 1 : 0;
        ia[4] = blockMetadata;

        for (int i = 0; i < 6; i++) {
            ia[i+5] = rsIn[i] ? 1 : 0;
        }
        for (int i = 0; i < 6; i++) {
            ia[i+11] = rsOut[i] ? 1 : 0;
        }

        char[] c = channelIn.toCharArray();
        for (int i = 0; i < 15; i++) {
            if (!(i >= c.length))
                ia[i+17] = c[i];
        }
        char[] d = channelOut.toCharArray();
        for (int i = 0; i < 15; i++) {
            if (!(i >= d.length))
                ia[i+32] = d[i];
        }

        if (compare.equals("AND"))
            ia[47] = 0;
        else if (compare.equals("OR"))
            ia[47] = 1;
        else if (compare.equals("XOR"))
            ia[47] = 2;

        return ia;
    }

    @Override
    public void setValues(int[] values) {
        isPowerModuleInstalled = values[0] == 1;
        isControlModuleInstalled = values[1] == 1;
        isRedstoneModuleInstalled = values[2] == 1;
        isRemoteModuleInstalled = values[3] == 1;
        blockMetadata = values[4];

        for (int i = 0; i < 6; i++) {
            rsIn[i] = values[i+5] == 1;
        }
        for (int i = 0; i < 6; i++) {
            rsOut[i] = values[i+11] == 1;
        }
        char[] c = new char[15];
        for (int i = 0; i < 15; i++) {
            c[i] = (char) values[i+32];
        }
        channelIn = String.valueOf(c);

        c = new char[15];
        for (int i = 0; i < 15; i++) {
            c[i] = (char) values[i+32];
        }
        channelOut = String.valueOf(c);

        if (values[47] == 0)
            compare = "AND";
        else if (values[47] == 1)
            compare = "OR";
        else if (values[47] == 2)
            compare = "XOR";
    }

    @Override
    public void sendUpdate() {
        PacketSyncerToClient.sendPacket(this);
    }

}
