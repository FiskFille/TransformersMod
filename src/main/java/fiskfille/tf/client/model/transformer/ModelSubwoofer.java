package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ModelSubwoofer extends MowzieModelBase
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
        this.textureWidth = 128;
        this.textureHeight = 128;
        
        this.crotchbuttonR = new MowzieModelRenderer(this, 5, 112);
        this.crotchbuttonR.setRotationPoint(-3.0F, 0.5F, -2.7F);
        this.crotchbuttonR.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(crotchbuttonR, 0.0F, 0.08726646259971647F, 0.0F);
        this.shoulderbaseR = new MowzieModelRenderer(this, 48, 78);
        this.shoulderbaseR.setRotationPoint(-4.0F, -2.9F, 0.0F);
        this.shoulderbaseR.addBox(-2.9F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        this.setRotateAngle(shoulderbaseR, 0.0F, 0.0F, 0.05235987755982988F);
        this.shoulderplate2 = new MowzieModelRenderer(this, 48, 90);
        this.shoulderplate2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.shoulderplate2.addBox(1.0F, 0.0F, 0.0F, 4, 1, 3, 0.0F);
        this.setRotateAngle(shoulderplate2, -1.2915436464758039F, 0.0F, 0.0F);
        this.fistL = new MowzieModelRenderer(this, 52, 102);
        this.fistL.setRotationPoint(0.0F, 4.2F, 0.0F);
        this.fistL.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(fistL, -0.03490658503988659F, 0.06981317007977318F, 0.24434609527920614F);
        this.lowerArmL = new MowzieModelRenderer(this, 48, 94);
        this.lowerArmL.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.lowerArmL.addBox(-1.3F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(lowerArmL, -0.19198621771937624F, 0.06981317007977318F, 0.10471975511965977F);
        this.lowerlegL3 = new MowzieModelRenderer(this, 79, 82);
        this.lowerlegL3.setRotationPoint(-2.0F, -1.0F, -0.2F);
        this.lowerlegL3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(lowerlegL3, -0.03490658503988659F, 0.0F, 0.0F);
        this.chestmain1 = new MowzieModelRenderer(this, 0, 67);
        this.chestmain1.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.chestmain1.addBox(-4.0F, -4.0F, -3.0F, 8, 4, 6, 0.0F);
        this.fistR = new MowzieModelRenderer(this, 52, 102);
        this.fistR.mirror = true;
        this.fistR.setRotationPoint(0.0F, 4.2F, 0.0F);
        this.fistR.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(fistR, -0.03490658503988659F, -0.06981317007977318F, -0.24434609527920614F);
        this.lowerlegR3 = new MowzieModelRenderer(this, 79, 82);
        this.lowerlegR3.mirror = true;
        this.lowerlegR3.setRotationPoint(2.0F, -1.0F, -0.2F);
        this.lowerlegR3.addBox(-1.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(lowerlegR3, -0.03490658503988659F, 0.0F, 0.0F);
        this.head4 = new MowzieModelRenderer(this, 48, 69);
        this.head4.setRotationPoint(0.9F, 0.0F, -0.9F);
        this.head4.addBox(0.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(head4, 0.03490658503988659F, -0.03490658503988659F, 0.0F);
        this.shoulderplate3 = new MowzieModelRenderer(this, 61, 85);
        this.shoulderplate3.setRotationPoint(4.5F, -1.0F, -2.0F);
        this.shoulderplate3.addBox(-1.0F, 0.0F, -0.1F, 1, 2, 5, 0.0F);
        this.setRotateAngle(shoulderplate3, 0.0F, 0.19896753472735357F, -0.017453292519943295F);
        this.bass5 = new MowzieModelRenderer(this, 60, 92);
        this.bass5.setRotationPoint(-2.0F, 1.5F, -0.5F);
        this.bass5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(bass5, 1.5707963267948966F, 1.9198621771937625F, 0.0F);
        this.shoulderplateL1 = new MowzieModelRenderer(this, 48, 83);
        this.shoulderplateL1.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.shoulderplateL1.addBox(0.5F, -1.0F, -2.0F, 4, 2, 5, 0.0F);
        this.setRotateAngle(shoulderplateL1, 0.08726646259971647F, 0.0F, 0.03490658503988659F);
        this.leg1 = new MowzieModelRenderer(this, 86, 64);
        this.leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leg1.addBox(0.1F, 0.0F, -1.5F, 1, 3, 3, 0.0F);
        this.sideflapL = new MowzieModelRenderer(this, 0, 112);
        this.sideflapL.setRotationPoint(3.5F, 0.0F, 2.0F);
        this.sideflapL.addBox(-0.3F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(sideflapL, 0.0F, -0.06981317007977318F, -0.06981317007977318F);
        this.shoulderbaseL = new MowzieModelRenderer(this, 48, 78);
        this.shoulderbaseL.setRotationPoint(4.0F, -2.9F, 0.0F);
        this.shoulderbaseL.addBox(-0.1F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        this.setRotateAngle(shoulderbaseL, 0.0F, 0.0F, -0.05235987755982988F);
        this.lowerlegR4 = new MowzieModelRenderer(this, 76, 76);
        this.lowerlegR4.mirror = true;
        this.lowerlegR4.setRotationPoint(-1.0F, 4.5F, -1.0F);
        this.lowerlegR4.addBox(-0.7F, -0.3F, -0.5F, 1, 2, 4, 0.0F);
        this.setRotateAngle(lowerlegR4, 0.0F, 0.0F, 0.19198621771937624F);
        this.legR3 = new MowzieModelRenderer(this, 83, 79);
        this.legR3.mirror = true;
        this.legR3.setRotationPoint(-0.4F, 2.2F, 0.0F);
        this.legR3.addBox(-1.1F, -2.7F, -3.2F, 2, 1, 3, 0.0F);
        this.setRotateAngle(legR3, 0.4468042885105484F, -0.012217304763960306F, 0.041887902047863905F);
        this.waist = new MowzieModelRenderer(this, 0, 98);
        this.waist.setRotationPoint(0.0F, 9.0F, -0.5F);
        this.waist.addBox(-3.5F, 0.0F, -2.5F, 7, 2, 5, 0.0F);
        this.antenna1 = new MowzieModelRenderer(this, 30, 67);
        this.antenna1.setRotationPoint(1.0F, 0.1F, 1.0F);
        this.antenna1.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(antenna1, -0.05235987755982988F, 0.0F, -0.05235987755982988F);
        this.dish1 = new MowzieModelRenderer(this, 0, 67);
        this.dish1.setRotationPoint(0.0F, -4.0F, 2.0F);
        this.dish1.addBox(-2.0F, -3.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(dish1, -0.17453292519943295F, -0.05235987755982988F, -0.05235987755982988F);
        this.lowerlegR1 = new MowzieModelRenderer(this, 76, 66);
        this.lowerlegR1.mirror = true;
        this.lowerlegR1.setRotationPoint(-1.0F, 5.0F, -1.0F);
        this.lowerlegR1.addBox(-1.7F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        this.setRotateAngle(lowerlegR1, 0.17453292519943295F, 0.017453292519943295F, -0.06981317007977318F);
        this.bass2 = new MowzieModelRenderer(this, 0, 112);
        this.bass2.setRotationPoint(-2.5F, 2.0F, -1.5F);
        this.bass2.addBox(-0.2F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.frontchestR = new MowzieModelRenderer(this, 10, 77);
        this.frontchestR.setRotationPoint(-4.0F, -4.0F, -3.0F);
        this.frontchestR.addBox(0.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        this.setRotateAngle(frontchestR, -0.07330382858376185F, 0.03839724354387525F, -0.005235987755982988F);
        this.shoulderplateR3 = new MowzieModelRenderer(this, 61, 85);
        this.shoulderplateR3.setRotationPoint(-4.5F, -1.0F, -2.0F);
        this.shoulderplateR3.addBox(0.0F, 0.0F, -0.1F, 1, 2, 5, 0.0F);
        this.setRotateAngle(shoulderplateR3, 0.0F, -0.19896753472735357F, 0.017453292519943295F);
        this.lowerlegL1 = new MowzieModelRenderer(this, 76, 66);
        this.lowerlegL1.setRotationPoint(1.0F, 5.0F, -1.0F);
        this.lowerlegL1.addBox(-1.3F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        this.setRotateAngle(lowerlegL1, 0.17453292519943295F, -0.017453292519943295F, 0.06981317007977318F);
        this.legR2 = new MowzieModelRenderer(this, 83, 70);
        this.legR2.mirror = true;
        this.legR2.setRotationPoint(-0.2F, 2.2F, 0.0F);
        this.legR2.addBox(-1.3F, -1.2F, -4.1F, 2, 2, 7, 0.0F);
        this.setRotateAngle(legR2, 0.027925268031909273F, -0.012217304763960306F, 0.041887902047863905F);
        this.crotch1 = new MowzieModelRenderer(this, 0, 105);
        this.crotch1.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.crotch1.addBox(-1.0F, -0.7F, -2.4F, 2, 3, 4, 0.0F);
        this.setRotateAngle(crotch1, 0.2617993877991494F, 0.0F, 0.0F);
        this.stomach = new MowzieModelRenderer(this, 0, 58);
        this.stomach.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.stomach.addBox(-2.5F, -5.5F, -2.0F, 5, 5, 4, 0.0F);
        this.shoulderplateR1 = new MowzieModelRenderer(this, 48, 83);
        this.shoulderplateR1.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.shoulderplateR1.addBox(-4.5F, -1.0F, -2.0F, 4, 2, 5, 0.0F);
        this.setRotateAngle(shoulderplateR1, 0.08726646259971647F, 0.0F, -0.03490658503988659F);
        this.bass3 = new MowzieModelRenderer(this, 0, 112);
        this.bass3.mirror = true;
        this.bass3.setRotationPoint(-2.5F, -2.0F, -1.5F);
        this.bass3.addBox(-0.2F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.lowerlegL2 = new MowzieModelRenderer(this, 91, 64);
        this.lowerlegL2.setRotationPoint(0.0F, -1.5F, -1.5F);
        this.lowerlegL2.addBox(-1.3F, -1.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(lowerlegL2, -0.15707963267948966F, 0.0F, 0.0F);
        this.chestmain4 = new MowzieModelRenderer(this, 20, 77);
        this.chestmain4.setRotationPoint(-3.0F, 0.0F, 5.0F);
        this.chestmain4.addBox(0.0F, 0.0F, -2.0F, 6, 5, 2, 0.0F);
        this.setRotateAngle(chestmain4, -1.0297442586766543F, 0.0F, 0.0F);
        this.head1 = new MowzieModelRenderer(this, 48, 64);
        this.head1.setRotationPoint(0.0F, -3.0F, -1.0F);
        this.head1.addBox(-0.5F, -1.5F, -0.4F, 1, 2, 3, 0.0F);
        this.setRotateAngle(head1, -0.22689280275926282F, 0.0F, 0.0F);
        this.head2 = new MowzieModelRenderer(this, 56, 64);
        this.head2.setRotationPoint(1.3F, -3.0F, -1.0F);
        this.head2.addBox(-0.5F, -1.0F, -0.2F, 1, 2, 3, 0.0F);
        this.setRotateAngle(head2, -0.12217304763960307F, 0.0F, 0.20943951023931953F);
        this.crotch2 = new MowzieModelRenderer(this, 8, 105);
        this.crotch2.setRotationPoint(0.0F, 1.0F, 1.0F);
        this.crotch2.addBox(-1.0F, -0.9F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(crotch2, -0.2617993877991494F, 0.0F, 0.0F);
        this.legbaseL = new MowzieModelRenderer(this, 86, 58);
        this.legbaseL.setRotationPoint(0.4F, 4.4F, 0.7F);
        this.legbaseL.addBox(-2.1F, 0.0F, -1.5F, 2, 3, 3, 0.0F);
        this.setRotateAngle(legbaseL, -0.17453292519943295F, 0.03490658503988659F, 0.05235987755982988F);
        this.sideflapL_1 = new MowzieModelRenderer(this, 0, 112);
        this.sideflapL_1.mirror = true;
        this.sideflapL_1.setRotationPoint(-3.5F, 0.0F, 2.0F);
        this.sideflapL_1.addBox(-0.7F, 0.0F, -2.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(sideflapL_1, 0.0F, 0.06981317007977318F, 0.06981317007977318F);
        this.chestwheel1 = new MowzieModelRenderer(this, 0, 77);
        this.chestwheel1.setRotationPoint(-3.5F, -0.5F, -2.0F);
        this.chestwheel1.addBox(0.0F, 0.0F, 0.0F, 2, 3, 3, 0.0F);
        this.setRotateAngle(chestwheel1, -0.08726646259971647F, 0.0F, 0.13962634015954636F);
        this.head7 = new MowzieModelRenderer(this, 54, 69);
        this.head7.mirror = true;
        this.head7.setRotationPoint(1.8F, -3.07F, 1.1F);
        this.head7.addBox(-0.97F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(head7, 0.0F, 0.017453292519943295F, 0.0F);
        this.legL2 = new MowzieModelRenderer(this, 83, 70);
        this.legL2.setRotationPoint(-0.2F, 2.2F, 0.0F);
        this.legL2.addBox(-1.1F, -1.2F, -4.1F, 2, 2, 7, 0.0F);
        this.setRotateAngle(legL2, 0.027925268031909273F, 0.012217304763960306F, -0.041887902047863905F);
        this.head5 = new MowzieModelRenderer(this, 48, 69);
        this.head5.mirror = true;
        this.head5.setRotationPoint(-0.9F, 0.0F, -0.9F);
        this.head5.addBox(-1.0F, -1.0F, 0.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(head5, 0.03490658503988659F, 0.03490658503988659F, 0.0F);
        this.upperLegR = new MowzieModelRenderer(this, 76, 58);
        this.upperLegR.mirror = true;
        this.upperLegR.setRotationPoint(-1.0F, 1.0F, 0.0F);
        this.upperLegR.addBox(-2.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        this.setRotateAngle(upperLegR, -0.2617993877991494F, 0.10471975511965977F, 0.08726646259971647F);
        this.lowerArmR = new MowzieModelRenderer(this, 48, 94);
        this.lowerArmR.mirror = true;
        this.lowerArmR.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.lowerArmR.addBox(-1.7F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        this.setRotateAngle(lowerArmR, -0.19198621771937624F, -0.06981317007977318F, -0.10471975511965977F);
        this.bass1 = new MowzieModelRenderer(this, 60, 92);
        this.bass1.setRotationPoint(-2.0F, 5.0F, -0.5F);
        this.bass1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(bass1, 1.5707963267948966F, 1.9198621771937625F, 0.0F);
        this.lowerlegR2 = new MowzieModelRenderer(this, 91, 64);
        this.lowerlegR2.mirror = true;
        this.lowerlegR2.setRotationPoint(0.0F, -1.5F, -1.5F);
        this.lowerlegR2.addBox(-1.7F, -1.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(lowerlegR2, -0.15707963267948966F, 0.0F, 0.0F);
        this.frontchestL = new MowzieModelRenderer(this, 10, 77);
        this.frontchestL.mirror = true;
        this.frontchestL.setRotationPoint(4.0F, -4.0F, -3.0F);
        this.frontchestL.addBox(-4.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        this.setRotateAngle(frontchestL, -0.07330382858376185F, -0.03839724354387525F, 0.005235987755982988F);
        this.headplate2_1 = new MowzieModelRenderer(this, 47, 65);
        this.headplate2_1.mirror = true;
        this.headplate2_1.setRotationPoint(-1.0F, 0.0F, -0.3F);
        this.headplate2_1.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(headplate2_1, 0.0F, 0.45029494701453704F, 0.0F);
        this.antenna2 = new MowzieModelRenderer(this, 30, 67);
        this.antenna2.setRotationPoint(5.0F, 0.1F, 1.0F);
        this.antenna2.addBox(-0.5F, -7.0F, -0.5F, 1, 7, 1, 0.0F);
        this.setRotateAngle(antenna2, -0.05235987755982988F, 0.0F, 0.03490658503988659F);
        this.clawL1 = new MowzieModelRenderer(this, 48, 102);
        this.clawL1.setRotationPoint(1.0F, 0.0F, 0.3F);
        this.clawL1.addBox(0.0F, -0.5F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(clawL1, 0.0F, 0.0F, 0.08726646259971647F);
        this.lowerarmL3 = new MowzieModelRenderer(this, 60, 97);
        this.lowerarmL3.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.lowerarmL3.addBox(0.7F, 0.4F, -1.5F, 1, 2, 3, 0.0F);
        this.chestmain3 = new MowzieModelRenderer(this, 0, 83);
        this.chestmain3.setRotationPoint(-3.0F, -4.0F, 3.0F);
        this.chestmain3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
        this.headplate2 = new MowzieModelRenderer(this, 47, 65);
        this.headplate2.setRotationPoint(1.0F, 0.0F, -0.3F);
        this.headplate2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(headplate2, 0.0F, -0.45029494701453704F, 0.0F);
        this.bass4 = new MowzieModelRenderer(this, 60, 92);
        this.bass4.mirror = true;
        this.bass4.setRotationPoint(-2.0F, 5.0F, 1.5F);
        this.bass4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(bass4, 1.5707963267948966F, 1.2217304763960306F, 0.0F);
        this.crotchbuttonL = new MowzieModelRenderer(this, 5, 112);
        this.crotchbuttonL.mirror = true;
        this.crotchbuttonL.setRotationPoint(3.0F, 0.5F, -2.7F);
        this.crotchbuttonL.addBox(-2.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(crotchbuttonL, 0.0F, -0.08726646259971647F, 0.0F);
        this.chestmain2 = new MowzieModelRenderer(this, 18, 62);
        this.chestmain2.setRotationPoint(-3.0F, 0.0F, -4.0F);
        this.chestmain2.addBox(0.0F, 0.0F, 0.3F, 6, 3, 2, 0.0F);
        this.setRotateAngle(chestmain2, 0.33161255787892263F, 0.0F, 0.0F);
        this.head3 = new MowzieModelRenderer(this, 56, 64);
        this.head3.mirror = true;
        this.head3.setRotationPoint(-1.3F, -3.0F, -1.0F);
        this.head3.addBox(-0.5F, -1.0F, -0.2F, 1, 2, 3, 0.0F);
        this.setRotateAngle(head3, -0.12217304763960307F, 0.0F, -0.20943951023931953F);
        this.head = new MowzieModelRenderer(this, 48, 58);
        this.head.setRotationPoint(0.0F, -4.0F, -1.5F);
        this.head.addBox(-1.5F, -3.0F, -1.0F, 3, 3, 3, 0.0F);
        this.legbaseR = new MowzieModelRenderer(this, 86, 58);
        this.legbaseR.mirror = true;
        this.legbaseR.setRotationPoint(0.4F, 4.4F, 0.7F);
        this.legbaseR.addBox(-0.7F, 0.0F, -1.5F, 2, 3, 3, 0.0F);
        this.setRotateAngle(legbaseR, -0.17453292519943295F, -0.03490658503988659F, -0.05235987755982988F);
        this.head6 = new MowzieModelRenderer(this, 54, 69);
        this.head6.setRotationPoint(-1.8F, -3.07F, 1.1F);
        this.head6.addBox(-0.03F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(head6, 0.0F, 0.017453292519943295F, 0.0F);
        this.upperLegL = new MowzieModelRenderer(this, 76, 58);
        this.upperLegL.setRotationPoint(1.0F, 1.0F, 0.0F);
        this.upperLegL.addBox(0.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        this.setRotateAngle(upperLegL, -0.2617993877991494F, -0.10471975511965977F, -0.08726646259971647F);
        this.lowerlegL4 = new MowzieModelRenderer(this, 76, 76);
        this.lowerlegL4.setRotationPoint(1.0F, 4.5F, -1.0F);
        this.lowerlegL4.addBox(-0.3F, -0.3F, -0.5F, 1, 2, 4, 0.0F);
        this.setRotateAngle(lowerlegL4, 0.0F, 0.0F, -0.19198621771937624F);
        this.upperArmL = new MowzieModelRenderer(this, 61, 79);
        this.upperArmL.setRotationPoint(1.7F, 1.0F, 0.0F);
        this.upperArmL.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(upperArmL, 0.03490658503988659F, 0.0F, -0.10471975511965977F);
        this.chestmain6 = new MowzieModelRenderer(this, 0, 89);
        this.chestmain6.mirror = true;
        this.chestmain6.setRotationPoint(-3.0F, -4.0F, 0.0F);
        this.chestmain6.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        this.setRotateAngle(chestmain6, -0.06981317007977318F, -0.12217304763960307F, -0.12217304763960307F);
        this.upperArmR = new MowzieModelRenderer(this, 61, 79);
        this.upperArmR.setRotationPoint(-1.7F, 1.0F, 0.0F);
        this.upperArmR.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(upperArmR, 0.03490658503988659F, 0.0F, 0.10471975511965977F);
        this.legR1 = new MowzieModelRenderer(this, 86, 64);
        this.legR1.mirror = true;
        this.legR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.legR1.addBox(-1.9F, 0.0F, -1.5F, 1, 3, 3, 0.0F);
        this.chestmain5 = new MowzieModelRenderer(this, 0, 89);
        this.chestmain5.setRotationPoint(3.0F, -4.0F, 0.0F);
        this.chestmain5.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        this.setRotateAngle(chestmain5, -0.05235987755982988F, 0.12217304763960307F, 0.12217304763960307F);
        this.dish2 = new MowzieModelRenderer(this, 22, 67);
        this.dish2.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.dish2.addBox(-2.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(dish2, 0.5235987755982988F, 0.0F, 0.0F);
        this.bass6 = new MowzieModelRenderer(this, 60, 92);
        this.bass6.mirror = true;
        this.bass6.setRotationPoint(-2.0F, 1.5F, 1.5F);
        this.bass6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(bass6, 1.5707963267948966F, 1.2217304763960306F, 0.0F);
        this.shoulderplateR2 = new MowzieModelRenderer(this, 48, 90);
        this.shoulderplateR2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.shoulderplateR2.addBox(-5.0F, 0.0F, 0.0F, 4, 1, 3, 0.0F);
        this.setRotateAngle(shoulderplateR2, -1.2915436464758039F, 0.0F, 0.0F);
        this.clawL2 = new MowzieModelRenderer(this, 48, 102);
        this.clawL2.mirror = true;
        this.clawL2.setRotationPoint(1.0F, 0.0F, -1.3F);
        this.clawL2.addBox(0.0F, -0.5F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(clawL2, 0.0F, 0.0F, 0.08726646259971647F);
        this.chestwheel2 = new MowzieModelRenderer(this, 0, 77);
        this.chestwheel2.mirror = true;
        this.chestwheel2.setRotationPoint(3.5F, -0.5F, -2.0F);
        this.chestwheel2.addBox(-2.0F, 0.0F, 0.0F, 2, 3, 3, 0.0F);
        this.setRotateAngle(chestwheel2, -0.08726646259971647F, 0.0F, -0.14608405839192537F);
        this.headplate1 = new MowzieModelRenderer(this, 53, 65);
        this.headplate1.setRotationPoint(0.0F, -1.0F, -1.0F);
        this.headplate1.addBox(-1.0F, 0.0F, -0.3F, 2, 1, 1, 0.0F);
        this.lowerarmL2 = new MowzieModelRenderer(this, 60, 92);
        this.lowerarmL2.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.lowerarmL2.addBox(0.7F, -6.6F, -1.5F, 1, 2, 3, 0.0F);
        this.legL3 = new MowzieModelRenderer(this, 83, 79);
        this.legL3.setRotationPoint(-0.2F, 2.2F, 0.0F);
        this.legL3.addBox(-1.1F, -2.7F, -3.2F, 2, 1, 3, 0.0F);
        this.setRotateAngle(legL3, 0.4468042885105484F, 0.012217304763960306F, -0.041887902047863905F);
        
        this.waist.addChild(this.crotchbuttonR);
        this.chestmain1.addChild(this.shoulderbaseR);
        this.shoulderplateL1.addChild(this.shoulderplate2);
        this.lowerArmL.addChild(this.fistL);
        this.upperArmL.addChild(this.lowerArmL);
        this.lowerlegL1.addChild(this.lowerlegL3);
        this.stomach.addChild(this.chestmain1);
        this.lowerArmR.addChild(this.fistR);
        this.lowerlegR1.addChild(this.lowerlegR3);
        this.head.addChild(this.head4);
        this.shoulderplateL1.addChild(this.shoulderplate3);
        this.lowerArmR.addChild(this.bass5);
        this.shoulderbaseL.addChild(this.shoulderplateL1);
        this.legbaseL.addChild(this.leg1);
        this.waist.addChild(this.sideflapL);
        this.chestmain1.addChild(this.shoulderbaseL);
        this.lowerlegR1.addChild(this.lowerlegR4);
        this.legbaseR.addChild(this.legR3);
        this.chestmain3.addChild(this.antenna1);
        this.chestmain1.addChild(this.dish1);
        this.upperLegR.addChild(this.lowerlegR1);
        this.lowerArmR.addChild(this.bass2);
        this.chestmain1.addChild(this.frontchestR);
        this.shoulderplateR1.addChild(this.shoulderplateR3);
        this.upperLegL.addChild(this.lowerlegL1);
        this.legbaseR.addChild(this.legR2);
        this.waist.addChild(this.crotch1);
        this.waist.addChild(this.stomach);
        this.shoulderbaseR.addChild(this.shoulderplateR1);
        this.lowerArmR.addChild(this.bass3);
        this.lowerlegL1.addChild(this.lowerlegL2);
        this.chestmain1.addChild(this.chestmain4);
        this.head.addChild(this.head1);
        this.head.addChild(this.head2);
        this.crotch1.addChild(this.crotch2);
        this.lowerlegL1.addChild(this.legbaseL);
        this.waist.addChild(this.sideflapL_1);
        this.chestmain1.addChild(this.chestwheel1);
        this.head.addChild(this.head7);
        this.legbaseL.addChild(this.legL2);
        this.head.addChild(this.head5);
        this.crotch1.addChild(this.upperLegR);
        this.upperArmR.addChild(this.lowerArmR);
        this.lowerArmR.addChild(this.bass1);
        this.lowerlegR1.addChild(this.lowerlegR2);
        this.chestmain1.addChild(this.frontchestL);
        this.headplate1.addChild(this.headplate2_1);
        this.chestmain3.addChild(this.antenna2);
        this.lowerarmL3.addChild(this.clawL1);
        this.lowerArmL.addChild(this.lowerarmL3);
        this.chestmain1.addChild(this.chestmain3);
        this.headplate1.addChild(this.headplate2);
        this.lowerArmR.addChild(this.bass4);
        this.waist.addChild(this.crotchbuttonL);
        this.chestmain1.addChild(this.chestmain2);
        this.head.addChild(this.head3);
        this.chestmain1.addChild(this.head);
        this.lowerlegR1.addChild(this.legbaseR);
        this.head.addChild(this.head6);
        this.crotch1.addChild(this.upperLegL);
        this.lowerlegL1.addChild(this.lowerlegL4);
        this.shoulderbaseL.addChild(this.upperArmL);
        this.chestmain1.addChild(this.chestmain6);
        this.shoulderbaseR.addChild(this.upperArmR);
        this.legbaseR.addChild(this.legR1);
        this.chestmain1.addChild(this.chestmain5);
        this.dish1.addChild(this.dish2);
        this.lowerArmR.addChild(this.bass6);
        this.shoulderplateR1.addChild(this.shoulderplateR2);
        this.lowerarmL3.addChild(this.clawL2);
        this.chestmain1.addChild(this.chestwheel2);
        this.head.addChild(this.headplate1);
        this.lowerArmL.addChild(this.lowerarmL2);
        this.legbaseL.addChild(this.legL3);
        
        //parts = new MowzieModelRenderer[]{antenna1, antenna2, bass1, bass2, bass3, bass4, bass5, bass6, chestmain1, chestmain2, chestmain3, chestmain4, chestmain5, chestmain6, chestwheel1, chestwheel2, clawL1, clawL2, crotch1, crotch2, crotchbuttonL, crotchbuttonR, dish1, dish2, fistL, fistR, frontchestL, frontchestR, head, head1, head2, head3, head4, head5, head6, head7, headplate1, headplate2, headplate2_1, leg1, legbaseL, legbaseR, legL2, legL3, legR1, legR2, legR3, lowerarmR1, lowerarmL1, lowerarmL2, lowerarmL3, lowerlegL1, lowerlegL2, lowerlegL3, lowerlegL4, lowerlegR1, lowerlegR2, lowerlegR3, lowerlegR4, shoulderbaseL, shoulderbaseR, shoulderplate2, shoulderplate3, shoulderplateL1, shoulderplateR1, shoulderplateR2, shoulderplateR3, sideflapL, sideflapL_1, stomach, upperArmL, upperArmR, upperLegL, upperLegR, waist};
        
        setInitPose();
        
        this.vehicleCover6 = new ModelRenderer(this, 17, 0);
        this.vehicleCover6.setRotationPoint(4.1F, -1.4F, -2.4F);
        this.vehicleCover6.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
        this.setRotateAngle(vehicleCover6, 0.0F, 0.0F, -0.017453292519943295F);
        this.vehicleLowerArmRL1 = new ModelRenderer(this, 48, 94);
        this.vehicleLowerArmRL1.mirror = true;
        this.vehicleLowerArmRL1.setRotationPoint(1.4F, 4.5F, 0.6F);
        this.vehicleLowerArmRL1.addBox(-1.7F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        this.vehicleChestMain1 = new ModelRenderer(this, 0, 67);
        this.vehicleChestMain1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.vehicleChestMain1.addBox(-4.0F, -4.0F, -3.0F, 8, 4, 6, 0.0F);
        this.setRotateAngle(vehicleChestMain1, -1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleCover3 = new ModelRenderer(this, 0, 0);
        this.vehicleCover3.mirror = true;
        this.vehicleCover3.setRotationPoint(-4.1F, -3.9F, -3.4F);
        this.vehicleCover3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 14, 0.0F);
        this.vehicleRear1 = new ModelRenderer(this, 0, 22);
        this.vehicleRear1.setRotationPoint(-4.0F, -0.5F, 7.0F);
        this.vehicleRear1.addBox(0.0F, 0.0F, 0.0F, 8, 4, 2, 0.0F);
        this.vehicleRear2 = new ModelRenderer(this, 0, 29);
        this.vehicleRear2.setRotationPoint(-4.0F, -2.5F, 8.0F);
        this.vehicleRear2.addBox(0.0F, -1.0F, 0.0F, 8, 3, 1, 0.0F);
        this.vehicleFrontChest2 = new ModelRenderer(this, 10, 77);
        this.vehicleFrontChest2.mirror = true;
        this.vehicleFrontChest2.setRotationPoint(4.0F, -4.0F, -3.0F);
        this.vehicleFrontChest2.addBox(-4.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        this.setRotateAngle(vehicleFrontChest2, -0.07330382858376185F, 0.0F, 0.005235987755982988F);
        this.vehicleLowerLegL3 = new ModelRenderer(this, 79, 82);
        this.vehicleLowerLegL3.setRotationPoint(0.7F, -1.5F, 2.5F);
        this.vehicleLowerLegL3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.vehicleLowerLegR1 = new ModelRenderer(this, 76, 66);
        this.vehicleLowerLegR1.mirror = true;
        this.vehicleLowerLegR1.setRotationPoint(-1.3F, 2.5F, -1.0F);
        this.vehicleLowerLegR1.addBox(-1.7F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        this.vehicleUpperLegL = new ModelRenderer(this, 76, 58);
        this.vehicleUpperLegL.setRotationPoint(1.0F, 1.0F, 0.0F);
        this.vehicleUpperLegL.addBox(0.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        this.setRotateAngle(vehicleUpperLegL, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleBaseplate = new ModelRenderer(this, 20, 2);
        this.vehicleBaseplate.setRotationPoint(-3.0F, 1.6F, -5.4F);
        this.vehicleBaseplate.addBox(0.0F, 0.0F, 0.0F, 6, 1, 16, 0.0F);
        this.vehicleLowerArmL1 = new ModelRenderer(this, 48, 94);
        this.vehicleLowerArmL1.setRotationPoint(-1.4F, 4.5F, 0.6F);
        this.vehicleLowerArmL1.addBox(-1.3F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
        this.vehicleFront2 = new ModelRenderer(this, 0, 0);
        this.vehicleFront2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleFront2.addBox(7.0F, 0.4F, -2.8F, 1, 3, 1, 0.0F);
        this.setRotateAngle(vehicleFront2, -0.13962634015954636F, 0.0F, 0.0F);
        this.vehicleUpperArmR = new ModelRenderer(this, 61, 79);
        this.vehicleUpperArmR.setRotationPoint(-1.7F, 1.0F, 0.0F);
        this.vehicleUpperArmR.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        this.vehicleRearWheel2 = new ModelRenderer(this, 0, 77);
        this.vehicleRearWheel2.setRotationPoint(0.2F, 4.4F, -0.5F);
        this.vehicleRearWheel2.addBox(0.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        this.vehicleDish2 = new ModelRenderer(this, 22, 67);
        this.vehicleDish2.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.vehicleDish2.addBox(-2.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(vehicleDish2, 0.2792526803190927F, 0.0F, 0.0F);
        this.vehicleUpperLegR = new ModelRenderer(this, 76, 58);
        this.vehicleUpperLegR.mirror = true;
        this.vehicleUpperLegR.setRotationPoint(-1.0F, 1.0F, 0.0F);
        this.vehicleUpperLegR.addBox(-2.0F, -1.0F, -2.0F, 2, 5, 3, 0.0F);
        this.setRotateAngle(vehicleUpperLegR, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleCover2 = new ModelRenderer(this, 0, 42);
        this.vehicleCover2.setRotationPoint(-4.0F, -4.5F, 3.0F);
        this.vehicleCover2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 7, 0.0F);
        this.vehicleBase = new ModelRenderer(this, 0, 98);
        this.vehicleBase.setRotationPoint(0.0F, 20.0F, -0.5F);
        this.vehicleBase.addBox(-3.5F, 0.0F, -2.5F, 7, 2, 5, 0.0F);
        this.vehicleCover4 = new ModelRenderer(this, 0, 0);
        this.vehicleCover4.setRotationPoint(4.1F, -3.9F, -3.4F);
        this.vehicleCover4.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 14, 0.0F);
        this.setRotateAngle(vehicleCover4, 0.006283185307179587F, 0.0F, 0.0F);
        this.vehicleCover5 = new ModelRenderer(this, 17, 0);
        this.vehicleCover5.mirror = true;
        this.vehicleCover5.setRotationPoint(-4.1F, -1.4F, -2.4F);
        this.vehicleCover5.addBox(0.0F, 0.0F, 0.0F, 1, 4, 9, 0.0F);
        this.setRotateAngle(vehicleCover5, 0.0F, 0.0F, 0.017453292519943295F);
        this.vehicleCrotch1 = new ModelRenderer(this, 0, 105);
        this.vehicleCrotch1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleCrotch1.addBox(-1.0F, -0.7F, -2.4F, 2, 3, 4, 0.0F);
        this.vehicleChestMain2 = new ModelRenderer(this, 18, 62);
        this.vehicleChestMain2.setRotationPoint(-3.0F, -0.6F, -6.1F);
        this.vehicleChestMain2.addBox(0.0F, 0.0F, 0.3F, 6, 3, 2, 0.0F);
        this.setRotateAngle(vehicleChestMain2, 0.03490658503988659F, 0.0F, 0.0F);
        this.vehicleFrontWheel2 = new ModelRenderer(this, 0, 77);
        this.vehicleFrontWheel2.mirror = true;
        this.vehicleFrontWheel2.setRotationPoint(2.5F, 1.5F, -3.7F);
        this.vehicleFrontWheel2.addBox(0.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        this.vehicleLowerLegR3 = new ModelRenderer(this, 79, 82);
        this.vehicleLowerLegR3.mirror = true;
        this.vehicleLowerLegR3.setRotationPoint(-1.7F, -1.5F, 2.5F);
        this.vehicleLowerLegR3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.vehicleChestMain3 = new ModelRenderer(this, 0, 83);
        this.vehicleChestMain3.setRotationPoint(-3.0F, -4.0F, 3.0F);
        this.vehicleChestMain3.addBox(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
        this.vehicleDish1 = new ModelRenderer(this, 0, 67);
        this.vehicleDish1.setRotationPoint(-4.0F, -4.6F, 6.2F);
        this.vehicleDish1.addBox(-2.0F, -3.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(vehicleDish1, 0.017453292519943295F, 0.12217304763960307F, 1.5707963267948966F);
        this.vehicleShoulderBase1 = new ModelRenderer(this, 48, 78);
        this.vehicleShoulderBase1.setRotationPoint(-1.0F, -1.9F, 0.0F);
        this.vehicleShoulderBase1.addBox(-2.9F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        this.setRotateAngle(vehicleShoulderBase1, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleUpperArmL = new ModelRenderer(this, 61, 79);
        this.vehicleUpperArmL.setRotationPoint(1.7F, 1.0F, 0.0F);
        this.vehicleUpperArmL.addBox(-1.0F, -0.2F, -1.0F, 2, 4, 2, 0.0F);
        this.vehicleCover1 = new ModelRenderer(this, 0, 34);
        this.vehicleCover1.setRotationPoint(-3.0F, -4.5F, 3.0F);
        this.vehicleCover1.addBox(0.0F, 0.0F, -6.0F, 6, 1, 6, 0.0F);
        this.setRotateAngle(vehicleCover1, 0.08203047484373349F, 0.0F, 0.0F);
        this.vehicleAntenna1 = new ModelRenderer(this, 30, 67);
        this.vehicleAntenna1.setRotationPoint(0.0F, 1.1F, 6.0F);
        this.vehicleAntenna1.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(vehicleAntenna1, -0.05235987755982988F, 0.0F, 0.0F);
        this.vehicleFront1 = new ModelRenderer(this, 0, 0);
        this.vehicleFront1.mirror = true;
        this.vehicleFront1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleFront1.addBox(0.0F, 0.4F, -2.8F, 1, 3, 1, 0.0F);
        this.setRotateAngle(vehicleFront1, -0.13962634015954636F, 0.0F, 0.0F);
        this.vehicleChestMain5 = new ModelRenderer(this, 0, 89);
        this.vehicleChestMain5.setRotationPoint(2.9F, 0.5F, 3.0F);
        this.vehicleChestMain5.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        this.setRotateAngle(vehicleChestMain5, 3.141592653589793F, 0.0F, 0.0F);
        this.vehicleHood = new ModelRenderer(this, 0, 18);
        this.vehicleHood.setRotationPoint(-4.0F, -1.0F, -3.0F);
        this.vehicleHood.addBox(0.0F, 0.0F, -2.8F, 8, 1, 3, 0.0F);
        this.setRotateAngle(vehicleHood, 0.13962634015954636F, 0.0F, 0.0F);
        this.vehicleStomach = new ModelRenderer(this, 0, 58);
        this.vehicleStomach.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.vehicleStomach.addBox(-2.5F, -5.5F, -2.0F, 5, 5, 4, 0.0F);
        this.setRotateAngle(vehicleStomach, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleFrontChest1 = new ModelRenderer(this, 10, 77);
        this.vehicleFrontChest1.setRotationPoint(-4.0F, -4.0F, -3.0F);
        this.vehicleFrontChest1.addBox(0.0F, 0.0F, -0.6F, 4, 4, 1, 0.0F);
        this.setRotateAngle(vehicleFrontChest1, -0.07330382858376185F, 0.0F, -0.005235987755982988F);
        this.vehicleBass3 = new ModelRenderer(this, 0, 112);
        this.vehicleBass3.mirror = true;
        this.vehicleBass3.setRotationPoint(-2.5F, -2.5F, 2.1F);
        this.vehicleBass3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(vehicleBass3, 0.0F, 1.5707963267948966F, 0.0F);
        this.vehicleFrontWheel1 = new ModelRenderer(this, 0, 77);
        this.vehicleFrontWheel1.setRotationPoint(-2.5F, 1.5F, -3.7F);
        this.vehicleFrontWheel1.addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        this.vehicleAntenna2 = new ModelRenderer(this, 30, 67);
        this.vehicleAntenna2.setRotationPoint(6.0F, 1.1F, 6.0F);
        this.vehicleAntenna2.addBox(-0.5F, -7.0F, -0.5F, 1, 7, 1, 0.0F);
        this.setRotateAngle(vehicleAntenna2, -0.05235987755982988F, 0.0F, 0.0F);
        this.vehicleLowerLegL1 = new ModelRenderer(this, 76, 66);
        this.vehicleLowerLegL1.setRotationPoint(1.3F, 2.5F, -1.0F);
        this.vehicleLowerLegL1.addBox(-1.3F, -1.5F, -1.5F, 3, 6, 4, 0.0F);
        this.setRotateAngle(vehicleLowerLegL1, 0.0F, 0.004886921905584123F, 0.0F);
        this.vehicleBass2 = new ModelRenderer(this, 0, 112);
        this.vehicleBass2.setRotationPoint(1.7F, -2.5F, 2.1F);
        this.vehicleBass2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.setRotateAngle(vehicleBass2, 0.0F, 1.5707963267948966F, 0.0F);
        this.vehicleChestMain6 = new ModelRenderer(this, 0, 89);
        this.vehicleChestMain6.mirror = true;
        this.vehicleChestMain6.setRotationPoint(-2.9F, 0.5F, 3.0F);
        this.vehicleChestMain6.addBox(-1.0F, -2.0F, 0.0F, 2, 3, 4, 0.0F);
        this.setRotateAngle(vehicleChestMain6, 3.141592653589793F, 0.0F, 0.0F);
        this.vehicleShoulderBase2 = new ModelRenderer(this, 48, 78);
        this.vehicleShoulderBase2.setRotationPoint(1.0F, -1.9F, 0.0F);
        this.vehicleShoulderBase2.addBox(-0.1F, -1.0F, -1.4F, 3, 2, 3, 0.0F);
        this.setRotateAngle(vehicleShoulderBase2, 1.5707963267948966F, 0.0F, 0.0F);
        this.vehicleRearWheel1 = new ModelRenderer(this, 0, 77);
        this.vehicleRearWheel1.mirror = true;
        this.vehicleRearWheel1.setRotationPoint(-0.2F, 4.4F, -0.5F);
        this.vehicleRearWheel1.addBox(-2.0F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
        
        this.vehicleChestMain1.addChild(this.vehicleCover6);
        this.vehicleUpperArmR.addChild(this.vehicleLowerArmRL1);
        this.vehicleStomach.addChild(this.vehicleChestMain1);
        this.vehicleChestMain1.addChild(this.vehicleCover3);
        this.vehicleBase.addChild(this.vehicleRear1);
        this.vehicleBase.addChild(this.vehicleRear2);
        this.vehicleChestMain1.addChild(this.vehicleFrontChest2);
        this.vehicleLowerLegL1.addChild(this.vehicleLowerLegL3);
        this.vehicleUpperLegR.addChild(this.vehicleLowerLegR1);
        this.vehicleCrotch1.addChild(this.vehicleUpperLegL);
        this.vehicleChestMain1.addChild(this.vehicleBaseplate);
        this.vehicleUpperArmL.addChild(this.vehicleLowerArmL1);
        this.vehicleHood.addChild(this.vehicleFront2);
        this.vehicleShoulderBase1.addChild(this.vehicleUpperArmR);
        this.vehicleLowerLegL1.addChild(this.vehicleRearWheel2);
        this.vehicleDish1.addChild(this.vehicleDish2);
        this.vehicleCrotch1.addChild(this.vehicleUpperLegR);
        this.vehicleChestMain1.addChild(this.vehicleCover2);
        this.vehicleChestMain1.addChild(this.vehicleCover4);
        this.vehicleChestMain1.addChild(this.vehicleCover5);
        this.vehicleBase.addChild(this.vehicleCrotch1);
        this.vehicleChestMain1.addChild(this.vehicleChestMain2);
        this.vehicleChestMain1.addChild(this.vehicleFrontWheel2);
        this.vehicleLowerLegR1.addChild(this.vehicleLowerLegR3);
        this.vehicleChestMain1.addChild(this.vehicleChestMain3);
        this.vehicleChestMain1.addChild(this.vehicleDish1);
        this.vehicleChestMain1.addChild(this.vehicleShoulderBase1);
        this.vehicleShoulderBase2.addChild(this.vehicleUpperArmL);
        this.vehicleChestMain1.addChild(this.vehicleCover1);
        this.vehicleChestMain3.addChild(this.vehicleAntenna1);
        this.vehicleHood.addChild(this.vehicleFront1);
        this.vehicleChestMain1.addChild(this.vehicleChestMain5);
        this.vehicleChestMain1.addChild(this.vehicleHood);
        this.vehicleBase.addChild(this.vehicleStomach);
        this.vehicleChestMain1.addChild(this.vehicleFrontChest1);
        this.vehicleLowerArmRL1.addChild(this.vehicleBass3);
        this.vehicleChestMain1.addChild(this.vehicleFrontWheel1);
        this.vehicleChestMain3.addChild(this.vehicleAntenna2);
        this.vehicleUpperLegL.addChild(this.vehicleLowerLegL1);
        this.vehicleLowerArmRL1.addChild(this.vehicleBass2);
        this.vehicleChestMain1.addChild(this.vehicleChestMain6);
        this.vehicleChestMain1.addChild(this.vehicleShoulderBase2);
        this.vehicleLowerLegR1.addChild(this.vehicleRearWheel1);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            
            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerSubwoofer;
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerSubwoofer;
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerSubwoofer;
            
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
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            
            setToInitPose();
            
            float globalSpeed = 1;
            float globalDegree = 0.8F;
            
            ModelOffset offsets = TFModelHelper.getOffsets(player);
            this.head.rotationPointX += offsets.headOffsetX;
            this.head.rotationPointY += offsets.headOffsetY;
            this.head.rotationPointZ += offsets.headOffsetZ;
            
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
                faceTarget(head, 1, par4, par5);
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
            
            if (this.heldItemLeft != 0)
            {
                this.upperArmL.rotateAngleX -= 0.2F;
            }
            if (this.heldItemRight != 0)
            {
                this.upperArmR.rotateAngleX -= 0.2F;
            }
            
            if (wearingChest && wearingHead && wearingLegs)
            {
                if (this.onGround > -9990.0F)
                {
                    float hitTick = this.onGround;
                    double max = 0.99126524;
                    
                    stomach.rotateAngleY += 20 * (hitTick) * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
                    chestmain1.rotateAngleY += 20 * (hitTick) * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
                    head.rotateAngleY += -40 * (hitTick) * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
                    upperArmR.rotateAngleZ += -2 * (hitTick) * (Math.pow(hitTick, 5) - max);
                    upperArmR.rotateAngleX += 40 * (hitTick) * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
                    lowerArmR.rotateAngleX += 50 * (hitTick) * (hitTick - 0.5) * (hitTick - 0.5) * (hitTick - max);
                    upperArmL.rotateAngleX -= 40 * (hitTick) * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
                    lowerArmL.rotateAngleX += 25 * (hitTick) * (Math.pow(hitTick, 0.1) - max);
                }
                
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
                    
                    bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, par1, par2);
                    waist.rotationPointY += (1 * par2) + 3;
                    walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
                    walk(chestmain1, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
                    swing(chestmain1, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0, 0, par1, par2);
                    swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, par1, par2);
                    walk(head, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * par2 * backwardInverter, par1, par2);
                    
                    swing(head, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, par1, par2);
                    head.rotationPointX += 0.6 * globalDegree * par2 * Math.cos(par1 * 0.5F * globalSpeed);
                    
                    swing(upperLegR, 0.5F * globalSpeed, 0F * globalDegree, false, 0, -0.15F, par1, par2);
                    swing(upperLegL, 0.5F * globalSpeed, 0F * globalDegree, false, 0, 0.15F, par1, par2);
                    walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, par1, par2);
                    walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, par1, par2);
                    walk(lowerlegR1, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, par1, par2);
                    walk(lowerlegL1, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, par1, par2);
                    walk(upperArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * par2 * backwardInverter, par1, par2);
                    walk(upperArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * par2 * backwardInverter, par1, par2);
                    walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * par2, par1, par2);
                    walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * par2, par1, par2);
                    
                    int ticksExisted = entity.ticksExisted;
                    
                    walk(stomach, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
                    walk(chestmain1, 0.08F, 0.15F, false, 1, 0, ticksExisted, 1F);
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
                //If not on ground
                {
                    double motionY = entity.posY - entity.prevPosY;
                    
                    float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
                    float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));
                    
                    waist.rotateAngleX += 0.2 * par2 * backwardInverter;
                    
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
                    
                    walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, par1, par2);
                    walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, par1, par2);
                    walk(lowerlegR1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, par1, par2);
                    walk(lowerlegL1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, par1, par2);
                    
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
                this.upperArmL.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
                this.upperArmR.rotateAngleX += (MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2) / 2;
                
                this.lowerArmL.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 4;
                this.lowerArmR.rotateAngleX += (MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2) / 4;
                
                this.upperLegR.rotateAngleX += (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
                this.upperLegL.rotateAngleX += (MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2) / 2;
                
                if (this.isRiding)
                {
                    this.upperArmR.rotateAngleX += -((float) Math.PI / 5F);
                    this.upperArmL.rotateAngleX += -((float) Math.PI / 5F);
                    this.upperLegR.rotateAngleX = -((float) Math.PI * 2F / 5F);
                    this.upperLegL.rotateAngleX = -((float) Math.PI * 2F / 5F);
                    this.upperLegR.rotateAngleY = ((float) Math.PI / 10F);
                    this.upperLegL.rotateAngleY = -((float) Math.PI / 10F);
                }
                
                if (this.aimedBow)
                {
                    this.upperArmR.rotateAngleY += -0.1F + this.bipedHead.rotateAngleY;
                    this.upperArmL.rotateAngleY += 0.1F + this.bipedHead.rotateAngleY + 0.4F;
                    this.upperArmR.rotateAngleX += -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
                    this.upperArmL.rotateAngleX += -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
                    this.upperArmR.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                    this.upperArmL.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                    this.upperArmR.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
                    this.upperArmL.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
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
                    this.waist.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float) Math.PI * 2.0F) * 0.2F;
                    this.upperArmR.rotateAngleY += this.waist.rotateAngleY;
                    this.upperArmL.rotateAngleY += this.waist.rotateAngleY;
                    this.upperArmL.rotateAngleX += this.waist.rotateAngleY;
                    f6 = 1.0F - this.onGround;
                    f6 *= f6;
                    f6 *= f6;
                    f6 = 1.0F - f6;
                    float f7 = MathHelper.sin(f6 * (float) Math.PI);
                    float f8 = MathHelper.sin(this.onGround * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
                    this.upperArmR.rotateAngleX = (float) ((double) this.upperArmR.rotateAngleX - ((double) f7 * 1.2D + (double) f8));
                    this.upperArmR.rotateAngleY += this.waist.rotateAngleY * 2.0F;
                    this.upperArmR.rotateAngleZ = MathHelper.sin(this.onGround * (float) Math.PI) * -0.4F;
                    this.upperArmR.rotateAngleZ += 0.15F;
                }
            }
            
            int t = TFDataManager.getTransformationTimer(player);
            float f = (float) (20 - t);
            
            waist.rotationPointY += f * 0.65F;
            waist.rotateAngleY += f * 0.165F;
            crotch1.rotateAngleY -= f * 0.165F;
            crotch2.rotateAngleY -= f * 0.165F;
            stomach.rotateAngleY -= f * 0.165F;
            chestwheel1.rotationPointX -= f * 0.05F;
            chestwheel2.rotationPointX += f * 0.05F;
            chestmain1.rotationPointY += f * 0.2F;
            head.rotateAngleX += f * 0.1F;
            head.rotationPointY += f * 0.1F;
            head.rotationPointZ += f * 0.2F;
            upperLegL.rotateAngleX += f * 0.09F;
            upperLegR.rotateAngleX += f * 0.09F;
            upperLegL.rotateAngleZ -= f * 0.1F;
            upperLegR.rotateAngleZ += f * 0.1F;
            shoulderbaseL.rotateAngleY += f * 0.1F;
            shoulderbaseR.rotateAngleY -= f * 0.1F;
            shoulderbaseL.rotationPointZ += f * 0.5F;
            shoulderbaseR.rotationPointZ += f * 0.5F;
            lowerArmL.rotateAngleX += f * 0.1F;
            lowerArmL.rotateAngleY -= f * 0.1F;
            lowerArmR.rotateAngleX += f * 0.1F;
            lowerArmR.rotateAngleY += f * 0.1F;
            dish1.rotateAngleZ -= f * 0.08F;
            dish1.rotationPointY -= f * 0.1F;
            
            float wheelSpinSpeed = par1 * 0.8F;
            vehicleFrontWheel1.rotateAngleX = wheelSpinSpeed;
            vehicleFrontWheel2.rotateAngleX = wheelSpinSpeed;
            vehicleRearWheel1.rotateAngleX = wheelSpinSpeed;
            vehicleRearWheel2.rotateAngleX = wheelSpinSpeed;
            
            for (ModelRenderer modelRenderer : new ModelRenderer[] { vehicleBase })
            {
                modelRenderer.rotateAngleY = bipedBody.rotateAngleY;
                
                if (player == Minecraft.getMinecraft().thePlayer)
                {
                    modelRenderer.rotateAngleX = -(float) player.motionY - 0.0784000015258789F;
                }
                else
                {
                    modelRenderer.rotateAngleX = -(float) (player.posY - player.prevPosY) * 1.5F;
                }
            }
            
            ItemStack heldItem = player.getHeldItem();
            
            if (heldItem != null && heldItem.getItem() == TFItems.subwoofersBassBlaster && TFDataManager.getTransformationTimer(player) == 20)
            {
                setRotateAngle(shoulderbaseR, 0.0F, 0.0F, 0.0F);
                setRotateAngle(upperArmR, 0.0F, 0.0F, 0.2F);
                setRotateAngle(lowerArmR, bipedHead.rotateAngleX - pi / 2, bipedHead.rotateAngleY, 0.0F);
            }
        }
    }
}
