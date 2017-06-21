package fiskfille.tf.common.transformer.base;

import fiskfille.tf.common.config.TFConfig;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.helper.TFVectorHelper;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.sound.TFSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

/**
 * @author gegy1000
 */
public abstract class TransformerTank extends Transformer
{
    public TransformerTank(int id, ResourceLocation identifier)
    {
        super(id, identifier);
    }

    @Override
    public boolean canZoom(EntityPlayer player)
    {
        return true;
    }

    @Override
    public SoundEvent getShootSound(int altMode)
    {
        return TFSounds.TANK_FIRE;
    }

    @Override
    public float fall(EntityPlayer player, float distance, int altMode)
    {
        return TFHelper.isFullyTransformed(player) ? 0 : super.fall(player, distance, altMode);
    }

    @Override
    public void updateMovement(EntityPlayer player, int altMode)
    {
        TFMotionManager.motion(player, 20, 30, 0, 20, false, true, false);
    }

    @Override
    public boolean canShoot(EntityPlayer player, int altMode)
    {
        return true;
    }

    @Override
    public Item getShootItem(int altMode)
    {
        return TFItems.TANK_SHELL;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player, int altMode)
    {
        return new EntityTankShell(player.world, player, TFConfig.projectiles.allowTankShellExplosions);
    }

    @Override
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
        Random rand = player.world.rand;

        for (int i = 0; i < 4; ++i)
        {
            Vec3d side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -0.6, false);
            player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, side.x, side.y, side.z, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }

    /*@Override
    public EnumTutorialType getTutorialType(int altMode)
    {
        return EnumTutorialType.TANK;
    }*/
}
