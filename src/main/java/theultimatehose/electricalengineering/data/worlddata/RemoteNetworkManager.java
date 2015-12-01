package theultimatehose.electricalengineering.data.worlddata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import theultimatehose.electricalengineering.tile.TileEntityPcbFrame;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoteNetworkManager {

    static NBTTagCompound networkList;
    static HashMap<String, ArrayList<PcbFrameDataEntry>> list;

    public static void init() {
        networkList = new NBTTagCompound();
        list = new HashMap<String, ArrayList<PcbFrameDataEntry>>(256);
    }

    public static void registerRemoteTile(int worldID, int x, int y, int z, String network) {
        if (!network.equals("")) {
            ArrayList<PcbFrameDataEntry> tmp = list.get(network);
            if (tmp == null) {
                tmp = new ArrayList<PcbFrameDataEntry>(256);
            }
                tmp.add(new PcbFrameDataEntry(worldID, x, y, z));
                list.put(network, tmp);
        }
    }

    public static void updateRemote(int worldID, int x, int y, int z, String oldNetwork, String newNetwork) {

        ArrayList<PcbFrameDataEntry> oldTmp = list.get(oldNetwork);
        PcbFrameDataEntry newData = new PcbFrameDataEntry(worldID, x, y, z);

        if (oldTmp != null) {
            for (PcbFrameDataEntry data : oldTmp) {
                if (data.hasSamePosition(newData)) {
                    oldTmp.remove(data);
                    list.put(oldNetwork, oldTmp);
                }
            }
        }

        ArrayList<PcbFrameDataEntry> newTmp = list.get(newNetwork);
        if (newTmp == null) {
            newTmp = new ArrayList<PcbFrameDataEntry>(256);
        }
            newTmp.add(newData);
            list.put(newNetwork, newTmp);

    }

    public static boolean isNetworkTileActive(String network) {

        if (!network.equals("")) {
            ArrayList<PcbFrameDataEntry> tmp = list.get(network);

            if (tmp == null) {
                return false;
            }

            for (PcbFrameDataEntry data : tmp) {
                World world = DimensionManager.getWorld(data.getWorldID());
                if (((TileEntityPcbFrame) world.getTileEntity(data.getX(), data.getY(), data.getZ())).isOutputtingRcSignal())
                    return true;
            }
        }

        return false;
    }

}
