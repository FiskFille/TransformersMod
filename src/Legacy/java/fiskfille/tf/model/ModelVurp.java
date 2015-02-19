package fiskfille.tf.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.data.TFPlayerData;

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
	ModelRenderer upperArm1;
	ModelRenderer frontWheel1;
	ModelRenderer lowerArm1;
	ModelRenderer armPanel1;
	ModelRenderer upperArm2;
	ModelRenderer lowerArm2;
	ModelRenderer armPanel2;
	ModelRenderer frontWheel2;
	ModelRenderer head;
	ModelRenderer ear1;
	ModelRenderer ear2;
	ModelRenderer mask;

	ModelRenderer vehichleFrontWheel1;
	ModelRenderer vehichleFrontWheel2;
	ModelRenderer vehichleBackWheel1;
	ModelRenderer vehichleBackWheel2;
	ModelRenderer vehichleBumper1;
	ModelRenderer vehichleBumper2;
	ModelRenderer vehichleRoof;
	ModelRenderer vehichleBody;
	ModelRenderer vehichleHood;
	ModelRenderer vehichleBackWindShield;
	ModelRenderer vehichleWindShield;
	ModelRenderer vehichleSpoiler;
	ModelRenderer vehichleDoor1;
	ModelRenderer vehichleDoor2;

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
		
		
		vehichleFrontWheel1 = new ModelRenderer(this, 0, 64);
		vehichleFrontWheel1.addBox(-1F, -1F, -1F, 1, 2, 2);
		vehichleFrontWheel1.setRotationPoint(-2.3F, 23F, -4.5F);
		vehichleFrontWheel1.setTextureSize(64, 128);
		vehichleFrontWheel1.mirror = true;
		setRotation(vehichleFrontWheel1, 0F, 0F, 0F);
		vehichleFrontWheel2 = new ModelRenderer(this, 0, 64);
		vehichleFrontWheel2.addBox(0F, -1F, -1F, 1, 2, 2);
		vehichleFrontWheel2.setRotationPoint(2.3F, 23F, -4.5F);
		vehichleFrontWheel2.setTextureSize(64, 128);
		vehichleFrontWheel2.mirror = true;
		setRotation(vehichleFrontWheel2, 0F, 0F, 0F);
		vehichleBackWheel1 = new ModelRenderer(this, 0, 64);
		vehichleBackWheel1.addBox(-1F, -1F, -1F, 1, 2, 2);
		vehichleBackWheel1.setRotationPoint(-2.3F, 23F, 4F);
		vehichleBackWheel1.setTextureSize(64, 128);
		vehichleBackWheel1.mirror = true;
		setRotation(vehichleBackWheel1, 0F, 0F, 0F);
		vehichleBackWheel2 = new ModelRenderer(this, 0, 64);
		vehichleBackWheel2.addBox(0F, -1F, -1F, 1, 2, 2);
		vehichleBackWheel2.setRotationPoint(2.3F, 23F, 4F);
		vehichleBackWheel2.setTextureSize(64, 128);
		vehichleBackWheel2.mirror = true;
		setRotation(vehichleBackWheel2, 0F, 0F, 0F);
		vehichleBumper1 = new ModelRenderer(this, 0, 68);
		vehichleBumper1.addBox(-2.5F, -1.5F, -2F, 3, 2, 2);
		vehichleBumper1.setRotationPoint(0F, 23F, -5F);
		vehichleBumper1.setTextureSize(64, 128);
		vehichleBumper1.mirror = true;
		setRotation(vehichleBumper1, 0F, 0.2443461F, 0F);
		vehichleBumper2 = new ModelRenderer(this, 0, 68);
		vehichleBumper2.mirror = true;
		vehichleBumper2.addBox(-0.5F, -1.5F, -2F, 3, 2, 2);
		vehichleBumper2.setRotationPoint(0F, 23F, -5F);
		vehichleBumper2.setTextureSize(64, 128);
		setRotation(vehichleBumper2, 0F, -0.2443461F, 0F);
		vehichleBumper2.mirror = true;
		vehichleRoof = new ModelRenderer(this, 0, 78);
		vehichleRoof.addBox(-2.5F, -0.5F, 0F, 5, 1, 2);
		vehichleRoof.setRotationPoint(0F, 20.7F, -0.5F);
		vehichleRoof.setTextureSize(64, 128);
		vehichleRoof.mirror = true;
		setRotation(vehichleRoof, 0F, 0F, 0F);
		vehichleBody = new ModelRenderer(this, 0, 64);
		vehichleBody.addBox(-3F, -1F, 0F, 6, 2, 12);
		vehichleBody.setRotationPoint(0F, 22.5F, -6.2F);
		vehichleBody.setTextureSize(64, 128);
		vehichleBody.mirror = true;
		setRotation(vehichleBody, 0F, 0F, 0F);
		vehichleHood = new ModelRenderer(this, 0, 81);
		vehichleHood.addBox(-2F, -1F, -2F, 4, 1, 3);
		vehichleHood.setRotationPoint(0F, 22.3F, -3.7F);
		vehichleHood.setTextureSize(64, 128);
		vehichleHood.mirror = true;
		setRotation(vehichleHood, 0F, 0F, 0F);
		vehichleBackWindShield = new ModelRenderer(this, 14, 78);
		vehichleBackWindShield.addBox(-2.5F, -0.5F, 0F, 5, 1, 3);
		vehichleBackWindShield.setRotationPoint(0F, 20.7F, 1.3F);
		vehichleBackWindShield.setTextureSize(64, 128);
		vehichleBackWindShield.mirror = true;
		setRotation(vehichleBackWindShield, -0.3839724F, 0F, 0F);
		vehichleWindShield = new ModelRenderer(this, 14, 82);
		vehichleWindShield.addBox(-2.5F, -1F, 0F, 5, 1, 3);
		vehichleWindShield.setRotationPoint(0F, 22.4F, -2.7F);
		vehichleWindShield.setTextureSize(64, 128);
		vehichleWindShield.mirror = true;
		setRotation(vehichleWindShield, 0.4537856F, 0F, 0F);
		vehichleSpoiler = new ModelRenderer(this, 24, 64);
		vehichleSpoiler.addBox(-2.5F, -1F, -1.5F, 5, 1, 3);
		vehichleSpoiler.setRotationPoint(0F, 22F, 4.8F);
		vehichleSpoiler.setTextureSize(64, 128);
		vehichleSpoiler.mirror = true;
		setRotation(vehichleSpoiler, 0.1919862F, 0F, 0F);
		vehichleDoor1 = new ModelRenderer(this, 24, 68);
		vehichleDoor1.addBox(-1F, -1F, -1.5F, 1, 1, 3);
		vehichleDoor1.setRotationPoint(-1.8F, 21.7F, 0.5F);
		vehichleDoor1.setTextureSize(64, 128);
		vehichleDoor1.mirror = true;
		setRotation(vehichleDoor1, 0F, 0F, 0.2268928F);
		vehichleDoor2 = new ModelRenderer(this, 24, 68);
		vehichleDoor2.addBox(0F, -1F, -1.5F, 1, 1, 3);
		vehichleDoor2.setRotationPoint(1.8F, 21.7F, 0.5F);
		vehichleDoor2.setTextureSize(64, 128);
		vehichleDoor2.mirror = true;
		setRotation(vehichleDoor2, 0F, 0F, -0.2268928F);
		
		
		this.addChildTo(vehichleFrontWheel1, vehichleBody);
		this.addChildTo(vehichleFrontWheel2, vehichleBody);
		this.addChildTo(vehichleBackWheel1, vehichleBody);
		this.addChildTo(vehichleBackWheel2, vehichleBody);
		this.addChildTo(vehichleBumper1, vehichleBody);
		this.addChildTo(vehichleBumper2, vehichleBody);
		this.addChildTo(vehichleRoof, vehichleBody);
		this.addChildTo(vehichleHood, vehichleBody);
		this.addChildTo(vehichleBackWindShield, vehichleBody);
		this.addChildTo(vehichleWindShield, vehichleBody);
		this.addChildTo(vehichleSpoiler, vehichleBody);
		this.addChildTo(vehichleDoor1, vehichleBody);
		this.addChildTo(vehichleDoor2, vehichleBody);
		

		adjustPos();
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

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			if (TFPlayerData.getTransformationTimer(player) == 0)
			{
				float f = this.bipedHead.rotateAngleY - (this.bipedBody.rotateAngleY - this.bipedHead.rotateAngleY) / 3;
				if (vehichleBody.rotateAngleY < f) {vehichleBody.rotateAngleY += 0.05F;}
				if (vehichleBody.rotateAngleY > f) {vehichleBody.rotateAngleY -= 0.05F;}
				vehichleBody.rotateAngleY = f;
				vehichleBody.rotateAngleX = -(float)player.motionY - 0.0784000015258789F;
				vehichleFrontWheel1.rotateAngleX = -par1;
				vehichleFrontWheel2.rotateAngleX = -par1;
				vehichleBackWheel1.rotateAngleX = -par1;
				vehichleBackWheel2.rotateAngleX = -par1;
				
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
				float f = (10 - t);
				
				vehichleBody.rotateAngleX = 0;
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
				
				if (t < 10)
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
				vehichleBody.offsetY = 256F;
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