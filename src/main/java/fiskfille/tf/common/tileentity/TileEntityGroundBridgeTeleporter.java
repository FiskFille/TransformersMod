package fiskfille.tf.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.NetworkRegistry;
import fiskfille.tf.common.network.MessageClosePortal;
import fiskfille.tf.common.network.base.TFNetworkManager;

public class TileEntityGroundBridgeTeleporter extends TileEntityTF
{
    public TileEntityControlPanel controlPanel;
    public boolean returnPortal = false;
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

    @Override
    public double getMaxRenderDistanceSquared()
    {
        return super.getMaxRenderDistanceSquared() * 2;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        controlPanel = new TileEntityControlPanel();
        controlPanel.readFromNBT(nbt.getCompoundTag("ControlPanel"));
        controlPanel.setWorldObj(worldObj);
        returnPortal = nbt.getBoolean("ReturnPortal");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        if (controlPanel != null)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            controlPanel.writeToNBT(nbttagcompound);
            nbt.setTag("ControlPanel", nbttagcompound);
        }

        nbt.setBoolean("ReturnPortal", returnPortal);
    }
}
