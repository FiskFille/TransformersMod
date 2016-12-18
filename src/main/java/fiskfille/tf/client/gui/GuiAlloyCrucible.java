package fiskfille.tf.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiIconFlat.IButtonRenderCallback;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.container.ContainerAlloyCrucible;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible.EnumSmeltingMode;
import fiskfille.tf.helper.TFEnergyHelper;

@SideOnly(Side.CLIENT)
public class GuiAlloyCrucible extends GuiContainer implements IButtonRenderCallback
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/alloy_crucible.png");
    private TileEntityAlloyCrucible tileentity;

    public GuiAlloyCrucible(InventoryPlayer inventoryPlayer, TileEntityAlloyCrucible tile)
    {
        super(new ContainerAlloyCrucible(inventoryPlayer, tile));
        tileentity = tile;
        ySize = 170;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        buttonList.add(new GuiIconFlat(0, x + 151, y + 5, this));
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 0)
        {
            TFNetworkManager.networkWrapper.sendToServer(new MessageTileTrigger(mc.thePlayer, tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, id));
        }
    }
    
    @Override
    public void render(GuiButton button, int mouseX, int mouseY)
    {
        int id = button.id;
        
        if (id == 0)
        {
            EnumSmeltingMode mode = tileentity.smeltingMode;
            IIcon alloyIcon = TFBlocks.alloyCrucible.getIcon(2, 2);
            IIcon furnaceIcon = Blocks.furnace.getIcon(2, 2);
            
            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            
            switch (mode)
            {
            case ALLOY:
                drawTexturedModelRectFromIcon(2, 2, alloyIcon, 16, 16);
                break;
            case FURNACE:
                drawTexturedModelRectFromIcon(2, 2, furnaceIcon, 16, 16);
                break;
            default:
                Tessellator tessellator = Tessellator.instance;
                IIcon icon = alloyIcon;
                
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(2, 18, zLevel, icon.getMinU(), icon.getMaxV());
                tessellator.addVertexWithUV(10, 18, zLevel, icon.getInterpolatedU(8), icon.getMaxV());
                tessellator.addVertexWithUV(10, 2, zLevel, icon.getInterpolatedU(8), icon.getMinV());
                tessellator.addVertexWithUV(2, 2, zLevel, icon.getMinU(), icon.getMinV());
                icon = furnaceIcon;
                tessellator.addVertexWithUV(10, 18, zLevel, icon.getInterpolatedU(8), icon.getMaxV());
                tessellator.addVertexWithUV(18, 18, zLevel, icon.getMaxU(), icon.getMaxV());
                tessellator.addVertexWithUV(18, 2, zLevel, icon.getMaxU(), icon.getMinV());
                tessellator.addVertexWithUV(10, 2, zLevel, icon.getInterpolatedU(8), icon.getMinV());
                tessellator.draw();
                break;
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        String s = StatCollector.translateToLocal("gui.alloy_crucible");
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 94, 4210752);

        ArrayList text = Lists.newArrayList();
        ArrayList colors = Lists.newArrayList();
        text.add(StatCollector.translateToLocalFormatted("gui.emb.storage", TFEnergyHelper.formatNumber(tileentity.getEnergy()), TFEnergyHelper.formatNumber(tileentity.getMaxEnergy())));
        colors.add(-1);

        if (mouseX >= x + 49 && mouseX < x + 49 + 16 && mouseY >= y + 19 && mouseY < y + 19 + 52)
        {
            drawHoveringText(text, colors, mouseX - x, mouseY - y, fontRendererObj);
        }
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
            drawTexturedModalRect(x + 49, y + 19 + Math.round(52 * (1 - f)), 176, Math.round(52 * (1 - f)), 16, Math.round(52 * f));
        }
        
        if (tileentity.smeltTime > 0)
        {
            int progress = tileentity.getSmeltProgressScaled(14);
            drawTexturedModalRect(x + 107, y + 49 - progress + 15, 192, 12 - progress, 14, progress);
        }
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
