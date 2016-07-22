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
import net.minecraft.util.Vec3;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.model.player.ModelBipedTF;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFModelHelper;
import fiskfille.tf.helper.TFVectorHelper;

/**
 * @author FiskFille
 */
public class TFMotionManager
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

        VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

        if (transformedPlayer != null)
        {
            nitro = transformedPlayer.getNitro();
            forwardVelocity = transformedPlayer.getForwardVelocity();
            horizontalVelocity = transformedPlayer.getHorizontalVelocity();
            currentSpeedLimit = canMoveSideways ? sidewaysSpeedLimit : nitroPressed && nitro > 0 ? nitroSpeedLimit : speedLimit;

            if (moveForward)
            {
                if (forwardVelocity < currentSpeedLimit)
                {
                    forwardVelocity += inStealthMode ? 1 : (currentSpeedLimit - forwardVelocity) / 20;
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
                    double multiplier = forwardVelocity / 20;
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
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();

        // Variables
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
            
            Block block = player.worldObj.getBlock((int)player.posX - 1, (int)(player.posY - 0.5F) - 2, (int)player.posZ - 1);
            int landingTimer = transformedPlayer.getLandingTimer();
            float f = (float)landingTimer / 20;
            boolean pitch = true;
            
//            if (block == Blocks.air)
            if (!player.onGround)
            {
                if (landingTimer < 20)
                {
                    ++landingTimer;
                }
            }
            else
            {
                if (landingTimer > 0)
                {
                    --landingTimer;
                }
            }
            
            if (landingTimer == 0)
            {
                pitch = player.rotationPitch < 0;
            }
            
            TFMotionManager.moveForward(player, forwardVelocity * Math.max(0.5F, f), pitch);
            player.motionY -= 0.1 * f;
            
            if (landingTimer < 5 && player.rotationPitch < 0 && moveForward)
            {
                player.motionY += 0.5F;
            }
            
            if (player.isCollidedHorizontally && forwardVelocity > 60)
            {
                landingTimer = 20;
                transformedPlayer.setLandingTimer(20);
                
                if (TFDataManager.setInVehicleMode(player, false))
                {
                    player.playSound(TransformersMod.modid + ":transform_robot", 1.0F, 1.0F);
                }
            }

            transformedPlayer.setForwardVelocity(forwardVelocity);
            transformedPlayer.setLandingTimer(landingTimer);
        }
    }

    public static void drift(EntityPlayer player, double forwardVelocity, double driftAmount, boolean tireParticles)
    {
        TFMotionManager.moveWithVelocity(player, forwardVelocity, -driftAmount * 10);

        if (tireParticles)
        {
            for (int i = 0; i < 10; ++i)
            {
                Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i <= 5, -0.5F, false);
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
        Vec3 frontCoords = TFVectorHelper.getBackSideCoords(player, d1, false, d, false);
        player.motionX = frontCoords.xCoord - player.posX;
        player.motionZ = frontCoords.zCoord - player.posZ;
    }

    public static void moveForward(EntityPlayer player, double vel, boolean pitch)
    {
        double d = vel / 100 * 1.3898888673066752967899576429878D;
        Vec3 frontCoords = TFVectorHelper.getFrontCoords(player, d, pitch);
        player.motionX = frontCoords.xCoord - player.posX;

        if (pitch)
        {
            player.motionY = frontCoords.yCoord - player.posY;
        }

        player.motionZ = frontCoords.zCoord - player.posZ;
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