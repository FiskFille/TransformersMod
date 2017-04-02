package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block
{
    public BlockBasic(Material material)
    {
        super(material);
    }

    public BlockBasic setHarvestLvl(String tool, int level)
    {
        setHarvestLevel(tool, level);
        return this;
    }
}
