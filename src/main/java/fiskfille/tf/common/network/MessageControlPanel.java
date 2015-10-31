package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageControlPanel implements IMessage
{
	private int id;
    private int x;
    private int y;
    private int z;
    private int action;

    public MessageControlPanel()
    {

    }

    public MessageControlPanel(EntityPlayer player, int x, int y, int z, int action)
    {
    	this.id = player != null ? player.getEntityId() : 0;
        this.x = x;
        this.y = y;
        this.z = z;
        this.action = action;
    }

    public void fromBytes(ByteBuf buf)
    {
    	id = buf.readInt();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        action = buf.readInt();
    }

    public void toBytes(ByteBuf buf)
    {
    	buf.writeInt(id);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(action);
    }

    public static class Handler implements IMessageHandler<MessageControlPanel, IMessage>
    {
        public IMessage onMessage(MessageControlPanel message, MessageContext ctx)
        {
            EntityPlayer clientPlayer = ctx.side.isClient() ? TransformersMod.proxy.getPlayer(ctx) : ctx.getServerHandler().playerEntity;
            EntityPlayer player = null;
            World world = clientPlayer.worldObj;
            
            TileEntityControlPanel tile = (TileEntityControlPanel)world.getTileEntity(message.x, message.y, message.z);
            int action = message.action;
            
            if (tile != null)
            {
            	if (world.getEntityByID(message.id) instanceof EntityPlayer)
            	{
            		player = (EntityPlayer)world.getEntityByID(message.id);
                	int increment = player.isSneaking() ? -1 : 1;
                	
                	if (action >= 1 && action <= 4)
                	{
                		tile.changeSwitch(0, action - 1, increment);
                	}
                	else if (action >= 5 && action <= 8)
                	{
                		tile.changeSwitch(1, action - 5, increment);
                	}
                	else if (action >= 9 && action <= 12)
                	{
                		tile.changeSwitch(2, action - 9, increment);
                	}
                	else if (action == 13)
                	{
                		tile.portalDirection = (tile.portalDirection + 1) % 4;
                	}
                	else if (action == 14)
                	{
                		tile.activationLeverState = !tile.activationLeverState;
                	}
            	}
            	
            	if (ctx.side.isServer())
            	{
            		TFNetworkManager.networkWrapper.sendToAll(new MessageControlPanel(player, tile.xCoord, tile.yCoord, tile.zCoord, action));
            	}
            }

            return null;
        }
    }
}
