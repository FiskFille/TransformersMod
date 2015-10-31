package fiskfille.tf.common.transformer.base;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.tutorial.EnumTutorialType;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.config.TFConfig;
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

    public boolean canZoom(EntityPlayer player)
    {
        return true;
    }

    public float getVehicleCameraYOffset(EntityPlayer player)
    {
        return -1.0F;
    }

    @Override
    public String getShootSound()
    {
        return TransformersMod.modid + ":tankfire";
    }

    @Override
    public float fall(EntityPlayer player, float distance)
    {
        return TFDataManager.isInVehicleMode(player) ? 0 : super.fall(player, distance);
    }

    @Override
    public void updateMovement(EntityPlayer player)
    {
        TFMotionManager.motion(player, 20, 30, 0, 20, false, true, false, false);
    }

    @Override
    public void tick(EntityPlayer player, int timer)
    {
        IAttributeInstance entityAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);

        if (TFDataManager.isInVehicleMode(player) && timer == 0)
        {
            entityAttribute.setBaseValue(0.0D);
        }
        else if (timer == 20)
        {
            entityAttribute.setBaseValue(0.1D);
        }
    }

    @Override
    public boolean canShoot(EntityPlayer player)
    {
        return true;
    }

    @Override
    public Item getShootItem()
    {
        return TFItems.tankShell;
    }

    @Override
    public Entity getShootEntity(EntityPlayer player)
    {
        return new EntityTankShell(player.worldObj, player, TFConfig.allowTankShellExplosions);
    }

    @Override
    public void doNitroParticles(EntityPlayer player)
    {
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 2, -0.6, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.3F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }

    @Override
    public EnumTutorialType getTutorialType()
    {
        return EnumTutorialType.TANK;
    }
}
