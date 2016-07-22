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
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFVectorHelper;

/**
 * @author gegy1000
 */
public abstract class TransformerTruck extends Transformer
{
    public TransformerTruck(String name)
    {
        super(name);
    }

    @Override
    public float fall(EntityPlayer player, float distance)
    {
        return TFDataManager.isInVehicleMode(player) ? distance / 4 : super.fall(player, distance);
    }

    @Override
    public boolean hasStealthForce(EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean canJumpAsVehicle(EntityPlayer player)
    {
        return TFDataManager.isInStealthMode(player);
    }

    @Override
    public float getCameraYOffset(EntityPlayer player)
    {
        return -1F;
    }

    @Override
    public boolean canUseNitro(EntityPlayer player)
    {
        return !TFDataManager.isInStealthMode(player);
    }

    @Override
    public void updateMovement(EntityPlayer player)
    {
        TFMotionManager.motion(player, 40, 60, 20, 10, false, true, TFDataManager.isInStealthMode(player), true);
    }

    @Override
    public boolean canShoot(EntityPlayer player)
    {
        return player.worldObj.isRemote ? TFDataManager.getStealthModeTimer(player) < 5 : TFDataManager.isInStealthMode(player);
    }

    @Override
    public Item getShootItem()
    {
        return TFItems.missile;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player)
    {
        //        EntityMissile entityMissile = new EntityMissile(player.worldObj, player, 3, TFConfig.allowMissileExplosions, TFDataManager.isInStealthMode(player));
        EntityMissile entityMissile = new EntityMissile(player.worldObj, player, TFConfig.allowMissileExplosions, TFDataManager.isInStealthMode(player));
        //        entityMissile.posY--;

        return entityMissile;
    }

    @Override
    public int getShots()
    {
        return 8;
    }

    @Override
    public void doNitroParticles(EntityPlayer player)
    {
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.25F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }

        for (int i = 0; i < 10; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -0.9, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.25F, side.zCoord, rand.nextFloat() / 10, rand.nextFloat() / 10 + 0.05F, rand.nextFloat() / 10);
        }
    }

    @Override
    public EnumTutorialType getTutorialType()
    {
        return EnumTutorialType.TRUCK;
    }
}
