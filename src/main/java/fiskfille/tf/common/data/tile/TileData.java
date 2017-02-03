package fiskfille.tf.common.data.tile;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;

public abstract class TileData
{
    private DimensionalCoords coords;
    private boolean init = false;

    public TileData()
    {
    }

    public TileData(TileData data)
    {
        coords = DimensionalCoords.copy(data.coords);
    }

    public boolean isInitialized()
    {
        return init;
    }

    public <T extends TileEntity> T initialize(T tile)
    {
        if (!isInitialized())
        {
            init = true;
            coords = new DimensionalCoords(tile);
        }

        return tile;
    }

    public void toBytes(ByteBuf buf)
    {
        coords.toBytes(buf);
        buf.writeBoolean(init);
    }

    public void fromBytes(ByteBuf buf)
    {
        coords = new DimensionalCoords().fromBytes(buf);
        init = buf.readBoolean();
    }

    public DimensionalCoords getCoords()
    {
        return coords;
    }

    @Override
    public final boolean equals(Object obj)
    {
        if (obj instanceof TileData)
        {
            TileData data = (TileData) obj;

            return getCoords().equals(data.getCoords()) && matches(data);
        }

        return false;
    }

    public abstract boolean matches(TileData data);
}
