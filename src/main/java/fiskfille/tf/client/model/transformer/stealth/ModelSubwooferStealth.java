package fiskfille.tf.client.model.transformer.stealth;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.playerdata.TFDataManager;

public class ModelSubwooferStealth extends MowzieModelBase
{
    public ModelRenderer vehicleBase;
    public ModelRenderer vehicleStomach;
    public ModelRenderer vehicleCrotch1;
    public ModelRenderer vehicleRear1;
    public ModelRenderer vehicleRear2;
    public ModelRenderer vehicleChestMain1;
    public ModelRenderer vehicleFrontWheel1;
    public ModelRenderer vehicleFrontWheel2;
    public ModelRenderer vehicleFrontChestR;
    public ModelRenderer vehicleFrontChestL;
    public ModelRenderer vehicleChestMain2;
    public ModelRenderer vehicleChestMain3;
    public ModelRenderer vehicleChestMain5;
    public ModelRenderer vehicleChestMain6;
    public ModelRenderer vehicleDish1;
    public ModelRenderer vehicleShoulderBaseL;
    public ModelRenderer vehicleShoulderBaseR;
    public ModelRenderer vehicleCover1;
    public ModelRenderer vehicleCover2;
    public ModelRenderer vehicleCover3;
    public ModelRenderer vehicleCover4;
    public ModelRenderer vehicleCover5;
    public ModelRenderer vehicleCover6;
    public ModelRenderer vehicleHood;
    public ModelRenderer vehicleBasePlate;
    public ModelRenderer vehicleAntenna1;
    public ModelRenderer vehicleAntenna2;
    public ModelRenderer vehicleDish2;
    public ModelRenderer vehicleUpperArmL;
    public ModelRenderer vehicleLowerArmL1;
    public ModelRenderer vehicleUpperArmR;
    public ModelRenderer vehicleLowerArmRL1;
    public ModelRenderer vehicleBass2;
    public ModelRenderer vehicleBass3;
    public ModelRenderer vehicleRpg1;
    public ModelRenderer vehicleRpg2;
    public ModelRenderer vehicleFront1;
    public ModelRenderer vehicleFront2;
    public ModelRenderer vehicleUpperLegL;
    public ModelRenderer vehicleUpperLegR;
    public ModelRenderer vehicleLowerLegL1;
    public ModelRenderer vehicleCannonL1;
    public ModelRenderer vehicleCannonL2;
    public ModelRenderer vehicleRearWheel2;
    public ModelRenderer vehicleLowerLegL3;
    public ModelRenderer vehicleLowerLegR1;
    public ModelRenderer vehicleCannonR1;
    public ModelRenderer vehicleCannonR2;
    public ModelRenderer vehicleLowerLegR3;
    public ModelRenderer vehicleRearWheel1;

    public ModelSubwooferStealth()
    {
        textureWidth = 128;
        textureHeight = 128;
        vehicleLowerArmL1 = new ModelRenderer(this, 48, 94);
        vehicleLowerArmL1.setRotationPoint(-1.4F, 4.5F, 0.6F);
        vehicleLowerArmL1.addBox(-1.3F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        vehicleChestMain6 = new ModelRenderer(this, 0, 89);
        vehicleChestMain6.mirror = true;
        vehicleChestMain6.setRotationPoint(-2.9F, 0.5F, 3.0F);
        vehicleChestMain6.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        setRotateAngle(vehicleChestMain6, 3.141592653589793F, 0.0F, 0.0F);
        vehicleChestMain1 = new ModelRenderer(this, 0, 67);
        vehicleChestMain1.setRotationPoint(0.0F, -2.0F, 0.0F);
        vehicleChestMain1.addBox(-4.0F, -4.0F, -3.0F, 8, 4, 6, 0.0F);
        setRotateAngle(vehicleChestMain1, -1.5707963267948966F, 0.0F, 0.0F);
        vehicleLowerLegR1 = new ModelRenderer(this, 76, 66);
        vehicleLowerLegR1.mirror = true;
        vehicleLowerLegR1.setRotationPoint(-1.3F, 2.5F, -1.0F);
        vehicleLowerLegR1.addBox(-1.7F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        vehicleCover3 = new ModelRenderer(this, 0, 0);
        vehicleCover3.mirror = true;
        vehicleCover3.setRotationPoint(-4.1F, -3.9F, -3.4F);
        vehicleCover3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 14, 0.0F);
        vehicleChestMain2 = new ModelRenderer(this, 18, 62);
        vehicleChestMain2.setRotationPoint(-3.0F, -0.6F, -6.1F);
        vehicleChestMain2.addBox(0.0F, 0.0F, 0.3F, 6, 3, 2, 0.0F);
        setRotateAngle(vehicleChestMain2, 0.03490658503988659F, 0.0F, 0.0F);
        vehicleUpperLegR = new ModelRenderer(this, 76, 58);
        vehicleUpperLegR.mirror = true;
        vehicleUpperLegR.setRotationPoint(-4.0F, 1.0F, 0.0F);
        vehicleUpperLegR.addBox(-2.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        setRotateAngle(vehicleUpperLegR, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleBase = new ModelRenderer(this, 0, 98);
        vehicleBase.setRotationPoint(0.0F, 20.0F, -0.5F);
        vehicleBase.addBox(-3.5F, 0.0F, -2.5F, 7, 2, 5, 0.0F);
        vehicleCannonR2 = new ModelRenderer(this, 39, 14);
        vehicleCannonR2.setRotationPoint(-0.9F, -0.5F, -1.9F);
        vehicleCannonR2.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        vehicleFront2 = new ModelRenderer(this, 0, 0);
        vehicleFront2.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleFront2.addBox(7.0F, 0.4F, -2.8F, 1, 3, 1, 0.0F);
        setRotateAngle(vehicleFront2, -0.13962634015954636F, 0.0F, 0.0F);
        vehicleChestMain3 = new ModelRenderer(this, 0, 83);
        vehicleChestMain3.setRotationPoint(-3.0F, -4.0F, 3.0F);
        vehicleChestMain3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
        vehicleCrotch1 = new ModelRenderer(this, 0, 105);
        vehicleCrotch1.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleCrotch1.addBox(-1.0F, -0.7F, -2.4F, 2, 3, 4, 0.0F);
        vehicleRear1 = new ModelRenderer(this, 0, 22);
        vehicleRear1.setRotationPoint(-4.0F, -0.5F, 7.0F);
        vehicleRear1.addBox(0.0F, 0.0F, 0.0F, 8, 4, 2, 0.0F);
        vehicleCover6 = new ModelRenderer(this, 17, 0);
        vehicleCover6.setRotationPoint(4.1F, -1.4F, -2.4F);
        vehicleCover6.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
        setRotateAngle(vehicleCover6, 0.0F, 0.0F, -0.017453292519943295F);
        vehicleFrontWheel2 = new ModelRenderer(this, 0, 77);
        vehicleFrontWheel2.mirror = true;
        vehicleFrontWheel2.setRotationPoint(2.5F, 1.5F, -3.7F);
        vehicleFrontWheel2.addBox(0.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        vehicleFrontChestR = new ModelRenderer(this, 10, 77);
        vehicleFrontChestR.setRotationPoint(-4.0F, -4.0F, -3.0F);
        vehicleFrontChestR.addBox(0.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        setRotateAngle(vehicleFrontChestR, -0.07330382858376185F, 0.0F, -0.005235987755982988F);
        vehicleRearWheel2 = new ModelRenderer(this, 0, 77);
        vehicleRearWheel2.setRotationPoint(0.2F, 4.4F, -0.5F);
        vehicleRearWheel2.addBox(0.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        vehicleLowerLegL3 = new ModelRenderer(this, 79, 82);
        vehicleLowerLegL3.setRotationPoint(0.9F, -1.6F, 1.9F);
        vehicleLowerLegL3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        setRotateAngle(vehicleLowerLegL3, 0.0F, -0.6108652381980153F, 0.0F);
        vehicleFrontWheel1 = new ModelRenderer(this, 0, 77);
        vehicleFrontWheel1.setRotationPoint(-2.5F, 1.5F, -3.7F);
        vehicleFrontWheel1.addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        vehicleAntenna2 = new ModelRenderer(this, 30, 67);
        vehicleAntenna2.setRotationPoint(6.0F, 1.1F, 6.0F);
        vehicleAntenna2.addBox(-0.5F, -7.0F, -0.5F, 1, 7, 1, 0.0F);
        setRotateAngle(vehicleAntenna2, -0.7853981633974483F, 0.0F, 0.0F);
        vehicleCannonL2 = new ModelRenderer(this, 39, 14);
        vehicleCannonL2.setRotationPoint(0.9F, -0.5F, -1.9F);
        vehicleCannonL2.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        vehicleRpg2 = new ModelRenderer(this, 36, 4);
        vehicleRpg2.setRotationPoint(3.4F, 0.2F, -6.7F);
        vehicleRpg2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
        vehicleRpg1 = new ModelRenderer(this, 36, 4);
        vehicleRpg1.setRotationPoint(0.6F, 0.2F, -6.7F);
        vehicleRpg1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
        vehicleUpperArmL = new ModelRenderer(this, 61, 79);
        vehicleUpperArmL.setRotationPoint(1.7F, 1.0F, 0.0F);
        vehicleUpperArmL.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        vehicleCover2 = new ModelRenderer(this, 0, 42);
        vehicleCover2.setRotationPoint(-4.0F, -4.5F, 3.0F);
        vehicleCover2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 7, 0.0F);
        vehicleCover5 = new ModelRenderer(this, 17, 0);
        vehicleCover5.mirror = true;
        vehicleCover5.setRotationPoint(-4.1F, -1.4F, -2.4F);
        vehicleCover5.addBox(0.0F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
        setRotateAngle(vehicleCover5, 0.0F, 0.0F, 0.017453292519943295F);
        vehicleCannonL1 = new ModelRenderer(this, 39, 14);
        vehicleCannonL1.setRotationPoint(0.9F, -0.5F, -0.1F);
        vehicleCannonL1.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        vehicleFrontChestL = new ModelRenderer(this, 10, 77);
        vehicleFrontChestL.mirror = true;
        vehicleFrontChestL.setRotationPoint(4.0F, -4.0F, -3.0F);
        vehicleFrontChestL.addBox(-4.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        setRotateAngle(vehicleFrontChestL, -0.07330382858376185F, 0.0F, 0.005235987755982988F);
        vehicleShoulderBaseL = new ModelRenderer(this, 48, 78);
        vehicleShoulderBaseL.setRotationPoint(1.0F, -1.9F, 0.0F);
        vehicleShoulderBaseL.addBox(-0.1F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        setRotateAngle(vehicleShoulderBaseL, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleDish1 = new ModelRenderer(this, 0, 67);
        vehicleDish1.setRotationPoint(-4.0F, -4.6F, 6.2F);
        vehicleDish1.addBox(-2.0F, -3.0F, 0.0F, 2, 3, 1, 0.0F);
        setRotateAngle(vehicleDish1, 0.017453292519943295F, 0.12217304763960307F, 1.5707963267948966F);
        vehicleLowerLegR3 = new ModelRenderer(this, 79, 82);
        vehicleLowerLegR3.mirror = true;
        vehicleLowerLegR3.setRotationPoint(-0.9F, -1.6F, 1.9F);
        vehicleLowerLegR3.addBox(-1.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        setRotateAngle(vehicleLowerLegR3, 0.0F, 0.5235987755982988F, 0.0F);
        vehicleCover4 = new ModelRenderer(this, 0, 0);
        vehicleCover4.setRotationPoint(4.1F, -3.9F, -3.4F);
        vehicleCover4.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 14, 0.0F);
        setRotateAngle(vehicleCover4, 0.006283185307179587F, 0.0F, 0.0F);
        vehicleLowerLegL1 = new ModelRenderer(this, 76, 66);
        vehicleLowerLegL1.setRotationPoint(1.3F, 2.5F, -1.0F);
        vehicleLowerLegL1.addBox(-1.3F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        vehicleBass2 = new ModelRenderer(this, 0, 112);
        vehicleBass2.setRotationPoint(1.7F, -2.5F, 2.1F);
        vehicleBass2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        setRotateAngle(vehicleBass2, 0.0F, 1.5707963267948966F, 0.0F);
        vehicleFront1 = new ModelRenderer(this, 0, 0);
        vehicleFront1.mirror = true;
        vehicleFront1.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleFront1.addBox(0.0F, 0.4F, -2.8F, 1, 3, 1, 0.0F);
        setRotateAngle(vehicleFront1, -0.13962634015954636F, 0.0F, 0.0F);
        vehicleCover1 = new ModelRenderer(this, 0, 34);
        vehicleCover1.setRotationPoint(-3.0F, -4.5F, 3.0F);
        vehicleCover1.addBox(0.0F, 0.0F, -5.0F, 6, 1, 6, 0.0F);
        setRotateAngle(vehicleCover1, -0.2844886680750757F, 0.0F, 0.0F);
        vehicleStomach = new ModelRenderer(this, 0, 58);
        vehicleStomach.setRotationPoint(0.0F, 1.0F, 0.0F);
        vehicleStomach.addBox(-2.5F, -5.5F, -2.0F, 5, 5, 4, 0.0F);
        setRotateAngle(vehicleStomach, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleLowerArmRL1 = new ModelRenderer(this, 48, 94);
        vehicleLowerArmRL1.mirror = true;
        vehicleLowerArmRL1.setRotationPoint(1.4F, 4.5F, 0.6F);
        vehicleLowerArmRL1.addBox(-1.7F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        vehicleAntenna1 = new ModelRenderer(this, 30, 67);
        vehicleAntenna1.setRotationPoint(0.0F, 1.1F, 6.0F);
        vehicleAntenna1.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        setRotateAngle(vehicleAntenna1, -0.7853981633974483F, 0.0F, 0.0F);
        vehicleRear2 = new ModelRenderer(this, 0, 29);
        vehicleRear2.setRotationPoint(-4.0F, -2.5F, 8.0F);
        vehicleRear2.addBox(0.0F, -1.0F, 0.0F, 8, 3, 1, 0.0F);
        vehicleRearWheel1 = new ModelRenderer(this, 0, 77);
        vehicleRearWheel1.mirror = true;
        vehicleRearWheel1.setRotationPoint(-0.2F, 4.4F, -0.5F);
        vehicleRearWheel1.addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        vehicleUpperLegL = new ModelRenderer(this, 76, 58);
        vehicleUpperLegL.setRotationPoint(4.0F, 1.0F, 0.0F);
        vehicleUpperLegL.addBox(0.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        setRotateAngle(vehicleUpperLegL, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleChestMain5 = new ModelRenderer(this, 0, 89);
        vehicleChestMain5.setRotationPoint(2.9F, 0.5F, 3.0F);
        vehicleChestMain5.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        setRotateAngle(vehicleChestMain5, 3.141592653589793F, 0.0F, 0.0F);
        vehicleUpperArmR = new ModelRenderer(this, 61, 79);
        vehicleUpperArmR.setRotationPoint(-1.7F, 1.0F, 0.0F);
        vehicleUpperArmR.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        vehicleCannonR1 = new ModelRenderer(this, 39, 14);
        vehicleCannonR1.setRotationPoint(-0.9F, -0.5F, -0.1F);
        vehicleCannonR1.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
        vehicleHood = new ModelRenderer(this, 0, 18);
        vehicleHood.setRotationPoint(-4.0F, -1.0F, -3.0F);
        vehicleHood.addBox(0.0F, 0.0F, -2.8F, 8, 1, 3, 0.0F);
        setRotateAngle(vehicleHood, 0.13962634015954636F, 0.0F, 0.0F);
        vehicleBasePlate = new ModelRenderer(this, 20, 2);
        vehicleBasePlate.setRotationPoint(-3.0F, 1.6F, -5.4F);
        vehicleBasePlate.addBox(0.0F, 0.0F, 0.0F, 6, 1, 16, 0.0F);
        vehicleBass3 = new ModelRenderer(this, 0, 112);
        vehicleBass3.mirror = true;
        vehicleBass3.setRotationPoint(-2.3F, -2.5F, 2.1F);
        vehicleBass3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        setRotateAngle(vehicleBass3, 0.0F, 1.5707963267948966F, 0.0F);
        vehicleShoulderBaseR = new ModelRenderer(this, 48, 78);
        vehicleShoulderBaseR.setRotationPoint(-1.0F, -1.9F, 0.0F);
        vehicleShoulderBaseR.addBox(-2.9F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        setRotateAngle(vehicleShoulderBaseR, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleDish2 = new ModelRenderer(this, 22, 67);
        vehicleDish2.setRotationPoint(0.0F, -3.0F, 1.0F);
        vehicleDish2.addBox(-2.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        setRotateAngle(vehicleDish2, 0.2792526803190927F, 0.0F, 0.0F);
        vehicleUpperArmL.addChild(vehicleLowerArmL1);
        vehicleChestMain1.addChild(vehicleChestMain6);
        vehicleStomach.addChild(vehicleChestMain1);
        vehicleUpperLegR.addChild(vehicleLowerLegR1);
        vehicleChestMain1.addChild(vehicleCover3);
        vehicleChestMain1.addChild(vehicleChestMain2);
        vehicleCrotch1.addChild(vehicleUpperLegR);
        vehicleUpperLegR.addChild(vehicleCannonR2);
        vehicleHood.addChild(vehicleFront2);
        vehicleChestMain1.addChild(vehicleChestMain3);
        vehicleBase.addChild(vehicleCrotch1);
        vehicleBase.addChild(vehicleRear1);
        vehicleChestMain1.addChild(vehicleCover6);
        vehicleChestMain1.addChild(vehicleFrontWheel2);
        vehicleChestMain1.addChild(vehicleFrontChestR);
        vehicleLowerLegL1.addChild(vehicleRearWheel2);
        vehicleLowerLegL1.addChild(vehicleLowerLegL3);
        vehicleChestMain1.addChild(vehicleFrontWheel1);
        vehicleChestMain3.addChild(vehicleAntenna2);
        vehicleUpperLegL.addChild(vehicleCannonL2);
        vehicleCover1.addChild(vehicleRpg2);
        vehicleCover1.addChild(vehicleRpg1);
        vehicleShoulderBaseL.addChild(vehicleUpperArmL);
        vehicleChestMain1.addChild(vehicleCover2);
        vehicleChestMain1.addChild(vehicleCover5);
        vehicleUpperLegL.addChild(vehicleCannonL1);
        vehicleChestMain1.addChild(vehicleFrontChestL);
        vehicleChestMain1.addChild(vehicleShoulderBaseL);
        vehicleChestMain1.addChild(vehicleDish1);
        vehicleLowerLegR1.addChild(vehicleLowerLegR3);
        vehicleChestMain1.addChild(vehicleCover4);
        vehicleUpperLegL.addChild(vehicleLowerLegL1);
        vehicleLowerArmRL1.addChild(vehicleBass2);
        vehicleHood.addChild(vehicleFront1);
        vehicleChestMain1.addChild(vehicleCover1);
        vehicleBase.addChild(vehicleStomach);
        vehicleUpperArmR.addChild(vehicleLowerArmRL1);
        vehicleChestMain3.addChild(vehicleAntenna1);
        vehicleBase.addChild(vehicleRear2);
        vehicleLowerLegR1.addChild(vehicleRearWheel1);
        vehicleCrotch1.addChild(vehicleUpperLegL);
        vehicleChestMain1.addChild(vehicleChestMain5);
        vehicleShoulderBaseR.addChild(vehicleUpperArmR);
        vehicleUpperLegR.addChild(vehicleCannonR1);
        vehicleChestMain1.addChild(vehicleHood);
        vehicleChestMain1.addChild(vehicleBasePlate);
        vehicleLowerArmRL1.addChild(vehicleBass3);
        vehicleChestMain1.addChild(vehicleShoulderBaseR);
        vehicleDish1.addChild(vehicleDish2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        vehicleBase.render(0.0625F);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            int t = TFDataManager.getStealthModeTimer(player);
            float f = (float) (5 - t) / 5;

            vehicleAntenna1.rotateAngleX = -f;
            vehicleAntenna2.rotateAngleX = -f;
            vehicleCover1.rotateAngleX = -0.2844886680750757F * f;
            vehicleRpg1.rotationPointZ = -6.7F + (float) t / 2;
            vehicleRpg2.rotationPointZ = -6.7F + (float) t / 2;
            vehicleUpperLegR.rotationPointX = -4 * f;
            vehicleUpperLegL.rotationPointX = 4 * f;
            vehicleLowerLegR3.rotateAngleY = 0.5235987755982988F * f;
            vehicleLowerLegL3.rotateAngleY = -0.5235987755982988F * f;
            vehicleUpperLegR.rotationPointZ = 3 * (float) t / 5;
            vehicleLowerLegR1.rotationPointY = 2.5F - 3 * (float) t / 5;
            vehicleUpperLegL.rotationPointZ = 3 * (float) t / 5;
            vehicleLowerLegL1.rotationPointY = 2.5F - 3 * (float) t / 5;
            vehicleCannonR1.rotationPointY = -0.5F + 8 * (float) t / 5;
            vehicleCannonR2.rotationPointY = -0.5F + 8 * (float) t / 5;
            vehicleCannonL1.rotationPointY = -0.5F + 8 * (float) t / 5;
            vehicleCannonL2.rotationPointY = -0.5F + 8 * (float) t / 5;

            VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

            if (transformedPlayer != null)
            {
                for (ModelRenderer modelRenderer : new ModelRenderer[] { vehicleFrontWheel1, vehicleFrontWheel2, vehicleRearWheel1, vehicleRearWheel2 })
                {
                    float wheelSpinSpeed = (transformedPlayer.getForwardVelocity() < 0 ? -par1 : par1) * 0.8F;
                    modelRenderer.rotateAngleX = wheelSpinSpeed;
                }

                float vel = (float) transformedPlayer.getHorizontalVelocity();
                float rot = -(vel > 0 ? vel <= 8 ? vel : 8 : vel >= -8 ? vel : -8) / 10;

                vehicleRearWheel1.rotateAngleZ = rot;
                vehicleRearWheel2.rotateAngleZ = rot;
                vehicleFrontWheel1.rotateAngleY = -rot;
                vehicleFrontWheel2.rotateAngleY = -rot;
            }

            vehicleBase.rotateAngleY = bipedBody.rotateAngleY;

            if (player == Minecraft.getMinecraft().thePlayer)
            {
                vehicleBase.rotateAngleX = -(float) player.motionY - 0.0784000015258789F;
            }
            else
            {
                vehicleBase.rotateAngleX = -(float) (player.posY - player.prevPosY) * 1.5F;
            }
        }
    }
}