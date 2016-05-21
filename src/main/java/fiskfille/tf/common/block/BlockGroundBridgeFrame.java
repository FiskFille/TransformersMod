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
    
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityGroundBridgeFrame();
    }
}
