package fiskfille.tf.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

@SideOnly(Side.CLIENT)
public class GuiVerticalSlider extends GuiSliderBase
{
	protected static final ResourceLocation buttonTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/widgets.png");

	public GuiVerticalSlider(int id, int x, int y, int height)
	{
		super(id, x, y, 20, height, "");
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1, 1, 1, 1);
			field_146123_n = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
			int k = getHoverState(field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			drawTexturedModalRect(xPosition, yPosition, k * 20, 0, width, height / 2);
			drawTexturedModalRect(xPosition, yPosition + height / 2, k * 20, 200 - height / 2, width, height / 2);
			mouseDragged(mc, mouseX, mouseY);
		}
	}
	
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (enabled && visible && mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height)
        {
            percentage = (float) (mouseY - (yPosition + 4)) / (float) (height - 8);

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

	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
	{
		if (visible)
		{
			if (dragging)
			{
				percentage = (float) (mouseY - (yPosition + 4)) / (float) (height - 8);

				if (percentage < 0)
				{
					percentage = 0;
				}

				if (percentage > 1)
				{
					percentage = 1;
				}
			}

			GL11.glColor4f(1, 1, 1, 1);
			drawTexturedModalRect(xPosition, yPosition + (int) (percentage * (float) (height - 8)), 20, 0, 20, 4);
			drawTexturedModalRect(xPosition, yPosition + (int) (percentage * (float) (height - 8)) + 4, 20, 196, 20, 4);
		}
	}
}
