package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelControlPanel;
import fiskfille.tf.common.block.BlockControlPanel;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderControlPanel extends TileEntitySpecialRenderer
{
    private Minecraft mc = Minecraft.getMinecraft();
    private ItemRenderer itemRenderer = new ItemRenderer(mc);
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
        GL11.glRotatef(BlockControlPanel.getDirection(metadata) * 90 + 180, 0.0F, 1.0F, 0.0F);

        if (BlockControlPanel.isBlockLeftSideOfPanel(metadata))
        {
            GL11.glTranslatef(0.5F, 0, 0);
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            for (int i = 0; i < tile.getSizeInventory(); ++i)
            {
                ItemStack itemstack = tile.getStackInSlot(i);

                if (itemstack != null)
                {
                    GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glRotatef(-90, 0, 1, 0);
                    GL11.glTranslatef(-0.497F, 0.8765F, -0.2825F - i * 0.2175F);

                    float scale = 0.155F;
                    GL11.glScalef(-scale, -scale, scale);

                    GL11.glColor4f(1, 1, 1, 1);
                    itemRenderer.renderItem(mc.thePlayer, itemstack, 0);
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glPopMatrix();
                }
            }

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/ground_bridge_control_panel.png"));
            model.setBreaking(false);
            model.render(tile, partialTicks);

            TFRenderHelper.setLighting(61680);
            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/ground_bridge_control_panel_lights.png"));
            model.setBreaking(false);
            model.render(tile, partialTicks);
            model.table1.postRender(0.0625F);
            model.table2.postRender(0.0625F);

            String dimensionName = "";

            if (tile.getWorldObj() != null)
            {
                dimensionName = TFHelper.getDimensionName(tile.getDestDimensionID());
            }

            GL11.glPushMatrix();
            model.screen1.postRender(0.0625F);
            model.screen2.postRender(0.0625F);
            renderText(StatCollector.translateToLocal("ground_bridge.destination"), 0, 0, 0, -1);
            renderText(StatCollector.translateToLocalFormatted("ground_bridge.destination.format", tile.destX, tile.hasUpgrade(DataCore.leveler) ? String.format("%s -> %s", tile.prevDestY, tile.destY) : tile.destY, tile.destZ, dimensionName), 1, 0, 0, -1);

            if (!tile.errors.isEmpty())
            {
                renderText(StatCollector.translateToLocal("ground_bridge.error"), 2, 0, 0.025F, 0xC10000);
                renderText(tile.errors.get(0).translate(), 3, 0, 0.025F, 0xC10000);

                if (tile.errors.size() == 1)
                {
                    renderText(StatCollector.translateToLocal("ground_bridge.error.no_other_errors"), 7, 0, 0, -1);
                }
                else
                {
                    renderText(StatCollector.translateToLocalFormatted("ground_bridge.error.other_error" + (tile.errors.size() == 2 ? "" : "s"), tile.errors.size() - 1), 7, 0, 0, 0xC10000);
                }
            }

            if (tile.hasUpgrade(DataCore.spaceBridge))
            {
                GL11.glPushMatrix();
                model.dimPanel1.postRender(0.0625F);
                model.dimPanel2.postRender(0.0625F);
                GL11.glPushMatrix();
                model.dimPanel3.postRender(0.0625F);
                GL11.glTranslatef(0.3125F, -0.025F, -0.0625F * 5.21F);
                renderCenteredText("<", 0, 0, 0, -1, 0.00725F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                model.dimPanel4.postRender(0.0625F);
                GL11.glTranslatef(0.3125F, -0.025F, -0.0625F * 5.21F);
                renderCenteredText(">", 0, 0, 0, -1, 0.00725F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                model.dimPanel6.postRender(0.0625F);
                model.dimPanel7.postRender(0.0625F);
                GL11.glTranslatef(0.345F, -0.025F, -0.0625F * 6.3F);
                GL11.glRotatef(5, 1, 0, 0);
                renderCenteredText(tile.getDestDimensionID() + "", 0, 0, 0.02F, -1, 0.00725F);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.table6.postRender(0.0625F);
            GL11.glPushMatrix();
            model.compass_1.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocal("ground_bridge.direction.south"), 0, -0.0465F, 0.13F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.compass_2.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocal("ground_bridge.direction.west"), 0, -0.2395F, -0.0625F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.compass_3.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocal("ground_bridge.direction.north"), 0, -0.0465F, -0.2575F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            model.compass_4.postRender(0.0625F);
            GL11.glTranslatef(0, -0.032F, 0);
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glRotatef(-90, 1, 0, 0);
            renderCenteredText(StatCollector.translateToLocal("ground_bridge.direction.east"), 0, 0.1475F, -0.0625F, -1, 0.00725F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            TFRenderHelper.resetLighting();

            if (tile.getWorldObj() != null)
            {
                int progress = TFRenderHelper.getBlockDestroyProgress(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);

                if (progress >= 0)
                {
                    OpenGlHelper.glBlendFunc(774, 768, 1, 0);
                    bindTexture(new ResourceLocation(String.format("textures/blocks/destroy_stage_%s.png", progress)));
                    GL11.glColor4f(1, 1, 1, 0.5F);
                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    model.setBreaking(true);
                    model.render(tile, partialTicks);
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glPopMatrix();
                }
            }

            GL11.glDisable(GL11.GL_BLEND);
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

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityControlPanel) tileentity, d, d1, d2, f);
    }
}
