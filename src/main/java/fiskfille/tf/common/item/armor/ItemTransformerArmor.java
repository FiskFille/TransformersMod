package fiskfille.tf.common.item.armor;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorHelper;

public abstract class ItemTransformerArmor extends ItemArmor implements ISpecialArmor
{
    public ItemTransformerArmor(ArmorMaterial material, int renderIndex, int armorPiece)
    {
        super(material, renderIndex, armorPiece);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return TFModelRegistry.getModel(getTransformer()).getTexture(entity).toString();
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean p_77624_4_)
    {
    	ItemStack itemstack1 = TFArmorHelper.getArmorShell(itemstack);
    	
    	if (itemstack1 != null)
    	{
    		info.add(itemstack1.getDisplayName());
    	}
    }

    public abstract Transformer getTransformer();

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot)
    {
        ModelBiped armorModel = null;

        if (itemstack != null)
        {
            armorModel = getTransformer().getModel().getMainModel();

            if (entityLiving instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entityLiving;
                ModelBiped model = getTransformer().getModel().getStealthModel();

                if (TFDataManager.getStealthModeTimer(player) != 5 && model != null && TFDataManager.isTransformed(player))
                {
                    armorModel = model;
                }
            }

            if (armorModel != null)
            {
                if (armorModel instanceof ModelTransformerBase)
                {
                    ModelTransformerBase model = (ModelTransformerBase) armorModel;
                    model.layerToRender = armorSlot + 1;
                }

                armorModel.bipedHead.showModel = armorSlot == 0;
                armorModel.bipedHeadwear.showModel = armorSlot == 0;
                armorModel.bipedBody.showModel = armorSlot == 1;
                armorModel.bipedRightArm.showModel = armorSlot == 1;
                armorModel.bipedLeftArm.showModel = armorSlot == 1;
                armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
                armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

                armorModel.isSneak = entityLiving.isSneaking();
                armorModel.isRiding = entityLiving.isRiding();
                armorModel.isChild = entityLiving.isChild();
                armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;

                if (entityLiving instanceof EntityPlayer)
                {
                    ItemStack heldItem = entityLiving.getHeldItem();
                    armorModel.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 0 && heldItem != null && heldItem.getItemUseAction() == EnumAction.bow;
                    armorModel.heldItemRight = ((EntityPlayer) entityLiving).getItemInUseDuration() > 0 && heldItem != null && heldItem.getItemUseAction() == EnumAction.block ? 3 : entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
                }

                return armorModel;
            }
        }

        return null;
    }

    @Override
    public void registerIcons(IIconRegister par1IIconRegister)
    {

    }
    
    @Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
    	if (!source.isUnblockable())
    	{
    		ItemStack itemstack = TFArmorHelper.getArmorShell(armor);
    		
    		if (itemstack != null)
    		{
    			ItemArmor item = (ItemArmor)itemstack.getItem();
    			return new ArmorProperties(0, item.damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
    		}
    		else
    		{
    			return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
    		}
    	}
    	
    	return new ArmorProperties(0, 0, 0);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
	{
		ItemStack itemstack = TFArmorHelper.getArmorShell(armor);
		
		if (itemstack != null)
		{
			ItemArmor item = (ItemArmor)itemstack.getItem();
			return item.damageReduceAmount;
		}
		
		return damageReduceAmount;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
	{
		stack.damageItem(damage, entity);
	}
}
