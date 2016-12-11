package fiskfille.tf.client.model.transformer.vehicle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.common.item.ItemDisplayVehicle;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;

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

        if (TFArmorDyeHelper.areColorsIdentical(armorFromNBT[0], armorFromNBT[1], armorFromNBT[2]))
        {
        	boolean flag1 = false;
        	Transformer transformer = TFHelper.getTransformerFromArmor(armorFromNBT[0]);
        	
        	if (transformer != null && TFModelRegistry.getModel(transformer) != null)
        	{
        		flag1 = TFModelRegistry.getModel(transformer).hasLightsLayer();
        	}
        	
            render(null, armorFromNBT[0], flag1);
        }
    }

    /**
     * Renders the vehicle mode
     * 
     * @param player            The player for which this vehicle mode should be rendered, if any
     * @param itemstack         The ItemStack containing the vehicle mode's data
     * @param hasLightsLayer    If this vehicle mode has an additional render layer for glowy bits
     */
    public void render(EntityPlayer player, ItemStack itemstack, boolean hasLightsLayer)
    {
    }
}
