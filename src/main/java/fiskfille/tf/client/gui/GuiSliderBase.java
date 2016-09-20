package fiskfille.tf.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderBase extends GuiButton
{
    public float percentage;
    public float prevPercentage;
    public boolean dragging;

    public GuiSliderBase(int id, int x, int y, int width, int height, String s)
    {
        super(id, x, y, width, height, s);
        percentage = 1.0F;
        prevPercentage = 1.0F;
    }

    public int getHoverState(boolean b)
    {
        return 0;
    }

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
            drawTexturedModalRect(xPosition + (int) (percentage * (float) (width - 8)), yPosition, 0, 66, 4, 20);
            drawTexturedModalRect(xPosition + (int) (percentage * (float) (width - 8)) + 4, yPosition, 196, 66, 4, 20);
        }
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
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

            dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void mouseReleased(int mouseX, int mouseY)
    {
        dragging = false;
    }
}
