package fiskfille.tf.common.network;

import fiskfille.tf.common.capability.TFCapabilities;
import fiskfille.tf.common.data.TFData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.HashMap;
import java.util.Map;

public abstract class MessageSyncBase implements IMessage
{
    public Map<TFData, Object> playerData;

    public MessageSyncBase()
    {
    }

    public MessageSyncBase(EntityPlayer player)
    {
        this.playerData = player.getCapability(TFCapabilities.PLAYER_DATA_CAP, null).getData();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        NBTTagCompound tag = new NBTTagCompound();

        TFData.writeToNBT(tag, this.playerData);
        ByteBufUtils.writeTag(buf, tag);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.playerData = TFData.readFromNBT(ByteBufUtils.readTag(buf), new HashMap<>());
    }
}
