package fiskfille.tf.common.item.armor;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.common.helper.TFArmorHelper;
import fiskfille.tf.common.transformer.base.Transformer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class ItemTransformerArmor extends ItemArmor implements ISpecialArmor
{
    public ItemTransformerArmor(ArmorMaterial material, int renderIndex, EntityEquipmentSlot armorPiece)
    {
        super(material, renderIndex, armorPiece);
        this.setCreativeTab(TransformersMod.TRANSFORMERS_TAB);
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase) entity;

            for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
            {
                if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
                {
                    ItemStack armor = living.getItemStackFromSlot(slot);

                    if (!armor.isEmpty() && armor.getItem() instanceof ItemTransformerArmor)
                    {
                        if (this.getTransformer() != ((ItemTransformerArmor) armor.getItem()).getTransformer())
                        {
                            return false;
                        }
                    }

                }
            }
        }

        return super.isValidArmor(stack, armorType, entity);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage, int slot)
    {
        if (!source.isUnblockable())
        {
            ItemStack shell = TFArmorHelper.getArmorShell(stack);

            if (!shell.isEmpty())
            {
                ItemArmor armor = (ItemArmor) shell.getItem();
                return new ArmorProperties(0, armor.damageReduceAmount / 25D, stack.getMaxDamage() + 1 - stack.getItemDamage());
            }
            else
            {
                return new ArmorProperties(0, this.damageReduceAmount / 25D, stack.getMaxDamage() + 1 - stack.getItemDamage());
            }
        }

        return new ArmorProperties(0, 0, 0);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        ItemStack stack = TFArmorHelper.getArmorShell(armor);

        if (!stack.isEmpty())
        {
            return TFArmorHelper.getArmorValue(player, stack, slot);
        }

        return this.damageReduceAmount;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
        stack.damageItem(damage, entity);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return TFModelRegistry.getModel(this.getTransformer()).getTexture(entity, "").toString();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> info, boolean advanced)
    {
        ItemStack shell = TFArmorHelper.getArmorShell(stack);

        if (!shell.isEmpty())
        {
            info.add(shell.getDisplayName());
        }
    }

    public abstract Transformer getTransformer();

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        ModelTransformerBase model = this.getTransformer().getModel().getMainModel();

        if (!stack.isEmpty() && model != null)
        {
            model.slotToRender = armorSlot;

            model.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
            model.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
            model.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;
            model.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
            model.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
            model.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
            model.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;

            model.isSneak = entity.isSneaking();
            model.isRiding = entity.isRiding();
            model.isChild = entity.isChild();

            ModelBiped.ArmPose mainHandPose = this.getPose(entity, entity.getHeldItem(EnumHand.MAIN_HAND));
            ModelBiped.ArmPose offHandPose = this.getPose(entity, entity.getHeldItem(EnumHand.OFF_HAND));

            if (entity.getPrimaryHand() == EnumHandSide.RIGHT)
            {
                model.rightArmPose = mainHandPose;
                model.leftArmPose = offHandPose;
            }
            else
            {
                model.rightArmPose = offHandPose;
                model.leftArmPose = mainHandPose;
            }

            return model;
        }

        return null;
    }

    private ModelBiped.ArmPose getPose(EntityLivingBase entity, ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            if (entity.getItemInUseCount() > 0)
            {
                EnumAction action = stack.getItemUseAction();

                if (action == EnumAction.BLOCK)
                {
                    return ModelBiped.ArmPose.BLOCK;
                }
                else if (action == EnumAction.BOW)
                {
                    return ModelBiped.ArmPose.BOW_AND_ARROW;
                }
            }

            return ModelBiped.ArmPose.ITEM;
        }

        return ModelBiped.ArmPose.EMPTY;
    }
}
