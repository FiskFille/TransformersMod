package fiskfille.tf.client.model.transformer;

import fiskfille.tf.common.playerdata.TFDataManager;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModelSkystrike extends ModelTransformer.Biped
{
	ModelRenderer upperLeg1;
	ModelRenderer centerLeg1;
	ModelRenderer lowerLeg1;
	ModelRenderer footWheel1;
	ModelRenderer upperLeg2;
	ModelRenderer centerLeg2;
	ModelRenderer lowerLeg2;
	ModelRenderer footWheel2;
	ModelRenderer chest;
	ModelRenderer back;
	ModelRenderer stomach;
	ModelRenderer wing1;
	ModelRenderer wing2;
	ModelRenderer backPiece;
	ModelRenderer cockpit;
	ModelRenderer face;
	ModelRenderer topHeadPart;
	ModelRenderer centerHeadPart;
	ModelRenderer leftHeadPart;
	ModelRenderer rightHeadPart;
	ModelRenderer upperArm1;
	ModelRenderer lowerArm1;
	ModelRenderer upperArm2;
	ModelRenderer lowerArm2;

	ModelRenderer vehichleCockpit;
	ModelRenderer vehichleFrontPiece1;
	ModelRenderer vehichleFrontPiece2;
	ModelRenderer vehichleFrontPiece3;
	ModelRenderer vehichleMiddle;
	ModelRenderer vehichleFront;
	ModelRenderer vehichleLeftWing;
	ModelRenderer vehichleRightWing;
	ModelRenderer vehichleMiddleWing;
	ModelRenderer vehichleBottom;
	ModelRenderer vehichleLeftEngine;
	ModelRenderer vehichleRightEngine;
	ModelRenderer vehichleBody;

	public ModelSkystrike()
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

		upperLeg1 = new ModelRenderer(this, 0, 16);
		upperLeg1.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		upperLeg1.setRotationPoint(-2F, 12F, 0F);
		upperLeg1.setTextureSize(64, 128);
		upperLeg1.mirror = true;
		setRotation(upperLeg1, -0.3490659F, 0F, 0F);
		centerLeg1 = new ModelRenderer(this, 0, 25);
		centerLeg1.addBox(-2F, 0F, 0F, 4, 2, 2);
		centerLeg1.setRotationPoint(-2F, 17F, -1F);
		centerLeg1.setTextureSize(64, 128);
		centerLeg1.mirror = true;
		setRotation(centerLeg1, 0F, 0F, 0F);
		lowerLeg1 = new ModelRenderer(this, 12, 16);
		lowerLeg1.addBox(-1F, 0F, -1.5F, 2, 5, 3);
		lowerLeg1.setRotationPoint(-2F, 18.5F, 2F);
		lowerLeg1.setTextureSize(64, 128);
		lowerLeg1.mirror = true;
		setRotation(lowerLeg1, -0.6108652F, 0F, 0F);
		footWheel1 = new ModelRenderer(this, 12, 24);
		footWheel1.addBox(-1F, -1.5F, -1.5F, 1, 3, 3);
		footWheel1.setRotationPoint(-3F, 22.5F, -0.5F);
		footWheel1.setTextureSize(64, 128);
		footWheel1.mirror = true;
		setRotation(footWheel1, 0F, 0F, 0F);
		upperLeg2 = new ModelRenderer(this, 0, 16);
		upperLeg2.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		upperLeg2.setRotationPoint(2F, 12F, 0F);
		upperLeg2.setTextureSize(64, 128);
		upperLeg2.mirror = true;
		setRotation(upperLeg2, -0.3490659F, 0F, 0F);
		centerLeg2 = new ModelRenderer(this, 0, 25);
		centerLeg2.addBox(-2F, 0F, 0F, 4, 2, 2);
		centerLeg2.setRotationPoint(2F, 17F, -1F);
		centerLeg2.setTextureSize(64, 128);
		centerLeg2.mirror = true;
		setRotation(centerLeg2, 0F, 0F, 0F);
		lowerLeg2 = new ModelRenderer(this, 12, 16);
		lowerLeg2.addBox(-1F, 0F, -1.5F, 2, 5, 3);
		lowerLeg2.setRotationPoint(2F, 18.5F, 2F);
		lowerLeg2.setTextureSize(64, 128);
		lowerLeg2.mirror = true;
		setRotation(lowerLeg2, -0.6108652F, 0F, 0F);
		footWheel2 = new ModelRenderer(this, 12, 24);
		footWheel2.addBox(0F, -1.5F, -1.5F, 1, 3, 3);
		footWheel2.setRotationPoint(3F, 22.5F, -0.5F);
		footWheel2.setTextureSize(64, 128);
		footWheel2.mirror = true;
		setRotation(footWheel2, 0F, 0F, 0F);
		chest = new ModelRenderer(this, 22, 16);
		chest.addBox(-4F, -4F, -3F, 8, 4, 7);
		chest.setRotationPoint(0F, 4F, 0F);
		chest.setTextureSize(64, 128);
		chest.mirror = true;
		setRotation(chest, 0F, 0F, 0F);
		back = new ModelRenderer(this, 22, 27);
		back.addBox(-3F, -10F, -1.5F, 6, 10, 3);
		back.setRotationPoint(0F, 12.5F, -0.5F);
		back.setTextureSize(64, 128);
		back.mirror = true;
		setRotation(back, -0.2617994F, 0F, 0F);
		stomach = new ModelRenderer(this, 40, 27);
		stomach.addBox(-2.5F, -6F, -1.5F, 5, 6, 3);
		stomach.setRotationPoint(0F, 9.5F, 0F);
		stomach.setTextureSize(64, 128);
		stomach.mirror = true;
		setRotation(stomach, 0.1745329F, 0F, 0F);
		wing1 = new ModelRenderer(this, 40, 36);
		wing1.addBox(-0.5F, -10F, -4F, 1, 10, 8);
		wing1.setRotationPoint(-2F, 2F, 0F);
		wing1.setTextureSize(64, 128);
		wing1.mirror = true;
		setRotation(wing1, -0.4363323F, 0F, -0.7853982F);
		wing2 = new ModelRenderer(this, 40, 36);
		wing2.addBox(-0.5F, -10F, -4F, 1, 10, 8);
		wing2.setRotationPoint(2F, 2F, 0F);
		wing2.setTextureSize(64, 128);
		wing2.mirror = true;
		setRotation(wing2, -0.4363323F, 0F, 0.7853982F);
		backPiece = new ModelRenderer(this, 44, 6);
		backPiece.addBox(-2F, -8F, -1F, 4, 8, 2);
		backPiece.setRotationPoint(0F, 3F, 3F);
		backPiece.setTextureSize(64, 128);
		backPiece.mirror = true;
		setRotation(backPiece, -0.4712389F, 0F, 0F);
		cockpit = new ModelRenderer(this, 44, 1);
		cockpit.addBox(-4F, -1.5F, -2F, 8, 3, 2);
		cockpit.setRotationPoint(0F, -3F, 6F);
		cockpit.setTextureSize(64, 128);
		cockpit.mirror = true;
		setRotation(cockpit, -0.418879F, 0F, 0F);
		face = new ModelRenderer(this, 0, 0);
		face.addBox(-2F, -4F, -2F, 4, 4, 4);
		face.setRotationPoint(0F, 0F, 0F);
		face.setTextureSize(64, 128);
		face.mirror = true;
		setRotation(face, 0F, 0F, 0F);
		topHeadPart = new ModelRenderer(this, 18, 0);
		topHeadPart.addBox(-2F, -1F, -2F, 4, 4, 4);
		topHeadPart.setRotationPoint(0F, -4.4F, 0.8F);
		topHeadPart.setTextureSize(64, 128);
		topHeadPart.mirror = true;
		setRotation(topHeadPart, 0F, 0F, 0F);
		centerHeadPart = new ModelRenderer(this, 0, 8);
		centerHeadPart.addBox(-2.5F, -3.5F, -1F, 5, 4, 4);
		centerHeadPart.setRotationPoint(0F, -1F, -0.5F);
		centerHeadPart.setTextureSize(64, 128);
		centerHeadPart.mirror = true;
		setRotation(centerHeadPart, 0F, 0F, 0F);
		leftHeadPart = new ModelRenderer(this, 22, 10);
		leftHeadPart.addBox(-1F, -1F, -2F, 2, 2, 4);
		leftHeadPart.setRotationPoint(2F, -3.8F, 0.3F);
		leftHeadPart.setTextureSize(64, 128);
		leftHeadPart.mirror = true;
		setRotation(leftHeadPart, 0F, 0F, 0F);
		rightHeadPart = new ModelRenderer(this, 22, 10);
		rightHeadPart.addBox(-1F, -1F, -2F, 2, 2, 4);
		rightHeadPart.setRotationPoint(-2F, -3.8F, 0.3F);
		rightHeadPart.setTextureSize(64, 128);
		rightHeadPart.mirror = true;
		setRotation(rightHeadPart, 0F, 0F, 0F);
		upperArm1 = new ModelRenderer(this, 0, 30);
		upperArm1.addBox(-4F, -2F, -1.5F, 4, 6, 3);
		upperArm1.setRotationPoint(-4F, 2F, 0F);
		upperArm1.setTextureSize(64, 128);
		upperArm1.mirror = true;
		setRotation(upperArm1, 0.296706F, -0.1047198F, 0.2094395F);
		lowerArm1 = new ModelRenderer(this, 0, 39);
		lowerArm1.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
		lowerArm1.setRotationPoint(-6.7F, 5F, 1F);
		lowerArm1.setTextureSize(64, 128);
		lowerArm1.mirror = true;
		setRotation(lowerArm1, -0.2268928F, -0.1919862F, 0F);
		upperArm2 = new ModelRenderer(this, 0, 30);
		upperArm2.mirror = true;
		upperArm2.addBox(0F, -2F, -1.5F, 4, 6, 3);
		upperArm2.setRotationPoint(4F, 2F, 0F);
		upperArm2.setTextureSize(64, 128);
		upperArm2.mirror = true;
		setRotation(upperArm2, 0.296706F, 0.1047198F, -0.2094395F);
		lowerArm2 = new ModelRenderer(this, 0, 39);
		lowerArm2.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
		lowerArm2.setRotationPoint(6.7F, 5F, 1F);
		lowerArm2.setTextureSize(64, 128);
		lowerArm2.mirror = true;
		setRotation(lowerArm2, -0.2268928F, 0.1919862F, 0F);

		this.addChildTo(face, bipedHead);
		this.addChildTo(topHeadPart, bipedHead);
		this.addChildTo(centerHeadPart, bipedHead);
		this.addChildTo(leftHeadPart, bipedHead);
		this.addChildTo(rightHeadPart, bipedHead);
		this.addChildTo(chest, bipedBody);
		this.addChildTo(back, bipedBody);
		this.addChildTo(stomach, bipedBody);
		this.addChildTo(backPiece, chest);
		this.addChildTo(cockpit, backPiece);
		this.addChildTo(wing1, chest);
		this.addChildTo(wing2, chest);
		this.addChildTo(upperArm1, bipedRightArm);
		this.addChildTo(lowerArm1, bipedRightArm);
		this.addChildTo(upperArm2, bipedLeftArm);
		this.addChildTo(lowerArm2, bipedLeftArm);
		this.addChildTo(upperLeg1, bipedRightLeg);
		this.addChildTo(centerLeg1, bipedRightLeg);
		this.addChildTo(lowerLeg1, bipedRightLeg);
		this.addChildTo(footWheel1, bipedRightLeg);
		this.addChildTo(upperLeg2, bipedLeftLeg);
		this.addChildTo(centerLeg2, bipedLeftLeg);
		this.addChildTo(lowerLeg2, bipedLeftLeg);
		this.addChildTo(footWheel2, bipedLeftLeg);
		
		
		vehichleCockpit = new ModelRenderer(this, 0, 64);
		vehichleCockpit.addBox(-1.5F, -2F, -8F, 3, 2, 8);
		vehichleCockpit.setRotationPoint(0F, 0F, -4F);
		vehichleCockpit.setTextureSize(64, 128);
		vehichleCockpit.mirror = true;
		setRotation(vehichleCockpit, 0F, 0F, 0F);
		vehichleFrontPiece1 = new ModelRenderer(this, 0, 74);
		vehichleFrontPiece1.addBox(-2.5F, -1F, -12F, 5, 2, 12);
		vehichleFrontPiece1.setRotationPoint(0F, 1F, -4F);
		vehichleFrontPiece1.setTextureSize(64, 128);
		vehichleFrontPiece1.mirror = true;
		setRotation(vehichleFrontPiece1, 0F, 0F, 0F);
		vehichleFrontPiece2 = new ModelRenderer(this, 0, 88);
		vehichleFrontPiece2.addBox(-1.5F, -1F, -6F, 3, 3, 6);
		vehichleFrontPiece2.setRotationPoint(0F, 0F, -12F);
		vehichleFrontPiece2.setTextureSize(64, 128);
		vehichleFrontPiece2.mirror = true;
		setRotation(vehichleFrontPiece2, 0F, 0F, 0F);
		vehichleFrontPiece3 = new ModelRenderer(this, 0, 78);
		vehichleFrontPiece3.addBox(-1.5F, -1F, -3F, 3, 2, 3);
		vehichleFrontPiece3.setRotationPoint(0F, 1F, -18F);
		vehichleFrontPiece3.setTextureSize(64, 128);
		vehichleFrontPiece3.mirror = true;
		setRotation(vehichleFrontPiece3, 0F, 0F, 0F);
		vehichleMiddle = new ModelRenderer(this, 18, 88);
		vehichleMiddle.addBox(-4.5F, -2F, -13F, 9, 6, 13);
		vehichleMiddle.setRotationPoint(0F, -2F, 14F);
		vehichleMiddle.setTextureSize(64, 128);
		vehichleMiddle.mirror = true;
		setRotation(vehichleMiddle, 0F, 0F, 0F);
		vehichleFront = new ModelRenderer(this, 34, 78);
		vehichleFront.addBox(-4F, -2.5F, -5F, 8, 5, 5);
		vehichleFront.setRotationPoint(0F, 0F, 1F);
		vehichleFront.setTextureSize(64, 128);
		vehichleFront.mirror = true;
		setRotation(vehichleFront, 0F, 0F, 0F);
		vehichleLeftWing = new ModelRenderer(this, 22, 111);
		vehichleLeftWing.addBox(0F, -0.5F, -5F, 8, 1, 10);
		vehichleLeftWing.setRotationPoint(5F, 0F, 6F);
		vehichleLeftWing.setTextureSize(64, 128);
		vehichleLeftWing.mirror = true;
		setRotation(vehichleLeftWing, 0F, -0.5235988F, 0F);
		vehichleRightWing = new ModelRenderer(this, 22, 111);
		vehichleRightWing.addBox(-8F, -0.5F, -5F, 8, 1, 10);
		vehichleRightWing.setRotationPoint(-5F, 0F, 6F);
		vehichleRightWing.setTextureSize(64, 128);
		vehichleRightWing.mirror = true;
		setRotation(vehichleRightWing, 0F, 0.5235988F, 0F);
		vehichleMiddleWing = new ModelRenderer(this, 22, 66);
		vehichleMiddleWing.addBox(-7.5F, -1F, 0F, 15, 2, 6);
		vehichleMiddleWing.setRotationPoint(0F, 0F, 1F);
		vehichleMiddleWing.setTextureSize(64, 128);
		vehichleMiddleWing.mirror = true;
		setRotation(vehichleMiddleWing, 0F, 0F, 0F);
		vehichleBottom = new ModelRenderer(this, 0, 107);
		vehichleBottom.addBox(-3F, -2F, -6F, 6, 4, 10);
		vehichleBottom.setRotationPoint(0F, 1F, 11F);
		vehichleBottom.setTextureSize(64, 128);
		vehichleBottom.mirror = true;
		setRotation(vehichleBottom, 0.2617994F, 0F, 0F);
		vehichleLeftEngine = new ModelRenderer(this, 0, 97);
		vehichleLeftEngine.addBox(-1.5F, -1.5F, 0F, 3, 3, 3);
		vehichleLeftEngine.setRotationPoint(2F, -2F, 14F);
		vehichleLeftEngine.setTextureSize(64, 128);
		vehichleLeftEngine.mirror = true;
		setRotation(vehichleLeftEngine, 0F, 0F, 0F);
		vehichleRightEngine = new ModelRenderer(this, 0, 97);
		vehichleRightEngine.addBox(-1.5F, -1.5F, 0F, 3, 3, 3);
		vehichleRightEngine.setRotationPoint(-2F, -2F, 14F);
		vehichleRightEngine.setTextureSize(64, 128);
		vehichleRightEngine.mirror = true;
		setRotation(vehichleRightEngine, 0F, 0F, 0F);
		vehichleBody = new ModelRenderer(this, 0, 0);
        vehichleBody.addBox(-4.0F, 0.0F, -2.0F, 0, 0, 0);
        vehichleBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		
		
		this.addChildTo(vehichleCockpit, vehichleBody);
		this.addChildTo(vehichleFrontPiece1, vehichleBody);
		this.addChildTo(vehichleFrontPiece2, vehichleBody);
		this.addChildTo(vehichleFrontPiece3, vehichleBody);
		this.addChildTo(vehichleFront, vehichleBody);
		this.addChildTo(vehichleLeftWing, vehichleBody);
		this.addChildTo(vehichleRightWing, vehichleBody);
		this.addChildTo(vehichleMiddleWing, vehichleBody);
		this.addChildTo(vehichleMiddle, vehichleBody);
		this.addChildTo(vehichleBottom, vehichleBody);
		this.addChildTo(vehichleLeftEngine, vehichleBody);
		this.addChildTo(vehichleRightEngine, vehichleBody);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.vehichleBody.render(f5);
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
		
		this.bipedBody.rotationPointY = 0;
		this.upperLeg1.rotationPointY = 0.0F;
		this.centerLeg1.rotationPointY = 5.0F;
		this.lowerLeg1.rotationPointY = 6.0F;
		this.footWheel1.rotationPointY = 10.0F;
		this.upperLeg2.rotationPointY = 0.0F;
		this.centerLeg2.rotationPointY = 5.0F;
		this.lowerLeg2.rotationPointY = 6.0F;
		this.footWheel2.rotationPointY = 10.0F;
		this.upperArm1.rotationPointX = 1.0F;
		this.lowerArm1.rotationPointX = -2.0F;
		this.upperArm2.rotationPointX = -1.0F;
		this.lowerArm2.rotationPointX = 2.0F;
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			
			int timer = TFDataManager.getTransformationTimer(player);
			
			if (timer == 0)
			{
				this.vehichleBody.rotateAngleX = par5 / (180F / (float)Math.PI);
				this.vehichleBody.rotateAngleZ = -(this.bipedHead.rotateAngleY - (this.bipedBody.rotateAngleY - this.bipedHead.rotateAngleY));
				
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
				int t = timer;
				this.bipedBody.rotateAngleZ = 0.0F;
				
				setRotation(backPiece, -0.4712389F - ((((float)(20 - t) / 2)) * 0.2F), 0F, 0F);
				setRotation(chest, -((((float)(20 - t) / 2)) * 0.3F), 0F, 0F);
				bipedHead.rotationPointY = (((float)(20 - t) / 2)) * 0.8F;
				
				float armPosX = -5F + ((((float)(20 - t) / 2)) * 0.3F);
				float armPosY = (((float)(20 - t) / 2)) * 0.4F;
				bipedRightArm.setRotationPoint(armPosX, armPosY, 0.0F);
				bipedLeftArm.setRotationPoint(-armPosX, armPosY, 0.0F);
				
				if (t < 20)
				{
					bipedRightArm.rotateAngleX = (((float)(20 - t) / 2)) * 0.15F;
					bipedLeftArm.rotateAngleX = (((float)(20 - t) / 2)) * 0.15F;
					bipedRightLeg.rotateAngleX = (((float)(20 - t) / 2)) * 0.2F;
					bipedLeftLeg.rotateAngleX = (((float)(20 - t) / 2)) * 0.2F;
				}
				
				bipedHead.offsetY = -armPosY / 9;
				bipedBody.offsetY = -armPosY / 9;
				bipedRightArm.offsetY = -armPosY / 9;
				bipedLeftArm.offsetY = -armPosY / 9;
				bipedRightLeg.offsetY = -armPosY / 9;
				bipedLeftLeg.offsetY = -armPosY / 9;
				
				vehichleBody.offsetY = 256F;
			}
		}
	}
}