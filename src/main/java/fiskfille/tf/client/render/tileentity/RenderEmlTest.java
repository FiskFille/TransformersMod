package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityEmbTest;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderEmlTest extends TileEntitySpecialRenderer
{
    public void renderModelAt(TileEntityEmbTest tile, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        
        TFRenderHelper.setLighting(61680);
        TFRenderHelper.renderTag(String.format("Energy: %s", tile.getEnergy()), 0, -1, 0);
        TFRenderHelper.resetLighting();
        
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks)
    {
        renderModelAt((TileEntityEmbTest) tileentity, x, y, z, partialTicks);
    }
}
