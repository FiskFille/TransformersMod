package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import fiskfille.tf.common.entity.EntityBassCharge;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFHelper;

public class ItemBassBlaster extends Item
{
    public ItemBassBlaster()
    {
        super();
        setMaxDamage(1500);
        setMaxStackSize(1);
        setFull3D();
    }

    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time)
    {
        if (TFHelper.isPlayerSubwoofer(player) && !world.isRemote && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
        {
            stack.damageItem(1, player);

            if (!player.capabilities.isCreativeMode)
            {
                player.inventory.consumeInventoryItem(TFItems.energonCrystalPiece);
            }
        }
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
        int duration = getMaxItemUseDuration(stack) - count;

        if (duration < 60)
        {
            if (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode)
            {
                World world = player.worldObj;

                for (int i = 0; i < 2; ++i)
                {
                    world.playSoundAtEntity(player, "note.bass", 1.0F, 0.8F);
                }

                if (!world.isRemote)
                {
                    EntityBassCharge entity = new EntityBassCharge(world, player);

                    if (TFDataManager.isInVehicleMode(player))
                    {
                        entity.posY -= 1.;
                    }

                    world.spawnEntityInWorld(entity);
                }
            }
        }
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (TFHelper.isPlayerSubwoofer(player) && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
        {
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
        }

        return stack;
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        return stack;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.none;
    }

    public List<Entity> getEntitiesNear(World world, double x, double y, double z, float par4)
    {
        List<Entity> list = world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - par4, y - par4, z - par4, x + par4, y + par4, z + par4), IEntitySelector.selectAnything);
        return list;
    }
}