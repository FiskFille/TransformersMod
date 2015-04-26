package fiskfille.tf.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityLaser extends EntityThrowable
{
    public EntityLaser(World world)
    {
        super(world);
    }
    
    public EntityLaser(World world, EntityLivingBase entity)
    {
        super(world, entity);
    }
    
    public EntityLaser(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }
    
    public void onUpdate()
    {
        super.onUpdate();
    }
    
    protected float getGravityVelocity()
    {
        return 0.01F;
    }
    
    protected float func_70182_d()
    {
        return 3.5F;
    }
    
    protected void onImpact(MovingObjectPosition mop)
    {
        if (!worldObj.isRemote)
        {
            if (mop.typeOfHit == mop.typeOfHit.BLOCK)
            {
                setFire(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit);
            }
            else if (mop.typeOfHit == mop.typeOfHit.ENTITY)
            {
                Entity entityHit = mop.entityHit;
                
                entityHit.setFire(10);
                entityHit.attackEntityFrom(DamageSource.inFire, 5F);
            }
        }
        
        setDead();
    }
    
    public void setFire(int x, int y, int z, int sideHit)
    {
        if (sideHit == 0)
        {
            --y;
        }
        else if (sideHit == 1)
        {
            ++y;
        }
        else if (sideHit == 2)
        {
            --z;
        }
        else if (sideHit == 3)
        {
            ++z;
        }
        else if (sideHit == 4)
        {
            --x;
        }
        else if (sideHit == 5)
        {
            ++x;
        }

        worldObj.setBlock(x, y, z, Blocks.fire);
    }
}