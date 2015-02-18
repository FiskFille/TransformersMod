package fiskfille.tf.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
	
	public void register()
	{
		transformiumOre = new BlockBasic(Material.rock).setHarvestLvl("pickaxe", 2).setHardness(10.0F).setResistance(1000.0F);
		displayPillar = new BlockDisplayPillar().setHardness(0.5F).setResistance(1.0F);
		energonCrystal = new BlockEnergonCrystal().setHarvestLvl("pickaxe", 1).setStepSound(Block.soundTypeGlass).setHardness(6.0F).setResistance(10.0F).setLightLevel(0.75F);
		energonCube = new BlockEnergonCube().setHarvestLvl("pickaxe", 1).setStepSound(Block.soundTypeGlass).setHardness(6.0F).setResistance(10.0F).setLightLevel(0.75F);
		transformiumStone = new BlockTransformiumStone().setHarvestLvl("pickaxe", 2).setHardness(2.5F).setResistance(10.0F);
		transformiumSeed = new BlockTransformiumSeed().setResistance(1000000.0F);
		
		
		TFBlockRegistry.registerBlock(transformiumOre, "Transformium Ore");
		TFBlockRegistry.registerTileEntity(displayPillar, "Display Pillar", TileEntityDisplayPillar.class);
		TFBlockRegistry.registerTileEntity(energonCrystal, "Energon Crystal", TileEntityCrystal.class);
		TFBlockRegistry.registerBlock(energonCube, "Energon Cube");
		TFBlockRegistry.registerBlock(transformiumStone, "Transformium Stone");
		TFBlockRegistry.registerTileEntity(transformiumSeed, "Transformium Seed", TileEntityTransformiumSeed.class);
	}
}