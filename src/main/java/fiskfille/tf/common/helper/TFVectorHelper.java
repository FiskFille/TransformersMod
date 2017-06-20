package fiskfille.tf.common.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * @author FiskFille
 */
public class TFVectorHelper
{
    public static Vec3d getBackSides(EntityPlayer player, double amount, boolean side, double backAmount, boolean pitch)
    {
        Vec3d front = getFronts(player, backAmount, pitch).addVector(-player.posX, -player.getEntityBoundingBox().minY, -player.posZ);
        return getSides(player, amount, side).addVector(front.x, front.y, front.z);
    }

    public static Vec3d add(Vec3d left, Vec3d right)
    {
        return left.addVector(right.x, right.y, right.z);
    }

    public static Vec3d getSides(EntityPlayer player, double amount, int side)
    {
        float rotationPitch = player.rotationPitch;
        float rotationYaw = player.rotationYaw + side;
        double posX = player.posX;
        double posY = player.getEntityBoundingBox().minY;
        double posZ = player.posZ;
        Vec3d pos = new Vec3d(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-rotationPitch * 0.017453292F);
        float f6 = MathHelper.sin(-rotationPitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        return pos.addVector(f7 * amount, f6 * amount, f8 * amount);
    }

    public static Vec3d getSides(EntityPlayer player, double amount, boolean side)
    {
        float f1 = 0;
        float rotationYaw = player.rotationYaw + (side ? -90 : 90);
        double posX = player.posX;
        double posY = player.getEntityBoundingBox().minY;
        double posZ = player.posZ;
        Vec3d pos = new Vec3d(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        return pos.addVector(f7 * amount, f6 * amount, f8 * amount);
    }

    public static Vec3d getSides(EntityPlayer player, double amount, boolean side, boolean pitch)
    {
        float rotationPitch = player.rotationPitch;

        if (!pitch)
        {
            rotationPitch = 0;
        }

        float rotationYaw = player.rotationYaw + (side ? -90 : 90);
        double posX = player.posX;
        double posY = player.getEntityBoundingBox().minY;
        double posZ = player.posZ;
        Vec3d pos = new Vec3d(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-rotationPitch * 0.017453292F);
        float f6 = MathHelper.sin(-rotationPitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        return pos.addVector(f7 * amount, f6 * amount, f8 * amount);
    }

    public static Vec3d getFronts(EntityPlayer player, double amount, boolean pitch)
    {
        float rotationPitch = player.rotationPitch;
        float rotationYaw = player.rotationYaw;
        double posX = player.posX;
        double posY = player.getEntityBoundingBox().minY;
        double posZ = player.posZ;
        Vec3d pos = new Vec3d(posX, posY, posZ);

        if (!pitch)
        {
            rotationPitch = 0;
        }

        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-rotationPitch * 0.017453292F);
        float f6 = MathHelper.sin(-rotationPitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        return pos.addVector(f7 * amount, f6 * amount, f8 * amount);
    }

    public static Vec3d getFronts(EntityPlayer player, float angle, double amount)
    {
        float rotationYaw = player.rotationYaw;
        double posX = player.posX;
        double posY = player.getEntityBoundingBox().minY;
        double posZ = player.posZ;
        Vec3d pos = new Vec3d(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-angle * 0.017453292F);
        float f6 = MathHelper.sin(-angle * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        return pos.addVector(f7 * amount, f6 * amount, f8 * amount);
    }

    public static Vec3d getAboves(EntityPlayer player, float angle, double amount)
    {
        float rotationYaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw);
        double posX = player.posX;
        double posY = player.getEntityBoundingBox().minY;
        double posZ = player.posZ;
        Vec3d pos = new Vec3d(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-angle * 0.017453292F);
        float f6 = MathHelper.sin(-angle * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        return pos.addVector(f7 * amount, f6 * amount, f8 * amount);
    }

    public static Vec3d getVerticals(EntityPlayer player, float angle, double amount)
    {
        float pitch = player.rotationPitch - angle;
        double posX = player.posX;
        double posY = player.getEntityBoundingBox().minY;
        double posZ = player.posZ;
        Vec3d pos = new Vec3d(posX, posY, posZ);
        float f3 = MathHelper.cos(-angle * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-angle * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-pitch * 0.017453292F);
        float f6 = MathHelper.sin(-pitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        return pos.addVector(f7 * amount, f6 * amount, f8 * amount);
    }
}
