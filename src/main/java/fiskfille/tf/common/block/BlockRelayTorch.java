package fiskfille.tf.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRelayTorch extends BlockRelayTower
{
    @Override
    public int getBlockHeight()
    {
        return 1;
    }

    @Override
    public int getPlacedRotation(EntityLivingBase entity)
    {
        return 0;
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(metadata);
        float f = 0.0625F;
        float width = f * 8;
        float height = f * 4.75F;

        for (int i = 0; i < 2; ++i)
        {
            switch (dir)
            {
            case DOWN:
                addBox(0.5F - width / 2, 0, 0.5F - width / 2, 0.5F + width / 2, height, 0.5F + width / 2, x, y, z, aabb, list);
                break;
            case UP:
                addBox(0.5F - width / 2, 1 - height, 0.5F - width / 2, 0.5F + width / 2, 1, 0.5F + width / 2, x, y, z, aabb, list);
                break;
            case NORTH:
                addBox(0.5F - width / 2, 0.5F - width / 2, 0, 0.5F + width / 2, 0.5F + width / 2, height, x, y, z, aabb, list);
                break;
            case SOUTH:
                addBox(0.5F - width / 2, 0.5F - width / 2, 1 - height, 0.5F + width / 2, 0.5F + width / 2, 1, x, y, z, aabb, list);
                break;
            case WEST:
                addBox(0, 0.5F - width / 2, 0.5F - width / 2, height, 0.5F + width / 2, 0.5F + width / 2, x, y, z, aabb, list);
                break;
            case EAST:
                addBox(1 - height, 0.5F - width / 2, 0.5F - width / 2, 1, 0.5F + width / 2, 0.5F + width / 2, x, y, z, aabb, list);
                break;
            default:
                addBox(0, 0, 0, 1, 0.1F, 1, x, y, z, aabb, list);
                break;
            }

            width = f * 2.25F;
            height = f * 12;
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(metadata);
        float f = 0.0625F;
        float width = f * 8;
        float height = f * 12;

        switch (dir)
        {
        case DOWN:
            setBlockBounds(0.5F - width / 2, 0, 0.5F - width / 2, 0.5F + width / 2, height, 0.5F + width / 2);
            break;
        case UP:
            setBlockBounds(0.5F - width / 2, 1 - height, 0.5F - width / 2, 0.5F + width / 2, 1, 0.5F + width / 2);
            break;
        case NORTH:
            setBlockBounds(0.5F - width / 2, 0.5F - width / 2, 0, 0.5F + width / 2, 0.5F + width / 2, height);
            break;
        case SOUTH:
            setBlockBounds(0.5F - width / 2, 0.5F - width / 2, 1 - height, 0.5F + width / 2, 0.5F + width / 2, 1);
            break;
        case WEST:
            setBlockBounds(0, 0.5F - width / 2, 0.5F - width / 2, height, 0.5F + width / 2, 0.5F + width / 2);
            break;
        case EAST:
            setBlockBounds(1 - height, 0.5F - width / 2, 0.5F - width / 2, 1, 0.5F + width / 2, 0.5F + width / 2);
            break;
        default:
            setBlockBounds(0, 0, 0, 1, 1, 1);
            break;
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
        {            
            if (canPlaceAt(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir.getOpposite()))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(side);

        if (canPlaceAt(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir.getOpposite()))
        {
            return side;
        }

        for (ForgeDirection dir1 : ForgeDirection.VALID_DIRECTIONS)
        {
            if (canPlaceAt(world, x + dir1.offsetX, y + dir1.offsetY, z + dir1.offsetZ, dir1.getOpposite()))
            {
                return dir1.ordinal();
            }
        }

        return 0;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(metadata);

        if (!canPlaceAt(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir.getOpposite()))
        {
            removedByPlayer(world, null, x, y, z, true);
            world.setBlockToAir(x, y, z);
        }
    }
    
    public boolean canPlaceAt(World world, int x, int y, int z, ForgeDirection dir)
    {
        Block block = world.getBlock(x, y, z);
        
        return block.isSideSolid(world, x, y, z, dir) || block == TFBlocks.energyColumn && (dir == ForgeDirection.UP || dir == ForgeDirection.DOWN);
    }
}
