package fiskfille.tf.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.item.ItemMiniVehicle;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;

public class BlockDisplayPillar extends BlockBasic implements ITileEntityProvider
{
	private Random rand = new Random();

	public BlockDisplayPillar()
	{
		super(Material.rock);
		this.setCreativeTab(TransformersMod.transformersTab);
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		ItemStack itemstack = metadata != 0 ? new ItemStack(TFItems.displayVehicle, 1, metadata - 1) : null;

		if (itemstack != null)
		{
			float f = this.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

			while (itemstack.stackSize > 0)
			{
				int j1 = this.rand.nextInt(21) + 10;

				if (j1 > itemstack.stackSize)
				{
					j1 = itemstack.stackSize;
				}

				itemstack.stackSize -= j1;

				EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

				if (itemstack.hasTagCompound())
				{
					entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
				}

				float f3 = 0.05F;

				entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
				entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);

				world.spawnEntityInWorld(entityitem);
			}
		}

		super.breakBlock(world, x, y, z, block, metadata);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntityDisplayPillar tileEntityDisplayPillar = (TileEntityDisplayPillar) world.getTileEntity(x, y, z);

		if (tileEntityDisplayPillar != null)
		{
			ItemStack heldItem = player.getHeldItem();

			ItemStack displayItem = tileEntityDisplayPillar.getDisplayItem();

			if (heldItem == null && displayItem != null)
			{
				player.setCurrentItemOrArmor(0, displayItem);

				tileEntityDisplayPillar.setDisplayItem(null, true);
			}
			else if (heldItem != null && heldItem.getItem() instanceof ItemMiniVehicle)
			{
				tileEntityDisplayPillar.setDisplayItem(heldItem, true);

				//if (!player.capabilities.isCreativeMode)
				{
					player.setCurrentItemOrArmor(0, null);
				}
			}
		}

		return false;
	}

	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 p_149731_5_, Vec3 p_149731_6_)
	{
		TileEntityDisplayPillar tileEntityDisplayPillar = (TileEntityDisplayPillar)world.getTileEntity(x, y, z);

		if (tileEntityDisplayPillar != null)
		{
			ItemStack displayItem = tileEntityDisplayPillar.getDisplayItem();

			if (displayItem != null)
			{
				float f = 0.21F;
				int metadata = displayItem.getItemDamage();

				if (metadata == 0 || metadata == 1 || metadata == 4) {this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);}
				else if (metadata == 2 || metadata == 3) {this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);}
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.55F, 1.0F);
			}
		}

		return super.collisionRayTrace(world, x, y, z, p_149731_5_, p_149731_6_);
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
		return new TileEntityDisplayPillar();
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(TransformersMod.modid + ":" + getUnlocalizedName().substring(5));
	}
}