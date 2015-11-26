package theultimatehose.electricalengineering.gui;

import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.container.ContainerSolder;
import theultimatehose.electricalengineering.tile.TileEntitySolder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import java.util.Collections;

public class GUISolder extends GuiContainer {

    private static final ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/guiSolderingTable.png");
    private TileEntitySolder tileSolder;

    public GUISolder(InventoryPlayer inventory, TileEntitySolder tile) {
        super(new ContainerSolder(inventory, tile));
        this.tileSolder = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int x, int y) {
        String localMachineName = StatCollector.translateToLocal(this.tileSolder.getInventoryName());
        this.fontRendererObj.drawString(localMachineName, (xSize - fontRendererObj.getStringWidth(localMachineName)) / 2, -10, 16777215);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1, 1, 1, 1);

        this.mc.getTextureManager().bindTexture(resLoc);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);

        if (this.tileSolder.getEnergyStored(ForgeDirection.UNKNOWN) > 0) {
            int i = this.tileSolder.getEnergyToScale(58);
            this.drawTexturedModalRect(this.guiLeft + 110, this.guiTop + 61, 176, 32, i, 11);
        }
        if (this.tileSolder.currDesolderTime > 0) {
            int i = this.tileSolder.getDesolderTimeToScale(23);
            this.drawTexturedModalRect(this.guiLeft + 12, this.guiTop + 27, 200, 0, 24, i);
        }
        if (this.tileSolder.currSolderTime > 0) {
            int i = this.tileSolder.getSolderTimeToScale(23);
            this.drawTexturedModalRect(this.guiLeft + 107, this.guiTop + 15, 176, 15, i, 16);
        }
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
        String textEnergy = this.tileSolder.storage.getEnergyStored() + "/" + this.tileSolder.storage.getMaxEnergyStored() + " RF";
        if (x >= guiLeft + 110 && y >= guiTop + 61 && x <= guiLeft + 110 + 58 && y <= guiTop + 61 + 11) {
            this.func_146283_a(Collections.singletonList(textEnergy), x, y);
        }
    }

}
