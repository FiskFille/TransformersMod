package fiskfille.tf.common.transformer.cloudtrap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.packet.PacketCloudtrapJetpack;
import fiskfille.tf.common.packet.base.TFPacketManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class CloudtrapJetpackManager
{
    public static Map<EntityPlayer, Boolean> cloudtrapJetpacking = new HashMap<EntityPlayer, Boolean>();

    private static boolean prevJetpacking;
    
    public static void cloudtrapTick(EntityPlayer player)
    {
        boolean isClientPlayer = TransformersMod.proxy.getPlayer() == player;
        boolean jetpacking = Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed();
        
        if (isClientPlayer)
        {
            if (prevJetpacking != jetpacking)
            {
                TFPacketManager.networkWrapper.sendToServer(new PacketCloudtrapJetpack(player, jetpacking));
                prevJetpacking = jetpacking;
            }
        }
        else
        {
            boolean playerJetpacking = false;
            
            Boolean playerJetpackingObj = cloudtrapJetpacking.get(player);
        
            if(playerJetpackingObj != null)
            {
                playerJetpacking = playerJetpackingObj;
            }
            else
            {
                cloudtrapJetpacking.put(player, false);
            }
            
            if (playerJetpacking)
            {
                for (int i = 0; i < 20; ++i)
                {
                    Random rand = new Random();
                    player.worldObj.spawnParticle("flame", player.posX, player.posY, player.posZ, rand.nextFloat() / 4 - 0.125F, -0.8F, rand.nextFloat() / 4 - 0.125F);
                }
            }
        }
        
        if (jetpacking)
        {
            player.motionY += 0.09F;
            
            if (isClientPlayer)
            {
                for (int i = 0; i < 20; ++i)
                {
                    Random rand = new Random();
                    player.worldObj.spawnParticle("flame", player.posX, player.posY - 1.5F, player.posZ, rand.nextFloat() / 4 - 0.125F, -0.8F, rand.nextFloat() / 4 - 0.125F);
                }
            }
        }
    }
}
