package fiskfille.tf.common.item;

import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import net.minecraft.item.Item;

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
