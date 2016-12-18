package fiskfille.tf.common.recipe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;

public class AlloyRecipes
{
    private static final AlloyRecipes instance = new AlloyRecipes();
    
    private Map<AlloyIngredients, ItemStack> smeltingList = Maps.newHashMap();
    private Map<ItemStack, Integer> durationList = Maps.newHashMap();
    private Map<ItemStack, Float> experienceList = Maps.newHashMap();

    public static AlloyRecipes getInstance()
    {
        return instance;
    }

    private AlloyRecipes()
    {
        addRecipe(new AlloyIngredients(TFItems.transformiumFragment, Items.iron_ingot, Items.iron_ingot), new ItemStack(TFItems.transformiumAlloy, 2), 400, 1.0F);
        addRecipe(new AlloyIngredients(TFBlocks.transformiumStone, Blocks.clay, Items.quartz), new ItemStack(TFItems.transformiumFragment), 600, 0.35F);
        
        addRecipe(new AlloyIngredients(Blocks.stone, Items.ender_pearl), new ItemStack(Blocks.end_stone), 0.2F);
        addRecipe(new AlloyIngredients(Blocks.stained_glass), new ItemStack(Blocks.glass), 0);
        addRecipe(new AlloyIngredients(Blocks.stained_glass_pane), new ItemStack(Blocks.glass_pane), 0);
        addRecipe(new AlloyIngredients(Blocks.stained_hardened_clay), new ItemStack(Blocks.hardened_clay), 0);
    }
    
    public void addRecipe(AlloyIngredients alloy, ItemStack result, float xp)
    {
        addRecipe(alloy, result, 200, xp);
    }

    public void addRecipe(AlloyIngredients alloy, ItemStack result, int duration, float xp)
    {
        if (alloy == null || alloy.getIngredients()[0] == null)
        {
            FMLLog.warning("[TransformersMod] Mod '%s' attempted to register unknown or empty AlloyIngredients for item %s!", Loader.instance().activeModContainer().getModId(), Item.itemRegistry.getNameForObject(result.getItem()));
            return;
        }
        
        smeltingList.put(alloy, result);
        durationList.put(result, duration);
        experienceList.put(result, Float.valueOf(xp));
    }

    public ItemStack getSmeltingResult(AlloyIngredients ingredients)
    {
        Iterator iterator = smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry) iterator.next();
        }
        while (!ingredients.equals(entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private static boolean matches(ItemStack itemstack, ItemStack itemstack1)
    {
        return itemstack1.getItem() == itemstack.getItem() && (itemstack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || itemstack1.getItemDamage() == itemstack.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return smeltingList;
    }
    
    public int getSmeltTime(ItemStack itemstack)
    {
        Iterator iterator = durationList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 200;
            }

            entry = (Entry) iterator.next();
        }
        while (!matches(itemstack, (ItemStack) entry.getKey()));

        return (Integer) entry.getValue();
    }

    public float getXpYield(ItemStack itemstack)
    {
        float xp = itemstack.getItem().getSmeltingExperience(itemstack);
        
        if (xp != -1)
        {
            return xp;
        }

        Iterator iterator = experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry) iterator.next();
        }
        while (!matches(itemstack, (ItemStack) entry.getKey()));

        return (Float) entry.getValue();
    }
    
    public static class AlloyIngredients
    {
        private final ItemStack[] ingredients;
        
        public AlloyIngredients(Object... objects)
        {
            LinkedList<ItemStack> list = Lists.newLinkedList();
            
            for (int i = 0; i < objects.length; ++i)
            {
                Object obj = objects[i];
                
                if (obj instanceof ItemStack)
                {
                    list.add((ItemStack) obj);
                }
                else if (obj instanceof Item)
                {
                    list.add(new ItemStack((Item) obj, 1, OreDictionary.WILDCARD_VALUE));
                }
                else if (obj instanceof Block)
                {
                    list.add(new ItemStack(Item.getItemFromBlock((Block) obj), 1, OreDictionary.WILDCARD_VALUE));
                }
            }
            
            ingredients = list.toArray(new ItemStack[3]);
        }
        
        public ItemStack[] getIngredients()
        {
            return ingredients;
        }
        
        @Override
        public String toString()
        {
            return String.format("Alloy{%s}", Arrays.asList(ingredients));
        }
        
        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof AlloyIngredients)
            {
                AlloyIngredients alloy = (AlloyIngredients) obj;
                
                if (getIngredients().length == alloy.getIngredients().length)
                {
                    for (int i = 0; i < getIngredients().length; ++i)
                    {
                        ItemStack itemstack = getIngredients()[i];
                        ItemStack itemstack1 = alloy.getIngredients()[i];
                        
                        if (itemstack == null && itemstack1 == null)
                        {
                            continue;
                        }
                        else if (itemstack == null || itemstack1 == null)
                        {
                            return false;
                        }
                        
                        if (!matches(itemstack, itemstack1))
                        {
                            return false;
                        }
                    }
                    
                    return true;
                }
            }
            
            return false;
        }
    }
}
