package fiskfille.tf.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMissile extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";

    public EntityMissile(World par1World)
    {
        super(par1World);
    }

    public EntityMissile(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
    }

    public EntityMissile(World par1World, double par2, double par3, double par4)
    {
        super(par1World, par2, par3, par4);
    }
    
    protected float getGravityVelocity()
    {
        return 0.0F;
    }

    protected float func_70182_d()
    {
        return 10.5F;
    }

    protected float func_70183_g()
    {
        return -20.0F;
    }
    
    protected void onImpact(MovingObjectPosition mop)
    {
        if (mop.entityHit != null)
        {
            byte b0 = 0;

            if (mop.entityHit instanceof EntityBlaze)
            {
                b0 = 3;
            }

            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b0);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}