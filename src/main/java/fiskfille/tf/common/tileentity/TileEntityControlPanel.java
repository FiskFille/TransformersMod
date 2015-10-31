package fiskfille.tf.common.tileentity;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.NotEnoughDataDecoderException;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeChunkManager;

import com.google.common.collect.Lists;

import fiskfille.tf.common.block.BlockGroundBridgeControl;
import fiskfille.tf.common.groundbridge.EnumError;
import fiskfille.tf.common.network.MessageControlPanel;
import fiskfille.tf.common.network.base.TFNetworkManager;

public class TileEntityControlPanel extends TileEntity
{
	public int destX;
	public int destY;
	public int destZ;
	public int portalDirection;
	public Integer[][] switches = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
	public boolean activationLeverState = false;
	public boolean activationLeverCoverState = false;
	public float activationLeverTimer;
	public float activationLeverCoverTimer;
	
	public List<EnumError> errors = Lists.newArrayList();

    public void updateEntity()
    {
    	if (!BlockGroundBridgeControl.isBlockSideOfPanel(blockMetadata))
    	{
    		errors.clear();
    		destX = xCoord;
    		destY = yCoord;
    		destZ = zCoord;
    		
    		int[] aint = {1, 10, 100, 1000};
    		
    		for (int i = 0; i < switches[0].length; ++i)
    		{
    			destX += switches[0][i] * aint[i];
    		}
    		
    		for (int i = 0; i < switches[1].length; ++i)
    		{
    			destY += switches[1][i] * aint[i];
    		}
    		
    		for (int i = 0; i < switches[2].length; ++i)
    		{
    			destZ += switches[2][i] * aint[i];
    		}
    		
    		if (Math.sqrt(getDistanceFrom(destX, destY, destZ)) <= 100)
    		{
//    			errors.add(EnumError.INVALID_COORDS);
    		}
    		
    		Block block = Blocks.air;
    		
    		if (portalDirection % 2 == 0)
    		{
    			for (int i = 0; i < 5; ++i)
    	        {
    	            for (int j = 0; j < 3; ++j)
    	            {
    	                if (worldObj.getBlock(destX - 1 + j, destY - 2 + i, destZ) != block || worldObj.getBlock(destX - 2 + i, destY - 1 + j, destZ) != block)
    	                {
    	            		if (!errors.contains(EnumError.NOT_ENOUGH_SPACE))
    	            		{
    	            			errors.add(EnumError.NOT_ENOUGH_SPACE);
    	            		}
    	                }
    	            }
    	        }
    		}
    		else
    		{
    			for (int i = 0; i < 5; ++i)
    	        {
    	            for (int j = 0; j < 3; ++j)
    	            {
    	            	if (worldObj.getBlock(destX, destY - 2 + i, destZ - 1 + j) != block || worldObj.getBlock(destX, destY - 1 + j, destZ - 2 + i) != block)
    	            	{
    	            		if (!errors.contains(EnumError.NOT_ENOUGH_SPACE))
    	            		{
    	            			errors.add(EnumError.NOT_ENOUGH_SPACE);
    	            		}
    	            	}
    	            }
    	        }
    		}
    		
    		if (!errors.isEmpty() && !activationLeverState)
    		{
    			activationLeverCoverState = false;
    		}
    		else
    		{
    			activationLeverCoverState = true;
    		}
    		
    		if (activationLeverState)
    		{
    			if (activationLeverTimer < 1)
    			{
    				activationLeverTimer += 0.125F;
    			}
    		}
    		else
    		{
    			if (activationLeverTimer > 0)
    			{
    				activationLeverTimer -= 0.125F;
    			}
    		}
    		
    		if (activationLeverCoverState)
    		{
    			if (activationLeverCoverTimer < 1)
    			{
    				activationLeverCoverTimer += 0.1F;
    			}
    		}
    		else
    		{
    			if (activationLeverCoverTimer > 0)
    			{
    				activationLeverCoverTimer -= 0.1F;
    			}
    		}
    		
    		activationLeverTimer = MathHelper.clamp_float(activationLeverTimer, 0, 1);
    		activationLeverCoverTimer = MathHelper.clamp_float(activationLeverCoverTimer, 0, 1);
    	}
    }

	public AxisAlignedBB getRenderBoundingBox()
    {
    	return INFINITE_EXTENT_AABB;
    }
    
    public void changeSwitch(int group, int id, int amount)
    {
    	if (!activationLeverState)
    	{
    		markBlockForUpdate();
    		
    		if (switches[group][id] + amount <= 10 && switches[group][id] + amount >= -10)
        	{
        		switches[group][id] += amount;
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
        portalDirection = nbt.getInteger("PortalDirection");
        activationLeverState = nbt.getBoolean("Lever");
        activationLeverCoverState = nbt.getBoolean("LeverCover");
        
        if (nbt.hasKey("Switches"))
        {
        	NBTTagList nbttaglist = nbt.getTagList("Switches", 10);
            
            for (int i = 0; i < nbttaglist.tagCount(); i++)
            {
                NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
                
                for (int j = 0; j < 4; ++j)
            	{
                	switches[i][j] = nbttagcompound.getInteger("Switch" + (j + 1));
            	}
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("PortalDirection", portalDirection);
        nbt.setBoolean("Lever", activationLeverState);
        nbt.setBoolean("LeverCover", activationLeverCoverState);
        
        NBTTagList nbttaglist = new NBTTagList();
        
        for (int i = 0; i < switches.length; ++i)
        {
        	NBTTagCompound nbttagcompound = new NBTTagCompound();
        	
        	for (int j = 0; j < switches[i].length; ++j)
        	{
        		int value = switches[i][j];
        		nbttagcompound.setInteger("Switch" + (j + 1), value);
        	}
        	
        	nbttaglist.appendTag(nbttagcompound);
        }
        
        nbt.setTag("Switches", nbttaglist);
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
