package theultimatehose.electricalengineering.data.worlddata;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.HashMap;

public class RemoteNetworkManager {

    static NBTTagCompound networkList;
    static HashMap<String, String> list;

    public static void init() {
        networkList = new NBTTagCompound();
        list = new HashMap<String, String>(256);
    }

    public static void registerRemote(int x, int y, int z, String network) {

    }

}
