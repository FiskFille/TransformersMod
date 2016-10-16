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
import fiskfille.tf.client.model.AnimationModifier;
import fiskfille.tf.client.model.AnimationModifier.Predicate;
import fiskfille.tf.client.model.AnimationModifier.Type;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class ModelCloudtrap extends ModelTransformerBaseNew
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

	public ModelCloudtrap()
	{
		super(1, 0.8F,
				new AnimationModifier(Type.DEGREE, Predicate.SNEAKING, 1.5F),
				new AnimationModifier(Type.SPEED, Predicate.SNEAKING, 1.3F),
				new AnimationModifier(Type.DEGREE, Predicate.and(Predicate.SNEAKING, Predicate.FLYING), 0.4F),
				new AnimationModifier(Type.SPEED, Predicate.and(Predicate.SNEAKING, Predicate.FLYING), 0.6923076923076923F)
				);

		textureWidth = 128;
		textureHeight = 128;
		waist1 = new MowzieModelRenderer(this, 6, 14);
		waist1.setRotationPoint(0, 1.4F, -1.1F);
		waist1.addBox(-1, 0, 0, 2, 1, 2, 0);
		setRotateAngle(waist1, -0.017453292519943295F, 0, 0);
		fistR2 = new MowzieModelRenderer(this, 41, 42);
		fistR2.mirror = true;
		fistR2.setRotationPoint(0, 0, 0);
		fistR2.addBox(-0.2F, -0.2F, -1, 1, 2, 2, 0);
		lowerlegRbase = new MowzieModelRenderer(this, 0, 81);
		lowerlegRbase.mirror = true;
		lowerlegRbase.setRotationPoint(-0.5F, 6, 0);
		lowerlegRbase.addBox(-1, 0, -1, 2, 6, 2, 0);
		setRotateAngle(lowerlegRbase, 0.19198621771937624F, 0.017453292519943295F, -0.017453292519943295F);
		rightshoulder4 = new MowzieModelRenderer(this, 10, 44);
		rightshoulder4.setRotationPoint(-1, 0, 1);
		rightshoulder4.addBox(-0.6F, -0.2F, -0.5F, 1, 3, 3, 0);
		setRotateAngle(rightshoulder4, 0.06981317007977318F, 0.20943951023931953F, -0.9424777960769379F);
		lowerarmL2 = new MowzieModelRenderer(this, 35, 35);
		lowerarmL2.setRotationPoint(1, 4, 1.2F);
		lowerarmL2.addBox(-1, -4, -1, 1, 4, 1, 0);
		setRotateAngle(lowerarmL2, -0.08726646259971647F, 0, 0.10471975511965977F);
		torsoL1 = new MowzieModelRenderer(this, 15, 53);
		torsoL1.mirror = true;
		torsoL1.setRotationPoint(1, -5, -1.4F);
		torsoL1.addBox(0, 0.2F, 0, 2, 2, 2, 0);
		setRotateAngle(torsoL1, 0.17453292519943295F, -0.2617993877991494F, 0.19198621771937624F);
		feetR2 = new MowzieModelRenderer(this, 14, 88);
		feetR2.mirror = true;
		feetR2.setRotationPoint(-1.5F, 0, -3.4F);
		feetR2.addBox(0, 0, -1, 3, 1, 1, 0);
		setRotateAngle(feetR2, 1.0122909661567112F, 0, 0);
		engineL3 = new MowzieModelRenderer(this, 10, 77);
		engineL3.mirror = true;
		engineL3.setRotationPoint(0, 1, 3);
		engineL3.addBox(-1, 0, -1, 2, 2, 1, 0);
		setRotateAngle(engineL3, -0.19198621771937624F, 0, 0);
		rightshoulder5 = new MowzieModelRenderer(this, 0, 56);
		rightshoulder5.setRotationPoint(-1, -1, -2.8F);
		rightshoulder5.addBox(0, 0, 0, 1, 2, 4, 0);
		setRotateAngle(rightshoulder5, 0, 0, 0.03490658503988659F);
		lowerlegLbase = new MowzieModelRenderer(this, 0, 81);
		lowerlegLbase.setRotationPoint(0.5F, 6, 0);
		lowerlegLbase.addBox(-1, 0, -1, 2, 6, 2, 0);
		setRotateAngle(lowerlegLbase, 0.19198621771937624F, -0.017453292519943295F, 0.017453292519943295F);
		feetL5 = new MowzieModelRenderer(this, 0, 98);
		feetL5.mirror = true;
		feetL5.setRotationPoint(-1, 1.6F, -3.3F);
		feetL5.addBox(0, 0, 0, 2, 1, 3, 0);
		setRotateAngle(feetL5, 0.47123889803846897F, 0, 0);
		wingL2 = new MowzieModelRenderer(this, 43, 23);
		wingL2.setRotationPoint(0, 2, 0);
		wingL2.addBox(-0.4F, -0.3F, -1, 1, 6, 2, 0);
		setRotateAngle(wingL2, 0.05235987755982988F, 0.017453292519943295F, 0.08726646259971647F);
		lowerarmL1 = new MowzieModelRenderer(this, 43, 31);
		lowerarmL1.setRotationPoint(0, 0, 0);
		lowerarmL1.addBox(-0.9F, -1.1F, 0.5F, 2, 2, 1, 0);
		setRotateAngle(lowerarmL1, -0.08726646259971647F, -0.08726646259971647F, -0.7853981633974483F);
		wingbaseL = new MowzieModelRenderer(this, 35, 17);
		wingbaseL.mirror = true;
		wingbaseL.setRotationPoint(1.6F, 0, 0);
		wingbaseL.addBox(-0.3F, -1.2F, -1.1F, 1, 2, 3, 0);
		setRotateAngle(wingbaseL, 0.17453292519943295F, 0.45378560551852565F, 0.08726646259971647F);
		leftshoulder2 = new MowzieModelRenderer(this, 10, 44);
		leftshoulder2.mirror = true;
		leftshoulder2.setRotationPoint(1, 0, 1);
		leftshoulder2.addBox(-0.4F, -0.2F, -0.5F, 1, 3, 3, 0);
		setRotateAngle(leftshoulder2, 0.06981317007977318F, -0.20943951023931953F, 0.9424777960769379F);
		finger2_2 = new MowzieModelRenderer(this, 46, 35);
		finger2_2.setRotationPoint(-1, 1.7F, -0.2F);
		finger2_2.addBox(0, 0, 0, 1, 1, 1, 0);
		setRotateAngle(finger2_2, 0.8377580409572781F, 0, 0);
		wingstrutR2 = new MowzieModelRenderer(this, 40, 22);
		wingstrutR2.setRotationPoint(-0.1F, -0.5F, 2);
		wingstrutR2.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
		setRotateAngle(wingstrutR2, 0.05235987755982988F, 0.017453292519943295F, -0.017453292519943295F);
		wingconnectorR = new MowzieModelRenderer(this, 44, 20);
		wingconnectorR.setRotationPoint(-0.6F, 1, 0);
		wingconnectorR.addBox(-1.7F, -0.5F, -0.5F, 2, 1, 1, 0);
		setRotateAngle(wingconnectorR, -0.03490658503988659F, 0.5235987755982988F, 0.24434609527920614F);
		leftshoulder3 = new MowzieModelRenderer(this, 0, 56);
		leftshoulder3.mirror = true;
		leftshoulder3.setRotationPoint(2, -1, -2.8F);
		leftshoulder3.addBox(-1, 0, 0, 1, 2, 4, 0);
		setRotateAngle(leftshoulder3, 0, 0, -0.03490658503988659F);
		head1 = new MowzieModelRenderer(this, 14, 19);
		head1.setRotationPoint(0, -3, 2.7F);
		head1.addBox(-2, -0.4F, -2.2F, 4, 1, 2, 0);
		setRotateAngle(head1, 0.04537856055185257F, 0, 0);
		torsoR1 = new MowzieModelRenderer(this, 15, 53);
		torsoR1.setRotationPoint(-1, -5, -1.4F);
		torsoR1.addBox(-2, 0.2F, 0, 2, 2, 2, 0);
		setRotateAngle(torsoR1, 0.17453292519943295F, 0.2617993877991494F, -0.19198621771937624F);
		chestcockpitbase = new MowzieModelRenderer(this, 21, 44);
		chestcockpitbase.setRotationPoint(-1.5F, -2, -1.5F);
		chestcockpitbase.addBox(0, 0, -2, 3, 2, 2, 0);
		setRotateAngle(chestcockpitbase, 0.7853981633974483F, 0, 0);
		waistR2 = new MowzieModelRenderer(this, 18, 62);
		waistR2.setRotationPoint(-1, 0.1F, -1);
		waistR2.addBox(-2, 0, -0.5F, 2, 1, 1, 0);
		setRotateAngle(waistR2, 0, 0.5585053606381855F, 0.20943951023931953F);
		rightshoulderbase = new MowzieModelRenderer(this, 0, 46);
		rightshoulderbase.setRotationPoint(-4, 0.15F, 1.4F);
		rightshoulderbase.addBox(-1, -1, -2.9F, 3, 1, 4, 0);
		setRotateAngle(rightshoulderbase, -0.06981317007977318F, 0.17453292519943295F, 0.05235987755982988F);
		rightshoulder1 = new MowzieModelRenderer(this, 6, 56);
		rightshoulder1.setRotationPoint(2.1F, -1, -2.9F);
		rightshoulder1.addBox(-3.1F, 0, 0, 3, 1, 3, 0);
		setRotateAngle(rightshoulder1, 0.20943951023931953F, 0.06981317007977318F, 0);
		engineL2 = new MowzieModelRenderer(this, 4, 77);
		engineL2.setRotationPoint(0, 1, 0);
		engineL2.addBox(-1, 0, 0, 2, 2, 1, 0);
		setRotateAngle(engineL2, 0.19198621771937624F, 0, 0);
		lowerlegR2 = new MowzieModelRenderer(this, 18, 80);
		lowerlegR2.setRotationPoint(0, 0, 1);
		lowerlegR2.addBox(-3, -2, -1, 3, 2, 1, 0);
		setRotateAngle(lowerlegR2, 0.4886921905584123F, 0, 0);
		shoulderpieceR1 = new MowzieModelRenderer(this, 44, 16);
		shoulderpieceR1.setRotationPoint(0, 0.3F, 0);
		shoulderpieceR1.addBox(-1, 0, -1, 1, 2, 2, 0);
		setRotateAngle(shoulderpieceR1, 0.06981317007977318F, 0, -0.10471975511965977F);
		head10 = new MowzieModelRenderer(this, 24, 17);
		head10.mirror = true;
		head10.setRotationPoint(0, -0.8F, -0.8F);
		head10.addBox(1.2F, -1.9F, 0.1F, 1, 2, 2, 0);
		setRotateAngle(head10, 0, -0.08726646259971647F, 0);
		lowerlegL3 = new MowzieModelRenderer(this, 14, 80);
		lowerlegL3.mirror = true;
		lowerlegL3.setRotationPoint(4, 0, 0);
		lowerlegL3.addBox(0, 0, 0, 1, 3, 1, 0);
		setRotateAngle(lowerlegL3, 0, 0, 0.33161255787892263F);
		shoulderbase = new MowzieModelRenderer(this, 0, 40);
		shoulderbase.setRotationPoint(0, -1, 1);
		shoulderbase.addBox(-4, -0.8F, 0, 8, 2, 2, 0);
		setRotateAngle(shoulderbase, -0.45378560551852565F, 0, 0);
		engineL6 = new MowzieModelRenderer(this, 0, 89);
		engineL6.setRotationPoint(0, 2, 1);
		engineL6.addBox(-1, -3, -0.4F, 2, 5, 2, 0);
		setRotateAngle(engineL6, -0.2792526803190927F, 0.3490658503988659F, 0);
		neck1 = new MowzieModelRenderer(this, 0, 14);
		neck1.setRotationPoint(-0.5F, -1.2F, -2);
		neck1.addBox(0, 0, 0, 1, 2, 3, 0);
		fistL1 = new MowzieModelRenderer(this, 45, 40);
		fistL1.mirror = true;
		fistL1.setRotationPoint(0, 0.4F, -0.3F);
		fistL1.addBox(-1, -1.4F, -1, 2, 2, 1, 0);
		setRotateAngle(fistL1, 0.08726646259971647F, 0, 0);
		feetL3 = new MowzieModelRenderer(this, 14, 90);
		feetL3.mirror = true;
		feetL3.setRotationPoint(-0.5F, 0, 1.6F);
		feetL3.addBox(-1, 0, 0, 1, 1, 2, 0);
		setRotateAngle(feetL3, 0, -0.4363323129985824F, 0);
		wingstrutL1 = new MowzieModelRenderer(this, 40, 22);
		wingstrutL1.setRotationPoint(0.1F, 0, -0.4F);
		wingstrutL1.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
		setRotateAngle(wingstrutL1, 0, 0.08726646259971647F, -0.03490658503988659F);
		engineR1 = new MowzieModelRenderer(this, 4, 73);
		engineR1.setRotationPoint(0, 3.9F, 0);
		engineR1.addBox(-1.5F, 0, 0, 3, 1, 3, 0);
		setRotateAngle(engineR1, 0.3490658503988659F, -0.017453292519943295F, -0.017453292519943295F);
		head = new MowzieModelRenderer(this, 14, 13);
		head.setRotationPoint(0.5F, 0, 1.2F);
		head.addBox(-1.5F, -3, -1.3F, 3, 3, 3, 0);
		back3 = new MowzieModelRenderer(this, 18, 57);
		back3.setRotationPoint(0, 3, 0);
		back3.addBox(-1, -1, -0.8F, 2, 4, 1, 0);
		setRotateAngle(back3, 0.3665191429188092F, 0, 0);
		engineR5 = new MowzieModelRenderer(this, 4, 77);
		engineR5.mirror = true;
		engineR5.setRotationPoint(0, 1, 0);
		engineR5.addBox(-1, 0, 0, 2, 2, 1, 0);
		setRotateAngle(engineR5, 0.19198621771937624F, 0, 0);
		lowerarmL3 = new MowzieModelRenderer(this, 35, 35);
		lowerarmL3.mirror = true;
		lowerarmL3.setRotationPoint(-1, 4, 1.2F);
		lowerarmL3.addBox(0, -4, -1, 1, 4, 1, 0);
		setRotateAngle(lowerarmL3, -0.08726646259971647F, 0, -0.10471975511965977F);
		feetL4 = new MowzieModelRenderer(this, 14, 90);
		feetL4.setRotationPoint(0.5F, 0, 1.6F);
		feetL4.addBox(0, 0, 0, 1, 1, 2, 0);
		setRotateAngle(feetL4, 0, 0.4363323129985824F, 0);
		rightshoulder3 = new MowzieModelRenderer(this, 0, 51);
		rightshoulder3.setRotationPoint(2, 1, -2.9F);
		rightshoulder3.addBox(-3, -0.5F, 0.1F, 3, 1, 4, 0);
		setRotateAngle(rightshoulder3, 0.006981317007977318F, 0, 0.15707963267948966F);
		buttflapR = new MowzieModelRenderer(this, 9, 65);
		buttflapR.setRotationPoint(-1, 0.2F, 1);
		buttflapR.addBox(-1, 0, 0, 3, 3, 1, 0);
		setRotateAngle(buttflapR, 0.13962634015954636F, 0.08726646259971647F, -0.10471975511965977F);
		head5 = new MowzieModelRenderer(this, 14, 26);
		head5.setRotationPoint(0, 0, -1);
		head5.addBox(-1.9F, -0.8F, -1, 2, 1, 3, 0);
		setRotateAngle(head5, 0.24434609527920614F, 0.33161255787892263F, 0);
		wingL1 = new MowzieModelRenderer(this, 35, 22);
		wingL1.mirror = true;
		wingL1.setRotationPoint(0, -1.2F, -1.1F);
		wingL1.addBox(-0.3F, 0, 0, 1, 4, 3, 0);
		setRotateAngle(wingL1, 0.9773843811168246F, -0.0017453292519943296F, 0);
		upperlegL3 = new MowzieModelRenderer(this, 8, 100);
		upperlegL3.setRotationPoint(0.3F, 1.6F, 0);
		upperlegL3.addBox(-1, 0, -1, 1, 4, 2, 0);
		lowerlegR5 = new MowzieModelRenderer(this, 18, 83);
		lowerlegR5.setRotationPoint(-2, 5, 0);
		lowerlegR5.addBox(-1, 0, 0, 1, 2, 2, 0);
		wingstrutL2 = new MowzieModelRenderer(this, 40, 22);
		wingstrutL2.setRotationPoint(0.1F, -0.5F, 2);
		wingstrutL2.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
		setRotateAngle(wingstrutL2, 0.05235987755982988F, 0.017453292519943295F, -0.017453292519943295F);
		wingL4 = new MowzieModelRenderer(this, 43, 23);
		wingL4.setRotationPoint(0, 2, 0);
		wingL4.addBox(-0.4F, -0.3F, -1, 1, 6, 2, 0);
		setRotateAngle(wingL4, -0.3665191429188092F, -0.08726646259971647F, 0.15707963267948966F);
		cockpit4 = new MowzieModelRenderer(this, 14, 47);
		cockpit4.setRotationPoint(0, 1, -2);
		cockpit4.addBox(0, 0, -3, 1, 1, 5, 0);
		setRotateAngle(cockpit4, 0.2792526803190927F, -0.17453292519943295F, 0.15707963267948966F);
		lowerarmR2 = new MowzieModelRenderer(this, 35, 35);
		lowerarmR2.mirror = true;
		lowerarmR2.setRotationPoint(-1, 4, 1.2F);
		lowerarmR2.addBox(0, -4, -1, 1, 4, 1, 0);
		setRotateAngle(lowerarmR2, -0.08726646259971647F, 0, -0.10471975511965977F);
		fistLbase = new MowzieModelRenderer(this, 39, 37);
		fistLbase.setRotationPoint(0, 4.1F, 0.3F);
		fistLbase.addBox(-1.5F, -0.2F, -1, 3, 1, 2, 0);
		setRotateAngle(fistLbase, 0.08726646259971647F, -1.3962634015954636F, 0);
		head8 = new MowzieModelRenderer(this, 14, 30);
		head8.setRotationPoint(0, -2, 2.7F);
		head8.addBox(-2, -0.3F, -1.4F, 4, 2, 1, 0);
		setRotateAngle(head8, -0.33161255787892263F, 0, 0);
		engineR7 = new MowzieModelRenderer(this, 4, 73);
		engineR7.setRotationPoint(0, -1.2F, 0.5F);
		engineR7.addBox(-1.5F, 0, -1.5F, 3, 1, 3, 0);
		back1 = new MowzieModelRenderer(this, 33, 49);
		back1.setRotationPoint(0, -6.9F, 1);
		back1.addBox(-2, -2, -0.1F, 4, 5, 2, 0);
		setRotateAngle(back1, -0.22514747350726852F, 0, 0);
		lowerarmR1 = new MowzieModelRenderer(this, 43, 31);
		lowerarmR1.setRotationPoint(0, 0, 0);
		lowerarmR1.addBox(-0.9F, -1.1F, 0.5F, 2, 2, 1, 0);
		setRotateAngle(lowerarmR1, -0.08726646259971647F, -0.08726646259971647F, -0.7853981633974483F);
		feetR6 = new MowzieModelRenderer(this, 0, 98);
		feetR6.setRotationPoint(-1, 1.6F, -3.3F);
		feetR6.addBox(0, 0, 0, 2, 1, 3, 0);
		setRotateAngle(feetR6, 0.47123889803846897F, 0, 0);
		torsoConnector = new MowzieModelRenderer(this, 0, 25);
		torsoConnector.setRotationPoint(0, 0, 0);
		torsoConnector.addBox(-1, -6, -1, 2, 6, 2, 0);
		lowerlegL1 = new MowzieModelRenderer(this, 16, 73);
		lowerlegL1.mirror = true;
		lowerlegL1.setRotationPoint(-1, 0, -2);
		lowerlegL1.addBox(0, 0, 0, 3, 5, 2, 0);
		setRotateAngle(lowerlegL1, -0.06981317007977318F, -0.3839724354387525F, 0);
		engineR2 = new MowzieModelRenderer(this, 10, 77);
		engineR2.setRotationPoint(0, 1, 3);
		engineR2.addBox(-1, 0, -1, 2, 2, 1, 0);
		setRotateAngle(engineR2, -0.19198621771937624F, 0, 0);
		fistRbase = new MowzieModelRenderer(this, 35, 42);
		fistRbase.setRotationPoint(0, 4, 0.3F);
		fistRbase.addBox(-0.8F, -0.2F, -1, 1, 2, 2, 0);
		setRotateAngle(fistRbase, -0.05235987755982988F, 0.017453292519943295F, -0.12217304763960307F);
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
		setRotateAngle(upperLegR, -0.10471975511965977F, 0, 0.03490658503988659F);
		head7 = new MowzieModelRenderer(this, 21, 26);
		head7.setRotationPoint(0, 0.05F, -1.75F);
		head7.addBox(-1, -1.65F, -0.7F, 2, 2, 1, 0);
		setRotateAngle(head7, -0.9948376736367678F, 0, 0);
		finger1_2 = new MowzieModelRenderer(this, 46, 35);
		finger1_2.setRotationPoint(0, 1.7F, -0.2F);
		finger1_2.addBox(0, 0, 0, 1, 1, 1, 0);
		setRotateAngle(finger1_2, 0.8377580409572781F, 0, 0);
		wingR2 = new MowzieModelRenderer(this, 43, 23);
		wingR2.mirror = true;
		wingR2.setRotationPoint(0, 2, 0);
		wingR2.addBox(-0.6F, -0.3F, -1, 1, 6, 2, 0);
		setRotateAngle(wingR2, 0.05235987755982988F, -0.017453292519943295F, -0.08726646259971647F);
		upperlegR1 = new MowzieModelRenderer(this, 0, 102);
		upperlegR1.setRotationPoint(0, 0, 0);
		upperlegR1.addBox(-1.5F, -0.2F, -1, 2, 2, 2, 0);
		setRotateAngle(upperlegR1, 0.06981317007977318F, 0.20943951023931953F, -0.06981317007977318F);
		lowerlegR4 = new MowzieModelRenderer(this, 14, 80);
		lowerlegR4.setRotationPoint(-4, 0, 0);
		lowerlegR4.addBox(-1, 0, 0, 1, 3, 1, 0);
		setRotateAngle(lowerlegR4, 0, 0, -0.33161255787892263F);
		shoulderpieceL1 = new MowzieModelRenderer(this, 44, 16);
		shoulderpieceL1.mirror = true;
		shoulderpieceL1.setRotationPoint(0, 0.3F, 0);
		shoulderpieceL1.addBox(0, 0, -1, 1, 2, 2, 0);
		setRotateAngle(shoulderpieceL1, 0.06981317007977318F, 0, 0.10471975511965977F);
		finger4_2 = new MowzieModelRenderer(this, 46, 35);
		finger4_2.setRotationPoint(-0.5F, 1.7F, -0.2F);
		finger4_2.addBox(0, 0, 0, 1, 1, 1, 0);
		setRotateAngle(finger4_2, 0.8377580409572781F, 0, 0);
		engineL7 = new MowzieModelRenderer(this, 4, 73);
		engineL7.setRotationPoint(0, -1.2F, 0.5F);
		engineL7.addBox(-1.5F, 0, -1.5F, 3, 1, 3, 0);
		feetR4 = new MowzieModelRenderer(this, 14, 90);
		feetR4.mirror = true;
		feetR4.setRotationPoint(-0.5F, 0, 1.6F);
		feetR4.addBox(-1, 0, 0, 1, 1, 2, 0);
		setRotateAngle(feetR4, 0, -0.4363323129985824F, 0);
		head4 = new MowzieModelRenderer(this, 23, 21);
		head4.setRotationPoint(2, -3.3F, 0.5F);
		head4.addBox(-1, 0, -3, 1, 1, 3, 0);
		setRotateAngle(head4, 0.06981317007977318F, 0.15707963267948966F, 0.008726646259971648F);
		waistL1 = new MowzieModelRenderer(this, 0, 67);
		waistL1.setRotationPoint(1, 0.1F, 0);
		waistL1.addBox(0, 0, -0.5F, 2, 1, 2, 0);
		setRotateAngle(waistL1, 0, -0.05235987755982988F, -0.20943951023931953F);
		upperlegR3 = new MowzieModelRenderer(this, 8, 100);
		upperlegR3.setRotationPoint(-0.7F, 1.6F, 0);
		upperlegR3.addBox(-1, 0, -1, 1, 4, 2, 0);
		leftshoulder5 = new MowzieModelRenderer(this, 0, 62);
		leftshoulder5.mirror = true;
		leftshoulder5.setRotationPoint(0.1F, 0, 3);
		leftshoulder5.addBox(0, 0, 0, 3, 2, 2, 0);
		setRotateAngle(leftshoulder5, -0.4886921905584123F, 0, 0);
		wingstrutR3 = new MowzieModelRenderer(this, 40, 22);
		wingstrutR3.setRotationPoint(-0.1F, -1.2F, 3);
		wingstrutR3.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
		setRotateAngle(wingstrutR3, 0.5759586531581287F, 0.017453292519943295F, -0.017453292519943295F);
		waistR1 = new MowzieModelRenderer(this, 0, 67);
		waistR1.setRotationPoint(-1, 0.1F, 0);
		waistR1.addBox(-2, 0, -0.5F, 2, 1, 2, 0);
		setRotateAngle(waistR1, 0, 0.05235987755982988F, 0.20943951023931953F);
		torsoL2 = new MowzieModelRenderer(this, 10, 60);
		torsoL2.mirror = true;
		torsoL2.setRotationPoint(0.4F, -3.1F, -0.9F);
		torsoL2.addBox(0, -0.7F, 0, 2, 4, 1, 0);
		setRotateAngle(torsoL2, 0.13962634015954636F, -0.20943951023931953F, 0.19198621771937624F);
		lowerlegR3 = new MowzieModelRenderer(this, 8, 84);
		lowerlegR3.setRotationPoint(-2.5F, 0.4F, 0.6F);
		lowerlegR3.addBox(-4, 0, 0, 4, 3, 1, 0);
		setRotateAngle(lowerlegR3, -0.17453292519943295F, 0.6632251157578453F, -0.17453292519943295F);
		head2 = new MowzieModelRenderer(this, 14, 22);
		head2.setRotationPoint(0, -3.3F, 0.5F);
		head2.addBox(-1.5F, 0, -3, 3, 1, 3, 0);
		setRotateAngle(head2, 0.06981317007977318F, 0, 0);
		rightshoulder2 = new MowzieModelRenderer(this, 0, 62);
		rightshoulder2.setRotationPoint(-3.1F, 0, 3);
		rightshoulder2.addBox(0, 0, 0, 3, 2, 2, 0);
		setRotateAngle(rightshoulder2, -0.4886921905584123F, 0, 0);
		leftshoulder4 = new MowzieModelRenderer(this, 6, 56);
		leftshoulder4.mirror = true;
		leftshoulder4.setRotationPoint(-1.1F, -1, -2.9F);
		leftshoulder4.addBox(0.1F, 0, 0, 3, 1, 3, 0);
		setRotateAngle(leftshoulder4, 0.20943951023931953F, -0.06981317007977318F, 0);
		head6 = new MowzieModelRenderer(this, 14, 26);
		head6.mirror = true;
		head6.setRotationPoint(0, 0, -1);
		head6.addBox(-0.1F, -0.8F, -1, 2, 1, 3, 0);
		setRotateAngle(head6, 0.24434609527920614F, -0.33161255787892263F, 0);
		lowerarmR3 = new MowzieModelRenderer(this, 35, 35);
		lowerarmR3.setRotationPoint(1, 4, 1.2F);
		lowerarmR3.addBox(-1, -4, -1, 1, 4, 1, 0);
		setRotateAngle(lowerarmR3, -0.08726646259971647F, 0, 0.10471975511965977F);
		wingR3 = new MowzieModelRenderer(this, 43, 23);
		wingR3.mirror = true;
		wingR3.setRotationPoint(0, 2, 0);
		wingR3.addBox(-0.6F, -0.3F, -1, 1, 6, 2, 0);
		setRotateAngle(wingR3, 0.05235987755982988F, -0.017453292519943295F, -0.08726646259971647F);
		feetbaseR = new MowzieModelRenderer(this, 8, 88);
		feetbaseR.setRotationPoint(0, 5.1F, -0.2F);
		feetbaseR.addBox(-1, 0, -0.9F, 2, 2, 2, 0);
		setRotateAngle(feetbaseR, -0.08726646259971647F, -0.017453292519943295F, -0.017453292519943295F);
		engineR6 = new MowzieModelRenderer(this, 0, 89);
		engineR6.mirror = true;
		engineR6.setRotationPoint(0, 2, 1);
		engineR6.addBox(-1, -3, -0.4F, 2, 5, 2, 0);
		setRotateAngle(engineR6, -0.2792526803190927F, -0.3490658503988659F, 0);
		lowerlegL4 = new MowzieModelRenderer(this, 18, 80);
		lowerlegL4.mirror = true;
		lowerlegL4.setRotationPoint(0, 0, 1);
		lowerlegL4.addBox(0, -2, -1, 3, 2, 1, 0);
		setRotateAngle(lowerlegL4, 0.4886921905584123F, 0, 0);
		buttflapL = new MowzieModelRenderer(this, 9, 65);
		buttflapL.mirror = true;
		buttflapL.setRotationPoint(1, 0.2F, 1);
		buttflapL.addBox(-2, 0, 0, 3, 3, 1, 0);
		setRotateAngle(buttflapL, 0.13962634015954636F, -0.08726646259971647F, 0.10471975511965977F);
		lowerlegR1 = new MowzieModelRenderer(this, 16, 73);
		lowerlegR1.setRotationPoint(1, 0, -2);
		lowerlegR1.addBox(-3, 0, 0, 3, 5, 2, 0);
		setRotateAngle(lowerlegR1, -0.06981317007977318F, 0.3839724354387525F, 0);
		feetR3 = new MowzieModelRenderer(this, 14, 90);
		feetR3.setRotationPoint(0.5F, 0, 1.6F);
		feetR3.addBox(0, 0, 0, 1, 1, 2, 0);
		setRotateAngle(feetR3, 0, 0.4363323129985824F, 0);
		torsoR2 = new MowzieModelRenderer(this, 10, 60);
		torsoR2.setRotationPoint(-0.4F, -3.1F, -0.9F);
		torsoR2.addBox(-2, -0.7F, 0, 2, 4, 1, 0);
		setRotateAngle(torsoR2, 0.13962634015954636F, 0.20943951023931953F, -0.19198621771937624F);
		wingbaseR = new MowzieModelRenderer(this, 35, 17);
		wingbaseR.setRotationPoint(-1.6F, 0, 0);
		wingbaseR.addBox(-0.7F, -1.2F, -1.1F, 1, 2, 3, 0);
		setRotateAngle(wingbaseR, 0.17453292519943295F, -0.45378560551852565F, -0.08726646259971647F);
		engineL5 = new MowzieModelRenderer(this, 8, 80);
		engineL5.setRotationPoint(-1.5F, 1, 1.5F);
		engineL5.addBox(0, 0, -1, 1, 2, 2, 0);
		setRotateAngle(engineL5, 0, 0, -0.19198621771937624F);
		wingL3 = new MowzieModelRenderer(this, 43, 23);
		wingL3.setRotationPoint(0, 2, 0);
		wingL3.addBox(-0.4F, -0.3F, -1, 1, 6, 2, 0);
		setRotateAngle(wingL3, 0.05235987755982988F, 0.017453292519943295F, 0.08726646259971647F);
		wasit3 = new MowzieModelRenderer(this, 10, 17);
		wasit3.mirror = true;
		wasit3.setRotationPoint(0.7F, -0.4F, -1.9F);
		wasit3.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
		lowerArmR = new MowzieModelRenderer(this, 35, 29);
		lowerArmR.setRotationPoint(0, 4.1F, 0);
		lowerArmR.addBox(-1, 0, -0.8F, 2, 4, 2, 0);
		setRotateAngle(lowerArmR, -0.08726646259971647F, -0.08726646259971647F, -0.10471975511965977F);
		cockpit2 = new MowzieModelRenderer(this, 21, 40);
		cockpit2.setRotationPoint(0, -0.1F, -3);
		cockpit2.addBox(0, -0.1F, -1.7F, 1, 2, 2, 0);
		setRotateAngle(cockpit2, 0, 0, 0.08726646259971647F);
		finger3_1 = new MowzieModelRenderer(this, 35, 40);
		finger3_1.setRotationPoint(-1.2F, 0.25F, 0.4F);
		finger3_1.addBox(-0.4F, -0.2F, -0.4F, 1, 1, 1, 0);
		setRotateAngle(finger3_1, 0.17453292519943295F, 0.06981317007977318F, 1.4311699866353502F);
		finger4_1 = new MowzieModelRenderer(this, 42, 34);
		finger4_1.setRotationPoint(0, 0.8F, -0.8F);
		finger4_1.addBox(-0.5F, -0.3F, -0.2F, 1, 2, 1, 0);
		setRotateAngle(finger4_1, -0.08726646259971647F, 0, 0);
		upperLegL = new MowzieModelRenderer(this, 0, 73);
		upperLegL.mirror = true;
		upperLegL.setRotationPoint(1.5F, 1, 0);
		upperLegL.addBox(0, -0.5F, -0.5F, 1, 7, 1, 0);
		setRotateAngle(upperLegL, -0.10471975511965977F, 0, -0.03490658503988659F);
		wingstrutL3 = new MowzieModelRenderer(this, 40, 22);
		wingstrutL3.setRotationPoint(0.1F, -1.2F, 3);
		wingstrutL3.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
		setRotateAngle(wingstrutL3, 0.5759586531581287F, 0.017453292519943295F, -0.017453292519943295F);
		upperArmR = new MowzieModelRenderer(this, 40, 15);
		upperArmR.setRotationPoint(-4, 0.5F, 0.5F);
		upperArmR.addBox(-0.5F, 0.3F, -0.5F, 1, 4, 1, 0);
		setRotateAngle(upperArmR, 0, 0.10471975511965977F, 0.15707963267948966F);
		finger1_1 = new MowzieModelRenderer(this, 42, 34);
		finger1_1.setRotationPoint(0.5F, 0.8F, -0.8F);
		finger1_1.addBox(0, -0.3F, -0.2F, 1, 2, 1, 0);
		setRotateAngle(finger1_1, -0.06981317007977318F, 0, -0.13962634015954636F);
		waist4 = new MowzieModelRenderer(this, 10, 17);
		waist4.mirror = true;
		waist4.setRotationPoint(2, -0.5F, -1.7F);
		waist4.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
		setRotateAngle(waist4, 0, -0.2617993877991494F, -0.15707963267948966F);
		cockpit1 = new MowzieModelRenderer(this, 21, 48);
		cockpit1.setRotationPoint(0.5F, 0, -2);
		cockpit1.addBox(0, 0, -3, 2, 1, 3, 0);
		setRotateAngle(cockpit1, 0.6108652381980153F, 0, 0);
		neck2 = new MowzieModelRenderer(this, 16, 35);
		neck2.setRotationPoint(-0.5F, -1.2F, -2);
		neck2.addBox(0, 0, 0, 1, 2, 3, 0);
		setRotateAngle(neck2, 0, -0.33161255787892263F, 0);
		wingconnectorL = new MowzieModelRenderer(this, 44, 20);
		wingconnectorL.setRotationPoint(0.6F, 1, 0);
		wingconnectorL.addBox(-0.3F, -0.5F, -0.5F, 2, 1, 1, 0);
		setRotateAngle(wingconnectorL, -0.03490658503988659F, -0.5235987755982988F, -0.24434609527920614F);
		upperlegL2 = new MowzieModelRenderer(this, 8, 100);
		upperlegL2.mirror = true;
		upperlegL2.setRotationPoint(0.7F, 1.6F, 0);
		upperlegL2.addBox(0, 0, -1, 1, 4, 2, 0);
		head3 = new MowzieModelRenderer(this, 23, 21);
		head3.setRotationPoint(-2, -3.3F, 0.5F);
		head3.addBox(0, 0, -3, 1, 1, 3, 0);
		setRotateAngle(head3, 0.06981317007977318F, -0.15707963267948966F, -0.008726646259971648F);
		engineR4 = new MowzieModelRenderer(this, 8, 80);
		engineR4.setRotationPoint(-1.5F, 1, 1.5F);
		engineR4.addBox(0, 0, -1, 1, 2, 2, 0);
		setRotateAngle(engineR4, 0, 0, -0.19198621771937624F);
		upperlegL1 = new MowzieModelRenderer(this, 0, 102);
		upperlegL1.mirror = true;
		upperlegL1.setRotationPoint(0, 0, 0);
		upperlegL1.addBox(-0.5F, -0.2F, -1, 2, 2, 2, 0);
		setRotateAngle(upperlegL1, 0.06981317007977318F, -0.20943951023931953F, 0.06981317007977318F);
		feetR1 = new MowzieModelRenderer(this, 3, 92);
		feetR1.mirror = true;
		feetR1.setRotationPoint(0, 1.5F, 0);
		feetR1.addBox(-1.5F, 0, -3.4F, 3, 1, 5, 0);
		cockpit3 = new MowzieModelRenderer(this, 21, 40);
		cockpit3.mirror = true;
		cockpit3.setRotationPoint(2, -0.1F, -3);
		cockpit3.addBox(-1, -0.1F, -1.7F, 1, 2, 2, 0);
		setRotateAngle(cockpit3, 0, 0, -0.08726646259971647F);
		engineL4 = new MowzieModelRenderer(this, 8, 80);
		engineL4.mirror = true;
		engineL4.setRotationPoint(1.5F, 1, 1.5F);
		engineL4.addBox(-1, 0, -1, 1, 2, 2, 0);
		setRotateAngle(engineL4, 0, 0, 0.19198621771937624F);
		cockpit5 = new MowzieModelRenderer(this, 14, 47);
		cockpit5.mirror = true;
		cockpit5.setRotationPoint(3, 1, -2);
		cockpit5.addBox(-1, 0, -3, 1, 1, 5, 0);
		setRotateAngle(cockpit5, 0.2792526803190927F, 0.17453292519943295F, -0.15707963267948966F);
		waistL2 = new MowzieModelRenderer(this, 18, 62);
		waistL2.setRotationPoint(1, 0.1F, -1);
		waistL2.addBox(0, 0, -0.5F, 2, 1, 1, 0);
		setRotateAngle(waistL2, 0, -0.5585053606381855F, -0.20943951023931953F);
		leftshoulder1 = new MowzieModelRenderer(this, 0, 51);
		leftshoulder1.mirror = true;
		leftshoulder1.setRotationPoint(-1, 1, -2.9F);
		leftshoulder1.addBox(0, -0.5F, 0.1F, 3, 1, 4, 0);
		setRotateAngle(leftshoulder1, 0.006981317007977318F, 0, -0.15707963267948966F);
		finger2_1 = new MowzieModelRenderer(this, 42, 34);
		finger2_1.setRotationPoint(-0.5F, 0.8F, -0.8F);
		finger2_1.addBox(-1, -0.3F, -0.2F, 1, 2, 1, 0);
		setRotateAngle(finger2_1, -0.10471975511965977F, 0, 0.13962634015954636F);
		wingR1 = new MowzieModelRenderer(this, 35, 22);
		wingR1.setRotationPoint(0, -1.2F, -1.1F);
		wingR1.addBox(-0.7F, 0, 0, 1, 4, 3, 0);
		setRotateAngle(wingR1, 0.9773843811168246F, 0.0017453292519943296F, 0);
		feetl6 = new MowzieModelRenderer(this, 14, 93);
		feetl6.setRotationPoint(-0.9F, 0.5F, 0);
		feetl6.addBox(-0.4F, -0.1F, -0.6F, 1, 2, 2, 0);
		setRotateAngle(feetl6, 0.10471975511965977F, -0.06981317007977318F, 0.22689280275926282F);
		waist2 = new MowzieModelRenderer(this, 10, 17);
		waist2.setRotationPoint(-0.7F, -0.4F, -1.9F);
		waist2.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
		back2 = new MowzieModelRenderer(this, 23, 53);
		back2.setRotationPoint(0, 2, 1.9F);
		back2.addBox(-1.5F, 0, -2, 3, 3, 2, 0);
		setRotateAngle(back2, -0.06981317007977318F, 0, 0);
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
		setRotateAngle(upperArmL, 0, -0.10471975511965977F, -0.15707963267948966F);
		engineR3 = new MowzieModelRenderer(this, 8, 80);
		engineR3.mirror = true;
		engineR3.setRotationPoint(1.5F, 1, 1.5F);
		engineR3.addBox(-1, 0, -1, 1, 2, 2, 0);
		setRotateAngle(engineR3, 0, 0, 0.19198621771937624F);
		feetL2 = new MowzieModelRenderer(this, 14, 88);
		feetL2.setRotationPoint(-1.5F, 0, -3.4F);
		feetL2.addBox(0, 0, -1, 3, 1, 1, 0);
		setRotateAngle(feetL2, 1.0122909661567112F, 0, 0);
		waist5 = new MowzieModelRenderer(this, 10, 17);
		waist5.setRotationPoint(-2, -0.5F, -1.7F);
		waist5.addBox(-0.5F, 0, 0, 1, 2, 1, 0);
		setRotateAngle(waist5, 0, 0.2617993877991494F, 0.15707963267948966F);
		lowerlegL2 = new MowzieModelRenderer(this, 8, 84);
		lowerlegL2.mirror = true;
		lowerlegL2.setRotationPoint(2.5F, 0.4F, 0.6F);
		lowerlegL2.addBox(0, 0, 0, 4, 3, 1, 0);
		setRotateAngle(lowerlegL2, -0.17453292519943295F, -0.6632251157578453F, 0.17453292519943295F);
		neck3 = new MowzieModelRenderer(this, 16, 35);
		neck3.setRotationPoint(0.5F, -1.2F, -2);
		neck3.addBox(-1, 0, 0, 1, 2, 3, 0);
		setRotateAngle(neck3, 0, 0.33161255787892263F, 0);
		finger3_2 = new MowzieModelRenderer(this, 39, 40);
		finger3_2.setRotationPoint(-0.4F, 1, 0);
		finger3_2.addBox(0, -0.5F, -0.4F, 2, 1, 1, 0);
		setRotateAngle(finger3_2, 0, 0, -0.10471975511965977F);
		leftshoulderbase = new MowzieModelRenderer(this, 0, 46);
		leftshoulderbase.mirror = true;
		leftshoulderbase.setRotationPoint(3, 0.15F, 1.4F);
		leftshoulderbase.addBox(-1, -1, -2.9F, 3, 1, 4, 0);
		setRotateAngle(leftshoulderbase, -0.06981317007977318F, -0.17453292519943295F, -0.05235987755982988F);
		head9 = new MowzieModelRenderer(this, 24, 17);
		head9.setRotationPoint(0, -0.8F, -0.8F);
		head9.addBox(-2.2F, -1.9F, 0.1F, 1, 2, 2, 0);
		setRotateAngle(head9, 0, 0.08726646259971647F, 0);
		upperBodyBase = new MowzieModelRenderer(this, 0, 33);
		upperBodyBase.setRotationPoint(0, -6, 0);
		upperBodyBase.addBox(-2, -2, -1.5F, 4, 3, 4, 0);
		setRotateAngle(upperBodyBase, 0.45378560551852565F, 0, 0);
		wingstrutR1 = new MowzieModelRenderer(this, 40, 22);
		wingstrutR1.setRotationPoint(-0.1F, 0, -0.4F);
		wingstrutR1.addBox(-0.5F, 0, -0.5F, 1, 2, 1, 0);
		setRotateAngle(wingstrutR1, 0, 0.08726646259971647F, -0.03490658503988659F);
		wingR4 = new MowzieModelRenderer(this, 43, 23);
		wingR4.mirror = true;
		wingR4.setRotationPoint(0, 2, 0);
		wingR4.addBox(-0.6F, -0.3F, -1, 1, 6, 2, 0);
		setRotateAngle(wingR4, -0.3665191429188092F, 0.08726646259971647F, -0.15707963267948966F);
		engineL1 = new MowzieModelRenderer(this, 4, 73);
		engineL1.setRotationPoint(0, 3.9F, 0);
		engineL1.addBox(-1.5F, 0, 0, 3, 1, 3, 0);
		setRotateAngle(engineL1, 0.3490658503988659F, 0.017453292519943295F, 0.017453292519943295F);
		feetR5 = new MowzieModelRenderer(this, 14, 93);
		feetR5.mirror = true;
		feetR5.setRotationPoint(0.9F, 0.5F, 0);
		feetR5.addBox(-0.6F, -0.1F, -0.6F, 1, 2, 2, 0);
		setRotateAngle(feetR5, 0.10471975511965977F, 0.08726646259971647F, -0.22689280275926282F);
		lowerArmL = new MowzieModelRenderer(this, 35, 29);
		lowerArmL.mirror = true;
		lowerArmL.setRotationPoint(0, 4.1F, 0);
		lowerArmL.addBox(-1, 0, -0.8F, 2, 4, 2, 0);
		setRotateAngle(lowerArmL, -0.08726646259971647F, 0.08726646259971647F, 0.10471975511965977F);
		feetbaseL = new MowzieModelRenderer(this, 8, 88);
		feetbaseL.setRotationPoint(0, 5.1F, -0.2F);
		feetbaseL.addBox(-1, 0, -0.9F, 2, 2, 2, 0);
		setRotateAngle(feetbaseL, -0.08726646259971647F, 0.017453292519943295F, 0.017453292519943295F);

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

    @Override
    public Transformer getTransformer()
    {
		return TransformerManager.transformerCloudtrap;
	}

    @Override
    public ModelRenderer getWaist()
    {
		return waist;
	}

    @Override
    public ModelRenderer getRightLeg()
    {
		return upperLegR;
	}

    @Override
    public ModelRenderer getLeftLeg()
    {
		return upperLegL;
	}

    @Override
    public ModelRenderer getHead()
    {
		return head;
	}

	@Override
	public void setupOffsets(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		ModelOffset offsets = TFModelHelper.getOffsets(player);
		head.rotationPointX += offsets.headOffsetX;
		head.rotationPointY += offsets.headOffsetY;
		head.rotationPointZ += offsets.headOffsetZ;

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
	}

	@Override
	public void doActiveAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
		applyDefaultHittingAnimation(upperArmR, upperArmL, head, torsoConnector, lowerArmR, lowerArmL);

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
		
		ItemStack heldItem = player.getHeldItem();

		if (heldItem != null && heldItem.getItem() == TFItems.cloudtrapsFlamethrower && TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) == 20 && player.isUsingItem())
		{
			setRotation(upperArmR, bipedHead.rotateAngleX - pi / 2 + 0.2F, bipedHead.rotateAngleY, 0.1F);
			setRotation(lowerArmR, -0.2F, 0, 0);
		}
	}

	@Override
	public void doWalkingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
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

		if (player.isSneaking())
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

	@Override
	public void doIdleAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
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
	}

	@Override
	public void doFallingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		double motionX = player.posX - player.prevPosX;
		double motionZ = player.posZ - player.prevPosZ;

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

	@Override
	public void doPartialAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
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

	@Override
	public void doTransformationAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		ModelBiped modelBiped = TFModelHelper.modelBipedMain;
		ModelCloudtrapVehicle vehicle = (ModelCloudtrapVehicle)getTransformerModel().getVehicleModel();
		
		if (modelBiped != null)
		{
			vehicle.vehicleBody.rotateAngleX = rotationPitch / (180F / (float) Math.PI);
			vehicle.vehicleBody.rotateAngleZ = -modelBiped.bipedHead.rotateAngleY;
		}

		VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
		int landingTimer = 20;

		if (transformedPlayer != null)
		{
			landingTimer = transformedPlayer.getLandingTimer();
			float f1 = (float) landingTimer / 20;
			float f2 = 1 - f1;

			vehicle.vehicleBody.rotateAngleX = (rotationPitch / (180F / (float) Math.PI)) * f1;
			vehicle.vehicleBody.rotateAngleZ = -modelBiped.bipedHead.rotateAngleY * f1;
			vehicle.vehicleBody.setRotationPoint(0, 18 * f2, 0);
		}

		rotateTo(waist, vehicle.vehicleBody, f);
		rotateTo(upperLegL, vehicle.vehicleUpperlegLbase, f);
		rotateTo(upperLegR, vehicle.vehicleUpperlegRbase, f);

		rotateTo(cockpit1, vehicle.vehiclecockpit1, f);
		rotateTo(cockpit2, vehicle.vehiclecockpit2, f);
		rotateTo(cockpit3, vehicle.vehiclecockpit3, f);
		rotateTo(cockpit4, vehicle.vehiclecockpit4, f);
		rotateTo(cockpit5, vehicle.vehiclecockpit5, f);

		rotateTo(engineL1, vehicle.vehicleengineL1, f);
		rotateTo(engineL2, vehicle.vehicleengineL2, f);
		rotateTo(engineL3, vehicle.vehicleengineL3, f);
		rotateTo(engineL4, vehicle.vehicleengineL4, f);
		rotateTo(engineL5, vehicle.vehicleengineL5, f);

		rotateTo(engineR1, vehicle.vehicleengineR1, f);
		rotateTo(engineR2, vehicle.vehicleengineR2, f);
		rotateTo(engineR3, vehicle.vehicleengineR3, f);
		rotateTo(engineR4, vehicle.vehicleengineR4, f);
		rotateTo(engineR5, vehicle.vehicleengineR5, f);

		rotateTo(feetbaseL, vehicle.vehiclefeetbaseL, f);
		rotateTo(feetbaseR, vehicle.vehiclefeetbaseR, f);

		rotateTo(feetL1, vehicle.vehiclefeetL1, f);
		rotateTo(feetL3, vehicle.vehiclefeetL3, f);
		rotateTo(feetL4, vehicle.vehiclefeetL4, f);

		rotateTo(feetR1, vehicle.vehiclefeetR1, f);
		rotateTo(feetR3, vehicle.vehiclefeetR3, f);
		rotateTo(feetR4, vehicle.vehiclefeetR4, f);

		rotateTo(leftshoulder1, vehicle.vehicleleftshoulder1, f);
		rotateTo(leftshoulder3, vehicle.vehicleleftshoulder3, f);
		rotateTo(leftshoulder4, vehicle.vehicleleftshoulder4, f);
		rotateTo(leftshoulder5, vehicle.vehicleleftshoulder5, f);

		rotateTo(rightshoulder1, vehicle.vehiclerightshoulder1, f);
		rotateTo(rightshoulder2, vehicle.vehiclerightshoulder2, f);
		rotateTo(rightshoulder3, vehicle.vehiclerightshoulder3, f);
		rotateTo(rightshoulder5, vehicle.vehiclerightshoulder5, f);

		rotateTo(leftshoulderbase, vehicle.vehicleleftshoulderbase, f);
		rotateTo(rightshoulderbase, vehicle.vehiclerightshoulderbase, f);

		rotateTo(lowerArmL, vehicle.vehiclelowerarmbaseL, f);
		rotateTo(lowerArmR, vehicle.vehiclelowerarmbaseR, f);

		rotateTo(lowerarmL1, vehicle.vehiclelowerarmL1, f);
		rotateTo(lowerarmL2, vehicle.vehiclelowerarmL2, f);
		rotateTo(lowerarmL3, vehicle.vehiclelowerarmL3, f);

		rotateTo(lowerarmR1, vehicle.vehiclelowerarmR1, f);
		rotateTo(lowerarmR2, vehicle.vehiclelowerarmR2, f);
		rotateTo(lowerarmR3, vehicle.vehiclelowerarmR3, f);

		rotateTo(lowerlegL1, vehicle.vehiclelowerlegL1, f);
		rotateTo(lowerlegL2, vehicle.vehiclelowerlegL2, f);
		rotateTo(lowerlegL3, vehicle.vehiclelowerlegL3, f);
		rotateTo(lowerlegL4, vehicle.vehiclelowerlegL4, f);
		rotateTo(lowerlegL5, vehicle.vehiclelowerlegL5, f);

		rotateTo(lowerlegR1, vehicle.vehiclelowerlegR1, f);
		rotateTo(lowerlegR2, vehicle.vehiclelowerlegR2, f);
		rotateTo(lowerlegR3, vehicle.vehiclelowerlegR3, f);
		rotateTo(lowerlegR4, vehicle.vehiclelowerlegR4, f);
		rotateTo(lowerlegR5, vehicle.vehiclelowerlegR5, f);

		rotateTo(lowerlegLbase, vehicle.vehiclelowerlegLbase, f);
		rotateTo(lowerlegRbase, vehicle.vehiclelowerlegRbase, f);

		rotateTo(shoulderbase, vehicle.vehicleshoulderbase, f);

		rotateTo(shoulderpieceL1, vehicle.vehicleshoulderpieceL1, f);
		rotateTo(shoulderpieceR1, vehicle.vehicleshoulderpieceR1, f);

		rotateTo(torsoConnector, vehicle.vehicleTorsoConnector, f);

		rotateTo(torsoL2, vehicle.vehicletorsoL2, f);
		rotateTo(torsoR2, vehicle.vehicleTorsoR2, f);

		rotateTo(upperArmL, vehicle.vehicleupperarmbaseL, f);
		rotateTo(upperArmR, vehicle.vehicleupperarmbaseR, f);

		rotateTo(upperBodyBase, vehicle.vehicleUpperBodyBase, f);
		rotateTo(upperlegL2, vehicle.vehicleupperlegL2, f);
		rotateTo(upperlegL3, vehicle.vehicleupperlegL3, f);
		rotateTo(upperlegR2, vehicle.vehicleupperlegR2, f);
		rotateTo(upperlegR3, vehicle.vehicleupperlegR3, f);

		rotateTo(upperLegL, vehicle.vehicleUpperlegLbase, f);
		rotateTo(upperLegR, vehicle.vehicleUpperlegRbase, f);

		rotateTo(waist1, vehicle.vehicleWaist1, f);
		rotateTo(waist2, vehicle.vehiclewaist2, f);
		rotateTo(wasit3, vehicle.vehiclewaist3, f);
		rotateTo(waist4, vehicle.vehicleWaist4, f);
		rotateTo(waist5, vehicle.vehicleWaist5, f);

		rotateTo(wingbaseL, vehicle.vehiclewingbaseL, f);
		rotateTo(wingbaseR, vehicle.vehiclewingbaseR, f);

		rotateTo(wingconnectorL, vehicle.vehiclewingconnectorL, f);
		rotateTo(wingconnectorR, vehicle.vehiclewingconnectorR, f);

		rotateTo(wingL1, vehicle.vehiclewingL1, f);
		rotateTo(wingL2, vehicle.vehiclewingL2, f);
		rotateTo(wingL3, vehicle.vehiclewingL3, f);
		rotateTo(wingL4, vehicle.vehiclewingL4, f);

		rotateTo(wingR1, vehicle.vehiclewingR1, f);
		rotateTo(wingR2, vehicle.vehiclewingR2, f);
		rotateTo(wingR3, vehicle.vehiclewingR3, f);
		rotateTo(wingR4, vehicle.vehiclewingR4, f);

		rotateTo(wingstrutL1, vehicle.vehiclewingstrutL1, f);
		rotateTo(wingstrutL2, vehicle.vehiclewingstrutL2, f);
		rotateTo(wingstrutL3, vehicle.vehiclewingstrutL3, f);

		rotateTo(wingstrutR1, vehicle.vehiclewingstrutR1, f);
		rotateTo(wingstrutR2, vehicle.vehiclewingstrutR2, f);
		rotateTo(wingstrutR3, vehicle.vehiclewingstrutR3, f);

		head.rotationPointY += f * 0.125F;

		fistLbase.rotationPointY -= f * 0.25F;
		fistRbase.rotationPointY -= f * 0.25F;

		rotateTo(chestcockpitbase, vehicle.vehiclechestcockpitbase, f);
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
}
