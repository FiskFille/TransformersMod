package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

@SideOnly(Side.CLIENT)
public abstract class TransformerModel
{
    public abstract ModelTransformerBase getMainModel();

    public abstract ModelVehicleBase getVehicleModel();
    
    public abstract ModelRenderer[] getFeet();
    
    public abstract ModelRenderer[] getLegs();

    public abstract ModelRenderer getLowerArm();

    public abstract ModelRenderer getUpperArm();

    public abstract ModelRenderer getBody();

    public abstract ModelRenderer getHead();

    public abstract ResourceLocation getTexture(Entity entity);

    public ModelTransformerBase getStealthModel()
    {
        return null;
    }

    public void renderItem(EntityPlayer player, ItemStack stack)
    {
    }

    public void renderCape(EntityPlayer player)
    {
    }

    public void renderFirstPersonArm(EntityPlayer player)
    {
    }

    public String getTextureDir()
    {
        return "";
    }

    public String getTextureDirPrefix()
    {
        return TransformersMod.modid;
    }
    
    public boolean hasLightsLayer()
	{
		return false;
	}

    public abstract ModelTransformerBase getItemInventoryModel();
}
