package fiskfille.tf.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import fiskfille.tf.common.component.ComponentArmor;
import fiskfille.tf.common.component.ComponentColor;
import fiskfille.tf.common.energon.DefaultEnergon;
import fiskfille.tf.common.energon.RedEnergon;
import fiskfille.tf.common.item.armor.ItemCloudtrapArmor;
import fiskfille.tf.common.item.armor.ItemPurgeArmor;
import fiskfille.tf.common.item.armor.ItemSkystrikeArmor;
import fiskfille.tf.common.item.armor.ItemSubwooferArmor;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.item.armor.ItemVurpArmor;
import fiskfille.tf.common.item.armor.ItemWardenArmor;
import fiskfille.tf.common.registry.TFItemRegistry;

public class TFItems
{
    public static ArmorMaterial TRANSFORMERMATERIAL = EnumHelper.addArmorMaterial("Transformer", 1250 / 16, new int[]{3, 9, 6, 3}, 2);
    public static ArmorMaterial TANKMATERIAL = EnumHelper.addArmorMaterial("Transformer", 1550 / 16, new int[]{4, 9, 7, 3}, 2);
    public static ArmorMaterial SUBWOOFERMATERIAL = EnumHelper.addArmorMaterial("Transformer", 1250 / 16, new int[]{3, 9, 7, 3}, 2);

    public static Item transformium;
    public static Item transformiumAlloy;
    public static ItemMetaBasic craftingMaterial;
    public static Item fuelCanister;
    public static Item dye;
    public static Item componentBase;
    public static Item colorComponent;
    public static Item armorComponent;
    public static Item dataCore;
    public static Item csd;
    public static Item groundBridgeRemote;

    public static Item skystrikesCrossbow;
    public static Item purgesKatana;
    public static Item vurpsSniper;
    public static Item cloudtrapsFlamethrower;
    public static Item subwoofersBassBlaster;

    public static ItemTransformerArmor skystrikeHelmet;
    public static ItemTransformerArmor skystrikeChestplate;
    public static ItemTransformerArmor skystrikeLeggings;
    public static ItemTransformerArmor skystrikeBoots;
    public static ItemTransformerArmor purgeHelmet;
    public static ItemTransformerArmor purgeChestplate;
    public static ItemTransformerArmor purgeLeggings;
    public static ItemTransformerArmor purgeBoots;
    public static ItemTransformerArmor vurpHelmet;
    public static ItemTransformerArmor vurpChestplate;
    public static ItemTransformerArmor vurpLeggings;
    public static ItemTransformerArmor vurpBoots;
    public static ItemTransformerArmor subwooferHelmet;
    public static ItemTransformerArmor subwooferChestplate;
    public static ItemTransformerArmor subwooferLeggings;
    public static ItemTransformerArmor subwooferBoots;
    public static ItemTransformerArmor cloudtrapHelmet;
    public static ItemTransformerArmor cloudtrapChestplate;
    public static ItemTransformerArmor cloudtrapLeggings;
    public static ItemTransformerArmor cloudtrapBoots;
    public static ItemTransformerArmor wardenHelmet;
    public static ItemTransformerArmor wardenChestplate;
    public static ItemTransformerArmor wardenLeggings;
    public static ItemTransformerArmor wardenBoots;

    public static Item displayVehicle;
    public static Item energonCrystalPiece;
    public static Item redEnergonCrystalPiece;

    public static Item tankShell;
    public static Item missile;
    
    public static Item transformiumDetector;

    public static void register()
    {
        transformium = new Item();
        transformiumAlloy = new Item();
        craftingMaterial = new ItemMetaBasic();
        fuelCanister = new ItemFuelCanister();
        dye = new ItemMetaBasic("Dark Red Dye", "Beige Dye", "Dark Gray Dye");
        componentBase = new Item();
        colorComponent = new ItemComponent(new ComponentColor());
        armorComponent = new ItemComponent(new ComponentArmor());
        dataCore = new ItemDataCore();
        csd = new ItemCSD();
        groundBridgeRemote = new ItemGroundBridgeRemote();

        skystrikesCrossbow = new ItemSkystrikesCrossbow();
        purgesKatana = new ItemPurgesKatana();
        vurpsSniper = new ItemVurpsSniper();
        cloudtrapsFlamethrower = new ItemFlamethrower();
        subwoofersBassBlaster = new ItemBassBlaster();

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
        wardenHelmet = new ItemWardenArmor(0);
        wardenChestplate = new ItemWardenArmor(1);
        wardenLeggings = new ItemWardenArmor(2);
        wardenBoots = new ItemWardenArmor(3);

        transformiumDetector = new ItemTransformiumDetector();

        displayVehicle = new ItemDisplayVehicle();
        energonCrystalPiece = new ItemEnergon(new DefaultEnergon());
        redEnergonCrystalPiece = new ItemEnergon(new RedEnergon());

        tankShell = new Item();
        missile = new Item().setFull3D();

        TRANSFORMERMATERIAL.customCraftingMaterial = transformium;
        TANKMATERIAL.customCraftingMaterial = transformium;
        SUBWOOFERMATERIAL.customCraftingMaterial = transformium;

        TFItemRegistry.registerItem(transformium, "Transformium");
        TFItemRegistry.registerItem(transformiumAlloy, "Transformium Alloy");
        TFItemRegistry.registerItem(craftingMaterial, "Crafting Material");
        TFItemRegistry.registerItem(transformiumDetector, "Transformium Detector");
        TFItemRegistry.registerItem(fuelCanister, "Fuel Canister");
        TFItemRegistry.registerIngot(dye, "Dye", "dye");
        TFItemRegistry.registerItem(componentBase, "Component Base");
        TFItemRegistry.registerItem(colorComponent, "Color Component");
        TFItemRegistry.registerItem(armorComponent, "Armor Component");
        TFItemRegistry.registerItem(dataCore, "Data Core");
        TFItemRegistry.registerItem(csd, "CSD");
        TFItemRegistry.registerItem(groundBridgeRemote, "Ground Bridge Remote");

        TFItemRegistry.registerItem(skystrikesCrossbow, "Skystrike's Energon Crossbow");
        TFItemRegistry.registerItem(purgesKatana, "Purge's Katana");
        TFItemRegistry.registerItem(vurpsSniper, "Vurp's Sniper");
        TFItemRegistry.registerItem(subwoofersBassBlaster, "Subwoofer's Bass Blaster");
        TFItemRegistry.registerItem(cloudtrapsFlamethrower, "Flame Thrower");

        TFItemRegistry.registerItem(skystrikeHelmet, "Skystrike Head");
        TFItemRegistry.registerItem(skystrikeChestplate, "Skystrike Torso");
        TFItemRegistry.registerItem(skystrikeLeggings, "Skystrike Legs");
        TFItemRegistry.registerItem(skystrikeBoots, "Skystrike Feet");
        TFItemRegistry.registerItem(purgeHelmet, "Purge Head");
        TFItemRegistry.registerItem(purgeChestplate, "Purge Torso");
        TFItemRegistry.registerItem(purgeLeggings, "Purge Legs");
        TFItemRegistry.registerItem(purgeBoots, "Purge Feet");
        TFItemRegistry.registerItem(vurpHelmet, "Vurp Head");
        TFItemRegistry.registerItem(vurpChestplate, "Vurp Torso");
        TFItemRegistry.registerItem(vurpLeggings, "Vurp Legs");
        TFItemRegistry.registerItem(vurpBoots, "Vurp Feet");
        TFItemRegistry.registerItem(subwooferHelmet, "Subwoofer Head");
        TFItemRegistry.registerItem(subwooferChestplate, "Subwoofer Torso");
        TFItemRegistry.registerItem(subwooferLeggings, "Subwoofer Legs");
        TFItemRegistry.registerItem(subwooferBoots, "Subwoofer Feet");
        TFItemRegistry.registerItem(cloudtrapHelmet, "Cloudtrap Head");
        TFItemRegistry.registerItem(cloudtrapChestplate, "Cloudtrap Torso");
        TFItemRegistry.registerItem(cloudtrapLeggings, "Cloudtrap Legs");
        TFItemRegistry.registerItem(cloudtrapBoots, "Cloudtrap Feet");
        TFItemRegistry.registerItem(wardenHelmet, "Warden Head");
        TFItemRegistry.registerItem(wardenChestplate, "Warden Torso");
        TFItemRegistry.registerItem(wardenLeggings, "Warden Legs");
        TFItemRegistry.registerItem(wardenBoots, "Warden Feet");

        TFItemRegistry.registerItem(displayVehicle, "Display Vehicle");
        TFItemRegistry.registerItem(energonCrystalPiece, "Energon Crystal Piece");
        TFItemRegistry.registerItem(redEnergonCrystalPiece, "Red Energon Crystal Piece");
        TFItemRegistry.registerItem(tankShell, "Tank Shell");
        TFItemRegistry.registerItem(missile, "Missile");

        dye.setCreativeTab(CreativeTabs.tabMaterials);
        TFSubItems.register();
    }
}
