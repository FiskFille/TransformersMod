package fiskfille.tf.client.model.transformer.vehicle;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.common.item.ItemDisplayVehicle;
import fiskfille.tf.helper.TFArmorDyeHelper;

public class ModelVehicleBase extends ModelChildBase.Base
{
    public void renderBase(ItemStack itemstack)
    {
        if (!itemstack.hasTagCompound())
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        ItemStack[] armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(itemstack);

        if (armorFromNBT == null)
        {
            ItemDisplayVehicle.setNBTData(itemstack);
            armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(itemstack);
        }

        if (TFArmorDyeHelper.areColorsIdentical(armorFromNBT[0], armorFromNBT[1], armorFromNBT[2]))
        {
            render(armorFromNBT[0]);
        }
    }

    public void render(ItemStack itemstack)
    {
    }
}