package fiskfille.tf.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderItemTileEntity implements IItemRenderer
{
	private TileEntity tileentity;

	public RenderItemTileEntity(TileEntity tile)
	{
		this.tileentity = tile;
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
		if (type == type.EQUIPPED || type == type.EQUIPPED_FIRST_PERSON || type == type.FIRST_PERSON_MAP)
		{
			TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.0F, 0.0F, 0.0F, 0.0F);
		}
		else
		{
			if (type == type.INVENTORY)
			{
				GL11.glRotatef(90, 0, 1, 0);
			}
			else
			{
				GL11.glRotatef(180, 0, 1, 0);
			}

			TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, -0.5F, -0.5F, -0.5F, 0.0F);
		}
	}
}
