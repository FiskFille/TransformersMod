package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateArmor implements IMessage
{
    public int id;
    private ItemStack armor;
    private int armorSlot;

    public MessageUpdateArmor()
    {

    }

    public MessageUpdateArmor(EntityPlayer player, ItemStack itemstack, int slot)
    {
        id = player.getEntityId();
        armor = itemstack;
        armorSlot = slot;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        armor = ByteBufUtils.readItemStack(buf);
        armorSlot = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
        ByteBufUtils.writeItemStack(buf, armor);
        buf.writeInt(armorSlot);
    }

    public static class Handler implements IMessageHandler<MessageUpdateArmor, IMessage>
    {
        @Override
        public IMessage onMessage(MessageUpdateArmor message, MessageContext ctx)
        {
            ItemStack itemstack = message.armor;
            int slot = message.armorSlot;

            if (ctx.side.isClient())
            {
                EntityPlayer player = TransformersMod.proxy.getPlayer();
                Entity entity = player.worldObj.getEntityByID(message.id);

                if (entity instanceof EntityPlayer)
                {
                    EntityPlayer player1 = (EntityPlayer) entity;
                    player.inventory.armorInventory[slot] = itemstack;
                }
            }

            return null;
        }
    }
}
