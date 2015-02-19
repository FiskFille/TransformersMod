package fiskfille.tf.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.main.MainClass;
import fiskfille.tf.main.TFHelper;
import fiskfille.tf.main.TFItems;
import fiskfille.tf.main.misc.TFVehichleModeMotionManager;

public class GuiOverlay extends Gui
{
	private Minecraft mc;
	private RenderItem itemRenderer;
	public static final ResourceLocation texture = new ResourceLocation(MainClass.modid, "textures/gui/mod_icons.png");

	public GuiOverlay(Minecraft mc)
	{
		super();
		this.mc = mc;
		this.itemRenderer = new RenderItem();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRender(RenderGameOverlayEvent.Pre event)
	{	
		int width = event.resolution.getScaledWidth();
		int height = event.resolution.getScaledHeight();
		EntityPlayer player = mc.thePlayer;

		if (!(event.type == ElementType.JUMPBAR || event.type == ElementType.HOTBAR))
			return;

		renderNitroAndSpeed(event, width, height, player);
		renderKatanaDash(event, width, height, player);
		renderCrossbowAmmo(event, width, height, player);
	}

	public void renderNitroAndSpeed(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		int nitro = TFVehichleModeMotionManager.nitroMap.get(player.getDisplayName()) == null ? 0 : TFVehichleModeMotionManager.nitroMap.get(player.getDisplayName());
		int speed = (int)((TFVehichleModeMotionManager.velocityMap.get(player.getDisplayName()) == null ? 0 : TFVehichleModeMotionManager.velocityMap.get(player.getDisplayName())) * 100);
		int i = TFPlayerData.getTransformationTimer(player) * 30;
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(0F, 0F, 0F, 0.3F);
		drawTexturedModalRect(5 - i, height - 17, 0, 0, 202, 12);
		drawTexturedModalRect(5 - i, height - 25, 0, 0, 202, 6);
		GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
		drawTexturedModalRect(6 - i, height - 16, 0, 0, (int)(nitro * 1.25F), 10);
		GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
		drawTexturedModalRect(6 - i, height - 24, 0, 0, (int)(speed * 1F), 4);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		drawCenteredString(mc.fontRenderer, "Nitro", 106 - i, height - 15, 0xffffff);
		drawCenteredString(mc.fontRenderer, speed + " km/h", 106 - i, height - 26, 0xffffff);
	}

	public void renderKatanaDash(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		if (player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.purgesKatana && !TFPlayerData.isInVehicleMode(player) && TFHelper.isPlayerPurge(player))
		{
			int j = TFItems.purgesKatana.getMaxItemUseDuration(player.getHeldItem()) - player.getItemInUseCount();
			double d = (double)j / 10;
	        
	        if (d > 2.0D)
	        {
	        	d = 2.0D;
	        }
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0F, 0F, 0F, 0.15F);
			drawTexturedModalRect(width / 2 - 26, height / 2 + 9, 0, 0, 52, 12);
			GL11.glColor4f(1F, 0F, 0F, 0.25F);
			drawTexturedModalRect(width / 2 - 25, height / 2 + 10, 0, 0, (int)(d * 25), 10);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
	}
	
	public void renderCrossbowAmmo(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		
	}
}