package fiskfille.tf.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.common.block.BlockEnergonOre;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderBlockEnergonOre implements ISimpleBlockRenderingHandler
{
    public static RenderBlockEnergonOre instance = new RenderBlockEnergonOre();
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        boolean flag = false;
        
        BlockEnergonOre.renderPass = 1;
        flag |= renderer.renderStandardBlock(block, x, y, z);
        BlockEnergonOre.renderPass = 2;
        flag |= renderer.renderStandardBlock(block, x, y, z);
        BlockEnergonOre.renderPass = 0;
        
        return flag;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        
        BlockEnergonOre.renderPass = 0;
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
