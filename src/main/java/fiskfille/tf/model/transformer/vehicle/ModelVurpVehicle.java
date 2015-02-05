package fiskfille.tf.model.transformer.vehicle;

import net.minecraft.client.model.ModelRenderer;

public class ModelVurpVehicle extends ModelVehicleBase
{
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
	
	public ModelVurpVehicle()
	{
		textureWidth = 64;
		textureHeight = 128;
		
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
	}
	
	public void render()
	{
		vehichleBody.render(0.0625F);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}