package fiskfille.tf.client.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.tileentity.TileEntityMachine;

@SideOnly(Side.CLIENT)
public class GuiButtonDistribution extends GuiButtonFlat
{
    public TileEntityMachine machine;
    
    public GuiButtonDistribution(int id, int x, int y, TileEntityMachine tile)
    {
        super(id, x, y, 13, "");
        machine = tile;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        switch (machine.distribution)
        {
        case QUEUED:
            displayString = "=";
            break;
        case SPREAD:
            displayString = "/";
            break;
        }
        
        super.drawButton(mc, mouseX, mouseY);
    }
    
    @Override
    public List<String> getHoverText()
    {
        return Arrays.asList(I18n.format("gui.tf.distribution"), EnumChatFormatting.GRAY + I18n.format("gui.tf.distribution." + machine.distribution.name().toLowerCase(Locale.ENGLISH)));
    }
}
