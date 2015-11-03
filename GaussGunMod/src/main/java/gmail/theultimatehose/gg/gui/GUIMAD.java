package gmail.theultimatehose.gg.gui;

import gmail.theultimatehose.gg.container.ContainerMAD;
import gmail.theultimatehose.gg.tile.TileEntityMAD;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import java.util.Collections;

public class GUIMAD extends GuiContainer {

    private static final ResourceLocation resLoc = new ResourceLocation("gg", "textures/gui/guiMAD.png");
    private TileEntityMAD tileMAD;

    public GUIMAD(InventoryPlayer inventory, TileEntityMAD tile) {
        super(new ContainerMAD(inventory, tile));
        this.tileMAD = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawGuiContainerForegroundLayer(int x, int y) {
        String localMachineName = StatCollector.translateToLocal(this.tileMAD.getInventoryName());
        this.fontRendererObj.drawString(localMachineName, (xSize-fontRendererObj.getStringWidth(localMachineName))/2, -10, 16777215);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(resLoc);
        this.drawTexturedModalRect((this.width-this.xSize)/2, (this.height-this.ySize)/2, 0, 0, this.xSize, this.ySize);

        if (this.tileMAD.getEnergyStored(ForgeDirection.UNKNOWN)>0) {
            int i = this.tileMAD.getEnergyToScale(58);
            this.drawTexturedModalRect(this.guiLeft+110, this.guiTop+61, 176, 32, i, 11);
        }
        if (this.tileMAD.currSortTime>0) {
            int i = this.tileMAD.getSortTimeToScale(30);
            this.drawTexturedModalRect(this.guiLeft+10, this.guiTop+25, 176, 0, 20, i);
        }
        if (this.tileMAD.currEtchTime>0) {
            int i = this.tileMAD.getEtchTimeToScale(24);
            this.drawTexturedModalRect(this.guiLeft+83, this.guiTop+25, 196, 0, 16, i);
        }
        if (this.tileMAD.currAcidAmount>0) {
            int i = this.tileMAD.getAcidAmountToScale(50);
            this.drawTexturedModalRect(this.guiLeft+151, this.guiTop+6+50-i, 240, 0, 16, i);
        }
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
        String textEnergy = this.tileMAD.storage.getEnergyStored()+"/"+this.tileMAD.storage.getMaxEnergyStored()+" RF";
        if (x>=guiLeft+110 && y>=guiTop+61 && x<=guiLeft+110+58 && y<=guiTop+61+11) {
            this.func_146283_a(Collections.singletonList(textEnergy), x, y);
        }
        String textAcid = this.tileMAD.currAcidAmount+"/"+this.tileMAD.acidAmount+" mB";
        if (x>=guiLeft+151 && y>=guiTop+6 && x<=guiLeft+151+16 && y<=guiTop+6+50) {
            this.func_146283_a(Collections.singletonList(textAcid), x, y);
        }
    }
}
