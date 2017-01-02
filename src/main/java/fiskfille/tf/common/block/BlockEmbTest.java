package fiskfille.tf.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;

public class BlockEmbTest extends BlockMachineBase
{
    protected BlockEmbTest()
    {
        super(Material.rock);
    }

    @Override
    public int getPlacedRotation(EntityLivingBase entity)
    {
        return 0;
    }
}
