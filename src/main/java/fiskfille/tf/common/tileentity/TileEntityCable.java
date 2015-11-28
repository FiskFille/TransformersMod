package fiskfille.tf.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import fiskfille.tf.common.block.ICablePowered;
import fiskfille.tf.common.groundbridge.CableSignal;

public class TileEntityCable extends TileEntity
{
	public CableSignal signal;
	public int signalStrength;

    public void updateEntity()
    {
    	int x = xCoord;
    	int y = yCoord;
    	int z = zCoord;
    	
    	
    	
    	int i = BlockHopper.getDirectionFromMetadata(getBlockMetadata());
    	transferSignalTo(getCable(x + Facing.offsetsXForSide[i], y + Facing.offsetsYForSide[i], z + Facing.offsetsZForSide[i]));
    	transferSignalTo(x + Facing.offsetsXForSide[i], y + Facing.offsetsYForSide[i], z + Facing.offsetsZForSide[i]);
    	    	
    	if (signal != null && signal.message.equals("ground_bridge_activate"))
    	{
//    		worldObj.spawnParticle("flame", x + 0.5F, y + 1.5F, z + 0.5F, 0, 0, 0);
    	}
    	
    	signal = null;
    }
    
    public TileEntityCable getCable(int x, int y, int z)
    {
    	TileEntity tile = worldObj.getTileEntity(x, y, z);
    	
    	if (tile instanceof TileEntityCable)
    	{
    		return (TileEntityCable)tile;
    	}
    	
    	return null;
    }
    
    public void transferSignalTo(TileEntityCable tile)
    {
    	if (tile != null && tile.signalStrength < signalStrength)
    	{
    		tile.signal = signal;
    		tile.signalStrength = signalStrength - 1;
    	}
    }
    
    public void transferSignalTo(int x, int y, int z)
    {
    	if (worldObj.getBlock(x, y, z) instanceof ICablePowered)
    	{
    		ICablePowered icablepowered = (ICablePowered)getWorldObj().getBlock(x, y, z);
    		
    		if (icablepowered.connectTo(this, x, y, z))
    		{
    			icablepowered.updatePoweredState(worldObj, x, y, z, signal);
    		}
    	}
    }

    public void markBlockForUpdate()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
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
