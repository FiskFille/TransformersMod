package fiskfille.tf.common.item;

import java.util.List;
import java.util.Random;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fiskfille.tf.client.particle.TFParticleType;
import fiskfille.tf.client.particle.TFParticles;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.helper.TFHelper;

public class ItemFlamethrower extends ItemSword
{
    public ItemFlamethrower(ToolMaterial material)
    {
        super(material);
        this.setMaxDamage(1500);
        this.setCreativeTab(null);
    }
    
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time)
    {
        if (TFHelper.isPlayerCloudtrap(player) && !world.isRemote && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
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
                Random rand = new Random();
                
                if (duration % 4 == 0)
                {
                    Vec3 backCoords = TFMotionManager.getFrontCoords(player, -0.1F, true);
                    player.motionX = (backCoords.xCoord - player.posX);
                    player.motionZ = (backCoords.zCoord - player.posZ);
                    
                    world.playAuxSFX(1009, (int) player.posX, (int) player.posY, (int) player.posZ, 0);
                }
                
                Vec3 sideCoords = TFMotionManager.getBackSideCoords(player, 0.3F, false, 0.6F, true);
                Vec3 backCoords = TFMotionManager.getFrontCoords(player, 0.5F, true);
                float divider = 3;
                
                if (!world.isRemote)
                {
                    for (int i = 0; i < 50; ++i)
                    {
                        float motionX = (float)(backCoords.xCoord - player.posX) + (rand.nextFloat() - 0.5F) / divider;
                        float motionY = (float)(backCoords.yCoord - (player.posY + player.getEyeHeight())) + (rand.nextFloat() - 0.5F) / divider;
                        float motionZ = (float)(backCoords.zCoord - player.posZ) + (rand.nextFloat() - 0.5F) / divider;
                        
                        TFParticles.spawnParticle(TFParticleType.FLAMETHROWER_FLAME, sideCoords.xCoord, sideCoords.yCoord - player.getEyeHeight(), sideCoords.zCoord, motionX, motionY, motionZ);
                    }
                    
                    for (int i = 0; i < 5; ++i)
                    {
                        float motionX = (float)(backCoords.xCoord - player.posX) + (rand.nextFloat() - 0.5F) / divider;
                        float motionY = (float)(backCoords.yCoord - (player.posY + player.getEyeHeight())) + (rand.nextFloat() - 0.5F) / divider;
                        float motionZ = (float)(backCoords.zCoord - player.posZ) + (rand.nextFloat() - 0.5F) / divider;
                        
                        EntityFlamethrowerFire entity = new EntityFlamethrowerFire(world, player);
                        entity.motionX = motionX;
                        entity.motionY = motionY;
                        entity.motionZ = motionZ;
                        entity.setPosition(sideCoords.xCoord, sideCoords.yCoord - player.getEyeHeight(), sideCoords.zCoord);
                        world.spawnEntityInWorld(entity);
                    }
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
        return EnumAction.none;
    }
    
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (TFHelper.isPlayerCloudtrap(player) && (player.inventory.hasItem(TFItems.energonCrystalPiece) || player.capabilities.isCreativeMode))
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        
        return stack;
    }
    
    public List<Entity> getEntitiesNear(World world, double x, double y, double z, float range)
    {
        return world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range), IEntitySelector.selectAnything);
    }
}