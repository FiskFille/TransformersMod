package fiskfille.tf.waila;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import fiskfille.tf.common.energon.power.IEnergyContainer;
import fiskfille.tf.helper.TFEnergyHelper;

public class DataProviderEnergyContainer implements IWailaDataProvider
{
    public String key;
    public Class targetClass;

    public DataProviderEnergyContainer(String s, Class c)
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
        int metadata = accessor.getMetadata();
        TileEntity tile = accessor.getWorld().getTileEntity(accessor.getPosition().blockX, getTileY(accessor.getPosition().blockY, metadata), accessor.getPosition().blockZ);

        if (tile instanceof IEnergyContainer && tile.getClass() == targetClass && config.getConfig(key, true))
        {
            IEnergyContainer energycontainer = (IEnergyContainer) tile;
            list.add(StatCollector.translateToLocalFormatted("gui.emb", TFEnergyHelper.formatNumber(energycontainer.getEnergy()), TFEnergyHelper.formatNumber(energycontainer.getMaxEnergy())));
        }

        return list;
    }

    public int getTileY(int y, int metadata)
    {
        return y;
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
