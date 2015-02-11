package fiskfille.transformersmod.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.transformersmod.common.playerdata.TFDataManager;

public class ModelSubwoofer extends ModelTransformer.Biped
{
	ModelRenderer head;
	ModelRenderer helmetPiece1;
	ModelRenderer helmetPiece2;
	ModelRenderer helmetEar1;
	ModelRenderer helmetEar2;
	ModelRenderer helmet;
	ModelRenderer waist;
	ModelRenderer chestPanel1;
	ModelRenderer chestPanel2;
	ModelRenderer chestPanel3;
	ModelRenderer chestPanel4;
	ModelRenderer chestPanel5;
	ModelRenderer hipSlab1;
	ModelRenderer hipSlab2;
	ModelRenderer bumper1;
	ModelRenderer bumper2;
	ModelRenderer upperBumper1;
	ModelRenderer middleBumper1;
	ModelRenderer upperBumper2;
	ModelRenderer middleBumper2;
	ModelRenderer chest;
	ModelRenderer stomach;
	ModelRenderer leg1;
	ModelRenderer upperLegSlab1;
	ModelRenderer upperLegPanel1;
	ModelRenderer lowerLegPanel1;
	ModelRenderer foot1;
	ModelRenderer backWheel1;
	ModelRenderer leg2;
	ModelRenderer upperLegSlab2;
	ModelRenderer upperLegPanel2;
	ModelRenderer lowerLegPanel2;
	ModelRenderer foot2;
	ModelRenderer backWheel2;
	ModelRenderer upperArm1;
	ModelRenderer lowerArm1;
	ModelRenderer lowerArmPanel1;
	ModelRenderer shoulder1;
	ModelRenderer frontWheel1;
	ModelRenderer upperArm2;
	ModelRenderer lowerArm2;
	ModelRenderer lowerArmPanel2;
	ModelRenderer shoulder2;
	ModelRenderer frontWheel2;

	ModelRenderer vehicleBase;
	ModelRenderer vehicleFrontWheel1;
	ModelRenderer vehicleFrontWheel2;
	ModelRenderer vehicleBackWheel1;
	ModelRenderer vehicleBackWheel2;
	ModelRenderer vehicleRoof;
	ModelRenderer vehicleBackWindShield;
	ModelRenderer vehicleWindShield;
	ModelRenderer vehicleDoor1;
	ModelRenderer vehicleDoor2;
	ModelRenderer vehicleBumper1;
	ModelRenderer vehicleBumper2;
	ModelRenderer vehicleUpperBumper1;
	ModelRenderer vehicleUpperBumper2;
	ModelRenderer vehicleMiddleBumper;
	ModelRenderer vehicleTrunk;
	ModelRenderer vehicleHood;

	public ModelRenderer stealthForceBase;
	public ModelRenderer stealthForceRoof;
	public ModelRenderer stealthForceBackWindShield;
	public ModelRenderer stealthForceWindShield;
	public ModelRenderer stealthForceDoor1;
	public ModelRenderer stealthForceDoor2;
	public ModelRenderer stealthForceBumper1;
	public ModelRenderer stealthForceBumper2;
	public ModelRenderer stealthForceUpperBumper1;
	public ModelRenderer stealthForceUpperBumper2;
	public ModelRenderer stealthForceMiddleBumper;
	public ModelRenderer stealthForceTrunk;
	public ModelRenderer stealthForceHood;
	public ModelRenderer stealthForceFrontGun;
	public ModelRenderer stealthForceFrontWheelCover1;
	public ModelRenderer stealthForceFrontWheelCover2;
	public ModelRenderer stealthForceBackWheelCover1;
	public ModelRenderer stealthForceBackWheelCover2;
	public ModelRenderer stealthForceFrontWheel1;
	public ModelRenderer stealthForceFrontWheel2;
	public ModelRenderer stealthForceBackWheel1;
	public ModelRenderer stealthForceSideGun1;
	public ModelRenderer stealthForceBackWheel2;
	public ModelRenderer stealthForceSideGun2;

	public ModelSubwoofer()
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

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-1.5F, -3F, -1.5F, 3, 3, 3);
		head.setRotationPoint(0F, 0F, -0.5F);
		head.setTextureSize(64, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		helmetPiece1 = new ModelRenderer(this, 6, 6);
		helmetPiece1.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		helmetPiece1.setRotationPoint(-1.7F, -1.3F, 0.5F);
		helmetPiece1.setTextureSize(64, 128);
		helmetPiece1.mirror = true;
		setRotation(helmetPiece1, 0.2443461F, -0.0872665F, 0F);
		helmetPiece2 = new ModelRenderer(this, 6, 6);
		helmetPiece2.mirror = true;
		helmetPiece2.addBox(-0.5F, -0.5F, -3F, 1, 1, 3);
		helmetPiece2.setRotationPoint(1.7F, -1.3F, 0.5F);
		helmetPiece2.setTextureSize(64, 128);
		helmetPiece2.mirror = true;
		setRotation(helmetPiece2, 0.2443461F, 0.0872665F, 0F);
		helmetEar1 = new ModelRenderer(this, 0, 6);
		helmetEar1.addBox(-0.5F, -3F, -1F, 1, 3, 2);
		helmetEar1.setRotationPoint(-1.7F, -1.5F, -0.5F);
		helmetEar1.setTextureSize(64, 128);
		helmetEar1.mirror = true;
		setRotation(helmetEar1, 0.2443461F, 0F, 0.1745329F);
		helmetEar2 = new ModelRenderer(this, 0, 6);
		helmetEar2.addBox(-0.5F, -3F, -1F, 1, 3, 2);
		helmetEar2.setRotationPoint(1.7F, -1.5F, -0.5F);
		helmetEar2.setTextureSize(64, 128);
		helmetEar2.mirror = true;
		setRotation(helmetEar2, 0.2443461F, 0F, -0.1745329F);
		helmet = new ModelRenderer(this, 12, 0);
		helmet.addBox(-1.5F, -3F, -1.5F, 3, 3, 3);
		helmet.setRotationPoint(0F, -0.8F, 0F);
		helmet.setTextureSize(64, 128);
		helmet.mirror = true;
		setRotation(helmet, 0.2443461F, 0F, 0F);
		waist = new ModelRenderer(this, 0, 27);
		waist.addBox(-3F, -3F, -1.5F, 6, 3, 3);
		waist.setRotationPoint(0F, 12F, 0F);
		waist.setTextureSize(64, 128);
		waist.mirror = true;
		setRotation(waist, 0F, 0F, 0F);
		chestPanel1 = new ModelRenderer(this, 16, 24);
		chestPanel1.addBox(-1F, -4F, -1F, 2, 4, 1);
		chestPanel1.setRotationPoint(-1F, 6.5F, -1F);
		chestPanel1.setTextureSize(64, 128);
		chestPanel1.mirror = true;
		setRotation(chestPanel1, 0.2792527F, 0.2792527F, -0.4537856F);
		chestPanel2 = new ModelRenderer(this, 16, 24);
		chestPanel2.addBox(-1F, -4F, -1F, 2, 4, 1);
		chestPanel2.setRotationPoint(1F, 6.5F, -1F);
		chestPanel2.setTextureSize(64, 128);
		chestPanel2.mirror = true;
		setRotation(chestPanel2, 0.2792527F, -0.2792527F, 0.4537856F);
		chestPanel3 = new ModelRenderer(this, 22, 20);
		chestPanel3.addBox(-1F, -3F, -1F, 2, 3, 1);
		chestPanel3.setRotationPoint(0F, 6.1F, -0.9F);
		chestPanel3.setTextureSize(64, 128);
		chestPanel3.mirror = true;
		setRotation(chestPanel3, 0.2443461F, 0F, 0F);
		chestPanel4 = new ModelRenderer(this, 16, 20);
		chestPanel4.addBox(-1F, -3F, -1F, 2, 3, 1);
		chestPanel4.setRotationPoint(0F, 3.5F, -1.6F);
		chestPanel4.setTextureSize(64, 128);
		chestPanel4.mirror = true;
		setRotation(chestPanel4, 0F, 0F, 0F);
		chestPanel5 = new ModelRenderer(this, 11, 33);
		chestPanel5.addBox(-1F, -3F, -1F, 2, 3, 1);
		chestPanel5.setRotationPoint(0F, 9.2F, -0.8F);
		chestPanel5.setTextureSize(64, 128);
		chestPanel5.mirror = true;
		setRotation(chestPanel5, 0.0872665F, 0F, 0F);
		hipSlab1 = new ModelRenderer(this, 0, 45);
		hipSlab1.addBox(-4F, -0.5F, -2F, 4, 1, 4);
		hipSlab1.setRotationPoint(0.2F, 12.5F, 0F);
		hipSlab1.setTextureSize(64, 128);
		hipSlab1.mirror = true;
		setRotation(hipSlab1, 0F, 0F, 0.6981317F);
		hipSlab2 = new ModelRenderer(this, 0, 45);
		hipSlab2.addBox(0F, -0.5F, -2F, 4, 1, 4);
		hipSlab2.setRotationPoint(-0.2F, 12.5F, 0F);
		hipSlab2.setTextureSize(64, 128);
		hipSlab2.mirror = true;
		setRotation(hipSlab2, 0F, 0F, -0.6981317F);
		bumper1 = new ModelRenderer(this, 8, 42);
		bumper1.addBox(-3F, -0.5F, -1F, 3, 1, 2);
		bumper1.setRotationPoint(-1F, 2.5F, -1.8F);
		bumper1.setTextureSize(64, 128);
		bumper1.mirror = true;
		setRotation(bumper1, 0F, 0.1745329F, 0.2617994F);
		bumper2 = new ModelRenderer(this, 8, 42);
		bumper2.mirror = true;
		bumper2.addBox(0F, -0.5F, -1F, 3, 1, 2);
		bumper2.setRotationPoint(1F, 2.5F, -1.8F);
		bumper2.setTextureSize(64, 128);
		bumper2.mirror = true;
		setRotation(bumper2, 0F, -0.1745329F, -0.2617994F);
		upperBumper1 = new ModelRenderer(this, 8, 39);
		upperBumper1.addBox(-2F, -0.5F, -1F, 2, 1, 2);
		upperBumper1.setRotationPoint(-2F, 1F, -1.8F);
		upperBumper1.setTextureSize(64, 128);
		upperBumper1.mirror = true;
		setRotation(upperBumper1, 0F, 0.1745329F, 0.0872665F);
		middleBumper1 = new ModelRenderer(this, 16, 35);
		middleBumper1.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		middleBumper1.setRotationPoint(-1.8F, 1.3F, -1.8F);
		middleBumper1.setTextureSize(64, 128);
		middleBumper1.mirror = true;
		setRotation(middleBumper1, 0F, 0F, 0.1919862F);
		upperBumper2 = new ModelRenderer(this, 8, 39);
		upperBumper2.mirror = true;
		upperBumper2.addBox(0F, -0.5F, -1F, 2, 1, 2);
		upperBumper2.setRotationPoint(2F, 1F, -1.8F);
		upperBumper2.setTextureSize(64, 128);
		upperBumper2.mirror = true;
		setRotation(upperBumper2, 0F, -0.1745329F, -0.0872665F);
		middleBumper2 = new ModelRenderer(this, 16, 35);
		middleBumper2.mirror = true;
		middleBumper2.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		middleBumper2.setRotationPoint(1.8F, 1.3F, -1.8F);
		middleBumper2.setTextureSize(64, 128);
		middleBumper2.mirror = true;
		setRotation(middleBumper2, 0F, 0F, -0.1919862F);
		chest = new ModelRenderer(this, 0, 11);
		chest.addBox(-4F, -5F, -2F, 8, 5, 4);
		chest.setRotationPoint(0F, 5F, 0F);
		chest.setTextureSize(64, 128);
		chest.mirror = true;
		setRotation(chest, 0F, 0F, 0F);
		stomach = new ModelRenderer(this, 0, 20);
		stomach.addBox(-2.5F, -4F, -1.5F, 5, 4, 3);
		stomach.setRotationPoint(0F, 9F, 0F);
		stomach.setTextureSize(64, 128);
		stomach.mirror = true;
		setRotation(stomach, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 28, 20);
		leg1.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg1.setRotationPoint(-2.5F, 24F, 0F);
		leg1.setTextureSize(64, 128);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0.0523599F);
		upperLegSlab1 = new ModelRenderer(this, 22, 34);
		upperLegSlab1.addBox(-1F, -3F, -1F, 1, 4, 2);
		upperLegSlab1.setRotationPoint(-2.3F, 15.5F, -1F);
		upperLegSlab1.setTextureSize(64, 128);
		upperLegSlab1.mirror = true;
		setRotation(upperLegSlab1, 0F, 0.2617994F, 0.0698132F);
		upperLegPanel1 = new ModelRenderer(this, 20, 27);
		upperLegPanel1.addBox(-1.5F, -3F, -2F, 2, 5, 2);
		upperLegPanel1.setRotationPoint(-1.1F, 15.5F, 0F);
		upperLegPanel1.setTextureSize(64, 128);
		upperLegPanel1.mirror = true;
		setRotation(upperLegPanel1, 0F, 0F, 0.0698132F);
		lowerLegPanel1 = new ModelRenderer(this, 0, 39);
		lowerLegPanel1.addBox(-1.5F, -4F, -1F, 3, 5, 1);
		lowerLegPanel1.setRotationPoint(-2.4F, 21F, -1F);
		lowerLegPanel1.setTextureSize(64, 128);
		lowerLegPanel1.mirror = true;
		setRotation(lowerLegPanel1, 0.1919862F, 0.1919862F, 0.0698132F);
		foot1 = new ModelRenderer(this, 0, 33);
		foot1.addBox(-1.5F, -1F, -4F, 3, 1, 5);
		foot1.setRotationPoint(-2.5F, 23F, 0.5F);
		foot1.setTextureSize(64, 128);
		foot1.mirror = true;
		setRotation(foot1, 0.2617994F, 0.0872665F, 0F);
		backWheel1 = new ModelRenderer(this, 44, 11);
		backWheel1.addBox(-1F, -1F, -1F, 1, 2, 2);
		backWheel1.setRotationPoint(-3F, 17F, 0F);
		backWheel1.setTextureSize(64, 128);
		backWheel1.mirror = true;
		setRotation(backWheel1, 0.1047198F, 0F, 0.0523599F);
		leg2 = new ModelRenderer(this, 28, 20);
		leg2.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg2.setRotationPoint(2.5F, 24F, 0F);
		leg2.setTextureSize(64, 128);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, -0.0523599F);
		upperLegSlab2 = new ModelRenderer(this, 22, 34);
		upperLegSlab2.addBox(0F, -3F, -1F, 1, 4, 2);
		upperLegSlab2.setRotationPoint(2.3F, 15.5F, -1F);
		upperLegSlab2.setTextureSize(64, 128);
		upperLegSlab2.mirror = true;
		setRotation(upperLegSlab2, 0F, -0.2617994F, -0.0698132F);
		upperLegPanel2 = new ModelRenderer(this, 20, 27);
		upperLegPanel2.addBox(-0.5F, -3F, -2F, 2, 5, 2);
		upperLegPanel2.setRotationPoint(1.1F, 15.5F, 0F);
		upperLegPanel2.setTextureSize(64, 128);
		upperLegPanel2.mirror = true;
		setRotation(upperLegPanel2, 0F, 0F, -0.0698132F);
		lowerLegPanel2 = new ModelRenderer(this, 0, 39);
		lowerLegPanel2.addBox(-1.5F, -4F, -1F, 3, 5, 1);
		lowerLegPanel2.setRotationPoint(2.4F, 21F, -1F);
		lowerLegPanel2.setTextureSize(64, 128);
		lowerLegPanel2.mirror = true;
		setRotation(lowerLegPanel2, 0.1919862F, -0.1919862F, -0.0698132F);
		foot2 = new ModelRenderer(this, 0, 33);
		foot2.addBox(-1.5F, -1F, -4F, 3, 1, 5);
		foot2.setRotationPoint(2.5F, 23F, 0.5F);
		foot2.setTextureSize(64, 128);
		foot2.mirror = true;
		setRotation(foot2, 0.2617994F, -0.0872665F, 0F);
		backWheel2 = new ModelRenderer(this, 44, 11);
		backWheel2.addBox(0F, -1F, -1F, 1, 2, 2);
		backWheel2.setRotationPoint(3F, 17F, 0F);
		backWheel2.setTextureSize(64, 128);
		backWheel2.mirror = true;
		setRotation(backWheel2, 0.1047198F, 0F, -0.0523599F);
		upperArm1 = new ModelRenderer(this, 24, 12);
		upperArm1.addBox(-2.5F, -1F, -1.5F, 3, 5, 3);
		upperArm1.setRotationPoint(-4F, 2F, 0F);
		upperArm1.setTextureSize(64, 128);
		upperArm1.mirror = true;
		setRotation(upperArm1, 0.0872665F, 0F, 0.2094395F);
		lowerArm1 = new ModelRenderer(this, 36, 11);
		lowerArm1.addBox(-1F, -0.2F, -1F, 2, 7, 2);
		lowerArm1.setRotationPoint(-5.8F, 5.2F, 0.5F);
		lowerArm1.setTextureSize(64, 128);
		lowerArm1.mirror = true;
		setRotation(lowerArm1, -0.2443461F, 0F, 0.1396263F);
		lowerArmPanel1 = new ModelRenderer(this, 38, 20);
		lowerArmPanel1.addBox(-1F, -2F, -1.5F, 1, 4, 3);
		lowerArmPanel1.setRotationPoint(-7F, 8.5F, -0.3F);
		lowerArmPanel1.setTextureSize(64, 128);
		lowerArmPanel1.mirror = true;
		setRotation(lowerArmPanel1, -0.2617994F, 0F, 0.0872665F);
		shoulder1 = new ModelRenderer(this, 44, 15);
		shoulder1.addBox(-0.5F, -3F, -1F, 1, 3, 2);
		shoulder1.setRotationPoint(-4.5F, 1.2F, 0F);
		shoulder1.setTextureSize(64, 128);
		shoulder1.mirror = true;
		setRotation(shoulder1, 0F, 0F, 0.1396263F);
		frontWheel1 = new ModelRenderer(this, 44, 11);
		frontWheel1.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		frontWheel1.setRotationPoint(-5.2F, -0.4F, 0F);
		frontWheel1.setTextureSize(64, 128);
		frontWheel1.mirror = true;
		setRotation(frontWheel1, 0.1047198F, 0F, 0.1396263F);
		upperArm2 = new ModelRenderer(this, 24, 12);
		upperArm2.addBox(-0.5F, -1F, -1.5F, 3, 5, 3);
		upperArm2.setRotationPoint(4F, 2F, 0F);
		upperArm2.setTextureSize(64, 128);
		upperArm2.mirror = true;
		setRotation(upperArm2, 0.0872665F, 0F, -0.2094395F);
		lowerArm2 = new ModelRenderer(this, 36, 11);
		lowerArm2.addBox(-1F, -0.2F, -1F, 2, 7, 2);
		lowerArm2.setRotationPoint(5.8F, 5.2F, 0.5F);
		lowerArm2.setTextureSize(64, 128);
		lowerArm2.mirror = true;
		setRotation(lowerArm2, -0.2443461F, 0F, -0.1396263F);
		lowerArmPanel2 = new ModelRenderer(this, 38, 20);
		lowerArmPanel2.addBox(0F, -2F, -1.5F, 1, 4, 3);
		lowerArmPanel2.setRotationPoint(7F, 8.5F, -0.3F);
		lowerArmPanel2.setTextureSize(64, 128);
		lowerArmPanel2.mirror = true;
		setRotation(lowerArmPanel2, -0.2617994F, 0F, -0.0872665F);
		shoulder2 = new ModelRenderer(this, 44, 15);
		shoulder2.addBox(-0.5F, -3F, -1F, 1, 3, 2);
		shoulder2.setRotationPoint(4.5F, 1.2F, 0F);
		shoulder2.setTextureSize(64, 128);
		shoulder2.mirror = true;
		setRotation(shoulder2, 0F, 0F, -0.1396263F);
		frontWheel2 = new ModelRenderer(this, 44, 11);
		frontWheel2.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		frontWheel2.setRotationPoint(5.2F, -0.4F, 0F);
		frontWheel2.setTextureSize(64, 128);
		frontWheel2.mirror = true;
		setRotation(frontWheel2, 0.1047198F, 0F, -0.1396263F);


		this.addChildTo(waist, bipedBody);
		this.addChildTo(hipSlab1, waist);
		this.addChildTo(hipSlab2, waist);
		this.addChildTo(stomach, waist);
		this.addChildTo(chest, stomach);
		this.addChildTo(chestPanel1, chest);
		this.addChildTo(chestPanel2, chest);
		this.addChildTo(chestPanel3, chest);
		this.addChildTo(chestPanel4, chest);
		this.addChildTo(chestPanel5, stomach);
		this.addChildTo(bumper1, chest);
		this.addChildTo(bumper2, chest);
		this.addChildTo(upperBumper1, chest);
		this.addChildTo(upperBumper2, chest);
		this.addChildTo(middleBumper1, chest);
		this.addChildTo(middleBumper2, chest);
		this.addChildTo(leg1, bipedRightLeg);
		this.addChildTo(upperLegSlab1, leg1);
		this.addChildTo(upperLegPanel1, leg1);
		this.addChildTo(lowerLegPanel1, leg1);
		this.addChildTo(foot1, leg1);
		this.addChildTo(backWheel1, leg1);
		this.addChildTo(leg2, bipedLeftLeg);
		this.addChildTo(upperLegSlab2, leg2);
		this.addChildTo(upperLegPanel2, leg2);
		this.addChildTo(lowerLegPanel2, leg2);
		this.addChildTo(foot2, leg2);
		this.addChildTo(backWheel2, leg2);
		this.addChildTo(upperArm1, bipedRightArm);
		this.addChildTo(lowerArm1, upperArm1);
		this.addChildTo(lowerArmPanel1, lowerArm1);
		this.addChildTo(shoulder1, upperArm1);
		this.addChildTo(frontWheel1, shoulder1);
		this.addChildTo(upperArm2, bipedLeftArm);
		this.addChildTo(lowerArm2, upperArm2);
		this.addChildTo(lowerArmPanel2, lowerArm2);
		this.addChildTo(shoulder2, upperArm2);
		this.addChildTo(frontWheel2, shoulder2);
		this.addChildTo(head, bipedHead);
		this.addChildTo(helmet, bipedHead);
		this.addChildTo(helmetPiece1, bipedHead);
		this.addChildTo(helmetPiece2, bipedHead);
		this.addChildTo(helmetEar1, bipedHead);
		this.addChildTo(helmetEar2, bipedHead);


		vehicleBase = new ModelRenderer(this, 0, 64);
		vehicleBase.addBox(-3F, -1F, -2F, 6, 2, 12);
		vehicleBase.setRotationPoint(0F, 22.5F, -4.2F);
		vehicleBase.setTextureSize(64, 128);
		vehicleBase.mirror = true;
		setRotation(vehicleBase, 0F, 0F, 0F);
		vehicleFrontWheel1 = new ModelRenderer(this, 0, 64);
		vehicleFrontWheel1.addBox(-1F, -1F, -1F, 1, 2, 2);
		vehicleFrontWheel1.setRotationPoint(-2.3F, 23F, -4.5F);
		vehicleFrontWheel1.setTextureSize(64, 128);
		vehicleFrontWheel1.mirror = true;
		setRotation(vehicleFrontWheel1, 0F, 0F, 0F);
		vehicleFrontWheel2 = new ModelRenderer(this, 0, 64);
		vehicleFrontWheel2.addBox(0F, -1F, -1F, 1, 2, 2);
		vehicleFrontWheel2.setRotationPoint(2.3F, 23F, -4.5F);
		vehicleFrontWheel2.setTextureSize(64, 128);
		vehicleFrontWheel2.mirror = true;
		setRotation(vehicleFrontWheel2, 0F, 0F, 0F);
		vehicleBackWheel1 = new ModelRenderer(this, 0, 64);
		vehicleBackWheel1.addBox(-1F, -1F, -1F, 1, 2, 2);
		vehicleBackWheel1.setRotationPoint(-2.3F, 23F, 4F);
		vehicleBackWheel1.setTextureSize(64, 128);
		vehicleBackWheel1.mirror = true;
		setRotation(vehicleBackWheel1, 0F, 0F, 0F);
		vehicleBackWheel2 = new ModelRenderer(this, 0, 64);
		vehicleBackWheel2.addBox(0F, -1F, -1F, 1, 2, 2);
		vehicleBackWheel2.setRotationPoint(2.3F, 23F, 4F);
		vehicleBackWheel2.setTextureSize(64, 128);
		vehicleBackWheel2.mirror = true;
		setRotation(vehicleBackWheel2, 0F, 0F, 0F);
		vehicleRoof = new ModelRenderer(this, 18, 83);
		vehicleRoof.addBox(-2.5F, -0.5F, 0F, 5, 1, 2);
		vehicleRoof.setRotationPoint(0F, 20.7F, -0.5F);
		vehicleRoof.setTextureSize(64, 128);
		vehicleRoof.mirror = true;
		setRotation(vehicleRoof, 0F, 0F, 0F);
		vehicleBackWindShield = new ModelRenderer(this, 14, 86);
		vehicleBackWindShield.addBox(-2.5F, -0.5F, 0F, 5, 1, 4);
		vehicleBackWindShield.setRotationPoint(0F, 20.7F, 1.3F);
		vehicleBackWindShield.setTextureSize(64, 128);
		vehicleBackWindShield.mirror = true;
		setRotation(vehicleBackWindShield, -0.2617994F, 0F, 0F);
		vehicleWindShield = new ModelRenderer(this, 13, 78);
		vehicleWindShield.addBox(-2.5F, -0.5F, -4F, 5, 1, 4);
		vehicleWindShield.setRotationPoint(0F, 20.7F, -0.3F);
		vehicleWindShield.setTextureSize(64, 128);
		vehicleWindShield.mirror = true;
		setRotation(vehicleWindShield, 0.3316126F, 0F, 0F);
		vehicleDoor1 = new ModelRenderer(this, 24, 64);
		vehicleDoor1.addBox(-1F, -1F, -1.5F, 1, 1, 3);
		vehicleDoor1.setRotationPoint(-1.8F, 21.7F, 0.5F);
		vehicleDoor1.setTextureSize(64, 128);
		vehicleDoor1.mirror = true;
		setRotation(vehicleDoor1, 0F, 0F, 0.2268928F);
		vehicleDoor2 = new ModelRenderer(this, 24, 64);
		vehicleDoor2.addBox(0F, -1F, -1.5F, 1, 1, 3);
		vehicleDoor2.setRotationPoint(1.8F, 21.7F, 0.5F);
		vehicleDoor2.setTextureSize(64, 128);
		vehicleDoor2.mirror = true;
		setRotation(vehicleDoor2, 0F, 0F, -0.2268928F);
		vehicleBumper1 = new ModelRenderer(this, 0, 70);
		vehicleBumper1.addBox(-2.5F, -0.5F, -2F, 3, 1, 1);
		vehicleBumper1.setRotationPoint(0F, 23F, -5.1F);
		vehicleBumper1.setTextureSize(64, 128);
		vehicleBumper1.mirror = true;
		setRotation(vehicleBumper1, 0F, 0.2443461F, 0F);
		vehicleBumper2 = new ModelRenderer(this, 0, 70);
		vehicleBumper2.mirror = true;
		vehicleBumper2.addBox(-0.5F, -0.5F, -2F, 3, 1, 1);
		vehicleBumper2.setRotationPoint(0F, 23F, -5.1F);
		vehicleBumper2.setTextureSize(64, 128);
		vehicleBumper2.mirror = true;
		setRotation(vehicleBumper2, 0F, -0.2443461F, 0F);
		vehicleUpperBumper1 = new ModelRenderer(this, 0, 68);
		vehicleUpperBumper1.addBox(-2.5F, -1.5F, -2F, 2, 1, 1);
		vehicleUpperBumper1.setRotationPoint(0F, 23.5F, -5.3F);
		vehicleUpperBumper1.setTextureSize(64, 128);
		vehicleUpperBumper1.mirror = true;
		setRotation(vehicleUpperBumper1, -0.2443461F, 0.2443461F, 0F);
		vehicleUpperBumper2 = new ModelRenderer(this, 0, 68);
		vehicleUpperBumper2.mirror = true;
		vehicleUpperBumper2.addBox(0.5F, -1.5F, -2F, 2, 1, 1);
		vehicleUpperBumper2.setRotationPoint(0F, 23.5F, -5.3F);
		vehicleUpperBumper2.setTextureSize(64, 128);
		vehicleUpperBumper2.mirror = true;
		setRotation(vehicleUpperBumper2, -0.2443461F, -0.2443461F, 0F);
		vehicleMiddleBumper = new ModelRenderer(this, 6, 68);
		vehicleMiddleBumper.addBox(-1F, -1.5F, -2F, 2, 1, 1);
		vehicleMiddleBumper.setRotationPoint(0F, 23.5F, -5.1F);
		vehicleMiddleBumper.setTextureSize(64, 128);
		vehicleMiddleBumper.mirror = true;
		setRotation(vehicleMiddleBumper, -0.2443461F, 0F, 0F);
		vehicleTrunk = new ModelRenderer(this, 0, 84);
		vehicleTrunk.addBox(-2.5F, -1F, 0F, 5, 1, 2);
		vehicleTrunk.setRotationPoint(0F, 22.2F, 3.6F);
		vehicleTrunk.setTextureSize(64, 128);
		vehicleTrunk.mirror = true;
		setRotation(vehicleTrunk, 0F, 0F, 0F);
		vehicleHood = new ModelRenderer(this, 0, 78);
		vehicleHood.addBox(-2F, -1F, 0F, 4, 1, 5);
		vehicleHood.setRotationPoint(0F, 22.5F, -6.3F);
		vehicleHood.setTextureSize(64, 128);
		vehicleHood.mirror = true;
		setRotation(vehicleHood, 0.1047198F, 0F, 0F);


		this.addChildTo(vehicleFrontWheel1, vehicleBase);
		this.addChildTo(vehicleFrontWheel2, vehicleBase);
		this.addChildTo(vehicleBackWheel1, vehicleBase);
		this.addChildTo(vehicleBackWheel2, vehicleBase);
		this.addChildTo(vehicleRoof, vehicleBase);
		this.addChildTo(vehicleBackWindShield, vehicleBase);
		this.addChildTo(vehicleWindShield, vehicleBase);
		this.addChildTo(vehicleDoor1, vehicleBase);
		this.addChildTo(vehicleDoor2, vehicleBase);
		this.addChildTo(vehicleBumper1, vehicleBase);
		this.addChildTo(vehicleBumper2, vehicleBase);
		this.addChildTo(vehicleUpperBumper1, vehicleBase);
		this.addChildTo(vehicleUpperBumper2, vehicleBase);
		this.addChildTo(vehicleMiddleBumper, vehicleBase);
		this.addChildTo(vehicleTrunk, vehicleBase);
		this.addChildTo(vehicleHood, vehicleBase);


		this.stealthForceFrontWheelCover2 = new ModelRenderer(this, 31, 68);
		this.stealthForceFrontWheelCover2.setRotationPoint(2.5F, 0.0F, 0.0F);
		this.stealthForceFrontWheelCover2.addBox(0.0F, -1.0F, -2.0F, 1, 2, 3, 0.0F);
		this.stealthForceTrunk = new ModelRenderer(this, 0, 84);
		this.stealthForceTrunk.setRotationPoint(0.0F, -0.3F, 7.8F);
		this.stealthForceTrunk.addBox(-2.5F, -1.0F, 0.0F, 5, 1, 2, 0.0F);
		this.stealthForceUpperBumper1 = new ModelRenderer(this, 0, 68);
		this.stealthForceUpperBumper1.setRotationPoint(0.0F, 1.0F, -1.1F);
		this.stealthForceUpperBumper1.addBox(-2.5F, -1.5F, -2.0F, 2, 1, 1, 0.0F);
		this.setRotation(stealthForceUpperBumper1, -0.24434609527920614F, 0.24434609527920614F, 0.0F);
		this.stealthForceBackWheelCover2 = new ModelRenderer(this, 24, 68);
		this.stealthForceBackWheelCover2.setRotationPoint(2.5F, 0.0F, 9.0F);
		this.stealthForceBackWheelCover2.addBox(0.0F, -1.0F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotation(stealthForceBackWheelCover2, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
		this.stealthForceBumper1 = new ModelRenderer(this, 0, 70);
		this.stealthForceBumper1.setRotationPoint(-1.0F, 0.5F, -0.9F);
		this.stealthForceBumper1.addBox(-2.5F, -0.5F, -2.0F, 3, 1, 1, 0.0F);
		this.setRotation(stealthForceBumper1, 0.0F, 0.24434609527920614F, 0.3490658503988659F);
		this.stealthForceSideGun1 = new ModelRenderer(this, 29, 64);
		this.stealthForceSideGun1.setRotationPoint(-0.4F, 0.0F, -4.0F);
		this.stealthForceSideGun1.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.stealthForceSideGun2 = new ModelRenderer(this, 29, 64);
		this.stealthForceSideGun2.setRotationPoint(0.4F, 0.0F, -4.0F);
		this.stealthForceSideGun2.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.stealthForceBackWheel2 = new ModelRenderer(this, 0, 64);
		this.stealthForceBackWheel2.setRotationPoint(0.3F, 0.5F, -0.8F);
		this.stealthForceBackWheel2.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.setRotation(stealthForceBackWheel2, 0.2617993877991494F, 0.0F, 0.0F);
		this.stealthForceHood = new ModelRenderer(this, 0, 78);
		this.stealthForceHood.setRotationPoint(0.0F, 0.0F, -2.1F);
		this.stealthForceHood.addBox(-2.0F, -1.0F, 0.0F, 4, 1, 5, 0.0F);
		this.setRotation(stealthForceHood, 0.10471975511965977F, -0.0F, 0.0F);
		this.stealthForceFrontWheelCover1 = new ModelRenderer(this, 31, 68);
		this.stealthForceFrontWheelCover1.setRotationPoint(-2.5F, 0.0F, 0.0F);
		this.stealthForceFrontWheelCover1.addBox(-1.0F, -1.0F, -2.0F, 1, 2, 3, 0.0F);
		this.stealthForceBase = new ModelRenderer(this, 0, 64);
		this.stealthForceBase.setRotationPoint(0.0F, 22.5F, -4.2F);
		this.stealthForceBase.addBox(-3.0F, -1.0F, -2.0F, 6, 2, 12, 0.0F);
		this.stealthForceFrontWheel1 = new ModelRenderer(this, 0, 64);
		this.stealthForceFrontWheel1.setRotationPoint(-0.3F, 0.5F, -0.5F);
		this.stealthForceFrontWheel1.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.setRotation(stealthForceFrontWheel1, 0.0F, 0.0F, -0.2617993877991494F);
		this.stealthForceBackWindShield = new ModelRenderer(this, 14, 86);
		this.stealthForceBackWindShield.setRotationPoint(0.0F, -1.8F, 5.5F);
		this.stealthForceBackWindShield.addBox(-2.5F, -0.5F, 0.0F, 5, 1, 4, 0.0F);
		this.setRotation(stealthForceBackWindShield, -0.2617993877991494F, -0.0F, 0.0F);
		this.stealthForceWindShield = new ModelRenderer(this, 13, 78);
		this.stealthForceWindShield.setRotationPoint(0.0F, -1.8F, 3.8F);
		this.stealthForceWindShield.addBox(-2.5F, -0.5F, -4.0F, 5, 1, 4, 0.0F);
		this.setRotation(stealthForceWindShield, 0.33161255787892263F, -0.0F, 0.0F);
		this.stealthForceBumper2 = new ModelRenderer(this, 0, 70);
		this.stealthForceBumper2.mirror = true;
		this.stealthForceBumper2.setRotationPoint(1.0F, 0.5F, -0.9F);
		this.stealthForceBumper2.addBox(-0.5F, -0.5F, -2.0F, 3, 1, 1, 0.0F);
		this.setRotation(stealthForceBumper2, 0.0F, -0.24434609527920614F, -0.3490658503988659F);
		this.stealthForceUpperBumper2 = new ModelRenderer(this, 0, 68);
		this.stealthForceUpperBumper2.mirror = true;
		this.stealthForceUpperBumper2.setRotationPoint(0.0F, 1.0F, -1.1F);
		this.stealthForceUpperBumper2.addBox(0.5F, -1.5F, -2.0F, 2, 1, 1, 0.0F);
		this.setRotation(stealthForceUpperBumper2, -0.24434609527920614F, -0.24434609527920614F, 0.0F);
		this.stealthForceDoor1 = new ModelRenderer(this, 24, 64);
		this.stealthForceDoor1.setRotationPoint(-1.8F, -0.8F, 4.7F);
		this.stealthForceDoor1.addBox(-1.0F, -1.0F, -1.5F, 1, 1, 3, 0.0F);
		this.setRotation(stealthForceDoor1, 0.0F, -0.0F, 0.22689280275926282F);
		this.stealthForceDoor2 = new ModelRenderer(this, 24, 64);
		this.stealthForceDoor2.setRotationPoint(1.8F, -0.8F, 4.7F);
		this.stealthForceDoor2.addBox(0.0F, -1.0F, -1.5F, 1, 1, 3, 0.0F);
		this.setRotation(stealthForceDoor2, 0.0F, -0.0F, -0.22689280275926282F);
		this.stealthForceFrontWheel2 = new ModelRenderer(this, 0, 64);
		this.stealthForceFrontWheel2.setRotationPoint(0.3F, 0.5F, -0.5F);
		this.stealthForceFrontWheel2.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.setRotation(stealthForceFrontWheel2, 0.0F, 0.0F, 0.2617993877991494F);
		this.stealthForceFrontGun = new ModelRenderer(this, 29, 64);
		this.stealthForceFrontGun.setRotationPoint(0.0F, 0.5F, -2.0F);
		this.stealthForceFrontGun.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.stealthForceBackWheelCover1 = new ModelRenderer(this, 24, 68);
		this.stealthForceBackWheelCover1.setRotationPoint(-2.5F, 0.0F, 9.0F);
		this.stealthForceBackWheelCover1.addBox(-1.0F, -1.0F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotation(stealthForceBackWheelCover1, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
		this.stealthForceBackWheel1 = new ModelRenderer(this, 0, 64);
		this.stealthForceBackWheel1.setRotationPoint(-0.3F, 0.5F, -0.8F);
		this.stealthForceBackWheel1.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.setRotation(stealthForceBackWheel1, 0.2617993877991494F, 0.0F, 0.0F);
		this.stealthForceRoof = new ModelRenderer(this, 18, 83);
		this.stealthForceRoof.setRotationPoint(0.0F, -1.8F, 3.6F);
		this.stealthForceRoof.addBox(-2.5F, -0.5F, 0.0F, 5, 1, 2, 0.0F);
		this.stealthForceMiddleBumper = new ModelRenderer(this, 6, 68);
		this.stealthForceMiddleBumper.setRotationPoint(0.0F, 1.0F, -0.9F);
		this.stealthForceMiddleBumper.addBox(-1.0F, -1.5F, -2.0F, 2, 1, 1, 0.0F);
		this.setRotation(stealthForceMiddleBumper, -0.24434609527920614F, -0.0F, 0.0F);
		this.stealthForceBase.addChild(this.stealthForceFrontWheelCover2);
		this.stealthForceBase.addChild(this.stealthForceTrunk);
		this.stealthForceBase.addChild(this.stealthForceUpperBumper1);
		this.stealthForceBase.addChild(this.stealthForceBackWheelCover2);
		this.stealthForceBase.addChild(this.stealthForceBumper1);
		this.stealthForceBackWheelCover1.addChild(this.stealthForceSideGun1);
		this.stealthForceBackWheelCover2.addChild(this.stealthForceSideGun2);
		this.stealthForceBackWheelCover2.addChild(this.stealthForceBackWheel2);
		this.stealthForceBase.addChild(this.stealthForceHood);
		this.stealthForceBase.addChild(this.stealthForceFrontWheelCover1);
		this.stealthForceFrontWheelCover1.addChild(this.stealthForceFrontWheel1);
		this.stealthForceBase.addChild(this.stealthForceBackWindShield);
		this.stealthForceBase.addChild(this.stealthForceWindShield);
		this.stealthForceBase.addChild(this.stealthForceBumper2);
		this.stealthForceBase.addChild(this.stealthForceUpperBumper2);
		this.stealthForceBase.addChild(this.stealthForceDoor1);
		this.stealthForceBase.addChild(this.stealthForceDoor2);
		this.stealthForceFrontWheelCover2.addChild(this.stealthForceFrontWheel2);
		this.stealthForceBase.addChild(this.stealthForceFrontGun);
		this.stealthForceBase.addChild(this.stealthForceBackWheelCover1);
		this.stealthForceBackWheelCover1.addChild(this.stealthForceBackWheel1);
		this.stealthForceBase.addChild(this.stealthForceRoof);
		this.stealthForceBase.addChild(this.stealthForceMiddleBumper);


		adjustPos();
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		vehicleBase.render(f5);
		stealthForceBase.render(f5);
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

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			if (TFDataManager.getTransformationTimer(player) == 0)
			{
				for (ModelRenderer modelRenderer : new ModelRenderer[] {stealthForceBase, vehicleBase})
				{
					float f1 = this.bipedHead.rotateAngleY - (this.bipedBody.rotateAngleY - this.bipedHead.rotateAngleY) / 3;
					if (modelRenderer.rotateAngleY < f1) {modelRenderer.rotateAngleY += 0.05F;}
					if (modelRenderer.rotateAngleY > f1) {modelRenderer.rotateAngleY -= 0.05F;}
					modelRenderer.rotateAngleY = f1;

					if (player == Minecraft.getMinecraft().thePlayer)
					{
						modelRenderer.rotateAngleX = -(float)player.motionY - 0.0784000015258789F;
					}
					else
					{
						modelRenderer.rotateAngleX = -(float)(player.posY - player.prevPosY) * 1.5F;
					}
				}

				if (TFDataManager.getStealthModeTimer(player) == 5)
				{
					vehicleFrontWheel1.rotateAngleX = par1;
					vehicleFrontWheel2.rotateAngleX = par1;
					vehicleBackWheel1.rotateAngleX = par1;
					vehicleBackWheel2.rotateAngleX = par1;
					vehicleBase.offsetY = 0F;
					stealthForceBase.offsetY = 256F;
				}
				else
				{
					int t = TFDataManager.getStealthModeTimer(player) * 2;
					float f = (10 - t);
					
					stealthForceBackWheelCover1.rotationPointX = -2 - (f / 10) / 2;
					stealthForceBackWheelCover2.rotationPointX = 2 + (f / 10) / 2;
					stealthForceBackWheelCover1.rotateAngleZ = -0.2617993877991494F * (f / 10);
					stealthForceBackWheelCover2.rotateAngleZ = 0.2617993877991494F * (f / 10);
					stealthForceBackWheelCover1.rotateAngleX = -0.2617993877991494F * (f / 10);
					stealthForceBackWheelCover2.rotateAngleX = -0.2617993877991494F * (f / 10);
					
					stealthForceFrontWheelCover1.rotationPointX = -2 - (f / 10) / 2;
					stealthForceFrontWheelCover2.rotationPointX = 2 + (f / 10) / 2;
					stealthForceFrontWheel1.rotateAngleZ = -0.2617993877991494F * (f / 10);
					stealthForceFrontWheel2.rotateAngleZ = 0.2617993877991494F * (f / 10);
					stealthForceBumper1.rotationPointX = -(f / 10);
					stealthForceBumper2.rotationPointX = (f / 10);
					stealthForceBumper1.rotateAngleZ = 0.3490658503988659F * (f / 10);
					stealthForceBumper2.rotateAngleZ = -0.3490658503988659F * (f / 10);
					stealthForceFrontGun.rotationPointZ = 0 - (f / 10) * 2;
					
					stealthForceFrontWheel1.rotateAngleX = par1;
					stealthForceFrontWheel2.rotateAngleX = par1;
					stealthForceBackWheel1.rotateAngleX = par1 + 0.2617993877991494F;
					stealthForceBackWheel2.rotateAngleX = par1 + 0.2617993877991494F;
					stealthForceBase.offsetY = 0F;
					vehicleBase.offsetY = 256F;
				}

				bipedHead.offsetY = 256F;
				bipedBody.offsetY = 256F;
				bipedRightArm.offsetY = 256F;
				bipedLeftArm.offsetY = 256F;
				bipedRightLeg.offsetY = 256F;
				bipedLeftLeg.offsetY = 256F;
			}
			else
			{
				int t = TFDataManager.getTransformationTimer(player);
				float f = (float)(20 - t) / 2;

				vehicleBase.rotateAngleX = 0;
				chestPanel4.rotationPointY = f / 2 - 1.6F;
				bumper1.rotateAngleZ = -(f * 0.02617994F) + 0.2617994F;
				bumper2.rotateAngleZ = (f * 0.02617994F) - 0.2617994F;
				middleBumper1.rotateAngleZ = -(f * 0.01919862F) + 0.1919862F;
				middleBumper2.rotateAngleZ = (f * 0.01919862F) - 0.1919862F;

				bipedBody.rotationPointY = f * 2.5F;
				bipedBody.rotationPointZ = f / 10 * -7.5F;
				bipedRightArm.rotationPointZ = f / 10 * -5F;
				bipedLeftArm.rotationPointZ = f / 10 * -5F;
				chest.rotationPointZ = -3.0F / 10 * f;
				chest.rotateAngleX = -pi / 2 / 10 * f;
				bipedRightArm.rotationPointY = f * 2;
				bipedLeftArm.rotationPointY = f * 2;
				bipedRightArm.rotationPointX = f / 3.5F - 5;
				bipedLeftArm.rotationPointX = -f / 3.5F + 5;
				bipedRightLeg.rotateAngleZ = f * pi / 10;
				bipedLeftLeg.rotateAngleZ = f * pi / 10;
				bipedRightLeg.rotationPointX = -f / 7.5F;
				bipedLeftLeg.rotationPointX = -f / 7.5F;

				if (t < 20)
				{
					bipedRightLeg.rotateAngleX = f * pi / 2 / 10;
					bipedLeftLeg.rotateAngleX = f * pi / 2 / 10;
					bipedRightLeg.rotationPointY = f * 1.3F + 12;
					bipedLeftLeg.rotationPointY = f * 1.3F + 12;
					bipedRightLeg.rotationPointZ = -f / 2;
					bipedLeftLeg.rotationPointZ = -f / 2;
					bipedBody.rotateAngleX = pi / 2 / 10 * f;
					bipedHead.rotationPointY = f * 2.5F;
					bipedRightArm.rotateAngleX = f / 10 * 1.5F;
					bipedLeftArm.rotateAngleX = f / 10 * 1.5F;
				}

				bipedHead.offsetY = 0F;
				bipedBody.offsetY = 0F;
				bipedRightArm.offsetY = 0F;
				bipedLeftArm.offsetY = 0F;
				bipedRightLeg.offsetY = 0F;
				bipedLeftLeg.offsetY = 0F;
				vehicleBase.offsetY = 256F;
				stealthForceBase.offsetY = 256F;
			}
		}
	}

	public void adjustPos()
	{
		// Body
		chest.rotationPointY = -4F;
		bumper1.rotationPointY = -2.5F;
		bumper2.rotationPointY = -2.5F;
		upperBumper1.rotationPointY = -4.0F;
		upperBumper2.rotationPointY = -4.0F;
		middleBumper1.rotationPointY = -3.7F;
		middleBumper2.rotationPointY = -3.7F;
		chestPanel1.rotationPointY = 1.25F;
		chestPanel2.rotationPointY = 1.25F;
		chestPanel3.rotationPointY = 1.0F;
		chestPanel4.rotationPointY = -1.6F;
		chestPanel5.rotationPointY = 0.15F;

		// Arms
		upperArm1.rotationPointX = 1F;
		lowerArm1.setRotationPoint(-1F, 3.5F, 0);
		lowerArmPanel1.setRotationPoint(-0.8F, 3.75F, 0F);
		setRotation(lowerArmPanel1, 0F, 0F, -0.0872665F);
		upperArm2.rotationPointX = -1F;
		lowerArm2.setRotationPoint(1F, 3.5F, 0);
		lowerArmPanel2.setRotationPoint(0.8F, 3.75F, 0F);
		setRotation(lowerArmPanel2, 0F, 0F, 0.0872665F);
		frontWheel1.rotationPointX = -1.0F;
		frontWheel1.rotationPointY = -1.5F;
		frontWheel1.rotateAngleZ = 0F;
		frontWheel2.rotationPointX = 1.0F;
		frontWheel2.rotationPointY = -1.5F;
		frontWheel2.rotateAngleZ = 0F;

		// Legs
		leg1.rotationPointY = 12.0F;
		upperLegSlab1.rotationPointX = -0.25F;
		upperLegPanel1.rotationPointX = 1.0F;
		leg2.rotationPointY = 12.0F;
		upperLegSlab2.rotationPointX = 0.25F;
		upperLegPanel2.rotationPointX = -1.0F;
	}
}