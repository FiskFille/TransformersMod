package fiskfille.tf.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.main.MainClass;

public class ItemVurpArmor extends ItemArmor implements ITransformerArmor
{
	public ItemVurpArmor(int par2)
	{
		super(ArmorMaterial.DIAMOND, 4, par2);
		this.setCreativeTab(MainClass.tabTransformers);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return MainClass.modid + ":textures/models/vurp/vurp.png";
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot) 
	{
		ModelBiped armorModel = null;

		if (itemstack != null)
		{
			if (itemstack.getItem() instanceof ItemVurpArmor)
			{
				int type = ((ItemArmor)itemstack.getItem()).armorType;

				armorModel = MainClass.proxy.getArmorModel("Vurp");
			}

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

	public void registerIcons(IIconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(MainClass.modid + ":" + iconString);
	}
}