package fiskfille.tf.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fiskfille.tf.client.particle.TFParticleType;
import fiskfille.tf.client.particle.TFParticles;

public class EntityFlamethrowerFire extends EntityThrowable
{
	public EntityFlamethrowerFire(World world)
	{
		super(world);
	}

	public EntityFlamethrowerFire(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}

	public EntityFlamethrowerFire(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	protected float getGravityVelocity()
	{
		return 0.0F;
	}

	protected float func_70182_d()
	{
		return 2.0F;
	}

	public void onUpdate()
	{
		super.onUpdate();

		if (this.isEntityAlive())
		{
			if(worldObj.isRemote)
			{
				for (int i = 0; i < 5; ++i)
				{
					float f = (rand.nextFloat() / 5);

					TFParticles.spawnParticle(TFParticleType.FLAMETHROWER_FLAME, posX + f, posY + 0.15F + f, posZ + f, 0, 0, 0);
				}
			}

			if (ticksExisted > 7)
			{
				this.setDead();
			}
		}
	}

	protected void onImpact(MovingObjectPosition mop)
	{
		if (mop.entityHit != null)
		{
			mop.entityHit.setFire(20);

			if (getThrower() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)getThrower();
				mop.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
			}
		}

		setFire(worldObj, mop.blockX, mop.blockY, mop.blockZ, mop.sideHit);
		this.setDead();
	}

	public boolean setFire(World world, int x, int y, int z, int sideHit)
	{
		if (sideHit == 0)
		{
			--y;
		}

		if (sideHit == 1)
		{
			++y;
		}

		if (sideHit == 2)
		{
			--z;
		}

		if (sideHit == 3)
		{
			++z;
		}

		if (sideHit == 4)
		{
			--x;
		}

		if (sideHit == 5)
		{
			++x;
		}

		if (world.isAirBlock(x, y, z))
		{
			world.setBlock(x, y, z, Blocks.fire);
		}

		return true;
	}
}