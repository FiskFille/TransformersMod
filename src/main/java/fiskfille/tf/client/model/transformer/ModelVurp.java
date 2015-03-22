package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.item.ItemVurpsSniper;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ModelVurp extends MowzieModelBase
{
    public ModelRenderer vehicleBase;
    public ModelRenderer vehicleWaist1;
    public ModelRenderer vehicleWaist2;
    public ModelRenderer vehicleWaistConnector1;
    public ModelRenderer vehicleTorsoconnector1;
    public ModelRenderer vehicleUpperlegR1;
    public ModelRenderer vehicleUpperlegL1;
    public ModelRenderer vehicleRplate1;
    public ModelRenderer vehicleLplate1;
    public ModelRenderer vehicleRearplate;
    public ModelRenderer vehicleUpperlegR2;
    public ModelRenderer vehicleKneeR1;
    public ModelRenderer vehicleLowerlegR1;
    public ModelRenderer vehiclelowerlegR7;
    public ModelRenderer vehicleLowerlegR4;
    public ModelRenderer vehicleLowerlegR5;
    public ModelRenderer vehicleWheelBackR;
    public ModelRenderer vehicleupperlegL2;
    public ModelRenderer vehiclekneeL2;
    public ModelRenderer vehiclelowerlegL1;
    public ModelRenderer vehiclelowerlegL7;
    public ModelRenderer vehiclelowerlegL4;
    public ModelRenderer vehiclelowerlegL5;
    public ModelRenderer vehicleWheelBackL;
    public ModelRenderer vehicletorsobase;
    public ModelRenderer vehiclehood1;
    public ModelRenderer vehiclefrontR1;
    public ModelRenderer vehiclefrontL1;
    public ModelRenderer vehiclebackplate1;
    public ModelRenderer vehiclebackplate2;
    public ModelRenderer vehicleWheelR;
    public ModelRenderer vehicleWheelL;
    public ModelRenderer vehiclearmbaseR1;
    public ModelRenderer vehiclearmbaseL1;
    public ModelRenderer vehiclewindow1;
    public ModelRenderer vehiclebaseplate;
    public ModelRenderer vehiclehood2;
    public ModelRenderer vehiclehood3;
    public ModelRenderer vehiclehood4;
    public ModelRenderer vehiclefrontR2;
    public ModelRenderer vehiclefrontL2;
    public ModelRenderer vehicleupperarmR1;
    public ModelRenderer vehicleshoulderL1;
    public ModelRenderer vehiclelowerarmR1;
    public ModelRenderer vehiclelowerarmR2;
    public ModelRenderer vehicleUpperarmL1;
    public ModelRenderer vehicleshoulderL1_1;
    public ModelRenderer vehicleLowerarmL1;
    public ModelRenderer vehicleLowerarmL2;
    public ModelRenderer vehicleWindow2;
    public ModelRenderer vehiclerear1;
    public ModelRenderer vehicleRear2;

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
        this.textureWidth = 128;
        this.textureHeight = 128;

        this.waist1 = new MowzieModelRenderer(this, 0, 64);
        this.waist1.mirror = true;
        this.waist1.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.waist1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
        this.setRotateAngle(waist1, 0.0F, -0.12217304763960307F,
                -0.19198621771937624F);
        this.fistL = new MowzieModelRenderer(this, 72, 64);
        this.fistL.mirror = true;
        this.fistL.setRotationPoint(0.0F, 3.8F, -0.5F);
        this.fistL.addBox(-1.2F, -0.5F, -1.0F, 2, 2, 2);
        this.setRotateAngle(fistL, 0.0F, 0.0F, 0.12217304763960307F);
        this.lowerlegL4 = new MowzieModelRenderer(this, 8, 83);
        this.lowerlegL4.setRotationPoint(-0.3F, 4.0F, 0.5F);
        this.lowerlegL4.addBox(-0.6F, -2.0F, -2.0F, 1, 3, 2);
        this.setRotateAngle(lowerlegL4, 0.19198621771937624F,
                -0.2792526803190927F, 0.0F);
        this.torsoconnector1 = new MowzieModelRenderer(this, 35, 60);
        this.torsoconnector1.setRotationPoint(0.0F, 0.1F, -0.4F);
        this.torsoconnector1.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2);
        this.setRotateAngle(torsoconnector1, 0.0F, 0.7853981633974483F, 0.0F);
        this.lowerlegR7 = new MowzieModelRenderer(this, 14, 85);
        this.lowerlegR7.setRotationPoint(0.0F, -0.2F, -1.0F);
        this.lowerlegR7.addBox(-2.5F, 0.0F, -1.0F, 3, 2, 1);
        this.setRotateAngle(lowerlegR7, 0.4363323129985824F,
                0.017453292519943295F, 0.0F);
        this.shoulderL1 = new MowzieModelRenderer(this, 78, 60);
        this.shoulderL1.mirror = true;
        this.shoulderL1.setRotationPoint(2.3F, -0.2F, 0.4F);
        this.shoulderL1.addBox(-1.0F, -1.5F, -1.5F, 1, 3, 3);
        this.setRotateAngle(shoulderL1, 0.017453292519943295F,
                0.05235987755982988F, -0.20943951023931953F);
        this.torsodetail2 = new MowzieModelRenderer(this, 45, 78);
        this.torsodetail2.setRotationPoint(2.0F, 0.0F, 1.0F);
        this.torsodetail2.addBox(-1.5F, -1.0F, -1.0F, 2, 2, 2);
        this.setRotateAngle(torsodetail2, 0.13962634015954636F,
                -0.03490658503988659F, 0.15707963267948966F);
        this.shoulderpadR = new MowzieModelRenderer(this, 55, 68);
        this.shoulderpadR.setRotationPoint(-2.0F, -3.0F, 1.0F);
        this.shoulderpadR.addBox(-0.2F, -2.0F, -1.0F, 1, 2, 2);
        this.setRotateAngle(shoulderpadR, -0.20943951023931953F, 0.0F,
                -1.064650843716541F);
        this.frontL1 = new MowzieModelRenderer(this, 35, 77);
        this.frontL1.mirror = true;
        this.frontL1.setRotationPoint(1.8F, -2.1F, 1.4F);
        this.frontL1.addBox(-0.5F, -1.0F, -4.0F, 2, 2, 3);
        this.setRotateAngle(frontL1, 0.5410520681182421F,
                -0.03490658503988659F, -0.20943951023931953F);
        this.lowerlegL3 = new MowzieModelRenderer(this, 0, 85);
        this.lowerlegL3.mirror = true;
        this.lowerlegL3.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.lowerlegL3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.setRotateAngle(lowerlegL3, 0.0F, -0.296705972839036F, 0.0F);
        this.footL1 = new MowzieModelRenderer(this, 5, 92);
        this.footL1.mirror = true;
        this.footL1.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.footL1.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 3);
        this.setRotateAngle(footL1, 0.3839724354387525F, 0.0F, 0.0F);
        this.upperArmR = new MowzieModelRenderer(this, 74, 60);
        this.upperArmR.setRotationPoint(-1.5F, 0.0F, 1.0F);
        this.upperArmR.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1);
        this.setRotateAngle(upperArmR, 0.22689280275926282F, 0.0F, 0.0F);
        this.backplate1 = new MowzieModelRenderer(this, 51, 74);
        this.backplate1.setRotationPoint(0.0F, -3.0F, 3.0F);
        this.backplate1.addBox(0.0F, -1.0F, 0.0F, 3, 5, 1);
        this.setRotateAngle(backplate1, 0.017453292519943295F,
                0.12217304763960307F, -0.10471975511965977F);
        this.lowerlegR2 = new MowzieModelRenderer(this, 10, 75);
        this.lowerlegR2.setRotationPoint(0.5F, 0.0F, 0.0F);
        this.lowerlegR2.addBox(0.0F, 0.0F, 0.0F, 1, 6, 2);
        this.setRotateAngle(lowerlegR2, -0.33161255787892263F, 0.0F,
                -0.03490658503988659F);
        this.upperlegR2 = new MowzieModelRenderer(this, 6, 72);
        this.upperlegR2.setRotationPoint(-2.0F, -0.5F, -0.5F);
        this.upperlegR2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
        this.setRotateAngle(upperlegR2, 0.0F, 0.05235987755982988F,
                -0.03490658503988659F);
        this.upperLegL = new MowzieModelRenderer(this, 0, 71);
        this.upperLegL.setRotationPoint(1.0F, 2.0F, 0.0F);
        this.upperLegL.addBox(0.0F, -1.0F, -1.0F, 1, 4, 2);
        this.setRotateAngle(upperLegL, -0.148352986419518F,
                0.017453292519943295F, -0.12217304763960307F);
        this.fronttorso1 = new MowzieModelRenderer(this, 35, 73);
        this.fronttorso1.setRotationPoint(-2.0F, -2.0F, -0.5F);
        this.fronttorso1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2);
        this.setRotateAngle(fronttorso1, -1.1519173063162573F, 0.0F, 0.0F);
        this.upperLegR = new MowzieModelRenderer(this, 0, 71);
        this.upperLegR.setRotationPoint(-1.0F, 2.0F, 0.0F);
        this.upperLegR.addBox(-1.0F, -1.0F, -1.0F, 1, 4, 2);
        this.setRotateAngle(upperLegR, -0.148352986419518F,
                -0.017453292519943295F, 0.12217304763960307F);
        this.kneeR1 = new MowzieModelRenderer(this, 10, 71);
        this.kneeR1.setRotationPoint(0.0F, 2.7F, 0.0F);
        this.kneeR1.addBox(-2.0F, 0.0F, -1.0F, 2, 2, 2);
        this.setRotateAngle(kneeR1, 0.148352986419518F, 0.017453292519943295F,
                -0.12217304763960307F);
        this.shoulderR1 = new MowzieModelRenderer(this, 78, 60);
        this.shoulderR1.setRotationPoint(-2.3F, -0.2F, 0.4F);
        this.shoulderR1.addBox(0.0F, -1.5F, -1.5F, 1, 3, 3);
        this.setRotateAngle(shoulderR1, 0.017453292519943295F,
                -0.05235987755982988F, 0.20943951023931953F);
        this.footR1 = new MowzieModelRenderer(this, 5, 92);
        this.footR1.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.footR1.addBox(-1.5F, -0.5F, -2.0F, 3, 1, 3);
        this.setRotateAngle(footR1, 0.3839724354387525F, 0.0F, 0.0F);
        this.ribs1 = new MowzieModelRenderer(this, 35, 82);
        this.ribs1.setRotationPoint(0.0F, -1.8F, 0.0F);
        this.ribs1.addBox(-1.0F, 0.0F, -2.0F, 2, 1, 4);
        this.setRotateAngle(ribs1, 0.0F, 0.7853981633974483F, 0.0F);
        this.headback2 = new MowzieModelRenderer(this, 40, 98);
        this.headback2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headback2.addBox(0.0F, 0.0F, -2.0F, 1, 1, 3);
        this.setRotateAngle(headback2, 0.10471975511965977F,
                -0.08726646259971647F, 0.12217304763960307F);
        this.armbaseL1 = new MowzieModelRenderer(this, 64, 60);
        this.armbaseL1.mirror = true;
        this.armbaseL1.setRotationPoint(3.0F, -2.0F, 1.0F);
        this.armbaseL1.addBox(-0.3F, -1.0F, -1.0F, 2, 2, 3);
        this.setRotateAngle(armbaseL1, 0.0F, -0.10471975511965977F,
                -0.17453292519943295F);
        this.armbaseR1 = new MowzieModelRenderer(this, 64, 60);
        this.armbaseR1.setRotationPoint(-3.0F, -2.0F, 1.0F);
        this.armbaseR1.addBox(-1.7F, -1.0F, -1.0F, 2, 2, 3);
        this.setRotateAngle(armbaseR1, 0.0F, 0.10471975511965977F,
                0.17453292519943295F);
        this.lowerarmL2 = new MowzieModelRenderer(this, 74, 68);
        this.lowerarmL2.mirror = true;
        this.lowerarmL2.setRotationPoint(0.9F, 0.8F, -1.6F);
        this.lowerarmL2.addBox(-1.0F, 0.0F, -2.0F, 1, 3, 2);
        this.setRotateAngle(lowerarmL2, 0.0F, 1.117010721276371F, 0.0F);
        this.ribs2 = new MowzieModelRenderer(this, 47, 80);
        this.ribs2.setRotationPoint(0.0F, -3.5F, 0.0F);
        this.ribs2.addBox(-0.9F, 0.0F, -3.0F, 2, 1, 6);
        this.setRotateAngle(ribs2, 0.0F, 0.7853981633974483F, 0.0F);
        this.lowerlegR6 = new MowzieModelRenderer(this, 15, 82);
        this.lowerlegR6.setRotationPoint(1.0F, 0.0F, 2.0F);
        this.lowerlegR6.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 1);
        this.setRotateAngle(lowerlegR6, -0.45378560551852565F, 0.0F, 0.0F);
        this.lowerLegL = new MowzieModelRenderer(this, 0, 77);
        this.lowerLegL.mirror = true;
        this.lowerLegL.setRotationPoint(1.0F, 0.4F, -1.0F);
        this.lowerLegL.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2);
        this.setRotateAngle(lowerLegL, 0.24434609527920614F, 0.0F, 0.0F);
        this.lowerlegR5 = new MowzieModelRenderer(this, 16, 75);
        this.lowerlegR5.setRotationPoint(-0.5F, 0.0F, 0.0F);
        this.lowerlegR5.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 1);
        this.setRotateAngle(lowerlegR5, -0.17453292519943295F,
                0.05235987755982988F, 0.0F);
        this.lowerlegR3 = new MowzieModelRenderer(this, 0, 85);
        this.lowerlegR3.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.lowerlegR3.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 1);
        this.setRotateAngle(lowerlegR3, 0.0F, 0.296705972839036F, -0.0F);
        this.waist = new MowzieModelRenderer(this, 0, 60);
        this.waist.setRotationPoint(0.0F, 11.1F, 1.0F);
        this.waist.addBox(-2.0F, 0.0F, -1.0F, 4, 1, 3);
        this.lowerArmR = new MowzieModelRenderer(this, 64, 65);
        this.lowerArmR.mirror = true;
        this.lowerArmR.setRotationPoint(0.0F, 3.3F, -0.6F);
        this.lowerArmR.addBox(-0.9F, -0.2F, -1.6F, 2, 4, 3);
        this.setRotateAngle(lowerArmR, -0.5235987755982988F,
                -0.06981317007977318F, -0.12217304763960307F);
        this.waist3 = new MowzieModelRenderer(this, 12, 64);
        this.waist3.setRotationPoint(0.0F, -0.2F, -0.5F);
        this.waist3.addBox(-3.0F, 0.0F, 0.0F, 6, 2, 2);
        this.setRotateAngle(waist3, -0.24434609527920614F, 0.0F, 0.0F);
        this.fistR = new MowzieModelRenderer(this, 72, 64);
        this.fistR.setRotationPoint(0.0F, 3.8F, -0.5F);
        this.fistR.addBox(-0.8F, -0.5F, -1.0F, 2, 2, 2);
        this.setRotateAngle(fistR, 0.0F, 0.0F, -0.12217304763960307F);
        this.visor1 = new MowzieModelRenderer(this, 43, 90);
        this.visor1.setRotationPoint(-1.0F, -2.9F, -1.0F);
        this.visor1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.setRotateAngle(visor1, -0.17453292519943295F, 0.0F, 0.0F);
        this.footL2 = new MowzieModelRenderer(this, 0, 96);
        this.footL2.mirror = true;
        this.footL2.setRotationPoint(-1.5F, 0.0F, -2.0F);
        this.footL2.addBox(0.0F, -0.5F, -1.0F, 3, 1, 4);
        this.setRotateAngle(footL2, -0.33161255787892263F, 0.0F, 0.0F);
        this.backplate2 = new MowzieModelRenderer(this, 51, 74);
        this.backplate2.mirror = true;
        this.backplate2.setRotationPoint(0.0F, -3.0F, 3.0F);
        this.backplate2.addBox(-3.0F, -1.0F, 0.0F, 3, 5, 1);
        this.setRotateAngle(backplate2, 0.017453292519943295F,
                -0.12217304763960307F, 0.12217304763960307F);
        this.neck1 = new MowzieModelRenderer(this, 35, 90);
        this.neck1.setRotationPoint(0.0F, -1.7F, -0.5F);
        this.neck1.addBox(-1.0F, -2.0F, 0.0F, 2, 1, 2);
        this.visor4 = new MowzieModelRenderer(this, 45, 96);
        this.visor4.setRotationPoint(3.0F, 1.0F, 0.0F);
        this.visor4.addBox(-1.0F, 0.0F, -2.0F, 1, 1, 2);
        this.setRotateAngle(visor4, 0.10471975511965977F, 0.2792526803190927F,
                0.0F);
        this.footR2 = new MowzieModelRenderer(this, 0, 96);
        this.footR2.setRotationPoint(1.5F, 0.0F, -2.0F);
        this.footR2.addBox(-3.0F, -0.5F, -1.0F, 3, 1, 4);
        this.setRotateAngle(footR2, -0.33161255787892263F, 0.0F, 0.0F);
        this.headback3 = new MowzieModelRenderer(this, 40, 98);
        this.headback3.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.headback3.addBox(-1.0F, 0.0F, -2.0F, 1, 1, 3);
        this.setRotateAngle(headback3, 0.10471975511965977F,
                0.08726646259971647F, -0.12217304763960307F);
        this.waistconnector1 = new MowzieModelRenderer(this, 4, 64);
        this.waistconnector1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.waistconnector1.addBox(-1.0F, -0.1F, -2.3F, 2, 3, 4);
        this.setRotateAngle(waistconnector1, 0.008726646259971648F, 0.0F, 0.0F);
        this.headback1 = new MowzieModelRenderer(this, 35, 97);
        this.headback1.setRotationPoint(-1.5F, -3.0F, 1.0F);
        this.headback1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
        this.setRotateAngle(headback1, -0.10471975511965977F, 0.0F, 0.0F);
        this.footbaseR = new MowzieModelRenderer(this, 0, 91);
        this.footbaseR.setRotationPoint(0.0F, 4.0F, 0.7F);
        this.footbaseR.addBox(-1.0F, 1.0F, -1.0F, 2, 2, 2);
        this.setRotateAngle(footbaseR, -0.24434609527920614F, 0.0F, 0.0F);
        this.upperArmL = new MowzieModelRenderer(this, 74, 60);
        this.upperArmL.mirror = true;
        this.upperArmL.setRotationPoint(0.5F, 0.0F, 1.0F);
        this.upperArmL.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1);
        this.setRotateAngle(upperArmL, 0.22689280275926282F, 0.0F, 0.0F);
        this.lowerarmR2 = new MowzieModelRenderer(this, 74, 68);
        this.lowerarmR2.setRotationPoint(-0.9F, 0.8F, -1.6F);
        this.lowerarmR2.addBox(0.0F, 0.0F, -2.0F, 1, 3, 2);
        this.setRotateAngle(lowerarmR2, 0.0F, -1.117010721276371F, 0.0F);
        this.footL3 = new MowzieModelRenderer(this, 0, 101);
        this.footL3.mirror = true;
        this.footL3.setRotationPoint(0.0F, 3.4F, 0.4F);
        this.footL3.addBox(-1.0F, -1.2F, -2.0F, 2, 2, 4);
        this.setRotateAngle(footL3, 0.0F, -0.008726646259971648F, 0.0F);
        this.lowerlegL6 = new MowzieModelRenderer(this, 15, 82);
        this.lowerlegL6.mirror = true;
        this.lowerlegL6.setRotationPoint(-1.0F, 0.0F, 2.0F);
        this.lowerlegL6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.setRotateAngle(lowerlegL6, -0.45378560551852565F, 0.0F, 0.0F);
        this.frontR2 = new MowzieModelRenderer(this, 48, 70);
        this.frontR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontR2.addBox(-1.5F, -2.0F, -3.7F, 2, 1, 3);
        this.setRotateAngle(frontR2, 0.24434609527920614F, 0.0F,
                -0.017453292519943295F);
        this.visor2 = new MowzieModelRenderer(this, 44, 93);
        this.visor2.setRotationPoint(0.5F, 0.1F, 0.0F);
        this.visor2.addBox(0.0F, 0.0F, -2.0F, 2, 1, 2);
        this.setRotateAngle(visor2, 0.10471975511965977F, 0.0F, 0.0F);
        this.shoulderpadL = new MowzieModelRenderer(this, 55, 68);
        this.shoulderpadL.mirror = true;
        this.shoulderpadL.setRotationPoint(2.0F, -3.0F, 1.0F);
        this.shoulderpadL.addBox(-0.8F, -2.0F, -1.0F, 1, 2, 2);
        this.setRotateAngle(shoulderpadL, -0.20943951023931953F, 0.0F,
                1.064650843716541F);
        this.waist2 = new MowzieModelRenderer(this, 0, 64);
        this.waist2.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.waist2.addBox(-3.0F, 0.0F, 0.0F, 3, 2, 1);
        this.setRotateAngle(waist2, 0.0F, 0.12217304763960307F,
                0.19198621771937624F);
        this.kneeL1 = new MowzieModelRenderer(this, 10, 71);
        this.kneeL1.setRotationPoint(0.0F, 2.7F, 0.0F);
        this.kneeL1.addBox(0.0F, 0.0F, -1.0F, 2, 2, 2);
        this.setRotateAngle(kneeL1, 0.148352986419518F, -0.017453292519943295F,
                0.12217304763960307F);
        this.visor3 = new MowzieModelRenderer(this, 45, 96);
        this.visor3.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.visor3.addBox(0.0F, 0.0F, -2.0F, 1, 1, 2);
        this.setRotateAngle(visor3, 0.10471975511965977F, -0.2792526803190927F,
                0.0F);
        this.lowerlegL7 = new MowzieModelRenderer(this, 14, 85);
        this.lowerlegL7.mirror = true;
        this.lowerlegL7.setRotationPoint(0.0F, -0.2F, -1.0F);
        this.lowerlegL7.addBox(-0.5F, 0.0F, -1.0F, 3, 2, 1);
        this.setRotateAngle(lowerlegL7, 0.4363323129985824F,
                -0.017453292519943295F, 0.0F);
        this.hood4 = new MowzieModelRenderer(this, 51, 65);
        this.hood4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hood4.addBox(-1.5F, -0.1F, -1.4F, 3, 2, 1);
        this.setRotateAngle(hood4, 0.8656833089891874F, 0.0F, 0.0F);
        this.hood3 = new MowzieModelRenderer(this, 47, 65);
        this.hood3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hood3.addBox(-1.8F, -3.3F, -1.0F, 1, 4, 1);
        this.setRotateAngle(hood3, 0.0F, -0.0017453292519943296F,
                -0.19198621771937624F);
        this.connector2 = new MowzieModelRenderer(this, 43, 61);
        this.connector2.mirror = true;
        this.connector2.setRotationPoint(1.5F, 0.0F, 2.0F);
        this.connector2.addBox(-1.0F, -0.1F, -0.5F, 1, 4, 1);
        this.setRotateAngle(connector2, -0.13962634015954636F, 0.0F,
                0.13962634015954636F);
        this.hood2 = new MowzieModelRenderer(this, 47, 65);
        this.hood2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hood2.addBox(0.8F, -3.3F, -1.0F, 1, 4, 1);
        this.setRotateAngle(hood2, 0.0F, 0.0017453292519943296F,
                0.19198621771937624F);
        this.frontR1 = new MowzieModelRenderer(this, 35, 77);
        this.frontR1.setRotationPoint(-1.8F, -2.1F, 1.4F);
        this.frontR1.addBox(-1.5F, -1.0F, -4.0F, 2, 2, 3);
        this.setRotateAngle(frontR1, 0.5410520681182421F, 0.03490658503988659F,
                0.20943951023931953F);
        this.lowerlegL2 = new MowzieModelRenderer(this, 10, 75);
        this.lowerlegL2.mirror = true;
        this.lowerlegL2.setRotationPoint(-0.5F, 0.0F, 0.0F);
        this.lowerlegL2.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 2);
        this.setRotateAngle(lowerlegL2, -0.33161255787892263F, 0.0F,
                0.03490658503988659F);
        this.upperarmL2 = new MowzieModelRenderer(this, 74, 60);
        this.upperarmL2.setRotationPoint(0.0F, -0.6F, -1.3F);
        this.upperarmL2.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1);
        this.torsobase = new MowzieModelRenderer(this, 35, 66);
        this.torsobase.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.torsobase.addBox(-2.0F, -3.0F, -0.7F, 4, 3, 4);
        this.setRotateAngle(torsobase, 0.0F, -0.7853981633974483F, 0.0F);
        this.torsodetail1 = new MowzieModelRenderer(this, 45, 78);
        this.torsodetail1.setRotationPoint(-2.0F, 0.0F, 1.0F);
        this.torsodetail1.addBox(-0.5F, -1.0F, -1.0F, 2, 2, 2);
        this.setRotateAngle(torsodetail1, 0.13962634015954636F,
                0.03490658503988659F, -0.15707963267948966F);
        this.lowerlegL5 = new MowzieModelRenderer(this, 16, 75);
        this.lowerlegL5.mirror = true;
        this.lowerlegL5.setRotationPoint(0.5F, 0.0F, 0.0F);
        this.lowerlegL5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
        this.setRotateAngle(lowerlegL5, -0.17453292519943295F,
                -0.05235987755982988F, 0.0F);
        this.upperarmR2 = new MowzieModelRenderer(this, 74, 60);
        this.upperarmR2.mirror = true;
        this.upperarmR2.setRotationPoint(0.0F, -0.6F, -1.3F);
        this.upperarmR2.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1);
        this.hood1 = new MowzieModelRenderer(this, 47, 60);
        this.hood1.setRotationPoint(0.0F, -0.5F, -1.8F);
        this.hood1.addBox(-1.5F, -3.0F, -1.0F, 3, 4, 1);
        this.setRotateAngle(hood1, -0.5061454830783556F, 0.0F, 0.0F);
        this.lowerArmL = new MowzieModelRenderer(this, 64, 65);
        this.lowerArmL.setRotationPoint(1.0F, 3.3F, -0.6F);
        this.lowerArmL.addBox(-1.1F, -0.2F, -1.6F, 2, 4, 3);
        this.setRotateAngle(lowerArmL, -0.5235987755982988F,
                0.06981317007977318F, 0.12217304763960307F);
        this.upperlegL2 = new MowzieModelRenderer(this, 6, 72);
        this.upperlegL2.setRotationPoint(2.0F, -0.5F, -0.5F);
        this.upperlegL2.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1);
        this.setRotateAngle(upperlegL2, 0.0F, -0.05235987755982988F,
                0.03490658503988659F);
        this.lowerlegR4 = new MowzieModelRenderer(this, 8, 83);
        this.lowerlegR4.setRotationPoint(0.3F, 4.0F, 0.5F);
        this.lowerlegR4.addBox(-0.4F, -2.0F, -2.0F, 1, 3, 2);
        this.setRotateAngle(lowerlegR4, 0.19198621771937624F,
                0.2792526803190927F, 0.0F);
        this.head = new MowzieModelRenderer(this, 35, 93);
        this.head.setRotationPoint(0.0F, -1.9F, 0.6F);
        this.head.addBox(-1.5F, -1.0F, -1.5F, 3, 1, 3);
        this.frontL2 = new MowzieModelRenderer(this, 48, 70);
        this.frontL2.mirror = true;
        this.frontL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontL2.addBox(-0.5F, -2.0F, -3.7F, 2, 1, 3);
        this.setRotateAngle(frontL2, 0.24434609527920614F, 0.0F,
                0.017453292519943295F);
        this.footR3 = new MowzieModelRenderer(this, 0, 101);
        this.footR3.setRotationPoint(0.0F, 3.4F, 0.4F);
        this.footR3.addBox(-1.0F, -1.2F, -2.0F, 2, 2, 4);
        this.setRotateAngle(footR3, 0.0F, 0.008726646259971648F, 0.0F);
        this.connector1 = new MowzieModelRenderer(this, 43, 61);
        this.connector1.setRotationPoint(-1.5F, 0.0F, 2.0F);
        this.connector1.addBox(0.0F, -0.1F, -0.5F, 1, 4, 1);
        this.setRotateAngle(connector1, -0.13962634015954636F, 0.0F,
                -0.13962634015954636F);
        this.torsodetail3 = new MowzieModelRenderer(this, 55, 61);
        this.torsodetail3.setRotationPoint(-2.0F, -2.0F, 1.0F);
        this.torsodetail3.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2);
        this.setRotateAngle(torsodetail3, 0.0F, 0.13962634015954636F,
                0.33161255787892263F);
        this.footbaseL = new MowzieModelRenderer(this, 0, 91);
        this.footbaseL.mirror = true;
        this.footbaseL.setRotationPoint(0.0F, 4.0F, 0.7F);
        this.footbaseL.addBox(-1.0F, 1.0F, -1.0F, 2, 2, 2);
        this.setRotateAngle(footbaseL, -0.24434609527920614F, 0.0F, 0.0F);
        this.torsodetail4 = new MowzieModelRenderer(this, 55, 61);
        this.torsodetail4.setRotationPoint(2.0F, -2.0F, 1.0F);
        this.torsodetail4.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2);
        this.setRotateAngle(torsodetail4, 0.0F, -0.13962634015954636F,
                -0.33161255787892263F);
        this.lowerLegR = new MowzieModelRenderer(this, 0, 77);
        this.lowerLegR.setRotationPoint(-1.0F, 0.4F, -1.0F);
        this.lowerLegR.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2);
        this.setRotateAngle(lowerLegR, 0.24434609527920614F, 0.0F, 0.0F);

        this.footbaseR.addChild(this.footR3);
        this.torsobase.addChild(this.connector1);
        this.torsobase.addChild(this.torsodetail3);
        this.lowerLegL.addChild(this.footbaseL);
        this.torsobase.addChild(this.torsodetail4);
        this.kneeR1.addChild(this.lowerLegR);
        this.waist.addChild(this.waist1);
        this.lowerArmL.addChild(this.fistL);
        this.lowerLegL.addChild(this.lowerlegL4);
        this.waist.addChild(this.torsoconnector1);
        this.armbaseL1.addChild(this.shoulderL1);
        this.torsobase.addChild(this.torsodetail2);
        this.torsobase.addChild(this.shoulderpadR);
        this.torsobase.addChild(this.frontL1);
        this.lowerlegL2.addChild(this.lowerlegL3);
        this.footbaseL.addChild(this.footL1);
        this.armbaseR1.addChild(this.upperArmR);
        this.torsobase.addChild(this.backplate1);
        this.lowerLegR.addChild(this.lowerlegR2);
        this.upperLegR.addChild(this.upperlegR2);
        this.waistconnector1.addChild(this.upperLegL);
        this.torsobase.addChild(this.fronttorso1);
        this.waistconnector1.addChild(this.upperLegR);
        this.upperLegR.addChild(this.kneeR1);
        this.armbaseR1.addChild(this.shoulderR1);
        this.footbaseR.addChild(this.footR1);
        this.lowerLegR.addChild(this.footbaseR);
        this.torsoconnector1.addChild(this.ribs1);
        this.headback1.addChild(this.headback2);
        this.torsobase.addChild(this.armbaseL1);
        this.torsobase.addChild(this.armbaseR1);
        this.lowerArmL.addChild(this.lowerarmL2);
        this.torsoconnector1.addChild(this.ribs2);
        this.lowerLegR.addChild(this.lowerlegR6);
        this.kneeL1.addChild(this.lowerLegL);
        this.lowerLegR.addChild(this.lowerlegR5);
        this.lowerlegR2.addChild(this.lowerlegR3);
        this.upperArmR.addChild(this.lowerArmR);
        this.waist.addChild(this.waist3);
        this.lowerArmR.addChild(this.fistR);
        this.head.addChild(this.visor1);
        this.footL1.addChild(this.footL2);
        this.torsobase.addChild(this.backplate2);
        this.torsobase.addChild(this.neck1);
        this.headback1.addChild(this.visor4);
        this.footR1.addChild(this.footR2);
        this.headback1.addChild(this.headback3);
        this.waist.addChild(this.waistconnector1);
        this.head.addChild(this.headback1);
        this.armbaseL1.addChild(this.upperArmL);
        this.lowerArmR.addChild(this.lowerarmR2);
        this.footbaseL.addChild(this.footL3);
        this.lowerLegL.addChild(this.lowerlegL6);
        this.frontR1.addChild(this.frontR2);
        this.torsobase.addChild(this.shoulderpadL);
        this.hood1.addChild(this.hood2);
        this.torsobase.addChild(this.frontR1);
        this.upperArmL.addChild(this.upperarmL2);
        this.lowerLegL.addChild(this.lowerlegL2);
        this.torsoconnector1.addChild(this.torsobase);
        this.torsobase.addChild(this.torsodetail1);
        this.lowerLegL.addChild(this.lowerlegL5);
        this.upperArmR.addChild(this.upperarmR2);
        this.upperArmL.addChild(this.lowerArmL);
        this.torsobase.addChild(this.hood1);
        this.lowerLegR.addChild(this.lowerlegR4);
        this.upperLegL.addChild(this.upperlegL2);
        this.frontL1.addChild(this.frontL2);
        this.neck1.addChild(this.head);
        this.headback1.addChild(this.visor2);
        this.waist.addChild(this.waist2);
        this.upperLegL.addChild(this.kneeL1);
        this.headback1.addChild(this.visor3);
        this.kneeL1.addChild(this.lowerlegL7);
        this.kneeR1.addChild(this.lowerlegR7);
        this.hood1.addChild(this.hood4);
        this.hood1.addChild(this.hood3);
        this.torsobase.addChild(this.connector2);

        this.setInitPose();

        // this.parts = new MowzieModelRenderer[]{armbaseL1, armbaseR1,
        // backplate1, backplate2};

        this.vehiclebackplate2 = new ModelRenderer(this, 51, 74);
        this.vehiclebackplate2.mirror = true;
        this.vehiclebackplate2.setRotationPoint(-2.3F, -1.8F, 1.8F);
        this.vehiclebackplate2.addBox(-3.0F, -2.0F, 0.0F, 3, 5, 1);
        this.setRotateAngle(vehiclebackplate2, 1.5882496193148399F,
                0.017453292519943295F, -1.5707963267948966F);
        this.vehicleWaistConnector1 = new ModelRenderer(this, 4, 64);
        this.vehicleWaistConnector1.setRotationPoint(0.0F, 2.0F, -1.0F);
        this.vehicleWaistConnector1.addBox(-1.0F, -0.1F, -2.3F, 2, 3, 4);
        this.setRotateAngle(vehicleWaistConnector1, 1.5707963267948966F, 0.0F,
                0.0F);
        this.vehiclewindow1 = new ModelRenderer(this, 0, 7);
        this.vehiclewindow1.setRotationPoint(0.0F, -1.9F, -1.2F);
        this.vehiclewindow1.addBox(-2.5F, -0.2F, 0.0F, 5, 2, 3);
        this.setRotateAngle(vehiclewindow1, 0.33161255787892263F, 0.0F, 0.0F);
        this.vehicleLowerlegR1 = new ModelRenderer(this, 0, 77);
        this.vehicleLowerlegR1.setRotationPoint(-0.8F, 2.0F, 1.5F);
        this.vehicleLowerlegR1.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2);
        this.setRotateAngle(vehicleLowerlegR1, 3.141592653589793F, 0.0F,
                -0.017453292519943295F);
        this.vehiclelowerlegL1 = new ModelRenderer(this, 0, 77);
        this.vehiclelowerlegL1.mirror = true;
        this.vehiclelowerlegL1.setRotationPoint(0.8F, 2.0F, 1.5F);
        this.vehiclelowerlegL1.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 2);
        this.setRotateAngle(vehiclelowerlegL1, 3.141592653589793F, 0.0F,
                0.017453292519943295F);
        this.vehicleLowerarmL1 = new ModelRenderer(this, 64, 65);
        this.vehicleLowerarmL1.setRotationPoint(0.0F, 3.3F, 0.4F);
        this.vehicleLowerarmL1.addBox(-1.1F, -0.2F, -1.6F, 2, 4, 3);
        this.setRotateAngle(vehicleLowerarmL1, -3.141592653589793F, 0.0F, 0.0F);
        this.vehiclefrontL2 = new ModelRenderer(this, 48, 70);
        this.vehiclefrontL2.mirror = true;
        this.vehiclefrontL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehiclefrontL2.addBox(-0.5F, -2.0F, -3.7F, 2, 1, 3);
        this.setRotateAngle(vehiclefrontL2, 0.24434609527920614F, 0.0F,
                0.017453292519943295F);
        this.vehicleWaist2 = new ModelRenderer(this, 0, 64);
        this.vehicleWaist2.setRotationPoint(0.0F, 7.0F, 1.5F);
        this.vehicleWaist2.addBox(-3.0F, 0.0F, 0.0F, 3, 2, 1);
        this.setRotateAngle(vehicleWaist2, -1.5707963267948966F, 0.0F,
                -3.211405823669566F);
        this.vehicleupperarmR1 = new ModelRenderer(this, 74, 60);
        this.vehicleupperarmR1.setRotationPoint(-1.5F, 0.0F, 1.0F);
        this.vehicleupperarmR1.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1);
        this.setRotateAngle(vehicleupperarmR1, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehiclehood3 = new ModelRenderer(this, 47, 65);
        this.vehiclehood3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehiclehood3.addBox(-1.8F, -3.3F, -1.0F, 1, 4, 1);
        this.setRotateAngle(vehiclehood3, -0.005235987755982988F,
                0.003490658503988659F, -0.22165681500327983F);
        this.vehicleTorsoconnector1 = new ModelRenderer(this, 35, 60);
        this.vehicleTorsoconnector1.setRotationPoint(0.0F, 0.1F, -0.4F);
        this.vehicleTorsoconnector1.addBox(-1.2F, -4.0F, -0.8F, 2, 4, 2);
        this.setRotateAngle(vehicleTorsoconnector1, 0.0F, 0.7853981633974483F,
                0.0F);
        this.vehiclelowerlegL5 = new ModelRenderer(this, 16, 75);
        this.vehiclelowerlegL5.mirror = true;
        this.vehiclelowerlegL5.setRotationPoint(1.4F, 0.0F, 0.0F);
        this.vehiclelowerlegL5.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 1);
        this.setRotateAngle(vehiclelowerlegL5, -0.15707963267948966F,
                0.06981317007977318F, 0.05235987755982988F);
        this.vehiclehood2 = new ModelRenderer(this, 47, 65);
        this.vehiclehood2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehiclehood2.addBox(0.8F, -3.3F, -1.0F, 1, 4, 1);
        this.setRotateAngle(vehiclehood2, -0.005235987755982988F,
                0.003490658503988659F, 0.22165681500327983F);
        this.vehicleLowerlegR5 = new ModelRenderer(this, 16, 75);
        this.vehicleLowerlegR5.setRotationPoint(-1.4F, 0.0F, 0.0F);
        this.vehicleLowerlegR5.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
        this.setRotateAngle(vehicleLowerlegR5, -0.15707963267948966F,
                -0.06981317007977318F, -0.05235987755982988F);
        this.vehiclelowerlegL4 = new ModelRenderer(this, 8, 83);
        this.vehiclelowerlegL4.setRotationPoint(-0.3F, 2.5F, 0.9F);
        this.vehiclelowerlegL4.addBox(-0.5F, -2.0F, -2.0F, 1, 3, 2);
        this.setRotateAngle(vehiclelowerlegL4, 0.15707963267948966F,
                -0.10471975511965977F, 0.08726646259971647F);
        this.vehiclehood4 = new ModelRenderer(this, 51, 65);
        this.vehiclehood4.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.vehiclehood4.addBox(-1.5F, -0.7F, -1.4F, 3, 2, 1);
        this.setRotateAngle(vehiclehood4, 1.3526301702956054F, 0.0F, 0.0F);
        this.vehicleWindow2 = new ModelRenderer(this, 0, 24);
        this.vehicleWindow2.setRotationPoint(0.0F, -0.2F, 3.0F);
        this.vehicleWindow2.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 2);
        this.setRotateAngle(vehicleWindow2, -0.20943951023931953F, 0.0F, 0.0F);
        this.vehiclelowerlegR7 = new ModelRenderer(this, 14, 85);
        this.vehiclelowerlegR7.setRotationPoint(0.5F, 1.0F, 1.6F);
        this.vehiclelowerlegR7.addBox(-2.4F, -0.5F, -1.0F, 3, 2, 1);
        this.setRotateAngle(vehiclelowerlegR7, 0.6108652381980153F,
                -0.05235987755982988F, 0.0F);
        this.vehiclelowerarmR2 = new ModelRenderer(this, 74, 68);
        this.vehiclelowerarmR2.setRotationPoint(-1.5F, 3.5F, 0.0F);
        this.vehiclelowerarmR2.addBox(0.0F, -3.0F, -1.9F, 1, 3, 2);
        this.setRotateAngle(vehiclelowerarmR2, 0.03490658503988659F,
                -0.2617993877991494F, -0.10122909661567112F);
        this.vehicleRplate1 = new ModelRenderer(this, 0, 17);
        this.vehicleRplate1.setRotationPoint(0.0F, 0.4F, 0.3F);
        this.vehicleRplate1.addBox(-3.4F, -0.9F, -5.2F, 3, 1, 6);
        this.setRotateAngle(vehicleRplate1, 0.061086523819801536F,
                0.05235987755982988F, -0.017453292519943295F);
        this.vehiclefrontL1 = new ModelRenderer(this, 35, 77);
        this.vehiclefrontL1.mirror = true;
        this.vehiclefrontL1.setRotationPoint(1.8F, -0.1F, -0.9F);
        this.vehiclefrontL1.addBox(-0.5F, -1.0F, -4.0F, 2, 2, 3);
        this.vehiclebaseplate = new ModelRenderer(this, 13, 0);
        this.vehiclebaseplate.setRotationPoint(-2.5F, 0.1F, -4.5F);
        this.vehiclebaseplate.addBox(0.0F, 0.0F, 0.0F, 5, 1, 13);
        this.vehiclebackplate1 = new ModelRenderer(this, 51, 74);
        this.vehiclebackplate1.setRotationPoint(2.3F, -1.8F, 1.8F);
        this.vehiclebackplate1.addBox(0.0F, -2.0F, 0.0F, 3, 5, 1);
        this.setRotateAngle(vehiclebackplate1, 1.5882496193148399F,
                -0.017453292519943295F, 1.5707963267948966F);
        this.vehicleLplate1 = new ModelRenderer(this, 0, 17);
        this.vehicleLplate1.mirror = true;
        this.vehicleLplate1.setRotationPoint(0.0F, 0.4F, 0.3F);
        this.vehicleLplate1.addBox(0.4F, -0.9F, -5.2F, 3, 1, 6);
        this.setRotateAngle(vehicleLplate1, 0.061086523819801536F,
                -0.05235987755982988F, 0.017453292519943295F);
        this.vehiclelowerarmR1 = new ModelRenderer(this, 64, 65);
        this.vehiclelowerarmR1.mirror = true;
        this.vehiclelowerarmR1.setRotationPoint(1.0F, 3.3F, 0.4F);
        this.vehiclelowerarmR1.addBox(-0.9F, -0.2F, -1.6F, 2, 4, 3);
        this.setRotateAngle(vehiclelowerarmR1, -3.141592653589793F, 0.0F, 0.0F);
        this.vehiclerear1 = new ModelRenderer(this, 0, 0);
        this.vehiclerear1.setRotationPoint(0.0F, 0.3F, 4.0F);
        this.vehiclerear1.addBox(-2.5F, 0.1F, 0.0F, 5, 2, 5);
        this.setRotateAngle(vehiclerear1, -0.3141592653589793F, 0.0F, 0.0F);
        this.vehiclearmbaseR1 = new ModelRenderer(this, 64, 60);
        this.vehiclearmbaseR1.setRotationPoint(-1.0F, -0.4F, 1.0F);
        this.vehiclearmbaseR1.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 3);
        this.vehicleWaist1 = new ModelRenderer(this, 0, 64);
        this.vehicleWaist1.mirror = true;
        this.vehicleWaist1.setRotationPoint(0.0F, 7.0F, 1.5F);
        this.vehicleWaist1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
        this.setRotateAngle(vehicleWaist1, -1.5707963267948966F, 0.0F,
                3.211405823669566F);
        this.vehiclelowerlegL7 = new ModelRenderer(this, 14, 85);
        this.vehiclelowerlegL7.mirror = true;
        this.vehiclelowerlegL7.setRotationPoint(-0.5F, 1.0F, 1.6F);
        this.vehiclelowerlegL7.addBox(-0.6F, -0.5F, -1.0F, 3, 2, 1);
        this.setRotateAngle(vehiclelowerlegL7, 0.6108652381980153F,
                0.05235987755982988F, 0.0F);
        this.vehicleshoulderL1_1 = new ModelRenderer(this, 78, 60);
        this.vehicleshoulderL1_1.mirror = true;
        this.vehicleshoulderL1_1.setRotationPoint(2.3F, 0.0F, -1.6F);
        this.vehicleshoulderL1_1.addBox(-1.0F, -1.4F, -1.5F, 1, 3, 3);
        this.setRotateAngle(vehicleshoulderL1_1, -0.019198621771937624F, 0.0F,
                0.0F);
        this.vehicleshoulderL1 = new ModelRenderer(this, 78, 60);
        this.vehicleshoulderL1.setRotationPoint(-2.3F, 0.0F, -1.6F);
        this.vehicleshoulderL1.addBox(0.0F, -1.4F, -1.5F, 1, 3, 3);
        this.setRotateAngle(vehicleshoulderL1, -0.019198621771937624F, 0.0F,
                0.0F);
        this.vehiclefrontR1 = new ModelRenderer(this, 35, 77);
        this.vehiclefrontR1.setRotationPoint(-1.8F, -0.1F, -0.9F);
        this.vehiclefrontR1.addBox(-1.5F, -1.0F, -4.0F, 2, 2, 3);
        this.vehicleUpperlegR2 = new ModelRenderer(this, 6, 72);
        this.vehicleUpperlegR2.setRotationPoint(-2.0F, -0.5F, -0.5F);
        this.vehicleUpperlegR2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
        this.setRotateAngle(vehicleUpperlegR2, 0.0F, 0.05235987755982988F,
                -0.03490658503988659F);
        this.vehicleupperlegL2 = new ModelRenderer(this, 6, 72);
        this.vehicleupperlegL2.setRotationPoint(2.0F, -0.5F, -0.5F);
        this.vehicleupperlegL2.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1);
        this.setRotateAngle(vehicleupperlegL2, 0.0F, -0.05235987755982988F,
                0.03490658503988659F);
        this.vehiclekneeL2 = new ModelRenderer(this, 10, 71);
        this.vehiclekneeL2.setRotationPoint(0.0F, 2.7F, 0.0F);
        this.vehiclekneeL2.addBox(0.0F, 0.0F, -1.0F, 2, 2, 2);
        this.vehicleUpperlegR1 = new ModelRenderer(this, 0, 71);
        this.vehicleUpperlegR1.setRotationPoint(-1.0F, 1.0F, 0.0F);
        this.vehicleUpperlegR1.addBox(-1.0F, -1.0F, -1.0F, 1, 4, 2);
        this.setRotateAngle(vehicleUpperlegR1, -1.5707963267948966F, 0.0F, 0.0F);
        this.vehiclefrontR2 = new ModelRenderer(this, 48, 70);
        this.vehiclefrontR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehiclefrontR2.addBox(-1.5F, -2.0F, -3.7F, 2, 1, 3);
        this.setRotateAngle(vehiclefrontR2, 0.24434609527920614F, 0.0F,
                -0.017453292519943295F);
        this.vehicleKneeR1 = new ModelRenderer(this, 10, 71);
        this.vehicleKneeR1.setRotationPoint(0.0F, 2.7F, 0.0F);
        this.vehicleKneeR1.addBox(-2.0F, 0.0F, -1.0F, 2, 2, 2);
        this.vehicleUpperarmL1 = new ModelRenderer(this, 74, 60);
        this.vehicleUpperarmL1.mirror = true;
        this.vehicleUpperarmL1.setRotationPoint(0.5F, 0.0F, 1.0F);
        this.vehicleUpperarmL1.addBox(0.0F, 0.8F, -0.5F, 1, 3, 1);
        this.setRotateAngle(vehicleUpperarmL1, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleLowerarmL2 = new ModelRenderer(this, 74, 68);
        this.vehicleLowerarmL2.mirror = true;
        this.vehicleLowerarmL2.setRotationPoint(1.5F, 3.5F, 0.0F);
        this.vehicleLowerarmL2.addBox(-1.0F, -3.0F, -1.9F, 1, 3, 2);
        this.setRotateAngle(vehicleLowerarmL2, 0.03490658503988659F,
                0.2617993877991494F, 0.10122909661567112F);
        this.vehicleLowerlegR4 = new ModelRenderer(this, 8, 83);
        this.vehicleLowerlegR4.setRotationPoint(0.3F, 2.5F, 0.9F);
        this.vehicleLowerlegR4.addBox(-0.5F, -2.0F, -2.0F, 1, 3, 2);
        this.setRotateAngle(vehicleLowerlegR4, 0.15707963267948966F,
                0.10471975511965977F, -0.08726646259971647F);
        this.vehicleUpperlegL1 = new ModelRenderer(this, 0, 71);
        this.vehicleUpperlegL1.setRotationPoint(1.0F, 1.0F, 0.0F);
        this.vehicleUpperlegL1.addBox(0.0F, -1.0F, -1.0F, 1, 4, 2);
        this.setRotateAngle(vehicleUpperlegL1, -1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleRear2 = new ModelRenderer(this, 0, 27);
        this.vehicleRear2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.vehicleRear2.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 2);
        this.setRotateAngle(vehicleRear2, -0.19198621771937624F, 0.0F, 0.0F);
        this.vehicleRearplate = new ModelRenderer(this, 0, 12);
        this.vehicleRearplate.setRotationPoint(0.0F, 0.6F, -1.1F);
        this.vehicleRearplate.addBox(-1.5F, -1.0F, -4.0F, 3, 1, 4);
        this.vehicleBase = new ModelRenderer(this, 0, 60);
        this.vehicleBase.setRotationPoint(0.0F, 22.3F, 1.0F);
        this.vehicleBase.addBox(-2.0F, 0.0F, -1.0F, 4, 1, 3);
        this.setRotateAngle(vehicleBase, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehiclehood1 = new ModelRenderer(this, 47, 60);
        this.vehiclehood1.setRotationPoint(0.0F, -0.5F, -3.8F);
        this.vehiclehood1.addBox(-1.5F, -3.0F, -1.0F, 3, 4, 1);
        this.setRotateAngle(vehiclehood1, -1.3439035240356336F, 0.0F, 0.0F);

        this.vehicleWheelBackR = new ModelRenderer(this, 55, 68);
        this.vehicleWheelBackR.setRotationPoint(-1F, 3F, 2.2F);
        this.vehicleWheelBackR.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2);
        this.vehicleWheelBackL = new ModelRenderer(this, 55, 68);
        this.vehicleWheelBackL.mirror = true;
        this.vehicleWheelBackL.setRotationPoint(1.0F, 3.0F, 2.3F);
        this.vehicleWheelBackL.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2);
        this.vehicleWheelL = new ModelRenderer(this, 55, 68);
        this.vehicleWheelL.mirror = true;
        this.vehicleWheelL.setRotationPoint(2.6F, 0.3F, -2.0F);
        this.vehicleWheelL.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2);
        this.vehicleWheelR = new ModelRenderer(this, 55, 68);
        this.vehicleWheelR.setRotationPoint(-2.7F, 0.3F, -2.0F);
        this.vehicleWheelR.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2);

        this.vehiclearmbaseL1 = new ModelRenderer(this, 64, 60);
        this.vehiclearmbaseL1.mirror = true;
        this.vehiclearmbaseL1.setRotationPoint(1.0F, -0.4F, 1.0F);
        this.vehiclearmbaseL1.addBox(0.0F, -1.0F, -1.0F, 2, 2, 3);
        this.vehicletorsobase = new ModelRenderer(this, 35, 66);
        this.vehicletorsobase.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.vehicletorsobase.addBox(-2.0F, -2.0F, -1.3F, 4, 3, 4);
        this.setRotateAngle(vehicletorsobase, -1.5707963267948966F,
                -0.7853981633974483F, 0.0F);

        this.vehicleWaistConnector1.addChild(this.vehicleRearplate);
        this.vehicletorsobase.addChild(this.vehiclehood1);
        this.vehiclelowerlegL1.addChild(this.vehicleWheelBackL);
        this.vehicletorsobase.addChild(this.vehicleWheelL);
        this.vehicletorsobase.addChild(this.vehiclearmbaseL1);
        this.vehicleTorsoconnector1.addChild(this.vehicletorsobase);
        this.vehicletorsobase.addChild(this.vehiclebackplate2);
        this.vehicleBase.addChild(this.vehicleWaistConnector1);
        this.vehicletorsobase.addChild(this.vehiclewindow1);
        this.vehicleKneeR1.addChild(this.vehicleLowerlegR1);
        this.vehiclekneeL2.addChild(this.vehiclelowerlegL1);
        this.vehicleUpperarmL1.addChild(this.vehicleLowerarmL1);
        this.vehicletorsobase.addChild(this.vehicleWheelR);
        this.vehiclefrontL1.addChild(this.vehiclefrontL2);
        this.vehicleBase.addChild(this.vehicleWaist2);
        this.vehiclearmbaseR1.addChild(this.vehicleupperarmR1);
        this.vehiclehood1.addChild(this.vehiclehood3);
        this.vehicleBase.addChild(this.vehicleTorsoconnector1);
        this.vehiclelowerlegL1.addChild(this.vehiclelowerlegL5);
        this.vehiclehood1.addChild(this.vehiclehood2);
        this.vehicleLowerlegR1.addChild(this.vehicleLowerlegR5);
        this.vehiclelowerlegL1.addChild(this.vehiclelowerlegL4);
        this.vehiclehood1.addChild(this.vehiclehood4);
        this.vehiclewindow1.addChild(this.vehicleWindow2);
        this.vehicleupperarmR1.addChild(this.vehiclelowerarmR1);
        this.vehicleKneeR1.addChild(this.vehiclelowerlegR7);
        this.vehiclelowerarmR1.addChild(this.vehiclelowerarmR2);
        this.vehicleWaistConnector1.addChild(this.vehicleRplate1);
        this.vehicletorsobase.addChild(this.vehiclefrontL1);
        this.vehicletorsobase.addChild(this.vehiclebaseplate);
        this.vehicletorsobase.addChild(this.vehiclebackplate1);
        this.vehicleWaistConnector1.addChild(this.vehicleLplate1);
        this.vehicleWindow2.addChild(this.vehiclerear1);
        this.vehicleBase.addChild(this.vehicleWaist1);
        this.vehicletorsobase.addChild(this.vehiclearmbaseR1);
        this.vehiclekneeL2.addChild(this.vehiclelowerlegL7);
        this.vehiclearmbaseL1.addChild(this.vehicleshoulderL1_1);
        this.vehiclearmbaseR1.addChild(this.vehicleshoulderL1);
        this.vehicletorsobase.addChild(this.vehiclefrontR1);
        this.vehicleUpperlegR1.addChild(this.vehicleUpperlegR2);
        this.vehicleUpperlegL1.addChild(this.vehicleupperlegL2);
        this.vehicleUpperlegL1.addChild(this.vehiclekneeL2);
        this.vehicleWaistConnector1.addChild(this.vehicleUpperlegR1);
        this.vehiclefrontR1.addChild(this.vehiclefrontR2);
        this.vehicleUpperlegR1.addChild(this.vehicleKneeR1);
        this.vehiclearmbaseL1.addChild(this.vehicleUpperarmL1);
        this.vehicleWaistConnector1.addChild(this.vehicleUpperlegL1);
        this.vehicleWindow2.addChild(this.vehicleRear2);
        this.vehicleLowerlegR1.addChild(this.vehicleLowerlegR4);
        this.vehicleLowerlegR1.addChild(this.vehicleWheelBackR);
        this.vehicleLowerarmL1.addChild(this.vehicleLowerarmL2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerVurp;
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerVurp;
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerVurp;

            if (TFDataManager.getTransformationTimer(player) == 0)
            {
                this.vehicleBase.render(f5);
            }
            else
            {
                if (!wearingChest)
                {
                    if (wearingHead)
                    {
                        head.render(f5);
                    }

                    if (wearingLegs)
                    {
                        upperLegL.render(f5);
                        upperLegR.render(f5);
                    }
                }
                else
                {
                    waist.render(f5);
                }
            }
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3,
            float par4, float par5, float par6, Entity entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            setToInitPose();

            ModelOffset offsets = TFModelHelper.getOffsets(player);

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerVurp;

            Transformer transformerChest = TFHelper.getTransformerFromArmor(
                    player, 2);
            boolean wearingChest = transformerChest instanceof TransformerVurp;

            Transformer transformerLegs = TFHelper.getTransformerFromArmor(
                    player, 1);
            boolean wearingLegs = transformerLegs instanceof TransformerVurp;

            head.showModel = wearingHead;
            upperLegR.showModel = wearingLegs;
            upperLegL.showModel = wearingLegs;

            float globalSpeed = 1;
            float globalDegree = 0.8F;

            this.head.rotationPointX += offsets.headOffsetX;
            this.head.rotationPointY += offsets.headOffsetY;
            this.head.rotationPointZ += offsets.headOffsetZ;

            if (wearingHead)
            {
                faceTarget(head, 1, par4, par5);
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

            if (this.heldItemLeft != 0)
            {
                this.upperArmL.rotateAngleX -= 0.2F;
            }
            if (this.heldItemRight != 0)
            {
                this.upperArmR.rotateAngleX -= 0.2F;
            }

            ItemStack heldItemStack = player.getHeldItem();
            boolean holdingSniper = heldItemStack != null
                    && heldItemStack.getItem() instanceof ItemVurpsSniper;

            if (wearingChest && wearingHead && wearingLegs)
            {
                boolean playerOnGround = onGround(player);

                if (playerOnGround || player.capabilities.isFlying)
                {
                    waist.rotationPointY -= 3F;

                    upperLegR.rotateAngleY += 0.2;
                    upperLegL.rotateAngleY -= 0.2;
                    upperLegR.rotateAngleX -= 0.1;
                    upperLegL.rotateAngleX -= 0.1;
                    lowerLegL.rotateAngleX += 0.1;
                    lowerLegR.rotateAngleX += 0.1;
                    lowerArmL.rotateAngleX -= 0.1;
                    lowerArmR.rotateAngleX -= 0.1;
                    head.rotateAngleX += 0.1;

                    bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false,
                            par1, par2);
                    waist.rotationPointY += (1 * par2) + 3;
                    walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false,
                            1, 0.15F * par2 * backwardInverter, par1, par2);
                    walk(torsobase, 1F * globalSpeed, 0.05F * globalDegree,
                            false, 1, 0.15F * par2 * backwardInverter, par1,
                            par2);
                    swing(torsobase, 0.5F * globalSpeed, 0.6F * globalDegree,
                            true, 0, 0, par1, par2);
                    swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree,
                            false, 0, 0, par1, par2);
                    walk(head, 1F * globalSpeed, -0.1F * globalDegree, false,
                            1F, -0.3F * par2 * backwardInverter, par1, par2);

                    swing(head, 0.5F * globalSpeed, 0.4F * globalDegree, false,
                            0, 0, par1, par2);
                    head.rotationPointX += 0.6 * globalDegree * par2
                            * Math.cos(par1 * 0.5F * globalSpeed);

                    swing(upperLegR, 0.5F * globalSpeed, 0F * globalDegree,
                            false, 0, -0.15F, par1, par2);
                    swing(upperLegL, 0.5F * globalSpeed, 0F * globalDegree,
                            false, 0, 0.15F, par1, par2);
                    walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree,
                            false, 0, 0, par1, par2);
                    walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree,
                            true, 0, 0, par1, par2);
                    walk(lowerLegR, 0.5F * globalSpeed, 1.2F * globalDegree,
                            false, -2.2F * backwardInverter, 0.6F, par1, par2);
                    walk(lowerLegL, 0.5F * globalSpeed, 1.2F * globalDegree,
                            true, -2.2F * backwardInverter, 0.6F, par1, par2);
                    walk(upperArmR, 0.5F * globalSpeed, 0.5F * globalDegree,
                            true, 0F, -0.3F * par2 * backwardInverter, par1,
                            par2);
                    walk(upperArmL, 0.5F * globalSpeed, 0.5F * globalDegree,
                            false, 0F, -0.3F * par2 * backwardInverter, par1,
                            par2);
                    walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree,
                            true, -1F * backwardInverter, -0.5F * par2, par1,
                            par2);
                    walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree,
                            false, -1F * backwardInverter, -0.5F * par2, par1,
                            par2);

                    int ticksExisted = entity.ticksExisted;

                    walk(fronttorso1, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
                    walk(torsobase, 0.08F, 0.15F, false, 1, 0, ticksExisted, 1F);
                    walk(head, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
                    walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
                    walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);

                    flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
                    flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticksExisted, 1F);
                    walk(lowerArmR, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
                    walk(lowerArmL, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);

                    if (player.isSneaking()) // TODO: Sneak Animation
                    {
                    }
                }
                else
                // If not on ground
                {
                    double motionY = entity.posY - entity.prevPosY;

                    float upwardPose = (float) (1 / (1 + Math.exp(-20
                            * (motionY + 0.2))));
                    float downwardPose = (float) (1 / (1 + Math
                            .exp(10 * (motionY + 0.2))));

                    waist.rotateAngleX += 0.2 * par2 * backwardInverter;

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

                    walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree
                            * downwardPose, false, 0, 0, par1, par2);
                    walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree
                            * downwardPose, true, 0, 0, par1, par2);
                    walk(lowerLegR, 0.5F * globalSpeed, 0.2F * globalDegree
                            * downwardPose, false, -2.2F * backwardInverter,
                            0F, par1, par2);
                    walk(lowerLegL, 0.5F * globalSpeed, 0.2F * globalDegree
                            * downwardPose, true, -2.2F * backwardInverter, 0F,
                            par1, par2);
                    waist.rotateAngleX -= 0.2 * downwardPose;
                    fronttorso1.rotateAngleX += 0.3 * downwardPose;
                    torsobase.rotateAngleX += 0.3 * downwardPose;
                    head.rotateAngleX += 0.3 * downwardPose;
                    upperLegR.rotateAngleX -= 1.2 * downwardPose;
                    upperLegL.rotateAngleX -= 0.2 * downwardPose;
                    lowerLegR.rotateAngleX += 2 * downwardPose;
                    lowerLegL.rotateAngleX += 0.5 * downwardPose;
                    upperArmR.rotateAngleZ += 1 * downwardPose;
                    upperArmL.rotateAngleZ -= 1 * downwardPose;
                    lowerArmR.rotateAngleX -= 1 * downwardPose;
                    lowerArmL.rotateAngleX -= 1 * downwardPose;
                }

                if (holdingSniper)
                {
                    armbaseR1.rotateAngleX += head.rotateAngleX;
                    upperArmR.rotateAngleX -= 0.25F;
                    upperArmR.rotateAngleY -= 0.25F;
                    lowerArmR.rotateAngleX -= 0.25F;
                    lowerArmR.rotateAngleY -= 0.5F;

                    armbaseL1.rotateAngleX += head.rotateAngleX;
                    upperArmL.rotateAngleX -= 0.25F;
                    upperArmL.rotateAngleY += 0.25F;
                    lowerArmL.rotateAngleX -= 0.5F;
                    lowerArmL.rotateAngleY += 0.8F;
                }

                int t = TFDataManager.getTransformationTimer(player);
                float f = (float) (20 - t);

                this.waist.rotationPointY += f * 0.9F;
                this.armbaseL1.rotateAngleX -= f * 0.08F;
                this.armbaseR1.rotateAngleX -= f * 0.08F;
                this.lowerArmL.rotateAngleZ += f * 0.1F;
                this.lowerArmR.rotateAngleZ -= f * 0.1F;
            }
            else
            // If not fully suited
            {
                waist.rotationPointY += 1;
                this.upperArmL.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
                this.upperArmR.rotateAngleX += (MathHelper.cos(par1 * 0.6662F
                        + (float) Math.PI) * 1.4F * par2) / 2;

                this.lowerArmL.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 4;
                this.lowerArmR.rotateAngleX += (MathHelper.cos(par1 * 0.6662F
                        + (float) Math.PI) * 1.4F * par2) / 4;

                this.upperLegR.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
                this.upperLegL.rotateAngleX += (MathHelper.cos(par1 * 0.6662F
                        + (float) Math.PI) * 1.4F * par2) / 2;

                if (this.isRiding)
                {
                    this.upperArmR.rotateAngleX += -((float) Math.PI / 5F);
                    this.upperArmL.rotateAngleX += -((float) Math.PI / 5F);
                    this.upperLegR.rotateAngleX = -((float) Math.PI * 2F / 5F);
                    this.upperLegL.rotateAngleX = -((float) Math.PI * 2F / 5F);
                    this.upperLegR.rotateAngleY = ((float) Math.PI / 10F);
                    this.upperLegL.rotateAngleY = -((float) Math.PI / 10F);
                }

                if (this.isSneak)
                {
                    this.waist.rotateAngleX += 0.4F;
                    this.waist.rotationPointZ += 4F;
                    this.upperArmR.rotateAngleX -= 0.4F;
                    this.upperArmL.rotateAngleX -= 0.4F;

                    if (wearingChest)
                    {
                        this.head.rotateAngleX -= 0.4F;
                        this.upperLegR.rotateAngleX -= 0.4F;
                        this.upperLegL.rotateAngleX -= 0.4F;
                    }
                    else
                    {
                        this.upperLegL.rotationPointZ += 5F;
                        this.upperLegL.rotationPointY -= 0.8F;
                        this.upperLegR.rotationPointZ += 5F;
                        this.upperLegR.rotationPointY -= 0.8F;
                    }
                }
                else
                {
                    this.waist.rotationPointY -= 0.8F;
                    this.waist.rotateAngleX = 0.0F;
                }

                if (this.onGround > -9990.0F)
                {
                    float f6 = this.onGround;
                    this.waist.rotateAngleY = MathHelper.sin(MathHelper
                            .sqrt_float(f6) * (float) Math.PI * 2.0F) * 0.2F;
                    this.upperArmR.rotateAngleY += this.waist.rotateAngleY;
                    this.upperArmL.rotateAngleY += this.waist.rotateAngleY;
                    this.upperArmL.rotateAngleX += this.waist.rotateAngleY;
                    f6 = 1.0F - this.onGround;
                    f6 *= f6;
                    f6 *= f6;
                    f6 = 1.0F - f6;
                    float f7 = MathHelper.sin(f6 * (float) Math.PI);
                    float f8 = MathHelper.sin(this.onGround * (float) Math.PI)
                            * -(this.head.rotateAngleX - 0.7F) * 0.75F;
                    this.upperArmR.rotateAngleX = (float) ((double) this.upperArmR.rotateAngleX - ((double) f7 * 1.2D + (double) f8));
                    this.upperArmR.rotateAngleY += this.waist.rotateAngleY * 2.0F;
                    this.upperArmR.rotateAngleZ = MathHelper.sin(this.onGround
                            * (float) Math.PI)
                            * -0.4F;
                    this.upperArmR.rotateAngleZ += 0.15F;
                }
            }

            float wheelSpinSpeed = par1 * 0.8F;
            vehicleWheelBackR.rotateAngleX = wheelSpinSpeed;
            vehicleWheelBackL.rotateAngleX = wheelSpinSpeed;
            vehicleWheelL.rotateAngleX = wheelSpinSpeed;
            vehicleWheelR.rotateAngleX = wheelSpinSpeed;

            for (ModelRenderer modelRenderer : new ModelRenderer[] { vehicleBase })
            {
                modelRenderer.rotateAngleY = bipedBody.rotateAngleY;

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

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y,
            float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
