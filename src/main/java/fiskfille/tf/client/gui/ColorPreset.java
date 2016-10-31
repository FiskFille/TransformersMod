package fiskfille.tf.client.gui;

import java.awt.Color;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

import org.lwjgl.opengl.GL11;

public class ColorPreset extends Gui
{
    public FontRenderer fontRendererObj;
    public int posX;
    public int posY;

    public int primaryColor;
    public int secondaryColor;
    public String name;

    public ColorPreset(int primaryColor, int secondaryColor, String name)
    {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.name = name;
    }

    public void initGui()
    {
    }

    public void updateScreen()
    {
    }

    protected void keyTyped(char c, int key)
    {
    }

    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        Color color = new Color(primaryColor);
        Color color1 = new Color(secondaryColor);

        GL11.glColor4f(0, 0, 0, 1);
        drawTexturedModalRect(posX, posY, 0, 0, 50, 50);

        GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, 1);
        drawTexturedModalRect(posX + 1, posY + 1, 0, 0, 23, 48);
        GL11.glColor4f((float) color1.getRed() / 255, (float) color1.getGreen() / 255, (float) color1.getBlue() / 255, 1);
        drawTexturedModalRect(posX + 26, posY + 1, 0, 0, 23, 48);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}
