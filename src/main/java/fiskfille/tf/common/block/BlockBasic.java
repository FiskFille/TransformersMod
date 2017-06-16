package fiskfille.tf.common.block;

import fiskfille.tf.common.item.RegisterItemModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block implements RegisterItemModel
{
    public BlockBasic(Material material)
    {
        super(material);
    }

    public BlockBasic setHarvestLvl(String tool, int level)
    {
        this.setHarvestLevel(tool, level);
        return this;
    }
}
