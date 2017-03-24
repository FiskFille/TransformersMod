package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.world.IBlockAccess;
import fiskfille.tf.common.energon.Energon;
import fiskfille.tf.common.energon.IEnergon;
import fiskfille.tf.helper.TFMathHelper;

public class BlockEnergonCube extends BlockBasic implements IEnergon
{
    private Energon energonType;

    public BlockEnergonCube(Energon type)
    {
        super(TFMaterial.energon);
        energonType = type;

        setHarvestLvl("pickaxe", 1);
        setStepSound(Block.soundTypeMetal);
        setHardness(6.0F);
        setResistance(10.0F);
        setLightLevel(0.5F);
    }
    
    @Override
    public int getMixedBrightnessForBlock(IBlockAccess world, int x, int y, int z)
    {
        return 255;
    }

    @Override
    public Energon getEnergonType()
    {
        return energonType;
    }

    @Override
    public int getMass()
    {
        return Energon.CRYSTAL_BLOCK;
    }

    @Override
    public MapColor getMapColor(int metadata)
    {
        return TFMathHelper.getClosestMapColor(getEnergonType().getColor());
    }
}
