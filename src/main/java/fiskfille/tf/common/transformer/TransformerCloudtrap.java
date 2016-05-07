package fiskfille.tf.common.transformer;

import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.transformer.base.TransformerJet;
import fiskfille.tf.common.transformer.cloudtrap.CloudtrapJetpackManager;
import fiskfille.tf.helper.TFVectorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;

import java.util.Random;

/**
 * @author gegy1000
 */
public class TransformerCloudtrap extends TransformerJet
{
    public TransformerCloudtrap()
    {
        super("Cloudtrap");
    }

    @Override
    public Item getHelmet()
    {
        return TFItems.cloudtrapHelmet;
    }

    @Override
    public Item getChestplate()
    {
        return TFItems.cloudtrapChestplate;
    }

    @Override
    public Item getLeggings()
    {
        return TFItems.cloudtrapLeggings;
    }

    @Override
    public Item getBoots()
    {
        return TFItems.cloudtrapBoots;
    }

    @Override
    public void tick(EntityPlayer player, int timer)
    {
        if (timer > 10)
        {
            if (player.worldObj.isRemote)
            {
                CloudtrapJetpackManager.cloudtrapTick(player);
            }
        }

        if (timer == 20)
        {
            if (!player.capabilities.isFlying)
            {
                if (player.motionY < 0.0D)
                {
                    player.motionY *= 0.975;
                }
            }
        }
    }

    @Override
    public boolean onJump(EntityPlayer player)
    {
        return !player.isSneaking();
    }

    @Override
    public void updateMovement(EntityPlayer player, int altMode)
    {
        TFMotionManager.motionJet(player, 140, 200, 50);
    }

    @Override
    public void doNitroParticles(EntityPlayer player, int altMode)
    {
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.135F, i < 2, -2.5, true);
            Random rand = new Random();

            if (player != Minecraft.getMinecraft().thePlayer)
            {
                side.yCoord += 0.8F;
            }

            player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord - 0.4F, side.zCoord, (rand.nextFloat() - 0.5F) / 20, (rand.nextFloat() - 0.5F) / 20, (rand.nextFloat() - 0.5F) / 20);
        }
    }
}