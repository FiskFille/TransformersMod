package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import fiskfille.tf.common.block.BlockDisplayPedestal;

public class ItemDisplayPedestal extends ItemBlockWithMetadata
{
    public ItemDisplayPedestal(Block block)
    {
        super(block);
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < BlockDisplayPedestal.getTextures().size(); ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
