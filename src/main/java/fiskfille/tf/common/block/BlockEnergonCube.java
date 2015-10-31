package fiskfille.tf.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockEnergonCube extends BlockBasic
{
    public BlockEnergonCube()
    {
        super(Material.glass);
        setHarvestLvl("pickaxe", 1);
        setStepSound(Block.soundTypeGlass);
        setHardness(6.0F);
        setResistance(10.0F);
        setLightLevel(0.75F);
    }

    protected boolean canSilkHarvest()
    {
        return true;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return shouldRenderSide(world, x, y, z, 1 - side);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldRenderSide(IBlockAccess world, int x, int y, int z, int side)
    {
        Block block = world.getBlock(x, y, z);

        return block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }
}
