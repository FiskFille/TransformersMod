package fiskfille.tf.nei;

import static codechicken.lib.gui.GuiDraw.changeTexture;
import static codechicken.lib.gui.GuiDraw.drawTexturedModalRect;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.lwjgl.opengl.GL11;

import codechicken.core.ReflectionManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiAssemblyTable;
import fiskfille.tf.common.recipe.AssemblyTableCraftingManager;
import fiskfille.tf.common.recipe.AssemblyTableRecipe;

public class AssemblyTableRecipeHandler extends TemplateRecipeHandler
{
    public class CachedAssemblyRecipe extends CachedRecipe
    {
        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;

        public CachedAssemblyRecipe(int width, int height, Object[] items, ItemStack out)
        {
            result = new PositionedStack(out, 131, 14);
            ingredients = new ArrayList<PositionedStack>();
            setIngredients(width, height, items);
        }

        public CachedAssemblyRecipe(AssemblyTableRecipe recipe)
        {
            this(recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, recipe.getRecipeOutput());
        }

        public void setIngredients(int width, int height, Object[] items)
        {
            this.addSlotToContainer(0, 136, 56, items);
            this.addSlotToContainer(4, 136, 74, items);
            this.addSlotToContainer(20, 136, 92, items);
            this.addSlotToContainer(1, 42, 25, items);
            this.addSlotToContainer(2, 60, 25, items);
            this.addSlotToContainer(3, 78, 25, items);
            this.addSlotToContainer(21, 42, 105, items);
            this.addSlotToContainer(22, 60, 105, items);
            this.addSlotToContainer(23, 78, 105, items);
            this.addSlotToContainer(5, 20, 47, items);
            this.addSlotToContainer(10, 20, 65, items);
            this.addSlotToContainer(15, 20, 83, items);
            this.addSlotToContainer(9, 100, 47, items);
            this.addSlotToContainer(14, 100, 65, items);
            this.addSlotToContainer(19, 100, 83, items);

            for (int i = 0; i < 3; ++i)
            {
                for (int j = 0; j < 3; ++j)
                {
                    this.addSlotToContainer(6 + i + j * 2 + j * 3, 42 + i * 18, 47 + j * 18, items);
                }
            }
        }

        private void addSlotToContainer(int id, int x, int y, Object[] items)
        {
            if (items[id] != null)
            {
                PositionedStack stack = new PositionedStack(items[id], x - 5, y - 11, false);
                ingredients.add(stack);
            }
        }

        @Override
        public List<PositionedStack> getIngredients()
        {
            return getCycledIngredients(cycleticks / 20, ingredients);
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
        transferRects.add(new RecipeTransferRect(new Rectangle(97, 14, 22, 15), "assembly_table"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiAssemblyTable.class;
    }

    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("recipe.assembly_table");
    }

    @Override
    public int recipiesPerPage()
    {
        return 1;
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals("assembly_table") && getClass() == AssemblyTableRecipeHandler.class)
        {
            for (IRecipe irecipe : (List<IRecipe>) AssemblyTableCraftingManager.getInstance().getRecipeList())
            {
                CachedAssemblyRecipe recipe = null;

                if (irecipe instanceof AssemblyTableRecipe)
                {
                    recipe = new CachedAssemblyRecipe((AssemblyTableRecipe) irecipe);
                }
                else if (irecipe instanceof ShapedOreRecipe)
                {
                    recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);
                }

                if (recipe == null)
                {
                    continue;
                }

                recipe.computeVisuals();
                arecipes.add(recipe);
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
        for (IRecipe irecipe : (List<IRecipe>) AssemblyTableCraftingManager.getInstance().getRecipeList())
        {
            if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getRecipeOutput(), result))
            {
                CachedAssemblyRecipe recipe = null;

                if (irecipe instanceof AssemblyTableRecipe)
                {
                    recipe = new CachedAssemblyRecipe((AssemblyTableRecipe) irecipe);
                }
                else if (irecipe instanceof ShapedOreRecipe)
                {
                    recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);
                }

                if (recipe == null)
                {
                    continue;
                }

                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        for (IRecipe irecipe : (List<IRecipe>) AssemblyTableCraftingManager.getInstance().getRecipeList())
        {
            CachedAssemblyRecipe recipe = null;

            if (irecipe instanceof AssemblyTableRecipe)
            {
                recipe = new CachedAssemblyRecipe((AssemblyTableRecipe) irecipe);
            }
            else if (irecipe instanceof ShapedOreRecipe)
            {
                recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);
            }

            if (recipe == null || !recipe.contains(recipe.ingredients, ingredient.getItem()))
            {
                continue;
            }

            recipe.computeVisuals();

            if (recipe.contains(recipe.ingredients, ingredient))
            {
                recipe.setIngredientPermutation(recipe.ingredients, ingredient);
                arecipes.add(recipe);
            }
        }
    }

    public CachedAssemblyRecipe forgeShapedRecipe(ShapedOreRecipe recipe)
    {
        try
        {
            int width = ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 4);
            int height = ReflectionManager.getField(ShapedOreRecipe.class, Integer.class, recipe, 5);

            Object[] items = recipe.getInput();

            for (Object item : items)
            {
                if (item instanceof List && ((List<?>) item).isEmpty())//ore handler, no ores
                {
                    return null;
                }
            }

            return new CachedAssemblyRecipe(width, height, items, recipe.getRecipeOutput());
        }
        catch (Exception e)
        {
            NEIClientConfig.logger.error("Error loading recipe: ", e);
            return null;
        }
    }

    @Override
    public String getGuiTexture()
    {
        return TransformersMod.modid + ":textures/gui/container/assembly_table.png";
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "assembly_table";
    }

    @Override
    public void drawBackground(int recipe)
    {
        GL11.glColor4f(1, 1, 1, 1);
        changeTexture(getGuiTexture());
        drawTexturedModalRect(0, 0, 5, 11, 166, 122);
    }
}
