package fiskfille.tf.client.model.transformer.stealth;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;

public class ModelVurpStealth extends ModelTransformerBase
{
    public MowzieModelRenderer vehicleBase;
    public MowzieModelRenderer vehicleWaist1;
    public MowzieModelRenderer vehicleWaist2;
    public MowzieModelRenderer vehicleWaistConnector1;
    public MowzieModelRenderer vehicleTorsoConnector1;
    public MowzieModelRenderer vehicleUpperLegR1;
    public MowzieModelRenderer vehicleUpperLegL1;
    public MowzieModelRenderer vehicleRPlate1;
    public MowzieModelRenderer vehicleLPlate1;
    public MowzieModelRenderer vehicleRearPlate;
    public MowzieModelRenderer vehicleUpperLegR2;
    public MowzieModelRenderer vehicleKneeR1;
    public MowzieModelRenderer vehicleLowerLegR1;
    public MowzieModelRenderer vehicleLowerLegR7;
    public MowzieModelRenderer vehicleLowerLegR4;
    public MowzieModelRenderer vehicleLowerLegR5;
    public MowzieModelRenderer vehicleWheelBackR;
    public MowzieModelRenderer vehicleUpperLegL2;
    public MowzieModelRenderer vehicleKneeL2;
    public MowzieModelRenderer vehicleLowerLegL1;
    public MowzieModelRenderer vehicleLowerLegL7;
    public MowzieModelRenderer vehicleLowerLegL4;
    public MowzieModelRenderer vehicleLowerLegL5;
    public MowzieModelRenderer vehicleWheelBackL;
    public MowzieModelRenderer vehicleTorsoBase;
    public MowzieModelRenderer vehicleHood1;
    public MowzieModelRenderer vehicleFrontR1;
    public MowzieModelRenderer vehicleFrontL1;
    public MowzieModelRenderer vehicleBackPlate1;
    public MowzieModelRenderer vehicleBackPlate2;
    public MowzieModelRenderer vehicleWheelR;
    public MowzieModelRenderer vehicleWheelL;
    public MowzieModelRenderer vehicleArmBaseR1;
    public MowzieModelRenderer vehicleArmBaseL1;
    public MowzieModelRenderer vehicleWindow1;
    public MowzieModelRenderer vehicleBasePlate;
    public MowzieModelRenderer vehicleHood2;
    public MowzieModelRenderer vehicleHood3;
    public MowzieModelRenderer vehicleHood4;
    public MowzieModelRenderer vehicleFrontR2;
    public MowzieModelRenderer vehicleFrontL2;
    public MowzieModelRenderer vehicleUpperArmR1;
    public MowzieModelRenderer vehicleShoulderL1;
    public MowzieModelRenderer vehicleLowerArmR1;
    public MowzieModelRenderer vehicleLowerArmR2;
    public MowzieModelRenderer vehicleUpperArmL1;
    public MowzieModelRenderer vehicleShoulderL2;
    public MowzieModelRenderer vehicleLowerArmL1;
    public MowzieModelRenderer vehicleLowerArmL2;
    public MowzieModelRenderer vehicleWindow2;
    public MowzieModelRenderer vehicleRear1;
    public MowzieModelRenderer vehicleRear2;
    public MowzieModelRenderer vehicleRpg1;
    public MowzieModelRenderer vehicleRpg2;
    public MowzieModelRenderer vehicleGun1;
    public MowzieModelRenderer vehicleGun2;
    public MowzieModelRenderer vehicleGun3;
    public MowzieModelRenderer vehicleGun4;

    public ModelVurpStealth()
    {
        textureWidth = 128;
        textureHeight = 128;
        vehicleLowerLegR4 = new MowzieModelRenderer(this, 8, 83);
        vehicleLowerLegR4.setRotationPoint(0.3F, 2.5F, 0.9F);
        vehicleLowerLegR4.addBox(-0.5F, -3.0F, -2.0F, 1, 3, 2, 0.0F);
        setRotateAngle(vehicleLowerLegR4, 1.239183768915974F, 0.10471975511965977F, -0.08726646259971647F);
        vehicleBase = new MowzieModelRenderer(this, 0, 60);
        vehicleBase.setRotationPoint(0.0F, 22.3F, 1.0F);
        vehicleBase.addBox(-2.0F, 0.0F, -1.0F, 4, 1, 3, 0.0F);
        setRotateAngle(vehicleBase, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleRpg2 = new MowzieModelRenderer(this, 26, 0);
        vehicleRpg2.mirror = true;
        vehicleRpg2.setRotationPoint(3.9F, -0.5F, 2.0F);
        vehicleRpg2.addBox(0.0F, -0.7F, -3.5F, 1, 2, 5, 0.0F);
        vehicleRPlate1 = new MowzieModelRenderer(this, 0, 17);
        vehicleRPlate1.setRotationPoint(0.0F, 0.4F, 0.3F);
        vehicleRPlate1.addBox(-3.4F, -0.9F, -5.2F, 3, 1, 6, 0.0F);
        setRotateAngle(vehicleRPlate1, 0.061086523819801536F, 0.593411945678072F, -0.017453292519943295F);
        vehicleWaistConnector1 = new MowzieModelRenderer(this, 4, 64);
        vehicleWaistConnector1.setRotationPoint(0.0F, 2.0F, -1.0F);
        vehicleWaistConnector1.addBox(-1.0F, -0.1F, -2.3F, 2, 3, 4, 0.0F);
        setRotateAngle(vehicleWaistConnector1, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleLowerLegR5 = new MowzieModelRenderer(this, 16, 75);
        vehicleLowerLegR5.setRotationPoint(-1.4F, 0.0F, 0.0F);
        vehicleLowerLegR5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        setRotateAngle(vehicleLowerLegR5, -0.15707963267948966F, -0.06981317007977318F, -0.05235987755982988F);
        vehicleLowerLegL5 = new MowzieModelRenderer(this, 16, 75);
        vehicleLowerLegL5.mirror = true;
        vehicleLowerLegL5.setRotationPoint(1.4F, 0.0F, 0.0F);
        vehicleLowerLegL5.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        setRotateAngle(vehicleLowerLegL5, -0.15707963267948966F, 0.06981317007977318F, 0.05235987755982988F);
        vehicleWindow2 = new MowzieModelRenderer(this, 0, 24);
        vehicleWindow2.setRotationPoint(0.0F, -0.2F, 3.0F);
        vehicleWindow2.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 2, 0.0F);
        setRotateAngle(vehicleWindow2, -0.20943951023931953F, 0.0F, 0.0F);
        vehicleWheelL = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelL.mirror = true;
        vehicleWheelL.setRotationPoint(3.6F, 0.3F, -1.0F);
        vehicleWheelL.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        vehicleHood3 = new MowzieModelRenderer(this, 47, 65);
        vehicleHood3.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleHood3.addBox(-1.8F, -3.3F, -1.0F, 1, 4, 1, 0.0F);
        setRotateAngle(vehicleHood3, -0.005235987755982988F, 0.003490658503988659F, -0.22165681500327983F);

        vehicleWheelBackR = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelBackR.setRotationPoint(-2.0F, 2.0F, 2.3F);
        vehicleWheelBackR.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        vehicleWheelBackL = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelBackL.mirror = true;
        vehicleWheelBackL.setRotationPoint(2.0F, 2.0F, 2.3F);
        vehicleWheelBackL.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);

        vehicleFrontL1 = new MowzieModelRenderer(this, 35, 77);
        vehicleFrontL1.mirror = true;
        vehicleFrontL1.setRotationPoint(1.8F, -0.1F, -0.9F);
        vehicleFrontL1.addBox(-0.5F, -1.0F, -4.0F, 2, 2, 3, 0.0F);
        setRotateAngle(vehicleFrontL1, 0.0F, -0.9599310885968813F, 0.0F);
        vehicleArmBaseR1 = new MowzieModelRenderer(this, 64, 60);
        vehicleArmBaseR1.setRotationPoint(-1.0F, -0.4F, 1.0F);
        vehicleArmBaseR1.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 3, 0.0F);
        vehicleWaist2 = new MowzieModelRenderer(this, 0, 64);
        vehicleWaist2.setRotationPoint(0.0F, 7.0F, 1.5F);
        vehicleWaist2.addBox(-3.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        setRotateAngle(vehicleWaist2, -1.5707963267948966F, 0.0F, -3.211405823669566F);
        vehicleRpg1 = new MowzieModelRenderer(this, 26, 0);
        vehicleRpg1.mirror = true;
        vehicleRpg1.setRotationPoint(2.1F, -0.5F, 2.0F);
        vehicleRpg1.addBox(-2.0F, -0.7F, -3.5F, 1, 2, 5, 0.0F);
        vehicleFrontL2 = new MowzieModelRenderer(this, 48, 70);
        vehicleFrontL2.mirror = true;
        vehicleFrontL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleFrontL2.addBox(-0.5F, -2.0F, -3.7F, 2, 1, 3, 0.0F);
        setRotateAngle(vehicleFrontL2, 0.017453292519943295F, 0.0F, 0.0F);
        vehicleKneeR1 = new MowzieModelRenderer(this, 10, 71);
        vehicleKneeR1.setRotationPoint(0.0F, 2.7F, 0.0F);
        vehicleKneeR1.addBox(-2.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        vehicleLowerArmR1 = new MowzieModelRenderer(this, 64, 65);
        vehicleLowerArmR1.mirror = true;
        vehicleLowerArmR1.setRotationPoint(1.0F, 3.3F, 0.4F);
        vehicleLowerArmR1.addBox(-0.9F, -0.2F, -1.6F, 2, 4, 3, 0.0F);
        setRotateAngle(vehicleLowerArmR1, -3.141592653589793F, 0.0F, 0.0F);
        vehicleRear2 = new MowzieModelRenderer(this, 0, 27);
        vehicleRear2.setRotationPoint(0.0F, 0.0F, 2.0F);
        vehicleRear2.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 2, 0.0F);
        setRotateAngle(vehicleRear2, -0.19198621771937624F, 0.0F, 0.0F);
        vehicleKneeL2 = new MowzieModelRenderer(this, 10, 71);
        vehicleKneeL2.setRotationPoint(0.0F, 2.7F, 0.0F);
        vehicleKneeL2.addBox(0.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        vehicleLowerArmR2 = new MowzieModelRenderer(this, 74, 68);
        vehicleLowerArmR2.setRotationPoint(-1.5F, 3.5F, 0.0F);
        vehicleLowerArmR2.addBox(0.0F, -3.0F, -1.9F, 1, 3, 2, 0.0F);
        setRotateAngle(vehicleLowerArmR2, 0.03490658503988659F, -0.2617993877991494F, -0.10122909661567112F);
        vehicleTorsoBase = new MowzieModelRenderer(this, 35, 66);
        vehicleTorsoBase.setRotationPoint(0.0F, -4.0F, 0.0F);
        vehicleTorsoBase.addBox(-2.0F, -2.0F, -1.3F, 4, 3, 4, 0.0F);
        setRotateAngle(vehicleTorsoBase, -1.5707963267948966F, -0.7853981633974483F, 0.0F);
        vehicleRear1 = new MowzieModelRenderer(this, 0, 0);
        vehicleRear1.setRotationPoint(0.0F, 0.3F, 4.0F);
        vehicleRear1.addBox(-2.5F, 0.1F, 0.0F, 5, 2, 5, 0.0F);
        setRotateAngle(vehicleRear1, -0.3141592653589793F, 0.0F, 0.0F);
        vehicleGun3 = new MowzieModelRenderer(this, 23, 10);
        vehicleGun3.setRotationPoint(6.5F, -0.5F, 2.2F);
        vehicleGun3.addBox(-1.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(vehicleGun3, 0.0F, 0.017453292519943295F, 0.0F);
        vehicleUpperArmR1 = new MowzieModelRenderer(this, 74, 60);
        vehicleUpperArmR1.setRotationPoint(-1.5F, 0.0F, 1.0F);
        vehicleUpperArmR1.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1, 0.0F);
        setRotateAngle(vehicleUpperArmR1, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleLowerLegL7 = new MowzieModelRenderer(this, 14, 85);
        vehicleLowerLegL7.mirror = true;
        vehicleLowerLegL7.setRotationPoint(-0.5F, 1.0F, 1.6F);
        vehicleLowerLegL7.addBox(-0.6F, -0.5F, -1.0F, 3, 2, 1, 0.0F);
        setRotateAngle(vehicleLowerLegL7, 0.6108652381980153F, 0.05235987755982988F, 0.0F);
        vehicleWaist1 = new MowzieModelRenderer(this, 0, 64);
        vehicleWaist1.mirror = true;
        vehicleWaist1.setRotationPoint(0.0F, 7.0F, 1.5F);
        vehicleWaist1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        setRotateAngle(vehicleWaist1, -1.5707963267948966F, 0.0F, 3.211405823669566F);
        vehicleBasePlate = new MowzieModelRenderer(this, 13, 0);
        vehicleBasePlate.setRotationPoint(-2.5F, 0.1F, -4.5F);
        vehicleBasePlate.addBox(0.0F, 0.0F, 0.0F, 5, 1, 13, 0.0F);
        vehicleLPlate1 = new MowzieModelRenderer(this, 0, 17);
        vehicleLPlate1.mirror = true;
        vehicleLPlate1.setRotationPoint(0.0F, 0.4F, 0.3F);
        vehicleLPlate1.addBox(0.4F, -0.9F, -5.2F, 3, 1, 6, 0.0F);
        setRotateAngle(vehicleLPlate1, 0.061086523819801536F, -0.593411945678072F, 0.017453292519943295F);
        vehicleLowerLegL4 = new MowzieModelRenderer(this, 8, 83);
        vehicleLowerLegL4.setRotationPoint(-0.3F, 2.5F, 0.9F);
        vehicleLowerLegL4.addBox(-0.5F, -3.0F, -2.0F, 1, 3, 2, 0.0F);
        setRotateAngle(vehicleLowerLegL4, 1.239183768915974F, -0.10471975511965977F, 0.08726646259971647F);
        vehicleFrontR1 = new MowzieModelRenderer(this, 35, 77);
        vehicleFrontR1.setRotationPoint(-1.8F, -0.1F, -0.9F);
        vehicleFrontR1.addBox(-1.5F, -1.0F, -4.0F, 2, 2, 3, 0.0F);
        setRotateAngle(vehicleFrontR1, 0.0F, 0.9599310885968813F, 0.0F);
        vehicleFrontR2 = new MowzieModelRenderer(this, 48, 70);
        vehicleFrontR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleFrontR2.addBox(-1.5F, -2.0F, -3.7F, 2, 1, 3, 0.0F);
        setRotateAngle(vehicleFrontR2, -0.017453292519943295F, 0.0F, -0.017453292519943295F);
        vehicleLowerArmL2 = new MowzieModelRenderer(this, 74, 68);
        vehicleLowerArmL2.mirror = true;
        vehicleLowerArmL2.setRotationPoint(1.5F, 3.5F, 0.0F);
        vehicleLowerArmL2.addBox(-1.0F, -3.0F, -1.9F, 1, 3, 2, 0.0F);
        setRotateAngle(vehicleLowerArmL2, 0.03490658503988659F, 0.2617993877991494F, 0.10122909661567112F);
        vehicleRearPlate = new MowzieModelRenderer(this, 0, 12);
        vehicleRearPlate.setRotationPoint(0.0F, 0.6F, -1.1F);
        vehicleRearPlate.addBox(-1.5F, -1.0F, -4.0F, 3, 1, 4, 0.0F);
        vehicleUpperLegR1 = new MowzieModelRenderer(this, 0, 71);
        vehicleUpperLegR1.setRotationPoint(-1.0F, 1.0F, 0.0F);
        vehicleUpperLegR1.addBox(-1.0F, -1.0F, -1.0F, 1, 4, 2, 0.0F);
        setRotateAngle(vehicleUpperLegR1, -1.5707963267948966F, 0.0F, 0.0F);
        vehicleGun2 = new MowzieModelRenderer(this, 23, 10);
        vehicleGun2.setRotationPoint(6.5F, -2.0F, 2.2F);
        vehicleGun2.addBox(-1.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(vehicleGun2, 0.0F, 0.017453292519943295F, 0.0F);
        vehicleLowerLegR7 = new MowzieModelRenderer(this, 14, 85);
        vehicleLowerLegR7.setRotationPoint(0.5F, 1.0F, 1.6F);
        vehicleLowerLegR7.addBox(-2.4F, -0.5F, -1.0F, 3, 2, 1, 0.0F);
        setRotateAngle(vehicleLowerLegR7, 0.6108652381980153F, -0.05235987755982988F, 0.0F);
        vehicleHood2 = new MowzieModelRenderer(this, 47, 65);
        vehicleHood2.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleHood2.addBox(0.8F, -3.3F, -1.0F, 1, 4, 1, 0.0F);
        setRotateAngle(vehicleHood2, -0.005235987755982988F, 0.003490658503988659F, 0.22165681500327983F);
        vehicleShoulderL1 = new MowzieModelRenderer(this, 78, 60);
        vehicleShoulderL1.setRotationPoint(-2.3F, 0.0F, -1.6F);
        vehicleShoulderL1.addBox(0.0F, -1.4F, -1.5F, 1, 3, 3, 0.0F);
        setRotateAngle(vehicleShoulderL1, -0.019198621771937624F, 0.0F, 0.0F);
        vehicleGun1 = new MowzieModelRenderer(this, 23, 10);
        vehicleGun1.setRotationPoint(-1.5F, -2.0F, 2.2F);
        vehicleGun1.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(vehicleGun1, 0.0F, -0.017453292519943295F, 0.0F);
        vehicleLowerLegR1 = new MowzieModelRenderer(this, 0, 77);
        vehicleLowerLegR1.setRotationPoint(-0.8F, 2.0F, 1.5F);
        vehicleLowerLegR1.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2, 0.0F);
        setRotateAngle(vehicleLowerLegR1, 3.141592653589793F, 0.0F, -0.017453292519943295F);
        vehicleUpperLegL2 = new MowzieModelRenderer(this, 6, 72);
        vehicleUpperLegL2.setRotationPoint(2.0F, -0.5F, -0.5F);
        vehicleUpperLegL2.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        setRotateAngle(vehicleUpperLegL2, 0.0F, -0.05235987755982988F, 0.03490658503988659F);
        vehicleBackPlate1 = new MowzieModelRenderer(this, 51, 74);
        vehicleBackPlate1.setRotationPoint(2.3F, -1.8F, 1.8F);
        vehicleBackPlate1.addBox(0.0F, -2.0F, 0.0F, 3, 5, 1, 0.0F);
        setRotateAngle(vehicleBackPlate1, 1.5882496193148399F, -0.017453292519943295F, 1.5707963267948966F);
        vehicleHood1 = new MowzieModelRenderer(this, 47, 60);
        vehicleHood1.setRotationPoint(0.0F, -0.5F, -3.8F);
        vehicleHood1.addBox(-1.5F, -3.0F, -1.0F, 3, 4, 1, 0.0F);
        setRotateAngle(vehicleHood1, -1.3439035240356336F, 0.0F, 0.0F);
        vehicleTorsoConnector1 = new MowzieModelRenderer(this, 35, 60);
        vehicleTorsoConnector1.setRotationPoint(0.0F, 0.1F, -0.4F);
        vehicleTorsoConnector1.addBox(-1.2F, -4.0F, -0.8F, 2, 4, 2, 0.0F);
        setRotateAngle(vehicleTorsoConnector1, 0.0F, 0.7853981633974483F, 0.0F);
        vehicleArmBaseL1 = new MowzieModelRenderer(this, 64, 60);
        vehicleArmBaseL1.mirror = true;
        vehicleArmBaseL1.setRotationPoint(1.0F, -0.4F, 1.0F);
        vehicleArmBaseL1.addBox(0.0F, -1.0F, -1.0F, 2, 2, 3, 0.0F);
        vehicleShoulderL2 = new MowzieModelRenderer(this, 78, 60);
        vehicleShoulderL2.mirror = true;
        vehicleShoulderL2.setRotationPoint(2.3F, 0.0F, -1.6F);
        vehicleShoulderL2.addBox(-1.0F, -1.4F, -1.5F, 1, 3, 3, 0.0F);
        setRotateAngle(vehicleShoulderL2, -0.019198621771937624F, 0.0F, 0.0F);
        vehicleBackPlate2 = new MowzieModelRenderer(this, 51, 74);
        vehicleBackPlate2.mirror = true;
        vehicleBackPlate2.setRotationPoint(-2.3F, -1.8F, 1.8F);
        vehicleBackPlate2.addBox(-3.0F, -2.0F, 0.0F, 3, 5, 1, 0.0F);
        setRotateAngle(vehicleBackPlate2, 1.5882496193148399F, 0.017453292519943295F, -1.5707963267948966F);
        vehicleWheelR = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelR.setRotationPoint(-3.7F, 0.3F, -1.0F);
        vehicleWheelR.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        vehicleWindow1 = new MowzieModelRenderer(this, 0, 7);
        vehicleWindow1.setRotationPoint(0.0F, -1.9F, -1.2F);
        vehicleWindow1.addBox(-2.5F, -0.2F, 0.0F, 5, 2, 3, 0.0F);
        setRotateAngle(vehicleWindow1, 0.33161255787892263F, 0.0F, 0.0F);
        vehicleHood4 = new MowzieModelRenderer(this, 51, 65);
        vehicleHood4.setRotationPoint(0.0F, -0.2F, 0.0F);
        vehicleHood4.addBox(-1.5F, -0.7F, -1.4F, 3, 2, 1, 0.0F);
        setRotateAngle(vehicleHood4, 1.3526301702956054F, 0.0F, 0.0F);
        vehicleUpperLegL1 = new MowzieModelRenderer(this, 0, 71);
        vehicleUpperLegL1.setRotationPoint(1.0F, 1.0F, 0.0F);
        vehicleUpperLegL1.addBox(0.0F, -1.0F, -1.0F, 1, 4, 2, 0.0F);
        setRotateAngle(vehicleUpperLegL1, -1.5707963267948966F, 0.0F, 0.0F);
        vehicleUpperArmL1 = new MowzieModelRenderer(this, 74, 60);
        vehicleUpperArmL1.mirror = true;
        vehicleUpperArmL1.setRotationPoint(0.5F, 0.0F, 1.0F);
        vehicleUpperArmL1.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1, 0.0F);
        setRotateAngle(vehicleUpperArmL1, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleUpperLegR2 = new MowzieModelRenderer(this, 6, 72);
        vehicleUpperLegR2.setRotationPoint(-2.0F, -0.5F, -0.5F);
        vehicleUpperLegR2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        setRotateAngle(vehicleUpperLegR2, 0.0F, 0.05235987755982988F, -0.03490658503988659F);
        vehicleLowerArmL1 = new MowzieModelRenderer(this, 64, 65);
        vehicleLowerArmL1.setRotationPoint(0.0F, 3.3F, 0.4F);
        vehicleLowerArmL1.addBox(-1.1F, -0.2F, -1.6F, 2, 4, 3, 0.0F);
        setRotateAngle(vehicleLowerArmL1, -3.141592653589793F, 0.0F, 0.0F);
        vehicleLowerLegL1 = new MowzieModelRenderer(this, 0, 77);
        vehicleLowerLegL1.mirror = true;
        vehicleLowerLegL1.setRotationPoint(0.8F, 2.0F, 1.5F);
        vehicleLowerLegL1.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2, 0.0F);
        setRotateAngle(vehicleLowerLegL1, 3.141592653589793F, 0.0F, 0.017453292519943295F);
        vehicleGun4 = new MowzieModelRenderer(this, 23, 10);
        vehicleGun4.setRotationPoint(-1.5F, -0.5F, 2.2F);
        vehicleGun4.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        setRotateAngle(vehicleGun4, 0.0F, -0.017453292519943295F, 0.0F);
        vehicleLowerLegR1.addChild(vehicleLowerLegR4);
        vehicleBasePlate.addChild(vehicleRpg2);
        vehicleWaistConnector1.addChild(vehicleRPlate1);
        vehicleBase.addChild(vehicleWaistConnector1);
        vehicleLowerLegR1.addChild(vehicleLowerLegR5);
        vehicleLowerLegL1.addChild(vehicleLowerLegL5);
        vehicleWindow1.addChild(vehicleWindow2);
        vehicleTorsoBase.addChild(vehicleWheelL);
        vehicleHood1.addChild(vehicleHood3);
        vehicleLowerLegL1.addChild(vehicleWheelBackL);
        vehicleTorsoBase.addChild(vehicleFrontL1);
        vehicleTorsoBase.addChild(vehicleArmBaseR1);
        vehicleBase.addChild(vehicleWaist2);
        vehicleBasePlate.addChild(vehicleRpg1);
        vehicleFrontL1.addChild(vehicleFrontL2);
        vehicleUpperLegR1.addChild(vehicleKneeR1);
        vehicleUpperArmR1.addChild(vehicleLowerArmR1);
        vehicleWindow2.addChild(vehicleRear2);
        vehicleUpperLegL1.addChild(vehicleKneeL2);
        vehicleLowerArmR1.addChild(vehicleLowerArmR2);
        vehicleTorsoConnector1.addChild(vehicleTorsoBase);
        vehicleWindow2.addChild(vehicleRear1);
        vehicleBasePlate.addChild(vehicleGun3);
        vehicleArmBaseR1.addChild(vehicleUpperArmR1);
        vehicleKneeL2.addChild(vehicleLowerLegL7);
        vehicleBase.addChild(vehicleWaist1);
        vehicleTorsoBase.addChild(vehicleBasePlate);
        vehicleWaistConnector1.addChild(vehicleLPlate1);
        vehicleLowerLegL1.addChild(vehicleLowerLegL4);
        vehicleTorsoBase.addChild(vehicleFrontR1);
        vehicleFrontR1.addChild(vehicleFrontR2);
        vehicleLowerArmL1.addChild(vehicleLowerArmL2);
        vehicleWaistConnector1.addChild(vehicleRearPlate);
        vehicleWaistConnector1.addChild(vehicleUpperLegR1);
        vehicleBasePlate.addChild(vehicleGun2);
        vehicleKneeR1.addChild(vehicleLowerLegR7);
        vehicleHood1.addChild(vehicleHood2);
        vehicleArmBaseR1.addChild(vehicleShoulderL1);
        vehicleBasePlate.addChild(vehicleGun1);
        vehicleKneeR1.addChild(vehicleLowerLegR1);
        vehicleUpperLegL1.addChild(vehicleUpperLegL2);
        vehicleTorsoBase.addChild(vehicleBackPlate1);
        vehicleTorsoBase.addChild(vehicleHood1);
        vehicleBase.addChild(vehicleTorsoConnector1);
        vehicleTorsoBase.addChild(vehicleArmBaseL1);
        vehicleArmBaseL1.addChild(vehicleShoulderL2);
        vehicleTorsoBase.addChild(vehicleBackPlate2);
        vehicleTorsoBase.addChild(vehicleWheelR);
        vehicleTorsoBase.addChild(vehicleWindow1);
        vehicleHood1.addChild(vehicleHood4);
        vehicleWaistConnector1.addChild(vehicleUpperLegL1);
        vehicleArmBaseL1.addChild(vehicleUpperArmL1);
        vehicleUpperLegR1.addChild(vehicleUpperLegR2);
        vehicleUpperArmL1.addChild(vehicleLowerArmL1);
        vehicleLowerLegR1.addChild(vehicleWheelBackR);
        vehicleKneeL2.addChild(vehicleLowerLegL1);
        vehicleBasePlate.addChild(vehicleGun4);
        
        float scale = 1.25F;
        vehicleWheelR.setScale(scale, scale, scale);
        vehicleWheelL.setScale(scale, scale, scale);
        vehicleWheelBackR.setScale(scale, scale, scale);
        vehicleWheelBackL.setScale(scale, scale, scale);
    }
    
    public Transformer getTransformer()
	{
		return TransformerManager.transformerVurp;
	}
	
	public ModelRenderer getWaist()
	{
		return vehicleBase;
	}
	
	public ModelRenderer getVehicle()
	{
		return vehicleBase;
	}
	
	public ModelRenderer getRightLeg()
	{
		return vehicleBase;
	}
	
	public ModelRenderer getLeftLeg()
	{
		return vehicleBase;
	}
	
	public ModelRenderer getHead()
	{
		return vehicleBase;
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
            float f1 = (float) t / 5;

            vehicleRPlate1.rotateAngleY = f * 0.593411945678072F;
            vehicleLPlate1.rotateAngleY = f * -0.593411945678072F;
            vehicleRpg1.rotationPointZ = 2F + (float) t / 2;
            vehicleRpg2.rotationPointZ = 2F + (float) t / 2;
            vehicleLowerLegR4.rotateAngleX = 1.239183768915974F * f;
            vehicleLowerLegL4.rotateAngleX = 1.239183768915974F * f;
            vehicleFrontR1.rotateAngleY = 0.9599310885968813F * f;
            vehicleFrontL1.rotateAngleY = -0.9599310885968813F * f;
            vehicleFrontR2.rotateAngleX = 0.24434609527920614F * f1;
            vehicleFrontL2.rotateAngleX = 0.24434609527920614F * f1;
            vehicleGun1.setRotationPoint(-1.5F + f1, -2 + 1 * f1, 2.2F + (float) t / 2);
            vehicleGun2.setRotationPoint(6.5F - f1, -2 + 1 * f1, 2.2F + (float) t / 2);
            vehicleGun3.setRotationPoint(6.5F - f1, -0.5F, 2.2F + (float) t / 2);
            vehicleGun4.setRotationPoint(-1.5F + f1, -0.5F, 2.2F + (float) t / 2);

            vehicleWheelR.setRotationPoint(-3.7F + f1, 0.3F, -1.0F - f1);
            vehicleWheelL.setRotationPoint(3.7F - f1, 0.3F, -1.0F - f1);

            vehicleWheelBackR.setRotationPoint(-2.0F + f1, 2.0F, 2.3F - f1);
            vehicleWheelBackL.setRotationPoint(2.0F - f1, 2.0F, 2.3F - f1);

            VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

            if (transformedPlayer != null)
            {
                for (ModelRenderer modelRenderer : new ModelRenderer[] { vehicleWheelR, vehicleWheelL, vehicleWheelBackR, vehicleWheelBackL })
                {

                    float wheelSpinSpeed = (transformedPlayer.getForwardVelocity() < 0 ? -par1 : par1) * 0.8F;
                    modelRenderer.rotateAngleX = wheelSpinSpeed;
                }

                float vel = (float) transformedPlayer.getHorizontalVelocity();
                float rot = (vel > 0 ? vel <= 8 ? vel : 8 : vel >= -8 ? vel : -8) / 10;
                vehicleWheelBackR.rotateAngleZ = rot;
                vehicleWheelBackL.rotateAngleZ = rot;
                vehicleWheelR.rotateAngleY = rot;
                vehicleWheelL.rotateAngleY = rot;
            }

            float d = bipedHead.rotateAngleY - (bipedBody.rotateAngleY - bipedHead.rotateAngleY) / 3;
            if (vehicleBase.rotateAngleY < d)
            {
                vehicleBase.rotateAngleY += 0.05F;
            }
            if (vehicleBase.rotateAngleY > d)
            {
                vehicleBase.rotateAngleY -= 0.05F;
            }
            vehicleBase.rotateAngleY = d;

            vehicleBase.rotateAngleX = 1.65F;

            if (player == Minecraft.getMinecraft().thePlayer)
            {
                vehicleBase.rotateAngleX += -(float) player.motionY - 0.0784000015258789F;
            }
            else
            {
                vehicleBase.rotateAngleX += -(float) (player.posY - player.prevPosY) * 1.5F;
            }

            vehicleBase.rotateAngleX -= 0.1F;
        }
    }
}