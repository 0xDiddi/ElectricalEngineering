package theultimatehose.electricalengineering;

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

    public static int[] baToIa(boolean[] ba) {
        int[] ia = new int[ba.length];
        for (int i = 0; i < ba.length; i++) {
            ia[i] = (ba[i]) ? 1 : 0;
        }
        return ia;
    }

    public static boolean[] iaToBa(int[] ia) {
        boolean[] ba = new boolean[ia.length];
        for (int i = 0; i < ia.length; i++) {
            ba[i] = ia[i] == 1;
        }
        return ba;
    }

}
