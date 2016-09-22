package fiskfille.tf.common.block;

import java.util.List;

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
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;

public class BlockRelayTower extends BlockTransmitter
{
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
//		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
		
		AxisAlignedBB aabb1 = getCollisionBoundingBoxFromPool(world, x, y, z);

        if (aabb1 != null && aabb.intersectsWith(aabb1))
        {
            list.add(aabb1);
        }
		
		setBlockBoundsBasedOnState(world, x, y, z);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		setBounds(world.getBlockMetadata(x, y, z));
	}

	public void setBounds(int metadata)
	{
		float f = 0.0625F;
		float width = 0.25F;

		if (metadata < 4)
		{
			setBlockBounds(width, 0, width, 1 - width, 2, 1 - width);
		}
		else
		{
			setBlockBounds(width, -1, width, 1 - width, 1, 1 - width);
		}
	}

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
			return false;
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		world.removeTileEntity(x, y, z);
	}

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

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return y >= world.getHeight() - 1 ? false : super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
	{
		int rotation = MathHelper.floor_double((double) ((entity.rotationYaw * 4F) / 360F) + 2.5D) & 3;

		world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
		world.setBlock(x, y + 1, z, this, rotation + 4, 2);
	}

	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityRelayTower();
	}
}
