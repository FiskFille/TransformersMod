package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.common.tileentity.TileEntityCrystal;

public class BlockEnergonCrystal extends BlockBasic implements ITileEntityProvider, IEnergon
{
    private Random rand = new Random();
    private Energon energonType;

    public BlockEnergonCrystal(Energon type)
    {
        super(TFMaterial.energon);
        energonType = type;

        setHarvestLvl("pickaxe", 1);
        setStepSound(Block.soundTypeGlass);
        setHardness(6);
        setResistance(10);
        setLightLevel(0.75F);
    }

    @Override
    public Energon getEnergonType()
    {
        return energonType;
    }

    @Override
    public int getMass()
    {
        return Energon.CRYSTAL_FULL;
    }

    @Override
    public MapColor getMapColor(int metadata)
    {
        return MapColor.airColor;
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(3) + 2;
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return energonType.getCrystalPiece();
    }

    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
        return MathHelper.getRandomIntegerInRange(rand, 0, 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(metadata).getOpposite();
        float f = 0.21F;
        
        if (dir == ForgeDirection.UP)
        {
            f = 0.2F;
            setBlockBounds(0.5F - f, f * 2, 0.5F - f, 0.5F + f, 1, 0.5F + f);
        }
        else if (dir == ForgeDirection.DOWN)
        {
            f = 0.2F;
            setBlockBounds(0.5F - f, 0, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
        }
        else if (dir == ForgeDirection.WEST)
        {
            setBlockBounds(0, 0.2F, 0.5F - f, f * 2, 0.8F, 0.5F + f);
        }
        else if (dir == ForgeDirection.EAST)
        {
            setBlockBounds(1 - f * 2, 0.2F, 0.5F - f, 1, 0.8F, 0.5F + f);
        }
        else if (dir == ForgeDirection.NORTH)
        {
            setBlockBounds(0.5F - f, 0.2F, 0, 0.5F + f, 0.8F, f * 2);
        }
        else if (dir == ForgeDirection.SOUTH)
        {
            setBlockBounds(0.5F - f, 0.2F, 1 - f * 2, 0.5F + f, 0.8F, 1);
        }
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {
            if (world.isSideSolid(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir.getOpposite(), false))
            {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
        
        if (world.isSideSolid(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir.getOpposite(), false))
        {
            return side;
        }
        
        for (ForgeDirection dir1 : ForgeDirection.VALID_DIRECTIONS)
        {
            if (world.isSideSolid(x + dir1.offsetX, y + dir1.offsetY, z + dir1.offsetZ, dir1.getOpposite(), false))
            {
                return dir1.getOpposite().ordinal();
            }
        }
        
        return 0;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(metadata).getOpposite();
        
        if (!world.isSideSolid(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir.getOpposite(), false))
        {
            if (rand.nextInt(9) == 0)
            {
                if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
                {
                    float f = 0.7F;
                    double motionX = world.rand.nextFloat() * f + (1 - f) * 0.5D;
                    double motionY = world.rand.nextFloat() * f + (1 - f) * 0.5D;
                    double motionZ = world.rand.nextFloat() * f + (1 - f) * 0.5D;
                    EntityItem entityitem = new EntityItem(world, x + motionX, y + motionY, z + motionZ, new ItemStack(energonType.getCrystal()));
                    entityitem.delayBeforeCanPickup = 10;
                    world.spawnEntityInWorld(entityitem);
                }
            }
            else
            {
                dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            }

            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityCrystal();
    }
}
