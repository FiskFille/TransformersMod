package fiskfille.tf.client.model;

import com.google.common.base.Predicate;

public class AnimationModifier
{
    public Type type;
    public Predicate predicate;
    public float factor;

    public AnimationModifier(Type t, Predicate p, float f)
    {
        type = t;
        predicate = p;
        factor = f;
    }

    public enum Type
    {
        SPEED, DEGREE
    }
}
