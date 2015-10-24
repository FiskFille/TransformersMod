package fiskfille.tf.client.model.transformer;

import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSkystrike extends ModelTransformerBase
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
    public MowzieModelRenderer wingL1biped;
    public MowzieModelRenderer intakeL;
    public MowzieModelRenderer upperArmL;
    public MowzieModelRenderer wingL2biped;
    public MowzieModelRenderer lowerArmL;
    public MowzieModelRenderer lowerarmL2;
    public MowzieModelRenderer lowerarmL3;
    public MowzieModelRenderer lowerarmL4;
    public MowzieModelRenderer fistL;
    public MowzieModelRenderer wingR1biped;
    public MowzieModelRenderer intakeR1;
    public MowzieModelRenderer upperArmR;
    public MowzieModelRenderer wingR2biped;
    public MowzieModelRenderer lowerArmR;
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
        textureWidth = 128;
        textureHeight = 128;

        textureWidth = 128;
        textureHeight = 128;
        feetconnectorR = new MowzieModelRenderer(this, 62, 74);
        feetconnectorR.mirror = true;
        feetconnectorR.setRotationPoint(0.0F, 7.0F, -1.0F);
        feetconnectorR.addBox(-1.3F, -1.2F, -1.5F, 2, 4, 2, 0.0F);
        this.setRotation(feetconnectorR, 0.2897246558310587F, 0.04363323129985824F, 0.0F);
        bodywingL2 = new MowzieModelRenderer(this, 7, 35);
        bodywingL2.setRotationPoint(0.0F, 0.0F, 5.0F);
        bodywingL2.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
        this.setRotation(bodywingL2, -1.1519173063162573F, 0.03490658503988659F, 0.003490658503988659F);
        middlelegL = new MowzieModelRenderer(this, 75, 49);
        middlelegL.mirror = true;
        middlelegL.setRotationPoint(1.4F, 2.5F, 0.9F);
        middlelegL.addBox(-1.0F, -0.1F, 0.5F, 2, 2, 4, 0.0F);
        this.setRotation(middlelegL, -0.08831366015091306F, -0.06981317007977318F, 0.0F);
        toeL2 = new MowzieModelRenderer(this, 73, 76);
        toeL2.mirror = true;
        toeL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        toeL2.addBox(-1.8F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
        this.setRotation(toeL2, 0.027925268031909273F, -0.11344640137963141F, 0.02617993877991494F);
        lowerArmL = new MowzieModelRenderer(this, 40, 87);
        lowerArmL.setRotationPoint(0.0F, 3.0F, 0.0F);
        lowerArmL.addBox(-1.0F, 1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotation(lowerArmL, -0.19198621771937624F, 0.06981317007977318F, 0.05235987755982988F);
        feetbaseR = new MowzieModelRenderer(this, 62, 68);
        feetbaseR.setRotationPoint(0.0F, 0.0F, -0.3F);
        feetbaseR.addBox(-1.8F, 1.0F, -1.8F, 3, 3, 3, 0.0F);
        this.setRotation(feetbaseR, 0.6108652381980153F, 0.0F, 0.0F);
        wingR1biped = new MowzieModelRenderer(this, 36, 60);
        wingR1biped.setRotationPoint(-4.0F, -3.0F, -1.8F);
        wingR1biped.addBox(-6.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
        this.setRotation(wingR1biped, 0.017453292519943295F, 0.07853981633974483F, 0.5061454830783556F);
        buttflapL = new MowzieModelRenderer(this, 36, 108);
        buttflapL.setRotationPoint(1.0F, -1.0F, 2.0F);
        buttflapL.addBox(-0.4F, 0.0F, -0.7F, 3, 4, 1, 0.0F);
        this.setRotation(buttflapL, 0.17453292519943295F, 0.12217304763960307F, -0.15707963267948966F);
        energyUP = new MowzieModelRenderer(this, 12, 93);
        energyUP.setRotationPoint(0.0F, 0.0F, 0.0F);
        energyUP.addBox(-2.0F, 0.2F, -3.3F, 4, 2, 3, 0.0F);
        this.setRotation(energyUP, 0.9826203688728075F, 0.0F, 0.0F);
        buttflapR = new MowzieModelRenderer(this, 36, 108);
        buttflapR.mirror = true;
        buttflapR.setRotationPoint(-1.0F, -1.0F, 2.0F);
        buttflapR.addBox(-2.6F, 0.0F, -0.7F, 3, 4, 1, 0.0F);
        this.setRotation(buttflapR, 0.17453292519943295F, -0.12217304763960307F, 0.15707963267948966F);
        lowerchestM = new MowzieModelRenderer(this, 0, 97);
        lowerchestM.setRotationPoint(0.0F, 7.0F, 3.0F);
        lowerchestM.addBox(-1.5F, 0.3F, -5.8F, 3, 2, 4, 0.0F);
        this.setRotation(lowerchestM, -0.9948376736367678F, 0.0F, 0.0F);
        headintakeR2 = new MowzieModelRenderer(this, 16, 117);
        headintakeR2.setRotationPoint(0.0F, 0.0F, 1.0F);
        headintakeR2.addBox(-1.2F, -1.0F, -3.6F, 1, 2, 1, 0.0F);
        this.setRotation(headintakeR2, 0.061086523819801536F, 0.3141592653589793F, 0.0F);
        bodywingR2 = new MowzieModelRenderer(this, 7, 35);
        bodywingR2.mirror = true;
        bodywingR2.setRotationPoint(0.0F, 0.0F, 5.0F);
        bodywingR2.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
        this.setRotation(bodywingR2, -1.1519173063162573F, -0.03490658503988659F, -0.003490658503988659F);
        headnose = new MowzieModelRenderer(this, 0, 121);
        headnose.setRotationPoint(0.0F, -1.0F, 0.3F);
        headnose.addBox(-0.5F, -1.1F, -2.7F, 1, 2, 1, 0.0F);
        this.setRotation(headnose, -0.0890117918517108F, 0.0F, 0.0F);
        upperchestL = new MowzieModelRenderer(this, 12, 76);
        upperchestL.setRotationPoint(4.0F, 0.0F, 0.0F);
        upperchestL.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotation(upperchestL, 0.9948376736367678F, 0.0F, 0.0F);
        crotchfront = new MowzieModelRenderer(this, 46, 102);
        crotchfront.setRotationPoint(-1.0F, -1.0F, -4.0F);
        crotchfront.addBox(0.0F, 0.3F, 0.4F, 2, 5, 2, 0.0F);
        this.setRotation(crotchfront, 0.12217304763960307F, 0.0F, 0.0F);
        wheelL = new MowzieModelRenderer(this, 75, 55);
        wheelL.mirror = true;
        wheelL.setRotationPoint(0.0F, 0.0F, 0.0F);
        wheelL.addBox(-1.0F, 1.0F, 1.5F, 2, 3, 3, 0.0F);
        this.setRotation(wheelL, 0.5864306286700948F, 0.0F, 0.0F);
        toeR1 = new MowzieModelRenderer(this, 73, 76);
        toeR1.mirror = true;
        toeR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        toeR1.addBox(-2.2F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
        this.setRotation(toeR1, 0.027925268031909273F, 0.11344640137963141F, -0.008726646259971648F);
        rearstomach = new MowzieModelRenderer(this, 0, 76);
        rearstomach.setRotationPoint(0.0F, -8.6F, 2.9F);
        rearstomach.addBox(-2.0F, -0.7F, -0.4F, 4, 6, 2, 0.0F);
        this.setRotation(rearstomach, -0.46774823953448036F, 0.0F, 0.0F);
        collarL = new MowzieModelRenderer(this, 0, 103);
        collarL.setRotationPoint(4.0F, -1.5F, 3.0F);
        collarL.addBox(-1.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        this.setRotation(collarL, -0.0471238898038469F, -0.296705972839036F, 0.03490658503988659F);
        shoulderL = new MowzieModelRenderer(this, 36, 51);
        shoulderL.setRotationPoint(4.0F, 2.5F, 4.0F);
        shoulderL.addBox(0.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
        bodywingL1 = new MowzieModelRenderer(this, 0, 55);
        bodywingL1.setRotationPoint(3.0F, 0.2F, 6.0F);
        bodywingL1.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
        this.setRotation(bodywingL1, 0.0F, 0.9250245035569946F, 0.2792526803190927F);
        energyDOWN = new MowzieModelRenderer(this, 0, 49);
        energyDOWN.setRotationPoint(0.0F, 7.1F, 3.0F);
        energyDOWN.addBox(-2.0F, -0.4F, -6.0F, 4, 2, 4, 0.0F);
        this.setRotation(energyDOWN, -1.0018189906447452F, 0.0F, 0.0F);
        collarM = new MowzieModelRenderer(this, 10, 98);
        collarM.setRotationPoint(-3.0F, -1.4F, 4.9F);
        collarM.addBox(0.0F, -0.02F, 0.0F, 6, 2, 1, 0.0F);
        this.setRotation(collarM, -0.03490658503988659F, 0.0F, 0.0F);
        chestcenter = new MowzieModelRenderer(this, 0, 0);
        chestcenter.setRotationPoint(0.0F, -10.0F, 2.0F);
        chestcenter.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotation(chestcenter, -0.10646508437165408F, 0.0F, 0.0F);
        cockpit1 = new MowzieModelRenderer(this, 0, 84);
        cockpit1.setRotationPoint(0.0F, 0.0F, 7.0F);
        cockpit1.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 2, 0.0F);
        this.setRotation(cockpit1, -0.5585053606381855F, 0.0F, 0.0017453292519943296F);
        collarR = new MowzieModelRenderer(this, 0, 103);
        collarR.mirror = true;
        collarR.setRotationPoint(-4.0F, -1.5F, 3.0F);
        collarR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
        this.setRotation(collarR, -0.0471238898038469F, 0.296705972839036F, -0.03490658503988659F);
        feetpartR1 = new MowzieModelRenderer(this, 63, 80);
        feetpartR1.setRotationPoint(0.0F, 0.0F, 0.0F);
        feetpartR1.addBox(-1.7F, 1.0F, -3.0F, 3, 2, 1, 0.0F);
        this.setRotation(feetpartR1, -0.0767944870877505F, 0.11344640137963141F, -0.017453292519943295F);
        wingL2biped = new MowzieModelRenderer(this, 36, 65);
        wingL2biped.mirror = true;
        wingL2biped.setRotationPoint(-2.0F, -1.0F, 4.2F);
        wingL2biped.addBox(2.0F, 0.1F, -0.2F, 6, 1, 2, 0.0F);
        this.setRotation(wingL2biped, -0.3665191429188092F, 0.0F, 0.0F);
        wheelconnectorL = new MowzieModelRenderer(this, 71, 64);
        wheelconnectorL.setRotationPoint(0.0F, 0.0F, 0.0F);
        wheelconnectorL.addBox(-1.3F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotation(wheelconnectorL, 0.03490658503988659F, 0.0F, 0.0F);
        lowerarmL3 = new MowzieModelRenderer(this, 36, 76);
        lowerarmL3.mirror = true;
        lowerarmL3.setRotationPoint(1.0F, 5.8F, 0.3F);
        lowerarmL3.addBox(0.0F, -5.2F, -2.0F, 1, 7, 4, 0.0F);
        this.setRotation(lowerarmL3, -0.03490658503988659F, -0.12566370614359174F, 0.04363323129985824F);
        intakeL = new MowzieModelRenderer(this, 36, 68);
        intakeL.mirror = true;
        intakeL.setRotationPoint(4.0F, -3.0F, -0.5F);
        intakeL.addBox(-1.0F, 0.0F, -2.0F, 1, 4, 4, 0.0F);
        this.setRotation(intakeL, 0.0F, 0.0F, -0.22689280275926282F);
        feetpartR2 = new MowzieModelRenderer(this, 74, 69);
        feetpartR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        feetpartR2.addBox(-2.2F, 1.0F, -5.0F, 4, 1, 3, 0.0F);
        this.setRotation(feetpartR2, 0.4625122517784973F, 0.11344640137963141F, -0.017453292519943295F);
        headbase = new MowzieModelRenderer(this, 0, 120);
        headbase.setRotationPoint(0.0F, -2.0F, 2.5F);
        headbase.addBox(-2.0F, -3.0F, -2.0F, 4, 4, 4, 0.0F);
        middlelegR = new MowzieModelRenderer(this, 75, 49);
        middlelegR.setRotationPoint(-1.6F, 2.5F, 0.9F);
        middlelegR.addBox(-1.0F, -0.1F, 0.5F, 2, 2, 4, 0.0F);
        this.setRotation(middlelegR, -0.08831366015091306F, 0.06981317007977318F, 0.0F);
        feetconnectorL = new MowzieModelRenderer(this, 62, 74);
        feetconnectorL.mirror = true;
        feetconnectorL.setRotationPoint(0.0F, 7.0F, -1.0F);
        feetconnectorL.addBox(-0.7F, -1.2F, -1.5F, 2, 4, 2, 0.0F);
        this.setRotation(feetconnectorL, 0.2897246558310587F, -0.04363323129985824F, 0.0F);
        lowerchestR = new MowzieModelRenderer(this, 0, 90);
        lowerchestR.setRotationPoint(-4.0F, 7.0F, 3.0F);
        lowerchestR.addBox(0.0F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
        this.setRotation(lowerchestR, -0.9948376736367678F, 0.0F, 0.0F);
        upperchestM = new MowzieModelRenderer(this, 8, 87);
        upperchestM.setRotationPoint(0.0F, 0.0F, 0.0F);
        upperchestM.addBox(-1.5F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
        this.setRotation(upperchestM, 0.9948376736367678F, 0.0F, 0.0F);
        toeR2 = new MowzieModelRenderer(this, 73, 76);
        toeR2.mirror = true;
        toeR2.setRotationPoint(0.0F, 0.0F, 0.0F);
        toeR2.addBox(0.8F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
        this.setRotation(toeR2, 0.027925268031909273F, 0.11344640137963141F, -0.02617993877991494F);
        lowerArmR = new MowzieModelRenderer(this, 40, 87);
        lowerArmR.mirror = true;
        lowerArmR.setRotationPoint(0.0F, 3.0F, 0.0F);
        lowerArmR.addBox(-1.0F, 1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotation(lowerArmR, -0.19198621771937624F, -0.05235987755982988F, -0.06981317007977318F);
        headhelmet2 = new MowzieModelRenderer(this, 13, 114);
        headhelmet2.setRotationPoint(0.0F, 0.0F, 0.0F);
        headhelmet2.addBox(-2.5F, -3.17F, -3.1F, 5, 1, 2, 0.0F);
        this.setRotation(headhelmet2, 0.061086523819801536F, 0.0F, 0.0F);
        lowerLegL = new MowzieModelRenderer(this, 63, 58);
        lowerLegL.mirror = true;
        lowerLegL.setRotationPoint(0.0F, -0.9F, 5.0F);
        lowerLegL.addBox(-1.3F, -0.2F, -1.1F, 3, 7, 3, 0.0F);
        this.setRotation(lowerLegL, -0.1780235837034216F, 0.0F, 0.0F);
        legarmorR = new MowzieModelRenderer(this, 73, 73);
        legarmorR.mirror = true;
        legarmorR.setRotationPoint(0.0F, 0.0F, 2.0F);
        legarmorR.addBox(-1.0F, -1.3F, -0.2F, 2, 8, 1, 0.0F);
        shoulderR = new MowzieModelRenderer(this, 36, 51);
        shoulderR.mirror = true;
        shoulderR.setRotationPoint(-4.0F, 2.5F, 4.0F);
        shoulderR.addBox(-4.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
        waist = new MowzieModelRenderer(this, 36, 95);
        waist.setRotationPoint(0.0F, 8.7F, 3.4F);
        waist.addBox(-3.5F, -1.0F, -3.0F, 7, 2, 5, 0.0F);
        crotchBack = new MowzieModelRenderer(this, 44, 109);
        crotchBack.setRotationPoint(-1.0F, 1.0F, 1.0F);
        crotchBack.addBox(0.0F, -0.3F, -1.2F, 2, 3, 2, 0.0F);
        this.setRotation(crotchBack, -0.28797932657906433F, 0.0F, 0.0F);
        feetbaseL = new MowzieModelRenderer(this, 62, 68);
        feetbaseL.setRotationPoint(0.0F, 0.0F, -0.3F);
        feetbaseL.addBox(-1.2F, 1.0F, -1.8F, 3, 3, 3, 0.0F);
        this.setRotation(feetbaseL, 0.6108652381980153F, 0.0F, 0.0F);
        LowerchestL = new MowzieModelRenderer(this, 0, 90);
        LowerchestL.mirror = true;
        LowerchestL.setRotationPoint(4.0F, 7.0F, 3.0F);
        LowerchestL.addBox(-2.0F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
        this.setRotation(LowerchestL, -0.9948376736367678F, 0.0F, 0.0F);
        bodywingR1 = new MowzieModelRenderer(this, 0, 55);
        bodywingR1.mirror = true;
        bodywingR1.setRotationPoint(-3.0F, 0.2F, 6.0F);
        bodywingR1.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
        this.setRotation(bodywingR1, 0.0F, -0.9250245035569946F, -0.2792526803190927F);
        headhelmet1 = new MowzieModelRenderer(this, 0, 114);
        headhelmet1.setRotationPoint(0.0F, 0.0F, 0.0F);
        headhelmet1.addBox(-2.5F, -3.1F, -1.3F, 5, 3, 3, 0.0F);
        wingR2biped = new MowzieModelRenderer(this, 36, 65);
        wingR2biped.setRotationPoint(2.0F, -1.0F, 4.2F);
        wingR2biped.addBox(-8.0F, 0.1F, -0.2F, 6, 1, 2, 0.0F);
        this.setRotation(wingR2biped, -0.3665191429188092F, 0.0F, 0.0F);
        feetpartL1 = new MowzieModelRenderer(this, 63, 80);
        feetpartL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        feetpartL1.addBox(-1.3F, 1.0F, -3.0F, 3, 2, 1, 0.0F);
        this.setRotation(feetpartL1, -0.0767944870877505F, -0.11344640137963141F, 0.017453292519943295F);
        fistR = new MowzieModelRenderer(this, 46, 78);
        fistR.mirror = true;
        fistR.setRotationPoint(0.0F, 4.0F, 1.0F);
        fistR.addBox(-0.8F, -0.4F, -2.0F, 2, 2, 3, 0.0F);
        this.setRotation(fistR, 0.05235987755982988F, 0.017453292519943295F, -0.2792526803190927F);
        crotch1 = new MowzieModelRenderer(this, 36, 102);
        crotch1.setRotationPoint(-1.0F, 1.0F, -2.0F);
        crotch1.addBox(0.0F, 0.0F, 0.0F, 2, 3, 3, 0.0F);
        cockpit2 = new MowzieModelRenderer(this, 10, 82);
        cockpit2.mirror = true;
        cockpit2.setRotationPoint(0.0F, -0.5F, 5.8F);
        cockpit2.addBox(-1.0F, 0.2F, -0.6F, 2, 2, 3, 0.0F);
        this.setRotation(cockpit2, 0.24434609527920614F, 0.0F, 0.0F);
        headwingL = new MowzieModelRenderer(this, 12, 120);
        headwingL.setRotationPoint(0.0F, 0.0F, 0.5F);
        headwingL.addBox(1.5F, -3.1F, 1.2F, 1, 2, 2, 0.0F);
        lowerarmR3 = new MowzieModelRenderer(this, 36, 76);
        lowerarmR3.setRotationPoint(-3.0F, 5.8F, 0.3F);
        lowerarmR3.addBox(1.0F, -5.2F, -2.0F, 1, 7, 4, 0.0F);
        this.setRotation(lowerarmR3, -0.03490658503988659F, 0.12566370614359174F, -0.06335545184739416F);
        intakeR1 = new MowzieModelRenderer(this, 36, 68);
        intakeR1.setRotationPoint(-4.0F, -3.0F, -0.5F);
        intakeR1.addBox(0.0F, 0.0F, -2.0F, 1, 4, 4, 0.0F);
        this.setRotation(intakeR1, 0.0F, 0.0F, 0.22689280275926282F);
        upperLegL = new MowzieModelRenderer(this, 63, 49);
        upperLegL.mirror = true;
        upperLegL.setRotationPoint(2.0F, 0.5F, 1.1F);
        upperLegL.addBox(0.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotation(upperLegL, -0.6457718232379019F, 0.0F, -0.08726646259971647F);
        neck = new MowzieModelRenderer(this, 23, 67);
        neck.setRotationPoint(-1.0F, -2.0F, 1.5F);
        neck.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        upperchestR = new MowzieModelRenderer(this, 12, 76);
        upperchestR.mirror = true;
        upperchestR.setRotationPoint(-4.0F, 0.0F, 0.0F);
        upperchestR.addBox(0.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotation(upperchestR, 0.9948376736367678F, 0.0F, 0.0F);
        wheelconnectorR = new MowzieModelRenderer(this, 71, 64);
        wheelconnectorR.setRotationPoint(0.0F, 0.0F, 0.0F);
        wheelconnectorR.addBox(-1.7F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotation(wheelconnectorR, 0.03490658503988659F, 0.0F, 0.0F);
        headwingR = new MowzieModelRenderer(this, 12, 120);
        headwingR.setRotationPoint(0.0F, 0.0F, 0.5F);
        headwingR.addBox(-2.5F, -3.1F, 1.2F, 1, 2, 2, 0.0F);
        lowerLegR = new MowzieModelRenderer(this, 63, 58);
        lowerLegR.setRotationPoint(0.0F, -0.9F, 5.0F);
        lowerLegR.addBox(-1.7F, -0.2F, -1.1F, 3, 7, 3, 0.0F);
        this.setRotation(lowerLegR, -0.1780235837034216F, 0.0F, 0.0F);
        headintakeL2 = new MowzieModelRenderer(this, 16, 117);
        headintakeL2.mirror = true;
        headintakeL2.setRotationPoint(0.0F, 0.0F, 1.0F);
        headintakeL2.addBox(0.2F, -1.0F, -3.6F, 1, 2, 1, 0.0F);
        this.setRotation(headintakeL2, 0.061086523819801536F, -0.3141592653589793F, 0.0F);
        lowerarmR4 = new MowzieModelRenderer(this, 43, 74);
        lowerarmR4.setRotationPoint(0.0F, 0.0F, 0.0F);
        lowerarmR4.addBox(-1.5F, 0.2F, -1.5F, 3, 1, 3, 0.0F);
        headintakeL1 = new MowzieModelRenderer(this, 16, 124);
        headintakeL1.mirror = true;
        headintakeL1.setRotationPoint(0.0F, 0.0F, 0.9F);
        headintakeL1.addBox(1.2F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
        this.setRotation(headintakeL1, 0.06981317007977318F, -0.022689280275926284F, 0.0017453292519943296F);
        lowerarmL4 = new MowzieModelRenderer(this, 43, 74);
        lowerarmL4.setRotationPoint(0.0F, 0.0F, 0.0F);
        lowerarmL4.addBox(-1.5F, 0.2F, -1.5F, 3, 1, 3, 0.0F);
        toeL1 = new MowzieModelRenderer(this, 73, 76);
        toeL1.mirror = true;
        toeL1.setRotationPoint(0.0F, 0.0F, 0.0F);
        toeL1.addBox(1.2F, 2.9F, -4.6F, 1, 1, 6, 0.0F);
        this.setRotation(toeL1, 0.027925268031909273F, -0.11344640137963141F, 0.008726646259971648F);
        fistL = new MowzieModelRenderer(this, 46, 78);
        fistL.setRotationPoint(0.0F, 4.0F, 1.0F);
        fistL.addBox(-1.2F, -0.4F, -2.0F, 2, 2, 3, 0.0F);
        this.setRotation(fistL, 0.05235987755982988F, -0.017453292519943295F, 0.3141592653589793F);
        legarmorL = new MowzieModelRenderer(this, 73, 73);
        legarmorL.setRotationPoint(0.0F, 0.0F, 2.0F);
        legarmorL.addBox(-1.0F, -1.3F, -0.2F, 2, 8, 1, 0.0F);
        feetpartL2 = new MowzieModelRenderer(this, 74, 69);
        feetpartL2.setRotationPoint(0.0F, 0.0F, 0.0F);
        feetpartL2.addBox(-1.8F, 1.0F, -5.0F, 4, 1, 3, 0.0F);
        this.setRotation(feetpartL2, 0.4625122517784973F, -0.11344640137963141F, 0.017453292519943295F);
        lowerarmL2 = new MowzieModelRenderer(this, 46, 83);
        lowerarmL2.setRotationPoint(0.0F, 3.0F, -1.0F);
        lowerarmL2.addBox(-1.3F, 2.0F, -0.5F, 3, 2, 3, 0.0F);
        upperLegR = new MowzieModelRenderer(this, 63, 49);
        upperLegR.setRotationPoint(0.0F, 0.5F, 1.1F);
        upperLegR.addBox(-3.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotation(upperLegR, -0.6457718232379019F, 0.0F, 0.08726646259971647F);
        wheelR = new MowzieModelRenderer(this, 75, 55);
        wheelR.mirror = true;
        wheelR.setRotationPoint(0.0F, 0.0F, 0.0F);
        wheelR.addBox(-1.0F, 1.0F, 1.5F, 2, 3, 3, 0.0F);
        this.setRotation(wheelR, 0.5864306286700948F, 0.0F, 0.0F);
        headintakeR1 = new MowzieModelRenderer(this, 16, 124);
        headintakeR1.setRotationPoint(0.0F, 0.0F, 0.9F);
        headintakeR1.addBox(-2.2F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
        this.setRotation(headintakeR1, 0.06981317007977318F, 0.029670597283903602F, -0.0017453292519943296F);
        wingL1biped = new MowzieModelRenderer(this, 36, 60);
        wingL1biped.mirror = true;
        wingL1biped.setRotationPoint(4.0F, -3.0F, -1.8F);
        wingL1biped.addBox(-2.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
        this.setRotation(wingL1biped, 0.017453292519943295F, -0.07853981633974483F, -0.5061454830783556F);
        chest1 = new MowzieModelRenderer(this, 0, 64);
        chest1.setRotationPoint(0.0F, -1.9F, -3.8F);
        chest1.addBox(-4.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
        stomach = new MowzieModelRenderer(this, 12, 53);
        stomach.setRotationPoint(0.0F, -0.8F, -0.6F);
        stomach.addBox(-3.0F, -7.0F, -2.0F, 6, 7, 4, 0.0F);
        this.setRotation(stomach, 0.10646508437165408F, 0.0F, 0.0F);
        lowerarmR2 = new MowzieModelRenderer(this, 46, 83);
        lowerarmR2.mirror = true;
        lowerarmR2.setRotationPoint(0.0F, 3.0F, -1.0F);
        lowerarmR2.addBox(-1.7F, 2.0F, -0.5F, 3, 2, 3, 0.0F);
        upperArmL = new MowzieModelRenderer(this, 46, 68);
        upperArmL.mirror = true;
        upperArmL.setRotationPoint(2.5F, 0.6F, -0.5F);
        upperArmL.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotation(upperArmL, 0.017453292519943295F, 0.017453292519943295F, -0.05235987755982988F);
        upperArmR = new MowzieModelRenderer(this, 46, 68);
        upperArmR.setRotationPoint(-2.5F, 0.6F, -0.5F);
        upperArmR.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotation(upperArmR, 0.017453292519943295F, -0.017453292519943295F, 0.05235987755982988F);
        legarmorR.addChild(feetconnectorR);
        bodywingL1.addChild(bodywingL2);
        upperLegL.addChild(middlelegL);
        feetbaseL.addChild(toeL2);
        upperArmL.addChild(lowerArmL);
        feetconnectorR.addChild(feetbaseR);
        shoulderR.addChild(wingR1biped);
        waist.addChild(buttflapL);
        chest1.addChild(energyUP);
        waist.addChild(buttflapR);
        chest1.addChild(lowerchestM);
        headbase.addChild(headintakeR2);
        bodywingR1.addChild(bodywingR2);
        headbase.addChild(headnose);
        chest1.addChild(upperchestL);
        waist.addChild(crotchfront);
        wheelconnectorL.addChild(wheelL);
        feetbaseR.addChild(toeR1);
        stomach.addChild(rearstomach);
        chest1.addChild(collarL);
        chest1.addChild(shoulderL);
        chest1.addChild(bodywingL1);
        chest1.addChild(energyDOWN);
        chest1.addChild(collarM);
        stomach.addChild(chestcenter);
        chest1.addChild(cockpit1);
        chest1.addChild(collarR);
        feetbaseR.addChild(feetpartR1);
        wingL1biped.addChild(wingL2biped);
        feetconnectorL.addChild(wheelconnectorL);
        lowerArmL.addChild(lowerarmL3);
        shoulderL.addChild(intakeL);
        feetbaseR.addChild(feetpartR2);
        chest1.addChild(headbase);
        upperLegR.addChild(middlelegR);
        legarmorL.addChild(feetconnectorL);
        chest1.addChild(lowerchestR);
        chest1.addChild(upperchestM);
        feetbaseR.addChild(toeR2);
        upperArmR.addChild(lowerArmR);
        headbase.addChild(headhelmet2);
        middlelegL.addChild(lowerLegL);
        lowerLegR.addChild(legarmorR);
        chest1.addChild(shoulderR);
        waist.addChild(crotchBack);
        feetconnectorL.addChild(feetbaseL);
        chest1.addChild(LowerchestL);
        chest1.addChild(bodywingR1);
        headbase.addChild(headhelmet1);
        wingR1biped.addChild(wingR2biped);
        feetbaseL.addChild(feetpartL1);
        lowerarmR2.addChild(fistR);
        waist.addChild(crotch1);
        chest1.addChild(cockpit2);
        headbase.addChild(headwingL);
        lowerArmR.addChild(lowerarmR3);
        shoulderR.addChild(intakeR1);
        crotch1.addChild(upperLegL);
        chest1.addChild(neck);
        chest1.addChild(upperchestR);
        feetconnectorR.addChild(wheelconnectorR);
        headbase.addChild(headwingR);
        middlelegR.addChild(lowerLegR);
        headbase.addChild(headintakeL2);
        lowerArmR.addChild(lowerarmR4);
        headbase.addChild(headintakeL1);
        lowerArmL.addChild(lowerarmL4);
        feetbaseL.addChild(toeL1);
        lowerarmL2.addChild(fistL);
        lowerLegL.addChild(legarmorL);
        feetbaseL.addChild(feetpartL2);
        lowerArmL.addChild(lowerarmL2);
        crotch1.addChild(upperLegR);
        wheelconnectorR.addChild(wheelR);
        headbase.addChild(headintakeR1);
        shoulderL.addChild(wingL1biped);
        chestcenter.addChild(chest1);
        waist.addChild(stomach);
        lowerArmR.addChild(lowerarmR2);
        shoulderL.addChild(upperArmL);
        shoulderR.addChild(upperArmR);

        mainbody14 = new ModelRenderer(this, 0, 15);
        mainbody14.setRotationPoint(-3.5F, 0.0F, 7.0F);
        mainbody14.addBox(0.0F, 0.0F, 0.0F, 7, 6, 6, 0.0F);
        wingL1 = new ModelRenderer(this, 36, 60);
        wingL1.mirror = true;
        wingL1.setRotationPoint(10.0F, 6.0F, -1.8F);
        wingL1.addBox(-7.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
        this.setRotation(wingL1, -1.5707963267948966F, -0.07853981633974483F, 0.5061454830783556F);
        cockpit = new ModelRenderer(this, 0, 84);
        cockpit.setRotationPoint(-1.5F, 4.0F, -9.0F);
        cockpit.addBox(0.0F, -0.2F, -0.6F, 3, 4, 2, 0.0F);
        this.setRotation(cockpit, 1.879719604397893F, 0.0F, 0.0F);
        front1 = new ModelRenderer(this, 0, 28);
        front1.setRotationPoint(-3.5F, 1.5F, -2.0F);
        front1.addBox(0.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
        this.setRotation(front1, 0.06981317007977318F, -0.13962634015954636F, 0.0F);
        mainbody13 = new ModelRenderer(this, 0, 7);
        mainbody13.setRotationPoint(0.0F, 8.5F, 2.0F);
        mainbody13.addBox(-7.5F, -3.0F, 0.0F, 15, 1, 7, 0.0F);
        cannon1 = new ModelRenderer(this, 73, 76);
        cannon1.mirror = true;
        cannon1.setRotationPoint(0.5F, 9.5F, 3.9F);
        cannon1.addBox(-6.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
        this.setRotation(cannon1, 1.5707963267948966F, 0.0F, 0.0F);
        engineR2 = new ModelRenderer(this, 36, 68);
        engineR2.setRotationPoint(0.0F, -3.0F, -2.5F);
        engineR2.addBox(-1.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotation(engineR2, 0.0F, 0.0F, 1.3055062804917585F);
        mainbody7 = new ModelRenderer(this, 0, 90);
        mainbody7.setRotationPoint(-4.0F, 7.0F, 3.0F);
        mainbody7.addBox(0.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
        this.setRotation(mainbody7, -0.9948376736367678F, 0.0F, 0.0F);
        mainbody2 = new ModelRenderer(this, 0, 55);
        mainbody2.mirror = true;
        mainbody2.setRotationPoint(-2.5F, 1.5F, 9.0F);
        mainbody2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
        this.setRotation(mainbody2, 0.0F, -1.5707963267948966F, 0.0F);
        engineL2 = new ModelRenderer(this, 36, 68);
        engineL2.mirror = true;
        engineL2.setRotationPoint(0.0F, -3.0F, -2.5F);
        engineL2.addBox(0.0F, -0.2F, 0.0F, 1, 4, 4, 0.0F);
        this.setRotation(engineL2, 0.0F, 0.0F, -1.3055062804917585F);
        exhaust2 = new ModelRenderer(this, 55, 0);
        exhaust2.setRotationPoint(4.0F, 8.0F, 2.0F);
        exhaust2.addBox(-3.5F, 0.5F, 0.0F, 3, 4, 5, 0.0F);
        this.setRotation(exhaust2, -1.5707963267948966F, 0.0F, 0.0F);
        front3 = new ModelRenderer(this, 19, 15);
        front3.setRotationPoint(-2.0F, 2.8F, -13.0F);
        front3.addBox(0.0F, 0.0F, 0.0F, 4, 5, 21, 0.0F);
        rearR2 = new ModelRenderer(this, 63, 58);
        rearR2.setRotationPoint(0.0F, 5.5F, -1.5F);
        rearR2.addBox(-2.9F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.setRotation(rearR2, 0.2617993877991494F, 0.0F, 0.0F);
        mainbody10 = new ModelRenderer(this, 8, 87);
        mainbody10.setRotationPoint(-1.5F, 0.0F, 0.0F);
        mainbody10.addBox(0.0F, 0.0F, -4.0F, 3, 2, 4, 0.0F);
        this.setRotation(mainbody10, 0.9948376736367678F, 0.0F, 0.0F);
        cannon3 = new ModelRenderer(this, 73, 76);
        cannon3.mirror = true;
        cannon3.setRotationPoint(0.5F, 9.5F, 3.9F);
        cannon3.addBox(4.5F, 1.6F, -14.0F, 1, 1, 6, 0.0F);
        this.setRotation(cannon3, 1.5707963267948966F, 0.0F, 0.0F);
        engineR1 = new ModelRenderer(this, 36, 51);
        engineR1.mirror = true;
        engineR1.setRotationPoint(-3.5F, 3.5F, 1.0F);
        engineR1.addBox(-4.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
        this.setRotation(engineR1, 1.5707963267948966F, 0.0F, 0.0F);
        wingR1 = new ModelRenderer(this, 36, 65);
        wingR1.setRotationPoint(-11.0F, 11.6F, -1.5F);
        wingR1.addBox(3.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
        this.setRotation(wingR1, -1.5707963267948966F, 0.07853981633974483F, -0.5061454830783556F);
        mainbody4 = new ModelRenderer(this, 12, 53);
        mainbody4.setRotationPoint(-3.0F, 7.0F, 0.0F);
        mainbody4.addBox(0.0F, -0.1F, -0.1F, 6, 7, 4, 0.0F);
        this.setRotation(mainbody4, 1.5707963267948966F, 0.0F, 0.0F);
        bodyplate2 = new ModelRenderer(this, 73, 73);
        bodyplate2.setRotationPoint(1.3F, 14.7F, 5.6F);
        bodyplate2.addBox(0.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
        mainbody8 = new ModelRenderer(this, 0, 90);
        mainbody8.mirror = true;
        mainbody8.setRotationPoint(4.0F, 7.0F, 3.0F);
        mainbody8.addBox(-2.5F, -0.7F, -5.8F, 2, 3, 4, 0.0F);
        this.setRotation(mainbody8, -0.9948376736367678F, 0.0F, 0.0F);
        front2 = new ModelRenderer(this, 0, 28);
        front2.mirror = true;
        front2.setRotationPoint(3.5F, 1.5F, -2.0F);
        front2.addBox(-3.0F, 0.6F, -11.0F, 3, 5, 13, 0.0F);
        this.setRotation(front2, 0.06981317007977318F, 0.13962634015954636F, 0.0F);
        wingL2 = new ModelRenderer(this, 36, 65);
        wingL2.setRotationPoint(11.0F, 11.6F, -1.5F);
        wingL2.addBox(-9.0F, -1.0F, -0.4F, 6, 1, 2, 0.0F);
        this.setRotation(wingL2, -1.5707963267948966F, -0.07853981633974483F, 0.5061454830783556F);
        engineR3 = new ModelRenderer(this, 36, 76);
        engineR3.setRotationPoint(-2.0F, 1.0F, -2.0F);
        engineR3.addBox(-2.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotation(engineR3, 0.0F, -0.008726646259971648F, 0.0F);
        exhaust1 = new ModelRenderer(this, 55, 0);
        exhaust1.mirror = true;
        exhaust1.setRotationPoint(0.0F, 8.0F, 2.0F);
        exhaust1.addBox(-3.5F, 0.5F, 0.0F, 3, 4, 5, 0.0F);
        this.setRotation(exhaust1, -1.5707963267948966F, 0.0F, 0.0F);
        mainbody12 = new ModelRenderer(this, 0, 7);
        mainbody12.setRotationPoint(0.0F, 8.5F, 2.0F);
        mainbody12.addBox(-7.5F, -7.0F, 0.0F, 15, 1, 7, 0.0F);
        mainbody3 = new ModelRenderer(this, 0, 55);
        mainbody3.setRotationPoint(2.5F, 1.5F, 9.0F);
        mainbody3.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 5, 0.0F);
        this.setRotation(mainbody3, 0.0F, 1.5707963267948966F, 0.0F);
        bodyplate1 = new ModelRenderer(this, 73, 73);
        bodyplate1.mirror = true;
        bodyplate1.setRotationPoint(-1.3F, 14.7F, 5.6F);
        bodyplate1.addBox(-2.5F, -1.0F, -0.1F, 2, 8, 1, 0.0F);
        cannon2 = new ModelRenderer(this, 73, 76);
        cannon2.mirror = true;
        cannon2.setRotationPoint(0.5F, 9.5F, 3.9F);
        cannon2.addBox(-2.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotation(cannon2, 1.5707963267948966F, 0.0F, 0.0F);
        engineL1 = new ModelRenderer(this, 36, 51);
        engineL1.setRotationPoint(3.5F, 3.5F, 1.0F);
        engineL1.addBox(0.0F, -3.0F, -3.0F, 4, 4, 5, 0.0F);
        this.setRotation(engineL1, 1.5707963267948966F, 0.0F, 0.0F);
        wingR2 = new ModelRenderer(this, 36, 60);
        wingR2.setRotationPoint(-10.0F, 6.0F, -1.8F);
        wingR2.addBox(-1.0F, -1.0F, 0.0F, 8, 1, 4, 0.0F);
        this.setRotation(wingR2, -1.5707963267948966F, 0.07853981633974483F, -0.5061454830783556F);
        legL2 = new ModelRenderer(this, 63, 58);
        legL2.mirror = true;
        legL2.setRotationPoint(0.0F, 5.5F, -1.5F);
        legL2.addBox(-0.1F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.setRotation(legL2, 0.2617993877991494F, 0.0F, 0.0F);
        wheel2 = new ModelRenderer(this, 75, 55);
        wheel2.mirror = true;
        wheel2.setRotationPoint(4.5F, 13.5F, 3.9F);
        wheel2.addBox(0.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
        engineL3 = new ModelRenderer(this, 36, 76);
        engineL3.mirror = true;
        engineL3.setRotationPoint(2.0F, 1.0F, -2.0F);
        engineL3.addBox(1.0F, 0.0F, 0.0F, 1, 7, 4, 0.0F);
        this.setRotation(engineL3, 0.0F, 0.008726646259971648F, 0.0F);
        rearwing1 = new ModelRenderer(this, 0, 0);
        rearwing1.setRotationPoint(-3.0F, 5.0F, -1.4F);
        rearwing1.addBox(-5.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
        this.setRotation(rearwing1, 1.4311699866353502F, -0.6981317007977318F, 0.2792526803190927F);
        mainbody9 = new ModelRenderer(this, 12, 76);
        mainbody9.setRotationPoint(4.0F, 0.0F, 0.0F);
        mainbody9.addBox(-2.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotation(mainbody9, 0.9948376736367678F, 0.0F, 0.0F);
        mainbody11 = new ModelRenderer(this, 0, 97);
        mainbody11.setRotationPoint(-1.5F, 7.0F, 3.0F);
        mainbody11.addBox(0.0F, 0.3F, -5.8F, 3, 2, 4, 0.0F);
        this.setRotation(mainbody11, -0.9948376736367678F, 0.0F, 0.0F);
        mainbody1 = new ModelRenderer(this, 0, 64);
        mainbody1.setRotationPoint(0.0F, -5.0F, -2.0F);
        mainbody1.addBox(-4.0F, 0.0F, 0.0F, 8, 5, 7, 0.0F);
        mainbody5 = new ModelRenderer(this, 0, 76);
        mainbody5.setRotationPoint(-2.0F, 3.5F, -5.6F);
        mainbody5.addBox(0.0F, 0.3F, 0.0F, 4, 6, 2, 0.0F);
        this.setRotation(mainbody5, 1.8203784098300857F, 0.0F, 0.0F);
        rearramp1 = new ModelRenderer(this, 37, 0);
        rearramp1.setRotationPoint(0.0F, 3.0F, 19.5F);
        rearramp1.addBox(-2.0F, -3.0F, -7.3F, 4, 3, 10, 0.0F);
        this.setRotation(rearramp1, 0.41887902047863906F, 0.0F, 0.0F);
        rearR1 = new ModelRenderer(this, 63, 49);
        rearR1.setRotationPoint(0.0F, 0.5F, 22.0F);
        rearR1.addBox(-3.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotation(rearR1, -1.5707963267948966F, 0.0F, 0.0F);
        rearwing2 = new ModelRenderer(this, 0, 0);
        rearwing2.mirror = true;
        rearwing2.setRotationPoint(3.0F, 5.0F, -1.4F);
        rearwing2.addBox(-1.0F, 0.0F, 0.0F, 6, 1, 5, 0.0F);
        this.setRotation(rearwing2, 1.4311699866353502F, 0.7155849933176751F, -0.296705972839036F);
        legL1 = new ModelRenderer(this, 63, 49);
        legL1.mirror = true;
        legL1.setRotationPoint(0.0F, 0.5F, 22.0F);
        legL1.addBox(0.0F, -0.5F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotation(legL1, -1.5707963267948966F, 0.0F, 0.0F);
        cannon4 = new ModelRenderer(this, 73, 76);
        cannon4.mirror = true;
        cannon4.setRotationPoint(0.5F, 9.5F, 3.9F);
        cannon4.addBox(0.0F, 2.6F, -27.0F, 1, 1, 6, 0.0F);
        this.setRotation(cannon4, 1.5707963267948966F, 0.0F, 0.0F);
        wheel1 = new ModelRenderer(this, 75, 55);
        wheel1.mirror = true;
        wheel1.setRotationPoint(-4.5F, 13.5F, 3.9F);
        wheel1.addBox(-2.0F, 1.0F, 0.5F, 2, 3, 3, 0.0F);
        mainbody6 = new ModelRenderer(this, 12, 76);
        mainbody6.mirror = true;
        mainbody6.setRotationPoint(-4.0F, 0.0F, 0.0F);
        mainbody6.addBox(0.5F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotation(mainbody6, 0.9948376736367678F, 0.0F, 0.0F);
        mainbody1.addChild(mainbody14);
        engineL1.addChild(wingL1);
        mainbody1.addChild(cockpit);
        mainbody1.addChild(front1);
        mainbody1.addChild(mainbody13);
        rearR1.addChild(cannon1);
        engineR1.addChild(engineR2);
        mainbody1.addChild(mainbody7);
        mainbody1.addChild(mainbody2);
        engineL1.addChild(engineL2);
        engineL1.addChild(exhaust2);
        mainbody1.addChild(front3);
        rearR1.addChild(rearR2);
        mainbody1.addChild(mainbody10);
        legL1.addChild(cannon3);
        mainbody1.addChild(engineR1);
        engineR1.addChild(wingR1);
        mainbody1.addChild(mainbody4);
        legL1.addChild(bodyplate2);
        mainbody1.addChild(mainbody8);
        mainbody1.addChild(front2);
        engineL1.addChild(wingL2);
        engineR1.addChild(engineR3);
        engineR1.addChild(exhaust1);
        mainbody1.addChild(mainbody12);
        mainbody1.addChild(mainbody3);
        rearR1.addChild(bodyplate1);
        rearR1.addChild(cannon2);
        mainbody1.addChild(engineL1);
        engineR1.addChild(wingR2);
        legL1.addChild(legL2);
        legL1.addChild(wheel2);
        engineL1.addChild(engineL3);
        rearR1.addChild(rearwing1);
        mainbody1.addChild(mainbody9);
        mainbody1.addChild(mainbody11);
        mainbody1.addChild(mainbody5);
        mainbody1.addChild(rearramp1);
        mainbody1.addChild(rearR1);
        legL1.addChild(rearwing2);
        mainbody1.addChild(legL1);
        legL1.addChild(cannon4);
        rearR1.addChild(wheel1);
        mainbody1.addChild(mainbody6);

        setInitPose();
    }

    public Transformer getTransformer()
    {
        return TransformerManager.transformerSkystrike;
    }

    public ModelRenderer getWaist()
    {
        return waist;
    }

    public ModelRenderer getVehicle()
    {
        return mainbody1;
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
        return headbase;
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

            headbase.showModel = wearingHead;
            upperLegL.showModel = wearingLegs;
            upperLegR.showModel = wearingLegs;

            ModelOffset offsets = TFModelHelper.getOffsets(player);

            headbase.rotationPointX += offsets.headOffsetX;
            headbase.rotationPointY += offsets.headOffsetY;
            headbase.rotationPointZ += offsets.headOffsetZ;

            if (wearingHead)
            {
                faceTarget(headbase, 1, par4, par5);
            }

            if (!wearingChest)
            {
                upperLegL.rotationPointY += 10;
                upperLegR.rotationPointY += 10;
                upperLegL.rotationPointX -= 1;
                upperLegR.rotationPointX -= 1;
                upperLegR.rotationPointZ -= 1;
                upperLegL.rotationPointZ -= 1;

                headbase.rotationPointZ -= 2F;
                headbase.rotationPointY += 1F;
            }
            else
            {
                offsets.headOffsetY = -4F;
                offsets.headOffsetZ = 2.5F;

                headbase.rotationPointY += 4F;
                headbase.rotationPointZ -= 2.5F;

                if (!wearingLegs)
                {
                    waist.rotationPointY += 2.5F;
                    waist.rotationPointZ -= 2.5F;
                    offsets.headOffsetY += 2.5F;
                    offsets.headOffsetZ -= 2.5F;
                    headbase.rotationPointY -= 2.5F;
                    headbase.rotationPointZ += 2.5F;
                }
            }

            float globalSpeed = 1;
            float globalDegree = 0.8F;

            if (entity.isSneaking())
            {
                globalDegree = 1F;
                globalSpeed = 1.5F;
            }

            int backwardInverter = 1;

            if (player.moveForward < 0)
            {
                backwardInverter = -1;
                globalDegree = 0.5F;
            }

            applyDefaultHoldingAnimation(upperArmR, upperArmL, lowerArmR, lowerArmL);
            applyDefaultHittingAnimation(shoulderR, shoulderL, headbase, stomach, lowerArmR, lowerArmL);

            boolean sneaking = player.isSneaking();

            if (sneaking)
            {
                globalDegree = 1.5F;
                globalSpeed = 1.3F;
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
                upperArmR.rotateAngleY += -0.1F + headbase.rotateAngleY;
                upperArmL.rotateAngleY += 0.1F + headbase.rotateAngleY + 0.4F;
                upperArmR.rotateAngleX += -((float) Math.PI / 2F) + headbase.rotateAngleX;
                upperArmL.rotateAngleX += -((float) Math.PI / 2F) + headbase.rotateAngleX;
                upperArmR.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                upperArmL.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
                upperArmR.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
                upperArmL.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;

                upperArmR.rotateAngleX += 0.25F;
                upperArmL.rotateAngleX += 0.25F;
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
                    walk(middlelegL, 0.5F * globalSpeed, 1F * globalDegree, true, 1F * backwardInverter, 0F, par1, par2);
                    walk(lowerLegL, 0.5F * globalSpeed, 0.6F * globalDegree, false, 0F, 0F, par1, par2);
                    walk(feetbaseL, 0.5F * globalSpeed, 0.4F * globalDegree, true, 0.5F * backwardInverter, 0.3F, par1, par2);

                    walk(upperLegR, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, par1, par2);
                    walk(middlelegR, 0.5F * globalSpeed, 1F * globalDegree, false, 1F * backwardInverter, 0F, par1, par2);
                    walk(lowerLegR, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0F, 0F, par1, par2);
                    walk(feetbaseR, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0.5F * backwardInverter, 0.3F, par1, par2);

                    walk(shoulderL, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * par2 * backwardInverter, par1, par2);
                    walk(shoulderR, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * par2 * backwardInverter, par1, par2);
                    walk(lowerArmL, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * par2, par1, par2);
                    walk(lowerArmR, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * par2, par1, par2);

                    //Idle animation
                    int ticksExisted = entity.ticksExisted;

                    walk(stomach, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
                    walk(chest1, 0.08F, 0.05F, false, 1, 0, ticksExisted, 1F);
                    walk(headbase, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);
                    walk(upperArmR, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);
                    walk(upperArmL, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);

                    flap(upperArmR, 0.08F, 0.04F, true, 1, 0, ticksExisted, 1F);
                    flap(upperArmL, 0.08F, 0.04F, false, 1, 0, ticksExisted, 1F);
                    walk(lowerArmR, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
                    walk(lowerArmL, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);

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
                        lowerLegL.rotateAngleX -= 0.5;
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
                    double motionX = entity.posX - entity.prevPosX;
                    double motionZ = entity.posZ - entity.prevPosZ;

                    double motionY = entity.posY - entity.prevPosY;
                    float upwardPose = (float) (1 / (1 + Math.exp(-20 * (motionY + 0.2))));
                    float downwardPose = (float) (1 / (1 + Math.exp(10 * (motionY + 0.2))));

                    double speed = Math.sqrt(motionX * motionX + motionZ * motionZ) * 1.2D;

                    //                	waist.rotateAngleX -= 0.1 * par2 * backwardInverter;

                    chestcenter.rotateAngleX -= 0.2 * upwardPose;
                    headbase.rotateAngleX -= 0.2 * upwardPose;

                    shoulderR.rotateAngleX += 0.1 * upwardPose;
                    shoulderL.rotateAngleX += 0.1 * upwardPose;
                    shoulderR.rotateAngleZ -= 0.1 * upwardPose;
                    shoulderL.rotateAngleZ += 0.1 * upwardPose;
                    lowerArmR.rotateAngleX += 0.2 * upwardPose;
                    lowerArmL.rotateAngleX += 0.2 * upwardPose;

                    upperLegR.rotateAngleX -= 0.3 * upwardPose;
                    upperLegL.rotateAngleX += 0.2 * upwardPose;
                    middlelegR.rotateAngleX += 0.5 * upwardPose;
                    lowerLegR.rotateAngleX += 0.3 * upwardPose;
                    lowerLegL.rotateAngleX += 0.5 * upwardPose;

                    waist.rotateAngleX -= speed * downwardPose * 0.5;
                    buttflapL.rotateAngleX += speed * downwardPose + downwardPose / 2;
                    buttflapR.rotateAngleX += speed * downwardPose + downwardPose / 2;

                    shoulderR.rotateAngleZ += 1 * downwardPose;
                    shoulderL.rotateAngleZ -= 1 * downwardPose;
                    lowerArmR.rotateAngleX -= 1 * downwardPose;
                    lowerArmL.rotateAngleX -= 1 * downwardPose;
                    headbase.rotateAngleX += 0.2 * downwardPose;
                    headbase.rotateAngleX -= speed * downwardPose * 0.3F;

                    wingR1biped.rotateAngleZ -= downwardPose;
                    wingR1biped.rotationPointY += 1.25 * downwardPose;
                    wingL1biped.rotateAngleZ += downwardPose;
                    wingL1biped.rotationPointY += 1.25 * downwardPose;
                    bodywingR1.rotateAngleX += 1.5 * downwardPose;
                    bodywingR1.rotateAngleY += 1 * downwardPose;
                    bodywingR1.rotateAngleZ -= 1 * downwardPose;
                    bodywingR2.rotateAngleX += 0.75 * downwardPose;
                    bodywingL1.rotateAngleX += 1.5 * downwardPose;
                    bodywingL1.rotateAngleY -= 1 * downwardPose;
                    bodywingL1.rotateAngleZ += 1 * downwardPose;
                    bodywingL2.rotateAngleX += 0.75 * downwardPose;
                }
            }
            else
            {
                upperArmL.rotateAngleX += MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 / 2;
                upperArmR.rotateAngleX += MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 / 2;

                lowerArmL.rotateAngleX += MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 / 4;
                lowerArmR.rotateAngleX += MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 / 4;

                upperLegR.rotateAngleX += MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 / 2;
                upperLegL.rotateAngleX += MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2 / 2;

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

            int t = TFDataManager.getTransformationTimer(player);

            ModelBiped modelBiped = TFModelHelper.modelBipedMain;

            mainbody1.rotateAngleX = par5 / (180F / (float) Math.PI);
            mainbody1.rotateAngleZ = -modelBiped.bipedHead.rotateAngleY;

            VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
            int landingTimer = 20;

            if (transformedPlayer != null)
            {
                landingTimer = transformedPlayer.getLandingTimer();
                float f = (float) landingTimer / 20;
                float f1 = 1 - f;

                wheel1.setRotationPoint(-4.5F, 13.5F, 3.9F + f1);
                wheel2.setRotationPoint(4.5F, 13.5F, 3.9F + f1);
                mainbody1.rotateAngleX = (par5 / (180F / (float) Math.PI)) * f;
                mainbody1.rotateAngleZ = -modelBiped.bipedHead.rotateAngleY * f;
                mainbody1.setRotationPoint(0.0F, -5.0F + 20 * f1, -2.0F);
            }

            if (t != 20)
            {
                float f = 20 - t;
                //            	float f = 20;
                float f1 = f / 20;
                float f2 = 1.0F - f1;

                setToInitPose();
                headbase.rotationPointY = -2 + 4.5F * f1;
                neck.rotationPointY = -2 + 3 * f1;
                shoulderR.rotationPointX -= f1;
                shoulderL.rotationPointX += f1;
                setRotation(upperArmR, 0.017453292519943295F * f2, -0.017453292519943295F * f2, 0.05235987755982988F * f2);
                setRotation(lowerArmR, -0.19198621771937624F * f2, -0.05235987755982988F * f2 + pi * f1, -0.06981317007977318F * f2 - pi / 2 * f1);
                setRotation(lowerarmR3, -0.03490658503988659F * f2, 0.12566370614359174F * f2, -0.06335545184739416F * f2);
                lowerArmR.setRotationPoint(1.9F * f1, 3.0F * f2 - f1 * 1.6F, 0.0F);
                shoulderR.rotateAngleX = -pi / 2 * f1;
                shoulderR.rotateAngleY = -pi / 2 * f1;
                setRotation(wingR1biped, 0.017453292519943295F * f2 + pi / 2 * f1, 0.07853981633974483F * f2, 0.5061454830783556F * f2 + (pi / 2 + 0.5061454830783556F) * f1);
                setRotation(wingR2biped, -0.3665191429188092F * f2, 0.0F, 0.0F);
                wingR1biped.setRotationPoint(-4.0F + 5 * f1, -3.0F, -1.8F + 2.5F * f1);
                setRotation(upperArmL, 0.017453292519943295F * f2, 0.017453292519943295F * f2, -0.05235987755982988F * f2);
                setRotation(lowerArmL, -0.19198621771937624F * f2, 0.05235987755982988F * f2 + pi * f1, 0.06981317007977318F * f2 + pi / 2 * f1);
                setRotation(lowerarmL3, -0.03490658503988659F * f2, -0.12566370614359174F * f2, 0.06335545184739416F * f2);
                lowerArmL.setRotationPoint(-1.9F * f1, 3.0F * f2 - f1 * 1.6F, 0.0F);
                shoulderL.rotateAngleX = -pi / 2 * f1;
                shoulderL.rotateAngleY = pi / 2 * f1;
                setRotation(wingL1biped, 0.017453292519943295F * f2 + pi / 2 * f1, -0.07853981633974483F * f2, -0.5061454830783556F * f2 - (pi / 2 + 0.5061454830783556F) * f1);
                setRotation(wingL2biped, -0.3665191429188092F * f2, 0.0F, 0.0F);
                wingL1biped.setRotationPoint(4.0F - 5 * f1, -3.0F, -1.8F + 2.5F * f1);
                setRotation(chest1, -pi / 2 * f1, pi * f1, 0);
                bodywingR1.setRotationPoint(-3.0F, 0.2F, 6.0F - 5 * f1);
                setRotation(bodywingR1, -pi * f1, -0.9250245035569946F * f2 + 0.15F, -0.2792526803190927F * f2 + pi * f1);
                setRotation(bodywingR2, -1.1519173063162573F * f2, -0.03490658503988659F * f2, -0.003490658503988659F * f2);
                bodywingL1.setRotationPoint(3.0F, 0.2F, 6.0F - 5 * f1);
                setRotation(bodywingL1, -pi * f1, 0.9250245035569946F * f2 - 0.15F, 0.2792526803190927F * f2 + pi * f1);
                setRotation(bodywingL2, -1.1519173063162573F * f2, 0.03490658503988659F * f2, 0.003490658503988659F * f2);
                setRotation(waist, pi / 2 * f1, 0, pi * f1);
                waist.rotationPointY -= (8 - ((float) landingTimer / 20 + 1) * 5) * f1;
                waist.rotationPointZ += 6 * f1;
                rearstomach.setRotationPoint(0.0F, -8.6F - 10 * f1, 2.9F - 3 * f1);
                setRotation(rearstomach, -0.46774823953448036F * f2 - 0.15F * f1, 0.0F, 0.0F);
                setRotation(upperLegR, -0.6457718232379019F * f2, 0.0F, 0.08726646259971647F * f2);
                upperLegR.setRotationPoint(f1, 0.5F - f1 * 7.5F, 1.1F - f1 * 3);
                setRotation(middlelegR, -0.08831366015091306F * f2 - pi / 2 * f1, 0.06981317007977318F * f2, 0.0F);
                setRotation(lowerLegR, -0.1780235837034216F * f2 + 1.2F * f1, 0.0F, 0.0F);
                lowerLegR.setRotationPoint(0.0F, -0.9F + f1, 5.0F - 2 * f1);
                setRotation(upperLegL, -0.6457718232379019F * f2, 0.0F, -0.08726646259971647F * f2);
                upperLegL.setRotationPoint(2 * f2 + f1, 0.5F - f1 * 7.5F, 1.1F - f1 * 3);
                setRotation(middlelegL, -0.08831366015091306F * f2 - pi / 2 * f1, -0.06981317007977318F * f2, 0.0F);
                setRotation(lowerLegL, -0.1780235837034216F * f2 + 1.2F * f1, 0.0F, 0.0F);
                lowerLegL.setRotationPoint(0.0F, -0.9F + f1, 5.0F - 2 * f1);
            }
        }
    }

    @Override
    public void renderArmorPiece(int armorPiece)
    {
        setToInitPose();

        if (armorPiece == 0)
        {
            GL11.glScalef(0.85F, 0.85F, 0.85F);
            GL11.glTranslatef(0.0F, 0.05F, -0.18F);
            this.headbase.render(0.0625F);
        }
        else if (armorPiece == 1)
        {
            GL11.glTranslatef(0.0F, 0.2F, 0.0F);
            this.upperLegL.showModel = false;
            this.upperLegR.showModel = false;
            this.headbase.showModel = false;
            this.waist.render(0.0625F);
            this.upperLegL.showModel = true;
            this.upperLegR.showModel = true;
            this.headbase.showModel = true;
        }
        else if (armorPiece == 2)
        {
            this.feetconnectorL.showModel = false;
            this.feetconnectorR.showModel = false;
            this.upperLegL.render(0.0625F);
            this.upperLegR.render(0.0625F);
            this.feetconnectorL.showModel = true;
            this.feetconnectorR.showModel = true;
        }
        else if (armorPiece == 3)
        {
            this.feetconnectorL.rotationPointX -= 4;
            this.feetconnectorR.rotationPointX += 4;
            GL11.glRotatef(-90, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.5F, -0.1F, 0.0F);
            this.feetconnectorL.renderWithParentRotations(0.0625F);
            this.feetconnectorR.renderWithParentRotations(0.0625F);
        }
    }
}