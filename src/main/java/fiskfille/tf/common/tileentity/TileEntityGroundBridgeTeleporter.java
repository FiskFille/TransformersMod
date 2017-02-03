package fiskfille.tf.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;
import cpw.mods.fml.common.network.NetworkRegistry;
import fiskfille.tf.common.block.BlockGroundBridgeTeleporter;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageClosePortal;
import fiskfille.tf.common.network.base.TFNetworkManager;

public class TileEntityGroundBridgeTeleporter extends TileEntityTF
{
    public DimensionalCoords controlPanel;
    public int lastUpdate;
    public int ticks;
    public boolean clientClosing;

    @Override
    public void updateEntity()
    {
        if (!worldObj.isRemote)
        {
            if (lastUpdate >= 12)
            {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                markBlockForUpdate();
            }
            else if (lastUpdate == 6)
            {
                TFNetworkManager.networkWrapper.sendToAllAround(new MessageClosePortal(xCoord, yCoord, zCoord), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 8192));
            }
        }

        if (!worldObj.isRemote || clientClosing)
        {
            ++lastUpdate;
        }

        ++ticks;
    }

    public boolean isReturnPortal(int metadata)
    {
        return BlockGroundBridgeTeleporter.isReturnPortal(metadata);
    }

    public boolean returnPortal()
    {
        return isReturnPortal(getBlockMetadata());
    }

    @Override
    public double getMaxRenderDistanceSquared()
    {
        return super.getMaxRenderDistanceSquared() * 2;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        if (nbt.hasKey("ControlPanel", NBT.TAG_COMPOUND))
        {
            NBTTagCompound nbttagcompound = nbt.getCompoundTag("ControlPanel");
            controlPanel = new DimensionalCoords(nbttagcompound.getInteger("x"), nbttagcompound.getInteger("y"), nbttagcompound.getInteger("z"), nbttagcompound.getInteger("dim"));
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        if (controlPanel != null)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setInteger("x", controlPanel.posX);
            nbttagcompound.setInteger("y", controlPanel.posY);
            nbttagcompound.setInteger("z", controlPanel.posZ);
            nbttagcompound.setInteger("dim", controlPanel.dimension);
            nbt.setTag("ControlPanel", nbttagcompound);
        }
    }
}
