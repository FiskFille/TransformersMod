package fiskfille.tf.common.generator;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenCrystal extends WorldGenerator
{
    private Block target;
    private Material growthMaterial;

    public WorldGenCrystal(Block block, Material material)
    {
        target = block;
        growthMaterial = material;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        boolean flag = false;
        int range = 3;

        for (int i = -range; i <= range && !flag; ++i)
        {
            for (int j = -range; j <= range; ++j)
            {
                int xPosition = x >> 4 + i;
                int zPosition = z >> 4 + j;
                Random random = new Random(world.getSeed() + (long) (xPosition * xPosition * 0x4c1906) + (long) (xPosition * 0x5ac0db) + (long) (zPosition * zPosition) * 0x4307a7L + (long) (zPosition * 0x5f24f) ^ 0x3ad8025f);
                
                if (random.nextInt(300) == 0)
                {
                    flag = true;
                    break;
                }
            }
        }

        if (flag && world.getBlock(x, y, z) == Blocks.air)
        {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
            {
                if (world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ).getMaterial() == growthMaterial)
                {
                    System.out.println(x + " " + y + " " + z);
                    world.setBlock(x, y, z, target, dir.getOpposite().ordinal(), 2);

                    return true;
                }
            }
        }

        return false;
    }
}
