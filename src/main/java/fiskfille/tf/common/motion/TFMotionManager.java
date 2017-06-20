package fiskfille.tf.common.motion;

import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.helper.TFVectorHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

import static net.minecraft.block.material.Material.*;

/**
 * @author FiskFille
 */
public class TFMotionManager
{
    private static final Material[] OFFROAD_MATERIALS = { CACTUS, CAKE, CLAY, CORAL, CRAFTED_SNOW, GOURD, GROUND, ICE, LEAVES, PACKED_ICE, PLANTS, SAND, SNOW, SPONGE, VINE, WEB };

    /**
     * Method used to apply realistic vehicle motion to the player.
     *
     * @param player              The player the motion should be applied to
     * @param speedLimit          How many km/h the vehicle can go moving normally
     * @param nitroSpeedLimit     How many km/h the vehicle can go while using nitro
     * @param sidewaysSpeedLimit  How many km/h the vehicle can go while in Stealth Force mode
     * @param reversingSpeedLimit How many km/h the vehicle can go backwards while reversing
     * @param canDrift            If the vehicle can drift
     * @param canDriveOffroad     If the vehicle can drive off-road
     * @param canMoveSideways     If the vehicle can move to the left or to the right
     */
    public static void motion(EntityPlayer player, double speedLimit, double nitroSpeedLimit, double sidewaysSpeedLimit, double reversingSpeedLimit, boolean canDrift, boolean canDriveOffroad, boolean canMoveSideways)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Random rand = new Random();

        // Controls
        boolean moveForward = mc.gameSettings.keyBindForward.isKeyDown();
        boolean moveBack = mc.gameSettings.keyBindBack.isKeyDown();
        boolean moveRight = mc.gameSettings.keyBindRight.isKeyDown();
        boolean moveLeft = mc.gameSettings.keyBindLeft.isKeyDown();

        // Variables
        boolean inStealthMode = TFHelper.isInStealthMode(player);
        boolean nitroPressed = mc.gameSettings.keyBindSprint.isKeyDown();
        boolean driftPressed = TFKeyBinds.BRAKE.isKeyDown();

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
            IBlockState state = player.world.getBlockState(player.getPosition().add(0, -2, -1));
            boolean isDrivingOffroad = false;

            for (Material mat : OFFROAD_MATERIALS)
            {
                if (state.getMaterial().equals(mat))
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
     * @param player           The player the motion should be applied to
     * @param speedLimit       How many km/h the jet can go moving normally
     * @param nitroSpeedLimit  How many km/h the jet can go while using nitro
     * @param idlingSpeedLimit How many km/h the jet goes while idling
     */
    public static void motionJet(EntityPlayer player, double speedLimit, double nitroSpeedLimit, double idlingSpeedLimit)
    {
        Minecraft mc = Minecraft.getMinecraft();
        boolean clientPlayer = player == mc.player;

        // Controls
        boolean moveForward = mc.gameSettings.keyBindForward.isKeyDown();
        boolean nitroPressed = mc.gameSettings.keyBindSprint.isKeyDown();

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
                Vec3d side = TFVectorHelper.getBackSideCoords(player, 0.15F, i < 5, -0.5F, false);
                player.world.spawnParticle(EnumParticleTypes.REDSTONE, side.x, player.getEntityBoundingBox().minY, side.z, -1, 0, 0);
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
        Vec3d frontCoords = TFVectorHelper.getBackSideCoords(player, fromKMPH(horizontalVel), false, fromKMPH(forwardVel), false);
        player.motionX = frontCoords.x - player.posX;
        player.motionZ = frontCoords.z - player.posZ;
    }

    public static void moveForward(EntityPlayer player, double vel, boolean pitch)
    {
        Vec3d frontCoords = TFVectorHelper.getFrontCoords(player, fromKMPH(vel), pitch);
        player.motionX = frontCoords.x - player.posX;

        if (pitch)
        {
            player.motionY = frontCoords.y - player.getEntityBoundingBox().minY;
        }

        player.motionZ = frontCoords.z - player.posZ;
    }

    public static double fromKMPH(double speed)
    {
        return speed * 1000.0 / 60.0 / 60.0 / 20.0;
    }
}
