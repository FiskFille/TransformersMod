package fiskfille.tf.common.item;

import fiskfille.tf.TransformersMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class TFItems
{
    public static final List<Item> REGISTERED_ITEMS = new ArrayList<>();

    public static final Item TRANSFORMIUM_FRAGMENT = new ItemBasic();

    public static void register()
    {
        TFItems.register(TRANSFORMIUM_FRAGMENT, new ResourceLocation(TransformersMod.MODID, "transformium_fragment"));
    }

    private static void register(Item item, ResourceLocation identifier)
    {
        item.setUnlocalizedName(identifier.getResourcePath());

        GameRegistry.register(item, identifier);

        REGISTERED_ITEMS.add(item);
    }
}
