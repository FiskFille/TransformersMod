package fiskfille.tf.common.energon;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

public abstract class Energon
{
    public abstract Block getCrystal();

    public abstract Item getCrystalPiece();

    public abstract int getColor();

    public abstract String getId();
    
    public String getTranslatedName()
    {
    	return StatCollector.translateToLocal("energon." + getId());
    }
}
