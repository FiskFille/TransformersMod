package fiskfille.tf.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderItemTileEntity implements IItemRenderer
{
    protected TileEntity tileentity;

    public RenderItemTileEntity(TileEntity tile)
    {
        tileentity = tile;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (type == ItemRenderType.INVENTORY)
        {
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        }
        else if (type == ItemRenderType.ENTITY)
        {
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        }
        
        TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.0F, 0.0F, 0.0F, 0.0F);
    }
}
