package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MessageControlPanel implements IMessage
{
    private int x;
    private int y;
    private int z;
    private int dimension;
    private int action;

    public MessageControlPanel()
    {

    }

    public MessageControlPanel(int x, int y, int z, int dimension, int action)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dimension = dimension;
        this.action = action;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        dimension = buf.readInt();
        action = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
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
            if (ctx.side.isServer())
            {
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                World world = MinecraftServer.getServer().worldServerForDimension(message.dimension);

                if (world != null)
                {
                    TileEntity tile = world.getTileEntity(message.x, message.y, message.z);

                    if (tile instanceof TileEntityControlPanel)
                    {
                        TileEntityControlPanel panel = (TileEntityControlPanel) tile;

                        if (panel.isUseableByPlayer(player) || (player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.groundBridgeRemote))
                        {
                            int action = message.action;

                            int increment = player.isSneaking() ? -1 : 1;

                            if (action >= 1 && action <= 4)
                            {
                                panel.changeSwitch(0, action - 1, increment);
                            }
                            else if (action >= 5 && action <= 8)
                            {
                                panel.changeSwitch(1, action - 5, increment);
                            }
                            else if (action >= 9 && action <= 12)
                            {
                                panel.changeSwitch(2, action - 9, increment);
                            }
                            else if (action == 13)
                            {
                                panel.portalDirection = (panel.portalDirection + 1) % 4;
                            }
                            else if (action == 14)
                            {
                                panel.activationLeverState = !panel.activationLeverState;
                            }
                            else if (action >= 15 && action <= 17)
                            {
                                if (!panel.activationLeverState)
                                {
                                    int slot = action - 15;

                                    if (panel.getStackInSlot(slot) == null && player.getHeldItem() != null && panel.isItemValidForSlot(slot, player.getHeldItem()))
                                    {
                                        panel.setInventorySlotContents(slot, player.getHeldItem());
                                        player.setCurrentItemOrArmor(0, null);
                                    }
                                    else if (panel.getStackInSlot(slot) != null && (player.getHeldItem() == null || panel.isItemValidForSlot(slot, player.getHeldItem())))
                                    {
                                        ItemStack itemstack = panel.getStackInSlot(slot).copy();

                                        panel.setInventorySlotContents(slot, player.getHeldItem());
                                        player.setCurrentItemOrArmor(0, itemstack);
                                    }

                                    panel.markBlockForUpdate();
                                }
                            }
                            else if (action == 18 || action == 19)
                            {
                                if (panel.hasUpgrade(DataCore.spaceBridge))
                                {
                                    panel.cycleDimensionID(action == 18 ? -1 : 1);
                                }
                            }
                            else
                            {
                                return null;
                            }

                            world.markBlockForUpdate(message.x, message.y, message.z);
                        }
                    }
                }
            }

            return null;
        }
    }
}