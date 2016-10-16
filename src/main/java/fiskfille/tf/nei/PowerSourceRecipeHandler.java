package fiskfille.tf.nei;

import static codechicken.nei.NEIClientUtils.translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
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
		return StatCollector.translateToLocal("recipe.powerSource");
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
			IFluidContainerItem container = (IFluidContainerItem)item;
			FluidStack stack = container.getFluid(result);

			if (!ItemFuelCanister.isEmpty(result) && stack.getFluid() == TFFluids.energon)
			{
				Map<String, Integer> ingredients = FluidEnergon.getContents(stack);

				for (CrystalPair crystal : crystals)
				{
					String id = crystal.energon.getEnergonType().getId();
					int mass = crystal.energon.getMass();

					if (ingredients.containsKey(id) && ingredients.get(id) >= mass)
					{
						result.stackSize = 1;
						CachedProcessorRecipe recipe = new CachedProcessorRecipe(new ItemStack(Items.fish), result);

						FluidStack stack1 = new FluidStack(TFFluids.energon, stack.amount - mass);
						FluidEnergon.setContents(stack, FluidEnergon.getContents(stack));
						FluidEnergon.setContents(stack1, FluidEnergon.getContents(stack));

						Map<String, Integer> map = FluidEnergon.getContents(stack1);

						if (map.get(id) - mass <= 0)
						{
							map.remove(id);
						}
						else
						{
							map.put(id, map.get(id) - mass);
						}

						FluidEnergon.setContents(stack1, map);
						int i = recipe.tank.fill(stack1, true);

						if (i > 0)
						{
							FluidEnergon.addContents(stack, Energon.createContentMap(mass, crystal.energon.getEnergonType()));
						}

						recipe.ingredient.item = crystal.stack;

						recipe.computeVisuals();
						arecipes.add(recipe);
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
			IEnergon ienergon = (IEnergon) (item instanceof ItemBlock ? Block.getBlockFromItem(item) : item);
			Energon energon = ienergon.getEnergonType();
			CachedProcessorRecipe recipe = new CachedProcessorRecipe(new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage()), new ItemStack(TFItems.fuelCanister));

			FluidStack stack = new FluidStack(TFFluids.energon, ienergon.getMass());
			FluidEnergon.setContents(stack, Energon.createContentMap(ienergon.getMass(), energon));

			((ItemFuelCanister)recipe.result.item.getItem()).fill(recipe.result.item, stack, true);

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
