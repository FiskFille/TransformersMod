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
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ModelSubwoofer extends ModelTransformerBase
{
    public MowzieModelRenderer waist;
    public MowzieModelRenderer stomach;
    public MowzieModelRenderer crotch1;
    public MowzieModelRenderer sideflapL;
    public MowzieModelRenderer crotchbuttonR;
    public MowzieModelRenderer crotchbuttonL;
    public MowzieModelRenderer sideflapL_1;
    public MowzieModelRenderer chestmain1;
    public MowzieModelRenderer chestwheel1;
    public MowzieModelRenderer chestwheel2;
    public MowzieModelRenderer frontchestR;
    public MowzieModelRenderer frontchestL;
    public MowzieModelRenderer chestmain2;
    public MowzieModelRenderer chestmain3;
    public MowzieModelRenderer chestmain4;
    public MowzieModelRenderer chestmain5;
    public MowzieModelRenderer chestmain6;
    public MowzieModelRenderer head;
    public MowzieModelRenderer dish1;
    public MowzieModelRenderer shoulderbaseL;
    public MowzieModelRenderer shoulderbaseR;
    public MowzieModelRenderer antenna1;
    public MowzieModelRenderer antenna2;
    public MowzieModelRenderer head1;
    public MowzieModelRenderer head2;
    public MowzieModelRenderer headplate1;
    public MowzieModelRenderer head3;
    public MowzieModelRenderer head4;
    public MowzieModelRenderer head5;
    public MowzieModelRenderer head6;
    public MowzieModelRenderer head7;
    public MowzieModelRenderer headplate2;
    public MowzieModelRenderer headplate2_1;
    public MowzieModelRenderer dish2;
    public MowzieModelRenderer shoulderplateL1;
    public MowzieModelRenderer upperArmL;
    public MowzieModelRenderer shoulderplate2;
    public MowzieModelRenderer shoulderplate3;
    public MowzieModelRenderer lowerArmL;
    public MowzieModelRenderer lowerarmL2;
    public MowzieModelRenderer lowerarmL3;
    public MowzieModelRenderer fistL;
    public MowzieModelRenderer clawL1;
    public MowzieModelRenderer clawL2;
    public MowzieModelRenderer upperArmR;
    public MowzieModelRenderer shoulderplateR1;
    public MowzieModelRenderer lowerArmR;
    public MowzieModelRenderer fistR;
    public MowzieModelRenderer bass1;
    public MowzieModelRenderer bass2;
    public MowzieModelRenderer bass3;
    public MowzieModelRenderer bass4;
    public MowzieModelRenderer bass5;
    public MowzieModelRenderer bass6;
    public MowzieModelRenderer shoulderplateR3;
    public MowzieModelRenderer shoulderplateR2;
    public MowzieModelRenderer upperLegL;
    public MowzieModelRenderer crotch2;
    public MowzieModelRenderer upperLegR;
    public MowzieModelRenderer lowerlegL1;
    public MowzieModelRenderer lowerlegL4;
    public MowzieModelRenderer legbaseL;
    public MowzieModelRenderer lowerlegL2;
    public MowzieModelRenderer lowerlegL3;
    public MowzieModelRenderer leg1;
    public MowzieModelRenderer legL2;
    public MowzieModelRenderer legL3;
    public MowzieModelRenderer lowerlegR1;
    public MowzieModelRenderer lowerlegR2;
    public MowzieModelRenderer lowerlegR3;
    public MowzieModelRenderer legbaseR;
    public MowzieModelRenderer lowerlegR4;
    public MowzieModelRenderer legR1;
    public MowzieModelRenderer legR2;
    public MowzieModelRenderer legR3;

    public ModelRenderer vehicleBase;
    public ModelRenderer vehicleStomach;
    public ModelRenderer vehicleCrotch1;
    public ModelRenderer vehicleRear1;
    public ModelRenderer vehicleRear2;
    public ModelRenderer vehicleChestMain1;
    public ModelRenderer vehicleFrontWheel1;
    public ModelRenderer vehicleFrontWheel2;
    public ModelRenderer vehicleFrontChest1;
    public ModelRenderer vehicleFrontChest2;
    public ModelRenderer vehicleChestMain2;
    public ModelRenderer vehicleChestMain3;
    public ModelRenderer vehicleChestMain5;
    public ModelRenderer vehicleChestMain6;
    public ModelRenderer vehicleDish1;
    public ModelRenderer vehicleShoulderBase2;
    public ModelRenderer vehicleShoulderBase1;
    public ModelRenderer vehicleCover1;
    public ModelRenderer vehicleCover2;
    public ModelRenderer vehicleCover3;
    public ModelRenderer vehicleCover4;
    public ModelRenderer vehicleCover5;
    public ModelRenderer vehicleCover6;
    public ModelRenderer vehicleHood;
    public ModelRenderer vehicleBaseplate;
    public ModelRenderer vehicleAntenna1;
    public ModelRenderer vehicleAntenna2;
    public ModelRenderer vehicleDish2;
    public ModelRenderer vehicleUpperArmL;
    public ModelRenderer vehicleLowerArmL1;
    public ModelRenderer vehicleUpperArmR;
    public ModelRenderer vehicleLowerArmRL1;
    public ModelRenderer vehicleBass2;
    public ModelRenderer vehicleBass3;
    public ModelRenderer vehicleFront1;
    public ModelRenderer vehicleFront2;
    public ModelRenderer vehicleUpperLegL;
    public ModelRenderer vehicleUpperLegR;
    public ModelRenderer vehicleLowerLegL1;
    public ModelRenderer vehicleRearWheel2;
    public ModelRenderer vehicleLowerLegL3;
    public ModelRenderer vehicleLowerLegR1;
    public ModelRenderer vehicleLowerLegR3;
    public ModelRenderer vehicleRearWheel1;

    public ModelSubwoofer()
    {
        textureWidth = 128;
        textureHeight = 128;

        crotchbuttonR = new MowzieModelRenderer(this, 5, 112);
        crotchbuttonR.setRotationPoint(-3.0F, 0.5F, -2.7F);
        crotchbuttonR.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        setRotateAngle(crotchbuttonR, 0.0F, 0.08726646259971647F, 0.0F);
        shoulderbaseR = new MowzieModelRenderer(this, 48, 78);
        shoulderbaseR.setRotationPoint(-4.0F, -2.9F, 0.0F);
        shoulderbaseR.addBox(-2.9F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        setRotateAngle(shoulderbaseR, 0.0F, 0.0F, 0.05235987755982988F);
        shoulderplate2 = new MowzieModelRenderer(this, 48, 90);
        shoulderplate2.setRotationPoint(0.0F, 0.0F, 3.0F);
        shoulderplate2.addBox(1.0F, 0.0F, 0.0F, 4, 1, 3, 0.0F);
        setRotateAngle(shoulderplate2, -1.2915436464758039F, 0.0F, 0.0F);
        fistL = new MowzieModelRenderer(this, 52, 102);
        fistL.setRotationPoint(0.0F, 4.2F, 0.0F);
        fistL.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        setRotateAngle(fistL, -0.03490658503988659F, 0.06981317007977318F, 0.24434609527920614F);
        lowerArmL = new MowzieModelRenderer(this, 48, 94);
        lowerArmL.setRotationPoint(0.0F, 4.0F, 0.0F);
        lowerArmL.addBox(-1.3F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        setRotateAngle(lowerArmL, -0.19198621771937624F, 0.06981317007977318F, 0.10471975511965977F);
        lowerlegL3 = new MowzieModelRenderer(this, 79, 82);
        lowerlegL3.setRotationPoint(-2.0F, -1.0F, -0.2F);
        lowerlegL3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        setRotateAngle(lowerlegL3, -0.03490658503988659F, 0.0F, 0.0F);
        chestmain1 = new MowzieModelRenderer(this, 0, 67);
        chestmain1.setRotationPoint(0.0F, -6.0F, 0.0F);
        chestmain1.addBox(-4.0F, -4.0F, -3.0F, 8, 4, 6, 0.0F);
        fistR = new MowzieModelRenderer(this, 52, 102);
        fistR.mirror = true;
        fistR.setRotationPoint(0.0F, 4.2F, 0.0F);
        fistR.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        setRotateAngle(fistR, -0.03490658503988659F, -0.06981317007977318F, -0.24434609527920614F);
        lowerlegR3 = new MowzieModelRenderer(this, 79, 82);
        lowerlegR3.mirror = true;
        lowerlegR3.setRotationPoint(2.0F, -1.0F, -0.2F);
        lowerlegR3.addBox(-1.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        setRotateAngle(lowerlegR3, -0.03490658503988659F, 0.0F, 0.0F);
        head4 = new MowzieModelRenderer(this, 48, 69);
        head4.setRotationPoint(0.9F, 0.0F, -0.9F);
        head4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F);
        setRotateAngle(head4, 0.03490658503988659F, -0.03490658503988659F, 0.0F);
        shoulderplate3 = new MowzieModelRenderer(this, 61, 85);
        shoulderplate3.setRotationPoint(4.5F, -1.0F, -2.0F);
        shoulderplate3.addBox(-1.0F, 0.0F, -0.1F, 1, 2, 5, 0.0F);
        setRotateAngle(shoulderplate3, 0.0F, 0.19896753472735357F, -0.017453292519943295F);
        bass5 = new MowzieModelRenderer(this, 60, 92);
        bass5.setRotationPoint(-2.0F, 1.5F, -0.5F);
        bass5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        setRotateAngle(bass5, 1.5707963267948966F, 1.9198621771937625F, 0.0F);
        shoulderplateL1 = new MowzieModelRenderer(this, 48, 83);
        shoulderplateL1.setRotationPoint(0.0F, -1.0F, 0.0F);
        shoulderplateL1.addBox(0.5F, -1.0F, -2.0F, 4, 2, 5, 0.0F);
        setRotateAngle(shoulderplateL1, 0.08726646259971647F, 0.0F, 0.03490658503988659F);
        leg1 = new MowzieModelRenderer(this, 86, 64);
        leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        leg1.addBox(0.1F, 0.0F, -1.5F, 1, 3, 3, 0.0F);
        sideflapL = new MowzieModelRenderer(this, 0, 112);
        sideflapL.setRotationPoint(3.5F, 0.0F, 2.0F);
        sideflapL.addBox(-0.3F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
        setRotateAngle(sideflapL, 0.0F, -0.06981317007977318F, -0.06981317007977318F);
        shoulderbaseL = new MowzieModelRenderer(this, 48, 78);
        shoulderbaseL.setRotationPoint(4.0F, -2.9F, 0.0F);
        shoulderbaseL.addBox(-0.1F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        setRotateAngle(shoulderbaseL, 0.0F, 0.0F, -0.05235987755982988F);
        lowerlegR4 = new MowzieModelRenderer(this, 76, 76);
        lowerlegR4.mirror = true;
        lowerlegR4.setRotationPoint(-1.0F, 4.5F, -1.0F);
        lowerlegR4.addBox(-0.7F, -0.3F, -0.5F, 1, 2, 4, 0.0F);
        setRotateAngle(lowerlegR4, 0.0F, 0.0F, 0.19198621771937624F);
        legR3 = new MowzieModelRenderer(this, 83, 79);
        legR3.mirror = true;
        legR3.setRotationPoint(-0.4F, 2.2F, 0.0F);
        legR3.addBox(-1.1F, -2.7F, -3.2F, 2, 1, 3, 0.0F);
        setRotateAngle(legR3, 0.4468042885105484F, -0.012217304763960306F, 0.041887902047863905F);
        waist = new MowzieModelRenderer(this, 0, 98);
        waist.setRotationPoint(0.0F, 9.0F, -0.5F);
        waist.addBox(-3.5F, 0.0F, -2.5F, 7, 2, 5, 0.0F);
        antenna1 = new MowzieModelRenderer(this, 30, 67);
        antenna1.setRotationPoint(1.0F, 0.1F, 1.0F);
        antenna1.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        setRotateAngle(antenna1, -0.05235987755982988F, 0.0F, -0.05235987755982988F);
        dish1 = new MowzieModelRenderer(this, 0, 67);
        dish1.setRotationPoint(0.0F, -4.0F, 2.0F);
        dish1.addBox(-2.0F, -3.0F, 0.0F, 2, 3, 1, 0.0F);
        setRotateAngle(dish1, -0.17453292519943295F, -0.05235987755982988F, -0.05235987755982988F);
        lowerlegR1 = new MowzieModelRenderer(this, 76, 66);
        lowerlegR1.mirror = true;
        lowerlegR1.setRotationPoint(-1.0F, 5.0F, -1.0F);
        lowerlegR1.addBox(-1.7F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        setRotateAngle(lowerlegR1, 0.17453292519943295F, 0.017453292519943295F, -0.06981317007977318F);
        bass2 = new MowzieModelRenderer(this, 0, 112);
        bass2.setRotationPoint(-2.5F, 2.0F, -1.5F);
        bass2.addBox(-0.2F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        frontchestR = new MowzieModelRenderer(this, 10, 77);
        frontchestR.setRotationPoint(-4.0F, -4.0F, -3.0F);
        frontchestR.addBox(0.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        setRotateAngle(frontchestR, -0.07330382858376185F, 0.03839724354387525F, -0.005235987755982988F);
        shoulderplateR3 = new MowzieModelRenderer(this, 61, 85);
        shoulderplateR3.setRotationPoint(-4.5F, -1.0F, -2.0F);
        shoulderplateR3.addBox(0.0F, 0.0F, -0.1F, 1, 2, 5, 0.0F);
        setRotateAngle(shoulderplateR3, 0.0F, -0.19896753472735357F, 0.017453292519943295F);
        lowerlegL1 = new MowzieModelRenderer(this, 76, 66);
        lowerlegL1.setRotationPoint(1.0F, 5.0F, -1.0F);
        lowerlegL1.addBox(-1.3F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        setRotateAngle(lowerlegL1, 0.17453292519943295F, -0.017453292519943295F, 0.06981317007977318F);
        legR2 = new MowzieModelRenderer(this, 83, 70);
        legR2.mirror = true;
        legR2.setRotationPoint(-0.2F, 2.2F, 0.0F);
        legR2.addBox(-1.3F, -1.2F, -4.1F, 2, 2, 7, 0.0F);
        setRotateAngle(legR2, 0.027925268031909273F, -0.012217304763960306F, 0.041887902047863905F);
        crotch1 = new MowzieModelRenderer(this, 0, 105);
        crotch1.setRotationPoint(0.0F, 2.0F, 0.0F);
        crotch1.addBox(-1.0F, -0.7F, -2.4F, 2, 3, 4, 0.0F);
        setRotateAngle(crotch1, 0.2617993877991494F, 0.0F, 0.0F);
        stomach = new MowzieModelRenderer(this, 0, 58);
        stomach.setRotationPoint(0.0F, 1.0F, 0.0F);
        stomach.addBox(-2.5F, -5.5F, -2.0F, 5, 5, 4, 0.0F);
        shoulderplateR1 = new MowzieModelRenderer(this, 48, 83);
        shoulderplateR1.setRotationPoint(0.0F, -1.0F, 0.0F);
        shoulderplateR1.addBox(-4.5F, -1.0F, -2.0F, 4, 2, 5, 0.0F);
        setRotateAngle(shoulderplateR1, 0.08726646259971647F, 0.0F, -0.03490658503988659F);
        bass3 = new MowzieModelRenderer(this, 0, 112);
        bass3.mirror = true;
        bass3.setRotationPoint(-2.5F, -2.0F, -1.5F);
        bass3.addBox(-0.2F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        lowerlegL2 = new MowzieModelRenderer(this, 91, 64);
        lowerlegL2.setRotationPoint(0.0F, -1.5F, -1.5F);
        lowerlegL2.addBox(-1.3F, -1.0F, 0.0F, 3, 1, 1, 0.0F);
        setRotateAngle(lowerlegL2, -0.15707963267948966F, 0.0F, 0.0F);
        chestmain4 = new MowzieModelRenderer(this, 20, 77);
        chestmain4.setRotationPoint(-3.0F, 0.0F, 5.0F);
        chestmain4.addBox(0.0F, 0.0F, -2.0F, 6, 5, 2, 0.0F);
        setRotateAngle(chestmain4, -1.0297442586766543F, 0.0F, 0.0F);
        head1 = new MowzieModelRenderer(this, 48, 64);
        head1.setRotationPoint(0.0F, -3.0F, -1.0F);
        head1.addBox(-0.5F, -1.5F, -0.4F, 1, 2, 3, 0.0F);
        setRotateAngle(head1, -0.22689280275926282F, 0.0F, 0.0F);
        head2 = new MowzieModelRenderer(this, 56, 64);
        head2.setRotationPoint(1.3F, -3.0F, -1.0F);
        head2.addBox(-0.5F, -1.0F, -0.2F, 1, 2, 3, 0.0F);
        setRotateAngle(head2, -0.12217304763960307F, 0.0F, 0.20943951023931953F);
        crotch2 = new MowzieModelRenderer(this, 8, 105);
        crotch2.setRotationPoint(0.0F, 1.0F, 1.0F);
        crotch2.addBox(-1.0F, -0.9F, 0.0F, 2, 2, 1, 0.0F);
        setRotateAngle(crotch2, -0.2617993877991494F, 0.0F, 0.0F);
        legbaseL = new MowzieModelRenderer(this, 86, 58);
        legbaseL.setRotationPoint(0.4F, 4.4F, 0.7F);
        legbaseL.addBox(-2.1F, 0.0F, -1.5F, 2, 3, 3, 0.0F);
        setRotateAngle(legbaseL, -0.17453292519943295F, 0.03490658503988659F, 0.05235987755982988F);
        sideflapL_1 = new MowzieModelRenderer(this, 0, 112);
        sideflapL_1.mirror = true;
        sideflapL_1.setRotationPoint(-3.5F, 0.0F, 2.0F);
        sideflapL_1.addBox(-0.7F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
        setRotateAngle(sideflapL_1, 0.0F, 0.06981317007977318F, 0.06981317007977318F);
        chestwheel1 = new MowzieModelRenderer(this, 0, 77);
        chestwheel1.setRotationPoint(-2.5F, 1F, -0.5F);
        chestwheel1.addBox(-1, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        setRotateAngle(chestwheel1, -0.08726646259971647F, 0.0F, 0.13962634015954636F);
        head7 = new MowzieModelRenderer(this, 54, 69);
        head7.mirror = true;
        head7.setRotationPoint(1.8F, -3.07F, 1.1F);
        head7.addBox(-0.97F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        setRotateAngle(head7, 0.0F, 0.017453292519943295F, 0.0F);
        legL2 = new MowzieModelRenderer(this, 83, 70);
        legL2.setRotationPoint(-0.2F, 2.2F, 0.0F);
        legL2.addBox(-1.1F, -1.2F, -4.1F, 2, 2, 7, 0.0F);
        setRotateAngle(legL2, 0.027925268031909273F, 0.012217304763960306F, -0.041887902047863905F);
        head5 = new MowzieModelRenderer(this, 48, 69);
        head5.mirror = true;
        head5.setRotationPoint(-0.9F, 0.0F, -0.9F);
        head5.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F);
        setRotateAngle(head5, 0.03490658503988659F, 0.03490658503988659F, 0.0F);
        upperLegR = new MowzieModelRenderer(this, 76, 58);
        upperLegR.mirror = true;
        upperLegR.setRotationPoint(-1.0F, 1.0F, 0.0F);
        upperLegR.addBox(-2.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        setRotateAngle(upperLegR, -0.2617993877991494F, 0.10471975511965977F, 0.08726646259971647F);
        lowerArmR = new MowzieModelRenderer(this, 48, 94);
        lowerArmR.mirror = true;
        lowerArmR.setRotationPoint(0.0F, 4.0F, 0.0F);
        lowerArmR.addBox(-1.7F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        setRotateAngle(lowerArmR, -0.19198621771937624F, -0.06981317007977318F, -0.10471975511965977F);
        bass1 = new MowzieModelRenderer(this, 60, 92);
        bass1.setRotationPoint(-2.0F, 5.0F, -0.5F);
        bass1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        setRotateAngle(bass1, 1.5707963267948966F, 1.9198621771937625F, 0.0F);
        lowerlegR2 = new MowzieModelRenderer(this, 91, 64);
        lowerlegR2.mirror = true;
        lowerlegR2.setRotationPoint(0.0F, -1.5F, -1.5F);
        lowerlegR2.addBox(-1.7F, -1.0F, 0.0F, 3, 1, 1, 0.0F);
        setRotateAngle(lowerlegR2, -0.15707963267948966F, 0.0F, 0.0F);
        frontchestL = new MowzieModelRenderer(this, 10, 77);
        frontchestL.mirror = true;
        frontchestL.setRotationPoint(4.0F, -4.0F, -3.0F);
        frontchestL.addBox(-4.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        setRotateAngle(frontchestL, -0.07330382858376185F, -0.03839724354387525F, 0.005235987755982988F);
        headplate2_1 = new MowzieModelRenderer(this, 47, 65);
        headplate2_1.mirror = true;
        headplate2_1.setRotationPoint(-1.0F, 0.0F, -0.3F);
        headplate2_1.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        setRotateAngle(headplate2_1, 0.0F, 0.45029494701453704F, 0.0F);
        antenna2 = new MowzieModelRenderer(this, 30, 67);
        antenna2.setRotationPoint(5.0F, 0.1F, 1.0F);
        antenna2.addBox(-0.5F, -7.0F, -0.5F, 1, 7, 1, 0.0F);
        setRotateAngle(antenna2, -0.05235987755982988F, 0.0F, 0.03490658503988659F);
        clawL1 = new MowzieModelRenderer(this, 48, 102);
        clawL1.setRotationPoint(1.0F, 0.0F, 0.3F);
        clawL1.addBox(0.0F, -0.5F, 0.0F, 1, 5, 1, 0.0F);
        setRotateAngle(clawL1, 0.0F, 0.0F, 0.08726646259971647F);
        lowerarmL3 = new MowzieModelRenderer(this, 60, 97);
        lowerarmL3.setRotationPoint(0.0F, 4.0F, 0.0F);
        lowerarmL3.addBox(0.7F, 0.4F, -1.5F, 1, 2, 3, 0.0F);
        chestmain3 = new MowzieModelRenderer(this, 0, 83);
        chestmain3.setRotationPoint(-3.0F, -4.0F, 3.0F);
        chestmain3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
        headplate2 = new MowzieModelRenderer(this, 47, 65);
        headplate2.setRotationPoint(1.0F, 0.0F, -0.3F);
        headplate2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        setRotateAngle(headplate2, 0.0F, -0.45029494701453704F, 0.0F);
        bass4 = new MowzieModelRenderer(this, 60, 92);
        bass4.mirror = true;
        bass4.setRotationPoint(-2.0F, 5.0F, 1.5F);
        bass4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        setRotateAngle(bass4, 1.5707963267948966F, 1.2217304763960306F, 0.0F);
        crotchbuttonL = new MowzieModelRenderer(this, 5, 112);
        crotchbuttonL.mirror = true;
        crotchbuttonL.setRotationPoint(3.0F, 0.5F, -2.7F);
        crotchbuttonL.addBox(-2.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        setRotateAngle(crotchbuttonL, 0.0F, -0.08726646259971647F, 0.0F);
        chestmain2 = new MowzieModelRenderer(this, 18, 62);
        chestmain2.setRotationPoint(-3.0F, 0.0F, -4.0F);
        chestmain2.addBox(0.0F, 0.0F, 0.3F, 6, 3, 2, 0.0F);
        setRotateAngle(chestmain2, 0.33161255787892263F, 0.0F, 0.0F);
        head3 = new MowzieModelRenderer(this, 56, 64);
        head3.mirror = true;
        head3.setRotationPoint(-1.3F, -3.0F, -1.0F);
        head3.addBox(-0.5F, -1.0F, -0.2F, 1, 2, 3, 0.0F);
        setRotateAngle(head3, -0.12217304763960307F, 0.0F, -0.20943951023931953F);
        head = new MowzieModelRenderer(this, 48, 58);
        head.setRotationPoint(0.0F, -4.0F, -1.5F);
        head.addBox(-1.5F, -3.0F, -1.0F, 3, 3, 3, 0.0F);
        legbaseR = new MowzieModelRenderer(this, 86, 58);
        legbaseR.mirror = true;
        legbaseR.setRotationPoint(0.4F, 4.4F, 0.7F);
        legbaseR.addBox(-0.7F, 0.0F, -1.5F, 2, 3, 3, 0.0F);
        setRotateAngle(legbaseR, -0.17453292519943295F, -0.03490658503988659F, -0.05235987755982988F);
        head6 = new MowzieModelRenderer(this, 54, 69);
        head6.setRotationPoint(-1.8F, -3.07F, 1.1F);
        head6.addBox(-0.03F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        setRotateAngle(head6, 0.0F, 0.017453292519943295F, 0.0F);
        upperLegL = new MowzieModelRenderer(this, 76, 58);
        upperLegL.setRotationPoint(1.0F, 1.0F, 0.0F);
        upperLegL.addBox(0.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        setRotateAngle(upperLegL, -0.2617993877991494F, -0.10471975511965977F, -0.08726646259971647F);
        lowerlegL4 = new MowzieModelRenderer(this, 76, 76);
        lowerlegL4.setRotationPoint(1.0F, 4.5F, -1.0F);
        lowerlegL4.addBox(-0.3F, -0.3F, -0.5F, 1, 2, 4, 0.0F);
        setRotateAngle(lowerlegL4, 0.0F, 0.0F, -0.19198621771937624F);
        upperArmL = new MowzieModelRenderer(this, 61, 79);
        upperArmL.setRotationPoint(1.7F, 1.0F, 0.0F);
        upperArmL.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        setRotateAngle(upperArmL, 0.03490658503988659F, 0.0F, -0.10471975511965977F);
        chestmain6 = new MowzieModelRenderer(this, 0, 89);
        chestmain6.mirror = true;
        chestmain6.setRotationPoint(-3.0F, -4.0F, 0.0F);
        chestmain6.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        setRotateAngle(chestmain6, -0.06981317007977318F, -0.12217304763960307F, -0.12217304763960307F);
        upperArmR = new MowzieModelRenderer(this, 61, 79);
        upperArmR.setRotationPoint(-1.7F, 1.0F, 0.0F);
        upperArmR.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        setRotateAngle(upperArmR, 0.03490658503988659F, 0.0F, 0.10471975511965977F);
        legR1 = new MowzieModelRenderer(this, 86, 64);
        legR1.mirror = true;
        legR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        legR1.addBox(-1.9F, 0.0F, -1.5F, 1, 3, 3, 0.0F);
        chestmain5 = new MowzieModelRenderer(this, 0, 89);
        chestmain5.setRotationPoint(3.0F, -4.0F, 0.0F);
        chestmain5.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        setRotateAngle(chestmain5, -0.05235987755982988F, 0.12217304763960307F, 0.12217304763960307F);
        dish2 = new MowzieModelRenderer(this, 22, 67);
        dish2.setRotationPoint(0.0F, -3.0F, 1.0F);
        dish2.addBox(-2.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        setRotateAngle(dish2, 0.5235987755982988F, 0.0F, 0.0F);
        bass6 = new MowzieModelRenderer(this, 60, 92);
        bass6.mirror = true;
        bass6.setRotationPoint(-2.0F, 1.5F, 1.5F);
        bass6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        setRotateAngle(bass6, 1.5707963267948966F, 1.2217304763960306F, 0.0F);
        shoulderplateR2 = new MowzieModelRenderer(this, 48, 90);
        shoulderplateR2.setRotationPoint(0.0F, 0.0F, 3.0F);
        shoulderplateR2.addBox(-5.0F, 0.0F, 0.0F, 4, 1, 3, 0.0F);
        setRotateAngle(shoulderplateR2, -1.2915436464758039F, 0.0F, 0.0F);
        clawL2 = new MowzieModelRenderer(this, 48, 102);
        clawL2.mirror = true;
        clawL2.setRotationPoint(1.0F, 0.0F, -1.3F);
        clawL2.addBox(0.0F, -0.5F, 0.0F, 1, 5, 1, 0.0F);
        setRotateAngle(clawL2, 0.0F, 0.0F, 0.08726646259971647F);
        chestwheel2 = new MowzieModelRenderer(this, 0, 77);
        chestwheel2.mirror = true;
        chestwheel2.setRotationPoint(2.5F, 1.0F, -0.5F);
        chestwheel2.addBox(-1, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        setRotateAngle(chestwheel2, -0.08726646259971647F, 0.0F, -0.14608405839192537F);
        headplate1 = new MowzieModelRenderer(this, 53, 65);
        headplate1.setRotationPoint(0.0F, -1.0F, -1.0F);
        headplate1.addBox(-1.0F, 0.0F, -0.3F, 2, 1, 1, 0.0F);
        lowerarmL2 = new MowzieModelRenderer(this, 60, 92);
        lowerarmL2.setRotationPoint(0.0F, 4.0F, 0.0F);
        lowerarmL2.addBox(0.7F, -6.6F, -1.5F, 1, 2, 3, 0.0F);
        legL3 = new MowzieModelRenderer(this, 83, 79);
        legL3.setRotationPoint(-0.2F, 2.2F, 0.0F);
        legL3.addBox(-1.1F, -2.7F, -3.2F, 2, 1, 3, 0.0F);
        setRotateAngle(legL3, 0.4468042885105484F, 0.012217304763960306F, -0.041887902047863905F);

        setInitPose();

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

        vehicleCover6 = new ModelRenderer(this, 17, 0);
        vehicleCover6.setRotationPoint(4.1F, -1.4F, -2.4F);
        vehicleCover6.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
        setRotateAngle(vehicleCover6, 0.0F, 0.0F, -0.017453292519943295F);
        vehicleLowerArmRL1 = new ModelRenderer(this, 48, 94);
        vehicleLowerArmRL1.mirror = true;
        vehicleLowerArmRL1.setRotationPoint(1.4F, 4.5F, 0.6F);
        vehicleLowerArmRL1.addBox(-1.7F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        vehicleChestMain1 = new ModelRenderer(this, 0, 67);
        vehicleChestMain1.setRotationPoint(0.0F, -2.0F, 0.0F);
        vehicleChestMain1.addBox(-4.0F, -4.0F, -3.0F, 8, 4, 6, 0.0F);
        setRotateAngle(vehicleChestMain1, -1.5707963267948966F, 0.0F, 0.0F);
        vehicleCover3 = new ModelRenderer(this, 0, 0);
        vehicleCover3.mirror = true;
        vehicleCover3.setRotationPoint(-4.1F, -3.9F, -3.4F);
        vehicleCover3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 14, 0.0F);
        vehicleRear1 = new ModelRenderer(this, 0, 22);
        vehicleRear1.setRotationPoint(-4.0F, -0.5F, 7.0F);
        vehicleRear1.addBox(0.0F, 0.0F, 0.0F, 8, 4, 2, 0.0F);
        vehicleRear2 = new ModelRenderer(this, 0, 29);
        vehicleRear2.setRotationPoint(-4.0F, -2.5F, 8.0F);
        vehicleRear2.addBox(0.0F, -1.0F, 0.0F, 8, 3, 1, 0.0F);
        vehicleFrontChest2 = new ModelRenderer(this, 10, 77);
        vehicleFrontChest2.mirror = true;
        vehicleFrontChest2.setRotationPoint(4.0F, -4.0F, -3.0F);
        vehicleFrontChest2.addBox(-4.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        setRotateAngle(vehicleFrontChest2, -0.07330382858376185F, 0.0F, 0.005235987755982988F);
        vehicleLowerLegL3 = new ModelRenderer(this, 79, 82);
        vehicleLowerLegL3.setRotationPoint(0.7F, -1.5F, 2.5F);
        vehicleLowerLegL3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        vehicleLowerLegR1 = new ModelRenderer(this, 76, 66);
        vehicleLowerLegR1.mirror = true;
        vehicleLowerLegR1.setRotationPoint(-1.3F, 2.5F, -1.0F);
        vehicleLowerLegR1.addBox(-1.7F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        vehicleUpperLegL = new ModelRenderer(this, 76, 58);
        vehicleUpperLegL.setRotationPoint(1.0F, 1.0F, 0.0F);
        vehicleUpperLegL.addBox(0.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        setRotateAngle(vehicleUpperLegL, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleBaseplate = new ModelRenderer(this, 20, 2);
        vehicleBaseplate.setRotationPoint(-3.0F, 1.6F, -5.4F);
        vehicleBaseplate.addBox(0.0F, 0.0F, 0.0F, 6, 1, 16, 0.0F);
        vehicleLowerArmL1 = new ModelRenderer(this, 48, 94);
        vehicleLowerArmL1.setRotationPoint(-1.4F, 4.5F, 0.6F);
        vehicleLowerArmL1.addBox(-1.3F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        vehicleFront2 = new ModelRenderer(this, 0, 0);
        vehicleFront2.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleFront2.addBox(7.0F, 0.4F, -2.8F, 1, 3, 1, 0.0F);
        setRotateAngle(vehicleFront2, -0.13962634015954636F, 0.0F, 0.0F);
        vehicleUpperArmR = new ModelRenderer(this, 61, 79);
        vehicleUpperArmR.setRotationPoint(-1.7F, 1.0F, 0.0F);
        vehicleUpperArmR.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        vehicleRearWheel2 = new ModelRenderer(this, 0, 77);
        vehicleRearWheel2.setRotationPoint(0.2F, 4.4F, -0.5F);
        vehicleRearWheel2.addBox(0.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        vehicleDish2 = new ModelRenderer(this, 22, 67);
        vehicleDish2.setRotationPoint(0.0F, -3.0F, 1.0F);
        vehicleDish2.addBox(-2.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        setRotateAngle(vehicleDish2, 0.2792526803190927F, 0.0F, 0.0F);
        vehicleUpperLegR = new ModelRenderer(this, 76, 58);
        vehicleUpperLegR.mirror = true;
        vehicleUpperLegR.setRotationPoint(-1.0F, 1.0F, 0.0F);
        vehicleUpperLegR.addBox(-2.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        setRotateAngle(vehicleUpperLegR, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleCover2 = new ModelRenderer(this, 0, 42);
        vehicleCover2.setRotationPoint(-4.0F, -4.5F, 3.0F);
        vehicleCover2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 7, 0.0F);
        vehicleBase = new ModelRenderer(this, 0, 98);
        vehicleBase.setRotationPoint(0.0F, 20.0F, -0.5F);
        vehicleBase.addBox(-3.5F, 0.0F, -2.5F, 7, 2, 5, 0.0F);
        vehicleCover4 = new ModelRenderer(this, 0, 0);
        vehicleCover4.setRotationPoint(4.1F, -3.9F, -3.4F);
        vehicleCover4.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 14, 0.0F);
        setRotateAngle(vehicleCover4, 0.006283185307179587F, 0.0F, 0.0F);
        vehicleCover5 = new ModelRenderer(this, 17, 0);
        vehicleCover5.mirror = true;
        vehicleCover5.setRotationPoint(-4.1F, -1.4F, -2.4F);
        vehicleCover5.addBox(0.0F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
        setRotateAngle(vehicleCover5, 0.0F, 0.0F, 0.017453292519943295F);
        vehicleCrotch1 = new ModelRenderer(this, 0, 105);
        vehicleCrotch1.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleCrotch1.addBox(-1.0F, -0.7F, -2.4F, 2, 3, 4, 0.0F);
        vehicleChestMain2 = new ModelRenderer(this, 18, 62);
        vehicleChestMain2.setRotationPoint(-3.0F, -0.6F, -6.1F);
        vehicleChestMain2.addBox(0.0F, 0.0F, 0.3F, 6, 3, 2, 0.0F);
        setRotateAngle(vehicleChestMain2, 0.03490658503988659F, 0.0F, 0.0F);
        vehicleFrontWheel2 = new ModelRenderer(this, 0, 77);
        vehicleFrontWheel2.mirror = true;
        vehicleFrontWheel2.setRotationPoint(2.5F, 1.5F, -3.7F);
        vehicleFrontWheel2.addBox(0.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        vehicleLowerLegR3 = new ModelRenderer(this, 79, 82);
        vehicleLowerLegR3.mirror = true;
        vehicleLowerLegR3.setRotationPoint(-1.7F, -1.5F, 2.5F);
        vehicleLowerLegR3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        vehicleChestMain3 = new ModelRenderer(this, 0, 83);
        vehicleChestMain3.setRotationPoint(-3.0F, -4.0F, 3.0F);
        vehicleChestMain3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
        vehicleDish1 = new ModelRenderer(this, 0, 67);
        vehicleDish1.setRotationPoint(-4.0F, -4.6F, 6.2F);
        vehicleDish1.addBox(-2.0F, -3.0F, 0.0F, 2, 3, 1, 0.0F);
        setRotateAngle(vehicleDish1, 0.017453292519943295F, 0.12217304763960307F, 1.5707963267948966F);
        vehicleShoulderBase1 = new ModelRenderer(this, 48, 78);
        vehicleShoulderBase1.setRotationPoint(-1.0F, -1.9F, 0.0F);
        vehicleShoulderBase1.addBox(-2.9F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        setRotateAngle(vehicleShoulderBase1, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleUpperArmL = new ModelRenderer(this, 61, 79);
        vehicleUpperArmL.setRotationPoint(1.7F, 1.0F, 0.0F);
        vehicleUpperArmL.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        vehicleCover1 = new ModelRenderer(this, 0, 34);
        vehicleCover1.setRotationPoint(-3.0F, -4.5F, 3.0F);
        vehicleCover1.addBox(0.0F, 0.0F, -6.0F, 6, 1, 6, 0.0F);
        setRotateAngle(vehicleCover1, 0.08203047484373349F, 0.0F, 0.0F);
        vehicleAntenna1 = new ModelRenderer(this, 30, 67);
        vehicleAntenna1.setRotationPoint(0.0F, 1.1F, 6.0F);
        vehicleAntenna1.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        setRotateAngle(vehicleAntenna1, -0.05235987755982988F, 0.0F, 0.0F);
        vehicleFront1 = new ModelRenderer(this, 0, 0);
        vehicleFront1.mirror = true;
        vehicleFront1.setRotationPoint(0.0F, 0.0F, 0.0F);
        vehicleFront1.addBox(0.0F, 0.4F, -2.8F, 1, 3, 1, 0.0F);
        setRotateAngle(vehicleFront1, -0.13962634015954636F, 0.0F, 0.0F);
        vehicleChestMain5 = new ModelRenderer(this, 0, 89);
        vehicleChestMain5.setRotationPoint(2.9F, 0.5F, 3.0F);
        vehicleChestMain5.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        setRotateAngle(vehicleChestMain5, 3.141592653589793F, 0.0F, 0.0F);
        vehicleHood = new ModelRenderer(this, 0, 18);
        vehicleHood.setRotationPoint(-4.0F, -1.0F, -3.0F);
        vehicleHood.addBox(0.0F, 0.0F, -2.8F, 8, 1, 3, 0.0F);
        setRotateAngle(vehicleHood, 0.13962634015954636F, 0.0F, 0.0F);
        vehicleStomach = new ModelRenderer(this, 0, 58);
        vehicleStomach.setRotationPoint(0.0F, 1.0F, 0.0F);
        vehicleStomach.addBox(-2.5F, -5.5F, -2.0F, 5, 5, 4, 0.0F);
        setRotateAngle(vehicleStomach, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleFrontChest1 = new ModelRenderer(this, 10, 77);
        vehicleFrontChest1.setRotationPoint(-4.0F, -4.0F, -3.0F);
        vehicleFrontChest1.addBox(0.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        setRotateAngle(vehicleFrontChest1, -0.07330382858376185F, 0.0F, -0.005235987755982988F);
        vehicleBass3 = new ModelRenderer(this, 0, 112);
        vehicleBass3.mirror = true;
        vehicleBass3.setRotationPoint(-2.3F, -2.5F, 2.1F);
        vehicleBass3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        setRotateAngle(vehicleBass3, 0.0F, 1.5707963267948966F, 0.0F);
        vehicleFrontWheel1 = new ModelRenderer(this, 0, 77);
        vehicleFrontWheel1.setRotationPoint(-2.5F, 1.5F, -3.7F);
        vehicleFrontWheel1.addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        vehicleAntenna2 = new ModelRenderer(this, 30, 67);
        vehicleAntenna2.setRotationPoint(6.0F, 1.1F, 6.0F);
        vehicleAntenna2.addBox(-0.5F, -7.0F, -0.5F, 1, 7, 1, 0.0F);
        setRotateAngle(vehicleAntenna2, -0.05235987755982988F, 0.0F, 0.0F);
        vehicleLowerLegL1 = new ModelRenderer(this, 76, 66);
        vehicleLowerLegL1.setRotationPoint(1.3F, 2.5F, -1.0F);
        vehicleLowerLegL1.addBox(-1.3F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        setRotateAngle(vehicleLowerLegL1, 0.0F, 0.004886921905584123F, 0.0F);
        vehicleBass2 = new ModelRenderer(this, 0, 112);
        vehicleBass2.setRotationPoint(1.7F, -2.5F, 2.1F);
        vehicleBass2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        setRotateAngle(vehicleBass2, 0.0F, 1.5707963267948966F, 0.0F);
        vehicleChestMain6 = new ModelRenderer(this, 0, 89);
        vehicleChestMain6.mirror = true;
        vehicleChestMain6.setRotationPoint(-2.9F, 0.5F, 3.0F);
        vehicleChestMain6.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        setRotateAngle(vehicleChestMain6, 3.141592653589793F, 0.0F, 0.0F);
        vehicleShoulderBase2 = new ModelRenderer(this, 48, 78);
        vehicleShoulderBase2.setRotationPoint(1.0F, -1.9F, 0.0F);
        vehicleShoulderBase2.addBox(-0.1F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        setRotateAngle(vehicleShoulderBase2, 1.5707963267948966F, 0.0F, 0.0F);
        vehicleRearWheel1 = new ModelRenderer(this, 0, 77);
        vehicleRearWheel1.mirror = true;
        vehicleRearWheel1.setRotationPoint(-0.2F, 4.4F, -0.5F);
        vehicleRearWheel1.addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);

        vehicleChestMain1.addChild(vehicleCover6);
        vehicleUpperArmR.addChild(vehicleLowerArmRL1);
        vehicleStomach.addChild(vehicleChestMain1);
        vehicleChestMain1.addChild(vehicleCover3);
        vehicleBase.addChild(vehicleRear1);
        vehicleBase.addChild(vehicleRear2);
        vehicleChestMain1.addChild(vehicleFrontChest2);
        vehicleLowerLegL1.addChild(vehicleLowerLegL3);
        vehicleUpperLegR.addChild(vehicleLowerLegR1);
        vehicleCrotch1.addChild(vehicleUpperLegL);
        vehicleChestMain1.addChild(vehicleBaseplate);
        vehicleUpperArmL.addChild(vehicleLowerArmL1);
        vehicleHood.addChild(vehicleFront2);
        vehicleShoulderBase1.addChild(vehicleUpperArmR);
        vehicleLowerLegL1.addChild(vehicleRearWheel2);
        vehicleDish1.addChild(vehicleDish2);
        vehicleCrotch1.addChild(vehicleUpperLegR);
        vehicleChestMain1.addChild(vehicleCover2);
        vehicleChestMain1.addChild(vehicleCover4);
        vehicleChestMain1.addChild(vehicleCover5);
        vehicleBase.addChild(vehicleCrotch1);
        vehicleChestMain1.addChild(vehicleChestMain2);
        vehicleChestMain1.addChild(vehicleFrontWheel2);
        vehicleLowerLegR1.addChild(vehicleLowerLegR3);
        vehicleChestMain1.addChild(vehicleChestMain3);
        vehicleChestMain1.addChild(vehicleDish1);
        vehicleChestMain1.addChild(vehicleShoulderBase1);
        vehicleShoulderBase2.addChild(vehicleUpperArmL);
        vehicleChestMain1.addChild(vehicleCover1);
        vehicleChestMain3.addChild(vehicleAntenna1);
        vehicleHood.addChild(vehicleFront1);
        vehicleChestMain1.addChild(vehicleChestMain5);
        vehicleChestMain1.addChild(vehicleHood);
        vehicleBase.addChild(vehicleStomach);
        vehicleChestMain1.addChild(vehicleFrontChest1);
        vehicleLowerArmRL1.addChild(vehicleBass3);
        vehicleChestMain1.addChild(vehicleFrontWheel1);
        vehicleChestMain3.addChild(vehicleAntenna2);
        vehicleUpperLegL.addChild(vehicleLowerLegL1);
        vehicleLowerArmRL1.addChild(vehicleBass2);
        vehicleChestMain1.addChild(vehicleChestMain6);
        vehicleChestMain1.addChild(vehicleShoulderBase2);
        vehicleLowerLegR1.addChild(vehicleRearWheel1);
    }

    public Transformer getTransformer()
    {
        return TransformerManager.transformerSubwoofer;
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

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ticks, float yaw, float pitch, float scale, Entity entity)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ticks, yaw, pitch, scale, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            setToInitPose();

            float globalSpeed = 1;
            float globalDegree = 0.8F;

            ModelOffset offsets = TFModelHelper.getOffsets(player);
            head.rotationPointX += offsets.headOffsetX;
            head.rotationPointY += offsets.headOffsetY;
            head.rotationPointZ += offsets.headOffsetZ;

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerSubwoofer;
            Transformer transformerChest = TFHelper.getTransformerFromArmor(player, 2);

            boolean wearingChest = transformerChest instanceof TransformerSubwoofer;
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerSubwoofer;

            head.showModel = wearingHead;
            upperLegR.showModel = wearingLegs;
            upperLegL.showModel = wearingLegs;

            if (!wearingChest && wearingHead)
            {
                head.rotationPointY += 4;

                if (transformerChest instanceof TransformerSkystrike)
                {
                    head.rotationPointY -= 1;
                }

                head.rotationPointZ += 1;
            }

            if (wearingHead)
            {
                faceTarget(head, 1, yaw, pitch);
            }

            if (!wearingChest && wearingLegs)
            {
                upperLegL.rotationPointY += 11;
                upperLegR.rotationPointY += 11;
                upperLegL.rotateAngleX += 0.2F;
                upperLegR.rotateAngleX += 0.2F;
            }

            int backwardInverter = 1;

            float moveForward = player.moveForward;

            if (moveForward < 0)
            {
                backwardInverter = -1;
                globalDegree = 0.5F;
            }

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

            if (wearingChest && wearingHead && wearingLegs)
            {
                boolean playerOnGround = onGround(player);

                if (playerOnGround || player.capabilities.isFlying)
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

                    walk(antenna1, idleSpeed * 1.0F, 0.1F, false, 0, 0, ticks, 1);
                    walk(antenna2, idleSpeed * 1.0F, 0.1F, true, 0, 0, ticks, 1);

                    swing(dish1, idleSpeed * 0.5F, 0.5F, false, 0, 0, ticks, 1);

                    bob(clawL1, idleSpeed * 1.0F, 0.2F, false, ticks, 1);
                    bob(clawL2, idleSpeed * 1.0F, 0.2F, false, ticks, 1);

                    chestwheel1.rotateAngleX += ticks * idleSpeed;
                    chestwheel2.rotateAngleX += ticks * idleSpeed;

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
                else
                {
                    double motionY = entity.posY - entity.prevPosY;

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

            ItemStack heldItem = player.getHeldItem();

            if (heldItem != null && heldItem.getItem() == TFItems.subwoofersBassBlaster && TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) == 20)
            {
                setRotateAngle(shoulderbaseR, 0.0F, 0.0F, 0.0F);
                setRotateAngle(upperArmR, 0.0F, 0.0F, 0.2F);
                setRotateAngle(lowerArmR, bipedHead.rotateAngleX - pi / 2, bipedHead.rotateAngleY, 0.0F);
            }

            float t = TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick);
            float f = 20 - t;

            rotateTo(waist, vehicleBase, f);
            rotateTo(upperArmR, vehicleUpperArmR, f);
            rotateTo(upperArmL, vehicleUpperArmL, f);
            rotateTo(dish1, vehicleDish1, f);
            rotateTo(dish2, vehicleDish2, f);
            rotateTo(antenna1, vehicleAntenna1, f);
            rotateTo(antenna2, vehicleAntenna2, f);
            rotateTo(lowerlegR1, vehicleLowerLegR1, f);
            rotateTo(lowerlegL1, vehicleLowerLegL1, f);
            rotateTo(upperLegR, vehicleUpperLegR, f);
            rotateTo(upperLegL, vehicleUpperLegL, f);
            rotateTo(lowerArmR, vehicleLowerArmRL1, f);
            rotateTo(lowerArmL, vehicleLowerArmL1, f);
            rotateTo(chestmain1, vehicleChestMain1, f);
            rotateTo(chestmain2, vehicleChestMain2, f);
            rotateTo(chestmain3, vehicleChestMain3, f);
            rotateTo(chestmain5, vehicleChestMain5, f);
            rotateTo(chestmain6, vehicleChestMain6, f);
            rotateTo(frontchestR, vehicleFrontChest1, f);
            rotateTo(frontchestL, vehicleFrontChest2, f);
            rotateTo(bass2, vehicleBass2, f);
            rotateTo(bass3, vehicleBass3, f);
            rotateTo(crotch1, vehicleCrotch1, f);
            rotateTo(lowerlegL3, vehicleLowerLegL3, f);
            rotateTo(lowerlegR3, vehicleLowerLegR3, f);
            rotateTo(stomach, vehicleStomach, f);
            rotateTo(shoulderbaseR, vehicleShoulderBase1, f);
            rotateTo(shoulderbaseL, vehicleShoulderBase2, f);
            shoulderplateR1.rotationPointX += f * 0.2F;
            shoulderplateL1.rotationPointX -= f * 0.2F;
            shoulderplateR1.rotationPointZ -= f * 0.2F;
            shoulderplateL1.rotationPointZ -= f * 0.2F;
            head.rotationPointY += f * 0.2F;

            for (ModelRenderer modelRenderer : new ModelRenderer[]{vehicleFrontWheel1, vehicleFrontWheel2, vehicleRearWheel1, vehicleRearWheel2})
            {
                VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

                if (transformedPlayer != null)
                {
                    float wheelSpinSpeed = (transformedPlayer.getForwardVelocity() < 0 ? -limbSwing : limbSwing) * 0.8F;
                    modelRenderer.rotateAngleX = wheelSpinSpeed;
                }
            }

            vehicleBase.rotateAngleY = bipedBody.rotateAngleY;

            if (player == Minecraft.getMinecraft().thePlayer)
            {
                vehicleBase.rotateAngleX = -(float) player.motionY - 0.0784000015258789F;
            }
            else
            {
                vehicleBase.rotateAngleX = -(float) (player.posY - player.prevPosY) * 1.5F;
            }
        }
    }

    @Override
    public void renderArmorPiece(int armorPiece)
    {
        setToInitPose();

        if (armorPiece == 0)
        {
            GL11.glTranslatef(0.0F, 0.2F, 0.0F);
            this.head.render(0.0625F);
        }
        else if (armorPiece == 1)
        {
            this.upperLegL.showModel = false;
            this.upperLegR.showModel = false;
            this.head.showModel = false;
            this.waist.render(0.0625F);
            this.upperLegL.showModel = true;
            this.upperLegR.showModel = true;
            this.head.showModel = true;
        }
        else if (armorPiece == 2)
        {
            this.upperLegL.rotationPointY += 2;
            this.upperLegR.rotationPointY += 2;
            this.legbaseL.showModel = false;
            this.legbaseR.showModel = false;
            this.upperLegL.render(0.0625F);
            this.upperLegR.render(0.0625F);
            this.legbaseL.showModel = true;
            this.legbaseR.showModel = true;
        }
        else if (armorPiece == 3)
        {
            GL11.glTranslatef(0.1F, 0.0F, 0.0F);
            GL11.glRotatef(8, 1.0F, 0.0F, 0.0F);
            this.legbaseL.rotationPointX -= 4;
            this.legbaseR.rotationPointX += 4;
            this.legbaseL.renderWithParentRotations(0.0625F);
            this.legbaseR.renderWithParentRotations(0.0625F);
        }
    }
}
