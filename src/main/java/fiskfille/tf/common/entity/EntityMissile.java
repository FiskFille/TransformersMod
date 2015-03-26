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

public class EntityMissile extends EntityThrowable implements IEntityAdditionalSpawnData
{
    private boolean missileExplosions;
    private boolean stealth;
    
    public EntityMissile(World world)
    {
        super(world);
    }
    
    public EntityMissile(World world, EntityLivingBase thrower, boolean missileExplosions, boolean stealth)
    {
        super(world, thrower);
        this.missileExplosions = missileExplosions;
        this.stealth = stealth;
    }

    public EntityMissile(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }
    
    public EntityMissile(World world, boolean missileExplosions)
    {
        super(world);
        this.missileExplosions = missileExplosions;
    }

    protected float func_70182_d()
    {
        return stealth ? 2F : 5F;
    }
    
    /**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     */
    protected float getGravityVelocity()
    {
        return stealth ? 0.1F : 0.05F;
    }
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        
        for (int i = 0; i < 40; ++i)
        {
            Random rand = new Random();
            worldObj.spawnParticle("smoke", posX + (rand.nextFloat() / 4), posY + (rand.nextFloat() / 4), posZ + (rand.nextFloat() / 4), 0, 0, 0);
        }
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
        
        worldObj.createExplosion(getThrower(), x, y, z, 4, missileExplosions);
        this.setDead();
    }

    @Override
    public void writeSpawnData(ByteBuf buf)
    {
        buf.writeBoolean(missileExplosions);
        buf.writeBoolean(stealth);
    }

    @Override
    public void readSpawnData(ByteBuf buf)
    {
        missileExplosions = buf.readBoolean();
        stealth = buf.readBoolean();
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        
        nbt.setBoolean("Explosion", missileExplosions);
        nbt.setBoolean("Stealth", stealth);
    }
    
    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        
        missileExplosions = nbt.getBoolean("Explosion");
        stealth = nbt.getBoolean("Stealth");
    }
}