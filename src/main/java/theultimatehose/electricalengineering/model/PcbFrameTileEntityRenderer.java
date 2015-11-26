package theultimatehose.electricalengineering.model;

import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PcbFrameTileEntityRenderer extends TileEntitySpecialRenderer {

    private final ModelPcbFrame model;
    private final ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/model/PcbFrame.png");

    public PcbFrameTileEntityRenderer() {
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

        int meta = tileEntity.getBlockMetadata();

        GL11.glRotatef(180f, 1f, 0, 0);
        GL11.glRotatef(meta * 90f + 180f, 0, 1f, 0);

        TileEntityPcbFrame tile = (TileEntityPcbFrame) tileEntity;

        this.model.render(0.0625f, tile.isPowerModuleInstalled(), tile.isControlModuleInstalled(), tile.isRedstoneModuleInstalled(), tile.isRemoteModuleInstalled());

        GL11.glPopMatrix();
    }
}
