package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
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

    public void render(TileEntityDisplayStation tileentity, double x, double y, double z, float partialTicks)
    {
        int metadata = 0;

        if (tileentity.getWorldObj() != null)
        {
            metadata = tileentity.getBlockMetadata();
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

            if (tileentity.getWorldObj() != null)
            {
                int progress = TFRenderHelper.getBlockDestroyProgress(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);

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
                EntityClientPlayerMP entity = tileentity.fakePlayer;
                ItemStack head = tileentity.getStackInSlot(0);
                ItemStack chest = tileentity.getStackInSlot(1);
                ItemStack legs = tileentity.getStackInSlot(2);
                ItemStack feet = tileentity.getStackInSlot(3);

                if (entity != null)
                {
                    entity.setCurrentItemOrArmor(4, head);
                    entity.setCurrentItemOrArmor(3, chest);
                    entity.setCurrentItemOrArmor(2, legs);
                    entity.setCurrentItemOrArmor(1, feet);
                    entity.capabilities.isFlying = true;
                    entity.rotationYawHead = 0;
                    entity.setInvisible(true);

                    Render render = RenderManager.instance.getEntityRenderObject(entity);

                    if (render != null)
                    {
                        GL11.glRotatef(180, 1, 0, 0);
                        GL11.glTranslatef(0, 0.0625F * 3, 0);
                        render.doRender(entity, 0, 0, 0, 0, 0.0625F);
                    }
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
