package fiskfille.tf.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.armor.ItemCloudtrapArmor;
import fiskfille.tf.common.item.armor.ItemPurgeArmor;
import fiskfille.tf.common.item.armor.ItemSkystrikeArmor;
import fiskfille.tf.common.item.armor.ItemSubwooferArmor;
import fiskfille.tf.common.item.armor.ItemVurpArmor;
import fiskfille.tf.common.registry.TFItemRegistry;

public class TFItems
{
    public static ArmorMaterial TRANSFORMERMATERIAL = EnumHelper.addArmorMaterial("Transformer", 1250 / 16, new int[] { 3, 9, 6, 3 }, 2);
    public static ArmorMaterial TANKMATERIAL = EnumHelper.addArmorMaterial("Transformer", 1550 / 16, new int[] { 4, 9, 7, 3 }, 2);
    public static ArmorMaterial SUBWOOFERMATERIAL = EnumHelper.addArmorMaterial("Transformer", 1250 / 16, new int[] { 3, 9, 7, 3 }, 2);
    
    public static Item transformium;
    public static Item transformiumArmorMolds;
    public static Item standardEngine;
    public static Item jetTurbine;
    public static Item jetThruster;
    public static Item ahd2JetWing;
    public static Item ahd2JetCockpit;
    public static Item t50JetWing;
    public static Item t50JetCockpit;
    public static Item tankTracks;
    public static Item tankTurret;
    public static Item carWheel;
    
    public static Item skystrikesCrossbow;
    public static Item purgesKatana;
    public static Item vurpsSniper;
    public static Item cloudtrapsFlamethrower;
    public static Item subwoofersBassBlaster;
    
    public static Item skystrikeHelmet;
    public static Item skystrikeChestplate;
    public static Item skystrikeLeggings;
    public static Item skystrikeBoots;
    public static Item purgeHelmet;
    public static Item purgeChestplate;
    public static Item purgeLeggings;
    public static Item purgeBoots;
    public static Item vurpHelmet;
    public static Item vurpChestplate;
    public static Item vurpLeggings;
    public static Item vurpBoots;
    public static Item subwooferHelmet;
    public static Item subwooferChestplate;
    public static Item subwooferLeggings;
    public static Item subwooferBoots;
    public static Item cloudtrapHelmet;
    public static Item cloudtrapChestplate;
    public static Item cloudtrapLeggings;
    public static Item cloudtrapBoots;
    
    public static Item displayVehicle;
    public static Item energonCrystalPiece;
    
    public static Item tankShell;
    public static Item miniMissile;
    public static Item missile;
    public static Item smallThruster;
    
    public static Item transformiumDetector;
    
    public void register()
    {
        String modId = TransformersMod.modid;
        
        transformium = new Item();
        transformiumArmorMolds = new ItemMetaBasic("Transformium Head Mold", "Transformium Torso Mold", "Transformium Legs Mold", "Transformium Feet Mold");
        standardEngine = new Item();
        jetTurbine = new Item();
        ahd2JetWing = new Item();
        ahd2JetCockpit = new Item();
        tankTracks = new Item();
        tankTurret = new Item();
        carWheel = new Item();
        t50JetCockpit = new Item().setCreativeTab(null);
        t50JetWing = new Item().setCreativeTab(null);
        jetThruster = new Item().setCreativeTab(null);
        smallThruster = new Item();
        
        skystrikesCrossbow = new ItemSkystrikesCrossbow(ToolMaterial.WOOD);
        purgesKatana = new ItemPurgesKatana(ToolMaterial.EMERALD);
        vurpsSniper = new ItemVurpsSniper(ToolMaterial.WOOD);
        cloudtrapsFlamethrower = new ItemFlamethrower(ToolMaterial.WOOD);
        subwoofersBassBlaster = new ItemBassBlaster(ToolMaterial.WOOD);
        
        skystrikeHelmet = new ItemSkystrikeArmor(0);
        skystrikeChestplate = new ItemSkystrikeArmor(1);
        skystrikeLeggings = new ItemSkystrikeArmor(2);
        skystrikeBoots = new ItemSkystrikeArmor(3);
        purgeHelmet = new ItemPurgeArmor(0);
        purgeChestplate = new ItemPurgeArmor(1);
        purgeLeggings = new ItemPurgeArmor(2);
        purgeBoots = new ItemPurgeArmor(3);
        vurpHelmet = new ItemVurpArmor(0);
        vurpChestplate = new ItemVurpArmor(1);
        vurpLeggings = new ItemVurpArmor(2);
        vurpBoots = new ItemVurpArmor(3);
        subwooferHelmet = new ItemSubwooferArmor(0);
        subwooferChestplate = new ItemSubwooferArmor(1);
        subwooferLeggings = new ItemSubwooferArmor(2);
        subwooferBoots = new ItemSubwooferArmor(3);
        cloudtrapHelmet = new ItemCloudtrapArmor(0);
        cloudtrapChestplate = new ItemCloudtrapArmor(1);
        cloudtrapLeggings = new ItemCloudtrapArmor(2);
        cloudtrapBoots = new ItemCloudtrapArmor(3);
        
        transformiumDetector = new ItemTransformiumDetector();
        
        displayVehicle = new ItemMiniVehicle();
        energonCrystalPiece = new Item();
        
        tankShell = new Item();
        missile = new Item().setFull3D();
        miniMissile = new Item().setFull3D();
        smallThruster = new Item();
        
        TRANSFORMERMATERIAL.customCraftingMaterial = transformium;
        TANKMATERIAL.customCraftingMaterial = transformium;
        SUBWOOFERMATERIAL.customCraftingMaterial = transformium;
        
        TFItemRegistry.registerItem(transformium, "Transformium", modId);
        TFItemRegistry.registerItem(transformiumArmorMolds, "Transformium Armor Molds", modId);
        TFItemRegistry.registerItem(standardEngine, "Standard Engine", modId);
        TFItemRegistry.registerItem(jetTurbine, "Jet Turbine", modId);
        TFItemRegistry.registerItem(ahd2JetWing, "AHD-2 Jet Wing", modId);
        TFItemRegistry.registerItem(ahd2JetCockpit, "AHD-2 Jet Cockpit", modId);
        TFItemRegistry.registerItem(t50JetWing, "T-50 Jet Wing", modId);
        TFItemRegistry.registerItem(t50JetCockpit, "T-50 Jet Cockpit", modId);
        TFItemRegistry.registerItem(tankTracks, "Tracks", modId);
        TFItemRegistry.registerItem(tankTurret, "Turret", modId);
        TFItemRegistry.registerItem(carWheel, "Wheel", modId);
        TFItemRegistry.registerItem(jetThruster, "Jet Thruster", modId);
        TFItemRegistry.registerItem(smallThruster, "Small Thruster", modId);
        TFItemRegistry.registerItem(transformiumDetector, "Transformium Detector", modId);
        
        TFItemRegistry.registerItem(skystrikesCrossbow, "Skystrike's Energon Crossbow", modId);
        TFItemRegistry.registerItem(purgesKatana, "Purge's Katana", modId);
        TFItemRegistry.registerItem(vurpsSniper, "Vurp's Sniper", modId);
        TFItemRegistry.registerItem(subwoofersBassBlaster, "Subwoofer's Bass Blaster", modId);
        TFItemRegistry.registerItem(cloudtrapsFlamethrower, "Flame Thrower", modId);
        
        TFItemRegistry.registerItem(skystrikeHelmet, "Skystrike Head", modId);
        TFItemRegistry.registerItem(skystrikeChestplate, "Skystrike Torso", modId);
        TFItemRegistry.registerItem(skystrikeLeggings, "Skystrike Legs", modId);
        TFItemRegistry.registerItem(skystrikeBoots, "Skystrike Feet", modId);
        TFItemRegistry.registerItem(purgeHelmet, "Purge Head", modId);
        TFItemRegistry.registerItem(purgeChestplate, "Purge Torso", modId);
        TFItemRegistry.registerItem(purgeLeggings, "Purge Legs", modId);
        TFItemRegistry.registerItem(purgeBoots, "Purge Feet", modId);
        TFItemRegistry.registerItem(vurpHelmet, "Vurp Head", modId);
        TFItemRegistry.registerItem(vurpChestplate, "Vurp Torso", modId);
        TFItemRegistry.registerItem(vurpLeggings, "Vurp Legs", modId);
        TFItemRegistry.registerItem(vurpBoots, "Vurp Feet", modId);
        TFItemRegistry.registerItem(subwooferHelmet, "Subwoofer Head", modId);
        TFItemRegistry.registerItem(subwooferChestplate, "Subwoofer Torso", modId);
        TFItemRegistry.registerItem(subwooferLeggings, "Subwoofer Legs", modId);
        TFItemRegistry.registerItem(subwooferBoots, "Subwoofer Feet", modId);
        TFItemRegistry.registerItem(cloudtrapHelmet, "Cloudtrap Head", modId);
        TFItemRegistry.registerItem(cloudtrapChestplate, "Cloudtrap Torso", modId);
        TFItemRegistry.registerItem(cloudtrapLeggings, "Cloudtrap Legs", modId);
        TFItemRegistry.registerItem(cloudtrapBoots, "Cloudtrap Feet", modId);
        
        TFItemRegistry.registerItem(displayVehicle, "Display Vehicle", modId);
        TFItemRegistry.registerItem(energonCrystalPiece, "Energon Crystal Piece", modId);
        TFItemRegistry.registerItem(tankShell, "Tank Shell", modId);
        TFItemRegistry.registerItem(missile, "Missile", modId);
        TFItemRegistry.registerItem(miniMissile, "Mini Missile", modId);
    }
}