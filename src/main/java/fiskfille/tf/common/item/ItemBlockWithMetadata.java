package fiskfille.tf.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockWithMetadata extends ItemBlock
{
    public ItemBlockWithMetadata(Block block)
    {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        return field_150939_a.getIcon(2, damage);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
}
