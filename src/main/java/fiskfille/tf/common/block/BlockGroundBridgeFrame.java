package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeFrame;

public class BlockGroundBridgeFrame extends Block implements ITileEntityProvider
{
    public BlockGroundBridgeFrame()
    {
        super(Material.circuits);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	int metadata = world.getBlockMetadata(x, y, z);
    	
    	if (getFrameDirection(world, x, y, z) != null)
    	{
    		if (metadata == 0)
    		{
    			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
    		}
    		else
    		{
    			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
    		}
    		
    		return true;
    	}
    	
        return false;
    }
    
    public static ForgeDirection getFrameDirection(World world, int x, int y, int z)
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
    
    
//    @Override
//	public void updatePoweredState(World world, int x, int y, int z, CableSignal signal)
//    {
//    	if (signal == null || signal.message.equals("ground_bridge_deactivate"))
//		{
//			for (int i = 0; i < 5; ++i)
//	        {
//	            for (int j = 0; j < 3; ++j)
//	            {
//	                replaceBlock(world, x - 1 + j, y + 1 + i, z, TFBlocks.groundBridgeTeleporter, Blocks.air);
//	                replaceBlock(world, x - 2 + i, y + 2 + j, z, TFBlocks.groundBridgeTeleporter, Blocks.air);
//	                replaceBlock(world, x, y + 1 + i, z - 1 + j, TFBlocks.groundBridgeTeleporter, Blocks.air);
//	            	replaceBlock(world, x, y + 2 + j, z - 2 + i, TFBlocks.groundBridgeTeleporter, Blocks.air);
//	            }
//	        }
//		}
//    	else if (signal != null && signal.source instanceof TileEntityControlPanel && signal.message.equals("ground_bridge_activate"))
//		{
//    		TileEntityControlPanel tile = (TileEntityControlPanel)signal.source;
//    		
//    		if (tile.errors.isEmpty())
//    		{
//    			TFBlocks.groundBridgeTeleporter.spawnTeleporter(world, x, y, z, tile);
//    			
//    			if (tile.portalDirection % 2 == 0)
//    	        {
//    				BlockGroundBridgeTeleporter.fillNorthFacingFrame(world, tile.destX, tile.destY - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, tile, true);
//    	        }
//    	        else
//    	        {
//    	        	BlockGroundBridgeTeleporter.fillEastFacingFrame(world, tile.destX, tile.destY - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, tile, true);
//    	        }
//    		}
//		}
//		
//		if (signal != null && signal.source instanceof TileEntityControlPanel)
//		{
//			TileEntityControlPanel tile = (TileEntityControlPanel)signal.source;
//			tile.groundBridgeFramePos = new BlockCoordinates(x, y, z);
//			
//			if (signal.message.equals("ground_bridge_deactivate"))
//			{
//				for (int i = 0; i < 5; ++i)
//		        {
//		            for (int j = 0; j < 3; ++j)
//		            {
//		                replaceBlock(world, tile.destX - 1 + j, tile.destY + 1 + i - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, Blocks.air);
//		                replaceBlock(world, tile.destX - 2 + i, tile.destY + 2 + j - 3, tile.destZ, TFBlocks.groundBridgeTeleporter, Blocks.air);
//		                replaceBlock(world, tile.destX, tile.destY + 1 + i - 3, tile.destZ - 1 + j, TFBlocks.groundBridgeTeleporter, Blocks.air);
//		            	replaceBlock(world, tile.destX, tile.destY + 2 + j - 3, tile.destZ - 2 + i, TFBlocks.groundBridgeTeleporter, Blocks.air);
//		            }
//		        }
//			}
//		}
//	}
//
//	@Override
//	public boolean connectTo(TileEntityCable cable, int x, int y, int z)
//	{
//		return getFrameDirection(cable.getWorldObj(), x, y, z) != null;
//	}
    
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityGroundBridgeFrame();
    }
}
