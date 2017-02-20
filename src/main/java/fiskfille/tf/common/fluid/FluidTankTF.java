package fiskfille.tf.common.fluid;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import cpw.mods.fml.common.network.ByteBufUtils;

public class FluidTankTF extends FluidTank
{
    protected int fluidUsage;
    protected int lastFluidAmount;

    public FluidTankTF(int capacity)
    {
        super(capacity);
    }

    public FluidTankTF copy()
    {
        FluidTankTF tank = new FluidTankTF(getCapacity());

        if (getFluid() != null)
        {
            tank.setFluid(getFluid().copy());
        }

        tank.setUsage(getUsage());

        return tank;
    }

    public void toBytes(ByteBuf buf)
    {
        boolean hasFluid = fluid != null;
        buf.writeBoolean(hasFluid);

        if (hasFluid)
        {
            NBTTagCompound tag = fluid.writeToNBT(new NBTTagCompound());
            ByteBufUtils.writeTag(buf, tag);
        }

        buf.writeShort(fluidUsage);
    }

    public void fromBytes(ByteBuf buf)
    {
        if (buf.readBoolean())
        {
            NBTTagCompound tag = ByteBufUtils.readTag(buf);
            fluid = FluidStack.loadFluidStackFromNBT(tag);
        }

        fluidUsage = buf.readShort();
        lastFluidAmount = getFluidAmount() - fluidUsage;
    }

    @Override
    public FluidTank readFromNBT(NBTTagCompound nbt)
    {
        FluidTank tank = super.readFromNBT(nbt);
        fluidUsage = nbt.getShort("FluidUsage");
        lastFluidAmount = getFluidAmount() - fluidUsage;
        return tank;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt = super.writeToNBT(nbt);
        nbt.setShort("FluidUsage", (short) fluidUsage);
        return nbt;
    }

    public void setUsage(int usage)
    {
        fluidUsage = usage;
        lastFluidAmount = getFluidAmount();
    }

    public int getUsage()
    {
        return fluidUsage;
    }

    public int calculateUsage()
    {
        int amount = getFluidAmount();
        fluidUsage = amount - lastFluidAmount;
        lastFluidAmount = amount;

        return fluidUsage;
    }
}
