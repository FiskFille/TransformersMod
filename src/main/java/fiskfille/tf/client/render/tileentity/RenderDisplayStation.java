package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

public class RenderDisplayStation extends TileEntitySpecialRenderer
{
    private ModelDisplayStation model = new ModelDisplayStation();

    public void render(TileEntityDisplayStation tileentity, double x, double y, double z, float partialTicks)
    {
        int metadata = 0;

        if (tileentity.getWorldObj() != null)
        {
            metadata = tileentity.getBlockMetadata();
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glRotatef(metadata * 90, 0.0F, 1.0F, 0.0F);

        if (metadata < 4)
        {
            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/display_station.png"));
            model.render();

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/display_station_lamp.png"));
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glColor4f(1, 1, 1, 1);
            setLighting(61680);
            GL11.glPushMatrix();
            GL11.glColor4f(1, 1, 1, 1);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            model.render();
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_LIGHTING);

            try
            {
            	GL11.glTranslatef(0, -0.0625F * 3, 0);
                EntityClientPlayerMP entity = tileentity.fakePlayer;

                if (entity != null)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        entity.setCurrentItemOrArmor(4 - i, tileentity.getStackInSlot(i));
                    }

                    entity.capabilities.isFlying = true;
                    entity.rotationYawHead = 0;
                    entity.experience = -0.0085F;
                    entity.setInvisible(true);
                    entity.setDead();
                    entity.setLocationAndAngles(tileentity.xCoord + 0.5F, tileentity.yCoord, tileentity.zCoord + 0.5F, 0, 0);
                }

                Render render = RenderManager.instance.getEntityRenderObject(entity);

                if (render != null)
                {
                    GL11.glPushMatrix();
                    GL11.glRotatef(180, 1, 0, 0);
                    render.doRender(entity, 0, 0, 0, 0, 0.0625F);
                    GL11.glPopMatrix();
                }
            }
            catch (Exception e)
            {
                
            }
        }

        GL11.glPopMatrix();
    }

    public void setLighting(int c0)
    {
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityDisplayStation)tileentity, d, d1, d2, f);
    } 
}
