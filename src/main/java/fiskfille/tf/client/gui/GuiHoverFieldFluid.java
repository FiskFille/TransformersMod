package fiskfille.tf.client.gui;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.helper.TFFormatHelper;

@SideOnly(Side.CLIENT)
public class GuiHoverFieldFluid extends GuiHoverField
{
    private FluidTankTF fluidTank;
    
    public GuiHoverFieldFluid(int x, int y, int width, int height, FluidTankTF tank)
    {
        super(x, y, width, height, new ArrayList<String>());
        fluidTank = tank;
    }
    
    public void update(FluidTankTF tank)
    {
        fluidTank = tank;
    }
    
    @Override
    public List<String> getHoverText()
    {
        return TFFormatHelper.toString(fluidTank.format());
    }
}
