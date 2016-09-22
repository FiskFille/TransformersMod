package fiskfille.tf.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;

public class TileEntityEmbTest extends TileEntity implements IEnergyReceiver
{
	public EnergyStorage storage = new EnergyStorage(32000);
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}
	
	@Override
	public float receiveEnergy(float amount)
	{
		return storage.add(amount);
	}
	
	@Override
	public float extractEnergy(float amount)
	{
		return storage.remove(amount);
	}
	
	@Override
	public float getEnergy()
	{
		return storage.getEnergy();
	}
	
	@Override
	public float getMaxEnergy()
	{
		return storage.getMaxEnergy();
	}

	@Override
	public boolean canReceiveEnergy(TileEntity from)
	{
		return true;
	}

	@Override
	public Vec3 getEnergyInputOffset()
	{
		return Vec3.createVectorHelper(0, 0.5D, 0);
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound syncData = new NBTTagCompound();
		writeToNBT(syncData);

		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
}
