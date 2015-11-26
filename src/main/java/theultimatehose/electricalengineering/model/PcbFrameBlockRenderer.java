package theultimatehose.electricalengineering.model;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.block.BlockManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class PcbFrameBlockRenderer implements ISimpleBlockRenderingHandler {

    private final ModelPcbFrame model;
    private final ResourceLocation resLoc = new ResourceLocation(Util.MOD_ID_LOWER, "textures/model/PcbFrame.png");

    public PcbFrameBlockRenderer() {
        this.model = new ModelPcbFrame();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        if (block == BlockManager.machinePcbFrame) {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(resLoc);
            GL11.glTranslatef(0, -0.45f, 0);
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glRotatef(90, 0, 1, 0);
            model.render(0.0625f, true, true, true, true);
            GL11.glPopMatrix();
        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return -1;
    }
}
