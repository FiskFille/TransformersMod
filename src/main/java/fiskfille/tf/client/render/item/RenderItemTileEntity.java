package fiskfille.tf.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemTileEntity implements IItemRenderer
{
    private TileEntity tileentity;

    public RenderItemTileEntity(TileEntity tile)
    {
        this.tileentity = tile;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (type == type.ENTITY || type == type.INVENTORY)
        {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, -0.5F, -0.5F, -0.5F, 0.0F);
        }
        if (type == type.EQUIPPED || type == type.EQUIPPED_FIRST_PERSON || type == type.FIRST_PERSON_MAP)
        {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.0F, 0.0F, 0.0F, 0.0F);
        }
    }
}
