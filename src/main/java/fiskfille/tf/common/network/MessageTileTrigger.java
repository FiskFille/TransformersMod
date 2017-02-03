package fiskfille.tf.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.network.base.TFNetworkManager;

public class MessageTileTrigger implements IMessage
{
    private DimensionalCoords coordinates;
    private int id;
    private int action;
    private int playerDimension;

    public MessageTileTrigger()
    {

    }

    public MessageTileTrigger(DimensionalCoords coords, EntityPlayer player, int action)
    {
        this.coordinates = coords;
        this.action = action;

        if (player != null)
        {
            id = player.getEntityId();
            playerDimension = player.worldObj.provider.dimensionId;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        action = buf.readInt();
        playerDimension = buf.readInt();
        coordinates = new DimensionalCoords().fromBytes(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeInt(action);
        buf.writeInt(playerDimension);
        coordinates.toBytes(buf);
    }

    public static class Handler implements IMessageHandler<MessageTileTrigger, IMessage>
    {
        @Override
        public IMessage onMessage(MessageTileTrigger message, MessageContext ctx)
        {
            EntityPlayer clientPlayer = ctx.side.isClient() ? TransformersMod.proxy.getPlayer() : ctx.getServerHandler().playerEntity;
            EntityPlayer player = null;
            World world = clientPlayer.worldObj;

            if (world.provider.dimensionId != message.playerDimension)
            {
                if (!ctx.side.isServer())
                {
                    return null;
                }

                world = MinecraftServer.getServer().worldServerForDimension(message.playerDimension);
            }

            if (world.getEntityByID(message.id) instanceof EntityPlayer)
            {
                player = (EntityPlayer) world.getEntityByID(message.id);
            }

            DimensionalCoords coords = message.coordinates;
            world = clientPlayer.worldObj;

            if (world.provider.dimensionId != coords.dimension)
            {
                if (!ctx.side.isServer())
                {
                    return null;
                }

                world = MinecraftServer.getServer().worldServerForDimension(coords.dimension);
            }

            if (world.getTileEntity(coords.posX, coords.posY, coords.posZ) instanceof ITileDataCallback)
            {
                ITileDataCallback callback = (ITileDataCallback) world.getTileEntity(coords.posX, coords.posY, coords.posZ);
                callback.receive(player, message.action);

                if (ctx.side.isServer())
                {
                    TFNetworkManager.networkWrapper.sendToAll(new MessageTileTrigger(coords, player, message.action));
                }
            }

            return null;
        }
    }

    public static interface ITileDataCallback
    {
        /**
         * Called when a tile gets triggered
         * 
         * @param player The player who triggered it, or null if none exists
         * @param action The trigger type
         */
        void receive(EntityPlayer player, int action);
    }
}
