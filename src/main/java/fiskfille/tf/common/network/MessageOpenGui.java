package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.helper.TFHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageOpenGui implements IMessage
{
    public int id;
    private int modGuiId;
    private int x;
    private int y;
    private int z;
    private int dimension;

    public MessageOpenGui()
    {

    }

    public MessageOpenGui(EntityPlayer player, int modGuiId, int x, int y, int z, int dimension)
    {
        this.id = player.getEntityId();
        this.modGuiId = modGuiId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        modGuiId = buf.readInt();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        dimension = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeInt(modGuiId);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(dimension);
    }

    public static class Handler implements IMessageHandler<MessageOpenGui, IMessage>
    {
        @Override
        public IMessage onMessage(MessageOpenGui message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer clientPlayer = TransformersMod.proxy.getPlayer();
                WorldServer world = DimensionManager.getWorld(message.dimension);

                if (world != null)
                {
                    if (clientPlayer.worldObj.getEntityByID(message.id) instanceof EntityPlayer)
                    {
                        EntityPlayer player = (EntityPlayer) clientPlayer.worldObj.getEntityByID(message.id);

                        TFHelper.openGui(player, TransformersMod.instance, message.modGuiId, world, message.x, message.y, message.z);
                        player.openContainer.windowId = message.modGuiId;
                    }
                }
            }

            return null;
        }
    }
}
