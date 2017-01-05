package fiskfille.tf.client.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerTransmitter;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTextureHelper;

@SideOnly(Side.CLIENT)
public class GuiTransmitter extends GuiContainerTF
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
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = StatCollector.translateToLocal(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);

        ArrayList text = Lists.newArrayList();
        ArrayList colors = Lists.newArrayList();
        FluidStack stack = tileentity.tank.getFluid();
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

        if (new Rectangle(x + 77, y + 17, 20, 52).contains(new Point(mouseX, mouseY)))
        {
            drawHoveringText(text, colors, mouseX, mouseY, fontRendererObj);
        }

        if (new Rectangle(x + 107, y + 17, 16, 52).contains(new Point(mouseX, mouseY)))
        {
            drawHoveringText(Arrays.asList(StatCollector.translateToLocalFormatted("gui.emb.storage", TFFormatHelper.formatNumber(tileentity.getEnergy()), TFFormatHelper.formatNumber(tileentity.getMaxEnergy()))), mouseX, mouseY, fontRendererObj);
        }

        GL11.glPopMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (tileentity.getEnergy() > 0)
        {
            float f = tileentity.getEnergy() / tileentity.getMaxEnergy();
            drawTexturedModalRect(x + 107, y + 17 + Math.round(52 * (1 - f)), 196, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
        }

        FluidTankInfo info = tileentity.tank.getInfo();
        FluidStack stack = info.fluid;

        if (stack != null && stack.amount > 0)
        {
            mc.getTextureManager().bindTexture(mc.getTextureMapBlocks().locationBlocksTexture);
            float[] rgb = TFRenderHelper.hexToRGB(FluidEnergon.getLiquidColor(stack));
            float f = (float) stack.amount / info.capacity;

            GL11.glPushMatrix();
            GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
            GL11.glTranslatef(x + 80, y + 19, 0);
            GL11.glScalef(3.0F, 3.0F, 3.0F);
            TFRenderHelper.startGlScissor(x + 80, y + 19 + MathHelper.floor_float(48 * (1 - f)), 16, MathHelper.ceiling_float_int(48 * f));
            drawTexturedModelRectFromIcon(0, 0, TFTextureHelper.energonStillIcon, 16, 16);
            TFRenderHelper.endGlScissor();
            GL11.glColor4f(1, 1, 1, 1);
            GL11.glPopMatrix();
        }

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x + 78, y + 17, 176, 0, 20, 52);
    }
}
