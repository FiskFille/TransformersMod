package fiskfille.tf.misc;

import net.minecraft.entity.player.EntityPlayer;

public enum VehicleType
{
	TANK
	{
		public void performMotion(EntityPlayer player)
		{
			TFMotionManager.motionTank(player);
		}
	}, 
	CAR
	{
		public void performMotion(EntityPlayer player)
		{
			TFMotionManager.motionCar(player);
		}
	}, 
	JET
	{
		public void performMotion(EntityPlayer player)
		{
			TFMotionManager.motionJet(player);
		}
	};
	
	public void performMotion(EntityPlayer player)
	{
		
	}
}
