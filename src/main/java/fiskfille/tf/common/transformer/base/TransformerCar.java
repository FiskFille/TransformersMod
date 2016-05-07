package fiskfille.tf.common.transformer.base;

import fiskfille.tf.client.tutorial.EnumTutorialType;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFVectorHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;

import java.util.Random;

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
        return TFDataManager.isTransformed(player) ? distance / 2 : super.fall(player, distance, altMode);
    }

    @Override
    public void tick(EntityPlayer player, int timer)
    {
        boolean vehicle = TFDataManager.isTransformed(player);
        IAttributeInstance entityAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);

        if (vehicle && timer == 0)
        {
            entityAttribute.setBaseValue(0.0D);
        }
        else if (timer == 20)
        {
            entityAttribute.setBaseValue(0.1D);
        }
    }

    @Override
    public boolean hasStealthForce(EntityPlayer player, int altMode)
    {
        return true;
    }

    @Override
    public boolean canJumpAsVehicle(EntityPlayer player, int altMode)
    {
        return TFDataManager.isInStealthMode(player);
    }

    @Override
    public float getCameraYOffset(EntityPlayer player, int altMode)
    {
        return -1.1F;
    }

    @Override
    public void updateMovement(EntityPlayer player, int altMode)
    {
        TFMotionManager.motion(player, 60, 100, 20, 20, true, false, TFDataManager.isInStealthMode(player), true);
    }

    @Override
    public boolean canUseNitro(EntityPlayer player, int altMode)
    {
        return !TFDataManager.isInStealthMode(player);
    }

    @Override
    public boolean canShoot(EntityPlayer player, int altMode)
    {
        return player.worldObj.isRemote ? TFDataManager.getStealthModeTimer(player) < 5 : TFDataManager.isInStealthMode(player);
    }

    @Override
    public Item getShootItem(int altMode)
    {
        return TFItems.missile;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player, int altMode)
    {
        EntityMissile entityMissile = new EntityMissile(player.worldObj, player, TFConfig.allowMissileExplosions, TFDataManager.isInStealthMode(player));
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
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -1.4, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord - 1.6F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }

        for (int i = 0; i < 10; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -1.4, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord - 1.6F, side.zCoord, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
        }
    }

    @Override
    public EnumTutorialType getTutorialType(int altMode)
    {
        return EnumTutorialType.CAR;
    }
}
