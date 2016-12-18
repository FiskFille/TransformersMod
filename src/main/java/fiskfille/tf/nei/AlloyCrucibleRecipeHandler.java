package fiskfille.tf.nei;

import static codechicken.lib.gui.GuiDraw.changeTexture;
import static codechicken.lib.gui.GuiDraw.drawTexturedModalRect;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiAlloyCrucible;
import fiskfille.tf.common.recipe.AlloyRecipes;
import fiskfille.tf.common.recipe.AlloyRecipes.AlloyIngredients;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;

public class AlloyCrucibleRecipeHandler extends TemplateRecipeHandler
{
    public class AlloyPair extends CachedRecipe
    {
        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;

        public AlloyPair(AlloyIngredients alloy, ItemStack out)
        {
            result = new PositionedStack(out, 107 - 5, 28 - 11);
            ingredients = new ArrayList<PositionedStack>();
            
            for (int i = 0; i < alloy.getIngredients().length; ++i)
            {
                addSlotToContainer(73, 19 + i * 18, alloy.getIngredients()[i]);
            }
        }

        private void addSlotToContainer(int x, int y, Object item)
        {
            if (item != null)
            {
                PositionedStack stack = new PositionedStack(item, x - 5, y - 11, false);
                ingredients.add(stack);
            }
        }

        @Override
        public List<PositionedStack> getIngredients()
        {
            return getCycledIngredients(cycleticks / 48, ingredients);
        }

        @Override
        public PositionedStack getResult()
        {
            return result;
        }

        public void computeVisuals()
        {
            for (PositionedStack p : ingredients)
            {
                p.generatePermutations();
            }
        }
    }

    @Override
    public void loadTransferRects()
    {
        transferRects.add(new RecipeTransferRect(new Rectangle(102, 38, 16, 16), "alloy_crucible"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiAlloyCrucible.class;
    }

    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("recipe.alloy_crucible");
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals("alloy_crucible") && getClass() == AlloyCrucibleRecipeHandler.class)
        {
            Map<AlloyIngredients, ItemStack> recipes = AlloyRecipes.getInstance().getSmeltingList();

            for (Entry<AlloyIngredients, ItemStack> e : recipes.entrySet())
            {
                arecipes.add(new AlloyPair(e.getKey(), e.getValue()));
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        Map<AlloyIngredients, ItemStack> recipes = AlloyRecipes.getInstance().getSmeltingList();

        for (Entry<AlloyIngredients, ItemStack> e : recipes.entrySet())
        {
            if (NEIServerUtils.areStacksSameType(e.getValue(), result))
            {
                arecipes.add(new AlloyPair(e.getKey(), e.getValue()));
            }
        }
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients)
    {
//        if (inputId.equals("fuel") && getClass() == AlloyCrucibleRecipeHandler.class)
//        {
//            loadCraftingRecipes("smelting");
//        }
//        else
        {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        Map<AlloyIngredients, ItemStack> recipes = AlloyRecipes.getInstance().getSmeltingList();

        for (Entry<AlloyIngredients, ItemStack> e : recipes.entrySet())
        {
            AlloyPair recipe = new AlloyPair(e.getKey(), e.getValue());
            
            for (int i = 0; i < e.getKey().getIngredients().length; ++i)
            {
                ItemStack itemstack = e.getKey().getIngredients()[i];
                
                if (NEIServerUtils.areStacksSameTypeCrafting(itemstack, ingredient))
                {
                    recipe.computeVisuals();
                    arecipes.add(recipe);
                    break;
                }
            }
        }
    }

    @Override
    public String getGuiTexture()
    {
        return TransformersMod.modid + ":textures/gui/container/alloy_crucible.png";
    }

    @Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(102, 40, 192, 0, 14, 14, 48, 3);
        drawProgressBar(44, 8, 176, 0, 16, 52, 52 * 48, 7);
    }
    
    @Override
    public void drawBackground(int recipe)
    {
        GL11.glColor4f(1, 1, 1, 1);
        changeTexture(getGuiTexture());
        drawTexturedModalRect(0, 0, 5, 11, 166, 65);
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "alloy_crucible";
    }
}
