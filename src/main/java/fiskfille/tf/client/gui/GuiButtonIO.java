package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.tileentity.TileEntityMachine;
import fiskfille.tf.common.tileentity.TileEntityMachine.EnumIO;

@SideOnly(Side.CLIENT)
public class GuiButtonIO extends GuiButtonFlat
{
    public TileEntityMachine machine;
    public ForgeDirection side;
    
    public GuiButtonIO(int id, int x, int y, TileEntityMachine tile, ForgeDirection dir)
    {
        super(id, x, y, 13, "");
        machine = tile;
        side = dir;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (visible)
        {
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f(1, 1, 1, 1);
            field_146123_n = new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
            int hoverState = getHoverState(field_146123_n);
            
            drawTexturedModalRect(xPosition, yPosition, 60 + side.ordinal() * width, 65 + hoverState * height, width, height);
            EnumIO mode = machine.io[side.ordinal()];
            
            if (mode.ordinal() > 0)
            {
                drawTexturedModalRect(xPosition, yPosition, 138 + (mode.ordinal() - 1) * width, 65, width, height);
            }
        }
    }
    
    @Override
    public List<String> getHoverText()
    {
        return Arrays.asList(I18n.format("direction." + side.name().toLowerCase(Locale.ENGLISH)), EnumChatFormatting.GRAY + I18n.format("gui.tf.io.mode", I18n.format("gui.tf.io.mode." + machine.io[side.ordinal()].name().toLowerCase(Locale.ENGLISH))));
    }
}
