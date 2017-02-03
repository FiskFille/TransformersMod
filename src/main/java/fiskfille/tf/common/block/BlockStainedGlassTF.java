package fiskfille.tf.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.item.ItemDyeTF;

public class BlockStainedGlassTF extends BlockStainedGlass
{
    private static IIcon[] icons;

    public BlockStainedGlassTF()
    {
        super(Material.glass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        Block block = world.getBlock(x, y, z);

        if (this == TFBlocks.stainedGlass)
        {
            if (world.getBlockMetadata(x, y, z) != world.getBlockMetadata(x - Facing.offsetsXForSide[side], y - Facing.offsetsYForSide[side], z - Facing.offsetsZForSide[side]))
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }
        }

        return super.shouldSideBeRendered(world, x, y, z, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return icons[MathHelper.clamp_int(metadata, 0, icons.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < ItemDyeTF.dyes.length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[ItemDyeTF.dyes.length];

        for (int i = 0; i < icons.length; ++i)
        {
            icons[i] = iconRegister.registerIcon(getTextureName() + "_" + ItemDyeTF.dyes[i]);
        }
    }
}
