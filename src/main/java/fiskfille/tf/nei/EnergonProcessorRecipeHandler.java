package fiskfille.tf.nei;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
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
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
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
import fiskfille.tf.helper.LiquidHelper;
import fiskfille.tf.helper.TFRenderHelper;

public class EnergonProcessorRecipeHandler extends TemplateRecipeHandler implements IContainerDrawHandler
{
    public class CachedProcessorRecipe extends CachedRecipe
    {
        public PositionedStack ingredient;
        public PositionedStack result;

        public Map<String, Float> energonContentMap = Maps.newTreeMap();
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
                IEnergon ienergon = (IEnergon) (item instanceof ItemBlock ? Block.getBlockFromItem(item) : item);
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
            Map<String, Float> ingredients = ItemFuelCanister.getContents(result);

            for (CrystalPair crystal : crystals)
            {
                String id = crystal.energon.getEnergonType().getId();
                float mass = crystal.energon.getMass();

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

                    for (Map.Entry<String, Float> e : recipe.energonContentMap.entrySet())
                    {
                        recipe.liquidAmount += e.getValue();
                    }

                    recipe.liquidColor = 0xffffff;

                    ArrayList<String> list = Lists.newArrayList();

                    for (Map.Entry<String, Float> e : recipe.energonContentMap.entrySet())
                    {
                        list.add(e.getKey() + ": " + e.getValue());
                    }

                    Collections.sort(list);
                    recipe.energonContentMap.clear();

                    for (String s : list)
                    {
                        String[] astring = s.split(": ");
                        recipe.energonContentMap.put(astring[0], Float.valueOf(astring[1]));
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
            IEnergon ienergon = (IEnergon) (item instanceof ItemBlock ? Block.getBlockFromItem(item) : item);
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
            for (Map.Entry<String, Float> e : recipe.energonContentMap.entrySet())
            {
                Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                int percent = Math.round(e.getValue() * percentMultiplier);

                if (energon != null)
                {
                    liquidColor = TFRenderHelper.blend(liquidColor, energon.getColor(), (float) percent / 100);
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

        if (Minecraft.getMinecraft().currentScreen instanceof GuiRecipe)
        {
        	GuiRecipe gui = (GuiRecipe)Minecraft.getMinecraft().currentScreen;
        	CachedProcessorRecipe recipe1 = getProcessorRecipes(null).get(recipe);
            int x = (gui.width - 176) / 2 + gui.getRecipePosition(recipe).x;
            int y = (gui.height - 166) / 2 + gui.getRecipePosition(recipe).y;
            
            if (recipe1.liquidAmount > 0)
            {
            	GuiDraw.changeTexture(Minecraft.getMinecraft().getTextureMapBlocks().locationBlocksTexture);
            	IIcon icon = GuiEnergonProcessor.energonIcon;
            	float[] rgb = TFRenderHelper.hexToRGB(recipe1.liquidColor);
            	float f = (float)recipe1.liquidAmount / LiquidHelper.ENERGON_PROCESSOR_STORAGE;

            	GL11.glPushMatrix();
            	GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
            	GL11.glTranslatef(74, 8, 0);
            	GL11.glScalef(3.0F, 3.0F, 3.0F);
            	TFRenderHelper.startGlScissor(74 + x, 8 + y + MathHelper.floor_float(48 * (1 - f)), 48, MathHelper.ceiling_float_int(48 * f));
            	GuiDraw.gui.drawTexturedModelRectFromIcon(0, 0, GuiEnergonProcessor.energonIcon, 16, 16);
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

                    double percentMultiplier = 100D / recipe1.liquidAmount;
                    ArrayList text = Lists.newArrayList();
                    ArrayList colors = Lists.newArrayList();

                    if (!recipe1.energonContentMap.isEmpty())
                    {
                        for (Map.Entry<String, Float> e : recipe1.energonContentMap.entrySet())
                        {
                        	Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                            int percent = (int) Math.round(e.getValue() * percentMultiplier);

                            text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.content", energon.getTranslatedName(), percent));
                            colors.add(energon.getColor());
                        }

                        text.add("");
                        colors.add(0);
                    }
                    else
                    {
                        text.add(StatCollector.translateToLocal("gui.energon_processor.unidentified"));
                        colors.add(0xbf0000);
                    }

                    text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", Math.round(recipe1.liquidAmount), Math.round(LiquidHelper.ENERGON_PROCESSOR_STORAGE)));
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
