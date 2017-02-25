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

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        boolean flag = false;
        int range = 2;
        
        for (int i = -range; i <= range && !flag; ++i)
        {
            for (int j = -range; j <= range; ++j)
            {
                Random random = new Random((long) (world.getSeed() + 5.257E7 * (x >> 4 + i) - 2.763E9 * (z >> 4 + j)));
                
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
