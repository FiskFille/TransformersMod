package fiskfille.tf.client.model.transformer;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.model.AnimationModifier;
import fiskfille.tf.client.model.AnimationModifier.Predicate;
import fiskfille.tf.client.model.AnimationModifier.Type;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class ModelPurge extends ModelTransformerBaseNew
{
	public MowzieModelRenderer waist;
	public MowzieModelRenderer torsoconnector;
	public MowzieModelRenderer upperLegR;
	public MowzieModelRenderer upperLegL;
	public MowzieModelRenderer waistL1;
	public MowzieModelRenderer waistR1;
	public MowzieModelRenderer crotch1;
	public MowzieModelRenderer waistL3;
	public MowzieModelRenderer waistR3;
	public MowzieModelRenderer torsobase;
	public MowzieModelRenderer stomach1;
	public MowzieModelRenderer bodyparts1;
	public MowzieModelRenderer bodyparts2;
	public MowzieModelRenderer bodyparts3;
	public MowzieModelRenderer bodyparts4;
	public MowzieModelRenderer trackbase1;
	public MowzieModelRenderer trackconnectorR1;
	public MowzieModelRenderer trackconnectorL1;
	public MowzieModelRenderer torsobaseL;
	public MowzieModelRenderer torsobaseR;
	public MowzieModelRenderer turretconnector;
	public MowzieModelRenderer neck1;
	public MowzieModelRenderer chestplateL1;
	public MowzieModelRenderer chestplateR1;
	public MowzieModelRenderer chestplate1;
	public MowzieModelRenderer upperArmL;
	public MowzieModelRenderer shoulderL1;
	public MowzieModelRenderer lowerArmL;
	public MowzieModelRenderer upperarmL2;
	public MowzieModelRenderer fistL;
	public MowzieModelRenderer lowerarmL2;
	public MowzieModelRenderer lowerarmL3;
	public MowzieModelRenderer lowerarmL4;
	public MowzieModelRenderer lowerarmL5;
	public MowzieModelRenderer shoulderL2;
	public MowzieModelRenderer shoulderL3;
	public MowzieModelRenderer shoulderL8;
	public MowzieModelRenderer shoulderL4;
	public MowzieModelRenderer shoulderL6;
	public MowzieModelRenderer shoulderL5;
	public MowzieModelRenderer shoulderL7;
	public MowzieModelRenderer upperArmR;
	public MowzieModelRenderer shoulderR1;
	public MowzieModelRenderer lowerArmR;
	public MowzieModelRenderer upperarmR2;
	public MowzieModelRenderer fistR;
	public MowzieModelRenderer lowerarmR2;
	public MowzieModelRenderer lowerarmR3;
	public MowzieModelRenderer lowerarmR5;
	public MowzieModelRenderer lowerarmR4;
	public MowzieModelRenderer shoulderR2;
	public MowzieModelRenderer shoulderR3;
	public MowzieModelRenderer shoulderR8;
	public MowzieModelRenderer shoulderR4;
	public MowzieModelRenderer shoulderR6;
	public MowzieModelRenderer shoulderR5;
	public MowzieModelRenderer shoulderR7;
	public MowzieModelRenderer turretbase;
	public MowzieModelRenderer turret1;
	public MowzieModelRenderer turret5;
	public MowzieModelRenderer turret11;
	public MowzieModelRenderer turret12;
	public MowzieModelRenderer turret2;
	public MowzieModelRenderer turret3;
	public MowzieModelRenderer turret4;
	public MowzieModelRenderer barrelbase1;
	public MowzieModelRenderer barrelbase2;
	public MowzieModelRenderer barrelbase3;
	public MowzieModelRenderer barrelbase4;
	public MowzieModelRenderer barrelbase5;
	public MowzieModelRenderer barrel1;
	public MowzieModelRenderer barrel2;
	public MowzieModelRenderer turret6;
	public MowzieModelRenderer turret7;
	public MowzieModelRenderer turret8;
	public MowzieModelRenderer turret9;
	public MowzieModelRenderer turret10;
	public MowzieModelRenderer headbase;
	public MowzieModelRenderer headback1;
	public MowzieModelRenderer headL1;
	public MowzieModelRenderer headR1;
	public MowzieModelRenderer headtop1;
	public MowzieModelRenderer headchin1;
	public MowzieModelRenderer headL2;
	public MowzieModelRenderer headR2;
	public MowzieModelRenderer headcrest1;
	public MowzieModelRenderer headcrest2;
	public MowzieModelRenderer headcrest3;
	public MowzieModelRenderer headcrest4;
	public MowzieModelRenderer chestplateL2;
	public MowzieModelRenderer chestplateL3;
	public MowzieModelRenderer chestplateR2;
	public MowzieModelRenderer chestplateR3;
	public MowzieModelRenderer trackconnectorR2;
	public MowzieModelRenderer trackconnectorR3;
	public MowzieModelRenderer trackconnectorR4;
	public MowzieModelRenderer wheelR3;
	public MowzieModelRenderer wheelR4;
	public MowzieModelRenderer wheelR5;
	public MowzieModelRenderer trackR1;
	public MowzieModelRenderer trackR6;
	public MowzieModelRenderer trackconnectorR5;
	public MowzieModelRenderer wheelR2;
	public MowzieModelRenderer wheelR1;
	public MowzieModelRenderer trackR2;
	public MowzieModelRenderer trackR5;
	public MowzieModelRenderer trackR3;
	public MowzieModelRenderer trackR4;
	public MowzieModelRenderer trackR7;
	public MowzieModelRenderer trackR8;
	public MowzieModelRenderer trackconnectorL2;
	public MowzieModelRenderer trackconnectorL3;
	public MowzieModelRenderer trackconnectorL4;
	public MowzieModelRenderer wheelL3;
	public MowzieModelRenderer wheelL4;
	public MowzieModelRenderer wheelL5;
	public MowzieModelRenderer trackL1;
	public MowzieModelRenderer trackL5;
	public MowzieModelRenderer trackcnnectorL5;
	public MowzieModelRenderer wheelL2;
	public MowzieModelRenderer wheelL1;
	public MowzieModelRenderer trackL2;
	public MowzieModelRenderer trackL3;
	public MowzieModelRenderer trackL4;
	public MowzieModelRenderer trackL6;
	public MowzieModelRenderer trackL8;
	public MowzieModelRenderer trackL7;
	public MowzieModelRenderer lowerlegR1;
	public MowzieModelRenderer upperlegR2;
	public MowzieModelRenderer feetbaseR1;
	public MowzieModelRenderer lowerlegR2;
	public MowzieModelRenderer feetR2;
	public MowzieModelRenderer feetR5;
	public MowzieModelRenderer feetR3;
	public MowzieModelRenderer feetR4;
	public MowzieModelRenderer lowerlegR3;
	public MowzieModelRenderer lowerlegR8;
	public MowzieModelRenderer lowerlegR10;
	public MowzieModelRenderer lowerlegR11;
	public MowzieModelRenderer lowerlegR13;
	public MowzieModelRenderer lowerlegR4;
	public MowzieModelRenderer lowerlegR5;
	public MowzieModelRenderer lowerlegR6;
	public MowzieModelRenderer lowerlegR7;
	public MowzieModelRenderer lowerlegR9;
	public MowzieModelRenderer lowerlegR12;
	public MowzieModelRenderer lowerlegL1;
	public MowzieModelRenderer upperlegL2;
	public MowzieModelRenderer feetbaseL1;
	public MowzieModelRenderer lowerlegL2;
	public MowzieModelRenderer feetL2;
	public MowzieModelRenderer feetL5;
	public MowzieModelRenderer feetL3;
	public MowzieModelRenderer feetL4;
	public MowzieModelRenderer lowerlegL3;
	public MowzieModelRenderer lowerlegL8;
	public MowzieModelRenderer lowerlegL10;
	public MowzieModelRenderer lowerlegL11;
	public MowzieModelRenderer lowerlegL13;
	public MowzieModelRenderer lowerlegL4;
	public MowzieModelRenderer lowerlegL5;
	public MowzieModelRenderer lowerlegL6;
	public MowzieModelRenderer lowerlegL7;
	public MowzieModelRenderer lowerlegL9;
	public MowzieModelRenderer lowerlegL12;
	public MowzieModelRenderer waistL2;
	public MowzieModelRenderer skirtL1;
	public MowzieModelRenderer skirtL2;
	public MowzieModelRenderer waistR2;
	public MowzieModelRenderer skirtR1;
	public MowzieModelRenderer skirtR2;
	public MowzieModelRenderer crotch2;
	public MowzieModelRenderer crotch3;
	public MowzieModelRenderer boxL1;
	public MowzieModelRenderer boxL2;
	public MowzieModelRenderer boxL3;
	public MowzieModelRenderer boxR1;
	public MowzieModelRenderer boxR2;
	public MowzieModelRenderer boxR3;

	public ModelPurge()
	{
		super(1, 0.8F, new AnimationModifier(Type.DEGREE, Predicate.BACKING, 0.5F));
		textureWidth = 128;
		textureHeight = 128;
		upperarmR2 = new MowzieModelRenderer(this, 27, 119);
		upperarmR2.mirror = true;
		upperarmR2.setRotationPoint(0, -1, -0.5F);
		upperarmR2.addBox(-1, 0, 0, 1, 5, 1, 0);
		wheelR2 = new MowzieModelRenderer(this, 11, 96);
		wheelR2.mirror = true;
		wheelR2.setRotationPoint(0.5F, -1.5F, 0);
		wheelR2.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		lowerArmL = new MowzieModelRenderer(this, 9, 114);
		lowerArmL.setRotationPoint(-0.1F, 4.6F, -0.2F);
		lowerArmL.addBox(-0.9F, -1, -1, 2, 5, 3, 0);
		setRotateAngle(lowerArmL, -0.2617993877991494F, 0.08726646259971647F, 0.08726646259971647F);
		lowerlegL1 = new MowzieModelRenderer(this, 87, 60);
		lowerlegL1.mirror = true;
		lowerlegL1.setRotationPoint(0, 4.6F, -0.8F);
		lowerlegL1.addBox(-1, -0.3F, 0, 2, 6, 3, 0);
		setRotateAngle(lowerlegL1, 0.17453292519943295F, 0, 0.08726646259971647F);
		feetbaseL1 = new MowzieModelRenderer(this, 89, 70);
		feetbaseL1.mirror = true;
		feetbaseL1.setRotationPoint(0.2F, 6, 1);
		feetbaseL1.addBox(-1, -1, -1.5F, 2, 2, 3, 0);
		setRotateAngle(feetbaseL1, -0.08726646259971647F, -0.08726646259971647F, 0);
		trackL4 = new MowzieModelRenderer(this, 8, 109);
		trackL4.setRotationPoint(-2.25F, -3.25F, 0);
		trackL4.addBox(0, 0, 0, 3, 1, 3, 0);
		setRotateAngle(trackL4, 0, 0.017453292519943295F, 0);
		skirtL1 = new MowzieModelRenderer(this, 80, 50);
		skirtL1.setRotationPoint(1.5F, 0.2F, -0.5F);
		skirtL1.addBox(0, 0, 0, 1, 6, 3, 0);
		setRotateAngle(skirtL1, 0, -0.05235987755982988F, -0.10471975511965977F);
		skirtL2 = new MowzieModelRenderer(this, 90, 55);
		skirtL2.setRotationPoint(0, 3, 3);
		skirtL2.addBox(0, 0, 0, 1, 3, 1, 0);
		trackR7 = new MowzieModelRenderer(this, 9, 100);
		trackR7.setRotationPoint(-1, 0, 0);
		trackR7.addBox(0, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackR7, 0, 0, 0.5235987755982988F);
		turretbase = new MowzieModelRenderer(this, 16, 77);
		turretbase.setRotationPoint(0, 1, 1);
		turretbase.addBox(-3, -1, 0, 6, 3, 3, 0);
		feetR2 = new MowzieModelRenderer(this, 87, 76);
		feetR2.setRotationPoint(-1.5F, 0, -2.5F);
		feetR2.addBox(0, 0, 3, 3, 2, 2, 0);
		crotch3 = new MowzieModelRenderer(this, 71, 78);
		crotch3.setRotationPoint(0, 0, 0);
		crotch3.addBox(0, -2, 0, 2, 2, 1, 0);
		setRotateAngle(crotch3, -0.17453292519943295F, 0, 0);
		shoulderL7 = new MowzieModelRenderer(this, 24, 64);
		shoulderL7.setRotationPoint(0.5F, 0, 0);
		shoulderL7.addBox(0, 0, -1, 2, 1, 1, 0);
		setRotateAngle(shoulderL7, 0.3490658503988659F, 0, 0);
		barrel1 = new MowzieModelRenderer(this, 40, 87);
		barrel1.setRotationPoint(0, -3.4F, 0);
		barrel1.addBox(-0.5F, 0, -0.5F, 1, 8, 1, 0);
		shoulderR4 = new MowzieModelRenderer(this, 34, 53);
		shoulderR4.mirror = true;
		shoulderR4.setRotationPoint(-2, 1, 0);
		shoulderR4.addBox(-1, 0, 0, 1, 4, 5, 0);
		turret10 = new MowzieModelRenderer(this, 27, 104);
		turret10.setRotationPoint(-2, 0, 0);
		turret10.addBox(-1, 0, 0, 1, 2, 2, 0);
		setRotateAngle(turret10, 0, 0.017453292519943295F, -0.2617993877991494F);
		headbase = new MowzieModelRenderer(this, 3, 52);
		headbase.setRotationPoint(0, -0.5F, 0);
		headbase.addBox(-1.5F, -3, -1.5F, 3, 3, 3, 0);
		lowerlegR7 = new MowzieModelRenderer(this, 94, 85);
		lowerlegR7.setRotationPoint(0, 2, -0.4F);
		lowerlegR7.addBox(-2, 0, -2, 2, 2, 2, 0);
		setRotateAngle(lowerlegR7, -0.17453292519943295F, -0.017453292519943295F, 0);
		headchin1 = new MowzieModelRenderer(this, 10, 77);
		headchin1.setRotationPoint(-1, -0.9F, -1.6F);
		headchin1.addBox(0, 0, 0, 2, 1, 1, 0);
		setRotateAngle(headchin1, -0.17453292519943295F, 0, 0);
		boxR2 = new MowzieModelRenderer(this, 2, 82);
		boxR2.setRotationPoint(0, -0.9F, 0.2F);
		boxR2.addBox(-3, 0, 0, 3, 1, 2, 0);
		setRotateAngle(boxR2, -0.32637657012293964F, 0, 0);
		trackL6 = new MowzieModelRenderer(this, 9, 100);
		trackL6.mirror = true;
		trackL6.setRotationPoint(1, 0, 0);
		trackL6.addBox(-1, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackL6, 0, 0, -0.5235987755982988F);
		torsobaseL = new MowzieModelRenderer(this, 52, 58);
		torsobaseL.setRotationPoint(2.5F, -1, 0);
		torsobaseL.addBox(0, -3, -1.5F, 1, 3, 3, 0);
		trackconnectorR5 = new MowzieModelRenderer(this, 20, 96);
		trackconnectorR5.mirror = true;
		trackconnectorR5.setRotationPoint(1, -3, 0);
		trackconnectorR5.addBox(-1, -2, 0, 1, 2, 1, 0);
		setRotateAngle(trackconnectorR5, -0.017453292519943295F, 0, -0.5235987755982988F);
		chestplate1 = new MowzieModelRenderer(this, 54, 65);
		chestplate1.setRotationPoint(-1, -2.3F, -3);
		chestplate1.addBox(0, 0, 0, 2, 2, 2, 0);
		setRotateAngle(chestplate1, 0.3141592653589793F, 0, 0);
		lowerlegR11 = new MowzieModelRenderer(this, 69, 82);
		lowerlegR11.setRotationPoint(-0.5F, 2.5F, -0.5F);
		lowerlegR11.addBox(-2, 0, 0, 2, 3, 1, 0);
		feetL3 = new MowzieModelRenderer(this, 90, 81);
		feetL3.mirror = true;
		feetL3.setRotationPoint(0.5F, 1.1F, -1.4F);
		feetL3.addBox(0, 0, 0, 2, 1, 3, 0);
		setRotateAngle(feetL3, 0.41887902047863906F, 0, 0.017453292519943295F);
		skirtR2 = new MowzieModelRenderer(this, 90, 55);
		skirtR2.mirror = true;
		skirtR2.setRotationPoint(0, 3, 3);
		skirtR2.addBox(-1, 0, 0, 1, 3, 1, 0);
		torsobaseR = new MowzieModelRenderer(this, 52, 58);
		torsobaseR.mirror = true;
		torsobaseR.setRotationPoint(-2.5F, -1, 0);
		torsobaseR.addBox(-1, -3, -1.5F, 1, 3, 3, 0);
		feetbaseR1 = new MowzieModelRenderer(this, 89, 70);
		feetbaseR1.setRotationPoint(-0.2F, 6, 1);
		feetbaseR1.addBox(-1, -1, -1.5F, 2, 2, 3, 0);
		setRotateAngle(feetbaseR1, -0.08726646259971647F, 0.08726646259971647F, 0);
		lowerlegL3 = new MowzieModelRenderer(this, 80, 88);
		lowerlegL3.mirror = true;
		lowerlegL3.setRotationPoint(2, 1, 1);
		lowerlegL3.addBox(0, 0, 0, 1, 6, 3, 0);
		setRotateAngle(lowerlegL3, -0.19198621771937624F, 0.017453292519943295F, 0);
		upperLegR = new MowzieModelRenderer(this, 80, 68);
		upperLegR.setRotationPoint(-2, 2, 0);
		upperLegR.addBox(-1, -1.5F, -1, 2, 6, 2, 0);
		setRotateAngle(upperLegR, -0.08726646259971647F, 0, 0.08726646259971647F);
		lowerArmR = new MowzieModelRenderer(this, 9, 114);
		lowerArmR.mirror = true;
		lowerArmR.setRotationPoint(0.1F, 4.6F, -0.2F);
		lowerArmR.addBox(-1.1F, -1, -1, 2, 5, 3, 0);
		setRotateAngle(lowerArmR, -0.2617993877991494F, -0.08726646259971647F, -0.08726646259971647F);
		shoulderR3 = new MowzieModelRenderer(this, 25, 63);
		shoulderR3.mirror = true;
		shoulderR3.setRotationPoint(-0.5F, -1.5F, -2);
		shoulderR3.addBox(-3, 0, 0, 4, 1, 5, 0);
		turret3 = new MowzieModelRenderer(this, 35, 77);
		turret3.setRotationPoint(-2.3F, 2.5F, 0);
		turret3.addBox(-2, -2, 0, 2, 2, 3, 0);
		setRotateAngle(turret3, 0, 0, 1.1990411961201044F);
		upperLegL = new MowzieModelRenderer(this, 80, 68);
		upperLegL.mirror = true;
		upperLegL.setRotationPoint(2, 2, 0);
		upperLegL.addBox(-1, -1.5F, -1, 2, 6, 2, 0);
		setRotateAngle(upperLegL, -0.08726646259971647F, 0, -0.08726646259971647F);
		chestplateR2 = new MowzieModelRenderer(this, 71, 58);
		chestplateR2.mirror = true;
		chestplateR2.setRotationPoint(0, 0.5F, 0);
		chestplateR2.addBox(-3, -2, 0, 3, 2, 1, 0);
		setRotateAngle(chestplateR2, -0.4363323129985824F, -0.017453292519943295F, 0);
		trackconnectorR4 = new MowzieModelRenderer(this, 24, 96);
		trackconnectorR4.mirror = true;
		trackconnectorR4.setRotationPoint(-1, -6, 0);
		trackconnectorR4.addBox(0, -3, 0, 1, 3, 1, 0);
		setRotateAngle(trackconnectorR4, 0, 0, 0.5235987755982988F);
		lowerlegR5 = new MowzieModelRenderer(this, 94, 85);
		lowerlegR5.setRotationPoint(1.8F, 0.6F, -1.8F);
		lowerlegR5.addBox(-2, 0, 0, 2, 2, 2, 0);
		setRotateAngle(lowerlegR5, 0.40142572795869574F, 0.03490658503988659F, 0);
		lowerlegL2 = new MowzieModelRenderer(this, 71, 88);
		lowerlegL2.mirror = true;
		lowerlegL2.setRotationPoint(-1.2F, -1, -1);
		lowerlegL2.addBox(0, 0, 0, 3, 6, 1, 0);
		shoulderL5 = new MowzieModelRenderer(this, 39, 63);
		shoulderL5.setRotationPoint(0.2F, 2.5F, 0.6F);
		shoulderL5.addBox(0, 0, 0, 1, 1, 4, 0);
		setRotateAngle(shoulderL5, -0.03490658503988659F, 0, 0);
		trackL3 = new MowzieModelRenderer(this, 9, 100);
		trackL3.setRotationPoint(-1, -3, 0);
		trackL3.addBox(0, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackL3, -0.03490658503988659F, -0.017453292519943295F, 0.5235987755982988F);
		skirtR1 = new MowzieModelRenderer(this, 80, 50);
		skirtR1.mirror = true;
		skirtR1.setRotationPoint(-1.5F, 0.2F, -0.5F);
		skirtR1.addBox(-1, 0, 0, 1, 6, 3, 0);
		setRotateAngle(skirtR1, 0, 0.05235987755982988F, 0.10471975511965977F);
		lowerlegL9 = new MowzieModelRenderer(this, 73, 84);
		lowerlegL9.mirror = true;
		lowerlegL9.setRotationPoint(0, -1, 0);
		lowerlegL9.addBox(0, 0, 0, 1, 1, 3, 0);
		setRotateAngle(lowerlegL9, -0.3490658503988659F, 0.017453292519943295F, 0);
		upperArmL = new MowzieModelRenderer(this, 24, 111);
		upperArmL.setRotationPoint(2, -1.5F, 0);
		upperArmL.addBox(-1, -1, -1, 1, 5, 2, 0);
		setRotateAngle(upperArmL, 0.08726646259971647F, 0, -0.17453292519943295F);
		trackcnnectorL5 = new MowzieModelRenderer(this, 20, 96);
		trackcnnectorL5.setRotationPoint(-1, -3, 0);
		trackcnnectorL5.addBox(0, -2, 0, 1, 2, 1, 0);
		setRotateAngle(trackcnnectorL5, -0.017453292519943295F, 0, 0.5235987755982988F);
		turret11 = new MowzieModelRenderer(this, 33, 103);
		turret11.setRotationPoint(-2.6F, -1.1F, 2.7F);
		turret11.addBox(0, 0, 0, 2, 2, 1, 0);
		fistL = new MowzieModelRenderer(this, 0, 115);
		fistL.setRotationPoint(0.1F, 3.6F, 0.2F);
		fistL.addBox(-1, 0, -1, 2, 2, 2, 0);
		setRotateAngle(fistL, -0.08726646259971647F, 0, 0.20943951023931953F);
		turret9 = new MowzieModelRenderer(this, 27, 104);
		turret9.setRotationPoint(2, 0, 0);
		turret9.addBox(0, 0, 0, 1, 2, 2, 0);
		setRotateAngle(turret9, 0, -0.017453292519943295F, 0.2617993877991494F);
		shoulderL1 = new MowzieModelRenderer(this, 25, 50);
		shoulderL1.setRotationPoint(1, -2.5F, 0.5F);
		shoulderL1.addBox(0, -0.5F, -2.5F, 1, 3, 4, 0);
		trackR4 = new MowzieModelRenderer(this, 8, 109);
		trackR4.mirror = true;
		trackR4.setRotationPoint(2.25F, -3.25F, 0);
		trackR4.addBox(-3, 0, 0, 3, 1, 3, 0);
		setRotateAngle(trackR4, 0, -0.017453292519943295F, 0);
		upperlegR2 = new MowzieModelRenderer(this, 82, 62);
		upperlegR2.setRotationPoint(-0.5F, 0, 0.9F);
		upperlegR2.addBox(0, 0, 0, 1, 4, 1, 0);
		shoulderL4 = new MowzieModelRenderer(this, 34, 53);
		shoulderL4.setRotationPoint(2, 1, 0);
		shoulderL4.addBox(0, 0, 0, 1, 4, 5, 0);
		lowerarmL3 = new MowzieModelRenderer(this, 6, 114);
		lowerarmL3.setRotationPoint(-0.9F, -1, 2);
		lowerarmL3.addBox(0, -2, -1, 2, 2, 1, 0);
		setRotateAngle(lowerarmL3, 0.17453292519943295F, 0, 0);
		wheelR5 = new MowzieModelRenderer(this, 11, 96);
		wheelR5.mirror = true;
		wheelR5.setRotationPoint(-0.5F, -4.5F, 0);
		wheelR5.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		turret8 = new MowzieModelRenderer(this, 27, 99);
		turret8.setRotationPoint(0, -1.3F, 0.9F);
		turret8.addBox(-2, 0, 0, 4, 2, 2, 0);
		lowerlegL8 = new MowzieModelRenderer(this, 62, 89);
		lowerlegL8.mirror = true;
		lowerlegL8.setRotationPoint(-0.2F, 2.1F, 1);
		lowerlegL8.addBox(0, 0, 0, 1, 4, 3, 0);
		boxR1 = new MowzieModelRenderer(this, 2, 86);
		boxR1.mirror = true;
		boxR1.setRotationPoint(-0.7F, -0.1F, 0);
		boxR1.addBox(-3, 0, 0, 3, 3, 2, 0);
		boxL3 = new MowzieModelRenderer(this, 10, 86);
		boxL3.mirror = true;
		boxL3.setRotationPoint(1, -0.2F, 1.2F);
		boxL3.addBox(0, 0, 0, 1, 1, 1, 0);
		setRotateAngle(boxL3, 0.06806784082777885F, 0, 0);
		feetR4 = new MowzieModelRenderer(this, 80, 77);
		feetR4.setRotationPoint(-0.3F, -1, 2.5F);
		feetR4.addBox(-1, -1, 0, 1, 3, 2, 0);
		setRotateAngle(feetR4, -1.0471975511965976F, 0, 0);
		bodyparts2 = new MowzieModelRenderer(this, 63, 66);
		bodyparts2.mirror = true;
		bodyparts2.setRotationPoint(-1, -4.8F, 2);
		bodyparts2.addBox(-1, 0, -1, 1, 5, 1, 0);
		setRotateAngle(bodyparts2, -0.12217304763960307F, 0, -0.10471975511965977F);
		headcrest1 = new MowzieModelRenderer(this, 3, 71);
		headcrest1.setRotationPoint(0, 0.9F, -1.5F);
		headcrest1.addBox(-0.5F, -3, -0.5F, 1, 3, 1, 0);
		setRotateAngle(headcrest1, 0.08726646259971647F, 0, 0);
		headR1 = new MowzieModelRenderer(this, 3, 64);
		headR1.mirror = true;
		headR1.setRotationPoint(-1, -2.7F, -1.4F);
		headR1.addBox(-1, 0, 0, 1, 3, 3, 0);
		setRotateAngle(headR1, 0, 0, 0.13962634015954636F);
		boxL1 = new MowzieModelRenderer(this, 2, 86);
		boxL1.setRotationPoint(0.7F, -0.1F, 0);
		boxL1.addBox(0, 0, 0, 3, 3, 2, 0);
		wheelR1 = new MowzieModelRenderer(this, 11, 96);
		wheelR1.mirror = true;
		wheelR1.setRotationPoint(-0.5F, -1.5F, 0);
		wheelR1.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		wheelL5 = new MowzieModelRenderer(this, 11, 96);
		wheelL5.setRotationPoint(0.5F, -4.5F, 0);
		wheelL5.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		wheelL2 = new MowzieModelRenderer(this, 11, 96);
		wheelL2.setRotationPoint(-0.5F, -1.5F, 0);
		wheelL2.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		waistR2 = new MowzieModelRenderer(this, 56, 77);
		waistR2.mirror = true;
		waistR2.setRotationPoint(0, 0, -1);
		waistR2.addBox(-2, 0, 0, 2, 2, 1, 0);
		setRotateAngle(waistR2, 0, 0.15184364492350666F, 0);
		trackbase1 = new MowzieModelRenderer(this, 63, 74);
		trackbase1.setRotationPoint(1.5F, -2, 0);
		trackbase1.addBox(-4, -2, 0, 5, 1, 2, 0);
		setRotateAngle(trackbase1, -0.06981317007977318F, 0, 0);
		bodyparts3 = new MowzieModelRenderer(this, 68, 69);
		bodyparts3.setRotationPoint(1.2F, -2, -0.2F);
		bodyparts3.addBox(0, 0, 0, 1, 2, 2, 0);
		setRotateAngle(bodyparts3, -0.15707963267948966F, 0, 0);
		wheelR4 = new MowzieModelRenderer(this, 11, 96);
		wheelR4.mirror = true;
		wheelR4.setRotationPoint(0.5F, -2, 0);
		wheelR4.addBox(-2, -1, -1.5F, 2, 2, 2, 0);
		chestplateR1 = new MowzieModelRenderer(this, 61, 59);
		chestplateR1.mirror = true;
		chestplateR1.setRotationPoint(-0.2F, -2.8F, -2.7F);
		chestplateR1.addBox(-3, 0, -1, 3, 3, 3, 0);
		setRotateAngle(chestplateR1, 0.3141592653589793F, 0.13962634015954636F, 0);
		lowerlegL6 = new MowzieModelRenderer(this, 94, 85);
		lowerlegL6.mirror = true;
		lowerlegL6.setRotationPoint(0, 2, 1.6F);
		lowerlegL6.addBox(0, 0, -2, 2, 2, 2, 0);
		setRotateAngle(lowerlegL6, -0.22689280275926282F, 0.017453292519943295F, 0);
		waistR3 = new MowzieModelRenderer(this, 60, 78);
		waistR3.mirror = true;
		waistR3.setRotationPoint(0, 0, 2.5F);
		waistR3.addBox(-3, 0, -2, 3, 2, 2, 0);
		setRotateAngle(waistR3, 0, -0.24434609527920614F, 0);
		headcrest4 = new MowzieModelRenderer(this, 0, 77);
		headcrest4.setRotationPoint(0, -0.5F, 0);
		headcrest4.addBox(-1.5F, 0, -1.5F, 3, 1, 3, 0);
		turret4 = new MowzieModelRenderer(this, 32, 83);
		turret4.setRotationPoint(0, 2, 2);
		turret4.addBox(-2.5F, 0, -2, 5, 1, 3, 0);
		wheelL3 = new MowzieModelRenderer(this, 11, 96);
		wheelL3.setRotationPoint(0.5F, 0.5F, 0);
		wheelL3.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		shoulderL8 = new MowzieModelRenderer(this, 25, 58);
		shoulderL8.setRotationPoint(1, -0.5F, -1.7F);
		shoulderL8.addBox(0, 0, 0, 1, 1, 3, 0);
		lowerarmR4 = new MowzieModelRenderer(this, 16, 114);
		lowerarmR4.mirror = true;
		lowerarmR4.setRotationPoint(-1, 3.8F, 0);
		lowerarmR4.addBox(0, 0, 0, 1, 1, 2, 0);
		setRotateAngle(lowerarmR4, 0.017453292519943295F, 0, -0.3839724354387525F);
		headcrest2 = new MowzieModelRenderer(this, 16, 71);
		headcrest2.setRotationPoint(1, 0.5F, -1.5F);
		headcrest2.addBox(-0.5F, -2, -0.5F, 1, 2, 1, 0);
		setRotateAngle(headcrest2, 0.20071286397934787F, 0, 0.19198621771937624F);
		trackconnectorR3 = new MowzieModelRenderer(this, 29, 90);
		trackconnectorR3.mirror = true;
		trackconnectorR3.setRotationPoint(-2, 0, -1);
		trackconnectorR3.addBox(-1, -6, 0, 1, 7, 1, 0);
		setRotateAngle(trackconnectorR3, 0.06981317007977318F, 0.10471975511965977F, 0.03490658503988659F);
		trackR2 = new MowzieModelRenderer(this, 9, 100);
		trackR2.mirror = true;
		trackR2.setRotationPoint(-1, 0, 0);
		trackR2.addBox(0, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackR2, 0, 0, 0.5235987755982988F);
		turret6 = new MowzieModelRenderer(this, 42, 94);
		turret6.setRotationPoint(1.5F, 0, 0);
		turret6.addBox(0, 0, 0, 2, 2, 3, 0);
		setRotateAngle(turret6, 0, 0, 0.7138396640656808F);
		lowerarmR2 = new MowzieModelRenderer(this, 2, 120);
		lowerarmR2.mirror = true;
		lowerarmR2.setRotationPoint(0.9F, -1, -1);
		lowerarmR2.addBox(0, 0, 0, 1, 5, 2, 0);
		setRotateAngle(lowerarmR2, 0.03490658503988659F, -0.03490658503988659F, 0.19198621771937624F);
		turret7 = new MowzieModelRenderer(this, 42, 94);
		turret7.setRotationPoint(-1.5F, 0, 0);
		turret7.addBox(-2, 0, 0, 2, 2, 3, 0);
		setRotateAngle(turret7, 0, 0, -0.7138396640656808F);
		lowerlegR12 = new MowzieModelRenderer(this, 85, 88);
		lowerlegR12.setRotationPoint(0, 0, 0);
		lowerlegR12.addBox(-2, -1, 0, 2, 1, 1, 0);
		setRotateAngle(lowerlegR12, -0.5061454830783556F, 0, -0.017453292519943295F);
		waistL3 = new MowzieModelRenderer(this, 60, 78);
		waistL3.setRotationPoint(0, 0, 2.5F);
		waistL3.addBox(0, 0, -2, 3, 2, 2, 0);
		setRotateAngle(waistL3, 0, 0.24434609527920614F, 0);
		headcrest3 = new MowzieModelRenderer(this, 16, 71);
		headcrest3.mirror = true;
		headcrest3.setRotationPoint(-1, 0.5F, -1.5F);
		headcrest3.addBox(-0.5F, -2, -0.5F, 1, 2, 1, 0);
		setRotateAngle(headcrest3, 0.20071286397934787F, 0, -0.19198621771937624F);
		trackconnectorR2 = new MowzieModelRenderer(this, 20, 93);
		trackconnectorR2.mirror = true;
		trackconnectorR2.setRotationPoint(0, 0.1F, -0.1F);
		trackconnectorR2.addBox(-3, 0, 0, 3, 1, 1, 0);
		setRotateAngle(trackconnectorR2, 0, 0.7853981633974483F, -0.13962634015954636F);
		upperarmL2 = new MowzieModelRenderer(this, 27, 119);
		upperarmL2.setRotationPoint(0, -1, -0.5F);
		upperarmL2.addBox(0, 0, 0, 1, 5, 1, 0);
		headL2 = new MowzieModelRenderer(this, 9, 63);
		headL2.setRotationPoint(0, 0.7F, 0);
		headL2.addBox(0, 0, -1, 1, 2, 1, 0);
		setRotateAngle(headL2, 0.4625122517784973F, 0, 0.017453292519943295F);
		turret2 = new MowzieModelRenderer(this, 35, 77);
		turret2.setRotationPoint(2.3F, 2.5F, 0);
		turret2.addBox(0, -2, 0, 2, 2, 3, 0);
		setRotateAngle(turret2, 0, 0, -1.1990411961201044F);
		barrelbase4 = new MowzieModelRenderer(this, 35, 90);
		barrelbase4.setRotationPoint(0, 0, 0);
		barrelbase4.addBox(-0.2F, 0.5F, -0.8F, 1, 2, 1, 0);
		boxL2 = new MowzieModelRenderer(this, 2, 82);
		boxL2.setRotationPoint(0, -0.9F, 0.2F);
		boxL2.addBox(0, 0, 0, 3, 1, 2, 0);
		setRotateAngle(boxL2, -0.32637657012293964F, 0, 0);
		lowerlegL12 = new MowzieModelRenderer(this, 85, 88);
		lowerlegL12.mirror = true;
		lowerlegL12.setRotationPoint(0, 0, 0);
		lowerlegL12.addBox(0, -1, 0, 2, 1, 1, 0);
		setRotateAngle(lowerlegL12, -0.5061454830783556F, 0, 0.017453292519943295F);
		shoulderL6 = new MowzieModelRenderer(this, 31, 50);
		shoulderL6.setRotationPoint(-0.5F, -0.3F, 2.8F);
		shoulderL6.addBox(0, 0, 0, 3, 1, 2, 0);
		headL1 = new MowzieModelRenderer(this, 3, 64);
		headL1.setRotationPoint(1, -2.7F, -1.4F);
		headL1.addBox(0, 0, 0, 1, 3, 3, 0);
		setRotateAngle(headL1, 0, 0, -0.13962634015954636F);
		lowerarmL5 = new MowzieModelRenderer(this, 16, 114);
		lowerarmL5.setRotationPoint(1, 3.8F, 0);
		lowerarmL5.addBox(-1, 0, 0, 1, 1, 2, 0);
		setRotateAngle(lowerarmL5, 0.017453292519943295F, 0, 0.3839724354387525F);
		lowerlegR13 = new MowzieModelRenderer(this, 98, 77);
		lowerlegR13.setRotationPoint(-2.3F, 1.3F, 0.5F);
		lowerlegR13.addBox(-1, 0, 0, 1, 5, 1, 0);
		lowerlegR2 = new MowzieModelRenderer(this, 71, 88);
		lowerlegR2.setRotationPoint(1.2F, -1, -1);
		lowerlegR2.addBox(-3, 0, 0, 3, 6, 1, 0);
		trackL8 = new MowzieModelRenderer(this, 8, 109);
		trackL8.setRotationPoint(0.25F, 7.25F, 0);
		trackL8.addBox(0, 0, 0, 3, 1, 3, 0);
		setRotateAngle(trackL8, -0.03490658503988659F, 0, 0);
		upperArmR = new MowzieModelRenderer(this, 24, 111);
		upperArmR.mirror = true;
		upperArmR.setRotationPoint(-2, -1.5F, 0);
		upperArmR.addBox(0, -1, -1, 1, 5, 2, 0);
		setRotateAngle(upperArmR, 0.08726646259971647F, 0, 0.17453292519943295F);
		trackconnectorL4 = new MowzieModelRenderer(this, 24, 96);
		trackconnectorL4.setRotationPoint(1, -6, 0);
		trackconnectorL4.addBox(-1, -3, 0, 1, 3, 1, 0);
		setRotateAngle(trackconnectorL4, 0, 0, -0.5235987755982988F);
		shoulderR1 = new MowzieModelRenderer(this, 25, 50);
		shoulderR1.mirror = true;
		shoulderR1.setRotationPoint(-1, -2.5F, 0.5F);
		shoulderR1.addBox(-1, -0.5F, -2.5F, 1, 3, 4, 0);
		lowerlegR6 = new MowzieModelRenderer(this, 94, 85);
		lowerlegR6.setRotationPoint(0, 2, 1.6F);
		lowerlegR6.addBox(-2, 0, -2, 2, 2, 2, 0);
		setRotateAngle(lowerlegR6, -0.22689280275926282F, -0.017453292519943295F, 0);
		turret1 = new MowzieModelRenderer(this, 32, 71);
		turret1.setRotationPoint(0, 1.3F, 0);
		turret1.addBox(-2, 0.4F, 0, 4, 2, 3, 0);
		turret5 = new MowzieModelRenderer(this, 45, 88);
		turret5.setRotationPoint(0, -2.3F, 0);
		turret5.addBox(-1.5F, 0, 0, 3, 2, 3, 0);
		wheelR3 = new MowzieModelRenderer(this, 11, 96);
		wheelR3.mirror = true;
		wheelR3.setRotationPoint(-0.5F, 0.5F, 0);
		wheelR3.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		feetL4 = new MowzieModelRenderer(this, 80, 77);
		feetL4.mirror = true;
		feetL4.setRotationPoint(0.3F, -1, 2.5F);
		feetL4.addBox(0, -1, 0, 1, 3, 2, 0);
		setRotateAngle(feetL4, -1.0471975511965976F, 0, 0);
		trackconnectorL2 = new MowzieModelRenderer(this, 20, 93);
		trackconnectorL2.setRotationPoint(0, 0.1F, -0.1F);
		trackconnectorL2.addBox(0, 0, 0, 3, 1, 1, 0);
		setRotateAngle(trackconnectorL2, 0, -0.7853981633974483F, 0.13962634015954636F);
		lowerlegR10 = new MowzieModelRenderer(this, 96, 90);
		lowerlegR10.setRotationPoint(0.2F, 5, 0);
		lowerlegR10.addBox(-1, 0, 0, 1, 2, 4, 0);
		setRotateAngle(lowerlegR10, 0, 0.08726646259971647F, 0);
		crotch2 = new MowzieModelRenderer(this, 58, 89);
		crotch2.setRotationPoint(1, 2, 3);
		crotch2.addBox(-1, -2, -1, 2, 2, 1, 0);
		setRotateAngle(crotch2, -0.4886921905584123F, 0, 0);
		torsoconnector = new MowzieModelRenderer(this, 50, 50);
		torsoconnector.setRotationPoint(0, 0, 0);
		torsoconnector.addBox(-1.5F, -5, -1, 3, 5, 2, 0);
		chestplateL1 = new MowzieModelRenderer(this, 61, 59);
		chestplateL1.setRotationPoint(0.2F, -2.8F, -2.7F);
		chestplateL1.addBox(0, 0, -1, 3, 3, 3, 0);
		setRotateAngle(chestplateL1, 0.3141592653589793F, -0.13962634015954636F, 0);
		shoulderL3 = new MowzieModelRenderer(this, 25, 63);
		shoulderL3.setRotationPoint(0.5F, -1.5F, -2);
		shoulderL3.addBox(-1, 0, 0, 4, 1, 5, 0);
		turretconnector = new MowzieModelRenderer(this, 21, 70);
		turretconnector.setRotationPoint(0, -4, 1.5F);
		turretconnector.addBox(-2, 0, 0, 4, 5, 1, 0);
		setRotateAngle(turretconnector, 0.10471975511965977F, 0, 0);
		trackconnectorR1 = new MowzieModelRenderer(this, 20, 90);
		trackconnectorR1.mirror = true;
		trackconnectorR1.setRotationPoint(-3, -4, 3);
		trackconnectorR1.addBox(0, 0, 0, 3, 1, 1, 0);
		setRotateAngle(trackconnectorR1, 0.06981317007977318F, 0.7853981633974483F, 0.13962634015954636F);
		trackR3 = new MowzieModelRenderer(this, 9, 100);
		trackR3.mirror = true;
		trackR3.setRotationPoint(1, -3, 0);
		trackR3.addBox(-1, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackR3, -0.03490658503988659F, 0.017453292519943295F, -0.5235987755982988F);
		trackR8 = new MowzieModelRenderer(this, 9, 100);
		trackR8.setRotationPoint(1, -3, 0);
		trackR8.addBox(-1, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackR8, -0.03490658503988659F, 0.017453292519943295F, -0.5235987755982988F);
		lowerlegR9 = new MowzieModelRenderer(this, 73, 84);
		lowerlegR9.setRotationPoint(0, -1, 0);
		lowerlegR9.addBox(-1, 0, 0, 1, 1, 3, 0);
		setRotateAngle(lowerlegR9, -0.3490658503988659F, -0.017453292519943295F, 0);
		chestplateL2 = new MowzieModelRenderer(this, 71, 58);
		chestplateL2.setRotationPoint(0, 0.5F, 0);
		chestplateL2.addBox(0, -2, 0, 3, 2, 1, 0);
		setRotateAngle(chestplateL2, -0.4363323129985824F, 0.017453292519943295F, 0);
		shoulderR5 = new MowzieModelRenderer(this, 39, 63);
		shoulderR5.mirror = true;
		shoulderR5.setRotationPoint(-0.2F, 2.5F, 0.6F);
		shoulderR5.addBox(-1, 0, 0, 1, 1, 4, 0);
		setRotateAngle(shoulderR5, -0.03490658503988659F, 0, 0);
		shoulderR7 = new MowzieModelRenderer(this, 24, 64);
		shoulderR7.mirror = true;
		shoulderR7.setRotationPoint(-0.5F, 0, 0);
		shoulderR7.addBox(-2, 0, -1, 2, 1, 1, 0);
		setRotateAngle(shoulderR7, 0.3490658503988659F, 0, 0);
		lowerlegR4 = new MowzieModelRenderer(this, 89, 88);
		lowerlegR4.setRotationPoint(0, 0, 3.1F);
		lowerlegR4.addBox(-1, -0.3F, -2, 1, 6, 2, 0);
		setRotateAngle(lowerlegR4, 0.3211405823669566F, -0.03490658503988659F, 0);
		boxR3 = new MowzieModelRenderer(this, 10, 86);
		boxR3.setRotationPoint(-1, -0.2F, 1.2F);
		boxR3.addBox(-1, 0, 0, 1, 1, 1, 0);
		setRotateAngle(boxR3, 0.06806784082777885F, 0, 0);
		shoulderL2 = new MowzieModelRenderer(this, 25, 50);
		shoulderL2.mirror = true;
		shoulderL2.setRotationPoint(1.5F, -0.5F, -2.5F);
		shoulderL2.addBox(0, 0, 0, 1, 3, 4, 0);
		shoulderR6 = new MowzieModelRenderer(this, 31, 50);
		shoulderR6.mirror = true;
		shoulderR6.setRotationPoint(0.5F, -0.3F, 2.8F);
		shoulderR6.addBox(-3, 0, 0, 3, 1, 2, 0);
		trackconnectorL3 = new MowzieModelRenderer(this, 29, 90);
		trackconnectorL3.setRotationPoint(2, 0, -1);
		trackconnectorL3.addBox(0, -6, 0, 1, 7, 1, 0);
		setRotateAngle(trackconnectorL3, 0.05235987755982988F, -0.10471975511965977F, -0.03490658503988659F);
		lowerlegR3 = new MowzieModelRenderer(this, 80, 88);
		lowerlegR3.setRotationPoint(-2, 1, 1);
		lowerlegR3.addBox(-1, 0, 0, 1, 6, 3, 0);
		setRotateAngle(lowerlegR3, -0.19198621771937624F, -0.017453292519943295F, 0);
		feetL5 = new MowzieModelRenderer(this, 79, 82);
		feetL5.mirror = true;
		feetL5.setRotationPoint(-1.5F, 1, -4);
		feetL5.addBox(0, 0, 0, 3, 1, 4, 0);
		bodyparts1 = new MowzieModelRenderer(this, 63, 66);
		bodyparts1.setRotationPoint(1, -4.8F, 2);
		bodyparts1.addBox(0, 0, -1, 1, 5, 1, 0);
		setRotateAngle(bodyparts1, -0.12217304763960307F, 0, 0.10471975511965977F);
		lowerlegL7 = new MowzieModelRenderer(this, 94, 85);
		lowerlegL7.mirror = true;
		lowerlegL7.setRotationPoint(0, 2, -0.4F);
		lowerlegL7.addBox(0, 0, -2, 2, 2, 2, 0);
		setRotateAngle(lowerlegL7, -0.17453292519943295F, 0.017453292519943295F, 0);
		feetL2 = new MowzieModelRenderer(this, 87, 76);
		feetL2.mirror = true;
		feetL2.setRotationPoint(-1.5F, 0, -2.5F);
		feetL2.addBox(0, 0, 3, 3, 2, 2, 0);
		chestplateL3 = new MowzieModelRenderer(this, 70, 62);
		chestplateL3.setRotationPoint(3, -1.9F, 0);
		chestplateL3.addBox(-1, 0, 0, 1, 2, 4, 0);
		setRotateAngle(chestplateL3, 0.17453292519943295F, 0.17453292519943295F, 0.03490658503988659F);
		waistL1 = new MowzieModelRenderer(this, 49, 83);
		waistL1.setRotationPoint(1.5F, 0, -0.5F);
		waistL1.addBox(0, 0, -0.7F, 2, 2, 3, 0);
		wheelL1 = new MowzieModelRenderer(this, 11, 96);
		wheelL1.setRotationPoint(0.5F, -1.5F, 0);
		wheelL1.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		lowerlegL4 = new MowzieModelRenderer(this, 89, 88);
		lowerlegL4.mirror = true;
		lowerlegL4.setRotationPoint(0, 0, 3.1F);
		lowerlegL4.addBox(0, -0.3F, -2, 1, 6, 2, 0);
		setRotateAngle(lowerlegL4, 0.3211405823669566F, 0.03490658503988659F, 0);
		headback1 = new MowzieModelRenderer(this, 3, 59);
		headback1.setRotationPoint(0, -2.7F, 1);
		headback1.addBox(-2, 0, 0, 4, 3, 1, 0);
		setRotateAngle(headback1, 0.15707963267948966F, 0, 0);
		lowerlegL10 = new MowzieModelRenderer(this, 96, 90);
		lowerlegL10.mirror = true;
		lowerlegL10.setRotationPoint(-0.2F, 5, 0);
		lowerlegL10.addBox(0, 0, 0, 1, 2, 4, 0);
		setRotateAngle(lowerlegL10, 0, -0.08726646259971647F, 0);
		lowerarmL2 = new MowzieModelRenderer(this, 2, 120);
		lowerarmL2.setRotationPoint(-0.9F, -1, -1);
		lowerarmL2.addBox(-1, 0, 0, 1, 5, 2, 0);
		setRotateAngle(lowerarmL2, 0.03490658503988659F, 0.03490658503988659F, -0.19198621771937624F);
		turret12 = new MowzieModelRenderer(this, 40, 101);
		turret12.setRotationPoint(1, -0.4F, 2.2F);
		turret12.addBox(0, 0, 0, 1, 3, 1, 0);
		trackL7 = new MowzieModelRenderer(this, 9, 100);
		trackL7.mirror = true;
		trackL7.setRotationPoint(-1, -3, 0);
		trackL7.addBox(0, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackL7, -0.03490658503988659F, -0.017453292519943295F, 0.5235987755982988F);
		bodyparts4 = new MowzieModelRenderer(this, 68, 69);
		bodyparts4.mirror = true;
		bodyparts4.setRotationPoint(-1.2F, -2, -0.2F);
		bodyparts4.addBox(-1, 0, 0, 1, 2, 2, 0);
		setRotateAngle(bodyparts4, -0.15707963267948966F, 0, 0);
		barrelbase2 = new MowzieModelRenderer(this, 35, 90);
		barrelbase2.mirror = true;
		barrelbase2.setRotationPoint(0, 0, 0);
		barrelbase2.addBox(-0.8F, 0.5F, -0.8F, 1, 2, 1, 0);
		lowerlegL5 = new MowzieModelRenderer(this, 94, 85);
		lowerlegL5.mirror = true;
		lowerlegL5.setRotationPoint(-1.8F, 0.6F, -1.8F);
		lowerlegL5.addBox(0, 0, 0, 2, 2, 2, 0);
		setRotateAngle(lowerlegL5, 0.40142572795869574F, -0.03490658503988659F, 0);
		trackconnectorL1 = new MowzieModelRenderer(this, 20, 90);
		trackconnectorL1.setRotationPoint(3, -4, 3);
		trackconnectorL1.addBox(-3, 0, 0, 3, 1, 1, 0);
		setRotateAngle(trackconnectorL1, 0.06981317007977318F, -0.7853981633974483F, -0.13962634015954636F);
		lowerlegR1 = new MowzieModelRenderer(this, 87, 60);
		lowerlegR1.setRotationPoint(0, 4.6F, -0.8F);
		lowerlegR1.addBox(-1, -0.3F, 0, 2, 6, 3, 0);
		setRotateAngle(lowerlegR1, 0.17453292519943295F, 0, -0.08726646259971647F);
		waistR1 = new MowzieModelRenderer(this, 49, 83);
		waistR1.mirror = true;
		waistR1.setRotationPoint(-1.5F, 0, -0.5F);
		waistR1.addBox(-2, 0, -0.7F, 2, 2, 3, 0);
		lowerarmR5 = new MowzieModelRenderer(this, 6, 114);
		lowerarmR5.mirror = true;
		lowerarmR5.setRotationPoint(0.9F, -1, 2);
		lowerarmR5.addBox(-2, -2, -1, 2, 2, 1, 0);
		setRotateAngle(lowerarmR5, 0.17453292519943295F, 0, 0);
		wheelL4 = new MowzieModelRenderer(this, 11, 96);
		wheelL4.setRotationPoint(0.5F, -2, 0);
		wheelL4.addBox(-1, -1, -1.5F, 2, 2, 2, 0);
		trackL1 = new MowzieModelRenderer(this, 18, 100);
		trackL1.setRotationPoint(1, -6, -2.1F);
		trackL1.addBox(0, 0, 0, 1, 8, 3, 0);
		crotch1 = new MowzieModelRenderer(this, 60, 83);
		crotch1.setRotationPoint(-1, 1.7F, -1.8F);
		crotch1.addBox(0, 0, 0, 2, 2, 3, 0);
		setRotateAngle(crotch1, 0.13962634015954636F, 0, 0);
		neck1 = new MowzieModelRenderer(this, 3, 48);
		neck1.setRotationPoint(0, -4, 0);
		neck1.addBox(-1, -0.5F, -1, 2, 1, 2, 0);
		trackL2 = new MowzieModelRenderer(this, 9, 100);
		trackL2.setRotationPoint(1, 0, 0);
		trackL2.addBox(-1, -3, 0, 1, 3, 3, 0);
		setRotateAngle(trackL2, 0, 0, -0.5235987755982988F);
		lowerlegL13 = new MowzieModelRenderer(this, 98, 77);
		lowerlegL13.mirror = true;
		lowerlegL13.setRotationPoint(2.3F, 1.3F, 0.5F);
		lowerlegL13.addBox(0, 0, 0, 1, 5, 1, 0);
		lowerlegL11 = new MowzieModelRenderer(this, 69, 82);
		lowerlegL11.mirror = true;
		lowerlegL11.setRotationPoint(0.5F, 2.5F, -0.5F);
		lowerlegL11.addBox(0, 0, 0, 2, 3, 1, 0);
		barrelbase3 = new MowzieModelRenderer(this, 35, 90);
		barrelbase3.setRotationPoint(0, 0, 0);
		barrelbase3.addBox(-0.2F, 0.5F, -0.2F, 1, 2, 1, 0);
		torsobase = new MowzieModelRenderer(this, 61, 50);
		torsobase.setRotationPoint(0, -4.5F, 0);
		torsobase.addBox(-2.5F, -4, -2, 5, 4, 4, 0);
		fistR = new MowzieModelRenderer(this, 0, 115);
		fistR.mirror = true;
		fistR.setRotationPoint(-0.1F, 3.6F, 0.2F);
		fistR.addBox(-1, 0, -1, 2, 2, 2, 0);
		setRotateAngle(fistR, -0.08726646259971647F, 0, -0.20943951023931953F);
		feetR5 = new MowzieModelRenderer(this, 79, 82);
		feetR5.setRotationPoint(1.5F, 1, -4);
		feetR5.addBox(-3, 0, 0, 3, 1, 4, 0);
		barrelbase1 = new MowzieModelRenderer(this, 19, 84);
		barrelbase1.setRotationPoint(0, 0.8F, -0.5F);
		barrelbase1.addBox(-2, -0.5F, -1, 4, 1, 2, 0);
		setRotateAngle(barrelbase1, -0.06981317007977318F, 0, 0);
		barrel2 = new MowzieModelRenderer(this, 33, 94);
		barrel2.setRotationPoint(0, 8.5F, 0);
		barrel2.addBox(-1, -1, -1, 2, 2, 2, 0);
		lowerarmR3 = new MowzieModelRenderer(this, 20, 118);
		lowerarmR3.mirror = true;
		lowerarmR3.setRotationPoint(-0.5F, -1, -0.9F);
		lowerarmR3.addBox(-1, -1.2F, 0, 1, 5, 2, 0);
		setRotateAngle(lowerarmR3, 0.15707963267948966F, 0, 0);
		trackR5 = new MowzieModelRenderer(this, 8, 109);
		trackR5.mirror = true;
		trackR5.setRotationPoint(0.25F, 7.25F, 0);
		trackR5.addBox(-1, 0, 0, 3, 1, 3, 0);
		setRotateAngle(trackR5, -0.03490658503988659F, 0, 0);
		lowerlegR8 = new MowzieModelRenderer(this, 62, 89);
		lowerlegR8.setRotationPoint(0.2F, 2.1F, 1);
		lowerlegR8.addBox(-1, 0, 0, 1, 4, 3, 0);
		trackR6 = new MowzieModelRenderer(this, 18, 100);
		trackR6.setRotationPoint(1.5F, -6, -2.1F);
		trackR6.addBox(-1, 0, 0, 1, 8, 3, 0);
		waistL2 = new MowzieModelRenderer(this, 56, 77);
		waistL2.setRotationPoint(0, 0, -1);
		waistL2.addBox(0, 0, 0, 2, 2, 1, 0);
		setRotateAngle(waistL2, 0, -0.15184364492350666F, 0);
		stomach1 = new MowzieModelRenderer(this, 50, 70);
		stomach1.setRotationPoint(-2, -4.5F, -2);
		stomach1.addBox(0, 0, 0, 4, 5, 2, 0);
		setRotateAngle(stomach1, 0.15707963267948966F, 0, 0);
		feetR3 = new MowzieModelRenderer(this, 90, 81);
		feetR3.setRotationPoint(2.5F, 1.1F, -1.4F);
		feetR3.addBox(-2, 0, 0, 2, 1, 3, 0);
		setRotateAngle(feetR3, 0.41887902047863906F, 0, -0.017453292519943295F);
		waist = new MowzieModelRenderer(this, 46, 78);
		waist.setRotationPoint(0, 9.65F, 0);
		waist.addBox(-1.5F, 0, -1.5F, 3, 2, 3, 0);
		headtop1 = new MowzieModelRenderer(this, 3, 71);
		headtop1.setRotationPoint(0, -3.5F, 0);
		headtop1.addBox(-2, 0, -2, 4, 1, 4, 0);
		lowerarmL4 = new MowzieModelRenderer(this, 20, 118);
		lowerarmL4.setRotationPoint(0.5F, -1, -0.9F);
		lowerarmL4.addBox(0, -1.2F, 0, 1, 5, 2, 0);
		setRotateAngle(lowerarmL4, 0.15707963267948966F, 0, 0);
		barrelbase5 = new MowzieModelRenderer(this, 35, 90);
		barrelbase5.mirror = true;
		barrelbase5.setRotationPoint(0, 0, 0);
		barrelbase5.addBox(-0.8F, 0.5F, -0.2F, 1, 2, 1, 0);
		chestplateR3 = new MowzieModelRenderer(this, 70, 62);
		chestplateR3.mirror = true;
		chestplateR3.setRotationPoint(-3, -1.9F, 0);
		chestplateR3.addBox(0, 0, 0, 1, 2, 4, 0);
		setRotateAngle(chestplateR3, 0.17453292519943295F, -0.17453292519943295F, -0.03490658503988659F);
		shoulderR2 = new MowzieModelRenderer(this, 25, 50);
		shoulderR2.setRotationPoint(-1.5F, -0.5F, -2.5F);
		shoulderR2.addBox(-1, 0, 0, 1, 3, 4, 0);
		shoulderR8 = new MowzieModelRenderer(this, 25, 58);
		shoulderR8.setRotationPoint(-1, -0.5F, -1.7F);
		shoulderR8.addBox(-1, 0, 0, 1, 1, 3, 0);
		headR2 = new MowzieModelRenderer(this, 9, 63);
		headR2.mirror = true;
		headR2.setRotationPoint(0, 0.7F, 0);
		headR2.addBox(-1, 0, -1, 1, 2, 1, 0);
		setRotateAngle(headR2, 0.4625122517784973F, 0, -0.017453292519943295F);
		trackR1 = new MowzieModelRenderer(this, 18, 100);
		trackR1.mirror = true;
		trackR1.setRotationPoint(-1, -6, -2.1F);
		trackR1.addBox(-1, 0, 0, 1, 8, 3, 0);
		upperlegL2 = new MowzieModelRenderer(this, 82, 62);
		upperlegL2.mirror = true;
		upperlegL2.setRotationPoint(-0.5F, 0, 0.9F);
		upperlegL2.addBox(0, 0, 0, 1, 4, 1, 0);
		trackL5 = new MowzieModelRenderer(this, 18, 100);
		trackL5.mirror = true;
		trackL5.setRotationPoint(-1.5F, -6, -2.1F);
		trackL5.addBox(0, 0, 0, 1, 8, 3, 0);
		upperArmR.addChild(upperarmR2);
		trackconnectorR4.addChild(wheelR2);
		upperArmL.addChild(lowerArmL);
		upperLegL.addChild(lowerlegL1);
		lowerlegL1.addChild(feetbaseL1);
		trackL3.addChild(trackL4);
		waistL1.addChild(skirtL1);
		skirtL1.addChild(skirtL2);
		trackR6.addChild(trackR7);
		turretconnector.addChild(turretbase);
		feetbaseR1.addChild(feetR2);
		crotch1.addChild(crotch3);
		shoulderL6.addChild(shoulderL7);
		barrelbase1.addChild(barrel1);
		shoulderR3.addChild(shoulderR4);
		turret8.addChild(turret10);
		neck1.addChild(headbase);
		lowerlegR6.addChild(lowerlegR7);
		headbase.addChild(headchin1);
		boxR1.addChild(boxR2);
		trackL5.addChild(trackL6);
		torsobase.addChild(torsobaseL);
		trackconnectorR4.addChild(trackconnectorR5);
		torsobase.addChild(chestplate1);
		lowerlegR2.addChild(lowerlegR11);
		feetL2.addChild(feetL3);
		skirtR1.addChild(skirtR2);
		torsobase.addChild(torsobaseR);
		lowerlegR1.addChild(feetbaseR1);
		lowerlegL2.addChild(lowerlegL3);
		waist.addChild(upperLegR);
		upperArmR.addChild(lowerArmR);
		shoulderR1.addChild(shoulderR3);
		turret1.addChild(turret3);
		waist.addChild(upperLegL);
		chestplateR1.addChild(chestplateR2);
		trackconnectorR3.addChild(trackconnectorR4);
		lowerlegR4.addChild(lowerlegR5);
		lowerlegL1.addChild(lowerlegL2);
		shoulderL4.addChild(shoulderL5);
		trackL2.addChild(trackL3);
		waistR1.addChild(skirtR1);
		lowerlegL8.addChild(lowerlegL9);
		torsobaseL.addChild(upperArmL);
		trackconnectorL4.addChild(trackcnnectorL5);
		turretbase.addChild(turret11);
		lowerArmL.addChild(fistL);
		turret8.addChild(turret9);
		torsobaseL.addChild(shoulderL1);
		trackR3.addChild(trackR4);
		upperLegR.addChild(upperlegR2);
		shoulderL3.addChild(shoulderL4);
		lowerArmL.addChild(lowerarmL3);
		trackconnectorR3.addChild(wheelR5);
		turret5.addChild(turret8);
		lowerlegL2.addChild(lowerlegL8);
		waistR3.addChild(boxR1);
		boxL1.addChild(boxL3);
		feetR3.addChild(feetR4);
		torsoconnector.addChild(bodyparts2);
		headtop1.addChild(headcrest1);
		headbase.addChild(headR1);
		waistL3.addChild(boxL1);
		trackconnectorR5.addChild(wheelR1);
		trackconnectorL3.addChild(wheelL5);
		trackconnectorL4.addChild(wheelL2);
		waistR1.addChild(waistR2);
		torsoconnector.addChild(trackbase1);
		torsoconnector.addChild(bodyparts3);
		trackconnectorR3.addChild(wheelR4);
		torsobase.addChild(chestplateR1);
		lowerlegL5.addChild(lowerlegL6);
		waist.addChild(waistR3);
		headtop1.addChild(headcrest4);
		turret1.addChild(turret4);
		trackconnectorL3.addChild(wheelL3);
		shoulderL1.addChild(shoulderL8);
		lowerarmR3.addChild(lowerarmR4);
		headtop1.addChild(headcrest2);
		trackconnectorR2.addChild(trackconnectorR3);
		trackR1.addChild(trackR2);
		turret5.addChild(turret6);
		lowerArmR.addChild(lowerarmR2);
		turret5.addChild(turret7);
		lowerlegR11.addChild(lowerlegR12);
		waist.addChild(waistL3);
		headtop1.addChild(headcrest3);
		trackconnectorR1.addChild(trackconnectorR2);
		upperArmL.addChild(upperarmL2);
		headL1.addChild(headL2);
		turret1.addChild(turret2);
		barrelbase1.addChild(barrelbase4);
		boxL1.addChild(boxL2);
		lowerlegL11.addChild(lowerlegL12);
		shoulderL3.addChild(shoulderL6);
		headbase.addChild(headL1);
		lowerarmL4.addChild(lowerarmL5);
		lowerlegR2.addChild(lowerlegR13);
		lowerlegR1.addChild(lowerlegR2);
		trackL5.addChild(trackL8);
		torsobaseR.addChild(upperArmR);
		trackconnectorL3.addChild(trackconnectorL4);
		torsobaseR.addChild(shoulderR1);
		lowerlegR5.addChild(lowerlegR6);
		turretbase.addChild(turret1);
		turretbase.addChild(turret5);
		trackconnectorR3.addChild(wheelR3);
		feetL3.addChild(feetL4);
		trackconnectorL1.addChild(trackconnectorL2);
		lowerlegR2.addChild(lowerlegR10);
		crotch1.addChild(crotch2);
		waist.addChild(torsoconnector);
		torsobase.addChild(chestplateL1);
		shoulderL1.addChild(shoulderL3);
		torsobase.addChild(turretconnector);
		torsoconnector.addChild(trackconnectorR1);
		trackR2.addChild(trackR3);
		trackR7.addChild(trackR8);
		lowerlegR8.addChild(lowerlegR9);
		chestplateL1.addChild(chestplateL2);
		shoulderR4.addChild(shoulderR5);
		shoulderR6.addChild(shoulderR7);
		lowerlegR3.addChild(lowerlegR4);
		boxR1.addChild(boxR3);
		shoulderL1.addChild(shoulderL2);
		shoulderR3.addChild(shoulderR6);
		trackconnectorL2.addChild(trackconnectorL3);
		lowerlegR2.addChild(lowerlegR3);
		feetbaseL1.addChild(feetL5);
		torsoconnector.addChild(bodyparts1);
		lowerlegL6.addChild(lowerlegL7);
		feetbaseL1.addChild(feetL2);
		chestplateL2.addChild(chestplateL3);
		waist.addChild(waistL1);
		trackcnnectorL5.addChild(wheelL1);
		lowerlegL3.addChild(lowerlegL4);
		headbase.addChild(headback1);
		lowerlegL2.addChild(lowerlegL10);
		lowerArmL.addChild(lowerarmL2);
		turretbase.addChild(turret12);
		trackL6.addChild(trackL7);
		torsoconnector.addChild(bodyparts4);
		barrelbase1.addChild(barrelbase2);
		lowerlegL4.addChild(lowerlegL5);
		torsoconnector.addChild(trackconnectorL1);
		upperLegR.addChild(lowerlegR1);
		waist.addChild(waistR1);
		lowerArmR.addChild(lowerarmR5);
		trackconnectorL3.addChild(wheelL4);
		trackconnectorL3.addChild(trackL1);
		waist.addChild(crotch1);
		torsobase.addChild(neck1);
		trackL1.addChild(trackL2);
		lowerlegL2.addChild(lowerlegL13);
		lowerlegL2.addChild(lowerlegL11);
		barrelbase1.addChild(barrelbase3);
		torsoconnector.addChild(torsobase);
		lowerArmR.addChild(fistR);
		feetbaseR1.addChild(feetR5);
		turret4.addChild(barrelbase1);
		barrel1.addChild(barrel2);
		lowerArmR.addChild(lowerarmR3);
		trackR1.addChild(trackR5);
		lowerlegR2.addChild(lowerlegR8);
		trackconnectorR3.addChild(trackR6);
		waistL1.addChild(waistL2);
		torsoconnector.addChild(stomach1);
		feetR2.addChild(feetR3);
		headbase.addChild(headtop1);
		lowerArmL.addChild(lowerarmL4);
		barrelbase1.addChild(barrelbase5);
		chestplateR2.addChild(chestplateR3);
		shoulderR1.addChild(shoulderR2);
		shoulderR1.addChild(shoulderR8);
		headR1.addChild(headR2);
		trackconnectorR3.addChild(trackR1);
		upperLegL.addChild(upperlegL2);
		trackconnectorL3.addChild(trackL5);

		setInitPose();
	}

    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.PURGE;
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
		return headbase;
	}

	@Override
	public void setupOffsets(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		ModelOffset offsets = TFModelHelper.getOffsets(player);
		headbase.rotationPointX += offsets.headOffsetX;
		headbase.rotationPointY += offsets.headOffsetY;
		headbase.rotationPointZ += offsets.headOffsetZ;

		headbase.showModel = wearingHead;
		upperLegR.showModel = wearingLegs;
		upperLegL.showModel = wearingLegs;

		if (wearingChest && !wearingHead)
		{
			offsets.headOffsetY = 0.8F;
		}

		if (wearingHead)
		{
			faceTarget(headbase, 1, rotationYaw, rotationPitch);

			if (!wearingChest)
			{
				headbase.rotationPointY += 1;
				headbase.rotationPointZ -= 0.5;

				if (TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerSkystrike)
				{
					headbase.rotationPointY -= 1.5;
				}
			}
		}

		if (wearingLegs && !wearingChest)
		{
			upperLegR.rotationPointY += 10;
			upperLegL.rotationPointY += 10;
		}
	}

	@Override
	public void doActiveAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
		applyDefaultHittingAnimation(torsobaseR, upperArmL, headbase, torsobase, lowerArmR, lowerArmL);

		if (isRiding)
		{
			upperArmR.rotateAngleX -= (float) Math.PI / 5F;
			upperArmL.rotateAngleX -= (float) Math.PI / 5F;
			upperLegR.rotateAngleX -= (float) Math.PI * 2F / 5F;
			upperLegL.rotateAngleX -= (float) Math.PI * 2F / 5F;

			upperLegR.rotateAngleY += (float) Math.PI / 10F;
			upperLegL.rotateAngleY -= (float) Math.PI / 10F;
		}

		if (aimedBow)
		{
			upperArmR.rotateAngleY += -0.1F + headbase.rotateAngleY;
			upperArmL.rotateAngleY += 0.1F + headbase.rotateAngleY + 0.4F;
			upperArmR.rotateAngleX += -((float) Math.PI / 2F) + headbase.rotateAngleX;
			upperArmL.rotateAngleX += -((float) Math.PI / 2F) + headbase.rotateAngleX;
			upperArmR.rotateAngleZ += MathHelper.cos(ticks * 0.09F) * 0.05F + 0.05F;
			upperArmL.rotateAngleZ -= MathHelper.cos(ticks * 0.09F) * 0.05F + 0.05F;
			upperArmR.rotateAngleX += MathHelper.sin(ticks * 0.067F) * 0.05F;
			upperArmL.rotateAngleX -= MathHelper.sin(ticks * 0.067F) * 0.05F;
		}
		
		ItemStack heldItem = player.getHeldItem();
		trackconnectorR1.showModel = !(heldItem != null && heldItem.getItem() == TFItems.purgesKatana);
	}

	@Override
	public void doWalkingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		upperArmR.rotateAngleZ += 0.05F;
		upperArmL.rotateAngleZ -= 0.05F;
		lowerArmR.rotateAngleX -= 0.1F;
		lowerArmL.rotateAngleX -= 0.1F;
		upperLegR.rotateAngleY += 0.2;
		upperLegL.rotateAngleY -= 0.2;
		upperLegR.rotateAngleX -= 0.2;
		upperLegL.rotateAngleX -= 0.2;
		lowerlegR1.rotateAngleX += 0.15;
		lowerlegL1.rotateAngleX += 0.15;
		waist.rotateAngleX += 0.1F;
		skirtR1.rotateAngleZ += 0.1F;
		skirtL1.rotateAngleZ -= 0.1F;

		MowzieModelRenderer armR = torsobaseR;
		MowzieModelRenderer armL = torsobaseL;

		bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, limbSwing, limbSwingAmount);
		waist.rotationPointY += 1.2 * limbSwingAmount;
		walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		walk(torsobase, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		swing(torsobase, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
		swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
		swing(headbase, 0.5F * globalSpeed, 0.2F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
		walk(headbase, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);

		swing(headbase, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
		headbase.rotationPointX += 0.6 * globalDegree * limbSwingAmount * Math.cos(limbSwing * 0.5F * globalSpeed);

		swing(upperLegR, 0.5F * globalSpeed, 0F * globalDegree, false, 0, -0.15F, limbSwing, limbSwingAmount);
		swing(upperLegL, 0.5F * globalSpeed, 0F * globalDegree, false, 0, 0.15F, limbSwing, limbSwingAmount);
		walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
		walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
		walk(lowerlegR1, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, limbSwing, limbSwingAmount);
		walk(lowerlegL1, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, limbSwing, limbSwingAmount);
		walk(armR, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		walk(armL, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);
		walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);

		flap(skirtR1, 1F * globalSpeed, 0.2F * globalDegree, false, -1, 0, limbSwing, limbSwingAmount);
		flap(skirtL1, 1F * globalSpeed, 0.2F * globalDegree, true, -1, 0, limbSwing, limbSwingAmount);
		walk(barrelbase1, 1F * globalSpeed, -0.3F * globalDegree, false, -1, 0, limbSwing, limbSwingAmount);

		if (player.isSneaking())
		{
			waist.rotationPointY += 1.8F;
			waist.rotateAngleX -= 0.1F;
			torsoconnector.rotateAngleX += 0.5;
			headbase.rotateAngleX -= 0.5;
			upperLegR.rotateAngleX -= 0.7;
			upperLegL.rotateAngleX -= 0.7;
			upperLegR.rotateAngleY += 0.2;
			upperLegL.rotateAngleY -= 0.2;
			lowerlegR1.rotateAngleX += 1.1;
			lowerlegL1.rotateAngleX += 1.1;
			feetbaseR1.rotateAngleX -= 0.3;
			feetbaseL1.rotateAngleX -= 0.3;
			upperArmR.rotateAngleX -= 0.5;
			upperArmL.rotateAngleX -= 0.5;
			upperArmR.rotateAngleZ += 0.5;
			upperArmL.rotateAngleZ -= 0.5;
			lowerArmR.rotateAngleZ -= 0.5;
			lowerArmL.rotateAngleZ += 0.5;
		}
	}

	@Override
	public void doIdleAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		walk(torsoconnector, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
		walk(torsobase, 0.08F, 0.15F, false, 1, 0, ticks, 1F);
		flap(trackconnectorR3, 0.08F, 0.1F, false, 1, 0, ticks, 1F);
		flap(trackconnectorL3, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
		walk(headbase, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
		walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
		walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticks, 1F);

		flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
		flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticks, 1F);
		walk(lowerArmR, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
		walk(lowerArmL, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
	}

	@Override
	public void doFallingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		double motionY = TFRenderHelper.getMotionY(player);

		float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
		float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));

		waist.rotateAngleX += 0.2 * limbSwingAmount * backwardInverter;

		stomach1.rotateAngleX += 0.2 * upwardPose;
		chestplate1.rotateAngleX -= 0.4 * upwardPose;
		headbase.rotateAngleX += 0.6 * upwardPose;

		upperArmR.rotateAngleX += 0.1 * upwardPose;
		upperArmL.rotateAngleX += 0.1 * upwardPose;
		upperArmR.rotateAngleZ -= 0.1 * upwardPose;
		upperArmL.rotateAngleZ += 0.1 * upwardPose;
		lowerArmR.rotateAngleX += 0.2 * upwardPose;
		lowerArmL.rotateAngleX += 0.2 * upwardPose;

		upperLegR.rotateAngleX += 0.2 * upwardPose;
		upperLegL.rotateAngleX -= 1 * upwardPose;
		lowerlegR1.rotateAngleX += 0.3 * upwardPose;
		lowerlegL1.rotateAngleX += 1.5 * upwardPose;

		walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, limbSwing, limbSwingAmount);
		walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, limbSwing, limbSwingAmount);
		walk(lowerlegR1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, limbSwing, limbSwingAmount);
		walk(lowerlegL1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, limbSwing, limbSwingAmount);

		waist.rotateAngleX -= 0.2 * downwardPose;
		stomach1.rotateAngleX += 0.3 * downwardPose;
		chestplate1.rotateAngleX += 0.3 * downwardPose;
		headbase.rotateAngleX += 0.3 * downwardPose;
		upperLegR.rotateAngleX -= 1.2 * downwardPose;
		upperLegL.rotateAngleX -= 0.2 * downwardPose;
		lowerlegR1.rotateAngleX += 2 * downwardPose;
		lowerlegL1.rotateAngleX += 0.5 * downwardPose;
		upperArmR.rotateAngleZ += 1 * downwardPose;
		upperArmL.rotateAngleZ -= 1 * downwardPose;
		lowerArmR.rotateAngleX -= 1 * downwardPose;
		lowerArmL.rotateAngleX -= 1 * downwardPose;
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
			waist.rotationPointZ += 4F;
			waist.rotationPointY -= 2F;
			upperArmR.rotateAngleX -= 0.1F;
			upperArmL.rotateAngleX -= 0.1F;

			if (wearingChest)
			{
				headbase.rotateAngleX -= 0.4F;
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
		ModelPurgeVehicle vehicle = (ModelPurgeVehicle)getTransformerModel().getVehicleModel();
		
		rotateTo(waist, vehicle.vehiclebase, f);
		rotateTo(turretbase, vehicle.vehicleturretbase_rotatehere, f);
		rotateTo(turretconnector, vehicle.vehicle31, f);
		rotateTo(shoulderL4, vehicle.vehicle11, f);
		rotateTo(shoulderR4, vehicle.vehicle26, f);
		rotateTo(lowerlegL1, vehicle.vehicle88, f);
		rotateTo(lowerlegR1, vehicle.vehicle81, f);
		rotateTo(lowerlegR2, vehicle.vehicle82, f);
		rotateTo(lowerlegL2, vehicle.vehicle89, f);
		rotateTo(lowerlegR3, vehicle.vehicle83, f);
		rotateTo(lowerlegL3, vehicle.vehicle90, f);
		rotateTo(chestplateL1, vehicle.vehicle33, f);
		rotateTo(chestplateR1, vehicle.vehicle37, f);
		rotateTo(skirtL1, vehicle.vehicle95, f);
		rotateTo(skirtR1, vehicle.vehicle97, f);
		rotateTo(barrel1, vehicle.vehiclebarrel1, f);
		rotateTo(trackconnectorR1, vehicle.vehicle42, f);
		rotateTo(trackconnectorR2, vehicle.vehicle43, f);
		rotateTo(trackconnectorR3, vehicle.vehicle44, f);
		rotateTo(trackconnectorL1, vehicle.vehicle61, f);
		rotateTo(trackconnectorL2, vehicle.vehicle62, f);
		rotateTo(trackconnectorL3, vehicle.vehicle63, f);
		rotateTo(torsoconnector, vehicle.vehicle1, f);
		rotateTo(torsobase, vehicle.vehicle2, f);
		rotateTo(upperArmL, vehicle.vehicle3, f);
		rotateTo(upperArmR, vehicle.vehicle17, f);
		rotateTo(lowerArmL, vehicle.vehicle4, f);
		rotateTo(lowerArmR, vehicle.vehicle18, f);
		rotateTo(lowerarmL4, vehicle.vehicle5, f);
		rotateTo(lowerarmR3, vehicle.vehicle19, f);
		rotateTo(upperLegR, vehicle.vehicle80, f);
		rotateTo(upperLegL, vehicle.vehicle87, f);
		rotateTo(shoulderL1, vehicle.vehicle8, f);
		rotateTo(shoulderR1, vehicle.vehicle23, f);
		rotateTo(shoulderL6, vehicle.vehicle13, f);
		rotateTo(shoulderR6, vehicle.vehicle28, f);
		rotateTo(shoulderL2, vehicle.vehicle9, f);
		rotateTo(shoulderR2, vehicle.vehicle24, f);
		rotateTo(shoulderR3, vehicle.vehicle25, f);
		rotateTo(shoulderL3, vehicle.vehicle10, f);
		rotateTo(boxL1, vehicle.vehicle99, f);
		rotateTo(boxR1, vehicle.vehicle103, f);
		rotateTo(boxL2, vehicle.vehicle100, f);
		rotateTo(boxR2, vehicle.vehicle104, f);
		rotateTo(boxL3, vehicle.vehicle101, f);
		rotateTo(boxR3, vehicle.vehicle105, f);
		rotateTo(waistL3, vehicle.vehicle98, f);
		rotateTo(waistR3, vehicle.vehicle102, f);
		rotateTo(lowerlegR13, vehicle.vehicle86, f);
		rotateTo(lowerlegL13, vehicle.vehicle93, f);
		rotateTo(lowerlegR11, vehicle.vehicle84, f);
		rotateTo(lowerlegL11, vehicle.vehicle91, f);
		rotateTo(lowerlegR12, vehicle.vehicle85, f);
		rotateTo(lowerlegL12, vehicle.vehicle92, f);
		rotateTo(trackR6, vehicle.vehicle58, f);
		rotateTo(trackR7, vehicle.vehicle59, f);
		rotateTo(trackR8, vehicle.vehicle60, f);
		rotateTo(trackR4, vehicle.vehicle56, f);
		rotateTo(trackR3, vehicle.vehicle55, f);
		rotateTo(trackR2, vehicle.vehicle54, f);
		rotateTo(trackR1, vehicle.vehicle53, f);
		rotateTo(trackR5, vehicle.vehicle57, f);
		rotateTo(wheelR5, vehicle.vehicle52, f);
		rotateTo(wheelR4, vehicle.vehicle51, f);
		rotateTo(wheelR3, vehicle.vehicle50, f);
		rotateTo(wheelR2, vehicle.vehicle49, f);
		rotateTo(wheelR1, vehicle.vehicle47, f);
		rotateTo(trackL6, vehicle.vehicle77, f);
		rotateTo(trackL7, vehicle.vehicle78, f);
		rotateTo(trackL8, vehicle.vehicle79, f);
		rotateTo(trackL4, vehicle.vehicle75, f);
		rotateTo(trackL3, vehicle.vehicle74, f);
		rotateTo(trackL2, vehicle.vehicle73, f);
		rotateTo(trackL1, vehicle.vehicle72, f);
		rotateTo(trackL5, vehicle.vehicle76, f);
		rotateTo(wheelL5, vehicle.vehicle71, f);
		rotateTo(wheelL4, vehicle.vehicle70, f);
		rotateTo(wheelL3, vehicle.vehicle69, f);
		rotateTo(wheelL2, vehicle.vehicle68, f);
		rotateTo(wheelL1, vehicle.vehicle66, f);

		feetbaseL1.rotationPointY -= f * 0.25F;
		feetbaseR1.rotationPointY -= f * 0.25F;
		feetbaseL1.rotationPointX -= f * 0.05F;
		feetbaseR1.rotationPointX += f * 0.05F;
		headbase.rotationPointY += f * 0.15F;
		headbase.rotationPointZ += f * 0.3F;
		
		vehicle.vehiclebase.rotateAngleY = bipedBody.rotateAngleY;
		vehicle.vehicleturretbase_rotatehere.rotateAngleZ = -(rotationYaw + 180) / (180f / (float) Math.PI);

		if (rotationPitch > 0)
		{
			rotationPitch = 0;
		}

		if (rotationPitch < -60)
		{
			rotationPitch = -60;
		}

		vehicle.vehiclebarrelbase1_rotatehere.rotateAngleX = -MathHelper.clamp_float(rotationPitch, -60, 0) / (180f / (float) Math.PI);
	}

	@Override
	public void renderArmorPiece(int armorPiece)
	{
		setToInitPose();

		if (armorPiece == 0)
		{
			GL11.glTranslatef(0, 0.075F, 0);
			headbase.render(0.0625F);
		}
		else if (armorPiece == 1)
		{
			upperLegL.showModel = false;
			upperLegR.showModel = false;
			headbase.showModel = false;
			waist.render(0.0625F);
			upperLegL.showModel = true;
			upperLegR.showModel = true;
			headbase.showModel = true;
		}
		else if (armorPiece == 2)
		{
			GL11.glTranslatef(0, 0.05F, 0);
			feetbaseL1.showModel = false;
			feetbaseR1.showModel = false;
			upperLegL.render(0.0625F);
			upperLegR.render(0.0625F);
			feetbaseL1.showModel = true;
			feetbaseR1.showModel = true;
		}
		else if (armorPiece == 3)
		{
			GL11.glRotatef(5, 1, 0, 0);
			feetbaseL1.rotationPointX -= 3;
			feetbaseR1.rotationPointX += 3;
			feetbaseL1.rotateAngleX += 0.2F;
			feetbaseL1.rotateAngleY += 0.2F;
			feetbaseR1.rotateAngleX += 0.2F;
			feetbaseR1.rotateAngleY -= 0.2F;
			feetbaseL1.render(0.0625F);
			feetbaseR1.render(0.0625F);
		}
	}
}
