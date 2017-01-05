package fiskfille.tf.main;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import fiskfille.tf.item.ItemBasic;
import fiskfille.tf.item.ItemMetaBasic;
import fiskfille.tf.item.ItemMiniVehicle;
import fiskfille.tf.item.ItemPurgeArmor;
import fiskfille.tf.item.ItemPurgesKatana;
import fiskfille.tf.item.ItemSkystrikeArmor;
import fiskfille.tf.item.ItemSkystrikesCrossbow;
import fiskfille.tf.item.ItemSubwooferArmor;
import fiskfille.tf.item.ItemVurpArmor;
import fiskfille.tf.main.util.TFItemRegistry;

public class TFItems
{
	public static Item transformium;
	public static Item transformiumArmorMolds;
	public static Item standardEngine;
	public static Item jetThruster;
	public static Item f88JetWing;
	public static Item f88JetCockpit;
	public static Item tankTracks;
	public static Item tankTurret;
	public static Item carWheel;
	
	public static Item skystrikesCrossbow;
	public static Item purgesKatana;
	public static Item vurpsRocketLauncher;
	
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
	
	public static Item displayVehicle;
	public static Item energonCrystalPiece;
	
	
	public void load(TFConfig ids)
	{
		String modId = MainClass.modid;
		transformium = new ItemBasic();
		transformiumArmorMolds = new ItemMetaBasic("Transformium Head Mold", "Transformium Torso Mold", "Transformium Legs Mold", "Transformium Feet Mold");
		standardEngine = new ItemBasic();
		jetThruster = new ItemBasic();
		f88JetWing = new ItemBasic();
		f88JetCockpit = new ItemBasic();
		tankTracks = new ItemBasic();
		tankTurret = new ItemBasic();
		carWheel = new ItemBasic();
		
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
		skystrikesCrossbow = new ItemSkystrikesCrossbow(ToolMaterial.WOOD);
		purgesKatana = new ItemPurgesKatana(ToolMaterial.EMERALD);
//		vurpsRocketLauncher = new ItemVurpsRocketLauncher();
		
		displayVehicle = new ItemMiniVehicle("F-88 Fighter Jet", "Tank", "2015 Chevrolet Corvette Z06", "2015 Acura NSX", "T-50 Stealth Jet");
		energonCrystalPiece = new ItemBasic();
		
		
		
		TFItemRegistry.registerItem(transformium, "Transformium", modId);
		TFItemRegistry.registerItem(transformiumArmorMolds, "Transformium Armor Molds", modId);
		TFItemRegistry.registerItem(standardEngine, "Standard Engine", modId);
		TFItemRegistry.registerItem(jetThruster, "Jet Thruster", modId);
		TFItemRegistry.registerItem(f88JetWing, "F-88 Jet Wing", modId);
		TFItemRegistry.registerItem(f88JetCockpit, "F-88 Jet Cockpit", modId);
		TFItemRegistry.registerItem(tankTracks, "Tracks", modId);
		TFItemRegistry.registerItem(tankTurret, "Turret", modId);
		TFItemRegistry.registerItem(carWheel, "Wheel", modId);
		
		TFItemRegistry.registerItem(skystrikesCrossbow, "Skystrike's Energon Crossbow", modId);
		TFItemRegistry.registerItem(purgesKatana, "Purge's Katana", modId);
		
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
		
		TFItemRegistry.registerItem(displayVehicle, "Display Vehicle", modId);
		TFItemRegistry.registerItem(energonCrystalPiece, "Energon Crystal Piece", modId);
	}
}