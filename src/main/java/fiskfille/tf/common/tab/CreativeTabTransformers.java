package fiskfille.tf.common.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import fiskfille.tf.common.block.TFBlocks;

public class CreativeTabTransformers extends CreativeTabs
{
    public CreativeTabTransformers()
    {
        super("Transformers");
    }
    
    @Override
    public Item getTabIconItem()
    {
        return Item.getItemFromBlock(TFBlocks.energonCrystal);
    }
}