package fiskfille.tf.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import fiskfille.tf.TFHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.achievement.TFAchievements;
import fiskfille.tf.packet.PacketHandleStealthTransformation;
import fiskfille.tf.packet.PacketHandleTransformation;
import fiskfille.tf.packet.PacketSyncTransformationStates;
import fiskfille.tf.transformer.Transformer;

public class TFDataManager 
{
	public static Map<UUID, Integer> transformationTimerClient = new HashMap<UUID, Integer>();
	public static Map<UUID, Integer> stealthModeTimerClient = new HashMap<UUID, Integer>();

	public static void setInVehicleMode(EntityPlayer player, boolean vehicleMode)
	{
		if (vehicleMode != TFPlayerData.getData(player).mode)
		{
			player.triggerAchievement(TFAchievements.transform);
			
			if (player.worldObj.isRemote)
			{
				TransformersMod.packetPipeline.sendToServer(new PacketHandleTransformation(player, vehicleMode));
			}
			else
			{
				TransformersMod.packetPipeline.sendToDimension(new PacketHandleTransformation(player, vehicleMode), player.dimension);
			}

			TFPlayerData.getData(player).mode = vehicleMode;
		}
	}
	
	public static void setInStealthMode(EntityPlayer player, boolean stealthMode)
	{
		if (stealthMode != TFPlayerData.getData(player).stealthMode)
		{
			if (isInVehicleMode(player))
			{
				if (player.worldObj.isRemote)
				{
					TransformersMod.packetPipeline.sendToServer(new PacketHandleStealthTransformation(player, stealthMode));
				}
				else
				{
					TransformersMod.packetPipeline.sendToDimension(new PacketHandleStealthTransformation(player, stealthMode), player.dimension);
				}

				TFPlayerData.getData(player).stealthMode = stealthMode;
			}
		}
	}

	public static void setTransformationTimer(EntityPlayer player, int timer)
	{
		transformationTimerClient.put(player.getUniqueID(), timer);
	}

	public static void setStealthModeTimer(EntityPlayer player, int timer)
	{
		stealthModeTimerClient.put(player.getUniqueID(), timer);
	}
	
	public static boolean isInVehicleMode(EntityPlayer player)
	{
		return TFPlayerData.getData(player).mode && TFHelper.isPlayerTransformer(player);
	}

	public static boolean isInStealthMode(EntityPlayer player)
	{
		Transformer transformer = TFHelper.getTransformer(player);

		if (transformer != null)
		{
			return transformer.hasStealthForce(player) && isInVehicleMode(player) && TFPlayerData.getData(player).stealthMode;
		}
		
		return false;
	}
	
	public static int getTransformationTimer(EntityPlayer player)
	{
		Integer timer = transformationTimerClient.get(player.getUniqueID());
		
		return timer != null ? timer : 0;
	}
	
	public static int getStealthModeTimer(EntityPlayer player)
	{
		Integer timer = stealthModeTimerClient.get(player.getUniqueID());
		
		return timer != null ? timer : 0;
	}

	public static void toggleVehicleMode(EntityPlayer player) 
	{
		setInVehicleMode(player, !TFPlayerData.getData(player).mode);
	}
	
	public static void toggleStealthMode(EntityPlayer player) 
	{
		setInStealthMode(player, !TFPlayerData.getData(player).stealthMode);
	}

	public static void updateTransformationStatesFor(EntityPlayer player) 
	{
		Map<UUID, Boolean[]> states = new HashMap<UUID, Boolean[]>();

		for (Object obj : player.worldObj.playerEntities) 
		{
			if (obj instanceof EntityPlayer)
			{
				EntityPlayer cPlayer = (EntityPlayer) obj;

				TFPlayerData data = TFPlayerData.getData(cPlayer);

				if (data != null)
				{
					states.put(cPlayer.getUniqueID(), new Boolean[]{data.mode, data.stealthMode});
				}
			}
		}

		TransformersMod.packetPipeline.sendTo(new PacketSyncTransformationStates(states), (EntityPlayerMP) player);
	}
}