package fiskfille.tf.common.item;

import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.helper.LiquidHelper;

public class ItemFuelCanister extends ItemLiquidContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon overlay;

    public ItemFuelCanister()
    {
        super();
    }
    
    @Override
    public float getMaxStorage()
    {
    	return LiquidHelper.FUEL_CANISTER_STORAGE;
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_)
    {
        float liquidAmount = 0;

        for (Map.Entry<String, Float> e : getContents(itemstack).entrySet())
        {
            liquidAmount += e.getValue();
        }

        float percentMultiplier = 100F / liquidAmount;

        if (!getContents(itemstack).isEmpty())
        {
            for (Map.Entry<String, Float> e : getContents(itemstack).entrySet())
            {
            	Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                int percent = Math.round(e.getValue() * percentMultiplier);

                list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("gui.energon_processor.content", energon.getTranslatedName(), percent));
            }

            list.add("");
        }
        else
        {
            list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("gui.energon_processor.unidentified"));
        }

        list.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocalFormatted("gui.energon_processor.filled", Math.round(liquidAmount), Math.round(getMaxStorage())));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int metadata, int layer)
    {
        return layer > 0 ? overlay : super.getIconFromDamageForRenderPass(metadata, layer);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int layer)
    {
        if (layer != 1)
        {
            return super.getColorFromItemStack(itemstack, layer);
        }
        else
        {
            return getLiquidColor(itemstack);
        }
    }

    public static void refreshNBT(ItemStack itemstack)
    {
        if (!itemstack.hasTagCompound())
        {
            itemstack.setTagCompound(new NBTTagCompound());
        }
    }

    public static Map<String, Float> getContents(ItemStack itemstack)
    {
    	if (!itemstack.hasTagCompound())
    	{
    		return Maps.newHashMap();
    	}
    	
        Map map = readMapFromString(itemstack.getTagCompound().getString("Contents"));
        return (Map<String, Float>) (map == null ? Maps.newHashMap() : map);
    }

    public static Map readMapFromString(String mapString)
    {
        mapString = mapString.replace("{", "").replace("}", "");
        String[] entries = mapString.split(", ");
        Map map = Maps.newHashMap();

        for (String entry : entries)
        {
            String[] keyValue = entry.split("=");

            if (keyValue.length == 2)
            {
                String key = keyValue[0];
                String value = keyValue[1];
                map.put(key, Float.valueOf(value));
            }
        }

        return map;
    }

    public static void setLiquidColor(ItemStack itemstack, int color)
    {
        refreshNBT(itemstack);
        itemstack.getTagCompound().setInteger("LiquidColor", color);
    }

    public static int getLiquidColor(ItemStack itemstack)
    {
        return itemstack.hasTagCompound() ? itemstack.getTagCompound().getInteger("LiquidColor") : 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IIconRegister)
    {
        itemIcon = par1IIconRegister.registerIcon(TransformersMod.modid + ":empty_fuel_canister");
        overlay = par1IIconRegister.registerIcon(TransformersMod.modid + ":fuel_canister_overlay");
    }
}
