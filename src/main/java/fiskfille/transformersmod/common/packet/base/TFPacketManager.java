package fiskfille.transformersmod.common.packet.base;

import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.common.packet.PacketBroadcastState;
import fiskfille.transformersmod.common.packet.PacketBroadcastStealthState;
import fiskfille.transformersmod.common.packet.PacketBroadcastTransformationState;
import fiskfille.transformersmod.common.packet.PacketCloudtrapJetpack;
import fiskfille.transformersmod.common.packet.PacketHandleStealthTransformation;
import fiskfille.transformersmod.common.packet.PacketHandleTransformation;
import fiskfille.transformersmod.common.packet.PacketSyncTransformationStates;
import fiskfille.transformersmod.common.packet.PacketTransformersAction;
import fiskfille.transformersmod.common.packet.PacketVehicleNitro;
import fiskfille.transformersmod.common.packet.PacketVurpSniperShoot;

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
