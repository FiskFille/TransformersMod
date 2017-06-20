package fiskfille.tf.common.data;

import net.minecraft.entity.player.EntityPlayer;

public class TFDataPrev<T> extends TFData<T>
{
    public final TFData tracking;

    public TFDataPrev(TFData<T> type)
    {
        super(false, type.defaultValue, player -> false);
        this.tracking = type;
    }

    @Override
    public boolean set(EntityPlayer player, T value)
    {
        return false;
    }

    @Override
    public boolean setWithoutNotify(EntityPlayer player, T value)
    {
        return false;
    }

    @Override
    public void clamp(EntityPlayer player, T min, T max)
    {
    }

    @Override
    public void clampWithoutNotify(EntityPlayer player, T min, T max)
    {
    }

    @Override
    public void incr(EntityPlayer player, T value)
    {
    }

    @Override
    public void incrWithoutNotify(EntityPlayer player, T value)
    {
    }
}
