package gmail.theultimatehose.electricalengineering.creativetab;

import gmail.theultimatehose.electricalengineering.ElectricalEngineering;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabGeneral extends CreativeTabs {

    public static CreativeTabGeneral instance = new CreativeTabGeneral();

    public CreativeTabGeneral() {
        super("general");
    }

    @Override
    public Item getTabIconItem() { return ElectricalEngineering.capacitorHV; }
}
