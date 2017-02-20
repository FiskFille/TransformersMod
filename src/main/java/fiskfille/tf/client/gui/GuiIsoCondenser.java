package fiskfille.tf.client.gui;

import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerEmpty;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.tileentity.TileEntityIsoCondenser;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class GuiIsoCondenser extends GuiContainerTF
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/isotopic_condenser.png");
    private TileEntityIsoCondenser tileentity;

    public GuiIsoCondenser(InventoryPlayer inventoryPlayer, TileEntityIsoCondenser tile)
    {
        super(new ContainerEmpty(inventoryPlayer));
        tileentity = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        int[][] aint = { {37, 35}, {37, 53}, {88, 35}, {88, 53}};

        String s = I18n.format("gui.isotopic_condenser");
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 94, 4210752);

        Map<Block, Integer> map = Maps.newHashMap();
        List<Block> blocks = Lists.newArrayList();

        for (Map.Entry<ForgeDirection, Block> e : tileentity.providers.entrySet())
        {
            if (!map.containsKey(e.getValue()))
            {
                map.put(e.getValue(), 1);
                blocks.add(e.getValue());
            }
            else
            {
                map.put(e.getValue(), map.get(e.getValue()) + 1);
            }
        }

        int totalMass = 0;
        TFRenderHelper.setupRenderItemIntoGUI();

        for (int i = 0; i < Math.min(aint.length, blocks.size()); ++i)
        {
            Block block = blocks.get(i);
            int[] pos = aint[i];
            int amount = map.get(block);

            TFRenderHelper.renderItemIntoGUI(pos[0], pos[1], new ItemStack(block, amount));
            totalMass += ((IEnergon) block).getMass() * amount;
        }

        TFRenderHelper.finishRenderItemIntoGUI();

        for (int i = 0; i < Math.min(aint.length, blocks.size()); ++i)
        {
            Block block = blocks.get(i);
            int[] pos = aint[i];
            int percent = Math.round((float) ((IEnergon) block).getMass() * map.get(block) / totalMass * 100);

            drawString(fontRendererObj, I18n.format("gui.isotopic_condenser.percent", percent), pos[0] + 21, pos[1] + 4, -1);
        }

        fontRendererObj.drawString(I18n.format("gui.isotopic_condenser.generating", TFFormatHelper.formatNumberPrecise(tileentity.getGenerationRate(totalMass))), 37, 23, 0x707070);

        GL11.glPushMatrix();
        GL11.glTranslatef(-x, -y, 0);

        if (new Rectangle(x + 17, y + 17, 16, 52).contains(mouseX, mouseY))
        {
            drawHoveringText(TFEnergyHelper.getHoverText(tileentity.data.storage), mouseX, mouseY, fontRendererObj);
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

        if (tileentity.getEnergy() > 0)
        {
            float f = tileentity.getEnergy() / tileentity.getMaxEnergy();
            drawTexturedModalRect(x + 17, y + 17 + Math.round(52 * (1 - f)), 176, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
        }
    }
}
