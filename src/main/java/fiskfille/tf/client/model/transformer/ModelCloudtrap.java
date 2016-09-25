package fiskfille.tf.client.model.transformer;

import net.minecraft.client.model.ModelBiped;
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
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class ModelCloudtrap extends ModelTransformerBase
{
    public MowzieModelRenderer waist;
    public MowzieModelRenderer torsoConnector;
    public MowzieModelRenderer waistL1;
    public MowzieModelRenderer waistR1;
    public MowzieModelRenderer waistR2;
    public MowzieModelRenderer waistL2;
    public MowzieModelRenderer upperLegL;
    public MowzieModelRenderer upperLegR;
    public MowzieModelRenderer waist1;
    public MowzieModelRenderer waist2;
    public MowzieModelRenderer wasit3;
    public MowzieModelRenderer waist4;
    public MowzieModelRenderer waist5;
    public MowzieModelRenderer upperBodyBase;
    public MowzieModelRenderer torsoR1;
    public MowzieModelRenderer torsoR2;
    public MowzieModelRenderer torsoL1;
    public MowzieModelRenderer torsoL2;
    public MowzieModelRenderer back1;
    public MowzieModelRenderer shoulderbase;
    public MowzieModelRenderer chestcockpitbase;
    public MowzieModelRenderer leftshoulderbase;
    public MowzieModelRenderer rightshoulderbase;
    public MowzieModelRenderer neck1;
    public MowzieModelRenderer neck2;
    public MowzieModelRenderer neck3;
    public MowzieModelRenderer upperArmL;
    public MowzieModelRenderer upperArmR;
    public MowzieModelRenderer leftshoulder1;
    public MowzieModelRenderer leftshoulder3;
    public MowzieModelRenderer leftshoulder4;
    public MowzieModelRenderer leftshoulder2;
    public MowzieModelRenderer leftshoulder5;
    public MowzieModelRenderer rightshoulder1;
    public MowzieModelRenderer rightshoulder3;
    public MowzieModelRenderer rightshoulder5;
    public MowzieModelRenderer rightshoulder2;
    public MowzieModelRenderer rightshoulder4;
    public MowzieModelRenderer head;
    public MowzieModelRenderer head1;
    public MowzieModelRenderer head2;
    public MowzieModelRenderer head3;
    public MowzieModelRenderer head4;
    public MowzieModelRenderer head5;
    public MowzieModelRenderer head6;
    public MowzieModelRenderer head7;
    public MowzieModelRenderer head8;
    public MowzieModelRenderer head9;
    public MowzieModelRenderer head10;
    public MowzieModelRenderer shoulderpieceL1;
    public MowzieModelRenderer lowerArmL;
    public MowzieModelRenderer wingconnectorL;
    public MowzieModelRenderer wingbaseL;
    public MowzieModelRenderer wingL1;
    public MowzieModelRenderer wingstrutL1;
    public MowzieModelRenderer wingstrutL2;
    public MowzieModelRenderer wingstrutL3;
    public MowzieModelRenderer wingL2;
    public MowzieModelRenderer wingL3;
    public MowzieModelRenderer wingL4;
    public MowzieModelRenderer lowerarmL1;
    public MowzieModelRenderer lowerarmL2;
    public MowzieModelRenderer lowerarmL3;
    public MowzieModelRenderer fistLbase;
    public MowzieModelRenderer finger1_1;
    public MowzieModelRenderer finger2_1;
    public MowzieModelRenderer finger3_1;
    public MowzieModelRenderer finger4_1;
    public MowzieModelRenderer fistL1;
    public MowzieModelRenderer finger1_2;
    public MowzieModelRenderer finger2_2;
    public MowzieModelRenderer finger3_2;
    public MowzieModelRenderer finger4_2;
    public MowzieModelRenderer lowerArmR;
    public MowzieModelRenderer shoulderpieceR1;
    public MowzieModelRenderer lowerarmR1;
    public MowzieModelRenderer lowerarmR2;
    public MowzieModelRenderer lowerarmR3;
    public MowzieModelRenderer fistRbase;
    public MowzieModelRenderer fistR2;
    public MowzieModelRenderer wingconnectorR;
    public MowzieModelRenderer wingbaseR;
    public MowzieModelRenderer wingR1;
    public MowzieModelRenderer wingstrutR3;
    public MowzieModelRenderer wingstrutR2;
    public MowzieModelRenderer wingstrutR1;
    public MowzieModelRenderer wingR4;
    public MowzieModelRenderer wingR3;
    public MowzieModelRenderer wingR2;
    public MowzieModelRenderer cockpit1;
    public MowzieModelRenderer cockpit4;
    public MowzieModelRenderer cockpit5;
    public MowzieModelRenderer cockpit2;
    public MowzieModelRenderer cockpit3;
    public MowzieModelRenderer back2;
    public MowzieModelRenderer back3;
    public MowzieModelRenderer buttflapL;
    public MowzieModelRenderer buttflapR;
    public MowzieModelRenderer lowerlegLbase;
    public MowzieModelRenderer upperlegL1;
    public MowzieModelRenderer upperlegL2;
    public MowzieModelRenderer upperlegL3;
    public MowzieModelRenderer engineL1;
    public MowzieModelRenderer lowerlegL1;
    public MowzieModelRenderer engineL6;
    public MowzieModelRenderer feetbaseL;
    public MowzieModelRenderer engineL2;
    public MowzieModelRenderer engineL3;
    public MowzieModelRenderer engineL4;
    public MowzieModelRenderer engineL5;
    public MowzieModelRenderer lowerlegL2;
    public MowzieModelRenderer lowerlegL4;
    public MowzieModelRenderer lowerlegL5;
    public MowzieModelRenderer lowerlegL3;
    public MowzieModelRenderer engineL7;
    public MowzieModelRenderer feetL1;
    public MowzieModelRenderer feetL5;
    public MowzieModelRenderer feetl6;
    public MowzieModelRenderer feetL2;
    public MowzieModelRenderer feetL3;
    public MowzieModelRenderer feetL4;
    public MowzieModelRenderer lowerlegRbase;
    public MowzieModelRenderer upperlegR1;
    public MowzieModelRenderer upperlegR2;
    public MowzieModelRenderer upperlegR3;
    public MowzieModelRenderer engineR1;
    public MowzieModelRenderer lowerlegR1;
    public MowzieModelRenderer feetbaseR;
    public MowzieModelRenderer engineR6;
    public MowzieModelRenderer engineR2;
    public MowzieModelRenderer engineR3;
    public MowzieModelRenderer engineR4;
    public MowzieModelRenderer engineR5;
    public MowzieModelRenderer lowerlegR2;
    public MowzieModelRenderer lowerlegR3;
    public MowzieModelRenderer lowerlegR5;
    public MowzieModelRenderer lowerlegR4;
    public MowzieModelRenderer feetR1;
    public MowzieModelRenderer feetR5;
    public MowzieModelRenderer feetR6;
    public MowzieModelRenderer feetR2;
    public MowzieModelRenderer feetR3;
    public MowzieModelRenderer feetR4;
    public MowzieModelRenderer engineR7;

    public ModelRenderer vehicleBody;
    public ModelRenderer vehicleTorsoConnector;
    public ModelRenderer vehicleUpperlegLbase;
    public ModelRenderer vehicleUpperlegRbase;
    public ModelRenderer vehicleWaist1;
    public ModelRenderer vehicleWaist4;
    public ModelRenderer vehicleWaist5;
    public ModelRenderer vehicleUpperBodyBase;
    public ModelRenderer vehicleTorsoR2;
    public ModelRenderer vehicletorsoL2;
    public ModelRenderer vehiclelowerRear;
    public ModelRenderer vehicleshoulderbase;
    public ModelRenderer vehiclechestcockpitbase;
    public ModelRenderer vehiclelowerCockpitRearR;
    public ModelRenderer vehiclelowerCockpitRearL;
    public ModelRenderer vehicleBottomCockpitR;
    public ModelRenderer vehiclebottomCockpitL;
    public ModelRenderer vehicleleftshoulderbase;
    public ModelRenderer vehiclerightshoulderbase;
    public ModelRenderer vehicleupperarmbaseL;
    public ModelRenderer vehicleupperarmbaseR;
    public ModelRenderer vehicleleftshoulder1;
    public ModelRenderer vehicleleftshoulder3;
    public ModelRenderer vehicleleftshoulder4;
    public ModelRenderer vehiclewingCockpitConnectorL;
    public ModelRenderer vehicleleftshoulder5;
    public ModelRenderer vehiclerearTailConnectorL;
    public ModelRenderer vehiclerightshoulder1;
    public ModelRenderer vehiclerightshoulder3;
    public ModelRenderer vehiclerightshoulder5;
    public ModelRenderer vehiclewingCockpitConnectorR;
    public ModelRenderer vehiclerightshoulder2;
    public ModelRenderer vehiclerearTailConnectorR;
    public ModelRenderer vehicleshoulderpieceL1;
    public ModelRenderer vehiclelowerarmbaseL;
    public ModelRenderer vehiclewingconnectorL;
    public ModelRenderer vehiclewingbaseL;
    public ModelRenderer vehiclewingL1;
    public ModelRenderer vehiclewingstrutL1;
    public ModelRenderer vehiclewingstrutL2;
    public ModelRenderer vehiclewingstrutL3;
    public ModelRenderer vehiclewingLBase;
    public ModelRenderer vehiclewingL2;
    public ModelRenderer vehiclewingL3;
    public ModelRenderer vehiclewingL4;
    public ModelRenderer vehiclewingFrontL;
    public ModelRenderer vehiclewingTipL;
    public ModelRenderer vehiclewingFlapL;
    public ModelRenderer vehiclewingConnectorL1;
    public ModelRenderer vehiclelowerarmL1;
    public ModelRenderer vehiclelowerarmL2;
    public ModelRenderer vehiclelowerarmL3;
    public ModelRenderer vehiclelowerarmbaseR;
    public ModelRenderer vehicleshoulderpieceR1;
    public ModelRenderer vehiclelowerarmR1;
    public ModelRenderer vehiclelowerarmR2;
    public ModelRenderer vehiclelowerarmR3;
    public ModelRenderer vehiclewingconnectorR;
    public ModelRenderer vehiclewingbaseR;
    public ModelRenderer vehiclewingR1;
    public ModelRenderer vehiclewingstrutR3;
    public ModelRenderer vehiclewingstrutR2;
    public ModelRenderer vehiclewingstrutR1;
    public ModelRenderer vehiclewingRBase;
    public ModelRenderer vehiclewingR4;
    public ModelRenderer vehiclewingR3;
    public ModelRenderer vehiclewingR2;
    public ModelRenderer vehiclewingFrontR;
    public ModelRenderer vehiclewingTipR;
    public ModelRenderer vehiclewingFlapR;
    public ModelRenderer vehiclewingConnectorR1;
    public ModelRenderer vehiclecockpit1;
    public ModelRenderer vehiclecockpit4;
    public ModelRenderer vehiclecockpit5;
    public ModelRenderer vehiclerearCockpit;
    public ModelRenderer vehiclecockpit2;
    public ModelRenderer vehiclecockpit3;
    public ModelRenderer vehiclecockpit1_1;
    public ModelRenderer vehiclecockpitR;
    public ModelRenderer vehiclecockpitL;
    public ModelRenderer vehiclecenterConnector1;
    public ModelRenderer vehiclerearSlantR;
    public ModelRenderer vehiclerearSlantL;
    public ModelRenderer vehiclerear;
    public ModelRenderer vehiclenoseLift;
    public ModelRenderer vehiclenoseBottom;
    public ModelRenderer vehiclenoseSideR1;
    public ModelRenderer vehiclenoseSideL1;
    public ModelRenderer vehiclenoseTop;
    public ModelRenderer vehiclelowerlegLbase;
    public ModelRenderer vehicleupperlegL2;
    public ModelRenderer vehicleupperlegL3;
    public ModelRenderer vehicleengineL1;
    public ModelRenderer vehiclelowerlegL1;
    public ModelRenderer vehiclefeetbaseL;
    public ModelRenderer vehicleengineL2;
    public ModelRenderer vehicleengineL3;
    public ModelRenderer vehicleengineL4;
    public ModelRenderer vehicleengineL5;
    public ModelRenderer vehiclelowerlegL2;
    public ModelRenderer vehiclelowerlegL4;
    public ModelRenderer vehiclelowerlegL5;
    public ModelRenderer vehiclelowerlegL3;
    public ModelRenderer vehicletailRFront;
    public ModelRenderer vehiclerearWingConnectorL;
    public ModelRenderer vehiclefeetL1;
    public ModelRenderer vehiclefeetL3;
    public ModelRenderer vehiclefeetL4;
    public ModelRenderer vehicletailWingR;
    public ModelRenderer vehicletailWingFlapR;
    public ModelRenderer vehicleupperlegL3_1;
    public ModelRenderer vehiclelowerlegRbase;
    public ModelRenderer vehicleupperlegR2;
    public ModelRenderer vehicleupperlegR3;
    public ModelRenderer vehicleengineR1;
    public ModelRenderer vehiclelowerlegR1;
    public ModelRenderer vehiclefeetbaseR;
    public ModelRenderer vehicleengineR2;
    public ModelRenderer vehicleengineR3;
    public ModelRenderer vehicleengineR4;
    public ModelRenderer vehicleengineR5;
    public ModelRenderer vehiclelowerlegR2;
    public ModelRenderer vehiclelowerlegR3;
    public ModelRenderer vehiclelowerlegR5;
    public ModelRenderer vehiclelowerlegR4;
    public ModelRenderer vehicletailLFront;
    public ModelRenderer vehiclerearWingConnectorR;
    public ModelRenderer vehiclefeetR1;
    public ModelRenderer vehiclefeetR3;
    public ModelRenderer vehiclefeetR4;
    public ModelRenderer vehicletailWingL;
    public ModelRenderer vehicletailWingFlapL;
    public ModelRenderer vehicleupperlegL3_2;
    public ModelRenderer vehiclewaist3;
    public ModelRenderer vehiclewaist2;

    public ModelCloudtrap()
    {
        textureWidth = 128;
        textureHeight = 128;

        waist1 = new MowzieModelRenderer(this, 6, 14);
        waist1.setRotationPoint(0, 1.4F, -1.1F);
        waist1.addBox(-1, 0, 0, 2, 1, 2, 0);
        setRotationAngle(waist1, -0.017453292519943295F, 0, 0);
        fistR2 = new MowzieModelRenderer(this, 41, 42);
        fistR2.mirror = true;
        fistR2.setRotationPoint(0, 0, 0);
        fistR2.addBox(-0.2F, -0.2F, -1, 1, 2, 2, 0);
        lowerlegRbase = new MowzieModelRenderer(this, 0, 81);
        lowerlegRbase.mirror = true;
        lowerlegRbase.setRotationPoint(-0.5F, 6, 0);
        lowerlegRbase.addBox(-1, 0, -1, 2, 6, 2, 0);
        setRotationAngle(lowerlegRbase, 0.19198621771937624F, 0.017453292519943295F, -0.017453292519943295F);
        rightshoulder4 = new MowzieModelRenderer(this, 10, 44);
        rightshoulder4.setRotationPoint(-1, 0, 1);
        rightshoulder4.addBox(-0.6F, -0.2F, -0.5F, 1, 3, 3, 0);
        setRotationAngle(rightshoulder4, 0.06981317007977318F, 0.20943951023931953F, -0.9424777960769379F);
        lowerarmL2 = new MowzieModelRenderer(this, 35, 35);
        lowerarmL2.setRotationPoint(1, 4, 1.2F);
        lowerarmL2.addBox(-1, -4, -1, 1, 4, 1, 0);
        setRotationAngle(lowerarmL2, -0.08726646259971647F, 0, 0.10471975511965977F);
        torsoL1 = new MowzieModelRenderer(this, 15, 53);
        torsoL1.mirror = true;
        torsoL1.setRotationPoint(1, -5, -1.4F);
        torsoL1.addBox(0, 0.2F, 0, 2, 2, 2, 0);
        setRotationAngle(torsoL1, 0.17453292519943295F, -0.2617993877991494F, 0.19198621771937624F);
        feetR2 = new MowzieModelRenderer(this, 14, 88);
        feetR2.mirror = true;
        feetR2.setRotationPoint(-1.5F, 0, -3.4F);
        feetR2.addBox(0, 0, -1, 3, 1, 1, 0);
        setRotationAngle(feetR2, 1.0122909661567112F, 0, 0);
        engineL3 = new MowzieModelRenderer(this, 10, 77);
        engineL3.mirror = true;
        engineL3.setRotationPoint(0, 1, 3);
        engineL3.addBox(-1, 0, -1, 2, 2, 1, 0);
        setRotationAngle(engineL3, -0.19198621771937624F, 0, 0);
        rightshoulder5 = new MowzieModelRenderer(this, 0, 56);
        rightshoulder5.setRotationPoint(-1, -1, -2.8F);
        rightshoulder5.addBox(0, 0, 0, 1, 2, 4, 0);
        setRotationAngle(rightshoulder5, 0, 0, 0.03490658503988659F);
        lowerlegLbase = new MowzieModelRenderer(this, 0, 81);
        lowerlegLbase.setRotationPoint(0.5F, 6, 0);
        lowerlegLbase.addBox(-1, 0, -1, 2, 6, 2, 0);
        setRotationAngle(lowerlegLbase, 0.19198621771937624F, -0.017453292519943295F, 0.017453292519943295F);
        feetL5 = new MowzieModelRenderer(this, 0, 98);
        feetL5.mirror = true;
        feetL5.setRotationPoint(-1, 1.6F, -3.3F);
        feetL5.addBox(0, 0, 0, 2, 1, 3, 0);
        setRotationAngle(feetL5, 0.47123889803846897F, 0, 0);
        wingL2 = new MowzieModelRenderer(this, 43, 23);
        wingL2.setRotationPoint(0, 2, 0);
        wingL2.addBox(-0.4F, -0.3F, -1, 1, 6, 2, 0);
        setRotationAngle(wingL2, 0.05235987755982988F, 0.017453292519943295F, 0.08726646259971647F);
        lowerarmL1 = new MowzieModelRenderer(this, 43, 31);
        lowerarmL1.setRotationPoint(0, 0, 0);
        lowerarmL1.addBox(-0.9F, -1.1F, 0.5F, 2, 2, 1, 0);
        setRotationAngle(lowerarmL1, -0.08726646259971647F, -0.08726646259971647F, -0.7853981633974483F);
        wingbaseL = new MowzieModelRenderer(this, 35, 17);
        wingbaseL.mirror = true;
        wingbaseL.setRotationPoint(1.6F, 0, 0);
        wingbaseL.addBox(-0.3F, -1.2F, -1.1F, 1, 2, 3, 0);
        setRotationAngle(wingbaseL, 0.17453292519943295F, 0.45378560551852565F, 0.08726646259971647F);
        leftshoulder2 = new MowzieModelRenderer(this, 10, 44);
        leftshoulder2.mirror = true;
        leftshoulder2.setRotationPoint(1, 0, 1);
        leftshoulder2.addBox(-0.4F, -0.2F, -0.5F, 1, 3, 3, 0);
        setRotationAngle(leftshoulder2, 0.06981317007977318F, -0.20943951023931953F, 0.9424777960769379F);
        finger2_2 = new MowzieModelRenderer(this, 46, 35);
        finger2_2.setRotationPoint(-1, 1.7F, -0.2F);
        finger2_2.addBox(0, 0, 0, 1, 1, 1, 0);
        setRotationAngle(finger2_2, 0.8377580409572781F, 0, 0);
        wingstrutR2 = new MowzieModelRenderer(this, 40, 22);
        wingstrutR2.setRotationPoint(-0.1F, -0.5F, 2);
        wingstrutR2.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(wingstrutR2, 0.05235987755982988F, 0.017453292519943295F, -0.017453292519943295F);
        wingconnectorR = new MowzieModelRenderer(this, 44, 20);
        wingconnectorR.setRotationPoint(-0.6F, 1, 0);
        wingconnectorR.addBox(-1.7F, -0.5F, -0.5F, 2, 1, 1, 0);
        setRotationAngle(wingconnectorR, -0.03490658503988659F, 0.5235987755982988F, 0.24434609527920614F);
        leftshoulder3 = new MowzieModelRenderer(this, 0, 56);
        leftshoulder3.mirror = true;
        leftshoulder3.setRotationPoint(2, -1, -2.8F);
        leftshoulder3.addBox(-1, 0, 0, 1, 2, 4, 0);
        setRotationAngle(leftshoulder3, 0, 0, -0.03490658503988659F);
        head1 = new MowzieModelRenderer(this, 14, 19);
        head1.setRotationPoint(0, -3, 2.7F);
        head1.addBox(-2, -0.4F, -2.2F, 4, 1, 2, 0);
        setRotationAngle(head1, 0.04537856055185257F, 0, 0);
        torsoR1 = new MowzieModelRenderer(this, 15, 53);
        torsoR1.setRotationPoint(-1, -5, -1.4F);
        torsoR1.addBox(-2, 0.2F, 0, 2, 2, 2, 0);
        setRotationAngle(torsoR1, 0.17453292519943295F, 0.2617993877991494F, -0.19198621771937624F);
        chestcockpitbase = new MowzieModelRenderer(this, 21, 44);
        chestcockpitbase.setRotationPoint(-1.5F, -2, -1.5F);
        chestcockpitbase.addBox(0, 0, -2, 3, 2, 2, 0);
        setRotationAngle(chestcockpitbase, 0.7853981633974483F, 0, 0);
        waistR2 = new MowzieModelRenderer(this, 18, 62);
        waistR2.setRotationPoint(-1, 0.1F, -1);
        waistR2.addBox(-2, 0, -0.5F, 2, 1, 1, 0);
        setRotationAngle(waistR2, 0, 0.5585053606381855F, 0.20943951023931953F);
        rightshoulderbase = new MowzieModelRenderer(this, 0, 46);
        rightshoulderbase.setRotationPoint(-4, 0.15F, 1.4F);
        rightshoulderbase.addBox(-1, -1, -2.9F, 3, 1, 4, 0);
        setRotationAngle(rightshoulderbase, -0.06981317007977318F, 0.17453292519943295F, 0.05235987755982988F);
        rightshoulder1 = new MowzieModelRenderer(this, 6, 56);
        rightshoulder1.setRotationPoint(2.1F, -1, -2.9F);
        rightshoulder1.addBox(-3.1F, 0, 0, 3, 1, 3, 0);
        setRotationAngle(rightshoulder1, 0.20943951023931953F, 0.06981317007977318F, 0);
        engineL2 = new MowzieModelRenderer(this, 4, 77);
        engineL2.setRotationPoint(0, 1, 0);
        engineL2.addBox(-1, 0, 0, 2, 2, 1, 0);
        setRotationAngle(engineL2, 0.19198621771937624F, 0, 0);
        lowerlegR2 = new MowzieModelRenderer(this, 18, 80);
        lowerlegR2.setRotationPoint(0, 0, 1);
        lowerlegR2.addBox(-3, -2, -1, 3, 2, 1, 0);
        setRotationAngle(lowerlegR2, 0.4886921905584123F, 0, 0);
        shoulderpieceR1 = new MowzieModelRenderer(this, 44, 16);
        shoulderpieceR1.setRotationPoint(0, 0.3F, 0);
        shoulderpieceR1.addBox(-1, 0, -1, 1, 2, 2, 0);
        setRotationAngle(shoulderpieceR1, 0.06981317007977318F, 0, -0.10471975511965977F);
        head10 = new MowzieModelRenderer(this, 24, 17);
        head10.mirror = true;
        head10.setRotationPoint(0, -0.8F, -0.8F);
        head10.addBox(1.2F, -1.9F, 0.1F, 1, 2, 2, 0);
        setRotationAngle(head10, 0, -0.08726646259971647F, 0);
        lowerlegL3 = new MowzieModelRenderer(this, 14, 80);
        lowerlegL3.mirror = true;
        lowerlegL3.setRotationPoint(4, 0, 0);
        lowerlegL3.addBox(0, 0, 0, 1, 3, 1, 0);
        setRotationAngle(lowerlegL3, 0, 0, 0.33161255787892263F);
        shoulderbase = new MowzieModelRenderer(this, 0, 40);
        shoulderbase.setRotationPoint(0, -1, 1);
        shoulderbase.addBox(-4, -0.8F, 0, 8, 2, 2, 0);
        setRotationAngle(shoulderbase, -0.45378560551852565F, 0, 0);
        engineL6 = new MowzieModelRenderer(this, 0, 89);
        engineL6.setRotationPoint(0, 2, 1);
        engineL6.addBox(-1, -3, -0.4F, 2, 5, 2, 0);
        setRotationAngle(engineL6, -0.2792526803190927F, 0.3490658503988659F, 0);
        neck1 = new MowzieModelRenderer(this, 0, 14);
        neck1.setRotationPoint(-0.5F, -1.2F, -2);
        neck1.addBox(0, 0, 0, 1, 2, 3, 0);
        fistL1 = new MowzieModelRenderer(this, 45, 40);
        fistL1.mirror = true;
        fistL1.setRotationPoint(0, 0.4F, -0.3F);
        fistL1.addBox(-1, -1.4F, -1, 2, 2, 1, 0);
        setRotationAngle(fistL1, 0.08726646259971647F, 0, 0);
        feetL3 = new MowzieModelRenderer(this, 14, 90);
        feetL3.mirror = true;
        feetL3.setRotationPoint(-0.5F, 0, 1.6F);
        feetL3.addBox(-1, 0, 0, 1, 1, 2, 0);
        setRotationAngle(feetL3, 0, -0.4363323129985824F, 0);
        wingstrutL1 = new MowzieModelRenderer(this, 40, 22);
        wingstrutL1.setRotationPoint(0.1F, 0, -0.4F);
        wingstrutL1.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(wingstrutL1, 0, 0.08726646259971647F, -0.03490658503988659F);
        engineR1 = new MowzieModelRenderer(this, 4, 73);
        engineR1.setRotationPoint(0, 3.9F, 0);
        engineR1.addBox(-1.5F, 0, 0, 3, 1, 3, 0);
        setRotationAngle(engineR1, 0.3490658503988659F, -0.017453292519943295F, -0.017453292519943295F);
        head = new MowzieModelRenderer(this, 14, 13);
        head.setRotationPoint(0.5F, 0, 1.2F);
        head.addBox(-1.5F, -3, -1.3F, 3, 3, 3, 0);
        back3 = new MowzieModelRenderer(this, 18, 57);
        back3.setRotationPoint(0, 3, 0);
        back3.addBox(-1, -1, -0.8F, 2, 4, 1, 0);
        setRotationAngle(back3, 0.3665191429188092F, 0, 0);
        engineR5 = new MowzieModelRenderer(this, 4, 77);
        engineR5.mirror = true;
        engineR5.setRotationPoint(0, 1, 0);
        engineR5.addBox(-1, 0, 0, 2, 2, 1, 0);
        setRotationAngle(engineR5, 0.19198621771937624F, 0, 0);
        lowerarmL3 = new MowzieModelRenderer(this, 35, 35);
        lowerarmL3.mirror = true;
        lowerarmL3.setRotationPoint(-1, 4, 1.2F);
        lowerarmL3.addBox(0, -4, -1, 1, 4, 1, 0);
        setRotationAngle(lowerarmL3, -0.08726646259971647F, 0, -0.10471975511965977F);
        feetL4 = new MowzieModelRenderer(this, 14, 90);
        feetL4.setRotationPoint(0.5F, 0, 1.6F);
        feetL4.addBox(0, 0, 0, 1, 1, 2, 0);
        setRotationAngle(feetL4, 0, 0.4363323129985824F, 0);
        rightshoulder3 = new MowzieModelRenderer(this, 0, 51);
        rightshoulder3.setRotationPoint(2, 1, -2.9F);
        rightshoulder3.addBox(-3, -0.5F, 0.1F, 3, 1, 4, 0);
        setRotationAngle(rightshoulder3, 0.006981317007977318F, 0, 0.15707963267948966F);
        buttflapR = new MowzieModelRenderer(this, 9, 65);
        buttflapR.setRotationPoint(-1, 0.2F, 1);
        buttflapR.addBox(-1, 0, 0, 3, 3, 1, 0);
        setRotationAngle(buttflapR, 0.13962634015954636F, 0.08726646259971647F, -0.10471975511965977F);
        head5 = new MowzieModelRenderer(this, 14, 26);
        head5.setRotationPoint(0, 0, -1);
        head5.addBox(-1.9F, -0.8F, -1, 2, 1, 3, 0);
        setRotationAngle(head5, 0.24434609527920614F, 0.33161255787892263F, 0);
        wingL1 = new MowzieModelRenderer(this, 35, 22);
        wingL1.mirror = true;
        wingL1.setRotationPoint(0, -1.2F, -1.1F);
        wingL1.addBox(-0.3F, 0, 0, 1, 4, 3, 0);
        setRotationAngle(wingL1, 0.9773843811168246F, -0.0017453292519943296F, 0);
        upperlegL3 = new MowzieModelRenderer(this, 8, 100);
        upperlegL3.setRotationPoint(0.3F, 1.6F, 0);
        upperlegL3.addBox(-1, 0, -1, 1, 4, 2, 0);
        lowerlegR5 = new MowzieModelRenderer(this, 18, 83);
        lowerlegR5.setRotationPoint(-2, 5, 0);
        lowerlegR5.addBox(-1, 0, 0, 1, 2, 2, 0);
        wingstrutL2 = new MowzieModelRenderer(this, 40, 22);
        wingstrutL2.setRotationPoint(0.1F, -0.5F, 2);
        wingstrutL2.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(wingstrutL2, 0.05235987755982988F, 0.017453292519943295F, -0.017453292519943295F);
        wingL4 = new MowzieModelRenderer(this, 43, 23);
        wingL4.setRotationPoint(0, 2, 0);
        wingL4.addBox(-0.4F, -0.3F, -1, 1, 6, 2, 0);
        setRotationAngle(wingL4, -0.3665191429188092F, -0.08726646259971647F, 0.15707963267948966F);
        cockpit4 = new MowzieModelRenderer(this, 14, 47);
        cockpit4.setRotationPoint(0, 1, -2);
        cockpit4.addBox(0, 0, -3, 1, 1, 5, 0);
        setRotationAngle(cockpit4, 0.2792526803190927F, -0.17453292519943295F, 0.15707963267948966F);
        lowerarmR2 = new MowzieModelRenderer(this, 35, 35);
        lowerarmR2.mirror = true;
        lowerarmR2.setRotationPoint(-1, 4, 1.2F);
        lowerarmR2.addBox(0, -4, -1, 1, 4, 1, 0);
        setRotationAngle(lowerarmR2, -0.08726646259971647F, 0, -0.10471975511965977F);
        fistLbase = new MowzieModelRenderer(this, 39, 37);
        fistLbase.setRotationPoint(0, 4.1F, 0.3F);
        fistLbase.addBox(-1.5F, -0.2F, -1, 3, 1, 2, 0);
        setRotationAngle(fistLbase, 0.08726646259971647F, -1.3962634015954636F, 0);
        head8 = new MowzieModelRenderer(this, 14, 30);
        head8.setRotationPoint(0, -2, 2.7F);
        head8.addBox(-2, -0.3F, -1.4F, 4, 2, 1, 0);
        setRotationAngle(head8, -0.33161255787892263F, 0, 0);
        engineR7 = new MowzieModelRenderer(this, 4, 73);
        engineR7.setRotationPoint(0, -1.2F, 0.5F);
        engineR7.addBox(-1.5F, 0, -1.5F, 3, 1, 3, 0);
        back1 = new MowzieModelRenderer(this, 33, 49);
        back1.setRotationPoint(0, -6.9F, 1);
        back1.addBox(-2, -2, -0.1F, 4, 5, 2, 0);
        setRotationAngle(back1, -0.22514747350726852F, 0, 0);
        lowerarmR1 = new MowzieModelRenderer(this, 43, 31);
        lowerarmR1.setRotationPoint(0, 0, 0);
        lowerarmR1.addBox(-0.9F, -1.1F, 0.5F, 2, 2, 1, 0);
        setRotationAngle(lowerarmR1, -0.08726646259971647F, -0.08726646259971647F, -0.7853981633974483F);
        feetR6 = new MowzieModelRenderer(this, 0, 98);
        feetR6.setRotationPoint(-1, 1.6F, -3.3F);
        feetR6.addBox(0, 0, 0, 2, 1, 3, 0);
        setRotationAngle(feetR6, 0.47123889803846897F, 0, 0);
        torsoConnector = new MowzieModelRenderer(this, 0, 25);
        torsoConnector.setRotationPoint(0, 0, 0);
        torsoConnector.addBox(-1, -6, -1, 2, 6, 2, 0);
        lowerlegL1 = new MowzieModelRenderer(this, 16, 73);
        lowerlegL1.mirror = true;
        lowerlegL1.setRotationPoint(-1, 0, -2);
        lowerlegL1.addBox(0, 0, 0, 3, 5, 2, 0);
        setRotationAngle(lowerlegL1, -0.06981317007977318F, -0.3839724354387525F, 0);
        engineR2 = new MowzieModelRenderer(this, 10, 77);
        engineR2.setRotationPoint(0, 1, 3);
        engineR2.addBox(-1, 0, -1, 2, 2, 1, 0);
        setRotationAngle(engineR2, -0.19198621771937624F, 0, 0);
        fistRbase = new MowzieModelRenderer(this, 35, 42);
        fistRbase.setRotationPoint(0, 4, 0.3F);
        fistRbase.addBox(-0.8F, -0.2F, -1, 1, 2, 2, 0);
        setRotationAngle(fistRbase, -0.05235987755982988F, 0.017453292519943295F, -0.12217304763960307F);
        upperlegR2 = new MowzieModelRenderer(this, 8, 100);
        upperlegR2.mirror = true;
        upperlegR2.setRotationPoint(-0.3F, 1.6F, 0);
        upperlegR2.addBox(0, 0, -1, 1, 4, 2, 0);
        feetL1 = new MowzieModelRenderer(this, 3, 92);
        feetL1.setRotationPoint(0, 1.5F, 0);
        feetL1.addBox(-1.5F, 0, -3.4F, 3, 1, 5, 0);
        upperLegR = new MowzieModelRenderer(this, 0, 73);
        upperLegR.setRotationPoint(-1.5F, 1, 0);
        upperLegR.addBox(-1, -0.5F, -0.5F, 1, 7, 1, 0);
        setRotationAngle(upperLegR, -0.10471975511965977F, 0, 0.03490658503988659F);
        head7 = new MowzieModelRenderer(this, 21, 26);
        head7.setRotationPoint(0, 0.05F, -1.75F);
        head7.addBox(-1, -1.65F, -0.7F, 2, 2, 1, 0);
        setRotationAngle(head7, -0.9948376736367678F, 0, 0);
        finger1_2 = new MowzieModelRenderer(this, 46, 35);
        finger1_2.setRotationPoint(0, 1.7F, -0.2F);
        finger1_2.addBox(0, 0, 0, 1, 1, 1, 0);
        setRotationAngle(finger1_2, 0.8377580409572781F, 0, 0);
        wingR2 = new MowzieModelRenderer(this, 43, 23);
        wingR2.mirror = true;
        wingR2.setRotationPoint(0, 2, 0);
        wingR2.addBox(-0.6F, -0.3F, -1, 1, 6, 2, 0);
        setRotationAngle(wingR2, 0.05235987755982988F, -0.017453292519943295F, -0.08726646259971647F);
        upperlegR1 = new MowzieModelRenderer(this, 0, 102);
        upperlegR1.setRotationPoint(0, 0, 0);
        upperlegR1.addBox(-1.5F, -0.2F, -1, 2, 2, 2, 0);
        setRotationAngle(upperlegR1, 0.06981317007977318F, 0.20943951023931953F, -0.06981317007977318F);
        lowerlegR4 = new MowzieModelRenderer(this, 14, 80);
        lowerlegR4.setRotationPoint(-4, 0, 0);
        lowerlegR4.addBox(-1, 0, 0, 1, 3, 1, 0);
        setRotationAngle(lowerlegR4, 0, 0, -0.33161255787892263F);
        shoulderpieceL1 = new MowzieModelRenderer(this, 44, 16);
        shoulderpieceL1.mirror = true;
        shoulderpieceL1.setRotationPoint(0, 0.3F, 0);
        shoulderpieceL1.addBox(0, 0, -1, 1, 2, 2, 0);
        setRotationAngle(shoulderpieceL1, 0.06981317007977318F, 0, 0.10471975511965977F);
        finger4_2 = new MowzieModelRenderer(this, 46, 35);
        finger4_2.setRotationPoint(-0.5F, 1.7F, -0.2F);
        finger4_2.addBox(0, 0, 0, 1, 1, 1, 0);
        setRotationAngle(finger4_2, 0.8377580409572781F, 0, 0);
        engineL7 = new MowzieModelRenderer(this, 4, 73);
        engineL7.setRotationPoint(0, -1.2F, 0.5F);
        engineL7.addBox(-1.5F, 0, -1.5F, 3, 1, 3, 0);
        feetR4 = new MowzieModelRenderer(this, 14, 90);
        feetR4.mirror = true;
        feetR4.setRotationPoint(-0.5F, 0, 1.6F);
        feetR4.addBox(-1, 0, 0, 1, 1, 2, 0);
        setRotationAngle(feetR4, 0, -0.4363323129985824F, 0);
        head4 = new MowzieModelRenderer(this, 23, 21);
        head4.setRotationPoint(2, -3.3F, 0.5F);
        head4.addBox(-1, 0, -3, 1, 1, 3, 0);
        setRotationAngle(head4, 0.06981317007977318F, 0.15707963267948966F, 0.008726646259971648F);
        waistL1 = new MowzieModelRenderer(this, 0, 67);
        waistL1.setRotationPoint(1, 0.1F, 0);
        waistL1.addBox(0, 0, -0.5F, 2, 1, 2, 0);
        setRotationAngle(waistL1, 0, -0.05235987755982988F, -0.20943951023931953F);
        upperlegR3 = new MowzieModelRenderer(this, 8, 100);
        upperlegR3.setRotationPoint(-0.7F, 1.6F, 0);
        upperlegR3.addBox(-1, 0, -1, 1, 4, 2, 0);
        leftshoulder5 = new MowzieModelRenderer(this, 0, 62);
        leftshoulder5.mirror = true;
        leftshoulder5.setRotationPoint(0.1F, 0, 3);
        leftshoulder5.addBox(0, 0, 0, 3, 2, 2, 0);
        setRotationAngle(leftshoulder5, -0.4886921905584123F, 0, 0);
        wingstrutR3 = new MowzieModelRenderer(this, 40, 22);
        wingstrutR3.setRotationPoint(-0.1F, -1.2F, 3);
        wingstrutR3.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(wingstrutR3, 0.5759586531581287F, 0.017453292519943295F, -0.017453292519943295F);
        waistR1 = new MowzieModelRenderer(this, 0, 67);
        waistR1.setRotationPoint(-1, 0.1F, 0);
        waistR1.addBox(-2, 0, -0.5F, 2, 1, 2, 0);
        setRotationAngle(waistR1, 0, 0.05235987755982988F, 0.20943951023931953F);
        torsoL2 = new MowzieModelRenderer(this, 10, 60);
        torsoL2.mirror = true;
        torsoL2.setRotationPoint(0.4F, -3.1F, -0.9F);
        torsoL2.addBox(0, -0.7F, 0, 2, 4, 1, 0);
        setRotationAngle(torsoL2, 0.13962634015954636F, -0.20943951023931953F, 0.19198621771937624F);
        lowerlegR3 = new MowzieModelRenderer(this, 8, 84);
        lowerlegR3.setRotationPoint(-2.5F, 0.4F, 0.6F);
        lowerlegR3.addBox(-4, 0, 0, 4, 3, 1, 0);
        setRotationAngle(lowerlegR3, -0.17453292519943295F, 0.6632251157578453F, -0.17453292519943295F);
        head2 = new MowzieModelRenderer(this, 14, 22);
        head2.setRotationPoint(0, -3.3F, 0.5F);
        head2.addBox(-1.5F, 0, -3, 3, 1, 3, 0);
        setRotationAngle(head2, 0.06981317007977318F, 0, 0);
        rightshoulder2 = new MowzieModelRenderer(this, 0, 62);
        rightshoulder2.setRotationPoint(-3.1F, 0, 3);
        rightshoulder2.addBox(0, 0, 0, 3, 2, 2, 0);
        setRotationAngle(rightshoulder2, -0.4886921905584123F, 0, 0);
        leftshoulder4 = new MowzieModelRenderer(this, 6, 56);
        leftshoulder4.mirror = true;
        leftshoulder4.setRotationPoint(-1.1F, -1, -2.9F);
        leftshoulder4.addBox(0.1F, 0, 0, 3, 1, 3, 0);
        setRotationAngle(leftshoulder4, 0.20943951023931953F, -0.06981317007977318F, 0);
        head6 = new MowzieModelRenderer(this, 14, 26);
        head6.mirror = true;
        head6.setRotationPoint(0, 0, -1);
        head6.addBox(-0.1F, -0.8F, -1, 2, 1, 3, 0);
        setRotationAngle(head6, 0.24434609527920614F, -0.33161255787892263F, 0);
        lowerarmR3 = new MowzieModelRenderer(this, 35, 35);
        lowerarmR3.setRotationPoint(1, 4, 1.2F);
        lowerarmR3.addBox(-1, -4, -1, 1, 4, 1, 0);
        setRotationAngle(lowerarmR3, -0.08726646259971647F, 0, 0.10471975511965977F);
        wingR3 = new MowzieModelRenderer(this, 43, 23);
        wingR3.mirror = true;
        wingR3.setRotationPoint(0, 2, 0);
        wingR3.addBox(-0.6F, -0.3F, -1, 1, 6, 2, 0);
        setRotationAngle(wingR3, 0.05235987755982988F, -0.017453292519943295F, -0.08726646259971647F);
        feetbaseR = new MowzieModelRenderer(this, 8, 88);
        feetbaseR.setRotationPoint(0, 5.1F, -0.2F);
        feetbaseR.addBox(-1, 0, -0.9F, 2, 2, 2, 0);
        setRotationAngle(feetbaseR, -0.08726646259971647F, -0.017453292519943295F, -0.017453292519943295F);
        engineR6 = new MowzieModelRenderer(this, 0, 89);
        engineR6.mirror = true;
        engineR6.setRotationPoint(0, 2, 1);
        engineR6.addBox(-1, -3, -0.4F, 2, 5, 2, 0);
        setRotationAngle(engineR6, -0.2792526803190927F, -0.3490658503988659F, 0);
        lowerlegL4 = new MowzieModelRenderer(this, 18, 80);
        lowerlegL4.mirror = true;
        lowerlegL4.setRotationPoint(0, 0, 1);
        lowerlegL4.addBox(0, -2, -1, 3, 2, 1, 0);
        setRotationAngle(lowerlegL4, 0.4886921905584123F, 0, 0);
        buttflapL = new MowzieModelRenderer(this, 9, 65);
        buttflapL.mirror = true;
        buttflapL.setRotationPoint(1, 0.2F, 1);
        buttflapL.addBox(-2, 0, 0, 3, 3, 1, 0);
        setRotationAngle(buttflapL, 0.13962634015954636F, -0.08726646259971647F, 0.10471975511965977F);
        lowerlegR1 = new MowzieModelRenderer(this, 16, 73);
        lowerlegR1.setRotationPoint(1, 0, -2);
        lowerlegR1.addBox(-3, 0, 0, 3, 5, 2, 0);
        setRotationAngle(lowerlegR1, -0.06981317007977318F, 0.3839724354387525F, 0);
        feetR3 = new MowzieModelRenderer(this, 14, 90);
        feetR3.setRotationPoint(0.5F, 0, 1.6F);
        feetR3.addBox(0, 0, 0, 1, 1, 2, 0);
        setRotationAngle(feetR3, 0, 0.4363323129985824F, 0);
        torsoR2 = new MowzieModelRenderer(this, 10, 60);
        torsoR2.setRotationPoint(-0.4F, -3.1F, -0.9F);
        torsoR2.addBox(-2, -0.7F, 0, 2, 4, 1, 0);
        setRotationAngle(torsoR2, 0.13962634015954636F, 0.20943951023931953F, -0.19198621771937624F);
        wingbaseR = new MowzieModelRenderer(this, 35, 17);
        wingbaseR.setRotationPoint(-1.6F, 0, 0);
        wingbaseR.addBox(-0.7F, -1.2F, -1.1F, 1, 2, 3, 0);
        setRotationAngle(wingbaseR, 0.17453292519943295F, -0.45378560551852565F, -0.08726646259971647F);
        engineL5 = new MowzieModelRenderer(this, 8, 80);
        engineL5.setRotationPoint(-1.5F, 1, 1.5F);
        engineL5.addBox(0, 0, -1, 1, 2, 2, 0);
        setRotationAngle(engineL5, 0, 0, -0.19198621771937624F);
        wingL3 = new MowzieModelRenderer(this, 43, 23);
        wingL3.setRotationPoint(0, 2, 0);
        wingL3.addBox(-0.4F, -0.3F, -1, 1, 6, 2, 0);
        setRotationAngle(wingL3, 0.05235987755982988F, 0.017453292519943295F, 0.08726646259971647F);
        wasit3 = new MowzieModelRenderer(this, 10, 17);
        wasit3.mirror = true;
        wasit3.setRotationPoint(0.7F, -0.4F, -1.9F);
        wasit3.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
        lowerArmR = new MowzieModelRenderer(this, 35, 29);
        lowerArmR.setRotationPoint(0, 4.1F, 0);
        lowerArmR.addBox(-1, 0, -0.8F, 2, 4, 2, 0);
        setRotationAngle(lowerArmR, -0.08726646259971647F, -0.08726646259971647F, -0.10471975511965977F);
        cockpit2 = new MowzieModelRenderer(this, 21, 40);
        cockpit2.setRotationPoint(0, -0.1F, -3);
        cockpit2.addBox(0, -0.1F, -1.7F, 1, 2, 2, 0);
        setRotationAngle(cockpit2, 0, 0, 0.08726646259971647F);
        finger3_1 = new MowzieModelRenderer(this, 35, 40);
        finger3_1.setRotationPoint(-1.2F, 0.25F, 0.4F);
        finger3_1.addBox(-0.4F, -0.2F, -0.4F, 1, 1, 1, 0);
        setRotationAngle(finger3_1, 0.17453292519943295F, 0.06981317007977318F, 1.4311699866353502F);
        finger4_1 = new MowzieModelRenderer(this, 42, 34);
        finger4_1.setRotationPoint(0, 0.8F, -0.8F);
        finger4_1.addBox(-0.5F, -0.3F, -0.2F, 1, 2, 1, 0);
        setRotationAngle(finger4_1, -0.08726646259971647F, 0, 0);
        upperLegL = new MowzieModelRenderer(this, 0, 73);
        upperLegL.mirror = true;
        upperLegL.setRotationPoint(1.5F, 1, 0);
        upperLegL.addBox(0, -0.5F, -0.5F, 1, 7, 1, 0);
        setRotationAngle(upperLegL, -0.10471975511965977F, 0, -0.03490658503988659F);
        wingstrutL3 = new MowzieModelRenderer(this, 40, 22);
        wingstrutL3.setRotationPoint(0.1F, -1.2F, 3);
        wingstrutL3.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(wingstrutL3, 0.5759586531581287F, 0.017453292519943295F, -0.017453292519943295F);
        upperArmR = new MowzieModelRenderer(this, 40, 15);
        upperArmR.setRotationPoint(-4, 0.5F, 0.5F);
        upperArmR.addBox(-0.5F, 0.3F, -0.5F, 1, 4, 1, 0);
        setRotationAngle(upperArmR, 0, 0.10471975511965977F, 0.15707963267948966F);
        finger1_1 = new MowzieModelRenderer(this, 42, 34);
        finger1_1.setRotationPoint(0.5F, 0.8F, -0.8F);
        finger1_1.addBox(0, -0.3F, -0.2F, 1, 2, 1, 0);
        setRotationAngle(finger1_1, -0.06981317007977318F, 0, -0.13962634015954636F);
        waist4 = new MowzieModelRenderer(this, 10, 17);
        waist4.mirror = true;
        waist4.setRotationPoint(2, -0.5F, -1.7F);
        waist4.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
        setRotationAngle(waist4, 0, -0.2617993877991494F, -0.15707963267948966F);
        cockpit1 = new MowzieModelRenderer(this, 21, 48);
        cockpit1.setRotationPoint(0.5F, 0, -2);
        cockpit1.addBox(0, 0, -3, 2, 1, 3, 0);
        setRotationAngle(cockpit1, 0.6108652381980153F, 0, 0);
        neck2 = new MowzieModelRenderer(this, 16, 35);
        neck2.setRotationPoint(-0.5F, -1.2F, -2);
        neck2.addBox(0, 0, 0, 1, 2, 3, 0);
        setRotationAngle(neck2, 0, -0.33161255787892263F, 0);
        wingconnectorL = new MowzieModelRenderer(this, 44, 20);
        wingconnectorL.setRotationPoint(0.6F, 1, 0);
        wingconnectorL.addBox(-0.3F, -0.5F, -0.5F, 2, 1, 1, 0);
        setRotationAngle(wingconnectorL, -0.03490658503988659F, -0.5235987755982988F, -0.24434609527920614F);
        upperlegL2 = new MowzieModelRenderer(this, 8, 100);
        upperlegL2.mirror = true;
        upperlegL2.setRotationPoint(0.7F, 1.6F, 0);
        upperlegL2.addBox(0, 0, -1, 1, 4, 2, 0);
        head3 = new MowzieModelRenderer(this, 23, 21);
        head3.setRotationPoint(-2, -3.3F, 0.5F);
        head3.addBox(0, 0, -3, 1, 1, 3, 0);
        setRotationAngle(head3, 0.06981317007977318F, -0.15707963267948966F, -0.008726646259971648F);
        engineR4 = new MowzieModelRenderer(this, 8, 80);
        engineR4.setRotationPoint(-1.5F, 1, 1.5F);
        engineR4.addBox(0, 0, -1, 1, 2, 2, 0);
        setRotationAngle(engineR4, 0, 0, -0.19198621771937624F);
        upperlegL1 = new MowzieModelRenderer(this, 0, 102);
        upperlegL1.mirror = true;
        upperlegL1.setRotationPoint(0, 0, 0);
        upperlegL1.addBox(-0.5F, -0.2F, -1, 2, 2, 2, 0);
        setRotationAngle(upperlegL1, 0.06981317007977318F, -0.20943951023931953F, 0.06981317007977318F);
        feetR1 = new MowzieModelRenderer(this, 3, 92);
        feetR1.mirror = true;
        feetR1.setRotationPoint(0, 1.5F, 0);
        feetR1.addBox(-1.5F, 0, -3.4F, 3, 1, 5, 0);
        cockpit3 = new MowzieModelRenderer(this, 21, 40);
        cockpit3.mirror = true;
        cockpit3.setRotationPoint(2, -0.1F, -3);
        cockpit3.addBox(-1, -0.1F, -1.7F, 1, 2, 2, 0);
        setRotationAngle(cockpit3, 0, 0, -0.08726646259971647F);
        engineL4 = new MowzieModelRenderer(this, 8, 80);
        engineL4.mirror = true;
        engineL4.setRotationPoint(1.5F, 1, 1.5F);
        engineL4.addBox(-1, 0, -1, 1, 2, 2, 0);
        setRotationAngle(engineL4, 0, 0, 0.19198621771937624F);
        cockpit5 = new MowzieModelRenderer(this, 14, 47);
        cockpit5.mirror = true;
        cockpit5.setRotationPoint(3, 1, -2);
        cockpit5.addBox(-1, 0, -3, 1, 1, 5, 0);
        setRotationAngle(cockpit5, 0.2792526803190927F, 0.17453292519943295F, -0.15707963267948966F);
        waistL2 = new MowzieModelRenderer(this, 18, 62);
        waistL2.setRotationPoint(1, 0.1F, -1);
        waistL2.addBox(0, 0, -0.5F, 2, 1, 1, 0);
        setRotationAngle(waistL2, 0, -0.5585053606381855F, -0.20943951023931953F);
        leftshoulder1 = new MowzieModelRenderer(this, 0, 51);
        leftshoulder1.mirror = true;
        leftshoulder1.setRotationPoint(-1, 1, -2.9F);
        leftshoulder1.addBox(0, -0.5F, 0.1F, 3, 1, 4, 0);
        setRotationAngle(leftshoulder1, 0.006981317007977318F, 0, -0.15707963267948966F);
        finger2_1 = new MowzieModelRenderer(this, 42, 34);
        finger2_1.setRotationPoint(-0.5F, 0.8F, -0.8F);
        finger2_1.addBox(-1, -0.3F, -0.2F, 1, 2, 1, 0);
        setRotationAngle(finger2_1, -0.10471975511965977F, 0, 0.13962634015954636F);
        wingR1 = new MowzieModelRenderer(this, 35, 22);
        wingR1.setRotationPoint(0, -1.2F, -1.1F);
        wingR1.addBox(-0.7F, 0, 0, 1, 4, 3, 0);
        setRotationAngle(wingR1, 0.9773843811168246F, 0.0017453292519943296F, 0);
        feetl6 = new MowzieModelRenderer(this, 14, 93);
        feetl6.setRotationPoint(-0.9F, 0.5F, 0);
        feetl6.addBox(-0.4F, -0.1F, -0.6F, 1, 2, 2, 0);
        setRotationAngle(feetl6, 0.10471975511965977F, -0.06981317007977318F, 0.22689280275926282F);
        waist2 = new MowzieModelRenderer(this, 10, 17);
        waist2.setRotationPoint(-0.7F, -0.4F, -1.9F);
        waist2.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
        back2 = new MowzieModelRenderer(this, 23, 53);
        back2.setRotationPoint(0, 2, 1.9F);
        back2.addBox(-1.5F, 0, -2, 3, 3, 2, 0);
        setRotationAngle(back2, -0.06981317007977318F, 0, 0);
        lowerlegL5 = new MowzieModelRenderer(this, 18, 83);
        lowerlegL5.mirror = true;
        lowerlegL5.setRotationPoint(2, 5, 0);
        lowerlegL5.addBox(0, 0, 0, 1, 2, 2, 0);
        waist = new MowzieModelRenderer(this, 0, 20);
        waist.setRotationPoint(0, 9.5F, 0);
        waist.addBox(-1.5F, 0, -1.5F, 3, 2, 3, 0);
        upperArmL = new MowzieModelRenderer(this, 40, 15);
        upperArmL.mirror = true;
        upperArmL.setRotationPoint(4, 0.5F, 0.5F);
        upperArmL.addBox(-0.5F, 0.3F, -0.5F, 1, 4, 1, 0);
        setRotationAngle(upperArmL, 0, -0.10471975511965977F, -0.15707963267948966F);
        engineR3 = new MowzieModelRenderer(this, 8, 80);
        engineR3.mirror = true;
        engineR3.setRotationPoint(1.5F, 1, 1.5F);
        engineR3.addBox(-1, 0, -1, 1, 2, 2, 0);
        setRotationAngle(engineR3, 0, 0, 0.19198621771937624F);
        feetL2 = new MowzieModelRenderer(this, 14, 88);
        feetL2.setRotationPoint(-1.5F, 0, -3.4F);
        feetL2.addBox(0, 0, -1, 3, 1, 1, 0);
        setRotationAngle(feetL2, 1.0122909661567112F, 0, 0);
        waist5 = new MowzieModelRenderer(this, 10, 17);
        waist5.setRotationPoint(-2, -0.5F, -1.7F);
        waist5.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
        setRotationAngle(waist5, 0, 0.2617993877991494F, 0.15707963267948966F);
        lowerlegL2 = new MowzieModelRenderer(this, 8, 84);
        lowerlegL2.mirror = true;
        lowerlegL2.setRotationPoint(2.5F, 0.4F, 0.6F);
        lowerlegL2.addBox(0, 0, 0, 4, 3, 1, 0);
        setRotationAngle(lowerlegL2, -0.17453292519943295F, -0.6632251157578453F, 0.17453292519943295F);
        neck3 = new MowzieModelRenderer(this, 16, 35);
        neck3.setRotationPoint(0.5F, -1.2F, -2);
        neck3.addBox(-1, 0, 0, 1, 2, 3, 0);
        setRotationAngle(neck3, 0, 0.33161255787892263F, 0);
        finger3_2 = new MowzieModelRenderer(this, 39, 40);
        finger3_2.setRotationPoint(-0.4F, 1, 0);
        finger3_2.addBox(0, -0.5F, -0.4F, 2, 1, 1, 0);
        setRotationAngle(finger3_2, 0, 0, -0.10471975511965977F);
        leftshoulderbase = new MowzieModelRenderer(this, 0, 46);
        leftshoulderbase.mirror = true;
        leftshoulderbase.setRotationPoint(3, 0.15F, 1.4F);
        leftshoulderbase.addBox(-1, -1, -2.9F, 3, 1, 4, 0);
        setRotationAngle(leftshoulderbase, -0.06981317007977318F, -0.17453292519943295F, -0.05235987755982988F);
        head9 = new MowzieModelRenderer(this, 24, 17);
        head9.setRotationPoint(0, -0.8F, -0.8F);
        head9.addBox(-2.2F, -1.9F, 0.1F, 1, 2, 2, 0);
        setRotationAngle(head9, 0, 0.08726646259971647F, 0);
        upperBodyBase = new MowzieModelRenderer(this, 0, 33);
        upperBodyBase.setRotationPoint(0, -6, 0);
        upperBodyBase.addBox(-2, -2, -1.5F, 4, 3, 4, 0);
        setRotationAngle(upperBodyBase, 0.45378560551852565F, 0, 0);
        wingstrutR1 = new MowzieModelRenderer(this, 40, 22);
        wingstrutR1.setRotationPoint(-0.1F, 0, -0.4F);
        wingstrutR1.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(wingstrutR1, 0, 0.08726646259971647F, -0.03490658503988659F);
        wingR4 = new MowzieModelRenderer(this, 43, 23);
        wingR4.mirror = true;
        wingR4.setRotationPoint(0, 2, 0);
        wingR4.addBox(-0.6F, -0.3F, -1, 1, 6, 2, 0);
        setRotationAngle(wingR4, -0.3665191429188092F, 0.08726646259971647F, -0.15707963267948966F);
        engineL1 = new MowzieModelRenderer(this, 4, 73);
        engineL1.setRotationPoint(0, 3.9F, 0);
        engineL1.addBox(-1.5F, 0, 0, 3, 1, 3, 0);
        setRotationAngle(engineL1, 0.3490658503988659F, 0.017453292519943295F, 0.017453292519943295F);
        feetR5 = new MowzieModelRenderer(this, 14, 93);
        feetR5.mirror = true;
        feetR5.setRotationPoint(0.9F, 0.5F, 0);
        feetR5.addBox(-0.6F, -0.1F, -0.6F, 1, 2, 2, 0);
        setRotationAngle(feetR5, 0.10471975511965977F, 0.08726646259971647F, -0.22689280275926282F);
        lowerArmL = new MowzieModelRenderer(this, 35, 29);
        lowerArmL.mirror = true;
        lowerArmL.setRotationPoint(0, 4.1F, 0);
        lowerArmL.addBox(-1, 0, -0.8F, 2, 4, 2, 0);
        setRotationAngle(lowerArmL, -0.08726646259971647F, 0.08726646259971647F, 0.10471975511965977F);
        feetbaseL = new MowzieModelRenderer(this, 8, 88);
        feetbaseL.setRotationPoint(0, 5.1F, -0.2F);
        feetbaseL.addBox(-1, 0, -0.9F, 2, 2, 2, 0);
        setRotationAngle(feetbaseL, -0.08726646259971647F, 0.017453292519943295F, 0.017453292519943295F);

        waist.addChild(waist1);
        fistRbase.addChild(fistR2);
        upperLegR.addChild(lowerlegRbase);
        rightshoulder3.addChild(rightshoulder4);
        lowerArmL.addChild(lowerarmL2);
        torsoConnector.addChild(torsoL1);
        feetR1.addChild(feetR2);
        engineL1.addChild(engineL3);
        rightshoulderbase.addChild(rightshoulder5);
        upperLegL.addChild(lowerlegLbase);
        feetbaseL.addChild(feetL5);
        wingstrutL1.addChild(wingL2);
        lowerArmL.addChild(lowerarmL1);
        wingconnectorL.addChild(wingbaseL);
        leftshoulder1.addChild(leftshoulder2);
        finger2_1.addChild(finger2_2);
        wingbaseR.addChild(wingstrutR2);
        shoulderpieceR1.addChild(wingconnectorR);
        leftshoulderbase.addChild(leftshoulder3);
        head.addChild(head1);
        torsoConnector.addChild(torsoR1);
        upperBodyBase.addChild(chestcockpitbase);
        waist.addChild(waistR2);
        shoulderbase.addChild(rightshoulderbase);
        rightshoulderbase.addChild(rightshoulder1);
        engineL1.addChild(engineL2);
        lowerlegR1.addChild(lowerlegR2);
        upperArmR.addChild(shoulderpieceR1);
        head.addChild(head10);
        lowerlegL2.addChild(lowerlegL3);
        upperBodyBase.addChild(shoulderbase);
        lowerlegLbase.addChild(engineL6);
        shoulderbase.addChild(neck1);
        fistLbase.addChild(fistL1);
        feetL1.addChild(feetL3);
        wingbaseL.addChild(wingstrutL1);
        lowerlegRbase.addChild(engineR1);
        neck1.addChild(head);
        back2.addChild(back3);
        engineR1.addChild(engineR5);
        lowerArmL.addChild(lowerarmL3);
        feetL1.addChild(feetL4);
        rightshoulderbase.addChild(rightshoulder3);
        waistR1.addChild(buttflapR);
        head.addChild(head5);
        wingbaseL.addChild(wingL1);
        upperLegL.addChild(upperlegL3);
        lowerlegR1.addChild(lowerlegR5);
        wingbaseL.addChild(wingstrutL2);
        wingstrutL3.addChild(wingL4);
        chestcockpitbase.addChild(cockpit4);
        lowerArmR.addChild(lowerarmR2);
        lowerArmL.addChild(fistLbase);
        head.addChild(head8);
        engineR6.addChild(engineR7);
        torsoConnector.addChild(back1);
        lowerArmR.addChild(lowerarmR1);
        feetbaseR.addChild(feetR6);
        waist.addChild(torsoConnector);
        lowerlegLbase.addChild(lowerlegL1);
        engineR1.addChild(engineR2);
        lowerArmR.addChild(fistRbase);
        upperLegR.addChild(upperlegR2);
        feetbaseL.addChild(feetL1);
        waist.addChild(upperLegR);
        head.addChild(head7);
        finger1_1.addChild(finger1_2);
        wingstrutR1.addChild(wingR2);
        upperLegR.addChild(upperlegR1);
        lowerlegR3.addChild(lowerlegR4);
        upperArmL.addChild(shoulderpieceL1);
        finger4_1.addChild(finger4_2);
        engineL6.addChild(engineL7);
        feetR1.addChild(feetR4);
        head.addChild(head4);
        waist.addChild(waistL1);
        upperLegR.addChild(upperlegR3);
        leftshoulder4.addChild(leftshoulder5);
        wingbaseR.addChild(wingstrutR3);
        waist.addChild(waistR1);
        torsoConnector.addChild(torsoL2);
        lowerlegR1.addChild(lowerlegR3);
        head.addChild(head2);
        rightshoulder1.addChild(rightshoulder2);
        leftshoulderbase.addChild(leftshoulder4);
        head.addChild(head6);
        lowerArmR.addChild(lowerarmR3);
        wingstrutR2.addChild(wingR3);
        lowerlegRbase.addChild(feetbaseR);
        lowerlegRbase.addChild(engineR6);
        lowerlegL1.addChild(lowerlegL4);
        waistL1.addChild(buttflapL);
        lowerlegRbase.addChild(lowerlegR1);
        feetR1.addChild(feetR3);
        torsoConnector.addChild(torsoR2);
        wingconnectorR.addChild(wingbaseR);
        engineL1.addChild(engineL5);
        wingstrutL2.addChild(wingL3);
        waist.addChild(wasit3);
        upperArmR.addChild(lowerArmR);
        cockpit1.addChild(cockpit2);
        fistLbase.addChild(finger3_1);
        fistLbase.addChild(finger4_1);
        waist.addChild(upperLegL);
        wingbaseL.addChild(wingstrutL3);
        shoulderbase.addChild(upperArmR);
        fistLbase.addChild(finger1_1);
        waist.addChild(waist4);
        chestcockpitbase.addChild(cockpit1);
        shoulderbase.addChild(neck2);
        shoulderpieceL1.addChild(wingconnectorL);
        upperLegL.addChild(upperlegL2);
        head.addChild(head3);
        engineR1.addChild(engineR4);
        upperLegL.addChild(upperlegL1);
        feetbaseR.addChild(feetR1);
        cockpit1.addChild(cockpit3);
        engineL1.addChild(engineL4);
        chestcockpitbase.addChild(cockpit5);
        waist.addChild(waistL2);
        leftshoulderbase.addChild(leftshoulder1);
        fistLbase.addChild(finger2_1);
        wingbaseR.addChild(wingR1);
        feetbaseL.addChild(feetl6);
        waist.addChild(waist2);
        back1.addChild(back2);
        lowerlegL1.addChild(lowerlegL5);
        shoulderbase.addChild(upperArmL);
        engineR1.addChild(engineR3);
        feetL1.addChild(feetL2);
        waist.addChild(waist5);
        lowerlegL1.addChild(lowerlegL2);
        shoulderbase.addChild(neck3);
        finger3_1.addChild(finger3_2);
        shoulderbase.addChild(leftshoulderbase);
        head.addChild(head9);
        torsoConnector.addChild(upperBodyBase);
        wingbaseR.addChild(wingstrutR1);
        wingstrutR3.addChild(wingR4);
        lowerlegLbase.addChild(engineL1);
        feetbaseR.addChild(feetR5);
        upperArmL.addChild(lowerArmL);
        lowerlegLbase.addChild(feetbaseL);

        vehiclewingFrontR = new ModelRenderer(this, 26, 85);
        vehiclewingFrontR.setRotationPoint(0, 8, 0);
        vehiclewingFrontR.addBox(0, 0, -1, 1, 2, 9, 0);
        setRotationAngle(vehiclewingFrontR, 0.5061454830783556F, 0, -0.017453292519943295F);
        vehiclerearSlantR = new ModelRenderer(this, 43, 69);
        vehiclerearSlantR.setRotationPoint(3, 5, 0);
        vehiclerearSlantR.addBox(-1, 0, 0, 1, 4, 1, 0);
        setRotationAngle(vehiclerearSlantR, 0.20943951023931953F, 0, -0.017453292519943295F);
        vehicletailLFront = new ModelRenderer(this, 53, 98);
        vehicletailLFront.setRotationPoint(0.5F, -2, 0);
        vehicletailLFront.addBox(-5, 0, 0, 5, 2, 1, 0);
        setRotationAngle(vehicletailLFront, -0.008726646259971648F, -0.005235987755982988F, -0.41887902047863906F);
        vehicletailWingFlapR = new ModelRenderer(this, 58, 74);
        vehicletailWingFlapR.setRotationPoint(-0.5F, -3.5F, 2);
        vehicletailWingFlapR.addBox(0, 0, -2, 1, 3, 2, 0);
        setRotationAngle(vehicletailWingFlapR, 0.5235987755982988F, 0.017453292519943295F, 0);
        vehiclecockpitR = new ModelRenderer(this, 43, 69);
        vehiclecockpitR.setRotationPoint(0, 0, 0);
        vehiclecockpitR.addBox(0, 0, -6, 1, 2, 6, 0);
        setRotationAngle(vehiclecockpitR, 0.2792526803190927F, -0.06981317007977318F, -0.017453292519943295F);
        vehiclelowerlegLbase = new ModelRenderer(this, 0, 81);
        vehiclelowerlegLbase.setRotationPoint(0.5F, 4, 0);
        vehiclelowerlegLbase.addBox(-1, 0, -1.5F, 2, 6, 2, 0);
        vehiclelowerarmL3 = new ModelRenderer(this, 35, 35);
        vehiclelowerarmL3.mirror = true;
        vehiclelowerarmL3.setRotationPoint(-1, 4, 1.2F);
        vehiclelowerarmL3.addBox(0, -4, -1, 1, 4, 1, 0);
        setRotationAngle(vehiclelowerarmL3, -0.08726646259971647F, 0, -0.10471975511965977F);
        vehiclewingFlapR = new ModelRenderer(this, 39, 88);
        vehiclewingFlapR.setRotationPoint(0, 0, 0);
        vehiclewingFlapR.addBox(0, -2, -1, 1, 2, 8, 0);
        setRotationAngle(vehiclewingFlapR, -0.23561944901923448F, -0.017453292519943295F, 0);
        vehiclelowerlegL3 = new ModelRenderer(this, 14, 80);
        vehiclelowerlegL3.mirror = true;
        vehiclelowerlegL3.setRotationPoint(4, 0, 0);
        vehiclelowerlegL3.addBox(0, 0, 0, 1, 3, 1, 0);
        setRotationAngle(vehiclelowerlegL3, 0, 0, 0.33161255787892263F);
        vehicleUpperlegLbase = new ModelRenderer(this, 0, 73);
        vehicleUpperlegLbase.mirror = true;
        vehicleUpperlegLbase.setRotationPoint(1.5F, 1, -4);
        vehicleUpperlegLbase.addBox(0, -0.5F, -0.5F, 1, 7, 1, 0);
        setRotationAngle(vehicleUpperlegLbase, 1.5707963267948966F, 0, 0);
        vehicletailRFront = new ModelRenderer(this, 53, 98);
        vehicletailRFront.mirror = true;
        vehicletailRFront.setRotationPoint(-0.5F, -2, 0);
        vehicletailRFront.addBox(0, 0, 0, 5, 2, 1, 0);
        setRotationAngle(vehicletailRFront, -0.008726646259971648F, 0.005235987755982988F, 0.41887902047863906F);
        vehicleBottomCockpitR = new ModelRenderer(this, 49, 88);
        vehicleBottomCockpitR.setRotationPoint(-2, 0.7F, -1.5F);
        vehicleBottomCockpitR.addBox(0, -2, -6, 1, 2, 6, 0);
        setRotationAngle(vehicleBottomCockpitR, -0.0471238898038469F, -0.16929693744344995F, 0);
        vehicleTorsoConnector = new ModelRenderer(this, 0, 25);
        vehicleTorsoConnector.setRotationPoint(0, 2, 0);
        vehicleTorsoConnector.addBox(-1, -6, -1, 2, 6, 2, 0);
        setRotationAngle(vehicleTorsoConnector, 1.5707963267948966F, 0, 0);
        vehiclewingFlapL = new ModelRenderer(this, 39, 88);
        vehiclewingFlapL.mirror = true;
        vehiclewingFlapL.setRotationPoint(0, 0, 0);
        vehiclewingFlapL.addBox(0, -2, -1, 1, 2, 8, 0);
        setRotationAngle(vehiclewingFlapL, -0.23561944901923448F, 0.017453292519943295F, 0);
        vehiclewingR3 = new ModelRenderer(this, 43, 23);
        vehiclewingR3.mirror = true;
        vehiclewingR3.setRotationPoint(0, 2, -0.4F);
        vehiclewingR3.addBox(-0.6F, -2.3F, -0.2F, 1, 6, 2, 0);
        setRotationAngle(vehiclewingR3, -0.41887902047863906F, -0.03490658503988659F, 0.013962634015954637F);
        vehiclelowerlegR4 = new ModelRenderer(this, 14, 80);
        vehiclelowerlegR4.setRotationPoint(-4, 0, 0);
        vehiclelowerlegR4.addBox(-1, 0, 0, 1, 3, 1, 0);
        setRotationAngle(vehiclelowerlegR4, 0, 0, -0.33161255787892263F);
        vehicleWaist1 = new ModelRenderer(this, 6, 14);
        vehicleWaist1.setRotationPoint(0, 1.4F, -1.1F);
        vehicleWaist1.addBox(-1, 0, 0, 2, 1, 2, 0);
        setRotationAngle(vehicleWaist1, -0.017453292519943295F, 0, 0);
        vehiclelowerarmR3 = new ModelRenderer(this, 35, 35);
        vehiclelowerarmR3.setRotationPoint(1, 4, 1.2F);
        vehiclelowerarmR3.addBox(-1, -4, -1, 1, 4, 1, 0);
        setRotationAngle(vehiclelowerarmR3, -0.08726646259971647F, 0, 0.10471975511965977F);
        vehiclecockpit3 = new ModelRenderer(this, 21, 40);
        vehiclecockpit3.mirror = true;
        vehiclecockpit3.setRotationPoint(2.1F, 0.2F, -3.3F);
        vehiclecockpit3.addBox(-1, -0.1F, -1.6F, 1, 2, 2, 0);
        setRotationAngle(vehiclecockpit3, 0.2617993877991494F, 0, 0);
        vehiclelowerCockpitRearR = new ModelRenderer(this, 37, 89);
        vehiclelowerCockpitRearR.setRotationPoint(-2, -2.3F, -1.5F);
        vehiclelowerCockpitRearR.addBox(0, 0, -4, 1, 1, 4, 0);
        setRotationAngle(vehiclelowerCockpitRearR, 0.07853981633974483F, -0.15707963267948966F, 0);
        vehiclelowerlegL4 = new ModelRenderer(this, 18, 80);
        vehiclelowerlegL4.mirror = true;
        vehiclelowerlegL4.setRotationPoint(0, 0, 0);
        vehiclelowerlegL4.addBox(0, -2, 0, 3, 2, 1, 0);
        setRotationAngle(vehiclelowerlegL4, -0.3665191429188092F, 0, 0);
        vehiclecockpitL = new ModelRenderer(this, 43, 69);
        vehiclecockpitL.mirror = true;
        vehiclecockpitL.setRotationPoint(3, 0, 0);
        vehiclecockpitL.addBox(-1, 0, -6, 1, 2, 6, 0);
        setRotationAngle(vehiclecockpitL, 0.2792526803190927F, 0.06981317007977318F, 0.017453292519943295F);
        vehiclewingTipL = new ModelRenderer(this, 28, 86);
        vehiclewingTipL.mirror = true;
        vehiclewingTipL.setRotationPoint(0.1F, 0, 7);
        vehiclewingTipL.addBox(0, -0.4F, 0, 1, 6, 2, 0);
        setRotationAngle(vehiclewingTipL, -0.17976891295541594F, 0, 0.012217304763960306F);
        vehicletorsoL2 = new ModelRenderer(this, 10, 60);
        vehicletorsoL2.mirror = true;
        vehicletorsoL2.setRotationPoint(0.4F, -3.1F, -0.9F);
        vehicletorsoL2.addBox(0, -0.7F, 0, 2, 4, 1, 0);
        setRotationAngle(vehicletorsoL2, 0.13962634015954636F, -0.20943951023931953F, 0.19198621771937624F);
        vehicleBody = new ModelRenderer(this, 0, 20);
        vehicleBody.setRotationPoint(0, 0, 0);
        vehicleBody.addBox(-1.5F, 0, -1.5F, 3, 2, 3, 0);
        vehiclewingCockpitConnectorR = new ModelRenderer(this, 38, 81);
        vehiclewingCockpitConnectorR.setRotationPoint(-1, -1, -2.9F);
        vehiclewingCockpitConnectorR.addBox(0, 0.1F, -4, 3, 1, 4, 0);
        setRotationAngle(vehiclewingCockpitConnectorR, 0.08726646259971647F, -0.8552113334772213F, -0.05235987755982988F);
        vehiclerearTailConnectorR = new ModelRenderer(this, 30, 79);
        vehiclerearTailConnectorR.setRotationPoint(-3, 0, 7);
        vehiclerearTailConnectorR.addBox(0, 0, 0, 2, 2, 4, 0);
        setRotationAngle(vehiclerearTailConnectorR, -0.006981317007977318F, 0.13962634015954636F, 0);
        vehiclewingConnectorR1 = new ModelRenderer(this, 29, 96);
        vehiclewingConnectorR1.setRotationPoint(0.5F, 5, 0);
        vehiclewingConnectorR1.addBox(0, 0, -1, 1, 3, 8, 0);
        setRotationAngle(vehiclewingConnectorR1, 0.296705972839036F, -0.06981317007977318F, -0.020943951023931952F);
        vehiclewingL2 = new ModelRenderer(this, 43, 23);
        vehiclewingL2.setRotationPoint(0.2F, 1, 2.2F);
        vehiclewingL2.addBox(-0.6F, -0.7F, -1.2F, 1, 6, 2, 0);
        setRotationAngle(vehiclewingL2, -1.3089969389957472F, -0.017453292519943295F, 0);
        vehiclefeetL4 = new ModelRenderer(this, 14, 90);
        vehiclefeetL4.setRotationPoint(-4, 2, 6);
        vehiclefeetL4.addBox(-1, 0, 0, 1, 1, 2, 0);
        setRotationAngle(vehiclefeetL4, 0, 0, -1.5707963267948966F);
        vehicletailWingFlapL = new ModelRenderer(this, 58, 74);
        vehicletailWingFlapL.mirror = true;
        vehicletailWingFlapL.setRotationPoint(-0.5F, -3.5F, 2);
        vehicletailWingFlapL.addBox(0, 0, -2, 1, 3, 2, 0);
        setRotationAngle(vehicletailWingFlapL, 0.5235987755982988F, -0.017453292519943295F, 0);
        vehicleleftshoulder1 = new ModelRenderer(this, 0, 51);
        vehicleleftshoulder1.mirror = true;
        vehicleleftshoulder1.setRotationPoint(-1, 1.5F, -3);
        vehicleleftshoulder1.addBox(0, -0.5F, 0.1F, 3, 1, 4, 0);
        setRotationAngle(vehicleleftshoulder1, 0.006981317007977318F, 0.017453292519943295F, -0.15707963267948966F);
        vehiclewingCockpitConnectorL = new ModelRenderer(this, 38, 81);
        vehiclewingCockpitConnectorL.setRotationPoint(2, -1, -2.9F);
        vehiclewingCockpitConnectorL.addBox(-3, 0.1F, -4, 3, 1, 4, 0);
        setRotationAngle(vehiclewingCockpitConnectorL, 0.08726646259971647F, 0.8552113334772213F, 0.05235987755982988F);
        vehiclelowerRear = new ModelRenderer(this, 44, 58);
        vehiclelowerRear.setRotationPoint(-3, 2, -1.6F);
        vehiclelowerRear.addBox(0, 0, 0, 6, 5, 1, 0);
        setRotationAngle(vehiclelowerRear, 0.06981317007977318F, 0, 0);
        vehiclefeetR1 = new ModelRenderer(this, 3, 92);
        vehiclefeetR1.mirror = true;
        vehiclefeetR1.setRotationPoint(2, -1.6F, 0.8F);
        vehiclefeetR1.addBox(-1.5F, 0, -3.4F, 3, 1, 5, 0);
        vehiclelowerarmbaseR = new ModelRenderer(this, 35, 29);
        vehiclelowerarmbaseR.setRotationPoint(2, 10.2F, 1.2F);
        vehiclelowerarmbaseR.addBox(-1, 0, -0.8F, 2, 4, 2, 0);
        setRotationAngle(vehiclelowerarmbaseR, 0.05235987755982988F, -0.19198621771937624F, -0.05235987755982988F);
        vehiclerearTailConnectorL = new ModelRenderer(this, 30, 79);
        vehiclerearTailConnectorL.setRotationPoint(3, 0, 7);
        vehiclerearTailConnectorL.addBox(-2, 0, 0, 2, 2, 4, 0);
        setRotationAngle(vehiclerearTailConnectorL, -0.006981317007977318F, -0.13962634015954636F, 0);
        vehiclelowerarmL2 = new ModelRenderer(this, 35, 35);
        vehiclelowerarmL2.setRotationPoint(1, 4, 1.2F);
        vehiclelowerarmL2.addBox(-1, -4, -1, 1, 4, 1, 0);
        setRotationAngle(vehiclelowerarmL2, -0.08726646259971647F, 0, 0.10471975511965977F);
        vehiclelowerarmR2 = new ModelRenderer(this, 35, 35);
        vehiclelowerarmR2.mirror = true;
        vehiclelowerarmR2.setRotationPoint(-1, 4, 1.2F);
        vehiclelowerarmR2.addBox(0, -4, -1, 1, 4, 1, 0);
        setRotationAngle(vehiclelowerarmR2, -0.08726646259971647F, 0, -0.10471975511965977F);
        vehicleUpperlegRbase = new ModelRenderer(this, 0, 73);
        vehicleUpperlegRbase.setRotationPoint(-1.5F, 1, -4);
        vehicleUpperlegRbase.addBox(-1, -0.5F, -0.5F, 1, 7, 1, 0);
        setRotationAngle(vehicleUpperlegRbase, 1.5707963267948966F, 0, 0);
        vehicleshoulderpieceL1 = new ModelRenderer(this, 44, 16);
        vehicleshoulderpieceL1.mirror = true;
        vehicleshoulderpieceL1.setRotationPoint(0, 0.3F, 0);
        vehicleshoulderpieceL1.addBox(-0.2F, 0, -1, 1, 2, 2, 0);
        vehiclelowerlegRbase = new ModelRenderer(this, 0, 81);
        vehiclelowerlegRbase.mirror = true;
        vehiclelowerlegRbase.setRotationPoint(-0.5F, 4, 0);
        vehiclelowerlegRbase.addBox(-1, 0, -1.5F, 2, 6, 2, 0);
        vehiclelowerarmR1 = new ModelRenderer(this, 43, 31);
        vehiclelowerarmR1.setRotationPoint(0, 0, 0);
        vehiclelowerarmR1.addBox(-0.9F, -1.1F, 0.5F, 2, 2, 1, 0);
        setRotationAngle(vehiclelowerarmR1, -0.08726646259971647F, -0.08726646259971647F, -0.7853981633974483F);
        vehiclewingL4 = new ModelRenderer(this, 43, 23);
        vehiclewingL4.setRotationPoint(0, 2, -1);
        vehiclewingL4.addBox(-0.4F, -0.7F, -1, 1, 6, 2, 0);
        setRotationAngle(vehiclewingL4, -0.09250245035569946F, 0.03490658503988659F, 0);
        vehiclefeetL1 = new ModelRenderer(this, 3, 92);
        vehiclefeetL1.setRotationPoint(-2, -1.6F, -4.2F);
        vehiclefeetL1.addBox(-1.5F, 0, -3.4F, 3, 1, 5, 0);
        vehiclechestcockpitbase = new ModelRenderer(this, 21, 44);
        vehiclechestcockpitbase.setRotationPoint(-1.5F, -2, -1.5F);
        vehiclechestcockpitbase.addBox(0, 0, -2, 3, 2, 2, 0);
        vehicleleftshoulder5 = new ModelRenderer(this, 30, 70);
        vehicleleftshoulder5.mirror = true;
        vehicleleftshoulder5.setRotationPoint(0.1F, 0, 3);
        vehicleleftshoulder5.addBox(0, 0, 0, 3, 2, 7, 0);
        setRotationAngle(vehicleleftshoulder5, -0.10471975511965977F, 0.06981317007977318F, 0);
        vehicleshoulderbase = new ModelRenderer(this, 0, 40);
        vehicleshoulderbase.setRotationPoint(0, -1, 1);
        vehicleshoulderbase.addBox(-4, -0.8F, 0, 8, 2, 2, 0);
        vehicleupperlegR2 = new ModelRenderer(this, 8, 100);
        vehicleupperlegR2.setRotationPoint(-0.2F, -2.4F, 0.8F);
        vehicleupperlegR2.addBox(-0.1F, 0, -2, 1, 4, 2, 0);
        setRotationAngle(vehicleupperlegR2, 0.13439035240356337F, 0, -0.05235987755982988F);
        vehicleengineR5 = new ModelRenderer(this, 4, 77);
        vehicleengineR5.mirror = true;
        vehicleengineR5.setRotationPoint(0, 1, 0);
        vehicleengineR5.addBox(-1, 0, 0, 2, 2, 1, 0);
        setRotationAngle(vehicleengineR5, 0.19198621771937624F, 0, 0);
        vehicleupperarmbaseR = new ModelRenderer(this, 40, 15);
        vehicleupperarmbaseR.setRotationPoint(-3.7F, 0.5F, -1.6F);
        vehicleupperarmbaseR.addBox(-0.5F, 0.3F, -0.5F, 1, 4, 1, 0);
        setRotationAngle(vehicleupperarmbaseR, 1.5707963267948966F, 0, 0);
        vehiclefeetbaseR = new ModelRenderer(this, 8, 88);
        vehiclefeetbaseR.setRotationPoint(0, 5.1F, -0.2F);
        vehiclefeetbaseR.addBox(-0.9F, 0, -0.9F, 2, 2, 2, 0);
        setRotationAngle(vehiclefeetbaseR, -1.5707963267948966F, 0, 0);
        vehiclerearWingConnectorR = new ModelRenderer(this, 25, 105);
        vehiclerearWingConnectorR.setRotationPoint(-1, 2, 0);
        vehiclerearWingConnectorR.addBox(0, 0, 0, 1, 4, 2, 0);
        setRotationAngle(vehiclerearWingConnectorR, 0.3141592653589793F, 0, 0);
        vehicleupperarmbaseL = new ModelRenderer(this, 40, 15);
        vehicleupperarmbaseL.mirror = true;
        vehicleupperarmbaseL.setRotationPoint(3.7F, 0.5F, -1.6F);
        vehicleupperarmbaseL.addBox(-0.5F, 0.3F, -0.5F, 1, 4, 1, 0);
        setRotationAngle(vehicleupperarmbaseL, 1.5707963267948966F, 0, 0);
        vehiclewingstrutR3 = new ModelRenderer(this, 40, 22);
        vehiclewingstrutR3.setRotationPoint(-0.1F, -5.2F, -4);
        vehiclewingstrutR3.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(vehiclewingstrutR3, 1.5707963267948966F, 0, 0);
        vehiclecockpit1_1 = new ModelRenderer(this, 21, 48);
        vehiclecockpit1_1.setRotationPoint(0, 0, 0);
        vehiclecockpit1_1.addBox(0, 0, 0, 2, 1, 3, 0);
        setRotationAngle(vehiclecockpit1_1, -0.5811946409141118F, 0, 0);
        vehiclewingconnectorR = new ModelRenderer(this, 44, 20);
        vehiclewingconnectorR.setRotationPoint(1, 5, 0);
        vehiclewingconnectorR.addBox(-1.7F, -0.5F, -0.5F, 2, 1, 1, 0);
        vehicleTorsoR2 = new ModelRenderer(this, 10, 60);
        vehicleTorsoR2.setRotationPoint(-0.4F, -3.1F, -0.9F);
        vehicleTorsoR2.addBox(-2, -0.7F, 0, 2, 4, 1, 0);
        setRotationAngle(vehicleTorsoR2, 0.13962634015954636F, 0.20943951023931953F, -0.19198621771937624F);
        vehiclewingbaseL = new ModelRenderer(this, 35, 17);
        vehiclewingbaseL.mirror = true;
        vehiclewingbaseL.setRotationPoint(2.6F, 0.5F, 0);
        vehiclewingbaseL.addBox(-0.3F, -1.2F, -1.1F, 1, 2, 3, 0);
        setRotationAngle(vehiclewingbaseL, 0, -1.5707963267948966F, 3.141592653589793F);
        vehiclewingTipR = new ModelRenderer(this, 28, 86);
        vehiclewingTipR.setRotationPoint(-0.1F, 0, 7);
        vehiclewingTipR.addBox(0, -0.4F, 0, 1, 6, 2, 0);
        setRotationAngle(vehiclewingTipR, -0.17976891295541594F, 0, -0.012217304763960306F);
        vehicleupperlegL3_2 = new ModelRenderer(this, 8, 100);
        vehicleupperlegL3_2.setRotationPoint(-0.1F, 4, 0);
        vehicleupperlegL3_2.addBox(0, 0, -2, 1, 5, 2, 0);
        setRotationAngle(vehicleupperlegL3_2, -0.13439035240356337F, 0, 0.05235987755982988F);
        vehiclelowerarmL1 = new ModelRenderer(this, 43, 31);
        vehiclelowerarmL1.setRotationPoint(0, 0, 0);
        vehiclelowerarmL1.addBox(-0.9F, -1.1F, 0.5F, 2, 2, 1, 0);
        setRotationAngle(vehiclelowerarmL1, -0.08726646259971647F, -0.08726646259971647F, -0.7853981633974483F);
        vehicleleftshoulder4 = new ModelRenderer(this, 6, 56);
        vehicleleftshoulder4.setRotationPoint(-1.1F, -1, -2.9F);
        vehicleleftshoulder4.addBox(0.1F, 0, 0, 3, 1, 3, 0);
        setRotationAngle(vehicleleftshoulder4, 0.10471975511965977F, -0.06981317007977318F, 0);
        vehiclerightshoulder1 = new ModelRenderer(this, 6, 56);
        vehiclerightshoulder1.setRotationPoint(2.1F, -1, -2.9F);
        vehiclerightshoulder1.addBox(-3.1F, 0, 0, 3, 1, 3, 0);
        setRotationAngle(vehiclerightshoulder1, 0.10471975511965977F, 0.06981317007977318F, 0);
        vehiclewingR4 = new ModelRenderer(this, 43, 23);
        vehiclewingR4.mirror = true;
        vehiclewingR4.setRotationPoint(0, 2, -1);
        vehiclewingR4.addBox(-0.6F, -0.7F, -1, 1, 6, 2, 0);
        setRotationAngle(vehiclewingR4, -0.09250245035569946F, -0.03490658503988659F, 0);
        vehiclecockpit5 = new ModelRenderer(this, 14, 47);
        vehiclecockpit5.mirror = true;
        vehiclecockpit5.setRotationPoint(3, 0, -2);
        vehiclecockpit5.addBox(-1, 0, -5, 1, 1, 5, 0);
        setRotationAngle(vehiclecockpit5, 0, 0.059341194567807204F, 0);
        vehiclebottomCockpitL = new ModelRenderer(this, 49, 88);
        vehiclebottomCockpitL.mirror = true;
        vehiclebottomCockpitL.setRotationPoint(2, 0.7F, -1.5F);
        vehiclebottomCockpitL.addBox(-1, -2, -6, 1, 2, 6, 0);
        setRotationAngle(vehiclebottomCockpitL, -0.0471238898038469F, 0.16929693744344995F, 0);
        vehiclewingconnectorL = new ModelRenderer(this, 44, 20);
        vehiclewingconnectorL.setRotationPoint(-1, 5, 0);
        vehiclewingconnectorL.addBox(-0.3F, -0.5F, -0.5F, 2, 1, 1, 0);
        vehiclewingFrontL = new ModelRenderer(this, 26, 85);
        vehiclewingFrontL.mirror = true;
        vehiclewingFrontL.setRotationPoint(0, 8, 0);
        vehiclewingFrontL.addBox(0, 0, -1, 1, 2, 9, 0);
        setRotationAngle(vehiclewingFrontL, 0.5061454830783556F, 0, 0.017453292519943295F);
        vehicleengineL3 = new ModelRenderer(this, 10, 77);
        vehicleengineL3.mirror = true;
        vehicleengineL3.setRotationPoint(0, 1, 3);
        vehicleengineL3.addBox(-1, 0, -1, 2, 2, 1, 0);
        setRotationAngle(vehicleengineL3, -0.19198621771937624F, 0, 0);
        vehiclefeetR3 = new ModelRenderer(this, 14, 90);
        vehiclefeetR3.setRotationPoint(4, 2, 1);
        vehiclefeetR3.addBox(0, 0, 0, 1, 1, 2, 0);
        setRotationAngle(vehiclefeetR3, 0, 0, 1.5707963267948966F);
        vehicleupperlegL3 = new ModelRenderer(this, 8, 100);
        vehicleupperlegL3.mirror = true;
        vehicleupperlegL3.setRotationPoint(0.2F, -2.4F, 0.8F);
        vehicleupperlegL3.addBox(-0.9F, 0, -2, 1, 4, 2, 0);
        setRotationAngle(vehicleupperlegL3, 0.13439035240356337F, 0, 0.05235987755982988F);
        vehiclelowerCockpitRearL = new ModelRenderer(this, 37, 89);
        vehiclelowerCockpitRearL.mirror = true;
        vehiclelowerCockpitRearL.setRotationPoint(2, -2.3F, -1.5F);
        vehiclelowerCockpitRearL.addBox(-1, 0, -4, 1, 1, 4, 0);
        setRotationAngle(vehiclelowerCockpitRearL, 0.07853981633974483F, 0.15707963267948966F, 0);
        vehiclewaist3 = new ModelRenderer(this, 10, 17);
        vehiclewaist3.mirror = true;
        vehiclewaist3.setRotationPoint(-3, 0, 0);
        vehiclewaist3.addBox(-0.5F, 0, -0.2F, 1, 2, 1, 0);
        vehiclewingbaseR = new ModelRenderer(this, 35, 17);
        vehiclewingbaseR.setRotationPoint(-2.6F, 0.5F, 0);
        vehiclewingbaseR.addBox(-0.7F, -1.2F, -1.1F, 1, 2, 3, 0);
        setRotationAngle(vehiclewingbaseR, 0, 1.5707963267948966F, 3.141592653589793F);
        vehiclenoseTop = new ModelRenderer(this, 49, 87);
        vehiclenoseTop.setRotationPoint(0, -3, 0);
        vehiclenoseTop.addBox(0, 0, 0, 2, 5, 1, 0);
        setRotationAngle(vehiclenoseTop, 0.6719517620178168F, 0, 0);
        vehiclerightshoulder3 = new ModelRenderer(this, 0, 51);
        vehiclerightshoulder3.setRotationPoint(2, 1.5F, -3);
        vehiclerightshoulder3.addBox(-3, -0.5F, 0.1F, 3, 1, 4, 0);
        setRotationAngle(vehiclerightshoulder3, 0.006981317007977318F, -0.017453292519943295F, 0.15707963267948966F);
        vehicleupperlegL3_1 = new ModelRenderer(this, 8, 100);
        vehicleupperlegL3_1.mirror = true;
        vehicleupperlegL3_1.setRotationPoint(0.1F, 4, 0);
        vehicleupperlegL3_1.addBox(-1, 0, -2, 1, 5, 2, 0);
        setRotationAngle(vehicleupperlegL3_1, -0.13439035240356337F, 0, -0.05235987755982988F);
        vehiclerearCockpit = new ModelRenderer(this, 21, 97);
        vehiclerearCockpit.setRotationPoint(0, -1.6F, 0.2F);
        vehiclerearCockpit.addBox(0, 0, 0, 3, 2, 5, 0);
        setRotationAngle(vehiclerearCockpit, -0.13962634015954636F, 0, 0);
        vehiclenoseSideR1 = new ModelRenderer(this, 51, 67);
        vehiclenoseSideR1.setRotationPoint(0, -10, 0.7F);
        vehiclenoseSideR1.addBox(0, 0, 0, 1, 6, 1, 0);
        setRotationAngle(vehiclenoseSideR1, 0.06981317007977318F, 0, 0.08726646259971647F);
        vehiclefeetR4 = new ModelRenderer(this, 14, 90);
        vehiclefeetR4.mirror = true;
        vehiclefeetR4.setRotationPoint(4, 1.9F, 1.3F);
        vehiclefeetR4.addBox(-1, 0, 0, 1, 1, 2, 0);
        setRotationAngle(vehiclefeetR4, 0, 0, 1.5707963267948966F);
        vehicleupperlegR3 = new ModelRenderer(this, 8, 100);
        vehicleupperlegR3.setRotationPoint(-2.1F, 1.5F, -0.2F);
        vehicleupperlegR3.addBox(-1, 0, -2, 1, 4, 2, 0);
        setRotationAngle(vehicleupperlegR3, 0, -0.2234021442552742F, 0);
        vehicletailWingL = new ModelRenderer(this, 55, 67);
        vehicletailWingL.mirror = true;
        vehicletailWingL.setRotationPoint(0, 0, 0);
        vehicletailWingL.addBox(-0.5F, -3.5F, -1, 1, 4, 3, 0);
        setRotationAngle(vehicletailWingL, -0.5235987755982988F, 0, 0);
        vehiclewaist2 = new ModelRenderer(this, 10, 17);
        vehiclewaist2.setRotationPoint(3, 0, 0);
        vehiclewaist2.addBox(-0.5F, 0, -0.2F, 1, 2, 1, 0);
        vehicleengineL4 = new ModelRenderer(this, 8, 80);
        vehicleengineL4.mirror = true;
        vehicleengineL4.setRotationPoint(1.5F, 1, 1.5F);
        vehicleengineL4.addBox(-1, 0, -1, 1, 2, 2, 0);
        setRotationAngle(vehicleengineL4, 0, 0, 0.19198621771937624F);
        vehiclenoseLift = new ModelRenderer(this, 52, 77);
        vehiclenoseLift.setRotationPoint(0, 0, 0);
        vehiclenoseLift.addBox(0, -8, 0, 2, 8, 1, 0);
        setRotationAngle(vehiclenoseLift, -0.06981317007977318F, 0, 0);
        vehiclewingstrutR2 = new ModelRenderer(this, 40, 22);
        vehiclewingstrutR2.setRotationPoint(-0.1F, -4.5F, 3);
        vehiclewingstrutR2.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        vehicleleftshoulder3 = new ModelRenderer(this, 0, 56);
        vehicleleftshoulder3.mirror = true;
        vehicleleftshoulder3.setRotationPoint(2, -0.5F, -2.9F);
        vehicleleftshoulder3.addBox(-1, 0, 0, 1, 2, 4, 0);
        setRotationAngle(vehicleleftshoulder3, -0.017453292519943295F, 0, -0.03490658503988659F);
        vehiclecockpit4 = new ModelRenderer(this, 14, 47);
        vehiclecockpit4.setRotationPoint(0, 0, -2);
        vehiclecockpit4.addBox(0, 0, -5, 1, 1, 5, 0);
        setRotationAngle(vehiclecockpit4, 0, -0.059341194567807204F, 0);
        vehiclecenterConnector1 = new ModelRenderer(this, 29, 68);
        vehiclecenterConnector1.setRotationPoint(2, -8, 0.6F);
        vehiclecenterConnector1.addBox(0, 0, 0, 2, 8, 1, 0);
        setRotationAngle(vehiclecenterConnector1, -0.07504915783575616F, 0, 0);
        vehicleWaist5 = new ModelRenderer(this, 10, 17);
        vehicleWaist5.setRotationPoint(-9.7F, 2.3F, 2.6F);
        vehicleWaist5.addBox(0.8F, 0, -0.2F, 1, 2, 1, 0);
        setRotationAngle(vehicleWaist5, 1.6755160819145563F, -0.20943951023931953F, 0);
        vehiclelowerlegR1 = new ModelRenderer(this, 16, 73);
        vehiclelowerlegR1.setRotationPoint(1, -3, -2.6F);
        vehiclelowerlegR1.addBox(-3, 0, 0, 3, 5, 2, 0);
        vehiclewingL3 = new ModelRenderer(this, 43, 23);
        vehiclewingL3.setRotationPoint(0, 2, -0.4F);
        vehiclewingL3.addBox(-0.4F, -2.3F, -0.2F, 1, 6, 2, 0);
        setRotationAngle(vehiclewingL3, -0.41887902047863906F, 0.03490658503988659F, -0.013962634015954637F);
        vehicleupperlegL2 = new ModelRenderer(this, 8, 100);
        vehicleupperlegL2.mirror = true;
        vehicleupperlegL2.setRotationPoint(2.1F, 1.5F, -0.2F);
        vehicleupperlegL2.addBox(0, 0, -2, 1, 4, 2, 0);
        setRotationAngle(vehicleupperlegL2, 0, 0.2234021442552742F, 0);
        vehiclelowerlegR3 = new ModelRenderer(this, 8, 84);
        vehiclelowerlegR3.setRotationPoint(-2.9F, 9.1F, 2.9F);
        vehiclelowerlegR3.addBox(-4, 0, 0, 4, 3, 1, 0);
        setRotationAngle(vehiclelowerlegR3, -0.2617993877991494F, 0.8726646259971648F, -0.2617993877991494F);
        vehiclerightshoulderbase = new ModelRenderer(this, 0, 46);
        vehiclerightshoulderbase.setRotationPoint(-3.5F, 0.15F, 1.4F);
        vehiclerightshoulderbase.addBox(-1, -1, -2.9F, 3, 1, 4, 0);
        setRotationAngle(vehiclerightshoulderbase, 0, 0, -0.20943951023931953F);
        vehiclewingstrutR1 = new ModelRenderer(this, 40, 22);
        vehiclewingstrutR1.setRotationPoint(-0.1F, 0, -0.4F);
        vehiclewingstrutR1.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        vehiclerearSlantL = new ModelRenderer(this, 43, 69);
        vehiclerearSlantL.setRotationPoint(3, 5, 0);
        vehiclerearSlantL.addBox(0, 0, 0, 1, 4, 1, 0);
        setRotationAngle(vehiclerearSlantL, 0.20943951023931953F, 0, 0.017453292519943295F);
        vehicleengineL1 = new ModelRenderer(this, 4, 73);
        vehicleengineL1.setRotationPoint(-0.3F, 6, -2);
        vehicleengineL1.addBox(-1.5F, 0, 0, 3, 1, 3, 0);
        vehiclelowerlegL1 = new ModelRenderer(this, 16, 73);
        vehiclelowerlegL1.mirror = true;
        vehiclelowerlegL1.setRotationPoint(-1, -3, -2.6F);
        vehiclelowerlegL1.addBox(0, 0, 0, 3, 5, 2, 0);
        vehiclelowerlegR5 = new ModelRenderer(this, 18, 83);
        vehiclelowerlegR5.setRotationPoint(-2, 5, 0);
        vehiclelowerlegR5.addBox(-1, 0, 0, 1, 2, 2, 0);
        vehiclerearWingConnectorL = new ModelRenderer(this, 25, 105);
        vehiclerearWingConnectorL.mirror = true;
        vehiclerearWingConnectorL.setRotationPoint(1, 2, 0);
        vehiclerearWingConnectorL.addBox(-1, 0, 0, 1, 4, 2, 0);
        setRotationAngle(vehiclerearWingConnectorL, 0.3141592653589793F, 0, 0);
        vehiclefeetL3 = new ModelRenderer(this, 14, 90);
        vehiclefeetL3.mirror = true;
        vehiclefeetL3.setRotationPoint(-4, 0.9F, 6.3F);
        vehiclefeetL3.addBox(-1, 0, 0, 1, 1, 2, 0);
        setRotationAngle(vehiclefeetL3, 0, 0, -1.5707963267948966F);
        vehicleWaist4 = new ModelRenderer(this, 10, 17);
        vehicleWaist4.mirror = true;
        vehicleWaist4.setRotationPoint(9.6F, 2.3F, 2.7F);
        vehicleWaist4.addBox(-1.8F, 0, -0.2F, 1, 2, 1, 0);
        setRotationAngle(vehicleWaist4, 1.6755160819145563F, 0.20943951023931953F, 0);
        vehiclewingR2 = new ModelRenderer(this, 43, 23);
        vehiclewingR2.mirror = true;
        vehiclewingR2.setRotationPoint(0.2F, 1, 2.2F);
        vehiclewingR2.addBox(-0.8F, -0.7F, -1.2F, 1, 6, 2, 0);
        setRotationAngle(vehiclewingR2, -1.3089969389957472F, 0.017453292519943295F, 0);
        vehiclewingLBase = new ModelRenderer(this, 35, 57);
        vehiclewingLBase.mirror = true;
        vehiclewingLBase.setRotationPoint(-0.5F, -6, -1.5F);
        vehiclewingLBase.addBox(0, 0, 0, 1, 5, 7, 0);
        vehicleengineR3 = new ModelRenderer(this, 8, 80);
        vehicleengineR3.mirror = true;
        vehicleengineR3.setRotationPoint(1.5F, 1, 1.5F);
        vehicleengineR3.addBox(-1, 0, -1, 1, 2, 2, 0);
        setRotationAngle(vehicleengineR3, 0, 0, 0.19198621771937624F);
        vehiclelowerlegL5 = new ModelRenderer(this, 18, 83);
        vehiclelowerlegL5.mirror = true;
        vehiclelowerlegL5.setRotationPoint(2, 5, 0);
        vehiclelowerlegL5.addBox(0, 0, 0, 1, 2, 2, 0);
        vehiclefeetbaseL = new ModelRenderer(this, 8, 88);
        vehiclefeetbaseL.setRotationPoint(0, 5.1F, -0.2F);
        vehiclefeetbaseL.addBox(-1.1F, 0, -0.9F, 2, 2, 2, 0);
        setRotationAngle(vehiclefeetbaseL, -1.5707963267948966F, 0, 0);
        vehiclecockpit2 = new ModelRenderer(this, 21, 40);
        vehiclecockpit2.setRotationPoint(-0.1F, 0.2F, -3.3F);
        vehiclecockpit2.addBox(0, 0.2F, -1.6F, 1, 2, 2, 0);
        setRotationAngle(vehiclecockpit2, 0.2617993877991494F, 0, 0);
        vehiclelowerlegR2 = new ModelRenderer(this, 18, 80);
        vehiclelowerlegR2.setRotationPoint(0, 0, 0);
        vehiclelowerlegR2.addBox(-3, -2, 0, 3, 2, 1, 0);
        setRotationAngle(vehiclelowerlegR2, -0.3665191429188092F, 0, 0);
        vehiclenoseSideL1 = new ModelRenderer(this, 51, 67);
        vehiclenoseSideL1.mirror = true;
        vehiclenoseSideL1.setRotationPoint(2, -10, 0.7F);
        vehiclenoseSideL1.addBox(-1, 0, 0, 1, 6, 1, 0);
        setRotationAngle(vehiclenoseSideL1, 0.06981317007977318F, 0, -0.08726646259971647F);
        vehicleengineL5 = new ModelRenderer(this, 8, 80);
        vehicleengineL5.setRotationPoint(-1.5F, 1, 1.5F);
        vehicleengineL5.addBox(0, 0, -1, 1, 2, 2, 0);
        setRotationAngle(vehicleengineL5, 0, 0, -0.19198621771937624F);
        vehiclewingR1 = new ModelRenderer(this, 35, 22);
        vehiclewingR1.setRotationPoint(0, -1.2F, -1.1F);
        vehiclewingR1.addBox(-0.7F, 0, 0, 1, 4, 3, 0);
        setRotationAngle(vehiclewingR1, 1.5707963267948966F, 0, 0);
        vehiclewingstrutL1 = new ModelRenderer(this, 40, 22);
        vehiclewingstrutL1.setRotationPoint(0.1F, 0, -0.4F);
        vehiclewingstrutL1.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        vehicleengineR1 = new ModelRenderer(this, 4, 73);
        vehicleengineR1.setRotationPoint(0.3F, 6, -2);
        vehicleengineR1.addBox(-1.5F, 0, 0, 3, 1, 3, 0);
        vehicleengineL2 = new ModelRenderer(this, 4, 77);
        vehicleengineL2.setRotationPoint(0, 1, 0);
        vehicleengineL2.addBox(-1, 0, 0, 2, 2, 1, 0);
        setRotationAngle(vehicleengineL2, 0.19198621771937624F, 0, 0);
        vehiclewingConnectorL1 = new ModelRenderer(this, 29, 96);
        vehiclewingConnectorL1.mirror = true;
        vehiclewingConnectorL1.setRotationPoint(-0.5F, 5, 0);
        vehiclewingConnectorL1.addBox(0, 0, -1, 1, 3, 8, 0);
        setRotationAngle(vehiclewingConnectorL1, 0.296705972839036F, 0.06981317007977318F, 0.020943951023931952F);
        vehiclenoseBottom = new ModelRenderer(this, 39, 98);
        vehiclenoseBottom.setRotationPoint(0, -8, 0);
        vehiclenoseBottom.addBox(0, -3, 0, 2, 3, 1, 0);
        setRotationAngle(vehiclenoseBottom, -0.20943951023931953F, 0, 0);
        vehiclewingstrutL3 = new ModelRenderer(this, 40, 22);
        vehiclewingstrutL3.setRotationPoint(0.1F, -5.2F, -4);
        vehiclewingstrutL3.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        setRotationAngle(vehiclewingstrutL3, 1.5707963267948966F, 0, -0);
        vehiclewingRBase = new ModelRenderer(this, 35, 57);
        vehiclewingRBase.setRotationPoint(-0.5F, -6, -1.5F);
        vehiclewingRBase.addBox(0, 0, 0, 1, 5, 7, 0);
        vehicletailWingR = new ModelRenderer(this, 55, 67);
        vehicletailWingR.setRotationPoint(0, 0, 0);
        vehicletailWingR.addBox(-0.5F, -3.5F, -1, 1, 4, 3, 0);
        setRotationAngle(vehicletailWingR, -0.5235987755982988F, 0, 0);
        vehicleUpperBodyBase = new ModelRenderer(this, 0, 33);
        vehicleUpperBodyBase.setRotationPoint(0, -6, -0.5F);
        vehicleUpperBodyBase.addBox(-2, -2.3F, -1.5F, 4, 3, 4, 0);
        setRotationAngle(vehicleUpperBodyBase, -1.5707963267948966F, 0, 0);
        vehiclelowerlegL2 = new ModelRenderer(this, 8, 84);
        vehiclelowerlegL2.mirror = true;
        vehiclelowerlegL2.setRotationPoint(2.9F, 9.1F, 2.9F);
        vehiclelowerlegL2.addBox(0, 0, 0, 4, 3, 1, 0);
        setRotationAngle(vehiclelowerlegL2, -0.2617993877991494F, -0.8726646259971648F, 0.2617993877991494F);
        vehicleengineR2 = new ModelRenderer(this, 10, 77);
        vehicleengineR2.setRotationPoint(0, 1, 3);
        vehicleengineR2.addBox(-1, 0, -1, 2, 2, 1, 0);
        setRotationAngle(vehicleengineR2, -0.19198621771937624F, 0, 0);
        vehiclecockpit1 = new ModelRenderer(this, 21, 48);
        vehiclecockpit1.setRotationPoint(0.5F, -2.2F, -2.6F);
        vehiclecockpit1.addBox(0, 0, -3, 2, 1, 3, 0);
        setRotationAngle(vehiclecockpit1, 0.3787364476827695F, 0, 0);
        vehiclerightshoulder5 = new ModelRenderer(this, 0, 56);
        vehiclerightshoulder5.setRotationPoint(-1, -0.5F, -2.9F);
        vehiclerightshoulder5.addBox(0, 0, 0, 1, 2, 4, 0);
        setRotationAngle(vehiclerightshoulder5, -0.017453292519943295F, 0, 0.03490658503988659F);
        vehiclerightshoulder2 = new ModelRenderer(this, 30, 70);
        vehiclerightshoulder2.setRotationPoint(-0.1F, 0, 3);
        vehiclerightshoulder2.addBox(-3, 0, 0, 3, 2, 7, 0);
        setRotationAngle(vehiclerightshoulder2, -0.10471975511965977F, -0.06981317007977318F, 0);
        vehicleshoulderpieceR1 = new ModelRenderer(this, 44, 16);
        vehicleshoulderpieceR1.setRotationPoint(0, 0.3F, 0);
        vehicleshoulderpieceR1.addBox(-0.8F, 0, -1, 1, 2, 2, 0);
        vehiclewingstrutL2 = new ModelRenderer(this, 40, 22);
        vehiclewingstrutL2.setRotationPoint(0.1F, -4.5F, 3);
        vehiclewingstrutL2.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
        vehiclelowerarmbaseL = new ModelRenderer(this, 35, 29);
        vehiclelowerarmbaseL.mirror = true;
        vehiclelowerarmbaseL.setRotationPoint(-2, 10.2F, 1.2F);
        vehiclelowerarmbaseL.addBox(-1, 0, -0.8F, 2, 4, 2, 0);
        setRotationAngle(vehiclelowerarmbaseL, 0.05235987755982988F, 0.19198621771937624F, 0.05235987755982988F);
        vehiclerear = new ModelRenderer(this, 45, 98);
        vehiclerear.setRotationPoint(3, 8.7F, 1.9F);
        vehiclerear.addBox(-1, -3.5F, -1, 2, 4, 2, 0);
        setRotationAngle(vehiclerear, -0.19722220547535924F, 0, 0);
        vehiclewingL1 = new ModelRenderer(this, 35, 22);
        vehiclewingL1.mirror = true;
        vehiclewingL1.setRotationPoint(0, -1.2F, -1.1F);
        vehiclewingL1.addBox(-0.3F, 0, 0, 1, 4, 3, 0);
        setRotationAngle(vehiclewingL1, 1.5707963267948966F, 0, 0);
        vehicleengineR4 = new ModelRenderer(this, 8, 80);
        vehicleengineR4.setRotationPoint(-1.5F, 1, 1.5F);
        vehicleengineR4.addBox(0, 0, -1, 1, 2, 2, 0);
        setRotationAngle(vehicleengineR4, 0, 0, -0.19198621771937624F);
        vehicleleftshoulderbase = new ModelRenderer(this, 0, 46);
        vehicleleftshoulderbase.mirror = true;
        vehicleleftshoulderbase.setRotationPoint(2.5F, -0.05F, 1.4F);
        vehicleleftshoulderbase.addBox(-1, -1, -2.9F, 3, 1, 4, 0);
        setRotationAngle(vehicleleftshoulderbase, 0, 0, 0.20943951023931953F);

        vehiclewingRBase.addChild(vehiclewingFrontR);
        vehiclelowerRear.addChild(vehiclerearSlantR);
        vehiclelowerlegR3.addChild(vehicletailLFront);
        vehicletailWingR.addChild(vehicletailWingFlapR);
        vehiclerearCockpit.addChild(vehiclecockpitR);
        vehicleUpperlegLbase.addChild(vehiclelowerlegLbase);
        vehiclelowerarmbaseL.addChild(vehiclelowerarmL3);
        vehiclewingRBase.addChild(vehiclewingFlapR);
        vehiclelowerlegL2.addChild(vehiclelowerlegL3);
        vehicleBody.addChild(vehicleUpperlegLbase);
        vehiclelowerlegL2.addChild(vehicletailRFront);
        vehicleUpperBodyBase.addChild(vehicleBottomCockpitR);
        vehicleBody.addChild(vehicleTorsoConnector);
        vehiclewingLBase.addChild(vehiclewingFlapL);
        vehiclewingstrutR2.addChild(vehiclewingR3);
        vehiclelowerlegR3.addChild(vehiclelowerlegR4);
        vehicleBody.addChild(vehicleWaist1);
        vehiclelowerarmbaseR.addChild(vehiclelowerarmR3);
        vehiclecockpit1.addChild(vehiclecockpit3);
        vehicleUpperBodyBase.addChild(vehiclelowerCockpitRearR);
        vehiclelowerlegL1.addChild(vehiclelowerlegL4);
        vehiclerearCockpit.addChild(vehiclecockpitL);
        vehiclewingLBase.addChild(vehiclewingTipL);
        vehicleTorsoConnector.addChild(vehicletorsoL2);
        vehiclerightshoulderbase.addChild(vehiclewingCockpitConnectorR);
        vehiclerightshoulder2.addChild(vehiclerearTailConnectorR);
        vehiclewingRBase.addChild(vehiclewingConnectorR1);
        vehiclewingstrutL1.addChild(vehiclewingL2);
        vehiclefeetL1.addChild(vehiclefeetL4);
        vehicletailWingL.addChild(vehicletailWingFlapL);
        vehicleleftshoulderbase.addChild(vehicleleftshoulder1);
        vehicleleftshoulderbase.addChild(vehiclewingCockpitConnectorL);
        vehicleTorsoConnector.addChild(vehiclelowerRear);
        vehiclefeetbaseR.addChild(vehiclefeetR1);
        vehicleupperarmbaseR.addChild(vehiclelowerarmbaseR);
        vehicleleftshoulder5.addChild(vehiclerearTailConnectorL);
        vehiclelowerarmbaseL.addChild(vehiclelowerarmL2);
        vehiclelowerarmbaseR.addChild(vehiclelowerarmR2);
        vehicleBody.addChild(vehicleUpperlegRbase);
        vehicleupperarmbaseL.addChild(vehicleshoulderpieceL1);
        vehicleUpperlegRbase.addChild(vehiclelowerlegRbase);
        vehiclelowerarmbaseR.addChild(vehiclelowerarmR1);
        vehiclewingstrutL3.addChild(vehiclewingL4);
        vehiclefeetbaseL.addChild(vehiclefeetL1);
        vehicleUpperBodyBase.addChild(vehiclechestcockpitbase);
        vehicleleftshoulder4.addChild(vehicleleftshoulder5);
        vehicleUpperBodyBase.addChild(vehicleshoulderbase);
        vehicleUpperlegRbase.addChild(vehicleupperlegR2);
        vehicleengineR1.addChild(vehicleengineR5);
        vehicleshoulderbase.addChild(vehicleupperarmbaseR);
        vehiclelowerlegRbase.addChild(vehiclefeetbaseR);
        vehiclelowerlegR5.addChild(vehiclerearWingConnectorR);
        vehicleshoulderbase.addChild(vehicleupperarmbaseL);
        vehiclewingbaseR.addChild(vehiclewingstrutR3);
        vehiclecockpit1.addChild(vehiclecockpit1_1);
        vehicleshoulderpieceR1.addChild(vehiclewingconnectorR);
        vehicleTorsoConnector.addChild(vehicleTorsoR2);
        vehiclewingconnectorL.addChild(vehiclewingbaseL);
        vehiclewingRBase.addChild(vehiclewingTipR);
        vehicleupperlegR2.addChild(vehicleupperlegL3_2);
        vehiclelowerarmbaseL.addChild(vehiclelowerarmL1);
        vehicleleftshoulderbase.addChild(vehicleleftshoulder4);
        vehiclerightshoulderbase.addChild(vehiclerightshoulder1);
        vehiclewingstrutR3.addChild(vehiclewingR4);
        vehiclechestcockpitbase.addChild(vehiclecockpit5);
        vehicleUpperBodyBase.addChild(vehiclebottomCockpitL);
        vehicleshoulderpieceL1.addChild(vehiclewingconnectorL);
        vehiclewingLBase.addChild(vehiclewingFrontL);
        vehicleengineL1.addChild(vehicleengineL3);
        vehiclefeetR1.addChild(vehiclefeetR3);
        vehicleUpperlegLbase.addChild(vehicleupperlegL3);
        vehicleUpperBodyBase.addChild(vehiclelowerCockpitRearL);
        vehicleWaist4.addChild(vehiclewaist3);
        vehiclewingconnectorR.addChild(vehiclewingbaseR);
        vehiclenoseBottom.addChild(vehiclenoseTop);
        vehiclerightshoulderbase.addChild(vehiclerightshoulder3);
        vehicleupperlegL3.addChild(vehicleupperlegL3_1);
        vehiclechestcockpitbase.addChild(vehiclerearCockpit);
        vehiclenoseLift.addChild(vehiclenoseSideR1);
        vehiclefeetR1.addChild(vehiclefeetR4);
        vehicleUpperlegRbase.addChild(vehicleupperlegR3);
        vehiclefeetR3.addChild(vehicletailWingL);
        vehicleWaist5.addChild(vehiclewaist2);
        vehicleengineL1.addChild(vehicleengineL4);
        vehiclecenterConnector1.addChild(vehiclenoseLift);
        vehiclewingbaseR.addChild(vehiclewingstrutR2);
        vehicleleftshoulderbase.addChild(vehicleleftshoulder3);
        vehiclechestcockpitbase.addChild(vehiclecockpit4);
        vehiclelowerRear.addChild(vehiclecenterConnector1);
        vehicleBody.addChild(vehicleWaist5);
        vehiclelowerlegRbase.addChild(vehiclelowerlegR1);
        vehiclewingstrutL2.addChild(vehiclewingL3);
        vehicleUpperlegLbase.addChild(vehicleupperlegL2);
        vehiclelowerlegR1.addChild(vehiclelowerlegR3);
        vehicleshoulderbase.addChild(vehiclerightshoulderbase);
        vehiclewingbaseR.addChild(vehiclewingstrutR1);
        vehiclelowerRear.addChild(vehiclerearSlantL);
        vehiclelowerlegLbase.addChild(vehicleengineL1);
        vehiclelowerlegLbase.addChild(vehiclelowerlegL1);
        vehiclelowerlegR1.addChild(vehiclelowerlegR5);
        vehiclelowerlegL5.addChild(vehiclerearWingConnectorL);
        vehiclefeetL1.addChild(vehiclefeetL3);
        vehicleBody.addChild(vehicleWaist4);
        vehiclewingstrutR1.addChild(vehiclewingR2);
        vehiclewingbaseL.addChild(vehiclewingLBase);
        vehicleengineR1.addChild(vehicleengineR3);
        vehiclelowerlegL1.addChild(vehiclelowerlegL5);
        vehiclelowerlegLbase.addChild(vehiclefeetbaseL);
        vehiclecockpit1.addChild(vehiclecockpit2);
        vehiclelowerlegR1.addChild(vehiclelowerlegR2);
        vehiclenoseLift.addChild(vehiclenoseSideL1);
        vehicleengineL1.addChild(vehicleengineL5);
        vehiclewingbaseR.addChild(vehiclewingR1);
        vehiclewingbaseL.addChild(vehiclewingstrutL1);
        vehiclelowerlegRbase.addChild(vehicleengineR1);
        vehicleengineL1.addChild(vehicleengineL2);
        vehiclewingLBase.addChild(vehiclewingConnectorL1);
        vehiclenoseLift.addChild(vehiclenoseBottom);
        vehiclewingbaseL.addChild(vehiclewingstrutL3);
        vehiclewingbaseR.addChild(vehiclewingRBase);
        vehiclefeetL4.addChild(vehicletailWingR);
        vehicleTorsoConnector.addChild(vehicleUpperBodyBase);
        vehiclelowerlegL1.addChild(vehiclelowerlegL2);
        vehicleengineR1.addChild(vehicleengineR2);
        vehiclechestcockpitbase.addChild(vehiclecockpit1);
        vehiclerightshoulderbase.addChild(vehiclerightshoulder5);
        vehiclerightshoulder1.addChild(vehiclerightshoulder2);
        vehicleupperarmbaseR.addChild(vehicleshoulderpieceR1);
        vehiclewingbaseL.addChild(vehiclewingstrutL2);
        vehicleupperarmbaseL.addChild(vehiclelowerarmbaseL);
        vehiclelowerRear.addChild(vehiclerear);
        vehiclewingbaseL.addChild(vehiclewingL1);
        vehicleengineR1.addChild(vehicleengineR4);
        vehicleshoulderbase.addChild(vehicleleftshoulderbase);

        setInitPose();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glPushMatrix();

        float scale = 1.1F;
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(0, -scale * 0.0625F, 0);

        super.render(entity, f, f1, f2, f3, f4, f5);
        GL11.glPopMatrix();
    }

    public Transformer getTransformer()
    {
        return TransformerManager.transformerCloudtrap;
    }

    public ModelRenderer getWaist()
    {
        return waist;
    }

    public ModelRenderer getVehicle(EntityPlayer player)
    {
        return vehicleBody;
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
    public void renderArmorPiece(int armorPiece)
    {
        setToInitPose();

        if (armorPiece == 0)
        {
            head.rotationPointX = 0;
            head.rotationPointY = 0;
            head.rotationPointZ = 0;
            head.render(0.0625F);
        }
        else if (armorPiece == 1)
        {
        	GL11.glTranslatef(0, -0.1F, 0);
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
            feetbaseL.showModel = false;
            feetbaseR.showModel = false;
            upperLegL.render(0.0625F);
            upperLegR.render(0.0625F);
            feetbaseL.showModel = true;
            feetbaseR.showModel = true;
        }
        else if (armorPiece == 3)
        {
            GL11.glRotatef(5.5F, 1, 0, 0);
            feetbaseL.rotationPointX -= 3.5F;
            feetbaseR.rotationPointX += 3.5F;
            feetbaseL.rotateAngleX += 0.2F;
            feetbaseL.rotateAngleY += 0.1F;
            feetbaseR.rotateAngleX += 0.2F;
            feetbaseR.rotateAngleY -= 0.1F;
            feetbaseL.render(0.0625F);
            feetbaseR.render(0.0625F);
        }
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, scale, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            setToInitPose();

            ModelOffset offsets = TFModelHelper.getOffsets(player);

            head.rotationPointX += offsets.headOffsetX;
            head.rotationPointY += offsets.headOffsetY;
            head.rotationPointZ += offsets.headOffsetZ;

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerCloudtrap;
            Transformer transformerChest = TFHelper.getTransformerFromArmor(player, 2);
            boolean wearingChest = transformerChest instanceof TransformerCloudtrap;
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerCloudtrap;

            float globalSpeed = 1;
            float globalDegree = 0.8F;

            head.showModel = wearingHead;
            upperLegR.showModel = wearingLegs;
            upperLegL.showModel = wearingLegs;

            if (wearingChest || wearingHead && !wearingLegs)
            {
                upperLegR.rotationPointY = 0;
                upperLegL.rotationPointY = 0;
            }

            waist.rotationPointY -= 0.4F;

            if (!wearingChest)
            {
                head.rotationPointZ -= 1.5F;
                head.rotationPointY += 0.6F;
                head.rotationPointX -= 0.5F;

                upperLegR.rotationPointY += 8.55F;
                upperLegL.rotationPointY += 8.55F;
            }

            if (!wearingLegs && wearingChest)
            {
                waist.rotationPointY += 0.6F;
            }

            if (wearingHead)
            {
                faceTarget(head, 1, rotationYaw, rotationPitch);
            }

            if (entity.isSneaking())
            {
                globalDegree = 0.4F;
                globalSpeed = 1.5F;
            }

            int backwardInverter = 1;

            if (player.moveForward < 0)
            {
                backwardInverter = -1;
                globalDegree = 0.5F;
            }

            applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
            applyDefaultHittingAnimation(upperArmR, upperArmL, head, torsoConnector, lowerArmR, lowerArmL);

            boolean sneaking = player.isSneaking();

            if (sneaking)
            {
                globalDegree = 1.5F;
                globalSpeed = 1.3F;

                if (player.capabilities.isFlying)
                {
                    globalDegree = 0.6F;
                    globalSpeed = 0.9F;
                }
            }

            if (isRiding)
            {
                upperArmR.rotateAngleX -= (float) Math.PI / 5F;
                upperArmL.rotateAngleX -= (float) Math.PI / 5F;
                upperLegR.rotateAngleX -= (float) Math.PI * 2F / 5F;
                upperLegL.rotateAngleX -= (float) Math.PI * 2F / 5F;

                upperLegR.rotateAngleY += (float) Math.PI / 10F;
                upperLegL.rotateAngleY -= (float) Math.PI / 10F;

                if (!wearingChest)
                {
                    upperLegL.rotationPointY += 3;
                    upperLegR.rotationPointY += 3;
                }
            }

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

                upperArmR.rotateAngleX += 0.25F;
                upperArmL.rotateAngleX += 0.25F;
            }

            if (wearingHead && wearingLegs && wearingChest)
            {
                boolean playerOnGround = onGround(player);

                if (playerOnGround || player.capabilities.isFlying)
                {
                    bob(waist, globalSpeed * 1F, globalDegree * 1, false, limbSwing, limbSwingAmount);

                    waist.rotationPointY += 1 * limbSwingAmount * 1.25F;
                    walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    walk(torsoConnector, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    swing(torsoConnector, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
                    swing(torsoConnector, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
                    swing(waist, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
                    walk(head, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    swing(head, 0.5F * globalSpeed, -0.4F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);

                    swing(upperLegL, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0F, limbSwing, limbSwingAmount);
                    swing(upperLegR, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0F, limbSwing, limbSwingAmount);

                    walk(upperLegR, globalSpeed * 0.5F, globalDegree * 1F, true, 0, 0.2F, limbSwing, limbSwingAmount);
                    walk(lowerlegRbase, 0.5F * globalSpeed, 1.3F * globalDegree, false, 1F * backwardInverter, 0.2F, limbSwing, limbSwingAmount);
                    walk(feetbaseR, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0F, 0F, limbSwing, limbSwingAmount);
                    walk(feetbaseR, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0.5F * backwardInverter, 0.3F, limbSwing, limbSwingAmount);

                    walk(upperLegL, globalSpeed * 0.5F, globalDegree * 1F, false, 0, 0.2F, limbSwing, limbSwingAmount);
                    walk(lowerlegLbase, 0.5F * globalSpeed, 1.3F * globalDegree, true, 1F * backwardInverter, 0.2F, limbSwing, limbSwingAmount);
                    walk(feetbaseL, 0.5F * globalSpeed, 0.6F * globalDegree, false, 0F, 0F, limbSwing, limbSwingAmount);
                    walk(feetbaseL, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0.5F * backwardInverter, 0.3F, limbSwing, limbSwingAmount);

                    walk(upperArmL, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    walk(upperArmR, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
                    walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);
                    walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);

                    walk(torsoConnector, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
                    walk(upperBodyBase, 0.08F, 0.05F, false, 1, 0, ticks, 1F);
                    walk(head, 0.08F, 0.04F, true, 1, 0, ticks, 1F);
                    walk(upperArmR, 0.08F, 0.04F, true, 1, 0, ticks, 1F);
                    walk(upperArmL, 0.08F, 0.04F, true, 1, 0, ticks, 1F);

                    flap(upperArmR, 0.08F, 0.04F, true, 1, 0, ticks, 1F);
                    flap(upperArmL, 0.08F, 0.04F, false, 1, 0, ticks, 1F);
                    walk(lowerArmR, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
                    walk(lowerArmL, 0.08F, 0.1F, true, 1, 0, ticks, 1F);

                    flap(wingbaseR, 0.08F, 0.04F, true, 1, 0, ticks, 1F);
                    flap(wingbaseL, 0.08F, 0.04F, false, 1, 0, ticks, 1F);

                    walk(wingstrutR1, 0.08F, 0.08F, true, 2.5F, 0, ticks, 1F);
                    walk(wingstrutL1, 0.08F, 0.08F, true, 2.5F, 0, ticks, 1F);

                    walk(wingstrutR3, 0.08F, 0.08F, false, 2.5F, 0, ticks, 1F);
                    walk(wingstrutL3, 0.08F, 0.08F, false, 2.5F, 0, ticks, 1F);

                    if (entity.isSneaking())
                    {
                        waist.rotateAngleX -= 0.1F;
                        waist.rotationPointY += 0.5F;
                        waist.rotationPointY -= limbSwing * limbSwingAmount * 0.0005F;
                        torsoConnector.rotateAngleX += 0.5;
                        head.rotateAngleX -= 0.5;
                        upperLegR.rotateAngleX -= 0.4;
                        upperLegL.rotateAngleX -= 0.4;
                        upperLegR.rotateAngleZ += 0.1;
                        upperLegL.rotateAngleZ -= 0.1;
                        upperLegR.rotateAngleY += 0.2;
                        upperLegL.rotateAngleY -= 0.2;
                        lowerlegRbase.rotateAngleX += 0.7;
                        lowerlegLbase.rotateAngleX += 0.7;
                        feetbaseR.rotateAngleX -= 0.15;
                        feetbaseL.rotateAngleX -= 0.15;
                        feetbaseR.rotateAngleZ -= 0.05;
                        feetbaseL.rotateAngleZ += 0.05;
                        upperArmR.rotateAngleX -= 0.5;
                        upperArmL.rotateAngleX -= 0.5;
                        upperArmR.rotateAngleZ += 0.5;
                        upperArmL.rotateAngleZ -= 0.5;
                        lowerArmR.rotateAngleZ -= 0.5;
                        lowerArmL.rotateAngleZ += 0.5;
                    }
                    else
                    {
                        upperArmR.rotateAngleZ += 0.075;
                        upperArmL.rotateAngleZ -= 0.075;
                        lowerArmR.rotateAngleZ -= 0.1;
                        lowerArmL.rotateAngleZ += 0.1;
                        lowerArmR.rotateAngleX -= 0.1;
                        lowerArmL.rotateAngleX -= 0.1;

                        upperLegR.rotateAngleZ += 0.05;
                        upperLegL.rotateAngleZ -= 0.05;

                        lowerlegRbase.rotateAngleZ -= 0.025;
                        lowerlegLbase.rotateAngleZ += 0.025;
                        feetbaseR.rotateAngleZ -= 0.025;
                        feetbaseL.rotateAngleZ += 0.025;
                    }
                }
                else
                {
                    double motionX = entity.posX - entity.prevPosX;
                    double motionZ = entity.posZ - entity.prevPosZ;

                    double motionY = TFRenderHelper.getMotionY(player);
                    float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
                    float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));

                    double speed = Math.sqrt(motionX * motionX + motionZ * motionZ) * 1.2D;

                    waist.rotateAngleX += 0.2 * limbSwingAmount * backwardInverter;

                    head.rotateAngleX -= 0.2 * upwardPose;
                    torsoConnector.rotateAngleX -= 0.2 * upwardPose;

                    rightshoulderbase.rotateAngleZ -= 0.5 * upwardPose;
                    leftshoulderbase.rotateAngleZ += 0.5 * upwardPose;

                    upperLegR.rotateAngleX -= 0.5 * upwardPose * 1.25;
                    lowerlegRbase.rotateAngleX += 0.75 * upwardPose * 1.25;

                    upperLegL.rotateAngleX -= 0.5 * upwardPose * 1.25;
                    lowerlegLbase.rotateAngleX += 0.75 * upwardPose * 1.25;

                    feetbaseL.rotateAngleX -= 0.45 * upwardPose * 1.25;
                    feetbaseR.rotateAngleX -= 0.45 * upwardPose * 1.25;

                    upperLegR.rotateAngleX += 0.2 * upwardPose;
                    upperLegL.rotateAngleX -= 0.2 * upwardPose;

                    feetbaseL.rotateAngleX += 0.2 * upwardPose;
                    feetbaseR.rotateAngleX -= 0.2 * upwardPose;

                    waist.rotateAngleX += 0.25 * upwardPose * 1.25;

                    walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, limbSwing, limbSwingAmount);
                    walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, limbSwing, limbSwingAmount);
                    walk(lowerlegRbase, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, limbSwing, limbSwingAmount);
                    walk(lowerlegLbase, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, limbSwing, limbSwingAmount);

                    waist.rotateAngleX -= 0.2 * downwardPose;
                    upperBodyBase.rotateAngleX += 0.3 * downwardPose;
                    torsoConnector.rotateAngleX += 0.3 * downwardPose;
                    head.rotateAngleX += 0.3 * downwardPose;
                    upperLegR.rotateAngleX -= 0.6 * downwardPose;
                    upperLegL.rotateAngleX -= 0.2 * downwardPose;
                    lowerlegRbase.rotateAngleX += 1.4F * downwardPose;
                    lowerlegLbase.rotateAngleX += 0.5F * downwardPose;
                    upperArmR.rotateAngleZ += 0.5F * downwardPose;
                    upperArmL.rotateAngleZ -= 0.5F * downwardPose;
                    lowerArmR.rotateAngleX -= 1 * downwardPose;
                    lowerArmL.rotateAngleX -= 1 * downwardPose;

                    wingbaseR.rotateAngleZ += 0.5F * downwardPose;
                    wingbaseL.rotateAngleZ -= 0.5F * downwardPose;

                    wingstrutR1.rotateAngleX -= 0.25F * downwardPose;
                    wingstrutR3.rotateAngleX += 0.25F * downwardPose;

                    wingstrutL1.rotateAngleX -= 0.25F * downwardPose;
                    wingstrutL3.rotateAngleX += 0.25F * downwardPose;
                }
            }
            else
            {
                upperArmL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 2;
                upperArmR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 2;

                lowerArmL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 4;
                lowerArmR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 4;

                upperLegR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 2;
                upperLegL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 2;

                if (isSneak)
                {
                    waist.rotateAngleX += 0.4F;
                    waist.rotationPointZ += 3.5F;
                    waist.rotationPointY -= 2F;
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

            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null && heldItem.getItem() == TFItems.cloudtrapsFlamethrower && TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) == 20 && player.isUsingItem())
            {
                setRotation(upperArmR, bipedHead.rotateAngleX - pi / 2 + 0.2F, bipedHead.rotateAngleY, 0.1F);
                setRotation(lowerArmR, -0.2F, 0, 0);
            }

            float t = TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick);
            float f = 20 - t;

            ModelBiped modelBiped = TFModelHelper.modelBipedMain;

            if (modelBiped != null)
            {
                vehicleBody.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
                vehicleBody.rotateAngleZ = -modelBiped.bipedHead.rotateAngleY;
            }

            VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
            int landingTimer = 20;

            if (transformedPlayer != null)
            {
                landingTimer = transformedPlayer.getLandingTimer();
                float f1 = (float) landingTimer / 20;
                float f2 = 1 - f1;

                vehicleBody.rotateAngleX = (rotationPitch / (180F / (float) Math.PI)) * f1;
                vehicleBody.rotateAngleZ = -modelBiped.bipedHead.rotateAngleY * f1;
                vehicleBody.setRotationPoint(0, 18 * f2, 0);
            }

            rotateTo(waist, vehicleBody, f);
            rotateTo(upperLegL, vehicleUpperlegLbase, f);
            rotateTo(upperLegR, vehicleUpperlegRbase, f);

            rotateTo(cockpit1, vehiclecockpit1, f);
            rotateTo(cockpit2, vehiclecockpit2, f);
            rotateTo(cockpit3, vehiclecockpit3, f);
            rotateTo(cockpit4, vehiclecockpit4, f);
            rotateTo(cockpit5, vehiclecockpit5, f);

            rotateTo(engineL1, vehicleengineL1, f);
            rotateTo(engineL2, vehicleengineL2, f);
            rotateTo(engineL3, vehicleengineL3, f);
            rotateTo(engineL4, vehicleengineL4, f);
            rotateTo(engineL5, vehicleengineL5, f);

            rotateTo(engineR1, vehicleengineR1, f);
            rotateTo(engineR2, vehicleengineR2, f);
            rotateTo(engineR3, vehicleengineR3, f);
            rotateTo(engineR4, vehicleengineR4, f);
            rotateTo(engineR5, vehicleengineR5, f);

            rotateTo(feetbaseL, vehiclefeetbaseL, f);
            rotateTo(feetbaseR, vehiclefeetbaseR, f);

            rotateTo(feetL1, vehiclefeetL1, f);
            rotateTo(feetL3, vehiclefeetL3, f);
            rotateTo(feetL4, vehiclefeetL4, f);

            rotateTo(feetR1, vehiclefeetR1, f);
            rotateTo(feetR3, vehiclefeetR3, f);
            rotateTo(feetR4, vehiclefeetR4, f);

            rotateTo(leftshoulder1, vehicleleftshoulder1, f);
            rotateTo(leftshoulder3, vehicleleftshoulder3, f);
            rotateTo(leftshoulder4, vehicleleftshoulder4, f);
            rotateTo(leftshoulder5, vehicleleftshoulder5, f);

            rotateTo(rightshoulder1, vehiclerightshoulder1, f);
            rotateTo(rightshoulder2, vehiclerightshoulder2, f);
            rotateTo(rightshoulder3, vehiclerightshoulder3, f);
            rotateTo(rightshoulder5, vehiclerightshoulder5, f);

            rotateTo(leftshoulderbase, vehicleleftshoulderbase, f);
            rotateTo(rightshoulderbase, vehiclerightshoulderbase, f);

            rotateTo(lowerArmL, vehiclelowerarmbaseL, f);
            rotateTo(lowerArmR, vehiclelowerarmbaseR, f);

            rotateTo(lowerarmL1, vehiclelowerarmL1, f);
            rotateTo(lowerarmL2, vehiclelowerarmL2, f);
            rotateTo(lowerarmL3, vehiclelowerarmL3, f);

            rotateTo(lowerarmR1, vehiclelowerarmR1, f);
            rotateTo(lowerarmR2, vehiclelowerarmR2, f);
            rotateTo(lowerarmR3, vehiclelowerarmR3, f);

            rotateTo(lowerlegL1, vehiclelowerlegL1, f);
            rotateTo(lowerlegL2, vehiclelowerlegL2, f);
            rotateTo(lowerlegL3, vehiclelowerlegL3, f);
            rotateTo(lowerlegL4, vehiclelowerlegL4, f);
            rotateTo(lowerlegL5, vehiclelowerlegL5, f);

            rotateTo(lowerlegR1, vehiclelowerlegR1, f);
            rotateTo(lowerlegR2, vehiclelowerlegR2, f);
            rotateTo(lowerlegR3, vehiclelowerlegR3, f);
            rotateTo(lowerlegR4, vehiclelowerlegR4, f);
            rotateTo(lowerlegR5, vehiclelowerlegR5, f);

            rotateTo(lowerlegLbase, vehiclelowerlegLbase, f);
            rotateTo(lowerlegRbase, vehiclelowerlegRbase, f);

            rotateTo(shoulderbase, vehicleshoulderbase, f);

            rotateTo(shoulderpieceL1, vehicleshoulderpieceL1, f);
            rotateTo(shoulderpieceR1, vehicleshoulderpieceR1, f);

            rotateTo(torsoConnector, vehicleTorsoConnector, f);

            rotateTo(torsoL2, vehicletorsoL2, f);
            rotateTo(torsoR2, vehicleTorsoR2, f);

            rotateTo(upperArmL, vehicleupperarmbaseL, f);
            rotateTo(upperArmR, vehicleupperarmbaseR, f);

            rotateTo(upperBodyBase, vehicleUpperBodyBase, f);
            rotateTo(upperlegL2, vehicleupperlegL2, f);
            rotateTo(upperlegL3, vehicleupperlegL3, f);
            rotateTo(upperlegR2, vehicleupperlegR2, f);
            rotateTo(upperlegR3, vehicleupperlegR3, f);

            rotateTo(upperLegL, vehicleUpperlegLbase, f);
            rotateTo(upperLegR, vehicleUpperlegRbase, f);

            rotateTo(waist1, vehicleWaist1, f);
            rotateTo(waist2, vehiclewaist2, f);
            rotateTo(wasit3, vehiclewaist3, f);
            rotateTo(waist4, vehicleWaist4, f);
            rotateTo(waist5, vehicleWaist5, f);

            rotateTo(wingbaseL, vehiclewingbaseL, f);
            rotateTo(wingbaseR, vehiclewingbaseR, f);

            rotateTo(wingconnectorL, vehiclewingconnectorL, f);
            rotateTo(wingconnectorR, vehiclewingconnectorR, f);

            rotateTo(wingL1, vehiclewingL1, f);
            rotateTo(wingL2, vehiclewingL2, f);
            rotateTo(wingL3, vehiclewingL3, f);
            rotateTo(wingL4, vehiclewingL4, f);

            rotateTo(wingR1, vehiclewingR1, f);
            rotateTo(wingR2, vehiclewingR2, f);
            rotateTo(wingR3, vehiclewingR3, f);
            rotateTo(wingR4, vehiclewingR4, f);

            rotateTo(wingstrutL1, vehiclewingstrutL1, f);
            rotateTo(wingstrutL2, vehiclewingstrutL2, f);
            rotateTo(wingstrutL3, vehiclewingstrutL3, f);

            rotateTo(wingstrutR1, vehiclewingstrutR1, f);
            rotateTo(wingstrutR2, vehiclewingstrutR2, f);
            rotateTo(wingstrutR3, vehiclewingstrutR3, f);

            head.rotationPointY += f * 0.125F;

            fistLbase.rotationPointY -= f * 0.25F;
            fistRbase.rotationPointY -= f * 0.25F;

            rotateTo(chestcockpitbase, vehiclechestcockpitbase, f);
        }
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
