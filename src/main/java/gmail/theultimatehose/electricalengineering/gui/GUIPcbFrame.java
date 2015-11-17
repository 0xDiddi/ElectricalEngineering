package gmail.theultimatehose.electricalengineering.gui;

import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.container.ContainerPcbFrame;
import gmail.theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIPcbFrame extends GuiContainer {

    private static final ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/guiPcbFrame.png");
    private TileEntityPcbFrame tilePcbFrame;

    public GUIPcbFrame(InventoryPlayer inventory, TileEntityPcbFrame tile) {
        super(new ContainerPcbFrame(inventory, tile));
        this.tilePcbFrame = tile;
        this.xSize = 176;
        this.ySize = 222;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par0, int par1, int par2) {
        GL11.glColor4f(1, 1, 1, 1);

        this.mc.renderEngine.bindTexture(resLoc);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
    }
}
