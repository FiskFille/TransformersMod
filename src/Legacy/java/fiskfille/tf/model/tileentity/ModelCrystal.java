package fiskfille.tf.model.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrystal extends ModelBase
{
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;

	public ModelCrystal()
	{
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-1F, -6F, -1F, 2, 6, 2);
		Shape1.setRotationPoint(-1F, 24F, -2F);
		Shape1.setTextureSize(16, 16);
		Shape1.mirror = true;
		setRotation(Shape1, 0.2443461F, 0F, -0.2443461F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(-1F, -9F, -1F, 2, 9, 2);
		Shape2.setRotationPoint(-1F, 24F, -1F);
		Shape2.setTextureSize(16, 16);
		Shape2.mirror = true;
		setRotation(Shape2, -0.0523599F, 0F, 0.1047198F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(-1F, -5F, -1F, 2, 5, 2);
		Shape3.setRotationPoint(0F, 24F, -1F);
		Shape3.setTextureSize(16, 16);
		Shape3.mirror = true;
		setRotation(Shape3, 0.4886922F, 0F, 0.3839724F);
		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(-1F, -6F, -1F, 2, 6, 2);
		Shape4.setRotationPoint(-1F, 24F, -1F);
		Shape4.setTextureSize(16, 16);
		Shape4.mirror = true;
		setRotation(Shape4, -0.3141593F, 0F, -0.4014257F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(-1F, -4F, -1F, 2, 4, 2);
		Shape5.setRotationPoint(0F, 24F, 0F);
		Shape5.setTextureSize(16, 16);
		Shape5.mirror = true;
		setRotation(Shape5, -0.0698132F, 0F, 0.5235988F);
		Shape6 = new ModelRenderer(this, 0, 0);
		Shape6.addBox(-1F, -3F, -1F, 2, 3, 2);
		Shape6.setRotationPoint(0F, 24F, 0F);
		Shape6.setTextureSize(16, 16);
		Shape6.mirror = true;
		setRotation(Shape6, -0.6806784F, 0F, -0.2268928F);
	}

	public void renderAll()
	{
		float f5 = 0.0625F;
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
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