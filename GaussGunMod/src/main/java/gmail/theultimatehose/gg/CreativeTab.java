package gmail.theultimatehose.gg;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

    public static CreativeTab instance = new CreativeTab();

    public CreativeTab() {
        super(Util.MOD_ID_LOWER);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Main.gaussGun, 1, 2049);
    }

    @Override
    public Item getTabIconItem() { return Main.gaussGun; }
}
