package fiskfille.tf.client.tutorial.ticker;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

public abstract class TutorialTicker extends Gui
{
	protected Minecraft mc = Minecraft.getMinecraft();
	protected ItemRenderer itemRenderer = new ItemRenderer(mc);
	
	protected void drawScaledString(String text, int x, int y, int color, float scale)
	{
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);
		drawString(mc.fontRenderer, text, (int)(x / scale), (int)(y / scale), color);
		GL11.glPopMatrix();
	}
	
	protected void drawCenteredScaledString(String text, int x, int y, int color, float scale)
	{
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);
		drawCenteredString(mc.fontRenderer, text, (int)(x / scale), (int)(y / scale), color);
		GL11.glPopMatrix();
	}
	
	public abstract void tick(EntityPlayer player);

	public void render(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		
	}
	
	public void shoot(EntityPlayer player)
	{
		
	}
	
	public static void delay(final float seconds)
	{
		new Thread()
		{
			public synchronized void start()
			{
				try
				{
					sleep((long)(seconds * 1000F));
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	public void drawBlinkingBox(int x, int y, int width, int height, int color)
	{
		Color color1 = new Color(color);
		float r = (float)color1.getRed() / 255;
		float g = (float)color1.getGreen() / 255;
		float b = (float)color1.getBlue() / 255;
		
		GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		float opacityMax = 20;
        float opacity = mc.thePlayer.ticksExisted % opacityMax;
        opacity = opacity > opacityMax / 2 ? opacityMax / 2 - (opacity - opacityMax / 2) : opacity;
        GL11.glColor4f(r, g, b, opacity / opacityMax + 0.1F);
        drawTexturedModalRect(x, y, 0, 0, width, height);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
