package fiskfille.tf.client.particle;

import net.minecraft.client.particle.EntityFX;

public enum TFParticleType
{
    FLAMETHROWER_FLAME(EntityTFFlameFX.class);

    public Class<? extends EntityFX> particleClass;

    TFParticleType(Class<? extends EntityFX> clazz)
    {
        particleClass = clazz;
    }
}
