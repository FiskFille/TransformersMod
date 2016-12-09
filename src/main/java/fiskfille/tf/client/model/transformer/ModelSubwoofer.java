package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.model.AnimationModifier;
import fiskfille.tf.client.model.AnimationModifier.Predicate;
import fiskfille.tf.client.model.AnimationModifier.Type;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class ModelSubwoofer extends ModelTransformerBase
{
	public ModelRendererTF waist;
	public ModelRendererTF stomach;
	public ModelRendererTF crotch1;
	public ModelRendererTF sideflapL;
	public ModelRendererTF crotchbuttonR;
	public ModelRendererTF crotchbuttonL;
	public ModelRendererTF sideflapL_1;
	public ModelRendererTF chestmain1;
	public ModelRendererTF chestwheel1;
	public ModelRendererTF chestwheel2;
	public ModelRendererTF frontchestR;
	public ModelRendererTF frontchestL;
	public ModelRendererTF chestmain2;
	public ModelRendererTF chestmain3;
	public ModelRendererTF chestmain4;
	public ModelRendererTF chestmain5;
	public ModelRendererTF chestmain6;
	public ModelRendererTF head;
	public ModelRendererTF dish1;
	public ModelRendererTF shoulderbaseL;
	public ModelRendererTF shoulderbaseR;
	public ModelRendererTF antenna1;
	public ModelRendererTF antenna2;
	public ModelRendererTF head1;
	public ModelRendererTF head2;
	public ModelRendererTF headplate1;
	public ModelRendererTF head3;
	public ModelRendererTF head4;
	public ModelRendererTF head5;
	public ModelRendererTF head6;
	public ModelRendererTF head7;
	public ModelRendererTF headplate2;
	public ModelRendererTF headplate2_1;
	public ModelRendererTF dish2;
	public ModelRendererTF shoulderplateL1;
	public ModelRendererTF upperArmL;
	public ModelRendererTF shoulderplate2;
	public ModelRendererTF shoulderplate3;
	public ModelRendererTF lowerArmL;
	public ModelRendererTF lowerarmL2;
	public ModelRendererTF lowerarmL3;
	public ModelRendererTF fistL;
	public ModelRendererTF clawL1;
	public ModelRendererTF clawL2;
	public ModelRendererTF upperArmR;
	public ModelRendererTF shoulderplateR1;
	public ModelRendererTF lowerArmR;
	public ModelRendererTF fistR;
	public ModelRendererTF bass1;
	public ModelRendererTF bass2;
	public ModelRendererTF bass3;
	public ModelRendererTF bass4;
	public ModelRendererTF bass5;
	public ModelRendererTF bass6;
	public ModelRendererTF shoulderplateR3;
	public ModelRendererTF shoulderplateR2;
	public ModelRendererTF upperLegL;
	public ModelRendererTF crotch2;
	public ModelRendererTF upperLegR;
	public ModelRendererTF lowerlegL1;
	public ModelRendererTF lowerlegL4;
	public ModelRendererTF legbaseL;
	public ModelRendererTF lowerlegL2;
	public ModelRendererTF lowerlegL3;
	public ModelRendererTF leg1;
	public ModelRendererTF legL2;
	public ModelRendererTF legL3;
	public ModelRendererTF lowerlegR1;
	public ModelRendererTF lowerlegR2;
	public ModelRendererTF lowerlegR3;
	public ModelRendererTF legbaseR;
	public ModelRendererTF lowerlegR4;
	public ModelRendererTF legR1;
	public ModelRendererTF legR2;
	public ModelRendererTF legR3;

	public ModelSubwoofer()
	{
		super(1, 0.8F, new AnimationModifier(Type.DEGREE, Predicate.BACKING, 0.5F));
		textureWidth = 128;
		textureHeight = 128;
		crotchbuttonR = new ModelRendererTF(this, 5, 112);
		crotchbuttonR.setRotationPoint(-3, 0.5F, -2.7F);
		crotchbuttonR.addBox(0, 0, 0, 2, 1, 1, 0);
		setRotateAngle(crotchbuttonR, 0, 0.08726646259971647F, 0);
		shoulderbaseR = new ModelRendererTF(this, 48, 78);
		shoulderbaseR.setRotationPoint(-4, -2.9F, 0);
		shoulderbaseR.addBox(-2.9F, -1, -1.4F, 3, 2, 3, 0);
		setRotateAngle(shoulderbaseR, 0, 0, 0.05235987755982988F);
		shoulderplate2 = new ModelRendererTF(this, 48, 90);
		shoulderplate2.setRotationPoint(0, 0, 3);
		shoulderplate2.addBox(1, 0, 0, 4, 1, 3, 0);
		setRotateAngle(shoulderplate2, -1.2915436464758039F, 0, 0);
		fistL = new ModelRendererTF(this, 52, 102);
		fistL.setRotationPoint(0, 4.2F, 0);
		fistL.addBox(-1, 0, -1, 2, 2, 2, 0);
		setRotateAngle(fistL, -0.03490658503988659F, 0.06981317007977318F, 0.24434609527920614F);
		lowerArmL = new ModelRendererTF(this, 48, 94);
		lowerArmL.setRotationPoint(0, 4, 0);
		lowerArmL.addBox(-1.3F, -0.6F, -1.5F, 3, 5, 3, 0);
		setRotateAngle(lowerArmL, -0.19198621771937624F, 0.06981317007977318F, 0.10471975511965977F);
		lowerlegL3 = new ModelRendererTF(this, 79, 82);
		lowerlegL3.setRotationPoint(-2, -1, -0.2F);
		lowerlegL3.addBox(0, 0, 0, 1, 7, 2, 0);
		setRotateAngle(lowerlegL3, -0.03490658503988659F, 0, 0);
		chestmain1 = new ModelRendererTF(this, 0, 67);
		chestmain1.setRotationPoint(0, -6, 0);
		chestmain1.addBox(-4, -4, -3, 8, 4, 6, 0);
		fistR = new ModelRendererTF(this, 52, 102);
		fistR.mirror = true;
		fistR.setRotationPoint(0, 4.2F, 0);
		fistR.addBox(-1, 0, -1, 2, 2, 2, 0);
		setRotateAngle(fistR, -0.03490658503988659F, -0.06981317007977318F, -0.24434609527920614F);
		lowerlegR3 = new ModelRendererTF(this, 79, 82);
		lowerlegR3.mirror = true;
		lowerlegR3.setRotationPoint(2, -1, -0.2F);
		lowerlegR3.addBox(-1, 0, 0, 1, 7, 2, 0);
		setRotateAngle(lowerlegR3, -0.03490658503988659F, 0, 0);
		head4 = new ModelRendererTF(this, 48, 69);
		head4.setRotationPoint(0.9F, 0, -0.9F);
		head4.addBox(0, -1, 0, 1, 1, 2, 0);
		setRotateAngle(head4, 0.03490658503988659F, -0.03490658503988659F, 0);
		shoulderplate3 = new ModelRendererTF(this, 61, 85);
		shoulderplate3.setRotationPoint(4.5F, -1, -2);
		shoulderplate3.addBox(-1, 0, -0.1F, 1, 2, 5, 0);
		setRotateAngle(shoulderplate3, 0, 0.19896753472735357F, -0.017453292519943295F);
		bass5 = new ModelRendererTF(this, 60, 92);
		bass5.setRotationPoint(-2, 1.5F, -0.5F);
		bass5.addBox(0, 0, 0, 1, 2, 3, 0);
		setRotateAngle(bass5, 1.5707963267948966F, 1.9198621771937625F, 0);
		shoulderplateL1 = new ModelRendererTF(this, 48, 83);
		shoulderplateL1.setRotationPoint(0, -1, 0);
		shoulderplateL1.addBox(0.5F, -1, -2, 4, 2, 5, 0);
		setRotateAngle(shoulderplateL1, 0.08726646259971647F, 0, 0.03490658503988659F);
		leg1 = new ModelRendererTF(this, 86, 64);
		leg1.setRotationPoint(0, 0, 0);
		leg1.addBox(0.1F, 0, -1.5F, 1, 3, 3, 0);
		sideflapL = new ModelRendererTF(this, 0, 112);
		sideflapL.setRotationPoint(3.5F, 0, 2);
		sideflapL.addBox(-0.3F, 0, -2, 1, 4, 3, 0);
		setRotateAngle(sideflapL, 0, -0.06981317007977318F, -0.06981317007977318F);
		shoulderbaseL = new ModelRendererTF(this, 48, 78);
		shoulderbaseL.setRotationPoint(4, -2.9F, 0);
		shoulderbaseL.addBox(-0.1F, -1, -1.4F, 3, 2, 3, 0);
		setRotateAngle(shoulderbaseL, 0, 0, -0.05235987755982988F);
		lowerlegR4 = new ModelRendererTF(this, 76, 76);
		lowerlegR4.mirror = true;
		lowerlegR4.setRotationPoint(-1, 4.5F, -1);
		lowerlegR4.addBox(-0.7F, -0.3F, -0.5F, 1, 2, 4, 0);
		setRotateAngle(lowerlegR4, 0, 0, 0.19198621771937624F);
		legR3 = new ModelRendererTF(this, 83, 79);
		legR3.mirror = true;
		legR3.setRotationPoint(-0.4F, 2.2F, 0);
		legR3.addBox(-1.1F, -2.7F, -3.2F, 2, 1, 3, 0);
		setRotateAngle(legR3, 0.4468042885105484F, -0.012217304763960306F, 0.041887902047863905F);
		waist = new ModelRendererTF(this, 0, 98);
		waist.setRotationPoint(0, 9, -0.5F);
		waist.addBox(-3.5F, 0, -2.5F, 7, 2, 5, 0);
		antenna1 = new ModelRendererTF(this, 30, 67);
		antenna1.setRotationPoint(1, 0.1F, 1);
		antenna1.addBox(-0.5F, -6, -0.5F, 1, 6, 1, 0);
		setRotateAngle(antenna1, -0.05235987755982988F, 0, -0.05235987755982988F);
		dish1 = new ModelRendererTF(this, 0, 67);
		dish1.setRotationPoint(0, -4, 2);
		dish1.addBox(-2, -3, 0, 2, 3, 1, 0);
		setRotateAngle(dish1, -0.17453292519943295F, -0.05235987755982988F, -0.05235987755982988F);
		lowerlegR1 = new ModelRendererTF(this, 76, 66);
		lowerlegR1.mirror = true;
		lowerlegR1.setRotationPoint(-1, 5, -1);
		lowerlegR1.addBox(-1.7F, -1.5F, -1.5F, 3, 6, 4, 0);
		setRotateAngle(lowerlegR1, 0.17453292519943295F, 0.017453292519943295F, -0.06981317007977318F);
		bass2 = new ModelRendererTF(this, 0, 112);
		bass2.setRotationPoint(-2.5F, 2, -1.5F);
		bass2.addBox(-0.2F, 0, 0, 1, 4, 3, 0);
		frontchestR = new ModelRendererTF(this, 10, 77);
		frontchestR.setRotationPoint(-4, -4, -3);
		frontchestR.addBox(0, 0, -0.6F, 4, 4, 1, 0);
		setRotateAngle(frontchestR, -0.07330382858376185F, 0.03839724354387525F, -0.005235987755982988F);
		shoulderplateR3 = new ModelRendererTF(this, 61, 85);
		shoulderplateR3.setRotationPoint(-4.5F, -1, -2);
		shoulderplateR3.addBox(0, 0, -0.1F, 1, 2, 5, 0);
		setRotateAngle(shoulderplateR3, 0, -0.19896753472735357F, 0.017453292519943295F);
		lowerlegL1 = new ModelRendererTF(this, 76, 66);
		lowerlegL1.setRotationPoint(1, 5, -1);
		lowerlegL1.addBox(-1.3F, -1.5F, -1.5F, 3, 6, 4, 0);
		setRotateAngle(lowerlegL1, 0.17453292519943295F, -0.017453292519943295F, 0.06981317007977318F);
		legR2 = new ModelRendererTF(this, 83, 70);
		legR2.mirror = true;
		legR2.setRotationPoint(-0.2F, 2.2F, 0);
		legR2.addBox(-1.3F, -1.2F, -4.1F, 2, 2, 7, 0);
		setRotateAngle(legR2, 0.027925268031909273F, -0.012217304763960306F, 0.041887902047863905F);
		crotch1 = new ModelRendererTF(this, 0, 105);
		crotch1.setRotationPoint(0, 2, 0);
		crotch1.addBox(-1, -0.7F, -2.4F, 2, 3, 4, 0);
		setRotateAngle(crotch1, 0.2617993877991494F, 0, 0);
		stomach = new ModelRendererTF(this, 0, 58);
		stomach.setRotationPoint(0, 1, 0);
		stomach.addBox(-2.5F, -5.5F, -2, 5, 5, 4, 0);
		shoulderplateR1 = new ModelRendererTF(this, 48, 83);
		shoulderplateR1.setRotationPoint(0, -1, 0);
		shoulderplateR1.addBox(-4.5F, -1, -2, 4, 2, 5, 0);
		setRotateAngle(shoulderplateR1, 0.08726646259971647F, 0, -0.03490658503988659F);
		bass3 = new ModelRendererTF(this, 0, 112);
		bass3.mirror = true;
		bass3.setRotationPoint(-2.5F, -2, -1.5F);
		bass3.addBox(-0.2F, 0, 0, 1, 4, 3, 0);
		lowerlegL2 = new ModelRendererTF(this, 91, 64);
		lowerlegL2.setRotationPoint(0, -1.5F, -1.5F);
		lowerlegL2.addBox(-1.3F, -1, 0, 3, 1, 1, 0);
		setRotateAngle(lowerlegL2, -0.15707963267948966F, 0, 0);
		chestmain4 = new ModelRendererTF(this, 20, 77);
		chestmain4.setRotationPoint(-3, 0, 5);
		chestmain4.addBox(0, 0, -2, 6, 5, 2, 0);
		setRotateAngle(chestmain4, -1.0297442586766543F, 0, 0);
		head1 = new ModelRendererTF(this, 48, 64);
		head1.setRotationPoint(0, -3, -1);
		head1.addBox(-0.5F, -1.5F, -0.4F, 1, 2, 3, 0);
		setRotateAngle(head1, -0.22689280275926282F, 0, 0);
		head2 = new ModelRendererTF(this, 56, 64);
		head2.setRotationPoint(1.3F, -3, -1);
		head2.addBox(-0.5F, -1, -0.2F, 1, 2, 3, 0);
		setRotateAngle(head2, -0.12217304763960307F, 0, 0.20943951023931953F);
		crotch2 = new ModelRendererTF(this, 8, 105);
		crotch2.setRotationPoint(0, 1, 1);
		crotch2.addBox(-1, -0.9F, 0, 2, 2, 1, 0);
		setRotateAngle(crotch2, -0.2617993877991494F, 0, 0);
		legbaseL = new ModelRendererTF(this, 86, 58);
		legbaseL.setRotationPoint(0.4F, 4.4F, 0.7F);
		legbaseL.addBox(-2.1F, 0, -1.5F, 2, 3, 3, 0);
		setRotateAngle(legbaseL, -0.17453292519943295F, 0.03490658503988659F, 0.05235987755982988F);
		sideflapL_1 = new ModelRendererTF(this, 0, 112);
		sideflapL_1.mirror = true;
		sideflapL_1.setRotationPoint(-3.5F, 0, 2);
		sideflapL_1.addBox(-0.7F, 0, -2, 1, 4, 3, 0);
		setRotateAngle(sideflapL_1, 0, 0.06981317007977318F, 0.06981317007977318F);
		chestwheel1 = new ModelRendererTF(this, 0, 77);
		chestwheel1.setRotationPoint(-2.5F, 1F, -0.5F);
		chestwheel1.addBox(-1, -1.5F, -1.5F, 2, 3, 3, 0);
		setRotateAngle(chestwheel1, -0.08726646259971647F, 0, 0.13962634015954636F);
		head7 = new ModelRendererTF(this, 54, 69);
		head7.mirror = true;
		head7.setRotationPoint(1.8F, -3.07F, 1.1F);
		head7.addBox(-0.97F, 0, 0, 1, 3, 1, 0);
		setRotateAngle(head7, 0, 0.017453292519943295F, 0);
		legL2 = new ModelRendererTF(this, 83, 70);
		legL2.setRotationPoint(-0.2F, 2.2F, 0);
		legL2.addBox(-1.1F, -1.2F, -4.1F, 2, 2, 7, 0);
		setRotateAngle(legL2, 0.027925268031909273F, 0.012217304763960306F, -0.041887902047863905F);
		head5 = new ModelRendererTF(this, 48, 69);
		head5.mirror = true;
		head5.setRotationPoint(-0.9F, 0, -0.9F);
		head5.addBox(-1, -1, 0, 1, 1, 2, 0);
		setRotateAngle(head5, 0.03490658503988659F, 0.03490658503988659F, 0);
		upperLegR = new ModelRendererTF(this, 76, 58);
		upperLegR.mirror = true;
		upperLegR.setRotationPoint(-1, 1, 0);
		upperLegR.addBox(-2, -1, -2, 2, 5, 3, 0);
		setRotateAngle(upperLegR, -0.2617993877991494F, 0.10471975511965977F, 0.08726646259971647F);
		lowerArmR = new ModelRendererTF(this, 48, 94);
		lowerArmR.mirror = true;
		lowerArmR.setRotationPoint(0, 4, 0);
		lowerArmR.addBox(-1.7F, -0.6F, -1.5F, 3, 5, 3, 0);
		setRotateAngle(lowerArmR, -0.19198621771937624F, -0.06981317007977318F, -0.10471975511965977F);
		bass1 = new ModelRendererTF(this, 60, 92);
		bass1.setRotationPoint(-2, 5, -0.5F);
		bass1.addBox(0, 0, 0, 1, 2, 3, 0);
		setRotateAngle(bass1, 1.5707963267948966F, 1.9198621771937625F, 0);
		lowerlegR2 = new ModelRendererTF(this, 91, 64);
		lowerlegR2.mirror = true;
		lowerlegR2.setRotationPoint(0, -1.5F, -1.5F);
		lowerlegR2.addBox(-1.7F, -1, 0, 3, 1, 1, 0);
		setRotateAngle(lowerlegR2, -0.15707963267948966F, 0, 0);
		frontchestL = new ModelRendererTF(this, 10, 77);
		frontchestL.mirror = true;
		frontchestL.setRotationPoint(4, -4, -3);
		frontchestL.addBox(-4, 0, -0.6F, 4, 4, 1, 0);
		setRotateAngle(frontchestL, -0.07330382858376185F, -0.03839724354387525F, 0.005235987755982988F);
		headplate2_1 = new ModelRendererTF(this, 47, 65);
		headplate2_1.mirror = true;
		headplate2_1.setRotationPoint(-1, 0, -0.3F);
		headplate2_1.addBox(-1, 0, 0, 1, 1, 1, 0);
		setRotateAngle(headplate2_1, 0, 0.45029494701453704F, 0);
		antenna2 = new ModelRendererTF(this, 30, 67);
		antenna2.setRotationPoint(5, 0.1F, 1);
		antenna2.addBox(-0.5F, -7, -0.5F, 1, 7, 1, 0);
		setRotateAngle(antenna2, -0.05235987755982988F, 0, 0.03490658503988659F);
		clawL1 = new ModelRendererTF(this, 48, 102);
		clawL1.setRotationPoint(1, 0, 0.3F);
		clawL1.addBox(0, -0.5F, 0, 1, 5, 1, 0);
		setRotateAngle(clawL1, 0, 0, 0.08726646259971647F);
		lowerarmL3 = new ModelRendererTF(this, 60, 97);
		lowerarmL3.setRotationPoint(0, 4, 0);
		lowerarmL3.addBox(0.7F, 0.4F, -1.5F, 1, 2, 3, 0);
		chestmain3 = new ModelRendererTF(this, 0, 83);
		chestmain3.setRotationPoint(-3, -4, 3);
		chestmain3.addBox(0, 0, 0, 6, 4, 2, 0);
		headplate2 = new ModelRendererTF(this, 47, 65);
		headplate2.setRotationPoint(1, 0, -0.3F);
		headplate2.addBox(0, 0, 0, 1, 1, 1, 0);
		setRotateAngle(headplate2, 0, -0.45029494701453704F, 0);
		bass4 = new ModelRendererTF(this, 60, 92);
		bass4.mirror = true;
		bass4.setRotationPoint(-2, 5, 1.5F);
		bass4.addBox(0, 0, 0, 1, 2, 3, 0);
		setRotateAngle(bass4, 1.5707963267948966F, 1.2217304763960306F, 0);
		crotchbuttonL = new ModelRendererTF(this, 5, 112);
		crotchbuttonL.mirror = true;
		crotchbuttonL.setRotationPoint(3, 0.5F, -2.7F);
		crotchbuttonL.addBox(-2, 0, 0, 2, 1, 1, 0);
		setRotateAngle(crotchbuttonL, 0, -0.08726646259971647F, 0);
		chestmain2 = new ModelRendererTF(this, 18, 62);
		chestmain2.setRotationPoint(-3, 0, -4);
		chestmain2.addBox(0, 0, 0.3F, 6, 3, 2, 0);
		setRotateAngle(chestmain2, 0.33161255787892263F, 0, 0);
		head3 = new ModelRendererTF(this, 56, 64);
		head3.mirror = true;
		head3.setRotationPoint(-1.3F, -3, -1);
		head3.addBox(-0.5F, -1, -0.2F, 1, 2, 3, 0);
		setRotateAngle(head3, -0.12217304763960307F, 0, -0.20943951023931953F);
		head = new ModelRendererTF(this, 48, 58);
		head.setRotationPoint(0, -4, -1.5F);
		head.addBox(-1.5F, -3, -1, 3, 3, 3, 0);
		legbaseR = new ModelRendererTF(this, 86, 58);
		legbaseR.mirror = true;
		legbaseR.setRotationPoint(0.4F, 4.4F, 0.7F);
		legbaseR.addBox(-0.7F, 0, -1.5F, 2, 3, 3, 0);
		setRotateAngle(legbaseR, -0.17453292519943295F, -0.03490658503988659F, -0.05235987755982988F);
		head6 = new ModelRendererTF(this, 54, 69);
		head6.setRotationPoint(-1.8F, -3.07F, 1.1F);
		head6.addBox(-0.03F, 0, 0, 1, 3, 1, 0);
		setRotateAngle(head6, 0, 0.017453292519943295F, 0);
		upperLegL = new ModelRendererTF(this, 76, 58);
		upperLegL.setRotationPoint(1, 1, 0);
		upperLegL.addBox(0, -1, -2, 2, 5, 3, 0);
		setRotateAngle(upperLegL, -0.2617993877991494F, -0.10471975511965977F, -0.08726646259971647F);
		lowerlegL4 = new ModelRendererTF(this, 76, 76);
		lowerlegL4.setRotationPoint(1, 4.5F, -1);
		lowerlegL4.addBox(-0.3F, -0.3F, -0.5F, 1, 2, 4, 0);
		setRotateAngle(lowerlegL4, 0, 0, -0.19198621771937624F);
		upperArmL = new ModelRendererTF(this, 61, 79);
		upperArmL.setRotationPoint(1.7F, 1, 0);
		upperArmL.addBox(-1, -0.2F, -1, 2, 4, 2, 0);
		setRotateAngle(upperArmL, 0.03490658503988659F, 0, -0.10471975511965977F);
		chestmain6 = new ModelRendererTF(this, 0, 89);
		chestmain6.mirror = true;
		chestmain6.setRotationPoint(-3, -4, 0);
		chestmain6.addBox(-1, -2, 0, 2, 3, 4, 0);
		setRotateAngle(chestmain6, -0.06981317007977318F, -0.12217304763960307F, -0.12217304763960307F);
		upperArmR = new ModelRendererTF(this, 61, 79);
		upperArmR.setRotationPoint(-1.7F, 1, 0);
		upperArmR.addBox(-1, -0.2F, -1, 2, 4, 2, 0);
		setRotateAngle(upperArmR, 0.03490658503988659F, 0, 0.10471975511965977F);
		legR1 = new ModelRendererTF(this, 86, 64);
		legR1.mirror = true;
		legR1.setRotationPoint(0, 0, 0);
		legR1.addBox(-1.9F, 0, -1.5F, 1, 3, 3, 0);
		chestmain5 = new ModelRendererTF(this, 0, 89);
		chestmain5.setRotationPoint(3, -4, 0);
		chestmain5.addBox(-1, -2, 0, 2, 3, 4, 0);
		setRotateAngle(chestmain5, -0.05235987755982988F, 0.12217304763960307F, 0.12217304763960307F);
		dish2 = new ModelRendererTF(this, 22, 67);
		dish2.setRotationPoint(0, -3, 1);
		dish2.addBox(-2, -2, -1, 2, 2, 1, 0);
		setRotateAngle(dish2, 0.5235987755982988F, 0, 0);
		bass6 = new ModelRendererTF(this, 60, 92);
		bass6.mirror = true;
		bass6.setRotationPoint(-2, 1.5F, 1.5F);
		bass6.addBox(0, 0, 0, 1, 2, 3, 0);
		setRotateAngle(bass6, 1.5707963267948966F, 1.2217304763960306F, 0);
		shoulderplateR2 = new ModelRendererTF(this, 48, 90);
		shoulderplateR2.setRotationPoint(0, 0, 3);
		shoulderplateR2.addBox(-5, 0, 0, 4, 1, 3, 0);
		setRotateAngle(shoulderplateR2, -1.2915436464758039F, 0, 0);
		clawL2 = new ModelRendererTF(this, 48, 102);
		clawL2.mirror = true;
		clawL2.setRotationPoint(1, 0, -1.3F);
		clawL2.addBox(0, -0.5F, 0, 1, 5, 1, 0);
		setRotateAngle(clawL2, 0, 0, 0.08726646259971647F);
		chestwheel2 = new ModelRendererTF(this, 0, 77);
		chestwheel2.mirror = true;
		chestwheel2.setRotationPoint(2.5F, 1, -0.5F);
		chestwheel2.addBox(-1, -1.5F, -1.5F, 2, 3, 3, 0);
		setRotateAngle(chestwheel2, -0.08726646259971647F, 0, -0.14608405839192537F);
		headplate1 = new ModelRendererTF(this, 53, 65);
		headplate1.setRotationPoint(0, -1, -1);
		headplate1.addBox(-1, 0, -0.3F, 2, 1, 1, 0);
		lowerarmL2 = new ModelRendererTF(this, 60, 92);
		lowerarmL2.setRotationPoint(0, 4, 0);
		lowerarmL2.addBox(0.7F, -6.6F, -1.5F, 1, 2, 3, 0);
		legL3 = new ModelRendererTF(this, 83, 79);
		legL3.setRotationPoint(-0.2F, 2.2F, 0);
		legL3.addBox(-1.1F, -2.7F, -3.2F, 2, 1, 3, 0);
		setRotateAngle(legL3, 0.4468042885105484F, 0.012217304763960306F, -0.041887902047863905F);
		waist.addChild(crotchbuttonR);
		chestmain1.addChild(shoulderbaseR);
		shoulderplateL1.addChild(shoulderplate2);
		lowerArmL.addChild(fistL);
		upperArmL.addChild(lowerArmL);
		lowerlegL1.addChild(lowerlegL3);
		stomach.addChild(chestmain1);
		lowerArmR.addChild(fistR);
		lowerlegR1.addChild(lowerlegR3);
		head.addChild(head4);
		shoulderplateL1.addChild(shoulderplate3);
		lowerArmR.addChild(bass5);
		shoulderbaseL.addChild(shoulderplateL1);
		legbaseL.addChild(leg1);
		waist.addChild(sideflapL);
		chestmain1.addChild(shoulderbaseL);
		lowerlegR1.addChild(lowerlegR4);
		legbaseR.addChild(legR3);
		chestmain3.addChild(antenna1);
		chestmain1.addChild(dish1);
		upperLegR.addChild(lowerlegR1);
		lowerArmR.addChild(bass2);
		chestmain1.addChild(frontchestR);
		shoulderplateR1.addChild(shoulderplateR3);
		upperLegL.addChild(lowerlegL1);
		legbaseR.addChild(legR2);
		waist.addChild(crotch1);
		waist.addChild(stomach);
		shoulderbaseR.addChild(shoulderplateR1);
		lowerArmR.addChild(bass3);
		lowerlegL1.addChild(lowerlegL2);
		chestmain1.addChild(chestmain4);
		head.addChild(head1);
		head.addChild(head2);
		crotch1.addChild(crotch2);
		lowerlegL1.addChild(legbaseL);
		waist.addChild(sideflapL_1);
		chestmain1.addChild(chestwheel1);
		head.addChild(head7);
		legbaseL.addChild(legL2);
		head.addChild(head5);
		crotch1.addChild(upperLegR);
		upperArmR.addChild(lowerArmR);
		lowerArmR.addChild(bass1);
		lowerlegR1.addChild(lowerlegR2);
		chestmain1.addChild(frontchestL);
		headplate1.addChild(headplate2_1);
		chestmain3.addChild(antenna2);
		lowerarmL3.addChild(clawL1);
		lowerArmL.addChild(lowerarmL3);
		chestmain1.addChild(chestmain3);
		headplate1.addChild(headplate2);
		lowerArmR.addChild(bass4);
		waist.addChild(crotchbuttonL);
		chestmain1.addChild(chestmain2);
		head.addChild(head3);
		chestmain1.addChild(head);
		lowerlegR1.addChild(legbaseR);
		head.addChild(head6);
		crotch1.addChild(upperLegL);
		lowerlegL1.addChild(lowerlegL4);
		shoulderbaseL.addChild(upperArmL);
		chestmain1.addChild(chestmain6);
		shoulderbaseR.addChild(upperArmR);
		legbaseR.addChild(legR1);
		chestmain1.addChild(chestmain5);
		dish1.addChild(dish2);
		lowerArmR.addChild(bass6);
		shoulderplateR1.addChild(shoulderplateR2);
		lowerarmL3.addChild(clawL2);
		chestmain1.addChild(chestwheel2);
		head.addChild(headplate1);
		lowerArmL.addChild(lowerarmL2);
		legbaseL.addChild(legL3);

		setInitPose();
	}

    @Override
    public Transformer getTransformer()
    {
        return TransformerManager.SUBWOOFER;
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

		head.showModel = wearingHead;
		upperLegR.showModel = wearingLegs;
		upperLegL.showModel = wearingLegs;

		if (!wearingChest && wearingHead)
		{
			head.rotationPointY += 4;

			if (TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerSkystrike)
			{
				head.rotationPointY -= 1;
			}

			head.rotationPointZ += 1;
		}

		if (wearingHead)
		{
			faceTarget(head, 1, rotationYaw, rotationPitch);
		}

		if (!wearingChest && wearingLegs)
		{
			upperLegL.rotationPointY += 11;
			upperLegR.rotationPointY += 11;
			upperLegL.rotateAngleX += 0.2F;
			upperLegR.rotateAngleX += 0.2F;
		}
	}

	@Override
	public void doActiveAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
		applyDefaultHittingAnimation(upperArmR, upperArmL, head, chestmain1, lowerArmR, lowerArmL);

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
			upperArmR.rotateAngleY += -0.1F + head.rotateAngleY;
			upperArmL.rotateAngleY += 0.1F + head.rotateAngleY + 0.4F;
			upperArmR.rotateAngleX += -((float) Math.PI / 2F) + head.rotateAngleX;
			upperArmL.rotateAngleX += -((float) Math.PI / 2F) + head.rotateAngleX;
			upperArmR.rotateAngleZ += MathHelper.cos(ticks * 0.09F) * 0.05F + 0.05F;
			upperArmL.rotateAngleZ -= MathHelper.cos(ticks * 0.09F) * 0.05F + 0.05F;
			upperArmR.rotateAngleX += MathHelper.sin(ticks * 0.067F) * 0.05F;
			upperArmL.rotateAngleX -= MathHelper.sin(ticks * 0.067F) * 0.05F;
		}

		ItemStack heldItem = player.getHeldItem();

		if (heldItem != null && heldItem.getItem() == TFItems.subwoofersBassBlaster && TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) == 20)
		{
			setRotateAngle(shoulderbaseR, 0, 0, 0);
			setRotateAngle(upperArmR, 0, 0, 0.2F);
			setRotateAngle(lowerArmR, bipedHead.rotateAngleX - pi / 2, bipedHead.rotateAngleY, 0);
		}
	}

	@Override
	public void doWalkingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		waist.rotationPointY -= 2;

		upperLegR.rotateAngleY += 0.2;
		upperLegL.rotateAngleY -= 0.2;
		upperLegR.rotateAngleX -= 0.1;
		upperLegL.rotateAngleX -= 0.1;
		lowerlegR1.rotateAngleX += 0.1;
		lowerlegL1.rotateAngleX += 0.1;
		lowerArmL.rotateAngleX -= 0.1;
		lowerArmR.rotateAngleX -= 0.1;
		head.rotateAngleX += 0.1;

		bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, limbSwing, limbSwingAmount);
		waist.rotationPointY += 1 * limbSwingAmount + 3;
		walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		walk(chestmain1, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		swing(chestmain1, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
		swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
		walk(head, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);

		swing(head, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
		head.rotationPointX += 0.6 * globalDegree * limbSwingAmount * Math.cos(limbSwing * 0.5F * globalSpeed);

		swing(upperLegR, 0.5F * globalSpeed, 0.1F * globalDegree, true, 0, -0.2F, limbSwing, limbSwingAmount);
		swing(upperLegL, 0.5F * globalSpeed, 0.1F * globalDegree, true, 0, 0.2F, limbSwing, limbSwingAmount);
		walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, limbSwing, limbSwingAmount);
		walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, limbSwing, limbSwingAmount);
		walk(lowerlegR1, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, limbSwing, limbSwingAmount);
		walk(lowerlegL1, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, limbSwing, limbSwingAmount);
		walk(upperArmR, 0.5F * globalSpeed, 0.7F * globalDegree, true, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		walk(upperArmL, 0.5F * globalSpeed, 0.7F * globalDegree, false, 0F, -0.3F * limbSwingAmount * backwardInverter, limbSwing, limbSwingAmount);
		walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);
		walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * limbSwingAmount, limbSwing, limbSwingAmount);

		if (player.isSneaking())
		{
			waist.rotationPointY -= limbSwingAmount;
			waist.rotateAngleX += 0.3F;
			waist.rotationPointZ -= 0F;
			waist.rotationPointY += 0.5F;

			head.rotateAngleX -= 0.3;
			upperLegR.rotateAngleX -= 0.7;
			upperLegL.rotateAngleX -= 0.7;
			lowerlegR1.rotateAngleX += 0.7;
			lowerlegL1.rotateAngleX += 0.7;
			upperArmR.rotateAngleX -= 0.3;
			upperArmL.rotateAngleX -= 0.3;
			upperArmR.rotateAngleZ += 0.5;
			upperArmL.rotateAngleZ -= 0.5;
			lowerArmR.rotateAngleZ -= 0.5;
			lowerArmL.rotateAngleZ += 0.5;
		}
	}

	@Override
	public void doIdleAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		walk(stomach, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
		walk(chestmain1, 0.08F, 0.15F, false, 1, 0, ticks, 1F);
		walk(head, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
		walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
		walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticks, 1F);

		flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticks, 1F);
		flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticks, 1F);
		walk(lowerArmR, 0.08F, 0.1F, true, 1, 0, ticks, 1F);
		walk(lowerArmL, 0.08F, 0.1F, true, 1, 0, ticks, 1F);

		float idleSpeed = 0.15F;

		walk(antenna1, idleSpeed * 1, 0.1F, false, 0, 0, ticks, 1);
		walk(antenna2, idleSpeed * 1, 0.1F, true, 0, 0, ticks, 1);

		swing(dish1, idleSpeed * 0.5F, 0.5F, false, 0, 0, ticks, 1);

		bob(clawL1, idleSpeed * 1, 0.2F, false, ticks, 1);
		bob(clawL2, idleSpeed * 1, 0.2F, false, ticks, 1);

		chestwheel1.rotateAngleX += ticks * idleSpeed;
		chestwheel2.rotateAngleX += ticks * idleSpeed;
	}

	@Override
	public void doFallingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs)
	{
		double motionY = TFRenderHelper.getMotionY(player);

		float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
		float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));

		waist.rotateAngleX += 0.2 * limbSwingAmount * backwardInverter;

		stomach.rotateAngleX += 0.2 * upwardPose;
		chestmain1.rotateAngleX -= 0.4 * upwardPose;
		head.rotateAngleX += 0.6 * upwardPose;

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
		stomach.rotateAngleX += 0.3 * downwardPose;
		chestmain1.rotateAngleX += 0.3 * downwardPose;
		head.rotateAngleX += 0.3 * downwardPose;
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
			waist.rotateAngleX += 0.4F;
			waist.rotationPointZ += 4F;
			waist.rotationPointY -= 1;

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
		ModelSubwooferVehicle vehicle = (ModelSubwooferVehicle)getTransformerModel().getVehicleModel();

		rotateTo(waist, vehicle.vehicleBase, f);
		rotateTo(upperArmR, vehicle.vehicleUpperArmR, f);
		rotateTo(upperArmL, vehicle.vehicleUpperArmL, f);
		rotateTo(dish1, vehicle.vehicleDish1, f);
		rotateTo(dish2, vehicle.vehicleDish2, f);
		rotateTo(antenna1, vehicle.vehicleAntenna1, f);
		rotateTo(antenna2, vehicle.vehicleAntenna2, f);
		rotateTo(lowerlegR1, vehicle.vehicleLowerLegR1, f);
		rotateTo(lowerlegL1, vehicle.vehicleLowerLegL1, f);
		rotateTo(upperLegR, vehicle.vehicleUpperLegR, f);
		rotateTo(upperLegL, vehicle.vehicleUpperLegL, f);
		rotateTo(lowerArmR, vehicle.vehicleLowerArmRL1, f);
		rotateTo(lowerArmL, vehicle.vehicleLowerArmL1, f);
		rotateTo(chestmain1, vehicle.vehicleChestMain1, f);
		rotateTo(chestmain2, vehicle.vehicleChestMain2, f);
		rotateTo(chestmain3, vehicle.vehicleChestMain3, f);
		rotateTo(chestmain5, vehicle.vehicleChestMain5, f);
		rotateTo(chestmain6, vehicle.vehicleChestMain6, f);
		rotateTo(frontchestR, vehicle.vehicleFrontChest1, f);
		rotateTo(frontchestL, vehicle.vehicleFrontChest2, f);
		rotateTo(bass2, vehicle.vehicleBass2, f);
		rotateTo(bass3, vehicle.vehicleBass3, f);
		rotateTo(crotch1, vehicle.vehicleCrotch1, f);
		rotateTo(lowerlegL3, vehicle.vehicleLowerLegL3, f);
		rotateTo(lowerlegR3, vehicle.vehicleLowerLegR3, f);
		rotateTo(stomach, vehicle.vehicleStomach, f);
		rotateTo(shoulderbaseR, vehicle.vehicleShoulderBase1, f);
		rotateTo(shoulderbaseL, vehicle.vehicleShoulderBase2, f);
		shoulderplateR1.rotationPointX += f * 0.2F;
		shoulderplateL1.rotationPointX -= f * 0.2F;
		shoulderplateR1.rotationPointZ -= f * 0.2F;
		shoulderplateL1.rotationPointZ -= f * 0.2F;
		head.rotationPointY += f * 0.2F;

		for (ModelRenderer modelRenderer : new ModelRenderer[] {vehicle.vehicleFrontWheel1, vehicle.vehicleFrontWheel2, vehicle.vehicleRearWheel1, vehicle.vehicleRearWheel2})
		{
			VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

			if (transformedPlayer != null)
			{
				float wheelSpinSpeed = (transformedPlayer.getForwardVelocity() < 0 ? -limbSwing : limbSwing) * 0.8F;
				modelRenderer.rotateAngleX = wheelSpinSpeed;
			}
		}

		vehicle.vehicleBase.rotateAngleY = bipedBody.rotateAngleY;

		if (player == Minecraft.getMinecraft().thePlayer)
		{
			vehicle.vehicleBase.rotateAngleX = -(float) player.motionY - 0.0784000015258789F;
		}
		else
		{
			vehicle.vehicleBase.rotateAngleX = -(float) (player.posY - player.prevPosY) * 1.5F;
		}
	}

	@Override
	public void renderArmorPiece(int armorPiece)
	{
		setToInitPose();

		if (armorPiece == 0)
		{
			GL11.glTranslatef(0, 0.3F, -0.03125F);
			head.render(0.0625F);
		}
		else if (armorPiece == 1)
		{
			GL11.glTranslatef(0, 0, 0.0625F);
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
			GL11.glRotatef(5, 1, 0, 0);
			GL11.glTranslatef(0, 0.125F, 0);
			lowerlegL3.showModel = false;
			lowerlegL4.showModel = false;
			lowerlegR3.showModel = false;
			lowerlegR4.showModel = false;
			legbaseL.showModel = false;
			legbaseR.showModel = false;
			upperLegL.render(0.0625F);
			upperLegR.render(0.0625F);
			lowerlegL3.showModel = true;
			lowerlegL4.showModel = true;
			lowerlegR3.showModel = true;
			lowerlegR4.showModel = true;
			legbaseL.showModel = true;
			legbaseR.showModel = true;
		}
		else if (armorPiece == 3)
		{
			GL11.glTranslatef(0, 0, -0.0625F);
			GL11.glRotatef(8, 1, 0, 0);
			legbaseL.rotationPointX -= 3;
			legbaseR.rotationPointX += 3;
			legbaseL.rotateAngleX += 0.2F;
			legbaseL.rotateAngleY += 0.2F;
			legbaseR.rotateAngleX += 0.2F;
			legbaseR.rotateAngleY -= 0.2F;
			legbaseL.render(0.0625F);
			legbaseR.render(0.0625F);
		}
	}
}
