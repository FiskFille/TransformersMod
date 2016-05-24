package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelControlPanel;
import fiskfille.tf.common.block.BlockGroundBridgeControl;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderControlPanel extends TileEntitySpecialRenderer
{
    private Minecraft mc = Minecraft.getMinecraft();
    private ModelControlPanel model = new ModelControlPanel();

    public void render(TileEntityControlPanel tile, double x, double y, double z, float partialTicks)
    {
        int metadata = 0;

        if (tile.getWorldObj() != null)
        {
            metadata = tile.getBlockMetadata();
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glRotatef(metadata * 90 + 180, 0.0F, 1.0F, 0.0F);

        if (!BlockGroundBridgeControl.isBlockSideOfPanel(metadata))
        {
            GL11.glTranslatef(0.5F, 0, 0);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/ground_bridge_control_panel.png"));
            model.render(tile, partialTicks);
            
            TFRenderHelper.setLighting(61680);
            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/ground_bridge_control_panel_lights.png"));
            model.render(tile, partialTicks);
            model.table1.postRender(0.0625F);
            model.table2.postRender(0.0625F);

            GL11.glPushMatrix();
            model.screen1.postRender(0.0625F);
            model.screen2.postRender(0.0625F);
            renderText(StatCollector.translateToLocal("ground_bridge.destination"), 0, 0, 0, -1);
            renderText(StatCollector.translateToLocalFormatted("ground_bridge.destination.format", tile.destX, tile.destY, tile.destZ), 1, 0, 0, -1);

            if (!tile.errors.isEmpty())
            {
                renderText(StatCollector.translateToLocal("ground_bridge.error"), 2, 0, 0.025F, 0xC10000);
                renderText(tile.errors.get(0).translate(tile.getWorldObj().getHeight()), 3, 0, 0.025F, 0xC10000);

                if (tile.errors.size() == 1)
                {
                    renderText(StatCollector.translateToLocal("ground_bridge.error.no_other_errors"), 7, 0, 0, -1);
                }
                else
                {
                    renderText(StatCollector.translateToLocalFormatted("ground_bridge.error.other_error" + (tile.errors.size() == 2 ? "" : "s"), tile.errors.size() - 1), 7, 0, 0, 0xC10000);
                }
            }

            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.table6.postRender(0.0625F);
            GL11.glPushMatrix();
            model.compass_1.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocalFormatted("ground_bridge.direction.south"), 0, -0.0465F, 0.13F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.compass_2.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocalFormatted("ground_bridge.direction.west"), 0, -0.2395F, -0.0625F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.compass_3.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocalFormatted("ground_bridge.direction.north"), 0, -0.0465F, -0.2575F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.compass_4.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocalFormatted("ground_bridge.direction.east"), 0, 0.1475F, -0.0625F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            
            GL11.glDisable(GL11.GL_BLEND);
            TFRenderHelper.resetLighting();
        }

        GL11.glPopMatrix();
    }

    protected void renderText(String s, int line, float x, float y, int color)
    {
        renderText(s, line, x, y, color, 0.004F);
    }

    protected void renderText(String s, int line, float x, float y, int color, float scale)
    {
    	float left = 0.05F;
        float top = 0.0375F;
        GL11.glPushMatrix();
        GL11.glTranslatef(x + left, y + top + 0.05F * line, -0.001F);
        GL11.glScalef(scale, scale, -scale);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        mc.fontRenderer.drawSplitString(s, 0, 0, 200, color);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    protected void renderCenteredText(String s, int line, float x, float y, int color, float scale)
    {
    	float left = 0.05F;
        float top = 0.0375F;
        GL11.glPushMatrix();
        GL11.glTranslatef(x + left, y + top + 0.05F * line, -0.001F);
        GL11.glScalef(scale, scale, -scale);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        mc.fontRenderer.drawString(s, -mc.fontRenderer.getStringWidth(s) / 2, 0, color);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityControlPanel)tileentity, d, d1, d2, f);
    }
}
