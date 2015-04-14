package fiskfille.tf.common.entity;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMissile extends EntityThrowable implements IEntityAdditionalSpawnData
{
    public boolean isInStealthMode;
    public boolean allowExplosions;
    
    public EntityMissile(World world)
    {
        super(world);
    }
    
    public EntityMissile(World world, EntityLivingBase entity, boolean explosions, boolean stealthMode)
    {
        super(world, entity);
        isInStealthMode = stealthMode;
        allowExplosions = explosions;
    }
    
    public EntityMissile(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }
    
    public void onUpdate()
    {
        super.onUpdate();
        
        if (ticksExisted < 10 && !isInStealthMode)
        {
            posY -= 0.2F;
        }
        
        for (int i = 0; i < 20; ++i)
        {
            float spread = (rand.nextFloat() - 0.5F) / 4;
            worldObj.spawnParticle("smoke", posX + spread, posY + spread, posZ + spread, 0, 0, 0);
        }
    }
    
    protected float getGravityVelocity()
    {
        return isInStealthMode ? 0.1F : 0.0002F;
    }
    
    protected float func_70182_d()
    {
        return isInStealthMode ? 2.0F : 4.0F;
    }
    
    protected void onImpact(MovingObjectPosition mop)
    {
        if (!worldObj.isRemote)
        {
            if (mop.typeOfHit == mop.typeOfHit.BLOCK)
            {
                worldObj.createExplosion(null, mop.blockX, mop.blockY, mop.blockZ, 4, allowExplosions);
            }
            else if (mop.typeOfHit == mop.typeOfHit.ENTITY)
            {
                worldObj.createExplosion(null, mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ, 4, allowExplosions);
            }
        }
        
        setDead();
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        
        nbt.setBoolean("Explosions", allowExplosions);
        nbt.setBoolean("StealthForce", isInStealthMode);
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        
        allowExplosions = nbt.getBoolean("Explosions");
        isInStealthMode = nbt.getBoolean("StealthForce");
    }
    
    @Override
    public void writeSpawnData(ByteBuf buf)
    {
        buf.writeBoolean(allowExplosions);
        buf.writeBoolean(isInStealthMode);
    }
    
    @Override
    public void readSpawnData(ByteBuf buf)
    {
        allowExplosions = buf.readBoolean();
        isInStealthMode = buf.readBoolean();
    }
}