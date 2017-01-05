package fiskfille.tf.client.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Map;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiEnergonProcessor extends GuiContainerTF
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
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = StatCollector.translateToLocal(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);

        FluidStack stack = tileentity.tank.getFluid();
        ArrayList text = Lists.newArrayList();
        ArrayList colors = Lists.newArrayList();
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

        text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", TFFormatHelper.formatNumber(liquidAmount), TFFormatHelper.formatNumber(tileentity.tank.getCapacity())));
        colors.add(stack != null ? stack.getFluid().getColor(stack) : -1);

        GL11.glPushMatrix();
        GL11.glTranslatef(-x, -y, 0);

        if (new Rectangle(x + 77, y + 17, 52, 52).contains(new Point(mouseX, mouseY)))
        {
            drawHoveringText(text, colors, mouseX, mouseY, fontRendererObj);
        }

        GL11.glPopMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (tileentity.burnTime > 0)
        {
            int i = (int) (tileentity.burnTime * 0.12F);
            drawTexturedModalRect(x + 47, y + 35, 176, 14, i, 17);
        }

        if (tileentity.fillTime > 0)
        {
            int i = (int) (tileentity.fillTime * 0.13F);
            drawTexturedModalRect(x + 135, y + 36, 176, 31, i, 12);
        }

        if (tileentity.powerTime > 0)
        {
            int i = tileentity.powerTime * 13 / tileentity.currentMaxPowerTime;
            drawTexturedModalRect(x + 25, y + 48 - i, 176, 12 - i, 14, i + 2);
        }

        FluidStack stack = tileentity.tank.getFluid();

        if (stack != null && stack.amount > 0)
        {
            mc.getTextureManager().bindTexture(mc.getTextureMapBlocks().locationBlocksTexture);
            float[] rgb = TFRenderHelper.hexToRGB(stack.getFluid().getColor(stack));
            float f = (float) stack.amount / tileentity.tank.getCapacity();

            GL11.glPushMatrix();
            GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
            GL11.glTranslatef(x + 79, y + 19, 0);
            GL11.glScalef(3, 3, 3);
            TFRenderHelper.startGlScissor(x + 79, y + 19 + MathHelper.floor_float(48 * (1 - f)), 48, MathHelper.ceiling_float_int(48 * f));
            drawTexturedModelRectFromIcon(0, 0, stack.getFluid().getStillIcon(), 16, 16);
            TFRenderHelper.endGlScissor();
            GL11.glColor4f(1, 1, 1, 1);
            GL11.glPopMatrix();
        }

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x + 77, y + 17, 204, 0, 52, 52);
    }
}
