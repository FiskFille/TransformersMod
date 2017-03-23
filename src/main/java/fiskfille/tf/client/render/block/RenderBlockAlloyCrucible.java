package fiskfille.tf.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.common.block.BlockAlloyCrucible;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderBlockAlloyCrucible implements ISimpleBlockRenderingHandler
{
    public static RenderBlockAlloyCrucible instance = new RenderBlockAlloyCrucible();
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        boolean flag = false;
        
        flag |= renderer.renderStandardBlock(block, x, y, z);
        
        if (BlockAlloyCrucible.getFlag(metadata, BlockAlloyCrucible.FLAG_TOP) || BlockAlloyCrucible.getFlag(metadata, BlockAlloyCrucible.FLAG_FRONT))
        {
            BlockAlloyCrucible.renderPass = 1;
            flag |= renderer.renderStandardBlock(block, x, y, z);
            BlockAlloyCrucible.renderPass = 0;
        }

        return flag;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        
        block.setBlockBoundsForItemRender();
        renderer.setRenderBoundsFromBlock(block);
        TFRenderHelper.renderBlock(block, metadata, renderer);
        
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
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
