package fiskfille.tf.common.energon.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidTank;

public class EnergonTank extends FluidTank
{
    protected int usage;
    protected int last;

    public EnergonTank(int capacity)
    {
        super(capacity);
    }

    @Override
    public FluidTank readFromNBT(NBTTagCompound nbt)
    {
        FluidTank tank = super.readFromNBT(nbt);
        usage = nbt.getShort("Usage");
        last = getFluidAmount() - usage;
        return tank;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt = super.writeToNBT(nbt);
        nbt.setShort("Usage", (short) usage);
        return nbt;
    }

    public void setUsage(int usage)
    {
        this.usage = usage;
        this.last = getFluidAmount();
    }

    public int getUsage()
    {
        return usage;
    }

    public int calculateUsage()
    {
        int amount = getFluidAmount();
        usage = amount - last;
        last = amount;
        return usage;
    }
}
