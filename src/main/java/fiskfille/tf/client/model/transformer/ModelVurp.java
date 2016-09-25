package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.item.ItemVurpsSniper;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class ModelVurp extends ModelTransformerBase
{
    public MowzieModelRenderer vehicleBase;
    public MowzieModelRenderer vehicleWaist1;
    public MowzieModelRenderer vehicleWaist2;
    public MowzieModelRenderer vehicleWaistConnector1;
    public MowzieModelRenderer vehicleTorsoconnector1;
    public MowzieModelRenderer vehicleUpperlegR1;
    public MowzieModelRenderer vehicleUpperlegL1;
    public MowzieModelRenderer vehicleRplate1;
    public MowzieModelRenderer vehicleLplate1;
    public MowzieModelRenderer vehicleRearplate;
    public MowzieModelRenderer vehicleUpperlegR2;
    public MowzieModelRenderer vehicleKneeR1;
    public MowzieModelRenderer vehicleLowerlegR1;
    public MowzieModelRenderer vehiclelowerlegR7;
    public MowzieModelRenderer vehicleLowerlegR4;
    public MowzieModelRenderer vehicleLowerlegR5;
    public MowzieModelRenderer vehicleWheelBackR;
    public MowzieModelRenderer vehicleupperlegL2;
    public MowzieModelRenderer vehiclekneeL2;
    public MowzieModelRenderer vehiclelowerlegL1;
    public MowzieModelRenderer vehiclelowerlegL7;
    public MowzieModelRenderer vehiclelowerlegL4;
    public MowzieModelRenderer vehiclelowerlegL5;
    public MowzieModelRenderer vehicleWheelBackL;
    public MowzieModelRenderer vehicletorsobase;
    public MowzieModelRenderer vehiclehood1;
    public MowzieModelRenderer vehiclefrontR1;
    public MowzieModelRenderer vehiclefrontL1;
    public MowzieModelRenderer vehiclebackplate1;
    public MowzieModelRenderer vehiclebackplate2;
    public MowzieModelRenderer vehicleWheelR;
    public MowzieModelRenderer vehicleWheelL;
    public MowzieModelRenderer vehiclearmbaseR1;
    public MowzieModelRenderer vehiclearmbaseL1;
    public MowzieModelRenderer vehiclewindow1;
    public MowzieModelRenderer vehiclebaseplate;
    public MowzieModelRenderer vehiclehood2;
    public MowzieModelRenderer vehiclehood3;
    public MowzieModelRenderer vehiclehood4;
    public MowzieModelRenderer vehiclefrontR2;
    public MowzieModelRenderer vehiclefrontL2;
    public MowzieModelRenderer vehicleupperarmR1;
    public MowzieModelRenderer vehicleshoulderL1;
    public MowzieModelRenderer vehiclelowerarmR1;
    public MowzieModelRenderer vehiclelowerarmR2;
    public MowzieModelRenderer vehicleUpperarmL1;
    public MowzieModelRenderer vehicleshoulderL1_1;
    public MowzieModelRenderer vehicleLowerarmL1;
    public MowzieModelRenderer vehicleLowerarmL2;
    public MowzieModelRenderer vehicleWindow2;
    public MowzieModelRenderer vehiclerear1;
    public MowzieModelRenderer vehicleRear2;

    public MowzieModelRenderer waist;
    public MowzieModelRenderer waist1;
    public MowzieModelRenderer waist2;
    public MowzieModelRenderer waistconnector1;
    public MowzieModelRenderer waist3;
    public MowzieModelRenderer torsoconnector1;
    public MowzieModelRenderer upperLegR;
    public MowzieModelRenderer upperLegL;
    public MowzieModelRenderer upperlegR2;
    public MowzieModelRenderer kneeR1;
    public MowzieModelRenderer lowerLegR;
    public MowzieModelRenderer lowerlegR7;
    public MowzieModelRenderer lowerlegR2;
    public MowzieModelRenderer lowerlegR4;
    public MowzieModelRenderer lowerlegR5;
    public MowzieModelRenderer lowerlegR6;
    public MowzieModelRenderer footbaseR;
    public MowzieModelRenderer lowerlegR3;
    public MowzieModelRenderer footR1;
    public MowzieModelRenderer footR3;
    public MowzieModelRenderer footR2;
    public MowzieModelRenderer upperlegL2;
    public MowzieModelRenderer kneeL1;
    public MowzieModelRenderer lowerLegL;
    public MowzieModelRenderer lowerlegL7;
    public MowzieModelRenderer lowerlegL2;
    public MowzieModelRenderer lowerlegL4;
    public MowzieModelRenderer lowerlegL5;
    public MowzieModelRenderer lowerlegL6;
    public MowzieModelRenderer footbaseL;
    public MowzieModelRenderer lowerlegL3;
    public MowzieModelRenderer footL1;
    public MowzieModelRenderer footL3;
    public MowzieModelRenderer footL2;
    public MowzieModelRenderer torsobase;
    public MowzieModelRenderer ribs1;
    public MowzieModelRenderer ribs2;
    public MowzieModelRenderer connector1;
    public MowzieModelRenderer connector2;
    public MowzieModelRenderer fronttorso1;
    public MowzieModelRenderer hood1;
    public MowzieModelRenderer frontR1;
    public MowzieModelRenderer torsodetail1;
    public MowzieModelRenderer frontL1;
    public MowzieModelRenderer torsodetail2;
    public MowzieModelRenderer torsodetail3;
    public MowzieModelRenderer torsodetail4;
    public MowzieModelRenderer backplate1;
    public MowzieModelRenderer backplate2;
    public MowzieModelRenderer neck1;
    public MowzieModelRenderer shoulderpadR;
    public MowzieModelRenderer shoulderpadL;
    public MowzieModelRenderer armbaseR1;
    public MowzieModelRenderer armbaseL1;
    public MowzieModelRenderer hood2;
    public MowzieModelRenderer hood3;
    public MowzieModelRenderer hood4;
    public MowzieModelRenderer frontR2;
    public MowzieModelRenderer frontL2;
    public MowzieModelRenderer head;
    public MowzieModelRenderer visor1;
    public MowzieModelRenderer headback1;
    public MowzieModelRenderer visor2;
    public MowzieModelRenderer headback2;
    public MowzieModelRenderer visor3;
    public MowzieModelRenderer visor4;
    public MowzieModelRenderer headback3;
    public MowzieModelRenderer upperArmR;
    public MowzieModelRenderer shoulderR1;
    public MowzieModelRenderer lowerArmR;
    public MowzieModelRenderer upperarmR2;
    public MowzieModelRenderer fistR;
    public MowzieModelRenderer lowerarmR2;
    public MowzieModelRenderer upperArmL;
    public MowzieModelRenderer shoulderL1;
    public MowzieModelRenderer upperarmL2;
    public MowzieModelRenderer lowerArmL;
    public MowzieModelRenderer fistL;
    public MowzieModelRenderer lowerarmL2;

    public ModelVurp()
    {
        textureWidth = 128;
        textureHeight = 128;

        waist1 = new MowzieModelRenderer(this, 0, 64);
        waist1.mirror = true;
        waist1.setRotationPoint(0, 0, -2);
        waist1.addBox(0, 0, 0, 3, 2, 1);
        setRotateAngle(waist1, 0, -0.12217304763960307F, -0.19198621771937624F);
        fistL = new MowzieModelRenderer(this, 72, 64);
        fistL.mirror = true;
        fistL.setRotationPoint(0, 3.8F, -0.5F);
        fistL.addBox(-1.2F, -0.5F, -1, 2, 2, 2);
        setRotateAngle(fistL, 0, 0, 0.12217304763960307F);
        lowerlegL4 = new MowzieModelRenderer(this, 8, 83);
        lowerlegL4.setRotationPoint(-0.3F, 4, 0.5F);
        lowerlegL4.addBox(-0.6F, -2, -2, 1, 3, 2);
        setRotateAngle(lowerlegL4, 0.19198621771937624F, -0.2792526803190927F, 0);
        torsoconnector1 = new MowzieModelRenderer(this, 35, 60);
        torsoconnector1.setRotationPoint(0, 0.1F, -0.4F);
        torsoconnector1.addBox(-1, -4, -1, 2, 4, 2);
        setRotateAngle(torsoconnector1, 0, 0.7853981633974483F, 0);
        lowerlegR7 = new MowzieModelRenderer(this, 14, 85);
        lowerlegR7.setRotationPoint(0, -0.2F, -1);
        lowerlegR7.addBox(-2.5F, 0, -1, 3, 2, 1);
        setRotateAngle(lowerlegR7, 0.4363323129985824F, 0.017453292519943295F, 0);
        shoulderL1 = new MowzieModelRenderer(this, 78, 60);
        shoulderL1.mirror = true;
        shoulderL1.setRotationPoint(2.3F, -0.2F, 0.4F);
        shoulderL1.addBox(-1, -1.5F, -1.5F, 1, 3, 3);
        setRotateAngle(shoulderL1, 0.017453292519943295F, 0.05235987755982988F, -0.20943951023931953F);
        torsodetail2 = new MowzieModelRenderer(this, 45, 78);
        torsodetail2.setRotationPoint(2, 0, 1);
        torsodetail2.addBox(-1.5F, -1, -1, 2, 2, 2);
        setRotateAngle(torsodetail2, 0.13962634015954636F, -0.03490658503988659F, 0.15707963267948966F);
        shoulderpadR = new MowzieModelRenderer(this, 55, 68);
        shoulderpadR.setRotationPoint(-2, -3, 1);
        shoulderpadR.addBox(-0.2F, -2, -1, 1, 2, 2);
        setRotateAngle(shoulderpadR, -0.20943951023931953F, 0, -1.064650843716541F);
        frontL1 = new MowzieModelRenderer(this, 35, 77);
        frontL1.mirror = true;
        frontL1.setRotationPoint(1.8F, -2.1F, 1.4F);
        frontL1.addBox(-0.5F, -1, -4, 2, 2, 3);
        setRotateAngle(frontL1, 0.5410520681182421F, -0.03490658503988659F, -0.20943951023931953F);
        lowerlegL3 = new MowzieModelRenderer(this, 0, 85);
        lowerlegL3.mirror = true;
        lowerlegL3.setRotationPoint(0, 4, 0);
        lowerlegL3.addBox(0, 0, 0, 2, 2, 1);
        setRotateAngle(lowerlegL3, 0, -0.296705972839036F, 0);
        footL1 = new MowzieModelRenderer(this, 5, 92);
        footL1.mirror = true;
        footL1.setRotationPoint(0, 3, 0);
        footL1.addBox(-1.5F, -0.5F, -2, 3, 1, 3);
        setRotateAngle(footL1, 0.3839724354387525F, 0, 0);
        upperArmR = new MowzieModelRenderer(this, 74, 60);
        upperArmR.setRotationPoint(-1.5F, 0, 1);
        upperArmR.addBox(0, 0.8F, -0.5F, 1, 3, 1);
        setRotateAngle(upperArmR, 0.22689280275926282F, 0, 0);
        backplate1 = new MowzieModelRenderer(this, 51, 74);
        backplate1.setRotationPoint(0, -3, 3);
        backplate1.addBox(0, -1, 0, 3, 5, 1);
        setRotateAngle(backplate1, 0.017453292519943295F, 0.12217304763960307F, -0.10471975511965977F);
        lowerlegR2 = new MowzieModelRenderer(this, 10, 75);
        lowerlegR2.setRotationPoint(0.5F, 0, 0);
        lowerlegR2.addBox(0, 0, 0, 1, 6, 2);
        setRotateAngle(lowerlegR2, -0.33161255787892263F, 0, -0.03490658503988659F);
        upperlegR2 = new MowzieModelRenderer(this, 6, 72);
        upperlegR2.setRotationPoint(-2, -0.5F, -0.5F);
        upperlegR2.addBox(0, 0, 0, 1, 4, 1);
        setRotateAngle(upperlegR2, 0, 0.05235987755982988F, -0.03490658503988659F);
        upperLegL = new MowzieModelRenderer(this, 0, 71);
        upperLegL.setRotationPoint(1, 2, 0);
        upperLegL.addBox(0, -1, -1, 1, 4, 2);
        setRotateAngle(upperLegL, -0.148352986419518F, 0.017453292519943295F, -0.12217304763960307F);
        fronttorso1 = new MowzieModelRenderer(this, 35, 73);
        fronttorso1.setRotationPoint(-2, -2, -0.5F);
        fronttorso1.addBox(0, 0, 0, 4, 2, 2);
        setRotateAngle(fronttorso1, -1.1519173063162573F, 0, 0);
        upperLegR = new MowzieModelRenderer(this, 0, 71);
        upperLegR.setRotationPoint(-1, 2, 0);
        upperLegR.addBox(-1, -1, -1, 1, 4, 2);
        setRotateAngle(upperLegR, -0.148352986419518F, -0.017453292519943295F, 0.12217304763960307F);
        kneeR1 = new MowzieModelRenderer(this, 10, 71);
        kneeR1.setRotationPoint(0, 2.7F, 0);
        kneeR1.addBox(-2, 0, -1, 2, 2, 2);
        setRotateAngle(kneeR1, 0.148352986419518F, 0.017453292519943295F, -0.12217304763960307F);
        shoulderR1 = new MowzieModelRenderer(this, 78, 60);
        shoulderR1.setRotationPoint(-2.3F, -0.2F, 0.4F);
        shoulderR1.addBox(0, -1.5F, -1.5F, 1, 3, 3);
        setRotateAngle(shoulderR1, 0.017453292519943295F, -0.05235987755982988F, 0.20943951023931953F);
        footR1 = new MowzieModelRenderer(this, 5, 92);
        footR1.setRotationPoint(0, 3, 0);
        footR1.addBox(-1.5F, -0.5F, -2, 3, 1, 3);
        setRotateAngle(footR1, 0.3839724354387525F, 0, 0);
        ribs1 = new MowzieModelRenderer(this, 35, 82);
        ribs1.setRotationPoint(0, -1.8F, 0);
        ribs1.addBox(-1, 0, -2, 2, 1, 4);
        setRotateAngle(ribs1, 0, 0.7853981633974483F, 0);
        headback2 = new MowzieModelRenderer(this, 40, 98);
        headback2.setRotationPoint(0, 0, 0);
        headback2.addBox(0, 0, -2, 1, 1, 3);
        setRotateAngle(headback2, 0.10471975511965977F, -0.08726646259971647F, 0.12217304763960307F);
        armbaseL1 = new MowzieModelRenderer(this, 64, 60);
        armbaseL1.mirror = true;
        armbaseL1.setRotationPoint(3, -2, 1);
        armbaseL1.addBox(-0.3F, -1, -1, 2, 2, 3);
        setRotateAngle(armbaseL1, 0, -0.10471975511965977F, -0.17453292519943295F);
        armbaseR1 = new MowzieModelRenderer(this, 64, 60);
        armbaseR1.setRotationPoint(-3, -2, 1);
        armbaseR1.addBox(-1.7F, -1, -1, 2, 2, 3);
        setRotateAngle(armbaseR1, 0, 0.10471975511965977F, 0.17453292519943295F);
        lowerarmL2 = new MowzieModelRenderer(this, 74, 68);
        lowerarmL2.mirror = true;
        lowerarmL2.setRotationPoint(0.9F, 0.8F, -1.6F);
        lowerarmL2.addBox(-1, 0, -2, 1, 3, 2);
        setRotateAngle(lowerarmL2, 0, 1.117010721276371F, 0);
        ribs2 = new MowzieModelRenderer(this, 47, 80);
        ribs2.setRotationPoint(0, -3.5F, 0);
        ribs2.addBox(-0.9F, 0, -3, 2, 1, 6);
        setRotateAngle(ribs2, 0, 0.7853981633974483F, 0);
        lowerlegR6 = new MowzieModelRenderer(this, 15, 82);
        lowerlegR6.setRotationPoint(1, 0, 2);
        lowerlegR6.addBox(-2, 0, 0, 2, 2, 1);
        setRotateAngle(lowerlegR6, -0.45378560551852565F, 0, 0);
        lowerLegL = new MowzieModelRenderer(this, 0, 77);
        lowerLegL.mirror = true;
        lowerLegL.setRotationPoint(1, 0.4F, -1);
        lowerLegL.addBox(-1.5F, 0, 0, 3, 6, 2);
        setRotateAngle(lowerLegL, 0.24434609527920614F, 0, 0);
        lowerlegR5 = new MowzieModelRenderer(this, 16, 75);
        lowerlegR5.setRotationPoint(-0.5F, 0, 0);
        lowerlegR5.addBox(-1, 0, 0, 1, 6, 1);
        setRotateAngle(lowerlegR5, -0.17453292519943295F, 0.05235987755982988F, 0);
        lowerlegR3 = new MowzieModelRenderer(this, 0, 85);
        lowerlegR3.setRotationPoint(0, 4, 0);
        lowerlegR3.addBox(-2, 0, 0, 2, 2, 1);
        setRotateAngle(lowerlegR3, 0, 0.296705972839036F, -0);
        waist = new MowzieModelRenderer(this, 0, 60);
        waist.setRotationPoint(0, 11.1F, 1);
        waist.addBox(-2, 0, -1, 4, 1, 3);
        lowerArmR = new MowzieModelRenderer(this, 64, 65);
        lowerArmR.mirror = true;
        lowerArmR.setRotationPoint(0, 3.3F, -0.6F);
        lowerArmR.addBox(-0.9F, -0.2F, -1.6F, 2, 4, 3);
        setRotateAngle(lowerArmR, -0.5235987755982988F, -0.06981317007977318F, -0.12217304763960307F);
        waist3 = new MowzieModelRenderer(this, 12, 64);
        waist3.setRotationPoint(0, -0.2F, -0.5F);
        waist3.addBox(-3, 0, 0, 6, 2, 2);
        setRotateAngle(waist3, -0.24434609527920614F, 0, 0);
        fistR = new MowzieModelRenderer(this, 72, 64);
        fistR.setRotationPoint(0, 3.8F, -0.5F);
        fistR.addBox(-0.8F, -0.5F, -1, 2, 2, 2);
        setRotateAngle(fistR, 0, 0, -0.12217304763960307F);
        visor1 = new MowzieModelRenderer(this, 43, 90);
        visor1.setRotationPoint(-1, -2.9F, -1);
        visor1.addBox(0, 0, 0, 2, 2, 1);
        setRotateAngle(visor1, -0.17453292519943295F, 0, 0);
        footL2 = new MowzieModelRenderer(this, 0, 96);
        footL2.mirror = true;
        footL2.setRotationPoint(-1.5F, 0, -2);
        footL2.addBox(0, -0.5F, -1, 3, 1, 4);
        setRotateAngle(footL2, -0.33161255787892263F, 0, 0);
        backplate2 = new MowzieModelRenderer(this, 51, 74);
        backplate2.mirror = true;
        backplate2.setRotationPoint(0, -3, 3);
        backplate2.addBox(-3, -1, 0, 3, 5, 1);
        setRotateAngle(backplate2, 0.017453292519943295F, -0.12217304763960307F, 0.12217304763960307F);
        neck1 = new MowzieModelRenderer(this, 35, 90);
        neck1.setRotationPoint(0, -1.7F, -0.5F);
        neck1.addBox(-1, -2, 0, 2, 1, 2);
        visor4 = new MowzieModelRenderer(this, 45, 96);
        visor4.setRotationPoint(3, 1, 0);
        visor4.addBox(-1, 0, -2, 1, 1, 2);
        setRotateAngle(visor4, 0.10471975511965977F, 0.2792526803190927F, 0);
        footR2 = new MowzieModelRenderer(this, 0, 96);
        footR2.setRotationPoint(1.5F, 0, -2);
        footR2.addBox(-3, -0.5F, -1, 3, 1, 4);
        setRotateAngle(footR2, -0.33161255787892263F, 0, 0);
        headback3 = new MowzieModelRenderer(this, 40, 98);
        headback3.setRotationPoint(3, 0, 0);
        headback3.addBox(-1, 0, -2, 1, 1, 3);
        setRotateAngle(headback3, 0.10471975511965977F, 0.08726646259971647F, -0.12217304763960307F);
        waistconnector1 = new MowzieModelRenderer(this, 4, 64);
        waistconnector1.setRotationPoint(0, 0, 0);
        waistconnector1.addBox(-1, -0.1F, -2.3F, 2, 3, 4);
        setRotateAngle(waistconnector1, 0.008726646259971648F, 0, 0);
        headback1 = new MowzieModelRenderer(this, 35, 97);
        headback1.setRotationPoint(-1.5F, -3, 1);
        headback1.addBox(0, 0, 0, 3, 3, 1);
        setRotateAngle(headback1, -0.10471975511965977F, 0, 0);
        footbaseR = new MowzieModelRenderer(this, 0, 91);
        footbaseR.setRotationPoint(0, 4, 0.7F);
        footbaseR.addBox(-1, 1, -1, 2, 2, 2);
        setRotateAngle(footbaseR, -0.24434609527920614F, 0, 0);
        upperArmL = new MowzieModelRenderer(this, 74, 60);
        upperArmL.mirror = true;
        upperArmL.setRotationPoint(0.5F, 0, 1);
        upperArmL.addBox(0, 0.8F, -0.5F, 1, 3, 1);
        setRotateAngle(upperArmL, 0.22689280275926282F, 0, 0);
        lowerarmR2 = new MowzieModelRenderer(this, 74, 68);
        lowerarmR2.setRotationPoint(-0.9F, 0.8F, -1.6F);
        lowerarmR2.addBox(0, 0, -2, 1, 3, 2);
        setRotateAngle(lowerarmR2, 0, -1.117010721276371F, 0);
        footL3 = new MowzieModelRenderer(this, 0, 101);
        footL3.mirror = true;
        footL3.setRotationPoint(0, 3.4F, 0.4F);
        footL3.addBox(-1, -1.2F, -2, 2, 2, 4);
        setRotateAngle(footL3, 0, -0.008726646259971648F, 0);
        lowerlegL6 = new MowzieModelRenderer(this, 15, 82);
        lowerlegL6.mirror = true;
        lowerlegL6.setRotationPoint(-1, 0, 2);
        lowerlegL6.addBox(0, 0, 0, 2, 2, 1);
        setRotateAngle(lowerlegL6, -0.45378560551852565F, 0, 0);
        frontR2 = new MowzieModelRenderer(this, 48, 70);
        frontR2.setRotationPoint(0, 0, 0);
        frontR2.addBox(-1.5F, -2, -3.7F, 2, 1, 3);
        setRotateAngle(frontR2, 0.24434609527920614F, 0, -0.017453292519943295F);
        visor2 = new MowzieModelRenderer(this, 44, 93);
        visor2.setRotationPoint(0.5F, 0.1F, 0);
        visor2.addBox(0, 0, -2, 2, 1, 2);
        setRotateAngle(visor2, 0.10471975511965977F, 0, 0);
        shoulderpadL = new MowzieModelRenderer(this, 55, 68);
        shoulderpadL.mirror = true;
        shoulderpadL.setRotationPoint(2, -3, 1);
        shoulderpadL.addBox(-0.8F, -2, -1, 1, 2, 2);
        setRotateAngle(shoulderpadL, -0.20943951023931953F, 0, 1.064650843716541F);
        waist2 = new MowzieModelRenderer(this, 0, 64);
        waist2.setRotationPoint(0, 0, -2);
        waist2.addBox(-3, 0, 0, 3, 2, 1);
        setRotateAngle(waist2, 0, 0.12217304763960307F, 0.19198621771937624F);
        kneeL1 = new MowzieModelRenderer(this, 10, 71);
        kneeL1.setRotationPoint(0, 2.7F, 0);
        kneeL1.addBox(0, 0, -1, 2, 2, 2);
        setRotateAngle(kneeL1, 0.148352986419518F, -0.017453292519943295F, 0.12217304763960307F);
        visor3 = new MowzieModelRenderer(this, 45, 96);
        visor3.setRotationPoint(0, 1, 0);
        visor3.addBox(0, 0, -2, 1, 1, 2);
        setRotateAngle(visor3, 0.10471975511965977F, -0.2792526803190927F, 0);
        lowerlegL7 = new MowzieModelRenderer(this, 14, 85);
        lowerlegL7.mirror = true;
        lowerlegL7.setRotationPoint(0, -0.2F, -1);
        lowerlegL7.addBox(-0.5F, 0, -1, 3, 2, 1);
        setRotateAngle(lowerlegL7, 0.4363323129985824F, -0.017453292519943295F, 0);
        hood4 = new MowzieModelRenderer(this, 51, 65);
        hood4.setRotationPoint(0, 0, 0);
        hood4.addBox(-1.5F, -0.1F, -1.4F, 3, 2, 1);
        setRotateAngle(hood4, 0.8656833089891874F, 0, 0);
        hood3 = new MowzieModelRenderer(this, 47, 65);
        hood3.setRotationPoint(0, 0, 0);
        hood3.addBox(-1.8F, -3.3F, -1, 1, 4, 1);
        setRotateAngle(hood3, 0, -0.0017453292519943296F, -0.19198621771937624F);
        connector2 = new MowzieModelRenderer(this, 43, 61);
        connector2.mirror = true;
        connector2.setRotationPoint(1.5F, 0, 2);
        connector2.addBox(-1, -0.1F, -0.5F, 1, 4, 1);
        setRotateAngle(connector2, -0.13962634015954636F, 0, 0.13962634015954636F);
        hood2 = new MowzieModelRenderer(this, 47, 65);
        hood2.setRotationPoint(0, 0, 0);
        hood2.addBox(0.8F, -3.3F, -1, 1, 4, 1);
        setRotateAngle(hood2, 0, 0.0017453292519943296F, 0.19198621771937624F);
        frontR1 = new MowzieModelRenderer(this, 35, 77);
        frontR1.setRotationPoint(-1.8F, -2.1F, 1.4F);
        frontR1.addBox(-1.5F, -1, -4, 2, 2, 3);
        setRotateAngle(frontR1, 0.5410520681182421F, 0.03490658503988659F, 0.20943951023931953F);
        lowerlegL2 = new MowzieModelRenderer(this, 10, 75);
        lowerlegL2.mirror = true;
        lowerlegL2.setRotationPoint(-0.5F, 0, 0);
        lowerlegL2.addBox(-1, 0, 0, 1, 6, 2);
        setRotateAngle(lowerlegL2, -0.33161255787892263F, 0, 0.03490658503988659F);
        upperarmL2 = new MowzieModelRenderer(this, 74, 60);
        upperarmL2.setRotationPoint(0, -0.6F, -1.3F);
        upperarmL2.addBox(0, 0.8F, -0.5F, 1, 3, 1);
        torsobase = new MowzieModelRenderer(this, 35, 66);
        torsobase.setRotationPoint(0, -4, 0);
        torsobase.addBox(-2, -3, -0.7F, 4, 3, 4);
        setRotateAngle(torsobase, 0, -0.7853981633974483F, 0);
        torsodetail1 = new MowzieModelRenderer(this, 45, 78);
        torsodetail1.setRotationPoint(-2, 0, 1);
        torsodetail1.addBox(-0.5F, -1, -1, 2, 2, 2);
        setRotateAngle(torsodetail1, 0.13962634015954636F, 0.03490658503988659F, -0.15707963267948966F);
        lowerlegL5 = new MowzieModelRenderer(this, 16, 75);
        lowerlegL5.mirror = true;
        lowerlegL5.setRotationPoint(0.5F, 0, 0);
        lowerlegL5.addBox(0, 0, 0, 1, 6, 1);
        setRotateAngle(lowerlegL5, -0.17453292519943295F, -0.05235987755982988F, 0);
        upperarmR2 = new MowzieModelRenderer(this, 74, 60);
        upperarmR2.mirror = true;
        upperarmR2.setRotationPoint(0, -0.6F, -1.3F);
        upperarmR2.addBox(0, 0.8F, -0.5F, 1, 3, 1);
        hood1 = new MowzieModelRenderer(this, 47, 60);
        hood1.setRotationPoint(0, -0.5F, -1.8F);
        hood1.addBox(-1.5F, -3, -1, 3, 4, 1);
        setRotateAngle(hood1, -0.5061454830783556F, 0, 0);
        lowerArmL = new MowzieModelRenderer(this, 64, 65);
        lowerArmL.setRotationPoint(1, 3.3F, -0.6F);
        lowerArmL.addBox(-1.1F, -0.2F, -1.6F, 2, 4, 3);
        setRotateAngle(lowerArmL, -0.5235987755982988F, 0.06981317007977318F, 0.12217304763960307F);
        upperlegL2 = new MowzieModelRenderer(this, 6, 72);
        upperlegL2.setRotationPoint(2, -0.5F, -0.5F);
        upperlegL2.addBox(-1, 0, 0, 1, 4, 1);
        setRotateAngle(upperlegL2, 0, -0.05235987755982988F, 0.03490658503988659F);
        lowerlegR4 = new MowzieModelRenderer(this, 8, 83);
        lowerlegR4.setRotationPoint(0.3F, 4, 0.5F);
        lowerlegR4.addBox(-0.4F, -2, -2, 1, 3, 2);
        setRotateAngle(lowerlegR4, 0.19198621771937624F, 0.2792526803190927F, 0);
        head = new MowzieModelRenderer(this, 35, 93);
        head.setRotationPoint(0, -1.9F, 0.6F);
        head.addBox(-1.5F, -1, -1.5F, 3, 1, 3);
        frontL2 = new MowzieModelRenderer(this, 48, 70);
        frontL2.mirror = true;
        frontL2.setRotationPoint(0, 0, 0);
        frontL2.addBox(-0.5F, -2, -3.7F, 2, 1, 3);
        setRotateAngle(frontL2, 0.24434609527920614F, 0, 0.017453292519943295F);
        footR3 = new MowzieModelRenderer(this, 0, 101);
        footR3.setRotationPoint(0, 3.4F, 0.4F);
        footR3.addBox(-1, -1.2F, -2, 2, 2, 4);
        setRotateAngle(footR3, 0, 0.008726646259971648F, 0);
        connector1 = new MowzieModelRenderer(this, 43, 61);
        connector1.setRotationPoint(-1.5F, 0, 2);
        connector1.addBox(0, -0.1F, -0.5F, 1, 4, 1);
        setRotateAngle(connector1, -0.13962634015954636F, 0, -0.13962634015954636F);
        torsodetail3 = new MowzieModelRenderer(this, 55, 61);
        torsodetail3.setRotationPoint(-2, -2, 1);
        torsodetail3.addBox(-1, -1, -1, 1, 2, 2);
        setRotateAngle(torsodetail3, 0, 0.13962634015954636F, 0.33161255787892263F);
        footbaseL = new MowzieModelRenderer(this, 0, 91);
        footbaseL.mirror = true;
        footbaseL.setRotationPoint(0, 4, 0.7F);
        footbaseL.addBox(-1, 1, -1, 2, 2, 2);
        setRotateAngle(footbaseL, -0.24434609527920614F, 0, 0);
        torsodetail4 = new MowzieModelRenderer(this, 55, 61);
        torsodetail4.setRotationPoint(2, -2, 1);
        torsodetail4.addBox(0, -1, -1, 1, 2, 2);
        setRotateAngle(torsodetail4, 0, -0.13962634015954636F, -0.33161255787892263F);
        lowerLegR = new MowzieModelRenderer(this, 0, 77);
        lowerLegR.setRotationPoint(-1, 0.4F, -1);
        lowerLegR.addBox(-1.5F, 0, 0, 3, 6, 2);
        setRotateAngle(lowerLegR, 0.24434609527920614F, 0, 0);

        footbaseR.addChild(footR3);
        torsobase.addChild(connector1);
        torsobase.addChild(torsodetail3);
        lowerLegL.addChild(footbaseL);
        torsobase.addChild(torsodetail4);
        kneeR1.addChild(lowerLegR);
        waist.addChild(waist1);
        lowerArmL.addChild(fistL);
        lowerLegL.addChild(lowerlegL4);
        waist.addChild(torsoconnector1);
        armbaseL1.addChild(shoulderL1);
        torsobase.addChild(torsodetail2);
        torsobase.addChild(shoulderpadR);
        torsobase.addChild(frontL1);
        lowerlegL2.addChild(lowerlegL3);
        footbaseL.addChild(footL1);
        armbaseR1.addChild(upperArmR);
        torsobase.addChild(backplate1);
        lowerLegR.addChild(lowerlegR2);
        upperLegR.addChild(upperlegR2);
        waistconnector1.addChild(upperLegL);
        torsobase.addChild(fronttorso1);
        waistconnector1.addChild(upperLegR);
        upperLegR.addChild(kneeR1);
        armbaseR1.addChild(shoulderR1);
        footbaseR.addChild(footR1);
        lowerLegR.addChild(footbaseR);
        torsoconnector1.addChild(ribs1);
        headback1.addChild(headback2);
        torsobase.addChild(armbaseL1);
        torsobase.addChild(armbaseR1);
        lowerArmL.addChild(lowerarmL2);
        torsoconnector1.addChild(ribs2);
        lowerLegR.addChild(lowerlegR6);
        kneeL1.addChild(lowerLegL);
        lowerLegR.addChild(lowerlegR5);
        lowerlegR2.addChild(lowerlegR3);
        upperArmR.addChild(lowerArmR);
        waist.addChild(waist3);
        lowerArmR.addChild(fistR);
        head.addChild(visor1);
        footL1.addChild(footL2);
        torsobase.addChild(backplate2);
        torsobase.addChild(neck1);
        headback1.addChild(visor4);
        footR1.addChild(footR2);
        headback1.addChild(headback3);
        waist.addChild(waistconnector1);
        head.addChild(headback1);
        armbaseL1.addChild(upperArmL);
        lowerArmR.addChild(lowerarmR2);
        footbaseL.addChild(footL3);
        lowerLegL.addChild(lowerlegL6);
        frontR1.addChild(frontR2);
        torsobase.addChild(shoulderpadL);
        hood1.addChild(hood2);
        torsobase.addChild(frontR1);
        upperArmL.addChild(upperarmL2);
        lowerLegL.addChild(lowerlegL2);
        torsoconnector1.addChild(torsobase);
        torsobase.addChild(torsodetail1);
        lowerLegL.addChild(lowerlegL5);
        upperArmR.addChild(upperarmR2);
        upperArmL.addChild(lowerArmL);
        torsobase.addChild(hood1);
        lowerLegR.addChild(lowerlegR4);
        upperLegL.addChild(upperlegL2);
        frontL1.addChild(frontL2);
        neck1.addChild(head);
        headback1.addChild(visor2);
        waist.addChild(waist2);
        upperLegL.addChild(kneeL1);
        headback1.addChild(visor3);
        kneeL1.addChild(lowerlegL7);
        kneeR1.addChild(lowerlegR7);
        hood1.addChild(hood4);
        hood1.addChild(hood3);
        torsobase.addChild(connector2);

        setInitPose();


        vehiclebackplate2 = new MowzieModelRenderer(this, 51, 74);
        vehiclebackplate2.mirror = true;
        vehiclebackplate2.setRotationPoint(-2.3F, -1.8F, 1.8F);
        vehiclebackplate2.addBox(-3, -2, 0, 3, 5, 1);
        setRotateAngle(vehiclebackplate2, 1.5882496193148399F, 0.017453292519943295F, -1.5707963267948966F);
        vehicleWaistConnector1 = new MowzieModelRenderer(this, 4, 64);
        vehicleWaistConnector1.setRotationPoint(0, 2, -1);
        vehicleWaistConnector1.addBox(-1, -0.1F, -2.3F, 2, 3, 4);
        setRotateAngle(vehicleWaistConnector1, 1.5707963267948966F, 0, 0);
        vehiclewindow1 = new MowzieModelRenderer(this, 0, 7);
        vehiclewindow1.setRotationPoint(0, -1.9F, -1.2F);
        vehiclewindow1.addBox(-2.5F, -0.2F, 0, 5, 2, 3);
        setRotateAngle(vehiclewindow1, 0.33161255787892263F, 0, 0);
        vehicleLowerlegR1 = new MowzieModelRenderer(this, 0, 77);
        vehicleLowerlegR1.setRotationPoint(-0.8F, 2, 1.5F);
        vehicleLowerlegR1.addBox(-1.5F, 0, 0, 3, 6, 2);
        setRotateAngle(vehicleLowerlegR1, 3.141592653589793F, 0, -0.017453292519943295F);
        vehiclelowerlegL1 = new MowzieModelRenderer(this, 0, 77);
        vehiclelowerlegL1.mirror = true;
        vehiclelowerlegL1.setRotationPoint(0.8F, 2, 1.5F);
        vehiclelowerlegL1.addBox(-1.5F, 0, 0, 3, 6, 2);
        setRotateAngle(vehiclelowerlegL1, 3.141592653589793F, 0, 0.017453292519943295F);
        vehicleLowerarmL1 = new MowzieModelRenderer(this, 64, 65);
        vehicleLowerarmL1.setRotationPoint(0, 3.3F, 0.4F);
        vehicleLowerarmL1.addBox(-1.1F, -0.2F, -1.6F, 2, 4, 3);
        setRotateAngle(vehicleLowerarmL1, -3.141592653589793F, 0, 0);
        vehiclefrontL2 = new MowzieModelRenderer(this, 48, 70);
        vehiclefrontL2.mirror = true;
        vehiclefrontL2.setRotationPoint(0, 0, 0);
        vehiclefrontL2.addBox(-0.5F, -2, -3.7F, 2, 1, 3);
        setRotateAngle(vehiclefrontL2, 0.24434609527920614F, 0, 0.017453292519943295F);
        vehicleWaist2 = new MowzieModelRenderer(this, 0, 64);
        vehicleWaist2.setRotationPoint(0, 7, 1.5F);
        vehicleWaist2.addBox(-3, 0, 0, 3, 2, 1);
        setRotateAngle(vehicleWaist2, -1.5707963267948966F, 0, -3.211405823669566F);
        vehicleupperarmR1 = new MowzieModelRenderer(this, 74, 60);
        vehicleupperarmR1.setRotationPoint(-1.5F, 0, 1);
        vehicleupperarmR1.addBox(0, 0.8F, -0.5F, 1, 3, 1);
        setRotateAngle(vehicleupperarmR1, 1.5707963267948966F, 0, 0);
        vehiclehood3 = new MowzieModelRenderer(this, 47, 65);
        vehiclehood3.setRotationPoint(0, 0, 0);
        vehiclehood3.addBox(-1.8F, -3.3F, -1, 1, 4, 1);
        setRotateAngle(vehiclehood3, -0.005235987755982988F, 0.003490658503988659F, -0.22165681500327983F);
        vehicleTorsoconnector1 = new MowzieModelRenderer(this, 35, 60);
        vehicleTorsoconnector1.setRotationPoint(0, 0.1F, -0.4F);
        vehicleTorsoconnector1.addBox(-1.2F, -4, -0.8F, 2, 4, 2);
        setRotateAngle(vehicleTorsoconnector1, 0, 0.7853981633974483F, 0);
        vehiclelowerlegL5 = new MowzieModelRenderer(this, 16, 75);
        vehiclelowerlegL5.mirror = true;
        vehiclelowerlegL5.setRotationPoint(1.4F, 0, 0);
        vehiclelowerlegL5.addBox(-1, 0, 0, 1, 6, 1);
        setRotateAngle(vehiclelowerlegL5, -0.15707963267948966F, 0.06981317007977318F, 0.05235987755982988F);
        vehiclehood2 = new MowzieModelRenderer(this, 47, 65);
        vehiclehood2.setRotationPoint(0, 0, 0);
        vehiclehood2.addBox(0.8F, -3.3F, -1, 1, 4, 1);
        setRotateAngle(vehiclehood2, -0.005235987755982988F, 0.003490658503988659F, 0.22165681500327983F);
        vehicleLowerlegR5 = new MowzieModelRenderer(this, 16, 75);
        vehicleLowerlegR5.setRotationPoint(-1.4F, 0, 0);
        vehicleLowerlegR5.addBox(0, 0, 0, 1, 6, 1);
        setRotateAngle(vehicleLowerlegR5, -0.15707963267948966F, -0.06981317007977318F, -0.05235987755982988F);
        vehiclelowerlegL4 = new MowzieModelRenderer(this, 8, 83);
        vehiclelowerlegL4.setRotationPoint(-0.3F, 2.5F, 0.9F);
        vehiclelowerlegL4.addBox(-0.5F, -2, -2, 1, 3, 2);
        setRotateAngle(vehiclelowerlegL4, 0.15707963267948966F, -0.10471975511965977F, 0.08726646259971647F);
        vehiclehood4 = new MowzieModelRenderer(this, 51, 65);
        vehiclehood4.setRotationPoint(0, -0.2F, 0);
        vehiclehood4.addBox(-1.5F, -0.7F, -1.4F, 3, 2, 1);
        setRotateAngle(vehiclehood4, 1.3526301702956054F, 0, 0);
        vehicleWindow2 = new MowzieModelRenderer(this, 0, 24);
        vehicleWindow2.setRotationPoint(0, -0.2F, 3);
        vehicleWindow2.addBox(-2.5F, 0, 0, 5, 1, 2);
        setRotateAngle(vehicleWindow2, -0.20943951023931953F, 0, 0);
        vehiclelowerlegR7 = new MowzieModelRenderer(this, 14, 85);
        vehiclelowerlegR7.setRotationPoint(0.5F, 1, 1.6F);
        vehiclelowerlegR7.addBox(-2.4F, -0.5F, -1, 3, 2, 1);
        setRotateAngle(vehiclelowerlegR7, 0.6108652381980153F, -0.05235987755982988F, 0);
        vehiclelowerarmR2 = new MowzieModelRenderer(this, 74, 68);
        vehiclelowerarmR2.setRotationPoint(-1.5F, 3.5F, 0);
        vehiclelowerarmR2.addBox(0, -3, -1.9F, 1, 3, 2);
        setRotateAngle(vehiclelowerarmR2, 0.03490658503988659F, -0.2617993877991494F, -0.10122909661567112F);
        vehicleRplate1 = new MowzieModelRenderer(this, 0, 17);
        vehicleRplate1.setRotationPoint(0, 0.4F, 0.3F);
        vehicleRplate1.addBox(-3.4F, -0.9F, -5.2F, 3, 1, 6);
        setRotateAngle(vehicleRplate1, 0.061086523819801536F, 0.05235987755982988F, -0.017453292519943295F);
        vehiclefrontL1 = new MowzieModelRenderer(this, 35, 77);
        vehiclefrontL1.mirror = true;
        vehiclefrontL1.setRotationPoint(1.8F, -0.1F, -0.9F);
        vehiclefrontL1.addBox(-0.5F, -1, -4, 2, 2, 3);
        vehiclebaseplate = new MowzieModelRenderer(this, 13, 0);
        vehiclebaseplate.setRotationPoint(-2.5F, 0.1F, -4.5F);
        vehiclebaseplate.addBox(0, 0, 0, 5, 1, 13);
        vehiclebackplate1 = new MowzieModelRenderer(this, 51, 74);
        vehiclebackplate1.setRotationPoint(2.3F, -1.8F, 1.8F);
        vehiclebackplate1.addBox(0, -2, 0, 3, 5, 1);
        setRotateAngle(vehiclebackplate1, 1.5882496193148399F, -0.017453292519943295F, 1.5707963267948966F);
        vehicleLplate1 = new MowzieModelRenderer(this, 0, 17);
        vehicleLplate1.mirror = true;
        vehicleLplate1.setRotationPoint(0, 0.4F, 0.3F);
        vehicleLplate1.addBox(0.4F, -0.9F, -5.2F, 3, 1, 6);
        setRotateAngle(vehicleLplate1, 0.061086523819801536F, -0.05235987755982988F, 0.017453292519943295F);
        vehiclelowerarmR1 = new MowzieModelRenderer(this, 64, 65);
        vehiclelowerarmR1.mirror = true;
        vehiclelowerarmR1.setRotationPoint(1, 3.3F, 0.4F);
        vehiclelowerarmR1.addBox(-0.9F, -0.2F, -1.6F, 2, 4, 3);
        setRotateAngle(vehiclelowerarmR1, -3.141592653589793F, 0, 0);
        vehiclerear1 = new MowzieModelRenderer(this, 0, 0);
        vehiclerear1.setRotationPoint(0, 0.3F, 4);
        vehiclerear1.addBox(-2.5F, 0.1F, 0, 5, 2, 5);
        setRotateAngle(vehiclerear1, -0.3141592653589793F, 0, 0);
        vehiclearmbaseR1 = new MowzieModelRenderer(this, 64, 60);
        vehiclearmbaseR1.setRotationPoint(-1, -0.4F, 1);
        vehiclearmbaseR1.addBox(-2, -1, -1, 2, 2, 3);
        vehicleWaist1 = new MowzieModelRenderer(this, 0, 64);
        vehicleWaist1.mirror = true;
        vehicleWaist1.setRotationPoint(0, 7, 1.5F);
        vehicleWaist1.addBox(0, 0, 0, 3, 2, 1);
        setRotateAngle(vehicleWaist1, -1.5707963267948966F, 0, 3.211405823669566F);
        vehiclelowerlegL7 = new MowzieModelRenderer(this, 14, 85);
        vehiclelowerlegL7.mirror = true;
        vehiclelowerlegL7.setRotationPoint(-0.5F, 1, 1.6F);
        vehiclelowerlegL7.addBox(-0.6F, -0.5F, -1, 3, 2, 1);
        setRotateAngle(vehiclelowerlegL7, 0.6108652381980153F, 0.05235987755982988F, 0);
        vehicleshoulderL1_1 = new MowzieModelRenderer(this, 78, 60);
        vehicleshoulderL1_1.mirror = true;
        vehicleshoulderL1_1.setRotationPoint(2.3F, 0, -1.6F);
        vehicleshoulderL1_1.addBox(-1, -1.4F, -1.5F, 1, 3, 3);
        setRotateAngle(vehicleshoulderL1_1, -0.019198621771937624F, 0, 0);
        vehicleshoulderL1 = new MowzieModelRenderer(this, 78, 60);
        vehicleshoulderL1.setRotationPoint(-2.3F, 0, -1.6F);
        vehicleshoulderL1.addBox(0, -1.4F, -1.5F, 1, 3, 3);
        setRotateAngle(vehicleshoulderL1, -0.019198621771937624F, 0, 0);
        vehiclefrontR1 = new MowzieModelRenderer(this, 35, 77);
        vehiclefrontR1.setRotationPoint(-1.8F, -0.1F, -0.9F);
        vehiclefrontR1.addBox(-1.5F, -1, -4, 2, 2, 3);
        vehicleUpperlegR2 = new MowzieModelRenderer(this, 6, 72);
        vehicleUpperlegR2.setRotationPoint(-2, -0.5F, -0.5F);
        vehicleUpperlegR2.addBox(0, 0, 0, 1, 4, 1);
        setRotateAngle(vehicleUpperlegR2, 0, 0.05235987755982988F, -0.03490658503988659F);
        vehicleupperlegL2 = new MowzieModelRenderer(this, 6, 72);
        vehicleupperlegL2.setRotationPoint(2, -0.5F, -0.5F);
        vehicleupperlegL2.addBox(-1, 0, 0, 1, 4, 1);
        setRotateAngle(vehicleupperlegL2, 0, -0.05235987755982988F, 0.03490658503988659F);
        vehiclekneeL2 = new MowzieModelRenderer(this, 10, 71);
        vehiclekneeL2.setRotationPoint(0, 2.7F, 0);
        vehiclekneeL2.addBox(0, 0, -1, 2, 2, 2);
        vehicleUpperlegR1 = new MowzieModelRenderer(this, 0, 71);
        vehicleUpperlegR1.setRotationPoint(-1, 1, 0);
        vehicleUpperlegR1.addBox(-1, -1, -1, 1, 4, 2);
        setRotateAngle(vehicleUpperlegR1, -1.5707963267948966F, 0, 0);
        vehiclefrontR2 = new MowzieModelRenderer(this, 48, 70);
        vehiclefrontR2.setRotationPoint(0, 0, 0);
        vehiclefrontR2.addBox(-1.5F, -2, -3.7F, 2, 1, 3);
        setRotateAngle(vehiclefrontR2, 0.24434609527920614F, 0, -0.017453292519943295F);
        vehicleKneeR1 = new MowzieModelRenderer(this, 10, 71);
        vehicleKneeR1.setRotationPoint(0, 2.7F, 0);
        vehicleKneeR1.addBox(-2, 0, -1, 2, 2, 2);
        vehicleUpperarmL1 = new MowzieModelRenderer(this, 74, 60);
        vehicleUpperarmL1.mirror = true;
        vehicleUpperarmL1.setRotationPoint(0.5F, 0, 1);
        vehicleUpperarmL1.addBox(0, 0.8F, -0.5F, 1, 3, 1);
        setRotateAngle(vehicleUpperarmL1, 1.5707963267948966F, 0, 0);
        vehicleLowerarmL2 = new MowzieModelRenderer(this, 74, 68);
        vehicleLowerarmL2.mirror = true;
        vehicleLowerarmL2.setRotationPoint(1.5F, 3.5F, 0);
        vehicleLowerarmL2.addBox(-1, -3, -1.9F, 1, 3, 2);
        setRotateAngle(vehicleLowerarmL2, 0.03490658503988659F, 0.2617993877991494F, 0.10122909661567112F);
        vehicleLowerlegR4 = new MowzieModelRenderer(this, 8, 83);
        vehicleLowerlegR4.setRotationPoint(0.3F, 2.5F, 0.9F);
        vehicleLowerlegR4.addBox(-0.5F, -2, -2, 1, 3, 2);
        setRotateAngle(vehicleLowerlegR4, 0.15707963267948966F, 0.10471975511965977F, -0.08726646259971647F);
        vehicleUpperlegL1 = new MowzieModelRenderer(this, 0, 71);
        vehicleUpperlegL1.setRotationPoint(1, 1, 0);
        vehicleUpperlegL1.addBox(0, -1, -1, 1, 4, 2);
        setRotateAngle(vehicleUpperlegL1, -1.5707963267948966F, 0, 0);
        vehicleRear2 = new MowzieModelRenderer(this, 0, 27);
        vehicleRear2.setRotationPoint(0, 0, 2);
        vehicleRear2.addBox(-2.5F, 0, 0, 5, 1, 2);
        setRotateAngle(vehicleRear2, -0.19198621771937624F, 0, 0);
        vehicleRearplate = new MowzieModelRenderer(this, 0, 12);
        vehicleRearplate.setRotationPoint(0, 0.6F, -1.1F);
        vehicleRearplate.addBox(-1.5F, -1, -4, 3, 1, 4);
        vehicleBase = new MowzieModelRenderer(this, 0, 60);
        vehicleBase.setRotationPoint(0, 22.3F, 1);
        vehicleBase.addBox(-2, 0, -1, 4, 1, 3);
        setRotateAngle(vehicleBase, 1.5707963267948966F, 0, 0);
        vehiclehood1 = new MowzieModelRenderer(this, 47, 60);
        vehiclehood1.setRotationPoint(0, -0.5F, -3.8F);
        vehiclehood1.addBox(-1.5F, -3, -1, 3, 4, 1);
        setRotateAngle(vehiclehood1, -1.3439035240356336F, 0, 0);

        vehicleWheelBackR = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelBackR.setRotationPoint(-1F, 3F, 2.2F);
        vehicleWheelBackR.addBox(-1, -1, -1, 1, 2, 2);
        vehicleWheelBackL = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelBackL.mirror = true;
        vehicleWheelBackL.setRotationPoint(1, 3, 2.3F);
        vehicleWheelBackL.addBox(0, -1, -1, 1, 2, 2);
        vehicleWheelL = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelL.mirror = true;
        vehicleWheelL.setRotationPoint(2.7F, 0.3F, -2);
        vehicleWheelL.addBox(0, -1, -1, 1, 2, 2);
        vehicleWheelR = new MowzieModelRenderer(this, 55, 68);
        vehicleWheelR.setRotationPoint(-2.7F, 0.3F, -2);
        vehicleWheelR.addBox(-1, -1, -1, 1, 2, 2);

        vehiclearmbaseL1 = new MowzieModelRenderer(this, 64, 60);
        vehiclearmbaseL1.mirror = true;
        vehiclearmbaseL1.setRotationPoint(1, -0.4F, 1);
        vehiclearmbaseL1.addBox(0, -1, -1, 2, 2, 3);
        vehicletorsobase = new MowzieModelRenderer(this, 35, 66);
        vehicletorsobase.setRotationPoint(0, -4, 0);
        vehicletorsobase.addBox(-2, -2, -1.3F, 4, 3, 4);
        setRotateAngle(vehicletorsobase, -1.5707963267948966F, -0.7853981633974483F, 0);

        vehicleWaistConnector1.addChild(vehicleRearplate);
        vehicletorsobase.addChild(vehiclehood1);
        vehiclelowerlegL1.addChild(vehicleWheelBackL);
        vehicletorsobase.addChild(vehicleWheelL);
        vehicletorsobase.addChild(vehiclearmbaseL1);
        vehicleTorsoconnector1.addChild(vehicletorsobase);
        vehicletorsobase.addChild(vehiclebackplate2);
        vehicleBase.addChild(vehicleWaistConnector1);
        vehicletorsobase.addChild(vehiclewindow1);
        vehicleKneeR1.addChild(vehicleLowerlegR1);
        vehiclekneeL2.addChild(vehiclelowerlegL1);
        vehicleUpperarmL1.addChild(vehicleLowerarmL1);
        vehicletorsobase.addChild(vehicleWheelR);
        vehiclefrontL1.addChild(vehiclefrontL2);
        vehicleBase.addChild(vehicleWaist2);
        vehiclearmbaseR1.addChild(vehicleupperarmR1);
        vehiclehood1.addChild(vehiclehood3);
        vehicleBase.addChild(vehicleTorsoconnector1);
        vehiclelowerlegL1.addChild(vehiclelowerlegL5);
        vehiclehood1.addChild(vehiclehood2);
        vehicleLowerlegR1.addChild(vehicleLowerlegR5);
        vehiclelowerlegL1.addChild(vehiclelowerlegL4);
        vehiclehood1.addChild(vehiclehood4);
        vehiclewindow1.addChild(vehicleWindow2);
        vehicleupperarmR1.addChild(vehiclelowerarmR1);
        vehicleKneeR1.addChild(vehiclelowerlegR7);
        vehiclelowerarmR1.addChild(vehiclelowerarmR2);
        vehicleWaistConnector1.addChild(vehicleRplate1);
        vehicletorsobase.addChild(vehiclefrontL1);
        vehicletorsobase.addChild(vehiclebaseplate);
        vehicletorsobase.addChild(vehiclebackplate1);
        vehicleWaistConnector1.addChild(vehicleLplate1);
        vehicleWindow2.addChild(vehiclerear1);
        vehicleBase.addChild(vehicleWaist1);
        vehicletorsobase.addChild(vehiclearmbaseR1);
        vehiclekneeL2.addChild(vehiclelowerlegL7);
        vehiclearmbaseL1.addChild(vehicleshoulderL1_1);
        vehiclearmbaseR1.addChild(vehicleshoulderL1);
        vehicletorsobase.addChild(vehiclefrontR1);
        vehicleUpperlegR1.addChild(vehicleUpperlegR2);
        vehicleUpperlegL1.addChild(vehicleupperlegL2);
        vehicleUpperlegL1.addChild(vehiclekneeL2);
        vehicleWaistConnector1.addChild(vehicleUpperlegR1);
        vehiclefrontR1.addChild(vehiclefrontR2);
        vehicleUpperlegR1.addChild(vehicleKneeR1);
        vehiclearmbaseL1.addChild(vehicleUpperarmL1);
        vehicleWaistConnector1.addChild(vehicleUpperlegL1);
        vehicleWindow2.addChild(vehicleRear2);
        vehicleLowerlegR1.addChild(vehicleLowerlegR4);
        vehicleLowerlegR1.addChild(vehicleWheelBackR);
        vehicleLowerarmL1.addChild(vehicleLowerarmL2);


        float scale = 1.25F;
        vehicleWheelR.setScale(1, scale, scale);
        vehicleWheelL.setScale(1, scale, scale);
        vehicleWheelBackR.setScale(1, scale, scale);
        vehicleWheelBackL.setScale(1, scale, scale);

        shoulderpadR.setScale(scale, scale, scale);
        shoulderpadL.setScale(scale, scale, scale);
        lowerlegR6.setScale(scale, scale, scale);
        lowerlegL6.setScale(scale, scale, scale);
    }

    public Transformer getTransformer()
    {
        return TransformerManager.transformerVurp;
    }

    public ModelRenderer getWaist()
    {
        return waist;
    }

    public ModelRenderer getVehicle(EntityPlayer player)
    {
        return vehicleBase;
    }

    public ModelRenderer getRightLeg()
    {
        return upperLegR;
    }

    public ModelRenderer getLeftLeg()
    {
        return upperLegL;
    }

    public ModelRenderer getHead()
    {
        return head;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ticks, float yaw, float pitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ticks, yaw, pitch, scale, entity);

        if (entity instanceof EntityPlayer)
        {
            setToInitPose();
            EntityPlayer player = (EntityPlayer) entity;
            float t = TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick);
            ModelOffset offsets = TFModelHelper.getOffsets(player);

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerVurp;

            Transformer transformerChest = TFHelper.getTransformerFromArmor(player, 2);
            boolean wearingChest = transformerChest instanceof TransformerVurp;

            Transformer transformerLegs = TFHelper.getTransformerFromArmor(player, 1);
            boolean wearingLegs = transformerLegs instanceof TransformerVurp;

            head.showModel = wearingHead;
            upperLegR.showModel = wearingLegs;
            upperLegL.showModel = wearingLegs;

            float globalSpeed = 1;
            float globalDegree = 0.8F;

            head.rotationPointX += offsets.headOffsetX;
            head.rotationPointY += offsets.headOffsetY;
            head.rotationPointZ += offsets.headOffsetZ;

            if (wearingHead)
            {
                faceTarget(head, 1, yaw, pitch);
            }

            if (!wearingChest)
            {
                head.rotationPointY += 2;
                upperLegL.rotationPointY += 10;
                upperLegR.rotationPointY += 10;
            }
            else
            {
                head.rotationPointY -= 3.5F;
                offsets.headOffsetY = 3.5F;

                if (!wearingLegs)
                {
                    waist.rotationPointY -= 1F;
                    waist.rotationPointZ -= 1F;

                    offsets.headOffsetY -= 1F;
                    head.rotationPointY += 1F;

                    if (transformerLegs instanceof TransformerSkystrike)
                    {
                        waist.rotationPointY -= 3;
                        offsets.headOffsetY -= 3;
                        head.rotationPointY += 3;
                    }
                }
            }

            if (transformerChest instanceof TransformerSkystrike)
            {
                head.rotationPointY -= 1;
                head.rotationPointZ -= 1;
            }

            int backwardInverter = 1;
            float moveForward = player.moveForward;

            if (moveForward < 0)
            {
                backwardInverter = -1;
                globalDegree = 0.5F;
            }

            applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
            applyDefaultHittingAnimation(armbaseR1, armbaseL1, head, torsobase, lowerArmR, lowerArmL);

            ItemStack heldItemStack = player.getHeldItem();
            boolean holdingSniper = heldItemStack != null && heldItemStack.getItem() instanceof ItemVurpsSniper;

            if (aimedBow)
            {
                upperArmR.rotateAngleY += -0.1F + head.rotateAngleY;
                upperArmL.rotateAngleY += 0.1F + head.rotateAngleY + 0.4F;
                upperArmR.rotateAngleX += -((float) Math.PI / 2F) + head.rotateAngleX;
                upperArmL.rotateAngleX += -((float) Math.PI / 2F) + head.rotateAngleX;
                upperArmR.rotateAngleZ += MathHelper.cos(ticks * 0.09F) * 0.05F + 0.05F;
                upperArmL.rotateAngleZ -= MathHelper.cos(ticks * 0.09F) * 0.05F + 0.05F;
                upperArmR.rotateAngleX += MathHelper.sin(ticks * 0.067F) * 0.05F;
                upperArmL.rotateAngleX -= MathHelper.sin(ticks * 0.067F) * 0.05F;
            }

            if (isRiding)
            {
                upperArmR.rotateAngleX -= (float) Math.PI / 5F;
                upperArmL.rotateAngleX -= (float) Math.PI / 5F;
                upperLegR.rotateAngleX -= (float) Math.PI * 2F / 5F;
                upperLegL.rotateAngleX -= (float) Math.PI * 2F / 5F;

                upperLegR.rotateAngleY += (float) Math.PI / 10F;
                upperLegL.rotateAngleY -= (float) Math.PI / 10F;
            }

            if (wearingChest && wearingHead && wearingLegs)
            {
                boolean playerOnGround = onGround(player);

                if (playerOnGround || player.capabilities.isFlying)
                {
                    waist.rotationPointY -= 3F;

                    if (!isRiding)
                    {
                        upperLegR.rotateAngleY += 0.2;
                        upperLegL.rotateAngleY -= 0.2;
                    }

                    upperLegR.rotateAngleX -= 0.1;
                    upperLegL.rotateAngleX -= 0.1;
                    lowerLegL.rotateAngleX += 0.1;
                    lowerLegR.rotateAngleX += 0.1;
                    lowerArmL.rotateAngleX -= 0.1;
                    lowerArmR.rotateAngleX -= 0.1;
                    head.rotateAngleX += 0.1;

                    bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, limbSwing, limbSwingAmount);
                    waist.rotationPointY += 1 * limbSwingAmount + 3;
                    walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    walk(torsobase, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    swing(torsobase, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
                    swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
                    walk(head, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);

                    swing(head, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
                    head.rotationPointX += 0.6 * globalDegree * limbSwingAmount * Math.cos(limbSwing * 0.5F * globalSpeed);

                    swing(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree, true, 0, -0.2F, limbSwing, limbSwingAmount);
                    swing(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree, true, 0, 0.2F, limbSwing, limbSwingAmount);

                    walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
                    walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
                    walk(lowerLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, limbSwing, limbSwingAmount);
                    walk(lowerLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, limbSwing, limbSwingAmount);
                    walk(upperArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    walk(upperArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);
                    walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);

                    walk(fronttorso1, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
                    walk(torsobase, 0.08F, 0.15F, false, 1, 0, ticks, 1F);
                    walk(head, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
                    walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
                    walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticks, 1F);

                    flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
                    flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticks, 1F);
                    walk(lowerArmR, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
                    walk(lowerArmL, 0.08F, 0.1F, true, 1, 0, ticks, 1F);

                    if (player.isSneaking())
                    {
                        waist.rotationPointY -= limbSwingAmount;
                        waist.rotateAngleX += 0.35F;
                        upperLegR.rotateAngleX -= 1F;
                        upperLegL.rotateAngleX -= 1F;
                        upperLegR.rotateAngleY += 0.1F;
                        upperLegL.rotateAngleY -= 0.1F;
                        lowerLegR.rotateAngleX += 1F;
                        lowerLegL.rotateAngleX += 1F;
                        lowerLegR.rotationPointY += 2F;
                        lowerLegL.rotationPointY += 2F;
                        upperArmR.rotateAngleX -= 0.35F;
                        upperArmL.rotateAngleX -= 0.35F;
                        upperArmR.rotateAngleY += 0.15F;
                        upperArmL.rotateAngleY -= 0.15F;
                        upperArmR.rotateAngleZ += 0.05F;
                        upperArmL.rotateAngleZ -= 0.05F;
                        footbaseR.rotateAngleX -= 0.35F;
                        footbaseL.rotateAngleX -= 0.35F;
                        head.rotateAngleX -= 0.35F;
                    }
                }
                else if (t == 20)
                {
                    double motionY = TFRenderHelper.getMotionY(player);

                    float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
                    float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));

                    waist.rotateAngleX += 0.2 * limbSwingAmount * backwardInverter;

                    fronttorso1.rotateAngleX += 0.2 * upwardPose;
                    torsobase.rotateAngleX -= 0.4 * upwardPose;
                    head.rotateAngleX += 0.6 * upwardPose;

                    upperArmR.rotateAngleX += 0.1 * upwardPose;
                    upperArmL.rotateAngleX += 0.1 * upwardPose;
                    upperArmR.rotateAngleZ -= 0.1 * upwardPose;
                    upperArmL.rotateAngleZ += 0.1 * upwardPose;
                    lowerArmR.rotateAngleX += 0.2 * upwardPose;
                    lowerArmL.rotateAngleX += 0.2 * upwardPose;

                    upperLegR.rotateAngleX += 0.2 * upwardPose;
                    upperLegL.rotateAngleX -= 1 * upwardPose;
                    lowerLegR.rotateAngleX += 0.3 * upwardPose;
                    lowerLegL.rotateAngleX += 1.5 * upwardPose;

                    walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, limbSwing, limbSwingAmount);
                    walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, limbSwing, limbSwingAmount);
                    walk(lowerLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, limbSwing, limbSwingAmount);
                    walk(lowerLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, limbSwing, limbSwingAmount);

                    waist.rotateAngleX -= 0.2 * downwardPose;
                    fronttorso1.rotateAngleX += 0.3 * downwardPose;
                    torsobase.rotateAngleX += 0.3 * downwardPose;
                    head.rotateAngleX += 0.3 * downwardPose;
                    upperLegR.rotateAngleX -= 1.2 * downwardPose;
                    upperLegL.rotateAngleX -= 0.2 * downwardPose;
                    lowerLegR.rotateAngleX += 2 * downwardPose;
                    lowerLegL.rotateAngleX += 0.5 * downwardPose;
                    upperArmR.rotateAngleZ += 0.5F * downwardPose;
                    upperArmL.rotateAngleZ -= 0.5F * downwardPose;
                    lowerArmR.rotateAngleX -= 1 * downwardPose;
                    lowerArmL.rotateAngleX -= 1 * downwardPose;
                }
            }
            else
            {
                waist.rotationPointY += 1;
                upperArmL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 2;
                upperArmR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 2;

                lowerArmL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 4;
                lowerArmR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 4;

                upperLegR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 2;
                upperLegL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 2;

                waist.rotationPointY -= 0.8F;

                if (isSneak)
                {
                    waist.rotationPointY -= 0.8F;
                    waist.rotateAngleX += 0.4F;
                    waist.rotationPointZ += 3F;
                    upperArmR.rotateAngleX -= 0.1F;
                    upperArmL.rotateAngleX -= 0.1F;

                    if (wearingChest)
                    {
                        head.rotateAngleX -= 0.4F;
                        upperLegR.rotateAngleX -= 0.4F;
                        upperLegL.rotateAngleX -= 0.4F;
                    }
                    else
                    {
                        upperLegL.rotationPointZ += 5F;
                        upperLegL.rotationPointY -= 0.8F;
                        upperLegR.rotationPointZ += 5F;
                        upperLegR.rotationPointY -= 0.8F;
                    }
                }
            }

            if (t != 20)
            {
                float f = 20 - t;
                float f1 = f / 20;
                float f2 = 1 - f1;

                setToInitPose();
                rotateTo(waist, vehicleBase, f);
                setRotateAngle(kneeR1, 0, 0, 0);
                setRotateAngle(kneeL1, 0, 0, 0);
                setRotateAngle(lowerLegR, 0, 0, 0);
                setRotateAngle(lowerLegL, 0, 0, 0);
                setRotateAngle(upperLegR, 0, 0, 0);
                setRotateAngle(upperLegL, 0, 0, 0);
                lowerLegR.rotateAngleX = 0.24434609527920614F + (pi - 0.24434609527920614F) * f1;
                lowerLegL.rotateAngleX = 0.24434609527920614F + (pi - 0.24434609527920614F) * f1;
                lowerLegR.setRotationPoint(-1 + f1 / 2.1F, 0.4F + 2.5F * f1, -1 + 2.5F * f1);
                lowerLegL.setRotationPoint(1 - f1 / 2.1F, 0.4F + 2.5F * f1, -1 + 2.5F * f1);
                footbaseR.rotateAngleX = -0.24434609527920614F + f1 * 1.7F;
                footbaseR.rotationPointZ = 0.7F - 2.5F * f1;
                footbaseL.rotateAngleX = -0.24434609527920614F + f1 * 1.7F;
                footbaseL.rotationPointZ = 0.7F - 2.5F * f1;
                head.rotateAngleX = pi * f1;
                footbaseR.rotationPointX = f1;
                footbaseL.rotationPointX = -f1;
                lowerlegR7.setRotationPoint(0.5F, -0.2F + f1 * 1.25F, -1 + 3 * f1);
                lowerlegL7.setRotationPoint(-0.5F, -0.2F + f1 * 1.25F, -1 + 3 * f1);
                lowerlegR6.setRotationPoint(1 - 2 * f1, 2 * f1, 2 + f1 / 2);
                lowerlegR6.rotateAngleY = -pi / 2 * f1;
                lowerlegR6.rotateAngleX = 0.45378560551852565F * f2;
                lowerlegL6.setRotationPoint(-1 + 2 * f1, 2 * f1, 2 + f1 / 2);
                lowerlegL6.rotateAngleY = pi / 2 * f1;
                lowerlegL6.rotateAngleX = -0.45378560551852565F * f2;
                backplate2.setRotationPoint(f1 * -2.01F, -3 + 4.5F * f1, 3);
                setRotateAngle(backplate2, 0.017453292519943295F * f2, 0.12217304763960307F * f2 - f1 * pi / 2, -0.10471975511965977F * f2);
                backplate1.setRotationPoint(f1 * 2.01F, -3 + 4.5F * f1, 3);
                setRotateAngle(backplate1, 0.017453292519943295F * f2, 0.12217304763960307F * f2 + f1 * pi / 2, -0.10471975511965977F * f2);
                fistL.rotateAngleZ = f1 * pi / 2;
                setRotateAngle(lowerArmL, -0.5235987755982988F * f2, 0.06981317007977318F * f2, 0.12217304763960307F * f2);
                setRotateAngle(upperArmL, 0.22689280275926282F * f2, 0, 0);
                setRotateAngle(armbaseL1, 0, -0.10471975511965977F * f2 - 1.3F * f1, -0.17453292519943295F * f2);
                armbaseL1.setRotationPoint(3 - f1 * 1.8F, -2 + f1 * 2.5F, 1);
                lowerArmL.setRotationPoint(1 + f1 / 5, 3.3F - 1.75F * f1, -0.6F);
                fistR.rotateAngleZ = f1 * -pi / 2;
                setRotateAngle(lowerArmR, -0.5235987755982988F * f2, -0.06981317007977318F * f2, -0.12217304763960307F * f2);
                setRotateAngle(upperArmR, 0.22689280275926282F * f2, 0, 0);
                setRotateAngle(armbaseR1, 0, 0.10471975511965977F * f2 + 1.3F * f1, 0.17453292519943295F * f2);
                armbaseR1.setRotationPoint(-3 + f1 * 2F, -2 + f1 * 2.5F, 1);
                lowerArmR.setRotationPoint(-f1 / 5, 3.3F - 1.75F * f1, -0.6F);
                waist1.rotationPointZ += f1;
                waist2.rotationPointZ += f1;
                rotateTo(frontR1, vehiclefrontR1, f);
                rotateTo(frontL1, vehiclefrontL1, f);
                rotateTo(hood1, vehiclehood1, f);
                frontR1.rotateAngleX -= pi / 2;
                frontL1.rotateAngleX -= pi / 2;
                hood1.rotateAngleX -= pi / 2;
                frontR1.rotationPointZ += 1.75F * f1;
                frontL1.rotationPointZ += 1.75F * f1;
                hood1.setRotationPoint(0, -0.5F - 2.2F * f1, -1.8F + 3.2F * f1);
                shoulderpadR.setRotationPoint(-2 - f1 * 1.5F, -3 + f1 * 2, 1 - f1 * 0.7F);
                shoulderpadL.setRotationPoint(2 + f1 * 1.5F, -3 + f1 * 2, 1 - f1 * 0.7F);
                setRotateAngle(shoulderpadR, 0.20943951023931953F * f2, 0, 1.064650843716541F * f2);
                setRotateAngle(shoulderpadL, -0.20943951023931953F * f2, 0, 1.064650843716541F * f2);
            }

            for (ModelRenderer modelRenderer : new ModelRenderer[]{vehicleWheelR, vehicleWheelL, vehicleWheelBackR, vehicleWheelBackL})
            {
                VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

                if (transformedPlayer != null)
                {
                    float wheelSpinSpeed = (transformedPlayer.getForwardVelocity() < 0 ? -limbSwing : limbSwing) * 0.8F;
                    modelRenderer.rotateAngleX = wheelSpinSpeed;
                }
            }

            if (t == 0)
            {
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

            if (holdingSniper) // Always put special item holding animations at the bottom, otherwise other animations like i.e. walking will override it!
            {
                ModelRenderer head = wearingHead ? this.head : bipedHead;

                upperArmR.rotateAngleX = -0.3F;
                lowerArmR.rotateAngleX = -1;
                lowerArmR.rotateAngleY = -0.5F;
                lowerArmR.rotateAngleZ = -0.3F;
                upperArmL.rotateAngleX = -0.8F;
                upperArmL.rotateAngleZ = 0.3F;
                lowerArmL.rotateAngleX = -0.3F;
                lowerArmL.rotateAngleY = 0.7F;
                lowerArmL.rotateAngleZ = 0.7F;
            }
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderArmorPiece(int armorPiece)
    {
        setToInitPose();

        if (armorPiece == 0)
        {
        	GL11.glTranslatef(0, 0.15F, 0);
            head.render(0.0625F);
        }
        else if (armorPiece == 1)
        {
        	GL11.glTranslatef(0, -0.2F, 0);
            upperLegL.showModel = false;
            upperLegR.showModel = false;
            head.showModel = false;
            waist.render(0.0625F);
            upperLegL.showModel = true;
            upperLegR.showModel = true;
            head.showModel = true;
        }
        else if (armorPiece == 2)
        {
        	GL11.glTranslatef(0, 0.075F, 0);
            footbaseL.showModel = false;
            footbaseR.showModel = false;
            upperLegL.render(0.0625F);
            upperLegR.render(0.0625F);
            footbaseL.showModel = true;
            footbaseR.showModel = true;
        }
        else if (armorPiece == 3)
        {
            GL11.glRotatef(12, 1, 0, 0);
            footbaseL.rotationPointX -= 2.5F;
            footbaseR.rotationPointX += 2.5F;
            footbaseL.rotateAngleX += 0.2F;
            footbaseL.rotateAngleY += 0.2F;
            footbaseR.rotateAngleX += 0.2F;
            footbaseR.rotateAngleY -= 0.2F;
            footbaseL.render(0.0625F);
            footbaseR.render(0.0625F);
        }
    }
}
