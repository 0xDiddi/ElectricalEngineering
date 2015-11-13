package gmail.theultimatehose.electricalengineering.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import gmail.theultimatehose.electricalengineering.entity.EntityBolt;
import gmail.theultimatehose.electricalengineering.entity.RenderBolt;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
import gmail.theultimatehose.electricalengineering.model.GaussGunRenderer;
import gmail.theultimatehose.electricalengineering.model.PcbFrameTileEntityRenderer;
import gmail.theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderStuff() {

        MinecraftForgeClient.registerItemRenderer(ItemManager.gaussGun, new GaussGunRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, new RenderBolt());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPcbFrame.class, new PcbFrameTileEntityRenderer());

    }
}
