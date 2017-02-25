package fiskfille.tf.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fiskfille.tf.common.block.BlockGroundBridgeFrame;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderBlockGroundBridgeFrame implements ISimpleBlockRenderingHandler
{
    public static RenderBlockGroundBridgeFrame instance = new RenderBlockGroundBridgeFrame();
    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        ForgeDirection dir = BlockGroundBridgeFrame.getFrameDirection(world, x, y, z);

        if (dir != null)
        {
            int metadata = world.getBlockMetadata(x, y, z);

            if (dir == ForgeDirection.EAST)
            {
                renderer.uvRotateTop = metadata == 0 ? 1 : 2;
            }
            else
            {
                renderer.uvRotateTop = metadata == 0 ? 0 : 3;
            }
        }

        boolean flag = renderer.renderStandardBlock(block, x, y, z);
        renderer.uvRotateTop = 0;

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
