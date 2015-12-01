package theultimatehose.electricalengineering.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.data.worlddata.RemoteNetworkManager;
import theultimatehose.electricalengineering.network.sync.IPacketSyncerToClient;
import theultimatehose.electricalengineering.network.sync.IPcbFrameDataStackReciever;
import theultimatehose.electricalengineering.network.sync.PacketSyncerToClient;

public class TileEntityPcbFrame extends TileEntityInventoryBase implements IEnergyReceiver, IPacketSyncerToClient, IPcbFrameDataStackReciever {

    public EnergyStorage storage = new EnergyStorage(50000, 1000);
    public String name;

    private boolean isPowerModuleInstalled, lastPwrInstalled;
    private boolean isControlModuleInstalled, lastCtrlInstalled;
    private boolean isRedstoneModuleInstalled, lastRsInstalled;
    private boolean isRemoteModuleInstalled, lastRcInstalled;
    private int lastMeta;

    private String channelIn, lastChannelIn, channelOut, lastchannelOut;
    private String compare, lastCompare;
    private boolean[] rsIn = new boolean[6], lastRsIn = new boolean[6], rsOut = new boolean[6], lastRsOut = new boolean[6];
    private boolean doOutput;

    public TileEntityPcbFrame() {
        slots = new ItemStack[0];
        name = "container." + Util.MOD_ID_LOWER + "pcbFrame";

        channelIn = "";
        channelOut = "";
        compare = "AND";
        rsIn = new boolean[6];
        rsOut = new boolean[6];
        doOutput = false;

        /**Useless, for demonstration only. */
        RemoteNetworkManager.registerRemoteTile(worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, "");

    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            boolean flag = false;

            for (int i = 0; i < 6; i++) {
                if (rsIn[i] && isSidePowered(ForgeDirection.getOrientation(i))) {
                    doOutput = true;
                    flag = true;
                }
            }

            if (doOutput && isRedstoneModuleInstalled) {
                for (int i = 0; i < 6; i++) {
                    if (rsOut[i]) {
                        setSidePowered(ForgeDirection.getOrientation(i));
                        flag = true;
                    }
                }
            }

            if (isPowerModuleInstalled != lastPwrInstalled || isControlModuleInstalled != lastCtrlInstalled || isRedstoneModuleInstalled != lastRsInstalled || isRemoteModuleInstalled != lastRcInstalled || lastMeta != blockMetadata ||
                    !channelIn.equals(lastChannelIn) || !channelOut.equals(lastchannelOut) || !compare.equals(lastCompare) || rsIn != lastRsIn || rsOut != lastRsOut) {
                lastPwrInstalled = isPowerModuleInstalled;
                lastCtrlInstalled = isControlModuleInstalled;
                lastRsInstalled = isRedstoneModuleInstalled;
                lastRcInstalled = isRemoteModuleInstalled;
                lastMeta = blockMetadata;
                lastChannelIn = channelIn;
                lastchannelOut = channelOut;
                lastCompare = compare;
                lastRsIn = rsIn;
                lastRsOut = rsOut;
                sendUpdate();
                flag = true;
            }

            if (flag)
                this.markDirty();

        }
    }

    public boolean isSidePowered(ForgeDirection side) {
        Block block = worldObj.getBlock(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);

        if (block instanceof BlockRedstoneWire) {
            int i = worldObj.getBlockMetadata(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
            if (i > 0)
                return true;
        }
        return false;
    }

    public void setSidePowered(ForgeDirection side) {
        Block block = worldObj.getBlock(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);

        if (block instanceof BlockRedstoneWire) {
            worldObj.setBlockMetadataWithNotify(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ, 15, 3);
        }

    }

    public boolean isOutputtingRcSignal() {
        return doOutput && isRemoteModuleInstalled;
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
    public boolean isUseableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
    }

    @Override
    public String getInventoryName() {
        return "Pcb Frame";
    }

    public int getEnergyToScale(int i) {
        return storage.getEnergyStored() * i / storage.getMaxEnergyStored();
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if (from ==ForgeDirection.DOWN && this.isPowerModuleInstalled)
            return storage.receiveEnergy(maxReceive, simulate);
        return 0;
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
        return from == ForgeDirection.DOWN && this.isPowerModuleInstalled;
    }

    @Override
    public int[] getValues() {
        int[] ia = new int[51];
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

        ia[17] = channelIn.length();
        char[] c = channelIn.toCharArray();
        for (int i = 0; i < 15; i++) {
            if (!(i >= c.length))
                ia[i+18] = c[i];
            else
                ia[i+18] = 0;
        }
        ia[33] = channelOut.length();
        char[] d = channelOut.toCharArray();
        for (int i = 0; i < 15; i++) {
            if (!(i >= d.length))
                ia[i+34] = d[i];
            else
                ia[i+34] = 0;
        }

        if (compare.equals("AND"))
            ia[49] = 0;
        else if (compare.equals("OR"))
            ia[49] = 1;
        else if (compare.equals("XOR"))
            ia[49] = 2;

        ia[50] = storage.getEnergyStored();

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

        char[] c = new char[values[17]];
        for (int i = 0; i < 15; i++) {
            if (values[i+18] != 0)
                c[i] = (char) values[i+18];
        }
        channelIn = String.valueOf(c);

        c = new char[values[33]];
        for (int i = 0; i < 15; i++) {
            if (values[i+34] != 0)
                c[i] = (char) values[i+34];
        }
        channelOut = String.valueOf(c);

        if (values[49] == 0)
            compare = "AND";
        else if (values[49] == 1)
            compare = "OR";
        else if (values[49] == 2)
            compare = "XOR";

        storage.setEnergyStored(values[50]);
    }

    @Override
    public void sendUpdate() {
        PacketSyncerToClient.sendPacket(this);
    }

    @Override
    public void onStackReceived(String channelIn, String channelOut, String compare, boolean[] rsIn, boolean[] rsOut) {
        this.channelIn = channelIn;
        this.channelOut = channelOut;
        this.compare = compare;
        this.rsIn = rsIn;
        this.rsOut = rsOut;
    }
}
