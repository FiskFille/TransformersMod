package fiskfille.tf.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;

public class RenderBlank extends Render
{
    public RenderBlank()
    {
        shadowSize = 0.0F;
    }
    
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }
    
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks)
    {
    }
}