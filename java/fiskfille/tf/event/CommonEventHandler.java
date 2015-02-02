package fiskfille.tf.event;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import fiskfille.tf.TFHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.achievement.TFAchievements;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.data.TFPlayerData;
import fiskfille.tf.donator.Donators;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.misc.TFMotionManager;
import fiskfille.tf.packet.PacketBroadcastState;
import fiskfille.tf.packet.PacketTransformersAction;
import fiskfille.tf.tick.TickHandler;
import fiskfille.tf.updatechecker.Update;

public class CommonEventHandler
{
	private List<EntityPlayer> playersNotSunc = new ArrayList<EntityPlayer>();
	private boolean loadedFromInternet;

	public static int shootCooldown = 0;
	public static int shotsLeft = 4;

	private boolean reloading;

	private boolean prevVehicleMode; 

	private boolean hasFullAmmo;

	@SubscribeEvent
	public void onSmelt(ItemSmeltedEvent event)
	{
		if(event.smelting.getItem() == TFItems.transformium)
		{
			event.player.addStat(TFAchievements.transformium, 1);
		}
	}

//	@SubscribeEvent
//	public void onChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event)
//	{
//		if(event.player.worldObj.isRemote && event.player == Minecraft.getMinecraft().thePlayer)
//		{	
//			TransformersMod.packetPipeline.sendToServer(new PacketClientRequestTransformationState(event.player));
//			TransformersMod.packetPipeline.sendToServer(new PacketClientRequestStealthState(event.player));
//		}
//	}
	
	@SubscribeEvent
	public void onEntityLoad(EntityEvent.EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			event.entity.registerExtendedProperties(TFPlayerData.IDENTIFIER, new TFPlayerData());
		}
	}

	@SubscribeEvent
	public void onPlayerBreakBlock(BlockEvent.BreakEvent event) {if (TFDataManager.isInVehicleMode(event.getPlayer())) {event.setCanceled(true);}}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) 
	{
		EntityPlayer player = event.entityPlayer;

		if (TFDataManager.isInVehicleMode(player)) 
		{
			Action action = event.action;

			if(action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
			{
				if(shotsLeft > 0)
				{
					if(TFHelper.isPlayerTank(player))
					{
						if(shootCooldown <= 0)
						{
							boolean isCreative = player.capabilities.isCreativeMode;
							boolean hasShell = isCreative || player.inventory.hasItem(TFItems.tankShell);

							if(hasShell)
							{
								TransformersMod.packetPipeline.sendToServer(new PacketTransformersAction(player, action));

								if(!isCreative)
								{
									player.inventory.consumeInventoryItem(TFItems.tankShell);
								}
							}

							shotsLeft--;
							
							if(shotsLeft <= 0)
							{
								shootCooldown = 20;
								reloading = true;
							}
						}
					}
					else if(TFHelper.isPlayerJet(player) || TFDataManager.isInStealthMode(player))
					{
						if(shootCooldown <= 0)
						{
							boolean isCreative = player.capabilities.isCreativeMode;
							boolean hasMissile = isCreative || player.inventory.hasItem(TFItems.missile);

							if(hasMissile && shotsLeft > 0)
							{
								TransformersMod.packetPipeline.sendToServer(new PacketTransformersAction(player, action));

								player.addStat(TFAchievements.firstMissile, 1);

								if(!isCreative)
								{
									player.inventory.consumeInventoryItem(TFItems.missile);
								}

								shotsLeft--;
								
								if(shotsLeft <= 0)
								{
									shootCooldown = 20;
									reloading = true;
								}
							}
						}
					}
				}
				else
				{
					if(!reloading)
					{
						shootCooldown = 20;
						reloading = true;
					}
				}
			}

			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void startTracking(StartTracking event)
	{
		EntityPlayer player = event.entityPlayer;
	
		if(player != null)
		{
			if(!player.worldObj.isRemote)
			{
				if(event.target instanceof EntityPlayer)
				{
					EntityPlayer beingTracked = (EntityPlayer) event.target;
					
					TransformersMod.packetPipeline.sendToDimension(new PacketBroadcastState(beingTracked), beingTracked.dimension);
					TransformersMod.packetPipeline.sendToDimension(new PacketBroadcastState(player), beingTracked.dimension);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityInteract(EntityInteractEvent event) {if (TFDataManager.isInVehicleMode(event.entityPlayer)) {event.setCanceled(true);}}

	@SubscribeEvent
	public void formatName(NameFormat event)
	{
		if(Donators.isDonator(event.entityPlayer))
		{
			event.displayname = EnumChatFormatting.BOLD + "" + EnumChatFormatting.GOLD + "[Donator] " + event.displayname;
		}
	}

	@SubscribeEvent
	public void onSpawn(EntityJoinWorldEvent event)
	{
		Entity entity = event.entity;
		World world = entity.worldObj;
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			player.addStat(TFAchievements.transformers, 1);

			if(!world.isRemote)
			{
				Donators.loadDonators();
				playersNotSunc.add(player);
			}
			else
			{
				TransformersMod.packetPipeline.sendToDimension(new PacketBroadcastState(player), player.dimension);
				TickHandler.prevViewBobbing = Minecraft.getMinecraft().gameSettings.viewBobbing;
				
				TFDataManager.setTransformationTimer(player, TFDataManager.isInVehicleMode(player) ? 10 : 0);
				TFDataManager.setStealthModeTimer(player, TFDataManager.isInStealthMode(player) ? 5 : 0);

				if(!loadedFromInternet)
				{
					Update update = TransformersMod.latestUpdate;

					if(update.isAvailable)
					{
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Version " + update.version + " is now available!"));
						player.addChatMessage(new ChatComponentText(""));
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + "What's New: "));

						String[] updates = update.updateLog.split(Pattern.quote("(newline)"));
						for (String updatePart : updates)
						{
							EnumChatFormatting colour = EnumChatFormatting.RED;

							if(updatePart.trim().startsWith("*"))
							{
								colour = EnumChatFormatting.GOLD;
							}
							else if(updatePart.trim().startsWith("+"))
							{
								colour = EnumChatFormatting.GREEN;
							}

							player.addChatMessage(new ChatComponentText(colour + updatePart));
						}

						player.addChatMessage(new ChatComponentText(""));
					}

					if(Donators.isDonator(player))
					{
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Thank you for donating $" + Donators.getDonationAmount(player).toString().replaceAll(Pattern.quote("$"), "") + "!"));
						player.addStat(TFAchievements.donate, 1);
					}

					loadedFromInternet = true;
				}
			}
		}
		//		else if(entity instanceof EntityTankShell)
		//		{
		//				if(world.isRemote)
		//				{
		//					world.playSound(entity.posX, entity.posY - (double)entity.yOffset, entity.posZ, TransformersMod.modid + ":tankfire", 1, 1, false);
		//				}
		//		}
	}

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
			if (TFHelper.isPlayerTank(player) || (TFHelper.isPlayerCar(player) && !TFDataManager.isInStealthMode(player)))
			{
				if (TFDataManager.isInVehicleMode(player) && TFDataManager.getTransformationTimer(player) < 10)
				{
					player.motionY = 0D;
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;

			//			if(!TFHelper.isPlayerJet(player))
			//			{
			//				boolean vehicleMode = TFDataManager.isInVehicleMode(player);
			//
			//				//if(vehicleMode != prevVehicleMode)
			//				{
			//					if(player == Minecraft.getMinecraft().thePlayer)
			//					{
			//						if(!vehicleMode)
			//						{
			//							float defaultEyeHeight = player.getDefaultEyeHeight();
			//
			//							if(player.eyeHeight != defaultEyeHeight)
			//							{
			//								player.eyeHeight = defaultEyeHeight;
			//							}
			//						}
			//						else
			//						{
			//							player.eyeHeight = -0.9F;
			//						}
			//
			//						//prevVehicleMode = vehicleMode;
			//					}
			//
			//					try 
			//					{
			//						if(vehicleMode)
			//						{
			//							boolean tank = TFHelper.isPlayerTank(player);
			//
			//							if(tank)
			//							{
			//								MainClass.setSizeMethod.invoke(player, 0.8F, 0.6F);
			//							}
			//							else
			//							{
			//								MainClass.setSizeMethod.invoke(player, 0.6F, 0.4F);
			//							}
			//						}
			//						else
			//						{
			//							MainClass.setSizeMethod.invoke(player, 0.6F, 1.8F);
			//						}
			//					} 
			//					catch (IllegalAccessException e)
			//					{
			//						e.printStackTrace();
			//					} 
			//					catch (IllegalArgumentException e) 
			//					{
			//						e.printStackTrace();
			//					} 
			//					catch (InvocationTargetException e)
			//					{
			//						e.printStackTrace();
			//					}
			//				}
			//			}

			if(!event.entity.worldObj.isRemote)
			{
				if(playersNotSunc.size() > 0 && playersNotSunc.contains(player))
				{
					TFDataManager.updateTransformationStatesFor(player);
					playersNotSunc.remove(player);
				}
			}
			else
			{
				if(player == Minecraft.getMinecraft().thePlayer)
				{
					if(shootCooldown > 0)
					{
						shootCooldown--;
					}

					Item ammo = getAmmunition(player);

					if(ammo != null)
					{
						int maxAmmo = 4;

						if(TFDataManager.isInStealthMode(player))
						{
							maxAmmo = 8;
						}

						int ammoCount; 

						if(player.capabilities.isCreativeMode)
						{
							ammoCount = maxAmmo;
						}
						else
						{
							ammoCount = getAmountOf(ammo, player);
						}

						if(ammoCount > maxAmmo)
						{
							ammoCount = maxAmmo;
						}

						if(shotsLeft > ammoCount)
						{
							shotsLeft = ammoCount;
						}

						if(TFDataManager.isInVehicleMode(player))
						{
							if(reloading && shootCooldown <= 0)
							{
								shotsLeft = ammoCount;

								reloading = false;
							}
						}
						else
						{
							int shots = ammoCount;

							if(shotsLeft > shots)
							{
								shotsLeft = shots;
							}
						}
					}
				}
			}
		}
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

	private int getAmountOf(Item item, EntityPlayer player)
	{
		int amount = 0;

		InventoryPlayer inventory = player.inventory;
		for (ItemStack stack : inventory.mainInventory)
		{
			if(stack != null)
			{
				if(stack.getItem() == item)
				{
					amount += stack.stackSize;
				}
			}
		}

		return amount;
	}

	@SubscribeEvent
	public void onLivingFall(LivingFallEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;

			if (TFHelper.isPlayerJet(player) || (TFHelper.isPlayerTank(player) && TFDataManager.isInVehicleMode(player)))
			{
				event.setCanceled(true);
			}
			else if(TFHelper.isPlayerCar(player))
			{
				event.distance /= 2;
			}
		}
	}

	private Item getAmmunition(EntityPlayer player)
	{
		if(TFHelper.isPlayerJet(player) || TFHelper.isPlayerCar(player))
		{
			return TFItems.missile;
		}
		else if(TFHelper.isPlayerTank(player))
		{
			return TFItems.tankShell;
		}

		return null;
	}
}