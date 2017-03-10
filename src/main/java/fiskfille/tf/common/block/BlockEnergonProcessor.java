package fiskfille.tf.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;

public class BlockEnergonProcessor extends BlockMachineBase
{
    public BlockEnergonProcessor()
    {
        super(Material.iron);
        setHarvestLevel("pickaxe", 1);
        setHardness(6.0F);
        setResistance(10.0F);
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
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
            TFGui.ENERGON_PROCESSOR.open(player, x, y, z);
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
