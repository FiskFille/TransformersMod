package fiskfille.tf.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelTransformiumSeed;
import fiskfille.tf.common.entity.EntityTransformiumSeed;

public class RenderTransformiumSeedEntity extends Render
{
	private ModelTransformiumSeed model;
	
	public RenderTransformiumSeedEntity()
    {
		model = new ModelTransformiumSeed();
        shadowSize = 0.5F;
    }
	
	public void doRender(EntityTransformiumSeed seed, double x, double y, double z, float rotationYaw, float p_76986_9_)
    {
		this.bindTexture(getEntityTexture(seed));
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glRotatef(180, 1, 0, 0);
		model.render(seed);
		
		GL11.glPopMatrix();
    }
	
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/transformium_seed.png");
	}
	
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityTransformiumSeed)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}