package fiskfille.tf.common.transformer;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.TransformerJet;
import fiskfille.tf.common.transformer.cloudtrap.CloudtrapJetpackManager;

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
    }
    
    @Override
    public void doNitroParticles(EntityPlayer player)
    {
        for (int i = 0; i < 4; ++i)
        {
            Vec3 side = NitroParticleHandler.getBackSideCoords(player, 0.135F, i < 2, -1.5, true);
            Random rand = new Random();
            
            if (player != Minecraft.getMinecraft().thePlayer)
            {
                side.yCoord += 0.8F;
            }
            
            player.worldObj.spawnParticle("flame", side.xCoord, side.yCoord - 0.4F, side.zCoord, rand.nextFloat() / 20, -0.2F + rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }
}