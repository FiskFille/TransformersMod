package fiskfille.tf.client.model.tileentity;

import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;

public class ModelEnergonProcessor extends MowzieModelBase
{
    public MowzieModelRenderer shape1;
    public MowzieModelRenderer shape2;
    public MowzieModelRenderer shape2_1;
    public MowzieModelRenderer shape2_2;
    public MowzieModelRenderer shape1_1;
    public MowzieModelRenderer shape7;
    public MowzieModelRenderer shape12;
    public MowzieModelRenderer shape1_2;
    public MowzieModelRenderer shape14;
    public MowzieModelRenderer shape14_1;
    public MowzieModelRenderer shape16;
    public MowzieModelRenderer shape16_1;
    public MowzieModelRenderer shape20;
    public MowzieModelRenderer shape20_1;
    public MowzieModelRenderer rotatingrod_top;
    public MowzieModelRenderer shape2_3;
    public MowzieModelRenderer rotatechamber_base;
    public MowzieModelRenderer shape27;
    public MowzieModelRenderer shape27_1;
    public MowzieModelRenderer shape27_2;
    public MowzieModelRenderer shape27_3;
    public MowzieModelRenderer flap1;
    public MowzieModelRenderer flap2;
    public MowzieModelRenderer shape31;
    public MowzieModelRenderer rotatechamber_1;
    public MowzieModelRenderer rotatechamber_2;
    public MowzieModelRenderer rotatechamber_3;

    public ModelEnergonProcessor()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.flap1 = new MowzieModelRenderer(this, 43, 50);
        this.flap1.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.flap1.addBox(-1.5F, -0.2F, 0.2F, 3, 7, 1, 0.0F);
        this.shape14 = new MowzieModelRenderer(this, 0, 26);
        this.shape14.setRotationPoint(-5.0F, 12.6F, 6.0F);
        this.shape14.addBox(-10.0F, 0.0F, 0.0F, 10, 2, 5, 0.0F);
        this.setRotateAngle(shape14, 0.6457718232379019F, 3.141592653589793F, 0.0F);
        this.shape27 = new MowzieModelRenderer(this, 0, 0);
        this.shape27.setRotationPoint(3.0F, 12.6F, -5.0F);
        this.shape27.addBox(0.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.shape27_1 = new MowzieModelRenderer(this, 0, 0);
        this.shape27_1.setRotationPoint(3.0F, 12.6F, 5.0F);
        this.shape27_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape12 = new MowzieModelRenderer(this, 0, 52);
        this.shape12.setRotationPoint(0.0F, 10.3F, 0.0F);
        this.shape12.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
        this.shape2_1 = new MowzieModelRenderer(this, 36, 32);
        this.shape2_1.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape2_1.addBox(8.0F, -0.4F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(shape2_1, -0.017453292519943295F, 0.7853981633974483F, -0.017453292519943295F);
        this.shape1 = new MowzieModelRenderer(this, 0, 50);
        this.shape1.setRotationPoint(-6.5F, 23.0F, -6.5F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 13, 1, 13, 0.0F);
        this.shape20 = new MowzieModelRenderer(this, 0, 18);
        this.shape20.setRotationPoint(1.0F, 10.0F, -2.0F);
        this.shape20.addBox(1.0F, -5.0F, 0.0F, 2, 4, 4, 0.0F);
        this.setRotateAngle(shape20, 0.0F, 0.0F, 0.7330382858376184F);
        this.rotatechamber_base = new MowzieModelRenderer(this, 0, 11);
        this.rotatechamber_base.setRotationPoint(0.0F, 14.9F, 0.0F);
        this.rotatechamber_base.addBox(-6.0F, 0.0F, -2.0F, 12, 3, 4, 0.0F);
        this.shape20_1 = new MowzieModelRenderer(this, 0, 18);
        this.shape20_1.setRotationPoint(-1.0F, 10.0F, -2.0F);
        this.shape20_1.addBox(-3.0F, -5.0F, 0.0F, 2, 4, 4, 0.0F);
        this.setRotateAngle(shape20_1, 0.0F, 0.0F, -0.7330382858376184F);
        this.rotatechamber_2 = new MowzieModelRenderer(this, 0, 11);
        this.rotatechamber_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rotatechamber_2.addBox(-6.0F, 0.0F, -2.0F, 12, 3, 4, 0.0F);
        this.setRotateAngle(rotatechamber_2, 0.0F, 1.5707963267948966F, 0.0F);
        this.shape14_1 = new MowzieModelRenderer(this, 0, 26);
        this.shape14_1.setRotationPoint(-5.0F, 12.6F, -6.0F);
        this.shape14_1.addBox(0.0F, 0.0F, 0.0F, 10, 2, 5, 0.0F);
        this.setRotateAngle(shape14_1, 0.6457718232379019F, 0.0F, 0.0F);
        this.shape7 = new MowzieModelRenderer(this, 0, 33);
        this.shape7.setRotationPoint(-6.0F, 18.5F, -6.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 12, 5, 12, 0.0F);
        this.shape27_2 = new MowzieModelRenderer(this, 0, 0);
        this.shape27_2.setRotationPoint(-3.0F, 12.6F, 5.0F);
        this.shape27_2.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape1_2 = new MowzieModelRenderer(this, 0, 50);
        this.shape1_2.setRotationPoint(-6.5F, 13.6F, -6.5F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 13, 1, 13, 0.0F);
        this.shape1_1 = new MowzieModelRenderer(this, 0, 50);
        this.shape1_1.setRotationPoint(-6.5F, 18.0F, -6.5F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 13, 1, 13, 0.0F);
        this.rotatechamber_3 = new MowzieModelRenderer(this, 0, 11);
        this.rotatechamber_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rotatechamber_3.addBox(-6.0F, 0.0F, -2.0F, 12, 3, 4, 0.0F);
        this.setRotateAngle(rotatechamber_3, 0.0F, -0.7853981633974483F, 0.0F);
        this.shape2_2 = new MowzieModelRenderer(this, 36, 32);
        this.shape2_2.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape2_2.addBox(8.0F, -0.4F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(shape2_2, 0.017453292519943295F, 2.356194490192345F, 0.017453292519943295F);
        this.shape31 = new MowzieModelRenderer(this, 0, 33);
        this.shape31.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.shape31.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.flap2 = new MowzieModelRenderer(this, 43, 50);
        this.flap2.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.flap2.addBox(-1.5F, -0.2F, 0.2F, 3, 7, 1, 0.0F);
        this.setRotateAngle(flap2, 0.0F, -3.141592653589793F, 0.0F);
        this.shape16_1 = new MowzieModelRenderer(this, 0, 37);
        this.shape16_1.setRotationPoint(-4.0F, 10.2F, -2.0F);
        this.shape16_1.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotateAngle(shape16_1, 0.0F, 0.0F, 0.33161255787892263F);
        this.rotatingrod_top = new MowzieModelRenderer(this, 39, 53);
        this.rotatingrod_top.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.rotatingrod_top.addBox(-0.5F, -9.0F, -0.5F, 1, 9, 1, 0.0F);
        this.shape27_3 = new MowzieModelRenderer(this, 0, 0);
        this.shape27_3.setRotationPoint(-3.0F, 12.6F, -5.0F);
        this.shape27_3.addBox(-1.0F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.shape16 = new MowzieModelRenderer(this, 0, 37);
        this.shape16.mirror = true;
        this.shape16.setRotationPoint(4.0F, 10.2F, -2.0F);
        this.shape16.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotateAngle(shape16, 0.0F, 0.0F, -0.33161255787892263F);
        this.rotatechamber_1 = new MowzieModelRenderer(this, 0, 11);
        this.rotatechamber_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rotatechamber_1.addBox(-6.0F, 0.0F, -2.0F, 12, 3, 4, 0.0F);
        this.setRotateAngle(rotatechamber_1, 0.0F, 0.7853981633974483F, 0.0F);
        this.shape2 = new MowzieModelRenderer(this, 36, 32);
        this.shape2.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape2.addBox(8.0F, -0.4F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(shape2, -0.017453292519943295F, -2.356194490192345F, 0.017453292519943295F);
        this.shape2_3 = new MowzieModelRenderer(this, 36, 32);
        this.shape2_3.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape2_3.addBox(8.0F, -0.4F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(shape2_3, 0.017453292519943295F, -0.7853981633974483F, -0.017453292519943295F);
        this.rotatingrod_top.addChild(this.flap1);
        this.rotatechamber_base.addChild(this.rotatechamber_2);
        this.rotatechamber_base.addChild(this.rotatechamber_3);
        this.rotatingrod_top.addChild(this.shape31);
        this.rotatingrod_top.addChild(this.flap2);
        this.rotatechamber_base.addChild(this.rotatechamber_1);
        setInitPose();
    }
    
    public void render(TileEntityEnergonProcessor tileentity)
    {
    	if (tileentity.burnTime == 0)
    	{
    		setToInitPose();
    	}
    	
    	
    	rotatingrod_top.rotateAngleY = tileentity.animationBurnTime * tileentity.animationTimer + tileentity.animationTimer * 10;
        rotatingrod_top.rotationPointY = 11 + tileentity.animationTimer * 4;
        flap1.rotateAngleX = (float)Math.PI / 2 * tileentity.animationTimer * 0.9F;
        flap2.rotateAngleX = (float)Math.PI / 2 * tileentity.animationTimer * 0.9F;
        rotatechamber_base.rotateAngleY = -tileentity.animationBurnTime * tileentity.animationTimer - tileentity.animationTimer * 10;
    	
    	float f5 = 0.0625F;
        this.shape14.render(f5);
        this.shape27.render(f5);
        this.shape27_1.render(f5);
        this.shape12.render(f5);
        this.shape2_1.render(f5);
        this.shape1.render(f5);
        this.shape20.render(f5);
        this.rotatechamber_base.render(f5);
        this.shape20_1.render(f5);
        this.shape14_1.render(f5);
        this.shape7.render(f5);
        this.shape27_2.render(f5);
        this.shape1_2.render(f5);
        this.shape1_1.render(f5);
        this.shape2_2.render(f5);
        this.shape16_1.render(f5);
        this.rotatingrod_top.render(f5);
        this.shape27_3.render(f5);
        this.shape16.render(f5);
        this.shape2.render(f5);
        this.shape2_3.render(f5);
    }
    
    public void setRotateAngle(MowzieModelRenderer MowzieModelRenderer, float x, float y, float z)
    {
        MowzieModelRenderer.rotateAngleX = x;
        MowzieModelRenderer.rotateAngleY = y;
        MowzieModelRenderer.rotateAngleZ = z;
    }
}