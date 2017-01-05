package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;

public class BlockAssemblyTable extends BlockMachineBase
{
    public BlockAssemblyTable()
    {
        super(Material.iron);
        setHarvestLevel("pickaxe", 1);
        setStepSound(Block.soundTypeMetal);
        setHardness(6.0F);
        setResistance(10.0F);
        setLightLevel(0.5F);
        setBlockBounds(0, 0, 0, 1, 0.0625F * 13, 1);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ)
    {
        if (!player.isSneaking())
        {
            TFGui.ASSEMBLY_TABLE.open(player, x, y, z);
            return true;
        }

        return false;
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
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("iron_block");
    }
}
