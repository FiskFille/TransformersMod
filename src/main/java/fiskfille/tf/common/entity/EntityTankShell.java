package fiskfille.tf.common.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityTankShell extends EntityThrowable implements IEntityAdditionalSpawnData
{
    public boolean allowExplosions;

    public EntityTankShell(World world)
    {
        super(world);
    }

    public EntityTankShell(World world, EntityLivingBase entity, boolean explosions)
    {
        super(world, entity);
        this.allowExplosions = explosions;
    }

    public EntityTankShell(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.04F;
    }

    private float getVelocity()
    {
        return 4.0F;
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                this.explode(result.getBlockPos(), result.sideHit);
            }
            else if (result.typeOfHit == RayTraceResult.Type.ENTITY)
            {
                this.world.createExplosion(null, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, 1.0F, this.allowExplosions);
            }
        }

        this.setDead();
    }

    public void explode(BlockPos pos, EnumFacing sideHit)
    {
        pos = pos.offset(sideHit);

        this.world.createExplosion(null, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, 1.0F, this.allowExplosions);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean("Explosions", this.allowExplosions);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        this.allowExplosions = nbt.getBoolean("Explosions");
    }

    @Override
    public void writeSpawnData(ByteBuf buf)
    {
        buf.writeBoolean(this.allowExplosions);
    }

    @Override
    public void readSpawnData(ByteBuf buf)
    {
        this.allowExplosions = buf.readBoolean();
    }
}
