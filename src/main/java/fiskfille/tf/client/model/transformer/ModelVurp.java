package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.model.AnimationModifier;
import fiskfille.tf.client.model.AnimationModifier.Predicate;
import fiskfille.tf.client.model.AnimationModifier.Type;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.vehicle.ModelVurpVehicle;
import fiskfille.tf.common.item.ItemVurpsSniper;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class ModelVurp extends ModelTransformerBase
{
	public ModelRendererTF waist;
	public ModelRendererTF waist1;
	public ModelRendererTF waist2;
	public ModelRendererTF waistconnector1;
	public ModelRendererTF waist3;
	public ModelRendererTF torsoconnector1;
	public ModelRendererTF upperLegR;
	public ModelRendererTF upperLegL;
	public ModelRendererTF upperlegR2;
	public ModelRendererTF kneeR1;
	public ModelRendererTF lowerLegR;
	public ModelRendererTF lowerlegR7;
	public ModelRendererTF lowerlegR2;
	public ModelRendererTF lowerlegR4;
	public ModelRendererTF lowerlegR5;
	public ModelRendererTF lowerlegR6;
	public ModelRendererTF footbaseR;
	public ModelRendererTF lowerlegR3;
	public ModelRendererTF footR1;
	public ModelRendererTF footR3;
	public ModelRendererTF footR2;
	public ModelRendererTF upperlegL2;
	public ModelRendererTF kneeL1;
	public ModelRendererTF lowerLegL;
	public ModelRendererTF lowerlegL7;
	public ModelRendererTF lowerlegL2;
	public ModelRendererTF lowerlegL4;
	public ModelRendererTF lowerlegL5;
	public ModelRendererTF lowerlegL6;
	public ModelRendererTF footbaseL;
	public ModelRendererTF lowerlegL3;
	public ModelRendererTF footL1;
	public ModelRendererTF footL3;
	public ModelRendererTF footL2;
	public ModelRendererTF torsobase;
	public ModelRendererTF ribs1;
	public ModelRendererTF ribs2;
	public ModelRendererTF connector1;
	public ModelRendererTF connector2;
	public ModelRendererTF fronttorso1;
	public ModelRendererTF hood1;
	public ModelRendererTF frontR1;
	public ModelRendererTF torsodetail1;
	public ModelRendererTF frontL1;
	public ModelRendererTF torsodetail2;
	public ModelRendererTF torsodetail3;
	public ModelRendererTF torsodetail4;
	public ModelRendererTF backplate1;
	public ModelRendererTF backplate2;
	public ModelRendererTF neck1;
	public ModelRendererTF shoulderpadR;
	public ModelRendererTF shoulderpadL;
	public ModelRendererTF armbaseR1;
	public ModelRendererTF armbaseL1;
	public ModelRendererTF hood2;
	public ModelRendererTF hood3;
	public ModelRendererTF hood4;
	public ModelRendererTF frontR2;
	public ModelRendererTF frontL2;
	public ModelRendererTF head;
	public ModelRendererTF visor1;
	public ModelRendererTF headback1;
	public ModelRendererTF visor2;
	public ModelRendererTF headback2;
	public ModelRendererTF visor3;
	public ModelRendererTF visor4;
	public ModelRendererTF headback3;
	public ModelRendererTF upperArmR;
	public ModelRendererTF shoulderR1;
	public ModelRendererTF lowerArmR;
	public ModelRendererTF upperarmR2;
	public ModelRendererTF fistR;
	public ModelRendererTF lowerarmR2;
	public ModelRendererTF upperArmL;
	public ModelRendererTF shoulderL1;
	public ModelRendererTF upperarmL2;
	public ModelRendererTF lowerArmL;
	public ModelRendererTF fistL;
	public ModelRendererTF lowerarmL2;

	public ModelVurp()
	{
		super(1, 0.8F, new AnimationModifier(Type.DEGREE, Predicate.BACKING, 0.5F));
		textureWidth = 128;
		textureHeight = 128;
		waist1 = new ModelRendererTF(this, 0, 64);
		waist1.mirror = true;
		waist1.setRotationPoint(0, 0, -2);
		waist1.addBox(0, 0, 0, 3, 2, 1);
		setRotateAngle(waist1, 0, -0.12217304763960307F, -0.19198621771937624F);
		fistL = new ModelRendererTF(this, 72, 64);
		fistL.mirror = true;
		fistL.setRotationPoint(0, 3.8F, -0.5F);
		fistL.addBox(-1.2F, -0.5F, -1, 2, 2, 2);
		setRotateAngle(fistL, 0, 0, 0.12217304763960307F);
		lowerlegL4 = new ModelRendererTF(this, 8, 83);
		lowerlegL4.setRotationPoint(-0.3F, 4, 0.5F);
		lowerlegL4.addBox(-0.6F, -2, -2, 1, 3, 2);
		setRotateAngle(lowerlegL4, 0.19198621771937624F, -0.2792526803190927F, 0);
		torsoconnector1 = new ModelRendererTF(this, 35, 60);
		torsoconnector1.setRotationPoint(0, 0.1F, -0.4F);
		torsoconnector1.addBox(-1, -4, -1, 2, 4, 2);
		setRotateAngle(torsoconnector1, 0, 0.7853981633974483F, 0);
		lowerlegR7 = new ModelRendererTF(this, 14, 85);
		lowerlegR7.setRotationPoint(0, -0.2F, -1);
		lowerlegR7.addBox(-2.5F, 0, -1, 3, 2, 1);
		setRotateAngle(lowerlegR7, 0.4363323129985824F, 0.017453292519943295F, 0);
		shoulderL1 = new ModelRendererTF(this, 78, 60);
		shoulderL1.mirror = true;
		shoulderL1.setRotationPoint(2.3F, -0.2F, 0.4F);
		shoulderL1.addBox(-1, -1.5F, -1.5F, 1, 3, 3);
		setRotateAngle(shoulderL1, 0.017453292519943295F, 0.05235987755982988F, -0.20943951023931953F);
		torsodetail2 = new ModelRendererTF(this, 45, 78);
		torsodetail2.setRotationPoint(2, 0, 1);
		torsodetail2.addBox(-1.5F, -1, -1, 2, 2, 2);
		setRotateAngle(torsodetail2, 0.13962634015954636F, -0.03490658503988659F, 0.15707963267948966F);
		shoulderpadR = new ModelRendererTF(this, 55, 68);
		shoulderpadR.setRotationPoint(-2, -3, 1);
		shoulderpadR.addBox(-0.2F, -2, -1, 1, 2, 2);
		setRotateAngle(shoulderpadR, -0.20943951023931953F, 0, -1.064650843716541F);
		frontL1 = new ModelRendererTF(this, 35, 77);
		frontL1.mirror = true;
		frontL1.setRotationPoint(1.8F, -2.1F, 1.4F);
		frontL1.addBox(-0.5F, -1, -4, 2, 2, 3);
		setRotateAngle(frontL1, 0.5410520681182421F, -0.03490658503988659F, -0.20943951023931953F);
		lowerlegL3 = new ModelRendererTF(this, 0, 85);
		lowerlegL3.mirror = true;
		lowerlegL3.setRotationPoint(0, 4, 0);
		lowerlegL3.addBox(0, 0, 0, 2, 2, 1);
		setRotateAngle(lowerlegL3, 0, -0.296705972839036F, 0);
		footL1 = new ModelRendererTF(this, 5, 92);
		footL1.mirror = true;
		footL1.setRotationPoint(0, 3, 0);
		footL1.addBox(-1.5F, -0.5F, -2, 3, 1, 3);
		setRotateAngle(footL1, 0.3839724354387525F, 0, 0);
		upperArmR = new ModelRendererTF(this, 74, 60);
		upperArmR.setRotationPoint(-1.5F, 0, 1);
		upperArmR.addBox(0, 0.8F, -0.5F, 1, 3, 1);
		setRotateAngle(upperArmR, 0.22689280275926282F, 0, 0);
		backplate1 = new ModelRendererTF(this, 51, 74);
		backplate1.setRotationPoint(0, -3, 3);
		backplate1.addBox(0, -1, 0, 3, 5, 1);
		setRotateAngle(backplate1, 0.017453292519943295F, 0.12217304763960307F, -0.10471975511965977F);
		lowerlegR2 = new ModelRendererTF(this, 10, 75);
		lowerlegR2.setRotationPoint(0.5F, 0, 0);
		lowerlegR2.addBox(0, 0, 0, 1, 6, 2);
		setRotateAngle(lowerlegR2, -0.33161255787892263F, 0, -0.03490658503988659F);
		upperlegR2 = new ModelRendererTF(this, 6, 72);
		upperlegR2.setRotationPoint(-2, -0.5F, -0.5F);
		upperlegR2.addBox(0, 0, 0, 1, 4, 1);
		setRotateAngle(upperlegR2, 0, 0.05235987755982988F, -0.03490658503988659F);
		upperLegL = new ModelRendererTF(this, 0, 71);
		upperLegL.setRotationPoint(1, 2, 0);
		upperLegL.addBox(0, -1, -1, 1, 4, 2);
		setRotateAngle(upperLegL, -0.148352986419518F, 0.017453292519943295F, -0.12217304763960307F);
		fronttorso1 = new ModelRendererTF(this, 35, 73);
		fronttorso1.setRotationPoint(-2, -2, -0.5F);
		fronttorso1.addBox(0, 0, 0, 4, 2, 2);
		setRotateAngle(fronttorso1, -1.1519173063162573F, 0, 0);
		upperLegR = new ModelRendererTF(this, 0, 71);
		upperLegR.setRotationPoint(-1, 2, 0);
		upperLegR.addBox(-1, -1, -1, 1, 4, 2);
		setRotateAngle(upperLegR, -0.148352986419518F, -0.017453292519943295F, 0.12217304763960307F);
		kneeR1 = new ModelRendererTF(this, 10, 71);
		kneeR1.setRotationPoint(0, 2.7F, 0);
		kneeR1.addBox(-2, 0, -1, 2, 2, 2);
		setRotateAngle(kneeR1, 0.148352986419518F, 0.017453292519943295F, -0.12217304763960307F);
		shoulderR1 = new ModelRendererTF(this, 78, 60);
		shoulderR1.setRotationPoint(-2.3F, -0.2F, 0.4F);
		shoulderR1.addBox(0, -1.5F, -1.5F, 1, 3, 3);
		setRotateAngle(shoulderR1, 0.017453292519943295F, -0.05235987755982988F, 0.20943951023931953F);
		footR1 = new ModelRendererTF(this, 5, 92);
		footR1.setRotationPoint(0, 3, 0);
		footR1.addBox(-1.5F, -0.5F, -2, 3, 1, 3);
		setRotateAngle(footR1, 0.3839724354387525F, 0, 0);
		ribs1 = new ModelRendererTF(this, 35, 82);
		ribs1.setRotationPoint(0, -1.8F, 0);
		ribs1.addBox(-1, 0, -2, 2, 1, 4);
		setRotateAngle(ribs1, 0, 0.7853981633974483F, 0);
		headback2 = new ModelRendererTF(this, 40, 98);
		headback2.setRotationPoint(0, 0, 0);
		headback2.addBox(0, 0, -2, 1, 1, 3);
		setRotateAngle(headback2, 0.10471975511965977F, -0.08726646259971647F, 0.12217304763960307F);
		armbaseL1 = new ModelRendererTF(this, 64, 60);
		armbaseL1.mirror = true;
		armbaseL1.setRotationPoint(3, -2, 1);
		armbaseL1.addBox(-0.3F, -1, -1, 2, 2, 3);
		setRotateAngle(armbaseL1, 0, -0.10471975511965977F, -0.17453292519943295F);
		armbaseR1 = new ModelRendererTF(this, 64, 60);
		armbaseR1.setRotationPoint(-3, -2, 1);
		armbaseR1.addBox(-1.7F, -1, -1, 2, 2, 3);
		setRotateAngle(armbaseR1, 0, 0.10471975511965977F, 0.17453292519943295F);
		lowerarmL2 = new ModelRendererTF(this, 74, 68);
		lowerarmL2.mirror = true;
		lowerarmL2.setRotationPoint(0.9F, 0.8F, -1.6F);
		lowerarmL2.addBox(-1, 0, -2, 1, 3, 2);
		setRotateAngle(lowerarmL2, 0, 1.117010721276371F, 0);
		ribs2 = new ModelRendererTF(this, 47, 80);
		ribs2.setRotationPoint(0, -3.5F, 0);
		ribs2.addBox(-0.9F, 0, -3, 2, 1, 6);
		setRotateAngle(ribs2, 0, 0.7853981633974483F, 0);
		lowerlegR6 = new ModelRendererTF(this, 15, 82);
		lowerlegR6.setRotationPoint(1, 0, 2);
		lowerlegR6.addBox(-2, 0, 0, 2, 2, 1);
		setRotateAngle(lowerlegR6, -0.45378560551852565F, 0, 0);
		lowerLegL = new ModelRendererTF(this, 0, 77);
		lowerLegL.mirror = true;
		lowerLegL.setRotationPoint(1, 0.4F, -1);
		lowerLegL.addBox(-1.5F, 0, 0, 3, 6, 2);
		setRotateAngle(lowerLegL, 0.24434609527920614F, 0, 0);
		lowerlegR5 = new ModelRendererTF(this, 16, 75);
		lowerlegR5.setRotationPoint(-0.5F, 0, 0);
		lowerlegR5.addBox(-1, 0, 0, 1, 6, 1);
		setRotateAngle(lowerlegR5, -0.17453292519943295F, 0.05235987755982988F, 0);
		lowerlegR3 = new ModelRendererTF(this, 0, 85);
		lowerlegR3.setRotationPoint(0, 4, 0);
		lowerlegR3.addBox(-2, 0, 0, 2, 2, 1);
		setRotateAngle(lowerlegR3, 0, 0.296705972839036F, -0);
		waist = new ModelRendererTF(this, 0, 60);
		waist.setRotationPoint(0, 11.1F, 1);
		waist.addBox(-2, 0, -1, 4, 1, 3);
		lowerArmR = new ModelRendererTF(this, 64, 65);
		lowerArmR.mirror = true;
		lowerArmR.setRotationPoint(0, 3.3F, -0.6F);
		lowerArmR.addBox(-0.9F, -0.2F, -1.6F, 2, 4, 3);
		setRotateAngle(lowerArmR, -0.5235987755982988F, -0.06981317007977318F, -0.12217304763960307F);
		waist3 = new ModelRendererTF(this, 12, 64);
		waist3.setRotationPoint(0, -0.2F, -0.5F);
		waist3.addBox(-3, 0, 0, 6, 2, 2);
		setRotateAngle(waist3, -0.24434609527920614F, 0, 0);
		fistR = new ModelRendererTF(this, 72, 64);
		fistR.setRotationPoint(0, 3.8F, -0.5F);
		fistR.addBox(-0.8F, -0.5F, -1, 2, 2, 2);
		setRotateAngle(fistR, 0, 0, -0.12217304763960307F);
		visor1 = new ModelRendererTF(this, 43, 90);
		visor1.setRotationPoint(-1, -2.9F, -1);
		visor1.addBox(0, 0, 0, 2, 2, 1);
		setRotateAngle(visor1, -0.17453292519943295F, 0, 0);
		footL2 = new ModelRendererTF(this, 0, 96);
		footL2.mirror = true;
		footL2.setRotationPoint(-1.5F, 0, -2);
		footL2.addBox(0, -0.5F, -1, 3, 1, 4);
		setRotateAngle(footL2, -0.33161255787892263F, 0, 0);
		backplate2 = new ModelRendererTF(this, 51, 74);
		backplate2.mirror = true;
		backplate2.setRotationPoint(0, -3, 3);
		backplate2.addBox(-3, -1, 0, 3, 5, 1);
		setRotateAngle(backplate2, 0.017453292519943295F, -0.12217304763960307F, 0.12217304763960307F);
		neck1 = new ModelRendererTF(this, 35, 90);
		neck1.setRotationPoint(0, -1.7F, -0.5F);
		neck1.addBox(-1, -2, 0, 2, 1, 2);
		visor4 = new ModelRendererTF(this, 45, 96);
		visor4.setRotationPoint(3, 1, 0);
		visor4.addBox(-1, 0, -2, 1, 1, 2);
		setRotateAngle(visor4, 0.10471975511965977F, 0.2792526803190927F, 0);
		footR2 = new ModelRendererTF(this, 0, 96);
		footR2.setRotationPoint(1.5F, 0, -2);
		footR2.addBox(-3, -0.5F, -1, 3, 1, 4);
		setRotateAngle(footR2, -0.33161255787892263F, 0, 0);
		headback3 = new ModelRendererTF(this, 40, 98);
		headback3.setRotationPoint(3, 0, 0);
		headback3.addBox(-1, 0, -2, 1, 1, 3);
		setRotateAngle(headback3, 0.10471975511965977F, 0.08726646259971647F, -0.12217304763960307F);
		waistconnector1 = new ModelRendererTF(this, 4, 64);
		waistconnector1.setRotationPoint(0, 0, 0);
		waistconnector1.addBox(-1, -0.1F, -2.3F, 2, 3, 4);
		setRotateAngle(waistconnector1, 0.008726646259971648F, 0, 0);
		headback1 = new ModelRendererTF(this, 35, 97);
		headback1.setRotationPoint(-1.5F, -3, 1);
		headback1.addBox(0, 0, 0, 3, 3, 1);
		setRotateAngle(headback1, -0.10471975511965977F, 0, 0);
		footbaseR = new ModelRendererTF(this, 0, 91);
		footbaseR.setRotationPoint(0, 4, 0.7F);
		footbaseR.addBox(-1, 1, -1, 2, 2, 2);
		setRotateAngle(footbaseR, -0.24434609527920614F, 0, 0);
		upperArmL = new ModelRendererTF(this, 74, 60);
		upperArmL.mirror = true;
		upperArmL.setRotationPoint(0.5F, 0, 1);
		upperArmL.addBox(0, 0.8F, -0.5F, 1, 3, 1);
		setRotateAngle(upperArmL, 0.22689280275926282F, 0, 0);
		lowerarmR2 = new ModelRendererTF(this, 74, 68);
		lowerarmR2.setRotationPoint(-0.9F, 0.8F, -1.6F);
		lowerarmR2.addBox(0, 0, -2, 1, 3, 2);
		setRotateAngle(lowerarmR2, 0, -1.117010721276371F, 0);
		footL3 = new ModelRendererTF(this, 0, 101);
		footL3.mirror = true;
		footL3.setRotationPoint(0, 3.4F, 0.4F);
		footL3.addBox(-1, -1.2F, -2, 2, 2, 4);
		setRotateAngle(footL3, 0, -0.008726646259971648F, 0);
		lowerlegL6 = new ModelRendererTF(this, 15, 82);
		lowerlegL6.mirror = true;
		lowerlegL6.setRotationPoint(-1, 0, 2);
		lowerlegL6.addBox(0, 0, 0, 2, 2, 1);
		setRotateAngle(lowerlegL6, -0.45378560551852565F, 0, 0);
		frontR2 = new ModelRendererTF(this, 48, 70);
		frontR2.setRotationPoint(0, 0, 0);
		frontR2.addBox(-1.5F, -2, -3.7F, 2, 1, 3);
		setRotateAngle(frontR2, 0.24434609527920614F, 0, -0.017453292519943295F);
		visor2 = new ModelRendererTF(this, 44, 93);
		visor2.setRotationPoint(0.5F, 0.1F, 0);
		visor2.addBox(0, 0, -2, 2, 1, 2);
		setRotateAngle(visor2, 0.10471975511965977F, 0, 0);
		shoulderpadL = new ModelRendererTF(this, 55, 68);
		shoulderpadL.mirror = true;
		shoulderpadL.setRotationPoint(2, -3, 1);
		shoulderpadL.addBox(-0.8F, -2, -1, 1, 2, 2);
		setRotateAngle(shoulderpadL, -0.20943951023931953F, 0, 1.064650843716541F);
		waist2 = new ModelRendererTF(this, 0, 64);
		waist2.setRotationPoint(0, 0, -2);
		waist2.addBox(-3, 0, 0, 3, 2, 1);
		setRotateAngle(waist2, 0, 0.12217304763960307F, 0.19198621771937624F);
		kneeL1 = new ModelRendererTF(this, 10, 71);
		kneeL1.setRotationPoint(0, 2.7F, 0);
		kneeL1.addBox(0, 0, -1, 2, 2, 2);
		setRotateAngle(kneeL1, 0.148352986419518F, -0.017453292519943295F, 0.12217304763960307F);
		visor3 = new ModelRendererTF(this, 45, 96);
		visor3.setRotationPoint(0, 1, 0);
		visor3.addBox(0, 0, -2, 1, 1, 2);
		setRotateAngle(visor3, 0.10471975511965977F, -0.2792526803190927F, 0);
		lowerlegL7 = new ModelRendererTF(this, 14, 85);
		lowerlegL7.mirror = true;
		lowerlegL7.setRotationPoint(0, -0.2F, -1);
		lowerlegL7.addBox(-0.5F, 0, -1, 3, 2, 1);
		setRotateAngle(lowerlegL7, 0.4363323129985824F, -0.017453292519943295F, 0);
		hood4 = new ModelRendererTF(this, 51, 65);
		hood4.setRotationPoint(0, 0, 0);
		hood4.addBox(-1.5F, -0.1F, -1.4F, 3, 2, 1);
		setRotateAngle(hood4, 0.8656833089891874F, 0, 0);
		hood3 = new ModelRendererTF(this, 47, 65);
		hood3.setRotationPoint(0, 0, 0);
		hood3.addBox(-1.8F, -3.3F, -1, 1, 4, 1);
		setRotateAngle(hood3, 0, -0.0017453292519943296F, -0.19198621771937624F);
		connector2 = new ModelRendererTF(this, 43, 61);
		connector2.mirror = true;
		connector2.setRotationPoint(1.5F, 0, 2);
		connector2.addBox(-1, -0.1F, -0.5F, 1, 4, 1);
		setRotateAngle(connector2, -0.13962634015954636F, 0, 0.13962634015954636F);
		hood2 = new ModelRendererTF(this, 47, 65);
		hood2.setRotationPoint(0, 0, 0);
		hood2.addBox(0.8F, -3.3F, -1, 1, 4, 1);
		setRotateAngle(hood2, 0, 0.0017453292519943296F, 0.19198621771937624F);
		frontR1 = new ModelRendererTF(this, 35, 77);
		frontR1.setRotationPoint(-1.8F, -2.1F, 1.4F);
		frontR1.addBox(-1.5F, -1, -4, 2, 2, 3);
		setRotateAngle(frontR1, 0.5410520681182421F, 0.03490658503988659F, 0.20943951023931953F);
		lowerlegL2 = new ModelRendererTF(this, 10, 75);
		lowerlegL2.mirror = true;
		lowerlegL2.setRotationPoint(-0.5F, 0, 0);
		lowerlegL2.addBox(-1, 0, 0, 1, 6, 2);
		setRotateAngle(lowerlegL2, -0.33161255787892263F, 0, 0.03490658503988659F);
		upperarmL2 = new ModelRendererTF(this, 74, 60);
		upperarmL2.setRotationPoint(0, -0.6F, -1.3F);
		upperarmL2.addBox(0, 0.8F, -0.5F, 1, 3, 1);
		torsobase = new ModelRendererTF(this, 35, 66);
		torsobase.setRotationPoint(0, -4, 0);
		torsobase.addBox(-2, -3, -0.7F, 4, 3, 4);
		setRotateAngle(torsobase, 0, -0.7853981633974483F, 0);
		torsodetail1 = new ModelRendererTF(this, 45, 78);
		torsodetail1.setRotationPoint(-2, 0, 1);
		torsodetail1.addBox(-0.5F, -1, -1, 2, 2, 2);
		setRotateAngle(torsodetail1, 0.13962634015954636F, 0.03490658503988659F, -0.15707963267948966F);
		lowerlegL5 = new ModelRendererTF(this, 16, 75);
		lowerlegL5.mirror = true;
		lowerlegL5.setRotationPoint(0.5F, 0, 0);
		lowerlegL5.addBox(0, 0, 0, 1, 6, 1);
		setRotateAngle(lowerlegL5, -0.17453292519943295F, -0.05235987755982988F, 0);
		upperarmR2 = new ModelRendererTF(this, 74, 60);
		upperarmR2.mirror = true;
		upperarmR2.setRotationPoint(0, -0.6F, -1.3F);
		upperarmR2.addBox(0, 0.8F, -0.5F, 1, 3, 1);
		hood1 = new ModelRendererTF(this, 47, 60);
		hood1.setRotationPoint(0, -0.5F, -1.8F);
		hood1.addBox(-1.5F, -3, -1, 3, 4, 1);
		setRotateAngle(hood1, -0.5061454830783556F, 0, 0);
		lowerArmL = new ModelRendererTF(this, 64, 65);
		lowerArmL.setRotationPoint(1, 3.3F, -0.6F);
		lowerArmL.addBox(-1.1F, -0.2F, -1.6F, 2, 4, 3);
		setRotateAngle(lowerArmL, -0.5235987755982988F, 0.06981317007977318F, 0.12217304763960307F);
		upperlegL2 = new ModelRendererTF(this, 6, 72);
		upperlegL2.setRotationPoint(2, -0.5F, -0.5F);
		upperlegL2.addBox(-1, 0, 0, 1, 4, 1);
		setRotateAngle(upperlegL2, 0, -0.05235987755982988F, 0.03490658503988659F);
		lowerlegR4 = new ModelRendererTF(this, 8, 83);
		lowerlegR4.setRotationPoint(0.3F, 4, 0.5F);
		lowerlegR4.addBox(-0.4F, -2, -2, 1, 3, 2);
		setRotateAngle(lowerlegR4, 0.19198621771937624F, 0.2792526803190927F, 0);
		head = new ModelRendererTF(this, 35, 93);
		head.setRotationPoint(0, -1.9F, 0.6F);
		head.addBox(-1.5F, -1, -1.5F, 3, 1, 3);
		frontL2 = new ModelRendererTF(this, 48, 70);
		frontL2.mirror = true;
		frontL2.setRotationPoint(0, 0, 0);
		frontL2.addBox(-0.5F, -2, -3.7F, 2, 1, 3);
		setRotateAngle(frontL2, 0.24434609527920614F, 0, 0.017453292519943295F);
		footR3 = new ModelRendererTF(this, 0, 101);
		footR3.setRotationPoint(0, 3.4F, 0.4F);
		footR3.addBox(-1, -1.2F, -2, 2, 2, 4);
		setRotateAngle(footR3, 0, 0.008726646259971648F, 0);
		connector1 = new ModelRendererTF(this, 43, 61);
		connector1.setRotationPoint(-1.5F, 0, 2);
		connector1.addBox(0, -0.1F, -0.5F, 1, 4, 1);
		setRotateAngle(connector1, -0.13962634015954636F, 0, -0.13962634015954636F);
		torsodetail3 = new ModelRendererTF(this, 55, 61);
		torsodetail3.setRotationPoint(-2, -2, 1);
		torsodetail3.addBox(-1, -1, -1, 1, 2, 2);
		setRotateAngle(torsodetail3, 0, 0.13962634015954636F, 0.33161255787892263F);
		footbaseL = new ModelRendererTF(this, 0, 91);
		footbaseL.mirror = true;
		footbaseL.setRotationPoint(0, 4, 0.7F);
		footbaseL.addBox(-1, 1, -1, 2, 2, 2);
		setRotateAngle(footbaseL, -0.24434609527920614F, 0, 0);
		torsodetail4 = new ModelRendererTF(this, 55, 61);
		torsodetail4.setRotationPoint(2, -2, 1);
		torsodetail4.addBox(0, -1, -1, 1, 2, 2);
		setRotateAngle(torsodetail4, 0, -0.13962634015954636F, -0.33161255787892263F);
		lowerLegR = new ModelRendererTF(this, 0, 77);
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

		float scale = 1.25F;
		shoulderpadR.setScale(scale, scale, scale);
		shoulderpadL.setScale(scale, scale, scale);
		lowerlegR6.setScale(scale, scale, scale);
		lowerlegL6.setScale(scale, scale, scale);
	}

    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.VURP;
    }

    @Override
    public ModelRendererTF getWaist()
    {
		return waist;
	}

	@Override
	public void setupOffsets(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		ModelOffset offsets = TFModelHelper.getOffsets(player);
		head.rotationPointX += offsets.headOffsetX;
		head.rotationPointY += offsets.headOffsetY;
		head.rotationPointZ += offsets.headOffsetZ;

		if (wearingHead)
		{
			faceTarget(head, 1, rotationYaw, rotationPitch);
		}

		if (!wearingChest)
		{
			head.rotationPointY += 2;
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

				if (TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerSkystrike)
				{
					waist.rotationPointY -= 3;
					offsets.headOffsetY -= 3;
					head.rotationPointY += 3;
				}
			}
		}

		if (TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerSkystrike)
		{
			head.rotationPointY -= 1;
			head.rotationPointZ -= 1;
		}
	}

	@Override
	public void doActiveAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
		applyDefaultHittingAnimation(armbaseR1, armbaseL1, head, torsobase, lowerArmR, lowerArmL);

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
	}

	@Override
	public void doWalkingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
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

	@Override
	public void doIdleAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		walk(fronttorso1, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
		walk(torsobase, 0.08F, 0.15F, false, 1, 0, ticks, 1F);
		walk(head, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
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

	@Override
	public void doPartialAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		waist.rotationPointY += 1;
		upperArmL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		upperArmR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		upperLegR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		upperLegL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		waist.rotationPointY -= 0.8F;

		if (isSneak)
		{
			waist.rotationPointY -= 0.8F;
			waist.rotateAngleX += 0.4F;
			waist.rotationPointZ += 3F;
			upperArmR.rotateAngleX -= 0.1F;
			upperArmL.rotateAngleX -= 0.1F;
			upperLegR.rotateAngleX -= 0.4F;
            upperLegL.rotateAngleX -= 0.4F;
            
			if (wearingChest)
			{
				head.rotateAngleX -= 0.4F;	
			}
		}
	}

	@Override
	public void doTransformationAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		ModelVurpVehicle vehicle = (ModelVurpVehicle)getTransformerModel().getVehicleModel();
		
		if (t != 20)
		{
			float f1 = f / 20;
			float f2 = 1 - f1;

			setToInitPose();
			rotateTo(waist, vehicle.vehicleBase, f);
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
			rotateTo(frontR1, vehicle.vehiclefrontR1, f);
			rotateTo(frontL1, vehicle.vehiclefrontL1, f);
			rotateTo(hood1, vehicle.vehiclehood1, f);
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

		for (ModelRenderer modelRenderer : new ModelRenderer[]{vehicle.vehicleWheelR, vehicle.vehicleWheelL, vehicle.vehicleWheelBackR, vehicle.vehicleWheelBackL})
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
			if (vehicle.vehicleBase.rotateAngleY < d)
			{
				vehicle.vehicleBase.rotateAngleY += 0.05F;
			}
			if (vehicle.vehicleBase.rotateAngleY > d)
			{
				vehicle.vehicleBase.rotateAngleY -= 0.05F;
			}
			vehicle.vehicleBase.rotateAngleY = d;

			vehicle.vehicleBase.rotateAngleX = 1.65F;

			if (player == Minecraft.getMinecraft().thePlayer)
			{
				vehicle.vehicleBase.rotateAngleX += -(float) player.motionY - 0.0784000015258789F;
			}
			else
			{
				vehicle.vehicleBase.rotateAngleX += -(float) (player.posY - player.prevPosY) * 1.5F;
			}

			vehicle.vehicleBase.rotateAngleX -= 0.1F;
		}
		
		ItemStack heldItemStack = player.getHeldItem();
		boolean holdingSniper = heldItemStack != null && heldItemStack.getItem() instanceof ItemVurpsSniper;

		if (holdingSniper)
		{
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

	@Override
	public void renderArmorPiece(int armorPiece)
	{
		setToInitPose();

		if (armorPiece == 0)
		{
			GL11.glTranslatef(0, 0.15F, -0.15F);
			head.render(0.0625F);
		}
		else if (armorPiece == 1)
		{
			GL11.glTranslatef(0, -0.2F, 0.03125F);
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
			GL11.glTranslatef(0, 0, -0.125F);
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
