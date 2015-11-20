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

    private GuiTextField txtRcChannelIn;
    private GuiCheckBox chkbxAND, chkbxOR, chkbxXOR;
    private GuiCheckBox chkbxUpIn, chkbxDownIn, chkbxNorthIn, chkbxSouthIn, chkbxWestIn, chkbxEastIn;

    private GuiTextField txtRcChannelOut;
    private GuiCheckBox chkbxUpOut, chkbxDownOut, chkbxNorthOut, chkbxSouthOut, chkbxWestOut, chkbxEastOut;

    private String channelIn, channelOut;
    private String compare;
    private boolean[] rsIn = new boolean[6], rsOut = new boolean[6];

    public GUIPcbFrame(InventoryPlayer inventory, TileEntityPcbFrame tile) {
        super(new ContainerPcbFrame(inventory, tile));
        this.tilePcbFrame = tile;
        this.xSize = 256;
        this.ySize = 227;
    }

    @Override
    public void initGui() {
        super.initGui();
        txtRcChannelIn = new GuiTextField(fontRendererObj, this.guiLeft + 7, this.guiTop + 27, 100, 20);
        txtRcChannelIn.setCanLoseFocus(true);
        txtRcChannelIn.setMaxStringLength(15);
        buttonList.add(chkbxAND = new GuiCheckBox(0, this.guiLeft + 6, this.guiTop + 55, "AND", true));
        buttonList.add(chkbxOR = new GuiCheckBox(1, this.guiLeft + 42, this.guiTop + 55, "OR", false));
        buttonList.add(chkbxXOR = new GuiCheckBox(2, this.guiLeft + 74, this.guiTop + 55, "XOR", false));

        buttonList.add(chkbxUpIn = new GuiCheckBox(3, this.guiLeft + 6, this.guiTop + 88, "Up", false));
        buttonList.add(chkbxDownIn = new GuiCheckBox(4, this.guiLeft + 55, this.guiTop + 88, "Down", false));
        buttonList.add(chkbxWestIn = new GuiCheckBox(5, this.guiLeft + 6, this.guiTop + 104, "West", false));
        buttonList.add(chkbxEastIn = new GuiCheckBox(6, this.guiLeft + 55, this.guiTop + 104, "East", false));
        buttonList.add(chkbxNorthIn = new GuiCheckBox(7, this.guiLeft + 6, this.guiTop + 120, "North", false));
        buttonList.add(chkbxSouthIn = new GuiCheckBox(8, this.guiLeft + 55, this.guiTop + 120, "South", false));

        txtRcChannelOut = new GuiTextField(fontRendererObj, this.guiLeft + 137, this.guiTop + 27, 100, 20);
        txtRcChannelOut.setCanLoseFocus(true);
        txtRcChannelOut.setMaxStringLength(15);

        buttonList.add(chkbxUpOut = new GuiCheckBox(3, this.guiLeft + 137, this.guiTop + 65, "Up", false));
        buttonList.add(chkbxDownOut = new GuiCheckBox(4, this.guiLeft + 186, this.guiTop + 65, "Down", false));
        buttonList.add(chkbxWestOut = new GuiCheckBox(5, this.guiLeft + 137, this.guiTop + 81, "West", false));
        buttonList.add(chkbxEastOut = new GuiCheckBox(6, this.guiLeft + 186, this.guiTop + 81, "East", false));
        buttonList.add(chkbxNorthOut = new GuiCheckBox(7, this.guiLeft + 137, this.guiTop + 97, "North", false));
        buttonList.add(chkbxSouthOut = new GuiCheckBox(8, this.guiLeft + 186, this.guiTop + 97, "South", false));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        txtRcChannelIn.drawTextBox();
        txtRcChannelOut.drawTextBox();
    }

    @Override
    public void updateScreen() {
        txtRcChannelIn.updateCursorCounter();
        txtRcChannelOut.updateCursorCounter();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (txtRcChannelIn.isFocused()) {
            txtRcChannelIn.textboxKeyTyped(typedChar, keyCode);
            if (keyCode == 28 || keyCode == 156) {
                channelIn = txtRcChannelIn.getText();
                tilePcbFrame.channelIn = this.channelIn;
            }
        } else if (txtRcChannelOut.isFocused()) {
            txtRcChannelOut.textboxKeyTyped(typedChar, keyCode);
            if (keyCode == 28 || keyCode == 156) {
                channelOut = txtRcChannelOut.getText();
                tilePcbFrame.channelOut = this.channelOut;
            }
        } else {
            super.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        txtRcChannelIn.mouseClicked(x, y, button);
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

        txtRcChannelOut.mouseClicked(x, y, button);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String localMachineName = StatCollector.translateToLocal(this.tilePcbFrame.getInventoryName());
        this.fontRendererObj.drawString(localMachineName, (xSize - fontRendererObj.getStringWidth(localMachineName)) / 2, -8, 16777215);

        this.fontRendererObj.drawString("Input", 5, 5, 4210752);
        this.fontRendererObj.drawString("RC Channel (In)", 5, 18, 4210752);
        this.fontRendererObj.drawString("Redstone Input", 5, 78, 4210752);

        this.fontRendererObj.drawString("Output", 135, 5, 4210752);
        this.fontRendererObj.drawString("RC Channel (Out)", 135, 18, 4210752);
        this.fontRendererObj.drawString("Redstone Output", 135, 55, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par0, int par1, int par2) {
        GL11.glColor4f(1, 1, 1, 1);

        this.mc.renderEngine.bindTexture(resLoc);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
    }
}