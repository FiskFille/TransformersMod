package fiskfille.tf.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.container.ContainerEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.helper.TFHelper;

@SideOnly(Side.CLIENT)
public class GuiEnergonProcessor extends GuiContainer
{
    private static final ResourceLocation guiTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energon_processor.png");
    private static final ResourceLocation energonTextures = new ResourceLocation(TransformersMod.modid, "textures/gui/container/energon_flow.png");
    private TileEntityEnergonProcessor tileentity;

    public GuiEnergonProcessor(InventoryPlayer inventoryPlayer, TileEntityEnergonProcessor tile)
    {
        super(new ContainerEnergonProcessor(inventoryPlayer, tile));
        tileentity = tile;
    }
    
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        
        String s = tileentity.hasCustomInventoryName() ? tileentity.getInventoryName() : I18n.format(tileentity.getInventoryName(), new Object[0]);
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, ySize - 96 + 2, 4210752);
        
        
        
        float percentMultiplier = 100F / tileentity.liquidAmount;
        ArrayList text = Lists.newArrayList();
        ArrayList colors = Lists.newArrayList();
        
        if (!tileentity.energonContentMap.isEmpty())
        {
            for (Map.Entry<String, Integer> e : tileentity.energonContentMap.entrySet())
            {
                String name = e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1);
                int percent = Math.round(e.getValue() * percentMultiplier);
                
                text.add(name + " Energon: " + percent + "%");
                colors.add(TransformersAPI.getEnergonTypeByName(e.getKey()).getColor());
            }
            
            text.add("");
            colors.add(0);
        }
        else
        {
            text.add("Unidentified");
            colors.add(0xbf0000);
        }
        
        int percent = Math.round(tileentity.liquidAmount);
        float litres = (float)Math.round(tileentity.liquidAmount) / 50;
        text.add(percent + "% filled (" + litres + "L)");
        colors.add(tileentity.liquidColor);
        
        if (mouseX > k + 77 && mouseX <= k + 77 + 52 && mouseY > l + 17 && mouseY <= l + 17 + 52)
        {   
            drawHoveringText(text, colors, mouseX - k, mouseY - l, fontRendererObj);
        }
    }
    
    public void drawHoveringText(List text, List colors, int x, int y, FontRenderer font)
    {
        if (!text.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = text.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
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

            if (i1 + k > this.width)
            {
                i1 -= 28 + k;
            }

            if (j1 + k1 + 6 > this.height)
            {
                j1 = this.height - k1 - 6;
            }

            this.zLevel = 300.0F;
            int l1 = -267386864;
            this.drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
            this.drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
            this.drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
            int i2 = 1347420415;
            int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
            this.drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
            this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
            this.drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

            for (int k2 = 0; k2 < text.size(); ++k2)
            {
                String s1 = (String)text.get(k2);
                int color = (Integer)colors.get(k2);
                font.drawStringWithShadow(s1, i1, j1, color);

                if (k2 == 0)
                {
                    j1 += 2;
                }

                j1 += 10;
            }

            this.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(guiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        
        if (tileentity.burnTime > 0)
        {
            int i = (int)((float)tileentity.burnTime * 0.12F);
            drawTexturedModalRect(k + 47, l + 35, 176, 14, i, 17);
        }
        if (tileentity.fillTime > 0)
        {
            int i = (int)((float)tileentity.fillTime * 0.13F);
            drawTexturedModalRect(k + 135, l + 36, 176, 31, i, 12);
        }
        if (tileentity.powerTime > 0)
        {
            int i = tileentity.powerTime * 13 / tileentity.currentMaxPowerTime;
            drawTexturedModalRect(k + 25, l + 48 - i, 176, 12 - i, 14, i + 2);
        }
        
        
        
//        float[] rgb = TFHelper.hexToRGB(tileentity.liquidColor);
//        int offsetY = tileentity.liquidAmount;
//        GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
//        drawTexturedModalRect(k + 77, l + 17 + 52 - offsetY, 204, 0, 52, offsetY);
//        GL11.glColor4f(1, 1, 1, 1);
        
        
        int t = mc.thePlayer.ticksExisted / 2;
        t = t <= 0 ? 1 : t;
        int textureX = (t % 16) / 5 * 26;
        int textureY = (t % 4) * 26;

        mc.getTextureManager().bindTexture(energonTextures);
        float[] rgb = TFHelper.hexToRGB(tileentity.liquidColor);
        int offsetY = (int)(tileentity.liquidAmount * 0.26F);
        float scale = 2;
        
        GL11.glPushMatrix();
        GL11.glColor4f(rgb[0], rgb[1], rgb[2], 1);
        GL11.glScalef(scale, scale, scale);
        drawTexturedModalRect((int)((k + 77) / scale), (int)((l + 17) / scale) + 26 - offsetY, textureX, textureY, 26, offsetY);
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glPopMatrix();
        
        mc.getTextureManager().bindTexture(guiTextures);
        drawTexturedModalRect(k + 77, l + 17, 204, 52, 52, 52);
    }
}
