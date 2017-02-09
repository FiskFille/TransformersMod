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
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerEnergonTank;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTileHelper;

@SideOnly(Side.CLIENT)
public class GuiEnergonTank extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energon_fluid_tank.png");
    private TileEntityEnergonTank tileentity;

    public FluidTank fluidTank;

    public GuiEnergonTank(InventoryPlayer inventoryPlayer, TileEntityEnergonTank tile)
    {
        super(new ContainerEnergonTank(inventoryPlayer, tile));
        tileentity = tile;
    }

    public void updateFluids()
    {
        TileEntityEnergonTank tileBase = TFTileHelper.getTileBase(tileentity);
        FluidStack stack = null;
        int y = tileBase.yCoord;
        int capacity = 0;

        while (y < tileentity.getWorldObj().getHeight() && TFTileHelper.getTileBase(tileentity.getWorldObj().getTileEntity(tileentity.xCoord, y, tileentity.zCoord)) == tileBase)
        {
            TileEntityEnergonTank tile = (TileEntityEnergonTank) tileentity.getWorldObj().getTileEntity(tileentity.xCoord, y, tileentity.zCoord);
            FluidTank tank = tile.getTank();
            
            if (stack == null && tank.getFluid() != null)
            {
                stack = tank.getFluid().copy();
                stack.amount = 0;
            }
            
            if (stack != null)
            {
                stack.amount += tank.getFluidAmount();
            }
            
            capacity += tank.getCapacity();
            ++y;
        }

        fluidTank = new FluidTank(stack, capacity);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        updateFluids();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = StatCollector.translateToLocal(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);

        if (fluidTank == null)
        {
            updateFluids();
        }

        FluidStack stack = fluidTank.getFluid();
        ArrayList text = Lists.newArrayList();
        ArrayList colors = Lists.newArrayList();

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

        text.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", TFFormatHelper.formatNumber(fluidTank.getFluidAmount()), TFFormatHelper.formatNumber(fluidTank.getCapacity())));
        colors.add(stack != null ? stack.getFluid().getColor(stack) : -1);

        GL11.glPushMatrix();
        GL11.glTranslatef(-x, -y, 0);

        if (new Rectangle(x + 61, y + 17, 52, 52).contains(new Point(mouseX, mouseY)))
        {
            drawHoveringText(text, colors, mouseX, mouseY, fontRendererObj);
        }

        GL11.glPopMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (tileentity.fillTime > 0)
        {
            int i = (int) (tileentity.fillTime * 0.13F);
            drawTexturedModalRect(x + 120, y + 36, 176, 0, i, 12);
        }

        if (fluidTank == null)
        {
            updateFluids();
        }

        FluidStack stack = fluidTank.getFluid();

        if (stack != null && stack.amount > 0)
        {
            mc.getTextureManager().bindTexture(mc.getTextureMapBlocks().locationBlocksTexture);
            float[] rgb = TFRenderHelper.hexToRGB(stack.getFluid().getColor(stack));
            float f = (float) stack.amount / fluidTank.getCapacity();

            GL11.glPushMatrix();
            GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
            GL11.glTranslatef(x + 64, y + 19, 0);
            GL11.glScalef(3, 3, 3);
            TFRenderHelper.startGlScissor(x + 64, y + 19 + MathHelper.floor_float(48 * (1 - f)), 48, MathHelper.ceiling_float_int(48 * f));
            drawTexturedModelRectFromIcon(0, 0, stack.getFluid().getStillIcon(), 16, 16);
            TFRenderHelper.endGlScissor();
            GL11.glColor4f(1, 1, 1, 1);
            GL11.glPopMatrix();
        }

        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(x + 62, y + 17, 204, 0, 52, 52);
    }
}
