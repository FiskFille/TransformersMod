package fiskfille.tf.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

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

    @Override
    public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
    {
        float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
        p_70186_1_ /= f2;
        p_70186_3_ /= f2;
        p_70186_5_ /= f2;
        p_70186_1_ *= p_70186_7_;
        p_70186_3_ *= p_70186_7_;
        p_70186_5_ *= p_70186_7_;
        motionX = p_70186_1_;
        motionY = p_70186_3_;
        motionZ = p_70186_5_;
        float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
        prevRotationYaw = rotationYaw = (float) (Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float) (Math.atan2(p_70186_3_, f3) * 180.0D / Math.PI);
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.005F;
    }

    @Override
    protected float func_70182_d()
    {
        return 4F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        if (!worldObj.isRemote)
        {
            if (mop.typeOfHit == MovingObjectType.BLOCK)
            {
                setFire(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit);
                setDead();
            }
            else if (mop.typeOfHit == MovingObjectType.ENTITY)
            {
                Entity entityHit = mop.entityHit;

                entityHit.setFire(10);
                entityHit.attackEntityFrom(DamageSource.inFire, 2.5F);
                entityHit.hurtResistantTime = 0;
                entityHit.motionY -= 0.2F;
            }
        }
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

        if (worldObj.isAirBlock(x, y, z))
        {
            worldObj.setBlock(x, y, z, Blocks.fire);
        }
    }
}