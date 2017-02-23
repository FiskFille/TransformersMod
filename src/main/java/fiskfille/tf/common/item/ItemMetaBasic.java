package fiskfille.tf.common.item;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

public class ItemMetaBasic extends Item
{
    public static Map<String, Integer> subItems = Maps.newHashMap();
    public Map<String, IIcon> mappedIcons = Maps.newHashMap();
    
    public static String[] iconNames;

    public ItemMetaBasic()
    {
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        List<Double> list1 = Lists.newArrayList();
        
        for (Map.Entry<String, Integer> e : subItems.entrySet())
        {
            list1.add(e.getValue().doubleValue());
        }
        
        Collections.sort(list1);
        
        for (int i = 0; i < list1.size(); ++i)
        {
            list.add(new ItemStack(this, 1, list1.get(i).intValue()));
        }
    }
    
    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int slot, boolean isHeld)
    {
        if (StringUtils.isNullOrEmpty(getNameFromId(itemstack.getItemDamage())))
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entity;
                player.inventory.mainInventory[slot] = null;
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return StatCollector.translateToLocal(String.format("item.%s.name", getNameFromId(stack.getItemDamage())));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        return mappedIcons.get(getNameFromId(damage));
    }
    
    public String getNameFromId(int id)
    {
        if (subItems.containsValue(id))
        {
            for (Map.Entry<String, Integer> e : subItems.entrySet())
            {
                if (e.getValue() == id)
                {
                    return e.getKey();
                }
            }
        }
        
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        mappedIcons.clear();

        for (int i = 0; i < iconNames.length; ++i)
        {
            mappedIcons.put(iconNames[i], iconRegister.registerIcon(TransformersMod.modid + ":" + iconNames[i]));
        }
    }
}
