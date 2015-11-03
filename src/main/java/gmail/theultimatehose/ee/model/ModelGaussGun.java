package gmail.theultimatehose.ee.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelGaussGun - TheUltimateHose
 * Created using Tabula 4.1.1
 */
public class ModelGaussGun extends ModelBase {

    public ModelRenderer tube;
    public ModelRenderer tube2;
    public ModelRenderer tube3;
    public ModelRenderer tube4;
    public ModelRenderer coil;
    public ModelRenderer coil2;
    public ModelRenderer coil3;
    public ModelRenderer coil4;
    public ModelRenderer coil5;
    public ModelRenderer coil6;
    public ModelRenderer coil7;
    public ModelRenderer coil8;
    public ModelRenderer coil9;
    public ModelRenderer coil10;
    public ModelRenderer coil11;
    public ModelRenderer coil12;
    public ModelRenderer coil13;
    public ModelRenderer coil14;
    public ModelRenderer coil15;
    public ModelRenderer coil16;
    public ModelRenderer pcb;
    public ModelRenderer capacitorLV;
    public ModelRenderer transistor;
    public ModelRenderer capacitorHV;
    public ModelRenderer transformer;
    public ModelRenderer pcb1;
    public ModelRenderer capacitorLV1;
    public ModelRenderer capacitorLV2;
    public ModelRenderer transistor1;
    public ModelRenderer transistor2;
    public ModelRenderer body;
    public ModelRenderer handle;
    public ModelRenderer trigger;

    public ModelGaussGun() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.transistor = new ModelRenderer(this, 0, 16);
        this.transistor.setRotationPoint(0.0F, -4.0F, 5.0F);
        this.transistor.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.tube4 = new ModelRenderer(this, 0, 0);
        this.tube4.setRotationPoint(-2.0F, -2.0F, -8.0F);
        this.tube4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 11, 0.0F);
        this.coil8 = new ModelRenderer(this, 32, 0);
        this.coil8.setRotationPoint(-3.0F, -3.0F, -1.0F);
        this.coil8.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.tube = new ModelRenderer(this, 0, 0);
        this.tube.setRotationPoint(-2.0F, 0.0F, -8.0F);
        this.tube.addBox(0.0F, 0.0F, 0.0F, 3, 1, 11, 0.0F);
        this.coil2 = new ModelRenderer(this, 28, 0);
        this.coil2.setRotationPoint(1.0F, -3.0F, -5.0F);
        this.coil2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.capacitorLV = new ModelRenderer(this, 0, 14);
        this.capacitorLV.setRotationPoint(0.0F, -4.0F, 2.0F);
        this.capacitorLV.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.capacitorHV = new ModelRenderer(this, 0, 27);
        this.capacitorHV.setRotationPoint(-2.6F, -5.0F, 1.3F);
        this.capacitorHV.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.coil13 = new ModelRenderer(this, 28, 0);
        this.coil13.setRotationPoint(-2.0F, 1.0F, -1.0F);
        this.coil13.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.coil14 = new ModelRenderer(this, 28, 0);
        this.coil14.setRotationPoint(-2.0F, 1.0F, -3.0F);
        this.coil14.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.capacitorLV1 = new ModelRenderer(this, 0, 14);
        this.capacitorLV1.setRotationPoint(2.1F, 0.1F, 2.3F);
        this.capacitorLV1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.coil9 = new ModelRenderer(this, 28, 0);
        this.coil9.setRotationPoint(-2.0F, -3.0F, -1.0F);
        this.coil9.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.coil12 = new ModelRenderer(this, 28, 0);
        this.coil12.setRotationPoint(-2.0F, -3.0F, -7.0F);
        this.coil12.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.coil11 = new ModelRenderer(this, 28, 0);
        this.coil11.setRotationPoint(-2.0F, -3.0F, -5.0F);
        this.coil11.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.coil6 = new ModelRenderer(this, 32, 0);
        this.coil6.setRotationPoint(-3.0F, -3.0F, -5.0F);
        this.coil6.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.transformer = new ModelRenderer(this, 0, 23);
        this.transformer.setRotationPoint(-2.6F, -5.0F, 4.8F);
        this.transformer.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.capacitorLV2 = new ModelRenderer(this, 0, 14);
        this.capacitorLV2.setRotationPoint(2.1F, -1.2F, 5.0F);
        this.capacitorLV2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.coil4 = new ModelRenderer(this, 28, 0);
        this.coil4.setRotationPoint(1.0F, -3.0F, -1.0F);
        this.coil4.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.pcb1 = new ModelRenderer(this, 0, 14);
        this.pcb1.setRotationPoint(2.1F, 2.1F, 7.0F);
        this.pcb1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 6, 0.0F);
        this.setRotateAngle(pcb1, 0.0F, 3.141592653589793F, 1.5707963267948966F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-3.0F, -2.0F, 1.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, 0.0F);
        this.coil15 = new ModelRenderer(this, 28, 0);
        this.coil15.setRotationPoint(-2.0F, 1.0F, -5.0F);
        this.coil15.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.coil16 = new ModelRenderer(this, 28, 0);
        this.coil16.setRotationPoint(-2.0F, 1.0F, -7.0F);
        this.coil16.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.transistor2 = new ModelRenderer(this, 0, 16);
        this.transistor2.setRotationPoint(2.1F, -0.1F, 4.0F);
        this.transistor2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.coil3 = new ModelRenderer(this, 28, 0);
        this.coil3.setRotationPoint(1.0F, -3.0F, -3.0F);
        this.coil3.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.transistor1 = new ModelRenderer(this, 0, 16);
        this.transistor1.setRotationPoint(2.1F, -1.9F, 3.0F);
        this.transistor1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.trigger = new ModelRenderer(this, 3, 29);
        this.trigger.setRotationPoint(-1.0F, 1.9F, 3.1F);
        this.trigger.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(trigger, -0.7740535232594852F, 0.0F, 0.0F);
        this.tube2 = new ModelRenderer(this, 0, 0);
        this.tube2.setRotationPoint(0.0F, -1.0F, -8.0F);
        this.tube2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 11, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(-1.6F, 1.9F, 3.3F);
        this.handle.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.pcb = new ModelRenderer(this, 0, 14);
        this.pcb.setRotationPoint(-3.0F, -3.0F, 1.0F);
        this.pcb.addBox(0.0F, 0.0F, 0.0F, 5, 1, 6, 0.0F);
        this.coil10 = new ModelRenderer(this, 28, 0);
        this.coil10.setRotationPoint(-2.0F, -3.0F, -3.0F);
        this.coil10.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.coil = new ModelRenderer(this, 28, 0);
        this.coil.setRotationPoint(1.0F, -3.0F, -7.0F);
        this.coil.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.tube3 = new ModelRenderer(this, 0, 0);
        this.tube3.setRotationPoint(-2.0F, -1.0F, -8.0F);
        this.tube3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 11, 0.0F);
        this.coil7 = new ModelRenderer(this, 32, 0);
        this.coil7.setRotationPoint(-3.0F, -3.0F, -3.0F);
        this.coil7.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.coil5 = new ModelRenderer(this, 32, 0);
        this.coil5.setRotationPoint(-3.0F, -3.0F, -7.0F);
        this.coil5.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
    }

    public void render(float f5) {
        this.transistor.render(f5);
        this.tube4.render(f5);
        this.coil8.render(f5);
        this.tube.render(f5);
        this.coil2.render(f5);
        this.capacitorLV.render(f5);
        this.capacitorHV.render(f5);
        this.coil13.render(f5);
        this.coil14.render(f5);
        this.capacitorLV1.render(f5);
        this.coil9.render(f5);
        this.coil12.render(f5);
        this.coil11.render(f5);
        this.coil6.render(f5);
        this.transformer.render(f5);
        this.capacitorLV2.render(f5);
        this.coil4.render(f5);
        this.pcb1.render(f5);
        this.body.render(f5);
        this.coil15.render(f5);
        this.coil16.render(f5);
        this.transistor2.render(f5);
        this.coil3.render(f5);
        this.transistor1.render(f5);
        this.trigger.render(f5);
        this.tube2.render(f5);
        this.handle.render(f5);
        this.pcb.render(f5);
        this.coil10.render(f5);
        this.coil.render(f5);
        this.tube3.render(f5);
        this.coil7.render(f5);
        this.coil5.render(f5);
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
