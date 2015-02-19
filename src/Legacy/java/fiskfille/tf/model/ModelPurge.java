package fiskfille.tf.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.main.TFHelper;
import fiskfille.tf.main.TFItems;

public class ModelPurge extends ModelChildBase.Biped
{
	ModelRenderer foot2;
	ModelRenderer footCylinder2;
	ModelRenderer leg2;
	ModelRenderer lowerLegPanel2;
	ModelRenderer upperLegPanel2;
	ModelRenderer foot1;
	ModelRenderer footCylinder1;
	ModelRenderer leg1;
	MowzieModelRenderer lowerLegPanel1;
	MowzieModelRenderer upperLegPanel1;
	MowzieModelRenderer chest;
	MowzieModelRenderer chestSlab1;
	MowzieModelRenderer chestSlab2;
	MowzieModelRenderer backKibble;
	MowzieModelRenderer turret;
	MowzieModelRenderer gun;
	MowzieModelRenderer stomach;
	MowzieModelRenderer waist;
	MowzieModelRenderer hipPanel1;
	MowzieModelRenderer hipPanel2;
	MowzieModelRenderer hipSlab1;
	MowzieModelRenderer hipSlab2;
	MowzieModelRenderer track1;
	MowzieModelRenderer track2;
	MowzieModelRenderer upperArm2;
	MowzieModelRenderer upperArmPanel2;
	MowzieModelRenderer lowerArm2;
	MowzieModelRenderer lowerArmPanel2;
	MowzieModelRenderer upperArm1;
	MowzieModelRenderer upperArmPanel1;
	MowzieModelRenderer lowerArm1;
	MowzieModelRenderer lowerArmPanel1;
	MowzieModelRenderer head;
	MowzieModelRenderer helmetRight;
	MowzieModelRenderer helmetLeft;
	MowzieModelRenderer helmetTop;
	MowzieModelRenderer helmetBack;
	MowzieModelRenderer horn1;
	MowzieModelRenderer horn2;
	MowzieModelRenderer centerHorn;

	MowzieModelRenderer vehichleTread1;
	MowzieModelRenderer vehichleTread2;
	MowzieModelRenderer vehichleFoot1;
	MowzieModelRenderer vehichleFoot2;
	MowzieModelRenderer vehichleBody;
	MowzieModelRenderer vehichleChassy;
	MowzieModelRenderer vehichleTurret;
	MowzieModelRenderer vehichleGun;
	MowzieModelRenderer vehichleBack;

	public ModelPurge()
	{
		float par1 = 12.0F;
		float par2 = 5.0F;
		textureWidth = 66;
		textureHeight = 128;

		bipedBody = new MowzieModelRenderer(this, 1000, 1000);
		bipedHead = new MowzieModelRenderer(this, 1000, 1000);
		bipedHeadwear = new MowzieModelRenderer(this, 1000, 1000);
		bipedRightLeg = new MowzieModelRenderer(this, 1000, 1000);
		bipedLeftLeg = new MowzieModelRenderer(this, 1000, 1000);
		bipedRightArm = new MowzieModelRenderer(this, 1000, 1000);
		bipedLeftArm = new MowzieModelRenderer(this, 1000, 1000);

		foot2 = new MowzieModelRenderer(this, 8, 35);
		foot2.addBox(-1F, -0.5F, -5F, 3, 1, 5);
		foot2.setRotationPoint(2F, 22F - par1, 1F);
		foot2.setTextureSize(64, 128);
		foot2.mirror = true;
		setRotation(foot2, 0.3490659F, 0F, 0F);
		footCylinder2 = new MowzieModelRenderer(this, 8, 31);
		footCylinder2.addBox(-2F, -1F, -1F, 4, 2, 2);
		footCylinder2.setRotationPoint(2.5F, 22F - par1, 1F);
		footCylinder2.setTextureSize(64, 128);
		footCylinder2.mirror = true;
		setRotation(footCylinder2, 0F, 0F, 0F);
		leg2 = new MowzieModelRenderer(this, 0, 16);
		leg2.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg2.setRotationPoint(2.5F, 24F - par1, 0F);
		leg2.setTextureSize(64, 128);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, -0.0523599F);
		lowerLegPanel2 = new MowzieModelRenderer(this, 0, 37);
		lowerLegPanel2.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
		lowerLegPanel2.setRotationPoint(2.5F, 22F - par1, -1.5F);
		lowerLegPanel2.setTextureSize(64, 128);
		lowerLegPanel2.mirror = true;
		setRotation(lowerLegPanel2, 0F, -0.1745329F, -0.0523599F);
		upperLegPanel2 = new MowzieModelRenderer(this, 0, 31);
		upperLegPanel2.addBox(-1.5F, -5F, -0.5F, 3, 5, 1);
		upperLegPanel2.setRotationPoint(2.2F, 17.5F - par1, -1.5F);
		upperLegPanel2.setTextureSize(64, 128);
		upperLegPanel2.mirror = true;
		setRotation(upperLegPanel2, 0F, -0.1745329F, -0.0523599F);
		upperLegPanel2.mirror = false;
		foot1 = new MowzieModelRenderer(this, 8, 35);
		foot1.addBox(-2F, -0.5F, -5F, 3, 1, 5);
		foot1.setRotationPoint(-2F, 22F - par1, 1F);
		foot1.setTextureSize(64, 128);
		foot1.mirror = true;
		setRotation(foot1, 0.3490659F, 0F, 0F);
		footCylinder1 = new MowzieModelRenderer(this, 8, 31);
		footCylinder1.addBox(-2F, -1F, -1F, 4, 2, 2);
		footCylinder1.setRotationPoint(-2.5F, 22F - par1, 1F);
		footCylinder1.setTextureSize(64, 128);
		footCylinder1.mirror = true;
		setRotation(footCylinder1, 0F, 0F, 0F);
		leg1 = new MowzieModelRenderer(this, 0, 16);
		leg1.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg1.setRotationPoint(-2.5F, 24F - par1, 0F);
		leg1.setTextureSize(64, 128);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0.0523599F);
		lowerLegPanel1 = new MowzieModelRenderer(this, 0, 37);
		lowerLegPanel1.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
		lowerLegPanel1.setRotationPoint(-2.5F, 22F - par1, -1.5F);
		lowerLegPanel1.setTextureSize(64, 128);
		lowerLegPanel1.mirror = true;
		setRotation(lowerLegPanel1, 0F, 0.1745329F, 0.0523599F);
		upperLegPanel1 = new MowzieModelRenderer(this, 0, 31);
		upperLegPanel1.addBox(-1.5F, -5F, -0.5F, 3, 5, 1);
		upperLegPanel1.setRotationPoint(-2.2F, 17.5F - par1, -1.5F);
		upperLegPanel1.setTextureSize(64, 128);
		upperLegPanel1.mirror = true;
		setRotation(upperLegPanel1, 0F, 0.1745329F, 0.0523599F);
		chest = new MowzieModelRenderer(this, 0, 42);
		chest.addBox(-4F, -5F, -2F, 8, 5, 4);
		chest.setRotationPoint(0F, 5F, 0F);
		chest.setTextureSize(64, 128);
		chest.mirror = true;
		setRotation(chest, 0F, 0F, 0F);
		chestSlab1 = new MowzieModelRenderer(this, 24, 35);
		chestSlab1.addBox(-4F, -1F, -1F, 4, 2, 2);
		chestSlab1.setRotationPoint(0F, 6F, -1.1F);
		chestSlab1.setTextureSize(64, 128);
		chestSlab1.mirror = true;
		setRotation(chestSlab1, 0F, 0F, 0.5235988F);
		chestSlab2 = new MowzieModelRenderer(this, 24, 35);
		chestSlab2.mirror = true;
		chestSlab2.addBox(0F, -1F, -1F, 4, 2, 2);
		chestSlab2.setRotationPoint(0F, 6F, -1.1F);
		chestSlab2.setTextureSize(64, 128);
		setRotation(chestSlab2, 0F, 0F, -0.5235988F);
		backKibble = new MowzieModelRenderer(this, 24, 39);
		backKibble.addBox(-3F, -6F, -0.5F, 6, 6, 5);
		backKibble.setRotationPoint(0F, 8F, 0F);
		backKibble.setTextureSize(64, 128);
		backKibble.mirror = true;
		setRotation(backKibble, -0.3490659F, 0F, 0F);
		turret = new MowzieModelRenderer(this, 0, 51);
		turret.addBox(-2.5F, -3F, -3F, 5, 3, 6);
		turret.setRotationPoint(0F, 3F, 3.5F);
		turret.setTextureSize(64, 128);
		turret.mirror = true;
		setRotation(turret, -0.3490659F, 0F, 0F);
		gun = new MowzieModelRenderer(this, 11, 51);
		gun.addBox(-1F, -1F, 0F, 2, 2, 11);
		gun.setRotationPoint(0F, 4F, 6F);
		gun.setTextureSize(64, 128);
		gun.mirror = true;
		setRotation(gun, -1.570796F, 0F, 0F);
		stomach = new MowzieModelRenderer(this, 20, 28);
		stomach.addBox(-2.5F, -4F, -1.5F, 5, 4, 3);
		stomach.setRotationPoint(0F, 9F, 0F);
		stomach.setTextureSize(64, 128);
		stomach.mirror = true;
		setRotation(stomach, 0F, 0F, 0F);
		waist = new MowzieModelRenderer(this, 10, 16);
		waist.addBox(-3F, -3F, -1.5F, 6, 3, 3);
		waist.setRotationPoint(0F, 12F, 0F);
		waist.setTextureSize(64, 128);
		waist.mirror = true;
		setRotation(waist, 0F, 0F, 0F);
		hipPanel1 = new MowzieModelRenderer(this, 10, 22);
		hipPanel1.addBox(-0.5F, -1F, -1.5F, 1, 6, 3);
		hipPanel1.setRotationPoint(-3.5F, 12F, 0F);
		hipPanel1.setTextureSize(64, 128);
		hipPanel1.mirror = true;
		setRotation(hipPanel1, 0F, 0F, 0.3141593F);
		hipPanel2 = new MowzieModelRenderer(this, 10, 22);
		hipPanel2.addBox(-0.5F, -1F, -1.5F, 1, 6, 3);
		hipPanel2.setRotationPoint(3.5F, 12F, 0F);
		hipPanel2.setTextureSize(64, 128);
		hipPanel2.mirror = true;
		setRotation(hipPanel2, 0F, 0F, -0.3141593F);
		hipSlab1 = new MowzieModelRenderer(this, 18, 22);
		hipSlab1.addBox(0F, -0.5F, -2F, 4, 1, 4);
		hipSlab1.setRotationPoint(0F, 12F, 0F);
		hipSlab1.setTextureSize(64, 128);
		hipSlab1.mirror = true;
		setRotation(hipSlab1, 0F, 0F, -2.792527F);
		hipSlab2 = new MowzieModelRenderer(this, 18, 22);
		hipSlab2.addBox(0F, -0.5F, -2F, 4, 1, 4);
		hipSlab2.setRotationPoint(0F, 12F, 0F);
		hipSlab2.setTextureSize(64, 128);
		hipSlab2.mirror = true;
		setRotation(hipSlab2, 0F, 0F, -0.3490659F);
		hipSlab2.mirror = false;
		track1 = new MowzieModelRenderer(this, 46, 47);
		track1.addBox(-2F, -13F, -1.5F, 2, 17, 3);
		track1.setRotationPoint(-3F, 5F, 4F);
		track1.setTextureSize(64, 128);
		track1.mirror = true;
		setRotation(track1, -0.1745329F, 0F, -0.0872665F);
		track2 = new MowzieModelRenderer(this, 46, 47);
		track2.addBox(0F, -13F, -1.5F, 2, 17, 3);
		track2.setRotationPoint(3F, 5F, 4F);
		track2.setTextureSize(64, 128);
		track2.mirror = true;
		setRotation(track2, -0.1745329F, 0F, 0.0872665F);
		track2.mirror = false;
		upperArm2 = new MowzieModelRenderer(this, 36, 21);
		upperArm2.addBox(0F, -1F, -1.5F, 2, 6, 3);
		upperArm2.setRotationPoint(4F - par2, 1F, 0F);
		upperArm2.setTextureSize(64, 128);
		upperArm2.mirror = true;
		setRotation(upperArm2, 0F, 0F, -0.1396263F);
		upperArm2.mirror = false;
		upperArmPanel2 = new MowzieModelRenderer(this, 46, 21);
		upperArmPanel2.addBox(0F, -6F, -1.5F, 1, 6, 3);
		upperArmPanel2.setRotationPoint(6F - par2, 5F, 0F);
		upperArmPanel2.setTextureSize(64, 128);
		upperArmPanel2.mirror = true;
		setRotation(upperArmPanel2, 0F, 0.296706F, 0F);
		upperArmPanel2.mirror = false;
		lowerArm2 = new MowzieModelRenderer(this, 36, 30);
		lowerArm2.addBox(-1F, 0F, -1F, 2, 7, 2);
		lowerArm2.setRotationPoint(6F - par2, 5.6F, 0F);
		lowerArm2.setTextureSize(64, 128);
		lowerArm2.mirror = true;
		setRotation(lowerArm2, -0.1919862F, 0.1396263F, 0F);
		lowerArm2.mirror = false;
		lowerArmPanel2 = new MowzieModelRenderer(this, 44, 30);
		lowerArmPanel2.addBox(0F, -4F, -1.5F, 2, 4, 3);
		lowerArmPanel2.setRotationPoint(5.5F - par2, 10F, -0.8F);
		lowerArmPanel2.setTextureSize(64, 128);
		lowerArmPanel2.mirror = true;
		setRotation(lowerArmPanel2, -0.1919862F, 0.1396263F, 0F);
		lowerArmPanel2.mirror = false;
		upperArm1 = new MowzieModelRenderer(this, 36, 21);
		upperArm1.addBox(-2F, -1F, -1.5F, 2, 6, 3);
		upperArm1.setRotationPoint(-4F + par2, 1F, 0F);
		upperArm1.setTextureSize(64, 128);
		upperArm1.mirror = true;
		setRotation(upperArm1, 0F, 0F, 0.1396263F);
		upperArmPanel1 = new MowzieModelRenderer(this, 46, 21);
		upperArmPanel1.addBox(-1F, -6F, -1.5F, 1, 6, 3);
		upperArmPanel1.setRotationPoint(-6F + par2, 5F, 0F);
		upperArmPanel1.setTextureSize(64, 128);
		upperArmPanel1.mirror = true;
		setRotation(upperArmPanel1, 0F, -0.296706F, 0F);
		lowerArm1 = new MowzieModelRenderer(this, 36, 30);
		lowerArm1.addBox(-1F, 0F, -1F, 2, 7, 2);
		lowerArm1.setRotationPoint(-6F + par2, 5.6F, 0F);
		lowerArm1.setTextureSize(64, 128);
		lowerArm1.mirror = true;
		setRotation(lowerArm1, -0.1919862F, -0.1396263F, 0F);
		lowerArmPanel1 = new MowzieModelRenderer(this, 44, 30);
		lowerArmPanel1.addBox(-2F, -4F, -1.5F, 2, 4, 3);
		lowerArmPanel1.setRotationPoint(-5.5F + par2, 10F, -0.8F);
		lowerArmPanel1.setTextureSize(64, 128);
		lowerArmPanel1.mirror = true;
		setRotation(lowerArmPanel1, -0.1919862F, -0.1396263F, 0F);
		head = new MowzieModelRenderer(this, 0, 0);
		head.addBox(-2F, -4F, -2F, 4, 4, 4);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		helmetRight = new MowzieModelRenderer(this, 28, 0);
		helmetRight.addBox(-1F, -4F, -2.5F, 1, 4, 5);
		helmetRight.setRotationPoint(-3F, -0.5F, 0F);
		helmetRight.setTextureSize(64, 128);
		helmetRight.mirror = true;
		setRotation(helmetRight, 0F, 0.1745329F, 0.4363323F);
		helmetLeft = new MowzieModelRenderer(this, 28, 0);
		helmetLeft.mirror = true;
		helmetLeft.addBox(0F, -4F, -2.5F, 1, 4, 5);
		helmetLeft.setRotationPoint(3F, -0.5F, 0F);
		helmetLeft.setTextureSize(64, 128);
		setRotation(helmetLeft, 0F, -0.1745329F, -0.4363323F);
		helmetLeft.mirror = false;
		helmetTop = new MowzieModelRenderer(this, 0, 8);
		helmetTop.addBox(-2F, -2F, -2.5F, 4, 2, 5);
		helmetTop.setRotationPoint(0F, -4F, 0F);
		helmetTop.setTextureSize(64, 128);
		helmetTop.mirror = true;
		setRotation(helmetTop, 0F, 0F, 0F);
		helmetBack = new MowzieModelRenderer(this, 27, 9);
		helmetBack.addBox(-2F, 0F, 0F, 4, 3, 1);
		helmetBack.setRotationPoint(0F, -4F, 1.5F);
		helmetBack.setTextureSize(64, 128);
		helmetBack.mirror = true;
		setRotation(helmetBack, 0.122173F, 0F, 0F);
		horn1 = new MowzieModelRenderer(this, 13, 9);
		horn1.addBox(0F, 0F, -1F, 4, 1, 1);
		horn1.setRotationPoint(0F, -4F, -2F);
		horn1.setTextureSize(64, 128);
		horn1.mirror = true;
		setRotation(horn1, 0F, 0F, -2.268928F);
		horn2 = new MowzieModelRenderer(this, 13, 9);
		horn2.addBox(0F, -1F, -1F, 4, 1, 1);
		horn2.setRotationPoint(0F, -4F, -2F);
		horn2.setTextureSize(64, 128);
		horn2.mirror = true;
		setRotation(horn2, 0F, 0F, -0.8726646F);
		centerHorn = new MowzieModelRenderer(this, 23, 9);
		centerHorn.addBox(-0.5F, -4F, -1F, 1, 4, 1);
		centerHorn.setRotationPoint(0F, -4.5F, -2F);
		centerHorn.setTextureSize(64, 128);
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


		vehichleTread1 = new MowzieModelRenderer(this, 0, 64);
		vehichleTread1.addBox(-2F, -1.5F, -13F, 2, 3, 17);
		vehichleTread1.setRotationPoint(-5F, 22.5F, 4.5F);
		vehichleTread1.setTextureSize(66, 128);
		vehichleTread1.mirror = true;
		setRotation(vehichleTread1, 0F, 0F, 0F);
		vehichleTread2 = new MowzieModelRenderer(this, 0, 64);
		vehichleTread2.addBox(0F, -1.5F, -13F, 2, 3, 17);
		vehichleTread2.setRotationPoint(5F, 22.5F, 4.5F);
		vehichleTread2.setTextureSize(66, 128);
		vehichleTread2.mirror = true;
		setRotation(vehichleTread2, 0F, 0F, 0F);
		vehichleFoot1 = new MowzieModelRenderer(this, 0, 64);
		vehichleFoot1.addBox(-1.5F, -1F, -1F, 3, 4, 1);
		vehichleFoot1.setRotationPoint(-5F, 21F, -8.7F);
		vehichleFoot1.setTextureSize(66, 128);
		vehichleFoot1.mirror = true;
		setRotation(vehichleFoot1, 0.4014257F, 0F, 0F);
		vehichleFoot2 = new MowzieModelRenderer(this, 0, 64);
		vehichleFoot2.mirror = true;
		vehichleFoot2.addBox(-1.5F, -1F, -1F, 3, 4, 1);
		vehichleFoot2.setRotationPoint(5F, 21F, -8.7F);
		vehichleFoot2.setTextureSize(66, 128);
		vehichleFoot2.mirror = true;
		setRotation(vehichleFoot2, 0.4014257F, 0F, 0F);
		vehichleFoot2.mirror = false;
		vehichleBody = new MowzieModelRenderer(this, 0, 106);
		vehichleBody.addBox(-6F, -3F, -7.5F, 12, 3, 16);
		vehichleBody.setRotationPoint(0F, 23F, -0.5F);
		vehichleBody.setTextureSize(66, 128);
		vehichleBody.mirror = true;
		setRotation(vehichleBody, 0F, 0F, 0F);
		vehichleChassy = new MowzieModelRenderer(this, 0, 84);
		vehichleChassy.addBox(-6.5F, -2F, -10F, 13, 2, 20);
		vehichleChassy.setRotationPoint(0F, 20.5F, 0F);
		vehichleChassy.setTextureSize(66, 128);
		vehichleChassy.mirror = true;
		setRotation(vehichleChassy, 0F, 0F, 0F);
		vehichleTurret = new MowzieModelRenderer(this, 21, 64);
		vehichleTurret.addBox(-3F, -3F, -3F, 6, 4, 7);
		vehichleTurret.setRotationPoint(0F, 18F, -0.5F);
		vehichleTurret.setTextureSize(66, 128);
		vehichleTurret.mirror = true;
		setRotation(vehichleTurret, 0F, 0F, 0F);
		vehichleGun = new MowzieModelRenderer(this, 38, 69);
		vehichleGun.addBox(-1F, -1F, -11F, 2, 2, 11);
		vehichleGun.setRotationPoint(0F, 17F, -3.5F);
		vehichleGun.setTextureSize(66, 128);
		vehichleGun.mirror = true;
		setRotation(vehichleGun, 0F, 0F, 0F);
		vehichleBack = new MowzieModelRenderer(this, 21, 75);
		vehichleBack.addBox(-6.5F, -1F, 0F, 13, 4, 1);
		vehichleBack.setRotationPoint(0F, 21F, 8.7F);
		vehichleBack.setTextureSize(66, 128);
		vehichleBack.mirror = true;
		setRotation(vehichleBack, -0.4014257F, 0F, 0F);
		
		
		this.addChildTo(vehichleGun, vehichleTurret);
		this.addChildTo(vehichleTurret, vehichleBody);
		this.addChildTo(vehichleBack, vehichleBody);
		this.addChildTo(vehichleChassy, vehichleBody);
		this.addChildTo(vehichleFoot1, vehichleBody);
		this.addChildTo(vehichleFoot2, vehichleBody);
		this.addChildTo(vehichleTread1, vehichleBody);
		this.addChildTo(vehichleTread2, vehichleBody);
		
		parts =
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    vehichleBody.render(f5);
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
			
			if (TFPlayerData.getTransformationTimer(player) == 0)
			{
				float xRotation = par5 / (180F / (float)Math.PI);
				if (par5 < 0) {this.vehichleGun.rotateAngleX = par5 / (180F / (float)Math.PI);}
				this.vehichleTurret.rotateAngleY = par4 / (180F / (float)Math.PI);
				
				bipedHead.offsetY = 256F;
				bipedBody.offsetY = 256F;
				bipedRightArm.offsetY = 256F;
				bipedLeftArm.offsetY = 256F;
				bipedRightLeg.offsetY = 256F;
				bipedLeftLeg.offsetY = 256F;
				vehichleBody.offsetY = 0F;
			}
			else
			{
				int t = TFPlayerData.getTransformationTimer(player);
				bipedBody.rotateAngleY = (float)((t * (Math.PI / 10)) - Math.PI);
				
				float armPosY = (10 - t) * 2.0F;
				float xRotation = (float)((t * (Math.PI / 20)) - Math.PI / 2);

				bipedBody.rotationPointX = armPosY / 9;
				bipedBody.rotationPointY = armPosY;
				bipedRightArm.rotationPointY = armPosY;
				bipedLeftArm.rotationPointY = armPosY;

				if (t < 10)
				{
					bipedRightLeg.rotationPointY = -(18 / 10 * t) + 18;
					bipedLeftLeg.rotationPointY = -(18 / 10 * t) + 18;
					bipedRightLeg.rotateAngleX = xRotation;
					bipedLeftLeg.rotateAngleX = xRotation;
					bipedHead.rotateAngleX = (float)((t * (Math.PI / 20)) - Math.PI / 2);
				}
				
				track1.rotateAngleX = 0.2745329F + (xRotation * 1.25F);
				track2.rotateAngleX = 0.2745329F + (xRotation * 1.25F);
				bipedHead.rotationPointY = -(24 / 10 * t) + 20;
				
				
				bipedHead.offsetY = 0F;
				bipedBody.offsetY = 0F;
				bipedRightArm.offsetY = 0F;
				bipedLeftArm.offsetY = 0F;
				bipedRightLeg.offsetY = 0F;
				bipedLeftLeg.offsetY = 0F;
				vehichleBody.offsetY = 256F;
				
				if (TFHelper.isPlayerPurge(player) && player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.purgesKatana)
				{
					track2.offsetY = 256F;
				}
				else
				{
					track2.offsetY = 0F;
				}
			}
		}
	}
}