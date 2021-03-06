package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderEnergonProcessor extends TileEntitySpecialRenderer
{
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/models/tiles/energon_processor.png");
    private ModelEnergonProcessor model = new ModelEnergonProcessor();

    public void render(TileEntityEnergonProcessor tileentity, double x, double y, double z, float partialTicks)
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

        bindTexture(texture);
        model.setBreaking(false);
        model.render(tileentity);

        bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/energon_processor_lights.png"));
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
        model.render(tileentity);
        TFRenderHelper.resetLighting();
        GL11.glEnable(GL11.GL_LIGHTING);

        if (tileentity.getWorldObj() != null)
        {
            int progress = TFRenderHelper.getBlockDestroyProgress(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);

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
                model.render(tileentity);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glPopMatrix();
            }
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityEnergonProcessor) tileentity, d, d1, d2, f);
    }
}
