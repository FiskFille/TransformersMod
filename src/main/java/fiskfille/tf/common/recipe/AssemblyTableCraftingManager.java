package fiskfille.tf.common.recipe;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssemblyTableCraftingManager
{
    private static final AssemblyTableCraftingManager instance = new AssemblyTableCraftingManager();
    private List recipes = new ArrayList();

    public static AssemblyTableCraftingManager getInstance()
    {
        return instance;
    }

    public AssemblyTableRecipe addRecipe(ItemStack result, Object... ingredients)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (ingredients[i] instanceof String[])
        {
            String[] astring = (String[]) ((String[]) ingredients[i++]);

            for (String s1 : astring)
            {
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (ingredients[i] instanceof String)
            {
                String s2 = (String) ingredients[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i < ingredients.length; i += 2)
        {
            Character character = (Character) ingredients[i];
            ItemStack itemstack1 = null;

            if (ingredients[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item) ingredients[i + 1]);
            }
            else if (ingredients[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block) ingredients[i + 1], 1, 32767);
            }
            else if (ingredients[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack) ingredients[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(c0))
            {
                aitemstack[i1] = ((ItemStack) hashmap.get(c0)).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        AssemblyTableRecipe assemblytablerecipes = new AssemblyTableRecipe(j, k, aitemstack, result);
        this.recipes.add(assemblytablerecipes);
        return assemblytablerecipes;
    }

    public ItemStack findMatchingRecipe(InventoryCrafting inventoryCrafting, World world)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < inventoryCrafting.getSizeInventory(); ++j)
        {
            ItemStack itemstack2 = inventoryCrafting.getStackInSlot(j);

            if (itemstack2 != null)
            {
                if (i == 0)
                {
                    itemstack = itemstack2;
                }

                if (i == 1)
                {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && itemstack.getItem().isRepairable())
        {
            Item item = itemstack.getItem();
            int j1 = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
            int k = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
            int l = j1 + k + item.getMaxDamage() * 5 / 100;
            int i1 = item.getMaxDamage() - l;

            if (i1 < 0)
            {
                i1 = 0;
            }

            return new ItemStack(itemstack.getItem(), 1, i1);
        }
        else
        {
            for (j = 0; j < this.recipes.size(); ++j)
            {
                IRecipe irecipe = (IRecipe) this.recipes.get(j);

                if (irecipe.matches(inventoryCrafting, world))
                {
                    return irecipe.getCraftingResult(inventoryCrafting);
                }
            }

            return null;
        }
    }

    public Map<Integer, Integer> getDecrMap(ItemStack result)
    {
        Map<Integer, Integer> map = Maps.newHashMap();

        for (IRecipe recipe : (List<IRecipe>) getRecipeList())
        {
            ItemStack itemstack = recipe.getRecipeOutput();

            if (itemstack != null && result != null && itemstack.getItem() == result.getItem() && itemstack.getItemDamage() == result.getItemDamage())
            {
                AssemblyTableRecipe recipe1 = (AssemblyTableRecipe) recipe;

                for (int i = 0; i < recipe1.recipeItems.length; ++i)
                {
                    ItemStack itemstack1 = recipe1.recipeItems[i];

                    if (itemstack1 != null)
                    {
                        map.put(i, itemstack1.stackSize);
                    }
                }
            }
        }

        return map;
    }

    public List getRecipeList()
    {
        return this.recipes;
    }
}
