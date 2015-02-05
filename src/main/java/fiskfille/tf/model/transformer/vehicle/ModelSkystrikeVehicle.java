package fiskfille.tf.model.transformer.vehicle;

import net.minecraft.client.model.ModelRenderer;

public class ModelSkystrikeVehicle extends ModelVehicleBase
{
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
	
	public ModelSkystrikeVehicle()
	{
		textureWidth = 64;
		textureHeight = 128;
		
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
		vehichleBody.offsetY = 1.25F;
	}
	
	public void render()
	{
		vehichleBody.render(0.0625F);
		vehichleBody.offsetY = 1.2F;
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}