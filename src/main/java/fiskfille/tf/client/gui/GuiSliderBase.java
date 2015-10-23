package fiskfille.tf.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderBase extends GuiButton
{
    public float percentage;
    public float prevPercentage;
    public boolean dragging;

    public GuiSliderBase(int id, int x, int y, String s)
    {
        super(id, x, y, 150, 20, s);
        this.percentage = 1.0F;
        this.prevPercentage = 1.0F;
    }

    public int getHoverState(boolean b)
    {
        return 0;
    }

    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            if (this.dragging)
            {
                this.percentage = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);

                if (this.percentage < 0.0F)
                {
                    this.percentage = 0.0F;
                }

                if (this.percentage > 1.0F)
                {
                    this.percentage = 1.0F;
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.percentage * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.percentage * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
        {
            this.percentage = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.percentage < 0.0F)
            {
                this.percentage = 0.0F;
            }

            if (this.percentage > 1.0F)
            {
                this.percentage = 1.0F;
            }
            
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void mouseReleased(int mouseX, int mouseY)
    {
        this.dragging = false;
    }
}
