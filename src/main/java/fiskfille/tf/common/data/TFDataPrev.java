package fiskfille.tf.common.data;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.base.Predicates;

public class TFDataPrev extends TFData
{
    public final TFData tracking;

    public TFDataPrev(TFData type)
    {
        super(false, type.defaultValue, Predicates.alwaysFalse());
        tracking = type;
    }

    @Override
    public boolean set(EntityPlayer player, Object value)
    {
        return false;
    }

    @Override
    public boolean setWithoutNotify(EntityPlayer player, Object value)
    {
        return false;
    }

    @Override
    public void clamp(EntityPlayer player, Object min, Object max)
    {
    }

    @Override
    public void clampWithoutNotify(EntityPlayer player, Object min, Object max)
    {
    }

    @Override
    public void incr(EntityPlayer player, Object value)
    {
    }

    @Override
    public void incrWithoutNotify(EntityPlayer player, Object value)
    {
    }
}
