package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public class GuiHoverField extends GuiButtonFlat
{
    private final List<String> hoverText;

    public GuiHoverField(int x, int y, int width, int height, List<String> text)
    {
        super(-1, x, y, width, "");
        this.height = height;
        hoverText = text;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        field_146123_n = new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return false;
    }

    @Override
    public List<String> getHoverText()
    {
        return hoverText;
    }
    
    public String colorFormat(int color)
    {
        return String.format("&<0x%s>", color);
    }
}
