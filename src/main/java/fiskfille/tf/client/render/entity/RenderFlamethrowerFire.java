package fiskfille.tf.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.entity.EntityTransformiumSeed;

public class RenderFlamethrowerFire extends Render
{	
	public RenderFlamethrowerFire()
    {
        shadowSize = 0.0F;
    }
	
	public void doRender(EntityFlamethrowerFire fire, double x, double y, double z, float rotationYaw, float p_76986_9_)
    {
    }
	
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
	
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityFlamethrowerFire)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}