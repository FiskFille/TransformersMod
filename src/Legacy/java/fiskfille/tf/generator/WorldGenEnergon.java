package fiskfille.tf.generator;

import java.util.Random;

import fiskfille.tf.main.TFBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEnergon extends WorldGenerator
{
    public boolean generate(World world, Random random, int chunkX, int chunkZ, int randPosY)
    {
		Random randomGenerator = random;
		
		for (int i = 0; i < 100; i++)
		{
			int randPosX = chunkX + randomGenerator.nextInt(8);
			int randPosZ = chunkZ + randomGenerator.nextInt(8);
			
//			if (TFBlocks.energonCrystal.canBlockStay(world, randPosX, randPosY, randPosZ))
			{
				new WorldGenMinable(TFBlocks.energonCrystal, 1).generate(world, random, randPosX, randPosY, randPosZ);
			}
		}
		
        return true;
    }
}