package fiskfille.tf.common.entity;

import java.util.List;

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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.block.TFBlocks;

public class EntityTransformiumSeed extends Entity
{
	public int fuse;
	public int maxFuse;
	private EntityLivingBase tntPlacedBy;

	public EntityTransformiumSeed(World world)
	{
		super(world);
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F;
	}

	public EntityTransformiumSeed(World world, double x, double y, double z, EntityLivingBase entity)
	{
		this(world);
		this.setPosition(x, y, z);
		float f = (float)(Math.random() * Math.PI * 2.0D);
		this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
		this.motionY = 0.05D;
		this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.tntPlacedBy = entity;
	}

	protected void entityInit() {}

	protected boolean canTriggerWalking()
	{
		return false;
	}

	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	public void onUpdate()
	{
		this.motionX = 0;
		this.motionZ = 0;
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.motionY > 0)
		{
			this.motionY -= 0.0005D;
		}
		else
		{
			this.motionY = 0D;
		}
		
		this.worldObj.playSoundAtEntity(this, "note.pling", 1, 0.0F + (float)ticksExisted / 50);
		this.worldObj.playSoundAtEntity(this, "note.bassattack", 1, 0.0F + (float)ticksExisted / 50);
		
		
		if (this.ticksExisted > 100)
		{
			if (this.fuse++ >= 40)
			{
				this.setDead();
				this.explode();
			}
			else
			{
				for (int i = 0; i < 360; ++i)
				{
					float f = 1.0F;
					float f1 = 0.0F;
					float f2 = i;
					double d0 = prevPosX + (posX - prevPosX) * (double)f;
					double d1 = prevPosY + (posY - prevPosY) * (double)f;
					double d2 = prevPosZ + (posZ - prevPosZ) * (double)f;
					Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
					float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
					float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
					float f5 = -MathHelper.cos(-f1 * 0.017453292F);
					float f6 = MathHelper.sin(-f1 * 0.017453292F);
					float f7 = f4 * f5;
					float f8 = f3 * f5;
					double d3 = fuse;
					Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);

					double x = (int)vec31.xCoord;
					int y = 256;
					double z = (int)vec31.zCoord;
					
					while (worldObj.getBlock((int)x, y, (int)z) == Blocks.air)
					{
						--y;
					}

					for (int j = 0; j < 5; ++j)
					{
						worldObj.spawnParticle("smoke", x + rand.nextFloat() - 0.5F / 2, y + 1.2F, z + rand.nextFloat() - 0.5F / 2, 0.0D, 0.0D, 0.0D);
						worldObj.spawnParticle("flame", x + rand.nextFloat() - 0.5F / 2, y + 1.2F, z + rand.nextFloat() - 0.5F / 2, 0.0D, 0.0D, 0.0D);
						
						if (worldObj.getBlock((int)x, y - j, (int)z) != TFBlocks.transformiumStone && !worldObj.isAirBlock((int)x, y - j, (int)z) && worldObj.getBlock((int)x, y - j, (int)z) != Blocks.bedrock)
						{
							worldObj.setBlock((int)x, y - j, (int)z, TFBlocks.transformiumStone);
						}

						List<Entity> list = getEntitiesNear(worldObj, x, y, z, 5.0F);

						for (Entity entity : list)
						{
							if (!entity.getUniqueID().equals(getUniqueID()))
							{
								if (entity instanceof EntityLivingBase)
								{
									((EntityLivingBase)entity).attackEntityFrom(DamageSource.onFire, 99999999999999999999.0F);
									((EntityLivingBase)entity).attackEntityFrom(DamageSource.generic, 99999999999999999999.0F);
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
		if (!this.worldObj.isRemote)
		{
			float f = 10.0F;
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, true);    		
		}
	}

	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setByte("Fuse", (byte)this.fuse);
		nbt.setByte("MaxFuse", (byte)this.maxFuse);
	}

	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.fuse = nbt.getByte("Fuse");
		this.maxFuse = nbt.getByte("MaxFuse");
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	public EntityLivingBase getTntPlacedBy()
	{
		return this.tntPlacedBy;
	}
}