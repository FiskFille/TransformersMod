package fiskfille.tf.common.network;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.data.TFData;
import fiskfille.tf.common.helper.TFDimensionHelper;
import fiskfille.tf.common.transformer.Transformer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessagePlayerJoin extends MessageSyncBase
{
    private Map<Transformer, Boolean> canTransform;
    private Map<Integer, String> dimensionNames = Maps.newHashMap();
    private Integer[] dimensionIDs;

    public MessagePlayerJoin()
    {
    }

    public MessagePlayerJoin(EntityPlayer player)
    {
        super(player);
        this.canTransform = new HashMap<>();//TFConfig.canTransform; TODO

        if (this.canTransform.isEmpty() || !this.canTransform.keySet().containsAll(TransformersAPI.REGISTRY.getKeys().stream().map(ResourceLocation::toString).collect(Collectors.toList())))
        {
            for (Transformer transformer : TransformersAPI.REGISTRY)
            {
                if (!this.canTransform.containsKey(transformer))
                {
                    this.canTransform.put(transformer, true);
                }
            }
        }

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
        this.canTransform = Maps.newHashMap();

        for (Transformer transformer : TransformersAPI.REGISTRY)
        {
            this.canTransform.put(transformer, buf.readBoolean());
        }

        int length = buf.readInt();

        for (int i = 0; i < length; ++i)
        {
            this.dimensionNames.put(buf.readInt(), ByteBufUtils.readUTF8String(buf));
        }

        this.dimensionIDs = new Integer[buf.readInt()];

        for (int i = 0; i < this.dimensionIDs.length; ++i)
        {
            this.dimensionIDs[i] = buf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);

        for (Map.Entry<Transformer, Boolean> transformable : this.canTransform.entrySet())
        {
            buf.writeBoolean(transformable.getValue());
        }

        buf.writeInt(this.dimensionNames.size());

        for (Map.Entry<Integer, String> e : this.dimensionNames.entrySet())
        {
            buf.writeInt(e.getKey());
            ByteBufUtils.writeUTF8String(buf, e.getValue());
        }

        buf.writeInt(this.dimensionIDs.length);

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

                    if (message.canTransform != null)
                    {
//                    TFConfig.canTransform = message.canTransform; TODO: Config
                    }

                    TFDimensionHelper.DIMENSION_NAMES = message.dimensionNames;
                    TFDimensionHelper.DIMENSION_IDS = message.dimensionIDs;
                }
            }, ctx);

            return null;
        }
    }
}
