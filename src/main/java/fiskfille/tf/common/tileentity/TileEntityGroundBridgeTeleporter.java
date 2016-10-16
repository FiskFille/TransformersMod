package fiskfille.tf.common.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry;
import fiskfille.tf.common.network.MessageClosePortal;
import fiskfille.tf.common.network.base.TFNetworkManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGroundBridgeTeleporter extends TileEntity
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

    public void markBlockForUpdate()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        controlPanel = new TileEntityControlPanel();
        controlPanel.readFromNBT(nbt.getCompoundTag("ControlPanel"));
        controlPanel.setWorldObj(worldObj);
        returnPortal = nbt.getBoolean("ReturnPortal");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        NBTTagCompound controlPanel = new NBTTagCompound();
        this.controlPanel.writeToNBT(controlPanel);
        nbt.setTag("ControlPanel", controlPanel);
        nbt.setBoolean("ReturnPortal", returnPortal);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        writeToNBT(syncData);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
    }
}
