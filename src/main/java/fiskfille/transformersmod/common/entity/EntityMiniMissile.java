package fiskfille.transformersmod.common.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityMiniMissile extends EntityArrow implements IProjectile
{
	private int blockX = -1;
	private int blockY = -1;
	private int blockZ = -1;
	private boolean inGround;
	/** Seems to be some sort of timer for animating an arrow. */
	public int missileShake;
	/** The owner of this arrow. */
	public Entity shootingEntity;
	private int ticksInAir;
	private double damage = 4.0D;
	/** The amount of knockback an arrow applies when it hits a mob. */
	private int knockbackStrength;

	private boolean missileExplosions;

	public EntityMiniMissile(World world)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.4F, 0.4F);
	}

	public EntityMiniMissile(World world, double x, double y, double z)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(x, y, z);
		this.yOffset = 0.0F;
	}

	public EntityMiniMissile(World world, EntityLivingBase shooter, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		if (shooter instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.posY = shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D;
		double d0 = p_i1755_3_.posX - shooter.posX;
		double d1 = p_i1755_3_.boundingBox.minY + (double)(p_i1755_3_.height / 3.0F) - this.posY;
		double d2 = p_i1755_3_.posZ - shooter.posZ;
		double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(shooter.posX + d4, this.posY, shooter.posZ + d5, f2, f3);
			this.yOffset = 0.0F;
			float f4 = (float)d3 * 0.2F;
			this.setThrowableHeading(d0, d1 + (double)f4, d2, p_i1755_4_, p_i1755_5_);
		}
	}

	public EntityMiniMissile(World world, EntityLivingBase shooter, float strength, boolean allowMissileExplosions)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		if (shooter instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ, shooter.rotationYawHead, shooter.rotationPitch);
		double dZ; 
		double dX; 

		dZ = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * (1.2D + rand.nextFloat());
		dX = -Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * (1.2D + rand.nextFloat());

		this.posX += dX;
		this.posZ += dZ;

		this.posY += 0.6D;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, strength * 1.5F, shooter.rotationPitch);
		this.missileExplosions = allowMissileExplosions;
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	public void setThrowableHeading(double x, double y, double z, float p1, float p2)
	{
		float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= (double)f2;
		y /= (double)f2;
		z /= (double)f2;
		x *= (double)p1;
		y *= (double)p1;
		z *= (double)p1;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f3 = MathHelper.sqrt_double(x * x + z * z);
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, (double)f3) * 180.0D / Math.PI);
	}

	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int p_70056_9_)
	{
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double xVel, double yVel, double zVel)
	{
		this.motionX = xVel;
		this.motionY = yVel;
		this.motionZ = zVel;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(xVel * xVel + zVel * zVel);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(xVel, zVel) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(yVel, (double)f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
		}

		Block block = this.worldObj.getBlock(this.blockX, this.blockY, this.blockZ);

		if (block.getMaterial() != Material.air)
		{
			block.setBlockBoundsBasedOnState(this.worldObj, this.blockX, this.blockY, this.blockZ);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.blockX, this.blockY, this.blockZ);

			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.missileShake > 0)
		{
			--this.missileShake;
		}

		if (this.inGround)
		{
			this.motionX = 0;
			this.motionY = 0;
			this.motionZ = 0;
			worldObj.createExplosion(this.shootingEntity, blockX, blockY, blockZ, 2, missileExplosions);
			this.setDead();
		}
		else
		{
			++this.ticksInAir;
			
			for (int i = 0; i < 10; ++i)
			{
				Random rand = new Random();
				worldObj.spawnParticle("smoke", posX + (rand.nextFloat() / 4), posY + (rand.nextFloat() / 4), posZ + (rand.nextFloat() / 4), 0, 0, 0);
			}
			
			Vec3 vecPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 movementVec = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vecPos, movementVec, false, true, false);
			vecPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			movementVec = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null)
			{
				movementVec = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int i;
			float gravity;

			for (i = 0; i < entities.size(); ++i)
			{
				Entity entity1 = (Entity)entities.get(i);

				if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
				{
					gravity = 0.2F;
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double)gravity, (double)gravity, (double)gravity);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vecPos, movementVec);

					if (movingobjectposition1 != null)
					{
						double d1 = vecPos.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D)
						{
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}

			Entity entityHit = null;

			if (movingobjectposition != null)
			{
				entityHit = movingobjectposition.entityHit;
			}

			if (movingobjectposition != null && entityHit != null && entityHit instanceof EntityPlayer)
			{
				EntityPlayer entityplayer = (EntityPlayer)entityHit;

				if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
				{
					movingobjectposition = null;
				}
			}

			float f2;
			float f4;

			if (movingobjectposition != null)
			{
				if (entityHit != null && entityHit != shootingEntity)
				{
					f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int k = MathHelper.ceiling_double_int((double)f2 * this.damage);

					if (this.getIsCritical())
					{
						k += this.rand.nextInt(k / 2 + 2);
					}

					DamageSource damagesource = null;

					if (this.shootingEntity == null)
					{
						damagesource = DamageSource.causeArrowDamage(this, this);
					}
					else
					{
						damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
					}

					if (this.isBurning() && !(entityHit instanceof EntityEnderman))
					{
						entityHit.setFire(5);
					}

					if (entityHit.attackEntityFrom(damagesource, (float)k))
					{
						worldObj.createExplosion(shootingEntity, entityHit.posX, entityHit.posY, entityHit.posZ, 2, missileExplosions);
						this.setDead();
					}
					else
					{
						this.motionX *= -0.10000000149011612D;
						this.motionY *= -0.10000000149011612D;
						this.motionZ *= -0.10000000149011612D;
						this.rotationYaw += 180.0F;
						this.prevRotationYaw += 180.0F;
						this.ticksInAir = 0;
					}
				}
				else
				{
					this.blockX = movingobjectposition.blockX;
					this.blockY = movingobjectposition.blockY;
					this.blockZ = movingobjectposition.blockZ;
					this.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - this.posX));
					this.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - this.posY));
					this.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - this.posZ));
					f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
					this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
					this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
					this.inGround = true;
					this.missileShake = 7;

					Block hitBlock = worldObj.getBlock(blockX, blockY, blockZ);
					if (hitBlock.getMaterial() != Material.air)
					{
						hitBlock.onEntityCollidedWithBlock(this.worldObj, this.blockX, this.blockY, this.blockZ, this);
					}
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
			{
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
			{
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F)
			{
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
			{
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float slow = 0.99F;

			gravity = 0.15F;

			if (this.isInWater())
			{
				for (int l = 0; l < 4; ++l)
				{
					f4 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f4, this.posY - this.motionY * (double)f4, this.posZ - this.motionZ * (double)f4, this.motionX, this.motionY, this.motionZ);
				}

				slow = 0.8F;
			}

			if (this.isWet())
			{
				this.extinguish();
			}

			this.motionX *= (double)slow;
			this.motionY *= (double)slow;
			this.motionZ *= (double)slow;
			this.motionY -= (double)gravity;
			this.setPosition(this.posX, this.posY, this.posZ);
			this.func_145775_I();
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setShort("xTile", (short)this.blockX);
		nbt.setShort("yTile", (short)this.blockY);
		nbt.setShort("zTile", (short)this.blockZ);
		nbt.setByte("shake", (byte)this.missileShake);
		nbt.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		nbt.setByte("pickup", (byte)this.canBePickedUp);
		nbt.setDouble("damage", this.damage);
		nbt.setBoolean("explosions", this.missileExplosions);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.blockX = nbt.getShort("xTile");
		this.blockY = nbt.getShort("yTile");
		this.blockZ = nbt.getShort("zTile");
		this.missileShake = nbt.getByte("shake") & 255;
		this.inGround = nbt.getByte("inGround") == 1;
		this.missileExplosions = nbt.getBoolean("explosions");

		if (nbt.hasKey("damage", 99))
		{
			this.damage = nbt.getDouble("damage");
		}

		if (nbt.hasKey("pickup", 99))
		{
			this.canBePickedUp = nbt.getByte("pickup");
		}
		else if (nbt.hasKey("player", 99))
		{
			this.canBePickedUp = nbt.getBoolean("player") ? 1 : 0;
		}
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	public void onCollideWithPlayer(EntityPlayer player)
	{

	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	public void setDamage(double p_70239_1_)
	{
		this.damage = p_70239_1_;
	}

	public double getDamage()
	{
		return this.damage;
	}

	/**
	 * Sets the amount of knockback the arrow applies when it hits a mob.
	 */
	public void setKnockbackStrength(int p_70240_1_)
	{
		this.knockbackStrength = p_70240_1_;
	}

	/**
	 * If returns false, the item will not inflict any damage against entities.
	 */
	public boolean canAttackWithItem()
	{
		return false;
	}
}