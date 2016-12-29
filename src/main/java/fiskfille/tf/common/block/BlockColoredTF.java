package fiskfille.tf.common.block;

import java.util.List;

import net.minecraft.block.BlockColored;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.item.ItemDyeTF;
import fiskfille.tf.helper.TFMathHelper;

public class BlockColoredTF extends BlockColored
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockColoredTF(Material material)
    {
        super(material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return icons[MathHelper.clamp_int(metadata, 0, icons.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List subItems)
    {
        for (int i = 0; i < ItemDyeTF.dyes.length; ++i)
        {
            subItems.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public MapColor getMapColor(int damage)
    {
        return TFMathHelper.getClosestMapColor(ItemDyeTF.getDyeColor(damage));
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
