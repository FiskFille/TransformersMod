package fiskfille.tf.client.gui;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.helper.TFEnergyHelper;

@SideOnly(Side.CLIENT)
public class GuiHoverFieldEnergy extends GuiHoverField
{
    private EnergyStorage energyStorage;
    
    public GuiHoverFieldEnergy(int x, int y, int width, int height, EnergyStorage storage)
    {
        super(x, y, width, height, new ArrayList<String>());
        energyStorage = storage;
    }
    
    public void update(EnergyStorage storage)
    {
        energyStorage = storage;
    }
    
    @Override
    public List<String> getHoverText()
    {
        return TFEnergyHelper.getHoverText(energyStorage);
    }
}
