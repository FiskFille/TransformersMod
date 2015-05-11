package fiskfille.tf.common.energon;

import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class DefualtEnergon extends Energon
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
}
