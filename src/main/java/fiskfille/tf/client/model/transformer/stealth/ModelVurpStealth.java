package fiskfille.tf.client.model.transformer.stealth;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.playerdata.TFDataManager;

public class ModelVurpStealth extends MowzieModelBase
{
    public ModelRenderer vehicleBase;
    public ModelRenderer vehicleWaist1;
    public ModelRenderer vehicleWaist2;
    public ModelRenderer vehicleWaistConnector1;
    public ModelRenderer vehicleTorsoConnector1;
    public ModelRenderer vehicleUpperLegR1;
    public ModelRenderer vehicleUpperLegL1;
    public ModelRenderer vehicleRPlate1;
    public ModelRenderer vehicleLPlate1;
    public ModelRenderer vehicleRearPlate;
    public ModelRenderer vehicleUpperLegR2;
    public ModelRenderer vehicleKneeR1;
    public ModelRenderer vehicleLowerLegR1;
    public ModelRenderer vehicleLowerLegR7;
    public ModelRenderer vehicleLowerLegR4;
    public ModelRenderer vehicleLowerLegR5;
    public ModelRenderer vehicleWheelBackR;
    public ModelRenderer vehicleUpperLegL2;
    public ModelRenderer vehicleKneeL2;
    public ModelRenderer vehicleLowerLegL1;
    public ModelRenderer vehicleLowerLegL7;
    public ModelRenderer vehicleLowerLegL4;
    public ModelRenderer vehicleLowerLegL5;
    public ModelRenderer vehicleWheelBackL;
    public ModelRenderer vehicleTorsoBase;
    public ModelRenderer vehicleHood1;
    public ModelRenderer vehicleFrontR1;
    public ModelRenderer vehicleFrontL1;
    public ModelRenderer vehicleBackPlate1;
    public ModelRenderer vehicleBackPlate2;
    public ModelRenderer vehicleWheelR;
    public ModelRenderer vehicleWheelL;
    public ModelRenderer vehicleArmBaseR1;
    public ModelRenderer vehicleArmBaseL1;
    public ModelRenderer vehicleWindow1;
    public ModelRenderer vehicleBasePlate;
    public ModelRenderer vehicleHood2;
    public ModelRenderer vehicleHood3;
    public ModelRenderer vehicleHood4;
    public ModelRenderer vehicleFrontR2;
    public ModelRenderer vehicleFrontL2;
    public ModelRenderer vehicleUpperArmR1;
    public ModelRenderer vehicleShoulderL1;
    public ModelRenderer vehicleLowerArmR1;
    public ModelRenderer vehicleLowerArmR2;
    public ModelRenderer vehicleUpperArmL1;
    public ModelRenderer vehicleShoulderL2;
    public ModelRenderer vehicleLowerArmL1;
    public ModelRenderer vehicleLowerArmL2;
    public ModelRenderer vehicleWindow2;
    public ModelRenderer vehicleRear1;
    public ModelRenderer vehicleRear2;
    public ModelRenderer vehicleRpg1;
    public ModelRenderer vehicleRpg2;
    public ModelRenderer vehicleGun1;
    public ModelRenderer vehicleGun2;
    public ModelRenderer vehicleGun3;
    public ModelRenderer vehicleGun4;

    public ModelVurpStealth()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.vehicleLowerLegR4 = new ModelRenderer(this, 8, 83);
        this.vehicleLowerLegR4.setRotationPoint(0.3F, 2.5F, 0.9F);
        this.vehicleLowerLegR4.addBox(-0.5F, -3.0F, -2.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(vehicleLowerLegR4, 1.239183768915974F, 0.10471975511965977F, -0.08726646259971647F);
        this.vehicleBase = new ModelRenderer(this, 0, 60);
        this.vehicleBase.setRotationPoint(0.0F, 22.3F, 1.0F);
        this.vehicleBase.addBox(-2.0F, 0.0F, -1.0F, 4, 1, 3, 0.0F);
        this.setRotateAngle(vehicleBase, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleRpg2 = new ModelRenderer(this, 26, 0);
        this.vehicleRpg2.mirror = true;
        this.vehicleRpg2.setRotationPoint(3.9F, -0.5F, 2.0F);
        this.vehicleRpg2.addBox(0.0F, -0.7F, -3.5F, 1, 2, 5, 0.0F);
        this.vehicleRPlate1 = new ModelRenderer(this, 0, 17);
        this.vehicleRPlate1.setRotationPoint(0.0F, 0.4F, 0.3F);
        this.vehicleRPlate1.addBox(-3.4F, -0.9F, -5.2F, 3, 1, 6, 0.0F);
        this.setRotateAngle(vehicleRPlate1, 0.061086523819801536F, 0.593411945678072F, -0.017453292519943295F);
        this.vehicleWaistConnector1 = new ModelRenderer(this, 4, 64);
        this.vehicleWaistConnector1.setRotationPoint(0.0F, 2.0F, -1.0F);
        this.vehicleWaistConnector1.addBox(-1.0F, -0.1F, -2.3F, 2, 3, 4, 0.0F);
        this.setRotateAngle(vehicleWaistConnector1, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleLowerLegR5 = new ModelRenderer(this, 16, 75);
        this.vehicleLowerLegR5.setRotationPoint(-1.4F, 0.0F, 0.0F);
        this.vehicleLowerLegR5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.setRotateAngle(vehicleLowerLegR5, -0.15707963267948966F, -0.06981317007977318F, -0.05235987755982988F);
        this.vehicleLowerLegL5 = new ModelRenderer(this, 16, 75);
        this.vehicleLowerLegL5.mirror = true;
        this.vehicleLowerLegL5.setRotationPoint(1.4F, 0.0F, 0.0F);
        this.vehicleLowerLegL5.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.setRotateAngle(vehicleLowerLegL5, -0.15707963267948966F, 0.06981317007977318F, 0.05235987755982988F);
        this.vehicleWindow2 = new ModelRenderer(this, 0, 24);
        this.vehicleWindow2.setRotationPoint(0.0F, -0.2F, 3.0F);
        this.vehicleWindow2.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 2, 0.0F);
        this.setRotateAngle(vehicleWindow2, -0.20943951023931953F, 0.0F, 0.0F);
        this.vehicleWheelL = new ModelRenderer(this, 55, 68);
        this.vehicleWheelL.mirror = true;
        this.vehicleWheelL.setRotationPoint(3.6F, 0.3F, -1.0F);
        this.vehicleWheelL.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.vehicleHood3 = new ModelRenderer(this, 47, 65);
        this.vehicleHood3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleHood3.addBox(-1.8F, -3.3F, -1.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(vehicleHood3, -0.005235987755982988F, 0.003490658503988659F, -0.22165681500327983F);
        
        this.vehicleWheelBackR = new ModelRenderer(this, 55, 68);
        this.vehicleWheelBackR.setRotationPoint(-2.0F, 2.0F, 2.3F);
        this.vehicleWheelBackR.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.vehicleWheelBackL = new ModelRenderer(this, 55, 68);
        this.vehicleWheelBackL.mirror = true;
        this.vehicleWheelBackL.setRotationPoint(2.0F, 2.0F, 2.3F);
        this.vehicleWheelBackL.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        
        this.vehicleFrontL1 = new ModelRenderer(this, 35, 77);
        this.vehicleFrontL1.mirror = true;
        this.vehicleFrontL1.setRotationPoint(1.8F, -0.1F, -0.9F);
        this.vehicleFrontL1.addBox(-0.5F, -1.0F, -4.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(vehicleFrontL1, 0.0F, -0.9599310885968813F, 0.0F);
        this.vehicleArmBaseR1 = new ModelRenderer(this, 64, 60);
        this.vehicleArmBaseR1.setRotationPoint(-1.0F, -0.4F, 1.0F);
        this.vehicleArmBaseR1.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 3, 0.0F);
        this.vehicleWaist2 = new ModelRenderer(this, 0, 64);
        this.vehicleWaist2.setRotationPoint(0.0F, 7.0F, 1.5F);
        this.vehicleWaist2.addBox(-3.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(vehicleWaist2, -1.5707963267948966F, 0.0F, -3.211405823669566F);
        this.vehicleRpg1 = new ModelRenderer(this, 26, 0);
        this.vehicleRpg1.mirror = true;
        this.vehicleRpg1.setRotationPoint(2.1F, -0.5F, 2.0F);
        this.vehicleRpg1.addBox(-2.0F, -0.7F, -3.5F, 1, 2, 5, 0.0F);
        this.vehicleFrontL2 = new ModelRenderer(this, 48, 70);
        this.vehicleFrontL2.mirror = true;
        this.vehicleFrontL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleFrontL2.addBox(-0.5F, -2.0F, -3.7F, 2, 1, 3, 0.0F);
        this.setRotateAngle(vehicleFrontL2, 0.017453292519943295F, 0.0F, 0.0F);
        this.vehicleKneeR1 = new ModelRenderer(this, 10, 71);
        this.vehicleKneeR1.setRotationPoint(0.0F, 2.7F, 0.0F);
        this.vehicleKneeR1.addBox(-2.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.vehicleLowerArmR1 = new ModelRenderer(this, 64, 65);
        this.vehicleLowerArmR1.mirror = true;
        this.vehicleLowerArmR1.setRotationPoint(1.0F, 3.3F, 0.4F);
        this.vehicleLowerArmR1.addBox(-0.9F, -0.2F, -1.6F, 2, 4, 3, 0.0F);
        this.setRotateAngle(vehicleLowerArmR1, -3.141592653589793F, 0.0F, 0.0F);
        this.vehicleRear2 = new ModelRenderer(this, 0, 27);
        this.vehicleRear2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.vehicleRear2.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 2, 0.0F);
        this.setRotateAngle(vehicleRear2, -0.19198621771937624F, 0.0F, 0.0F);
        this.vehicleKneeL2 = new ModelRenderer(this, 10, 71);
        this.vehicleKneeL2.setRotationPoint(0.0F, 2.7F, 0.0F);
        this.vehicleKneeL2.addBox(0.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.vehicleLowerArmR2 = new ModelRenderer(this, 74, 68);
        this.vehicleLowerArmR2.setRotationPoint(-1.5F, 3.5F, 0.0F);
        this.vehicleLowerArmR2.addBox(0.0F, -3.0F, -1.9F, 1, 3, 2, 0.0F);
        this.setRotateAngle(vehicleLowerArmR2, 0.03490658503988659F, -0.2617993877991494F, -0.10122909661567112F);
        this.vehicleTorsoBase = new ModelRenderer(this, 35, 66);
        this.vehicleTorsoBase.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.vehicleTorsoBase.addBox(-2.0F, -2.0F, -1.3F, 4, 3, 4, 0.0F);
        this.setRotateAngle(vehicleTorsoBase, -1.5707963267948966F, -0.7853981633974483F, 0.0F);
        this.vehicleRear1 = new ModelRenderer(this, 0, 0);
        this.vehicleRear1.setRotationPoint(0.0F, 0.3F, 4.0F);
        this.vehicleRear1.addBox(-2.5F, 0.1F, 0.0F, 5, 2, 5, 0.0F);
        this.setRotateAngle(vehicleRear1, -0.3141592653589793F, 0.0F, 0.0F);
        this.vehicleGun3 = new ModelRenderer(this, 23, 10);
        this.vehicleGun3.setRotationPoint(6.5F, -0.5F, 2.2F);
        this.vehicleGun3.addBox(-1.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(vehicleGun3, 0.0F, 0.017453292519943295F, 0.0F);
        this.vehicleUpperArmR1 = new ModelRenderer(this, 74, 60);
        this.vehicleUpperArmR1.setRotationPoint(-1.5F, 0.0F, 1.0F);
        this.vehicleUpperArmR1.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(vehicleUpperArmR1, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleLowerLegL7 = new ModelRenderer(this, 14, 85);
        this.vehicleLowerLegL7.mirror = true;
        this.vehicleLowerLegL7.setRotationPoint(-0.5F, 1.0F, 1.6F);
        this.vehicleLowerLegL7.addBox(-0.6F, -0.5F, -1.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(vehicleLowerLegL7, 0.6108652381980153F, 0.05235987755982988F, 0.0F);
        this.vehicleWaist1 = new ModelRenderer(this, 0, 64);
        this.vehicleWaist1.mirror = true;
        this.vehicleWaist1.setRotationPoint(0.0F, 7.0F, 1.5F);
        this.vehicleWaist1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(vehicleWaist1, -1.5707963267948966F, 0.0F, 3.211405823669566F);
        this.vehicleBasePlate = new ModelRenderer(this, 13, 0);
        this.vehicleBasePlate.setRotationPoint(-2.5F, 0.1F, -4.5F);
        this.vehicleBasePlate.addBox(0.0F, 0.0F, 0.0F, 5, 1, 13, 0.0F);
        this.vehicleLPlate1 = new ModelRenderer(this, 0, 17);
        this.vehicleLPlate1.mirror = true;
        this.vehicleLPlate1.setRotationPoint(0.0F, 0.4F, 0.3F);
        this.vehicleLPlate1.addBox(0.4F, -0.9F, -5.2F, 3, 1, 6, 0.0F);
        this.setRotateAngle(vehicleLPlate1, 0.061086523819801536F, -0.593411945678072F, 0.017453292519943295F);
        this.vehicleLowerLegL4 = new ModelRenderer(this, 8, 83);
        this.vehicleLowerLegL4.setRotationPoint(-0.3F, 2.5F, 0.9F);
        this.vehicleLowerLegL4.addBox(-0.5F, -3.0F, -2.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(vehicleLowerLegL4, 1.239183768915974F, -0.10471975511965977F, 0.08726646259971647F);
        this.vehicleFrontR1 = new ModelRenderer(this, 35, 77);
        this.vehicleFrontR1.setRotationPoint(-1.8F, -0.1F, -0.9F);
        this.vehicleFrontR1.addBox(-1.5F, -1.0F, -4.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(vehicleFrontR1, 0.0F, 0.9599310885968813F, 0.0F);
        this.vehicleFrontR2 = new ModelRenderer(this, 48, 70);
        this.vehicleFrontR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleFrontR2.addBox(-1.5F, -2.0F, -3.7F, 2, 1, 3, 0.0F);
        this.setRotateAngle(vehicleFrontR2, -0.017453292519943295F, 0.0F, -0.017453292519943295F);
        this.vehicleLowerArmL2 = new ModelRenderer(this, 74, 68);
        this.vehicleLowerArmL2.mirror = true;
        this.vehicleLowerArmL2.setRotationPoint(1.5F, 3.5F, 0.0F);
        this.vehicleLowerArmL2.addBox(-1.0F, -3.0F, -1.9F, 1, 3, 2, 0.0F);
        this.setRotateAngle(vehicleLowerArmL2, 0.03490658503988659F, 0.2617993877991494F, 0.10122909661567112F);
        this.vehicleRearPlate = new ModelRenderer(this, 0, 12);
        this.vehicleRearPlate.setRotationPoint(0.0F, 0.6F, -1.1F);
        this.vehicleRearPlate.addBox(-1.5F, -1.0F, -4.0F, 3, 1, 4, 0.0F);
        this.vehicleUpperLegR1 = new ModelRenderer(this, 0, 71);
        this.vehicleUpperLegR1.setRotationPoint(-1.0F, 1.0F, 0.0F);
        this.vehicleUpperLegR1.addBox(-1.0F, -1.0F, -1.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(vehicleUpperLegR1, -1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleGun2 = new ModelRenderer(this, 23, 10);
        this.vehicleGun2.setRotationPoint(6.5F, -2.0F, 2.2F);
        this.vehicleGun2.addBox(-1.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(vehicleGun2, 0.0F, 0.017453292519943295F, 0.0F);
        this.vehicleLowerLegR7 = new ModelRenderer(this, 14, 85);
        this.vehicleLowerLegR7.setRotationPoint(0.5F, 1.0F, 1.6F);
        this.vehicleLowerLegR7.addBox(-2.4F, -0.5F, -1.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(vehicleLowerLegR7, 0.6108652381980153F, -0.05235987755982988F, 0.0F);
        this.vehicleHood2 = new ModelRenderer(this, 47, 65);
        this.vehicleHood2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleHood2.addBox(0.8F, -3.3F, -1.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(vehicleHood2, -0.005235987755982988F, 0.003490658503988659F, 0.22165681500327983F);
        this.vehicleShoulderL1 = new ModelRenderer(this, 78, 60);
        this.vehicleShoulderL1.setRotationPoint(-2.3F, 0.0F, -1.6F);
        this.vehicleShoulderL1.addBox(0.0F, -1.4F, -1.5F, 1, 3, 3, 0.0F);
        this.setRotateAngle(vehicleShoulderL1, -0.019198621771937624F, 0.0F, 0.0F);
        this.vehicleGun1 = new ModelRenderer(this, 23, 10);
        this.vehicleGun1.setRotationPoint(-1.5F, -2.0F, 2.2F);
        this.vehicleGun1.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(vehicleGun1, 0.0F, -0.017453292519943295F, 0.0F);
        this.vehicleLowerLegR1 = new ModelRenderer(this, 0, 77);
        this.vehicleLowerLegR1.setRotationPoint(-0.8F, 2.0F, 1.5F);
        this.vehicleLowerLegR1.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(vehicleLowerLegR1, 3.141592653589793F, 0.0F, -0.017453292519943295F);
        this.vehicleUpperLegL2 = new ModelRenderer(this, 6, 72);
        this.vehicleUpperLegL2.setRotationPoint(2.0F, -0.5F, -0.5F);
        this.vehicleUpperLegL2.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(vehicleUpperLegL2, 0.0F, -0.05235987755982988F, 0.03490658503988659F);
        this.vehicleBackPlate1 = new ModelRenderer(this, 51, 74);
        this.vehicleBackPlate1.setRotationPoint(2.3F, -1.8F, 1.8F);
        this.vehicleBackPlate1.addBox(0.0F, -2.0F, 0.0F, 3, 5, 1, 0.0F);
        this.setRotateAngle(vehicleBackPlate1, 1.5882496193148399F, -0.017453292519943295F, 1.5707963267948966F);
        this.vehicleHood1 = new ModelRenderer(this, 47, 60);
        this.vehicleHood1.setRotationPoint(0.0F, -0.5F, -3.8F);
        this.vehicleHood1.addBox(-1.5F, -3.0F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotateAngle(vehicleHood1, -1.3439035240356336F, 0.0F, 0.0F);
        this.vehicleTorsoConnector1 = new ModelRenderer(this, 35, 60);
        this.vehicleTorsoConnector1.setRotationPoint(0.0F, 0.1F, -0.4F);
        this.vehicleTorsoConnector1.addBox(-1.2F, -4.0F, -0.8F, 2, 4, 2, 0.0F);
        this.setRotateAngle(vehicleTorsoConnector1, 0.0F, 0.7853981633974483F, 0.0F);
        this.vehicleArmBaseL1 = new ModelRenderer(this, 64, 60);
        this.vehicleArmBaseL1.mirror = true;
        this.vehicleArmBaseL1.setRotationPoint(1.0F, -0.4F, 1.0F);
        this.vehicleArmBaseL1.addBox(0.0F, -1.0F, -1.0F, 2, 2, 3, 0.0F);
        this.vehicleShoulderL2 = new ModelRenderer(this, 78, 60);
        this.vehicleShoulderL2.mirror = true;
        this.vehicleShoulderL2.setRotationPoint(2.3F, 0.0F, -1.6F);
        this.vehicleShoulderL2.addBox(-1.0F, -1.4F, -1.5F, 1, 3, 3, 0.0F);
        this.setRotateAngle(vehicleShoulderL2, -0.019198621771937624F, 0.0F, 0.0F);
        this.vehicleBackPlate2 = new ModelRenderer(this, 51, 74);
        this.vehicleBackPlate2.mirror = true;
        this.vehicleBackPlate2.setRotationPoint(-2.3F, -1.8F, 1.8F);
        this.vehicleBackPlate2.addBox(-3.0F, -2.0F, 0.0F, 3, 5, 1, 0.0F);
        this.setRotateAngle(vehicleBackPlate2, 1.5882496193148399F, 0.017453292519943295F, -1.5707963267948966F);
        this.vehicleWheelR = new ModelRenderer(this, 55, 68);
        this.vehicleWheelR.setRotationPoint(-3.7F, 0.3F, -1.0F);
        this.vehicleWheelR.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.vehicleWindow1 = new ModelRenderer(this, 0, 7);
        this.vehicleWindow1.setRotationPoint(0.0F, -1.9F, -1.2F);
        this.vehicleWindow1.addBox(-2.5F, -0.2F, 0.0F, 5, 2, 3, 0.0F);
        this.setRotateAngle(vehicleWindow1, 0.33161255787892263F, 0.0F, 0.0F);
        this.vehicleHood4 = new ModelRenderer(this, 51, 65);
        this.vehicleHood4.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.vehicleHood4.addBox(-1.5F, -0.7F, -1.4F, 3, 2, 1, 0.0F);
        this.setRotateAngle(vehicleHood4, 1.3526301702956054F, 0.0F, 0.0F);
        this.vehicleUpperLegL1 = new ModelRenderer(this, 0, 71);
        this.vehicleUpperLegL1.setRotationPoint(1.0F, 1.0F, 0.0F);
        this.vehicleUpperLegL1.addBox(0.0F, -1.0F, -1.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(vehicleUpperLegL1, -1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleUpperArmL1 = new ModelRenderer(this, 74, 60);
        this.vehicleUpperArmL1.mirror = true;
        this.vehicleUpperArmL1.setRotationPoint(0.5F, 0.0F, 1.0F);
        this.vehicleUpperArmL1.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(vehicleUpperArmL1, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleUpperLegR2 = new ModelRenderer(this, 6, 72);
        this.vehicleUpperLegR2.setRotationPoint(-2.0F, -0.5F, -0.5F);
        this.vehicleUpperLegR2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(vehicleUpperLegR2, 0.0F, 0.05235987755982988F, -0.03490658503988659F);
        this.vehicleLowerArmL1 = new ModelRenderer(this, 64, 65);
        this.vehicleLowerArmL1.setRotationPoint(0.0F, 3.3F, 0.4F);
        this.vehicleLowerArmL1.addBox(-1.1F, -0.2F, -1.6F, 2, 4, 3, 0.0F);
        this.setRotateAngle(vehicleLowerArmL1, -3.141592653589793F, 0.0F, 0.0F);
        this.vehicleLowerLegL1 = new ModelRenderer(this, 0, 77);
        this.vehicleLowerLegL1.mirror = true;
        this.vehicleLowerLegL1.setRotationPoint(0.8F, 2.0F, 1.5F);
        this.vehicleLowerLegL1.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(vehicleLowerLegL1, 3.141592653589793F, 0.0F, 0.017453292519943295F);
        this.vehicleGun4 = new ModelRenderer(this, 23, 10);
        this.vehicleGun4.setRotationPoint(-1.5F, -0.5F, 2.2F);
        this.vehicleGun4.addBox(0.0F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(vehicleGun4, 0.0F, -0.017453292519943295F, 0.0F);
        this.vehicleLowerLegR1.addChild(this.vehicleLowerLegR4);
        this.vehicleBasePlate.addChild(this.vehicleRpg2);
        this.vehicleWaistConnector1.addChild(this.vehicleRPlate1);
        this.vehicleBase.addChild(this.vehicleWaistConnector1);
        this.vehicleLowerLegR1.addChild(this.vehicleLowerLegR5);
        this.vehicleLowerLegL1.addChild(this.vehicleLowerLegL5);
        this.vehicleWindow1.addChild(this.vehicleWindow2);
        this.vehicleTorsoBase.addChild(this.vehicleWheelL);
        this.vehicleHood1.addChild(this.vehicleHood3);
        this.vehicleLowerLegL1.addChild(this.vehicleWheelBackL);
        this.vehicleTorsoBase.addChild(this.vehicleFrontL1);
        this.vehicleTorsoBase.addChild(this.vehicleArmBaseR1);
        this.vehicleBase.addChild(this.vehicleWaist2);
        this.vehicleBasePlate.addChild(this.vehicleRpg1);
        this.vehicleFrontL1.addChild(this.vehicleFrontL2);
        this.vehicleUpperLegR1.addChild(this.vehicleKneeR1);
        this.vehicleUpperArmR1.addChild(this.vehicleLowerArmR1);
        this.vehicleWindow2.addChild(this.vehicleRear2);
        this.vehicleUpperLegL1.addChild(this.vehicleKneeL2);
        this.vehicleLowerArmR1.addChild(this.vehicleLowerArmR2);
        this.vehicleTorsoConnector1.addChild(this.vehicleTorsoBase);
        this.vehicleWindow2.addChild(this.vehicleRear1);
        this.vehicleBasePlate.addChild(this.vehicleGun3);
        this.vehicleArmBaseR1.addChild(this.vehicleUpperArmR1);
        this.vehicleKneeL2.addChild(this.vehicleLowerLegL7);
        this.vehicleBase.addChild(this.vehicleWaist1);
        this.vehicleTorsoBase.addChild(this.vehicleBasePlate);
        this.vehicleWaistConnector1.addChild(this.vehicleLPlate1);
        this.vehicleLowerLegL1.addChild(this.vehicleLowerLegL4);
        this.vehicleTorsoBase.addChild(this.vehicleFrontR1);
        this.vehicleFrontR1.addChild(this.vehicleFrontR2);
        this.vehicleLowerArmL1.addChild(this.vehicleLowerArmL2);
        this.vehicleWaistConnector1.addChild(this.vehicleRearPlate);
        this.vehicleWaistConnector1.addChild(this.vehicleUpperLegR1);
        this.vehicleBasePlate.addChild(this.vehicleGun2);
        this.vehicleKneeR1.addChild(this.vehicleLowerLegR7);
        this.vehicleHood1.addChild(this.vehicleHood2);
        this.vehicleArmBaseR1.addChild(this.vehicleShoulderL1);
        this.vehicleBasePlate.addChild(this.vehicleGun1);
        this.vehicleKneeR1.addChild(this.vehicleLowerLegR1);
        this.vehicleUpperLegL1.addChild(this.vehicleUpperLegL2);
        this.vehicleTorsoBase.addChild(this.vehicleBackPlate1);
        this.vehicleTorsoBase.addChild(this.vehicleHood1);
        this.vehicleBase.addChild(this.vehicleTorsoConnector1);
        this.vehicleTorsoBase.addChild(this.vehicleArmBaseL1);
        this.vehicleArmBaseL1.addChild(this.vehicleShoulderL2);
        this.vehicleTorsoBase.addChild(this.vehicleBackPlate2);
        this.vehicleTorsoBase.addChild(this.vehicleWheelR);
        this.vehicleTorsoBase.addChild(this.vehicleWindow1);
        this.vehicleHood1.addChild(this.vehicleHood4);
        this.vehicleWaistConnector1.addChild(this.vehicleUpperLegL1);
        this.vehicleArmBaseL1.addChild(this.vehicleUpperArmL1);
        this.vehicleUpperLegR1.addChild(this.vehicleUpperLegR2);
        this.vehicleUpperArmL1.addChild(this.vehicleLowerArmL1);
        this.vehicleLowerLegR1.addChild(this.vehicleWheelBackR);
        this.vehicleKneeL2.addChild(this.vehicleLowerLegL1);
        this.vehicleBasePlate.addChild(this.vehicleGun4);
    }
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    { 
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.vehicleBase.render(0.0625F);
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
			float f = (float)(5 - t) / 5;
			float f1 = (float)t / 5;
			
			vehicleRPlate1.rotateAngleY = f * 0.593411945678072F;
			vehicleLPlate1.rotateAngleY = f * -0.593411945678072F;
			vehicleRpg1.rotationPointZ = 2F + (float)t / 2;
			vehicleRpg2.rotationPointZ = 2F + (float)t / 2;
			vehicleLowerLegR4.rotateAngleX = 1.239183768915974F * f;
			vehicleLowerLegL4.rotateAngleX = 1.239183768915974F * f;
			vehicleFrontR1.rotateAngleY = 0.9599310885968813F * f;
			vehicleFrontL1.rotateAngleY = -0.9599310885968813F * f;
			vehicleFrontR2.rotateAngleX = 0.24434609527920614F * f1;
			vehicleFrontL2.rotateAngleX = 0.24434609527920614F * f1;
			vehicleGun1.setRotationPoint(-1.5F + f1, -2 + 1 * f1, 2.2F + (float)t / 2);
			vehicleGun2.setRotationPoint(6.5F - f1, -2 + 1 * f1, 2.2F + (float)t / 2);
			vehicleGun3.setRotationPoint(6.5F - f1, -0.5F, 2.2F + (float)t / 2);
			vehicleGun4.setRotationPoint(-1.5F + f1, -0.5F, 2.2F + (float)t / 2);
			
			
			vehicleWheelR.setRotationPoint(-3.7F + f1, 0.3F, -1.0F - f1);
			vehicleWheelL.setRotationPoint(3.7F - f1, 0.3F, -1.0F - f1);
			
			vehicleWheelBackR.setRotationPoint(-2.0F + f1, 2.0F, 2.3F - f1);
			vehicleWheelBackL.setRotationPoint(2.0F - f1, 2.0F, 2.3F - f1);
			
			
			
			
			
			
			for (ModelRenderer modelRenderer : new ModelRenderer[] {vehicleWheelR, vehicleWheelL, vehicleWheelBackR, vehicleWheelBackL})
			{
				VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
				
				if (transformedPlayer != null)
				{
					float wheelSpinSpeed = (transformedPlayer.getForwardVelocity() < 0 ? -par1 : par1) * 0.8F;
					modelRenderer.rotateAngleX = wheelSpinSpeed;
					
					float vel = (float)transformedPlayer.getHorizontalVelocity();
					float rot = (vel > 0 ? (vel <= 8 ? vel : 8) : (vel >= -8 ? vel : -8)) / 10;
					vehicleWheelBackR.rotateAngleZ = rot;
					vehicleWheelBackL.rotateAngleZ = rot;
				}
			}
			
			for (ModelRenderer modelRenderer : new ModelRenderer[] {vehicleBase})
			{
				float d = this.bipedHead.rotateAngleY - (this.bipedBody.rotateAngleY - this.bipedHead.rotateAngleY) / 3;
				if (modelRenderer.rotateAngleY < d) {modelRenderer.rotateAngleY += 0.05F;}
				if (modelRenderer.rotateAngleY > d) {modelRenderer.rotateAngleY -= 0.05F;}
				modelRenderer.rotateAngleY = d;
				
				modelRenderer.rotateAngleX = 1.65F;

				if (player == Minecraft.getMinecraft().thePlayer)
				{
					modelRenderer.rotateAngleX += -(float) player.motionY - 0.0784000015258789F;
				}
				else
				{
					modelRenderer.rotateAngleX += -(float) (player.posY - player.prevPosY) * 1.5F;
				}

				modelRenderer.rotateAngleX -= 0.1F;
			}
		}
	}
}