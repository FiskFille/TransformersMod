package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.network.MessageControlPanel;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class BlockGroundBridgeControl extends BlockDirectional implements ITileEntityProvider
{
	public static final int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

	private final Random rand = new Random();

	public BlockGroundBridgeControl()
	{
		super(Material.iron);
		setHarvestLevel("pickaxe", 0);
		setStepSound(soundTypeMetal);
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
	public boolean hasTileEntity()
	{
		return true;
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
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		int direction = getDirection(metadata);
		boolean isSide = !isBlockLeftSideOfPanel(metadata);
		boolean isTop = isBlockTopOfPanel(metadata);
		
		TileEntity tile = world.getTileEntity(x - (isSide ? directions[direction][0] : 0), y - (isTop ? 1 : 0), z - (isSide ? directions[direction][1] : 0));
		setBounds(tile, metadata);
	}

	public void setBounds(TileEntity tile, int metadata)
	{
		int direction = getDirection(metadata);
		float f = 0.0625F;
		float f1 = 0.9575F;

		if (isBlockTopOfPanel(metadata))
		{
			if (tile instanceof TileEntityControlPanel && ((TileEntityControlPanel)tile).hasUpgrade(DataCore.spaceBridge))
			{
//				System.out.println( + ", " + ((TileEntityControlPanel)tile).hasSpaceBridgeUpgrade);
				float width = f * 6;
				float depth = f * 4;
				float height = f * 8;
				
				if (direction == 0)
				{
					setBlockBounds(0, f1 - 1, 1 - depth, width, f1 - 1 + height, 1);
				}
				else if (direction == 1)
				{
					setBlockBounds(0, f1 - 1, 0, depth, f1 - 1 + height, width);
				}
				else if (direction == 2)
				{
					setBlockBounds(1 - width, f1 - 1, 0, 1, f1 - 1 + height, depth);
				}
				else if (direction == 3)
				{
					setBlockBounds(1 - depth, f1 - 1, 1 - width, 1, f1 - 1 + height, 1);
				}
			}
			else
			{
				setBlockBounds(0, 0, 0, 0, 0, 0);
			}
		}
		else
		{
			int i = isBlockRightSideOfPanel(metadata) ? 0 : 1;
			
			setBlockBounds(0, 0, 0, 1, f1, 1);
			
//			if (direction == 0)
//			{
//				setBlockBounds(-i, 0, 0, 2 - i, f, 1);
//			}
//			else if (direction == 1)
//			{
//				setBlockBounds(0, 0, -i, 1, f, 2 - i);
//			}
//			else if (direction == 2)
//			{
//				setBlockBounds(-1 + i, 0, 0, 1 + i, f, 1);
//			}
//			else if (direction == 3)
//			{
//				setBlockBounds(0, 0, -1 + i, 1, f, 1 + i);
//			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		int direction = getDirection(metadata);

		if (isBlockRightSideOfPanel(metadata))
		{
			if (world.getBlock(x - directions[direction][0], y, z - directions[direction][1]) != this || world.getBlock(x, y + 1, z) != this)
			{
				world.setBlockToAir(x, y, z);
			}
		}
		else if (isBlockTopOfPanel(metadata))
		{
			if (world.getBlock(x, y - 1, z) != this || world.getBlock(x - directions[direction][0], y - 1, z - directions[direction][1]) != this)
			{
				world.setBlockToAir(x, y, z);
			}
		}
		else if (isBlockLeftSideOfPanel(metadata))
		{
			if (world.getBlock(x + directions[direction][0], y, z + directions[direction][1]) != this || world.getBlock(x + directions[direction][0], y + 1, z + directions[direction][1]) != this)
			{
				world.setBlockToAir(x, y, z);

				if (!world.isRemote)
				{
					dropBlockAsItem(world, x, y, z, metadata, 0);
				}
			}
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int p_149650_3_)
	{
		return !isBlockLeftSideOfPanel(metadata) ? Item.getItemById(0) : super.getItemDropped(metadata, rand, p_149650_3_);
	}

	public static boolean isBlockLeftSideOfPanel(int metadata)
	{
		return metadata < 4;
	}

	public static boolean isBlockRightSideOfPanel(int metadata)
	{
		return metadata >= 4 && metadata < 8;
	}

	public static boolean isBlockTopOfPanel(int metadata)
	{
		return metadata >= 8;
	}

	public static int getDirection(int metadata)
	{
		return metadata % 4;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float p_149690_6_, int p_149690_7_)
	{
		if (isBlockLeftSideOfPanel(metadata))
		{
			super.dropBlockAsItemWithChance(world, x, y, z, metadata, p_149690_6_, 0);
		}
	}

	@Override
	public int getMobilityFlag()
	{
		return 1;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
	{
		if (player.capabilities.isCreativeMode && !isBlockLeftSideOfPanel(metadata))
		{
			int direction = getDirection(metadata);
			x -= directions[direction][0];
			z -= directions[direction][1];

			if (isBlockTopOfPanel(metadata))
			{
				y -= 1;
			}

			if (world.getBlock(x, y, z) == this)
			{
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		TileEntityControlPanel tileentity = (TileEntityControlPanel) world.getTileEntity(x, y, z);

		if (tileentity != null)
		{
			for (int j1 = 0; j1 < tileentity.getSizeInventory(); ++j1)
			{
				ItemStack itemstack = tileentity.getStackInSlot(j1);

				if (itemstack != null)
				{
					float f = rand.nextFloat() * 0.8F + 0.1F;
					float f1 = rand.nextFloat() * 0.8F + 0.1F;
					float f2 = rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0)
					{
						int k1 = rand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize)
						{
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) rand.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) rand.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		int direction = getDirection(metadata);
		int face = -1;

		if (side == 0)
		{
			face = 5;
		}
		else if (side == 1)
		{
			face = 4;
		}

		if (direction == 0)
		{
			if (side == 2)
			{
				face = 0;
			}
			else if (side == 3)
			{
				face = 1;
			}
			else if (side == 4)
			{
				face = 2;
			}
			else if (side == 5)
			{
				face = 3;
			}
		}
		else if (direction == 1)
		{
			if (side == 5)
			{
				face = 0;
			}
			else if (side == 4)
			{
				face = 1;
			}
			else if (side == 2)
			{
				face = 2;
			}
			else if (side == 3)
			{
				face = 3;
			}
		}
		else if (direction == 2)
		{
			if (side == 3)
			{
				face = 0;
			}
			else if (side == 2)
			{
				face = 1;
			}
			else if (side == 5)
			{
				face = 2;
			}
			else if (side == 4)
			{
				face = 3;
			}
		}
		else if (direction == 3)
		{
			if (side == 4)
			{
				face = 0;
			}
			else if (side == 5)
			{
				face = 1;
			}
			else if (side == 3)
			{
				face = 2;
			}
			else if (side == 2)
			{
				face = 3;
			}
		}

		if (face != -1)
		{
			if (side == 1 || side == 0)
			{
				if (direction == 0)
				{
					hitX = 1 - hitX;
					hitY = (side == 1 ? 1 : hitZ * 2) - hitZ;
				}
				else if (direction == 1)
				{
					hitY = (side == 0 ? 1 : hitX * 2) - hitX;
					hitX = 1 - hitZ;
				}
				else if (direction == 2)
				{
					hitY = (side == 0 ? 1 : hitZ * 2) - hitZ;
				}
				else if (direction == 3)
				{
					hitY = (side == 1 ? 1 : hitX * 2) - hitX;
					hitX = hitZ;
				}
			}
			else if (side == 3)
			{
				hitY = 1 - hitY;
			}
			else if (side == 2)
			{
				hitX = 1 - hitX;
				hitY = 1 - hitY;
			}
			else if (side == 5)
			{
				hitX = 1 - hitZ;
				hitY = 1 - hitY;
			}
			else if (side == 4)
			{
				hitX = hitZ;
				hitY = 1 - hitY;
			}

			if (face == 1)
			{
				hitX = 1 - hitX;
			}

			boolean isSide = !isBlockLeftSideOfPanel(metadata);
			boolean isTop = isBlockTopOfPanel(metadata);

			if (isSide && face != 2 && face != 3)
			{
				++hitX;
			}
			
			if (isTop && face != 4 && face != 5)
			{
				--hitY;
			}

			TileEntityControlPanel tile = (TileEntityControlPanel) world.getTileEntity(x - (isSide ? directions[direction][0] : 0), y - (isTop ? 1 : 0), z - (isSide ? directions[direction][1] : 0));

			if (tile != null)
			{
				return onRightClick(world, x, y, z, tile, player, face, hitX, hitY);
			}
		}

		return false;
	}

	public boolean onRightClick(World world, int x, int y, int z, TileEntityControlPanel tile, EntityPlayer player, int face, float hitX, float hitY)
	{
		// 0 = front, 1 = back, 2 = right, 3 = left, 4 = top, 5 = bottom
		float f = 0.0625F;

		if (world.isRemote)
		{
			if (face == 0)
			{
				if (hitY > f * 4 && hitY <= f * 7.5F)
				{
					for (int i = 0; i < 3; ++i)
					{
						if (player.getHeldItem() != null && tile.isItemValidForSlot(i, player.getHeldItem()) || tile.getStackInSlot(i) != null && player.getHeldItem() == null)
						{
							if (hitX > f * (19 + i * 3.5F) && hitX <= f * (22 + i * 3.5F))
							{
								sendActionPacket(tile, player, 15 + i);
								return true;
							}
						}
					}
				}
				
				if (hitY >= f * -3.7F && hitY < f * -1.7F)
				{
					if (hitX >= f * 26.5F && hitX < f * 28.5F)
					{
						sendActionPacket(tile, player, 18);
						return true;
					}
					else if (hitX >= f * 28.7F && hitX < f * 30.7F)
					{
						sendActionPacket(tile, player, 19);
						return true;
					}
				}
			}
			else if (face == 4)
			{
				if (hitY > f * 2 && hitY <= f * 4.5F)
				{
					if (hitX > f * 1.15F && hitX <= f * 3.21F)
					{
						sendActionPacket(tile, player, 1);
						return true;
					}
					if (hitX > f * 3.25F && hitX <= f * 5.39F)
					{
						sendActionPacket(tile, player, 2);
						return true;
					}
					if (hitX > f * 5.55F && hitX <= f * 7.6F)
					{
						sendActionPacket(tile, player, 3);
						return true;
					}
					if (hitX > f * 7.6F && hitX <= f * 10F)
					{
						sendActionPacket(tile, player, 4);
						return true;
					}
				}
				if (hitY > f * 6.2F && hitY <= f * 8.8F)
				{
					if (hitX > f * 1.15F && hitX <= f * 3.21F)
					{
						sendActionPacket(tile, player, 5);
						return true;
					}
					if (hitX > f * 3.25F && hitX <= f * 5.39F)
					{
						sendActionPacket(tile, player, 6);
						return true;
					}
					if (hitX > f * 5.55F && hitX <= f * 7.6F)
					{
						sendActionPacket(tile, player, 7);
						return true;
					}
					if (hitX > f * 7.6F && hitX <= f * 10F)
					{
						sendActionPacket(tile, player, 8);
						return true;
					}
				}
				if (hitY > f * 10F && hitY <= f * 13F)
				{
					if (hitX > f * 1.15F && hitX <= f * 3.21F)
					{
						sendActionPacket(tile, player, 9);
						return true;
					}
					if (hitX > f * 3.25F && hitX <= f * 5.39F)
					{
						sendActionPacket(tile, player, 10);
						return true;
					}
					if (hitX > f * 5.55F && hitX <= f * 7.6F)
					{
						sendActionPacket(tile, player, 11);
						return true;
					}
					if (hitX > f * 7.6F && hitX <= f * 10F)
					{
						sendActionPacket(tile, player, 12);
						return true;
					}
				}
				if (hitX > f * 13.5F && hitX <= f * 18.5F && hitY > f * 5.65F && hitY <= f * 10.85F)
				{
					if (!tile.activationLeverState)
					{
						sendActionPacket(tile, player, 13);
					}

					return true;
				}
				if (hitX > f * 23F && hitX <= f * 31F && hitY > f * 4F && hitY <= f * 12.75F)
				{
					if (tile.activationLeverCoverState && (tile.activationLeverTimer == 0 || tile.activationLeverTimer == 1))
					{
						sendActionPacket(tile, player, 14);
					}

					return true;
				}
			}
		}

		return false;
	}

	public void sendActionPacket(TileEntityControlPanel tile, EntityPlayer player, int action)
	{
		TFNetworkManager.networkWrapper.sendToServer(new MessageControlPanel(player, tile.xCoord, tile.yCoord, tile.zCoord, tile.getWorldObj().provider.dimensionId, action));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityControlPanel();
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		blockIcon = par1IIconRegister.registerIcon("iron_block");
	}
}
