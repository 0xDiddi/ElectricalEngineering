package gmail.theultimatehose.gg.proxy;

import gmail.theultimatehose.gg.Main;
import gmail.theultimatehose.gg.model.GaussGunRenderer;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderStuff() {

        MinecraftForgeClient.registerItemRenderer(Main.gaussGun, new GaussGunRenderer());

    }
}
