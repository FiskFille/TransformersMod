package fiskfille.tf.common.transformer.base;

import fiskfille.tf.client.tutorial.EnumTutorialType;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.entity.EntityMissile;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFVectorHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;

import java.util.Random;

/**
 * @author gegy1000
 */
public abstract class TransformerJet extends Transformer
{
    public TransformerJet(String name)
    {
        super(name);
    }

    @Override
    public float fall(EntityPlayer player, float distance, int altMode)
    {
        return 0;
    }

    @Override
    public float getCameraYOffset(EntityPlayer player, int altMode)
    {
        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

        if (transformedPlayer != null)
        {
            float f = (float) transformedPlayer.getLandingTimer() / 20;
            return -1 * (1 - f);
        }

        return 0;
    }

    @Override
    public float getVehicleCameraYOffset(EntityPlayer player, int altMode)
    {
        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

        if (transformedPlayer != null)
        {
            float f = (float) transformedPlayer.getLandingTimer() / 20;
            return -1 * (1 - f);
        }

        return 0;
    }

    @Override
    public float getThirdPersonDistance(EntityPlayer player, int altMode)
    {
        return 4.0F;
    }

    @Override
    public void updateMovement(EntityPlayer player, int altMode)
    {
        TFMotionManager.motionJet(player, 100, 140, 20);
    }

    @Override
    public boolean canShoot(EntityPlayer player, int altMode)
    {
        return true;
    }

    @Override
    public Item getShootItem(int altMode)
    {
        return TFItems.missile;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player, int altMode)
    {
        return new EntityMissile(player.worldObj, player, TFConfig.allowMissileExplosions, TFDataManager.isInStealthMode(player));
    }

    @Override
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -2, true);
            Random rand = new Random();

            player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord - 0.4F, side.zCoord, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }

    @Override
    public EnumTutorialType getTutorialType(int altMode)
    {
        return EnumTutorialType.JET;
    }
}
