package gmail.theultimatehose.electricalengineering.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import gmail.theultimatehose.electricalengineering.ElectricalEngineering;
import net.minecraft.item.ItemStack;

public class CraftEvent {

    @SubscribeEvent
    public void onCrafted(ItemCraftedEvent event) {
        if (event.crafting.isItemEqual(new ItemStack(ElectricalEngineering.coil))) {
            event.crafting.setItemDamage(event.craftMatrix.getStackInSlot(4).getItemDamage() - 8);
        }
    }

}
