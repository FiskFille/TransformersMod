package fiskfille.tf.waila;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.common.fluid.TFFluids;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.helper.TFTileHelper;

public class DataProviderEnergonTank extends DataProviderMachine
{
    public FluidTank fluidTank = new FluidTank(TFFluids.energon, 0, 0);

    public DataProviderEnergonTank(String s)
    {
        super(s, TileEntityEnergonTank.class);
    }

    public void updateFluids(TileEntity tile)
    {   
        TileEntity tileBase = TFTileHelper.getTileBase(tile);
        FluidStack stack = null;
        int y = tileBase.yCoord;
        int capacity = 0;

        while (y < tile.getWorldObj().getHeight() && TFTileHelper.getTileBase(tile.getWorldObj().getTileEntity(tile.xCoord, y, tile.zCoord)) == tileBase && tile.getWorldObj().getTileEntity(tile.xCoord, y, tile.zCoord) instanceof IFluidHandlerTF)
        {
            IFluidHandlerTF fluidHandler = (IFluidHandlerTF) tile.getWorldObj().getTileEntity(tile.xCoord, y, tile.zCoord);
            FluidTank tank = fluidHandler.getTank();
            
            if (stack == null && tank.getFluid() != null)
            {
                stack = tank.getFluid().copy();
                stack.amount = 0;
            }
            
            if (stack != null)
            {
                stack.amount += tank.getFluidAmount();
            }
            
            capacity += tank.getCapacity();
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
