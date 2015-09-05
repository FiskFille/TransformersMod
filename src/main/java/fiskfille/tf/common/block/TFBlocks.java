package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.registry.TFBlockRegistry;
import fiskfille.tf.common.tileentity.TileEntityCrystal;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;

public class TFBlocks
{
    public static Block transformiumOre;
    public static Block displayPillar;
    public static Block energonCrystal;
    public static Block energonCube;
    public static Block transformiumStone;
    public static Block transformiumSeed;
    public static Block transformiumBlock;
    public static BlockCosmicRust cosmicRust;

    public void register()
    {
        transformiumOre = new BlockBasic(Material.rock).setHarvestLvl("pickaxe", 2).setHardness(10.0F).setResistance(1000.0F);
        displayPillar = new BlockDisplayPillar();
        energonCrystal = new BlockEnergonCrystal();
        energonCube = new BlockEnergonCube();
        transformiumStone = new BlockTransformiumStone().setResistance(1000.0F);
        transformiumSeed = new BlockTransformiumSeed();
        transformiumBlock = new BlockBasic(Material.rock).setHarvestLvl("pickaxe", 2).setHardness(7.0F).setResistance(1000000.0F);
        cosmicRust = new BlockCosmicRust();


        String modId = TransformersMod.modid;
        TFBlockRegistry.registerBlock(transformiumOre, "Transformium Ore", modId);
        TFBlockRegistry.registerTileEntity(displayPillar, "Display Pillar", TileEntityDisplayPillar.class, modId);
        TFBlockRegistry.registerTileEntity(energonCrystal, "Energon Crystal", TileEntityCrystal.class, modId);
        TFBlockRegistry.registerBlock(energonCube, "Energon Cube", modId);
        TFBlockRegistry.registerBlock(transformiumStone, "Transformium Stone", modId);
        TFBlockRegistry.registerTileEntity(transformiumSeed, "Transformium Seed", TileEntityTransformiumSeed.class, modId);
        TFBlockRegistry.registerBlock(transformiumBlock, "Block of Transformium", modId);
        TFBlockRegistry.registerItemBlock(cosmicRust, "Cosmic Rust", modId);
    }
}