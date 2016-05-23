package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import fiskfille.tf.common.energon.DefaultEnergon;
import fiskfille.tf.common.energon.RedEnergon;
import fiskfille.tf.common.item.ItemGroundBridgeControl;
import fiskfille.tf.common.registry.TFBlockRegistry;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityCrystal;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeFrame;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class TFBlocks
{
    public static Block transformiumOre;
    public static Block displayPillar;
    public static Block energonCrystal;
    public static Block redEnergonCrystal;
    public static Block energonCube;
    public static Block transformiumStone;
    public static Block transformiumSeed;
    public static Block transformiumBlock;
    public static Block cosmicRust;
    public static Block displayStation;
    public static Block energonProcessor;
    public static Block transmitter;
    public static Block groundBridgeFrame;
    public static Block groundBridgeTeleporter;
    public static Block assemblyTable;
    public static Block groundBridgeControlPanel;

    public void register()
    {
        transformiumOre = new BlockBasic(Material.rock).setHarvestLvl("pickaxe", 2).setHardness(10.0F).setResistance(1000.0F);
        displayPillar = new BlockDisplayPillar();
        energonCrystal = new BlockEnergonCrystal(new DefaultEnergon());
        redEnergonCrystal = new BlockEnergonCrystal(new RedEnergon());
        energonCube = new BlockEnergonCube();
        transformiumStone = new BlockTransformiumStone().setResistance(1000.0F);
        transformiumSeed = new BlockTransformiumSeed();
        transformiumBlock = new BlockBasic(Material.rock).setHarvestLvl("pickaxe", 2).setHardness(7.0F).setResistance(1000000.0F);
        cosmicRust = new BlockCosmicRust();
        displayStation = new BlockDisplayStation();
        energonProcessor = new BlockEnergonProcessor();
        transmitter = new BlockTransmitter();
        groundBridgeFrame = new BlockGroundBridgeFrame();
        groundBridgeTeleporter = new BlockGroundBridgeTeleporter();
        groundBridgeControlPanel = new BlockGroundBridgeControl();
        assemblyTable = new BlockAssemblyTable();


        TFBlockRegistry.registerBlock(transformiumOre, "Transformium Ore");
        TFBlockRegistry.registerTileEntity(displayPillar, "Display Pillar", TileEntityDisplayPillar.class);
        TFBlockRegistry.registerTileEntity(energonCrystal, "Energon Crystal", TileEntityCrystal.class);
        TFBlockRegistry.registerTileEntity(redEnergonCrystal, "Red Energon Crystal", TileEntityCrystal.class);
        TFBlockRegistry.registerBlock(energonCube, "Energon Cube");
        TFBlockRegistry.registerBlock(transformiumStone, "Transformium Stone");
        TFBlockRegistry.registerTileEntity(transformiumSeed, "Transformium Seed", TileEntityTransformiumSeed.class);
        TFBlockRegistry.registerBlock(transformiumBlock, "Block of Transformium");
        TFBlockRegistry.registerItemBlock(cosmicRust, "Cosmic Rust");
        TFBlockRegistry.registerTileEntity(displayStation, "Transformer Display Station", TileEntityDisplayStation.class);
        TFBlockRegistry.registerTileEntity(energonProcessor, "Energon Processor", TileEntityEnergonProcessor.class);
        TFBlockRegistry.registerTileEntity(transmitter, "Energon Transmitter", TileEntityTransmitter.class);
        TFBlockRegistry.registerTileEntity(groundBridgeFrame, "Ground Bridge Frame", TileEntityGroundBridgeFrame.class);
        TFBlockRegistry.registerTileEntity(groundBridgeTeleporter, "Ground Bridge Teleporter", TileEntityGroundBridgeTeleporter.class);
        TFBlockRegistry.registerItemBlockAsTileEntity(groundBridgeControlPanel, "Ground Bridge Control Panel", TileEntityControlPanel.class, ItemGroundBridgeControl.class);
        TFBlockRegistry.registerBlock(assemblyTable, "Transformer Assembly Table");

        groundBridgeTeleporter.setCreativeTab(null);
    }
}
