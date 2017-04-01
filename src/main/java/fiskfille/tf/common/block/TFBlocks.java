package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import fiskfille.tf.common.energon.TFEnergonManager;
import fiskfille.tf.common.item.ItemColoredTF;
import fiskfille.tf.common.item.ItemGroundBridgeControl;
import fiskfille.tf.common.registry.TFBlockRegistry;
import fiskfille.tf.common.tileentity.TileEntityAlloyCrucible;
import fiskfille.tf.common.tileentity.TileEntityAssemblyTable;
import fiskfille.tf.common.tileentity.TileEntityColumn;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityCrystal;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityEnergonTank;
import fiskfille.tf.common.tileentity.TileEntityEnergyPort;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeFrame;
import fiskfille.tf.common.tileentity.TileEntityGroundBridgeTeleporter;
import fiskfille.tf.common.tileentity.TileEntityIsoCondenser;
import fiskfille.tf.common.tileentity.TileEntityRelayTorch;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class TFBlocks
{
    // Misc
    public static Block transformiumOre;
    public static Block transformiumBlock;
    public static Block transformiumStone;
    public static Block transformiumSeed;
    public static Block cosmicRust;

    // Energon
    public static Block energonOre;
    public static Block energonCrystal;
    public static Block redEnergonCrystal;
    public static Block energonCube;
    public static Block redEnergonCube;

    // Aesthetic
    public static Block displayPillar;
    public static Block displayStation;
    public static Block wool;
    public static Block stainedHardenedClay;
    public static Block stainedGlass;
    public static Block stainedGlassPane;
    public static Block carpet;

    // Functional
    public static Block assemblyTable;
    public static Block alloyCrucible;
    public static Block energonProcessor;
    public static Block energonFluidTank;
    public static Block transmitter;
    public static Block relayTower;
    public static Block relayTorch;
    public static Block energyColumn;
    public static Block energyPort;
    public static Block isoCondenser;
    public static Block groundBridgeFrame;
    public static Block groundBridgeTeleporter;
    public static Block groundBridgeControlPanel;

    public static void register()
    {
        transformiumOre = new BlockBasic(Material.rock).setHarvestLvl("pickaxe", 2).setHardness(10.0F).setResistance(1000.0F);
        transformiumBlock = new BlockBasic(Material.rock).setHarvestLvl("pickaxe", 2).setHardness(7.0F).setResistance(1000000.0F);
        transformiumStone = new BlockTransformiumStone().setResistance(1000.0F);
        transformiumSeed = new BlockTransformiumSeed();
        cosmicRust = new BlockCosmicRust();

        energonOre = new BlockEnergonOre();
        energonCrystal = new BlockEnergonCrystal(TFEnergonManager.energon);
        redEnergonCrystal = new BlockEnergonCrystal(TFEnergonManager.redEnergon);
        energonCube = new BlockEnergonCube(TFEnergonManager.energon);
        redEnergonCube = new BlockEnergonCube(TFEnergonManager.redEnergon);

        displayPillar = new BlockDisplayPillar();
        displayStation = new BlockDisplayStation();
        wool = new BlockColoredTF(Material.cloth).setHardness(0.8F).setStepSound(Block.soundTypeCloth);
        stainedHardenedClay = new BlockColoredTF(Material.rock).setHardness(1.25F).setResistance(7.0F).setStepSound(Block.soundTypePiston);
        stainedGlass = new BlockStainedGlassTF().setHardness(0.3F).setStepSound(Block.soundTypeGlass);
        stainedGlassPane = new BlockStainedGlassPaneTF().setHardness(0.3F).setStepSound(Block.soundTypeGlass);
        carpet = new BlockCarpetTF().setHardness(0.1F).setStepSound(Block.soundTypeCloth).setLightOpacity(0);

        assemblyTable = new BlockAssemblyTable();
        alloyCrucible = new BlockAlloyCrucible();
        energonProcessor = new BlockEnergonProcessor();
        energonFluidTank = new BlockEnergonTank();
        transmitter = new BlockTransmitter();
        relayTower = new BlockRelayTower();
        relayTorch = new BlockRelayTorch();
        energyColumn = new BlockColumn();
        energyPort = new BlockEnergyPort();
        isoCondenser = new BlockIsoCondenser();
        groundBridgeFrame = new BlockGroundBridgeFrame();
        groundBridgeTeleporter = new BlockGroundBridgeTeleporter();
        groundBridgeControlPanel = new BlockControlPanel();
        

        TFBlockRegistry.registerBlock(transformiumOre, "Transformium Ore");
        TFBlockRegistry.registerBlock(transformiumBlock, "Block of Transformium");
        TFBlockRegistry.registerBlock(transformiumStone, "Transformium Stone");
        TFBlockRegistry.registerTileEntity(transformiumSeed, "Transformium Seed", TileEntityTransformiumSeed.class);
        TFBlockRegistry.registerItemBlock(cosmicRust, "Cosmic Rust");

        TFBlockRegistry.registerBlock(energonOre, "Energon Ore");
        TFBlockRegistry.registerTileEntity(energonCrystal, "Energon Crystal", TileEntityCrystal.class);
        TFBlockRegistry.registerTileEntity(redEnergonCrystal, "Red Energon Crystal", TileEntityCrystal.class);
        TFBlockRegistry.registerBlock(energonCube, "Energon Cube");
        TFBlockRegistry.registerBlock(redEnergonCube, "Red Energon Cube");

        TFBlockRegistry.registerTileEntity(displayPillar, "Display Pillar", TileEntityDisplayPillar.class);
        TFBlockRegistry.registerTileEntity(displayStation, "Transformer Display Station", TileEntityDisplayStation.class);
        TFBlockRegistry.registerItemBlock(wool, "Wool", ItemColoredTF.class);
        TFBlockRegistry.registerItemBlock(stainedHardenedClay, "Stained Hardened Clay", ItemColoredTF.class);
        TFBlockRegistry.registerItemBlock(stainedGlass, "Stained Glass", ItemColoredTF.class);
        TFBlockRegistry.registerItemBlock(stainedGlassPane, "Stained Glass Pane", ItemColoredTF.class);
        TFBlockRegistry.registerItemBlock(carpet, "Carpet", ItemColoredTF.class);

        TFBlockRegistry.registerTileEntity(assemblyTable, "Transformer Assembly Table", TileEntityAssemblyTable.class);
        TFBlockRegistry.registerMachine(alloyCrucible, "Alloy Crucible", TileEntityAlloyCrucible.class);
        TFBlockRegistry.registerMachine(energonProcessor, "Energon Processor", TileEntityEnergonProcessor.class);
        TFBlockRegistry.registerMachine(energonFluidTank, "Energon Fluid Tank", TileEntityEnergonTank.class);
        TFBlockRegistry.registerMachine(transmitter, "Energon Transmitter", TileEntityTransmitter.class);
        TFBlockRegistry.registerMachine(relayTower, "Energy Relay", TileEntityRelayTower.class);
        TFBlockRegistry.registerMachine(relayTorch, "Energy Relay Torch", TileEntityRelayTorch.class);
        TFBlockRegistry.registerMachine(energyColumn, "Energy Column", TileEntityColumn.class);
        TFBlockRegistry.registerMachine(energyPort, "Energy Port", TileEntityEnergyPort.class);
        TFBlockRegistry.registerMachine(isoCondenser, "Isotopic Condenser", TileEntityIsoCondenser.class);
        TFBlockRegistry.registerTileEntity(groundBridgeFrame, "Ground Bridge Frame", TileEntityGroundBridgeFrame.class);
        TFBlockRegistry.registerTileEntity(groundBridgeTeleporter, "Ground Bridge Teleporter", TileEntityGroundBridgeTeleporter.class);
        TFBlockRegistry.registerItemBlockAsTileEntity(groundBridgeControlPanel, "Ground Bridge Control Panel", TileEntityControlPanel.class, ItemGroundBridgeControl.class);


        groundBridgeTeleporter.setCreativeTab(null);
    }
}
