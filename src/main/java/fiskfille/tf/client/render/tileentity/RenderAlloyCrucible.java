package fiskfille.tf.client.render.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderAlloyCrucible extends TileEntitySpecialRenderer
{
    private RenderBlocks renderBlocks;

    public void render(TileEntityAlloyCrucible tile, double x, double y, double z, float partialTicks)
    {
        World world = tile.getWorldObj();
        Block block = tile.getBlockType();
        
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);

        if (world != null && (tile.getEnergy() > 0 || tile.canSmelt()))
        {
            Tessellator tessellator = Tessellator.instance;
            int metadata = tile.getBlockMetadata();
            int side = new int[] {3, 4, 2, 5}[metadata % 4];
            float f = 0.001F;
            
            bindTexture(TextureMap.locationBlocksTexture);
            
            tile.renderOverlay = true;
            GL11.glDisable(GL11.GL_LIGHTING);
            TFRenderHelper.setLighting(61680);
            tessellator.startDrawingQuads();
            block.setBlockBoundsBasedOnState(world, tile.xCoord, tile.yCoord, tile.zCoord);
            renderBlocks.setRenderBoundsFromBlock(block);
            renderBlocks.renderFaceYPos(block, 0, f, 0, renderBlocks.getBlockIconFromSideAndMetadata(block, 101, metadata));
            
            if (tile.canSmelt())
            {
                switch (side)
                {
                case 2:
                    renderBlocks.renderFaceZNeg(block, 0, 0, -f, renderBlocks.getBlockIconFromSideAndMetadata(block, side + 100, metadata));
                    break;
                case 3:
                    renderBlocks.renderFaceZPos(block, 0, 0, f, renderBlocks.getBlockIconFromSideAndMetadata(block, side + 100, metadata));
                    break;
                case 4:
                    renderBlocks.renderFaceXNeg(block, -f, 0, 0, renderBlocks.getBlockIconFromSideAndMetadata(block, side + 100, metadata));
                    break;
                case 5:
                    renderBlocks.renderFaceXPos(block, f, 0, 0, renderBlocks.getBlockIconFromSideAndMetadata(block, side + 100, metadata));
                    break;
                }
            }
            
            tessellator.draw();
            TFRenderHelper.resetLighting();
            GL11.glEnable(GL11.GL_LIGHTING);
            tile.renderOverlay = false;
        }
        
        GL11.glPopMatrix();
    }

    @Override
    public void func_147496_a(World world)
    {
        renderBlocks = new RenderBlocks(world);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityAlloyCrucible) tileentity, d, d1, d2, f);
    }
}
