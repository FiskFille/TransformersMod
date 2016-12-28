package fiskfille.tf.common.recipe;

import net.minecraft.item.ItemStack;

public class AssemblyTable
{
    public static AssemblyTableRecipe addRecipe(ItemStack result, Dyes dyes, Object... ingredients)
    {
        return AssemblyTableCraftingManager.getInstance().addRecipe(result, dyes.compile(), ingredients);
    }

    public static AssemblyTableRecipe addRecipe(ItemStack[] result, int amount, Dyes dyes, Object... ingredients)
    {
        return addRecipe(result[amount], dyes, ingredients);
    }

    public static AssemblyTableRecipe addRecipe(ItemStack[] result, Dyes dyes, Object... ingredients)
    {
        return addRecipe(result, 1, dyes, ingredients);
    }
}
