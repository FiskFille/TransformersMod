package fiskfille.tf.common.motion;

import static net.minecraft.block.material.Material.*;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFVectorHelper;

/**
 * @author FiskFille
 */
public class TFMotionManager
{
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
     */
    public static void motion(EntityPlayer player, double speedLimit, double nitroSpeedLimit, double sidewaysSpeedLimit, double reversingSpeedLimit, boolean canDrift, boolean canDriveOffroad, boolean canMoveSideways)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Random rand = new Random();

        // Controls
        boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
        boolean moveBack = mc.gameSettings.keyBindBack.getIsKeyPressed();
        boolean moveRight = mc.gameSettings.keyBindRight.getIsKeyPressed();
        boolean moveLeft = mc.gameSettings.keyBindLeft.getIsKeyPressed();

        // Variables
        boolean inStealthMode = TFHelper.isInStealthMode(player);
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();
        boolean driftPressed = TFKeyBinds.keyBindingBrake.getIsKeyPressed();

        double forwardVelocity = TFData.FORWARD_VELOCITY.get(player);
        double horizontalVelocity = TFData.HORIZONTAL_VELOCITY.get(player);
        double currentSpeedLimit = canMoveSideways ? sidewaysSpeedLimit : nitroPressed && TFData.NITRO.get(player) > 0 ? nitroSpeedLimit : speedLimit;

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
            Material[] offroadMaterials = {cactus, cake, clay, coral, craftedSnow, gourd, ground, ice, leaves, packedIce, plants, sand, snow, sponge, vine, web};
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
            drift(player, forwardVelocity, Math.toRadians(player.rotationYaw - player.renderYawOffset), true);
            forwardVelocity -= 3;
        }
        else
        {
            moveWithVelocity(player, forwardVelocity, horizontalVelocity);
        }

        TFData.FORWARD_VELOCITY.setWithoutNotify(player, forwardVelocity);
        TFData.HORIZONTAL_VELOCITY.setWithoutNotify(player, horizontalVelocity);
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
        boolean clientPlayer = player == mc.thePlayer;

        // Controls
        boolean moveForward = mc.gameSettings.keyBindForward.getIsKeyPressed();
        boolean nitroPressed = TFKeyBinds.keyBindingNitro.getIsKeyPressed() || mc.gameSettings.keyBindSprint.getIsKeyPressed();

        double forwardVelocity = TFData.FORWARD_VELOCITY.get(player);
        double currentSpeedLimit = nitroPressed && TFData.NITRO.get(player) > 0 ? nitroSpeedLimit : speedLimit;

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

        if (clientPlayer)
        {
            moveForward(player, forwardVelocity, true);

            if (player.isCollided && forwardVelocity > 60)
            {
                TFData.ALT_MODE.set(player, -1);
            }

            TFData.FORWARD_VELOCITY.setWithoutNotify(player, forwardVelocity);
        }
    }

    public static void drift(EntityPlayer player, double forwardVelocity, double driftAmount, boolean tireParticles)
    {
        moveWithVelocity(player, forwardVelocity, -driftAmount * 10);

        if (tireParticles)
        {
            for (int i = 0; i < 10; ++i)
            {
                Vec3 side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 5, -0.5F, false);
                player.worldObj.spawnParticle("reddust", side.xCoord, player.boundingBox.minY, side.zCoord, -1, 0, 0);
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
        Vec3 frontCoords = TFVectorHelper.getBackSideCoords(player, fromKMPH(horizontalVel), false, fromKMPH(forwardVel), false);
        player.motionX = frontCoords.xCoord - player.posX;
        player.motionZ = frontCoords.zCoord - player.posZ;
    }

    public static void moveForward(EntityPlayer player, double vel, boolean pitch)
    {
        Vec3 frontCoords = TFVectorHelper.getFrontCoords(player, fromKMPH(vel), pitch);
        player.motionX = frontCoords.xCoord - player.posX;

        if (pitch)
        {
            player.motionY = frontCoords.yCoord - player.boundingBox.minY;
        }

        player.motionZ = frontCoords.zCoord - player.posZ;
    }

    public static double fromKMPH(double speed)
    {
        return speed * 1000.0 / 60.0 / 60.0 / 20.0;
    }
}
