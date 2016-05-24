package fiskfille.tf.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class MessageGroundBridgeTeleport implements IMessage
{
    private int entity;
    private int x, y, z;
    private float yaw;
    private int motionXMultiplier;
    private int motionZMultiplier;

    public MessageGroundBridgeTeleport()
    {
    }

    public MessageGroundBridgeTeleport(Entity entity, int x, int y, int z, float yaw, int motionXMultiplier, int motionZMultiplier)
    {
        this.entity = entity.getEntityId();
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.motionXMultiplier = motionXMultiplier;
        this.motionZMultiplier = motionZMultiplier;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.entity = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.yaw = buf.readFloat();
        byte flags = buf.readByte();
        this.motionXMultiplier = flags & 1;
        this.motionZMultiplier = flags >> 1 & 1;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.entity);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeFloat(this.yaw);
        buf.writeByte((byte) (this.motionXMultiplier & 1 | (this.motionZMultiplier & 1) >> 1));
    }

    public static class Handler implements IMessageHandler<MessageGroundBridgeTeleport, IMessage>
    {
        public IMessage onMessage(MessageGroundBridgeTeleport message, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entity);

                if (entity != null)
                {
                    entity.setLocationAndAngles(message.x + 0.5, message.y + 1.0, message.z + 0.5, message.yaw, entity.rotationPitch);

                    entity.motionX *= message.motionXMultiplier == 0 ? -1 : 1;
                    entity.motionZ *= message.motionZMultiplier == 0 ? -1 : 1;
                }
            }

            return null;
        }
    }
}
