package fiskfille.tf.common.energon.power;

import net.minecraft.item.ItemStack;

public interface IEnergyContainerItem
{
    float receiveEnergy(ItemStack itemstack, float amount, boolean simulate);

    float extractEnergy(ItemStack itemstack, float amount, boolean simulate);

    float getEnergyStored(ItemStack itemstack);

    float getEnergyCapacity(ItemStack itemstack);
}
