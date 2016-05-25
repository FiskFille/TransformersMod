package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fiskfille.tf.common.tileentity.TileEntityEmlTest;

public class BlockEmlTest extends Block implements ITileEntityProvider
{
	protected BlockEmlTest()
	{
		super(Material.rock);
	}

	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityEmlTest();
	}
}
