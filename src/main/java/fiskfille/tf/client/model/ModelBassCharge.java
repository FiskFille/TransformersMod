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
        textureWidth = 16;
        textureHeight = 8;
        shape5 = new ModelRenderer(this, 0, 0);
        shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape5.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        setRotateAngle(shape5, 0.0F, 0.0F, 3.141592653589793F);
        shape4 = new ModelRenderer(this, 0, 0);
        shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape4.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        setRotateAngle(shape4, 0.0F, 0.0F, 2.356194490192345F);
        shape2 = new ModelRenderer(this, 0, 0);
        shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape2.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        setRotateAngle(shape2, 0.0F, 0.0F, 0.7853981633974483F);
        shape7 = new ModelRenderer(this, 0, 0);
        shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape7.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        setRotateAngle(shape7, 0.0F, 0.0F, 4.71238898038469F);
        shape6 = new ModelRenderer(this, 0, 0);
        shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape6.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        setRotateAngle(shape6, 0.0F, 0.0F, 3.9269908169872414F);
        shape3 = new ModelRenderer(this, 0, 0);
        shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape3.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        setRotateAngle(shape3, 0.0F, 0.0F, 1.5707963267948966F);
        shape1 = new ModelRenderer(this, 0, 0);
        shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape1.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        shape8 = new ModelRenderer(this, 0, 0);
        shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape8.addBox(-2.5F, -6.0F, -0.5F, 5, 1, 1, 0.0F);
        setRotateAngle(shape8, 0.0F, 0.0F, 5.497787143782138F);
        shape1.addChild(shape5);
        shape1.addChild(shape4);
        shape1.addChild(shape2);
        shape1.addChild(shape7);
        shape1.addChild(shape6);
        shape1.addChild(shape3);
        shape1.addChild(shape8);
    }

    public void render()
    {
        shape1.render(0.0625F);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
