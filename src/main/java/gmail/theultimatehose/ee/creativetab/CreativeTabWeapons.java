package gmail.theultimatehose.ee.creativetab;

import gmail.theultimatehose.ee.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabWeapons extends CreativeTabs {

    public static CreativeTabWeapons instance = new CreativeTabWeapons();

    public CreativeTabWeapons() {
        super("weapons");
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Main.gaussGun, 1, 2049);
    }

    @Override
    public Item getTabIconItem() { return Main.gaussGun; }
}
