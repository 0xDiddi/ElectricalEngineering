package gmail.theultimatehose.electricalengineering.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import gmail.theultimatehose.electricalengineering.ElectricalEngineering;
import gmail.theultimatehose.electricalengineering.container.ContainerCoilWinder;
import gmail.theultimatehose.electricalengineering.container.ContainerMAD;
import gmail.theultimatehose.electricalengineering.container.ContainerPcbFrame;
import gmail.theultimatehose.electricalengineering.container.ContainerSolder;
import gmail.theultimatehose.electricalengineering.tile.TileEntityCoilWinder;
import gmail.theultimatehose.electricalengineering.tile.TileEntityMAD;
import gmail.theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import gmail.theultimatehose.electricalengineering.tile.TileEntitySolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int SOLDER_ID = 0;
    public static final int MAD_ID = 1;
    public static final int WINDER_ID = 2;
    public static final int PCB_FRAME_ID = 3;

    public static void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ElectricalEngineering.instance, new GuiHandler());
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case SOLDER_ID:
                TileEntitySolder tileSolder = (TileEntitySolder) world.getTileEntity(x, y, z);
                return new ContainerSolder(player.inventory, tileSolder);
            case MAD_ID:
                TileEntityMAD tileMAD = (TileEntityMAD) world.getTileEntity(x, y, z);
                return new ContainerMAD(player.inventory, tileMAD);
            case WINDER_ID:
                TileEntityCoilWinder tileWinder = (TileEntityCoilWinder) world.getTileEntity(x, y, z);
                return new ContainerCoilWinder(player.inventory, tileWinder);
            case PCB_FRAME_ID:
                TileEntityPcbFrame tilePcbFrame = (TileEntityPcbFrame) world.getTileEntity(x, y, z);
                return new ContainerPcbFrame(player.inventory, tilePcbFrame);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case SOLDER_ID:
                TileEntitySolder tileSolder = (TileEntitySolder) world.getTileEntity(x, y, z);
                return new GUISolder(player.inventory, tileSolder);
            case MAD_ID:
                TileEntityMAD tileMAD = (TileEntityMAD) world.getTileEntity(x, y, z);
                return new GUIMAD(player.inventory, tileMAD);
            case WINDER_ID:
                TileEntityCoilWinder tileWinder = (TileEntityCoilWinder) world.getTileEntity(x, y, z);
                return new GUICoilWinder(player.inventory, tileWinder);
            case PCB_FRAME_ID:
                TileEntityPcbFrame tilePcbFrame = (TileEntityPcbFrame) world.getTileEntity(x, y, z);
                return new GUIPcbFrame(player.inventory, tilePcbFrame);
            default:
                return null;
        }
    }

}
