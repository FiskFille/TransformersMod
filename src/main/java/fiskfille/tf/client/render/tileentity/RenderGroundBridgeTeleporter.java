package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import fiskfille.tf.helper.TFHelper;

public class RenderGroundBridgeTeleporter extends TileEntitySpecialRenderer
{
	public void render(TileEntityGroundBridgeTeleporter tileentity, double x, double y, double z, float partialTicks)
	{
		int metadata = 0;

		if (tileentity.getWorldObj() != null)
		{
			metadata = tileentity.getBlockMetadata();
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1F, -1F, -1F);

		if (tileentity.controlPanel != null && tileentity.returnPortal)
		{
			GL11.glRotatef(90 * tileentity.controlPanel.portalDirection, 0, 1, 0);
		}

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor4f(1, 1, 1, 1);
		TFHelper.setLighting(61680);
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);


		bindTexture(new ResourceLocation("textures/blocks/log_oak_top.png"));

		if (metadata == 1)
		{
			float zOffset = -1.5F;
			float indent = 1.75F;
			float edge = 1.5F;
			float scale = 1.18F;
			float radius = 2;
			
			drawPortal(0, 0, zOffset, scale, radius, edge, indent, false);
			float shade = 0.75F;
			GL11.glColor4f(shade, shade, shade, 1);
			drawPortal(0, 0, zOffset, scale, radius, edge, indent, true);
		}

		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	public void drawPortal(float offsetX, float offsetY, float offsetZ, float scale, float radius, float edge, float indent, boolean invert)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef(offsetX, offsetY, offsetZ);
		GL11.glScalef(scale, scale, 1);
		Tessellator tessellator = Tessellator.instance;
		float size = 0.5F;
		float f = 0.4F;
		float f1 = 0.6F;

		if (!invert)
		{
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, size, 0, f, f1);
			tessellator.addVertexWithUV(-size, size, 0, f1, f1);
			tessellator.addVertexWithUV(-size, -size, 0, f1, f);
			tessellator.addVertexWithUV(size, -size, 0, f, f);
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size + radius, size, indent, 0, f1);
			tessellator.addVertexWithUV(size, size, 0, f, f1);
			tessellator.addVertexWithUV(size, -size, 0, f, f);
			tessellator.addVertexWithUV(size + radius, -size, indent, 0, f);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, size, 0, f1, f1);
			tessellator.addVertexWithUV(-size - radius, size, indent, 1, f1);
			tessellator.addVertexWithUV(-size - radius, -size, indent, 1, f);
			tessellator.addVertexWithUV(-size, -size, 0, f1, f);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, -size - radius, indent, f1, 0);
			tessellator.addVertexWithUV(size, -size - radius, indent, f, 0);
			tessellator.addVertexWithUV(size, -size, 0, f, f);
			tessellator.addVertexWithUV(-size, -size, 0, f1, f);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, size, 0, f1, f1);
			tessellator.addVertexWithUV(size, size, 0, f, f1);
			tessellator.addVertexWithUV(size, size + radius, indent, f, 1);
			tessellator.addVertexWithUV(-size, size + radius, indent, f1, 1);
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, -size - radius, indent, 0, f); // Top right
			tessellator.addVertexWithUV(size + radius / edge, -size - radius / edge, indent, 0, 0); // Top left
			tessellator.addVertexWithUV(size + radius, -size, indent, f, 0); // Bottom left
			tessellator.addVertexWithUV(size, -size, 0, f, f); // Bottom right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, -size - radius, indent, f1, 0); // Top left
			tessellator.addVertexWithUV(-size, -size, 0, f1, f); // Bottom left
			tessellator.addVertexWithUV(-size - radius, -size, indent, 1, f); // Bottom right
			tessellator.addVertexWithUV(-size - radius / edge, 1.5F - radius / edge - 2, indent, 1, 0); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size + radius, size, indent, f1, 0); // Top left
			tessellator.addVertexWithUV(size + radius / edge, size + radius / edge, indent, 1, 0); // Bottom left
			tessellator.addVertexWithUV(size, size + radius, indent, 1, f); // Bottom right
			tessellator.addVertexWithUV(size, size, 0, f1, f); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, size + radius, indent, 1, f1); // Bottom left
			tessellator.addVertexWithUV(-size - radius / edge, size + radius / edge, indent, 1, 1); // Bottom right
			tessellator.addVertexWithUV(-size - radius, size, indent, f1, 1); // Top right
			tessellator.addVertexWithUV(-size, size, 0, f1, f1); // Top left
			tessellator.draw();
			GL11.glPopMatrix();
		}
		else
		{
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size, -size, 0, f1, f);
			tessellator.addVertexWithUV(-size, size, 0, f1, f1);
			tessellator.addVertexWithUV(size, size, 0, f, f1);
			tessellator.addVertexWithUV(size, -size, 0, f, f);
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, -size, 0, f, f);
			tessellator.addVertexWithUV(size, size, 0, f, f1);
			tessellator.addVertexWithUV(size + radius, size, indent, 0, f1);
			tessellator.addVertexWithUV(size + radius, -size, indent, 0, f);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size - radius, -size, indent, 1, f);
			tessellator.addVertexWithUV(-size - radius, size, indent, 1, f1);
			tessellator.addVertexWithUV(-size, size, 0, f1, f1);
			tessellator.addVertexWithUV(-size, -size, 0, f1, f);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, -size, 0, f, f);
			tessellator.addVertexWithUV(size, -size - radius, indent, f, 0);
			tessellator.addVertexWithUV(-size, -size - radius, indent, f1, 0);
			tessellator.addVertexWithUV(-size, -size, 0, f1, f);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, size + radius, indent, f, 1);
			tessellator.addVertexWithUV(size, size, 0, f, f1);
			tessellator.addVertexWithUV(-size, size, 0, f1, f1);
			tessellator.addVertexWithUV(-size, size + radius, indent, f1, 1);
			tessellator.draw();

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size + radius, -size, indent, f, 0); // Bottom left
			tessellator.addVertexWithUV(size + radius / edge, -size - radius / edge, indent, 0, 0); // Top left
			tessellator.addVertexWithUV(size, -size - radius, indent, 0, f); // Top right
			tessellator.addVertexWithUV(size, -size, 0, f, f); // Bottom right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size - radius, -size, indent, 1, f); // Bottom right
			tessellator.addVertexWithUV(-size, -size, 0, f1, f); // Bottom left
			tessellator.addVertexWithUV(-size, -size - radius, indent, f1, 0); // Top left
			tessellator.addVertexWithUV(-size - radius / edge, 1.5F - radius / edge - 2, indent, 1, 0); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(size, size + radius, indent, 1, f); // Bottom right
			tessellator.addVertexWithUV(size + radius / edge, size + radius / edge, indent, 1, 0); // Bottom left
			tessellator.addVertexWithUV(size + radius, size, indent, f1, 0); // Top left
			tessellator.addVertexWithUV(size, size, 0, f1, f); // Top right
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-size - radius, size, indent, f1, 1); // Top right
			tessellator.addVertexWithUV(-size - radius / edge, size + radius / edge, indent, 1, 1); // Bottom right
			tessellator.addVertexWithUV(-size, size + radius, indent, 1, f1); // Bottom left
			tessellator.addVertexWithUV(-size, size, 0, f1, f1); // Top left
			tessellator.draw();
			GL11.glPopMatrix();
		}
	}

	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		render((TileEntityGroundBridgeTeleporter)tileentity, d, d1, d2, f);
	}
}
