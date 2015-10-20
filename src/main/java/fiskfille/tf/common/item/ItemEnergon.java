package fiskfille.tf.common.item;

import net.minecraft.item.Item;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;

public class ItemEnergon extends Item implements IEnergon
{
    private Energon energonType;
    
    public ItemEnergon(Energon type)
    {
        super();
        this.energonType = type;
    }
    
    public Energon getEnergonType()
    {
        return energonType;
    }
    
    public int getMass()
    {
        return 5;
    }
}
