package fiskfille.tf.common.energon.power;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;

public class NetworkEntry
{
    private DimensionalCoords coords;
    private TileEntity owner;

    protected NetworkEntry(DimensionalCoords coordinates, TileEntity tile)
    {
        coords = coordinates;
        owner = tile;
    }
    
    public NetworkEntry(TileEntity tile)
    {
        this(new DimensionalCoords(tile), tile);
    }

    public DimensionalCoords getCoords()
    {
        return coords;
    }

    public TileEntity getTile()
    {
        return owner;
    }

    public IEnergyTransmitter getTransmitter()
    {
        return (IEnergyTransmitter) getTile();
    }
    
    public IEnergyReceiver getReceiver()
    {
        return (IEnergyReceiver) getTile();
    }

    public void load(World world)
    {
        TileEntity tile = world.getTileEntity(coords.posX, coords.posY, coords.posZ);

        if (tile instanceof IEnergyTransmitter || tile instanceof IEnergyReceiver)
        {
            owner = tile;
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("X", coords.posX);
        compound.setInteger("Y", coords.posY);
        compound.setInteger("Z", coords.posZ);
        compound.setInteger("Dim", coords.dimension);
    }
    
    public static NetworkEntry readFromNBT(NBTTagCompound compound)
    {
        return new NetworkEntry(new DimensionalCoords(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z"), compound.getInteger("Dim")), null);
    }

    public void toBytes(ByteBuf buf)
    {
        coords.toBytes(buf);
    }

    public static NetworkEntry fromBytes(ByteBuf buf)
    {
        return new NetworkEntry(new DimensionalCoords().fromBytes(buf), null);
    }

    @Override
    public int hashCode()
    {
        return coords.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof NetworkEntry && ((NetworkEntry) obj).coords.equals(coords);
    }
}
