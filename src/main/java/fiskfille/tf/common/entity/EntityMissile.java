package fiskfille.tf.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

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
        this.isInStealthMode = stealthMode;
        this.allowExplosions = explosions;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getVelocity(), 1.0F);
    }

    public EntityMissile(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.ticksExisted < 5 && !this.isInStealthMode)
        {
            this.posY -= 0.2F;
        }

        for (int i = 0; i < 20; ++i)
        {
            float spread = (this.rand.nextFloat() - 0.5F) / 4;
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + spread, this.posY + spread, this.posZ + spread, 0, 0, 0);
        }
    }

    @Override
    protected float getGravityVelocity()
    {
        return this.isInStealthMode ? 0.05F : 0.005F;
    }

    private float getVelocity()
    {
        return this.isInStealthMode ? 2.0F : 4.0F;
    }

    @Override
    protected void onImpact(RayTraceResult mop)
    {
        if (!this.world.isRemote)
        {
            if (mop.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                this.explode(mop.getBlockPos(), mop.sideHit);
            }
            else if (mop.typeOfHit == RayTraceResult.Type.ENTITY)
            {
                this.world.createExplosion(null, mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ, 4, this.allowExplosions);

                if (mop.entityHit instanceof EntityBat && this.getThrower() instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) this.getThrower();

                    if (player.getDistanceSqToEntity(mop.entityHit) >= 25.0D)
                    {
//                        player.addStat(TFAchievements.sharpshooter, 1); TODO
                    }
                }
            }
        }

        this.setDead();
    }

    public void explode(BlockPos pos, EnumFacing sideHit)
    {
        pos = pos.offset(sideHit);

        this.world.createExplosion(null, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, 4, this.allowExplosions);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Explosions", this.allowExplosions);
        nbt.setBoolean("StealthForce", this.isInStealthMode);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.allowExplosions = nbt.getBoolean("Explosions");
        this.isInStealthMode = nbt.getBoolean("StealthForce");
    }

    @Override
    public void writeSpawnData(ByteBuf buf)
    {
        buf.writeBoolean(this.allowExplosions);
        buf.writeBoolean(this.isInStealthMode);
    }

    @Override
    public void readSpawnData(ByteBuf buf)
    {
        this.allowExplosions = buf.readBoolean();
        this.isInStealthMode = buf.readBoolean();
    }
}
