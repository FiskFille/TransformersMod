package fiskfille.tf.nei;

import static codechicken.nei.NEIClientUtils.translate;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.StatCollector;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.ItemList;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerDrawHandler;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.IRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.FurnaceRecipeHandler.FuelPair;
import codechicken.nei.recipe.FurnaceRecipeHandler.SmeltingPair;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.nei.EnergonProcessorRecipeHandler.CachedProcessorRecipe;
import fiskfille.tf.nei.EnergonProcessorRecipeHandler.CrystalPair;

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
		
		if (item == TFItems.filledFuelCanister)
		{
			Map<String, Integer> ingredients = ItemFuelCanister.getContents(result);
			
			for (CrystalPair crystal: crystals)
			{
				String id = crystal.energon.getEnergonType().getId();
				int mass = crystal.energon.getMass();
				
				if (ingredients.containsKey(id) && ingredients.get(id) >= mass)
				{
					result.stackSize = 1;
					CachedProcessorRecipe recipe = new CachedProcessorRecipe(new ItemStack(Items.fish), result);
					recipe.energonContentMap.putAll(ingredients);
					recipe.energonContentMap.put(id, recipe.energonContentMap.get(id) - mass);
					
					if (recipe.energonContentMap.get(id) <= 0)
					{
						recipe.energonContentMap.remove(id);
					}
					
					for (Map.Entry<String, Integer> e : recipe.energonContentMap.entrySet())
					{
						recipe.liquidAmount += e.getValue();
					}
					
					recipe.liquidColor = 0xffffff;
                    
                    ArrayList<String> list = Lists.newArrayList();

                    for (Map.Entry<String, Integer> e : recipe.energonContentMap.entrySet())
                    {
                    	list.add(e.getKey() + ": " + e.getValue());
                    }
                    
                    Collections.sort(list);
                    recipe.energonContentMap.clear();
                    
                    for (String s : list)
                    {
                    	String[] astring = s.split(": ");
                    	recipe.energonContentMap.put(astring[0], Integer.valueOf(astring[1]));
                    }
					
					recipe.liquidColor = calculateLiquidColor(recipe);
					recipe.ingredient.item = crystal.stack;
					
					recipe.computeVisuals();
					processorRecipes.add(recipe);
				}
			}
		}
	}

	public void loadProcessorUsageRecipes(ItemStack ingredient)
	{
		Item item = ingredient.getItem();
		
		if (item instanceof IEnergon || item instanceof ItemBlock && Block.getBlockFromItem(item) instanceof IEnergon)
		{
			IEnergon ienergon = (IEnergon)(item instanceof ItemBlock ? Block.getBlockFromItem(item) : item);
			Energon energon = ienergon.getEnergonType();
			CachedProcessorRecipe recipe = new CachedProcessorRecipe(new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage()), new ItemStack(Items.fish));
			ItemStack result = new ItemStack(TFItems.filledFuelCanister);

			recipe.energonContentMap.put(energon.getId(), ienergon.getMass());
			recipe.liquidColor = calculateLiquidColor(recipe);
			
			ItemFuelCanister.refreshNBT(result);
			result.getTagCompound().setString("Contents", recipe.energonContentMap.toString());
			ItemFuelCanister.setLiquidColor(result, recipe.liquidColor);
			recipe.result.item = result;
			recipe.energonContentMap.clear();
			recipe.liquidColor = 0xffffff;
			
			recipe.computeVisuals();
			processorRecipes.add(recipe);
		}
	}

	@Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
    }

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
    
//    @Override
//	public void drawExtras(int recipe)
//	{
//		drawProgressBar(20, 25, 176, 0, 14, 14, 48, 7);
//		drawProgressBar(42, 24, 176, 14, 24, 17, 48, 0);
//		drawProgressBar(130, 25, 176, 31, 13, 12, 48, 0);
//		
//		GuiDraw.changeTexture(getGuiTexture());
//		GuiDraw.drawTexturedModalRect(72, 6, 204, 52, 52, 52);
//	}

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
