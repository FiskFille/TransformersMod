package fiskfille.tf.client.model.tileentity;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;

public class ModelEnergyPort extends MowzieModelBase
{
    public MowzieModelRenderer shape1;
    public MowzieModelRenderer shape2;
    public MowzieModelRenderer shape3;
    public MowzieModelRenderer shape4;
    public MowzieModelRenderer shape5;
    public MowzieModelRenderer shape6;
    public MowzieModelRenderer shape7;
    public MowzieModelRenderer shape8;
    public MowzieModelRenderer shape9;

    public ModelEnergyPort()
    {
        textureWidth = 64;
        textureHeight = 32;
        shape7 = new MowzieModelRenderer(this, 0, 11);
        shape7.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape7.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape7, 0.4886921905584123F, -0.7853981633974483F, 0.0F);
        shape4 = new MowzieModelRenderer(this, 0, 11);
        shape4.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape4.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape4, 0.4886921905584123F, 1.5707963267948966F, 0.0F);
        shape2 = new MowzieModelRenderer(this, 0, 11);
        shape2.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape2.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape2, 0.4886921905584123F, 0.0F, 0.0F);
        shape1 = new MowzieModelRenderer(this, 0, 0);
        shape1.setRotationPoint(0.0F, 23.62F, 0.0F);
        shape1.addBox(-5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F);
        shape6 = new MowzieModelRenderer(this, 0, 11);
        shape6.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape6.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape6, 0.4886921905584123F, 3.141592653589793F, 0.0F);
        shape9 = new MowzieModelRenderer(this, 0, 11);
        shape9.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape9.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape9, 0.4886921905584123F, -2.356194490192345F, 0.0F);
        shape3 = new MowzieModelRenderer(this, 0, 11);
        shape3.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape3.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape3, 0.4886921905584123F, 0.7853981633974483F, 0.0F);
        shape5 = new MowzieModelRenderer(this, 0, 11);
        shape5.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape5.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape5, 0.4886921905584123F, 2.356194490192345F, 0.0F);
        shape8 = new MowzieModelRenderer(this, 0, 11);
        shape8.setRotationPoint(0.0F, -1.06F, 0.0F);
        shape8.addBox(-2.5F, 1.0F, 4.31F, 5, 4, 2, 0.0F);
        setRotateAngle(shape8, 0.4886921905584123F, -1.5707963267948966F, 0.0F);
        shape1.addChild(shape7);
        shape1.addChild(shape4);
        shape1.addChild(shape2);
        shape1.addChild(shape6);
        shape1.addChild(shape9);
        shape1.addChild(shape3);
        shape1.addChild(shape5);
        shape1.addChild(shape8);
    }

    public void render()
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(shape1.offsetX, shape1.offsetY, shape1.offsetZ);
        GL11.glTranslatef(shape1.rotationPointX * 0.0625F, shape1.rotationPointY * 0.0625F, shape1.rotationPointZ * 0.0625F);
        GL11.glScaled(1.0103125D, 1.0103125D, 1.0103125D);
        GL11.glTranslatef(-shape1.offsetX, -shape1.offsetY, -shape1.offsetZ);
        GL11.glTranslatef(-shape1.rotationPointX * 0.0625F, -shape1.rotationPointY * 0.0625F, -shape1.rotationPointZ * 0.0625F);
        shape1.render(0.0625F);
        GL11.glPopMatrix();
    }
}
