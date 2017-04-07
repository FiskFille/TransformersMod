package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import org.lwjgl.opengl.GL11;

public class GuiButtonComponent extends GuiButtonFlat
{
    public GuiButtonComponent(int id, int x, int y)
    {
        super(id, x, y, 6, "");
        height = 18;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            mc.getTextureManager().bindTexture(tfButtonTextures);
            GL11.glColor4f(1, 1, 1, 1);
            field_146123_n = new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
            int hoverState = getHoverState(field_146123_n);
            
            drawTexturedModalRect(xPosition, yPosition, 60 + hoverState * width, 104, width, height);
        }
    }
    
    @Override
    public List<String> getHoverText()
    {
        if (enabled)
        {
            return Arrays.asList(I18n.format("gui.display_station.component"));
        }
        
        return Arrays.asList();
    }
}
