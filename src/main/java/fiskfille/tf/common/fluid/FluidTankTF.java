package fiskfille.tf.common.fluid;

import static net.minecraft.util.EnumChatFormatting.GRAY;
import static net.minecraft.util.EnumChatFormatting.RED;
import static net.minecraft.util.EnumChatFormatting.YELLOW;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.helper.TFFormatHelper;
import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.network.ByteBufUtils;

public class FluidTankTF extends FluidTank
{
    protected int fluidUsage;
    protected int lastFluidAmount;

    public FluidTankTF(int capacity)
    {
        super(capacity);
    }
    
    public FluidTankTF(FluidStack stack, int capacity)
    {
        super(stack, capacity);
    }

    public FluidTankTF(Fluid fluid, int amount, int capacity)
    {
        super(fluid, amount, capacity);
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
    
    public List<IChatComponent> format()
    {
        List<IChatComponent> list = Lists.newArrayList();
        FluidStack stack = getFluid();

        if (stack != null && stack.amount > 0)
        {
            Map<String, Float> ratios = FluidEnergon.getRatios(stack);
            boolean flag = false;

            for (Map.Entry<String, Float> e : ratios.entrySet())
            {
                Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                int percentage = Math.round(e.getValue() * 100);

                if (percentage > 0)
                {
                    IChatComponent name = new ChatComponentText(energon.getTranslatedName()).setChatStyle(new ChatStyle().setColor(GRAY));
                    IChatComponent ratio = new ChatComponentText(percentage + "").setChatStyle(new ChatStyle().setColor(YELLOW));
                    
                    list.add(new ChatComponentTranslation("gui.energon_processor.content", name, ratio).setChatStyle(new ChatStyle().setColor(GRAY)));
                    flag = true;
                }
            }

            if (flag)
            {
                list.add(null);
            }
            else
            {
                list.add(new ChatComponentTranslation("gui.energon_processor.unidentified").setChatStyle(new ChatStyle().setColor(RED)));
            }
        }

        IChatComponent amount = new ChatComponentText(TFFormatHelper.formatNumber(getFluidAmount())).setChatStyle(new ChatStyle().setColor(YELLOW));
        IChatComponent capacity = new ChatComponentText(TFFormatHelper.formatNumber(getCapacity())).setChatStyle(new ChatStyle().setColor(YELLOW));
        
        list.add(new ChatComponentTranslation("gui.energon_processor.filled", amount, capacity).setChatStyle(new ChatStyle().setColor(GRAY)));
        
        return list;
    }
}
