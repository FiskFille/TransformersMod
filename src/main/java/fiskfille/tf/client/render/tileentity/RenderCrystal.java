package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.tileentity.ModelCrystal;
import fiskfille.tf.common.block.BlockEnergonCrystal;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.tileentity.TileEntityCrystal;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderCrystal extends TileEntitySpecialRenderer
{
    private ModelCrystal model = new ModelCrystal();

    public void render(TileEntityCrystal tile, double x, double y, double z, float partialTicks)
    {
        BlockEnergonCrystal block = (BlockEnergonCrystal) tile.getBlockType();
        Energon energon = block.getEnergonType();

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        GL11.glScalef(1, -1F, -1F);
        adjustRotation(tile, x, y, z, partialTicks);

        float[] rgb = TFRenderHelper.hexToRGB(energon.getColor());
        GL11.glColor4f(rgb[0], rgb[1], rgb[2], 0.5F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        TFRenderHelper.setLighting(61680);
        model.render();
        TFRenderHelper.resetLighting();
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        if (tile.getWorldObj() != null)
        {
            int progress = TFRenderHelper.getBlockDestroyProgress(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);

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
                model.render();
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

    public void adjustRotation(TileEntityCrystal tile, double x, double y, double z, float partialTicks)
    {
        int metadata = tile.getBlockMetadata();
        ForgeDirection dir = ForgeDirection.getOrientation(metadata).getOpposite();
        
        if (dir == ForgeDirection.UP)
        {
            GL11.glTranslatef(0, 1, 0);
            GL11.glRotatef(180, 0, 0, 1);
        }
        else if (dir == ForgeDirection.DOWN)
        {
            GL11.glTranslatef(0, -1, 0);
        }
        else
        {
            int[] rotations = {2, 0, 1, 3};
            
            GL11.glTranslatef(0, 0.125F, 0);
            GL11.glRotatef(90 * rotations[metadata - 2], 0, 1, 0);
            GL11.glRotatef(55, 1, 0, 0);
            GL11.glTranslatef(0, -1, 0.36F);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTicks)
    {
        render((TileEntityCrystal) tile, x, y, z, partialTicks);
    }
}
