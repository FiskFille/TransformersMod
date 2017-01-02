package fiskfille.tf.waila;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.helper.TFHelper;

public class DataProviderEnergonTank extends DataProviderMachine
{
    public FluidTank fluidTank = new FluidTank(TFFluids.energon, 0, 0);
    
    public DataProviderEnergonTank(String s)
    {
        super(s, TileEntityEnergonTank.class);
    }
    
    public void updateFluids(TileEntity tile)
    {
        FluidStack stack = new FluidStack(TFFluids.energon, 0);
        TileEntity tileBase = TFHelper.getTileBase(tile);
        int y = tileBase.yCoord;
        int capacity = 0;

        while (y < tile.getWorldObj().getHeight() && TFHelper.getTileBase(tile.getWorldObj().getTileEntity(tile.xCoord, y, tile.zCoord)) == tileBase && tile.getWorldObj().getTileEntity(tile.xCoord, y, tile.zCoord) instanceof IFluidHandlerTF)
        {
            IFluidHandlerTF fluidHandler = (IFluidHandlerTF) tile.getWorldObj().getTileEntity(tile.xCoord, y, tile.zCoord);
            capacity += fluidHandler.getTank().getCapacity();

            if (fluidHandler.getTank().getFluid() != null)
            {
                FluidEnergon.merge(stack, fluidHandler.getTank().getFluid(), fluidHandler.getTank().getFluidAmount());
                stack.amount += fluidHandler.getTank().getFluidAmount();
            }

            ++y;
        }
        
        fluidTank = new FluidTank(stack, capacity);
    }

    @Override
    public FluidStack getFluid(TileEntity tile, IFluidHandlerTF fluidHandler)
    {
        updateFluids(tile);
        return fluidTank.getFluid();
    }

    @Override
    public int getFluidAmount(TileEntity tile, IFluidHandlerTF fluidHandler)
    {
        return fluidTank.getFluidAmount();
    }

    @Override
    public int getFluidCapacity(TileEntity tile, IFluidHandlerTF fluidHandler)
    {
        return fluidTank.getCapacity();
    }
}
