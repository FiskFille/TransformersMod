package fiskfille.tf.common.energon.power;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TargetReceiver
{
    private ChunkCoordinates coordinates;
    private Vec3 energyInputOffset;

    private TileEntity tile;
    private IEnergyReceiver receiver;

    public TargetReceiver(ChunkCoordinates coordinates, Vec3 energyInputOffset, TileEntity tile, IEnergyReceiver receiver)
    {
        this.coordinates = coordinates;
        this.energyInputOffset = energyInputOffset;
        this.tile = tile;
        this.receiver = receiver;
    }

    public TargetReceiver(ChunkCoordinates coordinates, Vec3 energyInputOffset)
    {
        this(coordinates, energyInputOffset, null, null);
    }

    public ChunkCoordinates getCoordinates()
    {
        return coordinates;
    }

    public Vec3 getEnergyInputOffset()
    {
        return energyInputOffset;
    }

    public TileEntity getTile()
    {
        return tile;
    }

    public IEnergyReceiver getReceiver()
    {
        return receiver;
    }

    public void load(World world)
    {
        TileEntity tile = world.getTileEntity(coordinates.posX, coordinates.posY, coordinates.posZ);

        if (tile instanceof IEnergyReceiver)
        {
            this.tile = tile;
            this.receiver = (IEnergyReceiver) tile;
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("X", coordinates.posX);
        compound.setInteger("Y", coordinates.posY);
        compound.setInteger("Z", coordinates.posZ);

        compound.setFloat("InputX", (float) energyInputOffset.xCoord);
        compound.setFloat("InputY", (float) energyInputOffset.yCoord);
        compound.setFloat("InputZ", (float) energyInputOffset.zCoord);
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(coordinates.posX);
        buf.writeInt(coordinates.posY);
        buf.writeInt(coordinates.posZ);
        buf.writeFloat((float) energyInputOffset.xCoord);
        buf.writeFloat((float) energyInputOffset.yCoord);
        buf.writeFloat((float) energyInputOffset.zCoord);
    }

    public static TargetReceiver readFromNBT(NBTTagCompound compound)
    {
        int x = compound.getInteger("X");
        int y = compound.getInteger("Y");
        int z = compound.getInteger("Z");

        ChunkCoordinates coordinates = new ChunkCoordinates(x, y, z);

        float inputX = compound.getFloat("InputX");
        float inputY = compound.getFloat("InputY");
        float inputZ = compound.getFloat("InputZ");
        Vec3 energyInputOffset = Vec3.createVectorHelper(inputX, inputY, inputZ);

        return new TargetReceiver(coordinates, energyInputOffset);
    }

    public static TargetReceiver fromBytes(ByteBuf buf)
    {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();

        ChunkCoordinates coordinates = new ChunkCoordinates(x, y, z);

        float inputX = buf.readFloat();
        float inputY = buf.readFloat();
        float inputZ = buf.readFloat();
        Vec3 energyInputOffset = Vec3.createVectorHelper(inputX, inputY, inputZ);

        return new TargetReceiver(coordinates, energyInputOffset);
    }

    @Override
    public int hashCode()
    {
        return coordinates.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof TargetReceiver && ((TargetReceiver) obj).coordinates.equals(coordinates);
    }
}
