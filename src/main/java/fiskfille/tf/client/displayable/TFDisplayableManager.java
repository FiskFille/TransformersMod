package fiskfille.tf.client.displayable;

import net.minecraft.item.Item;
import fiskfille.tf.TransformersAPI;
import fiskfille.tf.common.block.TFBlocks;
import fiskfille.tf.common.item.TFItems;

public class TFDisplayableManager
{   
	public static void registerDisplayables()
	{
		TransformersAPI.registerDisplayable(TFItems.displayVehicle, new DisplayableVehicle());
		TransformersAPI.registerDisplayable(TFItems.skystrikesCrossbow, new DisplayableSkystrikesCrossbow());
		TransformersAPI.registerDisplayable(TFItems.purgesKatana, new DisplayablePurgesKatana());
		TransformersAPI.registerDisplayable(TFItems.vurpsSniper, new DisplayableVurpsSniper());
		TransformersAPI.registerDisplayable(TFItems.subwoofersBassBlaster, new DisplayableBassBlaster());
		TransformersAPI.registerDisplayable(TFItems.cloudtrapsFlamethrower, new DisplayableFlamethrower());
//		TransformersAPI.registerDisplayable(Item.getItemFromBlock(TFBlocks.energonCrystal), new DisplayableEnergonCrystal());
//		TransformersAPI.registerDisplayable(Item.getItemFromBlock(TFBlocks.transformiumSeed), new DisplayableTransformiumSeed());
	}
}
