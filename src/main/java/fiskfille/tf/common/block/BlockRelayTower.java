package fiskfille.tf.common.block;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockRelayTower extends BlockTransmitter
{
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        float f = 0.0625F;
        float width = f * 2;

        if (metadata < 4)
        {
            addBox(width, 0, width, 1 - width, f * 2, 1 - width, world, x, y, z, aabb, list, entity);
            width = f * 4;
            addBox(width, f * 2, width, 1 - width, f * 14, 1 - width, world, x, y, z, aabb, list, entity);
            width = f * 5;
            addBox(width, f * 14, width, 1 - width, f * 22, 1 - width, world, x, y, z, aabb, list, entity);
            width = f * 6;
            addBox(width, f * 22, width, 1 - width, 2, 1 - width, world, x, y, z, aabb, list, entity);
        }

        setBlockBoundsBasedOnState(world, x, y, z);
    }

    @Override
    public void addBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
    {
        setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        AxisAlignedBB aabb1 = getCollisionBoundingBoxFromPool(world, x, y, z);

        if (aabb1 != null && aabb.intersectsWith(aabb1))
        {
            list.add(aabb1);
        }

        setBlockBoundsBasedOnState(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        setBounds(world.getBlockMetadata(x, y, z));
    }

    @Override
    public void setBounds(int metadata)
    {
        float f = 0.0625F;
        float width = f * 4;

        if (metadata < 4)
        {
            setBlockBounds(width, 0, width, 1 - width, 2, 1 - width);
        }
        else
        {
            setBlockBounds(width, -1, width, 1 - width, 1, 1 - width);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.isSneaking())
        {
            int metadata = world.getBlockMetadata(x, y, z);

            if (metadata >= 4)
            {
                y -= 1;
            }

            if (world.getTileEntity(x, y, z) instanceof TileEntityRelayTower)
            {
                player.openGui(TransformersMod.instance, 5, world, x, y, z);
            }

            return true;
        }
        else
        {
            int metadata = world.getBlockMetadata(x, y, z);

            if (metadata >= 4)
            {
                y -= 1;
            }

            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityRelayTower)
            {
                TileEntityRelayTower tower = (TileEntityRelayTower) tile;
            }

            return false;
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        world.removeTileEntity(x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        if (metadata >= 4)
        {
            if (world.getBlock(x, y - 1, z) != TFBlocks.relayTower)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
        else
        {
            if (world.getBlock(x, y + 1, z) != TFBlocks.relayTower)
            {
                world.setBlockToAir(x, y, z);
                breakBlock(world, x, y, z, block, metadata);
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return y < world.getHeight() - 1 && (world.getBlock(x, y, z).isReplaceable(world, x, y, z) && world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z));
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        int rotation = MathHelper.floor_double((double) ((entity.rotationYaw * 4F) / 360F) + 2.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
        world.setBlock(x, y + 1, z, this, rotation + 4, 2);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityRelayTower();
    }
}
