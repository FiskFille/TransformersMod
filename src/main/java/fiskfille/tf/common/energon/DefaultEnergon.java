package fiskfille.tf.common.energon;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;

public class DefaultEnergon extends Energon
{
    @Override
    public Block getCrystal()
    {
        return TFBlocks.energonCrystal;
    }

    @Override
    public Item getCrystalPiece()
    {
        return TFItems.energonCrystalPiece;
    }
    
    @Override
    public int getColor()
    {
        return 0x0080FF;
    }
    
    @Override
    public String getId()
    {
        return "default";
    }
}
