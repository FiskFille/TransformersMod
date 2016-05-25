package fiskfille.tf.common.tileentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.common.util.ForgeDirection;

import com.google.common.collect.Lists;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.block.BlockCoordinates;
import fiskfille.tf.common.block.BlockGroundBridgeControl;
import fiskfille.tf.common.block.BlockGroundBridgeFrame;
import fiskfille.tf.common.block.BlockGroundBridgeTeleporter;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.groundbridge.EnumError;

public class TileEntityControlPanel extends TileEntity/* implements IEnergonPowered*/ // TODO
{
	public int destX;
	public int destY;
	public int destZ;
	public int portalDirection;
	public int srcPortalDirection;
	public Integer[][] switches = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
	public boolean activationLeverState = false;
	public boolean activationLeverCoverState = false;
	public float activationLeverTimer;
	public float prevActivationLeverTimer;
	public float activationLeverCoverTimer;
	public float prevActivationLeverCoverTimer;

	public List<EnumError> errors = Lists.newArrayList();
	public boolean hasSpace;

	public BlockCoordinates groundBridgeFramePos;
	private Ticket chunkTicket;

	public void updateEntity()
	{
		prevActivationLeverTimer = activationLeverTimer;
		prevActivationLeverCoverTimer = activationLeverCoverTimer;
		
		if (!BlockGroundBridgeControl.isBlockSideOfPanel(getBlockMetadata()))
		{
			loadChunks();
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

			if (groundBridgeFramePos != null)
			{
				int x = groundBridgeFramePos.x;
				int y = groundBridgeFramePos.y;
				int z = groundBridgeFramePos.z;

				if (BlockGroundBridgeFrame.getFrameDirection(worldObj, x, y, z) == null)
				{
					groundBridgeFramePos = null;
				}
			}
			
			boolean flag = false;

			if (!activationLeverState)
			{
				List<TileEntity> list = new ArrayList<TileEntity>(worldObj.loadedTileEntityList);
				Collections.sort(list, new Comparator<TileEntity>()
				{
					public int compare(TileEntity arg0, TileEntity arg1)
					{
						return Integer.compare((int)Math.sqrt(getDistanceFrom(arg0.xCoord, arg0.zCoord, arg0.yCoord)), (int)Math.sqrt(getDistanceFrom(arg1.xCoord, arg1.zCoord, arg1.yCoord)));
					}
				});

				for (TileEntity tileentity : list)
				{
					if (Math.sqrt(getDistanceFrom(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)) <= 20)
					{
						if (tileentity instanceof TileEntityGroundBridgeFrame)
						{
							TileEntityGroundBridgeFrame tile = (TileEntityGroundBridgeFrame)tileentity;
							ForgeDirection direction = BlockGroundBridgeFrame.getFrameDirection(worldObj, tile.xCoord, tile.yCoord, tile.zCoord);
							
							if (direction != null)
							{
								flag = isPortalObstructed(tile.xCoord, tile.yCoord, tile.zCoord, direction);
								groundBridgeFramePos = new BlockCoordinates(tile.xCoord, tile.yCoord, tile.zCoord);
								break;
							}
						}
					}
				}
			}

			if (groundBridgeFramePos == null)
			{
				errors.add(EnumError.NO_PORTAL_LINKED);
			}
			
			if (flag)
			{
				errors.add(EnumError.PORTAL_OBSTRUCTED);
			}

			if (Math.sqrt(getDistanceFrom(destX, destY, destZ)) <= 64)
			{
//				errors.add(EnumError.INVALID_COORDS);
			}
			
			if (destY - 2 <= 0 || destY + 2 >= worldObj.getHeight())
			{
				errors.add(EnumError.OUT_OF_BOUNDS);
			}

			if (!worldObj.isRemote)
			{
				boolean newSpace = checkForSpace();

				if (newSpace != hasSpace)
				{
					markBlockForUpdate();
					hasSpace = newSpace;
				}
			}

			if (!hasSpace)
			{
				errors.add(EnumError.NOT_ENOUGH_SPACE);
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

			if (!activationLeverState && groundBridgeFramePos != null)
			{
				int x = groundBridgeFramePos.x;
				int y = groundBridgeFramePos.y;
				int z = groundBridgeFramePos.z;
				srcPortalDirection = worldObj.getBlockMetadata(x, y, z);
			}

			if (errors.isEmpty() && activationLeverState && groundBridgeFramePos != null)
			{
				int x = groundBridgeFramePos.x;
				int y = groundBridgeFramePos.y;
				int z = groundBridgeFramePos.z;
				BlockGroundBridgeTeleporter.spawnTeleporter(worldObj, x, y, z, this);

				if (portalDirection % 2 == 0)
				{
					BlockGroundBridgeTeleporter.fillNorthFacingFrame(worldObj, destX, destY - 3, destZ, TFBlocks.groundBridgeTeleporter, this, true);
				}
				else
				{
					BlockGroundBridgeTeleporter.fillEastFacingFrame(worldObj, destX, destY - 3, destZ, TFBlocks.groundBridgeTeleporter, this, true);
				}
			}
		}
	}
	
	public boolean isPortalObstructed(int x, int y, int z, ForgeDirection direction)
	{
		if (direction == ForgeDirection.NORTH)
		{
			for (int i = 0; i < 5; ++i)
	        {
	            for (int j = 0; j < 3; ++j)
	            {
	            	if (!(worldObj.getBlock(x - 1 + j, y + 1 + i, z) == Blocks.air || worldObj.getBlock(x - 1 + j, y + 1 + i, z) == TFBlocks.groundBridgeTeleporter) || !(worldObj.getBlock(x - 2 + i, y + 2 + j, z) == Blocks.air || worldObj.getBlock(x - 2 + i, y + 2 + j, z) == TFBlocks.groundBridgeTeleporter))
	                {
	                	return true;
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
	            	if (!(worldObj.getBlock(x, y + 1 + i, z - 1 + j) == Blocks.air || worldObj.getBlock(x, y + 1 + i, z - 1 + j) == TFBlocks.groundBridgeTeleporter) || !(worldObj.getBlock(x, y + 2 + j, z - 2 + i) == Blocks.air || worldObj.getBlock(x, y + 2 + j, z - 2 + i) == TFBlocks.groundBridgeTeleporter))
	                {
	                	return true;
	                }
	            }
	        }
		}
		
		return false;
	}

	public boolean checkForSpace()
	{
		Block b = Blocks.air;
		Block b1 = TFBlocks.groundBridgeTeleporter;

		if (portalDirection % 2 == 0)
		{
			for (int i = 0; i < 5; ++i)
			{
				for (int j = 0; j < 3; ++j)
				{
					if (!(worldObj.getBlock(destX - 1 + j, destY - 2 + i, destZ) == b || worldObj.getBlock(destX - 1 + j, destY - 2 + i, destZ) == b1) || !(worldObj.getBlock(destX - 2 + i, destY - 1 + j, destZ) == b || worldObj.getBlock(destX - 2 + i, destY - 1 + j, destZ) == b1))
					{
						return false;
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
					if (!(worldObj.getBlock(destX, destY - 2 + i, destZ - 1 + j) == b || worldObj.getBlock(destX, destY - 2 + i, destZ - 1 + j) == b1) || !(worldObj.getBlock(destX, destY - 1 + j, destZ - 2 + i) == b || worldObj.getBlock(destX, destY - 1 + j, destZ - 2 + i) == b1))
					{
						return false;
					}
				}
			}
		}

		return true;
	}

	public int getSrcPortalDirection()
	{
		int i = 0;

		if (groundBridgeFramePos != null)
		{
			int x = groundBridgeFramePos.x;
			int y = groundBridgeFramePos.y;
			int z = groundBridgeFramePos.z;

			if (BlockGroundBridgeFrame.getFrameDirection(worldObj, x, y, z) == ForgeDirection.EAST)
			{
				i = 1;
			}
		}

		return i + srcPortalDirection * 2;
	}

	public AxisAlignedBB getRenderBoundingBox()
	{
		return super.getRenderBoundingBox().addCoord(0, 0.5D, 0);
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

        boolean updateTicket = true;

        if (chunkTicket != null)
        {
            NBTTagCompound modData = chunkTicket.getModData();

            if (destX == modData.getInteger("destX") && destZ == modData.getInteger("destZ"))
            {
                updateTicket = false;
            }
        }

        if (updateTicket)
        {
            ForgeChunkManager.unforceChunk(chunkTicket, new ChunkCoordIntPair(destX >> 4, destZ >> 4));
            chunkTicket = null;
        }
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		portalDirection = nbt.getInteger("PortalDirection");
		srcPortalDirection = nbt.getInteger("SrcPortalDirection");
		activationLeverState = nbt.getBoolean("Lever");
		activationLeverCoverState = nbt.getBoolean("LeverCover");
		hasSpace = nbt.getBoolean("HasSpace");
		destX = nbt.getInteger("DestX");
		destY = nbt.getInteger("DestY");
		destZ = nbt.getInteger("DestZ");

		if (nbt.getBoolean("ReadFramePos"))
		{
			groundBridgeFramePos = new BlockCoordinates(nbt.getInteger("FrameX"), nbt.getInteger("FrameY"), nbt.getInteger("FrameZ"));
		}

		if (nbt.hasKey("Switches"))
		{
			NBTTagList nbttaglist = nbt.getTagList("Switches", 10);

			for (int i = 0; i < nbttaglist.tagCount(); i++)
			{
				NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);

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
		nbt.setInteger("SrcPortalDirection", srcPortalDirection);
		nbt.setBoolean("Lever", activationLeverState);
		nbt.setBoolean("LeverCover", activationLeverCoverState);
		nbt.setBoolean("HasSpace", hasSpace);
		nbt.setBoolean("ReadFramePos", groundBridgeFramePos != null);
		nbt.setInteger("DestX", destX);
		nbt.setInteger("DestY", destY);
		nbt.setInteger("DestZ", destZ);

		if (groundBridgeFramePos != null)
		{
			nbt.setInteger("FrameX", groundBridgeFramePos.x);
			nbt.setInteger("FrameY", groundBridgeFramePos.y);
			nbt.setInteger("FrameZ", groundBridgeFramePos.z);
		}

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

	public void loadChunks()
	{
		if (!worldObj.isRemote)
		{
			while (chunkTicket == null)
			{
				chunkTicket = ForgeChunkManager.requestTicket(TransformersMod.instance, worldObj, Type.NORMAL);
			}
			
			if (chunkTicket == null)
			{
				System.err.println("Unable to load chunks!");
			}
			else
            {
                NBTTagCompound modData = chunkTicket.getModData();

                modData.setInteger("blockX", xCoord);
                modData.setInteger("blockY", yCoord);
                modData.setInteger("blockZ", zCoord);
                modData.setInteger("destX", destX);
                modData.setInteger("destZ", destZ);

                ForgeChunkManager.forceChunk(chunkTicket, new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4));
                ForgeChunkManager.forceChunk(chunkTicket, new ChunkCoordIntPair(destX >> 4, destZ >> 4));
            }
		}
	}

	public void unloadChunks()
	{
		ForgeChunkManager.unforceChunk(chunkTicket, new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4));
	}
	
	public void loadTicket(Ticket ticket)
	{
		if (chunkTicket == null)
		{
			chunkTicket = ticket;
		}
		
		ChunkCoordIntPair loadChunk = new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4);
		ForgeChunkManager.forceChunk(ticket, loadChunk);
        NBTTagCompound modData = chunkTicket.getModData();
        ChunkCoordIntPair destChunk = new ChunkCoordIntPair(modData.getInteger("destX") >> 4, modData.getInteger("destZ") >> 4);
        ForgeChunkManager.forceChunk(ticket, destChunk);
	}

//	@Override
//	public boolean canBePowered()
//	{
//		return !BlockGroundBridgeControl.isBlockSideOfPanel(getBlockMetadata());
//	}
//
//	@Override
//	public Vec3 getPowerInputOffset()
//	{
//		Vec3 vec3 = Vec3.createVectorHelper(-0.055F, 0.175F, -0.5F);
//        float pitch = 0;
//        float yaw = getBlockMetadata() * 90 + 180;
//        vec3.rotateAroundX(-pitch * (float)Math.PI / 180.0F);
//        vec3.rotateAroundY(-yaw * (float)Math.PI / 180.0F);
//        
//		return vec3;
//	}
}
