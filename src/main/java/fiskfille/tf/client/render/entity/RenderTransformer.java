package fiskfille.tf.client.render.entity;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelTransformer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderTransformer extends RendererLivingEntity
{
    public RenderTransformer()
    {
        super(new ModelTransformer(), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation(TransformersMod.modid + ":textures/models/purge/purge.png");
    }

    @Override
    public void doRender(EntityLivingBase entity, double x, double y, double z, float yaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, yaw, partialTicks);
    }
}
