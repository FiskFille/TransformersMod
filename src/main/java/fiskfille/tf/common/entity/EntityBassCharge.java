package fiskfille.tf.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBassCharge extends EntityThrowable
{
    public EntityBassCharge(World world)
    {
        super(world);
        this.setSize(1.0F, 1.0F);
    }
    
    public EntityBassCharge(World world, EntityLivingBase entity)
    {
        super(world, entity);
        this.setSize(1.0F, 1.0F);
    }
    
    public EntityBassCharge(World world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.setSize(1.0F, 1.0F);
    }
    
    public void onUpdate()
    {
        super.onUpdate();
        
        if (ticksExisted > 5)
        {
            this.setDead();
        }
    }
    
    protected float getGravityVelocity()
    {
        return 0.0F;
    }
    
    protected float func_70182_d()
    {
        return 3.0F;
    }
    
    protected void onImpact(MovingObjectPosition mop)
    {
        if (mop.entityHit != null)
        {
            float f = 2.0F;
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), f);
            mop.entityHit.hurtResistantTime = 0;
        }
        else if (mop.typeOfHit == mop.typeOfHit.BLOCK && worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ).getMaterial() == Material.glass)
        {
            this.worldObj.playAuxSFX(2001, mop.blockX, mop.blockY + 1, mop.blockZ, Block.getIdFromBlock(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ)) + (worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ) << 12));
            this.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
        }
        
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}