package fiskfille.tf.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

public class RenderItemDisplayStation extends RenderItemTileEntity
{
    public RenderItemDisplayStation()
    {
        super(new TileEntityDisplayStation());
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
    	float scale = 0.6F;
        GL11.glScalef(scale, scale, scale);
        
        if (type == type.ENTITY || type == type.INVENTORY)
        {
        	if (type == type.INVENTORY)
        	{
        		GL11.glRotatef(90, 0, 1, 0);
        	}
        	else
        	{
        		GL11.glRotatef(180, 0, 1, 0);
        	}
        	
        	GL11.glTranslatef(-0.5F, -1.0F, -0.5F);
        }
        else if (type == type.EQUIPPED)
        {
        	GL11.glTranslatef(0.5F, 0.0F, 0.5F);
        }
        else if (type == type.EQUIPPED_FIRST_PERSON || type == type.FIRST_PERSON_MAP)
        {
        	GL11.glTranslatef(0.5F, 0.0F, 0.5F);
        }
        
        TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.0F, 0.0F, 0.0F, 0.0F);
    }
}
