package fiskfille.tf.model.transformer.vehicle;

import net.minecraft.client.model.ModelRenderer;

public class ModelPurgeVehicle extends ModelVehicleBase
{
	ModelRenderer vehichleTread1;
	ModelRenderer vehichleTread2;
	ModelRenderer vehichleFoot1;
	ModelRenderer vehichleFoot2;
	ModelRenderer vehichleBody;
	ModelRenderer vehichleChassy;
	ModelRenderer vehichleTurret;
	ModelRenderer vehichleGun;
	ModelRenderer vehichleBack;
	
	public ModelPurgeVehicle()
	{
		textureWidth = 66;
		textureHeight = 128;
		
		vehichleTread1 = new ModelRenderer(this, 0, 64);
		vehichleTread1.addBox(-2F, -1.5F, -13F, 2, 3, 17);
		vehichleTread1.setRotationPoint(-5F, 22.5F, 4.5F);
		vehichleTread1.setTextureSize(66, 128);
		vehichleTread1.mirror = true;
		setRotation(vehichleTread1, 0F, 0F, 0F);
		vehichleTread2 = new ModelRenderer(this, 0, 64);
		vehichleTread2.addBox(0F, -1.5F, -13F, 2, 3, 17);
		vehichleTread2.setRotationPoint(5F, 22.5F, 4.5F);
		vehichleTread2.setTextureSize(66, 128);
		vehichleTread2.mirror = true;
		setRotation(vehichleTread2, 0F, 0F, 0F);
		vehichleFoot1 = new ModelRenderer(this, 0, 64);
		vehichleFoot1.addBox(-1.5F, -1F, -1F, 3, 4, 1);
		vehichleFoot1.setRotationPoint(-5F, 21F, -8.7F);
		vehichleFoot1.setTextureSize(66, 128);
		vehichleFoot1.mirror = true;
		setRotation(vehichleFoot1, 0.4014257F, 0F, 0F);
		vehichleFoot2 = new ModelRenderer(this, 0, 64);
		vehichleFoot2.mirror = true;
		vehichleFoot2.addBox(-1.5F, -1F, -1F, 3, 4, 1);
		vehichleFoot2.setRotationPoint(5F, 21F, -8.7F);
		vehichleFoot2.setTextureSize(66, 128);
		vehichleFoot2.mirror = true;
		setRotation(vehichleFoot2, 0.4014257F, 0F, 0F);
		vehichleFoot2.mirror = false;
		vehichleBody = new ModelRenderer(this, 0, 106);
		vehichleBody.addBox(-6F, -3F, -7.5F, 12, 3, 16);
		vehichleBody.setRotationPoint(0F, 23F, -0.5F);
		vehichleBody.setTextureSize(66, 128);
		vehichleBody.mirror = true;
		setRotation(vehichleBody, 0F, 0F, 0F);
		vehichleChassy = new ModelRenderer(this, 0, 84);
		vehichleChassy.addBox(-6.5F, -2F, -10F, 13, 2, 20);
		vehichleChassy.setRotationPoint(0F, 20.5F, 0F);
		vehichleChassy.setTextureSize(66, 128);
		vehichleChassy.mirror = true;
		setRotation(vehichleChassy, 0F, 0F, 0F);
		vehichleTurret = new ModelRenderer(this, 21, 64);
		vehichleTurret.addBox(-3F, -3F, -3F, 6, 4, 7);
		vehichleTurret.setRotationPoint(0F, 18F, -0.5F);
		vehichleTurret.setTextureSize(66, 128);
		vehichleTurret.mirror = true;
		setRotation(vehichleTurret, 0F, 0F, 0F);
		vehichleGun = new ModelRenderer(this, 38, 69);
		vehichleGun.addBox(-1F, -1F, -11F, 2, 2, 11);
		vehichleGun.setRotationPoint(0F, 17F, -3.5F);
		vehichleGun.setTextureSize(66, 128);
		vehichleGun.mirror = true;
		setRotation(vehichleGun, 0F, 0F, 0F);
		vehichleBack = new ModelRenderer(this, 21, 75);
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