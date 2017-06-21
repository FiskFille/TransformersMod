package fiskfille.tf.common.event;

import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;

@Mod.EventBusSubscriber
public class TFShootHandler
{
    public static int shootCooldown = 0;
    public static int shotsLeft = 4;

    public static boolean reloading;

    public static int laserCharge;
    public static boolean laserFilling;

    @SubscribeEvent
    public static void onLivingUpdate(LivingUpdateEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            if (event.getEntity().world.isRemote)
            {
                int altMode = TFData.ALT_MODE.get(player);
                boolean isTransformed = altMode != -1;

                if (player == Minecraft.getMinecraft().player)
                {
                    Transformer transformer = TFHelper.getTransformer(player);

                    if (laserFilling)
                    {
                        int max = 50;

                        if (laserCharge < max)
                        {
                            laserCharge += 1;
                        }
                        else if (laserCharge >= max)
                        {
                            laserFilling = false;
                            laserCharge = max;
                        }
                    }

                    if (transformer != null)
                    {
                        if (shootCooldown > 0)
                        {
                            shootCooldown--;
                        }

                        Item ammo = transformer.getShootItem(altMode);

                        if (ammo != null)
                        {
                            int ammoCount = getShotsLeft(player, transformer, ammo, altMode);

                            if (isTransformed)
                            {
                                if (reloading && shootCooldown <= 0)
                                {
                                    shotsLeft = ammoCount;
                                    reloading = false;
                                }
                            }
                            else
                            {
                                if (shotsLeft > ammoCount)
                                {
                                    shotsLeft = ammoCount;
                                }
                            }
                        }
                    }
                }

                Transformer transformer = TFHelper.getTransformer(player);

                if (Mouse.isButtonDown(1))
                {
                    if (transformer != null && isTransformed)
                    {
                        if (transformer.canShoot(player, altMode) && transformer.hasRapidFire(altMode) && player.ticksExisted % 2 == 0)
                        {
                            stealthForceShoot(transformer, player, altMode);
                        }
                    }
                }
            }
        }
    }

    private static int getShotsLeft(EntityPlayer player, Transformer transformer, Item shootItem, int altMode)
    {
        int maxAmmo = transformer.getShots(altMode);
        int ammoCount;

        if (player.capabilities.isCreativeMode)
        {
            ammoCount = maxAmmo;
        }
        else
        {
            ammoCount = countItem(shootItem, player);
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

    private static int countItem(Item item, EntityPlayer player)
    {
        int amount = 0;
        InventoryPlayer inventory = player.inventory;

        for (ItemStack stack : inventory.mainInventory)
        {
            if (!stack.isEmpty())
            {
                if (stack.getItem() == item)
                {
                    amount += stack.getCount();
                }
            }
        }

        return amount;
    }

    @SubscribeEvent
    public static void onPlayerInteractBlock(PlayerInteractEvent.RightClickBlock event)
    {
        EntityPlayer player = event.getEntityPlayer();
        Transformer transformer = TFHelper.getTransformer(player);

        if (shootInteraction(player, transformer))
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerInteractEmpty(PlayerInteractEvent.RightClickEmpty event)
    {
        EntityPlayer player = event.getEntityPlayer();
        shootInteraction(player, TFHelper.getTransformer(player));
    }

    private static boolean shootInteraction(EntityPlayer player, Transformer transformer)
    {
        int altMode = TFData.ALT_MODE.get(player);
        boolean isTransformed = altMode != -1;

        if (transformer != null && isTransformed)
        {
            if (transformer.canShoot(player, altMode) && !transformer.hasRapidFire(altMode) && player.world.isRemote)
            {
                stealthForceShoot(transformer, player, altMode);
                return true;
            }
        }

        return false;
    }

    private static void stealthForceShoot(Transformer transformer, EntityPlayer player, int altMode)
    {
        if (player == Minecraft.getMinecraft().player)
        {
            //TODO: Shoot handling
            /*if (transformer instanceof TransformerVurp)
            {
                if (transformer.canShoot(player, altMode))
                {
                    if (!laserFilling && laserCharge > 0)
                    {
                        laserCharge -= 5;
                        player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 2F);
                        TFNetworkManager.WRAPPER.sendToServer(new MessageLaserShoot(player, false));
                        TutorialHandler.shoot(player);
                    }
                    else
                    {
                        if (!laserFilling && (player.inventory.hasItem(transformer.getShootItem(altMode)) || player.capabilities.isCreativeMode))
                        {
                            TFNetworkManager.WRAPPER.sendToServer(new MessageLaserShoot(player, true));
                            TutorialHandler.shoot(player);
                            laserFilling = true;
                        }
                    }
                }
            }
            else
            {
                if (shotsLeft > 0)
                {
                    if (shootCooldown <= 0)
                    {
                        if (transformer.canShoot(player, altMode))
                        {
                            Item shootItem = transformer.getShootItem(altMode);

                            boolean isCreative = player.capabilities.isCreativeMode;
                            boolean hasAmmo = isCreative || player.inventory.hasItem(shootItem);

                            if (hasAmmo)
                            {
                                TFNetworkManager.WRAPPER.sendToServer(new MessageVehicleShoot(player));
                                TutorialHandler.shoot(player);

                                if (!isCreative)
                                {
                                    player.inventory.consumeInventoryItem(shootItem);
                                }
                            }
                        }

                        if (shotsLeft > transformer.getShots(altMode))
                        {
                            shotsLeft = transformer.getShots(altMode);
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
            }*/
        }
    }
}
