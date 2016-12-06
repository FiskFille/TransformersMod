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
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.helper.TFHelper;

public class DataProviderControlPanel implements IWailaDataProvider
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
        
        if (tileentity instanceof TileEntityControlPanel && config.getConfig("tf.control_panel", true))
        {
            TileEntityControlPanel tile = (TileEntityControlPanel) tileentity;
            
            for (int i = 0; i < tile.getSizeInventory(); ++i)
            {
                ItemStack itemstack1 = tile.getStackInSlot(i);
                
                if (itemstack1 != null)
                {
                    list.add(DataCore.get(itemstack1.getItemDamage()).getTranslatedName());
                }
            }
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
