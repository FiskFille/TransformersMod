package fiskfille.tf.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.render.entity.CustomEntityRenderer;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public class ClientEventHandler
{
	private final Minecraft mc = Minecraft.getMinecraft();
	private EntityRenderer renderer, prevRenderer;

	@SubscribeEvent
	public void onPlaySound(PlaySoundAtEntityEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (event.name.startsWith("step.") && TFDataManager.isInVehicleMode((EntityPlayer) event.entity))
			{
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onRenderPlayerSpecialsPre(RenderPlayerEvent.Specials.Pre event)
	{
		if (TFDataManager.getTransformationTimer(event.entityPlayer) < 10)
		{
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre event)
	{
		EntityPlayer player = event.entityPlayer;

		Transformer transformer = TFHelper.getTransformer(player);

		boolean isClientPlayer = mc.thePlayer == player;
		boolean isTransformer = TFHelper.isPlayerTransformer(player);
		boolean inVehicleMode = TFDataManager.isInVehicleMode(player);
		int transformationTimer = TFDataManager.getTransformationTimer(player);
		boolean isTransformerAndInVehicleMode = isTransformer && inVehicleMode;

		float cameraYOffset = 0;

		if (transformer != null)
		{
			cameraYOffset = transformer.getCameraYOffset();
		}
		
		if (isClientPlayer && cameraYOffset != 0)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0, -CustomEntityRenderer.getOffsetY(player), 0);
		}
		
		// This prevents the player from sinking into the ground when sneaking in vehicle mode
		if (player.isSneaking() && TFDataManager.getTransformationTimer(player) < 20)
		{
			GL11.glTranslatef(0, 0.08F, 0);
		}
	}

	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event)
	{
		EntityPlayer player = event.entityPlayer;

		Transformer transformer = TFHelper.getTransformer(player);

		boolean isClientPlayer = mc.thePlayer == player;

		if (transformer != null)
		{
			if (isClientPlayer && transformer.getCameraYOffset() != 0)
			{
				GL11.glPopMatrix();
			}
		}

		ModelBiped modelBipedMain = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, new String[]{"f", "modelBipedMain"});
		TFModelHelper.modelBipedMain = modelBipedMain;

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
				EntityClientPlayerMP player = mc.thePlayer;

				Transformer transformer = TFHelper.getTransformer(player);

				if (transformer != null)
				{
					if (transformer.getCameraYOffset() != 0)
					{
						if (renderer == null)
						{
							renderer = new CustomEntityRenderer(mc);
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
		EntityPlayerSP player = event.entity;
		VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

		int nitro = transformedPlayer == null ? 0 : transformedPlayer.getNitro();
		boolean moveForward = Minecraft.getMinecraft().gameSettings.keyBindForward.getIsKeyPressed();
		boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || Minecraft.getMinecraft().gameSettings.keyBindSprint.getIsKeyPressed();

		if (TFDataManager.isInVehicleMode(player))
		{
			if (nitro > 0 && moveForward && nitroPressed && !TFDataManager.isInStealthMode(player))
			{
				event.newfov = 1.3F;
			}
		}
		else
		{
			ItemStack itemstack = player.getHeldItem();
			
			if (TFDataManager.getZoomTimer(player) > 0 && TFHelper.isPlayerVurp(player) && itemstack != null && itemstack.getItem() == TFItems.vurpsSniper && this.mc.gameSettings.thirdPersonView == 0)
			{
				event.newfov = 1.0F - (float)TFDataManager.getZoomTimer(player) / 10;
			}
		}
	}
	
	@SubscribeEvent
	public void onItemToolTip(ItemTooltipEvent event)
	{
		String s = "tooltip." + event.itemStack.getUnlocalizedName();
		
		if (!s.equals(StatCollector.translateToLocal(s)))
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			{
				event.toolTip.add(StatCollector.translateToLocal(s));
			}
			else
			{
				event.toolTip.add(EnumChatFormatting.BLUE + "Hold SHIFT for info.");
			}
		}
	}
}