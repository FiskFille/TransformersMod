package fiskfille.tf.common.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformerManager;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class ItemPurgeArmor extends ItemTransformerArmor
{
	public ItemPurgeArmor(int armorPiece)
	{
		super(TFItems.TANKMATERIAL, 4, armorPiece);
		this.setCreativeTab(TransformersMod.tabTransformers);
	}

	@Override
	public Transformer getTransformer() 
	{
		return TransformerManager.transformerPurge;
	}
}