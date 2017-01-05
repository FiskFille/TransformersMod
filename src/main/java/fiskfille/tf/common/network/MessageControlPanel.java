package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
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
    private int dimension;
    private int action;

    public MessageControlPanel()
    {

    }

    public MessageControlPanel(EntityPlayer player, int x, int y, int z, int dimension, int action)
    {
        this.id = player != null ? player.getEntityId() : 0;
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
        this.action = action;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        dimension = buf.readInt();
        action = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(dimension);
        buf.writeInt(action);
    }

    public static class Handler implements IMessageHandler<MessageControlPanel, IMessage>
    {
        @Override
        public IMessage onMessage(MessageControlPanel message, MessageContext ctx)
        {
            EntityPlayer clientPlayer = ctx.side.isClient() ? TransformersMod.proxy.getPlayer() : ctx.getServerHandler().playerEntity;
            World world = MinecraftServer.getServer().worldServerForDimension(message.dimension);
            boolean flag = true;

            if (world != null)
            {
                if (clientPlayer.worldObj.getEntityByID(message.id) instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) clientPlayer.worldObj.getEntityByID(message.id);

                    if (player.worldObj.provider.dimensionId == message.dimension)
                    {
                        world = player.worldObj;
                    }
                    else if (clientPlayer.worldObj.provider.dimensionId == message.dimension)
                    {
                        world = clientPlayer.worldObj;
                    }
                    else
                    {
                        flag = ctx.side.isServer();
                    }

                    TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(message.x, message.y, message.z);
                    int action = message.action;

                    if (tile != null && flag)
                    {
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
                        else if (action >= 15 && action <= 17)
                        {
                            if (!tile.activationLeverState)
                            {
                                int slot = action - 15;

                                if (tile.getStackInSlot(slot) == null && player.getHeldItem() != null && tile.isItemValidForSlot(slot, player.getHeldItem()))
                                {
                                    tile.setInventorySlotContents(slot, player.getHeldItem());
                                    player.setCurrentItemOrArmor(0, null);
                                }
                                else if (tile.getStackInSlot(slot) != null && (player.getHeldItem() == null || tile.isItemValidForSlot(slot, player.getHeldItem())))
                                {
                                    ItemStack itemstack = tile.getStackInSlot(slot).copy();

                                    tile.setInventorySlotContents(slot, player.getHeldItem());
                                    player.setCurrentItemOrArmor(0, itemstack);
                                }

                                tile.markBlockForUpdate();
                            }
                        }
                        else if (action == 18 || action == 19)
                        {
                            if (tile.hasUpgrade(DataCore.spaceBridge))
                            {
                                tile.cycleDimensionID(action == 18 ? -1 : 1);
                            }
                        }

                        if (ctx.side.isServer())
                        {
                            TFNetworkManager.networkWrapper.sendToAll(new MessageControlPanel(player, tile.xCoord, tile.yCoord, tile.zCoord, message.dimension, action));
                        }
                    }
                }
            }

            return null;
        }
    }
}
