package fiskfille.tf.common.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
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

        GameRegistry.addRecipe(new ItemStack(TFItems.transformiumDetector), new Object[] {"IEI", "TRT", "rrr", 'I', Items.iron_ingot, 'E', TFBlocks.energonCrystal, 'T', TFItems.transformium, 'R', Blocks.redstone_block, 'r', Items.redstone});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.transformiumSeed, 1), new Object[] {"TET", "TNT", "DND", 'T', TFBlocks.transformiumBlock, 'E', TFBlocks.energonCube, 'N', Items.nether_star, 'D', Blocks.diamond_block});

        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.energonCrystalPiece, 9), new Object[] {TFBlocks.energonCube});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.transformium, 9), new Object[] {TFBlocks.transformiumBlock});

        GameRegistry.addRecipe(new ItemStack(TFBlocks.energonCube, 1), new Object[] {"CCC", "CCC", "CCC", 'C', TFItems.energonCrystalPiece});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.transformiumBlock, 1), new Object[] {"CCC", "CCC", "CCC", 'C', TFItems.transformium});
    }

    private static void addSmelting()
    {
        GameRegistry.addSmelting(TFBlocks.transformiumOre, new ItemStack(TFItems.transformium, 1), 5.0F);
        GameRegistry.addSmelting(TFBlocks.transformiumStone, new ItemStack(TFItems.transformium, 1), 5.0F);
    }

    private static void addWeaponRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(TFItems.skystrikesCrossbow, 1), new Object[] {"EII", "ITC", "ICT", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece});
        GameRegistry.addRecipe(new ItemStack(TFItems.purgesKatana, 1), new Object[] {"CtC", "TET", "CIC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 't', TFItems.tankTracks});
        GameRegistry.addRecipe(new ItemStack(TFItems.vurpsSniper, 1), new Object[] {"EI ", "CTG", "CIT", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 'G', new ItemStack(Blocks.stained_glass, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(TFItems.subwoofersBassBlaster, 1), new Object[] {"TIC", " EB", "TIC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 'B', Blocks.iron_block});
        GameRegistry.addRecipe(new ItemStack(TFItems.cloudtrapsFlamethrower, 1), new Object[] {"CT ", "BET", " IC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformium, 'C', TFItems.energonCrystalPiece, 'B', Blocks.iron_block});
    }

    private static void addCraftingComponentRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 0), new Object[] {"TTT", "T T", 'T', TFItems.transformium});
        GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 1), new Object[] {"T T", "TTT", "TTT", 'T', TFItems.transformium});
        GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 2), new Object[] {"TTT", "T T", "T T", 'T', TFItems.transformium});
        GameRegistry.addRecipe(new ItemStack(TFItems.transformiumArmorMolds, 1, 3), new Object[] {"T T", "T T", 'T', TFItems.transformium});
        GameRegistry.addRecipe(new ItemStack(TFItems.standardEngine, 1), new Object[] {"PRP", "BIB", 'P', Blocks.piston, 'R', Items.redstone, 'I', Items.iron_ingot, 'B', Blocks.iron_block});
        GameRegistry.addRecipe(new ItemStack(TFItems.jetTurbine, 1), new Object[] {"IIB", " JR", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'J', Blocks.iron_bars, 'R', Items.redstone});
        GameRegistry.addRecipe(new ItemStack(TFItems.jetThruster, 1), new Object[] {"IIB", "xRJ", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'J', Blocks.iron_bars, 'R', Items.redstone, 'x', Blocks.redstone_block});
        GameRegistry.addRecipe(new ItemStack(TFItems.ahd2JetWing, 1), new Object[] {"II ", "TII", 'I', Items.iron_ingot, 'T', TFItems.transformium});
        GameRegistry.addRecipe(new ItemStack(TFItems.ahd2JetCockpit, 1), new Object[] {"BG ", "BBI", 'B', Blocks.iron_block, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass, 1, 4)});
        GameRegistry.addRecipe(new ItemStack(TFItems.t50JetWing, 1), new Object[] {"  I", " II", "IIT", 'I', Items.iron_ingot, 'T', TFItems.transformium});
        GameRegistry.addRecipe(new ItemStack(TFItems.t50JetCockpit, 1), new Object[] {"GIT", 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 5)});
        GameRegistry.addRecipe(new ItemStack(TFItems.tankTracks, 1), new Object[] {"LLL", "IDI", "LLL", 'I', Items.iron_ingot, 'L', Items.leather, 'D', new ItemStack(TFItems.dye, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(TFItems.tankTurret, 1), new Object[] {"  D", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'D', new ItemStack(TFItems.dye, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(TFItems.carWheel, 1), new Object[] {"DLD", "LIL", "DLD", 'I', Items.iron_ingot, 'L', Items.leather, 'D', Items.dye});
        GameRegistry.addRecipe(new ItemStack(TFItems.smallThruster, 1), new Object[] {"III", " JR", "III", 'I', Items.iron_ingot, 'J', Blocks.iron_bars, 'R', Items.redstone});
        
        // Dyes
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.dye, 2, 0), new Object[] {new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 0)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.dye, 3, 1), new Object[] {new ItemStack(Items.dye, 1, 3), new ItemStack(Items.dye, 1, 11), new ItemStack(Items.dye, 1, 15)});
    }

    private static void addDisplayRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(TFBlocks.displayPillar, 1), new Object[] {" - ", "SWS", '-', Blocks.stone_slab, 'S', Blocks.stone, 'W', Blocks.cobblestone_wall});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.displayStation, 1), new Object[] {" L ", " I ", "-I-", '-', new ItemStack(Blocks.stone_slab, 1, 0), 'I', Items.iron_ingot, 'L', Blocks.redstone_lamp});
        GameRegistry.addRecipe(new ShapedOreRecipe(TFItems.colorComponent, new Object[] {"DDD", "DID", "DDD", 'D', "dye", 'I', Items.iron_ingot}));
        GameRegistry.addRecipe(new RecipesDisplayItems());

        int i = 0;

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            GameRegistry.addShapelessRecipe(new ItemStack(TFItems.displayVehicle, 1, i), new Object[] {transformer.getHelmet(), transformer.getChestplate(), transformer.getLeggings(), transformer.getBoots()});
            ++i;
        }
    }

    private static void addProjectileRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(TFItems.missile, 4), new Object[] {"T  ", " I ", "  E", 'I', Items.iron_ingot, 'T', Blocks.tnt, 'E', TFItems.smallThruster});
        GameRegistry.addRecipe(new ItemStack(TFItems.tankShell, 4), new Object[] {"IIT", 'I', Items.iron_ingot, 'T', Items.gunpowder});
    }

    private static void addArmorRecipes()
    {
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.skystrikeHelmet, 1),  new Object[] {"bIII ", " TTT ", " T T ", "I   I", " I I ", 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.skystrikeChestplate, 1),  new Object[] {"rICIw", "WT TW", "JTTTJ", "ITTTI", "     ", 'w', new ItemStack(Items.dye, 2, 15), 'r', new ItemStack(Items.dye, 5, 1), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'C', TFItems.ahd2JetCockpit, 'W', TFItems.ahd2JetWing, 'J', TFItems.jetThruster});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.skystrikeLeggings, 1),  new Object[] {"w   r", " TTT ", " T T ", " T T ", " I I ", 'w', new ItemStack(Items.dye, 3, 15), 'r', new ItemStack(Items.dye, 1, 1), 'T', TFItems.transformium, 'I', Items.iron_ingot});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.skystrikeBoots, 1),  new Object[] {"bI I ", " T T ", " T T ", "     ", "     ", 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot});
    	
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.purgeHelmet, 1),  new Object[] {"gGGG ", "ITTTI", "IT TI", "I   I", "     ", 'g', new ItemStack(Items.dye, 5, 8), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', Items.gold_ingot});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.purgeChestplate, 1),  new Object[] {"bUOUr", "BT TB", "ITTTI", "ITTTI", "     ", 'b', new ItemStack(TFItems.dye, 5, 1), 'r', new ItemStack(TFItems.dye, 3, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'U', TFItems.tankTracks, 'O', TFItems.tankTurret});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.purgeLeggings, 1),  new Object[] {"b   r", "ITTTI", "IT TI", " T T ", "     ", 'b', new ItemStack(TFItems.dye, 5, 1), 'r', new ItemStack(TFItems.dye, 3, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.purgeBoots, 1),  new Object[] {"r   b", " T T ", " T T ", "     ", "     ", 'r', new ItemStack(Items.dye, 2, 0), 'b', new ItemStack(TFItems.dye, 1, 1), 'T', TFItems.transformium});
    	
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.vurpHelmet, 1),  new Object[] {"gI Ib", "ITTTI", "IT TI", "I   I", " IGI ", 'g', new ItemStack(Items.dye, 3, 7), 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 5)});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.vurpChestplate, 1),  new Object[] {"gWEWl", "IT TI", "GTTTG", "ITTTI", "b    ", 'g', new ItemStack(Items.dye, 5, 7), 'l', new ItemStack(Items.dye, 3, 10), 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 15), 'W', TFItems.carWheel, 'E', TFItems.standardEngine});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.vurpLeggings, 1),  new Object[] {"gRIRl", " TTT ", "WT TW", " T T ", "     ", 'g', new ItemStack(Items.dye, 4, 7), 'l', new ItemStack(Items.dye, 2, 10), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'W', TFItems.carWheel, 'R', Items.redstone});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.vurpBoots, 1),  new Object[] {"g   b", " T T ", " T T ", "     ", "     ", 'g', new ItemStack(Items.dye, 2, 7), 'b', new ItemStack(Items.dye, 2, 0), 'T', TFItems.transformium});
    	
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.subwooferHelmet, 1),  new Object[] {"b   y", "ITTTI", "IT TI", "I   I", "  G  ", 'b', new ItemStack(Items.dye, 3, 4), 'y', new ItemStack(Items.dye, 2, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 3)});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.subwooferChestplate, 1),  new Object[] {"bWGWy", "PT TP", "ITTTI", "ITTTC", " WEW ", 'b', new ItemStack(Items.dye, 5, 4), 'y', new ItemStack(Items.dye, 2, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 3), 'P', Blocks.heavy_weighted_pressure_plate, 'C', TFBlocks.energonCrystal, 'W', TFItems.carWheel, 'E', TFItems.standardEngine});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.subwooferLeggings, 1),  new Object[] {"bRIRy", " TTT ", " T T ", " T T ", "     ", 'b', new ItemStack(Items.dye, 4, 4), 'y', new ItemStack(Items.dye, 1, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'R', Items.redstone});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.subwooferBoots, 1),  new Object[] {"b   g", " T T ", " T T ", "     ", "     ", 'b', new ItemStack(Items.dye, 2, 4), 'g', new ItemStack(Items.dye, 2, 8), 'T', TFItems.transformium});
    	
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.cloudtrapHelmet, 1),  new Object[] {"bIII ", "ITTTI", " T T ", "I   I", " BGB ", 'b', new ItemStack(Items.dye, 3, 0), 'y', new ItemStack(Items.dye, 2, 11), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 5), 'B', Blocks.iron_block});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.cloudtrapChestplate, 1),  new Object[] {"gI Ip", "WT TW", "ITTTI", "ITTTB", "  C  ", 'g', new ItemStack(Items.dye, 7, 7), 'p', new ItemStack(Items.dye, 1, 5), 'T', TFItems.transformium, 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'W', TFItems.t50JetWing, 'C', TFItems.t50JetCockpit});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.cloudtrapLeggings, 1),  new Object[] {"gIII ", " TTT ", "IT TI", "IT TI", "     ", 'g', new ItemStack(Items.dye, 6, 7), 'T', TFItems.transformium, 'I', Items.iron_ingot});
    	AssemblyTableCraftingManager.getInstance().addRecipe(new ItemStack(TFItems.cloudtrapBoots, 1),  new Object[] {"g   g", " T T ", " T T ", "     ", " J J ", 'g', new ItemStack(Items.dye, 3, 7), 'T', TFItems.transformium, 'J', TFItems.jetThruster});
    }
}