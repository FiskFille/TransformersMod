package fiskfille.tf.common.network;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.config.TFConfig;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.helper.TFDimensionHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MessagePlayerJoin extends MessageSyncBase
{
    private String[] transformBlacklist;
    private Map<Integer, String> dimensionNames = Maps.newHashMap();
    private Integer[] dimensionIDs;

    public MessagePlayerJoin()
    {
    }

    public MessagePlayerJoin(EntityPlayer player)
    {
        super(player);

        this.transformBlacklist = TFConfig.getGlobalRestrictions().transformBlacklist;

        Integer[] ids = DimensionManager.getIDs();
        this.dimensionNames = TFDimensionHelper.DIMENSION_NAMES;

        for (Integer id : ids)
        {
            WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(id);

            if (world != null && world.provider != null)
            {
                this.dimensionNames.put(id, world.provider.getDimensionType().getName());
            }
        }

        List<Integer> list = Lists.newArrayList();

        list.addAll(Arrays.asList(ids));

        list.sort(Comparator.comparing(Double::valueOf));

        TFDimensionHelper.DIMENSION_IDS = this.dimensionIDs = list.toArray(new Integer[list.size()]);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);

        this.transformBlacklist = new String[buf.readUnsignedByte()];

        for (int i = 0; i < this.transformBlacklist.length; i++)
        {
            this.transformBlacklist[i] = ByteBufUtils.readUTF8String(buf);
        }

        int length = buf.readUnsignedByte();

        for (int i = 0; i < length; ++i)
        {
            this.dimensionNames.put(buf.readInt(), ByteBufUtils.readUTF8String(buf));
        }

        this.dimensionIDs = new Integer[buf.readUnsignedByte()];

        for (int i = 0; i < this.dimensionIDs.length; ++i)
        {
            this.dimensionIDs[i] = buf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);

        buf.writeByte(this.transformBlacklist.length);

        for (String blacklisted : this.transformBlacklist)
        {
            ByteBufUtils.writeUTF8String(buf, blacklisted);
        }

        buf.writeByte(this.dimensionNames.size());

        for (Map.Entry<Integer, String> e : this.dimensionNames.entrySet())
        {
            buf.writeInt(e.getKey());
            ByteBufUtils.writeUTF8String(buf, e.getValue());
        }

        buf.writeByte(this.dimensionIDs.length);

        for (Integer dimensionID : this.dimensionIDs)
        {
            buf.writeInt(dimensionID);
        }
    }

    public static class Handler implements IMessageHandler<MessagePlayerJoin, IMessage>
    {
        @Override
        public IMessage onMessage(MessagePlayerJoin message, MessageContext ctx)
        {
            TransformersMod.PROXY.schedule(() ->
            {
                if (ctx.side.isClient())
                {
                    EntityPlayer player = TransformersMod.PROXY.getPlayer(ctx);

                    for (Map.Entry<TFData, Object> e : message.playerData.entrySet())
                    {
                        e.getKey().setWithoutNotify(player, e.getValue());
                    }

                    TFConfig.getGlobalRestrictions().transformBlacklist = message.transformBlacklist;

                    TFDimensionHelper.DIMENSION_NAMES = message.dimensionNames;
                    TFDimensionHelper.DIMENSION_IDS = message.dimensionIDs;
                }
            }, ctx);

            return null;
        }
    }
}
