package fiskfille.tf.client.model.tileentity;

import fiskfille.tf.client.model.tools.ModelRendererBreakable;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;

public class ModelCrystal extends MowzieModelBase
{
    public MowzieModelRenderer shape1;
    public MowzieModelRenderer shape2;
    public MowzieModelRenderer shape2_1;
    public MowzieModelRenderer shape5;
    public MowzieModelRenderer shape5_1;
    public MowzieModelRenderer shape7;
    public MowzieModelRenderer shape7_1;
    public MowzieModelRenderer shape7_2;
    public MowzieModelRenderer shape7_3;
    public MowzieModelRenderer shape39;
    public MowzieModelRenderer shape42;
    public MowzieModelRenderer shape42_1;
    public MowzieModelRenderer shape42_2;
    public MowzieModelRenderer shape42_3;
    public MowzieModelRenderer shape8;
    public MowzieModelRenderer shape8_1;
    public MowzieModelRenderer shape8_2;
    public MowzieModelRenderer shape8_3;
    public MowzieModelRenderer shape40;

    public ModelCrystal()
    {
        textureWidth = 16;
        textureHeight = 16;
        shape7_2 = new ModelRendererBreakable(this, 0, 0);
        shape7_2.setRotationPoint(2.5F, 0.4F, -0.5F);
        shape7_2.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
        setRotateAngle(shape7_2, -0.15707963267948966F, 1.5707963267948966F, -0.08726646259971647F);
        shape42_3 = new ModelRendererBreakable(this, 0, 0);
        shape42_3.setRotationPoint(2.3F, 0.5F, 2.1F);
        shape42_3.addBox(-1.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F);
        setRotateAngle(shape42_3, -0.3490658503988659F, 0.296705972839036F, 0.296705972839036F);
        shape7 = new ModelRendererBreakable(this, 0, 0);
        shape7.setRotationPoint(0.5F, 0.4F, 2.5F);
        shape7.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        setRotateAngle(shape7, -0.08726646259971647F, 0.0F, 0.08726646259971647F);
        shape5_1 = new ModelRendererBreakable(this, 0, 0);
        shape5_1.setRotationPoint(-2.5F, 0.0F, 0.0F);
        shape5_1.addBox(-1.0F, 0.0F, -1.5F, 1, 1, 3, 0.0F);
        shape39 = new ModelRendererBreakable(this, 0, -1);
        shape39.setRotationPoint(0.0F, 0.2F, -0.2F);
        shape39.addBox(-1.5F, -3.0F, -1.5F, 3, 3, 3, 0.0F);
        setRotateAngle(shape39, -0.05235987755982988F, -0.5235987755982988F, 0.05235987755982988F);
        shape8 = new ModelRendererBreakable(this, 0, 0);
        shape8.setRotationPoint(-1.0F, -0.1F, 0.4F);
        shape8.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        setRotateAngle(shape8, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        shape42_2 = new ModelRendererBreakable(this, 0, 0);
        shape42_2.setRotationPoint(-1.3F, 0.3F, 2.5F);
        shape42_2.addBox(0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F);
        setRotateAngle(shape42_2, -0.20943951023931953F, 0.2617993877991494F, -0.4886921905584123F);
        shape40 = new ModelRendererBreakable(this, 0, 0);
        shape40.setRotationPoint(0.0F, -2.7F, 0.0F);
        shape40.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        setRotateAngle(shape40, 0.06981317007977318F, -0.20943951023931953F, 0.0F);
        shape7_1 = new ModelRendererBreakable(this, 0, 0);
        shape7_1.setRotationPoint(-2.5F, 0.4F, 0.5F);
        shape7_1.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, 0.0F);
        setRotateAngle(shape7_1, 0.017453292519943295F, -1.5707963267948966F, -0.08726646259971647F);
        shape5 = new ModelRendererBreakable(this, 0, 0);
        shape5.setRotationPoint(2.5F, 0.0F, 0.0F);
        shape5.addBox(0.0F, 0.0F, -1.5F, 1, 1, 3, 0.0F);
        shape7_3 = new ModelRendererBreakable(this, 0, 0);
        shape7_3.setRotationPoint(-0.5F, 0.4F, -2.5F);
        shape7_3.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        setRotateAngle(shape7_3, -0.15707963267948966F, 3.141592653589793F, -0.08726646259971647F);
        shape42 = new ModelRendererBreakable(this, 0, 0);
        shape42.setRotationPoint(-2.0F, 0.3F, -2.0F);
        shape42.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        setRotateAngle(shape42, 0.03490658503988659F, -0.19198621771937624F, -0.3141592653589793F);
        shape8_1 = new ModelRendererBreakable(this, 0, 0);
        shape8_1.setRotationPoint(-1.0F, -0.1F, 0.4F);
        shape8_1.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        setRotateAngle(shape8_1, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        shape8_2 = new ModelRendererBreakable(this, 0, 0);
        shape8_2.setRotationPoint(-1.0F, -0.1F, 0.4F);
        shape8_2.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        setRotateAngle(shape8_2, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        shape2_1 = new ModelRendererBreakable(this, 0, 0);
        shape2_1.setRotationPoint(0.0F, 0.0F, -2.5F);
        shape2_1.addBox(-1.5F, 0.0F, -1.0F, 3, 1, 1, 0.0F);
        shape42_1 = new ModelRendererBreakable(this, 0, 0);
        shape42_1.setRotationPoint(1.6F, 0.3F, -2.4F);
        shape42_1.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
        setRotateAngle(shape42_1, 0.12217304763960307F, 0.19198621771937624F, 0.3141592653589793F);
        shape1 = new ModelRendererBreakable(this, 0, 0);
        shape1.setRotationPoint(0.0F, 23.0F, 0.0F);
        shape1.addBox(-2.5F, 0.0F, -2.5F, 5, 1, 5, 0.0F);
        shape2 = new ModelRendererBreakable(this, 0, 0);
        shape2.setRotationPoint(0.0F, 0.0F, 2.5F);
        shape2.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        shape8_3 = new ModelRendererBreakable(this, 0, 0);
        shape8_3.setRotationPoint(-1.0F, -0.1F, 0.4F);
        shape8_3.addBox(-1.0F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        setRotateAngle(shape8_3, -0.20943951023931953F, 0.0F, -0.20943951023931953F);
        shape1.addChild(shape7_2);
        shape1.addChild(shape42_3);
        shape1.addChild(shape7);
        shape1.addChild(shape5_1);
        shape1.addChild(shape39);
        shape7.addChild(shape8);
        shape1.addChild(shape42_2);
        shape39.addChild(shape40);
        shape1.addChild(shape7_1);
        shape1.addChild(shape5);
        shape1.addChild(shape7_3);
        shape1.addChild(shape42);
        shape7_1.addChild(shape8_1);
        shape7_2.addChild(shape8_2);
        shape1.addChild(shape2_1);
        shape1.addChild(shape42_1);
        shape1.addChild(shape2);
        shape7_3.addChild(shape8_3);
    }

    public void render()
    {
        shape1.render(0.0625F);
    }
}
