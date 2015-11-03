/*
 * This file ("CfgFloatValues.java") is inspired by the corresponding file in the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://github.com/Ellpeck/ActuallyAdditions/blob/master/README.md
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015 Ellpeck
 */

package gmail.theultimatehose.gg.config.values;

import gmail.theultimatehose.gg.config.CfgCat;

public enum CfgFloatValues {

    OLD_MACHINE_RARITY("Old machine: spawn chance", CfgCat.RARITY_VALUES, 1.0f, 0.5f, 1.5f, "How often old machines will spawn. " +
            "A lower value means a higher chance"),
    OLD_MACHINE_DROP_CHANCE("Old machine: drop chance", CfgCat.RARITY_VALUES, 1.0f, 0.5f, 1.5f, "How often the old machine drops something. " +
            "A lower value means a higher chance."),
    GAUSS_FUN_DAMAGE("Gauss Gun: damage", CfgCat.DAMAGE_VALUES, 1.0f, 0.5f, 1.5f, "The damage multiplier of the Gauss Gun. " +
            "A lower value means less damage");

    public final String name;
    public final String category;
    public final float defaultValue;
    public final float min;
    public final float max;
    public final String desc;

    public float currentValue;

    CfgFloatValues(String name, CfgCat category, float defValue, float min, float max, String desc) {
        this.name = name;
        this.category = category.name;
        defaultValue = defValue;
        this.min = min;
        this.max = max;
        this.desc = desc;
    }

    public float getValue() {
        return this.currentValue;
    }

}
