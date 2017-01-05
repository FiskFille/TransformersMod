package fiskfille.tf.server;

import fiskfille.tf.main.MainClass;

public class TFPacketRegistry 
{	
	public static void registerPackets()
	{
		MainClass.packetPipeline.registerPacket(PacketHandleTransformation.class);
	}
}
