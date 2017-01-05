package fiskfille.tf.nei;

import static codechicken.lib.gui.GuiDraw.changeTexture;
import static codechicken.lib.gui.GuiDraw.drawTexturedModalRect;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiAlloyCrucible;
import fiskfille.tf.common.recipe.AlloyRecipes;
import fiskfille.tf.common.recipe.AlloyRecipes.AlloyIngredients;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;
import fiskfille.tf.helper.TFFormatHelper;

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
                LinkedList<ItemStack> ingredients = Lists.newLinkedList();
                List<String> list = alloy.getOreDictNames(i);

                for (int j = 0; j < list.size(); ++j)
                {
                    ingredients.addAll(OreDictionary.getOres(list.get(j)));
                }

                Object items = ingredients;

                if (ingredients.isEmpty())
                {
                    items = alloy.getIngredients()[i];
                }

                addSlotToContainer(73, 19 + i * 18, items);
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

    public static TileEntityAlloyCrucible tileentity;

    @Override
    public TemplateRecipeHandler newInstance()
    {
        if (tileentity == null)
        {
            tileentity = new TileEntityAlloyCrucible();
            tileentity.alloyResult = true;
        }

        return super.newInstance();
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
                AlloyPair recipe = new AlloyPair(e.getKey(), e.getValue());
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
        Map<AlloyIngredients, ItemStack> recipes = AlloyRecipes.getInstance().getSmeltingList();

        for (Entry<AlloyIngredients, ItemStack> e : recipes.entrySet())
        {
            if (NEIServerUtils.areStacksSameTypeCrafting(e.getValue(), result))
            {
                AlloyPair recipe = new AlloyPair(e.getKey(), e.getValue());
                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        Map<AlloyIngredients, ItemStack> recipes = AlloyRecipes.getInstance().getSmeltingList();

        for (Entry<AlloyIngredients, ItemStack> e : recipes.entrySet())
        {
            AlloyPair recipe = new AlloyPair(e.getKey(), e.getValue());

            for (int i = 0; i < recipe.getIngredients().size(); ++i)
            {
                if (recipe.getIngredients().get(i).contains(ingredient))
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
    public List<String> handleTooltip(GuiRecipe gui, List<String> currenttip, int recipe)
    {
        currenttip = super.handleTooltip(gui, currenttip, recipe);

        int guiLeft = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, gui, 4);
        int guiTop = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, gui, 5);

        Point pos = GuiDraw.getMousePosition();
        Point offset = gui.getRecipePosition(recipe);
        Point relMouse = new Point(pos.x - guiLeft - offset.x, pos.y - guiTop - offset.y);

        if (new Rectangle(44, 8, 16, 52).contains(relMouse))
        {
            if (currenttip.isEmpty())
            {
                tileentity.smeltingResult = getResultStack(recipe).item;
                currenttip.add(StatCollector.translateToLocalFormatted("gui.emb.amount", TFFormatHelper.formatNumber(tileentity.getSmeltTimeMax() * tileentity.getConsumptionRate())));
            }
        }

        return currenttip;
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "alloy_crucible";
    }
}
