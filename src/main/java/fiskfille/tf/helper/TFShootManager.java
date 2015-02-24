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
import fiskfille.tf.common.event.PlayerTransformEvent;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.packet.PacketTransformersAction;
import fiskfille.tf.common.packet.base.TFPacketManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFShootManager
{
	public static int shootCooldown = 0;
	public static int shotsLeft = 4;

	public static boolean reloading;

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

					if (transformer != null)
					{
						if (shootCooldown > 0)
						{
							shootCooldown--;
						}

						if(TFHelper.isPlayerVurp(player) && !TFDataManager.isInVehicleMode(player)) //TODO
						{
							if (reloading && shootCooldown <= 0)
							{
								shotsLeft = getShotsLeft(player, transformer, TFItems.miniMissile);
								reloading = false;
							}
						}
						else
						{
							Item ammo = transformer.getShootItem();

							if (ammo != null)
							{
								int ammoCount = getShotsLeft(player, transformer, ammo);

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
	}

	private int getShotsLeft(EntityPlayer player, Transformer transformer, Item shootItem)
	{
		int maxAmmo = transformer.getShots();

		if(!TFDataManager.isInVehicleMode(player))
		{
			maxAmmo = 4;
		}

		int ammoCount; 

		if (player.capabilities.isCreativeMode)
		{
			ammoCount = maxAmmo;
		}
		else
		{
			ammoCount = getAmountOf(shootItem, player);
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

		Transformer transformer = TFHelper.getTransformer(player);

		if(transformer != null && TFDataManager.isInVehicleMode(player))
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
							if (transformer.canShoot(player))
							{
								Item shootItem = transformer.getShootItem();

								boolean isCreative = player.capabilities.isCreativeMode;
								boolean hasAmmo = isCreative || player.inventory.hasItem(shootItem);

								if (hasAmmo)
								{
									TFPacketManager.networkWrapper.sendToServer(new PacketTransformersAction(player, action));

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
