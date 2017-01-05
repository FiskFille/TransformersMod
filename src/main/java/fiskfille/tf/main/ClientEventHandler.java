package fiskfille.tf.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.data.TFPlayerDataNew;
import fiskfille.tf.main.misc.ClientProxy;
import fiskfille.tf.main.misc.TFVehichleModeMotionManager;
import fiskfille.tf.render.entity.EntityRendererAlt;

public class ClientEventHandler
{
	private final Minecraft mc = Minecraft.getMinecraft();
	private EntityRenderer renderer, prevRenderer;
	
	
	@SubscribeEvent
	public void onPlaySound(PlaySoundAtEntityEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (event.name.startsWith("step.") && TFPlayerData.isInVehicleMode((EntityPlayer) event.entity))
			{
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onRenderPlayerSpecialsPre(RenderPlayerEvent.Specials.Pre event)
	{
		if (TFPlayerData.getTransformationTimer(event.entityPlayer) < 5)
		{
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre event)
	{
		
		
		if (event.entityPlayer.isSneaking() && TFPlayerData.getTransformationTimer(event.entityPlayer) < 5)
		{
			GL11.glTranslatef(0, 0.07F, 0);
		}
		
		if (!TFHelper.isPlayerSkystrike(event.entityPlayer))
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0, -EntityRendererAlt.offsetY, 0);
		}
	}
	
	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event)
	{
		if (!TFHelper.isPlayerSkystrike(event.entityPlayer))
		{
			GL11.glPopMatrix();
		}
		
		ModelBiped modelBipedMain = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, new String[]{"f", "modelBipedMain"});
		ClientProxy.modelBipedMain = modelBipedMain;
		TFHelper.getInstance().adjustPlayerVisibility(event.entityPlayer, modelBipedMain);
		ObfuscationReflectionHelper.setPrivateValue(RenderPlayer.class, event.renderer, modelBipedMain, new String[]{"f", "modelBipedMain"});
	}
	
	@SubscribeEvent
	public void renderTick(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();

		if (mc.theWorld != null)
		{
			if (event.phase == TickEvent.Phase.START)
			{				
				if (!TFHelper.isPlayerSkystrike(mc.thePlayer))
				{
					if (renderer == null)
					{
						renderer = new EntityRendererAlt(mc);
					}
					if (mc.entityRenderer != renderer)
					{
						prevRenderer = mc.entityRenderer;
						mc.entityRenderer = renderer;
					}
				}
				else if (prevRenderer != null && mc.entityRenderer != prevRenderer)
				{
					mc.entityRenderer = prevRenderer;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onFOVUpdate(FOVUpdateEvent event)
	{
		int nitro = TFVehichleModeMotionManager.nitroMap.get(event.entity.getCommandSenderName()) == null ? 0 : TFVehichleModeMotionManager.nitroMap.get(event.entity.getCommandSenderName());
		boolean moveForward = Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = ClientProxy.keyBindingNitro.getIsKeyPressed() || Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed();
		
		if (nitro > 0 && TFPlayerData.isInVehicleMode(event.entity) && moveForward && nitroPressed)
		{
			event.newfov = 1.3F;
		}
		
		TFVehichleModeMotionManager.nitroMap.put(event.entity.getCommandSenderName(), nitro);
	}
}