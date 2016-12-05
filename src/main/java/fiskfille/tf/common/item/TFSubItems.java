package fiskfille.tf.common.item;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;

public class TFSubItems
{
	public static ItemStack[] optic_sensor;
	public static ItemStack[] protoform_head;
	public static ItemStack[] transformium_alloy_rod;
	
	public static ItemStack[] standard_engine;
	public static ItemStack[] jet_turbine;
	public static ItemStack[] jet_thruster;
	public static ItemStack[] small_thruster;
	public static ItemStack[] t50_jet_wing;
	public static ItemStack[] t50_jet_cockpit;
	public static ItemStack[] car_wheel;
	
	public static ItemStack[] skystrikes_wing;
    public static ItemStack[] skystrikes_shoulder_pad;
    public static ItemStack[] skystrikes_gauntlet;
    public static ItemStack[] skystrike_torso_base;
    public static ItemStack[] skystrikes_greave;

	public static ItemStack[] tank_turret_base;
	public static ItemStack[] tank_barrel;
	public static ItemStack[] tank_turret;
	public static ItemStack[] tank_track;
	public static ItemStack[] purges_pauldron;
	public static ItemStack[] purges_gauntlet;
	public static ItemStack[] purge_torso_base;
	public static ItemStack[] purges_greave;
	

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
