package gmail.theultimatehose.electricalengineering.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPcbFrame - TheUltimateHose
 * Created using Tabula 4.1.1
 */
public class ModelPcbFrame extends ModelBase {
    public ModelRenderer base_long_1;
    public ModelRenderer top_long_2;
    public ModelRenderer base_short_1;
    public ModelRenderer base_short_2;
    public ModelRenderer top_long_1;
    public ModelRenderer base_long_2;
    public ModelRenderer top_short_1;
    public ModelRenderer top_short_2;
    public ModelRenderer strut_1;
    public ModelRenderer strut_2;
    public ModelRenderer strut_3;
    public ModelRenderer strut_4;

    public ModelPcbFrame() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.base_long_1 = new ModelRenderer(this, 0, 0);
        this.base_long_1.setRotationPoint(-8.0F, 0.0F, -8.0F);
        this.base_long_1.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.top_short_1 = new ModelRenderer(this, 0, 0);
        this.top_short_1.setRotationPoint(7.0F, -15.0F, -7.0F);
        this.top_short_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.top_long_1 = new ModelRenderer(this, 0, 0);
        this.top_long_1.setRotationPoint(-8.0F, -15.0F, -8.0F);
        this.top_long_1.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.strut_1 = new ModelRenderer(this, 0, 0);
        this.strut_1.setRotationPoint(-8.0F, -14.0F, 7.0F);
        this.strut_1.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.top_long_2 = new ModelRenderer(this, 0, 0);
        this.top_long_2.setRotationPoint(-8.0F, -15.0F, 7.0F);
        this.top_long_2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.base_short_2 = new ModelRenderer(this, 0, 0);
        this.base_short_2.setRotationPoint(-8.0F, 0.0F, -7.0F);
        this.base_short_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.strut_2 = new ModelRenderer(this, 0, 0);
        this.strut_2.setRotationPoint(-8.0F, -14.0F, -8.0F);
        this.strut_2.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.strut_4 = new ModelRenderer(this, 0, 0);
        this.strut_4.setRotationPoint(7.0F, -14.0F, -8.0F);
        this.strut_4.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.top_short_2 = new ModelRenderer(this, 0, 0);
        this.top_short_2.setRotationPoint(-8.0F, -15.0F, -7.0F);
        this.top_short_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.base_short_1 = new ModelRenderer(this, 0, 0);
        this.base_short_1.setRotationPoint(7.0F, 0.0F, -7.0F);
        this.base_short_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.strut_3 = new ModelRenderer(this, 0, 0);
        this.strut_3.setRotationPoint(7.0F, -14.0F, 7.0F);
        this.strut_3.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.base_long_2 = new ModelRenderer(this, 0, 0);
        this.base_long_2.setRotationPoint(-8.0F, 0.0F, 7.0F);
        this.base_long_2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.base_long_1.render(f5);
        this.top_short_1.render(f5);
        this.top_long_1.render(f5);
        this.strut_1.render(f5);
        this.top_long_2.render(f5);
        this.base_short_2.render(f5);
        this.strut_2.render(f5);
        this.strut_4.render(f5);
        this.top_short_2.render(f5);
        this.base_short_1.render(f5);
        this.strut_3.render(f5);
        this.base_long_2.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
