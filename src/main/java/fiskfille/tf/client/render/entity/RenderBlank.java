package fiskfille.tf.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBlank extends Render
{
    public RenderBlank()
    {
        shadowSize = 0.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks)
    {
    }
}