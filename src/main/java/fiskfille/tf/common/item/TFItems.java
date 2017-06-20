package fiskfille.tf.common.item;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.armor.ItemSkystrikeArmor;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class TFItems
{
    public static final List<Item> REGISTERED_ITEMS = new ArrayList<>();

    public static ItemArmor.ArmorMaterial TRANSFORMERMATERIAL = EnumHelper.addArmorMaterial("Transformer", "", 1250 / 16, new int[] { 3, 9, 6, 3 }, 2, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static final Item TRANSFORMIUM_FRAGMENT = new ItemBasic();

    public static final ItemTransformerArmor SKYSTRIKE_HELMET = new ItemSkystrikeArmor(EntityEquipmentSlot.HEAD);
    public static final ItemTransformerArmor SKYSTRIKE_CHEST = new ItemSkystrikeArmor(EntityEquipmentSlot.CHEST);
    public static final ItemTransformerArmor SKYSTRIKE_LEGGINGS = new ItemSkystrikeArmor(EntityEquipmentSlot.LEGS);
    public static final ItemTransformerArmor SKYSTRIKE_BOOTS = new ItemSkystrikeArmor(EntityEquipmentSlot.FEET);

    public static void register()
    {
        TFItems.register(TRANSFORMIUM_FRAGMENT, new ResourceLocation(TransformersMod.MODID, "transformium_fragment"));

        TFItems.register(SKYSTRIKE_HELMET, new ResourceLocation(TransformersMod.MODID, "skystrike_helmet"));
        TFItems.register(SKYSTRIKE_CHEST, new ResourceLocation(TransformersMod.MODID, "skystrike_chest"));
        TFItems.register(SKYSTRIKE_LEGGINGS, new ResourceLocation(TransformersMod.MODID, "skystrike_leggings"));
        TFItems.register(SKYSTRIKE_BOOTS, new ResourceLocation(TransformersMod.MODID, "skystrike_boots"));
    }

    private static void register(Item item, ResourceLocation identifier)
    {
        item.setUnlocalizedName(identifier.getResourcePath());

        GameRegistry.register(item, identifier);

        REGISTERED_ITEMS.add(item);
    }
}
