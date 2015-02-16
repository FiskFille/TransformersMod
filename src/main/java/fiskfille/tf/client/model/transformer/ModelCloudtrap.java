package fiskfille.tf.client.model.transformer;

import fiskfille.tf.common.playerdata.TFDataManager;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModelCloudtrap extends ModelTransformer.Biped
{
    ModelRenderer upperLeg1;
    ModelRenderer backLeg1;
    ModelRenderer frontLegBeam1;
    ModelRenderer backLegBeam1;
    ModelRenderer jetEngine1;
    ModelRenderer legPanel1;
    ModelRenderer foot1;
    ModelRenderer upperLeg2;
    ModelRenderer backLeg2;
    ModelRenderer frontLegBeam2;
    ModelRenderer backLegBeam2;
    ModelRenderer jetEngine2;
    ModelRenderer legPanel2;
    ModelRenderer foot2;
    ModelRenderer upperWing1;
    ModelRenderer outerWing1;
    ModelRenderer innerWing1;
    ModelRenderer lowerArm1;
    ModelRenderer lowerArmPanel1;
    ModelRenderer upperWing2;
    ModelRenderer outerWing2;
    ModelRenderer innerWing2;
    ModelRenderer lowerArm2;
    ModelRenderer lowerArmPanel2;
    ModelRenderer waist;
    ModelRenderer torsoBlade1;
    ModelRenderer torsoBlade2;
    ModelRenderer chest;
    ModelRenderer frontPiece2;
    ModelRenderer cockpit;
    ModelRenderer frontPiece1;
    ModelRenderer frontPiece3;
    ModelRenderer head;
    ModelRenderer oxymenTube1;
    ModelRenderer oxygenTube2;
    ModelRenderer headTopPiece1;
    ModelRenderer headTopPiece2;
    ModelRenderer ear1;
    ModelRenderer ear2;
    ModelRenderer eyeGlass;
    ModelRenderer mouthPiece;
	
	ModelRenderer vehicleMain;
	ModelRenderer vehicleFrontPiece2;
	ModelRenderer vehicleFrontPiece3;
	ModelRenderer vehicleRightEngine;
	ModelRenderer vehicleLeftEngine;
	ModelRenderer vehicleRightFrontWing;
	ModelRenderer vehicleLeftFrontWing;
	ModelRenderer vehicleMiddleWing;
	ModelRenderer vehicleRightWing;
	ModelRenderer vehicleLeftWing;
	ModelRenderer vehicleMiddlePiece;
	ModelRenderer vehicleBackPiece;
	ModelRenderer vehicleBackFin1;
	ModelRenderer vehicleBackFin2;
	ModelRenderer vehicleBackFin3;
	ModelRenderer vehicleRightBackWing;
	ModelRenderer vehicleLeftBackWing;
	ModelRenderer vehicleCockpit;
	ModelRenderer vehicleFrontPiece1;
	public ModelRenderer vehicleBody;

	public ModelCloudtrap()
	{
		textureWidth = 64;
		textureHeight = 128;
		
		bipedBody = new ModelRenderer(this, 1000, 1000);
		bipedHead = new ModelRenderer(this, 1000, 1000);
		bipedHeadwear = new ModelRenderer(this, 1000, 1000);
		bipedRightLeg = new ModelRenderer(this, 1000, 1000);
		bipedLeftLeg = new ModelRenderer(this, 1000, 1000);
		bipedRightArm = new ModelRenderer(this, 1000, 1000);
		bipedLeftArm = new ModelRenderer(this, 1000, 1000);
		
		headTopPiece2 = new ModelRenderer(this, 12, 0);
        headTopPiece2.setRotationPoint(1.2F, -3.5F, -1.2F);
        headTopPiece2.addBox(-1.0F, -1.0F, -1.0F, 2, 1, 2);
        setRotateAngle(headTopPiece2, 0.8377580409572781F, 0.13962634015954636F, 0.2792526803190927F);
        lowerArmPanel2 = new ModelRenderer(this, 38, 20);
        lowerArmPanel2.setRotationPoint(1.9F, 8.3F, -1.1F);
        lowerArmPanel2.addBox(0.0F, -2.0F, -1.5F, 1, 4, 3);
        setRotateAngle(lowerArmPanel2, -0.33161255787892263F, -0.0F, 0.10471975511965977F);
        head = new ModelRenderer(this, 0, 0);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4);
        frontLegBeam1 = new ModelRenderer(this, 24, 17);
        frontLegBeam1.setRotationPoint(-0.1F, 7.0F, 3.0F);
        frontLegBeam1.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1);
        setRotateAngle(frontLegBeam1, 0.7330382858376184F, 0.0F, 0.05235987755982988F);
        frontLegBeam2 = new ModelRenderer(this, 24, 17);
        frontLegBeam2.setRotationPoint(-0.1F, 7.0F, 3.0F);
        frontLegBeam2.addBox(-0.5F, -5.0F, -0.5F, 1, 5, 1);
        setRotateAngle(frontLegBeam2, 0.7330382858376184F, 0.0F, 0.05235987755982988F);
        backLeg1 = new ModelRenderer(this, 30, 20);
        backLeg1.setRotationPoint(0.0F, 1.5F, 0.4F);
        backLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        setRotateAngle(backLeg1, 0.8726646259971648F, 0.0F, 0.0F);
        oxymenTube1 = new ModelRenderer(this, 16, 3);
        oxymenTube1.setRotationPoint(-0.65F, 0.0F, -2.48F);
        oxymenTube1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2);
        setRotateAngle(oxymenTube1, 0.45378560551852565F, -0.8726646259971648F, 0.0F);
        foot1 = new ModelRenderer(this, 29, 27);
        foot1.setRotationPoint(0.0F, 2.9F, 0.6F);
        foot1.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5);
        setRotateAngle(foot1, 0.08726646259971647F, 0.08726646259971647F, 0.0F);
        innerWing2 = new ModelRenderer(this, 50, 15);
        innerWing2.setRotationPoint(2.3F, 5.8F, 0.0F);
        innerWing2.addBox(-2.0F, -5.0F, -1.0F, 4, 5, 2);
        lowerArm1 = new ModelRenderer(this, 34, 11);
        lowerArm1.setRotationPoint(-1.4F, 4.7F, 0.1F);
        lowerArm1.addBox(-1.0F, -0.2F, -1.0F, 2, 7, 2);
        setRotateAngle(lowerArm1, -0.33161255787892263F, -0.0F, -0.06981317007977318F);
        upperLeg2 = new ModelRenderer(this, 24, 11);
        upperLeg2.setRotationPoint(1.7F, 12.0F, 0.2F);
        upperLeg2.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2);
        setRotateAngle(upperLeg2, -0.40142572795869574F, 0.0F, -0.13962634015954636F);
        outerWing1 = new ModelRenderer(this, 50, 11);
        outerWing1.setRotationPoint(-3.5F, 4.9F, 0.0F);
        outerWing1.addBox(-6.0F, -2.0F, -0.5F, 6, 3, 1);
        setRotateAngle(outerWing1, 0.015707963267948967F, 0.0F, 0.9075712110370513F);
        chest = new ModelRenderer(this, 0, 11);
        chest.setRotationPoint(0.0F, -7.0F, 0.0F);
        chest.addBox(-4.0F, -5.0F, -2.0F, 8, 5, 4);
        frontPiece3 = new ModelRenderer(this, 18, 21);
        frontPiece3.setRotationPoint(0.0F, 10.0F, 0.0F);
        frontPiece3.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        frontPiece2 = new ModelRenderer(this, 0, 26);
        frontPiece2.setRotationPoint(0.0F, -11.7F, -1.5F);
        frontPiece2.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3);
        setRotateAngle(frontPiece2, -0.06981317007977318F, 3.141592653589793F, 0.0F);
        mouthPiece = new ModelRenderer(this, 10, 8);
        mouthPiece.setRotationPoint(0.0F, -0.5F, -2.0F);
        mouthPiece.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1);
        jetEngine2 = new ModelRenderer(this, 42, 13);
        jetEngine2.setRotationPoint(-0.1F, 6.7F, 2.8F);
        jetEngine2.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
        setRotateAngle(jetEngine2, 0.7330382858376184F, 0.0F, 0.13962634015954636F);
        waist = new ModelRenderer(this, 0, 20);
        waist.setRotationPoint(0.0F, 12.0F, 0.0F);
        waist.addBox(-3.0F, -3.0F, -1.5F, 6, 3, 3);
        legPanel2 = new ModelRenderer(this, 26, 25);
        legPanel2.setRotationPoint(0.0F, 0.5F, -0.6F);
        legPanel2.addBox(-1.5F, -4.0F, -1.0F, 3, 5, 1);
        setRotateAngle(legPanel2, 0.06981317007977318F, -0.17453292519943295F, -0.08726646259971647F);
        outerWing2 = new ModelRenderer(this, 50, 11);
        outerWing2.setRotationPoint(3.5F, 4.9F, 0.0F);
        outerWing2.addBox(0.0F, -2.0F, -0.5F, 6, 3, 1);
        setRotateAngle(outerWing2, 0.015707963267948967F, 0.0F, -0.9075712110370513F);
        oxygenTube2 = new ModelRenderer(this, 16, 3);
        oxygenTube2.setRotationPoint(0.65F, 0.0F, -2.48F);
        oxygenTube2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2);
        setRotateAngle(oxygenTube2, 0.45378560551852565F, 0.8726646259971648F, 0.0F);
        jetEngine1 = new ModelRenderer(this, 42, 13);
        jetEngine1.setRotationPoint(-0.1F, 6.7F, 2.8F);
        jetEngine1.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
        setRotateAngle(jetEngine1, 0.7330382858376184F, 0.0F, -0.13962634015954636F);
        lowerArm2 = new ModelRenderer(this, 34, 11);
        lowerArm2.setRotationPoint(1.4F, 4.7F, 0.1F);
        lowerArm2.addBox(-1.0F, -0.2F, -1.0F, 2, 7, 2);
        setRotateAngle(lowerArm2, -0.33161255787892263F, -0.0F, 0.06981317007977318F);
        backLegBeam1 = new ModelRenderer(this, 28, 17);
        backLegBeam1.setRotationPoint(-0.1F, 6.7F, 3.55F);
        backLegBeam1.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1);
        setRotateAngle(backLegBeam1, 0.2617993877991494F, 0.0F, 0.0F);
        upperWing2 = new ModelRenderer(this, 26, 33);
        upperWing2.setRotationPoint(3.5F, 0.8F, 0.0F);
        upperWing2.addBox(0.0F, -1.2F, -1.0F, 8, 2, 2);
        setRotateAngle(upperWing2, 0.08726646259971647F, 0.0F, -0.20943951023931953F);
        lowerArmPanel1 = new ModelRenderer(this, 38, 20);
        lowerArmPanel1.setRotationPoint(-1.9F, 8.3F, -1.1F);
        lowerArmPanel1.addBox(-1.0F, -2.0F, -1.5F, 1, 4, 3);
        setRotateAngle(lowerArmPanel1, -0.33161255787892263F, -0.0F, -0.10471975511965977F);
        upperWing1 = new ModelRenderer(this, 26, 33);
        upperWing1.setRotationPoint(-3.5F, 0.8F, 0.0F);
        upperWing1.addBox(-8.0F, -1.2F, -1.0F, 8, 2, 2);
        setRotateAngle(upperWing1, 0.08726646259971647F, 0.0F, 0.20943951023931953F);
        ear2 = new ModelRenderer(this, 16, 6);
        ear2.setRotationPoint(1.5F, -2.0F, -1.6F);
        ear2.addBox(0.0F, -1.0F, -0.5F, 1, 2, 3);
        setRotateAngle(ear2, 0.24434609527920614F, 0.22689280275926282F, 0.0F);
        ear1 = new ModelRenderer(this, 16, 6);
        ear1.setRotationPoint(-1.5F, -2.0F, -1.6F);
        ear1.addBox(-1.0F, -1.0F, -0.5F, 1, 2, 3);
        setRotateAngle(ear1, 0.24434609527920614F, -0.22689280275926282F, 0.0F);
        torsoBlade2 = new ModelRenderer(this, 46, 20);
        torsoBlade2.setRotationPoint(1.0F, -3.2F, -1.45F);
        torsoBlade2.addBox(-0.5F, -5.0F, 0.0F, 1, 5, 2);
        setRotateAngle(torsoBlade2, -0.3141592653589793F, 0.0F, 0.2792526803190927F);
        cockpit = new ModelRenderer(this, 45, 27);
        cockpit.setRotationPoint(0.0F, 3.0F, -1.5F);
        cockpit.addBox(-1.5F, -3.0F, -1.0F, 3, 6, 1);
        headTopPiece1 = new ModelRenderer(this, 12, 0);
        headTopPiece1.setRotationPoint(-1.2F, -3.5F, -1.2F);
        headTopPiece1.addBox(-1.0F, -1.0F, -1.0F, 2, 1, 2);
        setRotateAngle(headTopPiece1, 0.8377580409572781F, -0.13962634015954636F, -0.2792526803190927F);
        torsoBlade1 = new ModelRenderer(this, 46, 20);
        torsoBlade1.setRotationPoint(-1.0F, -3.2F, -1.45F);
        torsoBlade1.addBox(-0.5F, -5.0F, 0.0F, 1, 5, 2);
        setRotateAngle(torsoBlade1, -0.3141592653589793F, 0.0F, -0.2792526803190927F);
        backLegBeam2 = new ModelRenderer(this, 28, 17);
        backLegBeam2.setRotationPoint(-0.1F, 6.7F, 3.55F);
        backLegBeam2.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1);
        setRotateAngle(backLegBeam2, 0.2617993877991494F, 0.0F, 0.05235987755982988F);
        foot2 = new ModelRenderer(this, 29, 27);
        foot2.setRotationPoint(0.0F, 2.9F, 0.6F);
        foot2.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5);
        setRotateAngle(foot2, 0.08726646259971647F, -0.08726646259971647F, 0.0F);
        backLeg2 = new ModelRenderer(this, 30, 20);
        backLeg2.setRotationPoint(0.0F, 1.5F, 0.4F);
        backLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        setRotateAngle(backLeg2, 0.8726646259971648F, 0.0F, 0.0F);
        eyeGlass = new ModelRenderer(this, 0, 8);
        eyeGlass.setRotationPoint(0.0F, -2.2F, -1.5F);
        eyeGlass.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 1);
        innerWing1 = new ModelRenderer(this, 50, 15);
        innerWing1.setRotationPoint(-2.3F, 5.8F, 0.0F);
        innerWing1.addBox(-2.0F, -5.0F, -1.0F, 4, 5, 2);
        upperLeg1 = new ModelRenderer(this, 24, 11);
        upperLeg1.setRotationPoint(-1.7F, 12.0F, 0.2F);
        upperLeg1.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2);
        setRotateAngle(upperLeg1, -0.40142572795869574F, 0.0F, 0.13962634015954636F);
        frontPiece1 = new ModelRenderer(this, 12, 26);
        frontPiece1.setRotationPoint(0.0F, 0.0F, 0.0F);
        frontPiece1.addBox(-2.5F, 0.0F, -1.0F, 5, 8, 2);
        legPanel1 = new ModelRenderer(this, 26, 25);
        legPanel1.setRotationPoint(0.0F, 0.5F, -0.6F);
        legPanel1.addBox(-1.5F, -4.0F, -1.0F, 3, 5, 1);
        setRotateAngle(legPanel1, 0.06981317007977318F, 0.17453292519943295F, 0.08726646259971647F);
        
        jetEngine1.addChild(legPanel1);
        frontPiece2.addChild(frontPiece1);
        upperWing1.addChild(innerWing1);
        head.addChild(eyeGlass);
        upperLeg2.addChild(backLeg2);
        jetEngine2.addChild(foot2);
        upperLeg2.addChild(backLegBeam2);
        waist.addChild(torsoBlade1);
        head.addChild(headTopPiece1);
        frontPiece2.addChild(cockpit);
        waist.addChild(torsoBlade2);
        head.addChild(ear1);
        head.addChild(ear2);
        upperWing1.addChild(lowerArmPanel1);
        upperLeg1.addChild(backLegBeam1);
        upperWing2.addChild(lowerArm2);
        upperLeg1.addChild(jetEngine1);
        head.addChild(oxygenTube2);
        upperWing2.addChild(outerWing2);
        jetEngine2.addChild(legPanel2);
        upperLeg2.addChild(jetEngine2);
        head.addChild(mouthPiece);
        waist.addChild(frontPiece2);
        frontPiece2.addChild(frontPiece3);
        waist.addChild(chest);
        upperWing1.addChild(outerWing1);
        upperWing1.addChild(lowerArm1);
        upperWing2.addChild(innerWing2);
        jetEngine1.addChild(foot1);
        head.addChild(oxymenTube1);
        upperLeg1.addChild(backLeg1);
        upperLeg2.addChild(frontLegBeam2);
        upperLeg1.addChild(frontLegBeam1);
        upperWing2.addChild(lowerArmPanel2);
        head.addChild(headTopPiece2);
        bipedHead.addChild(head);
        bipedBody.addChild(waist);
        bipedRightArm.addChild(upperWing1);
        bipedLeftArm.addChild(upperWing2);
        bipedRightLeg.addChild(upperLeg1);
        bipedLeftLeg.addChild(upperLeg2);
		
		
		vehicleMain = new ModelRenderer(this, 0, 98);
		vehicleMain.addBox(-4.0F, 0.0F, -2.0F, 0, 0, 0);
		vehicleMain.setRotationPoint(0.0F, 0.0F, 0.0F);
		vehicleBody = new ModelRenderer(this, 0, 98);
        vehicleBody.addBox(-4.0F, 0.0F, -2.0F, 0, 0, 0);
        vehicleBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        
		vehicleCockpit = new ModelRenderer(this, 0, 64);
		vehicleCockpit.setRotationPoint(0.0F, -1.5F, -11.0F);
		vehicleCockpit.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 6);
		vehicleLeftEngine = new ModelRenderer(this, 0, 78);
		vehicleLeftEngine.setRotationPoint(2.5F, 0.0F, 11.5F);
		vehicleLeftEngine.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4);
		vehicleMiddlePiece = new ModelRenderer(this, 20, 109);
		vehicleMiddlePiece.setRotationPoint(0.0F, 0.0F, 1.0F);
		vehicleMiddlePiece.addBox(-1.5F, -1.5F, -5.0F, 3, 3, 14);
		vehicleBackFin2 = new ModelRenderer(this, 22, 76);
		vehicleBackFin2.setRotationPoint(4.0F, -0.35F, 10.0F);
		vehicleBackFin2.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 3);
		setRotateAngle(vehicleBackFin2, -0.6108652381980153F, 0.0F, 0.3490658503988659F);
		vehicleBackPiece = new ModelRenderer(this, 0, 101);
		vehicleBackPiece.setRotationPoint(0.0F, 0.0F, 8.6F);
		vehicleBackPiece.addBox(-11.5F, -1.0F, 0.0F, 23, 2, 3);
		vehicleMiddleWing = new ModelRenderer(this, 0, 86);
		vehicleMiddleWing.setRotationPoint(0.0F, 0.0F, -1.4F);
		vehicleMiddleWing.addBox(-6.5F, -1.0F, 0.0F, 13, 2, 13);
		vehicleRightEngine = new ModelRenderer(this, 0, 78);
		vehicleRightEngine.setRotationPoint(-2.5F, 0.0F, 11.5F);
		vehicleRightEngine.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4);
		vehicleBackFin3 = new ModelRenderer(this, 25, 78);
		vehicleBackFin3.setRotationPoint(0.0F, 0.0F, 11.5F);
		vehicleBackFin3.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 5);
		vehicleFrontPiece1 = new ModelRenderer(this, 0, 72);
		vehicleFrontPiece1.setRotationPoint(0.0F, 0.0F, -4.0F);
		vehicleFrontPiece1.addBox(-2.5F, -1.0F, -12.0F, 5, 2, 12);
		vehicleRightBackWing = new ModelRenderer(this, 34, 80);
		vehicleRightBackWing.setRotationPoint(-4.4F, 0.0F, 10.8F);
		vehicleRightBackWing.addBox(-2.0F, -0.5F, 0.0F, 4, 1, 5);
		setRotateAngle(vehicleRightBackWing, 0.0F, -0.3839724354387525F, 0.0F);
		vehicleLeftFrontWing = new ModelRenderer(this, 40, 116);
		vehicleLeftFrontWing.setRotationPoint(1.0F, 0.0F, -4.0F);
		vehicleLeftFrontWing.addBox(-2.0F, -0.5F, 0.0F, 3, 1, 6);
		setRotateAngle(vehicleLeftFrontWing, 0.0F, 0.9599310885968813F, 0.0F);
		vehicleFrontPiece3 = new ModelRenderer(this, 0, 72);
		vehicleFrontPiece3.setRotationPoint(0.0F, 0.0F, -18.0F);
		vehicleFrontPiece3.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3);
		vehicleFrontPiece2 = new ModelRenderer(this, 0, 106);
		vehicleFrontPiece2.setRotationPoint(0.0F, 0.0F, -14.0F);
		vehicleFrontPiece2.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 14);
		vehicleLeftBackWing = new ModelRenderer(this, 34, 80);
		vehicleLeftBackWing.setRotationPoint(4.4F, 0.0F, 10.8F);
		vehicleLeftBackWing.addBox(-2.0F, -0.5F, 0.0F, 4, 1, 5);
		setRotateAngle(vehicleLeftBackWing, 0.0F, 0.3839724354387525F, 0.0F);
		vehicleBackFin1 = new ModelRenderer(this, 22, 76);
		vehicleBackFin1.setRotationPoint(-4.0F, -0.35F, 10.0F);
		vehicleBackFin1.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 3);
		setRotateAngle(vehicleBackFin1, -0.6108652381980153F, 0.0F, -0.3490658503988659F);
		vehicleRightFrontWing = new ModelRenderer(this, 40, 116);
		vehicleRightFrontWing.setRotationPoint(-1.0F, 0.0F, -4.0F);
		vehicleRightFrontWing.addBox(-1.0F, -0.5F, 0.0F, 3, 1, 6);
		setRotateAngle(vehicleRightFrontWing, 0.0F, -0.9599310885968813F, 0.0F);
		vehicleRightWing = new ModelRenderer(this, 40, 86);
		vehicleRightWing.setRotationPoint(-5.8F, 0.0F, 3.0F);
		vehicleRightWing.addBox(-8.0F, -0.5F, -1.0F, 8, 1, 4);
		setRotateAngle(vehicleRightWing, 0.0F, 0.9075712110370513F, 0.0F);
		vehicleLeftWing = new ModelRenderer(this, 40, 86);
		vehicleLeftWing.setRotationPoint(5.8F, 0.0F, 3.0F);
		vehicleLeftWing.addBox(0.0F, -0.5F, -1.0F, 8, 1, 4);
		setRotateAngle(vehicleLeftWing, 0.0F, -0.9075712110370513F, 0.0F);
		

		this.addChildTo(vehicleBackFin1, vehicleMain);
		this.addChildTo(vehicleBackFin2, vehicleMain);
		this.addChildTo(vehicleBackFin3, vehicleMain);
		this.addChildTo(vehicleBackPiece, vehicleMain);
		this.addChildTo(vehicleCockpit, vehicleMain);
		this.addChildTo(vehicleFrontPiece1, vehicleMain);
		this.addChildTo(vehicleFrontPiece2, vehicleMain);
		this.addChildTo(vehicleFrontPiece3, vehicleMain);
		this.addChildTo(vehicleLeftBackWing, vehicleMain);
		this.addChildTo(vehicleLeftEngine, vehicleMain);
		this.addChildTo(vehicleLeftFrontWing, vehicleMain);
		this.addChildTo(vehicleLeftWing, vehicleMain);
		this.addChildTo(vehicleMiddlePiece, vehicleMain);
		this.addChildTo(vehicleMiddleWing, vehicleMain);
		this.addChildTo(vehicleRightBackWing, vehicleMain);
		this.addChildTo(vehicleRightEngine, vehicleMain);
		this.addChildTo(vehicleRightFrontWing, vehicleMain);
		this.addChildTo(vehicleRightWing, vehicleMain);
		this.addChildTo(vehicleMain, vehicleBody);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.vehicleBody.render(f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
		
		this.bipedBody.rotationPointY = 0;
		this.upperWing1.rotationPointX = 1.75F;
		this.upperWing2.rotationPointX = -1.75F;
		this.upperLeg1.rotationPointY = 0;
		this.upperLeg2.rotationPointY = 0;
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			int timer = TFDataManager.getTransformationTimer(player);
			
			if (timer == 0)
			{
				this.vehicleBody.rotateAngleX = par5 / (180F / (float)Math.PI);
				this.vehicleBody.rotateAngleZ = -(this.bipedHead.rotateAngleY);
				
				bipedHead.offsetY = 256F;
				bipedBody.offsetY = 256F;
				bipedRightArm.offsetY = 256F;
				bipedLeftArm.offsetY = 256F;
				bipedRightLeg.offsetY = 256F;
				bipedLeftLeg.offsetY = 256F;
				vehicleBody.offsetY = 0F;
			}
			else
			{
				float f = (float)(20 - timer) / 2;
				this.bipedBody.rotateAngleZ = 0.0F;
				this.frontPiece2.rotateAngleX = -(pi / 20) * f * 2 - 0.06981317007977318F;
				this.frontPiece2.rotateAngleY = -3.141592653589793F - (pi / 10) * f;
				this.bipedRightArm.rotationPointZ = f * 1.2F;
				this.bipedLeftArm.rotationPointZ = f * 1.2F;
				
				if (timer < 20)
				{
					this.bipedBody.rotateAngleX = (pi / 20) * f;
					this.bipedHead.rotateAngleX = (pi / 20) * f;
					this.bipedRightArm.rotateAngleX = -(pi / 20) * f;
					this.bipedLeftArm.rotateAngleX = -(pi / 20) * f;
					this.bipedRightLeg.rotateAngleX = (pi / 20) * f;
					this.bipedLeftLeg.rotateAngleX = (pi / 20) * f;
					this.bipedRightLeg.rotationPointY = -1.2F * f + 12;
					this.bipedLeftLeg.rotationPointY = -1.2F * f + 12;
					this.bipedRightLeg.rotationPointZ = f * 0.5F;
					this.bipedLeftLeg.rotationPointZ = f * 0.5F;
				}
				
				bipedHead.offsetY = 0;
				bipedBody.offsetY = 0;
				bipedRightArm.offsetY = 0;
				bipedLeftArm.offsetY = 0;
				bipedRightLeg.offsetY = 0;
				bipedLeftLeg.offsetY = 0;
				vehicleBody.offsetY = 256F;
			}
		}
	}
}