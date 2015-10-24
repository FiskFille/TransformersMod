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
        float f = (float) (Math.random() * Math.PI * 2.0D);
        motionX = -((float) Math.sin(f)) * 0.02F;
        motionY = 0.05D;
        motionZ = -((float) Math.cos(f)) * 0.02F;
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;
        placedBye = entity;
    }

    protected void entityInit()
    {
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    public void onUpdate()
    {
        motionX = 0;
        motionZ = 0;
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        moveEntity(motionX, motionY, motionZ);

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
                for (int j = 0; j < 3; ++j)
                {
                    int y = 256;

                    while (worldObj.getBlock((int) posX - 1, y, (int) posZ - 1) == Blocks.air)
                    {
                        --y;
                    }

                    Block block = worldObj.getBlock((int) posX - 1, y - j, (int) posZ - 1);

                    if (block != TFBlocks.transformiumStone && block != Blocks.air && block != Blocks.bedrock)
                    {
                        worldObj.setBlock((int) posX - 1, y - j, (int) posZ - 1, TFBlocks.transformiumStone);
                    }
                }

                for (int i = 0; i < 360; ++i)
                {
                    float f = 1.0F;
                    float f1 = 0.0F;
                    float f2 = i;
                    double d0 = prevPosX + (posX - prevPosX) * f - 1;
                    double d1 = prevPosY + (posY - prevPosY) * f;
                    double d2 = prevPosZ + (posZ - prevPosZ) * f - 1;
                    Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
                    float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
                    float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
                    float f5 = -MathHelper.cos(-f1 * 0.017453292F);
                    float f6 = MathHelper.sin(-f1 * 0.017453292F);
                    float f7 = f4 * f5;
                    float f8 = f3 * f5;
                    double d3 = fuse * 0.9;
                    Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);

                    double x = (int) vec31.xCoord;
                    int y = 256;
                    double z = (int) vec31.zCoord;

                    while (worldObj.getBlock((int) x, y, (int) z) == Blocks.air)
                    {
                        --y;
                    }

                    for (int j = 0; j < 3; ++j)
                    {
                        worldObj.spawnParticle("smoke", x + rand.nextFloat() - 0.5F / 2, y + 1.2F, z + rand.nextFloat() - 0.5F / 2, 0.0D, 0.0D, 0.0D);
                        worldObj.spawnParticle("flame", x + rand.nextFloat() - 0.5F / 2, y + 1.2F, z + rand.nextFloat() - 0.5F / 2, 0.0D, 0.0D, 0.0D);

                        if (worldObj.getBlock((int) x, y - j, (int) z) != TFBlocks.transformiumStone && !worldObj.isAirBlock((int) x, y - j, (int) z) && worldObj.getBlock((int) x, y - j, (int) z) != Blocks.bedrock)
                        {
                            worldObj.setBlock((int) x, y - j, (int) z, TFBlocks.transformiumStone);
                        }

                        List<Entity> list = getEntitiesNear(worldObj, x, y - j, z, 5.0F);

                        for (Entity entity : list)
                        {
                            if (!entity.getUniqueID().equals(getUniqueID()))
                            {
                                if (entity instanceof EntityLivingBase)
                                {
                                    ((EntityLivingBase) entity).attackEntityFrom(DamageSource.onFire, Float.MAX_VALUE);
                                    ((EntityLivingBase) entity).attackEntityFrom(DamageSource.generic, Float.MAX_VALUE);
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
        List list = world.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius), IEntitySelector.selectAnything);
        return list;
    }

    private void explode()
    {
        if (!worldObj.isRemote)
        {
            float f = 10.0F;
            worldObj.createExplosion(this, posX, posY, posZ, f, true);
        }
    }

    protected void writeEntityToNBT(NBTTagCompound nbt)
    {
        nbt.setByte("Fuse", (byte) fuse);
        nbt.setByte("MaxFuse", (byte) maxFuse);
    }

    protected void readEntityFromNBT(NBTTagCompound nbt)
    {
        fuse = nbt.getByte("Fuse");
        maxFuse = nbt.getByte("MaxFuse");
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    public EntityLivingBase getSeedPlacedBy()
    {
        return placedBye;
    }
}