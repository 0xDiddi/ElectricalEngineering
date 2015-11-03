package gmail.theultimatehose.ee.event;

import gmail.theultimatehose.ee.Main;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftEvent {

	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event) {
		if (event.crafting.isItemEqual(new ItemStack(Main.coil))) {
			event.crafting.setItemDamage(event.craftMatrix.getStackInSlot(4).getItemDamage()-8);
		}
	}
	
}
