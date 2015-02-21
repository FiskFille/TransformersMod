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
	
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot) 
	{
		ModelBiped armorModel = null;

		if (itemstack != null)
		{
			armorModel = getTransformer().getModel();

			if (armorModel != null)
			{
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
				
				if (entityLiving instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer)entityLiving;
					ItemStack itemstack1 = player.getHeldItem();
					armorModel.aimedBow = player.getItemInUseDuration() > 0 && itemstack1 != null && itemstack1.getItemUseAction() == EnumAction.bow;
					armorModel.heldItemRight = player.getItemInUseDuration() > 0 && itemstack1 != null && itemstack1.getItemUseAction() == EnumAction.block ? 3 : (entityLiving.getEquipmentInSlot(0) != null ? 1 : 0);
					
					armorModel.bipedHead.isHidden = !TFHelper.isPlayerPurge(player);
					armorModel.bipedHeadwear.isHidden = !TFHelper.isPlayerPurge(player);
					armorModel.bipedBody.isHidden = !TFHelper.isPlayerPurge(player);
					armorModel.bipedRightArm.isHidden = !TFHelper.isPlayerPurge(player);
					armorModel.bipedLeftArm.isHidden = !TFHelper.isPlayerPurge(player);
					armorModel.bipedRightLeg.isHidden = !TFHelper.isPlayerPurge(player);
					armorModel.bipedLeftLeg.isHidden = !TFHelper.isPlayerPurge(player);
				}

				return armorModel;
			}
		}

		return null;
	}
}