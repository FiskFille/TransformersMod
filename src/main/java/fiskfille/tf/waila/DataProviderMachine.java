package fiskfille.tf.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.common.fluid.IFluidHandlerTF;
import fiskfille.tf.helper.TFFormatHelper;
import fiskfille.tf.helper.TFTileHelper;

public class DataProviderMachine implements IWailaDataProvider
{
    public String key;
    public Class targetClass;

    public DataProviderMachine(String s, Class c)
    {
        key = s;
        targetClass = c;
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemstack, List<String> list, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return list;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemstack, List<String> list, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        TileEntity tileentity = TFTileHelper.getTileBase(accessor.getTileEntity());

        if (tileentity.getClass() == targetClass && config.getConfig(key, true))
        {
            if (tileentity instanceof IEnergyContainer)
            {
                IEnergyContainer energyContainer = (IEnergyContainer) tileentity;
                EnergyStorage storage = new EnergyStorage(energyContainer.getMaxEnergy());
                storage.set(energyContainer.getEnergy());
                storage.setUsage(energyContainer.getEnergyUsage());
                
                list.addAll(TFFormatHelper.toString(storage.format()));
                
                if (tileentity instanceof IFluidHandlerTF)
                {
                    list.add(" ");
                }
            }
            
            if (tileentity instanceof IFluidHandlerTF)
            {
                IFluidHandlerTF fluidHandler = (IFluidHandlerTF) tileentity;
                FluidTankTF tank = getFluid(tileentity, fluidHandler);
                
                list.addAll(TFFormatHelper.toString(tank.format()));
            }
        }

        return list;
    }

    public FluidTankTF getFluid(TileEntity tile, IFluidHandlerTF fluidHandler)
    {
        return fluidHandler.getTank();
    }

    @Override
    public List<String> getWailaTail(ItemStack itemstack, List<String> list, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return list;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound nbttagcompound, World world, int x, int y, int z)
    {
        return null;
    }
}
