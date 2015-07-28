package fiskfille.tf.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBassCharge extends ModelBase
{
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;

    public ModelBassCharge()
    {
        this.textureWidth = 16;
        this.textureHeight = 8;
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape5.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape5, 0.0F, 0.0F, 3.141592653589793F);
        this.shape4 = new ModelRenderer(this, 0, 0);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape4, 0.0F, 0.0F, 2.356194490192345F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape2, 0.0F, 0.0F, 0.7853981633974483F);
        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape7.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape7, 0.0F, 0.0F, 4.71238898038469F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape6, 0.0F, 0.0F, 3.9269908169872414F);
        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape3, 0.0F, 0.0F, 1.5707963267948966F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 0);
        this.shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape8.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(shape8, 0.0F, 0.0F, 5.497787143782138F);
        this.shape1.addChild(this.shape5);
        this.shape1.addChild(this.shape4);
        this.shape1.addChild(this.shape2);
        this.shape1.addChild(this.shape7);
        this.shape1.addChild(this.shape6);
        this.shape1.addChild(this.shape3);
        this.shape1.addChild(this.shape8);
    }

    public void render()
    {
        this.shape1.render(0.0625F);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
