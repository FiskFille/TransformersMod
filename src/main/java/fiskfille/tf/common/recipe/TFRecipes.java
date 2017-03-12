package fiskfille.tf.common.recipe;

import static fiskfille.tf.common.recipe.Dyes.BEIGE;
import static fiskfille.tf.common.recipe.Dyes.BLACK;
import static fiskfille.tf.common.recipe.Dyes.BLUE;
import static fiskfille.tf.common.recipe.Dyes.BROWN;
import static fiskfille.tf.common.recipe.Dyes.DARK_GRAY;
import static fiskfille.tf.common.recipe.Dyes.DARK_RED;
import static fiskfille.tf.common.recipe.Dyes.GRAY;
import static fiskfille.tf.common.recipe.Dyes.LIGHT_BLUE;
import static fiskfille.tf.common.recipe.Dyes.LIME;
import static fiskfille.tf.common.recipe.Dyes.PALE_BROWN;
import static fiskfille.tf.common.recipe.Dyes.PALE_GREEN;
import static fiskfille.tf.common.recipe.Dyes.PURPLE;
import static fiskfille.tf.common.recipe.Dyes.RED;
import static fiskfille.tf.common.recipe.Dyes.SILVER;
import static fiskfille.tf.common.recipe.Dyes.WHITE;
import static fiskfille.tf.common.recipe.Dyes.YELLOW;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.registry.GameRegistry;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.groundbridge.DataCore;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.item.TFSubItems;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFRecipes
{
    public static List<IRecipe> tempRecipes = Lists.newArrayList();
    public static List<ItemStack> tempFurnaceRecipes = Lists.newArrayList();
    
    public static void register()
    {
        restore();
        List<IRecipe> prevRecipes = Lists.newArrayList(CraftingManager.getInstance().getRecipeList());
        List<ItemStack> prevFurnaceRecipes = Lists.newArrayList(FurnaceRecipes.smelting().getSmeltingList().keySet());
        
        PowerManager.register();
        AlloyRecipes.register();
        addSmelting();
        addDisplayRecipes();
        addDyeRecipes();
        addWeaponRecipes();
        addCraftingComponentRecipes();
        addProjectileRecipes();
        addArmorRecipes();

        GameRegistry.addRecipe(new RecipeClearConfig());
        GameRegistry.addRecipe(new ItemStack(TFBlocks.transformiumSeed, 1), "TET", "TNT", "DND", 'T', TFBlocks.transformiumBlock, 'E', TFBlocks.energonCube, 'N', Items.nether_star, 'D', Blocks.diamond_block);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.assemblyTable, 1), "EEE", "WCW", "IBI", 'E', TFItems.energonDust, 'W', TFSubItems.crude_alloy_wire[1], 'C', Blocks.crafting_table, 'I', Items.iron_ingot, 'B', Blocks.iron_block);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.alloyCrucible, 1), "IFI", "IUI", "IRI", 'I', Items.iron_ingot, 'F', Blocks.furnace, 'U', Items.cauldron, 'R', Items.redstone);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.energonProcessor, 1), " I ", "2W2", "BFB", 'I', Items.iron_ingot, '2', TFSubItems.improved_circuit[1], 'W', TFSubItems.refined_alloy_wire[1], 'B', Blocks.iron_block, 'F', TFItems.fuelCanister);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.energonFluidTank, 1), "IGI", "GFG", "IGI", 'I', Items.iron_ingot, 'G', Blocks.glass_pane, 'F', TFItems.fuelCanister);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.transmitter, 1), "EOE", "PRP", "S2S", 'E', TFItems.energonDust, 'O', TFSubItems.transmission_orb[1], 'P', TFSubItems.iron_pole[1], 'R', TFBlocks.relayTower, 'S', TFSubItems.superconductor[1], '2', TFSubItems.improved_circuit[1]);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.relayTower, 1), " R ", " W ", "PSP", 'R', TFBlocks.relayTorch, 'W', TFSubItems.crude_alloy_wire[1], 'P', TFSubItems.iron_pole[1], 'S', TFSubItems.iron_support[1]);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.relayTorch, 2), "O", "1", "W", 'O', TFSubItems.transmission_orb[1], '1', TFSubItems.basic_circuit[1], 'W', TFSubItems.refined_alloy_wire[1]);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.energyColumn, 1), "LCL", "EPE", "IBI", 'L', TFSubItems.canister_lid[1], 'C', TFSubItems.energy_converter[1], 'E', TFItems.energonAlloy, 'P', TFSubItems.lcd_panel[1], 'I', Items.iron_ingot, 'B', Blocks.iron_block);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.energyPort, 1), "IEI", "ECE", "IEI", 'I', Items.iron_ingot, 'E', TFItems.energonAlloy, 'C', TFSubItems.energon_coil[1]);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.isoCondenser, 1), "III", "RER", "ISI", 'I', Items.iron_ingot, 'R', Items.redstone, 'E', TFBlocks.energonCube, 'S', TFSubItems.iron_support[1]);
        AssemblyTable.addRecipe(new ItemStack(TFBlocks.groundBridgeFrame, 1), new Dyes(), "     ", " SES ", " E1E ", " SES ", "     ", 'S', TFSubItems.iron_support, 'E', TFItems.energonDust, '1', TFSubItems.basic_circuit);
        AssemblyTable.addRecipe(new ItemStack(TFBlocks.groundBridgeControlPanel, 1), new Dyes(), " PPP ", "IW3WI", "SCOCS", "FF3FF", "     ", 'P', TFSubItems.lcd_panel, 'I', Items.iron_ingot, 'W', TFSubItems.refined_alloy_wire, '3', TFSubItems.advanced_circuit, 'S', TFSubItems.iron_support, 'C', TFSubItems.energy_converter, 'O', TFSubItems.singularity, 'F', TFBlocks.groundBridgeFrame);
        
        GameRegistry.addRecipe(new ItemStack(TFItems.transformiumDetector, 1), "IEI", "TRT", "rrr", 'I', Items.iron_ingot, 'E', TFBlocks.energonCrystal, 'T', TFItems.transformiumFragment, 'R', Blocks.redstone_block, 'r', Items.redstone);
        GameRegistry.addRecipe(new ItemStack(TFItems.csd, 1), "I", "1", "I", 'I', Items.iron_ingot, '1', TFSubItems.basic_circuit[1]);
        GameRegistry.addRecipe(new ItemStack(TFItems.fuelCanister, 1), "L", "B", "L", 'L', TFSubItems.canister_lid[1], 'B', Items.bucket);
        GameRegistry.addRecipe(new ItemStack(TFItems.powerCanister, 1), "L", "G", "L", 'L', TFSubItems.canister_lid[1], 'G', Blocks.glass);
        AssemblyTable.addRecipe(new ItemStack(TFItems.dataCore, 1, DataCore.spaceBridge.index), new Dyes(), "     ", " F3F ", " EOE ", " F3F ", "     ", 'F', new ItemStack(TFItems.dataCore, 1, DataCore.range.index), '3', TFSubItems.advanced_circuit, 'E', TFItems.energonDust, 'O', TFSubItems.singularity);
        AssemblyTable.addRecipe(new ItemStack(TFItems.dataCore, 1, DataCore.leveler.index), new Dyes(), "     ", " F2F ", " EOE ", " F2F ", "     ", 'F', TFBlocks.groundBridgeFrame, '2', TFSubItems.improved_circuit, 'E', TFItems.energonDust, 'O', Items.comparator);
        AssemblyTable.addRecipe(new ItemStack(TFItems.dataCore, 1, DataCore.range.index), new Dyes(), "     ", " F2F ", " EOE ", " F2F ", "     ", 'F', TFBlocks.groundBridgeFrame, '2', TFSubItems.improved_circuit, 'E', TFItems.energonDust, 'O', Items.ender_pearl);
        AssemblyTable.addRecipe(new ItemStack(TFItems.groundBridgeRemote, 1), new Dyes(), " PPP ", " QRQ ", " 3W3 ", " III ", "     ", 'P', TFSubItems.lcd_panel, 'Q', Items.quartz, 'R', Items.redstone, '3', TFSubItems.advanced_circuit, 'W', TFSubItems.refined_alloy_wire, 'I', Items.iron_ingot);
        
        Item[] materials = {Items.gold_ingot, Items.diamond, Items.emerald};

        for (int i = 0; i < materials.length; ++i)
        {
            GameRegistry.addRecipe(new ItemStack(TFItems.powerCanister, 1, i + 1), "**", "##", "**", '*', materials[i], '#', new ItemStack(TFItems.powerCanister, 1, i));
        }

        addMaterialCompression(TFItems.energonCrystalShard, TFBlocks.energonCube);
        addMaterialCompression(TFItems.redEnergonCrystalShard, TFBlocks.redEnergonCube);
        addMaterialCompression(TFItems.transformiumFragment, TFBlocks.transformiumBlock);
        addMaterialCompression(TFItems.transformiumAlloyNugget, TFItems.transformiumAlloy);
        
        for (IRecipe recipe : (List<IRecipe>) CraftingManager.getInstance().getRecipeList())
        {
            if (!prevRecipes.contains(recipe))
            {
                tempRecipes.add(recipe);
            }
        }
        
        for (ItemStack itemstack : (Set<ItemStack>) FurnaceRecipes.smelting().getSmeltingList().keySet())
        {
            if (!prevFurnaceRecipes.contains(itemstack))
            {
                tempFurnaceRecipes.add(itemstack);
            }
        }
    }
    
    public static void restore()
    {
        Map<ItemStack, ItemStack> map = Maps.newHashMap();
        
        for (Map.Entry<ItemStack, ItemStack> e : ((Map<ItemStack, ItemStack>) FurnaceRecipes.smelting().getSmeltingList()).entrySet())
        {
            if (!tempFurnaceRecipes.contains(e.getKey()))
            {
                map.put(e.getKey(), e.getValue());
            }
        }
        
        CraftingManager.getInstance().getRecipeList().removeAll(tempRecipes);
        AssemblyTableCraftingManager.getInstance().getRecipeList().clear();
        FurnaceRecipes.smelting().getSmeltingList().clear();
        FurnaceRecipes.smelting().getSmeltingList().putAll(map);
        
        tempRecipes.clear();
        tempFurnaceRecipes.clear();
    }

    private static void addSmelting()
    {
        GameRegistry.addSmelting(TFBlocks.transformiumOre, new ItemStack(TFItems.transformiumFragment, 1), 1.0F);
        GameRegistry.addSmelting(TFBlocks.energonOre, new ItemStack(TFItems.energonDust, 1), 0.7F);
    }

    private static void addWeaponRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(TFItems.skystrikesCrossbow, 1), "EII", "ITC", "ICT", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformiumFragment, 'C', TFItems.energonCrystalShard);
        GameRegistry.addRecipe(new ItemStack(TFItems.purgesKatana, 1), "CtC", "TET", "CIC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformiumFragment, 'C', TFItems.energonCrystalShard, 't', TFSubItems.tank_track[1]);
        GameRegistry.addRecipe(new ItemStack(TFItems.vurpsSniper, 1), "EI ", "CTG", "CIT", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformiumFragment, 'C', TFItems.energonCrystalShard, 'G', new ItemStack(Blocks.stained_glass, 1, 5));
        GameRegistry.addRecipe(new ItemStack(TFItems.subwoofersBassBlaster, 1), "TIC", " EB", "TIC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformiumFragment, 'C', TFItems.energonCrystalShard, 'B', Blocks.iron_block);
        GameRegistry.addRecipe(new ItemStack(TFItems.cloudtrapsFlamethrower, 1), "CT ", "BET", " IC", 'E', TFBlocks.energonCrystal, 'I', Items.iron_ingot, 'T', TFItems.transformiumFragment, 'C', TFItems.energonCrystalShard, 'B', Blocks.iron_block);
    }

    private static void addCraftingComponentRecipes()
    {
        GameRegistry.addRecipe(TFSubItems.iron_pole[1], "#", "#", '#', Blocks.iron_bars);
        GameRegistry.addRecipe(TFSubItems.iron_support[1], "P P", " P ", "P P", 'P', TFSubItems.iron_pole[1]);
        GameRegistry.addRecipe(TFSubItems.canister_lid[2], " I ", "I#I", 'I', Items.iron_ingot, '#', Blocks.iron_bars);
        GameRegistry.addRecipe(TFSubItems.lcd_panel[3], "G1G", "P#P", "RWR", 'G', Items.glowstone_dust, '1', TFSubItems.basic_circuit[1], 'P', TFSubItems.iron_pole[1], '#', Blocks.glass_pane, 'R', Items.redstone, 'W', TFSubItems.crude_alloy_wire[1]);
        GameRegistry.addRecipe(TFSubItems.crude_alloy_wire[3], " C ", " W ", "C C", 'C', TFItems.crudeFluxAlloy, 'W', Blocks.planks);
        GameRegistry.addRecipe(TFSubItems.refined_alloy_wire[3], " R ", " W ", "R R", 'R', TFItems.refinedFluxAlloy, 'W', Blocks.planks);
        GameRegistry.addRecipe(TFSubItems.energon_coil[3], " W ", "W W", " W ", 'W', TFSubItems.crude_alloy_wire[1]);
        GameRegistry.addRecipe(TFSubItems.transmission_orb[5], "C", "F", "C", 'C', TFSubItems.energon_coil[1], 'F', TFSubItems.focusing_crystal[1]);
        GameRegistry.addRecipe(TFSubItems.energy_converter[1], "RTR", "E2E", "RTR", 'R', Items.redstone, 'T', TFItems.transformiumAlloy, 'E', TFItems.energonDust, '2', TFSubItems.improved_circuit[1]);
        GameRegistry.addRecipe(TFSubItems.superconductor[1], "WPW", "RPR", "WPW", 'W', TFSubItems.refined_alloy_wire[1], 'P', TFSubItems.iron_pole[1], 'R', Items.redstone);
        GameRegistry.addRecipe(TFSubItems.singularity[1], "DED", "SNS", "DED", 'D', Items.diamond, 'E', Items.ender_pearl, 'S', TFSubItems.superconductor[1], 'N', Items.nether_star);
        GameRegistry.addRecipe(TFSubItems.basic_circuit[1], "RRR", "CCR", "RWI", 'R', Items.redstone, 'C', TFItems.crudeFluxAlloy, 'W', TFSubItems.crude_alloy_wire[1], 'I', Items.iron_ingot);
        GameRegistry.addRecipe(TFSubItems.improved_circuit[1], "EEE", "1F1", "EWG", 'E', TFItems.energonAlloy, '1', TFSubItems.basic_circuit[1], 'F', TFSubItems.focusing_crystal[1], 'W', TFSubItems.refined_alloy_wire[1], 'G', Items.gold_ingot);
        GameRegistry.addRecipe(TFSubItems.advanced_circuit[1], "EEE", "2R2", "EWT", 'E', TFItems.energonAlloy, '2', TFSubItems.improved_circuit[1], 'R', TFItems.refinedFluxAlloy, 'W', TFSubItems.refined_alloy_wire[1], 'T', TFItems.transformiumAlloy);
        
        AssemblyTable.addRecipe(TFSubItems.optic_sensor, new Dyes(), "     ", " DT  ", " TT  ", "   R ", "     ", 'D', Items.diamond, 'T', TFItems.transformiumAlloyNugget, 'R', Items.redstone);
        AssemblyTable.addRecipe(TFSubItems.protoform_head, new Dyes(GRAY, 3), "     ", " TTT ", " OTO ", " TRT ", "  T  ", 'T', TFItems.transformiumAlloy, 'O', TFSubItems.optic_sensor, 'R', Items.redstone);
        AssemblyTable.addRecipe(TFSubItems.wheel, new Dyes(BLACK, 3), "     ", " LLL ", " LIL ", " LLL ", "     ", 'L', Items.leather, 'I', Items.iron_ingot);
        GameRegistry.addRecipe(TFSubItems.transformium_alloy_rod[1], "T", "T", "T", 'T', TFItems.transformiumAlloy);
        GameRegistry.addRecipe(TFSubItems.standard_engine[1], "PRP", "BIB", 'P', Blocks.piston, 'R', Items.redstone, 'I', Items.iron_ingot, 'B', Blocks.iron_block);
        GameRegistry.addRecipe(TFSubItems.jet_thruster[1], "IIB", "xRJ", "IIB", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'J', Blocks.iron_bars, 'R', Items.redstone, 'x', Blocks.redstone_block);
        GameRegistry.addRecipe(TFSubItems.small_thruster[1], "III", " JR", "III", 'I', Items.iron_ingot, 'J', Blocks.iron_bars, 'R', Items.redstone);

        AssemblyTable.addRecipe(TFSubItems.skystrikes_wing, new Dyes(GRAY, 5, DARK_GRAY, 1), "     ", " TTT ", " TTT ", "  TT ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.skystrikes_shoulder_pad, new Dyes(GRAY, 3), "     ", " TTT ", "    T", "     ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.skystrikes_gauntlet, new Dyes(GRAY, 2, WHITE, 2, RED, 1), "   T ", "  TT ", "  TT ", "  T  ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.skystrike_torso_base, new Dyes(WHITE, 7, RED, 6, GRAY, 1), "     ", "TTTTT", " TTT ", "  T  ", " TTT ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.skystrikes_greave, new Dyes(GRAY, 3, WHITE, 2, DARK_GRAY, 1), "  T  ", "  TT ", "  TT ", "  TT ", "     ", 'T', TFItems.transformiumAlloy);

        AssemblyTable.addRecipe(TFSubItems.tank_turret_base, new Dyes(BEIGE, 2), "     ", "     ", " IBI ", "     ", "     ", 'I', Items.iron_ingot, 'B', Blocks.iron_block);
        AssemblyTable.addRecipe(TFSubItems.tank_barrel, new Dyes(BEIGE, 1), "     ", "     ", " III ", "     ", "     ", 'I', Items.iron_ingot);
        AssemblyTable.addRecipe(TFSubItems.tank_turret, new Dyes(), "     ", "     ", " BT  ", "     ", "     ", 'B', TFSubItems.tank_barrel, 'T', TFSubItems.tank_turret_base);
        AssemblyTable.addRecipe(TFSubItems.tank_track, new Dyes(GRAY, 5, BEIGE, 2, DARK_RED, 1), "     ", "LLLLL", "LIIIL", " LLL ", "     ", 'L', Items.leather, 'I', Items.iron_ingot);
        AssemblyTable.addRecipe(TFSubItems.purges_pauldron, new Dyes(BEIGE, 3, PURPLE, 1), "     ", " TTT ", "   T ", "   T ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.purges_gauntlet, new Dyes(BEIGE, 2, DARK_RED, 1), "   T ", "  TT ", "  TT ", "  T  ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.purge_torso_base, new Dyes(BEIGE, 9, DARK_RED, 4, DARK_GRAY, 1), "     ", "TTTTT", " TTT ", "  T  ", " TTT ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.purges_greave, new Dyes(BEIGE, 4, DARK_RED, 2), "  T  ", "  TT ", "  TT ", "  TT ", "     ", 'T', TFItems.transformiumAlloy);

        AssemblyTable.addRecipe(TFSubItems.vurps_shoulder_pad, new Dyes(SILVER, 2, PURPLE, 1), "     ", "  TT ", "  TT ", "     ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.vurps_gauntlet, new Dyes(SILVER, 2, GRAY, 1), "   T ", "  TG ", "  TG ", "  T  ", "     ", 'T', TFItems.transformiumAlloy, 'G', Dyes.dye(Blocks.stained_glass_pane, BLACK));
        AssemblyTable.addRecipe(TFSubItems.vurp_torso_base, new Dyes(SILVER, 12, BLACK, 1, PALE_GREEN, 1), "     ", "TTTTT", " TTT ", "  T  ", " TTT ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.vurps_greave, new Dyes(SILVER, 4, DARK_GRAY, 1), "  T  ", "  TTW", "  TT ", "  TT ", "     ", 'T', TFItems.transformiumAlloy, 'W', TFSubItems.wheel);
        AssemblyTable.addRecipe(TFSubItems.vurps_facemask, new Dyes(), "     ", " GGG ", " GGG ", " GGG ", "     ", 'G', Dyes.dye(Blocks.stained_glass_pane, LIME));

        AssemblyTable.addRecipe(TFSubItems.subwoofers_shoulder_pad, new Dyes(BLUE, 2, DARK_GRAY, 1), "     ", "  TT ", "  TT ", "     ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.subwoofers_gauntlet, new Dyes(BLUE, 2, YELLOW, 1, GRAY, 1), "   T ", "  TT ", "  TT ", "  T  ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.subwoofer_torso_base, new Dyes(BLUE, 11, YELLOW, 1, SILVER, 1), "     ", "TTGTT", " WTW ", "  T  ", " TTT ", 'T', TFItems.transformiumAlloy, 'W', TFSubItems.wheel, 'G', Dyes.dye(Blocks.stained_glass, LIGHT_BLUE));
        AssemblyTable.addRecipe(TFSubItems.subwoofers_greave, new Dyes(BLUE, 4, YELLOW, 1), "  T  ", "  TT ", "  TT ", "  TT ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.subwoofers_visor, new Dyes(), "     ", "     ", " GGG ", "     ", "     ", 'G', Dyes.dye(Blocks.stained_glass_pane, LIGHT_BLUE));

        AssemblyTable.addRecipe(TFSubItems.cloudtraps_wing, new Dyes(GRAY, 4, PALE_BROWN, 2), " TTT ", "   TT", "  T T", " T  T", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.cloudtraps_pauldron, new Dyes(GRAY, 2, DARK_GRAY, 1), "     ", " TTT ", "  TT ", "     ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.cloudtraps_gauntlet, new Dyes(GRAY, 2, PALE_BROWN, 1, DARK_GRAY, 1), "   T ", "  TT ", "  TT ", "  T  ", "     ", 'T', TFItems.transformiumAlloy);
        AssemblyTable.addRecipe(TFSubItems.cloudtrap_torso_base, new Dyes(GRAY, 9, PALE_BROWN, 3, PURPLE, 1), "     ", "TTTTT", " TGT ", "  T  ", " TTT ", 'T', TFItems.transformiumAlloy, 'G', Dyes.dye(Blocks.stained_glass, LIME));
        AssemblyTable.addRecipe(TFSubItems.cloudtraps_greave, new Dyes(GRAY, 4, DARK_GRAY, 1), "  T  ", "  TTT", "  TTE", "  TT ", "     ", 'T', TFItems.transformiumAlloy, 'E', TFSubItems.jet_thruster);
        AssemblyTable.addRecipe(TFSubItems.cloudtraps_visor, new Dyes(), "     ", " G G ", " GGG ", "     ", "     ", 'G', Dyes.dye(Blocks.stained_glass_pane, LIME));
    }

    private static void addDisplayRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(TFBlocks.displayPillar, 1), " - ", "SWS", '-', Blocks.stone_slab, 'S', Blocks.stone, 'W', Blocks.cobblestone_wall);
        GameRegistry.addRecipe(new ItemStack(TFBlocks.displayStation, 1), " L ", " I ", "-I-", '-', new ItemStack(Blocks.stone_slab, 1, 0), 'I', Items.iron_ingot, 'L', Blocks.redstone_lamp);
        GameRegistry.addRecipe(new ItemStack(TFItems.componentBase), "III", "ITI", "III", 'I', Items.iron_ingot, 'T', TFItems.transformiumFragment);
        GameRegistry.addRecipe(new ShapedOreRecipe(TFItems.colorComponent, "DDD", "DCD", "DDD", 'D', "dye", 'C', TFItems.componentBase));
        GameRegistry.addRecipe(new ItemStack(TFItems.armorComponent), " I ", "ICI", " I ", 'C', TFItems.componentBase, 'I', Items.iron_chestplate);
        GameRegistry.addRecipe(new RecipeDisplayItems());

        int i = 0;

        for (Transformer transformer : TransformersAPI.getTransformers())
        {
            GameRegistry.addShapelessRecipe(new ItemStack(TFItems.displayVehicle, 1, i), transformer.getHelmet(), transformer.getChestplate(), transformer.getLeggings(), transformer.getBoots());
            ++i;
        }
    }

    private static void addDyeRecipes()
    {
        for (int index = 16; index < Dyes.dyes.size(); ++index)
        {
            int id = Dyes.dyes.get(index);
            GameRegistry.addShapelessRecipe(Dyes.dye(TFBlocks.wool, id), Dyes.get(id, 1), Dyes.dye(Blocks.wool, WHITE));
            GameRegistry.addRecipe(new ItemStack(TFBlocks.stainedHardenedClay, 8, id - 16), "###", "#X#", "###", '#', Blocks.hardened_clay, 'X', Dyes.get(id));
            GameRegistry.addRecipe(new ItemStack(TFBlocks.stainedGlass, 8, id - 16), "###", "#X#", "###", '#', Blocks.glass, 'X', Dyes.get(id));
            GameRegistry.addRecipe(new ItemStack(TFBlocks.stainedGlassPane, 16, id - 16), "###", "###", '#', Dyes.dye(TFBlocks.stainedGlass, id));
        }

        GameRegistry.addShapelessRecipe(Dyes.get(DARK_RED, 2), Dyes.get(RED), Dyes.get(BLACK));
        GameRegistry.addShapelessRecipe(Dyes.get(BEIGE, 2), Dyes.get(BROWN), Dyes.get(WHITE));
        GameRegistry.addShapelessRecipe(Dyes.get(DARK_GRAY, 2), Dyes.get(GRAY), Dyes.get(BLACK));
        GameRegistry.addShapelessRecipe(Dyes.get(DARK_GRAY, 3), Dyes.get(WHITE), Dyes.get(BLACK), Dyes.get(BLACK));
        GameRegistry.addShapelessRecipe(Dyes.get(PALE_GREEN, 2), Dyes.get(LIME), Dyes.get(WHITE));
        GameRegistry.addShapelessRecipe(Dyes.get(PALE_BROWN, 2), Dyes.get(BEIGE), Dyes.get(GRAY));
        GameRegistry.addShapelessRecipe(Dyes.get(PALE_BROWN, 3), Dyes.get(BROWN), Dyes.get(WHITE), Dyes.get(GRAY));

        for (int index = 16; index < Dyes.dyes.size(); ++index)
        {
            int id = Dyes.dyes.get(index);
            GameRegistry.addRecipe(new ItemStack(TFBlocks.carpet, 3, id - 16), "###", '#', Dyes.dye(TFBlocks.wool, id));
        }
    }

    private static void addProjectileRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(TFItems.missile, 4), "T  ", " I ", "  E", 'I', Items.iron_ingot, 'T', Blocks.tnt, 'E', TFSubItems.small_thruster[1]);
        GameRegistry.addRecipe(new ItemStack(TFItems.tankShell, 4), "IIT", 'I', Items.iron_ingot, 'T', Items.gunpowder);
    }

    private static void addArmorRecipes()
    {
        AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeHelmet), new Dyes(GRAY, 3, WHITE, 2, RED, 1), "     ", " TTT ", " THT ", "  T  ", "     ", 'T', TFItems.transformiumAlloy, 'H', TFSubItems.protoform_head);
        AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeChestplate), new Dyes(), "     ", " W W ", " SBS ", " G G ", "     ", 'W', TFSubItems.skystrikes_wing, 'S', TFSubItems.skystrikes_shoulder_pad, 'B', TFSubItems.skystrike_torso_base, 'G', TFSubItems.skystrikes_gauntlet);
        AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeLeggings), new Dyes(WHITE, 2, GRAY, 2), "     ", "     ", " R R ", " G G ", "     ", 'R', TFSubItems.transformium_alloy_rod, 'G', TFSubItems.skystrikes_greave);
        AssemblyTable.addRecipe(new ItemStack(TFItems.skystrikeBoots), new Dyes(GRAY, 2, WHITE, 2, RED, 2), "     ", "     ", "     ", " TTE ", "     ", 'T', new ItemStack(TFItems.transformiumAlloy, 2), 'E', TFSubItems.jet_thruster[2]);

        AssemblyTable.addRecipe(new ItemStack(TFItems.purgeHelmet), new Dyes(DARK_GRAY, 4), " GNG ", " TGT ", " THT ", " T T ", "     ", 'G', Items.gold_ingot, 'N', Items.gold_nugget, 'T', TFItems.transformiumAlloy, 'H', TFSubItems.protoform_head);
        AssemblyTable.addRecipe(new ItemStack(TFItems.purgeChestplate), new Dyes(), "     ", " WOW ", " PBP ", " G G ", "     ", 'W', TFSubItems.tank_track, 'O', TFSubItems.tank_turret, 'P', TFSubItems.purges_pauldron, 'B', TFSubItems.purge_torso_base, 'G', TFSubItems.purges_gauntlet);
        AssemblyTable.addRecipe(new ItemStack(TFItems.purgeLeggings), new Dyes(DARK_GRAY, 4), "     ", "     ", " R R ", " G G ", "     ", 'R', TFSubItems.transformium_alloy_rod, 'G', TFSubItems.purges_greave);
        AssemblyTable.addRecipe(new ItemStack(TFItems.purgeBoots), new Dyes(BLACK, 4, SILVER, 3), "     ", "     ", "     ", " TTT ", "     ", 'T', new ItemStack(TFItems.transformiumAlloy, 2));

        AssemblyTable.addRecipe(new ItemStack(TFItems.vurpHelmet), new Dyes(SILVER, 4, BLACK, 1), "     ", " TTT ", " THT ", "  F  ", "     ", 'T', TFItems.transformiumAlloy, 'H', TFSubItems.protoform_head, 'F', TFSubItems.vurps_facemask);
        AssemblyTable.addRecipe(new ItemStack(TFItems.vurpChestplate), new Dyes(), "     ", " W W ", " SBS ", " G G ", "     ", 'W', TFSubItems.wheel, 'S', TFSubItems.vurps_shoulder_pad, 'B', TFSubItems.vurp_torso_base, 'G', TFSubItems.vurps_gauntlet);
        AssemblyTable.addRecipe(new ItemStack(TFItems.vurpLeggings), new Dyes(PALE_GREEN, 4), "     ", "     ", " R R ", " G G ", "     ", 'R', TFSubItems.transformium_alloy_rod, 'G', TFSubItems.vurps_greave);
        AssemblyTable.addRecipe(new ItemStack(TFItems.vurpBoots), new Dyes(BLACK, 5), "     ", "     ", "     ", " TTT ", "     ", 'T', new ItemStack(TFItems.transformiumAlloy, 2));

        AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferHelmet), new Dyes(BLUE, 4, YELLOW, 1, SILVER, 1), "     ", " TTT ", " THT ", "  V  ", "     ", 'T', TFItems.transformiumAlloy, 'H', TFSubItems.protoform_head, 'V', TFSubItems.subwoofers_visor);
        AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferChestplate), new Dyes(), "     ", "     ", " SBS ", " G G ", "     ", 'S', TFSubItems.subwoofers_shoulder_pad, 'B', TFSubItems.subwoofer_torso_base, 'G', TFSubItems.subwoofers_gauntlet);
        AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferLeggings), new Dyes(SILVER, 4), "     ", "     ", " R R ", " G G ", "     ", 'R', TFSubItems.transformium_alloy_rod, 'G', TFSubItems.subwoofers_greave);
        AssemblyTable.addRecipe(new ItemStack(TFItems.subwooferBoots), new Dyes(SILVER, 5), "     ", "     ", "     ", " TTW ", "     ", 'T', new ItemStack(TFItems.transformiumAlloy, 2), 'W', TFSubItems.wheel[2]);

        AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapHelmet), new Dyes(BLACK, 3, DARK_GRAY, 2, GRAY, 1), "     ", " TTT ", " THT ", " BVB ", "     ", 'T', TFItems.transformiumAlloy, 'H', TFSubItems.protoform_head, 'V', TFSubItems.cloudtraps_visor, 'B', Blocks.iron_block);
        AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapChestplate), new Dyes(), "     ", " W W ", " PBP ", " G G ", "     ", 'W', TFSubItems.cloudtraps_wing, 'P', TFSubItems.cloudtraps_pauldron, 'B', TFSubItems.cloudtrap_torso_base, 'G', TFSubItems.cloudtraps_gauntlet);
        AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapLeggings), new Dyes(GRAY, 3, PALE_BROWN, 1), "     ", "     ", " R R ", " G G ", "     ", 'R', TFSubItems.transformium_alloy_rod, 'G', TFSubItems.cloudtraps_greave);
        AssemblyTable.addRecipe(new ItemStack(TFItems.cloudtrapBoots), new Dyes(GRAY, 3, PALE_BROWN, 2), "     ", "     ", "     ", " TTT ", "     ", 'T', new ItemStack(TFItems.transformiumAlloy, 2));
    }

    private static void addMaterialCompression(Object obj1, Object obj2)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(obj1 instanceof Item ? (Item) obj1 : Item.getItemFromBlock((Block) obj1), 9), obj2);
        GameRegistry.addRecipe(new ItemStack(obj2 instanceof Item ? (Item) obj2 : Item.getItemFromBlock((Block) obj2), 1), "###", "###", "###", '#', obj1);
    }
}
