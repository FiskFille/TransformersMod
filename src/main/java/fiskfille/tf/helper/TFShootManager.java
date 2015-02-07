package fiskfille.tf.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.event.PlayerTransformEvent;
import fiskfille.tf.packet.PacketTransformersAction;
import fiskfille.tf.transformer.base.Transformer;

public class TFShootManager
{
	public static int shootCooldown = 0;
	public static int shotsLeft = 4;

	private boolean reloading;
	private boolean hasFullAmmo;
	
	@SubscribeEvent
	public void onTransform(PlayerTransformEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		
		if (event.entity.worldObj.isRemote)
		{
			if (player == Minecraft.getMinecraft().thePlayer)
			{
				Transformer transformer = TFHelper.getTransformer(player);
				
				if(transformer != null)
				{
					shotsLeft = getShotsLeft(player, transformer);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;

			if (event.entity.worldObj.isRemote)
			{
				if (player == Minecraft.getMinecraft().thePlayer)
				{
					Transformer transformer = TFHelper.getTransformer(player);

					if (shootCooldown > 0)
					{
						shootCooldown--;
					}

					if (transformer != null)
					{
						Item ammo = transformer.getShootItem();

						if (ammo != null)
						{
							int ammoCount = getShotsLeft(player, transformer);

							if (TFDataManager.isInVehicleMode(player))
							{
								if (reloading && shootCooldown <= 0)
								{
									shotsLeft = ammoCount;

									reloading = false;
								}
							}
							else
							{
								int shots = ammoCount;

								if (shotsLeft > shots)
								{
									shotsLeft = shots;
								}
							}
						}
					}
				}
			}
		}
	}

	private int getShotsLeft(EntityPlayer player, Transformer transformer)
	{
		int maxAmmo = transformer.getShots();

		int ammoCount; 

		if (player.capabilities.isCreativeMode)
		{
			ammoCount = maxAmmo;
		}
		else
		{
			ammoCount = getAmountOf(transformer.getShootItem(), player);
		}

		if (ammoCount > maxAmmo)
		{
			ammoCount = maxAmmo;
		}

		if (shotsLeft > ammoCount)
		{
			shotsLeft = ammoCount;
		}
		return ammoCount;
	}

	private int getAmountOf(Item item, EntityPlayer player)
	{
		int amount = 0;

		InventoryPlayer inventory = player.inventory;

		for(ItemStack stack : inventory.mainInventory)
		{
			if (stack != null)
			{
				if (stack.getItem() == item)
				{
					amount += stack.stackSize;
				}
			}
		}

		return amount;
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) 
	{
		EntityPlayer player = event.entityPlayer;

		if (TFDataManager.isInVehicleMode(player)) 
		{
			Transformer transformer = TFHelper.getTransformer(player);

			if(transformer != null)
			{
				if (transformer.canShoot(player))
				{
					Action action = event.action;

					if (action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
					{
						if (shotsLeft > 0)
						{
							if (shootCooldown <= 0)
							{
								if (transformer.canShoot(player) && TFDataManager.isInVehicleMode(player))
								{
									Item shootItem = transformer.getShootItem();

									boolean isCreative = player.capabilities.isCreativeMode;
									boolean hasAmmo = isCreative || player.inventory.hasItem(shootItem);

									if (hasAmmo)
									{
										TransformersMod.packetPipeline.sendToServer(new PacketTransformersAction(player, action));

										if (!isCreative)
										{
											player.inventory.consumeInventoryItem(shootItem);
										}
									}
								} 

								shotsLeft--;

								if (shotsLeft <= 0)
								{
									shootCooldown = 20;
									reloading = true;
								}
							}
						}
						else
						{
							if (!reloading)
							{
								shootCooldown = 20;
								reloading = true;
							}
						}
					}

					event.setCanceled(true);
				}
			}
		}
	}
}
