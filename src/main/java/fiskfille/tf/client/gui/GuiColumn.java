package fiskfille.tf.client.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerColumn;
import fiskfille.tf.common.energon.power.IEnergyContainerItem;
import fiskfille.tf.common.tileentity.TileEntityColumn;
import fiskfille.tf.helper.TFFormatHelper;

@SideOnly(Side.CLIENT)
public class GuiColumn extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energy_column.png");
    private TileEntityColumn tileentity;

    public GuiColumn(InventoryPlayer inventoryPlayer, TileEntityColumn tile)
    {
        super(new ContainerColumn(inventoryPlayer, tile));
        tileentity = tile;
        ySize = 190;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = StatCollector.translateToLocal(tileentity.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 94, 4210752);

        GL11.glPushMatrix();
        GL11.glTranslatef(-x, -y, 0);

        for (int i = 0; i < tileentity.getSizeInventory(); ++i)
        {
            if (new Rectangle(x + 25 + i * 22, y + 19, 16, 52).contains(new Point(mouseX, mouseY)))
            {
                float usage = tileentity.getEnergyUsage();   
                String prefix = usage > 0 ? EnumChatFormatting.GREEN + "+" : usage < 0 ? EnumChatFormatting.RED + "-" : EnumChatFormatting.GRAY.toString();
                
                drawHoveringText(Arrays.asList(StatCollector.translateToLocalFormatted("gui.emb.storage", TFFormatHelper.formatNumber(tileentity.getEnergy()), TFFormatHelper.formatNumber(tileentity.getMaxEnergy())), prefix + StatCollector.translateToLocalFormatted("gui.emb.rate", TFFormatHelper.formatNumber(Math.abs(usage)) + EnumChatFormatting.GRAY)), mouseX, mouseY, fontRendererObj);
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        for (int i = 0; i < tileentity.getSizeInventory(); ++i)
        {
            ItemStack itemstack = tileentity.getStackInSlot(i);
            float energy = 0;
            float max = 0;

            if (itemstack != null && itemstack.getItem() instanceof IEnergyContainerItem)
            {
                IEnergyContainerItem container = (IEnergyContainerItem) itemstack.getItem();
                energy = container.getEnergyStored(itemstack);
                max = container.getEnergyCapacity(itemstack);
            }

            if (energy > 0)
            {
                float f = energy / max;
                drawTexturedModalRect(x + 25 + i * 22, y + 19 + Math.round(52 * (1 - f)), 176, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
            }
        }
    }
}
