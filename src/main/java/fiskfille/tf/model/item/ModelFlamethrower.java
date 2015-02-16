package fiskfille.tf.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Damn this is horrible code! @gegy1000
 */
public class ModelFlamethrower extends ModelBase
{
	ModelRenderer box1;
	ModelRenderer box2;
	ModelRenderer box3;
	ModelRenderer box4;
	ModelRenderer box5;
	ModelRenderer box6;
	ModelRenderer box7;
	ModelRenderer box8;
	ModelRenderer box9;
	ModelRenderer box10;
	ModelRenderer box11;
	ModelRenderer box12;
	ModelRenderer box13;
	ModelRenderer box14;
	ModelRenderer box15;
	ModelRenderer box16;
	ModelRenderer box17;
	
	public ModelFlamethrower()
	{
		textureWidth = 64;
		textureHeight = 64;

		box1 = new ModelRenderer(this, 0, 7);
		box1.addBox(-3F, -3F, -1.5F, 6, 3, 3, 0F);
		box1.setRotationPoint(0F, 0F, 0F);
		box1.setTextureSize(512, 512);
		box1.mirror = true;
		setRotation(box1, 0F, 0F, 0F);
		box2 = new ModelRenderer(this, 0, 13);
		box2.addBox(3F, -1F, -1F, 6, 2, 2, 0F);
		box2.setRotationPoint(0F, 0F, 0F);
		box2.setTextureSize(512, 512);
		box2.mirror = true;
		setRotation(box2, 0F, 0F, 0F);
		box3 = new ModelRenderer(this, 0, 17);
		box3.addBox(-1F, -0.2F, -0.5F, 5, 2, 1, 0F);
		box3.setRotationPoint(0F, 0F, 0F);
		box3.setTextureSize(512, 512);
		box3.mirror = true;
		setRotation(box3, 0F, 0F, -0.19198622F);
		box4 = new ModelRenderer(this, 0, 20);
		box4.addBox(-2F, -3.5F, -0.5F, 12, 4, 1, 0F);
		box4.setRotationPoint(0F, 0F, 0F);
		box4.setTextureSize(512, 512);
		box4.mirror = true;
		setRotation(box4, 0F, 0F, 0F);
		box5 = new ModelRenderer(this, 0, 25);
		box5.addBox(6.35F, -3F, -1.5F, 3, 1, 3, 0F);
		box5.setRotationPoint(0F, 0F, 0F);
		box5.setTextureSize(512, 512);
		box5.mirror = true;
		setRotation(box5, 0F, 0F, 0F);
		box6 = new ModelRenderer(this, 0, 29);
		box6.addBox(-1F, -2F, -3F, 3, 1, 1, 0F);
		box6.setRotationPoint(0F, 0F, 0F);
		box6.setTextureSize(512, 512);
		box6.mirror = true;
		setRotation(box6, 0F, -0.59341195F, 0F);
		box7 = new ModelRenderer(this, 0, 29);
		box7.addBox(-1F, -2F, 2F, 3, 1, 1, 0F);
		box7.setRotationPoint(0F, 0F, 0F);
		box7.setTextureSize(512, 512);
		box7.mirror = true;
		setRotation(box7, 0F, 0.59341195F, 0F);
		box8 = new ModelRenderer(this, 0, 29);
		box8.addBox(-3F, -2F, 0F, 3, 1, 1, 0F);
		box8.setRotationPoint(0F, 0F, 0F);
		box8.setTextureSize(512, 512);
		box8.mirror = true;
		setRotation(box8, 0F, 0.59341195F, 0F);
		box9 = new ModelRenderer(this, 0, 29);
		box9.addBox(-3F, -2F, -1F, 3, 1, 1, 0F);
		box9.setRotationPoint(0F, 0F, 0F);
		box9.setTextureSize(512, 512);
		box9.mirror = true;
		setRotation(box9, 0F, -0.59341195F, 0F);
		box10 = new ModelRenderer(this, 0, 31);
		box10.addBox(9.35F, -3F, -1.5F, 1, 3, 3, 0F);
		box10.setRotationPoint(0F, 0F, 0F);
		box10.setTextureSize(512, 512);
		box10.mirror = true;
		setRotation(box10, 0F, 0F, 0F);
		box11 = new ModelRenderer(this, 6, 29);
		box11.addBox(10.35F, -2.5F, -1F, 1, 2, 2, 0F);
		box11.setRotationPoint(0F, 0F, 0F);
		box11.setTextureSize(512, 512);
		box11.mirror = true;
		setRotation(box11, 0F, 0F, 0F);
		box12 = new ModelRenderer(this, 8, 0);
		box12.addBox(1F, 1.8F, -1.5F, 3, 4, 3, 0F);
		box12.setRotationPoint(0F, 0F, 0F);
		box12.setTextureSize(512, 512);
		box12.mirror = true;
		setRotation(box12, 0F, 0F, -0.19198622F);
		box13 = new ModelRenderer(this, 12, 25);
		box13.addBox(-1F, 0F, -0.5F, 2, 4, 1, 0F);
		box13.setRotationPoint(0F, 0F, 0F);
		box13.setTextureSize(512, 512);
		box13.mirror = true;
		setRotation(box13, 0F, 0F, 0.83775804F);
		box14 = new ModelRenderer(this, 0, 2);
		box14.addBox(-4F, -4F, -1F, 2, 3, 2, 0F);
		box14.setRotationPoint(0F, 0F, 0F);
		box14.setTextureSize(512, 512);
		box14.mirror = true;
		setRotation(box14, 0F, 0F, 0F);
		box15 = new ModelRenderer(this, 14, 15);
		box15.addBox(8.5F, -4F, -1F, 1, 2, 2, 0F);
		box15.setRotationPoint(0F, 0F, 0F);
		box15.setTextureSize(512, 512);
		box15.mirror = true;
		setRotation(box15, 0F, 0F, 0F);
		box16 = new ModelRenderer(this, 0, 0);
		box16.addBox(10F, -0.5F, -0.5F, 3, 1, 1, 0F);
		box16.setRotationPoint(0F, 0F, 0F);
		box16.setTextureSize(512, 512);
		box16.mirror = true;
		setRotation(box16, 0F, 0F, 0F);
		box17 = new ModelRenderer(this, 17, 0);
		box17.addBox(12F, -0.5F, -0.5F, 1, 1, 1, 0F);
		box17.setRotationPoint(0F, 0F, 0F);
		box17.setTextureSize(512, 512);
		box17.mirror = true;
		setRotation(box17, 0F, 0F, -0.05235988F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		box1.render(f5);
		box2.render(f5);
		box3.render(f5);
		box4.render(f5);
		box5.render(f5);
		box6.render(f5);
		box7.render(f5);
		box8.render(f5);
		box9.render(f5);
		box10.render(f5);
		box11.render(f5);
		box12.render(f5);
		box13.render(f5);
		box14.render(f5);
		box15.render(f5);
		box16.render(f5);
		box17.render(f5);
	}

	public void render()
	{
		float f5 = 0.0625F;
		
		box1.render(f5);
		box2.render(f5);
		box3.render(f5);
		box4.render(f5);
		box5.render(f5);
		box6.render(f5);
		box7.render(f5);
		box8.render(f5);
		box9.render(f5);
		box10.render(f5);
		box11.render(f5);
		box12.render(f5);
		box13.render(f5);
		box14.render(f5);
		box15.render(f5);
		box16.render(f5);
		box17.render(f5);
	}

	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
