package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ModelSkystrike extends MowzieModelBase
{
	public MowzieModelRenderer waist;
	public MowzieModelRenderer stomach;
	public MowzieModelRenderer chestcenter;
	public MowzieModelRenderer rearstomach;
	public MowzieModelRenderer crotch1;
	public MowzieModelRenderer crotchfront;
	public MowzieModelRenderer buttflapL;
	public MowzieModelRenderer buttflapR;
	public MowzieModelRenderer crotchBack;
	public MowzieModelRenderer upperLegR;
	public MowzieModelRenderer upperLegL;
	public MowzieModelRenderer middlelegR;
	public MowzieModelRenderer lowerLegR;
	public MowzieModelRenderer legarmorR;
	public MowzieModelRenderer feetconnectorR;
	public MowzieModelRenderer wheelconnectorR;
	public MowzieModelRenderer feetbaseR;
	public MowzieModelRenderer wheelR;
	public MowzieModelRenderer feetpartR1;
	public MowzieModelRenderer feetpartR2;
	public MowzieModelRenderer toeR1;
	public MowzieModelRenderer toeR2;
	public MowzieModelRenderer middlelegL;
	public MowzieModelRenderer lowerlegL;
	public MowzieModelRenderer legarmorL;
	public MowzieModelRenderer feetconnectorL;
	public MowzieModelRenderer wheelconnectorL;
	public MowzieModelRenderer feetbaseL;
	public MowzieModelRenderer wheelL;
	public MowzieModelRenderer toeL1;
	public MowzieModelRenderer toeL2;
	public MowzieModelRenderer feetpartL2;
	public MowzieModelRenderer feetpartL1;
	public MowzieModelRenderer chest1;
	public MowzieModelRenderer bodywingR1;
	public MowzieModelRenderer bodywingL1;
	public MowzieModelRenderer neck;
	public MowzieModelRenderer upperchestR;
	public MowzieModelRenderer cockpit1;
	public MowzieModelRenderer cockpit2;
	public MowzieModelRenderer lowerchestR;
	public MowzieModelRenderer LowerchestL;
	public MowzieModelRenderer upperchestL;
	public MowzieModelRenderer upperchestM;
	public MowzieModelRenderer lowerchestM;
	public MowzieModelRenderer energyUP;
	public MowzieModelRenderer energyDOWN;
	public MowzieModelRenderer collarR;
	public MowzieModelRenderer collarL;
	public MowzieModelRenderer collarM;
	public MowzieModelRenderer shoulderL;
	public MowzieModelRenderer shoulderR;
	public MowzieModelRenderer bodywingR2;
	public MowzieModelRenderer bodywingL2;
	public MowzieModelRenderer headbase;
	public MowzieModelRenderer headnose;
	public MowzieModelRenderer headhelmet1;
	public MowzieModelRenderer headwingR;
	public MowzieModelRenderer headhelmet2;
	public MowzieModelRenderer headwingL;
	public MowzieModelRenderer headintakeR1;
	public MowzieModelRenderer headintakeL2;
	public MowzieModelRenderer headintakeR2;
	public MowzieModelRenderer headintakeL1;
	public MowzieModelRenderer wingL1biped;
	public MowzieModelRenderer intakeL;
	public MowzieModelRenderer upperArmL;
	public MowzieModelRenderer wingL2biped;
	public MowzieModelRenderer lowerArmL1;
	public MowzieModelRenderer lowerarmL2;
	public MowzieModelRenderer lowerarmL3;
	public MowzieModelRenderer lowerarmL4;
	public MowzieModelRenderer fistL;
	public MowzieModelRenderer wingR1biped;
	public MowzieModelRenderer intakeR1;
	public MowzieModelRenderer upperArmR;
	public MowzieModelRenderer wingR2biped;
	public MowzieModelRenderer lowerArmR1;
	public MowzieModelRenderer lowerarmR4;
	public MowzieModelRenderer lowerarmR3;
	public MowzieModelRenderer lowerarmR2;
	public MowzieModelRenderer fistR;

	public ModelRenderer mainbody1;
	public ModelRenderer mainbody2;
	public ModelRenderer mainbody3;
	public ModelRenderer mainbody4;
	public ModelRenderer mainbody5;
	public ModelRenderer mainbody6;
	public ModelRenderer cockpit;
	public ModelRenderer mainbody7;
	public ModelRenderer mainbody8;
	public ModelRenderer mainbody9;
	public ModelRenderer mainbody10;
	public ModelRenderer mainbody11;
	public ModelRenderer mainbody12;
	public ModelRenderer front1;
	public ModelRenderer front2;
	public ModelRenderer front3;
	public ModelRenderer mainbody13;
	public ModelRenderer mainbody14;
	public ModelRenderer rearramp1;
	public ModelRenderer engineL1;
	public ModelRenderer wingL1;
	public ModelRenderer wingL2;
	public ModelRenderer engineL2;
	public ModelRenderer engineL3;
	public ModelRenderer exhaust2;
	public ModelRenderer rearR1;
	public ModelRenderer rearR2;
	public ModelRenderer wheel1;
	public ModelRenderer bodyplate1;
	public ModelRenderer cannon1;
	public ModelRenderer cannon2;
	public ModelRenderer rearwing1;
	public ModelRenderer engineR1;
	public ModelRenderer wingR1;
	public ModelRenderer engineR2;
	public ModelRenderer engineR3;
	public ModelRenderer wingR2;
	public ModelRenderer exhaust1;
	public ModelRenderer legL1;
	public ModelRenderer legL2;
	public ModelRenderer wheel2;
	public ModelRenderer bodyplate2;
	public ModelRenderer cannon3;
	public ModelRenderer cannon4;
	public ModelRenderer rearwing2;

	public ModelSkystrike()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;

		bipedBody = new MowzieModelRenderer(this, 1000, 1000);
		bipedHead = new MowzieModelRenderer(this, 1000, 1000);
		bipedHeadwear = new MowzieModelRenderer(this, 1000, 1000);
		bipedRightLeg = new MowzieModelRenderer(this, 1000, 1000);
		bipedLeftLeg = new MowzieModelRenderer(this, 1000, 1000);
		bipedRightArm = new MowzieModelRenderer(this, 1000, 1000);
		bipedLeftArm = new MowzieModelRenderer(this, 1000, 1000);

		this.textureWidth = 128;
		this.textureHeight = 128;
		this.feetconnectorR = new MowzieModelRenderer(this, 62, 74);
		this.feetconnectorR.mirror = true;
		this.feetconnectorR.setRotationPoint(0.0F, 7.0F, -1.0F);
		this.feetconnectorR.addBox(-1.3F, -1.2F, -1.5F, 2, 4, 2, 0.0F);
		this.setRotation(feetconnectorR, 0.2897246558310587F, 0.04363323129985824F, 0.0F);
		this.bodywingL2 = new MowzieModelRenderer(this, 7, 35);
		this.bodywingL2.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.bodywingL2.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
		this.setRotation(bodywingL2, -1.1519173063162573F, 0.03490658503988659F, 0.003490658503988659F);
		this.middlelegL = new MowzieModelRenderer(this, 75, 49);
		this.middlelegL.mirror = true;
		this.middlelegL.setRotationPoint(1.4F, 2.5F, 0.9F);
		this.middlelegL.addBox(-1.0F, -0.1F, 0.5F, 2, 2, 4, 0.0F);
		this.setRotation(middlelegL, -0.08831366015091306F, -0.06981317007977318F, 0.0F);
		this.toeL2 = new MowzieModelRenderer(this, 73, 76);
		this.toeL2.mirror = true;
		this.toeL2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeL2.addBox(-1.8F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeL2, 0.027925268031909273F, -0.11344640137963141F, 0.02617993877991494F);
		this.lowerArmL1 = new MowzieModelRenderer(this, 40, 87);
		this.lowerArmL1.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.lowerArmL1.addBox(-1.0F, 1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(lowerArmL1, -0.19198621771937624F, 0.06981317007977318F, 0.05235987755982988F);
		this.feetbaseR = new MowzieModelRenderer(this, 62, 68);
		this.feetbaseR.setRotationPoint(0.0F, 0.0F, -0.3F);
		this.feetbaseR.addBox(-1.8F, 1.0F, -1.8F, 3, 3, 3, 0.0F);
		this.setRotation(feetbaseR, 0.6108652381980153F, 0.0F, 0.0F);
		this.wingR1biped = new MowzieModelRenderer(this, 36, 60);
		this.wingR1biped.setRotationPoint(-4.0F, -3.0F, -1.8F);
		this.wingR1biped.addBox(-6.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(wingR1biped, 0.017453292519943295F, 0.07853981633974483F, 0.5061454830783556F);
		this.buttflapL = new MowzieModelRenderer(this, 36, 108);
		this.buttflapL.setRotationPoint(1.0F, -1.0F, 2.0F);
		this.buttflapL.addBox(-0.4F, 0.0F, -0.7F, 3, 4, 1, 0.0F);
		this.setRotation(buttflapL, 0.17453292519943295F, 0.12217304763960307F, -0.15707963267948966F);
		this.energyUP = new MowzieModelRenderer(this, 12, 93);
		this.energyUP.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.energyUP.addBox(-2.0F, 0.2F, -3.3F, 4, 2, 3, 0.0F);
		this.setRotation(energyUP, 0.9826203688728075F, 0.0F, 0.0F);
		this.buttflapR = new MowzieModelRenderer(this, 36, 108);
		this.buttflapR.mirror = true;
		this.buttflapR.setRotationPoint(-1.0F, -1.0F, 2.0F);
		this.buttflapR.addBox(-2.6F, 0.0F, -0.7F, 3, 4, 1, 0.0F);
		this.setRotation(buttflapR, 0.17453292519943295F, -0.12217304763960307F, 0.15707963267948966F);
		this.lowerchestM = new MowzieModelRenderer(this, 0, 97);
		this.lowerchestM.setRotationPoint(0.0F, 7.0F, 3.0F);
		this.lowerchestM.addBox(-1.5F, 0.3F, -5.8F, 3, 2, 4, 0.0F);
		this.setRotation(lowerchestM, -0.9948376736367678F, 0.0F, 0.0F);
		this.headintakeR2 = new MowzieModelRenderer(this, 16, 117);
		this.headintakeR2.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.headintakeR2.addBox(-1.2F, -1.0F, -3.6F, 1, 2, 1, 0.0F);
		this.setRotation(headintakeR2, 0.061086523819801536F, 0.3141592653589793F, 0.0F);
		this.bodywingR2 = new MowzieModelRenderer(this, 7, 35);
		this.bodywingR2.mirror = true;
		this.bodywingR2.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.bodywingR2.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
		this.setRotation(bodywingR2, -1.1519173063162573F, -0.03490658503988659F, -0.003490658503988659F);
		this.headnose = new MowzieModelRenderer(this, 0, 121);
		this.headnose.setRotationPoint(0.0F, -1.0F, 0.3F);
		this.headnose.addBox(-0.5F, -1.1F, -2.7F, 1, 2, 1, 0.0F);
		this.setRotation(headnose, -0.0890117918517108F, 0.0F, 0.0F);
		this.upperchestL = new MowzieModelRenderer(this, 12, 76);
		this.upperchestL.setRotationPoint(4.0F, 0.0F, 0.0F);
		this.upperchestL.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(upperchestL, 0.9948376736367678F, 0.0F, 0.0F);
		this.crotchfront = new MowzieModelRenderer(this, 46, 102);
		this.crotchfront.setRotationPoint(-1.0F, -1.0F, -4.0F);
		this.crotchfront.addBox(0.0F, 0.3F, 0.4F, 2, 5, 2, 0.0F);
		this.setRotation(crotchfront, 0.12217304763960307F, 0.0F, 0.0F);
		this.wheelL = new MowzieModelRenderer(this, 75, 55);
		this.wheelL.mirror = true;
		this.wheelL.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelL.addBox(-1.0F, 1.0F, 1.5F, 2, 3, 3, 0.0F);
		this.setRotation(wheelL, 0.5864306286700948F, 0.0F, 0.0F);
		this.toeR1 = new MowzieModelRenderer(this, 73, 76);
		this.toeR1.mirror = true;
		this.toeR1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeR1.addBox(-2.2F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeR1, 0.027925268031909273F, 0.11344640137963141F, -0.008726646259971648F);
		this.rearstomach = new MowzieModelRenderer(this, 0, 76);
		this.rearstomach.setRotationPoint(0.0F, -8.6F, 2.9F);
		this.rearstomach.addBox(-2.0F, -0.7F, -0.4F, 4, 6, 2, 0.0F);
		this.setRotation(rearstomach, -0.46774823953448036F, 0.0F, 0.0F);
		this.collarL = new MowzieModelRenderer(this, 0, 103);
		this.collarL.setRotationPoint(4.0F, -1.5F, 3.0F);
		this.collarL.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
		this.setRotation(collarL, -0.0471238898038469F, -0.296705972839036F, 0.03490658503988659F);
		this.shoulderL = new MowzieModelRenderer(this, 36, 51);
		this.shoulderL.setRotationPoint(4.0F, 2.5F, 4.0F);
		this.shoulderL.addBox(0.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.bodywingL1 = new MowzieModelRenderer(this, 0, 55);
		this.bodywingL1.setRotationPoint(3.0F, 0.2F, 6.0F);
		this.bodywingL1.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(bodywingL1, 0.0F, 0.9250245035569946F, 0.2792526803190927F);
		this.energyDOWN = new MowzieModelRenderer(this, 0, 49);
		this.energyDOWN.setRotationPoint(0.0F, 7.1F, 3.0F);
		this.energyDOWN.addBox(-2.0F, -0.4F, -6.0F, 4, 2, 4, 0.0F);
		this.setRotation(energyDOWN, -1.0018189906447452F, 0.0F, 0.0F);
		this.collarM = new MowzieModelRenderer(this, 10, 98);
		this.collarM.setRotationPoint(-3.0F, -1.4F, 4.9F);
		this.collarM.addBox(0.0F, -0.02F, 0.0F, 6, 2, 1, 0.0F);
		this.setRotation(collarM, -0.03490658503988659F, 0.0F, 0.0F);
		this.chestcenter = new MowzieModelRenderer(this, 0, 0);
		this.chestcenter.setRotationPoint(0.0F, -10.0F, 2.0F);
		this.chestcenter.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.setRotation(chestcenter, -0.10646508437165408F, 0.0F, 0.0F);
		this.cockpit1 = new MowzieModelRenderer(this, 0, 84);
		this.cockpit1.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.cockpit1.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 2, 0.0F);
		this.setRotation(cockpit1, -0.5585053606381855F, 0.0F, 0.0017453292519943296F);
		this.collarR = new MowzieModelRenderer(this, 0, 103);
		this.collarR.mirror = true;
		this.collarR.setRotationPoint(-4.0F, -1.5F, 3.0F);
		this.collarR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
		this.setRotation(collarR, -0.0471238898038469F, 0.296705972839036F, -0.03490658503988659F);
		this.feetpartR1 = new MowzieModelRenderer(this, 63, 80);
		this.feetpartR1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartR1.addBox(-1.7F, 1.0F, -3.0F, 3, 2, 1, 0.0F);
		this.setRotation(feetpartR1, -0.0767944870877505F, 0.11344640137963141F, -0.017453292519943295F);
		this.wingL2biped = new MowzieModelRenderer(this, 36, 65);
		this.wingL2biped.mirror = true;
		this.wingL2biped.setRotationPoint(-2.0F, -1.0F, 4.2F);
		this.wingL2biped.addBox(2.0F, 0.1F, -0.2F, 6, 1, 2, 0.0F);
		this.setRotation(wingL2biped, -0.3665191429188092F, 0.0F, 0.0F);
		this.wheelconnectorL = new MowzieModelRenderer(this, 71, 64);
		this.wheelconnectorL.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelconnectorL.addBox(-1.3F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
		this.setRotation(wheelconnectorL, 0.03490658503988659F, 0.0F, 0.0F);
		this.lowerarmL3 = new MowzieModelRenderer(this, 36, 76);
		this.lowerarmL3.mirror = true;
		this.lowerarmL3.setRotationPoint(1.0F, 5.8F, 0.3F);
		this.lowerarmL3.addBox(0.0F, -5.2F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotation(lowerarmL3, -0.03490658503988659F, -0.12566370614359174F, 0.04363323129985824F);
		this.intakeL = new MowzieModelRenderer(this, 36, 68);
		this.intakeL.mirror = true;
		this.intakeL.setRotationPoint(4.0F, -3.0F, -0.5F);
		this.intakeL.addBox(-1.0F, 0.0F, -2.0F, 1, 4, 4, 0.0F);
		this.setRotation(intakeL, 0.0F, 0.0F, -0.22689280275926282F);
		this.feetpartR2 = new MowzieModelRenderer(this, 74, 69);
		this.feetpartR2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartR2.addBox(-2.2F, 1.0F, -5.0F, 4, 1, 3, 0.0F);
		this.setRotation(feetpartR2, 0.4625122517784973F, 0.11344640137963141F, -0.017453292519943295F);
		this.headbase = new MowzieModelRenderer(this, 0, 120);
		this.headbase.setRotationPoint(0.0F, -2.0F, 2.5F);
		this.headbase.addBox(-2.0F, -3.0F, -2.0F, 4, 4, 4, 0.0F);
		this.middlelegR = new MowzieModelRenderer(this, 75, 49);
		this.middlelegR.setRotationPoint(-1.6F, 2.5F, 0.9F);
		this.middlelegR.addBox(-1.0F, -0.1F, 0.5F, 2, 2, 4, 0.0F);
		this.setRotation(middlelegR, -0.08831366015091306F, 0.06981317007977318F, 0.0F);
		this.feetconnectorL = new MowzieModelRenderer(this, 62, 74);
		this.feetconnectorL.mirror = true;
		this.feetconnectorL.setRotationPoint(0.0F, 7.0F, -1.0F);
		this.feetconnectorL.addBox(-0.7F, -1.2F, -1.5F, 2, 4, 2, 0.0F);
		this.setRotation(feetconnectorL, 0.2897246558310587F, -0.04363323129985824F, 0.0F);
		this.lowerchestR = new MowzieModelRenderer(this, 0, 90);
		this.lowerchestR.setRotationPoint(-4.0F, 7.0F, 3.0F);
		this.lowerchestR.addBox(0.0F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(lowerchestR, -0.9948376736367678F, 0.0F, 0.0F);
		this.upperchestM = new MowzieModelRenderer(this, 8, 87);
		this.upperchestM.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.upperchestM.addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
		this.setRotation(upperchestM, 0.9948376736367678F, 0.0F, 0.0F);
		this.toeR2 = new MowzieModelRenderer(this, 73, 76);
		this.toeR2.mirror = true;
		this.toeR2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeR2.addBox(0.8F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeR2, 0.027925268031909273F, 0.11344640137963141F, -0.02617993877991494F);
		this.lowerArmR1 = new MowzieModelRenderer(this, 40, 87);
		this.lowerArmR1.mirror = true;
		this.lowerArmR1.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.lowerArmR1.addBox(-1.0F, 1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(lowerArmR1, -0.19198621771937624F, -0.05235987755982988F, -0.06981317007977318F);
		this.headhelmet2 = new MowzieModelRenderer(this, 13, 114);
		this.headhelmet2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headhelmet2.addBox(-2.5F, -3.17F, -3.1F, 5, 1, 2, 0.0F);
		this.setRotation(headhelmet2, 0.061086523819801536F, 0.0F, 0.0F);
		this.lowerlegL = new MowzieModelRenderer(this, 63, 58);
		this.lowerlegL.mirror = true;
		this.lowerlegL.setRotationPoint(0.0F, -0.9F, 5.0F);
		this.lowerlegL.addBox(-1.3F, -0.2F, -1.1F, 3, 7, 3, 0.0F);
		this.setRotation(lowerlegL, -0.1780235837034216F, 0.0F, 0.0F);
		this.legarmorR = new MowzieModelRenderer(this, 73, 73);
		this.legarmorR.mirror = true;
		this.legarmorR.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.legarmorR.addBox(-1.0F, -1.3F, -0.2F, 2, 8, 1, 0.0F);
		this.shoulderR = new MowzieModelRenderer(this, 36, 51);
		this.shoulderR.mirror = true;
		this.shoulderR.setRotationPoint(-4.0F, 2.5F, 4.0F);
		this.shoulderR.addBox(-4.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.waist = new MowzieModelRenderer(this, 36, 95);
		this.waist.setRotationPoint(0.0F, 8.7F, 3.4F);
		this.waist.addBox(-3.5F, -1.0F, -3.0F, 7, 2, 5, 0.0F);
		this.crotchBack = new MowzieModelRenderer(this, 44, 109);
		this.crotchBack.setRotationPoint(-1.0F, 1.0F, 1.0F);
		this.crotchBack.addBox(0.0F, -0.3F, -1.2F, 2, 3, 2, 0.0F);
		this.setRotation(crotchBack, -0.28797932657906433F, 0.0F, 0.0F);
		this.feetbaseL = new MowzieModelRenderer(this, 62, 68);
		this.feetbaseL.setRotationPoint(0.0F, 0.0F, -0.3F);
		this.feetbaseL.addBox(-1.2F, 1.0F, -1.8F, 3, 3, 3, 0.0F);
		this.setRotation(feetbaseL, 0.6108652381980153F, 0.0F, 0.0F);
		this.LowerchestL = new MowzieModelRenderer(this, 0, 90);
		this.LowerchestL.mirror = true;
		this.LowerchestL.setRotationPoint(4.0F, 7.0F, 3.0F);
		this.LowerchestL.addBox(-2.0F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(LowerchestL, -0.9948376736367678F, 0.0F, 0.0F);
		this.bodywingR1 = new MowzieModelRenderer(this, 0, 55);
		this.bodywingR1.mirror = true;
		this.bodywingR1.setRotationPoint(-3.0F, 0.2F, 6.0F);
		this.bodywingR1.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(bodywingR1, 0.0F, -0.9250245035569946F, -0.2792526803190927F);
		this.headhelmet1 = new MowzieModelRenderer(this, 0, 114);
		this.headhelmet1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headhelmet1.addBox(-2.5F, -3.1F, -1.3F, 5, 3, 3, 0.0F);
		this.wingR2biped = new MowzieModelRenderer(this, 36, 65);
		this.wingR2biped.setRotationPoint(2.0F, -1.0F, 4.2F);
		this.wingR2biped.addBox(-8.0F, 0.1F, -0.2F, 6, 1, 2, 0.0F);
		this.setRotation(wingR2biped, -0.3665191429188092F, 0.0F, 0.0F);
		this.feetpartL1 = new MowzieModelRenderer(this, 63, 80);
		this.feetpartL1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartL1.addBox(-1.3F, 1.0F, -3.0F, 3, 2, 1, 0.0F);
		this.setRotation(feetpartL1, -0.0767944870877505F, -0.11344640137963141F, 0.017453292519943295F);
		this.fistR = new MowzieModelRenderer(this, 46, 78);
		this.fistR.mirror = true;
		this.fistR.setRotationPoint(0.0F, 4.0F, 1.0F);
		this.fistR.addBox(-0.8F, -0.4F, -2.0F, 2, 2, 3, 0.0F);
		this.setRotation(fistR, 0.05235987755982988F, 0.017453292519943295F, -0.2792526803190927F);
		this.crotch1 = new MowzieModelRenderer(this, 36, 102);
		this.crotch1.setRotationPoint(-1.0F, 1.0F, -2.0F);
		this.crotch1.addBox(0.0F, 0.0F, 0.0F, 2, 3, 3, 0.0F);
		this.cockpit2 = new MowzieModelRenderer(this, 10, 82);
		this.cockpit2.mirror = true;
		this.cockpit2.setRotationPoint(0.0F, -0.5F, 5.8F);
		this.cockpit2.addBox(-1.0F, 0.2F, -0.6F, 2, 2, 3, 0.0F);
		this.setRotation(cockpit2, 0.24434609527920614F, 0.0F, 0.0F);
		this.headwingL = new MowzieModelRenderer(this, 12, 120);
		this.headwingL.setRotationPoint(0.0F, 0.0F, 0.5F);
		this.headwingL.addBox(1.5F, -3.1F, 1.2F, 1, 2, 2, 0.0F);
		this.lowerarmR3 = new MowzieModelRenderer(this, 36, 76);
		this.lowerarmR3.setRotationPoint(-3.0F, 5.8F, 0.3F);
		this.lowerarmR3.addBox(1.0F, -5.2F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotation(lowerarmR3, -0.03490658503988659F, 0.12566370614359174F, -0.06335545184739416F);
		this.intakeR1 = new MowzieModelRenderer(this, 36, 68);
		this.intakeR1.setRotationPoint(-4.0F, -3.0F, -0.5F);
		this.intakeR1.addBox(0.0F, 0.0F, -2.0F, 1, 4, 4, 0.0F);
		this.setRotation(intakeR1, 0.0F, 0.0F, 0.22689280275926282F);
		this.upperLegL = new MowzieModelRenderer(this, 63, 49);
		this.upperLegL.mirror = true;
		this.upperLegL.setRotationPoint(2.0F, 0.5F, 1.1F);
		this.upperLegL.addBox(0.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(upperLegL, -0.6457718232379019F, 0.0F, -0.08726646259971647F);
		this.neck = new MowzieModelRenderer(this, 23, 67);
		this.neck.setRotationPoint(-1.0F, -2.0F, 1.5F);
		this.neck.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
		this.upperchestR = new MowzieModelRenderer(this, 12, 76);
		this.upperchestR.mirror = true;
		this.upperchestR.setRotationPoint(-4.0F, 0.0F, 0.0F);
		this.upperchestR.addBox(0.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(upperchestR, 0.9948376736367678F, 0.0F, 0.0F);
		this.wheelconnectorR = new MowzieModelRenderer(this, 71, 64);
		this.wheelconnectorR.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelconnectorR.addBox(-1.7F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
		this.setRotation(wheelconnectorR, 0.03490658503988659F, 0.0F, 0.0F);
		this.headwingR = new MowzieModelRenderer(this, 12, 120);
		this.headwingR.setRotationPoint(0.0F, 0.0F, 0.5F);
		this.headwingR.addBox(-2.5F, -3.1F, 1.2F, 1, 2, 2, 0.0F);
		this.lowerLegR = new MowzieModelRenderer(this, 63, 58);
		this.lowerLegR.setRotationPoint(0.0F, -0.9F, 5.0F);
		this.lowerLegR.addBox(-1.7F, -0.2F, -1.1F, 3, 7, 3, 0.0F);
		this.setRotation(lowerLegR, -0.1780235837034216F, 0.0F, 0.0F);
		this.headintakeL2 = new MowzieModelRenderer(this, 16, 117);
		this.headintakeL2.mirror = true;
		this.headintakeL2.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.headintakeL2.addBox(0.2F, -1.0F, -3.6F, 1, 2, 1, 0.0F);
		this.setRotation(headintakeL2, 0.061086523819801536F, -0.3141592653589793F, 0.0F);
		this.lowerarmR4 = new MowzieModelRenderer(this, 43, 74);
		this.lowerarmR4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lowerarmR4.addBox(-1.5F, 0.2F, -1.5F, 3, 1, 3, 0.0F);
		this.headintakeL1 = new MowzieModelRenderer(this, 16, 124);
		this.headintakeL1.mirror = true;
		this.headintakeL1.setRotationPoint(0.0F, 0.0F, 0.9F);
		this.headintakeL1.addBox(1.2F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotation(headintakeL1, 0.06981317007977318F, -0.022689280275926284F, 0.0017453292519943296F);
		this.lowerarmL4 = new MowzieModelRenderer(this, 43, 74);
		this.lowerarmL4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lowerarmL4.addBox(-1.5F, 0.2F, -1.5F, 3, 1, 3, 0.0F);
		this.toeL1 = new MowzieModelRenderer(this, 73, 76);
		this.toeL1.mirror = true;
		this.toeL1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeL1.addBox(1.2F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeL1, 0.027925268031909273F, -0.11344640137963141F, 0.008726646259971648F);
		this.fistL = new MowzieModelRenderer(this, 46, 78);
		this.fistL.setRotationPoint(0.0F, 4.0F, 1.0F);
		this.fistL.addBox(-1.2F, -0.4F, -2.0F, 2, 2, 3, 0.0F);
		this.setRotation(fistL, 0.05235987755982988F, -0.017453292519943295F, 0.3141592653589793F);
		this.legarmorL = new MowzieModelRenderer(this, 73, 73);
		this.legarmorL.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.legarmorL.addBox(-1.0F, -1.3F, -0.2F, 2, 8, 1, 0.0F);
		this.feetpartL2 = new MowzieModelRenderer(this, 74, 69);
		this.feetpartL2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartL2.addBox(-1.8F, 1.0F, -5.0F, 4, 1, 3, 0.0F);
		this.setRotation(feetpartL2, 0.4625122517784973F, -0.11344640137963141F, 0.017453292519943295F);
		this.lowerarmL2 = new MowzieModelRenderer(this, 46, 83);
		this.lowerarmL2.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.lowerarmL2.addBox(-1.3F, 2.0F, -0.5F, 3, 2, 3, 0.0F);
		this.upperLegR = new MowzieModelRenderer(this, 63, 49);
		this.upperLegR.setRotationPoint(0.0F, 0.5F, 1.1F);
		this.upperLegR.addBox(-3.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(upperLegR, -0.6457718232379019F, 0.0F, 0.08726646259971647F);
		this.wheelR = new MowzieModelRenderer(this, 75, 55);
		this.wheelR.mirror = true;
		this.wheelR.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelR.addBox(-1.0F, 1.0F, 1.5F, 2, 3, 3, 0.0F);
		this.setRotation(wheelR, 0.5864306286700948F, 0.0F, 0.0F);
		this.headintakeR1 = new MowzieModelRenderer(this, 16, 124);
		this.headintakeR1.setRotationPoint(0.0F, 0.0F, 0.9F);
		this.headintakeR1.addBox(-2.2F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotation(headintakeR1, 0.06981317007977318F, 0.029670597283903602F, -0.0017453292519943296F);
		this.wingL1biped = new MowzieModelRenderer(this, 36, 60);
		this.wingL1biped.mirror = true;
		this.wingL1biped.setRotationPoint(4.0F, -3.0F, -1.8F);
		this.wingL1biped.addBox(-2.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(wingL1biped, 0.017453292519943295F, -0.07853981633974483F, -0.5061454830783556F);
		this.chest1 = new MowzieModelRenderer(this, 0, 64);
		this.chest1.setRotationPoint(0.0F, -1.9F, -3.8F);
		this.chest1.addBox(-4.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
		this.stomach = new MowzieModelRenderer(this, 12, 53);
		this.stomach.setRotationPoint(0.0F, -0.8F, -0.6F);
		this.stomach.addBox(-3.0F, -7.0F, -2.0F, 6, 7, 4, 0.0F);
		this.setRotation(stomach, 0.10646508437165408F, 0.0F, 0.0F);
		this.lowerarmR2 = new MowzieModelRenderer(this, 46, 83);
		this.lowerarmR2.mirror = true;
		this.lowerarmR2.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.lowerarmR2.addBox(-1.7F, 2.0F, -0.5F, 3, 2, 3, 0.0F);
		this.upperArmL = new MowzieModelRenderer(this, 46, 68);
		this.upperArmL.mirror = true;
		this.upperArmL.setRotationPoint(2.5F, 0.6F, -0.5F);
		this.upperArmL.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(upperArmL, 0.017453292519943295F, 0.017453292519943295F, -0.05235987755982988F);
		this.upperArmR = new MowzieModelRenderer(this, 46, 68);
		this.upperArmR.setRotationPoint(-2.5F, 0.6F, -0.5F);
		this.upperArmR.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(upperArmR, 0.017453292519943295F, -0.017453292519943295F, 0.05235987755982988F);
		this.legarmorR.addChild(this.feetconnectorR);
		this.bodywingL1.addChild(this.bodywingL2);
		this.upperLegL.addChild(this.middlelegL);
		this.feetbaseL.addChild(this.toeL2);
		this.upperArmL.addChild(this.lowerArmL1);
		this.feetconnectorR.addChild(this.feetbaseR);
		this.shoulderR.addChild(this.wingR1biped);
		this.waist.addChild(this.buttflapL);
		this.chest1.addChild(this.energyUP);
		this.waist.addChild(this.buttflapR);
		this.chest1.addChild(this.lowerchestM);
		this.headbase.addChild(this.headintakeR2);
		this.bodywingR1.addChild(this.bodywingR2);
		this.headbase.addChild(this.headnose);
		this.chest1.addChild(this.upperchestL);
		this.waist.addChild(this.crotchfront);
		this.wheelconnectorL.addChild(this.wheelL);
		this.feetbaseR.addChild(this.toeR1);
		this.stomach.addChild(this.rearstomach);
		this.chest1.addChild(this.collarL);
		this.chest1.addChild(this.shoulderL);
		this.chest1.addChild(this.bodywingL1);
		this.chest1.addChild(this.energyDOWN);
		this.chest1.addChild(this.collarM);
		this.stomach.addChild(this.chestcenter);
		this.chest1.addChild(this.cockpit1);
		this.chest1.addChild(this.collarR);
		this.feetbaseR.addChild(this.feetpartR1);
		this.wingL1biped.addChild(this.wingL2biped);
		this.feetconnectorL.addChild(this.wheelconnectorL);
		this.lowerArmL1.addChild(this.lowerarmL3);
		this.shoulderL.addChild(this.intakeL);
		this.feetbaseR.addChild(this.feetpartR2);
		this.chest1.addChild(this.headbase);
		this.upperLegR.addChild(this.middlelegR);
		this.legarmorL.addChild(this.feetconnectorL);
		this.chest1.addChild(this.lowerchestR);
		this.chest1.addChild(this.upperchestM);
		this.feetbaseR.addChild(this.toeR2);
		this.upperArmR.addChild(this.lowerArmR1);
		this.headbase.addChild(this.headhelmet2);
		this.middlelegL.addChild(this.lowerlegL);
		this.lowerLegR.addChild(this.legarmorR);
		this.chest1.addChild(this.shoulderR);
		this.waist.addChild(this.crotchBack);
		this.feetconnectorL.addChild(this.feetbaseL);
		this.chest1.addChild(this.LowerchestL);
		this.chest1.addChild(this.bodywingR1);
		this.headbase.addChild(this.headhelmet1);
		this.wingR1biped.addChild(this.wingR2biped);
		this.feetbaseL.addChild(this.feetpartL1);
		this.lowerarmR2.addChild(this.fistR);
		this.waist.addChild(this.crotch1);
		this.chest1.addChild(this.cockpit2);
		this.headbase.addChild(this.headwingL);
		this.lowerArmR1.addChild(this.lowerarmR3);
		this.shoulderR.addChild(this.intakeR1);
		this.crotch1.addChild(this.upperLegL);
		this.chest1.addChild(this.neck);
		this.chest1.addChild(this.upperchestR);
		this.feetconnectorR.addChild(this.wheelconnectorR);
		this.headbase.addChild(this.headwingR);
		this.middlelegR.addChild(this.lowerLegR);
		this.headbase.addChild(this.headintakeL2);
		this.lowerArmR1.addChild(this.lowerarmR4);
		this.headbase.addChild(this.headintakeL1);
		this.lowerArmL1.addChild(this.lowerarmL4);
		this.feetbaseL.addChild(this.toeL1);
		this.lowerarmL2.addChild(this.fistL);
		this.lowerlegL.addChild(this.legarmorL);
		this.feetbaseL.addChild(this.feetpartL2);
		this.lowerArmL1.addChild(this.lowerarmL2);
		this.crotch1.addChild(this.upperLegR);
		this.wheelconnectorR.addChild(this.wheelR);
		this.headbase.addChild(this.headintakeR1);
		this.shoulderL.addChild(this.wingL1biped);
		this.chestcenter.addChild(this.chest1);
		this.waist.addChild(this.stomach);
		this.lowerArmR1.addChild(this.lowerarmR2);
		this.shoulderL.addChild(this.upperArmL);
		this.shoulderR.addChild(this.upperArmR);


		this.mainbody14 = new ModelRenderer(this, 0, 15);
		this.mainbody14.setRotationPoint(-3.5F, 0.0F, 7.0F);
		this.mainbody14.addBox(0.0F, 0.0F, 0.0F, 7, 6, 6, 0.0F);
		this.wingL1 = new ModelRenderer(this, 36, 60);
		this.wingL1.mirror = true;
		this.wingL1.setRotationPoint(10.0F, 6.0F, -1.8F);
		this.wingL1.addBox(-7.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(wingL1, -1.5707963267948966F, -0.07853981633974483F, 0.5061454830783556F);
		this.cockpit = new ModelRenderer(this, 0, 84);
		this.cockpit.setRotationPoint(-1.5F, 4.0F, -9.0F);
		this.cockpit.addBox(0.0F, -0.2F, -0.6F, 3, 4, 2, 0.0F);
		this.setRotation(cockpit, 1.879719604397893F, 0.0F, 0.0F);
		this.front1 = new ModelRenderer(this, 0, 28);
		this.front1.setRotationPoint(-3.5F, 1.5F, -2.0F);
		this.front1.addBox(0.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
		this.setRotation(front1, 0.06981317007977318F, -0.13962634015954636F, 0.0F);
		this.mainbody13 = new ModelRenderer(this, 0, 7);
		this.mainbody13.setRotationPoint(0.0F, 8.5F, 2.0F);
		this.mainbody13.addBox(-7.5F, -3.0F, 0.0F, 15, 1, 7, 0.0F);
		this.cannon1 = new ModelRenderer(this, 73, 76);
		this.cannon1.mirror = true;
		this.cannon1.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.cannon1.addBox(-6.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
		this.setRotation(cannon1, 1.5707963267948966F, 0.0F, 0.0F);
		this.engineR2 = new ModelRenderer(this, 36, 68);
		this.engineR2.setRotationPoint(0.0F, -3.0F, -2.5F);
		this.engineR2.addBox(-1.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
		this.setRotation(engineR2, 0.0F, 0.0F, 1.3055062804917585F);
		this.mainbody7 = new ModelRenderer(this, 0, 90);
		this.mainbody7.setRotationPoint(-4.0F, 7.0F, 3.0F);
		this.mainbody7.addBox(0.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(mainbody7, -0.9948376736367678F, 0.0F, 0.0F);
		this.mainbody2 = new ModelRenderer(this, 0, 55);
		this.mainbody2.mirror = true;
		this.mainbody2.setRotationPoint(-2.5F, 1.5F, 9.0F);
		this.mainbody2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(mainbody2, 0.0F, -1.5707963267948966F, 0.0F);
		this.engineL2 = new ModelRenderer(this, 36, 68);
		this.engineL2.mirror = true;
		this.engineL2.setRotationPoint(0.0F, -3.0F, -2.5F);
		this.engineL2.addBox(0.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
		this.setRotation(engineL2, 0.0F, 0.0F, -1.3055062804917585F);
		this.exhaust2 = new ModelRenderer(this, 55, 0);
		this.exhaust2.setRotationPoint(4.0F, 8.0F, 2.0F);
		this.exhaust2.addBox(-3.5F, 0.5F, 0.0F, 3, 4, 5, 0.0F);
		this.setRotation(exhaust2, -1.5707963267948966F, 0.0F, 0.0F);
		this.front3 = new ModelRenderer(this, 19, 15);
		this.front3.setRotationPoint(-2.0F, 2.8F, -13.0F);
		this.front3.addBox(0.0F, 0.0F, 0.0F, 4, 5, 21, 0.0F);
		this.rearR2 = new ModelRenderer(this, 63, 58);
		this.rearR2.setRotationPoint(0.0F, 5.5F, -1.5F);
		this.rearR2.addBox(-2.9F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
		this.setRotation(rearR2, 0.2617993877991494F, 0.0F, 0.0F);
		this.mainbody10 = new ModelRenderer(this, 8, 87);
		this.mainbody10.setRotationPoint(-1.5F, 0.0F, 0.0F);
		this.mainbody10.addBox(0.0F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
		this.setRotation(mainbody10, 0.9948376736367678F, 0.0F, 0.0F);
		this.cannon3 = new ModelRenderer(this, 73, 76);
		this.cannon3.mirror = true;
		this.cannon3.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.cannon3.addBox(4.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
		this.setRotation(cannon3, 1.5707963267948966F, 0.0F, 0.0F);
		this.engineR1 = new ModelRenderer(this, 36, 51);
		this.engineR1.mirror = true;
		this.engineR1.setRotationPoint(-3.5F, 3.5F, 1.0F);
		this.engineR1.addBox(-4.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.setRotation(engineR1, 1.5707963267948966F, 0.0F, 0.0F);
		this.wingR1 = new ModelRenderer(this, 36, 65);
		this.wingR1.setRotationPoint(-11.0F, 11.6F, -1.5F);
		this.wingR1.addBox(3.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
		this.setRotation(wingR1, -1.5707963267948966F, 0.07853981633974483F, -0.5061454830783556F);
		this.mainbody4 = new ModelRenderer(this, 12, 53);
		this.mainbody4.setRotationPoint(-3.0F, 7.0F, 0.0F);
		this.mainbody4.addBox(0.0F, -0.1F, -0.1F, 6, 7, 4, 0.0F);
		this.setRotation(mainbody4, 1.5707963267948966F, 0.0F, 0.0F);
		this.bodyplate2 = new ModelRenderer(this, 73, 73);
		this.bodyplate2.setRotationPoint(1.3F, 14.7F, 5.6F);
		this.bodyplate2.addBox(0.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
		this.mainbody8 = new ModelRenderer(this, 0, 90);
		this.mainbody8.mirror = true;
		this.mainbody8.setRotationPoint(4.0F, 7.0F, 3.0F);
		this.mainbody8.addBox(-2.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(mainbody8, -0.9948376736367678F, 0.0F, 0.0F);
		this.front2 = new ModelRenderer(this, 0, 28);
		this.front2.mirror = true;
		this.front2.setRotationPoint(3.5F, 1.5F, -2.0F);
		this.front2.addBox(-3.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
		this.setRotation(front2, 0.06981317007977318F, 0.13962634015954636F, 0.0F);
		this.wingL2 = new ModelRenderer(this, 36, 65);
		this.wingL2.setRotationPoint(11.0F, 11.6F, -1.5F);
		this.wingL2.addBox(-9.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
		this.setRotation(wingL2, -1.5707963267948966F, -0.07853981633974483F, 0.5061454830783556F);
		this.engineR3 = new ModelRenderer(this, 36, 76);
		this.engineR3.setRotationPoint(-2.0F, 1.0F, -2.0F);
		this.engineR3.addBox(-2.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
		this.setRotation(engineR3, 0.0F, -0.008726646259971648F, 0.0F);
		this.exhaust1 = new ModelRenderer(this, 55, 0);
		this.exhaust1.mirror = true;
		this.exhaust1.setRotationPoint(0.0F, 8.0F, 2.0F);
		this.exhaust1.addBox(-3.5F, 0.5F, 0.0F, 3, 4, 5, 0.0F);
		this.setRotation(exhaust1, -1.5707963267948966F, 0.0F, 0.0F);
		this.mainbody12 = new ModelRenderer(this, 0, 7);
		this.mainbody12.setRotationPoint(0.0F, 8.5F, 2.0F);
		this.mainbody12.addBox(-7.5F, -7.0F, 0.0F, 15, 1, 7, 0.0F);
		this.mainbody3 = new ModelRenderer(this, 0, 55);
		this.mainbody3.setRotationPoint(2.5F, 1.5F, 9.0F);
		this.mainbody3.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(mainbody3, 0.0F, 1.5707963267948966F, 0.0F);
		this.bodyplate1 = new ModelRenderer(this, 73, 73);
		this.bodyplate1.mirror = true;
		this.bodyplate1.setRotationPoint(-1.3F, 14.7F, 5.6F);
		this.bodyplate1.addBox(-2.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
		this.cannon2 = new ModelRenderer(this, 73, 76);
		this.cannon2.mirror = true;
		this.cannon2.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.cannon2.addBox(-2.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
		this.setRotation(cannon2, 1.5707963267948966F, 0.0F, 0.0F);
		this.engineL1 = new ModelRenderer(this, 36, 51);
		this.engineL1.setRotationPoint(3.5F, 3.5F, 1.0F);
		this.engineL1.addBox(0.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.setRotation(engineL1, 1.5707963267948966F, 0.0F, 0.0F);
		this.wingR2 = new ModelRenderer(this, 36, 60);
		this.wingR2.setRotationPoint(-10.0F, 6.0F, -1.8F);
		this.wingR2.addBox(-1.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(wingR2, -1.5707963267948966F, 0.07853981633974483F, -0.5061454830783556F);
		this.legL2 = new ModelRenderer(this, 63, 58);
		this.legL2.mirror = true;
		this.legL2.setRotationPoint(0.0F, 5.5F, -1.5F);
		this.legL2.addBox(-0.1F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
		this.setRotation(legL2, 0.2617993877991494F, 0.0F, 0.0F);
		this.wheel2 = new ModelRenderer(this, 75, 55);
		this.wheel2.mirror = true;
		this.wheel2.setRotationPoint(4.5F, 13.5F, 3.9F);
		this.wheel2.addBox(0.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
		this.engineL3 = new ModelRenderer(this, 36, 76);
		this.engineL3.mirror = true;
		this.engineL3.setRotationPoint(2.0F, 1.0F, -2.0F);
		this.engineL3.addBox(1.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
		this.setRotation(engineL3, 0.0F, 0.008726646259971648F, 0.0F);
		this.rearwing1 = new ModelRenderer(this, 0, 0);
		this.rearwing1.setRotationPoint(-3.0F, 5.0F, -1.4F);
		this.rearwing1.addBox(-5.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
		this.setRotation(rearwing1, 1.4311699866353502F, -0.6981317007977318F, 0.2792526803190927F);
		this.mainbody9 = new ModelRenderer(this, 12, 76);
		this.mainbody9.setRotationPoint(4.0F, 0.0F, 0.0F);
		this.mainbody9.addBox(-2.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(mainbody9, 0.9948376736367678F, 0.0F, 0.0F);
		this.mainbody11 = new ModelRenderer(this, 0, 97);
		this.mainbody11.setRotationPoint(-1.5F, 7.0F, 3.0F);
		this.mainbody11.addBox(0.0F, 0.3F, -5.8F, 3, 2, 4, 0.0F);
		this.setRotation(mainbody11, -0.9948376736367678F, 0.0F, 0.0F);
		this.mainbody1 = new ModelRenderer(this, 0, 64);
		this.mainbody1.setRotationPoint(0.0F, -5.0F, -2.0F);
		this.mainbody1.addBox(-4.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
		this.mainbody5 = new ModelRenderer(this, 0, 76);
		this.mainbody5.setRotationPoint(-2.0F, 3.5F, -5.6F);
		this.mainbody5.addBox(0.0F, 0.3F, 0.0F, 4, 6, 2, 0.0F);
		this.setRotation(mainbody5, 1.8203784098300857F, 0.0F, 0.0F);
		this.rearramp1 = new ModelRenderer(this, 37, 0);
		this.rearramp1.setRotationPoint(0.0F, 3.0F, 19.5F);
		this.rearramp1.addBox(-2.0F, -3.0F, -7.3F, 4, 3, 10, 0.0F);
		this.setRotation(rearramp1, 0.41887902047863906F, 0.0F, 0.0F);
		this.rearR1 = new ModelRenderer(this, 63, 49);
		this.rearR1.setRotationPoint(0.0F, 0.5F, 22.0F);
		this.rearR1.addBox(-3.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(rearR1, -1.5707963267948966F, 0.0F, 0.0F);
		this.rearwing2 = new ModelRenderer(this, 0, 0);
		this.rearwing2.mirror = true;
		this.rearwing2.setRotationPoint(3.0F, 5.0F, -1.4F);
		this.rearwing2.addBox(-1.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
		this.setRotation(rearwing2, 1.4311699866353502F, 0.7155849933176751F, -0.296705972839036F);
		this.legL1 = new ModelRenderer(this, 63, 49);
		this.legL1.mirror = true;
		this.legL1.setRotationPoint(0.0F, 0.5F, 22.0F);
		this.legL1.addBox(0.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(legL1, -1.5707963267948966F, 0.0F, 0.0F);
		this.cannon4 = new ModelRenderer(this, 73, 76);
		this.cannon4.mirror = true;
		this.cannon4.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.cannon4.addBox(0.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
		this.setRotation(cannon4, 1.5707963267948966F, 0.0F, 0.0F);
		this.wheel1 = new ModelRenderer(this, 75, 55);
		this.wheel1.mirror = true;
		this.wheel1.setRotationPoint(-4.5F, 13.5F, 3.9F);
		this.wheel1.addBox(-2.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
		this.mainbody6 = new ModelRenderer(this, 12, 76);
		this.mainbody6.mirror = true;
		this.mainbody6.setRotationPoint(-4.0F, 0.0F, 0.0F);
		this.mainbody6.addBox(0.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(mainbody6, 0.9948376736367678F, 0.0F, 0.0F);
		this.mainbody1.addChild(this.mainbody14);
		this.engineL1.addChild(this.wingL1);
		this.mainbody1.addChild(this.cockpit);
		this.mainbody1.addChild(this.front1);
		this.mainbody1.addChild(this.mainbody13);
		this.rearR1.addChild(this.cannon1);
		this.engineR1.addChild(this.engineR2);
		this.mainbody1.addChild(this.mainbody7);
		this.mainbody1.addChild(this.mainbody2);
		this.engineL1.addChild(this.engineL2);
		this.engineL1.addChild(this.exhaust2);
		this.mainbody1.addChild(this.front3);
		this.rearR1.addChild(this.rearR2);
		this.mainbody1.addChild(this.mainbody10);
		this.legL1.addChild(this.cannon3);
		this.mainbody1.addChild(this.engineR1);
		this.engineR1.addChild(this.wingR1);
		this.mainbody1.addChild(this.mainbody4);
		this.legL1.addChild(this.bodyplate2);
		this.mainbody1.addChild(this.mainbody8);
		this.mainbody1.addChild(this.front2);
		this.engineL1.addChild(this.wingL2);
		this.engineR1.addChild(this.engineR3);
		this.engineR1.addChild(this.exhaust1);
		this.mainbody1.addChild(this.mainbody12);
		this.mainbody1.addChild(this.mainbody3);
		this.rearR1.addChild(this.bodyplate1);
		this.rearR1.addChild(this.cannon2);
		this.mainbody1.addChild(this.engineL1);
		this.engineR1.addChild(this.wingR2);
		this.legL1.addChild(this.legL2);
		this.legL1.addChild(this.wheel2);
		this.engineL1.addChild(this.engineL3);
		this.rearR1.addChild(this.rearwing1);
		this.mainbody1.addChild(this.mainbody9);
		this.mainbody1.addChild(this.mainbody11);
		this.mainbody1.addChild(this.mainbody5);
		this.mainbody1.addChild(this.rearramp1);
		this.mainbody1.addChild(this.rearR1);
		this.legL1.addChild(this.rearwing2);
		this.mainbody1.addChild(this.legL1);
		this.legL1.addChild(this.cannon4);
		this.rearR1.addChild(this.wheel1);
		this.mainbody1.addChild(this.mainbody6);

		//parts = new MowzieModelRenderer[]{waist, stomach, chestcenter, rearstomach, crotch1, crotchfront, buttflapL, buttflapR, crotchBack, upperLegR, upperLegL, middlelegR, lowerLegR, legarmorR, feetconnectorR, wheelconnectorR, feetbaseR, wheelR, feetpartR1, feetpartR2, toeR1, toeR2, middlelegL, lowerlegL, legarmorL, feetconnectorL, wheelconnectorL, feetbaseL, wheelL, toeL1, toeL2, feetpartL2, feetpartL1, chest1, bodywingR1, bodywingL1, neck, upperchestR, cockpit1, cockpit2, lowerchestR, LowerchestL, upperchestL, upperchestM, lowerchestM, energyUP, energyDOWN, collarR, collarL, collarM, shoulderL, shoulderR, bodywingR2, bodywingL2, headbase, headnose, headhelmet1, headwingR, headhelmet2, headwingL, headintakeR1, headintakeL2, headintakeR2, headintakeL1, wingL1biped, intakeL, upperArmL, wingL2biped, lowerArmL1, lowerarmL2, lowerarmL3, lowerarmL4, fistL, wingR1biped, intakeR1, upperArmR, wingR2biped, lowerArmR1, lowerarmR4, lowerarmR3, lowerarmR2, fistR};
		setInitPose();
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerSkystrike;
			boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerSkystrike;
			boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerSkystrike;

			if(!(!wearingChest && !wearingHead && !wearingLegs))
			{
				if(wearingLegs && wearingHead && !wearingChest)
				{
					headbase.render(f5);
					upperLegR.render(f5);
					upperLegL.render(f5);
				}
				else if(wearingHead && !wearingChest)
				{
					headbase.render(f5);
				}
				else if(wearingLegs && !wearingChest)
				{
					upperLegR.render(f5);
					upperLegL.render(f5);
				}
				else
				{
					if(TFDataManager.getTransformationTimer(player) == 0)
					{
						this.mainbody1.render(f5);
					}
					else
					{
						this.waist.render(f5);
					}
				}
			}
		}
	}

	public void setRotation(MowzieModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;

			setToInitPose();

			boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerSkystrike;
			boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerSkystrike;
			boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerSkystrike;

			if(!(!wearingChest && !wearingHead && !wearingLegs))
			{
				float globalSpeed = 1;
				float globalDegree = 0.8F;

				headbase.showModel = wearingHead;
				upperLegL.showModel = wearingLegs;
				upperLegR.showModel = wearingLegs;

				if (entity.isSneaking())
				{
					globalDegree = 1F;
					globalSpeed = 1.5F;
				}

				ModelOffset offsets = TFModelHelper.getOffsets(player);

				this.headbase.rotationPointX += offsets.headOffsetX;
				this.headbase.rotationPointY += offsets.headOffsetY;
				this.headbase.rotationPointZ += offsets.headOffsetZ;

				if (wearingHead)
				{
					faceTarget(headbase, 1, par4, par5);

					headbase.rotationPointY += 3.5F;
					headbase.rotationPointZ -= 1.5F;
					
					if(!wearingChest)
					{
						headbase.rotationPointZ -= 4F;
					}
				}
				else
				{
					if(wearingChest)
					{
						if(wearingLegs)
						{
							offsets.headOffsetY = -3.1F;
							offsets.headOffsetZ = 2F;
						}
						else
						{
							offsets.headOffsetY = -0.6F;
							offsets.headOffsetZ = 0.2F;
						}
					}
				}

				if(!wearingChest && wearingLegs)
				{
					upperLegR.rotationPointX -= 1;
					upperLegL.rotationPointX -= 1;

					upperLegR.rotationPointZ -= 1;
					upperLegL.rotationPointZ -= 1;

					upperLegR.rotationPointY += 10;
					upperLegL.rotationPointY += 10;
				}

				if(!wearingLegs && wearingChest)
				{
					waist.rotationPointY += 2.5F;
					waist.rotationPointZ -= 2.5F;
				}

				int backwardInverter = 1;

				if (((EntityPlayer) entity).moveForward < 0)
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

				boolean sneaking = player.isSneaking();

				if (sneaking)
				{
					globalDegree = 1.5F;
					globalSpeed = 1.3F;
				}

				if (wearingHead && wearingLegs && wearingChest)
				{
					boolean playerOnGround = onGround(player);

					if (playerOnGround || player.capabilities.isFlying)
					{
						bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, par1, par2);
						waist.rotationPointY += 1 * par2;
						walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
						walk(chestcenter, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
						swing(stomach, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, par1, par2);
						swing(chestcenter, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, par1, par2);
						swing(waist, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0, 0, par1, par2);
						walk(headbase, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * par2 * backwardInverter, par1, par2);
						swing(headbase, 0.5F * globalSpeed, -0.4F * globalDegree, false, 0, 0, par1, par2);
						//						headbase.rotationPointX += 0.6 * globalDegree * par2 * Math.cos(par1 * 0.5F * globalSpeed);

						swing(upperLegL, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0F, par1, par2);
						swing(upperLegR, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0F, par1, par2);

						walk(buttflapL, 1F * globalSpeed, -0.6F * globalDegree, true, -1, 0.2F, par1, par2);
						walk(buttflapR, 1F * globalSpeed, -0.6F * globalDegree, true, -1, 0.2F, par1, par2);

						walk(upperLegL, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, par1, par2);
						walk(middlelegL, 0.5F * globalSpeed, 1F * globalDegree, true, 1F* backwardInverter, 0F, par1, par2);
						walk(lowerlegL, 0.5F * globalSpeed, 0.6F * globalDegree, false, 0F, 0F, par1, par2);
						walk(feetbaseL, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0.5F* backwardInverter, 0.3F, par1, par2);

						walk(upperLegR, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, par1, par2);
						walk(middlelegR, 0.5F * globalSpeed, 1F * globalDegree, false, 1F* backwardInverter, 0F, par1, par2);
						walk(lowerLegR, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0F, 0F, par1, par2);
						walk(feetbaseR, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0.5F* backwardInverter, 0.3F, par1, par2);

						walk(shoulderL, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * par2 * backwardInverter, par1, par2);
						walk(shoulderR, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * par2 * backwardInverter, par1, par2);
						walk(lowerArmL1, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * par2, par1, par2);
						walk(lowerArmR1, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * par2, par1, par2);

						//Idle animation
						int ticksExisted = entity.ticksExisted;

						walk(stomach, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
						walk(chest1, 0.08F, 0.05F, false, 1, 0, ticksExisted, 1F);
						walk(headbase, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);
						walk(upperArmR, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);
						walk(upperArmL, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);

						flap(upperArmR, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);
						flap(upperArmL, 0.08F, 0.04F, false, 1, 0, ticksExisted, 1F);
						walk(lowerArmR1, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
						walk(lowerArmL1, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);

						/*walk(waist, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
						walk(upperlegL, 0.08F, 0.1F, false, 1, 0, ticksExisted, 1F);
						walk(upperlegR, 0.08F, 0.1F, false, 1, 0, ticksExisted, 1F);
						walk(chestcenter, 0.08F, 0.15F, false, 1, 0, ticksExisted, 1F);
						walk(headbase, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
						walk(upperarmL, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
						walk(upperarmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);

						flap(shoulderL, 0.06F, 0.05F, true, 1, 0, ticksExisted, 1F);
						flap(shoulderR, 0.06F, 0.05F, false, 1, 0, ticksExisted, 1F);
						walk(lowerarmL1, 0.06F, 0.1F, true, 1, 0, ticksExisted, 1F);
						walk(lowerarmR1, 0.06F, 0.1F, true, 1, 0, ticksExisted, 1F);*/

						if (sneaking)
						{
							waist.rotationPointY -= par2;
							waist.rotateAngleX += 0.3F;
							waist.rotationPointZ -= 3F;
							waist.rotationPointY += 0.5F;

							headbase.rotateAngleX -= 0.3;
							upperLegR.rotateAngleX -= 0.3;
							upperLegL.rotateAngleX -= 0.3;
							middlelegR.rotateAngleX += 0.6;
							middlelegL.rotateAngleX += 0.6;
							lowerLegR.rotateAngleX -= 0.5;
							lowerlegL.rotateAngleX -= 0.5;
							upperArmR.rotateAngleX -= 0.3;
							upperArmL.rotateAngleX -= 0.3;
							upperArmR.rotateAngleZ += 0.5;
							upperArmL.rotateAngleZ -= 0.5;
							lowerArmR1.rotateAngleZ -= 0.5;
							lowerArmL1.rotateAngleZ += 0.5;
						}
					}
					else// if(!player.isWet())
					{
						double motionX = entity.posX - entity.prevPosX;
						double motionZ = entity.posZ - entity.prevPosZ;

						double motionY = entity.posY - entity.prevPosY;
						float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
						float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));

						double speed = Math.sqrt((motionX * motionX) + (motionZ * motionZ)) * 1.2D;

						waist.rotateAngleX += 0.2 * par2 * backwardInverter;

						chestcenter.rotateAngleX -= 0.2 * upwardPose;
						//chest1.rotateAngleX -= 0.4 * upwardPose;
						headbase.rotateAngleX -= 0.2 * upwardPose;

						upperArmR.rotateAngleX += 0.1 * upwardPose;
						upperArmL.rotateAngleX += 0.1 * upwardPose;
						upperArmR.rotateAngleZ -= 0.1 * upwardPose;
						upperArmL.rotateAngleZ += 0.1 * upwardPose;
						lowerArmR1.rotateAngleX += 0.2 * upwardPose;
						lowerArmL1.rotateAngleX += 0.2 * upwardPose;

						upperLegR.rotateAngleX += 0.2 * upwardPose;
						upperLegL.rotateAngleX += 0.2 * upwardPose;
						lowerLegR.rotateAngleX += 0.5 * upwardPose;
						lowerlegL.rotateAngleX += 0.5 * upwardPose;

						waist.rotateAngleX += speed * downwardPose;
						buttflapL.rotateAngleX += (speed * downwardPose) + downwardPose / 2;
						buttflapR.rotateAngleX += (speed * downwardPose) + downwardPose / 2;

						upperArmR.rotateAngleZ += 1 * downwardPose;
						upperArmL.rotateAngleZ -= 1 * downwardPose;
						lowerArmR1.rotateAngleX -= 1 * downwardPose;
						lowerArmL1.rotateAngleX -= 1 * downwardPose;
						headbase.rotateAngleX += 0.2 * downwardPose;
						headbase.rotateAngleX -= (speed * downwardPose) * 0.3F;
					}

					int timer = TFDataManager.getTransformationTimer(player);

					if (timer == 0)
					{
						ModelBiped modelBiped = TFModelHelper.modelBipedMain;

						this.mainbody1.rotateAngleX = par5 / (180F / (float) Math.PI);
						this.mainbody1.rotateAngleZ = -(modelBiped.bipedHead.rotateAngleY);

						waist.offsetY = 256F;
						mainbody1.offsetY = 0F;
					}
					else
					{
						int t = TFDataManager.getTransformationTimer(player);
						float f = (float) (20 - t) / 2;

						this.waist.rotationPointY -= (f * 0.8F);
						this.waist.rotateAngleX += (f * 0.15F);

						this.headbase.rotateAngleX += (f * -0.15F);

						this.shoulderR.rotateAngleX += f * -0.15F;
						this.shoulderL.rotateAngleX += f * -0.15F;

						this.shoulderR.rotateAngleZ += f * 0.15F;
						this.shoulderL.rotateAngleZ += f * -0.15F;

						this.lowerArmL1.rotateAngleZ += f * -0.5F;
						this.lowerArmR1.rotateAngleZ += f * 0.5F;

						this.upperLegR.rotateAngleX += (f * -0.3F);
						this.upperLegL.rotateAngleX += (f * -0.3F);

						this.wingR1biped.rotationPointZ += f * 0.5F;
						this.wingL1biped.rotationPointZ += f * 0.5F;

						this.wingR1biped.rotationPointY += f * 0.5F;
						this.wingL1biped.rotationPointY += f * 0.5F;

						this.wingR1biped.rotateAngleX += f * 0.1F;
						this.wingL1biped.rotateAngleX += f * 0.1F;

						this.wingR1biped.rotateAngleY += f * 0.2F;
						this.wingL1biped.rotateAngleY += -f * 0.2F;

						this.cockpit1.rotateAngleZ += f * 0.33F;
						this.cockpit1.rotationPointY += f * 0.1F;
						this.cockpit1.rotationPointZ += f * -0.2F;

						//						this.lowerarmL1.rotateAngleZ = f * 0.05F;
						//						this.lowerarmR1.rotateAngleZ = f * -0.05F;

						waist.offsetY = 0F;
						mainbody1.offsetY = 256F;
					}
				}
				else
				{
					this.upperArmL.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
					this.upperArmR.rotateAngleX += (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 2;

					this.lowerArmL1.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 4;
					this.lowerArmR1.rotateAngleX += (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 4;

					this.upperLegR.rotateAngleX = ((MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2) - 0.65F;
					this.upperLegL.rotateAngleX = ((MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 2) - 0.65F;

					if (this.isRiding)
					{
						this.upperArmR.rotateAngleX += -((float)Math.PI / 5F);
						this.upperArmL.rotateAngleX += -((float)Math.PI / 5F);
						this.upperLegR.rotateAngleX = -((float)Math.PI * 2F / 5F);
						this.upperLegL.rotateAngleX = -((float)Math.PI * 2F / 5F);
						this.upperLegR.rotateAngleY = ((float)Math.PI / 10F);
						this.upperLegL.rotateAngleY = -((float)Math.PI / 10F);
					}

					if (this.isSneak)
					{
						this.waist.rotateAngleX += 0.4F;
						this.waist.rotationPointZ += 4F;
						this.waist.rotationPointY -= 2F;
						this.upperArmR.rotateAngleX -= 0.4F;
						this.upperArmL.rotateAngleX -= 0.4F;

						if(wearingChest)
						{
							this.headbase.rotateAngleX -= 0.4F;
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
						this.waist.rotateAngleX = 0.0F;
					}

					if (this.onGround > -9990.0F)
					{
						float f6 = this.onGround;
						this.waist.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
						this.upperArmR.rotationPointZ = MathHelper.sin(this.waist.rotateAngleY) * 2.5F;
						this.upperArmR.rotationPointX = -MathHelper.cos(this.waist.rotateAngleY) * 2.5F;
						this.upperArmL.rotationPointZ = -MathHelper.sin(this.waist.rotateAngleY) * 2.5F;
						this.upperArmL.rotationPointX = MathHelper.cos(this.waist.rotateAngleY) * 2.5F;
						this.upperArmR.rotateAngleY += this.waist.rotateAngleY;
						this.upperArmL.rotateAngleY += this.waist.rotateAngleY;
						this.upperArmL.rotateAngleX += this.waist.rotateAngleY;
						f6 = 1.0F - this.onGround;
						f6 *= f6;
						f6 *= f6;
						f6 = 1.0F - f6;
						float f7 = MathHelper.sin(f6 * (float)Math.PI);
						float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.headbase.rotateAngleX - 0.7F) * 0.75F;
						this.upperArmR.rotateAngleX = (float)((double)this.upperArmR.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
						this.upperArmR.rotateAngleY += this.waist.rotateAngleY * 2.0F;
						this.upperArmR.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
					}
				}
			}
		}
	}
}