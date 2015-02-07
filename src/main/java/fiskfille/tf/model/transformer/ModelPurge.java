package fiskfille.tf.model.transformer;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import fiskfille.tf.TFHelper;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.item.TFItems;

public class ModelPurge extends ModelChildBase.Biped
{
    public ModelRenderer leg2;
    public ModelRenderer foot2;
    public ModelRenderer footCylinder2;
    public ModelRenderer lowerLegPanel2;
    public ModelRenderer upperLegPanel2;
    public ModelRenderer leg1;
    public ModelRenderer foot1;
    public ModelRenderer footCylinder1;
    public ModelRenderer lowerLegPanel1;
    public ModelRenderer upperLegPanel1;
    public ModelRenderer waist;
    public ModelRenderer stomach;
    public ModelRenderer hipSlab1;
    public ModelRenderer hipSlab2;
    public ModelRenderer chest;
    public ModelRenderer backKibble;
    public ModelRenderer chestSlab1;
    public ModelRenderer chestSlab2;
    public ModelRenderer tread1;
    public ModelRenderer tread2;
    public ModelRenderer turret;
    public ModelRenderer gun;
    public ModelRenderer turretFront;
    public ModelRenderer turretSights;
    public ModelRenderer turretRear;
    public ModelRenderer missileLauncher;
    public ModelRenderer hipPanel1;
    public ModelRenderer hipPanel2;
    public ModelRenderer upperArm2;
    public ModelRenderer shoulderPad2;
    public ModelRenderer lowerArm2;
    public ModelRenderer lowerArmPanel2;
    public ModelRenderer upperArm1;
    public ModelRenderer shoulderPad1;
    public ModelRenderer lowerArm1;
    public ModelRenderer lowerArmPanel1;
    public ModelRenderer head;
    public ModelRenderer helmetRight;
    public ModelRenderer helmetLeft;
    public ModelRenderer helmetTop;
    public ModelRenderer helmetBack;
    public ModelRenderer horn1;
    public ModelRenderer horn2;
    public ModelRenderer centerHorn;

    public ModelRenderer vehicleTread1;
    public ModelRenderer vehicleTread2;
    public ModelRenderer vehicleFoot1;
    public ModelRenderer vehicleFoot2;
    public ModelRenderer vehicleBody;
    public ModelRenderer vehicleTrackcover2;
    public ModelRenderer vehicleTrackcover1;
    public ModelRenderer vehicleRearPlate;
    public ModelRenderer vehicleBodyFront;
    public ModelRenderer vehicleBack2;
    public ModelRenderer vehicleBack1;
    public ModelRenderer vehicleTurret;
    public ModelRenderer vehicleBodyplate;
    public ModelRenderer vehicleTurretFront;
    public ModelRenderer vehicleTurretSights;
    public ModelRenderer vehicleGun;
    public ModelRenderer vehicleTurretRear;
    public ModelRenderer vehicleMissileLauncher;

	public ModelPurge()
	{
		textureWidth = 128;
		textureHeight = 128;
		
		bipedBody = new ModelRenderer(this, 1000, 1000);
		bipedHead = new ModelRenderer(this, 1000, 1000);
		bipedHeadwear = new ModelRenderer(this, 1000, 1000);
		bipedRightLeg = new ModelRenderer(this, 1000, 1000);
		bipedLeftLeg = new ModelRenderer(this, 1000, 1000);
		bipedRightArm = new ModelRenderer(this, 1000, 1000);
		bipedLeftArm = new ModelRenderer(this, 1000, 1000);

		this.chest = new ModelRenderer(this, 0, 42);
        this.chest.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.chest.addBox(-4.0F, -5.0F, -2.0F, 8, 5, 4, 0.0F);
        this.upperArm2 = new ModelRenderer(this, 36, 21);
        this.upperArm2.mirror = true;
        this.upperArm2.setRotationPoint(4.0F, 1.0F, 0.0F);
        this.upperArm2.addBox(0.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
        this.setRotation(upperArm2, 0.0F, -0.0F, -0.13962633907794952F);
        this.helmetLeft = new ModelRenderer(this, 28, 0);
        this.helmetLeft.mirror = true;
        this.helmetLeft.setRotationPoint(3.0F, -0.5F, 0.0F);
        this.helmetLeft.addBox(0.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
        this.setRotation(helmetLeft, 0.07438152130324906F, -0.15803570099691838F, -0.4422247463038985F);
        this.turret = new ModelRenderer(this, 21, 64);
        this.turret.setRotationPoint(0.0F, -8.0F, 2.0F);
        this.turret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
        this.setRotation(turret, 0.7853981633974483F, 3.141592653589793F, 0.0F);
        this.hipPanel1 = new ModelRenderer(this, 10, 22);
        this.hipPanel1.mirror = true;
        this.hipPanel1.setRotationPoint(-3.3F, 1.3F, 0.0F);
        this.hipPanel1.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
        this.setRotation(hipPanel1, 0.0F, -0.0F, -0.03490658503988659F);
        this.lowerArm1 = new ModelRenderer(this, 36, 30);
        this.lowerArm1.setRotationPoint(-1.2F, 4.6F, 0.0F);
        this.lowerArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotation(lowerArm1, -0.19198621771937624F, -0.13962634015954636F, -0.13962634015954636F);
        this.lowerLegPanel2 = new ModelRenderer(this, 0, 37);
        this.lowerLegPanel2.mirror = true;
        this.lowerLegPanel2.setRotationPoint(0.0F, -2.0F, -1.5F);
        this.lowerLegPanel2.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(lowerLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
        this.centerHorn = new ModelRenderer(this, 23, 9);
        this.centerHorn.setRotationPoint(0.0F, -4.5F, -2.0F);
        this.centerHorn.addBox(-0.5F, -4.0F, -1.0F, 1, 4, 1, 0.0F);
        this.chestSlab1 = new ModelRenderer(this, 24, 35);
        this.chestSlab1.setRotationPoint(0.0F, 1.0F, -1.1F);
        this.chestSlab1.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(chestSlab1, 0.0F, -0.0F, 0.5235987755982988F);
        this.horn2 = new ModelRenderer(this, 13, 9);
        this.horn2.setRotationPoint(0.0F, -4.0F, -2.0F);
        this.horn2.addBox(0.0F, -1.0F, -1.0F, 4, 1, 1, 0.0F);
        this.setRotation(horn2, 0.0F, -0.0F, -0.8726646304130553F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.setRotationPoint(2.5F, 24.0F, 0.0F);
        this.leg2.addBox(-1.0F, -12.0F, -1.5F, 2, 12, 3, 0.0F);
        this.setRotation(leg2, 0.0F, -0.0F, -0.05235987901687623F);
        this.tread1 = new ModelRenderer(this, 46, 0);
        this.tread1.setRotationPoint(-3.0F, -4.0F, 2.5F);
        this.tread1.addBox(-2.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
        this.setRotation(tread1, 0.17453292519943295F, -0.0F, -0.08726646259971647F);
        this.hipSlab2 = new ModelRenderer(this, 18, 22);
        this.hipSlab2.mirror = true;
        this.hipSlab2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hipSlab2.addBox(0.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
        this.setRotation(hipSlab2, 0.0F, -0.0F, -0.3490658503988659F);
        this.upperLegPanel2 = new ModelRenderer(this, 0, 31);
        this.upperLegPanel2.mirror = true;
        this.upperLegPanel2.setRotationPoint(0.0F, -6.5F, -1.5F);
        this.upperLegPanel2.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
        this.setRotation(upperLegPanel2, 0.008726646259971648F, -0.17453292519943295F, 0.0F);
        this.gun = new ModelRenderer(this, 38, 69);
        this.gun.setRotationPoint(0.0F, -1.1F, -3.0F);
        this.gun.addBox(-1.0F, -1.0F, -9.0F, 2, 2, 11, 0.0F);
        this.setRotation(gun, 0.4363323129985824F, 0.0F, 0.0F);
        this.upperArm1 = new ModelRenderer(this, 36, 21);
        this.upperArm1.setRotationPoint(-4.0F, 1.0F, 0.0F);
        this.upperArm1.addBox(-2.0F, -1.0F, -1.5F, 2, 6, 3, 0.0F);
        this.setRotation(upperArm1, 0.0F, 0.0F, 0.13962634015954636F);
        this.foot2 = new ModelRenderer(this, 8, 35);
        this.foot2.mirror = true;
        this.foot2.setRotationPoint(0.0F, -2.0F, 1.0F);
        this.foot2.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
        this.setRotation(foot2, 0.3490658503988659F, -0.0F, 0.03490658503988659F);
        this.lowerLegPanel1 = new ModelRenderer(this, 0, 37);
        this.lowerLegPanel1.setRotationPoint(0.0F, -2.0F, -1.5F);
        this.lowerLegPanel1.addBox(-1.5F, -4.0F, -0.5F, 3, 4, 1, 0.0F);
        this.setRotation(lowerLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
        this.turretFront = new ModelRenderer(this, 45, 97);
        this.turretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.turretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
        this.setRotation(turretFront, -0.4974188368183839F, 0.0F, 0.0F);
        this.upperLegPanel1 = new ModelRenderer(this, 0, 31);
        this.upperLegPanel1.setRotationPoint(0.0F, -6.5F, -1.5F);
        this.upperLegPanel1.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1, 0.0F);
        this.setRotation(upperLegPanel1, 0.008726646259971648F, 0.17453292519943295F, 0.0F);
        this.turretSights = new ModelRenderer(this, 40, 65);
        this.turretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.turretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
        this.setRotation(turretSights, 0.017453292519943295F, 0.0F, 0.0F);
        this.turretRear = new ModelRenderer(this, 46, 90);
        this.turretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.turretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
        this.setRotation(turretRear, 0.22689280275926282F, 0.0F, 0.0F);
        this.hipPanel2 = new ModelRenderer(this, 10, 22);
        this.hipPanel2.mirror = true;
        this.hipPanel2.setRotationPoint(3.3F, 1.3F, 0.0F);
        this.hipPanel2.addBox(-0.5F, -1.0F, -1.5F, 1, 6, 3, 0.0F);
        this.setRotation(hipPanel2, 0.0F, -0.0F, -0.03490658503988659F);
        this.shoulderPad2 = new ModelRenderer(this, 23, 102);
        this.shoulderPad2.setRotationPoint(-0.5F, 1.0F, 0.0F);
        this.shoulderPad2.addBox(0.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
        this.footCylinder2 = new ModelRenderer(this, 8, 31);
        this.footCylinder2.setRotationPoint(0.0F, -2.0F, 1.0F);
        this.footCylinder2.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(footCylinder2, 0.0F, -0.0F, 0.05235987755982988F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.setRotationPoint(-2.5F, 24.0F, 0.0F);
        this.leg1.addBox(-1.0F, -12.0F, -1.5F, 2, 12, 3, 0.0F);
        this.setRotation(leg1, 0.0F, -0.0F, 0.05235987901687623F);
        this.lowerArmPanel2 = new ModelRenderer(this, 44, 30);
        this.lowerArmPanel2.mirror = true;
        this.lowerArmPanel2.setRotationPoint(-0.3F, 3.0F, 0.0F);
        this.lowerArmPanel2.addBox(0.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
        this.stomach = new ModelRenderer(this, 20, 28);
        this.stomach.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.stomach.addBox(-2.5F, -4.0F, -1.5F, 5, 4, 3, 0.0F);
        this.foot1 = new ModelRenderer(this, 8, 35);
        this.foot1.setRotationPoint(0.0F, -2.0F, 1.0F);
        this.foot1.addBox(-1.5F, -0.5F, -5.0F, 3, 1, 5, 0.0F);
        this.setRotation(foot1, 0.3490658503988659F, -0.0F, -0.05235987755982988F);
        this.helmetTop = new ModelRenderer(this, 0, 8);
        this.helmetTop.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.helmetTop.addBox(-2.0F, -2.0F, -2.5F, 4, 2, 5, 0.0F);
        this.tread2 = new ModelRenderer(this, 46, 0);
        this.tread2.mirror = true;
        this.tread2.setRotationPoint(3.0F, -4.0F, 2.5F);
        this.tread2.addBox(0.0F, -13.0F, -1.5F, 2, 17, 3, 0.0F);
        this.setRotation(tread2, 0.17453292519943295F, -0.0F, 0.08726646259971647F);
        this.lowerArm2 = new ModelRenderer(this, 36, 30);
        this.lowerArm2.mirror = true;
        this.lowerArm2.setRotationPoint(1.2F, 4.6F, 0.0F);
        this.lowerArm2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotation(lowerArm2, -0.19198621771937624F, 0.13962634015954636F, 0.13962634015954636F);
        this.helmetBack = new ModelRenderer(this, 27, 9);
        this.helmetBack.setRotationPoint(0.0F, -4.0F, 1.5F);
        this.helmetBack.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
        this.setRotation(helmetBack, 0.12217304855585097F, -0.0F, 0.0F);
        this.hipSlab1 = new ModelRenderer(this, 18, 22);
        this.hipSlab1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hipSlab1.addBox(-4.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
        this.setRotation(hipSlab1, 0.0F, 0.0F, 0.3490658503988659F);
        this.waist = new ModelRenderer(this, 10, 16);
        this.waist.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.waist.addBox(-3.0F, -3.0F, -1.5F, 6, 3, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F);
        this.lowerArmPanel1 = new ModelRenderer(this, 44, 30);
        this.lowerArmPanel1.setRotationPoint(0.3F, 3.0F, 0.0F);
        this.lowerArmPanel1.addBox(-2.0F, -2.0F, -1.5F, 2, 4, 3, 0.0F);
        this.backKibble = new ModelRenderer(this, 24, 39);
        this.backKibble.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.backKibble.addBox(-3.0F, -6.0F, -0.5F, 6, 6, 5, 0.0F);
        this.setRotation(backKibble, -0.3490658503988659F, -0.0F, 0.0F);
        this.chestSlab2 = new ModelRenderer(this, 24, 35);
        this.chestSlab2.mirror = true;
        this.chestSlab2.setRotationPoint(0.0F, 1.0F, -1.1F);
        this.chestSlab2.addBox(0.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(chestSlab2, 0.0F, -0.0F, -0.5235987755982988F);
        this.missileLauncher = new ModelRenderer(this, 30, 90);
        this.missileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.missileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
        this.setRotation(missileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
        this.horn1 = new ModelRenderer(this, 13, 9);
        this.horn1.setRotationPoint(0.0F, -4.0F, -2.0F);
        this.horn1.addBox(0.0F, 0.0F, -1.0F, 4, 1, 1, 0.0F);
        this.setRotation(horn1, 0.0F, -0.0F, -2.268928050994873F);
        this.helmetRight = new ModelRenderer(this, 28, 0);
        this.helmetRight.setRotationPoint(-3.0F, -0.5F, 0.0F);
        this.helmetRight.addBox(-1.0F, -4.0F, -2.5F, 1, 4, 5, 0.0F);
        this.setRotation(helmetRight, 0.07438152130324906F, 0.15803570099691838F, 0.4422247463038985F);
        this.footCylinder1 = new ModelRenderer(this, 8, 31);
        this.footCylinder1.setRotationPoint(0.0F, -2.0F, 1.0F);
        this.footCylinder1.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2, 0.0F);
        this.setRotation(footCylinder1, 0.0F, -0.0F, -0.05235987755982988F);
        this.shoulderPad1 = new ModelRenderer(this, 23, 102);
        this.shoulderPad1.setRotationPoint(0.5F, 1.0F, 0.0F);
        this.shoulderPad1.addBox(-4.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
        this.stomach.addChild(this.chest);
        this.head.addChild(this.helmetLeft);
        this.backKibble.addChild(this.turret);
        this.hipSlab1.addChild(this.hipPanel1);
        this.upperArm1.addChild(this.lowerArm1);
        this.leg2.addChild(this.lowerLegPanel2);
        this.head.addChild(this.centerHorn);
        this.chest.addChild(this.chestSlab1);
        this.head.addChild(this.horn2);
        this.backKibble.addChild(this.tread1);
        this.waist.addChild(this.hipSlab2);
        this.leg2.addChild(this.upperLegPanel2);
        this.turret.addChild(this.gun);
        this.leg2.addChild(this.foot2);
        this.leg1.addChild(this.lowerLegPanel1);
        this.turret.addChild(this.turretFront);
        this.leg1.addChild(this.upperLegPanel1);
        this.turret.addChild(this.turretSights);
        this.turret.addChild(this.turretRear);
        this.hipSlab2.addChild(this.hipPanel2);
        this.upperArm2.addChild(this.shoulderPad2);
        this.leg2.addChild(this.footCylinder2);
        this.lowerArm2.addChild(this.lowerArmPanel2);
        this.waist.addChild(this.stomach);
        this.leg1.addChild(this.foot1);
        this.head.addChild(this.helmetTop);
        this.backKibble.addChild(this.tread2);
        this.upperArm2.addChild(this.lowerArm2);
        this.head.addChild(this.helmetBack);
        this.waist.addChild(this.hipSlab1);
        this.lowerArm1.addChild(this.lowerArmPanel1);
        this.stomach.addChild(this.backKibble);
        this.chest.addChild(this.chestSlab2);
        this.turretRear.addChild(this.missileLauncher);
        this.head.addChild(this.horn1);
        this.head.addChild(this.helmetRight);
        this.leg1.addChild(this.footCylinder1);
        this.upperArm1.addChild(this.shoulderPad1);
		this.bipedHead.addChild(head);
		this.bipedBody.addChild(waist);
		this.bipedRightArm.addChild(upperArm1);
		this.bipedLeftArm.addChild(upperArm2);
		this.bipedRightLeg.addChild(leg1);
		this.bipedLeftLeg.addChild(leg2);
		

		this.vehicleTread2 = new ModelRenderer(this, 0, 64);
        this.vehicleTread2.setRotationPoint(4.1F, 0.0F, 4.0F);
        this.vehicleTread2.addBox(0.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        this.vehicleBack2 = new ModelRenderer(this, 23, 102);
        this.vehicleBack2.setRotationPoint(2.5F, -6.5F, 3.8F);
        this.vehicleBack2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        this.vehicleBodyFront = new ModelRenderer(this, 39, 83);
        this.vehicleBodyFront.setRotationPoint(0.0F, -5.0F, -5.5F);
        this.vehicleBodyFront.addBox(-4.0F, 0.0F, -3.0F, 8, 4, 3, 0.0F);
        this.setRotation(vehicleBodyFront, 0.21467549799530256F, 0.0F, 0.0F);
        this.vehicleTurretFront = new ModelRenderer(this, 45, 97);
        this.vehicleTurretFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleTurretFront.addBox(-3.0F, -1.2F, -4.1F, 6, 4, 3, 0.0F);
        this.setRotation(vehicleTurretFront, -0.4974188368183839F, 0.0F, 0.0F);
        this.vehicleFoot1 = new ModelRenderer(this, 0, 64);
        this.vehicleFoot1.setRotationPoint(-5.0F, -1.5F, -9.2F);
        this.vehicleFoot1.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotation(vehicleFoot1, -0.03490658503988659F, -0.0F, 0.03490658503988659F);
        this.vehicleBodyplate = new ModelRenderer(this, 0, 86);
        this.vehicleBodyplate.setRotationPoint(-4.0F, -5.0F, -5.5F);
        this.vehicleBodyplate.addBox(0.0F, 0.0F, 0.0F, 8, 1, 14, 0.0F);
        this.vehicleRearPlate = new ModelRenderer(this, 0, 71);
        this.vehicleRearPlate.setRotationPoint(0.0F, -5.0F, 8.5F);
        this.vehicleRearPlate.addBox(-3.0F, 0.0F, 0.0F, 6, 5, 1, 0.0F);
        this.setRotation(vehicleRearPlate, -0.3839724354387525F, 0.0F, 0.0F);
        this.vehicleTurretSights = new ModelRenderer(this, 40, 65);
        this.vehicleTurretSights.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.vehicleTurretSights.addBox(-4.5F, -2.5F, 1.0F, 9, 2, 3, 0.0F);
        this.setRotation(vehicleTurretSights, 0.017453292519943295F, 0.0F, 0.0F);
        this.vehicleBody = new ModelRenderer(this, 64, 64);
        this.vehicleBody.setRotationPoint(0.0F, 22.5F, 0.5F);
        this.vehicleBody.addBox(-5.0F, -4.0F, -7.5F, 10, 4, 15, 0.0F);
        this.vehicleTread1 = new ModelRenderer(this, 0, 64);
        this.vehicleTread1.setRotationPoint(-4.1F, 0.0F, 4.0F);
        this.vehicleTread1.addBox(-2.0F, -1.5F, -13.0F, 2, 3, 17, 0.0F);
        this.vehicleTurretRear = new ModelRenderer(this, 46, 90);
        this.vehicleTurretRear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleTurretRear.addBox(-2.5F, -2.3F, 3.0F, 5, 3, 4, 0.0F);
        this.setRotation(vehicleTurretRear, 0.22689280275926282F, 0.0F, 0.0F);
        this.vehicleTurret = new ModelRenderer(this, 21, 64);
        this.vehicleTurret.setRotationPoint(0.0F, -6.0F, -2.5F);
        this.vehicleTurret.addBox(-3.0F, -3.0F, -3.0F, 6, 4, 7, 0.0F);
        this.vehicleTrackcover2 = new ModelRenderer(this, 0, 101);
        this.vehicleTrackcover2.setRotationPoint(3.5F, -5.5F, -10.2F);
        this.vehicleTrackcover2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        this.vehicleFoot2 = new ModelRenderer(this, 0, 64);
        this.vehicleFoot2.mirror = true;
        this.vehicleFoot2.setRotationPoint(5.0F, -1.5F, -9.2F);
        this.vehicleFoot2.addBox(-1.5F, -1.3F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotation(vehicleFoot2, -0.03490658503988659F, -0.0F, -0.03490658503988659F);
        this.vehicleMissileLauncher = new ModelRenderer(this, 30, 90);
        this.vehicleMissileLauncher.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.vehicleMissileLauncher.addBox(-2.0F, -6.0F, 2.0F, 4, 2, 4, 0.0F);
        this.setRotation(vehicleMissileLauncher, -0.41887902047863906F, 0.0F, 0.0F);
        this.vehicleBack1 = new ModelRenderer(this, 23, 102);
        this.vehicleBack1.setRotationPoint(-2.5F, -6.5F, 3.8F);
        this.vehicleBack1.addBox(-4.0F, 0.0F, 0.0F, 4, 6, 6, 0.0F);
        this.vehicleTrackcover1 = new ModelRenderer(this, 0, 101);
        this.vehicleTrackcover1.setRotationPoint(-3.5F, -5.5F, -10.2F);
        this.vehicleTrackcover1.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 14, 0.0F);
        this.vehicleGun = new ModelRenderer(this, 38, 69);
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
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.vehicleBody.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
		this.leg1.rotationPointY = 12;
		this.leg2.rotationPointY = 12;
		this.upperArm1.rotationPointX = 1;
		this.upperArm2.rotationPointX = -1;
		this.hipPanel1.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2) / 2;
		this.hipPanel2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2) / 2;
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			
			if (TFDataManager.getTransformationTimer(player) == 0)
			{
				float xRotation = par5 / (180F / (float)Math.PI);
				this.vehicleGun.rotateAngleX = par5 < 0 ? xRotation : 0;
				this.vehicleTurret.rotateAngleY = par4 / (180F / (float)Math.PI);
				
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
				int t = TFDataManager.getTransformationTimer(player);
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
				bipedLeftLeg.offsetY = 0F;
				vehicleBody.offsetY = 256F;
				
				if (TFHelper.isPlayerPurge(player) && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.purgesKatana)
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