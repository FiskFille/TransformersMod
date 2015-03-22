package fiskfille.tf.client.particle;

import net.minecraft.client.particle.EntityFX;

public enum TFParticleType
{
	FLAMETHROWER_FLAME 
	{
		@Override
		public Class<? extends EntityFX> getParticle() 
		{
			return EntityTFFlameFX.class;
		}
	};
	
	public abstract Class<? extends EntityFX> getParticle();
}
