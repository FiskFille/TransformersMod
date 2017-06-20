package fiskfille.tf.client.model.transformer;

import com.google.common.collect.Lists;
import fiskfille.tf.client.model.tools.AnimationModifier;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.common.helper.TFArmorDyeHelper;
import fiskfille.tf.common.helper.TFHelper;
import fiskfille.tf.common.helper.TFRenderHelper;
import fiskfille.tf.common.transformer.Transformer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.List;

public abstract class ModelTransformerBase extends MowzieModelBase
{
    private final float baseSpeed;
    private final float baseDegree;
    private AnimationModifier[] animModifiers;

    public EntityEquipmentSlot slotToRender;

    public float globalSpeed;
    public float globalDegree;
    public int backwardInverter = 1;

    public ModelTransformerBase(float speed, float degree, AnimationModifier... modifiers)
    {
        this.baseSpeed = speed;
        this.baseDegree = degree;
        this.animModifiers = modifiers;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            ItemStack feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.HEAD) == this.getTransformer();
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.CHEST) == this.getTransformer();
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.LEGS) == this.getTransformer();
            boolean wearingFeet = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.FEET) == this.getTransformer();

            TransformerModel tfModel = this.getTransformerModel();
            ModelVehicleBase vehicleModel = tfModel.getEffectiveVehicleModel(player);

            vehicleModel.setRotationAngles(f, f1, f2, f3, f4, f5, player);
            this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

            if (TFHelper.isFullyTransformed(player))
            {
                if (this.slotToRender == EntityEquipmentSlot.CHEST)
                {
                    vehicleModel.render(player, chest); // TODO: Create display vehicle instance with all pieces
                }
            }
            else
            {
                if (!wearingChest)
                {
                    if (this.slotToRender == EntityEquipmentSlot.HEAD && wearingHead)
                    {
                        TFRenderHelper.setupRenderLayers(entity, head, tfModel.getHead());
                    }

                    if (this.slotToRender == EntityEquipmentSlot.LEGS && wearingLegs)
                    {
                        List<ModelRenderer> hidden = Lists.newArrayList();
                        hidden.add(tfModel.getHead());
                        hidden.addAll(Arrays.asList(tfModel.getFeet()));

                        this.getWaist().hideUntil(tfModel.getLegs());

                        for (ModelRenderer model : hidden)
                        {
                            model.isHidden = true;
                        }

                        TFRenderHelper.setupRenderLayers(entity, legs, this.getWaist());

                        for (ModelRenderer model : hidden)
                        {
                            model.isHidden = false;
                        }
                    }

                    if (this.slotToRender == EntityEquipmentSlot.FEET && wearingFeet)
                    {
                        this.getWaist().hideUntil(tfModel.getFeet());

                        tfModel.getHead().isHidden = true;
                        TFRenderHelper.setupRenderLayers(entity, feet, this.getWaist());
                        tfModel.getHead().isHidden = false;
                    }
                }
                else if (!this.areIdentical(head, chest, legs, feet))
                {
                    List<ModelRenderer> hidden = Lists.newArrayList();
                    ItemStack itemstack = chest;

                    if (this.slotToRender == EntityEquipmentSlot.HEAD)
                    {
                        if (!this.areIdentical(chest, head))
                        {
                            this.getWaist().hideUntil(tfModel.getHead());
                            hidden.addAll(Arrays.asList(tfModel.getLegs()));
                            itemstack = head;
                        }
                        else
                        {
                            return;
                        }
                    }
                    else if (this.slotToRender == EntityEquipmentSlot.CHEST)
                    {
                        if (!this.areIdentical(chest, head))
                        {
                            hidden.add(tfModel.getHead());
                        }

                        if (!this.areIdentical(chest, legs))
                        {
                            hidden.addAll(Arrays.asList(tfModel.getLegs()));
                        }
                        else if (!this.areIdentical(chest, feet))
                        {
                            hidden.addAll(Arrays.asList(tfModel.getFeet()));
                        }
                    }
                    else if (this.slotToRender == EntityEquipmentSlot.LEGS)
                    {
                        if (!this.areIdentical(chest, legs))
                        {
                            this.getWaist().hideUntil(tfModel.getLegs());
                            hidden.add(tfModel.getHead());
                            itemstack = legs;

                            if (!this.areIdentical(legs, feet))
                            {
                                hidden.addAll(Arrays.asList(tfModel.getFeet()));
                            }
                        }
                        else
                        {
                            return;
                        }
                    }
                    else
                    {
                        if (!this.areIdentical(legs, feet))
                        {
                            this.getWaist().hideUntil(tfModel.getFeet());
                            hidden.add(tfModel.getHead());
                            itemstack = feet;
                        }
                        else
                        {
                            return;
                        }
                    }

                    for (ModelRenderer model : hidden)
                    {
                        model.isHidden = true;
                    }

                    TFRenderHelper.setupRenderLayers(entity, itemstack, this.getWaist());

                    for (ModelRenderer model : hidden)
                    {
                        model.isHidden = false;
                    }
                }
                else
                {
                    if (this.slotToRender == EntityEquipmentSlot.CHEST)
                    {
                        TFRenderHelper.setupRenderLayers(entity, chest, this.getWaist());
                    }
                }

                this.getWaist().hideUntil();
            }
        }
    }

    private boolean areIdentical(ItemStack... stacks)
    {
        if (!TFArmorDyeHelper.areColorsIdentical(stacks))
        {
            return false;
        }

        if (stacks.length > 1)
        {
            for (ItemStack stack : stacks)
            {
                if (stack.isEmpty() || stacks[0].isEmpty() || stacks[0].hasEffect() != stack.hasEffect())
                {
                    return false;
                }
            }
        }

        return true;
    }

    protected final TransformerModel getTransformerModel()
    {
        Transformer transformer = this.getTransformer();

        TransformerModel model = TFModelRegistry.getModel(transformer);

        if (model == null)
        {
            throw new RuntimeException(String.format("No TransformerModel instance registered for type '%s'!", transformer.getIdentifier()));
        }

        return model;
    }

    public void setupOffsets(EntityPlayer player, float progress, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs, boolean wearingFeet)
    {
    }

    public void doActiveAnimations(EntityPlayer player, float progress, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs, boolean wearingFeet)
    {
    }

    public void doWalkingAnimations(EntityPlayer player, float progress, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs, boolean wearingFeet)
    {
    }

    public void doIdleAnimations(EntityPlayer player, float progress, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs, boolean wearingFeet)
    {
    }

    public void doFallingAnimations(EntityPlayer player, float progress, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs, boolean wearingFeet)
    {
    }

    public void doPartialAnimations(EntityPlayer player, float progress, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs, boolean wearingFeet)
    {
    }

    public void doTransformationAnimations(EntityPlayer player, float progress, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs, boolean wearingFeet)
    {
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, float scale, Entity entity)
    {
        if (entity.motionY == 1.25E-85)
        {
            ticks = 0;
        }

        super.setRotationAngles(limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, scale, entity);
        this.setToInitPose();
        this.globalSpeed = this.baseSpeed;
        this.globalDegree = this.baseDegree;

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            boolean wearingHead = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.HEAD) == this.getTransformer();
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.CHEST) == this.getTransformer();
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.LEGS) == this.getTransformer();
            boolean wearingFeet = TFHelper.getTransformerFromArmor(player, EntityEquipmentSlot.FEET) == this.getTransformer();
            float progress = TFHelper.getTransformationTimer(player);

            this.setupOffsets(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);

            for (AnimationModifier modifier : this.animModifiers)
            {
                if (modifier.predicate.test(player))
                {
                    switch (modifier.type)
                    {
                        case SPEED:
                            this.globalSpeed *= modifier.factor;
                            break;
                        case DEGREE:
                            this.globalDegree *= modifier.factor;
                            break;
                    }
                }
            }

            if (player.moveForward < 0)
            {
                this.backwardInverter = -1;
            }
            else
            {
                this.backwardInverter = 1;
            }

            this.doActiveAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);

            if (TFHelper.getTransformer(player) == this.getTransformer())
            {
                if (this.onGround(player) || player.capabilities.isFlying)
                {
                    this.doWalkingAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
                    this.doIdleAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
                }
                else
                {
                    this.doFallingAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
                }
            }
            else
            {
                this.doPartialAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
            }

            this.doTransformationAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
        }
    }

    protected void applyDefaultHittingAnimation(ModelRenderer upperArmR, ModelRenderer upperArmL, ModelRenderer head, ModelRenderer chest, ModelRenderer lowerArmR, ModelRenderer lowerArmL)
    {
        if (this.swingProgress > -9990.0F)
        {
            float hitAnimation = this.swingProgress;

            float change = MathHelper.sin(MathHelper.sqrt(hitAnimation) * this.PI * 2.0F) * 0.2F;
            chest.rotateAngleY += change;
            head.rotateAngleY -= change;

            upperArmR.rotateAngleY += change * 0.5;
            upperArmL.rotateAngleY += change * 0.5;
            upperArmL.rotateAngleX += change * 0.5;

            lowerArmR.rotateAngleY += change * 0.5;
            lowerArmL.rotateAngleY += change * 0.5;
            lowerArmL.rotateAngleX += change * 0.5;

            hitAnimation = 1.0F - this.swingProgress;
            hitAnimation *= hitAnimation;
            hitAnimation *= hitAnimation;
            hitAnimation = 1.0F - hitAnimation;
            float f7 = MathHelper.sin(hitAnimation * this.PI);
            float f8 = MathHelper.sin(this.swingProgress * this.PI) * -(head.rotateAngleX - 0.7F) * 0.75F;

            float armRXChange = (float) (upperArmR.rotateAngleX - (f7 * 1.2D + f8)) * 0.5F;
            float armRZChange = MathHelper.sin(this.swingProgress * this.PI) * -0.4F * 0.5F;

            upperArmR.rotateAngleX += armRXChange;
            upperArmR.rotateAngleY += change * 2.0F * 0.5;
            upperArmR.rotateAngleZ += armRZChange;

            lowerArmR.rotateAngleX += armRXChange;
            lowerArmR.rotateAngleY += change * 2.0F * 0.5;
            lowerArmR.rotateAngleZ += armRZChange;
        }
    }

    protected void applyDefaultHoldingAnimation(ModelRenderer upperArmR, ModelRenderer upperArmL, ModelRenderer lowerArmR, ModelRenderer lowerArmL)
    {
        if (this.rightArmPose == ArmPose.ITEM)
        {
            upperArmR.rotateAngleX -= 0.125F;
            lowerArmR.rotateAngleX -= 0.0625F;
        }

        if (this.leftArmPose == ArmPose.ITEM)
        {
            upperArmL.rotateAngleX -= 0.125F;
            lowerArmL.rotateAngleX -= 0.0625F;
        }
    }

    public Transformer getTransformer()
    {
        return null;
    }

    public ModelRendererTF getWaist()
    {
        return null;
    }

    public void renderArmorPiece(EntityEquipmentSlot armorPiece)
    {
    }
}
