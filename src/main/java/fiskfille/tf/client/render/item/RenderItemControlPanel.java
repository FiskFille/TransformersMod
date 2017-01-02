package fiskfille.tf.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class RenderItemControlPanel extends RenderItemTileEntity
{
    public RenderItemControlPanel()
    {
        super(new TileEntityControlPanel());
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        float scale = 0.65F;
        GL11.glScalef(scale, scale, scale);

        if (type == type.ENTITY || type == type.INVENTORY)
        {
        	GL11.glRotatef(-90, 0, 1, 0);
            TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.0F, -0.75F, -0.5F, 0.0F);
        }
        else if (type == type.EQUIPPED)
        {
        	GL11.glRotatef(180, 0, 1, 0);
            TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, -0.5F, 0.0F, -1.5F, 0.0F);
        }
        else if (type == type.EQUIPPED_FIRST_PERSON || type == type.FIRST_PERSON_MAP)
        {
            TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.5F, 0.75F, 0.5F, 0.0F);
        }
    }
}
