package fiskfille.tf.client.model.tileentity;

import fiskfille.tf.client.model.tools.ModelRendererBreakable;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.tools.MowzieModelBase;

public class ModelDisplayPedestal extends MowzieModelBase
{
    public ModelRendererTF main;
    public ModelRendererTF backLeftFoot;
    public ModelRendererTF frontLeftFoot;
    public ModelRendererTF frontRightFoot;
    public ModelRendererTF backRightFoot;
    public ModelRendererTF top;

    public ModelDisplayPedestal()
    {
        textureWidth = 64;
        textureHeight = 64;
        main = new ModelRendererBreakable(this, 0, 10);
        main.addBox(-3F, -8F, -3F, 6, 8, 6);
        main.setRotationPoint(0F, 24F, 0F);
        main.setTextureSize(64, 64);
        main.mirror = true;
        setRotateAngle(main, 0F, 0F, 0F);
        backLeftFoot = new ModelRendererBreakable(this, 24, 10);
        backLeftFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
        backLeftFoot.setRotationPoint(2F, 17F, 2F);
        backLeftFoot.setTextureSize(64, 64);
        backLeftFoot.mirror = true;
        setRotateAngle(backLeftFoot, -0.6981317F, -2.356194F, 0F);
        frontLeftFoot = new ModelRendererBreakable(this, 24, 10);
        frontLeftFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
        frontLeftFoot.setRotationPoint(2F, 17F, -2F);
        frontLeftFoot.setTextureSize(64, 64);
        frontLeftFoot.mirror = true;
        setRotateAngle(frontLeftFoot, -0.6981317F, -0.7853982F, 0F);
        frontRightFoot = new ModelRendererBreakable(this, 24, 10);
        frontRightFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
        frontRightFoot.setRotationPoint(-2F, 17F, -2F);
        frontRightFoot.setTextureSize(64, 64);
        frontRightFoot.mirror = true;
        setRotateAngle(frontRightFoot, -0.6981317F, 0.7853982F, 0F);
        backRightFoot = new ModelRendererBreakable(this, 24, 10);
        backRightFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
        backRightFoot.setRotationPoint(-2F, 17F, 2F);
        backRightFoot.setTextureSize(64, 64);
        backRightFoot.mirror = true;
        setRotateAngle(backRightFoot, -0.6981317F, 2.356194F, 0F);
        top = new ModelRendererBreakable(this, 0, 0);
        top.addBox(-4F, -2F, -4F, 8, 2, 8);
        top.setRotationPoint(0F, 17.5F, 0F);
        top.setTextureSize(64, 64);
        top.mirror = true;
        setRotateAngle(top, 0F, 0F, 0F);

        setInitPose();
    }

    public void render()
    {
        setToInitPose();
        main.render(0.0625F);
        backLeftFoot.render(0.0625F);
        frontLeftFoot.render(0.0625F);
        frontRightFoot.render(0.0625F);
        backRightFoot.render(0.0625F);
        top.render(0.0625F);
    }
}
