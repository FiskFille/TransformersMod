package fiskfille.tf.common.energon;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;

public class RedEnergon extends Energon
{
    @Override
    public Block getCrystal()
    {
        return TFBlocks.redEnergonCrystal;
    }

    @Override
    public Item getCrystalPiece()
    {
        return TFItems.redEnergonCrystalPiece;
    }

    @Override
    public int getColor()
    {
        return 0xFF0000;
    }

    @Override
    public String getId()
    {
        return "red";
    }
    
    @Override
    public float getEnergyValue()
    {
    	return 10;
    }
}
