package fiskfille.tf.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerTransmitter;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTextureHelper;

@SideOnly(Side.CLIENT)
public class GuiTransmitter extends GuiContainer
{
	private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/transmitter.png");
	private TileEntityTransmitter tileentity;

	public GuiTransmitter(InventoryPlayer inventoryPlayer, TileEntityTransmitter tile)
	{
		super(new ContainerTransmitter(inventoryPlayer, tile));
		tileentity = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;

		String s = StatCollector.translateToLocal("gui.transmitter");
		fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);

		ArrayList text = Lists.newArrayList();
		ArrayList colors = Lists.newArrayList();
		text.add(StatCollector.translateToLocalFormatted("gui.emb.storage", TFEnergyHelper.formatNumber(tileentity.getEnergy()), TFEnergyHelper.formatNumber(tileentity.getMaxEnergy())));
		colors.add(-1);

		if (mouseX >= k + 107 && mouseX < k + 107 + 16 && mouseY >= l + 17 && mouseY < l + 17 + 52)
		{
		    drawHoveringText(text, colors, mouseX - k, mouseY - l, fontRendererObj);
		}
		
		FluidStack stack = tileentity.tank.getFluid();
    	text = Lists.newArrayList();
    	colors = Lists.newArrayList();
    	int liquidAmount = stack != null ? stack.amount : 0;

    	if (stack != null && stack.amount > 0)
    	{
    		Map<String, Float> ratios = FluidEnergon.getRatios(stack);
    		boolean flag = false;

			for (Map.Entry<String, Float> e : ratios.entrySet())
			{
				Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
				int percent = Math.round(e.getValue() * 100);

				if (percent > 0)
				{
					text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.content", energon.getTranslatedName(), Math.round(e.getValue() * 100)));
					colors.add(energon.getColor());
					flag = true;
				}
			}
    		
    		if (flag)
    		{
    			text.add("");
    			colors.add(-1);
    		}
    		else
    		{
    			text.add(StatCollector.translateToLocal("gui.energon_processor.unidentified"));
    			colors.add(0xbf0000);
    		}
    	}

    	text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", TFEnergyHelper.formatNumber(liquidAmount), TFEnergyHelper.formatNumber(tileentity.tank.getCapacity())));
    	colors.add(stack != null ? stack.getFluid().getColor(stack) : -1);
		
        if (mouseX > k + 77 && mouseX <= k + 77 + 20 && mouseY > l + 17 && mouseY <= l + 17 + 52)
        {
            drawHoveringText(text, colors, mouseX - k, mouseY - l, fontRendererObj);
        }
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(guiTextures);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

		if (tileentity.getEnergy() > 0)
		{
			float f = tileentity.getEnergy() / tileentity.getMaxEnergy();
			drawTexturedModalRect(k + 107, l + 17 + Math.round(52 * (1 - f)), 196, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
		}
		
		FluidTankInfo info = tileentity.getTankInfo(ForgeDirection.UNKNOWN)[0];
		FluidStack stack = info.fluid;
		
		if (stack != null && stack.amount > 0)
        {
        	mc.getTextureManager().bindTexture(mc.getTextureMapBlocks().locationBlocksTexture);
        	float[] rgb = TFRenderHelper.hexToRGB(FluidEnergon.getLiquidColor(stack));
        	float f = (float)stack.amount / info.capacity;

        	GL11.glPushMatrix();
        	GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
        	GL11.glTranslatef(k + 80, l + 19, 0);
        	GL11.glScalef(3.0F, 3.0F, 3.0F);
        	TFRenderHelper.startGlScissor(k + 80, l + 19 + MathHelper.floor_float(48 * (1 - f)), 16, MathHelper.ceiling_float_int(48 * f));
        	drawTexturedModelRectFromIcon(0, 0, TFTextureHelper.energonStillIcon, 16, 16);
        	TFRenderHelper.endGlScissor();
        	GL11.glColor4f(1, 1, 1, 1);
        	GL11.glPopMatrix();
        }

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(k + 78, l + 17, 176, 0, 20, 52);
	}

	public void drawHoveringText(List text, List colors, int x, int y, FontRenderer font)
	{
		if (!text.isEmpty())
		{
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int k = 0;

			for (Object aText : text)
			{
				String s = (String) aText;
				int l = font.getStringWidth(s);

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

			zLevel = 300.0F;
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

			zLevel = 0.0F;
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
		
		GL11.glColor4f(1, 1, 1, 1);
	}
}
