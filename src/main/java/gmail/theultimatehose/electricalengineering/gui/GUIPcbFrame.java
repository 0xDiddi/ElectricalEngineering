package gmail.theultimatehose.electricalengineering.gui;

import cpw.mods.fml.client.config.GuiCheckBox;
import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.container.ContainerPcbFrame;
import gmail.theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GUIPcbFrame extends GuiContainer {

    private static final ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/gui/guiPcbFrame.png");
    private TileEntityPcbFrame tilePcbFrame;

    private GuiTextField txtRcChannel;
    private GuiCheckBox chkbxAND, chkbxOR, chkbxXOR;
    private GuiCheckBox chkbxUp, chkbxDown, chkbxNorth, chkbxSouth, chkbxWest, chkbxEast;

    public GUIPcbFrame(InventoryPlayer inventory, TileEntityPcbFrame tile) {
        super(new ContainerPcbFrame(inventory, tile));
        this.tilePcbFrame = tile;
        this.xSize = 176;
        this.ySize = 222;
    }

    @Override
    public void initGui() {
        super.initGui();
        txtRcChannel = new GuiTextField(fontRendererObj, this.guiLeft + 7, this.guiTop + 27, 162, 20);
        txtRcChannel.setCanLoseFocus(true);
        txtRcChannel.setMaxStringLength(25);
        buttonList.add(chkbxAND = new GuiCheckBox(0, this.guiLeft + 10, this.guiTop + 52, "AND", true));
        buttonList.add(chkbxOR = new GuiCheckBox(1, this.guiLeft + 73, this.guiTop + 52, "OR", false));
        buttonList.add(chkbxXOR = new GuiCheckBox(2, this.guiLeft + 135,this.guiTop + 52, "XOR", false));
        buttonList.add(chkbxUp = new GuiCheckBox(3, this.guiLeft + 10, this.guiTop + 78, "Up", false));
        buttonList.add(chkbxDown = new GuiCheckBox(4, this.guiLeft + 65, this.guiTop + 78, "Down", false));
        buttonList.add(chkbxNorth = new GuiCheckBox(5, this.guiLeft + 120, this.guiTop + 78, "North", false));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        txtRcChannel.drawTextBox();
    }

    @Override
    public void updateScreen() {
        txtRcChannel.updateCursorCounter();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (txtRcChannel.isFocused()) {
            txtRcChannel.textboxKeyTyped(typedChar, keyCode);
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        txtRcChannel.mouseClicked(x, y, button);
        if (chkbxAND.mousePressed(mc, x, y)) {
            chkbxAND.setIsChecked(true);
            chkbxOR.setIsChecked(false);
            chkbxXOR.setIsChecked(false);
            Util.LOGGER.info("AND");
        } else if (chkbxOR.mousePressed(mc, x, y)) {
            chkbxAND.setIsChecked(false);
            chkbxOR.setIsChecked(true);
            chkbxXOR.setIsChecked(false);
            Util.LOGGER.info("OR");
        } else if (chkbxXOR.mousePressed(mc, x, y)) {
            chkbxAND.setIsChecked(false);
            chkbxOR.setIsChecked(false);
            chkbxXOR.setIsChecked(true);
            Util.LOGGER.info("XOR");
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String localMachineName = StatCollector.translateToLocal(this.tilePcbFrame.getInventoryName());
        this.fontRendererObj.drawString(localMachineName, (xSize - fontRendererObj.getStringWidth(localMachineName)) / 2, -8, 16777215);

        this.fontRendererObj.drawString("Input", 5, 5, 4210752);
        this.fontRendererObj.drawString("Remote Control Channel (Input)", 5, 18, 4210752);
        this.fontRendererObj.drawString("Redstone Input", 5, 68, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par0, int par1, int par2) {
        GL11.glColor4f(1, 1, 1, 1);

        this.mc.renderEngine.bindTexture(resLoc);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
    }
}