package fiskfille.tf.common.data.tile;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.helper.TFTileHelper;

public class TileDataRelay extends TileData
{
    public TransmissionHandler transmissionHandler = new TransmissionHandler();
    public boolean isPowered;

    public TileDataRelay()
    {
    }

    public TileDataRelay(TileDataRelay data)
    {
        super(data);
        transmissionHandler = data.transmissionHandler;
        transmissionHandler.setNeedsUpdate(false);
        isPowered = data.isPowered;
    }

    @Override
    public <T extends TileEntity> T initialize(T tile)
    {
        if (!isInitialized())
        {
            transmissionHandler.setOwner(tile);
        }

        return super.initialize(tile);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        transmissionHandler.toBytes(buf);
        buf.writeBoolean(isPowered);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        transmissionHandler.fromBytes(buf);
        isPowered = buf.readBoolean();
    }

    public void serverTickPre()
    {
        WorldServer world = MinecraftServer.getServer().worldServerForDimension(getCoords().dimension);

        if (world != null)
        {
            transmissionHandler.onUpdate(world);
        }
    }

    @Override
    public void kill()
    {
        if (TFTileHelper.getTileData(getCoords()) != null)
        {
            transmissionHandler.kill();
        }

        super.kill();
    }

    @Override
    public boolean matches(TileData tileData)
    {
        if (tileData instanceof TileDataRelay)
        {
            TileDataRelay data = (TileDataRelay) tileData;

            return isPowered == data.isPowered && !transmissionHandler.needsUpdate();
        }

        return false;
    }
}
