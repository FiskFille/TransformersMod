package fiskfille.tf.client.model.tools;

import net.minecraft.entity.player.EntityPlayer;

import java.util.function.Predicate;

public class AnimationModifier
{
    public Type type;
    public Predicate<EntityPlayer> predicate;
    public float factor;

    public AnimationModifier(Type type, Predicate<EntityPlayer> predicate, float factor)
    {
        this.type = type;
        this.predicate = predicate;
        this.factor = factor;
    }

    public enum Type
    {
        SPEED,
        DEGREE
    }
}
