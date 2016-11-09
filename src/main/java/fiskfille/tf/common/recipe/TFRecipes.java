package fiskfille.tf.common.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.TFSubItems;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFRecipes
{
	public static void registerRecipes()
	{
		PowerManager.load();
		addSmelting();
		addDisplayRecipes();
		addWeaponRecipes();
		addCraftingComponentRecipes();
		addProjectileRecipes();
		addArmorRecipes();

		GameRegistry.addRecipe(new ItemStack(TFItems.transformiumDetector), "IEI", "TRT", "rrr", 'I', Items.iron_ingot, 'E', TFBlocks.energonCrystal, 'T', TFItems.transformium, 'R', Blocks.redstone_block, 'r', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(TFBlocks.transformiumSeed, 1), "TET", "TNT", "DND", 'T', TFBlocks.transformiumBlock, 'E', TFBlocks.energonCube, 'N', Items.nether_star, 'D', Blocks.diamond_block);

		addMaterialCompression(TFItems.energonCrystalPiece, TFBlocks.energonCube);
		addMaterialCompression(TFItems.redEnergonCrystalPiece, TFBlocks.redEnergonCube);
		addMaterialCompression(TFItems.transformium, TFBlocks.transformiumBlock);
	}

	private static void addSmelting()
	{
		GameRegistry.addSmelting(TFBlocks.transformiumOre, new ItemStack(TFItems.transformium, 1), 5.0F);
		GameRegistry.addSmelting(TFBlocks.transformiumStone, new ItemStack(TFItems.transformium, 1), 5.0F);
	}

	private static void addWeaponRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(TFItems.skystrikesCrossbow, 1), "EII", "ITC", "ICT", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece);
		GameRegistry.addRecipe(new ItemStack(TFItems.purgesKatana, 1), "CtC", "TET", "CIC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 't', TFSubItems.tank_track[1]);
		GameRegistry.addRecipe(new ItemStack(TFItems.vurpsSniper, 1), "EI ", "CTG", "CIT", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 'G', new ItemStack(Blocks.stained_glass, 1, 5));
		GameRegistry.addRecipe(new ItemStack(TFItems.subwoofersBassBlaster, 1), "TIC", " EB", "TIC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 'B', Blocks.iron_block);
		GameRegistry.addRecipe(new ItemStack(TFItems.cloudtrapsFlamethrower, 1), "CT ", "BET", " IC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 'B', Blocks.iron_block);
	}

	private static void addCraftingComponentRecipes()
	{
//		GameRegistry.addRecipe(TFSubItems.standard_engine[1], "PRP", "BIB", 'P', Blocks.piston, 'R', Items.redstone, 'I', Items.iron_ingot, 'B', Blocks.iron_block);
//		GameRegistry.addRecipe(TFSubItems.jet_turbine[1], "IIB", " JR", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'J', Blocks.iron_bars, 'R', Items.redstone);
//		GameRegistry.addRecipe(TFSubItems.jet_thruster[1], "IIB", "xRJ", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'J', Blocks.iron_bars, 'R', Items.redstone, 'x', Blocks.redstone_block);
//		GameRegistry.addRecipe(TFSubItems.ahd2_jet_wing[1], "II ", "TII", 'I', Items.iron_ingot, 'T', TFItems.transformium);
//		GameRegistry.addRecipe(TFSubItems.ahd2_jet_cockpit[1], "BG ", "BBI", 'B', Blocks.iron_block, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass, 1, 4));
//		GameRegistry.addRecipe(TFSubItems.t50_jet_wing[1], "  I", " II", "IIT", 'I', Items.iron_ingot, 'T', TFItems.transformium);
//		GameRegistry.addRecipe(TFSubItems.t50_jet_cockpit[1], "GIT", 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 5));
//		GameRegistry.addRecipe(TFSubItems.tank_track[1], "LLL", "IDI", "LLL", 'I', Items.iron_ingot, 'L', Items.leather, 'D', new ItemStack(TFItems.dye, 1, 1));
//		GameRegistry.addRecipe(TFSubItems.tank_turret[1], "  D", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'D', new ItemStack(TFItems.dye, 1, 1));
//		GameRegistry.addRecipe(TFSubItems.car_wheel[1], "DLD", "LIL", "DLD", 'I', Items.iron_ingot, 'L', Items.leather, 'D', Items.dye);
//		GameRegistry.addRecipe(TFSubItems.small_thruster[1], "III", " JR", "III", 'I', Items.iron_ingot, 'J', Blocks.iron_bars, 'R', Items.redstone);

		AssemblyTable.addRecipe(TFSubItems.optic_sensor, new ItemStack[] {new ItemStack(Items.dye, 1, 10)}, "     ", " DE  ", " ET  ", "   R ", "     ", 'D', Items.diamond, 'E', TFItems.energonCrystalPiece, 'T', TFItems.transformiumAlloy, 'R', Items.redstone);
		AssemblyTable.addRecipe(TFSubItems.protoform_head, new ItemStack[] {new ItemStack(Items.dye, 3, 8)}, "     ", " TTT ", " OTO ", " TRT ", "  T  ", 'T', TFItems.transformiumAlloy, 'O', TFSubItems.optic_sensor, 'R', Items.redstone);
		GameRegistry.addRecipe(TFSubItems.transformium_alloy_rod[1], "T", "T", "T", 'T', TFItems.transformiumAlloy);
		
		AssemblyTable.addRecipe(TFSubItems.tank_turret_base, new ItemStack[] {new ItemStack(TFItems.dye, 2, 1)}, "     ", "     ", " IBI ", "     ", "     ", 'I', Items.iron_ingot, 'B', Blocks.iron_block);
		AssemblyTable.addRecipe(TFSubItems.tank_barrel, new ItemStack[] {new ItemStack(TFItems.dye, 1, 1)}, "     ", "     ", " III ", "     ", "     ", 'I', Items.iron_ingot);
		AssemblyTable.addRecipe(TFSubItems.tank_turret, new ItemStack[] {}, "     ", "     ", " BT  ", "     ", "     ", 'B', TFSubItems.tank_barrel, 'T', TFSubItems.tank_turret_base);
		AssemblyTable.addRecipe(TFSubItems.tank_track, new ItemStack[] {new ItemStack(Items.dye, 5, 8), new ItemStack(TFItems.dye, 2, 1), new ItemStack(TFItems.dye, 1, 0)}, "     ", "LLLLL", "LIIIL", " LLL ", "     ", 'L', Items.leather, 'I', Items.iron_ingot);
		AssemblyTable.addRecipe(TFSubItems.purges_pauldron, new ItemStack[] {new ItemStack(TFItems.dye, 3, 1), new ItemStack(Items.dye, 1, 5)}, "     ", " TTT ", "   T ", "   T ", "     ", 'T', TFItems.transformiumAlloy);
		AssemblyTable.addRecipe(TFSubItems.purges_gauntlet, new ItemStack[] {new ItemStack(TFItems.dye, 2, 1), new ItemStack(TFItems.dye, 1, 0)}, "   T ", "  TT ", "  TT ", "  T  ", "     ", 'T', TFItems.transformiumAlloy);
		AssemblyTable.addRecipe(TFSubItems.purge_torso_base, new ItemStack[] {new ItemStack(TFItems.dye, 9, 1), new ItemStack(TFItems.dye, 4, 0), new ItemStack(TFItems.dye, 1, 2)}, "     ", "TTTTT", " TTT ", "  T  ", " TTT ", 'T', TFItems.transformiumAlloy);
		AssemblyTable.addRecipe(TFSubItems.purges_greave, new ItemStack[] {new ItemStack(TFItems.dye, 4, 1), new ItemStack(TFItems.dye, 2, 0)}, "  T  ", "  TT ", "  TT ", "  TT ", "     ", 'T', TFItems.transformiumAlloy);


		// Dyes
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.dye, 2, 0), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.dye, 2, 1), new ItemStack(Items.dye, 1, 3), new ItemStack(Items.dye, 1, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.dye, 2, 2), new ItemStack(Items.dye, 1, 8), new ItemStack(Items.dye, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.dye, 3, 2), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 0), new ItemStack(Items.dye, 1, 0));
	}

	private static void addDisplayRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(TFBlocks.displayPillar, 1), " - ", "SWS", '-', Blocks.stone_slab, 'S', Blocks.stone, 'W', Blocks.cobblestone_wall);
		GameRegistry.addRecipe(new ItemStack(TFBlocks.displayStation, 1), " L ", " I ", "-I-", '-', new ItemStack(Blocks.stone_slab, 1, 0), 'I', Items.iron_ingot, 'L', Blocks.redstone_lamp);
		GameRegistry.addRecipe(new ItemStack(TFItems.componentBase), "III", "ITI", "III", 'I', Items.iron_ingot, 'T', TFItems.transformium);
		GameRegistry.addRecipe(new ShapedOreRecipe(TFItems.colorComponent, "DDD", "DCD", "DDD", 'D', "dye", 'C', TFItems.componentBase));
		GameRegistry.addRecipe(new ItemStack(TFItems.armorComponent), " I ", "ICI", " I ", 'C', TFItems.componentBase, 'I', Items.iron_chestplate);
		GameRegistry.addRecipe(new RecipesDisplayItems());

		int i = 0;

		for (Transformer transformer : TransformersAPI.getTransformers())
		{
			GameRegistry.addShapelessRecipe(new ItemStack(TFItems.displayVehicle, 1, i), transformer.getHelmet(), transformer.getChestplate(), transformer.getLeggings(), transformer.getBoots());
			++i;
		}
	}

	private static void addProjectileRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(TFItems.missile, 4), "T  ", " I ", "  E", 'I', Items.iron_ingot, 'T', Blocks.tnt, 'E', TFSubItems.small_thruster[1]);
		GameRegistry.addRecipe(new ItemStack(TFItems.tankShell, 4), "IIT", 'I', Items.iron_ingot, 'T', Items.gunpowder);
	}

	private static void addArmorRecipes()
	{		
//		AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeHelmet, 1), "bIII ", " TTT ", " T T ", "I   I", " I I ", 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeChestplate, 1), "rICIw", "WT TW", "JTTTJ", "ITTTI", "     ", 'w', new ItemStack(Items.dye, 2, 15), 'r', new ItemStack(Items.dye, 5, 1), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'C', TFSubItems.ahd2_jet_cockpit[1], 'W', TFSubItems.ahd2_jet_wing[1], 'J', TFSubItems.jet_thruster[1]);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeLeggings, 1), "w   r", " TTT ", " T T ", " T T ", " I I ", 'w', new ItemStack(Items.dye, 3, 15), 'r', new ItemStack(Items.dye, 1, 1), 'T', TFItems.transformium, 'I', Items.iron_ingot);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeBoots, 1), "bI I ", " T T ", " T T ", "     ", "     ", 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot);

		AssemblyTable.addRecipe(new ItemStack(TFItems.purgeHelmet), new ItemStack[] {new ItemStack(TFItems.dye, 4, 2)}, " GNG ", " TGT ", " THT ", " T T ", "     ", 'G', Items.gold_ingot, 'N', Items.gold_nugget, 'T', TFItems.transformiumAlloy, 'H', TFSubItems.protoform_head);
		AssemblyTable.addRecipe(new ItemStack(TFItems.purgeChestplate), new ItemStack[] {}, "     ", " WOW ", " PBP ", " G G ", "     ", 'W', TFSubItems.tank_track, 'O', TFSubItems.tank_turret, 'P', TFSubItems.purges_pauldron, 'B', TFSubItems.purge_torso_base, 'G', TFSubItems.purges_gauntlet);
		AssemblyTable.addRecipe(new ItemStack(TFItems.purgeLeggings), new ItemStack[] {new ItemStack(TFItems.dye, 4, 2)}, "     ", "     ", " R R ", " G G ", "     ", 'R', TFSubItems.transformium_alloy_rod, 'G', TFSubItems.purges_greave);
		AssemblyTable.addRecipe(new ItemStack(TFItems.purgeBoots), new ItemStack[] {new ItemStack(Items.dye, 4, 0), new ItemStack(Items.dye, 3, 7)}, "     ", "     ", "     ", " TTT ", "     ", 'T', new ItemStack(TFItems.transformiumAlloy, 2));

//		AssemblyTable.addRecipe(new ItemStack(TFItems.vurpHelmet, 1), "gI Ib", "ITTTI", "IT TI", "I   I", " IGI ", 'g', new ItemStack(Items.dye, 3, 7), 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 5));
//		AssemblyTable.addRecipe(new ItemStack(TFItems.vurpChestplate, 1), "gWEWl", "IT TI", "GTTTG", "ITTTI", "b    ", 'g', new ItemStack(Items.dye, 5, 7), 'l', new ItemStack(Items.dye, 3, 10), 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 15), 'W', TFSubItems.car_wheel[1], 'E', TFSubItems.standard_engine[1]);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.vurpLeggings, 1), "gRIRl", " TTT ", "WT TW", " T T ", "     ", 'g', new ItemStack(Items.dye, 4, 7), 'l', new ItemStack(Items.dye, 2, 10), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'W', TFSubItems.car_wheel[1], 'R', Items.redstone);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.vurpBoots, 1), "g   b", " T T ", " T T ", "     ", "     ", 'g', new ItemStack(Items.dye, 2, 7), 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium);
//
//		AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferHelmet, 1), "b   y", "ITTTI", "IT TI", "I   I", "  G  ", 'b', new ItemStack(Items.dye, 3, 4), 'y', new ItemStack(Items.dye, 2, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 3));
//		AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferChestplate, 1), "bWGWy", "PT TP", "ITTTI", "ITTTC", " WEW ", 'b', new ItemStack(Items.dye, 5, 4), 'y', new ItemStack(Items.dye, 2, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 3), 'P', Blocks.heavy_weighted_pressure_plate, 'C', TFBlocks.energonCrystal, 'W', TFSubItems.car_wheel[1], 'E', TFSubItems.standard_engine[1]);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferLeggings, 1), "bRIRy", " TTT ", " T T ", " T T ", "     ", 'b', new ItemStack(Items.dye, 4, 4), 'y', new ItemStack(Items.dye, 1, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'R', Items.redstone);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferBoots, 1), "b   g", " T T ", " T T ", "     ", "     ", 'b', new ItemStack(Items.dye, 2, 4), 'g', new ItemStack(Items.dye, 2, 8), 'T', TFItems.transformium);
//
//		AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapHelmet, 1), "bIII ", "ITTTI", " T T ", "I   I", " BGB ", 'b', new ItemStack(Items.dye, 3, 0), 'y', new ItemStack(Items.dye, 2, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 5), 'B', Blocks.iron_block);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapChestplate, 1), "gI Ip", "WT TW", "ITTTI", "ITTTB", "  C  ", 'g', new ItemStack(Items.dye, 7, 7), 'p', new ItemStack(Items.dye, 1, 5), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'W', TFSubItems.t50_jet_wing[1], 'C', TFSubItems.t50_jet_cockpit[1]);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapLeggings, 1), "gIII ", " TTT ", "IT TI", "IT TI", "     ", 'g', new ItemStack(Items.dye, 6, 7), 'T', TFItems.transformium, 'I', Items.iron_ingot);
//		AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapBoots, 1), "g    ", " T T ", " T T ", "     ", " J J ", 'g', new ItemStack(Items.dye, 3, 7), 'T', TFItems.transformium, 'J', TFSubItems.jet_thruster[1]);
	}

	private static void addMaterialCompression(Item item, Block block)
	{
		GameRegistry.addShapelessRecipe(new ItemStack(item, 9), block);
		GameRegistry.addRecipe(new ItemStack(block, 1), "###", "###", "###", '#', item);
	}
}
