package theultimatehose.electricalengineering.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import theultimatehose.electricalengineering.ElectricalEngineering;
import theultimatehose.electricalengineering.container.ContainerCoilWinder;
import theultimatehose.electricalengineering.container.ContainerMAD;
import theultimatehose.electricalengineering.container.ContainerPcbFrame;
import theultimatehose.electricalengineering.container.ContainerSolder;
import theultimatehose.electricalengineering.tile.TileEntityCoilWinder;
import theultimatehose.electricalengineering.tile.TileEntityMAD;
import theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import theultimatehose.electricalengineering.tile.TileEntitySolder;
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
