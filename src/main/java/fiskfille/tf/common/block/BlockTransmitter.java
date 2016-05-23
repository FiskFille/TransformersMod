package fiskfille.tf.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class BlockTransmitter extends Block implements ITileEntityProvider
{
    private final Random rand = new Random();

    public BlockTransmitter()
    {
        super(Material.rock);
        setHarvestLevel("pickaxe", 0);
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

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
	{
    	int metadata = world.getBlockMetadata(x, y, z);
    	float f = 0.0625F;
    	
    	if (metadata < 4)
    	{
    		addBox(0, 0, 0, 1, f * 4, 1, world, x, y, z, aabb, list, entity);
    		
    		for (int i = 0; i < 26; ++i)
    		{
            	float width = 1 - 0.6F * ((float)i / 26);
    			float f1 = 1 - width;
    			addBox(f1 / 2, f * (i + 4), f1 / 2, 1 - f1 / 2, f * (i + 5), 1 - f1 / 2, world, x, y, z, aabb, list, entity);
    		}
    	}
    	else if (metadata < 8)
    	{
    		addBox(0, f * 14, 0, 1, 1, 1, world, x, y, z, aabb, list, entity);
    	}
    	else
    	{
    		addBox(0, 0, 0, 1, 1, 1, world, x, y, z, aabb, list, entity);
    	}
    	
    	setBlockBoundsBasedOnState(world, x, y, z);
	}
    
    public void addBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
    	setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    	super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
    	setBlockBoundsBasedOnState(world, x, y, z);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        setBounds(world.getBlockMetadata(x, y, z));
    }

    public void setBounds(int metadata)
    {
        float f = 0.0625F;

        if (metadata < 4)
        {
        	setBlockBounds(0, 0, 0, 1, 3, 1);
        }
        else if (metadata < 8)
        {
        	setBlockBounds(0, -1, 0, 1, 2, 1);
        }
        else
        {
        	setBlockBounds(0, -2, 0, 1, 1, 1);
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (metadata < 8)
        {
            if (world.getBlock(x, y + 1, z) != TFBlocks.transmitter)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
        
        if (metadata >= 4)
        {
        	if (world.getBlock(x, y - 1, z) != TFBlocks.transmitter)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return y >= world.getHeight() - 2 ? false : super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z) && super.canPlaceBlockAt(world, x, y + 2, z);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        int rotation = MathHelper.floor_double((double) ((entity.rotationYaw * 4F) / 360F) + 2.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
        world.setBlock(x, y + 1, z, this, rotation + 4, 2);
        world.setBlock(x, y + 2, z, this, rotation + 8, 2);
    }

    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityTransmitter();
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        blockIcon = par1IIconRegister.registerIcon("stone");
    }
}
