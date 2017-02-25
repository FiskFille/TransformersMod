package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import fiskfille.tf.common.item.TFItems;

public class BlockEnergonOre extends Block
{
    private Random rand = new Random();

    public BlockEnergonOre()
    {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        setLightLevel(0.4F);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return TFItems.energonDust;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return quantityDropped(random) + random.nextInt(fortune + 1);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4 + random.nextInt(2);
    }

    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
        if (getItemDropped(metadata, rand, fortune) != Item.getItemFromBlock(this))
        {
            return 1 + rand.nextInt(5);
        }

        return 0;
    }
}
