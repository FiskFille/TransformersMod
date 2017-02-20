package fiskfille.tf.common.data.tile;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.MessageSetTileData;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFTileHelper;

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
        init = data.init;
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

    public void serverTick()
    {
        if (!equals(TFTileHelper.getTileData(coords)))
        {
            TFNetworkManager.networkWrapper.sendToAll(new MessageSetTileData(this));
            System.out.println("Syncing " + getClass().getSimpleName() + " at " + coords);
        }

        TFTileHelper.putServerData(this);
    }

    public void clientTick()
    {
    }

    public void kill()
    {
        if (TFTileHelper.getTileData(coords) != null)
        {
            TFNetworkManager.networkWrapper.sendToAll(new MessageSetTileData(coords, null));
            TFTileHelper.putServerData(coords, null);
        }
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

    public abstract boolean matches(TileData tileData);
}
