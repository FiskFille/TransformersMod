package fiskfille.tf.common.energon.power;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TargetingTransmitter
{
    private ChunkCoordinates coordinates;

    private TileEntity tile;
    private IEnergyTransmitter transmitter;

    public TargetingTransmitter(ChunkCoordinates coordinates, TileEntity tile, IEnergyTransmitter transmitter)
    {
        this.coordinates = coordinates;
        this.tile = tile;
        this.transmitter = transmitter;
    }

    public TargetingTransmitter(ChunkCoordinates coordinates)
    {
        this(coordinates, null, null);
    }

    public ChunkCoordinates getCoordinates()
    {
        return coordinates;
    }

    public TileEntity getTile()
    {
        return tile;
    }

    public IEnergyTransmitter getTransmitter()
    {
        return transmitter;
    }

    public void load(World world)
    {
        TileEntity tile = world.getTileEntity(coordinates.posX, coordinates.posY, coordinates.posZ);

        if (tile instanceof IEnergyTransmitter)
        {
            this.tile = tile;
            this.transmitter = (IEnergyTransmitter) tile;
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("X", coordinates.posX);
        compound.setInteger("Y", coordinates.posY);
        compound.setInteger("Z", coordinates.posZ);
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(coordinates.posX);
        buf.writeInt(coordinates.posY);
        buf.writeInt(coordinates.posZ);
    }

    public static TargetingTransmitter readFromNBT(NBTTagCompound compound)
    {
        int x = compound.getInteger("X");
        int y = compound.getInteger("Y");
        int z = compound.getInteger("Z");

        return new TargetingTransmitter(new ChunkCoordinates(x, y, z));
    }

    public static TargetingTransmitter fromBytes(ByteBuf buf)
    {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();

        return new TargetingTransmitter(new ChunkCoordinates(x, y, z));
    }

    @Override
    public int hashCode()
    {
        return coordinates.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof TargetingTransmitter && ((TargetingTransmitter) obj).coordinates.equals(coordinates);
    }
}
