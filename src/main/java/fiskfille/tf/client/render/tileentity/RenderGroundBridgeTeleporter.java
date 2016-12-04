package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.render.shader.PortalShader;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderGroundBridgeTeleporter extends TileEntitySpecialRenderer
{
    private static final ResourceLocation PORTAL_EFFECT = new ResourceLocation(TransformersMod.modid, "textures/misc/portal_effect.png");

    private PortalShader shader;

    public RenderGroundBridgeTeleporter()
    {
        try
        {
            shader = new PortalShader();
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

            GL11.glScalef(MathHelper.clamp_float(f1 * 2, 0, 1), f1, 1);

            GL11.glColor4f(1, 1, 1, 1);
            TFRenderHelper.setLighting(61680);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);

            float zOffset = 0.2F;
            float scale = 2.0F;
            float shade = 0.95F;

            bindTexture(PORTAL_EFFECT);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            shader.start();
            shader.setTime(tileentity.ticks + partialTicks);
            drawPortal(0, 0, zOffset, scale, false);
            GL11.glColor4f(shade, shade, shade, 1);
            drawPortal(0, 0, zOffset, scale, true);
            shader.stop();
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

//            GL11.glDisable(GL11.GL_TEXTURE_2D);
//            drawPortal(0, 0, zOffset, scale, radius, edge, indent, false);
//            GL11.glEnable(GL11.GL_TEXTURE_2D);
            
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glEnable(GL11.GL_LIGHTING);
            TFRenderHelper.resetLighting();
            GL11.glPopMatrix();
        }
    }

    public void drawPortal(float offsetX, float offsetY, float offsetZ, float scale, boolean invert)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180, 0, 0, 1);
        GL11.glTranslatef(offsetX, offsetY, offsetZ);
        GL11.glScalef(scale, scale, 1);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(GL11.GL_TRIANGLES);
        
        float corners = 45;
        float angle = 360F / corners;
        float offset = 0;
        
        for (int i = 0; i < 3; ++i)
        {
            float radius = 0;
            float innerRadius = 0;
            float dent = 0;
            
            if (i == 0)
            {
                radius = 2;
                innerRadius = 1;
                dent = -1;
            }
            else if (i == 1)
            {
                radius = 1;
                innerRadius = 0.25F;
                dent = -0.5F;
            }
            else
            {
                radius = 0.25F;
            }
            
            for (int j = 0; j < corners; ++j)
            {
                Vec3 pos1 = Vec3.createVectorHelper(0, radius, 0);
                Vec3 pos2 = Vec3.createVectorHelper(0, radius, 0);
                pos1.rotateAroundZ(angle * j * (float)Math.PI / 180.0F);
                pos2.rotateAroundZ(angle * (j - 1) * (float)Math.PI / 180.0F);
                Vec3 pos3 = Vec3.createVectorHelper((pos1.xCoord + pos2.xCoord) / 2, (pos1.yCoord + pos2.yCoord) / 2, (pos1.zCoord + pos2.zCoord) / 2);
                
                
                Vec3 pos4 = Vec3.createVectorHelper(0, innerRadius, 0);
                Vec3 pos5 = Vec3.createVectorHelper(0, innerRadius, 0);
                pos4.rotateAroundZ(angle * j * (float)Math.PI / 180.0F);
                pos5.rotateAroundZ(angle * (j - 1) * (float)Math.PI / 180.0F);
                
                if (!invert)
                {
                    tessellator.addVertex(pos3.xCoord, pos3.yCoord, offset);
                    tessellator.addVertex(pos2.xCoord, pos2.yCoord, offset);
                    tessellator.addVertex(pos5.xCoord, pos5.yCoord, dent + offset);
                    tessellator.addVertex(pos1.xCoord, pos1.yCoord, offset);
                    tessellator.addVertex(pos3.xCoord, pos3.yCoord, offset);
                    tessellator.addVertex(pos4.xCoord, pos4.yCoord, dent + offset);
                    tessellator.addVertex(pos3.xCoord, pos3.yCoord, offset);
                    tessellator.addVertex(pos5.xCoord, pos5.yCoord, dent + offset);
                    tessellator.addVertex(pos4.xCoord, pos4.yCoord, dent + offset);
                }
                else
                {
                    tessellator.addVertex(pos5.xCoord, pos5.yCoord, dent + offset);
                    tessellator.addVertex(pos2.xCoord, pos2.yCoord, offset);
                    tessellator.addVertex(pos3.xCoord, pos3.yCoord, offset);
                    tessellator.addVertex(pos1.xCoord, pos1.yCoord, offset);
                    tessellator.addVertex(pos4.xCoord, pos4.yCoord, dent + offset);
                    tessellator.addVertex(pos3.xCoord, pos3.yCoord, offset);
                    tessellator.addVertex(pos3.xCoord, pos3.yCoord, offset);
                    tessellator.addVertex(pos4.xCoord, pos4.yCoord, dent + offset);
                    tessellator.addVertex(pos5.xCoord, pos5.yCoord, dent + offset);
                }
            }
            
            offset += dent;
        }
        
        tessellator.draw();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityGroundBridgeTeleporter) tileentity, d, d1, d2, f);
    }
}
