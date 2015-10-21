package fiskfille.tf.nei;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.ItemList;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerDrawHandler;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.IRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiEnergonProcessor;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.helper.TFHelper;

public class EnergonProcessorRecipeHandler extends TemplateRecipeHandler implements IContainerDrawHandler
{
	public class CachedProcessorRecipe extends CachedRecipe
	{
		public PositionedStack ingredient;
		public PositionedStack result;
		
		public Map<String, Integer> energonContentMap = Maps.newTreeMap();
	    public int liquidColor = 0xffffff;
	    public int liquidAmount;

		public CachedProcessorRecipe(ItemStack in, ItemStack out)
		{
			ingredient = new PositionedStack(in, 19, 6);
			result = new PositionedStack(out, 133, 42);
		}

		@Override
		public PositionedStack getIngredient()
		{
			return ingredient;
		}

		public PositionedStack getResult()
		{
			return result;
		}
		
		@Override
		public PositionedStack getOtherStack()
		{
			return powerSources.get((cycleticks / 48) % powerSources.size()).stack;
		}

		public void computeVisuals()
		{
			ingredient.generatePermutations();
		}
	}
	
	public static class PowerSourcePair
    {
		public PositionedStack stack;
        public int burnTime;
		
        public PowerSourcePair(ItemStack ingred, int burnTime)
        {
            this.stack = new PositionedStack(ingred, 19, 42, false);
            this.burnTime = burnTime;
        }
    }
	
	public static class CrystalPair
    {
		public ItemStack stack;
        public IEnergon energon;
		
        public CrystalPair(ItemStack ingred, IEnergon energon)
        {
            this.stack = ingred;
            this.energon = energon;
        }
    }
	
	public static ArrayList<PowerSourcePair> powerSources;
	public static ArrayList<CrystalPair> crystals;

	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiEnergonProcessor.class;
	}

	@Override
	public String getRecipeName()
	{
		return StatCollector.translateToLocal("recipe.energonProcessor");
	}
	
	@Override
    public TemplateRecipeHandler newInstance()
	{
        if (powerSources == null || powerSources.isEmpty())
        {
            findPowerSources();
        }
        if (crystals == null || crystals.isEmpty())
        {
            findCrystals();
        }
        
        return super.newInstance();
    }

	private void findPowerSources()
	{
		powerSources = Lists.newArrayList();
		
		for (Map.Entry<ItemStack, Integer> e : PowerManager.powerSources.entrySet())
		{
			if (e.getValue() > 0)
			{
				powerSources.add(new PowerSourcePair(e.getKey().copy(), e.getValue()));
			}
		}
	}
	
	private void findCrystals()
	{
		crystals = Lists.newArrayList();
		
		for (ItemStack itemstack : ItemList.items)
		{
			Item item = itemstack.getItem();
			
			if (item instanceof IEnergon || item instanceof ItemBlock && Block.getBlockFromItem(item) instanceof IEnergon)
			{
				IEnergon ienergon = (IEnergon)(item instanceof ItemBlock ? Block.getBlockFromItem(item) : item);
				crystals.add(new CrystalPair(itemstack, ienergon));
			}
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
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
					arecipes.add(recipe);
				}
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient)
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
			arecipes.add(recipe);
		}
	}

	protected int calculateLiquidColor(CachedProcessorRecipe recipe)
	{
		float percentMultiplier = 100F / recipe.liquidAmount;
		int liquidColor = recipe.liquidColor;
        
        if (!recipe.energonContentMap.isEmpty())
        {
            for (Map.Entry<String, Integer> e : recipe.energonContentMap.entrySet())
            {
                Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                int percent = Math.round(e.getValue() * percentMultiplier);
                
                if (energon != null)
                {
                    liquidColor = TFHelper.blend(liquidColor, energon.getColor(), (float)percent / 100);
                }
            }
        }
		
		return liquidColor;
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		for (CachedProcessorRecipe recipe : getProcessorRecipes(null))
		{
			recipe.liquidColor = calculateLiquidColor(recipe);
		}
	}

	@Override
	public String getGuiTexture()
	{
		return TransformersMod.modid + ":textures/gui/container/energon_processor.png";
	}
	
	@Override
	public void drawExtras(int recipe)
	{
		drawProgressBar(20, 25, 176, 0, 14, 14, 48, 7);
		drawProgressBar(42, 24, 176, 14, 24, 17, 48, 0);
		drawProgressBar(130, 25, 176, 31, 13, 12, 48, 0);
		
		
		CachedProcessorRecipe recipe1 = getProcessorRecipes(null).get(recipe);
		
		int t = cycleticks / 2;
		t = t <= 0 ? 1 : t;
		int textureX = (t % 16) / 5 * 26;
		int textureY = (t % 4) * 26;

		GuiDraw.changeTexture(TransformersMod.modid + ":textures/gui/container/energon_flow.png");
		float[] rgb = TFHelper.hexToRGB(recipe1.liquidColor);
		int offsetY = (int)(recipe1.liquidAmount * 0.26F);
		float scale = 2;

		GL11.glPushMatrix();
		GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
		GL11.glScalef(scale, scale, scale);
		GuiDraw.drawTexturedModalRect((int)(72 / scale), (int)(17 / scale) + 21 - offsetY, textureX, textureY, 26, offsetY);
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPopMatrix();
		
		
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(72, 6, 204, 52, 52, 52);
	}
	
	@Override
	public void onPreDraw(GuiContainer gui)
	{
	}

	@Override
	public void renderObjects(GuiContainer gui, int mousex, int mousey)
	{	
	}

	@Override
	public void postRenderObjects(GuiContainer gui, int mouseX, int mouseY) 
	{
		if (gui instanceof GuiRecipe)
		{
			GuiRecipe gui1 = (GuiRecipe)gui;
			IRecipeHandler recipe = gui1.currenthandlers.get(gui1.recipetype);

			if (recipe.getRecipeName().equals(StatCollector.translateToLocal("recipe.energonProcessor")))
			{
				EnergonProcessorRecipeHandler recipeHandler = (EnergonProcessorRecipeHandler)recipe;
				int page = gui1.page;

				for (int i = page * recipeHandler.recipiesPerPage(); i < recipeHandler.numRecipes() && i < (page + 1) * recipeHandler.recipiesPerPage(); i++)
				{
					CachedProcessorRecipe recipe1 = getProcessorRecipes(recipeHandler).get(i);

					double percentMultiplier = 100D / recipe1.liquidAmount;
					ArrayList text = Lists.newArrayList();
					ArrayList colors = Lists.newArrayList();

					if (!recipe1.energonContentMap.isEmpty())
					{
						for (Map.Entry<String, Integer> e : recipe1.energonContentMap.entrySet())
						{
							String name = e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1);
							int percent = (int)Math.round(e.getValue() * percentMultiplier);

							text.add(name + " Energon: " + percent + "%");
							colors.add(TransformersAPI.getEnergonTypeByName(e.getKey()).getColor());
						}

						text.add("");
						colors.add(0);
					}
					else
					{
						text.add("Unidentified");
						colors.add(0xbf0000);
					}

					int percent = Math.round(recipe1.liquidAmount);
					float litres = (float)Math.round(recipe1.liquidAmount) / 50;
					text.add(percent + "% filled (" + litres + "L)");
					colors.add(recipe1.liquidColor);


					int guiLeft = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, gui, 4);
					int guiTop = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, gui, 5);

					Point offset = gui1.getRecipePosition(i);
					Point pos = GuiDraw.getMousePosition();
					Point relMouse = new Point(pos.x - guiLeft - offset.x, pos.y - guiTop - offset.y);
					int x = relMouse.x;
					int y = relMouse.y;

					if (y >= 0 && y < 65)
					{
						if (x >= 72 && x < 124 && y >= 6 && y < 58)
						{
							int k = 0;
							int l = text.size() * (gui.mc.fontRenderer.FONT_HEIGHT + 2);

							for (int j = 0; j < text.size(); ++j)
							{
								k = Math.max(k, gui.mc.fontRenderer.getStringWidth((String)text.get(j)));
							}

							GuiDraw.drawTooltipBox(mouseX + 8, mouseY - 16, k + 7, l + 4);
							RenderHelper.disableStandardItemLighting();
							
							for (int j = 0; j < text.size(); ++j)
							{
								String s = (String)text.get(j);
								GuiDraw.drawString(s, mouseX + 12, mouseY - 12 + (gui.mc.fontRenderer.FONT_HEIGHT + 2) * j, (Integer)colors.get(j));
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void renderSlotUnderlay(GuiContainer gui, Slot slot)
	{
	}

	@Override
	public void renderSlotOverlay(GuiContainer gui, Slot slot)
	{
	}
	
	public List<CachedProcessorRecipe> getProcessorRecipes(EnergonProcessorRecipeHandler handler)
	{
		List<CachedProcessorRecipe> list = Lists.newArrayList();
		
		for (CachedRecipe recipe : handler == null ? arecipes : handler.arecipes)
		{
			list.add((CachedProcessorRecipe)recipe);
		}
		
		return list;
	}
	
	static
	{
		GuiContainerManager.addDrawHandler(new EnergonProcessorRecipeHandler());
	}
}
