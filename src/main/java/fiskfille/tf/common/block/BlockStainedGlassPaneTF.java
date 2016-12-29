package fiskfille.tf.common.block;

import java.util.List;

import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.item.ItemDyeTF;

public class BlockStainedGlassPaneTF extends BlockStainedGlassPane
{
    private static IIcon[] icons;

    public BlockStainedGlassPaneTF()
    {
        super();
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int side, int metadata)
    {
        return TFBlocks.stainedGlass.getIcon(side, metadata);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return TFBlocks.stainedGlass.getIcon(side, metadata);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_150104_b(int metadata)
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
            icons[i] = iconRegister.registerIcon(getTextureName() + "_top_" + ItemDyeTF.dyes[i]);
        }
    }
}
