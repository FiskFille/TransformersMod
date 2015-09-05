package fiskfille.tf.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fiskfille.tf.common.achievement.TFAchievements;

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
        setThrowableHeading(motionX, motionY, motionZ, func_70182_d(), 1.0F);
    }

    public EntityMissile(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (ticksExisted < 5 && !isInStealthMode)
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
        return isInStealthMode ? 0.05F : 0.005F;
    }

    protected float func_70182_d()
    {
        return isInStealthMode ? 2.0F : 4.0F;
    }

    protected void onImpact(MovingObjectPosition mop)
    {
        if (!worldObj.isRemote)
        {
            if (mop.typeOfHit == MovingObjectType.BLOCK)
            {
                explode(mop.blockX, mop.blockY, mop.blockZ, mop.sideHit);
            }
            else if (mop.typeOfHit == MovingObjectType.ENTITY)
            {
                worldObj.createExplosion(null, mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ, 4, allowExplosions);

                if (mop.entityHit instanceof EntityBat && getThrower() instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) getThrower();

                    if (player.getDistanceSqToEntity(mop.entityHit) >= 25.0D)
                    {
                        player.addStat(TFAchievements.sharpshooter, 1);
                    }
                }
            }
        }

        setDead();
    }

    public void explode(int x, int y, int z, int sideHit)
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

        worldObj.createExplosion(null, x + 0.5F, y + 0.5F, z + 0.5F, 4, allowExplosions);
    }

    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Explosions", allowExplosions);
        nbt.setBoolean("StealthForce", isInStealthMode);
    }

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