package fiskfille.tf.client.model;

import net.minecraft.entity.player.EntityPlayer;

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
		SPEED,
		DEGREE
	}
	
	public static abstract class Predicate
	{
		public static final Predicate SNEAKING = new Sneaking();
		public static final Predicate BACKING = new Backing();
		public static final Predicate FLYING = new Flying();
		
		public abstract boolean matches(EntityPlayer player);
		
		public static Predicate and(final Predicate... predicates)
		{
			return new Predicate()
			{
				@Override
				public boolean matches(EntityPlayer player)
				{
					for (Predicate p : predicates)
					{
						if (!p.matches(player))
						{
							return false;
						}
					}
					
					return true;
				}
			};
		}
		
		private static class Sneaking extends Predicate
		{
			@Override
			public boolean matches(EntityPlayer player)
			{
				return player.isSneaking();
			}
		}
		
		private static class Backing extends Predicate
		{
			@Override
			public boolean matches(EntityPlayer player)
			{
				return player.moveForward < 0;
			}
		}
		
		private static class Flying extends Predicate
		{
			@Override
			public boolean matches(EntityPlayer player)
			{
				return player.capabilities.isFlying;
			}
		}
	}
}
