package fiskfille.tf.client.gui;

import java.awt.Rectangle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonSwapColors extends GuiButton
{
    public GuiButtonSwapColors(int id, int x, int y)
    {
        super(id, x, y, 20, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            mc.getTextureManager().bindTexture(GuiButtonFlat.buttonTextures);
            GL11.glColor4f(1, 1, 1, 1);
            boolean flag = new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
            drawTexturedModalRect(xPosition, yPosition, 0, 200 + (flag ? height : 0), width, height);
        }
    }
}
