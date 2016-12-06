package fiskfille.tf.common.fluid;

import net.minecraftforge.fluids.IFluidHandler;

public interface IFluidHandlerTF extends IFluidHandler
{
    FluidTankTF getTank();

    void updateClientFluid();
}
