package fiskfille.tf.nei;

import static codechicken.nei.NEIClientUtils.translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import codechicken.nei.ItemList;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.google.common.collect.Lists;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;

public class PowerSourceRecipeHandler extends EnergonProcessorRecipeHandler
{
    public class CachedPowerSourceRecipe extends CachedRecipe
    {
        public PowerSourcePair powerSource;

        public CachedPowerSourceRecipe(PowerSourcePair powerSource)
        {
            this.powerSource = powerSource;
        }

        @Override
        public PositionedStack getIngredient()
        {
            return processorRecipes.get(cycleticks / 48 % processorRecipes.size()).ingredient;
        }

        @Override
        public PositionedStack getResult()
        {
            return processorRecipes.get(cycleticks / 48 % processorRecipes.size()).result;
        }

        @Override
        public PositionedStack getOtherStack()
        {
            return powerSource.stack;
        }
    }

    private ArrayList<CachedProcessorRecipe> processorRecipes;

    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("recipe.power_source");
    }

    @Override
    public TemplateRecipeHandler newInstance()
    {
        if (processorRecipes == null || processorRecipes.isEmpty())
        {
            findProcessorRecipes();
        }

        return super.newInstance();
    }

    private void findProcessorRecipes()
    {
        processorRecipes = Lists.newArrayList();

        for (ItemStack item : ItemList.items)
        {
            loadProcessorCraftingRecipes(item);
            loadProcessorUsageRecipes(item);
        }
    }

    public void loadProcessorCraftingRecipes(ItemStack result)
    {
        Item item = result.getItem();

        if (item instanceof IFluidContainerItem)
        {
            IFluidContainerItem container = (IFluidContainerItem) item;
            FluidStack stack = container.getFluid(result);

            int amount = stack != null ? stack.amount : 0;

            if (!ItemFuelCanister.isEmpty(result) && stack.getFluid() == TFFluids.energon)
            {
                Map<String, Float> ratios = FluidEnergon.getRatios(stack);

                for (CrystalPair crystal : crystals)
                {
                    String id = crystal.energon.getEnergonType().getId();
                    int mass = crystal.energon.getMass();

                    if (ratios.get(id) > 0 && amount >= mass)
                    {
                        result.stackSize = 1;
                        CachedProcessorRecipe recipe = new CachedProcessorRecipe(crystal.stack, result);
                        FluidStack stack1 = new FluidStack(TFFluids.energon, 0);

                        for (Map.Entry<String, Float> e : ratios.entrySet())
                        {
                            Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                            int amount1 = Math.round(e.getValue() * amount);

                            if (e.getKey().equals(id))
                            {
                                amount1 -= mass;
                            }

                            if (amount1 > 0)
                            {
                                stack1.amount += amount1;
                                FluidEnergon.merge(stack1, FluidEnergon.create(energon, 0), amount1);
                            }
                        }

                        recipe.tank.fill(stack1, true);
                        recipe.computeVisuals();
                        processorRecipes.add(recipe);
                    }
                }
            }
        }
    }

    public void loadProcessorUsageRecipes(ItemStack ingredient)
    {
        Item item = ingredient.getItem();

        if (item instanceof IEnergon || item instanceof ItemBlock && Block.getBlockFromItem(item) instanceof IEnergon)
        {
            CachedProcessorRecipe recipe = new CachedProcessorRecipe(new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage()), new ItemStack(TFItems.fuelCanister));
            FluidStack stack = FluidEnergon.create(ingredient);

            ((ItemFuelCanister) recipe.result.item.getItem()).fill(recipe.result.item, stack, true);

            recipe.computeVisuals();
            processorRecipes.add(recipe);
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        if (processorRecipes == null || processorRecipes.isEmpty())
        {
            findProcessorRecipes();
        }

        for (PowerSourcePair powerSource : powerSources)
        {
            if (powerSource.stack.contains(ingredient))
            {
                arecipes.add(new CachedPowerSourceRecipe(powerSource));
            }
        }
    }

    @Override
    public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe)
    {
        CachedPowerSourceRecipe crecipe = (CachedPowerSourceRecipe) arecipes.get(recipe);
        PowerSourcePair powerSource = crecipe.powerSource;
        float burnTime = powerSource.burnTime / 200F;

        if (gui.isMouseOver(powerSource.stack, recipe) && burnTime < 1)
        {
            burnTime = 1F / burnTime;
            String s_time = Float.toString(burnTime);

            if (burnTime == Math.round(burnTime))
            {
                s_time = Integer.toString((int) burnTime);
            }

            currenttip.add(translate("recipe.fuel.required", s_time));
        }
        else if ((gui.isMouseOver(crecipe.getResult(), recipe) || gui.isMouseOver(crecipe.getIngredient(), recipe)) && burnTime > 1)
        {
            String s_time = Float.toString(burnTime);

            if (burnTime == Math.round(burnTime))
            {
                s_time = Integer.toString((int) burnTime);
            }

            currenttip.add(translate("recipe.fuel." + (gui.isMouseOver(crecipe.getResult(), recipe) ? "produced" : "processed"), s_time));
        }

        return currenttip;
    }

    @Override
    public List<CachedProcessorRecipe> getProcessorRecipes(EnergonProcessorRecipeHandler handler)
    {
        return processorRecipes;
    }
}
