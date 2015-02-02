package fiskfille.tf.packet;
//package fiskfille.tf.server;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//
//import java.util.EnumMap;
//import java.util.LinkedList;
//import java.util.List;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.network.Packet;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.World;
//import net.minecraftforge.common.util.ForgeDirection;
//import cpw.mods.fml.common.FMLCommonHandler;
//import cpw.mods.fml.common.network.FMLEmbeddedChannel;
//import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
//import cpw.mods.fml.common.network.FMLOutboundHandler;
//import cpw.mods.fml.common.network.NetworkRegistry;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
///**
// * Handles the packet wrangling for IronChest
// *
// * @author cpw
// */
//public enum TFPacketHandler
//{
//	INSTANCE;
//
//	/**
//	 * Our channel "pair" from {@link NetworkRegistry}
//	 */
//	private EnumMap<Side, FMLEmbeddedChannel> channels;
//
//
//	/**
//	 * Make our packet handler, and add an {@link IronChestCodec} always
//	 */
//	private TFPacketHandler()
//	{
//		// request a channel pair for IronChest from the network registry
//		// Add the IronChestCodec as a member of both channel pipelines
//		this.channels = NetworkRegistry.INSTANCE.newChannel("Transformers", new TEAltarCodec());
//		
//		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
//		{
//			addClientHandler();
//		}
//	}
//
//	@SideOnly(Side.CLIENT)
//	private void addClientHandler()
//	{
//		FMLEmbeddedChannel clientChannel = this.channels.get(Side.CLIENT);
//
//		String tileAltarCodec = clientChannel.findChannelHandlerNameForType(TEAltarCodec.class);
//		clientChannel.pipeline().addAfter(tileAltarCodec, "DisplayPillarHandler", new TEAltarMessageHandler());
//	}
//	
//
//	/**
//	 * This class simply handles the {@link IronChestMessage} when it's received
//	 * at the client side It can contain client only code, because it's only run
//	 * on the client.
//	 *
//	 * @author cpw
//	 */
//	private static class TEAltarMessageHandler extends SimpleChannelInboundHandler<TEAltarMessage>
//	{
//		@Override
//		protected void channelRead0(ChannelHandlerContext ctx, TEAltarMessage msg) throws Exception
//		{
//			World world = AlchemicalWizardry.proxy.getClientWorld();
////			TileEntity te = world.getTileEntity(msg.x, msg.y, msgz);
//			if (te instanceof TEAltar)
//			{
//				TEAltar altar = (TEAltar) te;
//
//				altar.handlePacketData(msg.items, msg.fluids, msg.capacity);
//			}
//		}
//	}
//
//	public static class BMMessage
//	{
//		int index;
//	}
//
//	public static class TEAltarMessage extends BMMessage
//	{
//		int x;
//		int y;
//		int z;
//
//		int[] items;
//		int[] fluids;
//		int capacity;
//	}
//
//	private class TEAltarCodec extends FMLIndexedMessageToMessageCodec<BMMessage>
//	{
//		public TEAltarCodec()
//		{
//			addDiscriminator(0, TEAltarMessage.class);
//		}
//
//		@Override
//		public void encodeInto(ChannelHandlerContext ctx, BMMessage msg, ByteBuf target) throws Exception
//		{
//			target.writeInt(msg.index);
//
//			switch (msg.index)
//			{
//			case 0:
//				target.writeInt(((TEAltarMessage) msg).x);
//				target.writeInt(((TEAltarMessage) msg).y);
//				target.writeInt(((TEAltarMessage) msg).z);
//
//				target.writeBoolean(((TEAltarMessage) msg).items != null);
//				if (((TEAltarMessage) msg).items != null)
//				{
//					int[] items = ((TEAltarMessage) msg).items;
//					for (int j = 0; j < items.length; j++)
//					{
//						int i = items[j];
//						target.writeInt(i);
//					}
//				}
//
//				target.writeBoolean(((TEAltarMessage) msg).fluids != null);
//				if (((TEAltarMessage) msg).fluids != null)
//				{
//					int[] fluids = ((TEAltarMessage) msg).fluids;
//					for (int j = 0; j < fluids.length; j++)
//					{
//						int i = fluids[j];
//						target.writeInt(i);
//					}
//				}
//
//				target.writeInt(((TEAltarMessage) msg).capacity);
//
//				break;
//
//			case 1:
//				target.writeInt(((TEOrientableMessage) msg).x);
//				target.writeInt(((TEOrientableMessage) msg).y);
//				target.writeInt(((TEOrientableMessage) msg).z);
//
//				target.writeInt(((TEOrientableMessage) msg).input);
//				target.writeInt(((TEOrientableMessage) msg).output);
//
//				break;
//
//			case 2:
//				target.writeInt(((TEPedestalMessage) msg).x);
//				target.writeInt(((TEPedestalMessage) msg).y);
//				target.writeInt(((TEPedestalMessage) msg).z);
//
//				target.writeBoolean(((TEPedestalMessage) msg).items != null);
//				if (((TEPedestalMessage) msg).items != null)
//				{
//					int[] items = ((TEPedestalMessage) msg).items;
//					for (int j = 0; j < items.length; j++)
//					{
//						int i = items[j];
//						target.writeInt(i);
//					}
//				}
//
//				break;
//
//			case 3:
//				target.writeInt(((TEPlinthMessage) msg).x);
//				target.writeInt(((TEPlinthMessage) msg).y);
//				target.writeInt(((TEPlinthMessage) msg).z);
//
//				target.writeBoolean(((TEPlinthMessage) msg).items != null);
//				if (((TEPlinthMessage) msg).items != null)
//				{
//					int[] items = ((TEPlinthMessage) msg).items;
//					for (int j = 0; j < items.length; j++)
//					{
//						int i = items[j];
//						target.writeInt(i);
//					}
//				}
//
//				break;
//
//			case 4:
//				target.writeInt(((TESocketMessage) msg).x);
//				target.writeInt(((TESocketMessage) msg).y);
//				target.writeInt(((TESocketMessage) msg).z);
//
//				target.writeBoolean(((TESocketMessage) msg).items != null);
//				if (((TESocketMessage) msg).items != null)
//				{
//					int[] items = ((TESocketMessage) msg).items;
//					for (int j = 0; j < items.length; j++)
//					{
//						int i = items[j];
//						target.writeInt(i);
//					}
//				}
//
//				break;
//
//			case 5:
//				target.writeInt(((TETeleposerMessage) msg).x);
//				target.writeInt(((TETeleposerMessage) msg).y);
//				target.writeInt(((TETeleposerMessage) msg).z);
//
//				target.writeBoolean(((TETeleposerMessage) msg).items != null);
//				if (((TETeleposerMessage) msg).items != null)
//				{
//					int[] items = ((TETeleposerMessage) msg).items;
//					for (int j = 0; j < items.length; j++)
//					{
//						int i = items[j];
//						target.writeInt(i);
//					}
//				}
//
//				break;
//
//			case 6:
//				target.writeInt(((TEWritingTableMessage) msg).x);
//				target.writeInt(((TEWritingTableMessage) msg).y);
//				target.writeInt(((TEWritingTableMessage) msg).z);
//
//				target.writeBoolean(((TEWritingTableMessage) msg).items != null);
//				if (((TEWritingTableMessage) msg).items != null)
//				{
//					int[] items = ((TEWritingTableMessage) msg).items;
//					for (int j = 0; j < items.length; j++)
//					{
//						int i = items[j];
//						target.writeInt(i);
//					}
//				}
//
//				break;
//
//			case 7:
//				String str = ((ParticleMessage) msg).particle;
//				target.writeInt(str.length());
//				for (int i = 0; i < str.length(); i++)
//				{
//					target.writeChar(str.charAt(i));
//				}
//
//				target.writeDouble(((ParticleMessage) msg).xCoord);
//				target.writeDouble(((ParticleMessage) msg).yCoord);
//				target.writeDouble(((ParticleMessage) msg).zCoord);
//
//				target.writeDouble(((ParticleMessage) msg).xVel);
//				target.writeDouble(((ParticleMessage) msg).yVel);
//				target.writeDouble(((ParticleMessage) msg).zVel);
//
//				break;
//
//			case 8:
//				target.writeDouble(((VelocityMessage) msg).xVel);
//				target.writeDouble(((VelocityMessage) msg).yVel);
//				target.writeDouble(((VelocityMessage) msg).zVel);
//
//				break;
//
//			case 9:
//				target.writeInt(((TEMasterStoneMessage) msg).x);
//				target.writeInt(((TEMasterStoneMessage) msg).y);
//				target.writeInt(((TEMasterStoneMessage) msg).z);
//
//				String ritual = ((TEMasterStoneMessage) msg).ritual;
//				target.writeInt(ritual.length());
//				for (int i = 0; i < ritual.length(); i++)
//				{
//					target.writeChar(ritual.charAt(i));
//				}
//
//				target.writeBoolean(((TEMasterStoneMessage) msg).isRunning);
//
//				break;
//
//			case 10:
//				target.writeInt(((TEReagentConduitMessage) msg).x);
//				target.writeInt(((TEReagentConduitMessage) msg).y);
//				target.writeInt(((TEReagentConduitMessage) msg).z);
//
//				List<ColourAndCoords> list = ((TEReagentConduitMessage) msg).destinationList;
//				target.writeInt(list.size());
//
//				for (ColourAndCoords colourSet : list)
//				{
//					target.writeInt(colourSet.colourRed);
//					target.writeInt(colourSet.colourGreen);
//					target.writeInt(colourSet.colourBlue);
//					target.writeInt(colourSet.colourIntensity);
//					target.writeInt(colourSet.xCoord);
//					target.writeInt(colourSet.yCoord);
//					target.writeInt(colourSet.zCoord);
//				}
//
//				break;
//			}
//		}
//
//
//		@Override
//		public void decodeInto(ChannelHandlerContext ctx, ByteBuf dat, BMMessage msg)
//		{
//			int index = dat.readInt();
//
//			switch (index)
//			{
//			case 0:
//				((TEAltarMessage) msg).x = dat.readInt();
//				((TEAltarMessage) msg).y = dat.readInt();
//				((TEAltarMessage) msg).z = dat.readInt();
//				boolean hasStacks = dat.readBoolean();
//
//				((TEAltarMessage) msg).items = new int[TEAltar.sizeInv * 3];
//				if (hasStacks)
//				{
//					((TEAltarMessage) msg).items = new int[TEAltar.sizeInv * 3];
//					for (int i = 0; i < ((TEAltarMessage) msg).items.length; i++)
//					{
//						((TEAltarMessage) msg).items[i] = dat.readInt();
//					}
//				}
//
//				boolean hasFluids = dat.readBoolean();
//				((TEAltarMessage) msg).fluids = new int[6];
//				if (hasFluids)
//					for (int i = 0; i < ((TEAltarMessage) msg).fluids.length; i++)
//					{
//						((TEAltarMessage) msg).fluids[i] = dat.readInt();
//					}
//
//				((TEAltarMessage) msg).capacity = dat.readInt();
//				
//				break;
//			}
//		}
//	}
//
//	//Packets to be obtained
//	public static Packet getPacket(TEAltar tileAltar)
//	{
//		TEAltarMessage msg = new TEAltarMessage();
//		msg.index = 0;
//		msg.x = tileAltar.xCoord;
//		msg.y = tileAltar.yCoord;
//		msg.z = tileAltar.zCoord;
//		msg.items = tileAltar.buildIntDataList();
//		msg.fluids = tileAltar.buildFluidList();
//		msg.capacity = tileAltar.getCapacity();
//
//		return INSTANCE.channels.get(Side.SERVER).generatePacketFrom(msg);
//	}
//
//	public void sendTo(Packet message, EntityPlayerMP player)
//	{
//		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
//		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
//		this.channels.get(Side.SERVER).writeAndFlush(message);
//	}
//
//	public void sendToAll(Packet message)
//	{
//		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
//		this.channels.get(Side.SERVER).writeAndFlush(message);
//	}
//
//	public void sendToAllAround(Packet message, NetworkRegistry.TargetPoint point)
//	{
//		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
//		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
//		this.channels.get(Side.SERVER).writeAndFlush(message);
//	}
//}