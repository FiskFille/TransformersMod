package fiskfille.tf.nei;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidContainerItem;

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

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiEnergonProcessor;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.item.ItemFuelCanister;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.recipe.PowerManager;
import fiskfille.tf.helper.TFRenderHelper;

public class EnergonProcessorRecipeHandler extends TemplateRecipeHandler implements IContainerDrawHandler
{
    public class CachedProcessorRecipe extends CachedRecipe
    {
        public PositionedStack ingredient;
        public PositionedStack result;

        public FluidTank tank = new FluidTank(2000); 

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
                IEnergon ienergon = (IEnergon) (item instanceof ItemBlock ? Block.getBlockFromItem(item) : item);
                crystals.add(new CrystalPair(itemstack, ienergon));
            }
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result)
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

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
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
            arecipes.add(recipe);
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

        if (Minecraft.getMinecraft().currentScreen instanceof GuiRecipe)
        {
        	GuiRecipe gui = (GuiRecipe)Minecraft.getMinecraft().currentScreen;
        	CachedProcessorRecipe recipe1 = getProcessorRecipes(null).get(recipe);
            int x = (gui.width - 176) / 2 + gui.getRecipePosition(recipe).x;
            int y = (gui.height - 166) / 2 + gui.getRecipePosition(recipe).y;
            
            FluidStack stack = recipe1.tank.getFluid();

            if (stack != null && stack.amount > 0)
            {
            	GuiDraw.changeTexture(Minecraft.getMinecraft().getTextureMapBlocks().locationBlocksTexture);
            	float[] rgb = TFRenderHelper.hexToRGB(stack.getFluid().getColor(stack));
            	float f = (float)stack.amount / recipe1.tank.getCapacity();

            	GL11.glPushMatrix();
            	GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
            	GL11.glTranslatef(74, 8, 0);
            	GL11.glScalef(3, 3, 3);
            	TFRenderHelper.startGlScissor(74 + x, 8 + y + MathHelper.floor_float(48 * (1 - f)), 48, MathHelper.ceiling_float_int(48 * f));
            	GuiDraw.gui.drawTexturedModelRectFromIcon(0, 0, TFRenderHelper.energonStillIcon, 16, 16);
            	TFRenderHelper.endGlScissor();
            	GL11.glColor4f(1, 1, 1, 1);
            	GL11.glPopMatrix();
            }
        }

        GuiDraw.changeTexture(getGuiTexture());
        GuiDraw.drawTexturedModalRect(72, 6, 204, 0, 52, 52);
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
            GuiRecipe gui1 = (GuiRecipe) gui;
            IRecipeHandler recipe = gui1.currenthandlers.get(gui1.recipetype);

            if (recipe.getRecipeName().equals(StatCollector.translateToLocal("recipe.energonProcessor")))
            {
                EnergonProcessorRecipeHandler recipeHandler = (EnergonProcessorRecipeHandler) recipe;
                int page = gui1.page;

                for (int i = page * recipeHandler.recipiesPerPage(); i < recipeHandler.numRecipes() && i < (page + 1) * recipeHandler.recipiesPerPage(); i++)
                {
                    CachedProcessorRecipe recipe1 = getProcessorRecipes(recipeHandler).get(i);

                    FluidStack stack = recipe1.tank.getFluid();
                	ArrayList text = Lists.newArrayList();
                	ArrayList colors = Lists.newArrayList();
                	int liquidAmount = 0;
                	int liquidAmount1 = 0;

                	if (stack != null && stack.amount > 0)
                	{
                		Map<String, Integer> contents = FluidEnergon.getContents(stack);

                		for (Map.Entry<String, Integer> e : contents.entrySet())
                		{
                			liquidAmount += e.getValue();
                		}

                		float percentMultiplier = 100F / liquidAmount;
                		liquidAmount1 = stack.amount;

                		if (!contents.isEmpty())
                		{
                			for (Map.Entry<String, Integer> e : contents.entrySet())
                			{
                				Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                				int percent = Math.round(e.getValue() * percentMultiplier);

                				text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.content", energon.getTranslatedName(), percent));
                				colors.add(energon.getColor());
                			}

                			text.add("");
                			colors.add(-1);
                		}
                		else
                		{
                			text.add(StatCollector.translateToLocal("gui.energon_processor.unidentified"));
                			colors.add(0xbf0000);
                		}
                	}

                	text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", liquidAmount1, recipe1.tank.getCapacity()));
                	colors.add(stack != null ? stack.getFluid().getColor(stack) : -1);


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
                                k = Math.max(k, gui.mc.fontRenderer.getStringWidth((String) text.get(j)));
                            }

                            GuiDraw.drawTooltipBox(mouseX + 8, mouseY - 16, k + 7, l + 4);
                            RenderHelper.disableStandardItemLighting();

                            for (int j = 0; j < text.size(); ++j)
                            {
                                String s = (String) text.get(j);
                                GuiDraw.drawString(s, mouseX + 12, mouseY - 12 + (gui.mc.fontRenderer.FONT_HEIGHT + 2) * j, (Integer) colors.get(j));
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
            list.add((CachedProcessorRecipe) recipe);
        }

        return list;
    }

    static
    {
        GuiContainerManager.addDrawHandler(new EnergonProcessorRecipeHandler());
    }
}
