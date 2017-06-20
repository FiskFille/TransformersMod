package fiskfille.tf.client.model.transformer.vehicle;

import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.common.helper.TFArmorDyeHelper;
import fiskfille.tf.common.item.ItemDisplayVehicle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ModelVehicleBase extends MowzieModelBase
{
    public void renderDisplayVehicle(ItemStack stack)
    {
        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        ItemStack[] armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(stack);

        if (armorFromNBT == null)
        {
            ItemDisplayVehicle.setNBTData(stack);
            armorFromNBT = ItemDisplayVehicle.getArmorFromNBT(stack);
        }

        if (TFArmorDyeHelper.areColorsIdentical(armorFromNBT[0], armorFromNBT[1], armorFromNBT[2], armorFromNBT[3]))
        {
            this.setToInitPose();
            this.render(null, armorFromNBT[0]);
        }
    }

    /**
     * Renders the vehicle mode
     *
     * @param player    The player for which this vehicle mode should be rendered, if any
     * @param stack The ItemStack containing the vehicle mode's data
     */
    public void render(EntityPlayer player, ItemStack stack)
    {
    }
}
