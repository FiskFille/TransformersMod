package fiskfille.tf.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class GuiVerticalHeightSlider extends GuiVerticalSlider
{
    public GuiSelectReceivers parent;
    private Runnable onChange;

    public GuiVerticalHeightSlider(int id, GuiSelectReceivers parentScreen, int x, int y, int height, Runnable onChange)
    {
        super(id, x, y, height);
        this.onChange = onChange;
        parent = parentScreen;
    }

    @Override
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible && enabled)
        {
            if (dragging)
            {
                percentage = MathHelper.clamp_float((float) (mouseY - (yPosition + 4)) / (float) (height - 8), 0, 1);
            }
            
            percentage = MathHelper.clamp_float(1 - (float) parent.layers.indexOf(parent.getLayer()) / (parent.layers.size() - 1), 0, 1);
            
            if (dragging)
            {
                onChange.run();
            }

            GL11.glDisable(GL11.GL_TEXTURE_2D);

            for (int i = 0; i < parent.layers.size(); ++i)
            {
                float f = 1 - (float)parent.layers.indexOf(parent.layers.get(i)) / (parent.layers.size() - 1);
                float shade = 0.1F;

                GL11.glColor4f(shade, shade, shade, 1);
                drawTexturedModalRect(xPosition + 1, yPosition + (int) (f * (height - 8)) + 3, 0, 0, width / 2 - 2, 2);
            }

            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glColor4f(1, 1, 1, 1);
            drawTexturedModalRect(xPosition, yPosition + (int) (percentage * (height - 8)), 20, 0, width, 4);
            drawTexturedModalRect(xPosition, yPosition + (int) (percentage * (height - 8)) + 4, 20, 196, width, 4);

            drawString(mc.fontRenderer, parent.getLayer() + "", xPosition + width + 3, yPosition + (int) (percentage * (float) (height - 8)), -1);
        }
    }
}
