package fiskfille.tf.client.gui;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.Lists;

public abstract class GuiContainerTF extends GuiContainer
{
    public GuiContainerTF(Container container)
    {
        super(container);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawHoveringText(mouseX, mouseY);
    }

    public void drawHoveringText(int mouseX, int mouseY)
    {
        for (GuiButton button : (List<GuiButton>) buttonList)
        {
            if (button.func_146115_a() && button instanceof GuiButtonFlat)
            {
                List<String> text = ((GuiButtonFlat) button).getHoverText();

                if (!text.isEmpty())
                {
                    drawHoveringText(text, mouseX, mouseY, fontRendererObj);
                }
            }
        }
    }

    @Override
    protected void drawHoveringText(List text, int mouseX, int mouseY, FontRenderer font)
    {
        if (!text.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = text.iterator();
            
            List<String> list = Lists.newArrayList();
            List<Integer> colors = Lists.newArrayList();

            while (iterator.hasNext())
            {
                String s = (String) iterator.next();
                int color = -1;
                
                if (s.startsWith("&<0x") && s.contains(">"))
                {
                    String s1 = s.split("&<0x")[1];
                    s1 = s1.substring(0, s1.indexOf('>'));
                    
                    try
                    {
                        color = Integer.valueOf(s1);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    s = s.substring(s.indexOf('>') + 1);
                }
                
                list.add(s);
                colors.add(color);
                int l = font.getStringWidth(s);

                if (l > k)
                {
                    k = l;
                }
            }
            
            text = Lists.newArrayList();
            text.addAll(list);

            int j2 = mouseX + 12;
            int k2 = mouseY - 12;
            int i1 = 8;

            if (text.size() > 1)
            {
                i1 += 2 + (text.size() - 1) * 10;
            }

            if (j2 + k > width)
            {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > height)
            {
                k2 = height - i1 - 6;
            }

            zLevel = 300;
            itemRender.zLevel = 300;
            int j1 = -267386864;
            drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < text.size(); ++i2)
            {
                String s1 = (String) text.get(i2);
                font.drawStringWithShadow(s1, j2, k2, colors.get(i2));

                if (i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }

            zLevel = 0;
            itemRender.zLevel = 0;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }
}
