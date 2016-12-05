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
        energonType = type;
    }

    @Override
    public Energon getEnergonType()
    {
        return energonType;
    }

    @Override
    public int getMass()
    {
        return Energon.CRYSTAL_SHARD;
    }
}
