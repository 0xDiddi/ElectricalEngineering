package gmail.theultimatehose.ee.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import gmail.theultimatehose.ee.Main;
import gmail.theultimatehose.ee.entity.EntityBolt;
import gmail.theultimatehose.ee.entity.RenderBolt;
import gmail.theultimatehose.ee.model.GaussGunRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderStuff() {

        MinecraftForgeClient.registerItemRenderer(Main.gaussGun, new GaussGunRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, new RenderBolt());

    }
}
