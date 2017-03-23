package fiskfille.tf.common.generator;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fiskfille.tf.common.block.TFBlocks;

public class TFWorldGenHandler
{
    private World world;
    private Random rand;
    private int xCoord;
    private int zCoord;
    
    public WorldGenerator transformiumGen;
    public WorldGenerator energonGen;
    public WorldGenerator energonCrystalGen;
    public WorldGenerator redEnergonCrystalGen;

    public TFWorldGenHandler()
    {
        transformiumGen = new WorldGenMinable(TFBlocks.transformiumOre, 8);
        energonGen = new WorldGenMinable(TFBlocks.energonOre, 6);
        energonCrystalGen = new WorldGenCrystal(TFBlocks.energonCrystal, Material.rock);
        redEnergonCrystalGen = new WorldGenCrystal(TFBlocks.redEnergonCrystal, Material.rock);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event)
    {
        world = event.world;
        rand = event.rand;
        xCoord = event.worldX;
        zCoord = event.worldZ;

        genStandardOre(2, transformiumGen, 16);
        genStandardOre(4, energonGen, 32);
    }
    
    @SubscribeEvent
    public void onPopulateChunkPost(PopulateChunkEvent.Post event)
    {
        world = event.world;
        rand = event.rand;
        xCoord = event.chunkX * 16;
        zCoord = event.chunkZ * 16;
        
        genStandardOre(100, energonCrystalGen, 48);
        genStandardOre(10, redEnergonCrystalGen, 24);
    }

    protected void genStandardOre(int veins, WorldGenerator generator, int maxHeight)
    {
        if (TerrainGen.generateOre(world, rand, generator, xCoord, zCoord, CUSTOM))
        {
            for (int i = 0; i < veins; ++i)
            {
                int x = xCoord + rand.nextInt(16);
                int y = rand.nextInt(maxHeight);
                int z = zCoord + rand.nextInt(16);
                
                generator.generate(world, rand, x, y, z);
            }
        }
    }
}
