package fiskfille.tf.common.motion;

import static net.minecraft.block.material.Material.cactus;
import static net.minecraft.block.material.Material.cake;
import static net.minecraft.block.material.Material.clay;
import static net.minecraft.block.material.Material.coral;
import static net.minecraft.block.material.Material.craftedSnow;
import static net.minecraft.block.material.Material.gourd;
import static net.minecraft.block.material.Material.ground;
import static net.minecraft.block.material.Material.ice;
import static net.minecraft.block.material.Material.leaves;
import static net.minecraft.block.material.Material.packedIce;
import static net.minecraft.block.material.Material.plants;
import static net.minecraft.block.material.Material.sand;
import static net.minecraft.block.material.Material.snow;
import static net.minecraft.block.material.Material.sponge;
import static net.minecraft.block.material.Material.vine;
import static net.minecraft.block.material.Material.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.player.ModelBipedTF;
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
    
    /**
     * Method used to apply realistic vehicle motion to the player.
     * 
     * @param player The player the motion should be applied to
     * @param speedLimit How many km/h the vehicle can go moving normally 
     * @param nitroSpeedLimit How many km/h the vehicle can go while using nitro
     * @param sidewaysSpeedLimit How many km/h the vehicle can go while in Stealth Force mode
     * @param reversingSpeedLimit How many km/h the vehicle can go backwards while reversing
     * @param canDrift If the vehicle can drift
     * @param canDriveOffroad If the vehicle can drive off-road
     * @param canMoveSideways If the vehicle can move to the left or to the right
     * @param faceForward If the vehicle should face forward or not. Used by tanks to make the head and body rotations different.
     */
    public static void motion(EntityPlayer player, double speedLimit, double nitroSpeedLimit, double sidewaysSpeedLimit, double reversingSpeedLimit, boolean canDrift, boolean canDriveOffroad, boolean canMoveSideways, boolean faceForward)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Random rand = new Random();
        
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
            currentSpeedLimit = canMoveSideways ? sidewaysSpeedLimit : (nitroPressed && nitro > 0 ? nitroSpeedLimit : speedLimit);
            
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
            
            if (canMoveSideways)
            {
                if (moveRight)
                {
                    if (horizontalVelocity < sidewaysSpeedLimit)
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
                    if (horizontalVelocity > -sidewaysSpeedLimit)
                    {
                        horizontalVelocity -= 1;
                    }
                }
                else if (horizontalVelocity < 0)
                {
                    horizontalVelocity += 1;
                }
            }
            
            if (!canMoveSideways)
            {
                if (horizontalVelocity > 0)
                {
                    horizontalVelocity -= 2;
                }
                else if (horizontalVelocity < 0)
                {
                    horizontalVelocity += 2;
                }
                
                if (horizontalVelocity >= -1 || horizontalVelocity <= 1)
                {
                    horizontalVelocity = 0;
                }
            }
            if (horizontalVelocity != (int) horizontalVelocity)
            {
                horizontalVelocity = (int) horizontalVelocity;
            }
            else if (horizontalVelocity > sidewaysSpeedLimit)
            {
                horizontalVelocity = sidewaysSpeedLimit;
            }
            else if (horizontalVelocity < -sidewaysSpeedLimit)
            {
                horizontalVelocity = -sidewaysSpeedLimit;
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
            
            if (!canDriveOffroad)
            {
                Material[] offroadMaterials = { cactus, cake, clay, coral, craftedSnow, gourd, ground, ice, leaves, packedIce, plants, sand, snow, sponge, vine, web };
                Block block = player.worldObj.getBlock((int) player.posX, (int) player.posY - 2, (int) player.posZ - 1);
                boolean isDrivingOffroad = false;
                
                for (Material mat : offroadMaterials)
                {
                    if (block.getMaterial().equals(mat))
                    {
                        isDrivingOffroad = true;
                    }
                }
                
                if (isDrivingOffroad && (forwardVelocity > 5 || forwardVelocity < -5 || horizontalVelocity > 5 || horizontalVelocity < -5))
                {
                    double multiplier = (double) (forwardVelocity) / 20;
                    drift(player, forwardVelocity, (rand.nextDouble() - 0.5D) * multiplier, false);
                    forwardVelocity *= 0.95D;
                }
            }
            
            if (canDrift && driftPressed && player.onGround && forwardVelocity > 10 && !inStealthMode)
            {
                ModelBipedTF modelBipedMain = (ModelBipedTF) TFModelHelper.modelBipedMain;
                float f = modelBipedMain.bipedHead.rotateAngleY - (modelBipedMain.bipedBody.rotateAngleY - modelBipedMain.bipedHead.rotateAngleY);
                
                drift(player, forwardVelocity, f, true);
                forwardVelocity -= 3;
            }
            else
            {
                TFMotionManager.moveWithVelocity(player, forwardVelocity, horizontalVelocity);
                
                if (faceForward || forwardVelocity < 0)
                {
                    player.renderYawOffset = player.rotationYawHead; // Makes sure the vehicle always faces forward, even when reversing.
                }
            }
            
            transformedPlayer.setForwardVelocity(forwardVelocity);
            transformedPlayer.setHorizontalVelocity(horizontalVelocity);
        }
    }
    
    /**
     * Method used to apply realistic jet motion to the player.
     * 
     * @param player The player the motion should be applied to
     * @param speedLimit How many km/h the jet can go moving normally 
     * @param nitroSpeedLimit How many km/h the jet can go while using nitro
     * @param idlingSpeedLimit How many km/h the jet goes while idling 
     */
    public static void motionJet(EntityPlayer player, double speedLimit, double nitroSpeedLimit, double idlingSpeedLimit)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Random rand = new Random();
        
        // Controls
        boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
        
        // Variables
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();
        int nitro = 0;
        double forwardVelocity = 0;
        double currentSpeedLimit = 0;
        
        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);
        
        if (transformedPlayer != null)
        {
            nitro = transformedPlayer.getNitro();
            forwardVelocity = transformedPlayer.getForwardVelocity();
            currentSpeedLimit = nitroPressed && nitro > 0 ? nitroSpeedLimit : speedLimit;
            
            if (moveForward)
            {
                if (forwardVelocity < currentSpeedLimit)
                {
                    forwardVelocity += (currentSpeedLimit - forwardVelocity) / 10;
                }
            }
            else if (forwardVelocity > 0)
            {
                forwardVelocity -= 1;
            }
            
            if (forwardVelocity >= currentSpeedLimit)
            {
                forwardVelocity -= 1;
            }
            else if (forwardVelocity < idlingSpeedLimit)
            {
                forwardVelocity = idlingSpeedLimit;
            }
            
            if ((player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) != Blocks.air || player.worldObj.getBlock((int) player.posX, (int) player.posY - 2, (int) player.posZ) != Blocks.air || player.worldObj.getBlock((int) player.posX, (int) player.posY - 3, (int) player.posZ) != Blocks.air))
            {
                player.setPosition(player.posX, player.posY + 0.8, player.posZ);
            }
            
            TFMotionManager.moveForward(player, forwardVelocity, true);
            player.motionY -= 0.1;
            
            transformedPlayer.setForwardVelocity(forwardVelocity);
        }
    }
    
    public static void drift(EntityPlayer player, double forwardVelocity, double driftAmount, boolean tireParticles)
    {
        TFMotionManager.moveWithVelocity(player, forwardVelocity, -driftAmount * 10);
        
        if (tireParticles)
        {
            for (int i = 0; i < 10; ++i)
            {
                Vec3 side = NitroParticleHandler.getBackSideCoords(player, 0.15F, i <= 5, -0.5F, false);
                player.worldObj.spawnParticle("reddust", side.xCoord, player.posY - 1.5F, side.zCoord, -1, 0, 0);
            }
        }
        
        if (driftAmount > 0.2F)
        {
            player.rotationYaw += driftAmount * 2;
        }
        if (driftAmount < -0.2F)
        {
            player.rotationYaw -= -driftAmount * 2;
        }
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
        double d = vel / 100 * 1.3898888673066752967899576429878D;
        Vec3 frontCoords = TFMotionManager.getFrontCoords(player, d, pitch);
        player.motionX = (frontCoords.xCoord - player.posX);
        
        if (pitch)
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