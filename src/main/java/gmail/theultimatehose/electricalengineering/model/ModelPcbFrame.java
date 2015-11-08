package gmail.theultimatehose.electricalengineering.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

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
    public ModelRenderer v_reg_base;
    public ModelRenderer v_reg_cap_hv;
    public ModelRenderer v_reg_transformer;
    public ModelRenderer v_reg_cap_1;
    public ModelRenderer v_reg_cap_2;
    public ModelRenderer v_reg_cap_3;
    public ModelRenderer v_reg_trans_1;
    public ModelRenderer v_reg_trans_2;
    public ModelRenderer v_reg_strut_1;
    public ModelRenderer v_reg_strut_2;
    public ModelRenderer ctrl_strut_1;
    public ModelRenderer ctrl_strut_2;
    public ModelRenderer ctrl_strut_long_1;
    public ModelRenderer ctrl_strut_long_2;
    public ModelRenderer ctrl_base;
    public ModelRenderer ctrl_cap_1;
    public ModelRenderer ctrl_cap_2;
    public ModelRenderer ctrl_cap_3;
    public ModelRenderer ctrl_cap_4;
    public ModelRenderer ctrl_trans_1;
    public ModelRenderer ctrl_trans_2;
    public ModelRenderer ctrl_trans_3;
    public ModelRenderer ctrl_trans_4;
    public ModelRenderer rs_ring_1;
    public ModelRenderer rs_ring_2;
    public ModelRenderer rs_ring_3;
    public ModelRenderer rs_ant_core;
    public ModelRenderer rs_strut_1;
    public ModelRenderer rs_strut_2;
    public ModelRenderer rs_strut_3;
    public ModelRenderer rs_conn_1;
    public ModelRenderer rs_conn_2;
    public ModelRenderer rc_ant_1;
    public ModelRenderer rc_ant_2;
    public ModelRenderer rc_ant_3;
    public ModelRenderer rc_ant_4;
    public ModelRenderer rc_strut_1;
    public ModelRenderer rc_strut_2;
    public ModelRenderer rc_strut_3;
    public ModelRenderer rc_conn_1;

    public ModelPcbFrame() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.strut_1 = new ModelRenderer(this, 0, 0);
        this.strut_1.setRotationPoint(-8.0F, -14.0F, 7.0F);
        this.strut_1.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.rs_strut_3 = new ModelRenderer(this, 0, 0);
        this.rs_strut_3.setRotationPoint(-3.5F, -14.0F, -4.0F);
        this.rs_strut_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.top_short_2 = new ModelRenderer(this, 1, 0);
        this.top_short_2.setRotationPoint(-8.0F, -15.0F, -7.0F);
        this.top_short_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.rc_conn_1 = new ModelRenderer(this, 27, 27);
        this.rc_conn_1.setRotationPoint(2.0F, -11.0F, 2.1F);
        this.rc_conn_1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.ctrl_strut_2 = new ModelRenderer(this, 1, 0);
        this.ctrl_strut_2.setRotationPoint(-5.0F, -6.0F, -7.0F);
        this.ctrl_strut_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.v_reg_cap_1 = new ModelRenderer(this, 10, 16);
        this.v_reg_cap_1.setRotationPoint(2.5F, -2.0F, -2.0F);
        this.v_reg_cap_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.rc_ant_1 = new ModelRenderer(this, 27, 25);
        this.rc_ant_1.setRotationPoint(0.0F, -12.0F, 4.0F);
        this.rc_ant_1.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.setRotateAngle(rc_ant_1, 0.0F, 1.5707963267948966F, 0.0F);
        this.top_long_2 = new ModelRenderer(this, 0, 0);
        this.top_long_2.setRotationPoint(-8.0F, -15.0F, 7.0F);
        this.top_long_2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.ctrl_base = new ModelRenderer(this, 18, 15);
        this.ctrl_base.setRotationPoint(-7.0F, -7.0F, -4.5F);
        this.ctrl_base.addBox(0.0F, 0.0F, 0.0F, 14, 1, 9, 0.0F);
        this.ctrl_cap_4 = new ModelRenderer(this, 10, 16);
        this.ctrl_cap_4.setRotationPoint(3.3F, -8.0F, -1.9F);
        this.ctrl_cap_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.ctrl_cap_2 = new ModelRenderer(this, 10, 16);
        this.ctrl_cap_2.setRotationPoint(-2.4F, -8.0F, -2.1F);
        this.ctrl_cap_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.v_reg_cap_3 = new ModelRenderer(this, 10, 16);
        this.v_reg_cap_3.setRotationPoint(-5.0F, -2.6F, -1.6F);
        this.v_reg_cap_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.rs_ant_core = new ModelRenderer(this, 32, 0);
        this.rs_ant_core.setRotationPoint(-3.5F, -12.0F, -6.0F);
        this.rs_ant_core.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12, 0.0F);
        this.rs_conn_2 = new ModelRenderer(this, 27, 27);
        this.rs_conn_2.setRotationPoint(-3.5F, -9.5F, -0.8F);
        this.rs_conn_2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.base_long_2 = new ModelRenderer(this, 0, 0);
        this.base_long_2.setRotationPoint(-8.0F, 0.0F, 7.0F);
        this.base_long_2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.rc_strut_2 = new ModelRenderer(this, 0, 0);
        this.rc_strut_2.setRotationPoint(2.0F, -14.0F, 3.0F);
        this.rc_strut_2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.ctrl_trans_1 = new ModelRenderer(this, 17, 16);
        this.ctrl_trans_1.setRotationPoint(-4.4F, -8.0F, -2.2F);
        this.ctrl_trans_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.rs_ring_3 = new ModelRenderer(this, 34, 0);
        this.rs_ring_3.setRotationPoint(-4.5F, -13.0F, -4.0F);
        this.rs_ring_3.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.rs_ring_1 = new ModelRenderer(this, 34, 0);
        this.rs_ring_1.setRotationPoint(-4.5F, -13.0F, 3.0F);
        this.rs_ring_1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.top_short_1 = new ModelRenderer(this, 0, 0);
        this.top_short_1.setRotationPoint(7.0F, -15.0F, -7.0F);
        this.top_short_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.strut_3 = new ModelRenderer(this, 0, 0);
        this.strut_3.setRotationPoint(7.0F, -14.0F, 7.0F);
        this.strut_3.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.rs_strut_1 = new ModelRenderer(this, 1, 0);
        this.rs_strut_1.setRotationPoint(-3.5F, -15.0F, -7.0F);
        this.rs_strut_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.ctrl_trans_3 = new ModelRenderer(this, 17, 16);
        this.ctrl_trans_3.setRotationPoint(2.7F, -8.0F, 0.6F);
        this.ctrl_trans_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.v_reg_trans_1 = new ModelRenderer(this, 17, 16);
        this.v_reg_trans_1.setRotationPoint(-3.1F, -2.0F, -2.2F);
        this.v_reg_trans_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.ctrl_strut_1 = new ModelRenderer(this, 2, 0);
        this.ctrl_strut_1.setRotationPoint(4.0F, -6.0F, -7.0F);
        this.ctrl_strut_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.top_long_1 = new ModelRenderer(this, 0, 5);
        this.top_long_1.setRotationPoint(-8.0F, -15.0F, -8.0F);
        this.top_long_1.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.base_short_1 = new ModelRenderer(this, 2, 0);
        this.base_short_1.setRotationPoint(7.0F, 0.0F, -7.0F);
        this.base_short_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.v_reg_trans_2 = new ModelRenderer(this, 17, 16);
        this.v_reg_trans_2.setRotationPoint(0.5F, -2.0F, -2.4F);
        this.v_reg_trans_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.rc_ant_4 = new ModelRenderer(this, 27, 25);
        this.rc_ant_4.setRotationPoint(0.0F, -12.0F, 4.0F);
        this.rc_ant_4.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.base_long_1 = new ModelRenderer(this, 0, 1);
        this.base_long_1.setRotationPoint(-8.0F, 0.0F, -8.0F);
        this.base_long_1.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.v_reg_strut_1 = new ModelRenderer(this, 2, 0);
        this.v_reg_strut_1.setRotationPoint(4.0F, 0.0F, -7.0F);
        this.v_reg_strut_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.ctrl_cap_1 = new ModelRenderer(this, 10, 16);
        this.ctrl_cap_1.setRotationPoint(-3.6F, -8.0F, 0.5F);
        this.ctrl_cap_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.v_reg_transformer = new ModelRenderer(this, 0, 19);
        this.v_reg_transformer.setRotationPoint(-5.0F, -4.0F, 0.0F);
        this.v_reg_transformer.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.base_short_2 = new ModelRenderer(this, 1, 0);
        this.base_short_2.setRotationPoint(-8.0F, 0.0F, -7.0F);
        this.base_short_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.v_reg_strut_2 = new ModelRenderer(this, 1, 0);
        this.v_reg_strut_2.setRotationPoint(-5.0F, 0.0F, -7.0F);
        this.v_reg_strut_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.rs_ring_2 = new ModelRenderer(this, 34, 0);
        this.rs_ring_2.setRotationPoint(-4.5F, -13.0F, -0.5F);
        this.rs_ring_2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.ctrl_trans_2 = new ModelRenderer(this, 17, 16);
        this.ctrl_trans_2.setRotationPoint(-1.3F, -8.0F, 1.1F);
        this.ctrl_trans_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.rc_ant_3 = new ModelRenderer(this, 27, 25);
        this.rc_ant_3.setRotationPoint(4.0F, -12.0F, 4.0F);
        this.rc_ant_3.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.setRotateAngle(rc_ant_3, 0.0F, 1.5707963267948966F, 0.0F);
        this.strut_4 = new ModelRenderer(this, 0, 0);
        this.strut_4.setRotationPoint(7.0F, -14.0F, -8.0F);
        this.strut_4.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.ctrl_cap_3 = new ModelRenderer(this, 10, 16);
        this.ctrl_cap_3.setRotationPoint(0.2F, -8.6F, -0.3F);
        this.ctrl_cap_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.v_reg_cap_2 = new ModelRenderer(this, 10, 16);
        this.v_reg_cap_2.setRotationPoint(-1.3F, -2.0F, -2.7F);
        this.v_reg_cap_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.ctrl_trans_4 = new ModelRenderer(this, 17, 16);
        this.ctrl_trans_4.setRotationPoint(0.6F, -8.0F, -2.2F);
        this.ctrl_trans_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.rs_strut_2 = new ModelRenderer(this, 0, 0);
        this.rs_strut_2.setRotationPoint(-3.5F, -14.0F, 3.0F);
        this.rs_strut_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.rc_strut_3 = new ModelRenderer(this, 0, 0);
        this.rc_strut_3.setRotationPoint(2.0F, -14.0F, -4.0F);
        this.rc_strut_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.strut_2 = new ModelRenderer(this, 0, 0);
        this.strut_2.setRotationPoint(-8.0F, -14.0F, -8.0F);
        this.strut_2.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.rs_conn_1 = new ModelRenderer(this, 27, 27);
        this.rs_conn_1.setRotationPoint(-3.5F, -10.5F, 3.5F);
        this.rs_conn_1.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1, 0.0F);
        this.setRotateAngle(rs_conn_1, 0.0F, 1.5707963267948966F, 0.0F);
        this.rc_strut_1 = new ModelRenderer(this, 1, 0);
        this.rc_strut_1.setRotationPoint(2.0F, -15.0F, -7.0F);
        this.rc_strut_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.v_reg_base = new ModelRenderer(this, 18, 15);
        this.v_reg_base.setRotationPoint(-7.0F, -1.0F, -4.5F);
        this.v_reg_base.addBox(0.0F, 0.0F, 0.0F, 14, 1, 9, 0.0F);
        this.ctrl_strut_long_2 = new ModelRenderer(this, 0, 1);
        this.ctrl_strut_long_2.setRotationPoint(-7.0F, -6.0F, 7.0F);
        this.ctrl_strut_long_2.addBox(0.0F, 0.0F, 0.0F, 14, 1, 1, 0.0F);
        this.rc_ant_2 = new ModelRenderer(this, 27, 25);
        this.rc_ant_2.setRotationPoint(2.0F, -12.0F, 4.0F);
        this.rc_ant_2.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.setRotateAngle(rc_ant_2, 0.0F, 1.5707963267948966F, 0.0F);
        this.v_reg_cap_hv = new ModelRenderer(this, 0, 25);
        this.v_reg_cap_hv.setRotationPoint(-0.6F, -4.0F, -0.6F);
        this.v_reg_cap_hv.addBox(0.0F, 0.0F, 0.0F, 6, 3, 4, 0.0F);
        this.ctrl_strut_long_1 = new ModelRenderer(this, 0, 1);
        this.ctrl_strut_long_1.setRotationPoint(-7.0F, -6.0F, -8.0F);
        this.ctrl_strut_long_1.addBox(0.0F, 0.0F, 0.0F, 14, 1, 1, 0.0F);
    }

    public void render(float f5, boolean renderVReg, boolean renderCtrl, boolean renderRS, boolean renderRC) {
        //Case
        this.base_long_1.render(f5);
        this.base_long_2.render(f5);
        this.base_short_1.render(f5);
        this.base_short_2.render(f5);
        this.top_long_1.render(f5);
        this.top_long_2.render(f5);
        this.top_short_1.render(f5);
        this.top_short_2.render(f5);
        this.strut_1.render(f5);
        this.strut_3.render(f5);
        this.strut_4.render(f5);
        this.strut_2.render(f5);

        //Voltage Regulation PCB
        if (renderVReg) {
            this.v_reg_base.render(f5);
            this.v_reg_cap_hv.render(f5);
            this.v_reg_transformer.render(f5);
            this.v_reg_cap_1.render(f5);
            this.v_reg_cap_2.render(f5);
            this.v_reg_cap_3.render(f5);
            this.v_reg_trans_1.render(f5);
            this.v_reg_trans_2.render(f5);
            this.v_reg_strut_1.render(f5);
            this.v_reg_strut_2.render(f5);
        }

        //Control PCB
        if (renderCtrl) {
            this.ctrl_strut_1.render(f5);
            this.ctrl_strut_2.render(f5);
            this.ctrl_strut_long_1.render(f5);
            this.ctrl_strut_long_2.render(f5);
            this.ctrl_base.render(f5);
            this.ctrl_cap_1.render(f5);
            this.ctrl_cap_2.render(f5);
            this.ctrl_cap_3.render(f5);
            this.ctrl_cap_4.render(f5);
            this.ctrl_trans_1.render(f5);
            this.ctrl_trans_2.render(f5);
            this.ctrl_trans_3.render(f5);
            this.ctrl_trans_4.render(f5);
        }

        //Redstone Control
        if (renderRS) {
            this.rs_ring_1.render(f5);
            this.rs_ring_2.render(f5);
            this.rs_ring_3.render(f5);
            this.rs_ant_core.render(f5);
            this.rs_strut_1.render(f5);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
            this.rs_strut_2.render(f5);
            this.rs_strut_3.render(f5);
            GL11.glDisable(GL11.GL_BLEND);
            this.rs_conn_1.render(f5);
            this.rs_conn_2.render(f5);
        }

        //Remote Control
        if (renderRC) {
            this.rc_ant_1.render(f5);
            this.rc_ant_2.render(f5);
            this.rc_ant_3.render(f5);
            this.rc_ant_4.render(f5);
            this.rc_strut_1.render(f5);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
            this.rc_strut_2.render(f5);
            this.rc_strut_3.render(f5);
            GL11.glDisable(GL11.GL_BLEND);
            this.rc_conn_1.render(f5);
        }
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
