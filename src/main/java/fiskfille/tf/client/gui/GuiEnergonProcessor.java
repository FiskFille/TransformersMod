package fiskfille.tf.client.gui;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.helper.TFRenderHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class GuiEnergonProcessor extends GuiContainer
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energon_processor.png");
    private TileEntityEnergonProcessor tileentity;

    public GuiEnergonProcessor(InventoryPlayer inventoryPlayer, TileEntityEnergonProcessor tile)
    {
        super(new ContainerEnergonProcessor(inventoryPlayer, tile));
        tileentity = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	int k = (width - xSize) / 2;
    	int l = (height - ySize) / 2;

    	String s = tileentity.hasCustomInventoryName() ? tileentity.getInventoryName() : I18n.format(tileentity.getInventoryName());
    	fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
    	fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);

    	FluidStack stack = tileentity.tank.getFluid();
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

    	text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", liquidAmount1, tileentity.tank.getCapacity()));
    	colors.add(stack != null ? stack.getFluid().getColor(stack) : -1);

    	if (mouseX > k + 77 && mouseX <= k + 77 + 52 && mouseY > l + 17 && mouseY <= l + 17 + 52)
    	{
    		drawHoveringText(text, colors, mouseX - k, mouseY - l, fontRendererObj);
    	}
    }

    public void drawHoveringText(List<String> text, List colors, int x, int y, FontRenderer font)
    {
        if (!text.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;

            for (String line : text)
            {
                int l = font.getStringWidth(line);

                if (l > k)
                {
                    k = l;
                }
            }

            int i1 = x + 12;
            int j1 = y - 12;
            int k1 = 8;

            if (text.size() > 1)
            {
                k1 += 2 + (text.size() - 1) * 10;
            }

            if (i1 + k > width)
            {
                i1 -= 28 + k;
            }

            if (j1 + k1 + 6 > height)
            {
                j1 = height - k1 - 6;
            }

            zLevel = 300;
            int l1 = -267386864;
            drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
            drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
            drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
            drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
            drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
            int i2 = 1347420415;
            int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
            drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
            drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
            drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
            drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

            for (int k2 = 0; k2 < text.size(); ++k2)
            {
                String s1 = (String) text.get(k2);
                int color = (Integer) colors.get(k2);
                font.drawStringWithShadow(s1, i1, j1, color);

                if (k2 == 0)
                {
                    j1 += 2;
                }

                j1 += 10;
            }

            zLevel = 0;
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
        
        GL11.glColor4f(1, 1, 1, 1);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    	GL11.glColor4f(1, 1, 1, 1);
    	mc.getTextureManager().bindTexture(guiTextures);
    	int k = (width - xSize) / 2;
    	int l = (height - ySize) / 2;
    	drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

    	if (tileentity.burnTime > 0)
    	{
    		int i = (int) ((float) tileentity.burnTime * 0.12F);
    		drawTexturedModalRect(k + 47, l + 35, 176, 14, i, 17);
    	}

    	if (tileentity.fillTime > 0)
    	{
    		int i = (int) ((float) tileentity.fillTime * 0.13F);
    		drawTexturedModalRect(k + 135, l + 36, 176, 31, i, 12);
    	}

    	if (tileentity.powerTime > 0)
    	{
    		int i = tileentity.powerTime * 13 / tileentity.currentMaxPowerTime;
    		drawTexturedModalRect(k + 25, l + 48 - i, 176, 12 - i, 14, i + 2);
    	}

        FluidStack stack = tileentity.tank.getFluid();

        if (stack != null && stack.amount > 0)
        {
        	mc.getTextureManager().bindTexture(mc.getTextureMapBlocks().locationBlocksTexture);
        	float[] rgb = TFRenderHelper.hexToRGB(stack.getFluid().getColor(stack));
        	float f = (float)stack.amount / tileentity.tank.getCapacity();
        	
        	GL11.glPushMatrix();
        	GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
        	GL11.glTranslatef(k + 79, l + 19, 0);
        	GL11.glScalef(3, 3, 3);
        	TFRenderHelper.startGlScissor(k + 79, l + 19 + MathHelper.floor_float(48 * (1 - f)), 48, MathHelper.ceiling_float_int(48 * f));
        	drawTexturedModelRectFromIcon(0, 0, TFRenderHelper.energonStillIcon, 16, 16);
        	TFRenderHelper.endGlScissor();
        	GL11.glColor4f(1, 1, 1, 1);
        	GL11.glPopMatrix();
        }

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(k + 77, l + 17, 204, 0, 52, 52);
    }
}
