package fiskfille.tf.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.common.block.BlockDisplayPedestal;
import fiskfille.tf.common.block.BlockDisplayPedestal.BlockIcon;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderBlockDisplayPedestal implements ISimpleBlockRenderingHandler
{
    public static RenderBlockDisplayPedestal instance = new RenderBlockDisplayPedestal();
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        boolean flag = !renderer.hasOverrideBlockTexture();
        boolean flag1 = false;
        
        BlockIcon[] icons = BlockDisplayPedestal.getTexture(world.getBlockMetadata(x, y, z));
        AxisAlignedBB[] bounds = BlockDisplayPedestal.getBounds();
        renderer.setRenderAllFaces(true);
        
        for (int i = 0; i < Math.min(icons.length, bounds.length); ++i)
        {
            AxisAlignedBB aabb = bounds[i];
            
            if (flag)
            {
                renderer.setOverrideBlockTexture(icons[i].block.getIcon(icons[i].side, icons[i].metadata));
            }
            
            renderer.setRenderBounds(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
            flag1 |= renderer.renderStandardBlock(block, x, y, z);
        }
        
        if (flag)
        {
            renderer.clearOverrideBlockTexture();
        }

        return flag1;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        BlockIcon[] icons = BlockDisplayPedestal.getTexture(metadata);
        AxisAlignedBB[] bounds = BlockDisplayPedestal.getBounds();
        
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        
        for (int i = 0; i < Math.min(icons.length, bounds.length); ++i)
        {
            AxisAlignedBB aabb = bounds[i];
            
            renderer.setOverrideBlockTexture(icons[i].block.getIcon(icons[i].side, icons[i].metadata));
            renderer.setRenderBounds(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
            TFRenderHelper.renderBlock(block, metadata, renderer);
        }
        
        renderer.clearOverrideBlockTexture();
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
