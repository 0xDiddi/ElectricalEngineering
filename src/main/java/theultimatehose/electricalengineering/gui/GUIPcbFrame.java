package theultimatehose.electricalengineering.gui;

import cpw.mods.fml.client.config.GuiCheckBox;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.container.ContainerPcbFrame;
import theultimatehose.electricalengineering.network.sync.PcbFrameDataStackPacket;
import theultimatehose.electricalengineering.tile.TileEntityPcbFrame;

import java.util.Collections;

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

        this.channelIn = tilePcbFrame.channelIn;
        this.channelOut = tilePcbFrame.channelOut;
        this.compare = tilePcbFrame.compare;
        this.rsIn = tilePcbFrame.rsIn;
        this.rsOut = tilePcbFrame.rsOut;
    }

    @Override
    public void initGui() {
        super.initGui();
        boolean isControllable = tilePcbFrame.isControlModuleInstalled();

        txtRcChannelIn = new GuiTextField(fontRendererObj, this.guiLeft + 7, this.guiTop + 27, 100, 20);
        txtRcChannelIn.setCanLoseFocus(true);
        txtRcChannelIn.setMaxStringLength(15);
        txtRcChannelIn.setText(channelIn);
        txtRcChannelIn.setEnabled(isControllable);

        chkbxAND = new GuiCheckBox(0, this.guiLeft + 6, this.guiTop + 55, "AND", compare.equals("AND"));
        chkbxAND.enabled = isControllable;
        buttonList.add(chkbxAND);
        chkbxOR = new GuiCheckBox(1, this.guiLeft + 42, this.guiTop + 55, "OR", compare.equals("OR"));
        chkbxOR.enabled = isControllable;
        buttonList.add(chkbxOR);
        chkbxXOR = new GuiCheckBox(2, this.guiLeft + 74, this.guiTop + 55, "XOR", compare.equals("XOR"));
        chkbxXOR.enabled = isControllable;
        buttonList.add(chkbxXOR);

        chkbxUpIn = new GuiCheckBox(3, this.guiLeft + 6, this.guiTop + 88, "Up", rsIn[1]);
        chkbxUpIn.enabled = isControllable;
        buttonList.add(chkbxUpIn);
        chkbxDownIn = new GuiCheckBox(4, this.guiLeft + 55, this.guiTop + 88, "Down", rsIn[0]);
        chkbxDownIn.enabled = isControllable;
        buttonList.add(chkbxDownIn);
        chkbxWestIn = new GuiCheckBox(5, this.guiLeft + 6, this.guiTop + 104, "West", rsIn[4]);
        chkbxWestIn.enabled = isControllable;
        buttonList.add(chkbxWestIn);
        chkbxEastIn = new GuiCheckBox(6, this.guiLeft + 55, this.guiTop + 104, "East", rsIn[5]);
        chkbxEastIn.enabled = isControllable;
        buttonList.add(chkbxEastIn);
        chkbxNorthIn = new GuiCheckBox(7, this.guiLeft + 6, this.guiTop + 120, "North", rsIn[2]);
        chkbxNorthIn.enabled = isControllable;
        buttonList.add(chkbxNorthIn);
        chkbxSouthIn = new GuiCheckBox(8, this.guiLeft + 55, this.guiTop + 120, "South", rsIn[3]);
        chkbxSouthIn.enabled = isControllable;
        buttonList.add(chkbxSouthIn);

        txtRcChannelOut = new GuiTextField(fontRendererObj, this.guiLeft + 137, this.guiTop + 27, 100, 20);
        txtRcChannelOut.setCanLoseFocus(true);
        txtRcChannelOut.setMaxStringLength(15);
        txtRcChannelOut.setText(channelOut);
        txtRcChannelOut.setEnabled(isControllable);

        chkbxUpOut = new GuiCheckBox(3, this.guiLeft + 137, this.guiTop + 65, "Up", rsOut[1]);
        chkbxUpOut.enabled = isControllable;
        buttonList.add(chkbxUpOut);
        chkbxDownOut = new GuiCheckBox(4, this.guiLeft + 186, this.guiTop + 65, "Down", rsOut[0]);
        chkbxDownOut.enabled = isControllable;
        buttonList.add(chkbxDownOut);
        chkbxWestOut = new GuiCheckBox(5, this.guiLeft + 137, this.guiTop + 81, "West", rsOut[4]);
        chkbxWestOut.enabled = isControllable;
        buttonList.add(chkbxWestOut);
        chkbxEastOut = new GuiCheckBox(6, this.guiLeft + 186, this.guiTop + 81, "East", rsOut[5]);
        chkbxEastOut.enabled = isControllable;
        buttonList.add(chkbxEastOut);
        chkbxNorthOut = new GuiCheckBox(7, this.guiLeft + 137, this.guiTop + 97, "North", rsOut[2]);
        chkbxNorthOut.enabled = isControllable;
        buttonList.add(chkbxNorthOut);
        chkbxSouthOut = new GuiCheckBox(8, this.guiLeft + 186, this.guiTop + 97, "South", rsOut[3]);
        chkbxSouthOut.enabled = isControllable;
        buttonList.add(chkbxSouthOut);
    }

    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawScreen(x, y, partialTicks);
        txtRcChannelIn.drawTextBox();
        txtRcChannelOut.drawTextBox();

        String textEnergy = this.tilePcbFrame.storage.getEnergyStored() + "/" + this.tilePcbFrame.storage.getMaxEnergyStored() + " RF";
        if (x >= guiLeft + 190 && y >= guiTop + 118 && x <= guiLeft + 190 + 58 && y <= guiTop + 118 + 11) {
            this.func_146283_a(Collections.singletonList(textEnergy), x, y);
        }
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
            if (keyCode == 28 || keyCode == 156) {
                this.passChanges();
            }
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        if (tilePcbFrame.isControlModuleInstalled())
            txtRcChannelIn.mouseClicked(x, y, button);
        if (chkbxAND.mousePressed(mc, x, y)) {
            chkbxAND.setIsChecked(true);
            chkbxOR.setIsChecked(false);
            chkbxXOR.setIsChecked(false);
            this.compare = "AND";
        } else if (chkbxOR.mousePressed(mc, x, y)) {
            chkbxAND.setIsChecked(false);
            chkbxOR.setIsChecked(true);
            chkbxXOR.setIsChecked(false);
            this.compare = "OR";
        } else if (chkbxXOR.mousePressed(mc, x, y)) {
            chkbxAND.setIsChecked(false);
            chkbxOR.setIsChecked(false);
            chkbxXOR.setIsChecked(true);
            this.compare = "XOR";
        }

        if (tilePcbFrame.isControlModuleInstalled())
            txtRcChannelOut.mouseClicked(x, y, button);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        this.passChanges();
    }

    public void passChanges() {
        channelIn = txtRcChannelIn.getText();
        tilePcbFrame.channelIn = this.channelIn;
        channelOut = txtRcChannelOut.getText();
        tilePcbFrame.channelOut = this.channelOut;
        tilePcbFrame.compare = this.compare;

        rsIn[0] = chkbxDownIn.isChecked();
        rsIn[1] = chkbxUpIn.isChecked();
        rsIn[2] = chkbxNorthIn.isChecked();
        rsIn[3] = chkbxSouthIn.isChecked();
        rsIn[4] = chkbxWestIn.isChecked();
        rsIn[5] = chkbxEastIn.isChecked();

        tilePcbFrame.rsIn = this.rsIn;

        rsOut[0] = chkbxDownOut.isChecked();
        rsOut[1] = chkbxUpOut.isChecked();
        rsOut[2] = chkbxNorthOut.isChecked();
        rsOut[3] = chkbxSouthOut.isChecked();
        rsOut[4] = chkbxWestOut.isChecked();
        rsOut[5] = chkbxEastOut.isChecked();

        tilePcbFrame.rsOut = this.rsOut;

        PcbFrameDataStackPacket packet = new PcbFrameDataStackPacket(tilePcbFrame.xCoord, tilePcbFrame.yCoord, tilePcbFrame.zCoord, tilePcbFrame.getWorldObj(),
                this.channelIn, this.channelOut, this.compare, this.rsIn, this.rsOut);
        PcbFrameDataStackPacket.sendUpdate(packet);
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

        if (!tilePcbFrame.isControlModuleInstalled()) {
            fontRendererObj.drawSplitString("Install a control module to configure", 100, 122, 100, 0xFF2020);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par0, int par1, int par2) {
        GL11.glColor4f(1, 1, 1, 1);

        this.mc.renderEngine.bindTexture(resLoc);
        this.drawTexturedModalRect((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);

        if (this.tilePcbFrame.getEnergyStored(ForgeDirection.UNKNOWN) > 0) {
            int i = this.tilePcbFrame.getEnergyToScale(58);
            this.drawTexturedModalRect(this.guiLeft + 190, this.guiTop + 118, 0, 228, i, 11);
        }
    }


}