package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityFlamethrowerFire;
import fiskfille.tf.common.entity.EntityLaserBeam;
import fiskfille.tf.helper.TFHelper;

public class ItemSkystrikesCrossbow extends ItemSword
{
    public ItemSkystrikesCrossbow(ToolMaterial material)
    {
        super(material);
        this.setCreativeTab(TransformersMod.tabTransformers);
        this.setMaxDamage(1500);
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
        int duration = this.getMaxItemUseDuration(stack) - count;
        
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
            //            boolean isInVehichleMode = false;
            //            
            //            if (duration == 1)
            //            {
            //                
            //            }
            //            
            //            if (duration > 1 && duration < 80)
            //            {
            //                float f = 1.0F;
            //                float f11 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
            //                float f21 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f) + 90;
            //                double d01 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
            //                double d11 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
            //                double d21 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
            //                Vec3 vec32 = Vec3.createVectorHelper(d01, d11, d21);
            //                float f31 = MathHelper.cos(-f21 * 0.017453292F - (float) Math.PI);
            //                float f41 = MathHelper.sin(-f21 * 0.017453292F - (float) Math.PI);
            //                float f51 = -MathHelper.cos(-f11 * 0.017453292F);
            //                float f61 = MathHelper.sin(-f11 * 0.017453292F);
            //                float f71 = f41 * f51;
            //                float f81 = f31 * f51;
            //                double d31 = isInVehichleMode ? 0.0D : 0.3D;
            //                Vec3 vec33 = vec32.addVector(f71 * d31, f61 * d31, f81 * d31);
            //                
            //                for (int i = 0; i < 20; ++i)
            //                {
            //                    float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
            //                    float f2 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f);
            //                    double d0 = vec33.xCoord * (double) f;
            //                    double d1 = vec33.yCoord * (double) f - 0.1D;
            //                    double d2 = vec33.zCoord * (double) f;
            //                    Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
            //                    float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
            //                    float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
            //                    float f5 = -MathHelper.cos(-f1 * 0.017453292F);
            //                    float f6 = MathHelper.sin(-f1 * 0.017453292F);
            //                    float f7 = f4 * f5;
            //                    float f8 = f3 * f5;
            //                    double d3 = i + 1;
            //                    Vec3 damageVec = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
            //                    
            //                    for (int i1 = 0; i1 < 10; ++i1)
            //                    {
            //                        boolean flag = stack.hasTagCompound() ? stack.getTagCompound().getBoolean("blueMode") : false;
            //                        player.worldObj.spawnParticle("reddust", damageVec.xCoord, damageVec.yCoord - (isInVehichleMode ? 0.5D : 0.0D), damageVec.zCoord, flag ? -1.0D : 0.0D, 0.0D, flag ? 1.0D : 0.0D);
            //                    }
            //                    
            //                    List<Entity> list = getEntitiesNear(player.worldObj, damageVec.xCoord, damageVec.yCoord, damageVec.zCoord, 0.3F);
            //                    
            //                    for (Entity entity : list)
            //                    {
            //                        if (!entity.getUniqueID().equals(player.getUniqueID()))
            //                        {
            //                            if (entity instanceof EntityLivingBase)
            //                            {
            //                                ((EntityLivingBase) entity).attackEntityFrom(DamageSource.causePlayerDamage(player), 5.0F);
            //                            }
            //                        }
            //                    }
            //                }
            //            }
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
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        
        return stack;
    }
    
    public List<Entity> getEntitiesNear(World world, double x, double y, double z, float par4)
    {
        List<Entity> list = world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - par4, y - par4, z - par4, x + par4, y + par4, z + par4), IEntitySelector.selectAnything);
        return list;
    }
    
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + iconString);
    }
}