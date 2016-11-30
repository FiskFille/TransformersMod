package fiskfille.tf.client.model.tileentity;

import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;

public class ModelRelayTorch extends ModelRelayTower
{
    public MowzieModelRenderer shape1;
    public MowzieModelRenderer shape2;
    public MowzieModelRenderer shape4;
    public MowzieModelRenderer shape6;
    public MowzieModelRenderer shape8;
    public MowzieModelRenderer shape9;
    public MowzieModelRenderer shape10;
    public MowzieModelRenderer shape11;
    public MowzieModelRenderer shape13;
    public MowzieModelRenderer shape30;
    public MowzieModelRenderer shape3;
    public MowzieModelRenderer shape5;
    public MowzieModelRenderer shape7;
    public MowzieModelRenderer shape12;
    public MowzieModelRenderer shape14;
    public MowzieModelRenderer shape18;
    public MowzieModelRenderer shape22;
    public MowzieModelRenderer shape26;
    public MowzieModelRenderer shape15;
    public MowzieModelRenderer shape16;
    public MowzieModelRenderer shape17;
    public MowzieModelRenderer shape19;
    public MowzieModelRenderer shape20;
    public MowzieModelRenderer shape21;
    public MowzieModelRenderer shape23;
    public MowzieModelRenderer shape24;
    public MowzieModelRenderer shape25;
    public MowzieModelRenderer shape27;
    public MowzieModelRenderer shape28;
    public MowzieModelRenderer shape29;

    public ModelRelayTorch()
    {
        textureWidth = 32;
        textureHeight = 32;
        shape26 = new MowzieModelRenderer(this, 0, 22);
        shape26.setRotationPoint(0.0F, 0.0F, -1.0F);
        shape26.addBox(-1.5F, -0.1F, 2.0F, 3, 1, 1, 0.0F);
        setRotateAngle(shape26, 0.0F, 3.141592653589793F, 0.0F);
        shape11 = new MowzieModelRenderer(this, 0, 8);
        shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape11.addBox(-0.5F, 1.0F, 0.2F, 1, 2, 2, 0.0F);
        setRotateAngle(shape11, 0.0F, 0.7853981633974483F, 0.0F);
        shape18 = new MowzieModelRenderer(this, 0, 22);
        shape18.setRotationPoint(1.0F, 0.0F, 0.0F);
        shape18.addBox(-1.5F, -0.1F, 2.0F, 3, 1, 1, 0.0F);
        setRotateAngle(shape18, 0.0F, 1.5707963267948966F, 0.0F);
        shape13 = new MowzieModelRenderer(this, 1, 18);
        shape13.setRotationPoint(0.0F, 4.0F, 0.0F);
        shape13.addBox(-1.5F, 0.0F, -1.5F, 3, 1, 3, 0.0F);
        shape24 = new MowzieModelRenderer(this, 0, 24);
        shape24.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape24.addBox(-1.3F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape24, 0.0F, -0.03490658503988659F, 0.0F);
        shape14 = new MowzieModelRenderer(this, 0, 22);
        shape14.setRotationPoint(0.0F, 0.0F, 1.0F);
        shape14.addBox(-1.5F, -0.1F, 2.0F, 3, 1, 1, 0.0F);
        shape22 = new MowzieModelRenderer(this, 0, 22);
        shape22.setRotationPoint(-1.0F, 0.0F, 0.0F);
        shape22.addBox(-1.5F, -0.1F, 2.0F, 3, 1, 1, 0.0F);
        setRotateAngle(shape22, 0.0F, -1.5707963267948966F, 0.0F);
        shape19 = new MowzieModelRenderer(this, 0, 24);
        shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape19.addBox(0.2F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape19, 0.0F, 0.03490658503988659F, 0.0F);
        shape8 = new MowzieModelRenderer(this, 0, 12);
        shape8.setRotationPoint(0.1F, 0.0F, 0.2F);
        shape8.addBox(0.0F, -7.0F, 0.0F, 1, 7, 1, 0.0F);
        shape27 = new MowzieModelRenderer(this, 0, 24);
        shape27.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape27.addBox(0.2F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape27, 0.0F, 0.03490658503988659F, 0.0F);
        shape1 = new MowzieModelRenderer(this, 0, 4);
        shape1.setRotationPoint(0.0F, 19.0F, 0.0F);
        shape1.addBox(-1.5F, 0.0F, -1.5F, 3, 1, 3, 0.0F);
        shape16 = new MowzieModelRenderer(this, 0, 24);
        shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape16.addBox(-1.3F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape16, 0.0F, -0.03490658503988659F, 0.0F);
        shape30 = new MowzieModelRenderer(this, 0, 0);
        shape30.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape30.addBox(-0.5F, 1.0F, -0.5F, 1, 3, 1, 0.0F);
        shape10 = new MowzieModelRenderer(this, 8, 12);
        shape10.setRotationPoint(0.1F, 0.0F, 0.2F);
        shape10.addBox(-0.1F, -3.6F, -1.3F, 1, 4, 1, 0.0F);
        shape6 = new MowzieModelRenderer(this, 0, 8);
        shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape6.addBox(-0.5F, 1.0F, 0.2F, 1, 2, 2, 0.0F);
        setRotateAngle(shape6, 0.0F, -2.356194490192345F, 0.0F);
        shape20 = new MowzieModelRenderer(this, 0, 24);
        shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape20.addBox(-1.3F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape20, 0.0F, -0.03490658503988659F, 0.0F);
        shape28 = new MowzieModelRenderer(this, 0, 24);
        shape28.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape28.addBox(-1.3F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape28, 0.0F, -0.03490658503988659F, 0.0F);
        shape21 = new MowzieModelRenderer(this, 4, 24);
        shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape21.addBox(-0.5F, -0.4F, -0.5F, 1, 1, 3, 0.0F);
        setRotateAngle(shape21, -0.12217304763960307F, 0.0F, 0.0F);
        shape29 = new MowzieModelRenderer(this, 4, 24);
        shape29.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape29.addBox(-0.5F, -0.4F, -0.5F, 1, 1, 3, 0.0F);
        setRotateAngle(shape29, -0.12217304763960307F, 0.0F, 0.0F);
        shape4 = new MowzieModelRenderer(this, 0, 8);
        shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape4.addBox(-0.5F, 1.0F, 0.2F, 1, 2, 2, 0.0F);
        setRotateAngle(shape4, 0.0F, 2.356194490192345F, 0.0F);
        shape9 = new MowzieModelRenderer(this, 4, 12);
        shape9.setRotationPoint(0.1F, 0.0F, 0.2F);
        shape9.addBox(-1.3F, -5.0F, -0.5F, 1, 5, 1, 0.0F);
        shape15 = new MowzieModelRenderer(this, 0, 24);
        shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape15.addBox(0.2F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape15, 0.0F, 0.03490658503988659F, 0.0F);
        shape25 = new MowzieModelRenderer(this, 4, 24);
        shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape25.addBox(-0.5F, -0.4F, -0.5F, 1, 1, 3, 0.0F);
        setRotateAngle(shape25, -0.12217304763960307F, 0.0F, 0.0F);
        shape2 = new MowzieModelRenderer(this, 0, 8);
        shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape2.addBox(-0.5F, 1.0F, 0.2F, 1, 2, 2, 0.0F);
        setRotateAngle(shape2, 0.0F, -0.7853981633974483F, 0.0F);
        shape12 = new MowzieModelRenderer(this, 6, 8);
        shape12.mirror = true;
        shape12.setRotationPoint(0.0F, 1.0F, 1.8F);
        shape12.addBox(-1.5F, -0.6F, 0.0F, 3, 3, 1, 0.0F);
        setRotateAngle(shape12, 0.08726646259971647F, 0.0F, 0.0F);
        shape5 = new MowzieModelRenderer(this, 6, 8);
        shape5.setRotationPoint(0.0F, 1.0F, 1.8F);
        shape5.addBox(-1.5F, -0.6F, 0.0F, 3, 3, 1, 0.0F);
        setRotateAngle(shape5, 0.08726646259971647F, 0.0F, 0.0F);
        shape3 = new MowzieModelRenderer(this, 6, 8);
        shape3.setRotationPoint(0.0F, 1.0F, 1.8F);
        shape3.addBox(-1.5F, -0.6F, 0.0F, 3, 3, 1, 0.0F);
        setRotateAngle(shape3, 0.08726646259971647F, 0.0F, 0.0F);
        shape7 = new MowzieModelRenderer(this, 6, 8);
        shape7.mirror = true;
        shape7.setRotationPoint(0.0F, 1.0F, 1.8F);
        shape7.addBox(-1.5F, -0.6F, 0.0F, 3, 3, 1, 0.0F);
        setRotateAngle(shape7, 0.08726646259971647F, 0.0F, 0.0F);
        shape17 = new MowzieModelRenderer(this, 4, 24);
        shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape17.addBox(-0.5F, -0.4F, -0.5F, 1, 1, 3, 0.0F);
        setRotateAngle(shape17, -0.12217304763960307F, 0.0F, 0.0F);
        shape23 = new MowzieModelRenderer(this, 0, 24);
        shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
        shape23.addBox(0.2F, -1.0F, 2.4F, 1, 2, 1, 0.0F);
        setRotateAngle(shape23, 0.0F, 0.03490658503988659F, 0.0F);
        shape13.addChild(shape26);
        shape1.addChild(shape11);
        shape13.addChild(shape18);
        shape1.addChild(shape13);
        shape22.addChild(shape24);
        shape13.addChild(shape14);
        shape13.addChild(shape22);
        shape18.addChild(shape19);
        shape1.addChild(shape8);
        shape26.addChild(shape27);
        shape14.addChild(shape16);
        shape1.addChild(shape30);
        shape1.addChild(shape10);
        shape1.addChild(shape6);
        shape18.addChild(shape20);
        shape26.addChild(shape28);
        shape18.addChild(shape21);
        shape26.addChild(shape29);
        shape1.addChild(shape4);
        shape1.addChild(shape9);
        shape14.addChild(shape15);
        shape22.addChild(shape25);
        shape1.addChild(shape2);
        shape11.addChild(shape12);
        shape4.addChild(shape5);
        shape2.addChild(shape3);
        shape6.addChild(shape7);
        shape14.addChild(shape17);
        shape22.addChild(shape23);
        
        setInitPose();
    }

    @Override
    public void render(TileEntityRelayTower tile, float partialTicks)
    {
        setToInitPose();
        shape1.render(0.0625F);
    }
}