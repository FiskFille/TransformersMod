package fiskfille.tf.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTransformiumStone extends BlockBasic
{
    public BlockTransformiumStone()
    {
        super(Material.rock);
        setHarvestLvl("pickaxe", 2);
        setHardness(2.5F);
        setResistance(10.0F);
        setTickRandomly(true);
    }

    public int tickRate(World world)
    {
        float f = 1F;
        return (int)(100000000000F * f);
    }

    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (rand.nextInt(100000) == 0 && !world.canBlockSeeTheSky(x, y + 1, z))
        {
            world.setBlock(x, y, z, TFBlocks.cosmicRust);
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        onBlockAdded(world, x, y, z);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        onBlockAdded(world, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        //        world.spawnParticle("smoke", x + 0.5F + (rand.nextFloat() - 0.5F), y + 1.0F, z + 0.5F + (rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
    }
}