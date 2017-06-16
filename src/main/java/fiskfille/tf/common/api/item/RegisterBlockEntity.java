package fiskfille.tf.common.api.item;

import net.minecraft.tileentity.TileEntity;

public interface RegisterBlockEntity
{
    Class<? extends TileEntity> getEntity();
}
