package fiskfille.tf.common.item;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

import fiskfille.tf.common.achievement.TFAchievements;
import fiskfille.tf.common.data.TFWorldData;
import fiskfille.tf.common.recipe.TFRecipes;

public class TFSubItems
{
    public static ItemStack[] iron_pole;
    public static ItemStack[] iron_support;
    public static ItemStack[] canister_lid;
    public static ItemStack[] lcd_panel;
    public static ItemStack[] crude_alloy_wire;
    public static ItemStack[] refined_alloy_wire;
    public static ItemStack[] energon_coil;
    public static ItemStack[] focusing_crystal;
    public static ItemStack[] transmission_orb;
    public static ItemStack[] energy_converter;
    public static ItemStack[] superconductor;
    public static ItemStack[] singularity;
    public static ItemStack[] basic_circuit;
    public static ItemStack[] improved_circuit;
    public static ItemStack[] advanced_circuit;
    
    public static ItemStack[] optic_sensor;
    public static ItemStack[] protoform_head;
    public static ItemStack[] transformium_alloy_rod;
    public static ItemStack[] standard_engine;
    public static ItemStack[] jet_thruster;
    public static ItemStack[] small_thruster;
    public static ItemStack[] wheel;

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

    public static ItemStack[] vurps_shoulder_pad;
    public static ItemStack[] vurps_gauntlet;
    public static ItemStack[] vurp_torso_base;
    public static ItemStack[] vurps_greave;
    public static ItemStack[] vurps_facemask;

    public static ItemStack[] subwoofers_shoulder_pad;
    public static ItemStack[] subwoofers_gauntlet;
    public static ItemStack[] subwoofer_torso_base;
    public static ItemStack[] subwoofers_greave;
    public static ItemStack[] subwoofers_visor;

    public static ItemStack[] cloudtraps_wing;
    public static ItemStack[] cloudtraps_pauldron;
    public static ItemStack[] cloudtraps_gauntlet;
    public static ItemStack[] cloudtrap_torso_base;
    public static ItemStack[] cloudtraps_greave;
    public static ItemStack[] cloudtraps_visor;
    
    public static void register()
    {
        List<String> names = Lists.newArrayList();

        for (Field field : TFSubItems.class.getFields())
        {
            String s = field.getType().getName();

            if (s.equals(ItemStack[].class.getName()))
            {
                names.add(field.getName());
            }
        }

        ItemMetaBasic.iconNames = names.toArray(new String[names.size()]);
    }

    public static void load(World world)
    {
        TFWorldData data = TFWorldData.get(world);

        for (Field field : TFSubItems.class.getFields())
        {
            String s = field.getType().getName();

            if (s.equals(ItemStack[].class.getName()))
            {
                try
                {
                    ItemStack[] itemstacks = new ItemStack[65];
                    String name = field.getName();
                    int id = data.getNextAvailableId();
                    
                    if (data.subItems.containsKey(name))
                    {
                        id = data.subItems.get(name);
                    }
                    else
                    {
                        data.subItems.put(name, id);
                    }

                    for (int amount = 0; amount < itemstacks.length; ++amount)
                    {
                        itemstacks[amount] = new ItemStack(TFItems.craftingMaterial, amount, id);
                    }

                    field.set(null, itemstacks);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        ItemMetaBasic.subItems = data.subItems;
        TFRecipes.register();
        TFAchievements.register();
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
