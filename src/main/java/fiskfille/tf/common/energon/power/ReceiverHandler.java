package fiskfille.tf.common.energon.power;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

public class ReceiverHandler
{
	public List<ChunkCoordinates> receiverCoords = Lists.newArrayList();
	public List<TileEntity> receivers = Lists.newArrayList();
	public TileEntity tileentity;
	
	public ReceiverHandler(TileEntity tile)
	{
		tileentity = tile;
	}
	
	public void onUpdate(World world)
	{
		receivers.clear();
		
		for (int i = 0; i < receiverCoords.size(); ++i)
		{
			ChunkCoordinates coords = receiverCoords.get(i);
			TileEntity tile = world.getTileEntity(coords.posX, coords.posY, coords.posZ);
			
			if (tile instanceof IEnergyReceiver && ((IEnergyReceiver)tile).canReceiveEnergy(tileentity))
			{
				receivers.add(tile);
			}
			else
			{
				receiverCoords.remove(i);
			}
		}
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		NBTTagCompound nbttagcompound = nbt.getCompoundTag("EmB");
		NBTTagList nbttaglist = nbttagcompound.getTagList("Receivers", 10);
		receiverCoords.clear();
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			ChunkCoordinates coords = new ChunkCoordinates();
			coords.posX = nbttagcompound1.getInteger("x");
			coords.posY = nbttagcompound1.getInteger("y");
			coords.posZ = nbttagcompound1.getInteger("z");
			receiverCoords.add(coords);
		}
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		NBTTagCompound nbttagcompound = nbt.getCompoundTag("EmB");
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < receiverCoords.size(); ++i)
		{
			NBTTagCompound nbttagcompound1 = new NBTTagCompound(); 
			ChunkCoordinates coords = receiverCoords.get(i);
			nbttagcompound1.setInteger("x", coords.posX);
			nbttagcompound1.setInteger("y", coords.posY);
			nbttagcompound1.setInteger("z", coords.posZ);
			nbttaglist.appendTag(nbttagcompound1);
		}
		
		nbttagcompound.setTag("Receivers", nbttaglist);
		nbt.setTag("EmB", nbttagcompound);
	}
}
