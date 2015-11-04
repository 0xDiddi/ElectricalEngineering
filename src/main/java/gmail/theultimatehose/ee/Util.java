package gmail.theultimatehose.ee;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {

    public static final String VERSION = "1.7.10-0.5";

    public static final String MOD_ID = "ElectricalEngineering";
    public static final String MOD_ID_LOWER = MOD_ID.toLowerCase();

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void registerEvent(Object o) {
        MinecraftForge.EVENT_BUS.register(o);
        FMLCommonHandler.instance().bus().register(o);
    }

}
