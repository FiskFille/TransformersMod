package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.common.playerdata.TFDataManager;

public class ModelVurp extends ModelChildBase.Biped
{
	ModelRenderer leg1;
	ModelRenderer foot1;
	ModelRenderer backWheel1;
	ModelRenderer upperLegPanel1;
	ModelRenderer lowerLegPanel1;
	ModelRenderer leg2;
	ModelRenderer foot2;
	ModelRenderer backWheel2;
	ModelRenderer upperLegPanel2;
	ModelRenderer lowerLegPanel2;
	ModelRenderer hipSlab1;
	ModelRenderer hipSlab2;
	ModelRenderer windShield;
	ModelRenderer chest;
	ModelRenderer frontCar1;
	ModelRenderer frontCar2;
	ModelRenderer torso;
	ModelRenderer waist;
	ModelRenderer chestPanel1;
	ModelRenderer chestPanel2;
	ModelRenderer chestPanel3;
	public ModelRenderer upperArm1;
	ModelRenderer frontWheel1;
	public ModelRenderer lowerArm1;
	ModelRenderer armPanel1;
	ModelRenderer upperArm2;
	ModelRenderer lowerArm2;
	ModelRenderer armPanel2;
	ModelRenderer frontWheel2;
	ModelRenderer head;
	ModelRenderer ear1;
	ModelRenderer ear2;
	ModelRenderer mask;

	ModelRenderer vehicleFrontWheel1;
	ModelRenderer vehicleFrontWheel2;
	ModelRenderer vehicleBackWheel1;
	ModelRenderer vehicleBackWheel2;
	ModelRenderer vehicleBumper1;
	ModelRenderer vehicleBumper2;
	ModelRenderer vehicleRoof;
	ModelRenderer vehicleBody;
	ModelRenderer vehicleHood;
	ModelRenderer vehicleBackWindShield;
	ModelRenderer vehicleWindShield;
	ModelRenderer vehicleSpoiler;
	ModelRenderer vehicleDoor1;
	ModelRenderer vehicleDoor2;
	
	public ModelRenderer stealthForceBase;
	public ModelRenderer stealthForceBackWheelCover1;
	public ModelRenderer stealthForceBackWheelCover2;
	public ModelRenderer stealthForceBackWindShield;
	public ModelRenderer stealthForceRoof;
	public ModelRenderer stealthForceBumper1;
	public ModelRenderer stealthForceBumper2;
	public ModelRenderer stealthForceWindShield;
	public ModelRenderer stealthForceSpoiler;
	public ModelRenderer stealthForceDoor1;
	public ModelRenderer stealthForceDoor2;
	public ModelRenderer stealthForceFrontWheelCover1;
	public ModelRenderer stealthForceFrontWheelCover2;
	public ModelRenderer stealthForceFrontGun;
	public ModelRenderer stealthForceHood;
	public ModelRenderer stealthForceGun1;
	public ModelRenderer stealthForceGun2;
	public ModelRenderer stealthForceBackWheel1;
	public ModelRenderer stealthForceBackWheel2;
	public ModelRenderer stealthForceFrontWheel1;
	public ModelRenderer stealthForceFrontWheel2;
	public ModelRenderer stealthForceHoodGun2;
	public ModelRenderer stealthForceHoodGun1;

	public ModelVurp()
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

		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg1.setRotationPoint(-2.5F, 24F, 0F);
		leg1.setTextureSize(64, 128);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0.0523599F);
		foot1 = new ModelRenderer(this, 2, 33);
		foot1.addBox(-1.5F, -0.5F, -5F, 3, 1, 6);
		foot1.setRotationPoint(-2.5F, 22.5F, 1F);
		foot1.setTextureSize(64, 128);
		foot1.mirror = true;
		setRotation(foot1, 0.2443461F, 0F, 0F);
		backWheel1 = new ModelRenderer(this, 0, 40);
		backWheel1.addBox(-1F, -1F, -1F, 1, 2, 2);
		backWheel1.setRotationPoint(-3.5F, 22.5F, 0F);
		backWheel1.setTextureSize(64, 128);
		backWheel1.mirror = true;
		setRotation(backWheel1, 0F, 0F, 0F);
		upperLegPanel1 = new ModelRenderer(this, 0, 31);
		upperLegPanel1.addBox(-1.5F, -3F, -1F, 3, 4, 1);
		upperLegPanel1.setRotationPoint(-2.1F, 15F, -1.2F);
		upperLegPanel1.setTextureSize(64, 128);
		upperLegPanel1.mirror = true;
		setRotation(upperLegPanel1, 0F, 0.1919862F, 0.0523599F);
		lowerLegPanel1 = new ModelRenderer(this, 10, 16);
		lowerLegPanel1.addBox(-1.5F, -5F, -1F, 3, 6, 1);
		lowerLegPanel1.setRotationPoint(-2.5F, 21F, -1F);
		lowerLegPanel1.setTextureSize(64, 128);
		lowerLegPanel1.mirror = true;
		setRotation(lowerLegPanel1, 0.2443461F, 0.0872665F, 0.0523599F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1F, -12F, -1.5F, 2, 12, 3);
		leg2.setRotationPoint(2.5F, 24F, 0F);
		leg2.setTextureSize(64, 128);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, -0.0523599F);
		foot2 = new ModelRenderer(this, 2, 33);
		foot2.addBox(-1.5F, -0.5F, -5F, 3, 1, 6);
		foot2.setRotationPoint(2.5F, 22.5F, 1F);
		foot2.setTextureSize(64, 128);
		foot2.mirror = true;
		setRotation(foot2, 0.2443461F, 0F, 0F);
		backWheel2 = new ModelRenderer(this, 0, 40);
		backWheel2.addBox(0F, -1F, -1F, 1, 2, 2);
		backWheel2.setRotationPoint(3.5F, 22.5F, 0F);
		backWheel2.setTextureSize(64, 128);
		backWheel2.mirror = true;
		setRotation(backWheel2, 0F, 0F, 0F);
		upperLegPanel2 = new ModelRenderer(this, 0, 31);
		upperLegPanel2.addBox(-1.5F, -3F, -1F, 3, 4, 1);
		upperLegPanel2.setRotationPoint(2.1F, 15F, -1.2F);
		upperLegPanel2.setTextureSize(64, 128);
		upperLegPanel2.mirror = true;
		setRotation(upperLegPanel2, 0F, -0.1919862F, -0.0523599F);
		lowerLegPanel2 = new ModelRenderer(this, 10, 16);
		lowerLegPanel2.addBox(-1.5F, -5F, -1F, 3, 6, 1);
		lowerLegPanel2.setRotationPoint(2.5F, 21F, -1F);
		lowerLegPanel2.setTextureSize(64, 128);
		lowerLegPanel2.mirror = true;
		setRotation(lowerLegPanel2, 0.2443461F, -0.0872665F, -0.0523599F);
		hipSlab1 = new ModelRenderer(this, 18, 16);
		hipSlab1.addBox(-0.5F, -4F, -2F, 1, 4, 4);
		hipSlab1.setRotationPoint(0F, 12.7F, 0F);
		hipSlab1.setTextureSize(64, 128);
		hipSlab1.mirror = true;
		setRotation(hipSlab1, 0F, 0F, -0.7853982F);
		hipSlab2 = new ModelRenderer(this, 18, 16);
		hipSlab2.addBox(-0.5F, -4F, -2F, 1, 4, 4);
		hipSlab2.setRotationPoint(0F, 12.7F, 0F);
		hipSlab2.setTextureSize(64, 128);
		hipSlab2.mirror = true;
		setRotation(hipSlab2, 0F, 0F, 0.7853982F);
		windShield = new ModelRenderer(this, 36, 33);
		windShield.addBox(-2.5F, 0F, 0F, 5, 3, 1);
		windShield.setRotationPoint(0F, 0F, 2F);
		windShield.setTextureSize(64, 128);
		windShield.mirror = true;
		setRotation(windShield, 0F, 0F, 0F);
		chest = new ModelRenderer(this, 18, 24);
		chest.addBox(-4F, -5F, -2F, 8, 5, 4);
		chest.setRotationPoint(0F, 5F, 0F);
		chest.setTextureSize(64, 128);
		chest.mirror = true;
		setRotation(chest, 0F, 0F, 0F);
		frontCar1 = new ModelRenderer(this, 28, 18);
		frontCar1.addBox(-3F, -2F, -3F, 3, 2, 2);
		frontCar1.setRotationPoint(-0.5F, 2.5F, 0F);
		frontCar1.setTextureSize(64, 128);
		frontCar1.mirror = true;
		setRotation(frontCar1, 0F, 0.2443461F, 0.0872665F);
		frontCar2 = new ModelRenderer(this, 28, 18);
		frontCar2.mirror = true;
		frontCar2.addBox(0F, -2F, -3F, 3, 2, 2);
		frontCar2.setRotationPoint(0.5F, 2.5F, 0F);
		frontCar2.setTextureSize(64, 128);
		setRotation(frontCar2, 0F, -0.2443461F, -0.0872665F);
		frontCar2.mirror = true;
		torso = new ModelRenderer(this, 28, 10);
		torso.addBox(-2.5F, -5F, -1.5F, 5, 5, 3);
		torso.setRotationPoint(0F, 9F, 0F);
		torso.setTextureSize(64, 128);
		torso.mirror = true;
		setRotation(torso, 0F, 0F, 0F);
		waist = new ModelRenderer(this, 18, 33);
		waist.addBox(-3F, -3F, -1.5F, 6, 3, 3);
		waist.setRotationPoint(0F, 12F, 0F);
		waist.setTextureSize(64, 128);
		waist.mirror = true;
		setRotation(waist, 0F, 0F, 0F);
		chestPanel1 = new ModelRenderer(this, 10, 23);
		chestPanel1.addBox(-1F, -4F, -1.5F, 2, 4, 1);
		chestPanel1.setRotationPoint(-1F, 7.5F, -0.5F);
		chestPanel1.setTextureSize(64, 128);
		chestPanel1.mirror = true;
		setRotation(chestPanel1, 0.2443461F, 0.1396263F, -0.3141593F);
		chestPanel2 = new ModelRenderer(this, 10, 23);
		chestPanel2.addBox(-1F, -4F, -1.5F, 2, 4, 1);
		chestPanel2.setRotationPoint(1F, 7.5F, -0.5F);
		chestPanel2.setTextureSize(64, 128);
		chestPanel2.mirror = true;
		setRotation(chestPanel2, 0.2443461F, -0.1396263F, 0.3141593F);
		chestPanel3 = new ModelRenderer(this, 20, 10);
		chestPanel3.addBox(-1F, -1.5F, -2F, 2, 4, 2);
		chestPanel3.setRotationPoint(0F, 2.1F, -0.9F);
		chestPanel3.setTextureSize(64, 128);
		chestPanel3.mirror = true;
		setRotation(chestPanel3, 0.1047198F, 0F, 0F);
		upperArm1 = new ModelRenderer(this, 52, 11);
		upperArm1.addBox(-2.5F, -1F, -1.5F, 3, 5, 3);
		upperArm1.setRotationPoint(-4F, 2F, 0F);
		upperArm1.setTextureSize(64, 128);
		upperArm1.mirror = true;
		setRotation(upperArm1, 0.0872665F, 0F, 0.1745329F);
		frontWheel1 = new ModelRenderer(this, 0, 40);
		frontWheel1.addBox(-1F, -1F, -1F, 1, 2, 2);
		frontWheel1.setRotationPoint(-4F, 0.5F, 0F);
		frontWheel1.setTextureSize(64, 128);
		frontWheel1.mirror = true;
		setRotation(frontWheel1, 0.0872665F, 0F, 0.8901179F);
		lowerArm1 = new ModelRenderer(this, 42, 18);
		lowerArm1.addBox(-1F, -4F, -1F, 2, 11, 2);
		lowerArm1.setRotationPoint(-5.7F, 5F, 0.5F);
		lowerArm1.setTextureSize(64, 128);
		lowerArm1.mirror = true;
		setRotation(lowerArm1, -0.2443461F, 0F, 0.1396263F);
		armPanel1 = new ModelRenderer(this, 44, 11);
		armPanel1.addBox(-1F, -2F, -1.5F, 1, 4, 3);
		armPanel1.setRotationPoint(-7F, 8.5F, -0.3F);
		armPanel1.setTextureSize(64, 128);
		armPanel1.mirror = true;
		setRotation(armPanel1, -0.2617994F, 0F, 0.0872665F);
		upperArm2 = new ModelRenderer(this, 52, 11);
		upperArm2.addBox(-0.5F, -1F, -1.5F, 3, 5, 3);
		upperArm2.setRotationPoint(4F, 2F, 0F);
		upperArm2.setTextureSize(64, 128);
		upperArm2.mirror = true;
		setRotation(upperArm2, 0.0872665F, 0F, -0.1745329F);
		lowerArm2 = new ModelRenderer(this, 42, 18);
		lowerArm2.addBox(-1F, -4F, -1F, 2, 11, 2);
		lowerArm2.setRotationPoint(5.7F, 5F, 0.5F);
		lowerArm2.setTextureSize(64, 128);
		lowerArm2.mirror = true;
		setRotation(lowerArm2, -0.2443461F, 0F, -0.1396263F);
		armPanel2 = new ModelRenderer(this, 44, 11);
		armPanel2.addBox(0F, -2F, -1.5F, 1, 4, 3);
		armPanel2.setRotationPoint(7F, 8.5F, -0.3F);
		armPanel2.setTextureSize(64, 128);
		armPanel2.mirror = true;
		setRotation(armPanel2, -0.2617994F, 0F, -0.0872665F);
		frontWheel2 = new ModelRenderer(this, 0, 40);
		frontWheel2.addBox(0F, -1F, -1F, 1, 2, 2);
		frontWheel2.setRotationPoint(4F, 0.5F, 0F);
		frontWheel2.setTextureSize(64, 128);
		frontWheel2.mirror = true;
		setRotation(frontWheel2, 0.0872665F, 0F, -0.8901179F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-2F, -4F, -1.5F, 4, 4, 3);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		ear1 = new ModelRenderer(this, 8, 7);
		ear1.addBox(-1F, -3F, -1F, 1, 4, 2);
		ear1.setRotationPoint(-1.2F, -1F, -1F);
		ear1.setTextureSize(64, 128);
		ear1.mirror = true;
		setRotation(ear1, -0.418879F, -0.1745329F, 0F);
		ear2 = new ModelRenderer(this, 8, 7);
		ear2.addBox(0F, -3F, -1F, 1, 4, 2);
		ear2.setRotationPoint(1.2F, -1F, -1F);
		ear2.setTextureSize(64, 128);
		ear2.mirror = true;
		setRotation(ear2, -0.418879F, 0.1745329F, 0F);
		mask = new ModelRenderer(this, 0, 7);
		mask.addBox(-1.5F, -2F, -1F, 3, 4, 1);
		mask.setRotationPoint(0F, -2F, -1.5F);
		mask.setTextureSize(64, 128);
		mask.mirror = true;
		setRotation(mask, -0.1745329F, 0F, 0F);

		this.addChildTo(waist, bipedBody);
		this.addChildTo(hipSlab1, waist);
		this.addChildTo(hipSlab2, waist);
		this.addChildTo(torso, waist);
		this.addChildTo(chestPanel1, torso);
		this.addChildTo(chestPanel2, torso);
		this.addChildTo(chest, torso);
		this.addChildTo(chestPanel3, chest);
		this.addChildTo(frontCar1, chest);
		this.addChildTo(frontCar2, chest);
		this.addChildTo(windShield, chest);
		this.addChildTo(frontWheel1, chest);
		this.addChildTo(frontWheel2, chest);
		this.addChildTo(head, bipedHead);
		this.addChildTo(mask, head);
		this.addChildTo(ear1, mask);
		this.addChildTo(ear2, mask);
		this.addChildTo(upperArm1, bipedRightArm);
		this.addChildTo(lowerArm1, upperArm1);
		this.addChildTo(armPanel1, lowerArm1);
		this.addChildTo(upperArm2, bipedLeftArm);
		this.addChildTo(lowerArm2, upperArm2);
		this.addChildTo(armPanel2, lowerArm2);
		this.addChildTo(leg1, bipedRightLeg);
		this.addChildTo(upperLegPanel1, leg1);
		this.addChildTo(lowerLegPanel1, leg1);
		this.addChildTo(foot1, leg1);
		this.addChildTo(backWheel1, leg1);
		this.addChildTo(leg2, bipedLeftLeg);
		this.addChildTo(upperLegPanel2, leg2);
		this.addChildTo(lowerLegPanel2, leg2);
		this.addChildTo(foot2, leg2);
		this.addChildTo(backWheel2, leg2);
		
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
		vehicleBumper1 = new ModelRenderer(this, 0, 68);
		vehicleBumper1.addBox(-2.5F, -1.5F, -2F, 3, 2, 2);
		vehicleBumper1.setRotationPoint(0F, 23F, -5F);
		vehicleBumper1.setTextureSize(64, 128);
		vehicleBumper1.mirror = true;
		setRotation(vehicleBumper1, 0F, 0.2443461F, 0F);
		vehicleBumper2 = new ModelRenderer(this, 0, 68);
		vehicleBumper2.mirror = true;
		vehicleBumper2.addBox(-0.5F, -1.5F, -2F, 3, 2, 2);
		vehicleBumper2.setRotationPoint(0F, 23F, -5F);
		vehicleBumper2.setTextureSize(64, 128);
		setRotation(vehicleBumper2, 0F, -0.2443461F, 0F);
		vehicleBumper2.mirror = true;
		vehicleRoof = new ModelRenderer(this, 0, 78);
		vehicleRoof.addBox(-2.5F, -0.5F, 0F, 5, 1, 2);
		vehicleRoof.setRotationPoint(0F, 20.7F, -0.5F);
		vehicleRoof.setTextureSize(64, 128);
		vehicleRoof.mirror = true;
		setRotation(vehicleRoof, 0F, 0F, 0F);
		vehicleBody = new ModelRenderer(this, 0, 64);
		vehicleBody.addBox(-3F, -1F, 0F, 6, 2, 12);
		vehicleBody.setRotationPoint(0F, 22.5F, -6.2F);
		vehicleBody.setTextureSize(64, 128);
		vehicleBody.mirror = true;
		setRotation(vehicleBody, 0F, 0F, 0F);
		vehicleHood = new ModelRenderer(this, 0, 81);
		vehicleHood.addBox(-2F, -1F, -2F, 4, 1, 3);
		vehicleHood.setRotationPoint(0F, 22.3F, -3.7F);
		vehicleHood.setTextureSize(64, 128);
		vehicleHood.mirror = true;
		setRotation(vehicleHood, 0F, 0F, 0F);
		vehicleBackWindShield = new ModelRenderer(this, 14, 78);
		vehicleBackWindShield.addBox(-2.5F, -0.5F, 0F, 5, 1, 3);
		vehicleBackWindShield.setRotationPoint(0F, 20.7F, 1.3F);
		vehicleBackWindShield.setTextureSize(64, 128);
		vehicleBackWindShield.mirror = true;
		setRotation(vehicleBackWindShield, -0.3839724F, 0F, 0F);
		vehicleWindShield = new ModelRenderer(this, 14, 82);
		vehicleWindShield.addBox(-2.5F, -1F, 0F, 5, 1, 3);
		vehicleWindShield.setRotationPoint(0F, 22.4F, -2.7F);
		vehicleWindShield.setTextureSize(64, 128);
		vehicleWindShield.mirror = true;
		setRotation(vehicleWindShield, 0.4537856F, 0F, 0F);
		vehicleSpoiler = new ModelRenderer(this, 24, 64);
		vehicleSpoiler.addBox(-2.5F, -1F, -1.5F, 5, 1, 3);
		vehicleSpoiler.setRotationPoint(0F, 22F, 4.8F);
		vehicleSpoiler.setTextureSize(64, 128);
		vehicleSpoiler.mirror = true;
		setRotation(vehicleSpoiler, 0.1919862F, 0F, 0F);
		vehicleDoor1 = new ModelRenderer(this, 24, 68);
		vehicleDoor1.addBox(-1F, -1F, -1.5F, 1, 1, 3);
		vehicleDoor1.setRotationPoint(-1.8F, 21.7F, 0.5F);
		vehicleDoor1.setTextureSize(64, 128);
		vehicleDoor1.mirror = true;
		setRotation(vehicleDoor1, 0F, 0F, 0.2268928F);
		vehicleDoor2 = new ModelRenderer(this, 24, 68);
		vehicleDoor2.addBox(0F, -1F, -1.5F, 1, 1, 3);
		vehicleDoor2.setRotationPoint(1.8F, 21.7F, 0.5F);
		vehicleDoor2.setTextureSize(64, 128);
		vehicleDoor2.mirror = true;
		setRotation(vehicleDoor2, 0F, 0F, -0.2268928F);
		
		this.addChildTo(vehicleFrontWheel1, vehicleBody);
		this.addChildTo(vehicleFrontWheel2, vehicleBody);
		this.addChildTo(vehicleBackWheel1, vehicleBody);
		this.addChildTo(vehicleBackWheel2, vehicleBody);
		this.addChildTo(vehicleBumper1, vehicleBody);
		this.addChildTo(vehicleBumper2, vehicleBody);
		this.addChildTo(vehicleRoof, vehicleBody);
		this.addChildTo(vehicleHood, vehicleBody);
		this.addChildTo(vehicleBackWindShield, vehicleBody);
		this.addChildTo(vehicleWindShield, vehicleBody);
		this.addChildTo(vehicleSpoiler, vehicleBody);
		this.addChildTo(vehicleDoor1, vehicleBody);
		this.addChildTo(vehicleDoor2, vehicleBody);
		
		
		this.stealthForceHoodGun1 = new ModelRenderer(this, 42, 66);
		this.stealthForceHoodGun1.setRotationPoint(-1.0F, 0.0F, -2.9F);
		this.stealthForceHoodGun1.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotation(stealthForceHoodGun1, 0.13962634015954636F, 0.0F, 0.0F);
		this.stealthForceBackWindShield = new ModelRenderer(this, 14, 78);
		this.stealthForceBackWindShield.setRotationPoint(0.0F, -1.8F, 7.5F);
		this.stealthForceBackWindShield.addBox(-2.5F, -0.5F, 0.0F, 5, 1, 3, 0.0F);
		this.setRotation(stealthForceBackWindShield, -0.3839724354387525F, -0.0F, 0.0F);
		this.stealthForceBackWheel2 = new ModelRenderer(this, 0, 64);
		this.stealthForceBackWheel2.setRotationPoint(0.3F, 0.5F, 0.2F);
		this.stealthForceBackWheel2.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.stealthForceRoof = new ModelRenderer(this, 0, 78);
		this.stealthForceRoof.setRotationPoint(0.0F, -1.8F, 5.7F);
		this.stealthForceRoof.addBox(-2.5F, -0.5F, 0.0F, 5, 1, 2, 0.0F);
		this.stealthForceBase = new ModelRenderer(this, 0, 64);
		this.stealthForceBase.setRotationPoint(0.0F, 22.5F, -6.2F);
		this.stealthForceBase.addBox(-3.0F, -1.0F, 0.0F, 6, 2, 12, 0.0F);
		this.stealthForceBackWheelCover2 = new ModelRenderer(this, 38, 68);
		this.stealthForceBackWheelCover2.setRotationPoint(3.0F, 0.0F, 10.0F);
		this.stealthForceBackWheelCover2.addBox(0.0F, -1.0F, -2.0F, 1, 2, 4, 0.0F);
		this.stealthForceGun2 = new ModelRenderer(this, 40, 64);
		this.stealthForceGun2.setRotationPoint(3.0F, -1.3F, 5.2F);
		this.stealthForceGun2.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.stealthForceSpoiler = new ModelRenderer(this, 24, 64);
		this.stealthForceSpoiler.setRotationPoint(0.0F, -0.5F, 11.0F);
		this.stealthForceSpoiler.addBox(-2.5F, -1.0F, -1.5F, 5, 1, 3, 0.0F);
		this.setRotation(stealthForceSpoiler, 0.19198621771937624F, -0.0F, 0.0F);
		this.stealthForceBumper2 = new ModelRenderer(this, 0, 68);
		this.stealthForceBumper2.mirror = true;
		this.stealthForceBumper2.setRotationPoint(1.0F, 0.5F, 1.2F);
		this.stealthForceBumper2.addBox(-0.5F, -1.5F, -2.0F, 3, 2, 2, 0.0F);
		this.setRotation(stealthForceBumper2, 0.0F, -0.24434609527920614F, 0.0F);
		this.stealthForceWindShield = new ModelRenderer(this, 14, 82);
		this.stealthForceWindShield.setRotationPoint(0.0F, -0.1F, 3.4F);
		this.stealthForceWindShield.addBox(-2.5F, -1.0F, 0.0F, 5, 1, 3, 0.0F);
		this.setRotation(stealthForceWindShield, 0.45378560551852565F, -0.0F, 0.0F);
		this.stealthForceDoor2 = new ModelRenderer(this, 24, 68);
		this.stealthForceDoor2.setRotationPoint(1.8F, -0.7F, 6.7F);
		this.stealthForceDoor2.addBox(0.0F, -1.0F, -1.5F, 1, 1, 3, 0.0F);
		this.setRotation(stealthForceDoor2, 0.0F, -0.0F, -0.22689280275926282F);
		this.stealthForceHood = new ModelRenderer(this, 0, 81);
		this.stealthForceHood.setRotationPoint(0.0F, -0.7F, 3.5F);
		this.stealthForceHood.addBox(-2.0F, -0.5F, -3.0F, 4, 1, 3, 0.0F);
		this.setRotation(stealthForceHood, -0.13962634015954636F, 0.0F, 0.0F);
		this.stealthForceGun1 = new ModelRenderer(this, 40, 64);
		this.stealthForceGun1.setRotationPoint(-3.0F, -1.3F, 5.2F);
		this.stealthForceGun1.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.stealthForceFrontWheel2 = new ModelRenderer(this, 0, 64);
		this.stealthForceFrontWheel2.setRotationPoint(0.3F, 0.5F, 0.2F);
		this.stealthForceFrontWheel2.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.stealthForceBackWheelCover1 = new ModelRenderer(this, 38, 68);
		this.stealthForceBackWheelCover1.setRotationPoint(-3.0F, 0.0F, 10.0F);
		this.stealthForceBackWheelCover1.addBox(-1.0F, -1.0F, -2.0F, 1, 2, 4, 0.0F);
		this.stealthForceDoor1 = new ModelRenderer(this, 24, 68);
		this.stealthForceDoor1.setRotationPoint(-1.8F, -0.7F, 6.7F);
		this.stealthForceDoor1.addBox(-1.0F, -1.0F, -1.5F, 1, 1, 3, 0.0F);
		this.setRotation(stealthForceDoor1, 0.0F, -0.0F, 0.22689280275926282F);
		this.stealthForceHoodGun2 = new ModelRenderer(this, 42, 66);
		this.stealthForceHoodGun2.setRotationPoint(1.0F, 0.0F, -2.9F);
		this.stealthForceHoodGun2.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotation(stealthForceHoodGun2, 0.13962634015954636F, 0.0F, 0.0F);
		this.stealthForceFrontWheelCover2 = new ModelRenderer(this, 28, 68);
		this.stealthForceFrontWheelCover2.setRotationPoint(3.0F, 0.0F, 1.5F);
		this.stealthForceFrontWheelCover2.addBox(0.0F, -1.0F, -1.5F, 1, 2, 4, 0.0F);
		this.stealthForceBackWheel1 = new ModelRenderer(this, 0, 64);
		this.stealthForceBackWheel1.setRotationPoint(-0.3F, 0.5F, 0.2F);
		this.stealthForceBackWheel1.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.stealthForceBumper1 = new ModelRenderer(this, 0, 68);
		this.stealthForceBumper1.setRotationPoint(-1.0F, 0.5F, 1.2F);
		this.stealthForceBumper1.addBox(-2.5F, -1.5F, -2.0F, 3, 2, 2, 0.0F);
		this.setRotation(stealthForceBumper1, 0.0F, 0.24434609527920614F, 0.0F);
		this.stealthForceFrontWheelCover1 = new ModelRenderer(this, 28, 68);
		this.stealthForceFrontWheelCover1.setRotationPoint(-3.0F, 0.0F, 1.5F);
		this.stealthForceFrontWheelCover1.addBox(-1.0F, -1.0F, -1.5F, 1, 2, 4, 0.0F);
		this.stealthForceFrontGun = new ModelRenderer(this, 41, 65);
		this.stealthForceFrontGun.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stealthForceFrontGun.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.stealthForceFrontWheel1 = new ModelRenderer(this, 0, 64);
		this.stealthForceFrontWheel1.setRotationPoint(-0.3F, 0.5F, 0.2F);
		this.stealthForceFrontWheel1.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.stealthForceHood.addChild(this.stealthForceHoodGun1);
		this.stealthForceBase.addChild(this.stealthForceBackWindShield);
		this.stealthForceBackWheelCover2.addChild(this.stealthForceBackWheel2);
		this.stealthForceBase.addChild(this.stealthForceRoof);
		this.stealthForceBase.addChild(this.stealthForceBackWheelCover2);
		this.stealthForceBase.addChild(this.stealthForceGun2);
		this.stealthForceBase.addChild(this.stealthForceSpoiler);
		this.stealthForceBase.addChild(this.stealthForceBumper2);
		this.stealthForceBase.addChild(this.stealthForceWindShield);
		this.stealthForceBase.addChild(this.stealthForceDoor2);
		this.stealthForceBase.addChild(this.stealthForceHood);
		this.stealthForceBase.addChild(this.stealthForceGun1);
		this.stealthForceFrontWheelCover2.addChild(this.stealthForceFrontWheel2);
		this.stealthForceBase.addChild(this.stealthForceBackWheelCover1);
		this.stealthForceBase.addChild(this.stealthForceDoor1);
		this.stealthForceHood.addChild(this.stealthForceHoodGun2);
		this.stealthForceBase.addChild(this.stealthForceFrontWheelCover2);
		this.stealthForceBackWheelCover1.addChild(this.stealthForceBackWheel1);
		this.stealthForceBase.addChild(this.stealthForceBumper1);
		this.stealthForceBase.addChild(this.stealthForceFrontWheelCover1);
		this.stealthForceBase.addChild(this.stealthForceFrontGun);
		this.stealthForceFrontWheelCover1.addChild(this.stealthForceFrontWheel1);
		
		
		
		adjustPos();
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		vehicleBody.render(f5);
		stealthForceBase.render(f5);
	}

	public void setRotation(ModelRenderer model, float x, float y, float z)
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
				for (ModelRenderer modelRenderer : new ModelRenderer[] {stealthForceBase, vehicleBody})
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
					vehicleBody.offsetY = 0F;
					stealthForceBase.offsetY = 256F;
				}
				else
				{
					int t = TFDataManager.getStealthModeTimer(player) * 2;
					float f = (10 - t);

					stealthForceBackWheelCover1.rotationPointX = -2 - (f / 10);
					stealthForceBackWheelCover2.rotationPointX = 2 + (f / 10);
					stealthForceFrontWheelCover1.rotationPointX = -2 - (f / 10);
					stealthForceFrontWheelCover2.rotationPointX = 2 + (f / 10);
					stealthForceBumper1.rotationPointX = -(f / 10);
					stealthForceBumper2.rotationPointX = (f / 10);
					stealthForceFrontGun.rotationPointZ = 2 - (f / 10) * 2;
					stealthForceHood.rotateAngleX = -0.13962634015954636F * (f / 10);
					stealthForceHoodGun1.rotationPointY = -(f / 10) + 1;
					stealthForceHoodGun2.rotationPointY = -(f / 10) + 1;
					stealthForceGun1.rotationPointX = -2 - (f / 10);
					stealthForceGun2.rotationPointX = 2 + (f / 10);
					stealthForceGun1.rotationPointY = -(f / 10) - 0.3F;
					stealthForceGun2.rotationPointY = -(f / 10) - 0.3F;
					
					stealthForceFrontWheel1.rotateAngleX = par1;
					stealthForceFrontWheel2.rotateAngleX = par1;
					stealthForceBackWheel1.rotateAngleX = par1;
					stealthForceBackWheel2.rotateAngleX = par1;
					stealthForceBase.offsetY = 0F;
					vehicleBody.offsetY = 256F;
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
				
				vehicleBody.rotateAngleX = 0;
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
				vehicleBody.offsetY = 256F;
				stealthForceBase.offsetY = 256F;
			}
		}
	}

	public void adjustPos()
	{
		// Body
		chest.rotationPointY = -4F;
		frontCar1.rotationPointY = -2.5F;
		frontCar2.rotationPointY = -2.5F;
		chestPanel1.rotationPointY = -1.5F;
		chestPanel2.rotationPointY = -1.5F;
		chestPanel3.rotationPointY = -3F;
		windShield.rotationPointY = -5F;
		frontWheel1.rotationPointY = -4.5F;
		frontWheel2.rotationPointY = -4.5F;

		// Arms
		upperArm1.rotationPointX = 1F;
		lowerArm1.setRotationPoint(-1F, 3.5F, 0);
		armPanel1.setRotationPoint(-1F, 3.75F, 0F);
		setRotation(armPanel1, 0F, 0F, -0.0872665F);
		upperArm2.rotationPointX = -1F;
		lowerArm2.setRotationPoint(1F, 3.5F, 0);
		armPanel2.setRotationPoint(1F, 3.75F, 0F);
		setRotation(armPanel2, 0F, 0F, 0.0872665F);

		// Legs
		leg1.rotationPointY = 12F;
		leg2.rotationPointY = 12F;
		upperLegPanel1.rotationPointX = 0F;
		upperLegPanel2.rotationPointX = 0F;
	}
}