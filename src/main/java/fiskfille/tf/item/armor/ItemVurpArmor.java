package fiskfille.tf.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.achievement.TFAchievements;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.transformer.base.Transformer;

public class ItemVurpArmor extends ItemArmor implements ITransformerArmor
{
	public ItemVurpArmor(int armorPiece)
	{
		super(TFItems.TRANSFORMERMATERIAL, 4, armorPiece);
		this.setCreativeTab(TransformersMod.transformersTab);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return TransformersMod.modid + ":textures/models/vurp/vurp.png";
	}

	/**
	 * Called to tick armor in the armor slot. Override to do something
	 *
	 * @param world
	 * @param player
	 * @param itemStack
	 */
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (TFHelper.isPlayerTransformer(player))
		{
			player.addStat(TFAchievements.transformer, 1);
		}
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
				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2;

				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;

				if (entityLiving instanceof EntityPlayer)
				{
					ItemStack itemstack1 = entityLiving.getHeldItem();
					armorModel.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 0 && itemstack1 != null && itemstack1.getItemUseAction() == EnumAction.bow;
					armorModel.heldItemRight = ((EntityPlayer)entityLiving).getItemInUseDuration() > 0 && itemstack1 != null && itemstack1.getItemUseAction() == EnumAction.block ? 3 : (entityLiving.getEquipmentInSlot(0) != null ? 1 : 0);
				}

				return armorModel;
			}
		}

		return null;
	}

	public void registerIcons(IIconRegister iconRegistry)
	{
		itemIcon = iconRegistry.registerIcon(TransformersMod.modid + ":" + iconString);
	}

	@Override
	public Transformer getTransformer() 
	{
		return TransformersMod.transformerVurp;
	}
}