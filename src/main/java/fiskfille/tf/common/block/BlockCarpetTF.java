package fiskfille.tf.common.block;

import java.util.List;

import net.minecraft.block.BlockCarpet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.item.ItemDyeTF;

public class BlockCarpetTF extends BlockCarpet
{
    protected BlockCarpetTF()
    {
        super();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return TFBlocks.wool.getIcon(side, metadata);
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
}
