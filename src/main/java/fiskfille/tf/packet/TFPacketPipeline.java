package fiskfille.tf.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Packet pipeline class. Directs all registered packet data to be handled by the packets themselves.
 * @author sirgingalot
 * some code from: cpw
 */
@ChannelHandler.Sharable
public class TFPacketPipeline extends MessageToMessageCodec<FMLProxyPacket, TransformersPacket> 
{
	private static final Logger logger = LogManager.getLogger();
	private EnumMap<Side, FMLEmbeddedChannel> channels;
	
	private LinkedList<Class<? extends TransformersPacket>> packets = new LinkedList<Class<? extends TransformersPacket>>();
	private boolean hasPostInitialised = false;

	/**
	 * Register your packet with the pipeline. Discriminators are automatically set.
	 *
	 * @param clazz the class to register
	 *
	 * @return whether registration was successful. Failure may occur if 256 packets have been registered or if the registry already contains this packet
	 */
	public boolean registerPacket(Class<? extends TransformersPacket> clazz)
	{
		if (this.packets.size() > 256)
		{
			logger.error("[Transformers Mod Packets] Failed to register packet " + clazz.getName() + ". Too many packets!");
			return false;
		}

		if (this.packets.contains(clazz))
		{
			logger.error("[Transformers Mod Packets] Failed to register packet " + clazz.getName() + ". It has already been registered!");
			return false;
		}

		if (this.hasPostInitialised) 
		{
			logger.error("[Transformers Mod Packets] Failed to register packet " + clazz.getName() + ". Post has already initialized!");
			return false;
		}

		this.packets.add(clazz);
		return true;
	}

	// In line encoding of the packet, including discriminator setting
	@Override
	protected void encode(ChannelHandlerContext ctx, TransformersPacket msg, List<Object> out)  throws Exception 
	{
		ByteBuf buffer = Unpooled.buffer();
		Class<? extends TransformersPacket> clazz = msg.getClass();
		if (!this.packets.contains(msg.getClass())) 
		{
			throw new NullPointerException("[Transformers Mod Packets] No Packet Registered for: " + msg.getClass().getCanonicalName());
		}

		byte discriminator = (byte) this.packets.indexOf(clazz);
		buffer.writeByte(discriminator);
		msg.encodeInto(ctx, buffer);
		FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
		out.add(proxyPacket);
	}

	// In line decoding and handling of the packet
	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception 
	{
		ByteBuf payload = msg.payload();
		byte discriminator = payload.readByte();
		Class<? extends TransformersPacket> clazz = this.packets.get(discriminator);
		if (clazz == null) 
		{
			throw new NullPointerException("[Transformers Mod Packets] No packet registered for discriminator: " + discriminator);
		}

		TransformersPacket pkt = clazz.newInstance();
		pkt.decodeInto(ctx, payload.slice());

		EntityPlayer player;
		switch (FMLCommonHandler.instance().getEffectiveSide())
		{
		case CLIENT:
			player = this.getClientPlayer();
			pkt.handleClientSide(player);
			break;

		case SERVER:
			INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
			player = ((NetHandlerPlayServer) netHandler).playerEntity;
			pkt.handleServerSide(player);
			break;

		default:
		}

		out.add(pkt);
	}

	public void initialize()
	{
		this.channels = NetworkRegistry.INSTANCE.newChannel("Transformers Mod", this);
	}

	public void postInitialize() 
	{
		if (this.hasPostInitialised)
		{
			return;
		}

		this.hasPostInitialised = true;
		Collections.sort(this.packets, new Comparator<Class<? extends TransformersPacket>>() 
				{

			@Override
			public int compare(Class<? extends TransformersPacket> clazz1, Class<? extends TransformersPacket> clazz2) {
				int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
				if (com == 0) {
					com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
				}

				return com;
			}
				});
	}

	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer() 
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	/**
	 * Send this message to everyone.
	 * <p/>
	 * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 */
	public void sendToAll(TransformersPacket message)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	/**
	 * Send this message to the specified player.
	 * <p/>
	 * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 * @param player  The player to send it to
	 */
	public void sendTo(TransformersPacket message, EntityPlayerMP player)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	/**
	 * Send this message to everyone within a certain range of a point.
	 * <p/>
	 * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 * @param point   The {@link cpw.mods.fml.common.network.NetworkRegistry.TargetPoint} around which to send
	 */
	public void sendToAllAround(TransformersPacket message, NetworkRegistry.TargetPoint point)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	/**
	 * Send this message to everyone within the supplied dimension.
	 * <p/>
	 * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message     The message to send
	 * @param dimensionId The dimension id to target
	 */
	public void sendToDimension(TransformersPacket message, int dimensionId) 
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	/**
	 * Send this message to the server.
	 * <p/>
	 * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 */
	public void sendToServer(TransformersPacket message) 
	{
		this.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		this.channels.get(Side.CLIENT).writeAndFlush(message);
	}
}