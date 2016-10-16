package fiskfille.tf.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFlamethrowerFire extends EntityThrowable
{
    protected int particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 2;

    public EntityFlamethrowerFire(World world)
    {
        super(world);
        noClip = false;
    }

    public EntityFlamethrowerFire(World world, EntityLivingBase entity)
    {
        super(world, entity);
        noClip = false;
    }

    public EntityFlamethrowerFire(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        noClip = false;
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.0F;
    }

    @Override
    protected float func_70182_d()
    {
        return 1.0F;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (isEntityAlive())
        {
            if (ticksExisted >= particleMaxAge)
            {
                setDead();
            }
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        if (mop.entityHit != null)
        {
            float multiplier = (float) (particleMaxAge - ticksExisted) / particleMaxAge;

            mop.entityHit.setFire((int) (20F * multiplier));

            if (getThrower() instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) getThrower();
                mop.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(player), 5.0F * multiplier);
            }
        }

        if (rand.nextInt(10) == 0)
        {
            if (!worldObj.isRemote)
            {
                if (!setFire(worldObj, mop.blockX, mop.blockY, mop.blockZ, mop.sideHit))
                {
                    float f = 0.25F;
                    motionX *= f;
                    motionY *= f;
                    motionZ *= f;
                }
            }
        }
        else
        {
            float f = 0.25F;
            motionX *= f;
            motionY *= f;
            motionZ *= f;
        }
    }

    public boolean setFire(World world, int x, int y, int z, int sideHit)
    {
        if (sideHit == 0)
        {
            --y;
        }

        if (sideHit == 1)
        {
            ++y;
        }

        if (sideHit == 2)
        {
            --z;
        }

        if (sideHit == 3)
        {
            ++z;
        }

        if (sideHit == 4)
        {
            --x;
        }

        if (sideHit == 5)
        {
            ++x;
        }

        if (world.isAirBlock(x, y, z))
        {
            world.setBlock(x, y, z, Blocks.fire);
        }

        return true;
    }
}