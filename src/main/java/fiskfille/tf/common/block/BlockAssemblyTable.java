package fiskfille.tf.common.block;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityAssemblyTable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAssemblyTable extends BlockBasic implements ITileEntityProvider
{
    public BlockAssemblyTable()
    {
        super(Material.glass);
        setHarvestLvl("pickaxe", 1);
        setStepSound(Block.soundTypeMetal);
        setHardness(6.0F);
        setResistance(10.0F);
        setLightLevel(0.5F);
        setBlockBounds(0, 0, 0, 1, 0.0625F * 13, 1);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ)
    {
        if (!player.isSneaking())
        {
            player.openGui(TransformersMod.instance, 2, world, x, y, z);
            return true;
        }
        else
        {
            return false;
        }
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
        return new TileEntityAssemblyTable();
    }

    public void registerBlockIcons(IIconRegister par1IIconRegister)
    {
        blockIcon = par1IIconRegister.registerIcon("iron_block");
    }
}
