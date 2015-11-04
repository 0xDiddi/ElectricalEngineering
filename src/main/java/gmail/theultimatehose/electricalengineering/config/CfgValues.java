/*
 * This file ("CfgValues.java") is part of the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://github.com/Ellpeck/ActuallyAdditions/blob/master/README.md
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015 Ellpeck
 */

package gmail.theultimatehose.electricalengineering.config;

import gmail.theultimatehose.electricalengineering.config.values.CfgFloatValues;
import net.minecraftforge.common.config.Configuration;

public class CfgValues {

    public static CfgFloatValues[] floatConfig = CfgFloatValues.values();

    public static void defineConfigValues(Configuration config) {

        for (CfgFloatValues currConf : floatConfig) {
            currConf.currentValue = ((float) config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc, currConf.min, currConf.max).getDouble());
        }

    }

}
