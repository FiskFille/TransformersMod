package fiskfille.tf.common.packet.base;

import fiskfille.tf.TransformersMod;
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
	public static TFPacketPipeline packetPipeline;

	public static void registerPackets()
	{
		packetPipeline = new TFPacketPipeline();
		
		packetPipeline.registerPacket(PacketHandleTransformation.class);
		packetPipeline.registerPacket(PacketHandleStealthTransformation.class);
		packetPipeline.registerPacket(PacketSyncTransformationStates.class);
		packetPipeline.registerPacket(PacketTransformersAction.class);
		packetPipeline.registerPacket(PacketCloudtrapJetpack.class);
		packetPipeline.registerPacket(PacketBroadcastStealthState.class);
		packetPipeline.registerPacket(PacketBroadcastTransformationState.class);
		packetPipeline.registerPacket(PacketBroadcastState.class);
		packetPipeline.registerPacket(PacketVehicleNitro.class);
		packetPipeline.registerPacket(PacketVurpSniperShoot.class);
	}
}
