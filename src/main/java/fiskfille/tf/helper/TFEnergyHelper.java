package fiskfille.tf.helper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;

public class TFEnergyHelper
{
	public static String formatNumber(float f)
	{
		String s = (long)f + "";
		
		if (!s.contains("E"))
		{
			String s1 = "";
			
			for (int i = 0; i < s.length(); ++i)
			{
				s1 += s.charAt(i);

				if ((s.length() - i) % 3 == 1)
				{
					s1 += ",";
				}
			}

			return s1.substring(0, s1.length() - 1);
		}
		
		return s;
	}
	
	public static boolean isInRange(TileEntity transmitterTile, TileEntity receiverTile)
	{
		try
		{
			IEnergyTransmitter transmitter = (IEnergyTransmitter)transmitterTile;
			IEnergyReceiver receiver = (IEnergyReceiver)receiverTile;
			Vec3 src = transmitter.getEnergyOutputOffset().addVector(transmitterTile.xCoord + 0.5F, transmitterTile.yCoord + 0.5F, transmitterTile.zCoord + 0.5F);
			Vec3 dst = receiver.getEnergyInputOffset().addVector(receiverTile.xCoord + 0.5F, receiverTile.yCoord + 0.5F, receiverTile.zCoord + 0.5F);
			
			return src.distanceTo(dst) <= transmitter.getRange();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isInRange(TileEntity transmitterTile, ChunkCoordinates dstCoords)
	{
		try
		{
			IEnergyTransmitter transmitter = (IEnergyTransmitter)transmitterTile;
			Vec3 src = transmitter.getEnergyOutputOffset().addVector(transmitterTile.xCoord + 0.5F, transmitterTile.yCoord + 0.5F, transmitterTile.zCoord + 0.5F);
			Vec3 dst = Vec3.createVectorHelper(dstCoords.posX + 0.5F, dstCoords.posY + 0.5F, dstCoords.posZ + 0.5F);
			
			return src.distanceTo(dst) <= transmitter.getRange();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
