package fiskfille.tf.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.ModelBassCharge;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBassCharge extends Render
{
    private ModelBassCharge model = new ModelBassCharge();

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float partialTicks)
    {
        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);

        float f1 = 0.2F + (float) entity.ticksExisted / 10;
        GL11.glScalef(f1, f1, f1);


        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);

        float r = 112F / 255;
        float g = 145F / 255;
        float b = 1.0F;
        float a = (float) (20 - entity.ticksExisted) / 20;
        GL11.glColor4f(r, g, b, a);
        model.render();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glDepthMask(true);


        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return TextureMap.locationItemsTexture;
    }
}