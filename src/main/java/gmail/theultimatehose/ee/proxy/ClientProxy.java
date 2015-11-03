package gmail.theultimatehose.ee.proxy;

import gmail.theultimatehose.ee.Main;
import gmail.theultimatehose.ee.model.GaussGunRenderer;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderStuff() {

        MinecraftForgeClient.registerItemRenderer(Main.gaussGun, new GaussGunRenderer());

    }
}
