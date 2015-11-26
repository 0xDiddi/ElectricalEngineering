package theultimatehose.electricalengineering.gui;

import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.container.ContainerCoilWinder;
import theultimatehose.electricalengineering.tile.TileEntityCoilWinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import java.util.Collections;

public class GUICoilWinder extends GuiContainer {

    private static final ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/guiCoilWinder.png");
    private TileEntityCoilWinder tileCoilWinder;

    public GUICoilWinder(InventoryPlayer inventory, TileEntityCoilWinder tile) {
        super(new ContainerCoilWinder(inventory, tile));
        this.tileCoilWinder = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int x, int y) {
        String localMachineName = StatCollector.translateToLocal(this.tileCoilWinder.getInventoryName());
        this.fontRendererObj.drawString(localMachineName, (xSize - fontRendererObj.getStringWidth(localMachineName)) / 2, -10, 16777215);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par0, int par1, int par2) {
        GL11.glColor4f(1, 1, 1, 1);

        this.mc.getTextureManager().bindTexture(resLoc);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);

        if (this.tileCoilWinder.getEnergyStored(ForgeDirection.UNKNOWN) > 0) {
            int i = this.tileCoilWinder.getEnergyToScale(58);
            this.drawTexturedModalRect(this.guiLeft + 110, this.guiTop + 61, 176, 32, i, 11);
        }
        if (this.tileCoilWinder.currCoilAmount > 0) {
            int i = this.tileCoilWinder.getCoilAmountToScale(72);
            this.drawTexturedModalRect(this.guiLeft + 46, this.guiTop + 6 + 72 - i, 234, 72 - i, 16, i);
        }
        if (this.tileCoilWinder.currWindTime > 0) {
            int i = this.tileCoilWinder.getWindTimeToScale(26);
            this.drawTexturedModalRect(this.guiLeft + 70, this.guiTop + 5, 176, 43, i, 26);
        }
        if (this.tileCoilWinder.slots[TileEntityCoilWinder.SLOT_COIL_IN] != null) {
            if (this.tileCoilWinder.slots[TileEntityCoilWinder.SLOT_COIL_IN].getItemDamage() > 0) {
                int i = this.tileCoilWinder.getCoilProgressToScale(22);
                this.drawTexturedModalRect(this.guiLeft + 75, this.guiTop + 32, 196, 0, 16, i);
            }
        }
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
        String textEnergy = this.tileCoilWinder.storage.getEnergyStored() + "/" + this.tileCoilWinder.storage.getMaxEnergyStored() + " RF";
        if (x >= guiLeft + 110 && y >= guiTop + 61 && x <= guiLeft + 110 + 58 && y <= guiTop + 61 + 11) {
            this.func_146283_a(Collections.singletonList(textEnergy), x, y);
        }
        String textCoil = this.tileCoilWinder.currCoilAmount + "/" + this.tileCoilWinder.coilAmount;
        if (x >= guiLeft + 46 && y >= guiTop + 6 && x <= guiLeft + 46 + 16 && y <= guiTop + 6 + 72) {
            this.func_146283_a(Collections.singletonList(textCoil), x, y);
        }
    }
}
