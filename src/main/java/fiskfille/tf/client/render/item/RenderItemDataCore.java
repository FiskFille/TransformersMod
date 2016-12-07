package fiskfille.tf.client.render.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderItemDataCore implements IItemRenderer
{
	public RenderBlocks renderBlocks = RenderBlocks.getInstance();

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
	    GL11.glColor4f(1, 1, 1, 1);
		GL11.glPushMatrix();
		float scale = type != ItemRenderType.INVENTORY ? 0.5F : 1;

		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.EQUIPPED)
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
		else if (type == ItemRenderType.ENTITY)
		{
			scale *= 0.5F;
		}

		GL11.glScalef(scale, scale, scale);
		render(item);
		GL11.glPopMatrix();
	}

	public void render(ItemStack itemstack)
	{
		Block block = TFBlocks.groundBridgeControlPanel;
		Tessellator tessellator = Tessellator.instance;
		float colorMultiplier = 1;
		
		GL11.glEnable(GL11.GL_BLEND);

		for (int i = 0; i < 2; ++i)
		{
			IIcon iicon = itemstack.getItem().getIcon(itemstack, i);
			int color = itemstack.getItem().getColorFromItemStack(itemstack, i);
			float f1 = (float)(color >> 16 & 255) / 255;
			float f2 = (float)(color >> 8 & 255) / 255;
			float f3 = (float)(color & 255) / 255;

			if (i == 1)
			{
			    GL11.glDisable(GL11.GL_LIGHTING);
				TFRenderHelper.setLighting(61680);
			}

			if (renderBlocks.useInventoryTint)
			{
				GL11.glColor4f(f1 * colorMultiplier, f2 * colorMultiplier, f3 * colorMultiplier, 1);
			}

			renderBlocks.setRenderBounds(0, 0, 0, 1, 1, 1);
			GL11.glPushMatrix();
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator.startDrawingQuads();
            tessellator.setNormal(0, -1, 0);
            renderBlocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0, 1, 0);
            renderBlocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0, 0, -1);
            renderBlocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0, 0, 1);
            renderBlocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1, 0, 0);
            renderBlocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1, 0, 0);
            renderBlocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, iicon);
            tessellator.draw();
			GL11.glPopMatrix();

			if (i == 1)
			{
				TFRenderHelper.resetLighting();
				GL11.glEnable(GL11.GL_LIGHTING);
			}
		}
		
		GL11.glDisable(GL11.GL_BLEND);
	}
}
