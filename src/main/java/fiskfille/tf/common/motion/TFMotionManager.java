package fiskfille.tf.common.motion;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.particle.NitroParticleHandler;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFModelHelper;

/**
 * @author FiskFille
 */
public class TFMotionManager //TODO-TF Clean this up
{
    private static Map<EntityPlayer, VehicleMotion> transformedPlayerMap = new HashMap<EntityPlayer, VehicleMotion>();
    public static boolean prevNitro;
    
    public static void motionTruck(EntityPlayer player, double speedLimit, double nitroSpeedLimit, double stealthModeSpeedLimit, double reversingSpeedLimit)
    {
    	Minecraft mc = Minecraft.getMinecraft();
        
    	// Controls
    	boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
        boolean moveBack = mc.gameSettings.keyBindBack.getIsKeyPressed();
        boolean moveRight = mc.gameSettings.keyBindRight.getIsKeyPressed();
        boolean moveLeft = mc.gameSettings.keyBindLeft.getIsKeyPressed();
    	
        // Variables
        boolean inStealthMode = TFDataManager.isInStealthMode(player);
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();
        boolean driftPressed = TFKeyBinds.keyBindingBrake.getIsKeyPressed();
        int nitro = 0;
        double forwardVelocity = 0;
        double horizontalVelocity = 0;
        double currentSpeedLimit = 0;
        player.stepHeight = 1.0F;
        
        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
        
        if (transformedPlayer != null)
        {
            nitro = transformedPlayer.getNitro();
            forwardVelocity = transformedPlayer.getForwardVelocity();
            horizontalVelocity = transformedPlayer.getHorizontalVelocity();
            currentSpeedLimit = inStealthMode ? stealthModeSpeedLimit : (nitroPressed && nitro > 0 ? nitroSpeedLimit : speedLimit);
            
//            System.out.println(player.worldObj.getBlock((int)player.posX, (int)player.posY - 2, (int)player.posZ).getUnlocalizedName());
            
            if (moveForward)
            {
            	if (forwardVelocity < currentSpeedLimit)
            	{
            		forwardVelocity += inStealthMode ? 1 : ((currentSpeedLimit - forwardVelocity) / 20);
            	}
            }
            else if (!moveBack && forwardVelocity > 0)
            {
            	forwardVelocity -= 1;
            }
            
            if (moveBack)
            {
            	if (forwardVelocity > -reversingSpeedLimit)
            	{
            		forwardVelocity -= 2;
            	}
            }
            else if (!moveForward && forwardVelocity < 0)
            {
            	forwardVelocity += 1;
            }
            
            if (inStealthMode)
            {
            	if (moveRight)
                {
                	if (horizontalVelocity < stealthModeSpeedLimit)
                	{
                		horizontalVelocity += 1;
                	}
                }
            	else if (horizontalVelocity > 0)
            	{
            		horizontalVelocity -= 1;
            	}
            	
            	if (moveLeft)
                {
                	if (horizontalVelocity > -stealthModeSpeedLimit)
                	{
                		horizontalVelocity -= 1;
                	}
                }
            	else if (horizontalVelocity < 0)
            	{
            		horizontalVelocity += 1;
            	}
            }
            
        	player.renderYawOffset = player.rotationYawHead; // Makes sure the vehicle always faces forward, even when reversing.
        	
            if (!inStealthMode)
            {
                if (horizontalVelocity > 0)
            	{
            		horizontalVelocity -= 2;
            	}
                else if (horizontalVelocity < 0)
            	{
            		horizontalVelocity += 2;
            	}
            }
            if (horizontalVelocity != (int)horizontalVelocity)
            {
            	horizontalVelocity = (int)horizontalVelocity;
            }
            else if (horizontalVelocity > stealthModeSpeedLimit)
            {
            	horizontalVelocity = stealthModeSpeedLimit;
            }
            else if (horizontalVelocity < -stealthModeSpeedLimit)
            {
            	horizontalVelocity = -stealthModeSpeedLimit;
            }
            if (forwardVelocity < 1 && forwardVelocity > -1)
            {
            	forwardVelocity = 0.0D;
            }
            else if (forwardVelocity < -reversingSpeedLimit)
            {
            	forwardVelocity = -reversingSpeedLimit;
            }
            else if (forwardVelocity >= currentSpeedLimit)
            {
            	forwardVelocity -= 1;
            }
            
            TFMotionManager.moveWithVelocity(player, forwardVelocity, horizontalVelocity);
            
            transformedPlayer.setForwardVelocity(forwardVelocity);
            transformedPlayer.setHorizontalVelocity(horizontalVelocity);
        }
    }
    
    public static void drift(EntityPlayer player, double forwardVelocity, double driftAmount)
    {
    	
    }
    
    public static void moveWithVelocity(EntityPlayer player, double forwardVel, double horizontalVel)
    {
    	double d = forwardVel / 100 * 1.3898888673066752967899576429878D;
    	double d1 = horizontalVel / 100 * 1.3898888673066752967899576429878D;
    	Vec3 frontCoords = TFMotionManager.getBackSideCoords(player, d1, false, d, false);
        player.motionX = (frontCoords.xCoord - player.posX);
        player.motionZ = (frontCoords.zCoord - player.posZ);
    }
    
    public static void moveForward(EntityPlayer player, double vel, boolean pitch)
    {
        Vec3 frontCoords = TFMotionManager.getFrontCoords(player, vel, pitch);
        player.motionX = (frontCoords.xCoord - player.posX);
        
        if(pitch)
        {
            player.motionY = (frontCoords.yCoord - player.posY);
        }
        
        player.motionZ = (frontCoords.zCoord - player.posZ);
    }
    
    public static Vec3 getBackSideCoords(EntityPlayer player, double amount, boolean side, double backAmount, boolean pitch)
    {
        Vec3 front = getFrontCoords(player, backAmount, pitch).addVector(-player.posX, -player.posY, -player.posZ);
        return getSideCoords(player, amount, side).addVector(front.xCoord, front.yCoord, front.zCoord);
    }
    
    public static Vec3 getSideCoords(EntityPlayer player, double amount, int side)
    {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) + side);
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }
    
    public static Vec3 getSideCoords(EntityPlayer player, double amount, boolean side)
    {
        float f = 1.0F;
//        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f1 = 0;
        float f2 = (player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) + (side ? -90 : 90));
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }
    
    public static Vec3 getFrontCoords(EntityPlayer player, double amount, boolean pitch)
    {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        
        if (!pitch)
        {
            f1 = 0;
        }
        
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }
    
    public static Vec3 getFrontCoords(EntityPlayer player, float angle, double amount)
    {
        float f = 1.0F;
        float f1 = angle * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }
    
    public static Vec3 getAboveCoords(EntityPlayer player, float angle, double amount)
    {
        float f = 1.0F;
        float f1 = angle * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }
    
    public static Vec3 getVerticalCoords(EntityPlayer player, float angle, double amount)
    {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f - angle;
        float f2 = angle * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + (double) (player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight());
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }
    
    public static void setForwardVelocity(EntityPlayer player, double vel)
    {
        VehicleMotion transformedPlayer = transformedPlayerMap.get(player);
        
        if (transformedPlayer != null)
        {
            transformedPlayer.setForwardVelocity(vel);
        }
        else
        {
            transformedPlayerMap.put(player, new VehicleMotion().setHorizontalVelocity(vel));
        }
    }
    
    public static void setNitro(EntityPlayer player, int nitro)
    {
        VehicleMotion transformedPlayer = transformedPlayerMap.get(player);
        
        if (transformedPlayer != null)
        {
            transformedPlayer.setNitro(nitro);
        }
        else
        {
            transformedPlayerMap.put(player, new VehicleMotion().setNitro(nitro));
        }
    }
    
    public static void setHorizontalVelocity(EntityPlayer player, double vel)
    {
        VehicleMotion transformedPlayer = transformedPlayerMap.get(player);
        
        if (transformedPlayer != null)
        {
            transformedPlayer.setHorizontalVelocity(vel);
        }
        else
        {
            transformedPlayerMap.put(player, new VehicleMotion().setHorizontalVelocity(vel));
        }
    }
    
    public static VehicleMotion getTransformerPlayer(EntityPlayer player)
    {
        VehicleMotion vehicleMotion = transformedPlayerMap.get(player);
        
        if (vehicleMotion == null)
        {
            vehicleMotion = new VehicleMotion();
            transformedPlayerMap.put(player, vehicleMotion);
        }
        
        return vehicleMotion;
    }
}