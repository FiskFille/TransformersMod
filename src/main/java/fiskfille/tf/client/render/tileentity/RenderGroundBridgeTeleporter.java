package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderGroundBridgeTeleporter extends TileEntitySpecialRenderer
{
	public void render(TileEntityGroundBridgeTeleporter tileentity, double x, double y, double z, float partialTicks)
	{
		int metadata = 0;

		if (tileentity.getWorldObj() != null)
		{
			metadata = tileentity.getBlockMetadata();
		}

		if (metadata == 1)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glScalef(1F, -1F, -1F);

			if (tileentity.controlPanel != null)
			{
				if (tileentity.returnPortal)
				{
					GL11.glRotatef(90 * tileentity.controlPanel.portalDirection, 0, 1, 0);
				}
				else
				{
					GL11.glRotatef(90 * tileentity.controlPanel.getSrcPortalDirection(), 0, 1, 0);
				}
			}
			
			float f1 = 1 - (float)(tileentity.lastUpdate > 0 ? tileentity.lastUpdate + partialTicks - 1 : 0) / 6;
			
			if (tileentity.lastUpdate == 0)
			{
				f1 = MathHelper.clamp_float(tileentity.ticks + partialTicks, 0, 6) / 6;
			}
			
			GL11.glScalef(MathHelper.clamp_float(f1 * 3, 0, 1), f1, 1);

			GL11.glColor4f(1, 1, 1, 1);
			TFRenderHelper.setLighting(61680);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);

			float zOffset = -1.0F;
			float indent = 1.25F;
			float edge = 1.5F;
			float scale = 1.18F;
			float radius = 2;
			float shade = 0.95F;
			IIcon icon = TFBlocks.groundBridgeTeleporter.getBlockTextureFromSide(0);
			
			bindTexture(TextureMap.locationBlocksTexture);
			drawPortal(0, 0, zOffset, scale, radius, edge, indent, false, icon);
			GL11.glColor4f(shade, shade, shade, 1);
			drawPortal(0, 0, zOffset, scale, radius, edge, indent, true, icon);
			
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			GL11.glEnable(GL11.GL_LIGHTING);
			TFRenderHelper.resetLighting();
			GL11.glPopMatrix();
		}
	}

	public void drawPortal(float offsetX, float offsetY, float offsetZ, float scale, float radius, float edge, float indent, boolean invert, IIcon icon)
	{
		GL11.glPushMatrix();
		GL11.glRotatef(180, 0, 0, 10);
		GL11.glTranslatef(offsetX, offsetY, offsetZ);
		GL11.glScalef(scale, scale, 1);
		Tessellator tessellator = Tessellator.instance;
		
		float size = 0.5F;
		float f = 0.4F;
		float f1 = 0.6F;

		if (!invert)
		{
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0));
			tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0));
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1));
			tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1));
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top right
			tessellator.addVertexWithUV(size + radius / edge, -size - radius / edge, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top left
			tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom left
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top left
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom left
			tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom right
			tessellator.addVertexWithUV(-size - radius / edge, 1.5F - radius / edge - 2, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top left
			tessellator.addVertexWithUV(size + radius / edge, size + radius / edge, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom left
			tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom right
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom left
			tessellator.addVertexWithUV(-size - radius / edge, size + radius / edge, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom right
			tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top right
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top left
			tessellator.draw();
		}
		else
		{
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0));
			tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0));
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1));
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1));
			tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1));
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom left
			tessellator.addVertexWithUV(size + radius / edge, -size - radius / edge, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top left
			tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top right
			tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom right
			tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f)); // Bottom left
			tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top left
			tessellator.addVertexWithUV(-size - radius / edge, 1.5F - radius / edge - 2, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 0)); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom right
			tessellator.addVertexWithUV(size + radius / edge, size + radius / edge, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom left
			tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 0), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top left
			tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top right
			tessellator.addVertexWithUV(-size - radius / edge, size + radius / edge, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), 1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom right
			tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), 1)); // Bottom left
			tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(icon.getMinU(), icon.getMaxU(), f1), TFRenderHelper.median(icon.getMinV(), icon.getMaxV(), f1)); // Top left
			tessellator.draw();
		}
		
		GL11.glPopMatrix();
	}

	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		render((TileEntityGroundBridgeTeleporter)tileentity, d, d1, d2, f);
	}
}
