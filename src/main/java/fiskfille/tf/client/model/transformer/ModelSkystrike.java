package fiskfille.tf.client.model.transformer;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ModelSkystrike extends MowzieModelBase 
{
	public MowzieModelRenderer waist;
	public MowzieModelRenderer lowertorso;
	public MowzieModelRenderer chestcenter;
	public MowzieModelRenderer rearwaist;
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
	public MowzieModelRenderer lowerLegL;
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
	public MowzieModelRenderer wingL1;
	public MowzieModelRenderer intakeL;
	public MowzieModelRenderer upperArmL;
	public MowzieModelRenderer wingL2;
	public MowzieModelRenderer lowerArmL1;
	public MowzieModelRenderer lowerarmL2;
	public MowzieModelRenderer lowerarmL3;
	public MowzieModelRenderer lowerarmL4;
	public MowzieModelRenderer fistL;
	public MowzieModelRenderer wingR1;
	public MowzieModelRenderer intakeR1;
	public MowzieModelRenderer upperArmR;
	public MowzieModelRenderer wingR2;
	public MowzieModelRenderer lowerArmR1;
	public MowzieModelRenderer lowerarmR4;
	public MowzieModelRenderer lowerarmR3;
	public MowzieModelRenderer lowerarmR2;
	public MowzieModelRenderer fistR;

	public ModelRenderer shape131;
	public ModelRenderer shape132;
	public ModelRenderer shape133;
	public ModelRenderer shape134;
	public ModelRenderer shape136;
	public ModelRenderer shape137;
	public ModelRenderer shape138;
	public ModelRenderer shape139;
	public ModelRenderer shape140;
	public ModelRenderer shape141;
	public ModelRenderer vehicleBody;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer shape8;
	public ModelRenderer shape10;
	public ModelRenderer shape11;
	public ModelRenderer shape12;
	public ModelRenderer shape13;
	public ModelRenderer shape14;
	public ModelRenderer shape15;
	public ModelRenderer shape16;
	public ModelRenderer shape130;
	public ModelRenderer shape30;
	public ModelRenderer shape31;
	public ModelRenderer shape32;
	public ModelRenderer shape33;
	public ModelRenderer shape35;
	public ModelRenderer shape56;
	public ModelRenderer shape58;
	public ModelRenderer shape59;
	public ModelRenderer shape64;
	public ModelRenderer shape66;
	public ModelRenderer shape67;
	public ModelRenderer shape40;
	public ModelRenderer shape44;
	public ModelRenderer shape41;
	public ModelRenderer shape43;
	public ModelRenderer shape49;
	public ModelRenderer shape68;
	public ModelRenderer shape69;
	public ModelRenderer shape70;
	public ModelRenderer shape75;
	public ModelRenderer shape77;
	public ModelRenderer shape78;

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
		this.feetpartL2 = new MowzieModelRenderer(this, 74, 69);
		this.feetpartL2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartL2.addBox(-1.8F, 1.0F, -5.0F, 4, 1, 3, 0.0F);
		this.setRotation(feetpartL2, 0.4625122517784973F, -0.11344640137963141F, 0.017453292519943295F);
		this.cockpit1 = new MowzieModelRenderer(this, 0, 84);
		this.cockpit1.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.cockpit1.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 2, 0.0F);
		this.setRotation(cockpit1, -0.5585053606381855F, 0.0F, 0.0017453292519943296F);
		this.headnose = new MowzieModelRenderer(this, 0, 121);
		this.headnose.setRotationPoint(0.0F, -1.0F, 0.3F);
		this.headnose.addBox(-0.5F, -1.1F, -2.7F, 1, 2, 1, 0.0F);
		this.setRotation(headnose, -0.0890117918517108F, 0.0F, 0.0F);
		this.lowerchestR = new MowzieModelRenderer(this, 0, 90);
		this.lowerchestR.setRotationPoint(-4.0F, 7.0F, 3.0F);
		this.lowerchestR.addBox(0.0F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(lowerchestR, -0.9948376736367678F, 0.0F, 0.0F);
		this.toeL2 = new MowzieModelRenderer(this, 73, 76);
		this.toeL2.mirror = true;
		this.toeL2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeL2.addBox(-1.8F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeL2, 0.027925268031909273F, -0.11344640137963141F, 0.02617993877991494F);
		this.lowerchestM = new MowzieModelRenderer(this, 0, 97);
		this.lowerchestM.setRotationPoint(0.0F, 7.0F, 3.0F);
		this.lowerchestM.addBox(-1.5F, 0.3F, -5.8F, 3, 2, 4, 0.0F);
		this.setRotation(lowerchestM, -0.9948376736367678F, 0.0F, 0.0F);
		this.headbase = new MowzieModelRenderer(this, 0, 120);
		this.headbase.setRotationPoint(0.0F, -2.0F, 2.5F);
		this.headbase.addBox(-2.0F, -3.0F, -2.0F, 4, 4, 4, 0.0F);
		this.bodywingR2 = new MowzieModelRenderer(this, 7, 35);
		this.bodywingR2.mirror = true;
		this.bodywingR2.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.bodywingR2.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
		this.setRotation(bodywingR2, -1.1519173063162573F, -0.03490658503988659F, -0.003490658503988659F);
		this.middlelegR = new MowzieModelRenderer(this, 75, 49);
		this.middlelegR.setRotationPoint(-1.6F, 2.5F, 0.9F);
		this.middlelegR.addBox(-1.0F, -0.1F, 0.5F, 2, 2, 4, 0.0F);
		this.setRotation(middlelegR, -0.08831366015091306F, 0.06981317007977318F, 0.0F);
		this.feetbaseR = new MowzieModelRenderer(this, 62, 68);
		this.feetbaseR.setRotationPoint(0.0F, 0.0F, -0.3F);
		this.feetbaseR.addBox(-1.8F, 1.0F, -1.8F, 3, 3, 3, 0.0F);
		this.setRotation(feetbaseR, 0.6108652381980153F, 0.0F, 0.0F);
		this.legarmorL = new MowzieModelRenderer(this, 73, 73);
		this.legarmorL.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.legarmorL.addBox(-1.0F, -1.3F, -0.2F, 2, 8, 1, 0.0F);
		this.energyDOWN = new MowzieModelRenderer(this, 0, 49);
		this.energyDOWN.setRotationPoint(0.0F, 7.1F, 3.0F);
		this.energyDOWN.addBox(-2.0F, -0.4F, -6.0F, 4, 2, 4, 0.0F);
		this.setRotation(energyDOWN, -1.0018189906447452F, 0.0F, 0.0F);
		this.lowerArmL1 = new MowzieModelRenderer(this, 40, 87);
		this.lowerArmL1.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.lowerArmL1.addBox(-1.0F, 1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(lowerArmL1, -0.19198621771937624F, 0.06981317007977318F, 0.05235987755982988F);
		this.upperchestR = new MowzieModelRenderer(this, 12, 76);
		this.upperchestR.mirror = true;
		this.upperchestR.setRotationPoint(-4.0F, 0.0F, 0.0F);
		this.upperchestR.addBox(0.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(upperchestR, 0.9948376736367678F, 0.0F, 0.0F);
		this.neck = new MowzieModelRenderer(this, 23, 67);
		this.neck.setRotationPoint(-1.0F, -2.0F, 1.5F);
		this.neck.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
		this.chest1 = new MowzieModelRenderer(this, 0, 64);
		this.chest1.setRotationPoint(0.0F, -1.9F, -1.8F);
		this.chest1.addBox(-4.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
		this.cockpit2 = new MowzieModelRenderer(this, 10, 82);
		this.cockpit2.mirror = true;
		this.cockpit2.setRotationPoint(0.0F, -0.5F, 5.8F);
		this.cockpit2.addBox(-1.0F, 0.2F, -0.6F, 2, 2, 3, 0.0F);
		this.setRotation(cockpit2, 0.24434609527920614F, 0.0F, 0.0F);
		this.wheelconnectorL = new MowzieModelRenderer(this, 71, 64);
		this.wheelconnectorL.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelconnectorL.addBox(-1.3F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
		this.setRotation(wheelconnectorL, 0.03490658503988659F, 0.0F, 0.0F);
		this.intakeR1 = new MowzieModelRenderer(this, 36, 68);
		this.intakeR1.setRotationPoint(-4.0F, -3.0F, -0.5F);
		this.intakeR1.addBox(0.0F, 0.0F, -2.0F, 1, 4, 4, 0.0F);
		this.setRotation(intakeR1, 0.0F, 0.0F, 0.22689280275926282F);
		this.bodywingR1 = new MowzieModelRenderer(this, 0, 55);
		this.bodywingR1.mirror = true;
		this.bodywingR1.setRotationPoint(-3.0F, 0.2F, 6.0F);
		this.bodywingR1.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(bodywingR1, 0.0F, -0.9250245035569946F, -0.2792526803190927F);
		this.fistL = new MowzieModelRenderer(this, 46, 78);
		this.fistL.setRotationPoint(0.0F, 4.0F, 1.0F);
		this.fistL.addBox(-1.2F, -0.4F, -2.0F, 2, 2, 3, 0.0F);
		this.setRotation(fistL, 0.05235987755982988F, -0.017453292519943295F, 0.3141592653589793F);
		this.waist = new MowzieModelRenderer(this, 12, 53);
		this.waist.setRotationPoint(0.0F, 2.2F, 0.0F);
		this.waist.addBox(-3.0F, 0.0F, 0.0F, 6, 7, 4, 0.0F);
		this.setRotation(waist, 0.10646508437165408F, 0.0F, 0.0F);
		this.fistR = new MowzieModelRenderer(this, 46, 78);
		this.fistR.mirror = true;
		this.fistR.setRotationPoint(0.0F, 4.0F, 1.0F);
		this.fistR.addBox(-0.8F, -0.4F, -2.0F, 2, 2, 3, 0.0F);
		this.setRotation(fistR, 0.05235987755982988F, 0.017453292519943295F, -0.2792526803190927F);
		this.wingR1 = new MowzieModelRenderer(this, 36, 60);
		this.wingR1.setRotationPoint(-4.0F, -3.0F, -1.8F);
		this.wingR1.addBox(-6.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(wingR1, 0.017453292519943295F, 0.07853981633974483F, 0.5061454830783556F);
		this.headhelmet1 = new MowzieModelRenderer(this, 0, 114);
		this.headhelmet1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headhelmet1.addBox(-2.5F, -3.1F, -1.3F, 5, 3, 3, 0.0F);
		this.lowerarmL3 = new MowzieModelRenderer(this, 36, 76);
		this.lowerarmL3.mirror = true;
		this.lowerarmL3.setRotationPoint(1.0F, 5.8F, 0.3F);
		this.lowerarmL3.addBox(0.0F, -5.2F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotation(lowerarmL3, -0.03490658503988659F, -0.12566370614359174F, 0.04363323129985824F);
		this.feetbaseL = new MowzieModelRenderer(this, 62, 68);
		this.feetbaseL.setRotationPoint(0.0F, 0.0F, -0.3F);
		this.feetbaseL.addBox(-1.2F, 1.0F, -1.8F, 3, 3, 3, 0.0F);
		this.setRotation(feetbaseL, 0.6108652381980153F, 0.0F, 0.0F);
		this.lowerarmL4 = new MowzieModelRenderer(this, 43, 74);
		this.lowerarmL4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lowerarmL4.addBox(-1.5F, 0.2F, -1.5F, 3, 1, 3, 0.0F);
		this.crotchfront = new MowzieModelRenderer(this, 46, 102);
		this.crotchfront.setRotationPoint(-1.0F, -1.0F, -4.0F);
		this.crotchfront.addBox(0.0F, 0.3F, 0.4F, 2, 5, 2, 0.0F);
		this.setRotation(crotchfront, 0.12217304763960307F, 0.0F, 0.0F);
		this.bodywingL2 = new MowzieModelRenderer(this, 7, 35);
		this.bodywingL2.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.bodywingL2.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
		this.setRotation(bodywingL2, -1.1519173063162573F, 0.03490658503988659F, 0.003490658503988659F);
		this.headwingL = new MowzieModelRenderer(this, 12, 120);
		this.headwingL.setRotationPoint(0.0F, 0.0F, 0.5F);
		this.headwingL.addBox(1.5F, -3.1F, 1.2F, 1, 2, 2, 0.0F);
		this.lowertorso = new MowzieModelRenderer(this, 36, 95);
		this.lowertorso.setRotationPoint(0.0F, 6.7F, 2.4F);
		this.lowertorso.addBox(-3.5F, -1.0F, -3.0F, 7, 2, 5, 0.0F);
		this.setRotation(lowertorso, -0.10646508437165408F, 0.0F, 0.0F);
		this.headintakeL1 = new MowzieModelRenderer(this, 16, 124);
		this.headintakeL1.mirror = true;
		this.headintakeL1.setRotationPoint(0.0F, 0.0F, 0.9F);
		this.headintakeL1.addBox(1.2F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotation(headintakeL1, 0.06981317007977318F, -0.022689280275926284F, 0.0017453292519943296F);
		this.middlelegL = new MowzieModelRenderer(this, 75, 49);
		this.middlelegL.mirror = true;
		this.middlelegL.setRotationPoint(1.4F, 2.5F, 0.9F);
		this.middlelegL.addBox(-1.0F, -0.1F, 0.5F, 2, 2, 4, 0.0F);
		this.setRotation(middlelegL, -0.08831366015091306F, -0.06981317007977318F, 0.0F);
		this.upperArmL = new MowzieModelRenderer(this, 46, 68);
		this.upperArmL.mirror = true;
		this.upperArmL.setRotationPoint(2.5F, 0.6F, -0.5F);
		this.upperArmL.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(upperArmL, 0.017453292519943295F, 0.017453292519943295F, -0.05235987755982988F);
		this.upperchestM = new MowzieModelRenderer(this, 8, 87);
		this.upperchestM.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.upperchestM.addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
		this.setRotation(upperchestM, 0.9948376736367678F, 0.0F, 0.0F);
		this.lowerarmL2 = new MowzieModelRenderer(this, 46, 83);
		this.lowerarmL2.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.lowerarmL2.addBox(-1.3F, 2.0F, -0.5F, 3, 2, 3, 0.0F);
		this.headintakeL2 = new MowzieModelRenderer(this, 16, 117);
		this.headintakeL2.mirror = true;
		this.headintakeL2.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.headintakeL2.addBox(0.2F, -1.0F, -3.6F, 1, 2, 1, 0.0F);
		this.setRotation(headintakeL2, 0.061086523819801536F, -0.3141592653589793F, 0.0F);
		this.wingL2 = new MowzieModelRenderer(this, 36, 65);
		this.wingL2.mirror = true;
		this.wingL2.setRotationPoint(-2.0F, -1.0F, 4.2F);
		this.wingL2.addBox(2.0F, 0.1F, -0.2F, 6, 1, 2, 0.0F);
		this.setRotation(wingL2, -0.3665191429188092F, 0.0F, 0.0F);
		this.crotch1 = new MowzieModelRenderer(this, 36, 102);
		this.crotch1.setRotationPoint(-1.0F, 1.0F, -2.0F);
		this.crotch1.addBox(0.0F, 0.0F, 0.0F, 2, 3, 3, 0.0F);
		this.wingL1 = new MowzieModelRenderer(this, 36, 60);
		this.wingL1.mirror = true;
		this.wingL1.setRotationPoint(4.0F, -3.0F, -1.8F);
		this.wingL1.addBox(-2.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(wingL1, 0.017453292519943295F, -0.07853981633974483F, -0.5061454830783556F);
		this.crotchBack = new MowzieModelRenderer(this, 44, 109);
		this.crotchBack.setRotationPoint(-1.0F, 1.0F, 1.0F);
		this.crotchBack.addBox(0.0F, -0.3F, -1.2F, 2, 3, 2, 0.0F);
		this.setRotation(crotchBack, -0.28797932657906433F, 0.0F, 0.0F);
		this.shoulderR = new MowzieModelRenderer(this, 36, 51);
		this.shoulderR.mirror = true;
		this.shoulderR.setRotationPoint(-4.0F, 2.5F, 4.0F);
		this.shoulderR.addBox(-4.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.feetpartL1 = new MowzieModelRenderer(this, 63, 80);
		this.feetpartL1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartL1.addBox(-1.3F, 1.0F, -3.0F, 3, 2, 1, 0.0F);
		this.setRotation(feetpartL1, -0.0767944870877505F, -0.11344640137963141F, 0.017453292519943295F);
		this.collarL = new MowzieModelRenderer(this, 0, 103);
		this.collarL.setRotationPoint(4.0F, -1.5F, 3.0F);
		this.collarL.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
		this.setRotation(collarL, -0.0471238898038469F, -0.296705972839036F, 0.03490658503988659F);
		this.upperchestL = new MowzieModelRenderer(this, 12, 76);
		this.upperchestL.setRotationPoint(4.0F, 0.0F, 0.0F);
		this.upperchestL.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(upperchestL, 0.9948376736367678F, 0.0F, 0.0F);
		this.headhelmet2 = new MowzieModelRenderer(this, 13, 114);
		this.headhelmet2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headhelmet2.addBox(-2.5F, -3.17F, -3.1F, 5, 1, 2, 0.0F);
		this.setRotation(headhelmet2, 0.061086523819801536F, 0.0F, 0.0F);
		this.feetconnectorR = new MowzieModelRenderer(this, 62, 74);
		this.feetconnectorR.mirror = true;
		this.feetconnectorR.setRotationPoint(0.0F, 7.0F, -1.0F);
		this.feetconnectorR.addBox(-1.3F, -1.2F, -1.5F, 2, 4, 2, 0.0F);
		this.setRotation(feetconnectorR, 0.2897246558310587F, 0.04363323129985824F, 0.0F);
		this.feetpartR2 = new MowzieModelRenderer(this, 74, 69);
		this.feetpartR2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartR2.addBox(-2.2F, 1.0F, -5.0F, 4, 1, 3, 0.0F);
		this.setRotation(feetpartR2, 0.4625122517784973F, 0.11344640137963141F, -0.017453292519943295F);
		this.lowerarmR2 = new MowzieModelRenderer(this, 46, 83);
		this.lowerarmR2.mirror = true;
		this.lowerarmR2.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.lowerarmR2.addBox(-1.7F, 2.0F, -0.5F, 3, 2, 3, 0.0F);
		this.bodywingL1 = new MowzieModelRenderer(this, 0, 55);
		this.bodywingL1.setRotationPoint(3.0F, 0.2F, 6.0F);
		this.bodywingL1.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(bodywingL1, 0.0F, 0.9250245035569946F, 0.2792526803190927F);
		this.energyUP = new MowzieModelRenderer(this, 12, 93);
		this.energyUP.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.energyUP.addBox(-2.0F, 0.2F, -3.3F, 4, 2, 3, 0.0F);
		this.setRotation(energyUP, 0.9826203688728075F, 0.0F, 0.0F);
		this.toeR1 = new MowzieModelRenderer(this, 73, 76);
		this.toeR1.mirror = true;
		this.toeR1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeR1.addBox(-2.2F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeR1, 0.027925268031909273F, 0.11344640137963141F, -0.008726646259971648F);
		this.collarM = new MowzieModelRenderer(this, 10, 98);
		this.collarM.setRotationPoint(-3.0F, -1.4F, 4.9F);
		this.collarM.addBox(0.0F, -0.02F, 0.0F, 6, 2, 1, 0.0F);
		this.setRotation(collarM, -0.03490658503988659F, 0.0F, 0.0F);
		this.headintakeR1 = new MowzieModelRenderer(this, 16, 124);
		this.headintakeR1.setRotationPoint(0.0F, 0.0F, 0.9F);
		this.headintakeR1.addBox(-2.2F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotation(headintakeR1, 0.06981317007977318F, 0.029670597283903602F, -0.0017453292519943296F);
		this.collarR = new MowzieModelRenderer(this, 0, 103);
		this.collarR.mirror = true;
		this.collarR.setRotationPoint(-4.0F, -1.5F, 3.0F);
		this.collarR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
		this.setRotation(collarR, -0.0471238898038469F, 0.296705972839036F, -0.03490658503988659F);
		this.lowerarmR4 = new MowzieModelRenderer(this, 43, 74);
		this.lowerarmR4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lowerarmR4.addBox(-1.5F, 0.2F, -1.5F, 3, 1, 3, 0.0F);
		this.buttflapL = new MowzieModelRenderer(this, 36, 108);
		this.buttflapL.setRotationPoint(1.0F, -1.0F, 2.0F);
		this.buttflapL.addBox(-0.4F, 0.0F, -0.7F, 3, 4, 1, 0.0F);
		this.setRotation(buttflapL, 0.17453292519943295F, 0.12217304763960307F, -0.15707963267948966F);
		this.buttflapR = new MowzieModelRenderer(this, 36, 108);
		this.buttflapR.mirror = true;
		this.buttflapR.setRotationPoint(-1.0F, -1.0F, 2.0F);
		this.buttflapR.addBox(-2.6F, 0.0F, -0.7F, 3, 4, 1, 0.0F);
		this.setRotation(buttflapR, 0.17453292519943295F, -0.12217304763960307F, 0.15707963267948966F);
		this.lowerLegR = new MowzieModelRenderer(this, 63, 58);
		this.lowerLegR.setRotationPoint(0.0F, -0.9F, 5.0F);
		this.lowerLegR.addBox(-1.7F, -0.2F, -1.1F, 3, 7, 3, 0.0F);
		this.setRotation(lowerLegR, -0.1780235837034216F, 0.0F, 0.0F);
		this.upperArmR = new MowzieModelRenderer(this, 46, 68);
		this.upperArmR.setRotationPoint(-2.5F, 0.6F, -0.5F);
		this.upperArmR.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(upperArmR, 0.017453292519943295F, -0.017453292519943295F, 0.05235987755982988F);
		this.legarmorR = new MowzieModelRenderer(this, 73, 73);
		this.legarmorR.mirror = true;
		this.legarmorR.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.legarmorR.addBox(-1.0F, -1.3F, -0.2F, 2, 8, 1, 0.0F);
		this.toeL1 = new MowzieModelRenderer(this, 73, 76);
		this.toeL1.mirror = true;
		this.toeL1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeL1.addBox(1.2F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeL1, 0.027925268031909273F, -0.11344640137963141F, 0.008726646259971648F);
		this.headintakeR2 = new MowzieModelRenderer(this, 16, 117);
		this.headintakeR2.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.headintakeR2.addBox(-1.2F, -1.0F, -3.6F, 1, 2, 1, 0.0F);
		this.setRotation(headintakeR2, 0.061086523819801536F, 0.3141592653589793F, 0.0F);
		this.wheelconnectorR = new MowzieModelRenderer(this, 71, 64);
		this.wheelconnectorR.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelconnectorR.addBox(-1.7F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
		this.setRotation(wheelconnectorR, 0.03490658503988659F, 0.0F, 0.0F);
		this.lowerArmR1 = new MowzieModelRenderer(this, 40, 87);
		this.lowerArmR1.mirror = true;
		this.lowerArmR1.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.lowerArmR1.addBox(-1.0F, 1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotation(lowerArmR1, -0.19198621771937624F, -0.05235987755982988F, -0.06981317007977318F);
		this.upperLegL = new MowzieModelRenderer(this, 63, 49);
		this.upperLegL.mirror = true;
		this.upperLegL.setRotationPoint(2.0F, 0.5F, 1.1F);
		this.upperLegL.addBox(0.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(upperLegL, -0.6457718232379019F, 0.0F, -0.08726646259971647F);
		this.lowerLegL = new MowzieModelRenderer(this, 63, 58);
		this.lowerLegL.mirror = true;
		this.lowerLegL.setRotationPoint(0.0F, -0.9F, 5.0F);
		this.lowerLegL.addBox(-1.3F, -0.2F, -1.1F, 3, 7, 3, 0.0F);
		this.setRotation(lowerLegL, -0.1780235837034216F, 0.0F, 0.0F);
		this.chestcenter = new MowzieModelRenderer(this, 0, 0);
		this.chestcenter.setRotationPoint(0.0F, -3.0F, 2.0F);
		this.chestcenter.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.setRotation(chestcenter, -0.10646508437165408F, 0.0F, 0.0F);
		this.upperLegR = new MowzieModelRenderer(this, 63, 49);
		this.upperLegR.setRotationPoint(0.0F, 0.5F, 1.1F);
		this.upperLegR.addBox(-3.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(upperLegR, -0.6457718232379019F, 0.0F, 0.08726646259971647F);
		this.intakeL = new MowzieModelRenderer(this, 36, 68);
		this.intakeL.mirror = true;
		this.intakeL.setRotationPoint(4.0F, -3.0F, -0.5F);
		this.intakeL.addBox(-1.0F, 0.0F, -2.0F, 1, 4, 4, 0.0F);
		this.setRotation(intakeL, 0.0F, 0.0F, -0.22689280275926282F);
		this.LowerchestL = new MowzieModelRenderer(this, 0, 90);
		this.LowerchestL.mirror = true;
		this.LowerchestL.setRotationPoint(4.0F, 7.0F, 3.0F);
		this.LowerchestL.addBox(-2.0F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(LowerchestL, -0.9948376736367678F, 0.0F, 0.0F);
		this.headwingR = new MowzieModelRenderer(this, 12, 120);
		this.headwingR.setRotationPoint(0.0F, 0.0F, 0.5F);
		this.headwingR.addBox(-2.5F, -3.1F, 1.2F, 1, 2, 2, 0.0F);
		this.wingR2 = new MowzieModelRenderer(this, 36, 65);
		this.wingR2.setRotationPoint(2.0F, -1.0F, 4.2F);
		this.wingR2.addBox(-8.0F, 0.1F, -0.2F, 6, 1, 2, 0.0F);
		this.setRotation(wingR2, -0.3665191429188092F, 0.0F, 0.0F);
		this.lowerarmR3 = new MowzieModelRenderer(this, 36, 76);
		this.lowerarmR3.setRotationPoint(-3.0F, 5.8F, 0.3F);
		this.lowerarmR3.addBox(1.0F, -5.2F, -2.0F, 1, 7, 4, 0.0F);
		this.setRotation(lowerarmR3, -0.03490658503988659F, 0.12566370614359174F, -0.06335545184739416F);
		this.wheelR = new MowzieModelRenderer(this, 75, 55);
		this.wheelR.mirror = true;
		this.wheelR.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelR.addBox(-1.0F, 1.0F, 1.5F, 2, 3, 3, 0.0F);
		this.setRotation(wheelR, 0.5864306286700948F, 0.0F, 0.0F);
		this.toeR2 = new MowzieModelRenderer(this, 73, 76);
		this.toeR2.mirror = true;
		this.toeR2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toeR2.addBox(0.8F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
		this.setRotation(toeR2, 0.027925268031909273F, 0.11344640137963141F, -0.02617993877991494F);
		this.feetpartR1 = new MowzieModelRenderer(this, 63, 80);
		this.feetpartR1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.feetpartR1.addBox(-1.7F, 1.0F, -3.0F, 3, 2, 1, 0.0F);
		this.setRotation(feetpartR1, -0.0767944870877505F, 0.11344640137963141F, -0.017453292519943295F);
		this.shoulderL = new MowzieModelRenderer(this, 36, 51);
		this.shoulderL.setRotationPoint(4.0F, 2.5F, 4.0F);
		this.shoulderL.addBox(0.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.rearwaist = new MowzieModelRenderer(this, 0, 76);
		this.rearwaist.setRotationPoint(0.0F, -1.6F, 4.9F);
		this.rearwaist.addBox(-2.0F, -0.6F, -0.3F, 4, 6, 2, 0.0F);
		this.setRotation(rearwaist, -0.46774823953448036F, 0.0F, 0.0F);
		this.feetconnectorL = new MowzieModelRenderer(this, 62, 74);
		this.feetconnectorL.mirror = true;
		this.feetconnectorL.setRotationPoint(0.0F, 7.0F, -1.0F);
		this.feetconnectorL.addBox(-0.7F, -1.2F, -1.5F, 2, 4, 2, 0.0F);
		this.setRotation(feetconnectorL, 0.2897246558310587F, -0.04363323129985824F, 0.0F);
		this.wheelL = new MowzieModelRenderer(this, 75, 55);
		this.wheelL.mirror = true;
		this.wheelL.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wheelL.addBox(-1.0F, 1.0F, 1.5F, 2, 3, 3, 0.0F);
		this.setRotation(wheelL, 0.5864306286700948F, 0.0F, 0.0F);
		this.neck.addChild(this.headbase);
		this.shoulderR.addChild(this.wingR1);
		this.middlelegR.addChild(this.lowerLegR);
		this.chestcenter.addChild(this.chest1);
		this.chest1.addChild(this.lowerchestR);
		this.wingR1.addChild(this.wingR2);
		this.feetbaseR.addChild(this.feetpartR2);
		this.feetconnectorL.addChild(this.wheelconnectorL);
		this.headbase.addChild(this.headhelmet2);
		this.headbase.addChild(this.headhelmet1);
		this.lowerArmR1.addChild(this.lowerarmR2);
		this.wingL1.addChild(this.wingL2);
		this.chest1.addChild(this.cockpit1);
		this.feetconnectorR.addChild(this.feetbaseR);
		this.upperLegL.addChild(this.middlelegL);
		this.shoulderL.addChild(this.upperArmL);
		this.chest1.addChild(this.upperchestL);
		this.chest1.addChild(this.collarL);
		this.chest1.addChild(this.shoulderL);
		this.headbase.addChild(this.headintakeL1);
		this.middlelegL.addChild(this.lowerLegL);
		this.waist.addChild(this.lowertorso);
		this.legarmorR.addChild(this.feetconnectorR);
		this.bodywingL1.addChild(this.bodywingL2);
		this.shoulderL.addChild(this.wingL1);
		this.wheelconnectorL.addChild(this.wheelL);
		this.lowerarmR2.addChild(this.fistR);
		this.chest1.addChild(this.collarM);
		this.feetbaseR.addChild(this.toeR1);
		this.waist.addChild(this.rearwaist);
		this.chest1.addChild(this.lowerchestM);
		this.lowerArmL1.addChild(this.lowerarmL2);
		this.lowerLegL.addChild(this.legarmorL);
		this.headbase.addChild(this.headnose);
		this.lowertorso.addChild(this.crotchBack);
		this.chest1.addChild(this.LowerchestL);
		this.feetbaseL.addChild(this.toeL2);
		this.lowerArmL1.addChild(this.lowerarmL3);
		this.feetbaseL.addChild(this.feetpartL2);
		this.headbase.addChild(this.headwingL);
		this.lowertorso.addChild(this.buttflapR);
		this.headbase.addChild(this.headwingR);
		this.bodywingR1.addChild(this.bodywingR2);
		this.upperArmR.addChild(this.lowerArmR1);
		this.chest1.addChild(this.energyDOWN);
		this.shoulderR.addChild(this.intakeR1);
		this.feetbaseR.addChild(this.feetpartR1);
		this.lowerLegR.addChild(this.legarmorR);
		this.shoulderL.addChild(this.intakeL);
		this.feetbaseR.addChild(this.toeR2);
		this.feetconnectorR.addChild(this.wheelconnectorR);
		this.chest1.addChild(this.neck);
		this.chest1.addChild(this.collarR);
		this.waist.addChild(this.chestcenter);
		this.chest1.addChild(this.shoulderR);
		this.chest1.addChild(this.upperchestM);
		this.chest1.addChild(this.bodywingL1);
		this.headbase.addChild(this.headintakeL2);
		this.feetconnectorL.addChild(this.feetbaseL);
		this.headbase.addChild(this.headintakeR2);
		this.crotch1.addChild(this.upperLegL);
		this.chest1.addChild(this.upperchestR);
		this.feetbaseL.addChild(this.toeL1);
		this.lowerArmR1.addChild(this.lowerarmR4);
		this.lowertorso.addChild(this.buttflapL);
		this.crotch1.addChild(this.upperLegR);
		this.upperLegR.addChild(this.middlelegR);
		this.chest1.addChild(this.bodywingR1);
		this.headbase.addChild(this.headintakeR1);
		this.wheelconnectorR.addChild(this.wheelR);
		this.feetbaseL.addChild(this.feetpartL1);
		this.chest1.addChild(this.energyUP);
		this.chest1.addChild(this.cockpit2);
		this.lowertorso.addChild(this.crotchfront);
		this.legarmorL.addChild(this.feetconnectorL);
		this.lowerArmL1.addChild(this.lowerarmL4);
		this.upperArmL.addChild(this.lowerArmL1);
		this.lowerarmL2.addChild(this.fistL);
		this.lowertorso.addChild(this.crotch1);
		this.shoulderR.addChild(this.upperArmR);
		this.lowerArmR1.addChild(this.lowerarmR3);


		this.shape59 = new MowzieModelRenderer(this, 75, 55);
		this.shape59.mirror = true;
		this.shape59.setRotationPoint(-4.5F, 13.5F, 3.9F);
		this.shape59.addBox(-2.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
		this.shape139 = new MowzieModelRenderer(this, 55, 0);
		this.shape139.setRotationPoint(4.0F, -3.0F, 7.0F);
		this.shape139.addBox(0.0F, 0.0F, 0.0F, 3, 4, 5, 0.0F);
		this.shape16 = new MowzieModelRenderer(this, 0, 49);
		this.shape16.setRotationPoint(-2.0F, 7.1F, 3.0F);
		this.shape16.addBox(0.0F, -0.4F, -6.0F, 4, 2, 4, 0.0F);
		this.setRotation(shape16, -1.0018189906447452F, 0.0F, 0.0F);
		this.shape8 = new MowzieModelRenderer(this, 0, 84);
		this.shape8.setRotationPoint(-1.5F, 4.0F, -9.0F);
		this.shape8.addBox(0.0F, -0.2F, -0.6F, 3, 4, 2, 0.0F);
		this.setRotation(shape8, 1.879719604397893F, 0.0F, 0.0F);
		this.shape12 = new MowzieModelRenderer(this, 12, 76);
		this.shape12.setRotationPoint(4.0F, 0.0F, 0.0F);
		this.shape12.addBox(-2.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(shape12, 0.9948376736367678F, 0.0F, 0.0F);
		this.shape56 = new MowzieModelRenderer(this, 63, 49);
		this.shape56.setRotationPoint(0.0F, -4.5F, 20.0F);
		this.shape56.addBox(-3.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(shape56, -1.5707963267948966F, 0.0F, 0.0F);
		this.shape75 = new MowzieModelRenderer(this, 73, 73);
		this.shape75.setRotationPoint(1.3F, 14.7F, 5.6F);
		this.shape75.addBox(0.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
		this.shape41 = new MowzieModelRenderer(this, 36, 68);
		this.shape41.setRotationPoint(0.0F, -3.0F, -2.5F);
		this.shape41.addBox(-1.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
		this.setRotation(shape41, 0.0F, 0.0F, 1.3055062804917585F);
		this.shape78 = new MowzieModelRenderer(this, 73, 76);
		this.shape78.mirror = true;
		this.shape78.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.shape78.addBox(0.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
		this.setRotation(shape78, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape35 = new MowzieModelRenderer(this, 36, 76);
		this.shape35.mirror = true;
		this.shape35.setRotationPoint(2.0F, 1.0F, -2.0F);
		this.shape35.addBox(1.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
		this.setRotation(shape35, 0.0F, 0.008726646259971648F, 0.0F);
		this.shape137 = new MowzieModelRenderer(this, 37, 0);
		this.shape137.setRotationPoint(0.0F, -3.0F, 19.5F);
		this.shape137.addBox(-2.0F, -2.9F, -9.3F, 4, 3, 10, 0.0F);
		this.setRotation(shape137, 0.41887902047863906F, 0.0F, 0.0F);
		this.shape68 = new MowzieModelRenderer(this, 63, 49);
		this.shape68.mirror = true;
		this.shape68.setRotationPoint(0.0F, -4.5F, 20.0F);
		this.shape68.addBox(0.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotation(shape68, -1.5707963267948966F, 0.0F, 0.0F);
		this.shape31 = new MowzieModelRenderer(this, 36, 60);
		this.shape31.mirror = true;
		this.shape31.setRotationPoint(10.0F, 6.0F, -1.8F);
		this.shape31.addBox(-7.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(shape31, -1.5707963267948966F, -0.07853981633974483F, 0.5061454830783556F);
		this.shape66 = new MowzieModelRenderer(this, 73, 76);
		this.shape66.mirror = true;
		this.shape66.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.shape66.addBox(-6.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
		this.setRotation(shape66, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape136 = new MowzieModelRenderer(this, 0, 15);
		this.shape136.setRotationPoint(-3.5F, -5.0F, 5.0F);
		this.shape136.addBox(0.0F, 0.0F, 0.0F, 7, 6, 6, 0.0F);
		this.shape77 = new MowzieModelRenderer(this, 73, 76);
		this.shape77.mirror = true;
		this.shape77.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.shape77.addBox(4.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
		this.setRotation(shape77, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape10 = new MowzieModelRenderer(this, 0, 90);
		this.shape10.setRotationPoint(-4.0F, 7.0F, 3.0F);
		this.shape10.addBox(0.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(shape10, -0.9948376736367678F, 0.0F, 0.0F);
		this.shape13 = new MowzieModelRenderer(this, 8, 87);
		this.shape13.setRotationPoint(-1.5F, 0.0F, 0.0F);
		this.shape13.addBox(0.0F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
		this.setRotation(shape13, 0.9948376736367678F, 0.0F, 0.0F);
		this.shape32 = new MowzieModelRenderer(this, 36, 65);
		this.shape32.setRotationPoint(11.0F, 11.6F, -1.5F);
		this.shape32.addBox(-9.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
		this.setRotation(shape32, -1.5707963267948966F, -0.07853981633974483F, 0.5061454830783556F);
		this.shape49 = new MowzieModelRenderer(this, 36, 60);
		this.shape49.setRotationPoint(-10.0F, 6.0F, -1.8F);
		this.shape49.addBox(-1.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
		this.setRotation(shape49, -1.5707963267948966F, 0.07853981633974483F, -0.5061454830783556F);
		this.shape138 = new MowzieModelRenderer(this, 55, 0);
		this.shape138.mirror = true;
		this.shape138.setRotationPoint(-4.0F, -3.0F, 7.0F);
		this.shape138.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 5, 0.0F);
		this.shape70 = new MowzieModelRenderer(this, 75, 55);
		this.shape70.mirror = true;
		this.shape70.setRotationPoint(4.5F, 13.5F, 3.9F);
		this.shape70.addBox(0.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
		this.shape69 = new MowzieModelRenderer(this, 63, 58);
		this.shape69.mirror = true;
		this.shape69.setRotationPoint(0.0F, 5.5F, -1.5F);
		this.shape69.addBox(-0.1F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
		this.setRotation(shape69, 0.2617993877991494F, 0.0F, 0.0F);
		this.shape131 = new MowzieModelRenderer(this, 0, 28);
		this.shape131.setRotationPoint(-3.5F, -3.5F, -4.0F);
		this.shape131.addBox(0.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
		this.setRotation(shape131, 0.06981317007977318F, -0.13962634015954636F, 0.0F);
		this.shape44 = new MowzieModelRenderer(this, 36, 65);
		this.shape44.setRotationPoint(-11.0F, 11.6F, -1.5F);
		this.shape44.addBox(3.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
		this.setRotation(shape44, -1.5707963267948966F, 0.07853981633974483F, -0.5061454830783556F);
		this.shape43 = new MowzieModelRenderer(this, 36, 76);
		this.shape43.setRotationPoint(-2.0F, 1.0F, -2.0F);
		this.shape43.addBox(-2.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
		this.setRotation(shape43, 0.0F, -0.008726646259971648F, 0.0F);
		this.shape2 = new MowzieModelRenderer(this, 0, 55);
		this.shape2.mirror = true;
		this.shape2.setRotationPoint(-2.5F, 1.5F, 9.0F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(shape2, 0.0F, -1.5707963267948966F, 0.0F);
		this.shape64 = new MowzieModelRenderer(this, 73, 73);
		this.shape64.mirror = true;
		this.shape64.setRotationPoint(-1.3F, 14.7F, 5.6F);
		this.shape64.addBox(-2.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
		this.shape140 = new MowzieModelRenderer(this, 0, 0);
		this.shape140.setRotationPoint(-3.0F, -6.0F, -15.0F);
		this.shape140.addBox(-5.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
		this.setRotation(shape140, 0.03490658503988659F, 0.15707963267948966F, 0.7853981633974483F);
		this.shape141 = new MowzieModelRenderer(this, 0, 0);
		this.shape141.mirror = true;
		this.shape141.setRotationPoint(3.0F, -6.0F, -15.0F);
		this.shape141.addBox(-1.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
		this.setRotation(shape141, 0.03490658503988659F, -0.15707963267948966F, -0.7853981633974483F);
		this.shape6 = new MowzieModelRenderer(this, 0, 76);
		this.shape6.setRotationPoint(-2.0F, 3.5F, -5.6F);
		this.shape6.addBox(0.0F, 0.3F, 0.0F, 4, 6, 2, 0.0F);
		this.setRotation(shape6, 1.8203784098300857F, 0.0F, 0.0F);
		this.shape67 = new MowzieModelRenderer(this, 73, 76);
		this.shape67.mirror = true;
		this.shape67.setRotationPoint(0.5F, 9.5F, 3.9F);
		this.shape67.addBox(-2.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
		this.setRotation(shape67, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape133 = new MowzieModelRenderer(this, 19, 15);
		this.shape133.setRotationPoint(-2.0F, -2.2F, -15.0F);
		this.shape133.addBox(0.0F, 0.0F, 0.0F, 4, 5, 21, 0.0F);
		this.shape132 = new MowzieModelRenderer(this, 0, 28);
		this.shape132.mirror = true;
		this.shape132.setRotationPoint(3.5F, -3.5F, -4.0F);
		this.shape132.addBox(-3.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
		this.setRotation(shape132, 0.06981317007977318F, 0.13962634015954636F, 0.0F);
		this.shape7 = new MowzieModelRenderer(this, 12, 76);
		this.shape7.mirror = true;
		this.shape7.setRotationPoint(-4.0F, 0.0F, 0.0F);
		this.shape7.addBox(0.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotation(shape7, 0.9948376736367678F, 0.0F, 0.0F);
		this.shape30 = new MowzieModelRenderer(this, 36, 51);
		this.shape30.setRotationPoint(3.5F, -1.5F, -1.0F);
		this.shape30.addBox(0.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.setRotation(shape30, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape33 = new MowzieModelRenderer(this, 36, 68);
		this.shape33.mirror = true;
		this.shape33.setRotationPoint(0.0F, -3.0F, -2.5F);
		this.shape33.addBox(0.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
		this.setRotation(shape33, 0.0F, 0.0F, -1.3055062804917585F);
		this.shape40 = new MowzieModelRenderer(this, 36, 51);
		this.shape40.mirror = true;
		this.shape40.setRotationPoint(-3.5F, -1.5F, -1.0F);
		this.shape40.addBox(-4.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
		this.setRotation(shape40, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape3 = new MowzieModelRenderer(this, 0, 55);
		this.shape3.setRotationPoint(2.5F, 1.5F, 9.0F);
		this.shape3.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
		this.setRotation(shape3, 0.0F, 1.5707963267948966F, 0.0F);
		this.shape15 = new MowzieModelRenderer(this, 12, 93);
		this.shape15.setRotationPoint(-2.0F, 0.0F, 0.0F);
		this.shape15.addBox(0.0F, 0.2F, -3.3F, 4, 2, 3, 0.0F);
		this.setRotation(shape15, 0.9826203688728075F, 0.0F, 0.0F);
		this.vehicleBody = new MowzieModelRenderer(this, 0, 64);
		this.vehicleBody.setRotationPoint(0.0F, -5.0F, -2.0F);
		this.vehicleBody.addBox(-4.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
		this.shape11 = new MowzieModelRenderer(this, 0, 90);
		this.shape11.mirror = true;
		this.shape11.setRotationPoint(4.0F, 7.0F, 3.0F);
		this.shape11.addBox(-2.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
		this.setRotation(shape11, -0.9948376736367678F, 0.0F, 0.0F);
		this.shape14 = new MowzieModelRenderer(this, 0, 97);
		this.shape14.setRotationPoint(-1.5F, 7.0F, 3.0F);
		this.shape14.addBox(0.0F, 0.3F, -5.8F, 3, 2, 4, 0.0F);
		this.setRotation(shape14, -0.9948376736367678F, 0.0F, 0.0F);
		this.shape58 = new MowzieModelRenderer(this, 63, 58);
		this.shape58.setRotationPoint(0.0F, 5.5F, -1.5F);
		this.shape58.addBox(-2.9F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
		this.setRotation(shape58, 0.2617993877991494F, 0.0F, 0.0F);
		this.shape134 = new MowzieModelRenderer(this, 0, 7);
		this.shape134.setRotationPoint(0.0F, 3.5F, 0.0F);
		this.shape134.addBox(-7.5F, -3.0F, 0.0F, 15, 1, 7, 0.0F);
		this.shape4 = new MowzieModelRenderer(this, 12, 53);
		this.shape4.setRotationPoint(-3.0F, 7.0F, 0.0F);
		this.shape4.addBox(0.0F, -0.1F, -0.1F, 6, 7, 4, 0.0F);
		this.setRotation(shape4, 1.5707963267948966F, 0.0F, 0.0F);
		this.shape130 = new MowzieModelRenderer(this, 0, 7);
		this.shape130.setRotationPoint(0.0F, 8.5F, 2.0F);
		this.shape130.addBox(-7.5F, -7.0F, 0.0F, 15, 1, 7, 0.0F);
		this.shape56.addChild(this.shape59);
		this.vehicleBody.addChild(this.shape16);
		this.vehicleBody.addChild(this.shape8);
		this.vehicleBody.addChild(this.shape12);
		this.shape68.addChild(this.shape75);
		this.shape40.addChild(this.shape41);
		this.shape68.addChild(this.shape78);
		this.shape30.addChild(this.shape35);
		this.shape30.addChild(this.shape31);
		this.shape56.addChild(this.shape66);
		this.shape68.addChild(this.shape77);
		this.vehicleBody.addChild(this.shape10);
		this.vehicleBody.addChild(this.shape13);
		this.shape30.addChild(this.shape32);
		this.shape40.addChild(this.shape49);
		this.shape68.addChild(this.shape70);
		this.shape68.addChild(this.shape69);
		this.shape40.addChild(this.shape44);
		this.shape40.addChild(this.shape43);
		this.vehicleBody.addChild(this.shape2);
		this.shape56.addChild(this.shape64);
		this.vehicleBody.addChild(this.shape6);
		this.shape56.addChild(this.shape67);
		this.vehicleBody.addChild(this.shape7);
		this.shape30.addChild(this.shape33);
		this.vehicleBody.addChild(this.shape3);
		this.vehicleBody.addChild(this.shape15);
		this.vehicleBody.addChild(this.shape11);
		this.vehicleBody.addChild(this.shape14);
		this.shape56.addChild(this.shape58);
		this.vehicleBody.addChild(this.shape4);
		this.vehicleBody.addChild(this.shape130);
		this.addChildTo(shape30, vehicleBody);
		this.addChildTo(shape56, vehicleBody);
		this.addChildTo(shape40, vehicleBody);
		this.addChildTo(shape68, vehicleBody);
		this.addChildTo(shape130, vehicleBody);
		this.addChildTo(shape131, vehicleBody);
		this.addChildTo(shape132, vehicleBody);
		this.addChildTo(shape133, vehicleBody);
		this.addChildTo(shape134, vehicleBody);
		this.addChildTo(shape136, vehicleBody);
		this.addChildTo(shape137, vehicleBody);
		this.addChildTo(shape138, vehicleBody);
		this.addChildTo(shape139, vehicleBody);
		this.addChildTo(shape140, vehicleBody);
		this.addChildTo(shape141, vehicleBody);

		parts = new MowzieModelRenderer[]{waist, lowertorso, chestcenter, rearwaist, crotch1, crotchfront, buttflapL, buttflapR, crotchBack, upperLegR, upperLegL, middlelegR, lowerLegR, legarmorR, feetconnectorR, wheelconnectorR, feetbaseR, wheelR, feetpartR1, feetpartR2, toeR1, toeR2, middlelegL, lowerLegL, legarmorL, feetconnectorL, wheelconnectorL, feetbaseL, wheelL, toeL1, toeL2, feetpartL2, feetpartL1, chest1, bodywingR1, bodywingL1, neck, upperchestR, cockpit1, cockpit2, lowerchestR, LowerchestL, upperchestL, upperchestM, lowerchestM, energyUP, energyDOWN, collarR, collarL, collarM, shoulderL, shoulderR, bodywingR2, bodywingL2, headbase, headnose, headhelmet1, headwingR, headhelmet2, headwingL, headintakeR1, headintakeL2, headintakeR2, headintakeL1, wingL1, intakeL, upperArmL, wingL2, lowerArmL1, lowerarmL2, lowerarmL3, lowerarmL4, fistL, wingR1, intakeR1, upperArmR, wingR2, lowerArmR1, lowerarmR4, lowerarmR3, lowerarmR2, fistR};
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
						this.vehicleBody.render(f5);
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
					globalDegree = 1.5F;
					globalSpeed = 1.5F;
				}

				if (wearingHead) 
				{
					faceTarget(headbase, 1, par4, par5);

					if(wearingChest)
					{
						headbase.rotationPointY += 2;
						headbase.rotationPointX += 1;
						headbase.rotationPointZ -= 1.5F;
					}
					else
					{
						headbase.rotationPointX += 0F;
						headbase.rotationPointZ -= 2.5F;
						headbase.rotationPointY += 0.5F;
					}
				}
				else
				{
					if(wearingChest)
					{
						if(wearingLegs)
						{
							TFModelHelper.headOffsetY = -3.1F;
							TFModelHelper.headOffsetZ = 2F;
						}
						else
						{
							TFModelHelper.headOffsetY = -0.6F;
							TFModelHelper.headOffsetZ = 0F;
						}

					}
					else
					{
						TFModelHelper.headOffsetY = 0F;
						TFModelHelper.headOffsetZ = 0F;
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

				boolean sneaking = player.isSneaking();
				if (sneaking)
				{
					globalDegree = 1.5F;
					globalSpeed = 1.3F;
				}
				
				if (wearingHead && wearingLegs && wearingChest)
				{
					if (entity.onGround || player.capabilities.isFlying)
					{
						bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, par1, par2);
						waist.rotationPointY += 2 * par2;
						walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
						walk(chestcenter, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
						swing(chestcenter, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0, 0, par1, par2);
						swing(waist, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0, 0, par1, par2);
						walk(headbase, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * par2 * backwardInverter, par1, par2);
						swing(headbase, 0.5F * globalSpeed, -0.4F * globalDegree, false, 0, 0, par1, par2);
						//						headbase.rotationPointX += 0.6 * globalDegree * par2 * Math.cos(par1 * 0.5F * globalSpeed);

						swing(upperLegL, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0F, par1, par2);
						swing(upperLegR, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0F, par1, par2);

						walk(upperLegL, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, par1, par2);
						walk(middlelegL, 0.5F * globalSpeed, 1F * globalDegree, true, 1F* backwardInverter, 0F, par1, par2);
						walk(lowerLegL, 0.5F * globalSpeed, 0.6F * globalDegree, false, 0F, 0F, par1, par2);
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
							waist.rotateAngleX += 0.5F;
							waist.rotationPointZ -= 6F;
							waist.rotationPointY += 0.2F;
							
							headbase.rotateAngleX -= 0.5;
							upperLegR.rotateAngleX -= 0.7;
							upperLegL.rotateAngleX -= 0.7;
							upperLegR.rotateAngleY += 0.2;
							upperLegL.rotateAngleY -= 0.2;
							lowerLegR.rotateAngleX -= 0.1;
							lowerLegL.rotateAngleX -= 0.1;
							feetbaseL.rotateAngleX += 0.5F;
							feetbaseR.rotateAngleX += 0.5F;
							feetbaseR.rotationPointY += 2;
							feetbaseL.rotationPointY += 2;
							upperArmR.rotateAngleX -= 0.5;
							upperArmL.rotateAngleX -= 0.5;
							upperArmR.rotateAngleZ += 0.5;
							upperArmL.rotateAngleZ -= 0.5;
							lowerArmR1.rotateAngleZ -= 0.5;
							lowerArmL1.rotateAngleZ += 0.5;
							
//							waist.rotateAngleX += 0.4F;
//							waist.rotationPointY += 1F;
//							headbase.rotateAngleX -= 0.4F;
//							
//							upperArmL.rotateAngleZ -= 0.3F;
//							upperArmL.rotateAngleX -= 0.2F;
//							
//							upperArmR.rotateAngleZ += 0.3F;
//							upperArmR.rotateAngleX -= 0.2F;
//							
//							lowerArmL1.rotateAngleX -= 0.5F;
//							lowerArmL1.rotateAngleZ += 0.4F;
//							
//							lowerArmR1.rotateAngleX -= 0.5F;
//							lowerArmR1.rotateAngleZ -= 0.4F;
//							
//							upperLegL.rotateAngleX -= 0.7F;
//							upperLegR.rotateAngleX -= 0.7F;
//							
//							upperLegL.rotateAngleY -= 0.3F;
//							upperLegR.rotateAngleY += 0.3F;
//							
//							feetbaseL.rotateAngleX += 0.5F;
//							feetbaseR.rotateAngleX += 0.5F;
						}
					}
					else// if(!player.isWet())
					{
						float upwardPose = (float) (1/(1 + Math.exp(-20 * (entity.motionY + 0.2))));
						float downwardPose = (float) (1/(1 + Math.exp(10 * (entity.motionY + 0.2))));

						double speed = Math.sqrt((player.motionX * player.motionX) + (player.motionZ * player.motionZ)) * 1.2D;

						waist.rotateAngleX += 0.2 * par2 * backwardInverter;

						chestcenter.rotateAngleX += 0.2 * upwardPose;
						chest1.rotateAngleX -= 0.4 * upwardPose;
						headbase.rotateAngleX += 0.6 * upwardPose;

						upperArmR.rotateAngleX += 0.1 * upwardPose;
						upperArmL.rotateAngleX += 0.1 * upwardPose;
						upperArmR.rotateAngleZ -= 0.1 * upwardPose;
						upperArmL.rotateAngleZ += 0.1 * upwardPose;
						lowerArmR1.rotateAngleX += 0.2 * upwardPose;
						lowerArmL1.rotateAngleX += 0.2 * upwardPose;

						upperLegR.rotateAngleX += 0.2 * upwardPose;
						upperLegL.rotateAngleX += 0.2 * upwardPose;
						lowerLegR.rotateAngleX += 0.5 * upwardPose;
						lowerLegL.rotateAngleX += 0.5 * upwardPose;

						waist.rotateAngleX += speed * downwardPose;

						upperArmR.rotateAngleZ += 1 * downwardPose;
						upperArmL.rotateAngleZ -= 1 * downwardPose;
						lowerArmR1.rotateAngleX -= 1 * downwardPose;
						lowerArmL1.rotateAngleX -= 1 * downwardPose;
					}

					int timer = TFDataManager.getTransformationTimer(player);

					if (timer == 0)
					{
						this.vehicleBody.rotateAngleX = par5 / (180F / (float) Math.PI);
						this.vehicleBody.rotateAngleZ = -(this.bipedHead.rotateAngleY - (this.bipedBody.rotateAngleY - this.bipedHead.rotateAngleY));

						bipedHead.offsetY = 256F;
						bipedBody.offsetY = 256F;
						bipedRightArm.offsetY = 256F;
						bipedLeftArm.offsetY = 256F;
						bipedRightLeg.offsetY = 256F;
						bipedLeftLeg.offsetY = 256F;
						waist.offsetY = 256F;
						vehicleBody.offsetY = 0F;
					}
					else 
					{
						int t = TFDataManager.getTransformationTimer(player);
						float f = (float) (20 - t) / 2;
						
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
				
						this.wingR1.rotationPointZ += f * 0.5F;
						this.wingL1.rotationPointZ += f * 0.5F;
						
						this.wingR1.rotationPointY += f * 0.5F;
						this.wingL1.rotationPointY += f * 0.5F;
						
						this.wingR1.rotateAngleX += f * 0.1F;
						this.wingL1.rotateAngleX += f * 0.1F;
						
						this.wingR1.rotateAngleY += f * 0.2F;
						this.wingL1.rotateAngleY += -f * 0.2F;
						
						this.cockpit1.rotateAngleZ += f * 0.33F;
						this.cockpit1.rotationPointY += f * 0.1F;
						this.cockpit1.rotationPointZ += f * -0.2F;
						
//						this.lowerArmL1.rotateAngleZ = f * 0.05F;
//						this.lowerArmR1.rotateAngleZ = f * -0.05F;
						
						bipedHead.offsetY = 0F;
						bipedBody.offsetY = 0F;
						bipedRightArm.offsetY = 0F;
						bipedLeftArm.offsetY = 0F;
						bipedRightLeg.offsetY = 0F;
						bipedLeftLeg.offsetY = 0F;
						waist.offsetY = 0F;
						vehicleBody.offsetY = 256F;
					}
				}
				else
				{
					this.upperArmL.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
					this.upperArmR.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 2;

					this.upperLegR.rotateAngleX = ((MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2) - 0.65F;
					this.upperLegL.rotateAngleX = ((MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 2) - 0.65F;
				
			        if (this.heldItemLeft != 0)
			        {
			            this.upperArmL.rotateAngleX = this.upperArmL.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
			        }

			        if (this.heldItemRight != 0)
			        {
			            this.upperArmR.rotateAngleX = this.upperArmR.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
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