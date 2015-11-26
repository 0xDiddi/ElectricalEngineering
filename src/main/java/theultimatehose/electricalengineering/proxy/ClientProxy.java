package theultimatehose.electricalengineering.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import theultimatehose.electricalengineering.entity.EntityBolt;
import theultimatehose.electricalengineering.entity.RenderBolt;
import theultimatehose.electricalengineering.item.ItemManager;
import theultimatehose.electricalengineering.model.GaussGunRenderer;
import theultimatehose.electricalengineering.model.PcbFrameTileEntityRenderer;
import theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderStuff() {

        MinecraftForgeClient.registerItemRenderer(ItemManager.gaussGun, new GaussGunRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, new RenderBolt());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPcbFrame.class, new PcbFrameTileEntityRenderer());

    }
}
