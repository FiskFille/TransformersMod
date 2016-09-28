package fiskfille.tf.client.model.transformer.vehicle;

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
        	
            render(armorFromNBT[0], flag1, true);
        }
    }
    
    public void setupRenderState()
    {
    }

    public void render(ItemStack itemstack, boolean hasLightsLayer, boolean displayVehicle)
    {
    }
}
