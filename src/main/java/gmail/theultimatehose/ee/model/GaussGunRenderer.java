package gmail.theultimatehose.ee.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class GaussGunRenderer implements IItemRenderer {

    protected ModelGaussGun model = new ModelGaussGun();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        switch (type) {
            case EQUIPPED: return true;
            case EQUIPPED_FIRST_PERSON: return true;
            case INVENTORY: return true;
            case ENTITY: return true;
            default: return false;
        }

    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        float scale;

        switch (type) {
            case EQUIPPED:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ee", "textures/model/GaussGun.png"));

                scale = 1f;

                GL11.glScalef(-1f, 1f, 1f);
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(-0.8f, 0.7f, 0.04f);
                GL11.glRotatef(140f, 0f, 0f, 1f);
                GL11.glRotatef(270f, 0f, 1f, 0f);

                this.model.render(0.0625f);

                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ee", "textures/model/GaussGun.png"));

                scale = 2.7f;

                GL11.glTranslatef(3f, 1f, 2f);
                GL11.glScalef(scale, scale, scale);
                GL11.glRotatef(90f, 0f, 0f, 1f);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glRotatef(235f, 1f, 0f, 0f);

                this.model.render(0.0625f);
                GL11.glPopMatrix();
                break;
            case INVENTORY:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ee", "textures/model/GaussGun.png"));

                scale = 16f;

                GL11.glScalef(-1f, 1f, 1f);
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(-0.47f, 0.48f, 0f);
                GL11.glRotatef(-30f, 1f, 0f, 1f);
                GL11.glRotatef(300f, 0f, 1f, 0f);
                GL11.glRotatef(10f, 0f, 0f, 1f);

                this.model.render(0.0625f);

                GL11.glPopMatrix();
            case ENTITY:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ee", "textures/model/GaussGun.png"));

                scale = 2f;

                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(0f, 0.3f, 0f);
                GL11.glRotatef(180f, 0f, 0f, 1f);

                this.model.render(0.0625f);

                GL11.glPopMatrix();
            default:
                break;
        }

    }
}
