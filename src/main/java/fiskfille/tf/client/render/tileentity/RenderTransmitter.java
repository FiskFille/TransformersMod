package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelTransmitter;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderTransmitter extends TileEntitySpecialRenderer
{
    private ModelTransmitter model = new ModelTransmitter();

    public void render(TileEntityTransmitter transmitter, double x, double y, double z, float partialTicks)
    {
        World world = transmitter.getWorldObj();
        int metadata = 0;

        if (world != null)
        {
            metadata = transmitter.getBlockMetadata();
        }

        if (metadata < 4)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1, -1F, -1F);
            GL11.glRotatef(metadata * 90, 0, 1, 0);

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/transmitter.png"));
            model.setBreaking(false);
            model.render(transmitter, partialTicks);

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/transmitter_lights.png"));
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            TFRenderHelper.setLighting(61680);
            model.render(transmitter, partialTicks);
            TFRenderHelper.resetLighting();
            GL11.glEnable(GL11.GL_LIGHTING);

            if (world != null)
            {
                int progress = TFRenderHelper.getBlockDestroyProgress(world, transmitter.xCoord, transmitter.yCoord, transmitter.zCoord);
                
                if (progress >= 0)
                {
                    OpenGlHelper.glBlendFunc(774, 768, 1, 0);
                    bindTexture(new ResourceLocation(String.format("textures/blocks/destroy_stage_%s.png", progress)));
                    GL11.glColor4f(1, 1, 1, 0.5F);
                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    model.setBreaking(true);
                    model.render(transmitter, partialTicks);
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glPopMatrix();
                }
            }

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();

            if (world != null)
            {
                TFRenderHelper.renderEnergyTransmissions(transmitter, x, y, z, partialTicks);
            }
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityTransmitter) tileentity, d, d1, d2, f);
    }
}
