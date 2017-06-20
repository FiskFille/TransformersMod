package fiskfille.tf.client.model.tools;

import net.minecraft.entity.Entity;

import java.util.function.Predicate;

public class AnimationModifier
{
    public Type type;
    public Predicate<Entity> predicate;
    public float factor;

    public AnimationModifier(Type type, Predicate<Entity> predicate, float factor)
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
