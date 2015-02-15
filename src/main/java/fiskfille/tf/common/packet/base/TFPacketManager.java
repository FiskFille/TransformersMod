package fiskfille.tf.common.packet.base;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.common.packet.PacketBroadcastState;
import fiskfille.tf.common.packet.PacketBroadcastStealthState;
import fiskfille.tf.common.packet.PacketBroadcastTransformationState;
import fiskfille.tf.common.packet.PacketCloudtrapJetpack;
import fiskfille.tf.common.packet.PacketHandleStealthTransformation;
import fiskfille.tf.common.packet.PacketHandleTransformation;
import fiskfille.tf.common.packet.PacketSyncTransformationStates;
import fiskfille.tf.common.packet.PacketTransformersAction;
import fiskfille.tf.common.packet.PacketVehicleNitro;
import fiskfille.tf.common.packet.PacketVurpSniperShoot;

public class TFPacketManager 
{
	public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;

	public static void registerPackets()
	{
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("transformersMod");
		
		registerPacket(PacketHandleTransformation.class, PacketHandleTransformation.class);
		registerPacket(PacketHandleStealthTransformation.class, PacketHandleStealthTransformation.class);
		registerPacket(PacketSyncTransformationStates.class, PacketSyncTransformationStates.class);
		registerPacket(PacketTransformersAction.class, PacketTransformersAction.class);
		registerPacket(PacketCloudtrapJetpack.class, PacketCloudtrapJetpack.class);
		registerPacket(PacketBroadcastStealthState.class, PacketBroadcastStealthState.class);
		registerPacket(PacketBroadcastTransformationState.class, PacketBroadcastTransformationState.class);
		registerPacket(PacketBroadcastState.class, PacketBroadcastState.class);
		registerPacket(PacketVehicleNitro.class, PacketVehicleNitro.class);
		registerPacket(PacketVurpSniperShoot.class, PacketVurpSniperShoot.class);
	}

    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.SERVER);
    }
}
