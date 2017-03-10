package fiskfille.tf.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.tileentity.TileEntityColumn;
import fiskfille.tf.helper.TFTileHelper;

public class BlockColumn extends BlockMachineBase
{
    public BlockColumn()
    {
        super(Material.iron);
        setHardness(5.0F);
        setResistance(10.0F);
    }

    @Override
    public int getBlockHeight()
    {
        return 2;
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
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        float f = 0.0625F;
        float width = f * 3.125F;

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
        if (super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
        {
            return true;
        }
        
        if (!player.isSneaking())
        {
            TileEntity tile = TFTileHelper.getTileBase(world.getTileEntity(x, y, z));

            if (tile instanceof TileEntityColumn)
            {
                TFGui.ENERGY_COLUMN.open(player, tile);
            }

            return true;
        }

        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("iron_block");
    }
}
