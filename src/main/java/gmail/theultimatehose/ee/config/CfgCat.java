/*
 * This file ("CfgCat.java") is part of the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://github.com/Ellpeck/ActuallyAdditions/blob/master/README.md
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015 Ellpeck
 */

package gmail.theultimatehose.ee.config;

public enum CfgCat {

    RARITY_VALUES("Rarity values", "How rare things are"),
    DAMAGE_VALUES("Damage values", "How much Damage things do");

    public final String name;
    public final String comment;

    CfgCat(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

}
