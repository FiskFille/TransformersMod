package fiskfille.tf.packet;

import fiskfille.tf.TransformersMod;

public class TFPacketRegistry 
{	
	public static void registerPackets()
	{
		TransformersMod.packetPipeline.registerPacket(PacketHandleTransformation.class);
	}
}
