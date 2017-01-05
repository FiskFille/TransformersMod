package fiskfille.tf.common.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.block.TFBlocks;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class EntityTransformiumSeed extends Entity
{
    public int fuse;
    public int maxFuse;
    private EntityLivingBase placedBye;

    public EntityTransformiumSeed(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(0.98F, 0.98F);
        yOffset = height / 2.0F;
    }

    public EntityTransformiumSeed(World world, double x, double y, double z, EntityLivingBase entity)
    {
        this(world);
        setPosition(x, y, z);
        motionY = 0.05D;
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;
        placedBye = entity;
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        moveEntity(0, motionY, 0);

        if (motionY > 0)
        {
            motionY -= 0.0005D;
        }
        else
        {
            motionY = 0D;
        }

        worldObj.playSoundAtEntity(this, "note.pling", 1, 0.0F + (float) ticksExisted / 50);
        worldObj.playSoundAtEntity(this, "note.bassattack", 1, 0.0F + (float) ticksExisted / 50);

        for (int j = 0; j < 5; ++j)
        {
            worldObj.spawnParticle("flame", posX, posY, posZ, 0.0D, -0.5D, 0.0D);
        }

        if (ticksExisted > 100)
        {
            if (fuse++ >= 40)
            {
                setDead();
                explode();
            }
            else
            {
                int y = worldObj.getHeightValue((int) posX, (int) posZ);

                for (int depth = 0; depth < 3; ++depth)
                {
                    Block block = worldObj.getBlock((int) posX, y - depth, (int) posZ);

                    if (block != TFBlocks.transformiumStone && block != Blocks.air && block != Blocks.bedrock)
                    {
                        worldObj.setBlock((int) posX, y - depth, (int) posZ, TFBlocks.transformiumStone);
                    }
                }

                Vec3 position = Vec3.createVectorHelper(posX, posY, posZ);

                for (float angle = 0; angle < 360; ++angle)
                {
                    double range = fuse * 0.9;
                    Vec3 blockPosition = position.addVector(MathHelper.sin(-angle * 0.017453292F - (float) Math.PI) * range, range, MathHelper.cos(-angle * 0.017453292F - (float) Math.PI) * range);

                    double x = (int) blockPosition.xCoord;
                    double z = (int) blockPosition.zCoord;
                    y = worldObj.getHeightValue((int) posX, (int) posZ);

                    for (int depth = 0; depth < 3; ++depth)
                    {
                        worldObj.spawnParticle("smoke", blockPosition.xCoord + rand.nextFloat() - 0.5F / 2, y + 1.2F, blockPosition.zCoord + rand.nextFloat() - 0.5F / 2, 0.0D, 0.0D, 0.0D);
                        worldObj.spawnParticle("flame", blockPosition.xCoord + rand.nextFloat() - 0.5F / 2, y + 1.2F, blockPosition.zCoord + rand.nextFloat() - 0.5F / 2, 0.0D, 0.0D, 0.0D);

                        if (worldObj.getBlock((int) x, y - depth, (int) z) != TFBlocks.transformiumStone && !worldObj.isAirBlock((int) x, y - depth, (int) z) && worldObj.getBlock((int) x, y - depth, (int) z) != Blocks.bedrock)
                        {
                            worldObj.setBlock((int) x, y - depth, (int) z, TFBlocks.transformiumStone);
                        }

                        List<Entity> entities = getEntitiesNear(worldObj, x, y - depth, z, 5.0F);

                        for (Entity entity : entities)
                        {
                            if (!entity.getUniqueID().equals(getUniqueID()))
                            {
                                if (entity instanceof EntityLivingBase)
                                {
                                    entity.attackEntityFrom(DamageSource.onFire, Float.MAX_VALUE);
                                    entity.attackEntityFrom(DamageSource.generic, Float.MAX_VALUE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static List<Entity> getEntitiesNear(World world, double x, double y, double z, float radius)
    {
        return world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius), IEntitySelector.selectAnything);
    }

    private void explode()
    {
        if (!worldObj.isRemote)
        {
            worldObj.createExplosion(this, posX, posY, posZ, 10.0F, true);
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setByte("Fuse", (byte) fuse);
        nbt.setByte("MaxFuse", (byte) maxFuse);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        fuse = nbt.getByte("Fuse");
        maxFuse = nbt.getByte("MaxFuse");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    @Override
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int steps)
    {
    }

    public EntityLivingBase getSeedPlacedBy()
    {
        return placedBye;
    }
}
