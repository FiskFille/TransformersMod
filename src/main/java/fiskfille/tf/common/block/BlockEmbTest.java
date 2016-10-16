package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fiskfille.tf.common.tileentity.TileEntityEmbTest;

public class BlockEmbTest extends Block implements ITileEntityProvider
{
	protected BlockEmbTest()
	{
		super(Material.rock);
	}

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
		return new TileEntityEmbTest();
	}
}
