package fiskfille.tf.client.displayable;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;
import net.minecraft.item.Item;

public class TFDisplayableManager
{
    public static void registerDisplayables()
    {
        TransformersAPI.registerDisplayable(TFItems.displayVehicle, DisplayableVehicle.class);
        TransformersAPI.registerDisplayable(TFItems.skystrikesCrossbow, DisplayableSkystrikesCrossbow.class);
        TransformersAPI.registerDisplayable(TFItems.purgesKatana, DisplayablePurgesKatana.class);
        TransformersAPI.registerDisplayable(TFItems.vurpsSniper, DisplayableVurpsSniper.class);
        TransformersAPI.registerDisplayable(TFItems.subwoofersBassBlaster, DisplayableBassBlaster.class);
        TransformersAPI.registerDisplayable(TFItems.cloudtrapsFlamethrower, DisplayableFlamethrower.class);
        TransformersAPI.registerDisplayable(Item.getItemFromBlock(TFBlocks.energonCrystal), DisplayableEnergonCrystal.class);
        TransformersAPI.registerDisplayable(Item.getItemFromBlock(TFBlocks.redEnergonCrystal), DisplayableEnergonCrystal.class);
        TransformersAPI.registerDisplayable(Item.getItemFromBlock(TFBlocks.transformiumSeed), DisplayableTransformiumSeed.class);
    }
}
