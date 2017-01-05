package fiskfille.tf.common.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
        addRecipe(new AlloyIngredients(TFItems.transformiumFragment, "ingotIron", "ingotIron"), new ItemStack(TFItems.transformiumAlloy, 2), 400, 1.0F);
        addRecipe(new AlloyIngredients(TFBlocks.transformiumStone, Blocks.clay, "gemQuartz"), new ItemStack(TFItems.transformiumFragment), 600, 0.35F);

        addRecipe(new AlloyIngredients("stone", Items.ender_pearl), new ItemStack(Blocks.end_stone), 0.2F);
        addRecipe(new AlloyIngredients("blockGlass"), new ItemStack(Blocks.glass), 0);
        addRecipe(new AlloyIngredients("paneGlass"), new ItemStack(Blocks.glass_pane), 0);
        addRecipe(new AlloyIngredients("blockClayHardened"), new ItemStack(Blocks.hardened_clay), 0);
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
        ItemStack[] itemstacks = ingredients.getIngredients();
        return getSmeltingResult(itemstacks[0], itemstacks[1], itemstacks[2]);
    }

    public ItemStack getSmeltingResult(ItemStack input1, ItemStack input2, ItemStack input3)
    {
        Iterator<Entry<AlloyIngredients, ItemStack>> iterator = smeltingList.entrySet().iterator();
        Entry<AlloyIngredients, ItemStack> entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = iterator.next();
        }
        while (!entry.getKey().matches(input1, input2, input3));

        return entry.getValue();
    }

    public static boolean matches(ItemStack itemstack, String oreDict)
    {
        List<ItemStack> aliases = OreDictionary.getOres(oreDict);

        for (int i = 0; i < aliases.size(); ++i)
        {
            ItemStack itemstack1 = aliases.get(i);

            if (matches(itemstack, itemstack1))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean matches(ItemStack itemstack, ItemStack itemstack1)
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
        private final Map<Integer, List<String>> oreDictNames = Maps.newHashMap();
        private final ItemStack[] ingredients;

        public AlloyIngredients(Object... objects)
        {
            LinkedList<ItemStack> list = Lists.newLinkedList();

            for (int i = 0; i < objects.length; ++i)
            {
                Object obj = objects[i];

                if (obj instanceof List)
                {
                    List list1 = (List) obj;

                    for (int j = 0; j < list1.size(); ++j)
                    {
                        List<ItemStack> list2 = OreDictionary.getOres((String) list1.get(j));

                        if (!list2.isEmpty())
                        {
                            list.add(list2.get(0));
                            break;
                        }
                    }

                    oreDictNames.put(i, list1);
                }
                else if (obj instanceof String)
                {
                    List<ItemStack> list1 = OreDictionary.getOres((String) obj);

                    if (!list1.isEmpty())
                    {
                        list.add(list1.get(0));
                    }

                    oreDictNames.put(i, Arrays.asList((String) obj));
                }
                else
                {
                    ItemStack itemstack = getItemStack(obj);

                    if (itemstack != null)
                    {
                        list.add(itemstack);
                    }
                }
            }

            ingredients = list.toArray(new ItemStack[3]);
        }

        public boolean matches(ItemStack input1, ItemStack input2, ItemStack input3)
        {
            ItemStack[] ingredients = new ItemStack[] {input1, input2, input3};

            for (int i = 0; i < getIngredients().length; ++i)
            {
                ItemStack itemstack = getIngredients()[i];

                if (itemstack == null && ingredients[i] == null)
                {
                    continue;
                }
                else if (itemstack == null || ingredients[i] == null)
                {
                    return false;
                }

                List<String> list = getOreDictNames(i);
                boolean oreDictMatch = false;

                for (int j = 0; j < list.size(); ++j)
                {
                    String oreDict = list.get(j);

                    if (AlloyRecipes.matches(ingredients[i], oreDict))
                    {
                        oreDictMatch = true;
                        break;
                    }
                }

                if (!oreDictMatch)
                {
                    if (!AlloyRecipes.matches(itemstack, ingredients[i]))
                    {
                        return false;
                    }
                }
            }

            return true;
        }

        private ItemStack getItemStack(Object obj)
        {
            if (obj instanceof ItemStack)
            {
                return (ItemStack) obj;
            }
            else if (obj instanceof Item)
            {
                return new ItemStack((Item) obj, 1, OreDictionary.WILDCARD_VALUE);
            }
            else if (obj instanceof Block)
            {
                return new ItemStack((Block) obj, 1, OreDictionary.WILDCARD_VALUE);
            }

            return null;
        }

        public ItemStack[] getIngredients()
        {
            return ingredients;
        }

        public List<String> getOreDictNames(int index)
        {
            return oreDictNames.get(index) != null ? oreDictNames.get(index) : new ArrayList<String>();
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

                        if (!AlloyRecipes.matches(itemstack, itemstack1))
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
