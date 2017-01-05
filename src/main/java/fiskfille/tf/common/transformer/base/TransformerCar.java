package fiskfille.tf.common.transformer.base;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.client.tutorial.EnumTutorialType;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFVectorHelper;

/**
 * @author gegy1000
 */
public abstract class TransformerCar extends Transformer
{
    public TransformerCar(String name)
    {
        super(name);
    }

    @Override
    public float fall(EntityPlayer player, float distance, int altMode)
    {
        return TFHelper.isFullyTransformed(player) ? distance / 2 : super.fall(player, distance, altMode);
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
    public void updateMovement(EntityPlayer player, int altMode)
    {
        TFMotionManager.motion(player, 60, 100, 20, 20, true, false, TFHelper.isInStealthMode(player));
    }

    @Override
    public boolean canUseNitro(EntityPlayer player, int altMode)
    {
        return !TFHelper.isInStealthMode(player);
    }

    @Override
    public boolean canShoot(EntityPlayer player, int altMode)
    {
        return TFHelper.isInStealthMode(player);
    }

    @Override
    public Item getShootItem(int altMode)
    {
        return TFItems.missile;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player, int altMode)
    {
        EntityMissile entityMissile = new EntityMissile(player.worldObj, player, TFConfig.allowMissileExplosions, TFHelper.isInStealthMode(player));
        return entityMissile;
    }

    @Override
    public int getShots(int altMode)
    {
        return 8;
    }

    @Override
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
        Random rand = new Random();
        
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -1.4, false);
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }

        for (int i = 0; i < 10; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -1.4, false);
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord, side.zCoord, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
        }
    }

    @Override
    public EnumTutorialType getTutorialType(int altMode)
    {
        return EnumTutorialType.CAR;
    }
}
