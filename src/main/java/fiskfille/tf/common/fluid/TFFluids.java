package fiskfille.tf.common.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TFFluids
{
	public static Fluid energon;
	
	public static void register()
	{
		energon = new FluidEnergon("energon");
		
		
		FluidRegistry.registerFluid(energon);
	}
}
