package fiskfille.tf.common.data.tile;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fluids.FluidStack;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.helper.TFHelper;

public class TileDataTransmitter extends TileDataTransmitterBase
{
    public FluidTankTF tank;
    
    public TileDataTransmitter()
    {
    }
    
    public TileDataTransmitter(float maxEnergy, int fluidCapacity)
    {
        super(maxEnergy);
        tank = new FluidTankTF(fluidCapacity);
    }
    
    public TileDataTransmitter(TileDataTransmitter data)
    {
        super(data);
        tank = data.tank.copy();
    }
    
    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeInt(getCapacity());
        tank.toBytes(buf);
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        tank = new FluidTankTF(buf.readInt());
        tank.fromBytes(buf);
    }
    
    @Override
    public void serverTick()
    {
        tank.calculateUsage();
        super.serverTick();
    }
    
    @Override
    public void clientTick()
    {
        TFHelper.applyFluidUsage(tank);
        super.clientTick();
    }
    
    public FluidStack getFluid()
    {
        return tank.getFluid();
    }

    public void setFluid(FluidStack fluid)
    {
        tank.setFluid(fluid);
    }

    public int getFluidAmount()
    {
        return tank.getFluidAmount();
    }

    public int getUsage()
    {
        return tank.getUsage();
    }

    public int getCapacity()
    {
        return tank.getCapacity();
    }
    
    @Override
    public boolean matches(TileData tileData)
    {
        if (tileData instanceof TileDataTransmitter)
        {
            TileDataTransmitter data = (TileDataTransmitter) tileData;
            
            return super.matches(tileData) && getUsage() == data.getUsage() && FluidStack.areFluidStackTagsEqual(getFluid(), data.getFluid());
        }
        
        return false;
    }
}
