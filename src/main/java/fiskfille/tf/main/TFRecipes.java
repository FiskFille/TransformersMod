package fiskfille.tf.main;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class TFRecipes
{
	public static void load()
	{
		GameRegistry.addSmelting(TFBlocks.transformiumOre, new ItemStack(TFItems.transformium, 1), 5.0F);
		
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.displayVehicle, 1, 0), new Object[] {TFItems.skystrikeHelmet, TFItems.skystrikeChestplate, TFItems.skystrikeLeggings, TFItems.skystrikeBoots});
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.displayVehicle, 1, 1), new Object[] {TFItems.purgeHelmet, TFItems.purgeChestplate, TFItems.purgeLeggings, TFItems.purgeBoots});
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.displayVehicle, 1, 2), new Object[] {TFItems.vurpHelmet, TFItems.vurpChestplate, TFItems.vurpLeggings, TFItems.vurpBoots});
		GameRegistry.addShapelessRecipe(new ItemStack(TFItems.displayVehicle, 1, 3), new Object[] {TFItems.subwooferHelmet, TFItems.subwooferChestplate, TFItems.subwooferLeggings, TFItems.subwooferBoots});
		GameRegistry.addRecipe(new ItemStack(TFBlocks.displayPillar, 1), new Object[] {" - ", "SWS", '-', Blocks.stone_slab, 'S', Blocks.stone, 'W', Blocks.cobblestone_wall});
//		GameRegistry.addRecipe(new ItemStack(TFBlocks.energonCube, 1), new Object[] {"CCC", "CCC", "CCC", 'C', TFItems.energonCrystalPiece});
		
		GameRegistry.addRecipe(new ItemStack(TFItems.skystrikesCrossbow, 1), new Object[] {"EII", "ITC", "ICT", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece});
		GameRegistry.addRecipe(new ItemStack(TFItems.purgesKatana, 1), new Object[] {"CtC", "TET", "CIC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 't', TFItems.tankTracks});
		
		
		// Crafting Components
		GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 0), new Object[] {"TTT", "T T", 'T', TFItems.transformium});
		GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 1), new Object[] {"T T", "TTT", "TTT", 'T', TFItems.transformium});
		GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 2), new Object[] {"TTT", "T T", "T T", 'T', TFItems.transformium});
		GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 3), new Object[] {"T T", "T T", 'T', TFItems.transformium});
		GameRegistry.addRecipe(new ItemStack(TFItems.standardEngine, 1), new Object[] {"PRP", "BIB", 'P', Blocks.piston, 'R', Items.redstone, 'I', Items.iron_ingot, 'B', Blocks.iron_block});
		GameRegistry.addRecipe(new ItemStack(TFItems.jetThruster, 1), new Object[] {"IIB", " JR", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'J', Blocks.iron_bars, 'R', Items.redstone});
		GameRegistry.addRecipe(new ItemStack(TFItems.f88JetWing, 1), new Object[] {"II ", "TII", 'I', Items.iron_ingot, 'T', TFItems.transformium});
		GameRegistry.addRecipe(new ItemStack(TFItems.f88JetCockpit, 1), new Object[] {"GII", 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(TFItems.tankTracks, 1), new Object[] {"LLL", "I I", "LLL", 'I', Items.iron_ingot, 'L', Items.leather});
		GameRegistry.addRecipe(new ItemStack(TFItems.tankTurret, 1), new Object[] {"  D", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'D', new ItemStack(Items.dye, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(TFItems.carWheel, 1), new Object[] {"DLD", "LIL", "DLD", 'I', Items.iron_ingot, 'L', Items.leather, 'D', Items.dye});
		
		
		GameRegistry.addRecipe(new ItemStack(TFItems.skystrikeHelmet, 1), new Object[] {"I*I", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 0), 'I', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(TFItems.skystrikeChestplate, 1), new Object[] {"WCW", "r*r", " w ", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 1), 'C', TFItems.f88JetCockpit, 'W', TFItems.f88JetWing, 'r', new ItemStack(Items.dye, 1, 1), 'w', new ItemStack(Items.dye, 1, 15)});
		GameRegistry.addRecipe(new ItemStack(TFItems.skystrikeLeggings, 1), new Object[] {"w*w", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 2), 'w', new ItemStack(Items.dye, 1, 15)});
		GameRegistry.addRecipe(new ItemStack(TFItems.skystrikeBoots, 1), new Object[] {"b*b", "T T", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 3), 'T', TFItems.jetThruster, 'b', new ItemStack(Items.dye, 1, 0)});
		
		GameRegistry.addRecipe(new ItemStack(TFItems.purgeHelmet, 1), new Object[] {"IGI", "I*I", "IbI", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 0), 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'b', new ItemStack(Items.dye, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(TFItems.purgeChestplate, 1), new Object[] {"WTW", "G*G", " r ", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 1), 'W', TFItems.tankTracks, 'T', TFItems.tankTurret, 'r', new ItemStack(Items.dye, 1, 1), 'G', Items.gold_ingot});
		GameRegistry.addRecipe(new ItemStack(TFItems.purgeLeggings, 1), new Object[] {"I*I", "r r", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 2), 'r', new ItemStack(Items.dye, 1, 1), 'I', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(TFItems.purgeBoots, 1), new Object[] {"r*r", "b b", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 3), 'b', new ItemStack(Items.dye, 1, 0), 'r', new ItemStack(Items.dye, 1, 1)});
		
		GameRegistry.addRecipe(new ItemStack(TFItems.vurpHelmet, 1), new Object[] {"sIs", "I*I", " G ", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 0), 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 5), 's', new ItemStack(Items.dye, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(TFItems.vurpChestplate, 1), new Object[] {"WEW", "s*s", " s ", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 1), 'W', TFItems.carWheel, 'E', TFItems.standardEngine, 's', new ItemStack(Items.dye, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(TFItems.vurpLeggings, 1), new Object[] {"s*s", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 2), 's', new ItemStack(Items.dye, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(TFItems.vurpBoots, 1), new Object[] {"s*s", "W W", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 3), 'W', TFItems.carWheel, 's', new ItemStack(Items.dye, 1, 7)});
		
		GameRegistry.addRecipe(new ItemStack(TFItems.subwooferHelmet, 1), new Object[] {"IbI", "I*I", " G ", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 0), 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 3), 'b', new ItemStack(Items.dye, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(TFItems.subwooferChestplate, 1), new Object[] {"WEW", "b*b", " b ", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 1), 'W', TFItems.carWheel, 'E', TFItems.standardEngine, 'b', new ItemStack(Items.dye, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(TFItems.subwooferLeggings, 1), new Object[] {"b*b", "W W", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 2), 'b', new ItemStack(Items.dye, 1, 4), 'W', TFItems.carWheel});
		GameRegistry.addRecipe(new ItemStack(TFItems.subwooferBoots, 1), new Object[] {"b*b", "W W", '*', new ItemStack(TFItems.transformiumArmorMolds, 1, 3), 'b', new ItemStack(Items.dye, 1, 4)});
	}
}