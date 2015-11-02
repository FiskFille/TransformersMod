package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fiskfille.tf.common.tileentity.TileEntityCable;

public class BlockCable extends BlockBasic implements ITileEntityProvider
{
    private Random rand = new Random();

    public BlockCable()
    {
        super(Material.rock);
        setHardness(0.5F);
        setResistance(1.0F);
    }
    
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        return Facing.oppositeSide[side];
    }
    
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
    	int i = BlockHopper.getDirectionFromMetadata(metadata);
    	Block block1 = world.getBlock(x + Facing.offsetsXForSide[i], y + Facing.offsetsYForSide[i], z + Facing.offsetsZForSide[i]);
    	TileEntity tileentity = world.getTileEntity(x, y, z);
    	
    	if (block1 instanceof ICablePowered && tileentity instanceof TileEntityCable)
    	{
    		ICablePowered icablepowered = (ICablePowered)block1;
    		TileEntityCable tile = (TileEntityCable)tileentity;
    		
    		
    		icablepowered.updatePoweredState(world, x + Facing.offsetsXForSide[i], y + Facing.offsetsYForSide[i], z + Facing.offsetsZForSide[i], null);
    		
//    		if (icablepowered.connectTo(this, x, y, z))
//    		{
//    			icablepowered.updatePoweredState(worldObj, x, y, z, signal);
//    		}
    	}
    	
    	super.breakBlock(world, x, y, z, block, metadata);
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 p_149731_5_, Vec3 p_149731_6_)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.collisionRayTrace(world, x, y, z, p_149731_5_, p_149731_6_);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
    	setBlockBounds(0, 0, 0, 1, 1, 1);
    }

    public boolean renderAsNormalBlock()
    {
    	return false;
    }

    public int getRenderType()
    {
    	return -1;
    }

    public boolean isOpaqueCube()
    {
    	return false;
    }

    public boolean hasTileEntity()
    {
        return true;
    }

    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityCable();
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
    	blockIcon = par1IIconRegister.registerIcon("iron_block");
    }
}
