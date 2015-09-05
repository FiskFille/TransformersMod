package fiskfille.tf.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.ModelMissile;

@SideOnly(Side.CLIENT)
public class RenderMissile extends Render
{
    public ModelMissile model = new ModelMissile();
    public ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/models/weapons/missile.png");

    public void doRender(Entity entity, double x, double y, double z, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 + 180, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9, 1.0F, 0.0F, 0.0F);
        float scale = 0.5F;
        GL11.glScalef(scale, scale, scale);
        bindEntityTexture(entity);
        model.render();
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return texture;
    }
}