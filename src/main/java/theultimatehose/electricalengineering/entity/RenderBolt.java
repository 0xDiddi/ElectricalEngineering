package theultimatehose.electricalengineering.entity;

import theultimatehose.electricalengineering.Util;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderBolt extends Render {

    private static final ResourceLocation arrowTextures = new ResourceLocation(Util.MOD_ID_LOWER, "textures/entity/bolt.png");
    private static final String __OBFID = "CL_00000978";

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T> extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityBolt bolt, double par1, double par2, double par3, float par4, float par5) {
        this.bindEntityTexture(bolt);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par1, (float) par2, (float) par3);
        GL11.glRotatef(bolt.prevRotationYaw + (bolt.rotationYaw - bolt.prevRotationYaw) * par5 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(bolt.prevRotationPitch + (bolt.rotationPitch - bolt.prevRotationPitch) * par5, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        byte b0 = 0;
        float u3 = 0.0F;
        float u4 = 0.5F;
        float v3 = (float) (0 + b0 * 10) / 32.0F;
        float v4 = (float) (5 + b0 * 10) / 32.0F;
        float u1 = 0.0F;
        float u2 = 0.15625F;
        float v1 = (float) (5 + b0 * 10) / 32.0F;
        float v2 = (float) (10 + b0 * 10) / 32.0F;
        float scale = 0.05625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(scale, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double) u1, (double) v1);
        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double) u2, (double) v1);
        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double) u2, (double) v2);
        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double) u1, (double) v2);
        tessellator.draw();
        GL11.glNormal3f(-scale, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double) u1, (double) v1);
        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double) u2, (double) v1);
        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double) u2, (double) v2);
        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double) u1, (double) v2);
        tessellator.draw();

        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, scale);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double) u3, (double) v3);
            tessellator.addVertexWithUV(8.0D, -2.0D, 0.0D, (double) u4, (double) v3);
            tessellator.addVertexWithUV(8.0D, 2.0D, 0.0D, (double) u4, (double) v4);
            tessellator.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double) u3, (double) v4);
            tessellator.draw();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBolt p_110775_1_) {
        return arrowTextures;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return this.getEntityTexture((EntityBolt) p_110775_1_);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double par1, double par2, double par3, float par4, float par5) {
        this.doRender((EntityBolt) entity, par1, par2, par3, par4, par5);
    }

}
