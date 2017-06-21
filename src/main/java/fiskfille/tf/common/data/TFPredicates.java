package fiskfille.tf.common.data;

import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.function.Predicate;

public class TFPredicates
{
    public static Predicate<EntityPlayer> and(final Predicate<EntityPlayer>... predicates)
    {
        return input ->
        {
            for (Predicate<EntityPlayer> p : predicates)
            {
                if (!p.test(input))
                {
                    return false;
                }
            }

            return true;
        };
    }

    public static Predicate<EntityPlayer> or(final Predicate<EntityPlayer>... predicates)
    {
        return input ->
        {
            for (Predicate<EntityPlayer> p : predicates)
            {
                if (p.test(input))
                {
                    return true;
                }
            }

            return false;
        };
    }

    public static Predicate<EntityPlayer> not(final Predicate<EntityPlayer> predicate)
    {
        return input -> !predicate.test(input);
    }

    public static Predicate<EntityPlayer> isTransformer()
    {
        return TFHelper::isTransformer;
    }

    public static Predicate<EntityPlayer> isTransformer(final Transformer transformer)
    {
        return input -> TFHelper.getTransformer(input) == transformer;
    }

    public static Predicate<EntityPlayer> isInVehicleMode()
    {
        return TFHelper::isFullyTransformed;
    }

    public static Predicate<EntityPlayer> hasStealthForce()
    {
        return input ->
        {
            Transformer transformer = TFHelper.getTransformer(input);
            return transformer != null && transformer.hasStealthForce(input, TFData.ALT_MODE.get(input));
        };
    }

    public static Predicate<EntityPlayer> isSneaking()
    {
        return Entity::isSneaking;
    }

    public static Predicate<EntityPlayer> isBacking()
    {
        return input -> input.moveForward < 0;
    }

    public static Predicate<EntityPlayer> isFlying()
    {
        return input -> input.capabilities.isFlying;
    }
}
