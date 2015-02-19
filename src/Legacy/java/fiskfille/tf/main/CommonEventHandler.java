package fiskfille.tf.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
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

public class CommonEventHandler
{
	@SubscribeEvent
	public void onEntityLoad(EntityEvent.EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			event.entity.registerExtendedProperties(TFPlayerDataNew.IDENTIFIER, new TFPlayerDataNew());
		}
	}
	
	@SubscribeEvent
	public void onPlayerBreakBlock(BlockEvent.BreakEvent event) {if (TFPlayerData.isInVehicleMode(event.getPlayer())) {event.setCanceled(true);}}
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {if (TFPlayerData.isInVehicleMode(event.entityPlayer)) {event.setCanceled(true);}}
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {if (TFPlayerData.isInVehicleMode(event.entityPlayer)) {event.setCanceled(true);}}
	
	@SubscribeEvent
	public void onLivingJump(LivingEvent.LivingJumpEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;

			if (TFHelper.isPlayerSkystrike(player))
			{
				player.motionY += 0.205D;
			}
			if (TFHelper.isPlayerPurge(player))
			{
				if (TFPlayerData.isInVehicleMode(player) && TFPlayerData.getTransformationTimer(player) < 5)
				{
					player.motionY = 0D;
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		//		if (event.entity instanceof EntityPlayer)
		//		{
		//			EntityPlayer player = (EntityPlayer)event.entity;
		//			
		//			if (TFPlayerData.getTransformationTimer(player) < 5 && TFHelper.isPlayerPurge(player) && Keyboard.isKeyDown(Keyboard.KEY_L) && !player.worldObj.isRemote)
		//			{
		//				EntitySnowball projectile = new EntitySnowball(player.worldObj, player);
		//				projectile.setPosition(player.posX, player.posY + 0.25D, player.posZ);
		//				player.worldObj.spawnEntityInWorld(projectile);
		//			}
		//			
		//			int i = TFPlayerData.getTransformationTimer(player);
		//			
		//			if (!player.worldObj.isRemote)
		//			{
		//				player.addChatMessage(i + "");
		//			}
		//		}
	}
	
	@SubscribeEvent
	public void onLivingFall(LivingFallEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;

			if (TFHelper.isPlayerSkystrike(player))
			{
				event.setCanceled(true);
			}
		}
	}
}