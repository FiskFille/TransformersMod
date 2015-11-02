package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fiskfille.tf.common.groundbridge.CableSignal;
import fiskfille.tf.common.tileentity.TileEntityCable;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class BlockGroundBridgeFrame extends Block implements ICablePowered
{
    public BlockGroundBridgeFrame()
    {
        super(Material.circuits);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }
    
    public ForgeDirection getFrameDirection(World world, int x, int y, int z)
    {
    	if (BlockGroundBridgeTeleporter.isNorthSouthFacingFramePresent(world, x, y, z))
    	{
    		return ForgeDirection.NORTH;
    	}
    	else if (BlockGroundBridgeTeleporter.isEastWestFacingFramePresent(world, x, y, z))
    	{
    		return ForgeDirection.EAST;
    	}
    	
    	return null;
    }
    
    public void replaceBlock(World world, int x, int y, int z, Block block, Block replacement)
    {
    	if (world.getBlock(x, y, z) == block)
    	{
    		world.setBlock(x, y, z, replacement);
    	}
    }
    
    @Override
	public void updatePoweredState(World world, int x, int y, int z, CableSignal signal)
    {
    	if (signal == null || signal.message.equals("ground_bridge_deactivate"))
		{
			for (int i = 0; i < 5; ++i)
	        {
	            for (int j = 0; j < 3; ++j)
	            {
	                replaceBlock(world, x - 1 + j, y + 1 + i, z, TFBlocks.groundBridgeTeleporter, Blocks.air);
	                replaceBlock(world, x - 2 + i, y + 2 + j, z, TFBlocks.groundBridgeTeleporter, Blocks.air);
	                replaceBlock(world, x, y + 1 + i, z - 1 + j, TFBlocks.groundBridgeTeleporter, Blocks.air);
	            	replaceBlock(world, x, y + 2 + j, z - 2 + i, TFBlocks.groundBridgeTeleporter, Blocks.air);
	            }
	        }
		}
    	else if (signal != null && signal.source instanceof TileEntityControlPanel && signal.message.equals("ground_bridge_activate"))
		{
    		TileEntityControlPanel tile = (TileEntityControlPanel)signal.source;
    		
    		if (tile.errors.isEmpty())
    		{
    			TFBlocks.groundBridgeTeleporter.spawnTeleporter(world, x, y, z, tile);
    			
    			if (tile.portalDirection % 2 == 0)
    	        {
    				BlockGroundBridgeTeleporter.fillNorthFacingFrame(world, tile.destX, tile.destY - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, tile, true);
    				
//    	            for (int i = 0; i < 5; ++i)
//    	            {
//    	                for (int j = 0; j < 3; ++j)
//    	                {
//    	                	world.setBlock(tile.destX - 1 + j, tile.destY - 2 + i, tile.destZ, TFBlocks);
//	                    	world.setBlock(tile.destX - 2 + i, tile.destY - 1 + j, tile.destZ);
//    	                }
//    	            }
    	        }
    	        else
    	        {
    	        	BlockGroundBridgeTeleporter.fillEastFacingFrame(world, tile.destX, tile.destY - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, tile, true);
    	        	
//    	            for (int i = 0; i < 5; ++i)
//    	            {
//    	                for (int j = 0; j < 3; ++j)
//    	                {
//    	                    if (world.getBlock(tile.destX, tile.destY - 2 + i, tile.destZ - 1 + j) != b || world.getBlock(tile.destX, tile.destY - 1 + j, tile.destZ - 2 + i) != b)
//    	                    {
//    	                        return false;
//    	                    }
//    	                }
//    	            }
    	        }
    		}
		}
		
		if (signal != null && signal.source instanceof TileEntityControlPanel)
		{
			TileEntityControlPanel tile = (TileEntityControlPanel)signal.source;
			tile.groundBridgeFramePos = new BlockCoordinates(x, y, z);
			
			if (signal.message.equals("ground_bridge_deactivate"))
			{
				for (int i = 0; i < 5; ++i)
		        {
		            for (int j = 0; j < 3; ++j)
		            {
		                replaceBlock(world, tile.destX - 1 + j, tile.destY + 1 + i - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, Blocks.air);
		                replaceBlock(world, tile.destX - 2 + i, tile.destY + 2 + j - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, Blocks.air);
		                replaceBlock(world, tile.destX, tile.destY + 1 + i - 3, tile.destZ - 1 + j, TFBlocks.groundBridgeTeleporter, Blocks.air);
		            	replaceBlock(world, tile.destX, tile.destY + 2 + j - 3, tile.destZ - 2 + i, TFBlocks.groundBridgeTeleporter, Blocks.air);
		            }
		        }
			}
		}
	}

	@Override
	public boolean connectTo(TileEntityCable cable, int x, int y, int z)
	{
		return getFrameDirection(cable.getWorldObj(), x, y, z) != null;
	}
}
