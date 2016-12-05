package fiskfille.tf.common.energon;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

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
        return TFItems.energonCrystalShard;
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
