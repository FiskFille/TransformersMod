package fiskfille.tf.client.model.transformer.vehicle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.common.item.ItemDisplayVehicle;
import fiskfille.tf.helper.TFArmorDyeHelper;

public class ModelVehicleBase extends MowzieModelBase
{
    public void renderDisplayVehicle(ItemStack itemstack)
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

        if (TFArmorDyeHelper.areColorsIdentical(armorFromNBT[0], armorFromNBT[1], armorFromNBT[2], armorFromNBT[3]))
        {
            setToInitPose();
            render(null, armorFromNBT[0]);
        }
    }

    /**
     * Renders the vehicle mode
     * 
     * @param player The player for which this vehicle mode should be rendered, if any
     * @param itemstack The ItemStack containing the vehicle mode's data
     */
    public void render(EntityPlayer player, ItemStack itemstack)
    {
    }
}
