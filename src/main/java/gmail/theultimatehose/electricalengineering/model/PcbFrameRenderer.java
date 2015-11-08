package gmail.theultimatehose.electricalengineering.model;

import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PcbFrameRenderer extends TileEntitySpecialRenderer {

    private final ModelPcbFrame model;
    private final ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/model/PcbFrame.png");

    public PcbFrameRenderer() {
        this.model = new ModelPcbFrame();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        float worldX = (float) x + 0.5F;
        float worldY = (float) y + 0.06F;
        float worldZ = (float) z + 0.5F;
        GL11.glTranslatef(worldX, worldY, worldZ);
        Minecraft.getMinecraft().renderEngine.bindTexture(resLoc);

        int meta = tileEntity.blockMetadata;

        GL11.glRotatef(180f, 1f, 0, 0);
        GL11.glRotatef(meta * 90f + 180f, 0, 1f, 0);

        TileEntityPcbFrame tile = (TileEntityPcbFrame) tileEntity.getWorldObj().getTileEntity(Math.round(worldX), Math.round(worldY), Math.round(worldZ));

        //this.model.render(0.0625f, tile.isPowerModuleInstalled(), tile.isControlModuleInstalled(), tile.isRedstoneModuleInstalled(), tile.isRemoteModuleInstalled());
        this.model.render(0.0625f, false, false, false, false);

        GL11.glPopMatrix();
    }
}
