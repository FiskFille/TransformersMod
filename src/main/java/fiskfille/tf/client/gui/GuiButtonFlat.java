package fiskfille.tf.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;

public class GuiButtonFlat extends GuiButton
{
    public static final ResourceLocation buttonTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/widgets.png");

    public GuiButtonFlat(int id, int x, int y, int width, String s)
    {
        super(id, x, y, width, 13, s);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(buttonTextures);

            GL11.glColor4f(1, 1, 1, 1);
            field_146123_n = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
            int hoverState = getHoverState(field_146123_n);

            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            mouseDragged(mc, mouseX, mouseY);
            int color = 0x373737;

            if (!enabled)
            {
                color = 0x7F7F7F;
            }

            if (width % 2 == 0)
            {
                drawTexturedModalRect(xPosition, yPosition, 60, hoverState * 13, width / 2, height);
                drawTexturedModalRect(xPosition + width / 2, yPosition, 60 + 150 - width / 2, hoverState * 13, width / 2, height);
                fontrenderer.drawString(displayString, xPosition + width / 2 - fontrenderer.getStringWidth(displayString) / 2, yPosition + (height - 6) / 2, color);
            }
            else
            {
                drawTexturedModalRect(xPosition, yPosition, 60, hoverState * 13, width / 2 + 1, height);
                drawTexturedModalRect(xPosition + width / 2 + 1, yPosition, 60 + 150 - width / 2, hoverState * 13, width / 2, height);
                fontrenderer.drawString(displayString, xPosition + width / 2 - fontrenderer.getStringWidth(displayString) / 2 + 1, yPosition + (height - 6) / 2, color);
            }
        }
    }
}
