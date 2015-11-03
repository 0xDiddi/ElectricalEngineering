package gmail.theultimatehose.ee.gui;

import gmail.theultimatehose.ee.Main;
import gmail.theultimatehose.ee.container.ContainerCoilWinder;
import gmail.theultimatehose.ee.container.ContainerMAD;
import gmail.theultimatehose.ee.container.ContainerSolder;
import gmail.theultimatehose.ee.tile.TileEntityCoilWinder;
import gmail.theultimatehose.ee.tile.TileEntityMAD;
import gmail.theultimatehose.ee.tile.TileEntitySolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

	public static final int SOLDER_ID = 0;
	public static final int MAD_ID = 1;
	public static final int WINDER_ID = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case SOLDER_ID:
				TileEntitySolder tileSolder = (TileEntitySolder)world.getTileEntity(x, y, z);
				return new ContainerSolder(player.inventory, tileSolder);
            case MAD_ID:
                TileEntityMAD tileMAD = (TileEntityMAD)world.getTileEntity(x, y, z);
                return new ContainerMAD(player.inventory, tileMAD);
            case WINDER_ID:
                TileEntityCoilWinder tileWinder = (TileEntityCoilWinder)world.getTileEntity(x, y, z);
                return new ContainerCoilWinder(player.inventory, tileWinder);
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case SOLDER_ID:
				TileEntitySolder tileSolder = (TileEntitySolder)world.getTileEntity(x, y, z);
				return new GUISolder(player.inventory, tileSolder);
            case MAD_ID:
                TileEntityMAD tileMAD = (TileEntityMAD)world.getTileEntity(x, y, z);
                return new GUIMAD(player.inventory, tileMAD);
            case WINDER_ID:
                TileEntityCoilWinder tileWinder = (TileEntityCoilWinder)world.getTileEntity(x, y, z);
                return new GUICoilWinder(player.inventory, tileWinder);
			default:
				return null;
		}
	}
	
	public static void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
}
