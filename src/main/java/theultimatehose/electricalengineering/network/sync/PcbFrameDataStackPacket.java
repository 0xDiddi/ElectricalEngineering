package theultimatehose.electricalengineering.network.sync;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.network.PacketHandler;

public class PcbFrameDataStackPacket implements IMessage {

    private int xCoord, yCoord, zCoord;
    private int worldID;
    private int playerID;
    private String channelIn, channelOut;
    private String compare;
    private boolean[] rsIn, rsOut;

    @SuppressWarnings("unused")
    public PcbFrameDataStackPacket() {}

    public PcbFrameDataStackPacket(int x, int y, int z, World world, String channelIn, String channelOut, String compare, boolean[] rsIn, boolean[] rsOut) {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        this.worldID = world.provider.dimensionId;

        this.channelIn = channelIn;
        this.channelOut = channelOut;
        this.compare = compare;
        this.rsIn = rsIn;
        this.rsOut = rsOut;
    }

    public static void sendUpdate(PcbFrameDataStackPacket packet) {
        PacketHandler.theNetwork.sendToServer(packet);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.xCoord = buf.readInt();
        this.yCoord = buf.readInt();
        this.zCoord = buf.readInt();
        this.worldID = buf.readInt();

        this.channelIn = "";
        int textlength = buf.readInt();
        for (int i = 0; i < textlength; i++) {
            this.channelIn += buf.readChar();
        }
        this.channelOut = "";
        textlength = buf.readInt();
        for (int i = 0; i < textlength; i++) {
            this.channelOut += buf.readChar();
        }

        int cmp = buf.readInt();
        if (cmp == 0)
            this.compare = "AND";
        else if (cmp == 1)
            this.compare = "OR";
        else if (cmp == 2)
            this.compare = "XOR";

        rsIn = new boolean[6];
        for (int i = 0; i < 6; i++) {
            rsIn[i] = buf.readBoolean();
        }
        rsOut = new boolean[6];
        for (int i = 0; i < 6; i++) {
            rsOut[i] = buf.readBoolean();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.xCoord);
        buf.writeInt(this.yCoord);
        buf.writeInt(this.zCoord);
        buf.writeInt(this.worldID);

        buf.writeInt(channelIn.length());
        for (int i = 0; i < channelIn.length(); i++) {
            buf.writeChar(channelIn.charAt(i));
        }
        buf.writeInt(channelOut.length());
        for (int i = 0; i < channelOut.length(); i++) {
            buf.writeChar(channelOut.charAt(i));
        }

        if (compare.equals("AND"))
            buf.writeInt(0);
        else if (compare.equals("OR"))
            buf.writeInt(1);
        else if (compare.equals("XOR"))
            buf.writeInt(2);

        for (int i = 0; i < 6; i++) {
            buf.writeBoolean(rsIn[i]);
        }
        for (int i = 0; i < 6; i++) {
            buf.writeBoolean(rsOut[i]);
        }
    }

    public static class Handler implements IMessageHandler<PcbFrameDataStackPacket, IMessage> {

        @Override
        public IMessage onMessage(PcbFrameDataStackPacket message, MessageContext ctx) {
            World world = FMLClientHandler.instance().getClient().theWorld;
            TileEntity tile = world.getTileEntity(message.xCoord, message.yCoord, message.zCoord);
            if (tile instanceof IPcbFrameDataStackReciever) {
                ((IPcbFrameDataStackReciever)tile).onStackReceived(message.channelIn, message.channelOut, message.compare, message.rsIn, message.rsOut);
                Util.LOGGER.info("Packet successfully delivered to TE at " + message.xCoord + ", " + message.yCoord + ", " + message.zCoord);
            }
            return null;
        }
    }

}
