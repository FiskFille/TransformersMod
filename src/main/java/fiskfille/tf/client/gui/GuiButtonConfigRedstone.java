package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.tileentity.TileEntityMachine;

@SideOnly(Side.CLIENT)
public class GuiButtonConfigRedstone extends GuiButtonFlat
{
    public TileEntityMachine machine;
    
    public GuiButtonConfigRedstone(int id, int x, int y, TileEntityMachine tile)
    {
        super(id, x, y, 13, "");
        machine = tile;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            mc.getTextureManager().bindTexture(tfButtonTextures);
            GL11.glColor4f(1, 1, 1, 1);
            field_146123_n = new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
            drawTexturedModalRect(xPosition, yPosition, 230 + (field_146123_n ? width : 0), 13 + machine.redstoneMode.ordinal() * height, width, height);
        }
    }
    
    @Override
    public List<String> getHoverText()
    {
        return Arrays.asList(I18n.format("gui.tf.redstone"), EnumChatFormatting.GRAY + I18n.format("gui.tf.redstone." + machine.redstoneMode.name().toLowerCase(Locale.ENGLISH)));
    }
}
