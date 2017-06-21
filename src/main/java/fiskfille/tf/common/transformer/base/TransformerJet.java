package fiskfille.tf.common.transformer.base;

import fiskfille.tf.common.helper.TFVectorHelper;
import fiskfille.tf.common.motion.TFMotionManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

/**
 * @author gegy1000
 */
public abstract class TransformerJet extends Transformer
{
    public TransformerJet(int id, ResourceLocation identifier)
    {
        super(id, identifier);
    }

    @Override
    public float fall(EntityPlayer player, float distance, int altMode)
    {
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

    /*@Override
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
        return new EntityMissile(player.worldObj, player, TFConfig.allowMissileExplosions, TFHelper.isInStealthMode(player));
    }*/

    @Override
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
        Random rand = player.world.rand;

        for (int i = 0; i < 4; ++i)
        {
            Vec3d side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -2, true);
            player.world.spawnParticle(EnumParticleTypes.FLAME, side.x, side.y + 0.3F, side.z, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }

    /*@Override
    public EnumTutorialType getTutorialType(int altMode)
    {
        return EnumTutorialType.JET;
    }*/
}
