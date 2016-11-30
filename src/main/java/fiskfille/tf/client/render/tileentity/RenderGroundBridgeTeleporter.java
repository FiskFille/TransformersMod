package fiskfille.tf.client.render.tileentity;

import fiskfille.tf.client.render.shader.PortalShader;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import fiskfille.tf.helper.TFRenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGroundBridgeTeleporter extends TileEntitySpecialRenderer
{
    private static final ResourceLocation PORTAL_EFFECT = new ResourceLocation("transformers", "textures/misc/portal_effect.png");

    private PortalShader shader;

    public RenderGroundBridgeTeleporter()
    {
        try
        {
            this.shader = new PortalShader();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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

            float f1 = 1 - (float) (tileentity.lastUpdate > 0 ? tileentity.lastUpdate + partialTicks - 1 : 0) / 6;

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

            bindTexture(PORTAL_EFFECT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            this.shader.start();
            this.shader.setTime(tileentity.ticks + partialTicks);
            drawPortal(0, 0, zOffset, scale, radius, edge, indent, false);
            GL11.glColor4f(shade, shade, shade, 1);
            drawPortal(0, 0, zOffset, scale, radius, edge, indent, true);
            this.shader.stop();
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glEnable(GL11.GL_LIGHTING);
            TFRenderHelper.resetLighting();
            GL11.glPopMatrix();
        }
    }

    public void drawPortal(float offsetX, float offsetY, float offsetZ, float scale, float radius, float edge, float indent, boolean invert)
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
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f));
            tessellator.draw();

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 0));
            tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 0));
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 1));
            tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 1));
            tessellator.draw();

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 0)); // Top right
            tessellator.addVertexWithUV(size + radius / edge, -size - radius / edge, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, 0)); // Top left
            tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f)); // Bottom left
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f)); // Bottom right
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 0)); // Top left
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f)); // Bottom left
            tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f)); // Bottom right
            tessellator.addVertexWithUV(-size - radius / edge, 1.5F - radius / edge - 2, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, 0)); // Top right
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f1)); // Top left
            tessellator.addVertexWithUV(size + radius / edge, size + radius / edge, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, 1)); // Bottom left
            tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 1)); // Bottom right
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1)); // Top right
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 1)); // Bottom left
            tessellator.addVertexWithUV(-size - radius / edge, size + radius / edge, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, 1)); // Bottom right
            tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f1)); // Top right
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1)); // Top left
            tessellator.draw();
        }
        else
        {
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f));
            tessellator.draw();

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f));
            tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 0));
            tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 0));
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 1));
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1));
            tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 1));
            tessellator.draw();

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size + radius, -size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f)); // Bottom left
            tessellator.addVertexWithUV(size + radius / edge, -size - radius / edge, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, 0)); // Top left
            tessellator.addVertexWithUV(size, -size - radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 0)); // Top right
            tessellator.addVertexWithUV(size, -size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f)); // Bottom right
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size - radius, -size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f)); // Bottom right
            tessellator.addVertexWithUV(-size, -size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f)); // Bottom left
            tessellator.addVertexWithUV(-size, -size - radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 0)); // Top left
            tessellator.addVertexWithUV(-size - radius / edge, 1.5F - radius / edge - 2, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, 0)); // Top right
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(size, size + radius, indent, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, 1)); // Bottom right
            tessellator.addVertexWithUV(size + radius / edge, size + radius / edge, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, 1)); // Bottom left
            tessellator.addVertexWithUV(size + radius, size, indent, TFRenderHelper.median(0, 1, 0), TFRenderHelper.median(0, 1, f1)); // Top left
            tessellator.addVertexWithUV(size, size, 0, TFRenderHelper.median(0, 1, f), TFRenderHelper.median(0, 1, f1)); // Top right
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-size - radius, size, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, f1)); // Top right
            tessellator.addVertexWithUV(-size - radius / edge, size + radius / edge, indent, TFRenderHelper.median(0, 1, 1), TFRenderHelper.median(0, 1, 1)); // Bottom right
            tessellator.addVertexWithUV(-size, size + radius, indent, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, 1)); // Bottom left
            tessellator.addVertexWithUV(-size, size, 0, TFRenderHelper.median(0, 1, f1), TFRenderHelper.median(0, 1, f1)); // Top left
            tessellator.draw();
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityGroundBridgeTeleporter) tileentity, d, d1, d2, f);
    }
}
