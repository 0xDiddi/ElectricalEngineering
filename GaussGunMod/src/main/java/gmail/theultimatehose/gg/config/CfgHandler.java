/*
 * This file ("CfgHandler.java") is part of the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://github.com/Ellpeck/ActuallyAdditions/blob/master/README.md
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015 Ellpeck
 */

package gmail.theultimatehose.gg.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gmail.theultimatehose.gg.Util;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class CfgHandler {

    public static Configuration config;

    public CfgHandler(File configFile) {
        Util.LOGGER.info("Retrieving Configuration...");

        Util.registerEvent(this);

        if (config == null) {
            config = new Configuration(configFile, true);
            loadConfig();
        }
    }

    private static void loadConfig() {
        CfgValues.defineConfigValues(config);

        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Util.MOD_ID)) {
            loadConfig();
        }
    }

}
