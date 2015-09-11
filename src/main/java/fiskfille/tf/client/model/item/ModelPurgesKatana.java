package fiskfille.tf.client.model.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPurgesKatana extends ModelBase
{
    public ModelRenderer base;
    public ModelRenderer blade1;
    public ModelRenderer blade17;
    public ModelRenderer blade19;
    public ModelRenderer blade20;
    public ModelRenderer blade2;
    public ModelRenderer blade8;
    public ModelRenderer blade9;
    public ModelRenderer blade10;
    public ModelRenderer blade11;
    public ModelRenderer blade14;
    public ModelRenderer blade3;
    public ModelRenderer balde6;
    public ModelRenderer blade7;
    public ModelRenderer blade4;
    public ModelRenderer blade5;
    public ModelRenderer blade12;
    public ModelRenderer blade13;
    public ModelRenderer blade15;
    public ModelRenderer blade16;
    public ModelRenderer blade18;

    public ModelPurgesKatana()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.base = new ModelRenderer(this, 20, 93);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-1.5F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(base, 0.0F, 1.5707963267948966F, 0.0F);
        this.blade18 = new ModelRenderer(this, 5, 23);
        this.blade18.setRotationPoint(0.0F, 4.5F, 0.0F);
        this.blade18.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.blade20 = new ModelRenderer(this, 9, 19);
        this.blade20.setRotationPoint(-0.6F, 1.0F, 0.0F);
        this.blade20.addBox(-3.0F, 0.0F, -1.0F, 3, 1, 2, 0.0F);
        this.setRotateAngle(blade20, 0.0F, 0.0F, 0.890117918517108F);
        this.blade10 = new ModelRenderer(this, 11, 96);
        this.blade10.setRotationPoint(0.5F, -4.5F, 0.0F);
        this.blade10.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
        this.blade16 = new ModelRenderer(this, 6, 15);
        this.blade16.mirror = true;
        this.blade16.setRotationPoint(-1.0F, -3.0F, 0.0F);
        this.blade16.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(blade16, 0.0F, 0.0F, 0.22689280275926282F);
        this.blade9 = new ModelRenderer(this, 11, 96);
        this.blade9.setRotationPoint(0.5F, -2.0F, 0.0F);
        this.blade9.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
        this.blade8 = new ModelRenderer(this, 11, 96);
        this.blade8.setRotationPoint(0.5F, 0.5F, 0.0F);
        this.blade8.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
        this.blade14 = new ModelRenderer(this, 1, 10);
        this.blade14.mirror = true;
        this.blade14.setRotationPoint(-1.2F, -7.0F, 0.0F);
        this.blade14.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.blade5 = new ModelRenderer(this, 20, 96);
        this.blade5.setRotationPoint(0.5F, -1.9F, 0.5F);
        this.blade5.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(blade5, 0.0F, -0.7853981633974483F, 0.0F);
        this.blade19 = new ModelRenderer(this, 9, 19);
        this.blade19.mirror = true;
        this.blade19.setRotationPoint(0.6F, 1.0F, 0.0F);
        this.blade19.addBox(0.0F, 0.0F, -1.0F, 3, 1, 2, 0.0F);
        this.setRotateAngle(blade19, 0.0F, 0.0F, -0.890117918517108F);
        this.blade7 = new ModelRenderer(this, 1, 20);
        this.blade7.setRotationPoint(-1.5F, -6.9F, 0.5F);
        this.blade7.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
        this.setRotateAngle(blade7, 0.7853981633974483F, 0.0F, 0.0F);
        this.blade12 = new ModelRenderer(this, 6, 10);
        this.blade12.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.blade12.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.blade11 = new ModelRenderer(this, 1, 10);
        this.blade11.setRotationPoint(1.2F, -7.0F, 0.0F);
        this.blade11.addBox(0.0F, 0.0F, 0.0F, 1, 8, 1, 0.0F);
        this.blade3 = new ModelRenderer(this, 20, 96);
        this.blade3.setRotationPoint(-1.0F, -3.0F, 0.0F);
        this.blade3.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.blade1 = new ModelRenderer(this, 29, 90);
        this.blade1.setRotationPoint(-0.5F, -1.0F, -0.5F);
        this.blade1.addBox(0.0F, -6.0F, 0.0F, 1, 7, 1, 0.0F);
        this.blade13 = new ModelRenderer(this, 6, 15);
        this.blade13.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.blade13.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(blade13, 0.0F, 0.0F, -0.22689280275926282F);
        this.blade2 = new ModelRenderer(this, 24, 96);
        this.blade2.setRotationPoint(1.0F, -6.0F, 0.0F);
        this.blade2.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.blade4 = new ModelRenderer(this, 11, 96);
        this.blade4.setRotationPoint(0.5F, -1.5F, 0.0F);
        this.blade4.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
        this.blade15 = new ModelRenderer(this, 6, 10);
        this.blade15.mirror = true;
        this.blade15.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.blade15.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        this.balde6 = new ModelRenderer(this, 11, 96);
        this.balde6.setRotationPoint(-0.5F, -1.5F, 0.0F);
        this.balde6.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
        this.blade17 = new ModelRenderer(this, 0, 23);
        this.blade17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.blade17.addBox(-0.5F, 1.0F, -0.5F, 1, 4, 1, 0.0F);
        this.blade17.addChild(this.blade18);
        this.base.addChild(this.blade20);
        this.blade1.addChild(this.blade10);
        this.blade15.addChild(this.blade16);
        this.blade1.addChild(this.blade9);
        this.blade1.addChild(this.blade8);
        this.blade1.addChild(this.blade14);
        this.blade3.addChild(this.blade5);
        this.base.addChild(this.blade19);
        this.blade2.addChild(this.blade7);
        this.blade11.addChild(this.blade12);
        this.blade1.addChild(this.blade11);
        this.blade2.addChild(this.blade3);
        this.base.addChild(this.blade1);
        this.blade12.addChild(this.blade13);
        this.blade1.addChild(this.blade2);
        this.blade3.addChild(this.blade4);
        this.blade14.addChild(this.blade15);
        this.blade2.addChild(this.balde6);
        this.base.addChild(this.blade17);
    }

    public void render()
    {
        this.base.rotateAngleY = (float) Math.toRadians(180);
        this.base.rotationPointY = -4;
        GL11.glScalef(1, 1, 0.75F);
        this.base.render(0.0625F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
