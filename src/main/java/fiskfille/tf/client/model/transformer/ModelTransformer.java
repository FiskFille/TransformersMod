// package fiskfille.tf.client.model.transformer;
//
//import fiskfille.tf.TransformerManager;
//import fiskfille.tf.client.model.tools.MowzieModelRenderer;
//import fiskfille.tf.common.entity.EntityTransformer;
//import TFItems;
//import fiskfille.tf.common.transformer.base.Transformer;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.MathHelper;
//import org.lwjgl.opengl.GL11;
//
//public class ModelTransformer extends ModelTransformerBase
//{
//    public MowzieModelRenderer waist;
//    public MowzieModelRenderer torsoconnector;
//    public MowzieModelRenderer upperLegR;
//    public MowzieModelRenderer upperLegL;
//    public MowzieModelRenderer waistL1;
//    public MowzieModelRenderer waistR1;
//    public MowzieModelRenderer crotch1;
//    public MowzieModelRenderer waistL3;
//    public MowzieModelRenderer waistR3;
//    public MowzieModelRenderer torsobase;
//    public MowzieModelRenderer stomach1;
//    public MowzieModelRenderer bodyparts1;
//    public MowzieModelRenderer bodyparts2;
//    public MowzieModelRenderer bodyparts3;
//    public MowzieModelRenderer bodyparts4;
//    public MowzieModelRenderer trackbase1;
//    public MowzieModelRenderer trackconnectorR1;
//    public MowzieModelRenderer trackconnectorL1;
//    public MowzieModelRenderer torsobaseL;
//    public MowzieModelRenderer torsobaseR;
//    public MowzieModelRenderer turretconnector;
//    public MowzieModelRenderer neck1;
//    public MowzieModelRenderer chestplateL1;
//    public MowzieModelRenderer chestplateR1;
//    public MowzieModelRenderer chestplate1;
//    public MowzieModelRenderer upperArmL;
//    public MowzieModelRenderer shoulderL1;
//    public MowzieModelRenderer lowerArmL;
//    public MowzieModelRenderer upperarmL2;
//    public MowzieModelRenderer fistL;
//    public MowzieModelRenderer lowerarmL2;
//    public MowzieModelRenderer lowerarmL3;
//    public MowzieModelRenderer lowerarmL4;
//    public MowzieModelRenderer lowerarmL5;
//    public MowzieModelRenderer shoulderL2;
//    public MowzieModelRenderer shoulderL3;
//    public MowzieModelRenderer shoulderL8;
//    public MowzieModelRenderer shoulderL4;
//    public MowzieModelRenderer shoulderL6;
//    public MowzieModelRenderer shoulderL5;
//    public MowzieModelRenderer shoulderL7;
//    public MowzieModelRenderer upperArmR;
//    public MowzieModelRenderer shoulderR1;
//    public MowzieModelRenderer lowerArmR;
//    public MowzieModelRenderer upperarmR2;
//    public MowzieModelRenderer fistR;
//    public MowzieModelRenderer lowerarmR2;
//    public MowzieModelRenderer lowerarmR3;
//    public MowzieModelRenderer lowerarmR5;
//    public MowzieModelRenderer lowerarmR4;
//    public MowzieModelRenderer shoulderR2;
//    public MowzieModelRenderer shoulderR3;
//    public MowzieModelRenderer shoulderR8;
//    public MowzieModelRenderer shoulderR4;
//    public MowzieModelRenderer shoulderR6;
//    public MowzieModelRenderer shoulderR5;
//    public MowzieModelRenderer shoulderR7;
//    public MowzieModelRenderer turretbase;
//    public MowzieModelRenderer turret1;
//    public MowzieModelRenderer turret5;
//    public MowzieModelRenderer turret11;
//    public MowzieModelRenderer turret12;
//    public MowzieModelRenderer turret2;
//    public MowzieModelRenderer turret3;
//    public MowzieModelRenderer turret4;
//    public MowzieModelRenderer barrelbase1;
//    public MowzieModelRenderer barrelbase2;
//    public MowzieModelRenderer barrelbase3;
//    public MowzieModelRenderer barrelbase4;
//    public MowzieModelRenderer barrelbase5;
//    public MowzieModelRenderer barrel1;
//    public MowzieModelRenderer barrel2;
//    public MowzieModelRenderer turret6;
//    public MowzieModelRenderer turret7;
//    public MowzieModelRenderer turret8;
//    public MowzieModelRenderer turret9;
//    public MowzieModelRenderer turret10;
//    public MowzieModelRenderer headbase;
//    public MowzieModelRenderer headback1;
//    public MowzieModelRenderer headL1;
//    public MowzieModelRenderer headR1;
//    public MowzieModelRenderer headtop1;
//    public MowzieModelRenderer headchin1;
//    public MowzieModelRenderer headL2;
//    public MowzieModelRenderer headR2;
//    public MowzieModelRenderer headcrest1;
//    public MowzieModelRenderer headcrest2;
//    public MowzieModelRenderer headcrest3;
//    public MowzieModelRenderer headcrest4;
//    public MowzieModelRenderer chestplateL2;
//    public MowzieModelRenderer chestplateL3;
//    public MowzieModelRenderer chestplateR2;
//    public MowzieModelRenderer chestplateR3;
//    public MowzieModelRenderer trackconnectorR2;
//    public MowzieModelRenderer trackconnectorR3;
//    public MowzieModelRenderer trackconnectorR4;
//    public MowzieModelRenderer wheelR3;
//    public MowzieModelRenderer wheelR4;
//    public MowzieModelRenderer wheelR5;
//    public MowzieModelRenderer trackR1;
//    public MowzieModelRenderer trackR6;
//    public MowzieModelRenderer trackconnectorR5;
//    public MowzieModelRenderer wheelR2;
//    public MowzieModelRenderer wheelR1;
//    public MowzieModelRenderer trackR2;
//    public MowzieModelRenderer trackR5;
//    public MowzieModelRenderer trackR3;
//    public MowzieModelRenderer trackR4;
//    public MowzieModelRenderer trackR7;
//    public MowzieModelRenderer trackR8;
//    public MowzieModelRenderer trackconnectorL2;
//    public MowzieModelRenderer trackconnectorL3;
//    public MowzieModelRenderer trackconnectorL4;
//    public MowzieModelRenderer wheelL3;
//    public MowzieModelRenderer wheelL4;
//    public MowzieModelRenderer wheelL5;
//    public MowzieModelRenderer trackL1;
//    public MowzieModelRenderer trackL5;
//    public MowzieModelRenderer trackcnnectorL5;
//    public MowzieModelRenderer wheelL2;
//    public MowzieModelRenderer wheelL1;
//    public MowzieModelRenderer trackL2;
//    public MowzieModelRenderer trackL3;
//    public MowzieModelRenderer trackL4;
//    public MowzieModelRenderer trackL6;
//    public MowzieModelRenderer trackL8;
//    public MowzieModelRenderer trackL7;
//    public MowzieModelRenderer lowerlegR1;
//    public MowzieModelRenderer upperlegR2;
//    public MowzieModelRenderer feetbaseR1;
//    public MowzieModelRenderer lowerlegR2;
//    public MowzieModelRenderer feetR2;
//    public MowzieModelRenderer feetR5;
//    public MowzieModelRenderer feetR3;
//    public MowzieModelRenderer feetR4;
//    public MowzieModelRenderer lowerlegR3;
//    public MowzieModelRenderer lowerlegR8;
//    public MowzieModelRenderer lowerlegR10;
//    public MowzieModelRenderer lowerlegR11;
//    public MowzieModelRenderer lowerlegR13;
//    public MowzieModelRenderer lowerlegR4;
//    public MowzieModelRenderer lowerlegR5;
//    public MowzieModelRenderer lowerlegR6;
//    public MowzieModelRenderer lowerlegR7;
//    public MowzieModelRenderer lowerlegR9;
//    public MowzieModelRenderer lowerlegR12;
//    public MowzieModelRenderer lowerlegL1;
//    public MowzieModelRenderer upperlegL2;
//    public MowzieModelRenderer feetbaseL1;
//    public MowzieModelRenderer lowerlegL2;
//    public MowzieModelRenderer feetL2;
//    public MowzieModelRenderer feetL5;
//    public MowzieModelRenderer feetL3;
//    public MowzieModelRenderer feetL4;
//    public MowzieModelRenderer lowerlegL3;
//    public MowzieModelRenderer lowerlegL8;
//    public MowzieModelRenderer lowerlegL10;
//    public MowzieModelRenderer lowerlegL11;
//    public MowzieModelRenderer lowerlegL13;
//    public MowzieModelRenderer lowerlegL4;
//    public MowzieModelRenderer lowerlegL5;
//    public MowzieModelRenderer lowerlegL6;
//    public MowzieModelRenderer lowerlegL7;
//    public MowzieModelRenderer lowerlegL9;
//    public MowzieModelRenderer lowerlegL12;
//    public MowzieModelRenderer waistL2;
//    public MowzieModelRenderer skirtL1;
//    public MowzieModelRenderer skirtL2;
//    public MowzieModelRenderer waistR2;
//    public MowzieModelRenderer skirtR1;
//    public MowzieModelRenderer skirtR2;
//    public MowzieModelRenderer crotch2;
//    public MowzieModelRenderer crotch3;
//    public MowzieModelRenderer boxL1;
//    public MowzieModelRenderer boxL2;
//    public MowzieModelRenderer boxL3;
//    public MowzieModelRenderer boxR1;
//    public MowzieModelRenderer boxR2;
//    public MowzieModelRenderer boxR3;
//
//    public ModelRenderer vehiclebase;
//    public ModelRenderer vehicle1;
//    public ModelRenderer vehicle80;
//    public ModelRenderer vehicle87;
//    public ModelRenderer vehicle94;
//    public ModelRenderer vehicle96;
//    public ModelRenderer vehicle98;
//    public ModelRenderer vehicle102;
//    public ModelRenderer vehicle106;
//    public ModelRenderer vehicle2;
//    public ModelRenderer vehicle42;
//    public ModelRenderer vehicle61;
//    public ModelRenderer vehicle2_1;
//    public ModelRenderer vehicle16;
//    public ModelRenderer vehicle31;
//    public ModelRenderer vehicle33;
//    public ModelRenderer vehicle37;
//    public ModelRenderer vehicle41;
//    public ModelRenderer vehicle3;
//    public ModelRenderer vehicle8;
//    public ModelRenderer vehicle4;
//    public ModelRenderer vehicle5;
//    public ModelRenderer vehicle6;
//    public ModelRenderer vehicle7;
//    public ModelRenderer vehicle9;
//    public ModelRenderer vehicle10;
//    public ModelRenderer vehicle15;
//    public ModelRenderer vehicle11;
//    public ModelRenderer vehicle13;
//    public ModelRenderer vehicle12;
//    public ModelRenderer vehicle14;
//    public ModelRenderer vehicle17;
//    public ModelRenderer vehicle23;
//    public ModelRenderer vehicle18;
//    public ModelRenderer vehicle19;
//    public ModelRenderer vehicle20;
//    public ModelRenderer vehicle22;
//    public ModelRenderer vehicle24;
//    public ModelRenderer vehicle25;
//    public ModelRenderer vehicle30;
//    public ModelRenderer vehicle26;
//    public ModelRenderer vehicle28;
//    public ModelRenderer vehicle27;
//    public ModelRenderer vehicle29;
//    public ModelRenderer vehicleturretbase_rotatehere;
//    public ModelRenderer vehicle32;
//    public ModelRenderer vehicleturret1;
//    public ModelRenderer vehicleturret5;
//    public ModelRenderer vehicleturret11;
//    public ModelRenderer vehicleturret12;
//    public ModelRenderer vehicleturret2;
//    public ModelRenderer vehicleturret3;
//    public ModelRenderer vehicleturret4;
//    public ModelRenderer vehiclebarrelbase1_rotatehere;
//    public ModelRenderer vehiclebarrelbase2;
//    public ModelRenderer vehiclebarrelbase3;
//    public ModelRenderer vehiclebarrelbase4;
//    public ModelRenderer vehiclebarrelbase5;
//    public ModelRenderer vehiclebarrel1;
//    public ModelRenderer vehiclebarrel2;
//    public ModelRenderer vehicleturret6;
//    public ModelRenderer vehicleturret7;
//    public ModelRenderer vehicleturret8;
//    public ModelRenderer vehicleturret9;
//    public ModelRenderer vehicleturret10;
//    public ModelRenderer vehicle34;
//    public ModelRenderer vehicle36;
//    public ModelRenderer vehicle35;
//    public ModelRenderer vehicle38;
//    public ModelRenderer vehicle40;
//    public ModelRenderer vehicle39;
//    public ModelRenderer vehicle43;
//    public ModelRenderer vehicle44;
//    public ModelRenderer vehicle45;
//    public ModelRenderer vehicle50;
//    public ModelRenderer vehicle51;
//    public ModelRenderer vehicle52;
//    public ModelRenderer vehicle53;
//    public ModelRenderer vehicle58;
//    public ModelRenderer vehicle46;
//    public ModelRenderer vehicle49;
//    public ModelRenderer vehicle47;
//    public ModelRenderer vehicle48;
//    public ModelRenderer vehicle54;
//    public ModelRenderer vehicle57;
//    public ModelRenderer vehicle55;
//    public ModelRenderer vehicle56;
//    public ModelRenderer vehicle59;
//    public ModelRenderer vehicle60;
//    public ModelRenderer vehicle62;
//    public ModelRenderer vehicle63;
//    public ModelRenderer vehicle64;
//    public ModelRenderer vehicle69;
//    public ModelRenderer vehicle70;
//    public ModelRenderer vehicle71;
//    public ModelRenderer vehicle72;
//    public ModelRenderer vehicle76;
//    public ModelRenderer vehicle65;
//    public ModelRenderer vehicle68;
//    public ModelRenderer vehicle66;
//    public ModelRenderer vehicle67;
//    public ModelRenderer vehicle73;
//    public ModelRenderer vehicle74;
//    public ModelRenderer vehicle75;
//    public ModelRenderer vehicle77;
//    public ModelRenderer vehicle79;
//    public ModelRenderer vehicle78;
//    public ModelRenderer vehicle81;
//    public ModelRenderer vehicle82;
//    public ModelRenderer vehicle83;
//    public ModelRenderer vehicle84;
//    public ModelRenderer vehicle86;
//    public ModelRenderer vehicle85;
//    public ModelRenderer vehicle88;
//    public ModelRenderer vehicle89;
//    public ModelRenderer vehicle90;
//    public ModelRenderer vehicle91;
//    public ModelRenderer vehicle93;
//    public ModelRenderer vehicle92;
//    public ModelRenderer vehicle95;
//    public ModelRenderer vehicle97;
//    public ModelRenderer vehicle99;
//    public ModelRenderer vehicle100;
//    public ModelRenderer vehicle101;
//    public ModelRenderer vehicle103;
//    public ModelRenderer vehicle104;
//    public ModelRenderer vehicle105;
//    public ModelRenderer vehicle107;
//    public ModelRenderer vehicle108;
//    public ModelRenderer vehicle109;
//
//    public ModelTransformer()
//    {
//        this.textureWidth = 128;
//        this.textureHeight = 128;
//        this.upperarmR2 = new MowzieModelRenderer(this, 27, 119);
//        this.upperarmR2.mirror = true;
//        this.upperarmR2.setRotationPoint(0.0F, -1.0F, -0.5F);
//        this.upperarmR2.addBox(-1.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
//        this.wheelR2 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelR2.mirror = true;
//        this.wheelR2.setRotationPoint(0.5F, -1.5F, 0.0F);
//        this.wheelR2.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.lowerArmL = new MowzieModelRenderer(this, 9, 114);
//        this.lowerArmL.setRotationPoint(-0.1F, 4.6F, -0.2F);
//        this.lowerArmL.addBox(-0.9F, -1.0F, -1.0F, 2, 5, 3, 0.0F);
//        this.setRotateAngle(lowerArmL, -0.2617993877991494F, 0.08726646259971647F, 0.08726646259971647F);
//        this.lowerlegL1 = new MowzieModelRenderer(this, 87, 60);
//        this.lowerlegL1.mirror = true;
//        this.lowerlegL1.setRotationPoint(0.0F, 4.6F, -0.8F);
//        this.lowerlegL1.addBox(-1.0F, -0.3F, 0.0F, 2, 6, 3, 0.0F);
//        this.setRotateAngle(lowerlegL1, 0.17453292519943295F, 0.0F, 0.08726646259971647F);
//        this.feetbaseL1 = new MowzieModelRenderer(this, 89, 70);
//        this.feetbaseL1.mirror = true;
//        this.feetbaseL1.setRotationPoint(0.2F, 6.0F, 1.0F);
//        this.feetbaseL1.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(feetbaseL1, -0.08726646259971647F, -0.08726646259971647F, 0.0F);
//        this.trackL4 = new MowzieModelRenderer(this, 8, 109);
//        this.trackL4.setRotationPoint(-2.25F, -3.25F, 0.0F);
//        this.trackL4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.setRotateAngle(trackL4, 0.0F, 0.017453292519943295F, 0.0F);
//        this.skirtL1 = new MowzieModelRenderer(this, 80, 50);
//        this.skirtL1.setRotationPoint(1.5F, 0.2F, -0.5F);
//        this.skirtL1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(skirtL1, 0.0F, -0.05235987755982988F, -0.10471975511965977F);
//        this.skirtL2 = new MowzieModelRenderer(this, 90, 55);
//        this.skirtL2.setRotationPoint(0.0F, 3.0F, 3.0F);
//        this.skirtL2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.trackR7 = new MowzieModelRenderer(this, 9, 100);
//        this.trackR7.setRotationPoint(-1.0F, 0.0F, 0.0F);
//        this.trackR7.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackR7, 0.0F, 0.0F, 0.5235987755982988F);
//        this.turretbase = new MowzieModelRenderer(this, 16, 77);
//        this.turretbase.setRotationPoint(0.0F, 1.0F, 1.0F);
//        this.turretbase.addBox(-3.0F, -1.0F, 0.0F, 6, 3, 3, 0.0F);
//        this.feetR2 = new MowzieModelRenderer(this, 87, 76);
//        this.feetR2.setRotationPoint(-1.5F, 0.0F, -2.5F);
//        this.feetR2.addBox(0.0F, 0.0F, 3.0F, 3, 2, 2, 0.0F);
//        this.crotch3 = new MowzieModelRenderer(this, 71, 78);
//        this.crotch3.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.crotch3.addBox(0.0F, -2.0F, 0.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(crotch3, -0.17453292519943295F, 0.0F, 0.0F);
//        this.shoulderL7 = new MowzieModelRenderer(this, 24, 64);
//        this.shoulderL7.setRotationPoint(0.5F, 0.0F, 0.0F);
//        this.shoulderL7.addBox(0.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(shoulderL7, 0.3490658503988659F, 0.0F, 0.0F);
//        this.barrel1 = new MowzieModelRenderer(this, 40, 87);
//        this.barrel1.setRotationPoint(0.0F, -3.4F, 0.0F);
//        this.barrel1.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
//        this.shoulderR4 = new MowzieModelRenderer(this, 34, 53);
//        this.shoulderR4.mirror = true;
//        this.shoulderR4.setRotationPoint(-2.0F, 1.0F, 0.0F);
//        this.shoulderR4.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
//        this.turret10 = new MowzieModelRenderer(this, 27, 104);
//        this.turret10.setRotationPoint(-2.0F, 0.0F, 0.0F);
//        this.turret10.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(turret10, 0.0F, 0.017453292519943295F, -0.2617993877991494F);
//        this.headbase = new MowzieModelRenderer(this, 3, 52);
//        this.headbase.setRotationPoint(0.0F, -0.5F, 0.0F);
//        this.headbase.addBox(-1.5F, -3.0F, -1.5F, 3, 3, 3, 0.0F);
//        this.lowerlegR7 = new MowzieModelRenderer(this, 94, 85);
//        this.lowerlegR7.setRotationPoint(0.0F, 2.0F, -0.4F);
//        this.lowerlegR7.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(lowerlegR7, -0.17453292519943295F, -0.017453292519943295F, 0.0F);
//        this.headchin1 = new MowzieModelRenderer(this, 10, 77);
//        this.headchin1.setRotationPoint(-1.0F, -0.9F, -1.6F);
//        this.headchin1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(headchin1, -0.17453292519943295F, 0.0F, 0.0F);
//        this.boxR2 = new MowzieModelRenderer(this, 2, 82);
//        this.boxR2.setRotationPoint(0.0F, -0.9F, 0.2F);
//        this.boxR2.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.setRotateAngle(boxR2, -0.32637657012293964F, 0.0F, 0.0F);
//        this.trackL6 = new MowzieModelRenderer(this, 9, 100);
//        this.trackL6.mirror = true;
//        this.trackL6.setRotationPoint(1.0F, 0.0F, 0.0F);
//        this.trackL6.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackL6, 0.0F, 0.0F, -0.5235987755982988F);
//        this.torsobaseL = new MowzieModelRenderer(this, 52, 58);
//        this.torsobaseL.setRotationPoint(2.5F, -1.0F, 0.0F);
//        this.torsobaseL.addBox(0.0F, -3.0F, -1.5F, 1, 3, 3, 0.0F);
//        this.trackconnectorR5 = new MowzieModelRenderer(this, 20, 96);
//        this.trackconnectorR5.mirror = true;
//        this.trackconnectorR5.setRotationPoint(1.0F, -3.0F, 0.0F);
//        this.trackconnectorR5.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(trackconnectorR5, -0.017453292519943295F, 0.0F, -0.5235987755982988F);
//        this.chestplate1 = new MowzieModelRenderer(this, 54, 65);
//        this.chestplate1.setRotationPoint(-1.0F, -2.3F, -3.0F);
//        this.chestplate1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(chestplate1, 0.3141592653589793F, 0.0F, 0.0F);
//        this.lowerlegR11 = new MowzieModelRenderer(this, 69, 82);
//        this.lowerlegR11.setRotationPoint(-0.5F, 2.5F, -0.5F);
//        this.lowerlegR11.addBox(-2.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
//        this.feetL3 = new MowzieModelRenderer(this, 90, 81);
//        this.feetL3.mirror = true;
//        this.feetL3.setRotationPoint(0.5F, 1.1F, -1.4F);
//        this.feetL3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
//        this.setRotateAngle(feetL3, 0.41887902047863906F, 0.0F, 0.017453292519943295F);
//        this.skirtR2 = new MowzieModelRenderer(this, 90, 55);
//        this.skirtR2.mirror = true;
//        this.skirtR2.setRotationPoint(0.0F, 3.0F, 3.0F);
//        this.skirtR2.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.torsobaseR = new MowzieModelRenderer(this, 52, 58);
//        this.torsobaseR.mirror = true;
//        this.torsobaseR.setRotationPoint(-2.5F, -1.0F, 0.0F);
//        this.torsobaseR.addBox(-1.0F, -3.0F, -1.5F, 1, 3, 3, 0.0F);
//        this.feetbaseR1 = new MowzieModelRenderer(this, 89, 70);
//        this.feetbaseR1.setRotationPoint(-0.2F, 6.0F, 1.0F);
//        this.feetbaseR1.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(feetbaseR1, -0.08726646259971647F, 0.08726646259971647F, 0.0F);
//        this.lowerlegL3 = new MowzieModelRenderer(this, 80, 88);
//        this.lowerlegL3.mirror = true;
//        this.lowerlegL3.setRotationPoint(2.0F, 1.0F, 1.0F);
//        this.lowerlegL3.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(lowerlegL3, -0.19198621771937624F, 0.017453292519943295F, 0.0F);
//        this.upperLegR = new MowzieModelRenderer(this, 80, 68);
//        this.upperLegR.setRotationPoint(-2.0F, 2.0F, 0.0F);
//        this.upperLegR.addBox(-1.0F, -1.5F, -1.0F, 2, 6, 2, 0.0F);
//        this.setRotateAngle(upperLegR, -0.08726646259971647F, 0.0F, 0.08726646259971647F);
//        this.lowerArmR = new MowzieModelRenderer(this, 9, 114);
//        this.lowerArmR.mirror = true;
//        this.lowerArmR.setRotationPoint(0.1F, 4.6F, -0.2F);
//        this.lowerArmR.addBox(-1.1F, -1.0F, -1.0F, 2, 5, 3, 0.0F);
//        this.setRotateAngle(lowerArmR, -0.2617993877991494F, -0.08726646259971647F, -0.08726646259971647F);
//        this.shoulderR3 = new MowzieModelRenderer(this, 25, 63);
//        this.shoulderR3.mirror = true;
//        this.shoulderR3.setRotationPoint(-0.5F, -1.5F, -2.0F);
//        this.shoulderR3.addBox(-3.0F, 0.0F, 0.0F, 4, 1, 5, 0.0F);
//        this.turret3 = new MowzieModelRenderer(this, 35, 77);
//        this.turret3.setRotationPoint(-2.3F, 2.5F, 0.0F);
//        this.turret3.addBox(-2.0F, -2.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(turret3, 0.0F, 0.0F, 1.1990411961201044F);
//        this.upperLegL = new MowzieModelRenderer(this, 80, 68);
//        this.upperLegL.mirror = true;
//        this.upperLegL.setRotationPoint(2.0F, 2.0F, 0.0F);
//        this.upperLegL.addBox(-1.0F, -1.5F, -1.0F, 2, 6, 2, 0.0F);
//        this.setRotateAngle(upperLegL, -0.08726646259971647F, 0.0F, -0.08726646259971647F);
//        this.chestplateR2 = new MowzieModelRenderer(this, 71, 58);
//        this.chestplateR2.mirror = true;
//        this.chestplateR2.setRotationPoint(0.0F, 0.5F, 0.0F);
//        this.chestplateR2.addBox(-3.0F, -2.0F, 0.0F, 3, 2, 1, 0.0F);
//        this.setRotateAngle(chestplateR2, -0.4363323129985824F, -0.017453292519943295F, 0.0F);
//        this.trackconnectorR4 = new MowzieModelRenderer(this, 24, 96);
//        this.trackconnectorR4.mirror = true;
//        this.trackconnectorR4.setRotationPoint(-1.0F, -6.0F, 0.0F);
//        this.trackconnectorR4.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.setRotateAngle(trackconnectorR4, 0.0F, 0.0F, 0.5235987755982988F);
//        this.lowerlegR5 = new MowzieModelRenderer(this, 94, 85);
//        this.lowerlegR5.setRotationPoint(1.8F, 0.6F, -1.8F);
//        this.lowerlegR5.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(lowerlegR5, 0.40142572795869574F, 0.03490658503988659F, 0.0F);
//        this.lowerlegL2 = new MowzieModelRenderer(this, 71, 88);
//        this.lowerlegL2.mirror = true;
//        this.lowerlegL2.setRotationPoint(-1.2F, -1.0F, -1.0F);
//        this.lowerlegL2.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1, 0.0F);
//        this.shoulderL5 = new MowzieModelRenderer(this, 39, 63);
//        this.shoulderL5.setRotationPoint(0.2F, 2.5F, 0.6F);
//        this.shoulderL5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
//        this.setRotateAngle(shoulderL5, -0.03490658503988659F, 0.0F, 0.0F);
//        this.trackL3 = new MowzieModelRenderer(this, 9, 100);
//        this.trackL3.setRotationPoint(-1.0F, -3.0F, 0.0F);
//        this.trackL3.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackL3, -0.03490658503988659F, -0.017453292519943295F, 0.5235987755982988F);
//        this.skirtR1 = new MowzieModelRenderer(this, 80, 50);
//        this.skirtR1.mirror = true;
//        this.skirtR1.setRotationPoint(-1.5F, 0.2F, -0.5F);
//        this.skirtR1.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(skirtR1, 0.0F, 0.05235987755982988F, 0.10471975511965977F);
//        this.lowerlegL9 = new MowzieModelRenderer(this, 73, 84);
//        this.lowerlegL9.mirror = true;
//        this.lowerlegL9.setRotationPoint(0.0F, -1.0F, 0.0F);
//        this.lowerlegL9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
//        this.setRotateAngle(lowerlegL9, -0.3490658503988659F, 0.017453292519943295F, 0.0F);
//        this.upperArmL = new MowzieModelRenderer(this, 24, 111);
//        this.upperArmL.setRotationPoint(2.0F, -1.5F, 0.0F);
//        this.upperArmL.addBox(-1.0F, -1.0F, -1.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(upperArmL, 0.08726646259971647F, 0.0F, -0.17453292519943295F);
//        this.trackcnnectorL5 = new MowzieModelRenderer(this, 20, 96);
//        this.trackcnnectorL5.setRotationPoint(-1.0F, -3.0F, 0.0F);
//        this.trackcnnectorL5.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(trackcnnectorL5, -0.017453292519943295F, 0.0F, 0.5235987755982988F);
//        this.turret11 = new MowzieModelRenderer(this, 33, 103);
//        this.turret11.setRotationPoint(-2.6F, -1.1F, 2.7F);
//        this.turret11.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
//        this.fistL = new MowzieModelRenderer(this, 0, 115);
//        this.fistL.setRotationPoint(0.1F, 3.6F, 0.2F);
//        this.fistL.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(fistL, -0.08726646259971647F, 0.0F, 0.20943951023931953F);
//        this.turret9 = new MowzieModelRenderer(this, 27, 104);
//        this.turret9.setRotationPoint(2.0F, 0.0F, 0.0F);
//        this.turret9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(turret9, 0.0F, -0.017453292519943295F, 0.2617993877991494F);
//        this.shoulderL1 = new MowzieModelRenderer(this, 25, 50);
//        this.shoulderL1.setRotationPoint(1.0F, -2.5F, 0.5F);
//        this.shoulderL1.addBox(0.0F, -0.5F, -2.5F, 1, 3, 4, 0.0F);
//        this.trackR4 = new MowzieModelRenderer(this, 8, 109);
//        this.trackR4.mirror = true;
//        this.trackR4.setRotationPoint(2.25F, -3.25F, 0.0F);
//        this.trackR4.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.setRotateAngle(trackR4, 0.0F, -0.017453292519943295F, 0.0F);
//        this.upperlegR2 = new MowzieModelRenderer(this, 82, 62);
//        this.upperlegR2.setRotationPoint(-0.5F, 0.0F, 0.9F);
//        this.upperlegR2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
//        this.shoulderL4 = new MowzieModelRenderer(this, 34, 53);
//        this.shoulderL4.setRotationPoint(2.0F, 1.0F, 0.0F);
//        this.shoulderL4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
//        this.lowerarmL3 = new MowzieModelRenderer(this, 6, 114);
//        this.lowerarmL3.setRotationPoint(-0.9F, -1.0F, 2.0F);
//        this.lowerarmL3.addBox(0.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(lowerarmL3, 0.17453292519943295F, 0.0F, 0.0F);
//        this.wheelR5 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelR5.mirror = true;
//        this.wheelR5.setRotationPoint(-0.5F, -4.5F, 0.0F);
//        this.wheelR5.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.turret8 = new MowzieModelRenderer(this, 27, 99);
//        this.turret8.setRotationPoint(0.0F, -1.3F, 0.9F);
//        this.turret8.addBox(-2.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F);
//        this.lowerlegL8 = new MowzieModelRenderer(this, 62, 89);
//        this.lowerlegL8.mirror = true;
//        this.lowerlegL8.setRotationPoint(-0.2F, 2.1F, 1.0F);
//        this.lowerlegL8.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
//        this.boxR1 = new MowzieModelRenderer(this, 2, 86);
//        this.boxR1.mirror = true;
//        this.boxR1.setRotationPoint(-0.7F, -0.1F, 0.0F);
//        this.boxR1.addBox(-3.0F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
//        this.boxL3 = new MowzieModelRenderer(this, 10, 86);
//        this.boxL3.mirror = true;
//        this.boxL3.setRotationPoint(1.0F, -0.2F, 1.2F);
//        this.boxL3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(boxL3, 0.06806784082777885F, 0.0F, 0.0F);
//        this.feetR4 = new MowzieModelRenderer(this, 80, 77);
//        this.feetR4.setRotationPoint(-0.3F, -1.0F, 2.5F);
//        this.feetR4.addBox(-1.0F, -1.0F, 0.0F, 1, 3, 2, 0.0F);
//        this.setRotateAngle(feetR4, -1.0471975511965976F, 0.0F, 0.0F);
//        this.bodyparts2 = new MowzieModelRenderer(this, 63, 66);
//        this.bodyparts2.mirror = true;
//        this.bodyparts2.setRotationPoint(-1.0F, -4.8F, 2.0F);
//        this.bodyparts2.addBox(-1.0F, 0.0F, -1.0F, 1, 5, 1, 0.0F);
//        this.setRotateAngle(bodyparts2, -0.12217304763960307F, 0.0F, -0.10471975511965977F);
//        this.headcrest1 = new MowzieModelRenderer(this, 3, 71);
//        this.headcrest1.setRotationPoint(0.0F, 0.9F, -1.5F);
//        this.headcrest1.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
//        this.setRotateAngle(headcrest1, 0.08726646259971647F, 0.0F, 0.0F);
//        this.headR1 = new MowzieModelRenderer(this, 3, 64);
//        this.headR1.mirror = true;
//        this.headR1.setRotationPoint(-1.0F, -2.7F, -1.4F);
//        this.headR1.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(headR1, 0.0F, 0.0F, 0.13962634015954636F);
//        this.boxL1 = new MowzieModelRenderer(this, 2, 86);
//        this.boxL1.setRotationPoint(0.7F, -0.1F, 0.0F);
//        this.boxL1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
//        this.wheelR1 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelR1.mirror = true;
//        this.wheelR1.setRotationPoint(-0.5F, -1.5F, 0.0F);
//        this.wheelR1.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.wheelL5 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelL5.setRotationPoint(0.5F, -4.5F, 0.0F);
//        this.wheelL5.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.wheelL2 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelL2.setRotationPoint(-0.5F, -1.5F, 0.0F);
//        this.wheelL2.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.waistR2 = new MowzieModelRenderer(this, 56, 77);
//        this.waistR2.mirror = true;
//        this.waistR2.setRotationPoint(0.0F, 0.0F, -1.0F);
//        this.waistR2.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(waistR2, 0.0F, 0.15184364492350666F, 0.0F);
//        this.trackbase1 = new MowzieModelRenderer(this, 63, 74);
//        this.trackbase1.setRotationPoint(1.5F, -2.0F, 0.0F);
//        this.trackbase1.addBox(-4.0F, -2.0F, 0.0F, 5, 1, 2, 0.0F);
//        this.setRotateAngle(trackbase1, -0.06981317007977318F, 0.0F, 0.0F);
//        this.bodyparts3 = new MowzieModelRenderer(this, 68, 69);
//        this.bodyparts3.setRotationPoint(1.2F, -2.0F, -0.2F);
//        this.bodyparts3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(bodyparts3, -0.15707963267948966F, 0.0F, 0.0F);
//        this.wheelR4 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelR4.mirror = true;
//        this.wheelR4.setRotationPoint(0.5F, -2.0F, 0.0F);
//        this.wheelR4.addBox(-2.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.chestplateR1 = new MowzieModelRenderer(this, 61, 59);
//        this.chestplateR1.mirror = true;
//        this.chestplateR1.setRotationPoint(-0.2F, -2.8F, -2.7F);
//        this.chestplateR1.addBox(-3.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
//        this.setRotateAngle(chestplateR1, 0.3141592653589793F, 0.13962634015954636F, 0.0F);
//        this.lowerlegL6 = new MowzieModelRenderer(this, 94, 85);
//        this.lowerlegL6.mirror = true;
//        this.lowerlegL6.setRotationPoint(0.0F, 2.0F, 1.6F);
//        this.lowerlegL6.addBox(0.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(lowerlegL6, -0.22689280275926282F, 0.017453292519943295F, 0.0F);
//        this.waistR3 = new MowzieModelRenderer(this, 60, 78);
//        this.waistR3.mirror = true;
//        this.waistR3.setRotationPoint(0.0F, 0.0F, 2.5F);
//        this.waistR3.addBox(-3.0F, 0.0F, -2.0F, 3, 2, 2, 0.0F);
//        this.setRotateAngle(waistR3, 0.0F, -0.24434609527920614F, 0.0F);
//        this.headcrest4 = new MowzieModelRenderer(this, 0, 77);
//        this.headcrest4.setRotationPoint(0.0F, -0.5F, 0.0F);
//        this.headcrest4.addBox(-1.5F, 0.0F, -1.5F, 3, 1, 3, 0.0F);
//        this.turret4 = new MowzieModelRenderer(this, 32, 83);
//        this.turret4.setRotationPoint(0.0F, 2.0F, 2.0F);
//        this.turret4.addBox(-2.5F, 0.0F, -2.0F, 5, 1, 3, 0.0F);
//        this.wheelL3 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelL3.setRotationPoint(0.5F, 0.5F, 0.0F);
//        this.wheelL3.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.shoulderL8 = new MowzieModelRenderer(this, 25, 58);
//        this.shoulderL8.setRotationPoint(1.0F, -0.5F, -1.7F);
//        this.shoulderL8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
//        this.lowerarmR4 = new MowzieModelRenderer(this, 16, 114);
//        this.lowerarmR4.mirror = true;
//        this.lowerarmR4.setRotationPoint(-1.0F, 3.8F, 0.0F);
//        this.lowerarmR4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
//        this.setRotateAngle(lowerarmR4, 0.017453292519943295F, 0.0F, -0.3839724354387525F);
//        this.headcrest2 = new MowzieModelRenderer(this, 16, 71);
//        this.headcrest2.setRotationPoint(1.0F, 0.5F, -1.5F);
//        this.headcrest2.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(headcrest2, 0.20071286397934787F, 0.0F, 0.19198621771937624F);
//        this.trackconnectorR3 = new MowzieModelRenderer(this, 29, 90);
//        this.trackconnectorR3.mirror = true;
//        this.trackconnectorR3.setRotationPoint(-2.0F, 0.0F, -1.0F);
//        this.trackconnectorR3.addBox(-1.0F, -6.0F, 0.0F, 1, 7, 1, 0.0F);
//        this.setRotateAngle(trackconnectorR3, 0.06981317007977318F, 0.10471975511965977F, 0.03490658503988659F);
//        this.trackR2 = new MowzieModelRenderer(this, 9, 100);
//        this.trackR2.mirror = true;
//        this.trackR2.setRotationPoint(-1.0F, 0.0F, 0.0F);
//        this.trackR2.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackR2, 0.0F, 0.0F, 0.5235987755982988F);
//        this.turret6 = new MowzieModelRenderer(this, 42, 94);
//        this.turret6.setRotationPoint(1.5F, 0.0F, 0.0F);
//        this.turret6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(turret6, 0.0F, 0.0F, 0.7138396640656808F);
//        this.lowerarmR2 = new MowzieModelRenderer(this, 2, 120);
//        this.lowerarmR2.mirror = true;
//        this.lowerarmR2.setRotationPoint(0.9F, -1.0F, -1.0F);
//        this.lowerarmR2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(lowerarmR2, 0.03490658503988659F, -0.03490658503988659F, 0.19198621771937624F);
//        this.turret7 = new MowzieModelRenderer(this, 42, 94);
//        this.turret7.setRotationPoint(-1.5F, 0.0F, 0.0F);
//        this.turret7.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(turret7, 0.0F, 0.0F, -0.7138396640656808F);
//        this.lowerlegR12 = new MowzieModelRenderer(this, 85, 88);
//        this.lowerlegR12.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.lowerlegR12.addBox(-2.0F, -1.0F, 0.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(lowerlegR12, -0.5061454830783556F, 0.0F, -0.017453292519943295F);
//        this.waistL3 = new MowzieModelRenderer(this, 60, 78);
//        this.waistL3.setRotationPoint(0.0F, 0.0F, 2.5F);
//        this.waistL3.addBox(0.0F, 0.0F, -2.0F, 3, 2, 2, 0.0F);
//        this.setRotateAngle(waistL3, 0.0F, 0.24434609527920614F, 0.0F);
//        this.headcrest3 = new MowzieModelRenderer(this, 16, 71);
//        this.headcrest3.mirror = true;
//        this.headcrest3.setRotationPoint(-1.0F, 0.5F, -1.5F);
//        this.headcrest3.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(headcrest3, 0.20071286397934787F, 0.0F, -0.19198621771937624F);
//        this.trackconnectorR2 = new MowzieModelRenderer(this, 20, 93);
//        this.trackconnectorR2.mirror = true;
//        this.trackconnectorR2.setRotationPoint(0.0F, 0.1F, -0.1F);
//        this.trackconnectorR2.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(trackconnectorR2, 0.0F, 0.7853981633974483F, -0.13962634015954636F);
//        this.upperarmL2 = new MowzieModelRenderer(this, 27, 119);
//        this.upperarmL2.setRotationPoint(0.0F, -1.0F, -0.5F);
//        this.upperarmL2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
//        this.headL2 = new MowzieModelRenderer(this, 9, 63);
//        this.headL2.setRotationPoint(0.0F, 0.7F, 0.0F);
//        this.headL2.addBox(0.0F, 0.0F, -1.0F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(headL2, 0.4625122517784973F, 0.0F, 0.017453292519943295F);
//        this.turret2 = new MowzieModelRenderer(this, 35, 77);
//        this.turret2.setRotationPoint(2.3F, 2.5F, 0.0F);
//        this.turret2.addBox(0.0F, -2.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(turret2, 0.0F, 0.0F, -1.1990411961201044F);
//        this.barrelbase4 = new MowzieModelRenderer(this, 35, 90);
//        this.barrelbase4.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.barrelbase4.addBox(-0.2F, 0.5F, -0.8F, 1, 2, 1, 0.0F);
//        this.boxL2 = new MowzieModelRenderer(this, 2, 82);
//        this.boxL2.setRotationPoint(0.0F, -0.9F, 0.2F);
//        this.boxL2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.setRotateAngle(boxL2, -0.32637657012293964F, 0.0F, 0.0F);
//        this.lowerlegL12 = new MowzieModelRenderer(this, 85, 88);
//        this.lowerlegL12.mirror = true;
//        this.lowerlegL12.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.lowerlegL12.addBox(0.0F, -1.0F, 0.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(lowerlegL12, -0.5061454830783556F, 0.0F, 0.017453292519943295F);
//        this.shoulderL6 = new MowzieModelRenderer(this, 31, 50);
//        this.shoulderL6.setRotationPoint(-0.5F, -0.3F, 2.8F);
//        this.shoulderL6.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.headL1 = new MowzieModelRenderer(this, 3, 64);
//        this.headL1.setRotationPoint(1.0F, -2.7F, -1.4F);
//        this.headL1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(headL1, 0.0F, 0.0F, -0.13962634015954636F);
//        this.lowerarmL5 = new MowzieModelRenderer(this, 16, 114);
//        this.lowerarmL5.setRotationPoint(1.0F, 3.8F, 0.0F);
//        this.lowerarmL5.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
//        this.setRotateAngle(lowerarmL5, 0.017453292519943295F, 0.0F, 0.3839724354387525F);
//        this.lowerlegR13 = new MowzieModelRenderer(this, 98, 77);
//        this.lowerlegR13.setRotationPoint(-2.3F, 1.3F, 0.5F);
//        this.lowerlegR13.addBox(-1.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
//        this.lowerlegR2 = new MowzieModelRenderer(this, 71, 88);
//        this.lowerlegR2.setRotationPoint(1.2F, -1.0F, -1.0F);
//        this.lowerlegR2.addBox(-3.0F, 0.0F, 0.0F, 3, 6, 1, 0.0F);
//        this.trackL8 = new MowzieModelRenderer(this, 8, 109);
//        this.trackL8.setRotationPoint(0.25F, 7.25F, 0.0F);
//        this.trackL8.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.setRotateAngle(trackL8, -0.03490658503988659F, 0.0F, 0.0F);
//        this.upperArmR = new MowzieModelRenderer(this, 24, 111);
//        this.upperArmR.mirror = true;
//        this.upperArmR.setRotationPoint(-2.0F, -1.5F, 0.0F);
//        this.upperArmR.addBox(0.0F, -1.0F, -1.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(upperArmR, 0.08726646259971647F, 0.0F, 0.17453292519943295F);
//        this.trackconnectorL4 = new MowzieModelRenderer(this, 24, 96);
//        this.trackconnectorL4.setRotationPoint(1.0F, -6.0F, 0.0F);
//        this.trackconnectorL4.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.setRotateAngle(trackconnectorL4, 0.0F, 0.0F, -0.5235987755982988F);
//        this.shoulderR1 = new MowzieModelRenderer(this, 25, 50);
//        this.shoulderR1.mirror = true;
//        this.shoulderR1.setRotationPoint(-1.0F, -2.5F, 0.5F);
//        this.shoulderR1.addBox(-1.0F, -0.5F, -2.5F, 1, 3, 4, 0.0F);
//        this.lowerlegR6 = new MowzieModelRenderer(this, 94, 85);
//        this.lowerlegR6.setRotationPoint(0.0F, 2.0F, 1.6F);
//        this.lowerlegR6.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(lowerlegR6, -0.22689280275926282F, -0.017453292519943295F, 0.0F);
//        this.turret1 = new MowzieModelRenderer(this, 32, 71);
//        this.turret1.setRotationPoint(0.0F, 1.3F, 0.0F);
//        this.turret1.addBox(-2.0F, 0.4F, 0.0F, 4, 2, 3, 0.0F);
//        this.turret5 = new MowzieModelRenderer(this, 45, 88);
//        this.turret5.setRotationPoint(0.0F, -2.3F, 0.0F);
//        this.turret5.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 3, 0.0F);
//        this.wheelR3 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelR3.mirror = true;
//        this.wheelR3.setRotationPoint(-0.5F, 0.5F, 0.0F);
//        this.wheelR3.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.feetL4 = new MowzieModelRenderer(this, 80, 77);
//        this.feetL4.mirror = true;
//        this.feetL4.setRotationPoint(0.3F, -1.0F, 2.5F);
//        this.feetL4.addBox(0.0F, -1.0F, 0.0F, 1, 3, 2, 0.0F);
//        this.setRotateAngle(feetL4, -1.0471975511965976F, 0.0F, 0.0F);
//        this.trackconnectorL2 = new MowzieModelRenderer(this, 20, 93);
//        this.trackconnectorL2.setRotationPoint(0.0F, 0.1F, -0.1F);
//        this.trackconnectorL2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(trackconnectorL2, 0.0F, -0.7853981633974483F, 0.13962634015954636F);
//        this.lowerlegR10 = new MowzieModelRenderer(this, 96, 90);
//        this.lowerlegR10.setRotationPoint(0.2F, 5.0F, 0.0F);
//        this.lowerlegR10.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 4, 0.0F);
//        this.setRotateAngle(lowerlegR10, 0.0F, 0.08726646259971647F, 0.0F);
//        this.crotch2 = new MowzieModelRenderer(this, 58, 89);
//        this.crotch2.setRotationPoint(1.0F, 2.0F, 3.0F);
//        this.crotch2.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(crotch2, -0.4886921905584123F, 0.0F, 0.0F);
//        this.torsoconnector = new MowzieModelRenderer(this, 50, 50);
//        this.torsoconnector.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.torsoconnector.addBox(-1.5F, -5.0F, -1.0F, 3, 5, 2, 0.0F);
//        this.chestplateL1 = new MowzieModelRenderer(this, 61, 59);
//        this.chestplateL1.setRotationPoint(0.2F, -2.8F, -2.7F);
//        this.chestplateL1.addBox(0.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
//        this.setRotateAngle(chestplateL1, 0.3141592653589793F, -0.13962634015954636F, 0.0F);
//        this.shoulderL3 = new MowzieModelRenderer(this, 25, 63);
//        this.shoulderL3.setRotationPoint(0.5F, -1.5F, -2.0F);
//        this.shoulderL3.addBox(-1.0F, 0.0F, 0.0F, 4, 1, 5, 0.0F);
//        this.turretconnector = new MowzieModelRenderer(this, 21, 70);
//        this.turretconnector.setRotationPoint(0.0F, -4.0F, 1.5F);
//        this.turretconnector.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 1, 0.0F);
//        this.setRotateAngle(turretconnector, 0.10471975511965977F, 0.0F, 0.0F);
//        this.trackconnectorR1 = new MowzieModelRenderer(this, 20, 90);
//        this.trackconnectorR1.mirror = true;
//        this.trackconnectorR1.setRotationPoint(-3.0F, -4.0F, 3.0F);
//        this.trackconnectorR1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(trackconnectorR1, 0.06981317007977318F, 0.7853981633974483F, 0.13962634015954636F);
//        this.trackR3 = new MowzieModelRenderer(this, 9, 100);
//        this.trackR3.mirror = true;
//        this.trackR3.setRotationPoint(1.0F, -3.0F, 0.0F);
//        this.trackR3.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackR3, -0.03490658503988659F, 0.017453292519943295F, -0.5235987755982988F);
//        this.trackR8 = new MowzieModelRenderer(this, 9, 100);
//        this.trackR8.setRotationPoint(1.0F, -3.0F, 0.0F);
//        this.trackR8.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackR8, -0.03490658503988659F, 0.017453292519943295F, -0.5235987755982988F);
//        this.lowerlegR9 = new MowzieModelRenderer(this, 73, 84);
//        this.lowerlegR9.setRotationPoint(0.0F, -1.0F, 0.0F);
//        this.lowerlegR9.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
//        this.setRotateAngle(lowerlegR9, -0.3490658503988659F, -0.017453292519943295F, 0.0F);
//        this.chestplateL2 = new MowzieModelRenderer(this, 71, 58);
//        this.chestplateL2.setRotationPoint(0.0F, 0.5F, 0.0F);
//        this.chestplateL2.addBox(0.0F, -2.0F, 0.0F, 3, 2, 1, 0.0F);
//        this.setRotateAngle(chestplateL2, -0.4363323129985824F, 0.017453292519943295F, 0.0F);
//        this.shoulderR5 = new MowzieModelRenderer(this, 39, 63);
//        this.shoulderR5.mirror = true;
//        this.shoulderR5.setRotationPoint(-0.2F, 2.5F, 0.6F);
//        this.shoulderR5.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
//        this.setRotateAngle(shoulderR5, -0.03490658503988659F, 0.0F, 0.0F);
//        this.shoulderR7 = new MowzieModelRenderer(this, 24, 64);
//        this.shoulderR7.mirror = true;
//        this.shoulderR7.setRotationPoint(-0.5F, 0.0F, 0.0F);
//        this.shoulderR7.addBox(-2.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(shoulderR7, 0.3490658503988659F, 0.0F, 0.0F);
//        this.lowerlegR4 = new MowzieModelRenderer(this, 89, 88);
//        this.lowerlegR4.setRotationPoint(0.0F, 0.0F, 3.1F);
//        this.lowerlegR4.addBox(-1.0F, -0.3F, -2.0F, 1, 6, 2, 0.0F);
//        this.setRotateAngle(lowerlegR4, 0.3211405823669566F, -0.03490658503988659F, 0.0F);
//        this.boxR3 = new MowzieModelRenderer(this, 10, 86);
//        this.boxR3.setRotationPoint(-1.0F, -0.2F, 1.2F);
//        this.boxR3.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(boxR3, 0.06806784082777885F, 0.0F, 0.0F);
//        this.shoulderL2 = new MowzieModelRenderer(this, 25, 50);
//        this.shoulderL2.mirror = true;
//        this.shoulderL2.setRotationPoint(1.5F, -0.5F, -2.5F);
//        this.shoulderL2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
//        this.shoulderR6 = new MowzieModelRenderer(this, 31, 50);
//        this.shoulderR6.mirror = true;
//        this.shoulderR6.setRotationPoint(0.5F, -0.3F, 2.8F);
//        this.shoulderR6.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.trackconnectorL3 = new MowzieModelRenderer(this, 29, 90);
//        this.trackconnectorL3.setRotationPoint(2.0F, 0.0F, -1.0F);
//        this.trackconnectorL3.addBox(0.0F, -6.0F, 0.0F, 1, 7, 1, 0.0F);
//        this.setRotateAngle(trackconnectorL3, 0.05235987755982988F, -0.10471975511965977F, -0.03490658503988659F);
//        this.lowerlegR3 = new MowzieModelRenderer(this, 80, 88);
//        this.lowerlegR3.setRotationPoint(-2.0F, 1.0F, 1.0F);
//        this.lowerlegR3.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(lowerlegR3, -0.19198621771937624F, -0.017453292519943295F, 0.0F);
//        this.feetL5 = new MowzieModelRenderer(this, 79, 82);
//        this.feetL5.mirror = true;
//        this.feetL5.setRotationPoint(-1.5F, 1.0F, -4.0F);
//        this.feetL5.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
//        this.bodyparts1 = new MowzieModelRenderer(this, 63, 66);
//        this.bodyparts1.setRotationPoint(1.0F, -4.8F, 2.0F);
//        this.bodyparts1.addBox(0.0F, 0.0F, -1.0F, 1, 5, 1, 0.0F);
//        this.setRotateAngle(bodyparts1, -0.12217304763960307F, 0.0F, 0.10471975511965977F);
//        this.lowerlegL7 = new MowzieModelRenderer(this, 94, 85);
//        this.lowerlegL7.mirror = true;
//        this.lowerlegL7.setRotationPoint(0.0F, 2.0F, -0.4F);
//        this.lowerlegL7.addBox(0.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(lowerlegL7, -0.17453292519943295F, 0.017453292519943295F, 0.0F);
//        this.feetL2 = new MowzieModelRenderer(this, 87, 76);
//        this.feetL2.mirror = true;
//        this.feetL2.setRotationPoint(-1.5F, 0.0F, -2.5F);
//        this.feetL2.addBox(0.0F, 0.0F, 3.0F, 3, 2, 2, 0.0F);
//        this.chestplateL3 = new MowzieModelRenderer(this, 70, 62);
//        this.chestplateL3.setRotationPoint(3.0F, -1.9F, 0.0F);
//        this.chestplateL3.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 4, 0.0F);
//        this.setRotateAngle(chestplateL3, 0.17453292519943295F, 0.17453292519943295F, 0.03490658503988659F);
//        this.waistL1 = new MowzieModelRenderer(this, 49, 83);
//        this.waistL1.setRotationPoint(1.5F, 0.0F, -0.5F);
//        this.waistL1.addBox(0.0F, 0.0F, -0.7F, 2, 2, 3, 0.0F);
//        this.wheelL1 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelL1.setRotationPoint(0.5F, -1.5F, 0.0F);
//        this.wheelL1.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.lowerlegL4 = new MowzieModelRenderer(this, 89, 88);
//        this.lowerlegL4.mirror = true;
//        this.lowerlegL4.setRotationPoint(0.0F, 0.0F, 3.1F);
//        this.lowerlegL4.addBox(0.0F, -0.3F, -2.0F, 1, 6, 2, 0.0F);
//        this.setRotateAngle(lowerlegL4, 0.3211405823669566F, 0.03490658503988659F, 0.0F);
//        this.headback1 = new MowzieModelRenderer(this, 3, 59);
//        this.headback1.setRotationPoint(0.0F, -2.7F, 1.0F);
//        this.headback1.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
//        this.setRotateAngle(headback1, 0.15707963267948966F, 0.0F, 0.0F);
//        this.lowerlegL10 = new MowzieModelRenderer(this, 96, 90);
//        this.lowerlegL10.mirror = true;
//        this.lowerlegL10.setRotationPoint(-0.2F, 5.0F, 0.0F);
//        this.lowerlegL10.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4, 0.0F);
//        this.setRotateAngle(lowerlegL10, 0.0F, -0.08726646259971647F, 0.0F);
//        this.lowerarmL2 = new MowzieModelRenderer(this, 2, 120);
//        this.lowerarmL2.setRotationPoint(-0.9F, -1.0F, -1.0F);
//        this.lowerarmL2.addBox(-1.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(lowerarmL2, 0.03490658503988659F, 0.03490658503988659F, -0.19198621771937624F);
//        this.turret12 = new MowzieModelRenderer(this, 40, 101);
//        this.turret12.setRotationPoint(1.0F, -0.4F, 2.2F);
//        this.turret12.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.trackL7 = new MowzieModelRenderer(this, 9, 100);
//        this.trackL7.mirror = true;
//        this.trackL7.setRotationPoint(-1.0F, -3.0F, 0.0F);
//        this.trackL7.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackL7, -0.03490658503988659F, -0.017453292519943295F, 0.5235987755982988F);
//        this.bodyparts4 = new MowzieModelRenderer(this, 68, 69);
//        this.bodyparts4.mirror = true;
//        this.bodyparts4.setRotationPoint(-1.2F, -2.0F, -0.2F);
//        this.bodyparts4.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(bodyparts4, -0.15707963267948966F, 0.0F, 0.0F);
//        this.barrelbase2 = new MowzieModelRenderer(this, 35, 90);
//        this.barrelbase2.mirror = true;
//        this.barrelbase2.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.barrelbase2.addBox(-0.8F, 0.5F, -0.8F, 1, 2, 1, 0.0F);
//        this.lowerlegL5 = new MowzieModelRenderer(this, 94, 85);
//        this.lowerlegL5.mirror = true;
//        this.lowerlegL5.setRotationPoint(-1.8F, 0.6F, -1.8F);
//        this.lowerlegL5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(lowerlegL5, 0.40142572795869574F, -0.03490658503988659F, 0.0F);
//        this.trackconnectorL1 = new MowzieModelRenderer(this, 20, 90);
//        this.trackconnectorL1.setRotationPoint(3.0F, -4.0F, 3.0F);
//        this.trackconnectorL1.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(trackconnectorL1, 0.06981317007977318F, -0.7853981633974483F, -0.13962634015954636F);
//        this.lowerlegR1 = new MowzieModelRenderer(this, 87, 60);
//        this.lowerlegR1.setRotationPoint(0.0F, 4.6F, -0.8F);
//        this.lowerlegR1.addBox(-1.0F, -0.3F, 0.0F, 2, 6, 3, 0.0F);
//        this.setRotateAngle(lowerlegR1, 0.17453292519943295F, 0.0F, -0.08726646259971647F);
//        this.waistR1 = new MowzieModelRenderer(this, 49, 83);
//        this.waistR1.mirror = true;
//        this.waistR1.setRotationPoint(-1.5F, 0.0F, -0.5F);
//        this.waistR1.addBox(-2.0F, 0.0F, -0.7F, 2, 2, 3, 0.0F);
//        this.lowerarmR5 = new MowzieModelRenderer(this, 6, 114);
//        this.lowerarmR5.mirror = true;
//        this.lowerarmR5.setRotationPoint(0.9F, -1.0F, 2.0F);
//        this.lowerarmR5.addBox(-2.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(lowerarmR5, 0.17453292519943295F, 0.0F, 0.0F);
//        this.wheelL4 = new MowzieModelRenderer(this, 11, 96);
//        this.wheelL4.setRotationPoint(0.5F, -2.0F, 0.0F);
//        this.wheelL4.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.trackL1 = new MowzieModelRenderer(this, 18, 100);
//        this.trackL1.setRotationPoint(1.0F, -6.0F, -2.1F);
//        this.trackL1.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.crotch1 = new MowzieModelRenderer(this, 60, 83);
//        this.crotch1.setRotationPoint(-1.0F, 1.7F, -1.8F);
//        this.crotch1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(crotch1, 0.13962634015954636F, 0.0F, 0.0F);
//        this.neck1 = new MowzieModelRenderer(this, 3, 48);
//        this.neck1.setRotationPoint(0.0F, -4.0F, 0.0F);
//        this.neck1.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
//        this.trackL2 = new MowzieModelRenderer(this, 9, 100);
//        this.trackL2.setRotationPoint(1.0F, 0.0F, 0.0F);
//        this.trackL2.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.setRotateAngle(trackL2, 0.0F, 0.0F, -0.5235987755982988F);
//        this.lowerlegL13 = new MowzieModelRenderer(this, 98, 77);
//        this.lowerlegL13.mirror = true;
//        this.lowerlegL13.setRotationPoint(2.3F, 1.3F, 0.5F);
//        this.lowerlegL13.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
//        this.lowerlegL11 = new MowzieModelRenderer(this, 69, 82);
//        this.lowerlegL11.mirror = true;
//        this.lowerlegL11.setRotationPoint(0.5F, 2.5F, -0.5F);
//        this.lowerlegL11.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
//        this.barrelbase3 = new MowzieModelRenderer(this, 35, 90);
//        this.barrelbase3.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.barrelbase3.addBox(-0.2F, 0.5F, -0.2F, 1, 2, 1, 0.0F);
//        this.torsobase = new MowzieModelRenderer(this, 61, 50);
//        this.torsobase.setRotationPoint(0.0F, -4.5F, 0.0F);
//        this.torsobase.addBox(-2.5F, -4.0F, -2.0F, 5, 4, 4, 0.0F);
//        this.fistR = new MowzieModelRenderer(this, 0, 115);
//        this.fistR.mirror = true;
//        this.fistR.setRotationPoint(-0.1F, 3.6F, 0.2F);
//        this.fistR.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(fistR, -0.08726646259971647F, 0.0F, -0.20943951023931953F);
//        this.feetR5 = new MowzieModelRenderer(this, 79, 82);
//        this.feetR5.setRotationPoint(1.5F, 1.0F, -4.0F);
//        this.feetR5.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
//        this.barrelbase1 = new MowzieModelRenderer(this, 19, 84);
//        this.barrelbase1.setRotationPoint(0.0F, 0.8F, -0.5F);
//        this.barrelbase1.addBox(-2.0F, -0.5F, -1.0F, 4, 1, 2, 0.0F);
//        this.setRotateAngle(barrelbase1, -0.06981317007977318F, 0.0F, 0.0F);
//        this.barrel2 = new MowzieModelRenderer(this, 33, 94);
//        this.barrel2.setRotationPoint(0.0F, 8.5F, 0.0F);
//        this.barrel2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
//        this.lowerarmR3 = new MowzieModelRenderer(this, 20, 118);
//        this.lowerarmR3.mirror = true;
//        this.lowerarmR3.setRotationPoint(-0.5F, -1.0F, -0.9F);
//        this.lowerarmR3.addBox(-1.0F, -1.2F, 0.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(lowerarmR3, 0.15707963267948966F, 0.0F, 0.0F);
//        this.trackR5 = new MowzieModelRenderer(this, 8, 109);
//        this.trackR5.mirror = true;
//        this.trackR5.setRotationPoint(0.25F, 7.25F, 0.0F);
//        this.trackR5.addBox(-1.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.setRotateAngle(trackR5, -0.03490658503988659F, 0.0F, 0.0F);
//        this.lowerlegR8 = new MowzieModelRenderer(this, 62, 89);
//        this.lowerlegR8.setRotationPoint(0.2F, 2.1F, 1.0F);
//        this.lowerlegR8.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
//        this.trackR6 = new MowzieModelRenderer(this, 18, 100);
//        this.trackR6.setRotationPoint(1.5F, -6.0F, -2.1F);
//        this.trackR6.addBox(-1.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.waistL2 = new MowzieModelRenderer(this, 56, 77);
//        this.waistL2.setRotationPoint(0.0F, 0.0F, -1.0F);
//        this.waistL2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
//        this.setRotateAngle(waistL2, 0.0F, -0.15184364492350666F, 0.0F);
//        this.stomach1 = new MowzieModelRenderer(this, 50, 70);
//        this.stomach1.setRotationPoint(-2.0F, -4.5F, -2.0F);
//        this.stomach1.addBox(0.0F, 0.0F, 0.0F, 4, 5, 2, 0.0F);
//        this.setRotateAngle(stomach1, 0.15707963267948966F, 0.0F, 0.0F);
//        this.feetR3 = new MowzieModelRenderer(this, 90, 81);
//        this.feetR3.setRotationPoint(2.5F, 1.1F, -1.4F);
//        this.feetR3.addBox(-2.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
//        this.setRotateAngle(feetR3, 0.41887902047863906F, 0.0F, -0.017453292519943295F);
//        this.waist = new MowzieModelRenderer(this, 46, 78);
//        this.waist.setRotationPoint(0.0F, 9.65F, 0.0F);
//        this.waist.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
//        this.headtop1 = new MowzieModelRenderer(this, 3, 71);
//        this.headtop1.setRotationPoint(0.0F, -3.5F, 0.0F);
//        this.headtop1.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
//        this.lowerarmL4 = new MowzieModelRenderer(this, 20, 118);
//        this.lowerarmL4.setRotationPoint(0.5F, -1.0F, -0.9F);
//        this.lowerarmL4.addBox(0.0F, -1.2F, 0.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(lowerarmL4, 0.15707963267948966F, 0.0F, 0.0F);
//        this.barrelbase5 = new MowzieModelRenderer(this, 35, 90);
//        this.barrelbase5.mirror = true;
//        this.barrelbase5.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.barrelbase5.addBox(-0.8F, 0.5F, -0.2F, 1, 2, 1, 0.0F);
//        this.chestplateR3 = new MowzieModelRenderer(this, 70, 62);
//        this.chestplateR3.mirror = true;
//        this.chestplateR3.setRotationPoint(-3.0F, -1.9F, 0.0F);
//        this.chestplateR3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4, 0.0F);
//        this.setRotateAngle(chestplateR3, 0.17453292519943295F, -0.17453292519943295F, -0.03490658503988659F);
//        this.shoulderR2 = new MowzieModelRenderer(this, 25, 50);
//        this.shoulderR2.setRotationPoint(-1.5F, -0.5F, -2.5F);
//        this.shoulderR2.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
//        this.shoulderR8 = new MowzieModelRenderer(this, 25, 58);
//        this.shoulderR8.setRotationPoint(-1.0F, -0.5F, -1.7F);
//        this.shoulderR8.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
//        this.headR2 = new MowzieModelRenderer(this, 9, 63);
//        this.headR2.mirror = true;
//        this.headR2.setRotationPoint(0.0F, 0.7F, 0.0F);
//        this.headR2.addBox(-1.0F, 0.0F, -1.0F, 1, 2, 1, 0.0F);
//        this.setRotateAngle(headR2, 0.4625122517784973F, 0.0F, -0.017453292519943295F);
//        this.trackR1 = new MowzieModelRenderer(this, 18, 100);
//        this.trackR1.mirror = true;
//        this.trackR1.setRotationPoint(-1.0F, -6.0F, -2.1F);
//        this.trackR1.addBox(-1.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.upperlegL2 = new MowzieModelRenderer(this, 82, 62);
//        this.upperlegL2.mirror = true;
//        this.upperlegL2.setRotationPoint(-0.5F, 0.0F, 0.9F);
//        this.upperlegL2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
//        this.trackL5 = new MowzieModelRenderer(this, 18, 100);
//        this.trackL5.mirror = true;
//        this.trackL5.setRotationPoint(-1.5F, -6.0F, -2.1F);
//        this.trackL5.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.upperArmR.addChild(this.upperarmR2);
//        this.trackconnectorR4.addChild(this.wheelR2);
//        this.upperArmL.addChild(this.lowerArmL);
//        this.upperLegL.addChild(this.lowerlegL1);
//        this.lowerlegL1.addChild(this.feetbaseL1);
//        this.trackL3.addChild(this.trackL4);
//        this.waistL1.addChild(this.skirtL1);
//        this.skirtL1.addChild(this.skirtL2);
//        this.trackR6.addChild(this.trackR7);
//        this.turretconnector.addChild(this.turretbase);
//        this.feetbaseR1.addChild(this.feetR2);
//        this.crotch1.addChild(this.crotch3);
//        this.shoulderL6.addChild(this.shoulderL7);
//        this.barrelbase1.addChild(this.barrel1);
//        this.shoulderR3.addChild(this.shoulderR4);
//        this.turret8.addChild(this.turret10);
//        this.neck1.addChild(this.headbase);
//        this.lowerlegR6.addChild(this.lowerlegR7);
//        this.headbase.addChild(this.headchin1);
//        this.boxR1.addChild(this.boxR2);
//        this.trackL5.addChild(this.trackL6);
//        this.torsobase.addChild(this.torsobaseL);
//        this.trackconnectorR4.addChild(this.trackconnectorR5);
//        this.torsobase.addChild(this.chestplate1);
//        this.lowerlegR2.addChild(this.lowerlegR11);
//        this.feetL2.addChild(this.feetL3);
//        this.skirtR1.addChild(this.skirtR2);
//        this.torsobase.addChild(this.torsobaseR);
//        this.lowerlegR1.addChild(this.feetbaseR1);
//        this.lowerlegL2.addChild(this.lowerlegL3);
//        this.waist.addChild(this.upperLegR);
//        this.upperArmR.addChild(this.lowerArmR);
//        this.shoulderR1.addChild(this.shoulderR3);
//        this.turret1.addChild(this.turret3);
//        this.waist.addChild(this.upperLegL);
//        this.chestplateR1.addChild(this.chestplateR2);
//        this.trackconnectorR3.addChild(this.trackconnectorR4);
//        this.lowerlegR4.addChild(this.lowerlegR5);
//        this.lowerlegL1.addChild(this.lowerlegL2);
//        this.shoulderL4.addChild(this.shoulderL5);
//        this.trackL2.addChild(this.trackL3);
//        this.waistR1.addChild(this.skirtR1);
//        this.lowerlegL8.addChild(this.lowerlegL9);
//        this.torsobaseL.addChild(this.upperArmL);
//        this.trackconnectorL4.addChild(this.trackcnnectorL5);
//        this.turretbase.addChild(this.turret11);
//        this.lowerArmL.addChild(this.fistL);
//        this.turret8.addChild(this.turret9);
//        this.torsobaseL.addChild(this.shoulderL1);
//        this.trackR3.addChild(this.trackR4);
//        this.upperLegR.addChild(this.upperlegR2);
//        this.shoulderL3.addChild(this.shoulderL4);
//        this.lowerArmL.addChild(this.lowerarmL3);
//        this.trackconnectorR3.addChild(this.wheelR5);
//        this.turret5.addChild(this.turret8);
//        this.lowerlegL2.addChild(this.lowerlegL8);
//        this.waistR3.addChild(this.boxR1);
//        this.boxL1.addChild(this.boxL3);
//        this.feetR3.addChild(this.feetR4);
//        this.torsoconnector.addChild(this.bodyparts2);
//        this.headtop1.addChild(this.headcrest1);
//        this.headbase.addChild(this.headR1);
//        this.waistL3.addChild(this.boxL1);
//        this.trackconnectorR5.addChild(this.wheelR1);
//        this.trackconnectorL3.addChild(this.wheelL5);
//        this.trackconnectorL4.addChild(this.wheelL2);
//        this.waistR1.addChild(this.waistR2);
//        this.torsoconnector.addChild(this.trackbase1);
//        this.torsoconnector.addChild(this.bodyparts3);
//        this.trackconnectorR3.addChild(this.wheelR4);
//        this.torsobase.addChild(this.chestplateR1);
//        this.lowerlegL5.addChild(this.lowerlegL6);
//        this.waist.addChild(this.waistR3);
//        this.headtop1.addChild(this.headcrest4);
//        this.turret1.addChild(this.turret4);
//        this.trackconnectorL3.addChild(this.wheelL3);
//        this.shoulderL1.addChild(this.shoulderL8);
//        this.lowerarmR3.addChild(this.lowerarmR4);
//        this.headtop1.addChild(this.headcrest2);
//        this.trackconnectorR2.addChild(this.trackconnectorR3);
//        this.trackR1.addChild(this.trackR2);
//        this.turret5.addChild(this.turret6);
//        this.lowerArmR.addChild(this.lowerarmR2);
//        this.turret5.addChild(this.turret7);
//        this.lowerlegR11.addChild(this.lowerlegR12);
//        this.waist.addChild(this.waistL3);
//        this.headtop1.addChild(this.headcrest3);
//        this.trackconnectorR1.addChild(this.trackconnectorR2);
//        this.upperArmL.addChild(this.upperarmL2);
//        this.headL1.addChild(this.headL2);
//        this.turret1.addChild(this.turret2);
//        this.barrelbase1.addChild(this.barrelbase4);
//        this.boxL1.addChild(this.boxL2);
//        this.lowerlegL11.addChild(this.lowerlegL12);
//        this.shoulderL3.addChild(this.shoulderL6);
//        this.headbase.addChild(this.headL1);
//        this.lowerarmL4.addChild(this.lowerarmL5);
//        this.lowerlegR2.addChild(this.lowerlegR13);
//        this.lowerlegR1.addChild(this.lowerlegR2);
//        this.trackL5.addChild(this.trackL8);
//        this.torsobaseR.addChild(this.upperArmR);
//        this.trackconnectorL3.addChild(this.trackconnectorL4);
//        this.torsobaseR.addChild(this.shoulderR1);
//        this.lowerlegR5.addChild(this.lowerlegR6);
//        this.turretbase.addChild(this.turret1);
//        this.turretbase.addChild(this.turret5);
//        this.trackconnectorR3.addChild(this.wheelR3);
//        this.feetL3.addChild(this.feetL4);
//        this.trackconnectorL1.addChild(this.trackconnectorL2);
//        this.lowerlegR2.addChild(this.lowerlegR10);
//        this.crotch1.addChild(this.crotch2);
//        this.waist.addChild(this.torsoconnector);
//        this.torsobase.addChild(this.chestplateL1);
//        this.shoulderL1.addChild(this.shoulderL3);
//        this.torsobase.addChild(this.turretconnector);
//        this.torsoconnector.addChild(this.trackconnectorR1);
//        this.trackR2.addChild(this.trackR3);
//        this.trackR7.addChild(this.trackR8);
//        this.lowerlegR8.addChild(this.lowerlegR9);
//        this.chestplateL1.addChild(this.chestplateL2);
//        this.shoulderR4.addChild(this.shoulderR5);
//        this.shoulderR6.addChild(this.shoulderR7);
//        this.lowerlegR3.addChild(this.lowerlegR4);
//        this.boxR1.addChild(this.boxR3);
//        this.shoulderL1.addChild(this.shoulderL2);
//        this.shoulderR3.addChild(this.shoulderR6);
//        this.trackconnectorL2.addChild(this.trackconnectorL3);
//        this.lowerlegR2.addChild(this.lowerlegR3);
//        this.feetbaseL1.addChild(this.feetL5);
//        this.torsoconnector.addChild(this.bodyparts1);
//        this.lowerlegL6.addChild(this.lowerlegL7);
//        this.feetbaseL1.addChild(this.feetL2);
//        this.chestplateL2.addChild(this.chestplateL3);
//        this.waist.addChild(this.waistL1);
//        this.trackcnnectorL5.addChild(this.wheelL1);
//        this.lowerlegL3.addChild(this.lowerlegL4);
//        this.headbase.addChild(this.headback1);
//        this.lowerlegL2.addChild(this.lowerlegL10);
//        this.lowerArmL.addChild(this.lowerarmL2);
//        this.turretbase.addChild(this.turret12);
//        this.trackL6.addChild(this.trackL7);
//        this.torsoconnector.addChild(this.bodyparts4);
//        this.barrelbase1.addChild(this.barrelbase2);
//        this.lowerlegL4.addChild(this.lowerlegL5);
//        this.torsoconnector.addChild(this.trackconnectorL1);
//        this.upperLegR.addChild(this.lowerlegR1);
//        this.waist.addChild(this.waistR1);
//        this.lowerArmR.addChild(this.lowerarmR5);
//        this.trackconnectorL3.addChild(this.wheelL4);
//        this.trackconnectorL3.addChild(this.trackL1);
//        this.waist.addChild(this.crotch1);
//        this.torsobase.addChild(this.neck1);
//        this.trackL1.addChild(this.trackL2);
//        this.lowerlegL2.addChild(this.lowerlegL13);
//        this.lowerlegL2.addChild(this.lowerlegL11);
//        this.barrelbase1.addChild(this.barrelbase3);
//        this.torsoconnector.addChild(this.torsobase);
//        this.lowerArmR.addChild(this.fistR);
//        this.feetbaseR1.addChild(this.feetR5);
//        this.turret4.addChild(this.barrelbase1);
//        this.barrel1.addChild(this.barrel2);
//        this.lowerArmR.addChild(this.lowerarmR3);
//        this.trackR1.addChild(this.trackR5);
//        this.lowerlegR2.addChild(this.lowerlegR8);
//        this.trackconnectorR3.addChild(this.trackR6);
//        this.waistL1.addChild(this.waistL2);
//        this.torsoconnector.addChild(this.stomach1);
//        this.feetR2.addChild(this.feetR3);
//        this.headbase.addChild(this.headtop1);
//        this.lowerArmL.addChild(this.lowerarmL4);
//        this.barrelbase1.addChild(this.barrelbase5);
//        this.chestplateR2.addChild(this.chestplateR3);
//        this.shoulderR1.addChild(this.shoulderR2);
//        this.shoulderR1.addChild(this.shoulderR8);
//        this.headR1.addChild(this.headR2);
//        this.trackconnectorR3.addChild(this.trackR1);
//        this.upperLegL.addChild(this.upperlegL2);
//        this.trackconnectorL3.addChild(this.trackL5);
//
//        this.vehicle60 = new ModelRenderer(this, 9, 100);
//        this.vehicle60.setRotationPoint(1.0F, -3.0F, 0.0F);
//        this.vehicle60.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehicle91 = new ModelRenderer(this, 69, 82);
//        this.vehicle91.mirror = true;
//        this.vehicle91.setRotationPoint(-1.6F, 7.6F, 2.3F);
//        this.vehicle91.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
//        this.setRotateAngle(vehicle91, 1.3089969389957472F, 1.5707963267948966F, 0.0F);
//        this.vehicle3 = new ModelRenderer(this, 24, 111);
//        this.vehicle3.setRotationPoint(2.0F, -2.6F, 12.1F);
//        this.vehicle3.addBox(-1.0F, -1.0F, -1.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(vehicle3, -1.5707963267948966F, 0.0F, 0.0F);
//        this.vehicle66 = new ModelRenderer(this, 11, 96);
//        this.vehicle66.setRotationPoint(0.5F, 0.4F, 0.0F);
//        this.vehicle66.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehiclebase = new ModelRenderer(this, 46, 78);
//        this.vehiclebase.setRotationPoint(0.0F, 21.0F, 0.0F);
//        this.vehiclebase.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3, 0.0F);
//        this.vehicle20 = new ModelRenderer(this, 62, 43);
//        this.vehicle20.mirror = true;
//        this.vehicle20.setRotationPoint(-2.1F, 4.0F, -1.0F);
//        this.vehicle20.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1, 0.0F);
//        this.vehicle44 = new ModelRenderer(this, 29, 90);
//        this.vehicle44.mirror = true;
//        this.vehicle44.setRotationPoint(0.0F, 2.0F, 0.0F);
//        this.vehicle44.addBox(-1.0F, -6.0F, 0.0F, 1, 7, 1, 0.0F);
//        this.setRotateAngle(vehicle44, 0.0F, 1.5707963267948966F, -1.5707963267948966F);
//        this.vehicle102 = new ModelRenderer(this, 60, 78);
//        this.vehicle102.mirror = true;
//        this.vehicle102.setRotationPoint(-2.5F, -2.3F, 8.0F);
//        this.vehicle102.addBox(-3.0F, 0.0F, -2.0F, 3, 2, 2, 0.0F);
//        this.vehicle12 = new ModelRenderer(this, 39, 63);
//        this.vehicle12.setRotationPoint(0.2F, 2.5F, 0.6F);
//        this.vehicle12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
//        this.vehicle56 = new ModelRenderer(this, 8, 109);
//        this.vehicle56.mirror = true;
//        this.vehicle56.setRotationPoint(2.25F, -4.0F, 0.0F);
//        this.vehicle56.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.vehiclebarrelbase5 = new ModelRenderer(this, 35, 90);
//        this.vehiclebarrelbase5.mirror = true;
//        this.vehiclebarrelbase5.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.vehiclebarrelbase5.addBox(-0.8F, 0.5F, -0.2F, 1, 2, 1, 0.0F);
//        this.vehicle5 = new ModelRenderer(this, 20, 118);
//        this.vehicle5.setRotationPoint(1.1F, 0.2F, -1.0F);
//        this.vehicle5.addBox(0.0F, -1.2F, 0.0F, 1, 5, 2, 0.0F);
//        this.vehicle50 = new ModelRenderer(this, 11, 96);
//        this.vehicle50.mirror = true;
//        this.vehicle50.setRotationPoint(-0.5F, 1.3F, 0.0F);
//        this.vehicle50.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle79 = new ModelRenderer(this, 8, 109);
//        this.vehicle79.setRotationPoint(0.25F, 8.0F, 0.0F);
//        this.vehicle79.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.vehicle77 = new ModelRenderer(this, 9, 100);
//        this.vehicle77.mirror = true;
//        this.vehicle77.setRotationPoint(1.0F, 0.0F, 0.0F);
//        this.vehicle77.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehicle96 = new ModelRenderer(this, 49, 83);
//        this.vehicle96.mirror = true;
//        this.vehicle96.setRotationPoint(-1.5F, 0.0F, -0.5F);
//        this.vehicle96.addBox(-1.5F, 0.0F, -0.7F, 2, 2, 3, 0.0F);
//        this.vehicle46 = new ModelRenderer(this, 20, 96);
//        this.vehicle46.mirror = true;
//        this.vehicle46.setRotationPoint(1.0F, -3.0F, 0.0F);
//        this.vehicle46.addBox(-1.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
//        this.vehicle28 = new ModelRenderer(this, 31, 50);
//        this.vehicle28.mirror = true;
//        this.vehicle28.setRotationPoint(0.5F, -0.3F, 2.8F);
//        this.vehicle28.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.vehicleturret7 = new ModelRenderer(this, 42, 94);
//        this.vehicleturret7.setRotationPoint(-1.5F, 0.0F, 0.0F);
//        this.vehicleturret7.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(vehicleturret7, 0.0F, 0.0F, -0.7138396640656808F);
//        this.vehicle92 = new ModelRenderer(this, 85, 88);
//        this.vehicle92.mirror = true;
//        this.vehicle92.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.vehicle92.addBox(0.0F, -1.0F, 0.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle92, -0.5061454830783556F, 0.0F, 0.017453292519943295F);
//        this.vehicleturret8 = new ModelRenderer(this, 27, 99);
//        this.vehicleturret8.setRotationPoint(0.0F, -1.3F, 0.9F);
//        this.vehicleturret8.addBox(-2.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F);
//        this.vehicle64 = new ModelRenderer(this, 24, 96);
//        this.vehicle64.setRotationPoint(1.0F, -6.0F, 0.0F);
//        this.vehicle64.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.vehicle72 = new ModelRenderer(this, 18, 100);
//        this.vehicle72.setRotationPoint(1.0F, -6.0F, -2.1F);
//        this.vehicle72.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.vehicle52 = new ModelRenderer(this, 11, 96);
//        this.vehicle52.mirror = true;
//        this.vehicle52.setRotationPoint(-0.5F, -3.6F, 0.0F);
//        this.vehicle52.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle75 = new ModelRenderer(this, 8, 109);
//        this.vehicle75.setRotationPoint(-2.25F, -4.0F, 0.0F);
//        this.vehicle75.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.vehicle80 = new ModelRenderer(this, 80, 68);
//        this.vehicle80.setRotationPoint(-2.0F, 0.0F, 3.0F);
//        this.vehicle80.addBox(-1.0F, -1.5F, -1.0F, 2, 6, 2, 0.0F);
//        this.setRotateAngle(vehicle80, 1.5707963267948966F, 0.0F, 0.0F);
//        this.vehicle82 = new ModelRenderer(this, 71, 88);
//        this.vehicle82.setRotationPoint(1.2F, -1.0F, -0.3F);
//        this.vehicle82.addBox(-3.0F, -4.0F, 0.0F, 3, 6, 1, 0.0F);
//        this.vehicle1 = new ModelRenderer(this, 50, 50);
//        this.vehicle1.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.vehicle1.addBox(-1.5F, -5.0F, -1.0F, 3, 5, 2, 0.0F);
//        this.setRotateAngle(vehicle1, 1.5707963267948966F, 0.0F, 0.0F);
//        this.vehicle26 = new ModelRenderer(this, 34, 53);
//        this.vehicle26.mirror = true;
//        this.vehicle26.setRotationPoint(-2.0F, 0.5F, 0.0F);
//        this.vehicle26.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
//        this.setRotateAngle(vehicle26, 0.0F, 0.0F, 0.017453292519943295F);
//        this.vehicle19 = new ModelRenderer(this, 20, 118);
//        this.vehicle19.mirror = true;
//        this.vehicle19.setRotationPoint(-1.1F, 0.2F, -1.0F);
//        this.vehicle19.addBox(-1.0F, -1.2F, 0.0F, 1, 5, 2, 0.0F);
//        this.vehicle42 = new ModelRenderer(this, 20, 90);
//        this.vehicle42.mirror = true;
//        this.vehicle42.setRotationPoint(-2.5F, 5.5F, -1.4F);
//        this.vehicle42.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.vehicleturret11 = new ModelRenderer(this, 33, 103);
//        this.vehicleturret11.setRotationPoint(-2.6F, -1.1F, 2.7F);
//        this.vehicleturret11.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
//        this.vehicleturret2 = new ModelRenderer(this, 35, 77);
//        this.vehicleturret2.setRotationPoint(2.3F, 2.5F, 0.0F);
//        this.vehicleturret2.addBox(0.0F, -2.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(vehicleturret2, 0.0F, 0.0F, -1.1990411961201044F);
//        this.vehicle45 = new ModelRenderer(this, 24, 96);
//        this.vehicle45.mirror = true;
//        this.vehicle45.setRotationPoint(-1.0F, -6.0F, 0.0F);
//        this.vehicle45.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.vehicle2 = new ModelRenderer(this, 61, 50);
//        this.vehicle2.setRotationPoint(0.0F, -5.5F, -1.5F);
//        this.vehicle2.addBox(-2.5F, -4.0F, -2.0F, 5, 4, 4, 0.0F);
//        this.setRotateAngle(vehicle2, -1.5707963267948966F, 0.0F, 0.0F);
//        this.vehicle18 = new ModelRenderer(this, 9, 114);
//        this.vehicle18.mirror = true;
//        this.vehicle18.setRotationPoint(0.8F, 4.6F, -0.2F);
//        this.vehicle18.addBox(-1.1F, -1.0F, -1.0F, 2, 5, 3, 0.0F);
//        this.vehicle58 = new ModelRenderer(this, 18, 100);
//        this.vehicle58.setRotationPoint(1.5F, -6.0F, -2.1F);
//        this.vehicle58.addBox(-1.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.vehiclebarrelbase4 = new ModelRenderer(this, 35, 90);
//        this.vehiclebarrelbase4.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.vehiclebarrelbase4.addBox(-0.2F, 0.5F, -0.8F, 1, 2, 1, 0.0F);
//        this.vehicle94 = new ModelRenderer(this, 49, 83);
//        this.vehicle94.setRotationPoint(1.5F, 0.0F, -0.5F);
//        this.vehicle94.addBox(-0.5F, 0.0F, -0.7F, 2, 2, 3, 0.0F);
//        this.vehicle98 = new ModelRenderer(this, 60, 78);
//        this.vehicle98.setRotationPoint(2.5F, -2.3F, 8.0F);
//        this.vehicle98.addBox(0.0F, 0.0F, -2.0F, 3, 2, 2, 0.0F);
//        this.vehicle99 = new ModelRenderer(this, 2, 86);
//        this.vehicle99.setRotationPoint(0.0F, -0.1F, -0.2F);
//        this.vehicle99.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
//        this.vehiclebarrelbase2 = new ModelRenderer(this, 35, 90);
//        this.vehiclebarrelbase2.mirror = true;
//        this.vehiclebarrelbase2.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.vehiclebarrelbase2.addBox(-0.8F, 0.5F, -0.8F, 1, 2, 1, 0.0F);
//        this.vehicleturret4 = new ModelRenderer(this, 32, 83);
//        this.vehicleturret4.setRotationPoint(0.0F, 2.0F, 2.0F);
//        this.vehicleturret4.addBox(-2.5F, 0.0F, -2.0F, 5, 1, 3, 0.0F);
//        this.vehicle11 = new ModelRenderer(this, 34, 53);
//        this.vehicle11.setRotationPoint(2.0F, 0.5F, 0.0F);
//        this.vehicle11.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
//        this.setRotateAngle(vehicle11, 0.0F, 0.0F, -0.017453292519943295F);
//        this.vehicle89 = new ModelRenderer(this, 71, 88);
//        this.vehicle89.mirror = true;
//        this.vehicle89.setRotationPoint(-1.2F, -1.0F, -0.3F);
//        this.vehicle89.addBox(0.0F, -4.0F, 0.0F, 3, 6, 1, 0.0F);
//        this.vehicle10 = new ModelRenderer(this, 25, 63);
//        this.vehicle10.setRotationPoint(-0.2F, -1.0F, -2.0F);
//        this.vehicle10.addBox(-1.0F, 0.0F, 0.0F, 4, 1, 5, 0.0F);
//        this.vehicle38 = new ModelRenderer(this, 71, 58);
//        this.vehicle38.mirror = true;
//        this.vehicle38.setRotationPoint(0.0F, 0.1F, 0.5F);
//        this.vehicle38.addBox(-3.0F, -2.0F, 0.0F, 3, 2, 1, 0.0F);
//        this.setRotateAngle(vehicle38, -0.4363323129985824F, 0.0F, 0.0F);
//        this.vehicle23 = new ModelRenderer(this, 25, 50);
//        this.vehicle23.mirror = true;
//        this.vehicle23.setRotationPoint(-0.5F, -2.8F, 10.5F);
//        this.vehicle23.addBox(-1.0F, -0.5F, -2.5F, 1, 3, 4, 0.0F);
//        this.vehicle108 = new ModelRenderer(this, 21, 35);
//        this.vehicle108.setRotationPoint(0.0F, -5.5F, 8.6F);
//        this.vehicle108.addBox(0.0F, 0.0F, 0.0F, 5, 2, 2, 0.0F);
//        this.vehicleturret12 = new ModelRenderer(this, 40, 101);
//        this.vehicleturret12.setRotationPoint(1.0F, -0.4F, 2.2F);
//        this.vehicleturret12.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
//        this.vehicle107 = new ModelRenderer(this, 0, 36);
//        this.vehicle107.setRotationPoint(0.0F, 1.0F, 7.0F);
//        this.vehicle107.addBox(0.0F, -1.0F, 0.0F, 5, 1, 9, 0.0F);
//        this.setRotateAngle(vehicle107, 0.03490658503988659F, 0.0F, 0.0F);
//        this.vehicle62 = new ModelRenderer(this, 20, 93);
//        this.vehicle62.setRotationPoint(-1.0F, 0.1F, -0.1F);
//        this.vehicle62.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle62, 0.0F, 0.0F, -1.5707963267948966F);
//        this.vehicle34 = new ModelRenderer(this, 71, 58);
//        this.vehicle34.setRotationPoint(0.0F, 0.1F, 0.5F);
//        this.vehicle34.addBox(0.0F, -2.0F, 0.0F, 3, 2, 1, 0.0F);
//        this.setRotateAngle(vehicle34, -0.4363323129985824F, 0.0F, 0.0F);
//        this.vehicle31 = new ModelRenderer(this, 21, 70);
//        this.vehicle31.setRotationPoint(0.0F, -3.8F, 2.5F);
//        this.vehicle31.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 1, 0.0F);
//        this.setRotateAngle(vehicle31, 1.5707963267948966F, 0.0F, 0.0F);
//        this.vehicle29 = new ModelRenderer(this, 24, 64);
//        this.vehicle29.mirror = true;
//        this.vehicle29.setRotationPoint(-0.5F, 0.0F, 0.0F);
//        this.vehicle29.addBox(-2.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle29, 0.3490658503988659F, 0.0F, 0.0F);
//        this.vehicle9 = new ModelRenderer(this, 25, 50);
//        this.vehicle9.mirror = true;
//        this.vehicle9.setRotationPoint(1.5F, -0.5F, -2.5F);
//        this.vehicle9.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
//        this.vehicle41 = new ModelRenderer(this, 54, 65);
//        this.vehicle41.setRotationPoint(-1.0F, -2.3F, -3.0F);
//        this.vehicle41.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
//        this.setRotateAngle(vehicle41, 0.3141592653589793F, 0.0F, 0.0F);
//        this.vehicle43 = new ModelRenderer(this, 20, 93);
//        this.vehicle43.mirror = true;
//        this.vehicle43.setRotationPoint(1.0F, 0.1F, -0.1F);
//        this.vehicle43.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle43, 0.0F, 0.0F, 1.5707963267948966F);
//        this.vehicle81 = new ModelRenderer(this, 87, 60);
//        this.vehicle81.setRotationPoint(-0.5F, -1.0F, 1.5F);
//        this.vehicle81.addBox(-1.0F, -0.3F, 0.0F, 2, 6, 3, 0.0F);
//        this.setRotateAngle(vehicle81, 0.0F, 1.5707963267948966F, 0.0F);
//        this.vehicle70 = new ModelRenderer(this, 11, 96);
//        this.vehicle70.setRotationPoint(0.5F, -1.2F, 0.0F);
//        this.vehicle70.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle101 = new ModelRenderer(this, 10, 86);
//        this.vehicle101.mirror = true;
//        this.vehicle101.setRotationPoint(1.0F, -0.2F, 1.2F);
//        this.vehicle101.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle101, 0.06806784082777885F, 0.0F, 0.0F);
//        this.vehiclebarrelbase3 = new ModelRenderer(this, 35, 90);
//        this.vehiclebarrelbase3.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.vehiclebarrelbase3.addBox(-0.2F, 0.5F, -0.2F, 1, 2, 1, 0.0F);
//        this.vehicleturretbase_rotatehere = new ModelRenderer(this, 16, 77);
//        this.vehicleturretbase_rotatehere.setRotationPoint(0.0F, 3.0F, 0.6F);
//        this.vehicleturretbase_rotatehere.addBox(-3.0F, -2.0F, 0.0F, 6, 3, 3, 0.0F);
//        this.setRotateAngle(vehicleturretbase_rotatehere, 0.0F, 0.0F, -3.141592653589793F);
//        this.vehicleturret6 = new ModelRenderer(this, 42, 94);
//        this.vehicleturret6.setRotationPoint(1.5F, 0.0F, 0.0F);
//        this.vehicleturret6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(vehicleturret6, 0.0F, 0.0F, 0.7138396640656808F);
//        this.vehicleturret5 = new ModelRenderer(this, 45, 88);
//        this.vehicleturret5.setRotationPoint(0.0F, -3.3F, 0.0F);
//        this.vehicleturret5.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 3, 0.0F);
//        this.vehicle2_1 = new ModelRenderer(this, 52, 58);
//        this.vehicle2_1.setRotationPoint(2.5F, -1.0F, 0.0F);
//        this.vehicle2_1.addBox(0.0F, -3.0F, 0.5F, 1, 3, 3, 0.0F);
//        this.vehicleturret1 = new ModelRenderer(this, 32, 71);
//        this.vehicleturret1.setRotationPoint(0.0F, 0.3F, 0.0F);
//        this.vehicleturret1.addBox(-2.0F, 0.4F, 0.0F, 4, 2, 3, 0.0F);
//        this.vehicle109 = new ModelRenderer(this, 3, 38);
//        this.vehicle109.setRotationPoint(2.0F, 1.3F, 0.9F);
//        this.vehicle109.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
//        this.setRotateAngle(vehicle109, -0.3839724354387525F, 0.0F, 0.0F);
//        this.vehicle51 = new ModelRenderer(this, 11, 96);
//        this.vehicle51.mirror = true;
//        this.vehicle51.setRotationPoint(0.5F, -1.2F, 0.0F);
//        this.vehicle51.addBox(-2.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle61 = new ModelRenderer(this, 20, 90);
//        this.vehicle61.setRotationPoint(2.5F, 5.5F, -1.4F);
//        this.vehicle61.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
//        this.vehicle100 = new ModelRenderer(this, 2, 82);
//        this.vehicle100.setRotationPoint(0.0F, -0.9F, 0.2F);
//        this.vehicle100.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.setRotateAngle(vehicle100, -0.32637657012293964F, 0.0F, 0.0F);
//        this.vehicle30 = new ModelRenderer(this, 25, 58);
//        this.vehicle30.setRotationPoint(-1.0F, -0.5F, -1.7F);
//        this.vehicle30.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
//        this.vehicle14 = new ModelRenderer(this, 24, 64);
//        this.vehicle14.setRotationPoint(0.5F, 0.0F, 0.0F);
//        this.vehicle14.addBox(0.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle14, 0.3490658503988659F, 0.0F, 0.0F);
//        this.vehicle95 = new ModelRenderer(this, 80, 50);
//        this.vehicle95.setRotationPoint(3.3F, -2.8F, 3.5F);
//        this.vehicle95.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(vehicle95, -1.5707963267948966F, 0.0F, -0.017453292519943295F);
//        this.vehicle105 = new ModelRenderer(this, 10, 86);
//        this.vehicle105.setRotationPoint(-1.0F, -0.2F, 1.2F);
//        this.vehicle105.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle105, 0.06806784082777885F, 0.0F, 0.0F);
//        this.vehicle27 = new ModelRenderer(this, 39, 63);
//        this.vehicle27.mirror = true;
//        this.vehicle27.setRotationPoint(-0.2F, 2.5F, 0.6F);
//        this.vehicle27.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
//        this.vehicle36 = new ModelRenderer(this, 71, 41);
//        this.vehicle36.setRotationPoint(3.0F, 0.0F, 0.0F);
//        this.vehicle36.addBox(0.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
//        this.vehicle71 = new ModelRenderer(this, 11, 96);
//        this.vehicle71.setRotationPoint(0.5F, -3.6F, 0.0F);
//        this.vehicle71.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle17 = new ModelRenderer(this, 24, 111);
//        this.vehicle17.mirror = true;
//        this.vehicle17.setRotationPoint(-2.0F, -2.6F, 12.1F);
//        this.vehicle17.addBox(0.0F, -1.0F, -1.0F, 1, 5, 2, 0.0F);
//        this.setRotateAngle(vehicle17, -1.5707963267948966F, 0.0F, 0.0F);
//        this.vehicle32 = new ModelRenderer(this, 47, 43);
//        this.vehicle32.setRotationPoint(-3.0F, 1.0F, 0.0F);
//        this.vehicle32.addBox(0.0F, -5.0F, 0.0F, 6, 4, 1, 0.0F);
//        this.setRotateAngle(vehicle32, 0.003490658503988659F, 0.0F, 0.0F);
//        this.vehicle57 = new ModelRenderer(this, 8, 109);
//        this.vehicle57.mirror = true;
//        this.vehicle57.setRotationPoint(0.25F, 8.0F, 0.0F);
//        this.vehicle57.addBox(-1.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
//        this.vehicle59 = new ModelRenderer(this, 9, 100);
//        this.vehicle59.setRotationPoint(-1.0F, 0.0F, 0.0F);
//        this.vehicle59.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehicle93 = new ModelRenderer(this, 98, 77);
//        this.vehicle93.mirror = true;
//        this.vehicle93.setRotationPoint(2.3F, 1.3F, 1.5F);
//        this.vehicle93.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
//        this.vehicle40 = new ModelRenderer(this, 71, 41);
//        this.vehicle40.mirror = true;
//        this.vehicle40.setRotationPoint(-3.0F, 0.0F, 0.0F);
//        this.vehicle40.addBox(-3.0F, 0.0F, -1.0F, 3, 1, 3, 0.0F);
//        this.vehicle88 = new ModelRenderer(this, 87, 60);
//        this.vehicle88.mirror = true;
//        this.vehicle88.setRotationPoint(0.5F, -1.0F, 1.5F);
//        this.vehicle88.addBox(-1.0F, -0.3F, 0.0F, 2, 6, 3, 0.0F);
//        this.setRotateAngle(vehicle88, 0.0F, -1.5707963267948966F, 0.0F);
//        this.vehiclebarrel1 = new ModelRenderer(this, 40, 87);
//        this.vehiclebarrel1.setRotationPoint(0.0F, 1.6F, 0.0F);
//        this.vehiclebarrel1.addBox(-0.5F, 0.0F, -0.5F, 1, 8, 1, 0.0F);
//        this.vehicle6 = new ModelRenderer(this, 62, 43);
//        this.vehicle6.setRotationPoint(2.1F, 4.0F, -1.0F);
//        this.vehicle6.addBox(-3.0F, 0.0F, 0.0F, 3, 5, 1, 0.0F);
//        this.vehicle90 = new ModelRenderer(this, 80, 88);
//        this.vehicle90.mirror = true;
//        this.vehicle90.setRotationPoint(2.0F, 1.0F, 0.5F);
//        this.vehicle90.addBox(0.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(vehicle90, 0.0F, -0.03490658503988659F, 0.0F);
//        this.vehicleturret9 = new ModelRenderer(this, 27, 104);
//        this.vehicleturret9.setRotationPoint(2.0F, 0.0F, 0.0F);
//        this.vehicleturret9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(vehicleturret9, 0.0F, -0.017453292519943295F, 0.2617993877991494F);
//        this.vehicle74 = new ModelRenderer(this, 9, 100);
//        this.vehicle74.setRotationPoint(-1.0F, -3.0F, 0.0F);
//        this.vehicle74.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehicle104 = new ModelRenderer(this, 2, 82);
//        this.vehicle104.setRotationPoint(0.0F, -0.9F, 0.2F);
//        this.vehicle104.addBox(-3.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.setRotateAngle(vehicle104, -0.32637657012293964F, 0.0F, 0.0F);
//        this.vehicle103 = new ModelRenderer(this, 2, 86);
//        this.vehicle103.mirror = true;
//        this.vehicle103.setRotationPoint(0.0F, -0.1F, -0.2F);
//        this.vehicle103.addBox(-3.0F, 0.0F, 0.0F, 3, 3, 2, 0.0F);
//        this.vehicle47 = new ModelRenderer(this, 11, 96);
//        this.vehicle47.mirror = true;
//        this.vehicle47.setRotationPoint(-0.5F, 0.4F, 0.0F);
//        this.vehicle47.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle54 = new ModelRenderer(this, 9, 100);
//        this.vehicle54.mirror = true;
//        this.vehicle54.setRotationPoint(-1.0F, 0.0F, 0.0F);
//        this.vehicle54.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehicle48 = new ModelRenderer(this, 11, 96);
//        this.vehicle48.mirror = true;
//        this.vehicle48.setRotationPoint(-0.5F, -2.2F, 0.0F);
//        this.vehicle48.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicleturret10 = new ModelRenderer(this, 27, 104);
//        this.vehicleturret10.setRotationPoint(-2.0F, 0.0F, 0.0F);
//        this.vehicleturret10.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
//        this.setRotateAngle(vehicleturret10, 0.0F, 0.017453292519943295F, -0.2617993877991494F);
//        this.vehicle39 = new ModelRenderer(this, 71, 46);
//        this.vehicle39.mirror = true;
//        this.vehicle39.setRotationPoint(-3.0F, 0.0F, 0.0F);
//        this.vehicle39.addBox(-3.0F, -2.0F, 0.0F, 3, 2, 1, 0.0F);
//        this.setRotateAngle(vehicle39, 0.0F, 0.017453292519943295F, 0.0F);
//        this.vehicle13 = new ModelRenderer(this, 31, 50);
//        this.vehicle13.setRotationPoint(-0.5F, -0.3F, 2.8F);
//        this.vehicle13.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
//        this.vehicle84 = new ModelRenderer(this, 69, 82);
//        this.vehicle84.setRotationPoint(1.6F, 7.6F, 2.3F);
//        this.vehicle84.addBox(-2.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
//        this.setRotateAngle(vehicle84, 1.3089969389957472F, -1.5707963267948966F, 0.0F);
//        this.vehicle65 = new ModelRenderer(this, 20, 96);
//        this.vehicle65.setRotationPoint(-1.0F, -3.0F, 0.0F);
//        this.vehicle65.addBox(0.0F, -2.0F, 0.0F, 1, 2, 1, 0.0F);
//        this.vehicle53 = new ModelRenderer(this, 18, 100);
//        this.vehicle53.mirror = true;
//        this.vehicle53.setRotationPoint(-1.0F, -6.0F, -2.1F);
//        this.vehicle53.addBox(-1.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.vehicle83 = new ModelRenderer(this, 80, 88);
//        this.vehicle83.setRotationPoint(-2.0F, 1.0F, 0.5F);
//        this.vehicle83.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(vehicle83, 0.0F, 0.03490658503988659F, 0.0F);
//        this.vehicle86 = new ModelRenderer(this, 98, 77);
//        this.vehicle86.setRotationPoint(-2.3F, 1.3F, 1.5F);
//        this.vehicle86.addBox(-1.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
//        this.vehicle76 = new ModelRenderer(this, 18, 100);
//        this.vehicle76.mirror = true;
//        this.vehicle76.setRotationPoint(-1.5F, -6.0F, -2.1F);
//        this.vehicle76.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3, 0.0F);
//        this.vehicle55 = new ModelRenderer(this, 9, 100);
//        this.vehicle55.mirror = true;
//        this.vehicle55.setRotationPoint(1.0F, -3.0F, 0.0F);
//        this.vehicle55.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehiclebarrel2 = new ModelRenderer(this, 33, 94);
//        this.vehiclebarrel2.setRotationPoint(0.0F, 8.5F, 0.0F);
//        this.vehiclebarrel2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
//        this.vehicle87 = new ModelRenderer(this, 80, 68);
//        this.vehicle87.mirror = true;
//        this.vehicle87.setRotationPoint(2.0F, 0.0F, 3.0F);
//        this.vehicle87.addBox(-1.0F, -1.5F, -1.0F, 2, 6, 2, 0.0F);
//        this.setRotateAngle(vehicle87, 1.5707963267948966F, 0.0F, 0.0F);
//        this.vehicle67 = new ModelRenderer(this, 11, 96);
//        this.vehicle67.setRotationPoint(0.5F, -2.2F, 0.0F);
//        this.vehicle67.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle63 = new ModelRenderer(this, 29, 90);
//        this.vehicle63.setRotationPoint(0.0F, 2.0F, 0.0F);
//        this.vehicle63.addBox(0.0F, -6.0F, 0.0F, 1, 7, 1, 0.0F);
//        this.setRotateAngle(vehicle63, 0.0F, -1.5707963267948966F, 1.5707963267948966F);
//        this.vehicle7 = new ModelRenderer(this, 9, 29);
//        this.vehicle7.setRotationPoint(0.0F, 1.0F, 0.5F);
//        this.vehicle7.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 2, 0.0F);
//        this.setRotateAngle(vehicle7, 0.0F, 0.017453292519943295F, 0.0F);
//        this.vehicle22 = new ModelRenderer(this, 9, 29);
//        this.vehicle22.mirror = true;
//        this.vehicle22.setRotationPoint(0.0F, 1.0F, 0.5F);
//        this.vehicle22.addBox(0.0F, 0.0F, 0.0F, 3, 4, 2, 0.0F);
//        this.setRotateAngle(vehicle22, 0.0F, -0.017453292519943295F, 0.0F);
//        this.vehicle73 = new ModelRenderer(this, 9, 100);
//        this.vehicle73.setRotationPoint(1.0F, 0.0F, 0.0F);
//        this.vehicle73.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehicle106 = new ModelRenderer(this, 22, 40);
//        this.vehicle106.setRotationPoint(-2.5F, 0.8F, -8.1F);
//        this.vehicle106.addBox(0.0F, 0.0F, 0.0F, 5, 1, 7, 0.0F);
//        this.setRotateAngle(vehicle106, -0.03490658503988659F, 0.0F, 0.0F);
//        this.vehicleturret3 = new ModelRenderer(this, 35, 77);
//        this.vehicleturret3.setRotationPoint(-2.3F, 2.5F, 0.0F);
//        this.vehicleturret3.addBox(-2.0F, -2.0F, 0.0F, 2, 2, 3, 0.0F);
//        this.setRotateAngle(vehicleturret3, 0.0F, 0.0F, 1.1990411961201044F);
//        this.vehicle15 = new ModelRenderer(this, 25, 58);
//        this.vehicle15.setRotationPoint(1.0F, -0.5F, -1.7F);
//        this.vehicle15.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
//        this.vehicle16 = new ModelRenderer(this, 52, 58);
//        this.vehicle16.mirror = true;
//        this.vehicle16.setRotationPoint(-2.5F, -1.0F, 0.0F);
//        this.vehicle16.addBox(-1.0F, -3.0F, 0.5F, 1, 3, 3, 0.0F);
//        this.vehicle35 = new ModelRenderer(this, 71, 46);
//        this.vehicle35.setRotationPoint(3.0F, 0.0F, 0.0F);
//        this.vehicle35.addBox(0.0F, -2.0F, 0.0F, 3, 2, 1, 0.0F);
//        this.setRotateAngle(vehicle35, 0.0F, -0.017453292519943295F, 0.0F);
//        this.vehicle78 = new ModelRenderer(this, 9, 100);
//        this.vehicle78.mirror = true;
//        this.vehicle78.setRotationPoint(-1.0F, -3.0F, 0.0F);
//        this.vehicle78.addBox(0.0F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
//        this.vehicle69 = new ModelRenderer(this, 11, 96);
//        this.vehicle69.setRotationPoint(0.5F, 1.3F, 0.0F);
//        this.vehicle69.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle25 = new ModelRenderer(this, 25, 63);
//        this.vehicle25.mirror = true;
//        this.vehicle25.setRotationPoint(0.2F, -1.0F, -2.0F);
//        this.vehicle25.addBox(-3.0F, 0.0F, 0.0F, 4, 1, 5, 0.0F);
//        this.vehicle24 = new ModelRenderer(this, 25, 50);
//        this.vehicle24.setRotationPoint(-1.5F, -0.5F, -2.5F);
//        this.vehicle24.addBox(-1.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
//        this.vehicle85 = new ModelRenderer(this, 85, 88);
//        this.vehicle85.setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.vehicle85.addBox(-2.0F, -1.0F, 0.0F, 2, 1, 1, 0.0F);
//        this.setRotateAngle(vehicle85, -0.5061454830783556F, 0.0F, -0.017453292519943295F);
//        this.vehicle8 = new ModelRenderer(this, 25, 50);
//        this.vehicle8.setRotationPoint(0.5F, -2.8F, 10.5F);
//        this.vehicle8.addBox(0.0F, -0.5F, -2.5F, 1, 3, 4, 0.0F);
//        this.vehicle4 = new ModelRenderer(this, 9, 114);
//        this.vehicle4.setRotationPoint(-0.8F, 4.6F, -0.2F);
//        this.vehicle4.addBox(-0.9F, -1.0F, -1.0F, 2, 5, 3, 0.0F);
//        this.vehicle33 = new ModelRenderer(this, 61, 59);
//        this.vehicle33.setRotationPoint(-0.2F, -2.8F, -2.7F);
//        this.vehicle33.addBox(0.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
//        this.setRotateAngle(vehicle33, 0.3141592653589793F, 0.0F, 0.0F);
//        this.vehicle49 = new ModelRenderer(this, 11, 96);
//        this.vehicle49.mirror = true;
//        this.vehicle49.setRotationPoint(0.5F, -0.1F, 0.0F);
//        this.vehicle49.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehicle97 = new ModelRenderer(this, 80, 50);
//        this.vehicle97.mirror = true;
//        this.vehicle97.setRotationPoint(-3.3F, -2.8F, 3.5F);
//        this.vehicle97.addBox(-1.0F, 0.0F, 0.0F, 1, 6, 3, 0.0F);
//        this.setRotateAngle(vehicle97, -1.5707963267948966F, 0.0F, 0.017453292519943295F);
//        this.vehicle68 = new ModelRenderer(this, 11, 96);
//        this.vehicle68.setRotationPoint(-0.5F, -0.1F, 0.0F);
//        this.vehicle68.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
//        this.vehiclebarrelbase1_rotatehere = new ModelRenderer(this, 19, 84);
//        this.vehiclebarrelbase1_rotatehere.setRotationPoint(0.0F, 0.8F, -0.5F);
//        this.vehiclebarrelbase1_rotatehere.addBox(-2.0F, -0.5F, -1.0F, 4, 1, 2, 0.0F);
//        this.vehicle37 = new ModelRenderer(this, 61, 59);
//        this.vehicle37.mirror = true;
//        this.vehicle37.setRotationPoint(0.2F, -2.8F, -2.7F);
//        this.vehicle37.addBox(-3.0F, 0.0F, -1.0F, 3, 3, 3, 0.0F);
//        this.setRotateAngle(vehicle37, 0.3141592653589793F, 0.0F, 0.0F);
//        this.vehicle59.addChild(this.vehicle60);
//        this.vehicle89.addChild(this.vehicle91);
//        this.vehicle2_1.addChild(this.vehicle3);
//        this.vehicle65.addChild(this.vehicle66);
//        this.vehicle18.addChild(this.vehicle20);
//        this.vehicle43.addChild(this.vehicle44);
//        this.vehiclebase.addChild(this.vehicle102);
//        this.vehicle11.addChild(this.vehicle12);
//        this.vehicle55.addChild(this.vehicle56);
//        this.vehiclebarrelbase1_rotatehere.addChild(this.vehiclebarrelbase5);
//        this.vehicle4.addChild(this.vehicle5);
//        this.vehicle44.addChild(this.vehicle50);
//        this.vehicle76.addChild(this.vehicle79);
//        this.vehicle76.addChild(this.vehicle77);
//        this.vehiclebase.addChild(this.vehicle96);
//        this.vehicle45.addChild(this.vehicle46);
//        this.vehicle25.addChild(this.vehicle28);
//        this.vehicleturret5.addChild(this.vehicleturret7);
//        this.vehicle91.addChild(this.vehicle92);
//        this.vehicleturret5.addChild(this.vehicleturret8);
//        this.vehicle63.addChild(this.vehicle64);
//        this.vehicle63.addChild(this.vehicle72);
//        this.vehicle44.addChild(this.vehicle52);
//        this.vehicle74.addChild(this.vehicle75);
//        this.vehiclebase.addChild(this.vehicle80);
//        this.vehicle81.addChild(this.vehicle82);
//        this.vehiclebase.addChild(this.vehicle1);
//        this.vehicle25.addChild(this.vehicle26);
//        this.vehicle18.addChild(this.vehicle19);
//        this.vehicle1.addChild(this.vehicle42);
//        this.vehicleturretbase_rotatehere.addChild(this.vehicleturret11);
//        this.vehicleturret1.addChild(this.vehicleturret2);
//        this.vehicle44.addChild(this.vehicle45);
//        this.vehicle1.addChild(this.vehicle2);
//        this.vehicle17.addChild(this.vehicle18);
//        this.vehicle44.addChild(this.vehicle58);
//        this.vehiclebarrelbase1_rotatehere.addChild(this.vehiclebarrelbase4);
//        this.vehiclebase.addChild(this.vehicle94);
//        this.vehiclebase.addChild(this.vehicle98);
//        this.vehicle98.addChild(this.vehicle99);
//        this.vehiclebarrelbase1_rotatehere.addChild(this.vehiclebarrelbase2);
//        this.vehicleturret1.addChild(this.vehicleturret4);
//        this.vehicle10.addChild(this.vehicle11);
//        this.vehicle88.addChild(this.vehicle89);
//        this.vehicle8.addChild(this.vehicle10);
//        this.vehicle37.addChild(this.vehicle38);
//        this.vehicle16.addChild(this.vehicle23);
//        this.vehicle107.addChild(this.vehicle108);
//        this.vehicleturretbase_rotatehere.addChild(this.vehicleturret12);
//        this.vehicle106.addChild(this.vehicle107);
//        this.vehicle61.addChild(this.vehicle62);
//        this.vehicle33.addChild(this.vehicle34);
//        this.vehicle2.addChild(this.vehicle31);
//        this.vehicle28.addChild(this.vehicle29);
//        this.vehicle8.addChild(this.vehicle9);
//        this.vehicle2.addChild(this.vehicle41);
//        this.vehicle42.addChild(this.vehicle43);
//        this.vehicle80.addChild(this.vehicle81);
//        this.vehicle63.addChild(this.vehicle70);
//        this.vehicle99.addChild(this.vehicle101);
//        this.vehiclebarrelbase1_rotatehere.addChild(this.vehiclebarrelbase3);
//        this.vehicle31.addChild(this.vehicleturretbase_rotatehere);
//        this.vehicleturret5.addChild(this.vehicleturret6);
//        this.vehicleturretbase_rotatehere.addChild(this.vehicleturret5);
//        this.vehicle2.addChild(this.vehicle2_1);
//        this.vehicleturretbase_rotatehere.addChild(this.vehicleturret1);
//        this.vehicle108.addChild(this.vehicle109);
//        this.vehicle44.addChild(this.vehicle51);
//        this.vehicle1.addChild(this.vehicle61);
//        this.vehicle99.addChild(this.vehicle100);
//        this.vehicle23.addChild(this.vehicle30);
//        this.vehicle13.addChild(this.vehicle14);
//        this.vehicle94.addChild(this.vehicle95);
//        this.vehicle103.addChild(this.vehicle105);
//        this.vehicle26.addChild(this.vehicle27);
//        this.vehicle33.addChild(this.vehicle36);
//        this.vehicle63.addChild(this.vehicle71);
//        this.vehicle16.addChild(this.vehicle17);
//        this.vehicle31.addChild(this.vehicle32);
//        this.vehicle53.addChild(this.vehicle57);
//        this.vehicle58.addChild(this.vehicle59);
//        this.vehicle89.addChild(this.vehicle93);
//        this.vehicle37.addChild(this.vehicle40);
//        this.vehicle87.addChild(this.vehicle88);
//        this.vehiclebarrelbase1_rotatehere.addChild(this.vehiclebarrel1);
//        this.vehicle4.addChild(this.vehicle6);
//        this.vehicle89.addChild(this.vehicle90);
//        this.vehicleturret8.addChild(this.vehicleturret9);
//        this.vehicle73.addChild(this.vehicle74);
//        this.vehicle103.addChild(this.vehicle104);
//        this.vehicle102.addChild(this.vehicle103);
//        this.vehicle46.addChild(this.vehicle47);
//        this.vehicle53.addChild(this.vehicle54);
//        this.vehicle46.addChild(this.vehicle48);
//        this.vehicleturret8.addChild(this.vehicleturret10);
//        this.vehicle38.addChild(this.vehicle39);
//        this.vehicle10.addChild(this.vehicle13);
//        this.vehicle82.addChild(this.vehicle84);
//        this.vehicle64.addChild(this.vehicle65);
//        this.vehicle44.addChild(this.vehicle53);
//        this.vehicle82.addChild(this.vehicle83);
//        this.vehicle82.addChild(this.vehicle86);
//        this.vehicle63.addChild(this.vehicle76);
//        this.vehicle54.addChild(this.vehicle55);
//        this.vehiclebarrel1.addChild(this.vehiclebarrel2);
//        this.vehiclebase.addChild(this.vehicle87);
//        this.vehicle65.addChild(this.vehicle67);
//        this.vehicle62.addChild(this.vehicle63);
//        this.vehicle6.addChild(this.vehicle7);
//        this.vehicle20.addChild(this.vehicle22);
//        this.vehicle72.addChild(this.vehicle73);
//        this.vehiclebase.addChild(this.vehicle106);
//        this.vehicleturret1.addChild(this.vehicleturret3);
//        this.vehicle8.addChild(this.vehicle15);
//        this.vehicle2.addChild(this.vehicle16);
//        this.vehicle34.addChild(this.vehicle35);
//        this.vehicle77.addChild(this.vehicle78);
//        this.vehicle63.addChild(this.vehicle69);
//        this.vehicle23.addChild(this.vehicle25);
//        this.vehicle23.addChild(this.vehicle24);
//        this.vehicle84.addChild(this.vehicle85);
//        this.vehicle2_1.addChild(this.vehicle8);
//        this.vehicle3.addChild(this.vehicle4);
//        this.vehicle2.addChild(this.vehicle33);
//        this.vehicle45.addChild(this.vehicle49);
//        this.vehicle96.addChild(this.vehicle97);
//        this.vehicle64.addChild(this.vehicle68);
//        this.vehicleturret4.addChild(this.vehiclebarrelbase1_rotatehere);
//        this.vehicle2.addChild(this.vehicle37);
//
//        setInitPose();
//    }
//
//    @Override
//    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
//    {
//        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
//
//        if (entity instanceof EntityTransformer)
//        {
//            EntityTransformer transformer = (EntityTransformer) entity;
//
//            if (transformer.getTransformationTimer() == 0)
//            {
//                vehiclebase.render(f5);
//            }
//            else
//            {
//                waist.render(f5);
//            }
//        }
//    }
//
//    @Override
//    public Transformer getTransformer()
//    {
//        return TransformerManager.PURGE;
//    }
//
//    @Override
//    public ModelRenderer getWaist()
//    {
//        return waist;
//    }
//
//    public ModelRenderer getVehicle()
//    {
//        return vehiclebase;
//    }
//
//    @Override
//    public ModelRenderer getRightLeg()
//    {
//        return upperLegR;
//    }
//
//    @Override
//    public ModelRenderer getLeftLeg()
//    {
//        return upperLegL;
//    }
//
//    @Override
//    public ModelRenderer getHead()
//    {
//        return headbase;
//    }
//
//    @Override
//    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
//    {
//        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
//
//        if (entity instanceof EntityTransformer)
//        {
//            EntityTransformer transformer = (EntityTransformer) entity;
//
//            setToInitPose();
//
//            float globalSpeed = 1;
//            float globalDegree = 0.8F;
//
////            ModelOffset offsets = TFModelHelper.getOffsets(transformer);
////            headbase.rotationPointX += offsets.headOffsetX;
////            headbase.rotationPointY += offsets.headOffsetY;
////            headbase.rotationPointZ += offsets.headOffsetZ;
//
//            faceTarget(headbase, 1, par4, par5);
//
//            int backwardInverter = 1;
//
//            float moveForward = transformer.moveForward;
//
//            if (moveForward < 0)
//            {
//                backwardInverter = -1;
//                globalDegree = 0.5F;
//            }
//
//            applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
//            applyDefaultHittingAnimation(torsobaseR, upperArmL, headbase, torsobase, lowerArmR, lowerArmL);
//
//            if (isRiding)
//            {
//                upperArmR.rotateAngleX -= PI / 5F;
//                upperArmL.rotateAngleX -= PI / 5F;
//                upperLegR.rotateAngleX -= PI * 2F / 5F;
//                upperLegL.rotateAngleX -= PI * 2F / 5F;
//
//                upperLegR.rotateAngleY += PI / 10F;
//                upperLegL.rotateAngleY -= PI / 10F;
//            }
//
//            if (aimedBow)
//            {
//                upperArmR.rotateAngleY += -0.1F + headbase.rotateAngleY;
//                upperArmL.rotateAngleY += 0.1F + headbase.rotateAngleY + 0.4F;
//                upperArmR.rotateAngleX += -(PI / 2F) + headbase.rotateAngleX;
//                upperArmL.rotateAngleX += -(PI / 2F) + headbase.rotateAngleX;
//                upperArmR.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
//                upperArmL.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
//                upperArmR.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
//                upperArmL.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
//            }
//
//            boolean playerOnGround = onGround(transformer);
//
//            if (playerOnGround)
//            {
//                upperArmR.rotateAngleZ += 0.05F;
//                upperArmL.rotateAngleZ -= 0.05F;
//                lowerArmR.rotateAngleX -= 0.1F;
//                lowerArmL.rotateAngleX -= 0.1F;
//                upperLegR.rotateAngleY += 0.2;
//                upperLegL.rotateAngleY -= 0.2;
//                upperLegR.rotateAngleX -= 0.2;
//                upperLegL.rotateAngleX -= 0.2;
//                lowerlegR1.rotateAngleX += 0.15;
//                lowerlegL1.rotateAngleX += 0.15;
//                waist.rotateAngleX += 0.1F;
//                skirtR1.rotateAngleZ += 0.1F;
//                skirtL1.rotateAngleZ -= 0.1F;
//
//                MowzieModelRenderer armR = torsobaseR;
//                MowzieModelRenderer armL = torsobaseL;
//
//                bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, par1, par2);
//                waist.rotationPointY += 1.2 * par2;
//                walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
//                walk(torsobase, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
//                swing(torsobase, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0, 0, par1, par2);
//                swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, par1, par2);
//                swing(headbase, 0.5F * globalSpeed, 0.2F * globalDegree, true, 0, 0, par1, par2);
//                walk(headbase, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * par2 * backwardInverter, par1, par2);
//
//                swing(headbase, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, par1, par2);
//                headbase.rotationPointX += 0.6 * globalDegree * par2 * Math.cos(par1 * 0.5F * globalSpeed);
//
//                swing(upperLegR, 0.5F * globalSpeed, 0F * globalDegree, false, 0, -0.15F, par1, par2);
//                swing(upperLegL, 0.5F * globalSpeed, 0F * globalDegree, false, 0, 0.15F, par1, par2);
//                walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, par1, par2);
//                walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, par1, par2);
//                walk(lowerlegR1, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, par1, par2);
//                walk(lowerlegL1, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, par1, par2);
//                walk(armR, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * par2 * backwardInverter, par1, par2);
//                walk(armL, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * par2 * backwardInverter, par1, par2);
//                walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * par2, par1, par2);
//                walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * par2, par1, par2);
//
//                flap(skirtR1, 1F * globalSpeed, 0.2F * globalDegree, false, -1, 0, par1, par2);
//                flap(skirtL1, 1F * globalSpeed, 0.2F * globalDegree, true, -1, 0, par1, par2);
//                walk(barrelbase1, 1F * globalSpeed, -0.3F * globalDegree, false, -1, 0, par1, par2);
//
//
//                int ticksExisted = entity.ticksExisted;
//
//                walk(torsoconnector, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
//                walk(torsobase, 0.08F, 0.15F, false, 1, 0, ticksExisted, 1F);
//                flap(trackconnectorR3, 0.08F, 0.1F, false, 1, 0, ticksExisted, 1F);
//                flap(trackconnectorL3, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
//                walk(headbase, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
//                walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
//                walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
//
//                flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
//                flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticksExisted, 1F);
//                walk(lowerArmR, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
//                walk(lowerArmL, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
//
//                if (transformer.isSneaking())
//                {
//                    waist.rotationPointY += 1.8F;
//                    waist.rotateAngleX -= 0.1F;
//                    torsoconnector.rotateAngleX += 0.5;
//                    headbase.rotateAngleX -= 0.5;
//                    upperLegR.rotateAngleX -= 0.7;
//                    upperLegL.rotateAngleX -= 0.7;
//                    upperLegR.rotateAngleY += 0.2;
//                    upperLegL.rotateAngleY -= 0.2;
//                    lowerlegR1.rotateAngleX += 1.1;
//                    lowerlegL1.rotateAngleX += 1.1;
//                    feetbaseR1.rotateAngleX -= 0.3;
//                    feetbaseL1.rotateAngleX -= 0.3;
//                    upperArmR.rotateAngleX -= 0.5;
//                    upperArmL.rotateAngleX -= 0.5;
//                    upperArmR.rotateAngleZ += 0.5;
//                    upperArmL.rotateAngleZ -= 0.5;
//                    lowerArmR.rotateAngleZ -= 0.5;
//                    lowerArmL.rotateAngleZ += 0.5;
//                }
//            }
//            else
//            {
//                double motionY = entity.posY - entity.prevPosY;
//
//                float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
//                float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));
//
//                waist.rotateAngleX += 0.2 * par2 * backwardInverter;
//
//                stomach1.rotateAngleX += 0.2 * upwardPose;
//                chestplate1.rotateAngleX -= 0.4 * upwardPose;
//                headbase.rotateAngleX += 0.6 * upwardPose;
//
//                upperArmR.rotateAngleX += 0.1 * upwardPose;
//                upperArmL.rotateAngleX += 0.1 * upwardPose;
//                upperArmR.rotateAngleZ -= 0.1 * upwardPose;
//                upperArmL.rotateAngleZ += 0.1 * upwardPose;
//                lowerArmR.rotateAngleX += 0.2 * upwardPose;
//                lowerArmL.rotateAngleX += 0.2 * upwardPose;
//
//                upperLegR.rotateAngleX += 0.2 * upwardPose;
//                upperLegL.rotateAngleX -= 1 * upwardPose;
//                lowerlegR1.rotateAngleX += 0.3 * upwardPose;
//                lowerlegL1.rotateAngleX += 1.5 * upwardPose;
//
//                walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, par1, par2);
//                walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, par1, par2);
//                walk(lowerlegR1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, par1, par2);
//                walk(lowerlegL1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, par1, par2);
//
//                waist.rotateAngleX -= 0.2 * downwardPose;
//                stomach1.rotateAngleX += 0.3 * downwardPose;
//                chestplate1.rotateAngleX += 0.3 * downwardPose;
//                headbase.rotateAngleX += 0.3 * downwardPose;
//                upperLegR.rotateAngleX -= 1.2 * downwardPose;
//                upperLegL.rotateAngleX -= 0.2 * downwardPose;
//                lowerlegR1.rotateAngleX += 2 * downwardPose;
//                lowerlegL1.rotateAngleX += 0.5 * downwardPose;
//                upperArmR.rotateAngleZ += 1 * downwardPose;
//                upperArmL.rotateAngleZ -= 1 * downwardPose;
//                lowerArmR.rotateAngleX -= 1 * downwardPose;
//                lowerArmL.rotateAngleX -= 1 * downwardPose;
//            }
//
//            int t = transformer.getTransformationTimer();
//            float f = 20 - t;
//
//            rotateTo(waist, vehiclebase, f);
//            rotateTo(turretbase, vehicleturretbase_rotatehere, f);
//            rotateTo(turretconnector, vehicle31, f);
//            rotateTo(shoulderL4, vehicle11, f);
//            rotateTo(shoulderR4, vehicle26, f);
//
//            rotateTo(lowerlegL1, vehicle88, f);
//            rotateTo(lowerlegR1, vehicle81, f);
//
//            rotateTo(lowerlegR2, vehicle82, f);
//            rotateTo(lowerlegL2, vehicle89, f);
//
//            rotateTo(lowerlegR3, vehicle83, f);
//            rotateTo(lowerlegL3, vehicle90, f);
//
//            rotateTo(chestplateL1, vehicle33, f);
//            rotateTo(chestplateR1, vehicle37, f);
//
//            rotateTo(skirtL1, vehicle95, f);
//            rotateTo(skirtR1, vehicle97, f);
//
//            rotateTo(barrel1, vehiclebarrel1, f);
//
//            rotateTo(trackconnectorR1, vehicle42, f);
//            rotateTo(trackconnectorR2, vehicle43, f);
//            rotateTo(trackconnectorR3, vehicle44, f);
//
//            rotateTo(trackconnectorL1, vehicle61, f);
//            rotateTo(trackconnectorL2, vehicle62, f);
//            rotateTo(trackconnectorL3, vehicle63, f);
//
//            rotateTo(torsoconnector, vehicle1, f);
//            rotateTo(torsobase, vehicle2, f);
//
//            rotateTo(upperArmL, vehicle3, f);
//            rotateTo(upperArmR, vehicle17, f);
//            rotateTo(lowerArmL, vehicle4, f);
//            rotateTo(lowerArmR, vehicle18, f);
//            rotateTo(lowerarmL4, vehicle5, f);
//            rotateTo(lowerarmR3, vehicle19, f);
//            rotateTo(upperLegR, vehicle80, f);
//            rotateTo(upperLegL, vehicle87, f);
//
//            rotateTo(shoulderL1, vehicle8, f);
//            rotateTo(shoulderR1, vehicle23, f);
//
//            rotateTo(shoulderL6, vehicle13, f);
//            rotateTo(shoulderR6, vehicle28, f);
//
//            rotateTo(shoulderL2, vehicle9, f);
//            rotateTo(shoulderR2, vehicle24, f);
//
//            rotateTo(shoulderR3, vehicle25, f);
//            rotateTo(shoulderL3, vehicle10, f);
//
//            rotateTo(boxL1, vehicle99, f);
//            rotateTo(boxR1, vehicle103, f);
//
//            rotateTo(boxL2, vehicle100, f);
//            rotateTo(boxR2, vehicle104, f);
//
//            rotateTo(boxL3, vehicle101, f);
//            rotateTo(boxR3, vehicle105, f);
//
//            rotateTo(waistL3, vehicle98, f);
//            rotateTo(waistR3, vehicle102, f);
//
//            rotateTo(lowerlegR13, vehicle86, f);
//            rotateTo(lowerlegL13, vehicle93, f);
//
//            rotateTo(lowerlegR11, vehicle84, f);
//            rotateTo(lowerlegL11, vehicle91, f);
//
//            rotateTo(lowerlegR12, vehicle85, f);
//            rotateTo(lowerlegL12, vehicle92, f);
//
//            rotateTo(trackR6, vehicle58, f);
//            rotateTo(trackR7, vehicle59, f);
//            rotateTo(trackR8, vehicle60, f);
//            rotateTo(trackR4, vehicle56, f);
//            rotateTo(trackR3, vehicle55, f);
//            rotateTo(trackR2, vehicle54, f);
//            rotateTo(trackR1, vehicle53, f);
//            rotateTo(trackR5, vehicle57, f);
//
//            rotateTo(wheelR5, vehicle52, f);
//            rotateTo(wheelR4, vehicle51, f);
//            rotateTo(wheelR3, vehicle50, f);
//            rotateTo(wheelR2, vehicle49, f);
//            rotateTo(wheelR1, vehicle47, f);
//
//            rotateTo(trackL6, vehicle77, f);
//            rotateTo(trackL7, vehicle78, f);
//            rotateTo(trackL8, vehicle79, f);
//            rotateTo(trackL4, vehicle75, f);
//            rotateTo(trackL3, vehicle74, f);
//            rotateTo(trackL2, vehicle73, f);
//            rotateTo(trackL1, vehicle72, f);
//            rotateTo(trackL5, vehicle76, f);
//
//            rotateTo(wheelL5, vehicle71, f);
//            rotateTo(wheelL4, vehicle70, f);
//            rotateTo(wheelL3, vehicle69, f);
//            rotateTo(wheelL2, vehicle68, f);
//            rotateTo(wheelL1, vehicle66, f);
//
//            vehiclebase.rotateAngleY = bipedBody.rotateAngleY;
//
//            feetbaseL1.rotationPointY -= f * 0.25F;
//            feetbaseR1.rotationPointY -= f * 0.25F;
//
//            feetbaseL1.rotationPointX -= f * 0.05F;
//            feetbaseR1.rotationPointX += f * 0.05F;
//
//            headbase.rotationPointY += f * 0.15F;
//            headbase.rotationPointZ += f * 0.3F;
//            vehicleturretbase_rotatehere.rotateAngleZ = -(par4 + 180) / (180f / PI);
//
//            if (par5 > 0)
//            {
//                par5 = 0;
//            }
//
//            if (par5 < -60)
//            {
//                par5 = -60;
//            }
//
//            vehiclebarrelbase1_rotatehere.rotateAngleX = -par5 / (180f / PI);
//
//            ItemStack heldItem = transformer.getHeldItem();
//
//            trackconnectorR1.showModel = !(heldItem != null && heldItem.getItem() == TFItems.purgesKatana);
//        }
//    }
//
//    /**
//     * This is a helper function from Tabula to set the rotation of model parts
//     */
//    @Override
//    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
//    {
//        modelRenderer.rotateAngleX = x;
//        modelRenderer.rotateAngleY = y;
//        modelRenderer.rotateAngleZ = z;
//    }
//
//    @Override
//    public void renderArmorPiece(int armorPiece)
//    {
//        setToInitPose();
//
//        if (armorPiece == 0)
//        {
//            this.headbase.render(0.0625F);
//        }
//        else if (armorPiece == 1)
//        {
//            this.upperLegL.showModel = false;
//            this.upperLegR.showModel = false;
//            this.headbase.showModel = false;
//            this.waist.render(0.0625F);
//            this.upperLegL.showModel = true;
//            this.upperLegR.showModel = true;
//            this.headbase.showModel = true;
//        }
//        else if (armorPiece == 2)
//        {
//            this.feetbaseL1.showModel = false;
//            this.feetbaseR1.showModel = false;
//            this.upperLegL.render(0.0625F);
//            this.upperLegR.render(0.0625F);
//            this.feetbaseL1.showModel = true;
//            this.feetbaseR1.showModel = true;
//        }
//        else if (armorPiece == 3)
//        {
//            GL11.glTranslatef(0.1F, 0.0F, 0.0F);
//            this.feetbaseL1.rotationPointX -= 4;
//            this.feetbaseR1.rotationPointX += 4;
//            this.feetbaseL1.renderWithParentRotations(0.0625F);
//            this.feetbaseR1.renderWithParentRotations(0.0625F);
//        }
//    }
//}
////package fiskfille.tf.client.model.transformer;
////
////import fiskfille.tf.client.model.tools.MowzieModelBase;
////import fiskfille.tf.client.model.tools.MowzieModelRenderer;
////import fiskfille.tf.common.entity.EntityTransformer;
////import TFItems;
////import net.minecraft.client.model.ModelRenderer;
////import net.minecraft.entity.Entity;
////import net.minecraft.util.MathHelper;
////
////public class ModelTransformer extends MowzieModelBase
////{
////    public MowzieModelRenderer upperLegR;
////    public MowzieModelRenderer upperLegPanel1;
////    public MowzieModelRenderer lowerLeg1;
////    public MowzieModelRenderer lowerLegPanel1;
////    public MowzieModelRenderer footCylinder1;
////    public MowzieModelRenderer foot1;
////    public MowzieModelRenderer waist;
////    public MowzieModelRenderer stomach;
////    public MowzieModelRenderer hipSlab1;
////    public MowzieModelRenderer hipSlab2;
////    public MowzieModelRenderer chest;
////    public MowzieModelRenderer backKibble;
////    public MowzieModelRenderer chestSlab1;
////    public MowzieModelRenderer chestSlab2;
////    public MowzieModelRenderer tread1;
////    public MowzieModelRenderer tread2;
////    public MowzieModelRenderer turret;
////    public MowzieModelRenderer gun;
////    public MowzieModelRenderer turretFront;
////    public MowzieModelRenderer turretSights;
////    public MowzieModelRenderer turretRear;
////    public MowzieModelRenderer missileLauncher;
////    public MowzieModelRenderer hipPanel1;
////    public MowzieModelRenderer hipPanel2;
////    public MowzieModelRenderer upperArmL;
////    public MowzieModelRenderer shoulderPad2;
////    public MowzieModelRenderer lowerArm2;
////    public MowzieModelRenderer lowerArmPanel2;
////    public MowzieModelRenderer upperArmR;
////    public MowzieModelRenderer shoulderPad1;
////    public MowzieModelRenderer lowerArm1;
////    public MowzieModelRenderer lowerArmPanel1;
////    public MowzieModelRenderer head;
////    public MowzieModelRenderer helmetRight;
////    public MowzieModelRenderer helmetLeft;
////    public MowzieModelRenderer helmetTop;
////    public MowzieModelRenderer helmetBack;
////    public MowzieModelRenderer horn1;
////    public MowzieModelRenderer horn2;
////    public MowzieModelRenderer centerHorn;
////    public MowzieModelRenderer upperLegL;
////    public MowzieModelRenderer upperLegPanel2;
////    public MowzieModelRenderer lowerLeg2;
////    public MowzieModelRenderer lowerLegPanel2;
////    public MowzieModelRenderer footCylinder2;
////    public MowzieModelRenderer foot2;
////
////    public ModelRenderer vehicleTread1;
////    public ModelRenderer vehicleTread2;
////    public ModelRenderer vehicleFoot1;
////    public ModelRenderer vehicleFoot2;
////    public ModelRenderer vehicleBody;
////    public ModelRenderer vehicleTrackcover2;
////    public ModelRenderer vehicleTrackcover1;
////    public ModelRenderer vehicleRearPlate;
////    public ModelRenderer vehicleBodyFront;
////    public ModelRenderer vehicleBack2;
////    public ModelRenderer vehicleBack1;
////    public ModelRenderer vehicleTurret;
////    public ModelRenderer vehicleBodyplate;
////    public ModelRenderer vehicleTurretFront;
////    public ModelRenderer vehicleTurretSights;
////    public ModelRenderer vehicleGun;
////    public ModelRenderer vehicleTurretRear;
////    public ModelRenderer vehicleMissileLauncher;
////
////    public ModelTransformer()
////    {
////        textureWidth = 128;
////        textureHeight = 128;
////
////        bipedBody = new MowzieModelRenderer(this, 1000, 1000);
////        bipedHead = new MowzieModelRenderer(this, 1000, 1000);
////        bipedHeadwear = new MowzieModelRenderer(this, 1000, 1000);
////        bipedRightLeg = new MowzieModelRenderer(this, 1000, 1000);
////        bipedLeftLeg = new MowzieModelRenderer(this, 1000, 1000);
////        bipedRightArm = new MowzieModelRenderer(this, 1000, 1000);
////        bipedLeftArm = new MowzieModelRenderer(this, 1000, 1000);
////
////        shoulderPad2 = new MowzieModelRenderer(this, 23, 102);
////        shoulderPad2.setRotationPoint(-0.5F, 1.0F, 0.0F);
////        shoulderPad2.addBox(0.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
////        head = new MowzieModelRenderer(this, 0, 0);
////        head.setRotationPoint(0.0F, 0.0F, 0.0F);
////        head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F);
////        upperLegL = new MowzieModelRenderer(this, 0, 16);
////        upperLegL.setRotationPoint(1.9F, 12.0F, 0.0F);
////        upperLegL.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
////        this.setRotation(upperLegL, 0.0F, -0.0F, -0.05235987755982988F);
////        turret = new MowzieModelRenderer(this, 21, 64);
////        turret.setRotationPoint(0.0F, -8.0F, 2.0F);
////        turret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
////        this.setRotation(turret, 0.7853981633974483F, 3.141592653589793F, 0.0F);
////        waist = new MowzieModelRenderer(this, 10, 16);
////        waist.setRotationPoint(0.0F, 12.0F, 0.0F);
////        waist.addBox(-3.0F, -3.0F, -1.5F, 6, 3, 3, 0.0F);
////        helmetBack = new MowzieModelRenderer(this, 27, 9);
////        helmetBack.setRotationPoint(0.0F, -4.0F, 1.5F);
////        helmetBack.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
////        this.setRotation(helmetBack, 0.12217304855585097F, -0.0F, 0.0F);
////        centerHorn = new MowzieModelRenderer(this, 23, 9);
////        centerHorn.setRotationPoint(0.0F, -4.5F, -2.0F);
////        centerHorn.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 1, 0.0F);
////        footCylinder2 = new MowzieModelRenderer(this, 8, 31);
////        footCylinder2.setRotationPoint(0.0F, 5.0F, 1.0F);
////        footCylinder2.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
////        this.setRotation(footCylinder2, 0.0F, -0.0F, 0.05235987755982988F);
////        tread1 = new MowzieModelRenderer(this, 46, 0);
////        tread1.setRotationPoint(-3.0F, -4.0F, 2.5F);
////        tread1.addBox(-2.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
////        this.setRotation(tread1, 0.17453292519943295F, -0.0F, -0.08726646259971647F);
////        chestSlab1 = new MowzieModelRenderer(this, 24, 35);
////        chestSlab1.setRotationPoint(0.0F, 1.0F, -1.1F);
////        chestSlab1.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
////        this.setRotation(chestSlab1, 0.0F, -0.0F, 0.5235987755982988F);
////        lowerLegPanel2 = new MowzieModelRenderer(this, 0, 37);
////        lowerLegPanel2.mirror = true;
////        lowerLegPanel2.setRotationPoint(0.0F, 5.0F, -1.5F);
////        lowerLegPanel2.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
////        this.setRotation(lowerLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
////        upperArmL = new MowzieModelRenderer(this, 36, 21);
////        upperArmL.mirror = true;
////        upperArmL.setRotationPoint(4.0F, 1.0F, 0.0F);
////        upperArmL.addBox(0.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
////        this.setRotation(upperArmL, 0.0F, -0.0F, -0.13962633907794952F);
////        gun = new MowzieModelRenderer(this, 38, 69);
////        gun.setRotationPoint(0.0F, -1.1F, -3.0F);
////        gun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
////        this.setRotation(gun, 0.4363323129985824F, 0.0F, 0.0F);
////        turretFront = new MowzieModelRenderer(this, 45, 97);
////        turretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
////        turretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
////        this.setRotation(turretFront, -0.4974188368183839F, 0.0F, 0.0F);
////        helmetLeft = new MowzieModelRenderer(this, 28, 0);
////        helmetLeft.mirror = true;
////        helmetLeft.setRotationPoint(3.0F, -0.5F, 0.0F);
////        helmetLeft.addBox(0.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
////        this.setRotation(helmetLeft, 0.07438152130324906F, -0.15803570099691838F, -0.4422247463038985F);
////        horn1 = new MowzieModelRenderer(this, 13, 9);
////        horn1.setRotationPoint(0.0F, -4.0F, -2.0F);
////        horn1.addBox(0.0F, 0.0F, -1.0F, 4, 1, 1, 0.0F);
////        this.setRotation(horn1, 0.0F, -0.0F, -2.268928050994873F);
////        lowerArm1 = new MowzieModelRenderer(this, 36, 30);
////        lowerArm1.setRotationPoint(-1.2F, 4.6F, 0.0F);
////        lowerArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
////        this.setRotation(lowerArm1, -0.19198621771937624F, -0.13962634015954636F, -0.13962634015954636F);
////        upperLegPanel2 = new MowzieModelRenderer(this, 0, 31);
////        upperLegPanel2.mirror = true;
////        upperLegPanel2.setRotationPoint(0.0F, 5.5F, -1.5F);
////        upperLegPanel2.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
////        this.setRotation(upperLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
////        upperLegR = new MowzieModelRenderer(this, 0, 16);
////        upperLegR.setRotationPoint(-1.9F, 12.0F, 0.0F);
////        upperLegR.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
////        this.setRotation(upperLegR, 0.0F, -0.0F, 0.05235987755982988F);
////        turretSights = new MowzieModelRenderer(this, 40, 65);
////        turretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
////        turretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
////        this.setRotation(turretSights, 0.017453292519943295F, 0.0F, 0.0F);
////        tread2 = new MowzieModelRenderer(this, 46, 0);
////        tread2.mirror = true;
////        tread2.setRotationPoint(3.0F, -4.0F, 2.5F);
////        tread2.addBox(0.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
////        this.setRotation(tread2, 0.17453292519943295F, -0.0F, 0.08726646259971647F);
////        chest = new MowzieModelRenderer(this, 0, 42);
////        chest.setRotationPoint(0.0F, -4.0F, 0.0F);
////        chest.addBox(-4.0F, -5.0F, -2.0F, 8, 5, 4, 0.0F);
////        lowerLeg2 = new MowzieModelRenderer(this, 0, 16);
////        lowerLeg2.setRotationPoint(0.0F, 5.0F, 0.0F);
////        lowerLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
////        foot1 = new MowzieModelRenderer(this, 8, 35);
////        foot1.setRotationPoint(0.0F, 5.0F, 1.0F);
////        foot1.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
////        this.setRotation(foot1, 0.3490658503988659F, -0.0F, -0.05235987755982988F);
////        chestSlab2 = new MowzieModelRenderer(this, 24, 35);
////        chestSlab2.mirror = true;
////        chestSlab2.setRotationPoint(0.0F, 1.0F, -1.1F);
////        chestSlab2.addBox(0.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
////        this.setRotation(chestSlab2, 0.0F, -0.0F, -0.5235987755982988F);
////        missileLauncher = new MowzieModelRenderer(this, 30, 90);
////        missileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
////        missileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
////        this.setRotation(missileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
////        backKibble = new MowzieModelRenderer(this, 24, 39);
////        backKibble.setRotationPoint(0.0F, -1.0F, 0.0F);
////        backKibble.addBox(-3.0F, -6.0F, -0.5F, 6, 6, 5, 0.0F);
////        this.setRotation(backKibble, -0.3490658503988659F, -0.0F, 0.0F);
////        upperArmR = new MowzieModelRenderer(this, 36, 21);
////        upperArmR.setRotationPoint(-4.0F, 1.0F, 0.0F);
////        upperArmR.addBox(-2.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
////        this.setRotation(upperArmR, 0.0F, 0.0F, 0.13962634015954636F);
////        turretRear = new MowzieModelRenderer(this, 46, 90);
////        turretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
////        turretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
////        this.setRotation(turretRear, 0.22689280275926282F, 0.0F, 0.0F);
////        footCylinder1 = new MowzieModelRenderer(this, 8, 31);
////        footCylinder1.setRotationPoint(0.0F, 5.0F, 1.0F);
////        footCylinder1.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
////        this.setRotation(footCylinder1, 0.0F, -0.0F, -0.05235987755982988F);
////        helmetTop = new MowzieModelRenderer(this, 0, 8);
////        helmetTop.setRotationPoint(0.0F, -4.0F, 0.0F);
////        helmetTop.addBox(-2.0F, -2.0F, -2.5F, 4, 2, 5, 0.0F);
////        foot2 = new MowzieModelRenderer(this, 8, 35);
////        foot2.mirror = true;
////        foot2.setRotationPoint(0.0F, 5.0F, 1.0F);
////        foot2.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
////        this.setRotation(foot2, 0.3490658503988659F, -0.0F, 0.03490658503988659F);
////        lowerLeg1 = new MowzieModelRenderer(this, 0, 16);
////        lowerLeg1.setRotationPoint(0.0F, 5.0F, 0.0F);
////        lowerLeg1.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
////        lowerArmPanel1 = new MowzieModelRenderer(this, 44, 30);
////        lowerArmPanel1.setRotationPoint(0.3F, 3.0F, 0.0F);
////        lowerArmPanel1.addBox(-2.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
////        lowerLegPanel1 = new MowzieModelRenderer(this, 0, 37);
////        lowerLegPanel1.setRotationPoint(0.0F, 5.0F, -1.5F);
////        lowerLegPanel1.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
////        this.setRotation(lowerLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
////        hipSlab2 = new MowzieModelRenderer(this, 18, 22);
////        hipSlab2.mirror = true;
////        hipSlab2.setRotationPoint(0.0F, 0.0F, 0.0F);
////        hipSlab2.addBox(0.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
////        this.setRotation(hipSlab2, 0.0F, -0.0F, -0.3490658503988659F);
////        lowerArmPanel2 = new MowzieModelRenderer(this, 44, 30);
////        lowerArmPanel2.mirror = true;
////        lowerArmPanel2.setRotationPoint(-0.3F, 3.0F, 0.0F);
////        lowerArmPanel2.addBox(0.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
////        stomach = new MowzieModelRenderer(this, 20, 28);
////        stomach.setRotationPoint(0.0F, -3.0F, 0.0F);
////        stomach.addBox(-2.5F, -4.0F, -1.5F, 5, 4, 3, 0.0F);
////        lowerArm2 = new MowzieModelRenderer(this, 36, 30);
////        lowerArm2.mirror = true;
////        lowerArm2.setRotationPoint(1.2F, 4.6F, 0.0F);
////        lowerArm2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
////        this.setRotation(lowerArm2, -0.19198621771937624F, 0.13962634015954636F, 0.13962634015954636F);
////        hipSlab1 = new MowzieModelRenderer(this, 18, 22);
////        hipSlab1.setRotationPoint(0.0F, 0.0F, 0.0F);
////        hipSlab1.addBox(-4.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
////        this.setRotation(hipSlab1, 0.0F, 0.0F, 0.3490658503988659F);
////        upperLegPanel1 = new MowzieModelRenderer(this, 0, 31);
////        upperLegPanel1.setRotationPoint(0.0F, 5.5F, -1.5F);
////        upperLegPanel1.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
////        this.setRotation(upperLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
////        hipPanel1 = new MowzieModelRenderer(this, 10, 22);
////        hipPanel1.mirror = true;
////        hipPanel1.setRotationPoint(-3.3F, 1.3F, 0.0F);
////        hipPanel1.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
////        this.setRotation(hipPanel1, 0.0F, -0.0F, -0.03490658503988659F);
////        helmetRight = new MowzieModelRenderer(this, 28, 0);
////        helmetRight.setRotationPoint(-3.0F, -0.5F, 0.0F);
////        helmetRight.addBox(-1.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
////        this.setRotation(helmetRight, 0.07438152130324906F, 0.15803570099691838F, 0.4422247463038985F);
////        shoulderPad1 = new MowzieModelRenderer(this, 23, 102);
////        shoulderPad1.setRotationPoint(0.5F, 1.0F, 0.0F);
////        shoulderPad1.addBox(-4.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
////        horn2 = new MowzieModelRenderer(this, 13, 9);
////        horn2.setRotationPoint(0.0F, -4.0F, -2.0F);
////        horn2.addBox(0.0F, -1.0F, -1.0F, 4, 1, 1, 0.0F);
////        this.setRotation(horn2, 0.0F, -0.0F, -0.8726646304130553F);
////        hipPanel2 = new MowzieModelRenderer(this, 10, 22);
////        hipPanel2.mirror = true;
////        hipPanel2.setRotationPoint(3.3F, 1.3F, 0.0F);
////        hipPanel2.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
////        this.setRotation(hipPanel2, 0.0F, -0.0F, -0.03490658503988659F);
////        upperArmL.addChild(shoulderPad2);
////        backKibble.addChild(turret);
////        head.addChild(helmetBack);
////        head.addChild(centerHorn);
////        lowerLeg2.addChild(footCylinder2);
////        backKibble.addChild(tread1);
////        chest.addChild(chestSlab1);
////        lowerLeg2.addChild(lowerLegPanel2);
////        turret.addChild(gun);
////        turret.addChild(turretFront);
////        head.addChild(helmetLeft);
////        head.addChild(horn1);
////        upperArmR.addChild(lowerArm1);
////        upperLegL.addChild(upperLegPanel2);
////        turret.addChild(turretSights);
////        backKibble.addChild(tread2);
////        stomach.addChild(chest);
////        upperLegL.addChild(lowerLeg2);
////        lowerLeg1.addChild(foot1);
////        chest.addChild(chestSlab2);
////        turretRear.addChild(missileLauncher);
////        chest.addChild(backKibble);
////        turret.addChild(turretRear);
////        lowerLeg1.addChild(footCylinder1);
////        head.addChild(helmetTop);
////        lowerLeg2.addChild(foot2);
////        upperLegR.addChild(lowerLeg1);
////        lowerArm1.addChild(lowerArmPanel1);
////        lowerLeg1.addChild(lowerLegPanel1);
////        waist.addChild(hipSlab2);
////        lowerArm2.addChild(lowerArmPanel2);
////        waist.addChild(stomach);
////        upperArmL.addChild(lowerArm2);
////        waist.addChild(hipSlab1);
////        upperLegR.addChild(upperLegPanel1);
////        hipSlab1.addChild(hipPanel1);
////        head.addChild(helmetRight);
////        upperArmR.addChild(shoulderPad1);
////        head.addChild(horn2);
////        hipSlab2.addChild(hipPanel2);
////        chest.addChild(head);
////        bipedBody.addChild(waist);
////        chest.addChild(upperArmR);
////        chest.addChild(upperArmL);
////        waist.addChild(upperLegR);
////        waist.addChild(upperLegL);
////
////        vehicleTread2 = new ModelRenderer(this, 0, 64);
////        vehicleTread2.setRotationPoint(4.1F, 0.0F, 4.0F);
////        vehicleTread2.addBox(0.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
////        vehicleBack2 = new ModelRenderer(this, 23, 102);
////        vehicleBack2.setRotationPoint(2.5F, -6.5F, 3.8F);
////        vehicleBack2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
////        vehicleBodyFront = new ModelRenderer(this, 39, 83);
////        vehicleBodyFront.setRotationPoint(0.0F, -5.0F, -5.5F);
////        vehicleBodyFront.addBox(-4.0F, 0.0F, -3.0F, 8, 4, 3, 0.0F);
////        this.setRotation(vehicleBodyFront, 0.21467549799530256F, 0.0F, 0.0F);
////        vehicleTurretFront = new ModelRenderer(this, 45, 97);
////        vehicleTurretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
////        vehicleTurretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
////        this.setRotation(vehicleTurretFront, -0.4974188368183839F, 0.0F, 0.0F);
////        vehicleFoot1 = new ModelRenderer(this, 0, 64);
////        vehicleFoot1.setRotationPoint(-5.0F, -1.5F, -9.2F);
////        vehicleFoot1.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
////        this.setRotation(vehicleFoot1, -0.03490658503988659F, -0.0F, 0.03490658503988659F);
////        vehicleBodyplate = new ModelRenderer(this, 0, 86);
////        vehicleBodyplate.setRotationPoint(-4.0F, -5.0F, -5.5F);
////        vehicleBodyplate.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
////        vehicleRearPlate = new ModelRenderer(this, 0, 71);
////        vehicleRearPlate.setRotationPoint(0.0F, -5.0F, 8.5F);
////        vehicleRearPlate.addBox(-3.0F, 0.0F, 0.0F, 6, 5, 1, 0.0F);
////        this.setRotation(vehicleRearPlate, -0.3839724354387525F, 0.0F, 0.0F);
////        vehicleTurretSights = new ModelRenderer(this, 40, 65);
////        vehicleTurretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
////        vehicleTurretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
////        this.setRotation(vehicleTurretSights, 0.017453292519943295F, 0.0F, 0.0F);
////        vehicleBody = new ModelRenderer(this, 64, 64);
////        vehicleBody.setRotationPoint(0.0F, 22.5F, 0.5F);
////        vehicleBody.addBox(-5.0F, -4.0F, -7.5F, 10, 4, 15, 0.0F);
////        vehicleTread1 = new ModelRenderer(this, 0, 64);
////        vehicleTread1.setRotationPoint(-4.1F, 0.0F, 4.0F);
////        vehicleTread1.addBox(-2.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
////        vehicleTurretRear = new ModelRenderer(this, 46, 90);
////        vehicleTurretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
////        vehicleTurretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
////        this.setRotation(vehicleTurretRear, 0.22689280275926282F, 0.0F, 0.0F);
////        vehicleTurret = new ModelRenderer(this, 21, 64);
////        vehicleTurret.setRotationPoint(0.0F, -6.0F, -2.5F);
////        vehicleTurret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
////        vehicleTrackcover2 = new ModelRenderer(this, 0, 101);
////        vehicleTrackcover2.setRotationPoint(3.5F, -5.5F, -10.2F);
////        vehicleTrackcover2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
////        vehicleFoot2 = new ModelRenderer(this, 0, 64);
////        vehicleFoot2.mirror = true;
////        vehicleFoot2.setRotationPoint(5.0F, -1.5F, -9.2F);
////        vehicleFoot2.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
////        this.setRotation(vehicleFoot2, -0.03490658503988659F, -0.0F, -0.03490658503988659F);
////        vehicleMissileLauncher = new ModelRenderer(this, 30, 90);
////        vehicleMissileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
////        vehicleMissileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
////        this.setRotation(vehicleMissileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
////        vehicleBack1 = new ModelRenderer(this, 23, 102);
////        vehicleBack1.setRotationPoint(-2.5F, -6.5F, 3.8F);
////        vehicleBack1.addBox(-4.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
////        vehicleTrackcover1 = new ModelRenderer(this, 0, 101);
////        vehicleTrackcover1.setRotationPoint(-3.5F, -5.5F, -10.2F);
////        vehicleTrackcover1.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
////        vehicleGun = new ModelRenderer(this, 38, 69);
////        vehicleGun.setRotationPoint(0.0F, -1.1F, -3.0F);
////        vehicleGun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
////        vehicleBody.addChild(vehicleTread2);
////        vehicleBody.addChild(vehicleBack2);
////        vehicleBody.addChild(vehicleBodyFront);
////        vehicleTurret.addChild(vehicleTurretFront);
////        vehicleBody.addChild(vehicleFoot1);
////        vehicleBody.addChild(vehicleBodyplate);
////        vehicleBody.addChild(vehicleRearPlate);
////        vehicleTurret.addChild(vehicleTurretSights);
////        vehicleBody.addChild(vehicleTread1);
////        vehicleTurret.addChild(vehicleTurretRear);
////        vehicleBody.addChild(vehicleTurret);
////        vehicleBody.addChild(vehicleTrackcover2);
////        vehicleBody.addChild(vehicleFoot2);
////        vehicleTurretRear.addChild(vehicleMissileLauncher);
////        vehicleBody.addChild(vehicleBack1);
////        vehicleBody.addChild(vehicleTrackcover1);
////        vehicleTurret.addChild(vehicleGun);
////
////        //parts = new MowzieModelRenderer[]{upperLegL, lowerLeg2, foot2, footCylinder2, lowerLegPanel2, upperLegPanel2, upperLegR, lowerLeg1, foot1, footCylinder1, lowerLegPanel1, upperLegPanel1, waist, stomach, hipSlab1, hipSlab2, chest, backKibble, chestSlab1, chestSlab2, tread1, tread2, turret, gun, turretFront, turretSights, turretRear, missileLauncher, hipPanel1, hipPanel2, upperArmL, shoulderPad2, lowerArm2, lowerArmPanel2, upperArmR, shoulderPad1, lowerArm1, lowerArmPanel1, head, helmetRight, helmetLeft, helmetTop, helmetBack, horn1, horn2, centerHorn};
////        setInitPose();
////    }
////
////    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
////    {
////        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
////
////        if (entity instanceof EntityTransformer)
////        {
////            EntityTransformer transformer = (EntityTransformer) entity;
////
////            if (transformer.getTransformationTimer() != 0)
////            {
////                waist.render(f5);
////            }
////            else
////            {
////                vehicleBody.render(f5);
////            }
////        }
////    }
////
////    private void setRotation(MowzieModelRenderer model, float x, float y, float z)
////    {
////        model.rotateAngleX = x;
////        model.rotateAngleY = y;
////        model.rotateAngleZ = z;
////    }
////
////    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
////    {
////        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
////
////        if (entity instanceof EntityTransformer)
////        {
////            EntityTransformer transformer = (EntityTransformer) entity;
////
////            setToInitPose();
////
////            float globalSpeed = 1;
////            float globalDegree = 0.8F;
////
////            upperArmR.rotationPointX = 1;
////            upperArmL.rotationPointX = -1;
////
////            vehicleTurret.rotationPointZ = -1;
////
////            //Walk animation
////            upperArmR.rotationPointX -= 5;
////            upperArmL.rotationPointX += 5;
////            upperArmR.rotationPointY -= 5;
////            upperArmL.rotationPointY -= 5;
////            backKibble.rotationPointY += 5;
////
////            waist.rotationPointY += 1;
////
////            boolean sneaking = entity.isSneaking();
////
////            if (sneaking)
////            {
////                waist.rotateAngleX -= 0.5F;
////                waist.rotationPointZ -= 6F;
////                waist.rotationPointY -= 3F;
////                globalDegree = 1.5F;
////                globalSpeed = 1.5F;
////            }
////
////            head.rotationPointY -= 5.5F;
////
////            head.rotateAngleY = par4 / (180F / PI);
////            head.rotateAngleX = par5 / (180F / PI);
////
////            int backwardInverter = 1;
////            if (transformer.moveForward < 0)
////            {
////                backwardInverter = -1;
////                globalDegree = 0.5F;
////            }
////
////            if (heldItemLeft != 0)
////            {
////                upperArmL.rotateAngleX -= 0.2F;
////            }
////            if (heldItemRight != 0)
////            {
////                upperArmR.rotateAngleX -= 0.2F;
////            }
////
////            if (onGround > -9990.0F)
////            {
////                float hitTick = onGround;
////                double max = 0.99126524;
////
////                stomach.rotateAngleY += 20 * hitTick * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
////                chest.rotateAngleY += 20 * hitTick * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
////                head.rotateAngleY += -40 * hitTick * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
////                upperArmR.rotateAngleZ += -2 * hitTick * (Math.pow(hitTick, 5) - max);
////                upperArmR.rotateAngleX += 40 * hitTick * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
////                lowerArm1.rotateAngleX += 50 * hitTick * (hitTick - 0.5) * (hitTick - 0.5) * (hitTick - max);
////                upperArmL.rotateAngleX -= 40 * hitTick * (hitTick - 0.4) * (Math.pow(hitTick, 0.3F) - max);
////                lowerArm2.rotateAngleX += 25 * hitTick * (Math.pow(hitTick, 0.1) - max);
////            }
////
////            upperLegL.rotationPointY -= 12;
////            upperLegR.rotationPointY -= 12;
////
////            boolean playerOnGround = onGround(transformer);
////
////            if (playerOnGround)
////            {
////                //New pose!
////                upperLegR.rotateAngleY += 0.2;
////                upperLegL.rotateAngleY -= 0.2;
////                upperLegR.rotateAngleX -= 0.4;
////                upperLegL.rotateAngleX -= 0.4;
////                lowerLeg1.rotateAngleX += 0.6;
////                lowerLeg2.rotateAngleX += 0.6;
////                foot1.rotateAngleX -= 0.3;
////                foot2.rotateAngleX -= 0.3;
////
////                waist.rotateAngleX += 0.1;
////
////                head.rotateAngleX += 0.1;
////
////                bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, par1, par2);
////                waist.rotationPointY += 1.2 * par2;
////                walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
////                walk(chest, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
////                swing(chest, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0, 0, par1, par2);
////                swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, par1, par2);
////                walk(head, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * par2 * backwardInverter, par1, par2);
////
////                swing(head, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, par1, par2);
////                head.rotationPointX += 0.6 * globalDegree * par2 * Math.cos(par1 * 0.5F * globalSpeed);
////
////                swing(upperLegR, 0.5F * globalSpeed, 0F * globalDegree, false, 0, -0.15F, par1, par2);
////                swing(upperLegL, 0.5F * globalSpeed, 0F * globalDegree, false, 0, 0.15F, par1, par2);
////                walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, par1, par2);
////                walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, par1, par2);
////                walk(lowerLeg1, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, par1, par2);
////                walk(lowerLeg2, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, par1, par2);
////                walk(upperArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * par2 * backwardInverter, par1, par2);
////                walk(upperArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * par2 * backwardInverter, par1, par2);
////                walk(lowerArm1, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * par2, par1, par2);
////                walk(lowerArm2, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * par2, par1, par2);
////
////                flap(hipPanel1, 1F * globalSpeed, 0.2F * globalDegree, false, -1, 0, par1, par2);
////                flap(hipPanel2, 1F * globalSpeed, 0.2F * globalDegree, true, -1, 0, par1, par2);
////                walk(gun, 1F * globalSpeed, -0.3F * globalDegree, false, -1, 0, par1, par2);
////
////                //Idle animation
////                int ticksExisted = entity.ticksExisted;
////
////                walk(stomach, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
////                walk(chest, 0.08F, 0.15F, false, 1, 0, ticksExisted, 1F);
////                walk(head, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
////                walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
////                walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
////
////                flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
////                flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticksExisted, 1F);
////                walk(lowerArm1, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
////                walk(lowerArm2, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
////
////                if (sneaking)
////                {
////                    waist.rotationPointY += 3;
////                    waist.rotationPointZ += 3;
////                    waist.rotateAngleX += 0.5;
////                    stomach.rotateAngleX += 0.5;
////                    head.rotateAngleX -= 0.5;
////                    upperLegR.rotateAngleX -= 0.7;
////                    upperLegL.rotateAngleX -= 0.7;
////                    upperLegR.rotateAngleY += 0.2;
////                    upperLegL.rotateAngleY -= 0.2;
////                    lowerLeg1.rotateAngleX += 1.1;
////                    lowerLeg2.rotateAngleX += 1.1;
////                    foot1.rotateAngleX -= 0.5;
////                    foot2.rotateAngleX -= 0.5;
////                    foot1.rotationPointY += 2;
////                    foot2.rotationPointY += 2;
////                    upperArmR.rotateAngleX -= 0.5;
////                    upperArmL.rotateAngleX -= 0.5;
////                    upperArmR.rotateAngleZ += 0.5;
////                    upperArmL.rotateAngleZ -= 0.5;
////                    lowerArm1.rotateAngleZ -= 0.5;
////                    lowerArm2.rotateAngleZ += 0.5;
////                }
////            }
////            else
////            {
////                double motionY = entity.posY - entity.prevPosY;
////                float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
////                float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));
////
////                waist.rotateAngleX += 0.2 * par2 * backwardInverter;
////
////                stomach.rotateAngleX += 0.2 * upwardPose;
////                chest.rotateAngleX -= 0.4 * upwardPose;
////                head.rotateAngleX += 0.6 * upwardPose;
////
////                upperArmR.rotateAngleX += 0.1 * upwardPose;
////                upperArmL.rotateAngleX += 0.1 * upwardPose;
////                upperArmR.rotateAngleZ -= 0.1 * upwardPose;
////                upperArmL.rotateAngleZ += 0.1 * upwardPose;
////                lowerArm1.rotateAngleX += 0.2 * upwardPose;
////                lowerArm2.rotateAngleX += 0.2 * upwardPose;
////
////                upperLegR.rotateAngleX += 0.2 * upwardPose;
////                upperLegL.rotateAngleX -= 1 * upwardPose;
////                lowerLeg1.rotateAngleX += 0.3 * upwardPose;
////                lowerLeg2.rotateAngleX += 1.5 * upwardPose;
////
////                walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, par1, par2);
////                walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, par1, par2);
////                walk(lowerLeg1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, par1, par2);
////                walk(lowerLeg2, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, par1, par2);
////                waist.rotateAngleX -= 0.2 * downwardPose;
////                stomach.rotateAngleX += 0.3 * downwardPose;
////                chest.rotateAngleX += 0.3 * downwardPose;
////                head.rotateAngleX += 0.3 * downwardPose;
////                upperLegR.rotateAngleX -= 1.2 * downwardPose;
////                upperLegL.rotateAngleX -= 0.2 * downwardPose;
////                lowerLeg1.rotateAngleX += 2 * downwardPose;
////                lowerLeg2.rotateAngleX += 0.5 * downwardPose;
////                upperArmR.rotateAngleZ += 1 * downwardPose;
////                upperArmL.rotateAngleZ -= 1 * downwardPose;
////                lowerArm1.rotateAngleX -= 1 * downwardPose;
////                lowerArm2.rotateAngleX -= 1 * downwardPose;
////            }
////
////            int transformationTimer = transformer.getTransformationTimer();
////
////            float transformProgress = (float) (20 - transformationTimer) / 20;
////            float transformProgressSin = MathHelper.sin(1.57079632679F * transformProgress) * MathHelper.sin(1.57079632679F * transformProgress);
////
////            head.rotateAngleX += Math.PI * transformProgressSin;
////            waist.rotateAngleX += Math.PI / 2 * transformProgressSin;
////            waist.rotateAngleY = (float) (Math.PI * transformProgressSin);
////            waist.rotationPointY += 5 * transformProgressSin;
////            bipedBody.rotationPointZ = -12 * transformProgressSin;
////            tread1.rotateAngleX -= Math.PI * transformProgressSin;
////            tread2.rotateAngleX -= Math.PI * transformProgressSin;
////            tread1.rotationPointZ -= 7 * transformProgressSin;
////            tread2.rotationPointZ -= 7 * transformProgressSin;
////            shoulderPad1.rotateAngleX += Math.PI / 2 * transformProgressSin;
////            shoulderPad2.rotateAngleX += Math.PI / 2 * transformProgressSin;
////            shoulderPad1.rotationPointY -= 5 * transformProgressSin;
////            shoulderPad2.rotationPointY -= 5 * transformProgressSin;
////            upperArmR.rotationPointY += 5 * transformProgressSin;
////            upperArmL.rotationPointY += 5 * transformProgressSin;
////            backKibble.rotationPointZ -= 2 * transformProgressSin;
////            tread1.rotationPointZ += 2 * transformProgressSin;
////            tread2.rotationPointZ += 2 * transformProgressSin;
////            turret.rotationPointZ += 2 * transformProgressSin;
////            turret.rotateAngleX += 0.5 * transformProgressSin;
////            gun.rotateAngleX -= 0.5 * transformProgressSin;
////            upperLegR.rotateAngleX -= Math.PI * transformProgressSin;
////            upperLegL.rotateAngleX -= Math.PI * transformProgressSin;
////            lowerLeg1.rotateAngleX += Math.PI * transformProgressSin;
////            lowerLeg2.rotateAngleX += Math.PI * transformProgressSin;
////            hipPanel1.rotateAngleZ -= Math.PI / 2 * transformProgressSin;
////            hipPanel2.rotateAngleZ += Math.PI / 2 * transformProgressSin;
////
////            if (transformationTimer == 0)
////            {
////                float xRotation = par5 / (180F / PI);
////
////                vehicleBody.rotateAngleY = bipedBody.rotateAngleY;
////                vehicleGun.rotateAngleX = par5 < 0 ? xRotation : 0;
////                vehicleTurret.rotateAngleY = par4 / (180F / PI);
////            }
////            else
////            {
////                if (transformer.getHeldItem() != null && transformer.getHeldItem().getItem() == TFItems.purgesKatana)
////                {
////                    tread1.showModel = false;
////                }
////                else
////                {
////                    tread1.showModel = true;
////                }
////            }
////        }
////    }
////}
