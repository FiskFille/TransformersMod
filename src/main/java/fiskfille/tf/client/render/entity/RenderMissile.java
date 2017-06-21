package fiskfille.tf.client.render.entity;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.ModelMissile;
import fiskfille.tf.common.entity.EntityMissile;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMissile extends Render<EntityMissile>
{
    public ModelMissile model = new ModelMissile();
    public ResourceLocation texture = new ResourceLocation(TransformersMod.MODID, "textures/models/weapons/missile.png");

    public RenderMissile(RenderManager renderManager)
    {
        super(renderManager);
    }

    @Override
    public void doRender(EntityMissile entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 1.0F, 0.0F, 0.0F);
        float scale = 0.5F;
        GlStateManager.scale(scale, scale, scale);
        this.bindEntityTexture(entity);
        this.model.render(0.0625F);
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMissile entity)
    {
        return this.texture;
    }
}
