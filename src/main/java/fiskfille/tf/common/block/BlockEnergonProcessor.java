package fiskfille.tf.common.block;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;

public class BlockEnergonProcessor extends BlockContainer
{
	private final Random rand = new Random();

	public BlockEnergonProcessor()
	{
		super(Material.iron);
		this.setTickRandomly(true);
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public int getRenderType()
	{
		return -1;
	}
	
	public boolean hasComparatorInputOverride()
    {
        return true;
    }
	
    public int getComparatorInputOverride(World world, int x, int y, int z, int metadata)
    {
    	TileEntityEnergonProcessor tile = (TileEntityEnergonProcessor)world.getTileEntity(x, y, z);
    	
    	if (tile != null)
    	{
    		int liquidAmount = 0;
    		
    		for (Map.Entry<String, Integer> e : tile.energonContentMap.entrySet())
            {
    			liquidAmount += e.getValue();
            }
    		
    		return Math.round((float)liquidAmount / 52 * 15);
    	}
    	
        return 0;
    }

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(TransformersMod.instance, 0, world, x, y, z);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		TileEntityEnergonProcessor tileentity = (TileEntityEnergonProcessor)world.getTileEntity(x, y, z);

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
						EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
						
						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (double)((float)rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)rand.nextGaussian() * f3);
						world.spawnEntityInWorld(entityitem);
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}
		
		super.breakBlock(world, x, y, z, block, metadata);
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
	{
		int rotation = MathHelper.floor_double((double)((entity.rotationYaw * 4F) / 360F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, rotation, 2);
	}
	
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityEnergonProcessor();
	}
	
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		blockIcon = par1IIconRegister.registerIcon("iron_block");
	}
}