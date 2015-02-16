package fiskfille.tf.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelDisplayPillar extends ModelBase
{
	ModelRenderer main;
	ModelRenderer backLeftFoot;
	ModelRenderer frontLeftFoot;
	ModelRenderer frontRightFoot;
	ModelRenderer backRightFoot;
	ModelRenderer top;

	public ModelDisplayPillar()
	{
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this, 0, 10);
		main.addBox(-3F, -8F, -3F, 6, 8, 6);
		main.setRotationPoint(0F, 24F, 0F);
		main.setTextureSize(64, 64);
		main.mirror = true;
		setRotation(main, 0F, 0F, 0F);
		backLeftFoot = new ModelRenderer(this, 24, 10);
		backLeftFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
		backLeftFoot.setRotationPoint(2F, 17F, 2F);
		backLeftFoot.setTextureSize(64, 64);
		backLeftFoot.mirror = true;
		setRotation(backLeftFoot, -0.6981317F, -2.356194F, 0F);
		frontLeftFoot = new ModelRenderer(this, 24, 10);
		frontLeftFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
		frontLeftFoot.setRotationPoint(2F, 17F, -2F);
		frontLeftFoot.setTextureSize(64, 64);
		frontLeftFoot.mirror = true;
		setRotation(frontLeftFoot, -0.6981317F, -0.7853982F, 0F);
		frontRightFoot = new ModelRenderer(this, 24, 10);
		frontRightFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
		frontRightFoot.setRotationPoint(-2F, 17F, -2F);
		frontRightFoot.setTextureSize(64, 64);
		frontRightFoot.mirror = true;
		setRotation(frontRightFoot, -0.6981317F, 0.7853982F, 0F);
		backRightFoot = new ModelRenderer(this, 24, 10);
		backRightFoot.addBox(-1F, 0F, -1F, 2, 10, 2);
		backRightFoot.setRotationPoint(-2F, 17F, 2F);
		backRightFoot.setTextureSize(64, 64);
		backRightFoot.mirror = true;
		setRotation(backRightFoot, -0.6981317F, 2.356194F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-4F, -2F, -4F, 8, 2, 8);
		top.setRotationPoint(0F, 17.5F, 0F);
		top.setTextureSize(64, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
	}

	public void renderAll()
	{
		float f5 = 0.0625F;
		main.render(f5);
		backLeftFoot.render(f5);
		frontLeftFoot.render(f5);
		frontRightFoot.render(f5);
		backRightFoot.render(f5);
		top.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}