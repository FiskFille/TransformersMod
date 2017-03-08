package fiskfille.tf.client.gui;

import java.awt.Rectangle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.GL11;

public class GuiButtonAlt extends GuiButton
{
    public GuiButtonAlt(int id, int x, int y, int width, int height, String s)
    {
        super(id, x, y, width, height, s);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            field_146123_n = new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
            int k = getHoverState(field_146123_n);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            drawTexturedModalRect(xPosition, yPosition, 0, 46 + k * 20, width / 2, height / 2);
            drawTexturedModalRect(xPosition, yPosition + height / 2, 0, 66 - height / 2 + k * 20, width / 2, height / 2);
            drawTexturedModalRect(xPosition + width / 2, yPosition, 200 - width / 2, 46 + k * 20, width / 2, height / 2);
            drawTexturedModalRect(xPosition + width / 2, yPosition + height / 2, 200 - width / 2, 66 - height / 2 + k * 20, width / 2, height / 2);
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

            drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, l);
        }
    }
}
