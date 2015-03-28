package fiskfille.tf.common.entity;

import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTankShell extends EntityThrowable implements IEntityAdditionalSpawnData
{
    private boolean explosions;
    
    public EntityTankShell(World world)
    {
        super(world);
    }
    
    public EntityTankShell(World world, EntityLivingBase thrower, boolean missileExplosions)
    {
        super(world, thrower);
        this.explosions = missileExplosions;
    }

    public EntityTankShell(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }
    
    public EntityTankShell(World world, boolean missileExplosions)
    {
        super(world);
        this.explosions = missileExplosions;
    }

    protected float func_70182_d()
    {
        return 3F;
    }
    
    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return 0.01F;
    }
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }
    
    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        double x = 0;
        double y = 0;
        double z = 0;
        
        if(mop.typeOfHit == MovingObjectType.BLOCK)
        {
            x = mop.blockX;
            y = mop.blockY;
            z = mop.blockZ;
        }
        else
        {
            Entity entityHit = mop.entityHit;
            
            x = entityHit.posX;
            y = entityHit.posY;
            z = entityHit.posZ;
        }
        
        int sideHit = mop.sideHit;
        
        if (sideHit == 0)
            --y;
        else if (sideHit == 1)
            ++y;
        else if (sideHit == 2)
            --z;
        else if (sideHit == 3)
            ++z;
        else if (sideHit == 4)
            --x;
        else if (sideHit == 5)
            ++x;
        
        worldObj.createExplosion(getThrower(), x, y, z, 1.0f, explosions);
        
        this.setDead();
    }

    @Override
    public void writeSpawnData(ByteBuf buf)
    {
        buf.writeBoolean(explosions);
    }

    @Override
    public void readSpawnData(ByteBuf buf)
    {
        explosions = buf.readBoolean();
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        
        nbt.setBoolean("Explosion", explosions);
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        
        explosions = nbt.getBoolean("Explosion");
    }
}