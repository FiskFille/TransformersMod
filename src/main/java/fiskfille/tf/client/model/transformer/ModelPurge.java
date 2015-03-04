package fiskfille.tf.client.model.transformer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.helper.TFHelper;

public class ModelPurge extends MowzieModelBase
{
	public MowzieModelRenderer upperLegR;
	public MowzieModelRenderer upperLegPanel1;
	public MowzieModelRenderer lowerLeg1;
	public MowzieModelRenderer lowerLegPanel1;
	public MowzieModelRenderer footCylinder1;
	public MowzieModelRenderer foot1;
	public MowzieModelRenderer waist;
	public MowzieModelRenderer stomach;
	public MowzieModelRenderer hipSlab1;
	public MowzieModelRenderer hipSlab2;
	public MowzieModelRenderer chest;
	public MowzieModelRenderer backKibble;
	public MowzieModelRenderer chestSlab1;
	public MowzieModelRenderer chestSlab2;
	public MowzieModelRenderer tread1;
	public MowzieModelRenderer tread2;
	public MowzieModelRenderer turret;
	public MowzieModelRenderer gun;
	public MowzieModelRenderer turretFront;
	public MowzieModelRenderer turretSights;
	public MowzieModelRenderer turretRear;
	public MowzieModelRenderer missileLauncher;
	public MowzieModelRenderer hipPanel1;
	public MowzieModelRenderer hipPanel2;
	public MowzieModelRenderer upperArmL;
	public MowzieModelRenderer shoulderPad2;
	public MowzieModelRenderer lowerArm2;
	public MowzieModelRenderer lowerArmPanel2;
	public MowzieModelRenderer upperArmR;
	public MowzieModelRenderer shoulderPad1;
	public MowzieModelRenderer lowerArm1;
	public MowzieModelRenderer lowerArmPanel1;
	public MowzieModelRenderer head;
	public MowzieModelRenderer helmetRight;
	public MowzieModelRenderer helmetLeft;
	public MowzieModelRenderer helmetTop;
	public MowzieModelRenderer helmetBack;
	public MowzieModelRenderer horn1;
	public MowzieModelRenderer horn2;
	public MowzieModelRenderer centerHorn;
	public MowzieModelRenderer upperLegL;
	public MowzieModelRenderer upperLegPanel2;
	public MowzieModelRenderer lowerLeg2;
	public MowzieModelRenderer lowerLegPanel2;
	public MowzieModelRenderer footCylinder2;
	public MowzieModelRenderer foot2;

	public MowzieModelRenderer vehicleTread1;
	public MowzieModelRenderer vehicleTread2;
	public MowzieModelRenderer vehicleFoot1;
	public MowzieModelRenderer vehicleFoot2;
	public MowzieModelRenderer vehicleBody;
	public MowzieModelRenderer vehicleTrackcover2;
	public MowzieModelRenderer vehicleTrackcover1;
	public MowzieModelRenderer vehicleRearPlate;
	public MowzieModelRenderer vehicleBodyFront;
	public MowzieModelRenderer vehicleBack2;
	public MowzieModelRenderer vehicleBack1;
	public MowzieModelRenderer vehicleTurret;
	public MowzieModelRenderer vehicleBodyplate;
	public MowzieModelRenderer vehicleTurretFront;
	public MowzieModelRenderer vehicleTurretSights;
	public MowzieModelRenderer vehicleGun;
	public MowzieModelRenderer vehicleTurretRear;
	public MowzieModelRenderer vehicleMissileLauncher;

	public ModelPurge()
	{
		textureWidth = 128;
		textureHeight = 128;

		bipedBody = new MowzieModelRenderer(this, 1000, 1000);
		bipedHead = new MowzieModelRenderer(this, 1000, 1000);
		bipedHeadwear = new MowzieModelRenderer(this, 1000, 1000);
		bipedRightLeg = new MowzieModelRenderer(this, 1000, 1000);
		bipedLeftLeg = new MowzieModelRenderer(this, 1000, 1000);
		bipedRightArm = new MowzieModelRenderer(this, 1000, 1000);
		bipedLeftArm = new MowzieModelRenderer(this, 1000, 1000);

		this.shoulderPad2 = new MowzieModelRenderer(this, 23, 102);
		this.shoulderPad2.setRotationPoint(-0.5F, 1.0F, 0.0F);
		this.shoulderPad2.addBox(0.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
		this.head = new MowzieModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F);
		this.upperLegL = new MowzieModelRenderer(this, 0, 16);
		this.upperLegL.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.upperLegL.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.setRotation(upperLegL, 0.0F, -0.0F, -0.05235987755982988F);
		this.turret = new MowzieModelRenderer(this, 21, 64);
		this.turret.setRotationPoint(0.0F, -8.0F, 2.0F);
		this.turret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
		this.setRotation(turret, 0.7853981633974483F, 3.141592653589793F, 0.0F);
		this.waist = new MowzieModelRenderer(this, 10, 16);
		this.waist.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.waist.addBox(-3.0F, -3.0F, -1.5F, 6, 3, 3, 0.0F);
		this.helmetBack = new MowzieModelRenderer(this, 27, 9);
		this.helmetBack.setRotationPoint(0.0F, -4.0F, 1.5F);
		this.helmetBack.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
		this.setRotation(helmetBack, 0.12217304855585097F, -0.0F, 0.0F);
		this.centerHorn = new MowzieModelRenderer(this, 23, 9);
		this.centerHorn.setRotationPoint(0.0F, -4.5F, -2.0F);
		this.centerHorn.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 1, 0.0F);
		this.footCylinder2 = new MowzieModelRenderer(this, 8, 31);
		this.footCylinder2.setRotationPoint(0.0F, 5.0F, 1.0F);
		this.footCylinder2.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
		this.setRotation(footCylinder2, 0.0F, -0.0F, 0.05235987755982988F);
		this.tread1 = new MowzieModelRenderer(this, 46, 0);
		this.tread1.setRotationPoint(-3.0F, -4.0F, 2.5F);
		this.tread1.addBox(-2.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
		this.setRotation(tread1, 0.17453292519943295F, -0.0F, -0.08726646259971647F);
		this.chestSlab1 = new MowzieModelRenderer(this, 24, 35);
		this.chestSlab1.setRotationPoint(0.0F, 1.0F, -1.1F);
		this.chestSlab1.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
		this.setRotation(chestSlab1, 0.0F, -0.0F, 0.5235987755982988F);
		this.lowerLegPanel2 = new MowzieModelRenderer(this, 0, 37);
		this.lowerLegPanel2.mirror = true;
		this.lowerLegPanel2.setRotationPoint(0.0F, 5.0F, -1.5F);
		this.lowerLegPanel2.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
		this.setRotation(lowerLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
		this.upperArmL = new MowzieModelRenderer(this, 36, 21);
		this.upperArmL.mirror = true;
		this.upperArmL.setRotationPoint(4.0F, 1.0F, 0.0F);
		this.upperArmL.addBox(0.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
		this.setRotation(upperArmL, 0.0F, -0.0F, -0.13962633907794952F);
		this.gun = new MowzieModelRenderer(this, 38, 69);
		this.gun.setRotationPoint(0.0F, -1.1F, -3.0F);
		this.gun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
		this.setRotation(gun, 0.4363323129985824F, 0.0F, 0.0F);
		this.turretFront = new MowzieModelRenderer(this, 45, 97);
		this.turretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.turretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
		this.setRotation(turretFront, -0.4974188368183839F, 0.0F, 0.0F);
		this.helmetLeft = new MowzieModelRenderer(this, 28, 0);
		this.helmetLeft.mirror = true;
		this.helmetLeft.setRotationPoint(3.0F, -0.5F, 0.0F);
		this.helmetLeft.addBox(0.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
		this.setRotation(helmetLeft, 0.07438152130324906F, -0.15803570099691838F, -0.4422247463038985F);
		this.horn1 = new MowzieModelRenderer(this, 13, 9);
		this.horn1.setRotationPoint(0.0F, -4.0F, -2.0F);
		this.horn1.addBox(0.0F, 0.0F, -1.0F, 4, 1, 1, 0.0F);
		this.setRotation(horn1, 0.0F, -0.0F, -2.268928050994873F);
		this.lowerArm1 = new MowzieModelRenderer(this, 36, 30);
		this.lowerArm1.setRotationPoint(-1.2F, 4.6F, 0.0F);
		this.lowerArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
		this.setRotation(lowerArm1, -0.19198621771937624F, -0.13962634015954636F, -0.13962634015954636F);
		this.upperLegPanel2 = new MowzieModelRenderer(this, 0, 31);
		this.upperLegPanel2.mirror = true;
		this.upperLegPanel2.setRotationPoint(0.0F, 5.5F, -1.5F);
		this.upperLegPanel2.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
		this.setRotation(upperLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
		this.upperLegR = new MowzieModelRenderer(this, 0, 16);
		this.upperLegR.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.upperLegR.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.setRotation(upperLegR, 0.0F, -0.0F, 0.05235987755982988F);
		this.turretSights = new MowzieModelRenderer(this, 40, 65);
		this.turretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.turretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
		this.setRotation(turretSights, 0.017453292519943295F, 0.0F, 0.0F);
		this.tread2 = new MowzieModelRenderer(this, 46, 0);
		this.tread2.mirror = true;
		this.tread2.setRotationPoint(3.0F, -4.0F, 2.5F);
		this.tread2.addBox(0.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
		this.setRotation(tread2, 0.17453292519943295F, -0.0F, 0.08726646259971647F);
		this.chest = new MowzieModelRenderer(this, 0, 42);
		this.chest.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.chest.addBox(-4.0F, -5.0F, -2.0F, 8, 5, 4, 0.0F);
		this.lowerLeg2 = new MowzieModelRenderer(this, 0, 16);
		this.lowerLeg2.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.lowerLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
		this.foot1 = new MowzieModelRenderer(this, 8, 35);
		this.foot1.setRotationPoint(0.0F, 5.0F, 1.0F);
		this.foot1.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
		this.setRotation(foot1, 0.3490658503988659F, -0.0F, -0.05235987755982988F);
		this.chestSlab2 = new MowzieModelRenderer(this, 24, 35);
		this.chestSlab2.mirror = true;
		this.chestSlab2.setRotationPoint(0.0F, 1.0F, -1.1F);
		this.chestSlab2.addBox(0.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
		this.setRotation(chestSlab2, 0.0F, -0.0F, -0.5235987755982988F);
		this.missileLauncher = new MowzieModelRenderer(this, 30, 90);
		this.missileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.missileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
		this.setRotation(missileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
		this.backKibble = new MowzieModelRenderer(this, 24, 39);
		this.backKibble.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.backKibble.addBox(-3.0F, -6.0F, -0.5F, 6, 6, 5, 0.0F);
		this.setRotation(backKibble, -0.3490658503988659F, -0.0F, 0.0F);
		this.upperArmR = new MowzieModelRenderer(this, 36, 21);
		this.upperArmR.setRotationPoint(-4.0F, 1.0F, 0.0F);
		this.upperArmR.addBox(-2.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
		this.setRotation(upperArmR, 0.0F, 0.0F, 0.13962634015954636F);
		this.turretRear = new MowzieModelRenderer(this, 46, 90);
		this.turretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.turretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
		this.setRotation(turretRear, 0.22689280275926282F, 0.0F, 0.0F);
		this.footCylinder1 = new MowzieModelRenderer(this, 8, 31);
		this.footCylinder1.setRotationPoint(0.0F, 5.0F, 1.0F);
		this.footCylinder1.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
		this.setRotation(footCylinder1, 0.0F, -0.0F, -0.05235987755982988F);
		this.helmetTop = new MowzieModelRenderer(this, 0, 8);
		this.helmetTop.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.helmetTop.addBox(-2.0F, -2.0F, -2.5F, 4, 2, 5, 0.0F);
		this.foot2 = new MowzieModelRenderer(this, 8, 35);
		this.foot2.mirror = true;
		this.foot2.setRotationPoint(0.0F, 5.0F, 1.0F);
		this.foot2.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
		this.setRotation(foot2, 0.3490658503988659F, -0.0F, 0.03490658503988659F);
		this.lowerLeg1 = new MowzieModelRenderer(this, 0, 16);
		this.lowerLeg1.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.lowerLeg1.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
		this.lowerArmPanel1 = new MowzieModelRenderer(this, 44, 30);
		this.lowerArmPanel1.setRotationPoint(0.3F, 3.0F, 0.0F);
		this.lowerArmPanel1.addBox(-2.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
		this.lowerLegPanel1 = new MowzieModelRenderer(this, 0, 37);
		this.lowerLegPanel1.setRotationPoint(0.0F, 5.0F, -1.5F);
		this.lowerLegPanel1.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
		this.setRotation(lowerLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
		this.hipSlab2 = new MowzieModelRenderer(this, 18, 22);
		this.hipSlab2.mirror = true;
		this.hipSlab2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hipSlab2.addBox(0.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
		this.setRotation(hipSlab2, 0.0F, -0.0F, -0.3490658503988659F);
		this.lowerArmPanel2 = new MowzieModelRenderer(this, 44, 30);
		this.lowerArmPanel2.mirror = true;
		this.lowerArmPanel2.setRotationPoint(-0.3F, 3.0F, 0.0F);
		this.lowerArmPanel2.addBox(0.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
		this.stomach = new MowzieModelRenderer(this, 20, 28);
		this.stomach.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.stomach.addBox(-2.5F, -4.0F, -1.5F, 5, 4, 3, 0.0F);
		this.lowerArm2 = new MowzieModelRenderer(this, 36, 30);
		this.lowerArm2.mirror = true;
		this.lowerArm2.setRotationPoint(1.2F, 4.6F, 0.0F);
		this.lowerArm2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
		this.setRotation(lowerArm2, -0.19198621771937624F, 0.13962634015954636F, 0.13962634015954636F);
		this.hipSlab1 = new MowzieModelRenderer(this, 18, 22);
		this.hipSlab1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hipSlab1.addBox(-4.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
		this.setRotation(hipSlab1, 0.0F, 0.0F, 0.3490658503988659F);
		this.upperLegPanel1 = new MowzieModelRenderer(this, 0, 31);
		this.upperLegPanel1.setRotationPoint(0.0F, 5.5F, -1.5F);
		this.upperLegPanel1.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
		this.setRotation(upperLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
		this.hipPanel1 = new MowzieModelRenderer(this, 10, 22);
		this.hipPanel1.mirror = true;
		this.hipPanel1.setRotationPoint(-3.3F, 1.3F, 0.0F);
		this.hipPanel1.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
		this.setRotation(hipPanel1, 0.0F, -0.0F, -0.03490658503988659F);
		this.helmetRight = new MowzieModelRenderer(this, 28, 0);
		this.helmetRight.setRotationPoint(-3.0F, -0.5F, 0.0F);
		this.helmetRight.addBox(-1.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
		this.setRotation(helmetRight, 0.07438152130324906F, 0.15803570099691838F, 0.4422247463038985F);
		this.shoulderPad1 = new MowzieModelRenderer(this, 23, 102);
		this.shoulderPad1.setRotationPoint(0.5F, 1.0F, 0.0F);
		this.shoulderPad1.addBox(-4.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
		this.horn2 = new MowzieModelRenderer(this, 13, 9);
		this.horn2.setRotationPoint(0.0F, -4.0F, -2.0F);
		this.horn2.addBox(0.0F, -1.0F, -1.0F, 4, 1, 1, 0.0F);
		this.setRotation(horn2, 0.0F, -0.0F, -0.8726646304130553F);
		this.hipPanel2 = new MowzieModelRenderer(this, 10, 22);
		this.hipPanel2.mirror = true;
		this.hipPanel2.setRotationPoint(3.3F, 1.3F, 0.0F);
		this.hipPanel2.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
		this.setRotation(hipPanel2, 0.0F, -0.0F, -0.03490658503988659F);
		this.upperArmL.addChild(this.shoulderPad2);
		this.backKibble.addChild(this.turret);
		this.head.addChild(this.helmetBack);
		this.head.addChild(this.centerHorn);
		this.lowerLeg2.addChild(this.footCylinder2);
		this.backKibble.addChild(this.tread1);
		this.chest.addChild(this.chestSlab1);
		this.lowerLeg2.addChild(this.lowerLegPanel2);
		this.turret.addChild(this.gun);
		this.turret.addChild(this.turretFront);
		this.head.addChild(this.helmetLeft);
		this.head.addChild(this.horn1);
		this.upperArmR.addChild(this.lowerArm1);
		this.upperLegL.addChild(this.upperLegPanel2);
		this.turret.addChild(this.turretSights);
		this.backKibble.addChild(this.tread2);
		this.stomach.addChild(this.chest);
		this.upperLegL.addChild(this.lowerLeg2);
		this.lowerLeg1.addChild(this.foot1);
		this.chest.addChild(this.chestSlab2);
		this.turretRear.addChild(this.missileLauncher);
		this.chest.addChild(this.backKibble);
		this.turret.addChild(this.turretRear);
		this.lowerLeg1.addChild(this.footCylinder1);
		this.head.addChild(this.helmetTop);
		this.lowerLeg2.addChild(this.foot2);
		this.upperLegR.addChild(this.lowerLeg1);
		this.lowerArm1.addChild(this.lowerArmPanel1);
		this.lowerLeg1.addChild(this.lowerLegPanel1);
		this.waist.addChild(this.hipSlab2);
		this.lowerArm2.addChild(this.lowerArmPanel2);
		this.waist.addChild(this.stomach);
		this.upperArmL.addChild(this.lowerArm2);
		this.waist.addChild(this.hipSlab1);
		this.upperLegR.addChild(this.upperLegPanel1);
		this.hipSlab1.addChild(this.hipPanel1);
		this.head.addChild(this.helmetRight);
		this.upperArmR.addChild(this.shoulderPad1);
		this.head.addChild(this.horn2);
		this.hipSlab2.addChild(this.hipPanel2);
		this.chest.addChild(head);
		this.bipedBody.addChild(waist);
		this.chest.addChild(upperArmR);
		this.chest.addChild(upperArmL);
		this.waist.addChild(upperLegR);
		this.waist.addChild(upperLegL);


		this.vehicleTread2 = new MowzieModelRenderer(this, 0, 64);
		this.vehicleTread2.setRotationPoint(4.1F, 0.0F, 4.0F);
		this.vehicleTread2.addBox(0.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
		this.vehicleBack2 = new MowzieModelRenderer(this, 23, 102);
		this.vehicleBack2.setRotationPoint(2.5F, -6.5F, 3.8F);
		this.vehicleBack2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
		this.vehicleBodyFront = new MowzieModelRenderer(this, 39, 83);
		this.vehicleBodyFront.setRotationPoint(0.0F, -5.0F, -5.5F);
		this.vehicleBodyFront.addBox(-4.0F, 0.0F, -3.0F, 8, 4, 3, 0.0F);
		this.setRotation(vehicleBodyFront, 0.21467549799530256F, 0.0F, 0.0F);
		this.vehicleTurretFront = new MowzieModelRenderer(this, 45, 97);
		this.vehicleTurretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.vehicleTurretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
		this.setRotation(vehicleTurretFront, -0.4974188368183839F, 0.0F, 0.0F);
		this.vehicleFoot1 = new MowzieModelRenderer(this, 0, 64);
		this.vehicleFoot1.setRotationPoint(-5.0F, -1.5F, -9.2F);
		this.vehicleFoot1.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
		this.setRotation(vehicleFoot1, -0.03490658503988659F, -0.0F, 0.03490658503988659F);
		this.vehicleBodyplate = new MowzieModelRenderer(this, 0, 86);
		this.vehicleBodyplate.setRotationPoint(-4.0F, -5.0F, -5.5F);
		this.vehicleBodyplate.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
		this.vehicleRearPlate = new MowzieModelRenderer(this, 0, 71);
		this.vehicleRearPlate.setRotationPoint(0.0F, -5.0F, 8.5F);
		this.vehicleRearPlate.addBox(-3.0F, 0.0F, 0.0F, 6, 5, 1, 0.0F);
		this.setRotation(vehicleRearPlate, -0.3839724354387525F, 0.0F, 0.0F);
		this.vehicleTurretSights = new MowzieModelRenderer(this, 40, 65);
		this.vehicleTurretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.vehicleTurretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
		this.setRotation(vehicleTurretSights, 0.017453292519943295F, 0.0F, 0.0F);
		this.vehicleBody = new MowzieModelRenderer(this, 64, 64);
		this.vehicleBody.setRotationPoint(0.0F, 22.5F, 0.5F);
		this.vehicleBody.addBox(-5.0F, -4.0F, -7.5F, 10, 4, 15, 0.0F);
		this.vehicleTread1 = new MowzieModelRenderer(this, 0, 64);
		this.vehicleTread1.setRotationPoint(-4.1F, 0.0F, 4.0F);
		this.vehicleTread1.addBox(-2.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
		this.vehicleTurretRear = new MowzieModelRenderer(this, 46, 90);
		this.vehicleTurretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.vehicleTurretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
		this.setRotation(vehicleTurretRear, 0.22689280275926282F, 0.0F, 0.0F);
		this.vehicleTurret = new MowzieModelRenderer(this, 21, 64);
		this.vehicleTurret.setRotationPoint(0.0F, -6.0F, -2.5F);
		this.vehicleTurret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
		this.vehicleTrackcover2 = new MowzieModelRenderer(this, 0, 101);
		this.vehicleTrackcover2.setRotationPoint(3.5F, -5.5F, -10.2F);
		this.vehicleTrackcover2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
		this.vehicleFoot2 = new MowzieModelRenderer(this, 0, 64);
		this.vehicleFoot2.mirror = true;
		this.vehicleFoot2.setRotationPoint(5.0F, -1.5F, -9.2F);
		this.vehicleFoot2.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
		this.setRotation(vehicleFoot2, -0.03490658503988659F, -0.0F, -0.03490658503988659F);
		this.vehicleMissileLauncher = new MowzieModelRenderer(this, 30, 90);
		this.vehicleMissileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.vehicleMissileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
		this.setRotation(vehicleMissileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
		this.vehicleBack1 = new MowzieModelRenderer(this, 23, 102);
		this.vehicleBack1.setRotationPoint(-2.5F, -6.5F, 3.8F);
		this.vehicleBack1.addBox(-4.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
		this.vehicleTrackcover1 = new MowzieModelRenderer(this, 0, 101);
		this.vehicleTrackcover1.setRotationPoint(-3.5F, -5.5F, -10.2F);
		this.vehicleTrackcover1.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
		this.vehicleGun = new MowzieModelRenderer(this, 38, 69);
		this.vehicleGun.setRotationPoint(0.0F, -1.1F, -3.0F);
		this.vehicleGun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
		this.vehicleBody.addChild(this.vehicleTread2);
		this.vehicleBody.addChild(this.vehicleBack2);
		this.vehicleBody.addChild(this.vehicleBodyFront);
		this.vehicleTurret.addChild(this.vehicleTurretFront);
		this.vehicleBody.addChild(this.vehicleFoot1);
		this.vehicleBody.addChild(this.vehicleBodyplate);
		this.vehicleBody.addChild(this.vehicleRearPlate);
		this.vehicleTurret.addChild(this.vehicleTurretSights);
		this.vehicleBody.addChild(this.vehicleTread1);
		this.vehicleTurret.addChild(this.vehicleTurretRear);
		this.vehicleBody.addChild(this.vehicleTurret);
		this.vehicleBody.addChild(this.vehicleTrackcover2);
		this.vehicleBody.addChild(this.vehicleFoot2);
		this.vehicleTurretRear.addChild(this.vehicleMissileLauncher);
		this.vehicleBody.addChild(this.vehicleBack1);
		this.vehicleBody.addChild(this.vehicleTrackcover1);
		this.vehicleTurret.addChild(this.vehicleGun);

		parts = new MowzieModelRenderer[]{upperLegL, lowerLeg2, foot2, footCylinder2, lowerLegPanel2, upperLegPanel2, upperLegR, lowerLeg1, foot1, footCylinder1, lowerLegPanel1, upperLegPanel1, waist, stomach, hipSlab1, hipSlab2, chest, backKibble, chestSlab1, chestSlab2, tread1, tread2, turret, gun, turretFront, turretSights, turretRear, missileLauncher, hipPanel1, hipPanel2, upperArmL, shoulderPad2, lowerArm2, lowerArmPanel2, upperArmR, shoulderPad1, lowerArm1, lowerArmPanel1, head, helmetRight, helmetLeft, helmetTop, helmetBack, horn1, horn2, centerHorn};
		setInitPose();
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerPurge;
			boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerPurge;
			boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerPurge;

			if(wearingLegs && wearingHead && !wearingChest)
			{
				head.render(f5);
				upperLegR.render(f5);
				upperLegL.render(f5);
			}
			else if(wearingHead && !wearingChest)
			{
				head.render(f5);
			}
			else if(wearingLegs && !wearingChest)
			{
				upperLegR.render(f5);
				upperLegL.render(f5);
			}
			else
			{
				vehicleBody.render(f5);
			}
		}
	}

	private void setRotation(MowzieModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

		//ModelBiped

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			setToInitPose();

			boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) instanceof TransformerPurge;
			boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) instanceof TransformerPurge;
			boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) instanceof TransformerPurge;

			float globalSpeed = 1;
			float globalDegree = 0.8F;

			if(wearingChest || wearingHead && !wearingLegs)
			{
				this.upperLegR.rotationPointY = 0;
				this.upperLegL.rotationPointY = 0;
			}

			this.upperArmR.rotationPointX = 1;
			this.upperArmL.rotationPointX = -1;
			this.vehicleTurret.rotationPointZ = -1;

			//Walk animation
			upperArmR.rotationPointX -= 5;
			upperArmL.rotationPointX += 5;
			upperArmR.rotationPointY -= 5;
			upperArmL.rotationPointY -= 5;
			backKibble.rotationPointY += 5;

			waist.rotationPointY += 1;

			if(wearingChest || wearingLegs && !wearingHead)
			{
				head.rotationPointY -= 5;
			}

			head.showModel = wearingHead;
			upperLegR.showModel = wearingLegs;
			upperLegL.showModel = wearingLegs;

			boolean sneaking = entity.isSneaking();

			if (sneaking)
			{
				waist.rotateAngleX -= 0.5F;
				waist.rotationPointZ -= 6F;
				waist.rotationPointY -= 3F;
				globalDegree = 1.5F;
				globalSpeed = 1.5F;
			}

			if(wearingHead)
			{
				faceTarget(head, 1, par4, par5);
			}

			int backwardInverter = 1;
			if (((EntityPlayer)entity).moveForward < 0)
			{
				backwardInverter = -1;
				globalDegree = 0.5F;
			}

			boolean fullSuit = wearingHead && wearingLegs && wearingChest;

			if(fullSuit)
			{
				if (this.onGround > -9990.0F)
				{
					float hitTick = this.onGround;
					float chestRotateY = MathHelper.sin(MathHelper.sqrt_float(hitTick) * (float)Math.PI * 2.0F) * 0.2F;
					this.chest.rotateAngleY += chestRotateY;
					this.head.rotateAngleY -= chestRotateY;
					this.upperArmR.rotateAngleX += chestRotateY * 5;
					this.upperArmR.rotateAngleZ += chestRotateY * 5;
				}
				
				if(entity.onGround || player.capabilities.isFlying)
				{
					//New pose!
					upperLegR.rotateAngleY += 0.2;
					upperLegL.rotateAngleY -= 0.2;
					upperLegR.rotateAngleX -= 0.4;
					upperLegL.rotateAngleX -= 0.4;
					lowerLeg1.rotateAngleX += 0.6;
					lowerLeg2.rotateAngleX += 0.6;
					foot1.rotateAngleX -= 0.3;
					foot2.rotateAngleX -= 0.3;

					waist.rotateAngleX += 0.1;

					head.rotateAngleX += 0.1;

					bob(waist, 1F * globalSpeed, 1.7F * globalDegree, false, par1, par2);
					waist.rotationPointY += 1.2 * par2;
					walk(waist, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
					walk(chest, 1F * globalSpeed, 0.05F * globalDegree, false, 1, 0.15F * par2 * backwardInverter, par1, par2);
					swing(chest, 0.5F * globalSpeed, 0.6F * globalDegree, true, 0, 0, par1, par2);
					swing(waist, 0.5F * globalSpeed, 0.2F * globalDegree, false, 0, 0, par1, par2);
					walk(head, 1F * globalSpeed, -0.1F * globalDegree, false, 1F, -0.3F * par2 * backwardInverter, par1, par2);

					swing(head, 0.5F * globalSpeed, 0.4F * globalDegree, false, 0, 0, par1, par2);
					head.rotationPointX += 0.6 * globalDegree * par2 * Math.cos(par1 * 0.5F * globalSpeed);

					swing(upperLegR, 0.5F * globalSpeed, 0F * globalDegree, false, 0, -0.15F, par1, par2);
					swing(upperLegL, 0.5F * globalSpeed, 0F * globalDegree, false, 0, 0.15F, par1, par2);
					walk(upperLegR, 0.5F * globalSpeed, 1.2F * globalDegree, false, 0, 0, par1, par2);
					walk(upperLegL, 0.5F * globalSpeed, 1.2F * globalDegree, true, 0, 0, par1, par2);
					walk(lowerLeg1, 0.5F * globalSpeed, 1.2F * globalDegree, false, -2.2F * backwardInverter, 0.6F, par1, par2);
					walk(lowerLeg2, 0.5F * globalSpeed, 1.2F * globalDegree, true, -2.2F * backwardInverter, 0.6F, par1, par2);
					walk(upperArmR, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, -0.3F * par2 * backwardInverter, par1, par2);
					walk(upperArmL, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, -0.3F * par2 * backwardInverter, par1, par2);
					walk(lowerArm1, 0.5F * globalSpeed, 0.5F * globalDegree, true, -1F * backwardInverter, -0.5F * par2, par1, par2);
					walk(lowerArm2, 0.5F * globalSpeed, 0.5F * globalDegree, false, -1F * backwardInverter, -0.5F * par2, par1, par2);

					flap(hipPanel1, 1F * globalSpeed, 0.2F * globalDegree, false, -1, 0, par1, par2);
					flap(hipPanel2, 1F * globalSpeed, 0.2F * globalDegree, true, -1, 0, par1, par2);
					walk(gun, 1F * globalSpeed, -0.3F * globalDegree, false, -1, 0, par1, par2);

					//Idle animation
					int ticksExisted = entity.ticksExisted;

					walk(stomach, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
					walk(chest, 0.08F, 0.15F, false, 1, 0, ticksExisted, 1F);
					walk(head, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
					walk(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
					walk(upperArmL, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);

					flap(upperArmR, 0.08F, 0.05F, true, 1, 0, ticksExisted, 1F);
					flap(upperArmL, 0.08F, 0.05F, false, 1, 0, ticksExisted, 1F);
					walk(lowerArm1, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);
					walk(lowerArm2, 0.08F, 0.1F, true, 1, 0, ticksExisted, 1F);

					if (sneaking) 
					{
						waist.rotationPointY += 3;
						stomach.rotateAngleX += 0.5;
						head.rotateAngleX -= 0.5;
						upperLegR.rotateAngleX -= 0.7;
						upperLegL.rotateAngleX -= 0.7;
						upperLegR.rotateAngleY += 0.2;
						upperLegL.rotateAngleY -= 0.2;
						lowerLeg1.rotateAngleX += 1.1;
						lowerLeg2.rotateAngleX += 1.1;
						foot1.rotateAngleX -= 0.5;
						foot2.rotateAngleX -= 0.5;
						foot1.rotationPointY += 2;
						foot2.rotationPointY += 2;
						upperArmR.rotateAngleX -= 0.5;
						upperArmL.rotateAngleX -= 0.5;
						upperArmR.rotateAngleZ += 0.5;
						upperArmL.rotateAngleZ -= 0.5;
						lowerArm1.rotateAngleZ -= 0.5;
						lowerArm2.rotateAngleZ += 0.5;
					}
				}
				else //If not on ground
				{
					float upwardPose = (float) (1/(1 + Math.exp(-20 * (entity.motionY + 0.2))));
					float downwardPose = (float) (1/(1 + Math.exp(10 * (entity.motionY + 0.2))));

					waist.rotateAngleX += 0.2 * par2 * backwardInverter;

					stomach.rotateAngleX += 0.2 * upwardPose;
					chest.rotateAngleX -= 0.4 * upwardPose;
					head.rotateAngleX += 0.6 * upwardPose;

					upperArmR.rotateAngleX += 0.1 * upwardPose;
					upperArmL.rotateAngleX += 0.1 * upwardPose;
					upperArmR.rotateAngleZ -= 0.1 * upwardPose;
					upperArmL.rotateAngleZ += 0.1 * upwardPose;
					lowerArm1.rotateAngleX += 0.2 * upwardPose;
					lowerArm2.rotateAngleX += 0.2 * upwardPose;

					upperLegR.rotateAngleX += 0.2 * upwardPose;
					upperLegL.rotateAngleX -= 1 * upwardPose;
					lowerLeg1.rotateAngleX += 0.3 * upwardPose;
					lowerLeg2.rotateAngleX += 1.5 * upwardPose;

					walk(upperLegR, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, 0, 0, par1, par2);
					walk(upperLegL, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, 0, 0, par1, par2);
					walk(lowerLeg1, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, false, -2.2F * backwardInverter, 0F, par1, par2);
					walk(lowerLeg2, 0.5F * globalSpeed, 0.2F * globalDegree * downwardPose, true, -2.2F * backwardInverter, 0F, par1, par2);
					waist.rotateAngleX -= 0.2 * downwardPose;
					stomach.rotateAngleX += 0.3 * downwardPose;
					chest.rotateAngleX += 0.3 * downwardPose;
					head.rotateAngleX += 0.3 * downwardPose;
					upperLegR.rotateAngleX -= 1.2 * downwardPose;
					upperLegL.rotateAngleX -= 0.2 * downwardPose;
					lowerLeg1.rotateAngleX += 2 * downwardPose;
					lowerLeg2.rotateAngleX += 0.5 * downwardPose;
					upperArmR.rotateAngleZ += 1 * downwardPose;
					upperArmL.rotateAngleZ -= 1 * downwardPose;
					lowerArm1.rotateAngleX -= 1 * downwardPose;
					lowerArm2.rotateAngleX -= 1 * downwardPose;
				}
			}
			else //If not fully suited
			{
				this.upperArmL.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
				this.upperArmR.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 2;

				this.upperLegR.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
				this.upperLegL.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 2;

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
					this.upperArmR.rotationPointZ = MathHelper.sin(this.waist.rotateAngleY) * 4.5F;
					this.upperArmR.rotationPointX = -MathHelper.cos(this.waist.rotateAngleY) * 4.5F;
					this.upperArmL.rotationPointZ = -MathHelper.sin(this.waist.rotateAngleY) * 4.5F;
					this.upperArmL.rotationPointX = MathHelper.cos(this.waist.rotateAngleY) * 4.5F;
					this.upperArmR.rotateAngleY += this.waist.rotateAngleY;
					this.upperArmL.rotateAngleY += this.waist.rotateAngleY;
					this.upperArmL.rotateAngleX += this.waist.rotateAngleY;
					f6 = 1.0F - this.onGround;
					f6 *= f6;
					f6 *= f6;
					f6 = 1.0F - f6;
					float f7 = MathHelper.sin(f6 * (float)Math.PI);
					float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
					this.upperArmR.rotateAngleX = (float)((double)this.upperArmR.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
					this.upperArmR.rotateAngleY += this.waist.rotateAngleY * 2.0F;
					this.upperArmR.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
				}
			}

			float transformProgress = (float)(20 - TFDataManager.getTransformationTimer(player)) / 20;
			float transformProgressSin = MathHelper.sin(1.57079632679F * transformProgress) * MathHelper.sin(1.57079632679F * transformProgress);

			head.rotateAngleX += Math.PI * transformProgressSin;
			waist.rotateAngleX += Math.PI/2 * transformProgressSin;
			bipedBody.rotateAngleY = (float) (Math.PI * transformProgressSin);
			waist.rotationPointY += 5 * transformProgressSin;
			bipedBody.rotationPointZ = -12 * transformProgressSin;
			tread1.rotateAngleX -= Math.PI * transformProgressSin;
			tread2.rotateAngleX -= Math.PI * transformProgressSin;
			tread1.rotationPointZ -= 7 * transformProgressSin;
			tread2.rotationPointZ -= 7 * transformProgressSin;
			shoulderPad1.rotateAngleX += Math.PI/2 * transformProgressSin;
			shoulderPad2.rotateAngleX += Math.PI/2 * transformProgressSin;
			shoulderPad1.rotationPointY -= 5 * transformProgressSin;
			shoulderPad2.rotationPointY -= 5 * transformProgressSin;
			upperArmR.rotationPointY += 5 * transformProgressSin;
			upperArmL.rotationPointY += 5 * transformProgressSin;
			backKibble.rotationPointZ -= 2 * transformProgressSin;
			tread1.rotationPointZ += 2 * transformProgressSin;
			tread2.rotationPointZ += 2 * transformProgressSin;
			turret.rotationPointZ += 2 * transformProgressSin;
			turret.rotateAngleX += 0.5 * transformProgressSin;
			gun.rotateAngleX -= 0.5 * transformProgressSin;
			upperLegR.rotateAngleX -= Math.PI * transformProgressSin;
			upperLegL.rotateAngleX -= Math.PI * transformProgressSin;
			lowerLeg1.rotateAngleX += Math.PI * transformProgressSin;
			lowerLeg2.rotateAngleX += Math.PI * transformProgressSin;
			hipPanel1.rotateAngleZ -= Math.PI/2 * transformProgressSin;
			hipPanel2.rotateAngleZ += Math.PI/2 * transformProgressSin;

			if (TFDataManager.getTransformationTimer(player) == 0)
			{
				float xRotation = par5 / (180F / (float)Math.PI);

				this.vehicleGun.rotateAngleX = par5 < 0 ? xRotation : 0;
				this.vehicleTurret.rotateAngleY = par4 / (180F / (float)Math.PI);

				/*float xRotation = par5 / (180F / (float)Math.PI);

				bipedHead.offsetY = 256F;
				bipedBody.offsetY = 256F;
				bipedRightArm.offsetY = 256F;
				bipedLeftArm.offsetY = 256F;
				bipedRightLeg.offsetY = 256F;
				bipedLeftLeg.offsetY = 256F;*/
				bipedBody.offsetY = 256F;
				vehicleBody.offsetY = 0F;
			}
			else
			{
				/*int t = TFDataManager.getTransformationTimer(player);
				float f = (float)(20 - t) / 2;
				float armPosY = (((float)(20 - t) / 2)) * 2.0F;
				float xRotation = (float)((t * (Math.PI / 40)) - Math.PI / 2);

				bipedBody.rotateAngleY = (float)((t * (Math.PI / 20)) - Math.PI);
				bipedBody.rotationPointX = armPosY / 9;
				bipedBody.rotationPointY = armPosY;
				bipedRightArm.rotationPointY = armPosY;
				bipedLeftArm.rotationPointY = armPosY;
				shoulderPad1.rotateAngleX = xRotation;
				shoulderPad2.rotateAngleX = xRotation;
				shoulderPad1.rotationPointY = -(f * 0.7F) + 1;
				shoulderPad2.rotationPointY = -(f * 0.7F) + 1;

				if (t < 20)
				{
					bipedRightLeg.rotationPointY = f * 0.2F + 12;
					bipedLeftLeg.rotationPointY = f * 0.2F + 12;
					bipedRightLeg.rotateAngleX = xRotation;
					bipedLeftLeg.rotateAngleX = xRotation;

					bipedRightArm.rotateAngleX = xRotation;
					bipedLeftArm.rotateAngleX = xRotation;
				}

				tread1.rotateAngleX = 0.2745329F + (xRotation * 1.25F);
				tread2.rotateAngleX = 0.2745329F + (xRotation * 1.25F);
				bipedHead.rotationPointY = -(24 / 20 * t) + 20;

				bipedHead.offsetY = 0F;
				bipedBody.offsetY = 0F;
				bipedRightArm.offsetY = 0F;
				bipedLeftArm.offsetY = 0F;
				bipedRightLeg.offsetY = 0F;
				bipedLeftLeg.offsetY = 0F;*/
				bipedBody.offsetY = 0F;
				vehicleBody.offsetY = 256F;

				if (wearingChest && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.purgesKatana)
				{
					tread1.offsetY = 256F;
				}
				else
				{
					tread1.offsetY = 0F;
				}
			}
		}
	}
}