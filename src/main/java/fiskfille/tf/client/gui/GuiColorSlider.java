package fiskfille.tf.client.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiColorSlider extends GuiSliderBase
{
    public int sliderId;

    public GuiColorSlider(int id, int x, int y, int sliderId, String s)
    {
        super(id, x, y, 150, 20, s);
        this.sliderId = sliderId;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            field_146123_n = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
            int k = getHoverState(field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            drawTexturedModalRect(xPosition, yPosition, 0, 46 + k * 20, width / 2, height);
            drawTexturedModalRect(xPosition + width / 2, yPosition, 200 - width / 2, 46 + k * 20, width / 2, height);

            Color color = sliderId == 1 ? Color.GREEN : sliderId == 2 ? Color.BLUE : Color.RED;
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setColorOpaque_I(0);
            tessellator.addVertex(0, height - 2, zLevel);
            tessellator.setColorOpaque_I(color.getRGB());
            tessellator.addVertex(width - 2, height - 2, zLevel);
            tessellator.addVertex(width - 2, 0, zLevel);
            tessellator.setColorOpaque_I(0);
            tessellator.addVertex(0, 0, zLevel);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glPushMatrix();
            GL11.glTranslatef(xPosition + 1, yPosition + 1, 0);
            tessellator.draw();
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_TEXTURE_2D);

            mouseDragged(mc, mouseX, mouseY);
            int l = 14737632;

            if (packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if (!enabled)
            {
                l = 10526880;
            }
            else if (field_146123_n)
            {
                l = 16777120;
            }

            drawCenteredString(fontrenderer, StatCollector.translateToLocalFormatted("gui.display_station.color.amount", displayString, (int) (percentage * 100)), xPosition + width / 2, yPosition + (height - 8) / 2, l);
        }
    }

    @Override
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            if (dragging)
            {
                percentage = (float) (mouseX - (xPosition + 4)) / (float) (width - 8);

                if (percentage < 0.0F)
                {
                    percentage = 0.0F;
                }

                if (percentage > 1.0F)
                {
                    percentage = 1.0F;
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexturedModalRect(xPosition + (int) (percentage * (width - 8)), yPosition, 0, 66, 4, 20);
            drawTexturedModalRect(xPosition + (int) (percentage * (width - 8)) + 4, yPosition, 196, 66, 4, 20);
        }
    }
}
