package fiskfille.tf.model.transformer;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import fiskfille.tf.TFHelper;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.item.TFItems;

public class ModelPurge extends ModelChildBase.Biped
{
	public ModelRenderer foot2;
	public ModelRenderer footCylinder2;
	public ModelRenderer leg2;
	public ModelRenderer lowerLegPanel2;
	public ModelRenderer upperLegPanel2;
	public ModelRenderer foot1;
	public ModelRenderer footCylinder1;
	public ModelRenderer leg1;
	public ModelRenderer lowerLegPanel1;
	public ModelRenderer upperLegPanel1;
	public ModelRenderer chest;
	public ModelRenderer chestSlab1;
	public ModelRenderer chestSlab2;
	public ModelRenderer backKibble;
	public ModelRenderer turret;
	public ModelRenderer gun;
	public ModelRenderer stomach;
	public ModelRenderer waist;
	public ModelRenderer hipPanel1;
	public ModelRenderer hipPanel2;
	public ModelRenderer hipSlab1;
	public ModelRenderer hipSlab2;
	public ModelRenderer track1;
	public ModelRenderer track2;
	public ModelRenderer upperArm2;
	public ModelRenderer upperArmPanel2;
	public ModelRenderer lowerArm2;
	public ModelRenderer lowerArmPanel2;
	public ModelRenderer upperArm1;
	public ModelRenderer upperArmPanel1;
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
		float par1 = 12.0F;
		float par2 = 5.0F;
		textureWidth = 128;
		textureHeight = 128;

		bipedBody = new ModelRenderer(this, 1000, 1000);
		bipedHead = new ModelRenderer(this, 1000, 1000);
		bipedHeadwear = new ModelRenderer(this, 1000, 1000);
		bipedRightLeg = new ModelRenderer(this, 1000, 1000);
		bipedLeftLeg = new ModelRenderer(this, 1000, 1000);
		bipedRightArm = new ModelRenderer(this, 1000, 1000);
		bipedLeftArm = new ModelRenderer(this, 1000, 1000);

		foot2 = new ModelRenderer(this, 8, 35);
		foot2.addBox(-1F, -0.5F, -5F, 3, 1, 5);
		foot2.setRotationPoint(2F, 22F - par1, 1F);
		foot2.mirror = true;
		setRotation(foot2, 0.3490659F, 0F, 0F);
		footCylinder2 = new ModelRenderer(this, 8, 31);
		footCylinder2.addBox(-2F, -1F, -1F, 4, 2, 2);
		footCylinder2.setRotationPoint(2.5F, 22F - par1, 1F);
		footCylinder2.mirror = true;
		setRotation(footCylinder2, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg2.setRotationPoint(2.5F, 24F - par1, 0F);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, -0.0523599F);
		lowerLegPanel2 = new ModelRenderer(this, 0, 37);
		lowerLegPanel2.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
		lowerLegPanel2.setRotationPoint(2.5F, 22F - par1, -1.5F);
		lowerLegPanel2.mirror = true;
		setRotation(lowerLegPanel2, 0F, -0.1745329F, -0.0523599F);
		upperLegPanel2 = new ModelRenderer(this, 0, 31);
		upperLegPanel2.addBox(-1.5F, -5F, -0.5F, 3, 5, 1);
		upperLegPanel2.setRotationPoint(2.2F, 17.5F - par1, -1.5F);
		upperLegPanel2.mirror = true;
		setRotation(upperLegPanel2, 0F, -0.1745329F, -0.0523599F);
		upperLegPanel2.mirror = false;
		foot1 = new ModelRenderer(this, 8, 35);
		foot1.addBox(-2F, -0.5F, -5F, 3, 1, 5);
		foot1.setRotationPoint(-2F, 22F - par1, 1F);
		foot1.mirror = true;
		setRotation(foot1, 0.3490659F, 0F, 0F);
		footCylinder1 = new ModelRenderer(this, 8, 31);
		footCylinder1.addBox(-2F, -1F, -1F, 4, 2, 2);
		footCylinder1.setRotationPoint(-2.5F, 22F - par1, 1F);
		footCylinder1.mirror = true;
		setRotation(footCylinder1, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg1.setRotationPoint(-2.5F, 24F - par1, 0F);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0.0523599F);
		lowerLegPanel1 = new ModelRenderer(this, 0, 37);
		lowerLegPanel1.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
		lowerLegPanel1.setRotationPoint(-2.5F, 22F - par1, -1.5F);
		lowerLegPanel1.mirror = true;
		setRotation(lowerLegPanel1, 0F, 0.1745329F, 0.0523599F);
		upperLegPanel1 = new ModelRenderer(this, 0, 31);
		upperLegPanel1.addBox(-1.5F, -5F, -0.5F, 3, 5, 1);
		upperLegPanel1.setRotationPoint(-2.2F, 17.5F - par1, -1.5F);
		upperLegPanel1.mirror = true;
		setRotation(upperLegPanel1, 0F, 0.1745329F, 0.0523599F);
		chest = new ModelRenderer(this, 0, 42);
		chest.addBox(-4F, -5F, -2F, 8, 5, 4);
		chest.setRotationPoint(0F, 5F, 0F);
		chest.mirror = true;
		setRotation(chest, 0F, 0F, 0F);
		chestSlab1 = new ModelRenderer(this, 24, 35);
		chestSlab1.addBox(-4F, -1F, -1F, 4, 2, 2);
		chestSlab1.setRotationPoint(0F, 6F, -1.1F);
		chestSlab1.mirror = true;
		setRotation(chestSlab1, 0F, 0F, 0.5235988F);
		chestSlab2 = new ModelRenderer(this, 24, 35);
		chestSlab2.mirror = true;
		chestSlab2.addBox(0F, -1F, -1F, 4, 2, 2);
		chestSlab2.setRotationPoint(0F, 6F, -1.1F);
		setRotation(chestSlab2, 0F, 0F, -0.5235988F);
		backKibble = new ModelRenderer(this, 24, 39);
		backKibble.addBox(-3F, -6F, -0.5F, 6, 6, 5);
		backKibble.setRotationPoint(0F, 8F, 0F);
		backKibble.mirror = true;
		setRotation(backKibble, -0.3490659F, 0F, 0F);
		turret = new ModelRenderer(this, 0, 51);
		turret.addBox(-2.5F, -3F, -3F, 5, 3, 6);
		turret.setRotationPoint(0F, 3F, 3.5F);
		turret.mirror = true;
		setRotation(turret, -0.3490659F, 0F, 0F);
		gun = new ModelRenderer(this, 11, 51);
		gun.addBox(-1F, -1F, 0F, 2, 2, 11);
		gun.setRotationPoint(0F, 4F, 6F);
		gun.mirror = true;
		setRotation(gun, -1.570796F, 0F, 0F);
		stomach = new ModelRenderer(this, 20, 28);
		stomach.addBox(-2.5F, -4F, -1.5F, 5, 4, 3);
		stomach.setRotationPoint(0F, 9F, 0F);
		stomach.mirror = true;
		setRotation(stomach, 0F, 0F, 0F);
		waist = new ModelRenderer(this, 10, 16);
		waist.addBox(-3F, -3F, -1.5F, 6, 3, 3);
		waist.setRotationPoint(0F, 12F, 0F);
		waist.mirror = true;
		setRotation(waist, 0F, 0F, 0F);
		hipPanel1 = new ModelRenderer(this, 10, 22);
		hipPanel1.addBox(-0.5F, -1F, -1.5F, 1, 6, 3);
		hipPanel1.setRotationPoint(-3.5F, 12F, 0F);
		hipPanel1.mirror = true;
		setRotation(hipPanel1, 0F, 0F, 0.3141593F);
		hipPanel2 = new ModelRenderer(this, 10, 22);
		hipPanel2.addBox(-0.5F, -1F, -1.5F, 1, 6, 3);
		hipPanel2.setRotationPoint(3.5F, 12F, 0F);
		hipPanel2.mirror = true;
		setRotation(hipPanel2, 0F, 0F, -0.3141593F);
		hipSlab1 = new ModelRenderer(this, 18, 22);
		hipSlab1.addBox(0F, -0.5F, -2F, 4, 1, 4);
		hipSlab1.setRotationPoint(0F, 12F, 0F);
		hipSlab1.mirror = true;
		setRotation(hipSlab1, 0F, 0F, -2.792527F);
		hipSlab2 = new ModelRenderer(this, 18, 22);
		hipSlab2.addBox(0F, -0.5F, -2F, 4, 1, 4);
		hipSlab2.setRotationPoint(0F, 12F, 0F);
		hipSlab2.mirror = true;
		setRotation(hipSlab2, 0F, 0F, -0.3490659F);
		hipSlab2.mirror = false;
		track1 = new ModelRenderer(this, 46, 44);
		track1.addBox(-2F, -13F, -1.5F, 2, 17, 3);
		track1.setRotationPoint(-3F, 5F, 4F);
		track1.mirror = true;
		setRotation(track1, -0.1745329F, 0F, -0.0872665F);
		track2 = new ModelRenderer(this, 46, 44);
		track2.addBox(0F, -13F, -1.5F, 2, 17, 3);
		track2.setRotationPoint(3F, 5F, 4F);
		track2.mirror = true;
		setRotation(track2, -0.1745329F, 0F, 0.0872665F);
		track2.mirror = false;
		upperArm2 = new ModelRenderer(this, 36, 21);
		upperArm2.addBox(0F, -1F, -1.5F, 2, 6, 3);
		upperArm2.setRotationPoint(4F - par2, 1F, 0F);
		upperArm2.mirror = true;
		setRotation(upperArm2, 0F, 0F, -0.1396263F);
		upperArm2.mirror = false;
		upperArmPanel2 = new ModelRenderer(this, 46, 21);
		upperArmPanel2.addBox(0F, -6F, -1.5F, 1, 6, 3);
		upperArmPanel2.setRotationPoint(6F - par2, 5F, 0F);
		upperArmPanel2.mirror = true;
		setRotation(upperArmPanel2, 0F, 0.296706F, 0F);
		upperArmPanel2.mirror = false;
		lowerArm2 = new ModelRenderer(this, 36, 30);
		lowerArm2.addBox(-1F, 0F, -1F, 2, 7, 2);
		lowerArm2.setRotationPoint(6F - par2, 5.6F, 0F);
		lowerArm2.mirror = true;
		setRotation(lowerArm2, -0.1919862F, 0.1396263F, 0F);
		lowerArm2.mirror = false;
		lowerArmPanel2 = new ModelRenderer(this, 44, 30);
		lowerArmPanel2.addBox(0F, -4F, -1.5F, 2, 4, 3);
		lowerArmPanel2.setRotationPoint(5.5F - par2, 10F, -0.8F);
		lowerArmPanel2.mirror = true;
		setRotation(lowerArmPanel2, -0.1919862F, 0.1396263F, 0F);
		lowerArmPanel2.mirror = false;
		upperArm1 = new ModelRenderer(this, 36, 21);
		upperArm1.addBox(-2F, -1F, -1.5F, 2, 6, 3);
		upperArm1.setRotationPoint(-4F + par2, 1F, 0F);
		upperArm1.mirror = true;
		setRotation(upperArm1, 0F, 0F, 0.1396263F);
		upperArmPanel1 = new ModelRenderer(this, 46, 21);
		upperArmPanel1.addBox(-1F, -6F, -1.5F, 1, 6, 3);
		upperArmPanel1.setRotationPoint(-6F + par2, 5F, 0F);
		upperArmPanel1.mirror = true;
		setRotation(upperArmPanel1, 0F, -0.296706F, 0F);
		lowerArm1 = new ModelRenderer(this, 36, 30);
		lowerArm1.addBox(-1F, 0F, -1F, 2, 7, 2);
		lowerArm1.setRotationPoint(-6F + par2, 5.6F, 0F);
		lowerArm1.mirror = true;
		setRotation(lowerArm1, -0.1919862F, -0.1396263F, 0F);
		lowerArmPanel1 = new ModelRenderer(this, 44, 30);
		lowerArmPanel1.addBox(-2F, -4F, -1.5F, 2, 4, 3);
		lowerArmPanel1.setRotationPoint(-5.5F + par2, 10F, -0.8F);
		lowerArmPanel1.mirror = true;
		setRotation(lowerArmPanel1, -0.1919862F, -0.1396263F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-2F, -4F, -2F, 4, 4, 4);
		head.setRotationPoint(0F, 0F, 0F);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		helmetRight = new ModelRenderer(this, 28, 0);
		helmetRight.addBox(-1F, -4F, -2.5F, 1, 4, 5);
		helmetRight.setRotationPoint(-3F, -0.5F, 0F);
		helmetRight.mirror = true;
		setRotation(helmetRight, 0F, 0.1745329F, 0.4363323F);
		helmetLeft = new ModelRenderer(this, 28, 0);
		helmetLeft.mirror = true;
		helmetLeft.addBox(0F, -4F, -2.5F, 1, 4, 5);
		helmetLeft.setRotationPoint(3F, -0.5F, 0F);
		setRotation(helmetLeft, 0F, -0.1745329F, -0.4363323F);
		helmetLeft.mirror = false;
		helmetTop = new ModelRenderer(this, 0, 8);
		helmetTop.addBox(-2F, -2F, -2.5F, 4, 2, 5);
		helmetTop.setRotationPoint(0F, -4F, 0F);
		helmetTop.mirror = true;
		setRotation(helmetTop, 0F, 0F, 0F);
		helmetBack = new ModelRenderer(this, 27, 9);
		helmetBack.addBox(-2F, 0F, 0F, 4, 3, 1);
		helmetBack.setRotationPoint(0F, -4F, 1.5F);
		helmetBack.mirror = true;
		setRotation(helmetBack, 0.122173F, 0F, 0F);
		horn1 = new ModelRenderer(this, 13, 9);
		horn1.addBox(0F, 0F, -1F, 4, 1, 1);
		horn1.setRotationPoint(0F, -4F, -2F);
		horn1.mirror = true;
		setRotation(horn1, 0F, 0F, -2.268928F);
		horn2 = new ModelRenderer(this, 13, 9);
		horn2.addBox(0F, -1F, -1F, 4, 1, 1);
		horn2.setRotationPoint(0F, -4F, -2F);
		horn2.mirror = true;
		setRotation(horn2, 0F, 0F, -0.8726646F);
		centerHorn = new ModelRenderer(this, 23, 9);
		centerHorn.addBox(-0.5F, -4F, -1F, 1, 4, 1);
		centerHorn.setRotationPoint(0F, -4.5F, -2F);
		centerHorn.mirror = true;
		setRotation(centerHorn, 0F, 0F, 0F);

		this.addChildTo(head, bipedHead);
		this.addChildTo(helmetRight, bipedHead);
		this.addChildTo(helmetLeft, bipedHead);
		this.addChildTo(helmetTop, bipedHead);
		this.addChildTo(helmetBack, bipedHead);
		this.addChildTo(horn1, bipedHead);
		this.addChildTo(horn2, bipedHead);
		this.addChildTo(centerHorn, bipedHead);
		this.addChildTo(upperArm1, bipedRightArm);
		this.addChildTo(upperArmPanel1, bipedRightArm);
		this.addChildTo(lowerArm1, bipedRightArm);
		this.addChildTo(lowerArmPanel1, bipedRightArm);
		this.addChildTo(upperArm2, bipedLeftArm);
		this.addChildTo(upperArmPanel2, bipedLeftArm);
		this.addChildTo(lowerArm2, bipedLeftArm);
		this.addChildTo(lowerArmPanel2, bipedLeftArm);
		this.addChildTo(hipPanel1, hipSlab1);
		this.addChildTo(hipPanel2, hipSlab2);
		this.addChildTo(hipSlab1, bipedBody);
		this.addChildTo(hipSlab2, bipedBody);
		this.addChildTo(waist, bipedBody);
		this.addChildTo(stomach, bipedBody);
		this.addChildTo(chest, bipedBody);
		this.addChildTo(chestSlab1, chest);
		this.addChildTo(chestSlab2, chest);
		this.addChildTo(backKibble, bipedBody);
		this.addChildTo(track1, backKibble);
		this.addChildTo(track2, backKibble);
		this.addChildTo(turret, backKibble);
		this.addChildTo(gun, turret);
		this.addChildTo(foot1, bipedRightLeg);
		this.addChildTo(footCylinder1, bipedRightLeg);
		this.addChildTo(leg1, bipedRightLeg);
		this.addChildTo(lowerLegPanel1, bipedRightLeg);
		this.addChildTo(upperLegPanel1, bipedRightLeg);
		this.addChildTo(foot2, bipedLeftLeg);
		this.addChildTo(footCylinder2, bipedLeftLeg);
		this.addChildTo(leg2, bipedLeftLeg);
		this.addChildTo(lowerLegPanel2, bipedLeftLeg);
		this.addChildTo(upperLegPanel2, bipedLeftLeg);


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
        this.vehicleBody = new ModelRenderer(this, 0, 0);
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
		track1.rotationPointZ = 2.5F;
		track1.rotationPointY = -4.0F;
		track2.rotationPointZ = 2.5F;
		track2.rotationPointY = -4.0F;
		hipPanel1.rotationPointY = -1F;
		hipPanel1.rotationPointX = 3.5F;
		hipPanel2.rotationPointY = 1F;
		turret.rotationPointZ = 1.5F;
		turret.rotationPointY = -6F;
		gun.rotationPointY = 0F;
		gun.rotationPointZ = 3F;
		gun.rotateAngleX = -1.25F;

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
				bipedBody.rotateAngleY = (float)((t * (Math.PI / 20)) - Math.PI);
				
				float armPosY = (((float)(20 - t) / 2)) * 2.0F;
				float xRotation = (float)((t * (Math.PI / 40)) - Math.PI / 2);
				
				bipedBody.rotationPointX = armPosY / 9;
				bipedBody.rotationPointY = armPosY;
				bipedRightArm.rotationPointY = armPosY;
				bipedLeftArm.rotationPointY = armPosY;

				if (t < 20)
				{
					bipedRightLeg.rotationPointY = -(18 / 10 * t) + 18;
					bipedLeftLeg.rotationPointY = -(18 / 10 * t) + 18;
					bipedRightLeg.rotateAngleX = xRotation;
					bipedLeftLeg.rotateAngleX = xRotation;
					bipedHead.rotateAngleX = (float)((t * (Math.PI / 20)) - Math.PI / 2);
				}
				
				track1.rotateAngleX = 0.2745329F + (xRotation * 1.25F);
				track2.rotateAngleX = 0.2745329F + (xRotation * 1.25F);
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
					track1.offsetY = 256F;
				}
				else
				{
					track1.offsetY = 0F;
				}
			}
		}
	}
}