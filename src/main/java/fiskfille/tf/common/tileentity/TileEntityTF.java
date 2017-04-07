package fiskfille.tf.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import fiskfille.tf.helper.TFTileHelper;

public abstract class TileEntityTF extends TileEntity
{
    @Override
    public final void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        readCustomNBT(nbt);
    }

    @Override
    public final void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        writeCustomNBT(nbt);
    }

    public void markBlockForUpdate()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        writeCustomNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readCustomNBT(pkt.func_148857_g());
    }
    
    @Override
    public void markDirty()
    {
        TileEntity tile = TFTileHelper.getTileBase(this);
        
        if (tile != null)
        {
            worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        }
    }

    protected abstract void writeCustomNBT(NBTTagCompound nbt);

    protected abstract void readCustomNBT(NBTTagCompound nbt);
}
