package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.energon.power.EnergonTank;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class MessageUpdateFluidState implements IMessage
{
    private int x;
    private int y;
    private int z;

    private int usage = 0;
    private FluidStack fluidStack;

    public MessageUpdateFluidState()
    {

    }

    public MessageUpdateFluidState(TileEntityTransmitter tile)
    {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;

        EnergonTank tank = tile.tank;
        this.usage = tank.getUsage();
        this.fluidStack = tank.getFluid();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

        usage = buf.readShort();

        if (buf.readBoolean())
        {
            NBTTagCompound tag = ByteBufUtils.readTag(buf);
            fluidStack = FluidStack.loadFluidStackFromNBT(tag);
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

        buf.writeShort(usage);

        boolean hasFluid = fluidStack != null;
        buf.writeBoolean(hasFluid);

        if (hasFluid)
        {
            NBTTagCompound tag = fluidStack.writeToNBT(new NBTTagCompound());
            ByteBufUtils.writeTag(buf, tag);
        }
    }

    public static class Handler implements IMessageHandler<MessageUpdateFluidState, IMessage>
    {
        @Override
        public IMessage onMessage(MessageUpdateFluidState message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                World world = player.worldObj;

                TileEntity tile = world.getTileEntity(message.x, message.y, message.z);

                if (tile instanceof TileEntityTransmitter)
                {
                    TileEntityTransmitter transmitter = (TileEntityTransmitter) tile;
                    transmitter.tank.setFluid(message.fluidStack);
                    transmitter.tank.setUsage(message.usage);
                }
            }

            return null;
        }
    }
}
