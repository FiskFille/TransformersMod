package fiskfille.tf.common.transformer.base;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.tutorial.EnumTutorialType;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFVectorHelper;

/**
 * @author gegy1000
 */
public abstract class TransformerTank extends Transformer
{
    public TransformerTank(String name)
    {
        super(name);
    }

    @Override
    public boolean canZoom(EntityPlayer player)
    {
        return true;
    }

    @Override
    public String getShootSound(int altMode)
    {
        return TransformersMod.modid + ":tankfire";
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
        return TFItems.tankShell;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player, int altMode)
    {
        return new EntityTankShell(player.worldObj, player, TFConfig.allowTankShellExplosions);
    }

    @Override
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
        Random rand = new Random();
        
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -0.6, false);
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }

    @Override
    public EnumTutorialType getTutorialType(int altMode)
    {
        return EnumTutorialType.TANK;
    }
}
