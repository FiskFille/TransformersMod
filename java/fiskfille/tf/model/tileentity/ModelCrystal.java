package fiskfille.tf.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCrystal extends ModelBase
{
	ModelRenderer shape1;
	ModelRenderer shape2;
	ModelRenderer shape3;
	ModelRenderer shape4;
	ModelRenderer shape5;
	ModelRenderer shape6;

	public ModelCrystal()
	{
		textureWidth = 64;
		textureHeight = 64;

		shape1 = new ModelRenderer(this, 0, 0);
		shape1.addBox(-1F, -6F, -1F, 2, 6, 2);
		shape1.setRotationPoint(-1F, 24F, -2F);
		shape1.setTextureSize(16, 16);
		shape1.mirror = true;
		setRotation(shape1, 0.2443461F, 0F, -0.2443461F);
		shape2 = new ModelRenderer(this, 0, 0);
		shape2.addBox(-1F, -9F, -1F, 2, 9, 2);
		shape2.setRotationPoint(-1F, 24F, -1F);
		shape2.setTextureSize(16, 16);
		shape2.mirror = true;
		setRotation(shape2, -0.0523599F, 0F, 0.1047198F);
		shape3 = new ModelRenderer(this, 0, 0);
		shape3.addBox(-1F, -5F, -1F, 2, 5, 2);
		shape3.setRotationPoint(0F, 24F, -1F);
		shape3.setTextureSize(16, 16);
		shape3.mirror = true;
		setRotation(shape3, 0.4886922F, 0F, 0.3839724F);
		shape4 = new ModelRenderer(this, 0, 0);
		shape4.addBox(-1F, -6F, -1F, 2, 6, 2);
		shape4.setRotationPoint(-1F, 24F, -1F);
		shape4.setTextureSize(16, 16);
		shape4.mirror = true;
		setRotation(shape4, -0.3141593F, 0F, -0.4014257F);
		shape5 = new ModelRenderer(this, 0, 0);
		shape5.addBox(-1F, -4F, -1F, 2, 4, 2);
		shape5.setRotationPoint(0F, 24F, 0F);
		shape5.setTextureSize(16, 16);
		shape5.mirror = true;
		setRotation(shape5, -0.0698132F, 0F, 0.5235988F);
		shape6 = new ModelRenderer(this, 0, 0);
		shape6.addBox(-1F, -3F, -1F, 2, 3, 2);
		shape6.setRotationPoint(0F, 24F, 0F);
		shape6.setTextureSize(16, 16);
		shape6.mirror = true;
		setRotation(shape6, -0.6806784F, 0F, -0.2268928F);
	}

	public void renderAll()
	{
		float f5 = 0.0625F;
		shape1.render(f5);
		shape2.render(f5);
		shape3.render(f5);
		shape4.render(f5);
		shape5.render(f5);
		shape6.render(f5);
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