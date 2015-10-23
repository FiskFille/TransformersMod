package fiskfille.tf.client.model.transformer.vehicle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.item.ItemDisplayVehicle;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;

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
    
    public void setupRenderLayers(ItemStack itemstack, ModelRenderer model)
	{
		if (itemstack != null && TFArmorDyeHelper.isDyed(itemstack) && itemstack.getItem() instanceof ItemTransformerArmor)
		{
			Transformer transformer = ((ItemTransformerArmor)itemstack.getItem()).getTransformer();
			TransformerModel tfModel = TFModelRegistry.getModel(transformer);
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			Minecraft mc = Minecraft.getMinecraft();
			float[] afloat = TFHelper.hexToRGB(TFArmorDyeHelper.getPrimaryColor(itemstack));

			GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
			mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_primary.png"));
			model.render(0.0625F);

			afloat = TFHelper.hexToRGB(TFArmorDyeHelper.getSecondaryColor(itemstack));
			GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
			mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_secondary.png"));
			model.render(0.0625F);

			GL11.glColor4f(1, 1, 1, 1);
			mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_base.png"));
			model.render(0.0625F);
		}
		else
		{
			model.render(0.0625F);
		}
	}
}