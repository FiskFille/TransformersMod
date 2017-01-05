package fiskfille.tf.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import akka.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.main.MainClass;
import fiskfille.tf.server.PacketHandleTransformation;
import fiskfille.tf.server.TFPacketPipeline;

public class TFPlayerData 
{
	public static Map<UUID, Integer> transformationTimerClient = new HashMap<UUID, Integer>();
	
	public static void setInVehichleMode(EntityPlayer player, boolean vehichleMode)
	{
		TFPlayerDataNew.getData(player).mode = vehichleMode;
	}

	public static void sync(EntityPlayer player)
	{
		if (player.worldObj.isRemote)
		{
			MainClass.packetPipeline.sendToServer(new PacketHandleTransformation(player.getUniqueID(), isInVehicleMode(player)));
		}
		else
		{
			MainClass.packetPipeline.sendToAll(new PacketHandleTransformation(player.getUniqueID(), isInVehicleMode(player)));
		}
	}
	
	public static void setTransformationTimer(EntityPlayer player, int timer)
	{
		transformationTimerClient.put(player.getUniqueID(), timer);
	}

	public static boolean isInVehicleMode(EntityPlayer player)
	{
		return TFPlayerDataNew.getData(player).mode;
	}

	public static int getTransformationTimer(EntityPlayer player)
	{
		return transformationTimerClient.get(player.getUniqueID()) != null ? transformationTimerClient.get(player.getUniqueID()) : 0;
	}
}