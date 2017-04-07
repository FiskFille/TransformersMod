package fiskfille.tf.common.data;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.base.Predicate;

import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class TFPredicates
{
    public static Predicate<EntityPlayer> and(final Predicate... predicates)
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                for (Predicate p : predicates)
                {
                    if (!p.apply(input))
                    {
                        return false;
                    }
                }

                return true;
            }
        };
    }

    public static Predicate<EntityPlayer> or(final Predicate... predicates)
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                for (Predicate p : predicates)
                {
                    if (p.apply(input))
                    {
                        return true;
                    }
                }

                return false;
            }
        };
    }

    public static Predicate<EntityPlayer> not(final Predicate p)
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return !p.apply(input);
            }
        };
    }

    public static Predicate<EntityPlayer> isTransformer()
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return TFHelper.isTransformer(input);
            }
        };
    }

    public static Predicate<EntityPlayer> isTransformer(final Transformer transformer)
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return TFHelper.getTransformer(input) == transformer;
            }
        };
    }

    public static Predicate<EntityPlayer> isInVehicleMode()
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return TFHelper.isFullyTransformed(input);
            }
        };
    }

    public static Predicate<EntityPlayer> hasStealthForce()
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                Transformer transformer = TFHelper.getTransformer(input);
                return transformer != null && transformer.hasStealthForce(input, TFData.ALT_MODE.get(input));
            }
        };
    }

    public static Predicate<EntityPlayer> isSneaking()
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return input.isSneaking();
            }
        };
    }

    public static Predicate<EntityPlayer> isBacking()
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return input.moveForward < 0;
            }
        };
    }

    public static Predicate<EntityPlayer> isFlying()
    {
        return new Predicate<EntityPlayer>()
        {
            @Override
            public boolean apply(EntityPlayer input)
            {
                return input.capabilities.isFlying;
            }
        };
    }
}
