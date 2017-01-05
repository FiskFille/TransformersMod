package fiskfille.tf.asm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ASMHooks
{
    public static float getEntityScale(Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
//            return SHData.getFloat((EntityPlayer) entity, SHData.SCALE);
        }

        return 1.0F;
    }

    public static float getModifiedEntityScale(Entity entity)
    {
        if (getEntityScale(entity) > 1)
        {
            return getEntityScale(entity);
        }

        return 1.0F;
    }

    public static double getScaledSneakOffset(Entity entity, double d)
    {
        return d * ASMHooks.getEntityScale(entity);
    }
}
