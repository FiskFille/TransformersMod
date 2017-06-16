package fiskfille.tf.common.generator;

import fiskfille.tf.common.block.TFBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM;

public class TFWorldGenHandler
{
    private World world;
    private Random rand;
    private BlockPos pos;

    public WorldGenerator transformiumGen;
    public WorldGenerator energonGen;
    public WorldGenerator energonCrystalGen;
    public WorldGenerator redEnergonCrystalGen;

    public TFWorldGenHandler()
    {
        this.transformiumGen = new WorldGenMinable(TFBlocks.TRANSFORMIUM_ORE.getDefaultState(), 8);
//        this.energonGen = new WorldGenMinable(TFBlocks.energonOre, 6);
//        this.energonCrystalGen = new WorldGenCrystal(TFBlocks.energonCrystal, Material.rock);
//        this.redEnergonCrystalGen = new WorldGenCrystal(TFBlocks.redEnergonCrystal, Material.rock);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event)
    {
        this.world = event.getWorld();
        this.rand = event.getRand();
        this.pos = event.getPos();

        this.genStandardOre(2, this.transformiumGen, 16);
//        this.genStandardOre(4, this.energonGen, 32);
    }

    @SubscribeEvent
    public void onPopulateChunkPost(PopulateChunkEvent.Post event)
    {
        this.world = event.getWorld();
        this.rand = event.getRand();
        this.pos = new BlockPos(event.getChunkX() << 4, 0, event.getChunkZ() << 4);

//        this.genStandardOre(100, this.energonCrystalGen, 48);
//        this.genStandardOre(10, this.redEnergonCrystalGen, 24);
    }

    protected void genStandardOre(int veins, WorldGenerator generator, int maxHeight)
    {
        if (TerrainGen.generateOre(this.world, this.rand, generator, this.pos, CUSTOM))
        {
            for (int i = 0; i < veins; ++i)
            {
                int offsetX = this.rand.nextInt(16);
                int offsetZ = this.rand.nextInt(16);
                int y = this.rand.nextInt(maxHeight);

                generator.generate(this.world, this.rand, this.pos.add(offsetX, y, offsetZ));
            }
        }
    }
}
