package fiskfille.tf.block;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fiskfille.tf.main.MainClass;
import fiskfille.tf.main.TFItems;
import fiskfille.tf.tileentity.TileEntityCrystal;

public class BlockCrystal extends BlockBasic implements ITileEntityProvider
{
	private Random rand = new Random();
	
	public BlockCrystal()
	{
		super(Material.glass);
		this.setCreativeTab(MainClass.tabTransformers);
	}
	
    protected boolean canSilkHarvest()
    {
        return true;
    }
	
    public int quantityDropped(Random random)
    {
        return random.nextInt(3) + 2;
    }
	
    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_)
    {
        return TFItems.energonCrystalPiece;
    }
	
    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
        return MathHelper.getRandomIntegerInRange(rand, 0, 2);
    }
    
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
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
    
    private boolean func_150107_m(World world, int x, int y, int z)
    {
        if (World.doesBlockHaveSolidTopSurface(world, x, y, z))
        {
            return true;
        }
        else
        {
            Block block = world.getBlock(x, y, z);
            return block.canPlaceTorchOnTop(world, x, y, z);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.isSideSolid(x - 1, y, z, EAST,  true) ||
               world.isSideSolid(x + 1, y, z, WEST,  true) ||
               world.isSideSolid(x, y, z - 1, SOUTH, true) ||
               world.isSideSolid(x, y, z + 1, NORTH, true) ||
               func_150107_m(world, x, y - 1, z) ||
               func_150107_m(world, x, y + 1, z);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        int j1 = metadata;

        if (side == 0 && this.func_150107_m(world, x, y + 1, z))
        {
            j1 = 6;
        }
        
        if (side == 1 && this.func_150107_m(world, x, y - 1, z))
        {
            j1 = 5;
        }

        if (side == 2 && world.isSideSolid(x, y, z + 1, NORTH, true))
        {
            j1 = 4;
        }

        if (side == 3 && world.isSideSolid(x, y, z - 1, SOUTH, true))
        {
            j1 = 3;
        }

        if (side == 4 && world.isSideSolid(x + 1, y, z, WEST, true))
        {
            j1 = 2;
        }

        if (side == 5 && world.isSideSolid(x - 1, y, z, EAST, true))
        {
            j1 = 1;
        }
        
        return j1;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

        if (p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_) == 0)
        {
            this.onBlockAdded(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z)
    {
        if (world.getBlockMetadata(x, y, z) == 0)
        {
            if (world.isSideSolid(x - 1, y, z, EAST, true))
            {
                world.setBlockMetadataWithNotify(x, y, z, 1, 2);
            }
            else if (world.isSideSolid(x + 1, y, z, WEST, true))
            {
                world.setBlockMetadataWithNotify(x, y, z, 2, 2);
            }
            else if (world.isSideSolid(x, y, z - 1, SOUTH, true))
            {
                world.setBlockMetadataWithNotify(x, y, z, 3, 2);
            }
            else if (world.isSideSolid(x, y, z + 1, NORTH, true))
            {
                world.setBlockMetadataWithNotify(x, y, z, 4, 2);
            }
            else if (this.func_150107_m(world, x, y - 1, z))
            {
                world.setBlockMetadataWithNotify(x, y, z, 5, 2);
            }
            else if (this.func_150107_m(world, x, y + 1, z))
            {
                world.setBlockMetadataWithNotify(x, y, z, 6, 2);
            }
        }

        this.func_150109_e(world, x, y, z);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        this.func_150108_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
    }

    protected boolean func_150108_b(World world, int x, int y, int z, Block block)
    {
        if (this.func_150109_e(world, x, y, z))
        {
            int l = world.getBlockMetadata(x, y, z);
            boolean flag = false;

            if (!world.isSideSolid(x - 1, y, z, EAST, true) && l == 1)
            {
                flag = true;
            }

            if (!world.isSideSolid(x + 1, y, z, WEST, true) && l == 2)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y, z - 1, SOUTH, true) && l == 3)
            {
                flag = true;
            }

            if (!world.isSideSolid(x, y, z + 1, NORTH, true) && l == 4)
            {
                flag = true;
            }

            if (!this.func_150107_m(world, x, y - 1, z) && l == 5)
            {
                flag = true;
            }
            
            if (!this.func_150107_m(world, x, y + 1, z) && l == 6)
            {
                flag = true;
            }

            if (flag)
            {
                this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
                world.setBlockToAir(x, y, z);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    protected boolean func_150109_e(World p_150109_1_, int p_150109_2_, int p_150109_3_, int p_150109_4_)
    {
        if (!this.canPlaceBlockAt(p_150109_1_, p_150109_2_, p_150109_3_, p_150109_4_))
        {
            if (p_150109_1_.getBlock(p_150109_2_, p_150109_3_, p_150109_4_) == this)
            {
                this.dropBlockAsItem(p_150109_1_, p_150109_2_, p_150109_3_, p_150109_4_, p_150109_1_.getBlockMetadata(p_150109_2_, p_150109_3_, p_150109_4_), 0);
                p_150109_1_.setBlockToAir(p_150109_2_, p_150109_3_, p_150109_4_);
            }

            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_, Vec3 p_149731_6_)
    {
        int l = p_149731_1_.getBlockMetadata(p_149731_2_, p_149731_3_, p_149731_4_) & 7;
        float f = 0.21F;

        if (l == 1)
        {
            this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
        }
        else if (l == 2)
        {
            this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
        }
        else if (l == 3)
        {
            this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
        }
        else if (l == 4)
        {
            this.setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
        }
        else if (l == 6)
        {
            f = 0.2F;
            this.setBlockBounds(0.5F - f, 0.0F + f * 2, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        }
        else
        {
            f = 0.2F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }
        
        return super.collisionRayTrace(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
    }
	
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityCrystal();
	}
	
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	blockIcon = par1IconRegister.registerIcon(MainClass.modid + ":energon_crystal");
    }
}