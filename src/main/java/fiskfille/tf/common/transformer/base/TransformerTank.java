package fiskfille.tf.common.transformer.base;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.entity.EntityTankShell;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.network.MessageVehicleNitro;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.config.TFConfig;

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
        return -0.9F;
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
//    	TFMotionManager.motion(player, false, 20, 30, 10, 20, false, true, true);
    	TFMotionManager.motion(player, 20, 30, 0, 20, false, true, false, false);
    	
    	
    	
    	
    	
    	
    	
    	
    	
//        Minecraft minecraft = Minecraft.getMinecraft();
//        boolean moveForward = minecraft.gameSettings.keyBindForward.getIsKeyPressed();
//        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || minecraft.gameSettings.keyBindSprint.getIsKeyPressed();
//        
//        player.stepHeight = 1.0F;
//        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
//        
//        int nitro = 0;
//        double vel = 0;
//        
//        if (transformedPlayer != null)
//        {
//            nitro = transformedPlayer.getNitro();
//            vel = transformedPlayer.getForwardVelocity();
//            double increment = ((nitroPressed && nitro > 0 ? 0.18D : 0.035D) - vel) / 10 + 0.001D;
//            
//            if (moveForward && vel <= 1.0D)
//            {
//                vel += increment * 0.5F;
//            }
//            if (vel > 0.02D && !moveForward)
//            {
//                vel -= 0.02D;
//            }
//            if (vel < 0.01D && !moveForward)
//            {
//                vel = 0.0D;
//            }
//            
//            TFMotionManager.moveForward(player, vel, false);
//            
//            if (vel <= 0)
//            {
//                vel = 0;
//            }
//            if (vel > 0.2D)
//            {
//                vel = 0.2D;
//            }
//            if (vel < 0.02D && !moveForward)
//            {
//                vel = 0;
//            }
//            
//            transformedPlayer.setForwardVelocity(vel);
//            
//            if (player.isInWater())
//            {
//                player.motionY = -0.1F;
//            }
//        }
    }
    
    @Override
    public void tick(EntityPlayer player, int timer)
    {
    	if (TFDataManager.isInVehicleMode(player) && timer == 0)
    	{
    		IAttributeInstance entityAttribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
    		entityAttribute.setBaseValue(0.0D);

//    		if (!TFPlayerData.getData(player).stealthForce)
//    		{
//    			CommonEventHandler.prevMove = entityAttribute.getAttributeValue();
//    			entityAttribute.setBaseValue(0.0D);
//    		}
//    		else
//    		{
//    			if (CommonEventHandler.prevMove != 0)
//    			{
//    				entityAttribute.setBaseValue(CommonEventHandler.prevMove);
//    			}
//    		}
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
            Vec3 side = NitroParticleHandler.getBackSideCoords(player, 0.15F, i < 2, -0.6, false);
            Random rand = new Random();
            player.worldObj.spawnParticle("smoke", side.xCoord, side.yCoord + 0.3F, side.zCoord, rand.nextFloat() / 20, rand.nextFloat() / 20, rand.nextFloat() / 20);
        }
    }
}
