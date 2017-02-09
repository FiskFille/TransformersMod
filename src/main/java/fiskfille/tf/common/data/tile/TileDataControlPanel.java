package fiskfille.tf.common.data.tile;

import io.netty.buffer.ByteBuf;

import java.util.List;

import com.google.common.collect.Lists;

import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.groundbridge.GroundBridgeError.ErrorContainer;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;

public class TileDataControlPanel extends TileDataEnergyContainer
{
    public DimensionalCoords framePos;
    public DimensionalCoords destination = new DimensionalCoords();
    public int modifiedDestY;

    public int direction;
    public int frameDirection;
    public boolean activationLeverState = false;
    public List<DataCore> upgrades = Lists.newArrayList();
    public List<ErrorContainer> errors = Lists.newArrayList();

    public TileDataControlPanel()
    {
        super(64000);
    }

    public TileDataControlPanel(TileDataControlPanel data)
    {
        super(data);
        framePos = DimensionalCoords.copy(data.framePos);
        destination = DimensionalCoords.copy(data.destination);
        modifiedDestY = data.modifiedDestY;
        direction = data.direction;
        frameDirection = data.frameDirection;
        activationLeverState = data.activationLeverState;
        upgrades = Lists.newArrayList(data.upgrades);
        errors = Lists.newArrayList(data.errors);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeInt(modifiedDestY);
        buf.writeByte(direction & 0xFF);
        buf.writeByte(frameDirection & 0xFF);
        buf.writeBoolean(activationLeverState);
        buf.writeByte(upgrades.size() & 0xFF);

        for (DataCore core : upgrades)
        {
            buf.writeByte(core.index & 0xFF);
        }

        buf.writeByte(errors.size() & 0xFF);

        for (ErrorContainer container : errors)
        {
            container.toBytes(buf);
        }

        if (framePos != null)
        {
            buf.writeBoolean(true);
            framePos.toBytes(buf);
        }
        else
        {
            buf.writeBoolean(false);
        }

        destination.toBytes(buf);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        modifiedDestY = buf.readInt();
        direction = buf.readByte() & 0xFF;
        frameDirection = buf.readByte() & 0xFF;
        activationLeverState = buf.readBoolean();

        upgrades = Lists.newArrayList();
        int upgradeCount = buf.readByte() & 0xFF;

        for (int i = 0; i < upgradeCount; i++)
        {
            int index = buf.readByte() & 0xFF;

            DataCore core = DataCore.get(index);

            if (core != null)
            {
                upgrades.add(core);
            }
        }

        errors = Lists.newArrayList();
        int errorCount = buf.readByte() & 0xFF;

        for (int i = 0; i < errorCount; i++)
        {
            ErrorContainer container = ErrorContainer.fromBytes(buf);

            if (container != null)
            {
                errors.add(container);
            }
        }

        if (buf.readBoolean())
        {
            framePos = new DimensionalCoords().fromBytes(buf);
        }

        destination.fromBytes(buf);
    }

    public boolean hasUpgrade(DataCore core)
    {
        return upgrades.contains(core);
    }

    @Override
    public boolean matches(TileData tileData)
    {
        if (tileData instanceof TileDataControlPanel)
        {
            TileDataControlPanel data = (TileDataControlPanel) tileData;
            boolean frameEquals = data.framePos == null && framePos == null || data.framePos != null && data.framePos.equals(framePos);
            boolean destinationEquals = data.destination.equals(destination) && data.modifiedDestY == modifiedDestY;
            boolean errorsEqual = data.errors.size() == errors.size();

            if (errorsEqual)
            {
                for (int i = 0; i < data.errors.size(); i++)
                {
                    if (!data.errors.get(i).equals(errors.get(i)))
                    {
                        errorsEqual = false;
                        break;
                    }
                }
            }

            boolean coresEqual = data.upgrades.size() == upgrades.size();

            if (coresEqual)
            {
                for (int i = 0; i < data.upgrades.size(); i++)
                {
                    if (data.upgrades.get(i).index != upgrades.get(i).index)
                    {
                        coresEqual = false;
                        break;
                    }
                }
            }

            return super.matches(data) && frameEquals && coresEqual && errorsEqual && destinationEquals && data.activationLeverState == activationLeverState && data.direction == direction && data.frameDirection == frameDirection;
        }

        return false;
    }
}
