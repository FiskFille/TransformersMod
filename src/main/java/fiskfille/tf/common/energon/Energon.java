package fiskfille.tf.common.energon;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public abstract class Energon
{
    public abstract Block getCrystal();
    
    public abstract Item getCrystalPiece();
    
    public abstract int getColor();
}
