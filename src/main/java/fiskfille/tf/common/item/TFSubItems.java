package fiskfille.tf.common.item;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;

public class TFSubItems
{
	public static ItemStack[] standard_engine;
	public static ItemStack[] jet_turbine;
	public static ItemStack[] jet_thruster;
	public static ItemStack[] small_thruster;
	public static ItemStack[] ahd2_jet_wing;
	public static ItemStack[] ahd2_jet_cockpit;
	public static ItemStack[] t50_jet_wing;
	public static ItemStack[] t50_jet_cockpit;
	public static ItemStack[] car_wheel;
	
	public static ItemStack[] tank_turret_base;
	public static ItemStack[] tank_barrel;
	public static ItemStack[] tank_turret;
	public static ItemStack[] tank_track;
	
	private static int ID_MAX;

	public static void register()
	{
		int i = 0;
		List<String> list = Lists.newArrayList();
		
		for (Field field : TFSubItems.class.getFields())
		{
			String s = field.getType().getName();

			if (s.equals(ItemStack[].class.getName()))
			{
				try
				{
					ItemStack[] aitemstack = new ItemStack[65];
					
					for (int j = 0; j < aitemstack.length; ++j)
					{
						aitemstack[j] = new ItemStack(TFItems.craftingMaterial, j, i);
					}
					
					field.set(null, aitemstack);
					list.add(field.getName());
					++i;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		try
		{
			Field field = ItemMetaBasic.class.getField("itemNames");
			String[] astring = list.toArray(new String[list.size()]);
			
			field.setAccessible(true);
			field.set(TFItems.craftingMaterial, astring);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		ID_MAX = i;
	}
	
	public static boolean matches(ItemStack itemstack, ItemStack[] item)
	{
		if (itemstack != null && item[1].getItem() == itemstack.getItem() && item[1].getItemDamage() == itemstack.getItemDamage())
		{
			return true;
		}
		
		return false;
	}
}
