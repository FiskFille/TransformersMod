package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageColorArmor implements IMessage
{
    private int x;
    private int y;
    private int z;
    private int primaryColor;
    private int secondaryColor;

    public MessageColorArmor()
    {

    }

    public MessageColorArmor(int x, int y, int z, int primaryColor, int secondaryColor)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        primaryColor = buf.readInt();
        secondaryColor = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(primaryColor);
        buf.writeInt(secondaryColor);
    }

    public static class Handler implements IMessageHandler<MessageColorArmor, IMessage>
    {
        @Override
        public IMessage onMessage(MessageColorArmor message, MessageContext ctx)
        {
            EntityPlayer player = ctx.side.isClient() ? TransformersMod.proxy.getPlayer() : ctx.getServerHandler().playerEntity;
            World world = player.worldObj;

            TileEntityDisplayStation tileentity = (TileEntityDisplayStation) world.getTileEntity(message.x, message.y, message.z);

            if (tileentity != null)
            {
                if (tileentity.setColor(message.primaryColor, message.secondaryColor))
                {
                    world.markBlockForUpdate(message.x, message.y, message.z);
                    
                    if (ctx.side.isServer())
                    {
                        TFNetworkManager.networkWrapper.sendToAll(new MessageColorArmor(message.x, message.y, message.z, message.primaryColor, message.secondaryColor));
                    }
                }
            }

            return null;
        }
    }
}
