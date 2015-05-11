package fiskfille.tf.common.energon;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

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
}
