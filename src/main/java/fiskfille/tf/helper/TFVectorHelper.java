package fiskfille.tf.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

/**
 * @author FiskFille
 */
public class TFVectorHelper
{
    public static Vec3 getBackSideCoords(EntityPlayer player, double amount, boolean side, double backAmount, boolean pitch)
    {
        Vec3 front = getFrontCoords(player, backAmount, pitch).addVector(-player.posX, -player.boundingBox.minY, -player.posZ);
        return getSideCoords(player, amount, side).addVector(front.xCoord, front.yCoord, front.zCoord);
    }

    public static Vec3 add(Vec3 vec31, Vec3 vec32)
    {
        return vec31.addVector(vec32.xCoord, vec32.yCoord, vec32.zCoord);
    }

    public static Vec3 getSideCoords(EntityPlayer player, double amount, int side)
    {
        float rotationPitch = player.rotationPitch;
        float rotationYaw = player.rotationYaw + side;
        double posX = player.posX;
        double posY = player.boundingBox.minY;
        double posZ = player.posZ;
        Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-rotationPitch * 0.017453292F);
        float f6 = MathHelper.sin(-rotationPitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }

    public static Vec3 getSideCoords(EntityPlayer player, double amount, boolean side)
    {
        float f1 = 0;
        float rotationYaw = player.rotationYaw + (side ? -90 : 90);
        double posX = player.posX;
        double posY = player.boundingBox.minY;
        double posZ = player.posZ;
        Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }

    public static Vec3 getSideCoords(EntityPlayer player, double amount, boolean side, boolean pitch)
    {
        float rotationPitch = player.rotationPitch;

        if (!pitch)
        {
            rotationPitch = 0;
        }

        float rotationYaw = player.rotationYaw + (side ? -90 : 90);
        double posX = player.posX;
        double posY = player.boundingBox.minY;
        double posZ = player.posZ;
        Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-rotationPitch * 0.017453292F);
        float f6 = MathHelper.sin(-rotationPitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }

    public static Vec3 getFrontCoords(EntityPlayer player, double amount, boolean pitch)
    {
        float rotationPitch = player.rotationPitch;
        float rotationYaw = player.rotationYaw;
        double posX = player.posX;
        double posY = player.boundingBox.minY;
        double posZ = player.posZ;
        Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);

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
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }

    public static Vec3 getFrontCoords(EntityPlayer player, float angle, double amount)
    {
        float rotationPitch = angle;
        float rotationYaw = player.rotationYaw;
        double posX = player.posX;
        double posY = player.boundingBox.minY;
        double posZ = player.posZ;
        Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-rotationPitch * 0.017453292F);
        float f6 = MathHelper.sin(-rotationPitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }

    public static Vec3 getAboveCoords(EntityPlayer player, float angle, double amount)
    {
        float rotationPitch = angle;
        float rotationYaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw);
        double d0 = player.posX;
        double d1 = player.boundingBox.minY;
        double d2 = player.posZ;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-rotationYaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-rotationPitch * 0.017453292F);
        float f6 = MathHelper.sin(-rotationPitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }

    public static Vec3 getVerticalCoords(EntityPlayer player, float angle, double amount)
    {
        float pitch = player.rotationPitch - angle;
        float yaw = angle;
        double d0 = player.posX;
        double d1 = player.boundingBox.minY;
        double d2 = player.posZ;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-pitch * 0.017453292F);
        float f6 = MathHelper.sin(-pitch * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        Vec3 vec31 = vec3.addVector(f7 * amount, f6 * amount, f8 * amount);
        return vec31;
    }
}
