package fiskfille.tf.common.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import fiskfille.tf.common.block.TFBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class OreWorldGenerator implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case 0:
                generateOverworld(world, random, chunkX * 16, chunkZ * 16);
                break;
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    public void generateOverworld(World world, Random random, int chunkX, int chunkZ)
    {
        if (world.getWorldInfo().getTerrainType() != WorldType.FLAT)
        {
            generateCrystal(75, TFBlocks.energonCrystal, 48, world, random, chunkX, chunkZ);
        }

        generateOre(2, TFBlocks.transformiumOre, 8, 10, world, random, chunkX, chunkZ);
    }

    public void generateNether(World world, Random random, int chunkX, int chunkZ)
    {

    }

    public void generateOre(int veinsPerChunk, Block block, int veinSize, int minY, World world, Random random, int chunkX, int chunkZ)
    {
        for (int i = 0; i < veinsPerChunk; i++)
        {
            int randPosX = chunkX + random.nextInt(16);
            int randPosY = random.nextInt(minY);
            int randPosZ = chunkZ + random.nextInt(16);
            new WorldGenMinable(block, veinSize).generate(world, random, randPosX, randPosY, randPosZ);
        }
    }

    public void generateCrystal(int veinsPerChunk, Block block, int minY, World world, Random random, int chunkX, int chunkZ)
    {
        for (int i = 0; i < veinsPerChunk; i++)
        {
            int randPosX = chunkX + random.nextInt(15) + 1;
            int randPosY = random.nextInt(minY);
            int randPosZ = chunkZ + random.nextInt(15) + 1;

            if (world.getBlock(randPosX, randPosY, randPosZ) == Blocks.air)
            {
                if (world.getBlock(randPosX + 1, randPosY, randPosZ).getMaterial() == Material.rock)
                {
                    world.setBlock(randPosX, randPosY, randPosZ, block);
                    world.setBlockMetadataWithNotify(randPosX, randPosY, randPosZ, 2, 2);
                }
                else if (world.getBlock(randPosX, randPosY, randPosZ + 1).getMaterial() == Material.rock)
                {
                    world.setBlock(randPosX, randPosY, randPosZ, block);
                    world.setBlockMetadataWithNotify(randPosX, randPosY, randPosZ, 4, 2);
                }
                else if (world.getBlock(randPosX - 1, randPosY, randPosZ).getMaterial() == Material.rock)
                {
                    world.setBlock(randPosX, randPosY, randPosZ, block);
                    world.setBlockMetadataWithNotify(randPosX, randPosY, randPosZ, 1, 2);
                }
                else if (world.getBlock(randPosX, randPosY, randPosZ - 1).getMaterial() == Material.rock)
                {
                    world.setBlock(randPosX, randPosY, randPosZ, block);
                    world.setBlockMetadataWithNotify(randPosX, randPosY, randPosZ, 3, 2);
                }

                if (world.getBlock(randPosX, randPosY - 1, randPosZ).getMaterial() == Material.rock)
                {
                    world.setBlock(randPosX, randPosY, randPosZ, block);
                    world.setBlockMetadataWithNotify(randPosX, randPosY, randPosZ, 5, 2);
                }
                if (world.getBlock(randPosX, randPosY + 1, randPosZ).getMaterial() == Material.rock)
                {
                    world.setBlock(randPosX, randPosY, randPosZ, block);
                    world.setBlockMetadataWithNotify(randPosX, randPosY, randPosZ, 6, 2);
                }
            }
        }
    }
}