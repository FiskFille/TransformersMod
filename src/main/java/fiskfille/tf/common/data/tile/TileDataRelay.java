package fiskfille.tf.common.data.tile;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

import com.google.common.collect.Lists;

import fiskfille.tf.common.energon.power.TransmissionHandler;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.helper.TFTileHelper;

public class TileDataRelay extends TileData
{
    public TransmissionHandler transmissionHandler = new TransmissionHandler();
    public List<DimensionalCoords> invertCurrent = Lists.newArrayList();
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
        invertCurrent = Lists.newArrayList(data.invertCurrent);
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
        buf.writeInt(invertCurrent.size());
        
        for (DimensionalCoords coords : invertCurrent)
        {
            coords.toBytes(buf);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        transmissionHandler.fromBytes(buf);
        isPowered = buf.readBoolean();
        
        int size = buf.readInt();
        
        for (int i = 0; i < size; ++i)
        {
            invertCurrent.add(new DimensionalCoords().fromBytes(buf));
        }
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

            return isPowered == data.isPowered && !transmissionHandler.needsUpdate() && invertCurrent.size() == data.invertCurrent.size() && invertCurrent.containsAll(data.invertCurrent);
        }

        return false;
    }
}
