package fiskfille.tf.common.transformer.base;

import fiskfille.tf.common.config.TFConfig;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.helper.TFVectorHelper;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

/**
 * @author gegy1000
 */
public abstract class TransformerTruck extends Transformer
{
    public TransformerTruck(int id, ResourceLocation identifier)
    {
        super(id, identifier);
    }

    @Override
    public float fall(EntityPlayer player, float distance, int altMode)
    {
        return TFHelper.isFullyTransformed(player) ? distance / 4 : super.fall(player, distance, altMode);
    }

    @Override
    public boolean hasStealthForce(EntityPlayer player, int altMode)
    {
        return true;
    }

    @Override
    public boolean canJumpAsVehicle(EntityPlayer player, int altMode)
    {
        return TFHelper.isInStealthMode(player);
    }

    @Override
    public float getHeightOffset(EntityPlayer player, int altMode)
    {
        return -1F;
    }

    @Override
    public boolean canUseNitro(EntityPlayer player, int altMode)
    {
        return !TFHelper.isInStealthMode(player);
    }

    @Override
    public void updateMovement(EntityPlayer player, int altMode)
    {
        TFMotionManager.motion(player, 40, 60, 20, 10, false, true, TFHelper.isInStealthMode(player));
    }

    @Override
    public boolean canShoot(EntityPlayer player, int altMode)
    {
        return TFHelper.isInStealthMode(player);
    }

    @Override
    public Item getShootItem(int altMode)
    {
        return TFItems.MISSILE;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player, int altMode)
    {
        return new EntityMissile(player.world, player, TFConfig.projectiles.allowMissileExplosions, TFHelper.isInStealthMode(player));
    }

    @Override
    public int getShots(int altMode)
    {
        return 8;
    }

    @Override
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
        Random rand = player.world.rand;

        for (int i = 0; i < 4; ++i)
        {
            Vec3d side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
            player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, side.x, side.y, side.z, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }

        for (int i = 0; i < 10; ++i)
        {
            Vec3d side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
            player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, side.x, side.y, side.z, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
        }
    }

    /*@Override
    public EnumTutorialType getTutorialType(int altMode)
    {
        return EnumTutorialType.TRUCK;
    }*/
}
