package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityBassCharge;
import fiskfille.tf.helper.TFHelper;

public class ItemBassBlaster extends ItemSword
{
    public static IIcon bassChargeIcon;
    
    public ItemBassBlaster(ToolMaterial material)
    {
        super(material);
        this.setCreativeTab(TransformersMod.tabTransformers);
        this.setMaxDamage(1500);
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
        int duration = this.getMaxItemUseDuration(stack) - count;
        
        if (duration < 40)
        {
            if (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode)
            {
                World world = player.worldObj;
                world.playSound(player.posX, player.posY, player.posZ, "note.bassattack", 1.0F, 0.8F, true);
                world.playSound(player.posX, player.posY, player.posZ, "note.bass", 1.0F, 0.8F, true);
                
                if (!world.isRemote)
                {
                    EntityBassCharge entity = new EntityBassCharge(world, player);
                    world.spawnEntityInWorld(entity);
                }
            }
        }
    }
    
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (TFHelper.isPlayerSubwoofer(player) && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
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
    
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + iconString);
        bassChargeIcon = iconRegister.registerIcon(TransformersMod.modid + ":bass_charge");
    }
}