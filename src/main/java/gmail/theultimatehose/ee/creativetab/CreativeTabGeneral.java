package gmail.theultimatehose.ee.creativetab;

import gmail.theultimatehose.ee.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabGeneral extends CreativeTabs {

    public static CreativeTabGeneral instance = new CreativeTabGeneral();

    public CreativeTabGeneral() {
        super("general");
    }

    @Override
    public Item getTabIconItem() { return Main.capacitorHV; }
}
