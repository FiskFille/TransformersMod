package fiskfille.tf.waila;

import java.util.List;
import java.util.Map;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.fluid.FluidEnergon;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFHelper;

public class DataProviderTransmitter implements IWailaDataProvider
{
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
        TileEntity tileentity = TFHelper.getTileBase(accessor.getTileEntity());
        
        if (tileentity instanceof TileEntityTransmitter && config.getConfig("tf.transmitter", true))
        {
            TileEntityTransmitter tile = (TileEntityTransmitter) tileentity;
            FluidStack stack = tile.getTank().getFluid();

            int liquidAmount = tile.getTank().getFluidAmount();
            int capacity = tile.getTank().getCapacity();

            if (stack != null && stack.amount > 0)
            {
                Map<String, Float> ratios = FluidEnergon.getRatios(stack);
                boolean flag = false;

                for (Map.Entry<String, Float> e : ratios.entrySet())
                {
                    Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
                    int percent = Math.round(e.getValue() * 100);

                    if (percent > 0)
                    {
                        list.add(StatCollector.translateToLocalFormatted("gui.energon_processor.content", energon.getTranslatedName(), percent));
                        flag = true;
                    }
                }

                if (flag)
                {
                    list.add(" ");
                }
                else
                {
                    list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("gui.energon_processor.unidentified"));
                }

                list.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", liquidAmount, capacity));
            }
            else
            {
                list.add(StatCollector.translateToLocalFormatted("gui.energon_processor.filled", liquidAmount, capacity));
            }

            list.add(StatCollector.translateToLocalFormatted("gui.emb.storage", TFEnergyHelper.formatNumber(tile.getEnergy()), TFEnergyHelper.formatNumber(tile.getMaxEnergy())));
        }

        return list;
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
