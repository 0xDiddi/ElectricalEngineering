package theultimatehose.electricalengineering.data.worlddata;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;
import theultimatehose.electricalengineering.Util;

public class WorldData extends WorldSavedData {

    public WorldData(String identifier) {
        super("worlddata." + Util.MOD_ID_LOWER + "." + identifier);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        //TODO: pass values to TileEntities
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        //TODO: read values from TileEntities
    }
}
