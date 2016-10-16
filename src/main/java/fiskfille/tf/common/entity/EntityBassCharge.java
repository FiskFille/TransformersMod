package fiskfille.tf.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityBassCharge extends EntityThrowable
{
    public EntityBassCharge(World world)
    {
        super(world);
        setSize(1.0F, 1.0F);
    }

    public EntityBassCharge(World world, EntityLivingBase entity)
    {
        super(world, entity);
        setSize(1.0F, 1.0F);
    }

    public EntityBassCharge(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        setSize(1.0F, 1.0F);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (ticksExisted > 20)
        {
            setDead();
        }
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.0F;
    }

    @Override
    protected float func_70182_d()
    {
        return 3.0F;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop)
    {
        if (mop.entityHit != null)
        {
            float f = 2.0F * (1.0F - (float) ticksExisted / 20);
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), f);
            mop.entityHit.hurtResistantTime = 0;
        }
        else if (mop.typeOfHit == MovingObjectType.BLOCK)
        {
            int x = mop.blockX;
            int y = mop.blockY;
            int z = mop.blockZ;
            Block block = worldObj.getBlock(x, y, z);

            if (block.getMaterial().equals(Material.glass))
            {
                worldObj.playAuxSFX(2001, x, y + 1, z, Block.getIdFromBlock(worldObj.getBlock(x, y, z)) + (worldObj.getBlockMetadata(x, y, z) << 12));
                worldObj.setBlockToAir(x, y, z);
            }
            else
            {

            }
        }

        setThrowableHeading(motionX, motionY, motionZ, -0.001F, 0);
    }
}