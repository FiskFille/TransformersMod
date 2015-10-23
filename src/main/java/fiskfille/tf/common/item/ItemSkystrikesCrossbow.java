package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import fiskfille.tf.common.entity.EntityLaserBeam;
import fiskfille.tf.helper.TFHelper;

public class ItemSkystrikesCrossbow extends Item
{
    public ItemSkystrikesCrossbow()
    {
        super();
        setMaxDamage(1500);
        setMaxStackSize(1);
        setFull3D();
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean b)
    {
        EntityPlayer player = (EntityPlayer) entity;

        if (!itemstack.hasTagCompound())
        {
            itemstack.setTagCompound(new NBTTagCompound());
            itemstack.getTagCompound().setBoolean("blueMode", false);
        }
    }

    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time)
    {
        if (TFHelper.isPlayerSkystrike(player) && !world.isRemote && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
        {
            boolean blue = isBlue(stack);
            stack.getTagCompound().setBoolean("blueMode", !blue);

            stack.damageItem(1, player);

            if (!player.capabilities.isCreativeMode)
            {
                player.inventory.consumeInventoryItem(TFItems.energonCrystalPiece);
            }
        }
    }

    private boolean isBlue(ItemStack stack)
    {
        return stack.hasTagCompound() ? stack.getTagCompound().getBoolean("blueMode") : false;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
        int duration = getMaxItemUseDuration(stack) - count;

        if (stack.hasTagCompound() && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
        {
            World world = player.worldObj;

            if (!world.isRemote)
            {
                if (duration > 1 && duration < 80)
                {
                    boolean blue = isBlue(stack);
                    EntityLaserBeam entity = new EntityLaserBeam(world, player, blue);
                    world.spawnEntityInWorld(entity);
                }
            }
        }
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
        return EnumAction.bow;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (TFHelper.isPlayerSkystrike(player) && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
        {
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
        }

        return stack;
    }

    public List<Entity> getEntitiesNear(World world, double x, double y, double z, float par4)
    {
        List<Entity> list = world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - par4, y - par4, z - par4, x + par4, y + par4, z + par4), IEntitySelector.selectAnything);
        return list;
    }
    
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.removeAll(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 4.0D, 0));
        return multimap;
    }
}