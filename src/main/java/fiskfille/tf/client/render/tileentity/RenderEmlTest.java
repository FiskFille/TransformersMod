package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityEmlTest;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderEmlTest extends TileEntitySpecialRenderer
{
    public void renderModelAt(TileEntityEmlTest tile, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        
        TFRenderHelper.setLighting(61680);
        TFRenderHelper.renderTag(String.format("Energy: %s", tile.getEnergy()), 0, -1, 0);
        
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        float[] afloat = TFRenderHelper.hexToRGB(tile.getEnergyColor());
        GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
        tessellator.startDrawingQuads();
        tessellator.addVertex(-0.5F, 0.5F, -0.505F);
        tessellator.addVertex(0.5F, 0.5F, -0.505F);
        tessellator.addVertex(0.5F, -0.5F, -0.505F);
        tessellator.addVertex(-0.5F, -0.5F, -0.505F);
        tessellator.draw();
        TFRenderHelper.resetLighting();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks)
    {
        renderModelAt((TileEntityEmlTest) tileentity, x, y, z, partialTicks);
    }
}
