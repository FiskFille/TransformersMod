package fiskfille.tf.common.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import fiskfille.tf.helper.TFRenderHelper;

public class TFFluids
{
	public static Fluid energon;
	
	public static void register()
	{
		energon = new FluidEnergon("energon").setIcons(TFRenderHelper.energonStillIcon, TFRenderHelper.energonFlowingIcon);
		
		
		FluidRegistry.registerFluid(energon);
	}
}
