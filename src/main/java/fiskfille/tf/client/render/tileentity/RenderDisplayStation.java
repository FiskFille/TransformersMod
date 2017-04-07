package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderDisplayStation extends TileEntitySpecialRenderer
{
    private ModelDisplayStation model = new ModelDisplayStation();

    public void render(TileEntityDisplayStation tile, double x, double y, double z, float partialTicks)
    {
        int metadata = 0;

        if (tile.getWorldObj() != null)
        {
            metadata = tile.getBlockMetadata();
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glRotatef(metadata * 90, 0.0F, 1.0F, 0.0F);

        if (metadata < 4)
        {
            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/display_station.png"));
            model.setBreaking(false);
            model.render();

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/display_station_lamp.png"));
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LIGHTING);
            TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
            model.render();
            TFRenderHelper.resetLighting();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);

            if (tile.getWorldObj() != null)
            {
                int progress = TFRenderHelper.getBlockDestroyProgress(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);

                if (progress >= 0)
                {
                    OpenGlHelper.glBlendFunc(774, 768, 1, 0);
                    bindTexture(new ResourceLocation(String.format("textures/blocks/destroy_stage_%s.png", progress)));
                    GL11.glColor4f(1, 1, 1, 0.5F);
                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    model.setBreaking(true);
                    model.render();
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glPopMatrix();
                }
            }

            try
            {
                EntityPlayer entity = tile.fakePlayer;
                
                if (entity != null && entity.experience != -0.0085F)
                {
                    entity.width = 0.6F;
                    entity.height = 1.8F;
                    entity.yOffset = 1.62F;
                    entity.capabilities.isFlying = true;
                    entity.rotationYawHead = 0;
                    entity.experience = -0.0085F;
                    entity.setInvisible(true);
                    entity.setDead();
                    entity.setLocationAndAngles(tile.xCoord + 0.5F, tile.yCoord, tile.zCoord + 0.5F, 0, 0);
                }

                if (entity != null)
                {
                    GL11.glRotatef(180, 1, 0, 0);
                    GL11.glTranslatef(0, 0.0625F * 3, 0);
                    RenderManager.instance.renderEntityWithPosYaw(entity, 0, 0, 0, 0, 1);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityDisplayStation) tileentity, d, d1, d2, f);
    }
}
