package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelTransformiumSeed;
import fiskfille.tf.common.entity.EntityTransformiumSeed;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderTransformiumSeed extends TileEntitySpecialRenderer
{
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/transformium_seed.png");
    private ModelTransformiumSeed model;

    public RenderTransformiumSeed()
    {
        model = new ModelTransformiumSeed();
        new ItemRenderer(Minecraft.getMinecraft());
    }

    public void renderModelAt(TileEntityTransformiumSeed seed, double x, double y, double z, float partialTicks)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        bindTexture(texture);
        model.render(new EntityTransformiumSeed(seed.getWorldObj()));

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        TFRenderHelper.setLighting(TFRenderHelper.LIGHTING_LUMINOUS);
        bindTexture(new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/transformium_seed_lights.png"));
        model.render(new EntityTransformiumSeed(seed.getWorldObj()));
        TFRenderHelper.resetLighting();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks)
    {
        renderModelAt((TileEntityTransformiumSeed) tileentity, x, y, z, partialTicks);
    }
}
