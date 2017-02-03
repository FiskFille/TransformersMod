package fiskfille.tf.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.common.block.BlockEnergonTank;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTileHelper;

public class RenderBlockEnergonTank implements ISimpleBlockRenderingHandler
{
    public static RenderBlockEnergonTank instance = new RenderBlockEnergonTank();
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        boolean flag = false;
        boolean connectAbove = false;
        boolean connectBelow = false;
        int metadata = world.getBlockMetadata(x, y, z);
        BlockEnergonTank.renderMetadata = metadata;

        if (world.getTileEntity(x, y, z) instanceof TileEntityEnergonTank)
        {
            TileEntityEnergonTank tile = (TileEntityEnergonTank) world.getTileEntity(x, y, z);
            TileEntityEnergonTank tileBase = TFTileHelper.getTileBase(tile);

            if (tileBase == TFTileHelper.getTileBase(world.getTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord)))
            {
                connectAbove = true;
            }

            if (tileBase == TFTileHelper.getTileBase(world.getTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord)))
            {
                connectBelow = true;
            }

            if (connectAbove && connectBelow)
            {
                BlockEnergonTank.renderSide = 2;
            }
            else if (connectAbove)
            {
                BlockEnergonTank.renderSide = 1;
            }
            else if (connectBelow)
            {
                BlockEnergonTank.renderSide = 3;
            }
            else
            {
                BlockEnergonTank.renderSide = 4;
            }

            float min = 0.001F;
            float max = 1.0F - min;
            renderer.setRenderBounds(min, min, min, max, max, max);
            flag |= renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBoundsFromBlock(block);
            BlockEnergonTank.renderSide = 0;
        }

        if (metadata == 1)
        {
            renderer.uvRotateSouth = 3;
            renderer.uvRotateEast = 3;
            renderer.uvRotateWest = 3;
            renderer.uvRotateNorth = 3;
        }
        else if (metadata == 2)
        {
            renderer.uvRotateSouth = 2;
            renderer.uvRotateNorth = 1;
            renderer.uvRotateTop = 3;
            renderer.uvRotateBottom = 3;
        }
        else if (metadata == 3)
        {
            renderer.uvRotateSouth = 1;
            renderer.uvRotateNorth = 2;
        }
        else if (metadata == 4)
        {
            renderer.uvRotateEast = 2;
            renderer.uvRotateWest = 1;
            renderer.uvRotateTop = 1;
            renderer.uvRotateBottom = 2;
        }
        else if (metadata == 5)
        {
            renderer.uvRotateEast = 1;
            renderer.uvRotateWest = 2;
            renderer.uvRotateTop = 2;
            renderer.uvRotateBottom = 1;
        }

        float min = connectAbove || connectBelow ? 0.002F : 0.002F;
        float max = 1.0F - min;

        for (int i = 0; i < 2; ++i)
        {
            BlockEnergonTank.renderingInside = i != 0;
            flag |= renderer.renderStandardBlock(block, x, y, z);
            renderer.setRenderBounds(min, min, min, max, max, max);
            renderer.renderFromInside = true;

            if (renderer.hasOverrideBlockTexture())
            {
                break;
            }

            if (metadata == 2)
            {
                renderer.uvRotateSouth = 1;
                renderer.uvRotateNorth = 2;
            }
            else if (metadata == 3)
            {
                renderer.uvRotateSouth = 2;
                renderer.uvRotateNorth = 1;
            }
            else if (metadata == 4)
            {
                renderer.uvRotateEast = 1;
                renderer.uvRotateWest = 2;
                renderer.uvRotateTop = 2;
                renderer.uvRotateBottom = 1;
            }
            else if (metadata == 5)
            {
                renderer.uvRotateEast = 2;
                renderer.uvRotateWest = 1;
                renderer.uvRotateTop = 1;
                renderer.uvRotateBottom = 2;
            }
        }

        BlockEnergonTank.renderingInside = false;
        renderer.renderFromInside = false;
        renderer.renderAllFaces = false;
        renderer.uvRotateSouth = 0;
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateNorth = 0;
        renderer.uvRotateTop = 0;
        renderer.uvRotateBottom = 0;

        return flag;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        float min = 0.0001F;
        float max = 1.0F - min;
        BlockEnergonTank.renderSide = 4;
        renderer.setRenderBounds(min, min, min, max, max, max);
        TFRenderHelper.renderBlock(block, metadata, renderer);
        renderer.setRenderBoundsFromBlock(block);
        BlockEnergonTank.renderSide = 0;

        if (metadata == 1)
        {
            renderer.uvRotateSouth = 3;
            renderer.uvRotateEast = 3;
            renderer.uvRotateWest = 3;
            renderer.uvRotateNorth = 3;
        }
        else if (metadata == 2)
        {
            renderer.uvRotateSouth = 2;
            renderer.uvRotateNorth = 1;
            renderer.uvRotateTop = 3;
            renderer.uvRotateBottom = 3;
        }
        else if (metadata == 3)
        {
            renderer.uvRotateSouth = 1;
            renderer.uvRotateNorth = 2;
        }
        else if (metadata == 4)
        {
            renderer.uvRotateEast = 2;
            renderer.uvRotateWest = 1;
            renderer.uvRotateTop = 1;
            renderer.uvRotateBottom = 2;
        }
        else if (metadata == 5)
        {
            renderer.uvRotateEast = 1;
            renderer.uvRotateWest = 2;
            renderer.uvRotateTop = 2;
            renderer.uvRotateBottom = 1;
        }

        for (int i = 0; i < 2; ++i)
        {
            TFRenderHelper.renderBlock(block, metadata, renderer);
            renderer.setRenderBounds(min, min, min, max, max, max);
            renderer.renderFromInside = true;
            renderer.renderAllFaces = true;

            if (metadata == 2)
            {
                renderer.uvRotateSouth = 1;
                renderer.uvRotateNorth = 2;
            }
            else if (metadata == 3)
            {
                renderer.uvRotateSouth = 2;
                renderer.uvRotateNorth = 1;
            }
            else if (metadata == 4)
            {
                renderer.uvRotateEast = 1;
                renderer.uvRotateWest = 2;
                renderer.uvRotateTop = 2;
                renderer.uvRotateBottom = 1;
            }
            else if (metadata == 5)
            {
                renderer.uvRotateEast = 2;
                renderer.uvRotateWest = 1;
                renderer.uvRotateTop = 1;
                renderer.uvRotateBottom = 2;
            }
        }

        renderer.renderFromInside = false;
        renderer.renderAllFaces = false;
        renderer.uvRotateSouth = 0;
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateNorth = 0;
        renderer.uvRotateTop = 0;
        renderer.uvRotateBottom = 0;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return renderId;
    }
}
