package fiskfille.tf.client.model.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.entity.EntityTransformiumSeed;

public class ModelTransformiumSeed extends ModelBase
{
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape5;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer wingA;
	public ModelRenderer wingB;
	public ModelRenderer wingC;
	public ModelRenderer wingD;
	public ModelRenderer antenna;

	public ModelTransformiumSeed()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.shape1.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
		this.shape2 = new ModelRenderer(this, 0, 3);
		this.shape2.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.shape2.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
		this.shape5 = new ModelRenderer(this, 0, 19);
		this.shape5.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.shape5.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
		this.antenna = new ModelRenderer(this, 14, 7);
		this.antenna.setRotationPoint(0.0F, 12.5F, 0.0F);
		this.antenna.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.shape6 = new ModelRenderer(this, 8, 8);
		this.shape6.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape6.addBox(-0.5F, -0.5F, -2.0F, 1, 2, 4, 0.0F);
		this.setRotation(shape6, 0.0F, 1.5707963267948966F, 0.0F);
		this.shape7 = new ModelRenderer(this, 8, 8);
		this.shape7.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.shape7.addBox(-0.5F, -0.5F, -2.0F, 1, 2, 4, 0.0F);
		this.wingA = new ModelRenderer(this, 12, 0);
		this.wingA.setRotationPoint(0.0F, 14.0F, -1.5F);
		this.wingA.addBox(-1.0F, -5.0F, -0.5F, 2, 6, 1, 0.0F);
		this.setRotation(wingA, 0.06981317007977318F, 0.0F, 0.0F);
		this.wingB = new ModelRenderer(this, 18, 0);
		this.wingB.setRotationPoint(-1.5F, 14.0F, 0.0F);
		this.wingB.addBox(-0.5F, -5.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotation(wingB, 0.0F, 0.0F, -0.06981317007977318F);
		this.shape4 = new ModelRenderer(this, 0, 19);
		this.shape4.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.shape4.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
		this.shape3 = new ModelRenderer(this, 0, 12);
		this.shape3.setRotationPoint(0.0F, 11.0F, 0.0F);
		this.shape3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
		this.wingC = new ModelRenderer(this, 24, 0);
		this.wingC.setRotationPoint(0.0F, 14.0F, 1.5F);
		this.wingC.addBox(-1.0F, -5.0F, -0.5F, 2, 6, 1, 0.0F);
		this.setRotation(wingC, -0.06981317007977318F, 0.0F, 0.0F);
		this.wingD = new ModelRenderer(this, 30, 0);
		this.wingD.setRotationPoint(1.5F, 14.0F, 0.0F);
		this.wingD.addBox(-0.5F, -5.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotation(wingD, 0.0F, 0.0F, 0.06981317007977318F);
	}

	public void render(EntityTransformiumSeed seed)
	{
		setRotationAngles(seed);
		float f5 = 0.0625F;
		float scale = 1.3F;
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, -0.85F, 0.0F);
		GL11.glScalef(scale, scale, scale);
		this.shape1.render(f5);
		this.shape2.render(f5);
		this.shape5.render(f5);
		this.antenna.render(f5);
		this.shape6.render(f5);
		this.shape7.render(f5);
		this.wingA.render(f5);
		this.wingB.render(f5);
		this.shape4.render(f5);
		this.shape3.render(f5);
		this.wingC.render(f5);
		this.wingD.render(f5);
		GL11.glPopMatrix();
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(EntityTransformiumSeed seed)
	{
		super.setRotationAngles(0, 0, 0, 0, 0, 0, seed);
		
		float t = (float)(seed.ticksExisted <= 50 ? seed.ticksExisted : 50) / 50;
		float i = 1.0F - t;
		
		this.setRotation(wingA, (0.06981317007977318F * i + ((float)Math.PI / 2) * t), 0.0F, 0.0F);
		this.setRotation(wingB, 0.0F, 0.0F, -(0.06981317007977318F * i + ((float)Math.PI / 2) * t));
		this.setRotation(wingC, -(0.06981317007977318F * i + ((float)Math.PI / 2) * t), 0.0F, 0.0F);
		this.setRotation(wingD, 0.0F, 0.0F, (0.06981317007977318F * i + ((float)Math.PI / 2) * t));
		this.antenna.setRotationPoint(0.0F, 12.5F + (t * 3.5F), 0.0F);
	}
}