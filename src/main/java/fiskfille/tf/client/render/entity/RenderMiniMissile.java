package fiskfille.tf.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.ModelMissile;
import fiskfille.tf.common.entity.EntityMiniMissile;

@SideOnly(Side.CLIENT)
public class RenderMiniMissile extends Render
{
    private ModelMissile model = new ModelMissile();
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/models/weapons/missile.png");
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method,
     * always casting down its argument and then handing it off to a worker
     * function which does the actual work. In all probabilty, the class Render
     * is generic (Render<T extends Entity) and this method has signature public
     * void func_76986_a(T entity, double d, double d1, double d2, float f,
     * float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityMiniMissile miniMissile, double x, double y, double z, float rotationYaw, float p_76986_9_)
    {
        this.bindEntityTexture(miniMissile);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(miniMissile.prevRotationYaw + (miniMissile.rotationYaw - miniMissile.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(miniMissile.prevRotationPitch + (miniMissile.rotationPitch - miniMissile.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(90, 0, -1, 0);
        
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f11 = (float) miniMissile.missileShake - p_76986_9_;
        
        if (f11 > 0.0F)
        {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
        }
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        
        float f = 0.7F;
        GL11.glScalef(f, f, f);
        model.render(miniMissile, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glRotatef(90, 0.0F, 0.0F, -1.0F);
        GL11.glPopMatrix();
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called
     * unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityMiniMissile tankShell)
    {
        return texture;
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called
     * unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityMiniMissile) p_110775_1_);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method,
     * always casting down its argument and then handing it off to a worker
     * function which does the actual work. In all probabilty, the class Render
     * is generic (Render<T extends Entity) and this method has signature public
     * void func_76986_a(T entity, double d, double d1, double d2, float f,
     * float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTicks)
    {
        this.doRender((EntityMiniMissile) entity, x, y, z, rotationYaw, partialTicks);
    }
}