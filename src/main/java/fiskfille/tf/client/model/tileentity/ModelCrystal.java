package fiskfille.tf.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCrystal extends ModelBase
{
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape2_1;
    public ModelRenderer shape5;
    public ModelRenderer shape5_1;
    public ModelRenderer shape7;
    public ModelRenderer shape7_1;
    public ModelRenderer shape7_2;
    public ModelRenderer shape7_3;
    public ModelRenderer shape39;
    public ModelRenderer shape42;
    public ModelRenderer shape42_1;
    public ModelRenderer shape42_2;
    public ModelRenderer shape42_3;
    public ModelRenderer shape8;
    public ModelRenderer shape8_1;
    public ModelRenderer shape8_2;
    public ModelRenderer shape8_3;
    public ModelRenderer shape40;

    public ModelCrystal()
    {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.shape7_2 = new ModelRenderer(this, 0, 0);
        this.shape7_2.setRotationPoint(2.5F, 0.4F, -0.5F);
        this.shape7_2.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(shape7_2, -0.15707963267948966F, 1.5707963267948966F, -0.08726646259971647F);
        this.shape42_3 = new ModelRenderer(this, 0, 0);
        this.shape42_3.setRotationPoint(2.3F, 0.5F, 2.1F);
        this.shape42_3.addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape42_3, -0.3490658503988659F, 0.296705972839036F, 0.296705972839036F);
        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.setRotationPoint(0.5F, 0.4F, 2.5F);
        this.shape7.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(shape7, -0.08726646259971647F, 0.0F, 0.08726646259971647F);
        this.shape5_1 = new ModelRenderer(this, 0, 0);
        this.shape5_1.setRotationPoint(-2.5F, 0.0F, 0.0F);
        this.shape5_1.addBox(-1.0F, 0.0F, -1.5F, 1, 1, 3, 0.0F);
        this.shape39 = new ModelRenderer(this, 0, -1);
        this.shape39.setRotationPoint(0.0F, 0.2F, -0.2F);
        this.shape39.addBox(-1.5F, -3.0F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(shape39, -0.05235987755982988F, -0.5235987755982988F, 0.05235987755982988F);
        this.shape8 = new ModelRenderer(this, 0, 0);
        this.shape8.setRotationPoint(-1.0F, -0.1F, 0.4F);
        this.shape8.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(shape8, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        this.shape42_2 = new ModelRenderer(this, 0, 0);
        this.shape42_2.setRotationPoint(-1.3F, 0.3F, 2.5F);
        this.shape42_2.addBox(0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape42_2, -0.20943951023931953F, 0.2617993877991494F, -0.4886921905584123F);
        this.shape40 = new ModelRenderer(this, 0, 0);
        this.shape40.setRotationPoint(0.0F, -2.7F, 0.0F);
        this.shape40.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(shape40, 0.06981317007977318F, -0.20943951023931953F, 0.0F);
        this.shape7_1 = new ModelRenderer(this, 0, 0);
        this.shape7_1.setRotationPoint(-2.5F, 0.4F, 0.5F);
        this.shape7_1.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(shape7_1, 0.017453292519943295F, -1.5707963267948966F, -0.08726646259971647F);
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(2.5F, 0.0F, 0.0F);
        this.shape5.addBox(0.0F, 0.0F, -1.5F, 1, 1, 3, 0.0F);
        this.shape7_3 = new ModelRenderer(this, 0, 0);
        this.shape7_3.setRotationPoint(-0.5F, 0.4F, -2.5F);
        this.shape7_3.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(shape7_3, -0.15707963267948966F, 3.141592653589793F, -0.08726646259971647F);
        this.shape42 = new ModelRenderer(this, 0, 0);
        this.shape42.setRotationPoint(-2.0F, 0.3F, -2.0F);
        this.shape42.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape42, 0.03490658503988659F, -0.19198621771937624F, -0.3141592653589793F);
        this.shape8_1 = new ModelRenderer(this, 0, 0);
        this.shape8_1.setRotationPoint(-1.0F, -0.1F, 0.4F);
        this.shape8_1.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(shape8_1, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        this.shape8_2 = new ModelRenderer(this, 0, 0);
        this.shape8_2.setRotationPoint(-1.0F, -0.1F, 0.4F);
        this.shape8_2.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(shape8_2, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        this.shape2_1 = new ModelRenderer(this, 0, 0);
        this.shape2_1.setRotationPoint(0.0F, 0.0F, -2.5F);
        this.shape2_1.addBox(-1.5F, 0.0F, -1.0F, 3, 1, 1, 0.0F);
        this.shape42_1 = new ModelRenderer(this, 0, 0);
        this.shape42_1.setRotationPoint(1.6F, 0.3F, -2.4F);
        this.shape42_1.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape42_1, 0.12217304763960307F, 0.19198621771937624F, 0.3141592653589793F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.shape1.addBox(-2.5F, 0.0F, -2.5F, 5, 1, 5, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, 2.5F);
        this.shape2.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.shape8_3 = new ModelRenderer(this, 0, 0);
        this.shape8_3.setRotationPoint(-1.0F, -0.1F, 0.4F);
        this.shape8_3.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(shape8_3, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        this.shape1.addChild(this.shape7_2);
        this.shape1.addChild(this.shape42_3);
        this.shape1.addChild(this.shape7);
        this.shape1.addChild(this.shape5_1);
        this.shape1.addChild(this.shape39);
        this.shape7.addChild(this.shape8);
        this.shape1.addChild(this.shape42_2);
        this.shape39.addChild(this.shape40);
        this.shape1.addChild(this.shape7_1);
        this.shape1.addChild(this.shape5);
        this.shape1.addChild(this.shape7_3);
        this.shape1.addChild(this.shape42);
        this.shape7_1.addChild(this.shape8_1);
        this.shape7_2.addChild(this.shape8_2);
        this.shape1.addChild(this.shape2_1);
        this.shape1.addChild(this.shape42_1);
        this.shape1.addChild(this.shape2);
        this.shape7_3.addChild(this.shape8_3);
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
