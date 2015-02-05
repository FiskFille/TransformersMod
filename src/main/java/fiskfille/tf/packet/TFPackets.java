package fiskfille.tf.packet;

import fiskfille.tf.TransformersMod;

public class TFPackets 
{
	public static void registerPackets()
	{
		TransformersMod.packetPipeline = new TFPacketPipeline();
		TransformersMod.packetPipeline.registerPacket(PacketHandleTransformation.class);
		TransformersMod.packetPipeline.registerPacket(PacketHandleStealthTransformation.class);
		
		TransformersMod.packetPipeline.registerPacket(PacketSyncTransformationStates.class);
		TransformersMod.packetPipeline.registerPacket(PacketTransformersAction.class);
		
		TransformersMod.packetPipeline.registerPacket(PacketCloudtrapJetpack.class);
		
		TransformersMod.packetPipeline.registerPacket(PacketBroadcastStealthState.class);
		TransformersMod.packetPipeline.registerPacket(PacketBroadcastTransformationState.class);
		
		TransformersMod.packetPipeline.registerPacket(PacketBroadcastState.class);
		
		TransformersMod.packetPipeline.registerPacket(PacketVehicleNitro.class);
	}
}
