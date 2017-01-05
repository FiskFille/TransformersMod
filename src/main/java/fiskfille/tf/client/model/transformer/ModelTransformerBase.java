package fiskfille.tf.client.model.transformer;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import com.google.common.collect.Lists;

import fiskfille.tf.client.model.AnimationModifier;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

public abstract class ModelTransformerBase extends MowzieModelBase
{
    private final float baseSpeed;
    private final float baseDegree;
    private AnimationModifier[] animModifiers;

    public int layerToRender;

    public float globalSpeed;
    public float globalDegree;
    public int backwardInverter = 1;

    public ModelTransformerBase(float speed, float degree, AnimationModifier... modifiers)
    {
        baseSpeed = speed;
        baseDegree = degree;
        animModifiers = modifiers;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            ItemStack head = player.getCurrentArmor(3);
            ItemStack chest = player.getCurrentArmor(2);
            ItemStack legs = player.getCurrentArmor(1);
            ItemStack feet = player.getCurrentArmor(0);

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) == getTransformer();
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) == getTransformer();
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) == getTransformer();
            boolean wearingFeet = TFHelper.getTransformerFromArmor(player, 0) == getTransformer();
            boolean hasLightsLayer = getTransformerModel().hasLightsLayer();

            TransformerModel tfModel = getTransformerModel();
            ModelVehicleBase vehicleModel = tfModel.getEffectiveVehicleModel(player);

            vehicleModel.setRotationAngles(f, f1, f2, f3, f4, f5, player);
            setRotationAngles(f, f1, f2, f3, f4, f5, entity);

            if (TFHelper.isFullyTransformed(player))
            {
                if (layerToRender == 2)
                {
                    vehicleModel.render(player, chest, hasLightsLayer); // TODO: Create display vehicle instance with all pieces
                }
            }
            else
            {
                if (!wearingChest)
                {
                    if (layerToRender == 1 && wearingHead)
                    {
                        TFRenderHelper.setupRenderLayers(head, tfModel.getHead(), hasLightsLayer);
                    }

                    if (layerToRender == 3 && wearingLegs)
                    {
                        List<ModelRenderer> hidden = Lists.newArrayList();
                        hidden.add(tfModel.getHead());
                        hidden.addAll(Arrays.asList(tfModel.getFeet()));

                        getWaist().hideUntil(tfModel.getLegs());

                        for (ModelRenderer model : hidden)
                        {
                            model.isHidden = true;
                        }

                        TFRenderHelper.setupRenderLayers(legs, getWaist(), hasLightsLayer);

                        for (ModelRenderer model : hidden)
                        {
                            model.isHidden = false;
                        }
                    }

                    if (layerToRender == 4 && wearingFeet)
                    {
                        getWaist().hideUntil(tfModel.getFeet());

                        tfModel.getHead().isHidden = true;
                        TFRenderHelper.setupRenderLayers(feet, getWaist(), hasLightsLayer);
                        tfModel.getHead().isHidden = false;
                    }
                }
                else if (!TFArmorDyeHelper.areColorsIdentical(head, chest, legs, feet))
                {
                    List<ModelRenderer> hidden = Lists.newArrayList();
                    ItemStack itemstack = chest;

                    if (layerToRender == 1)
                    {
                        if (!TFArmorDyeHelper.areColorsIdentical(chest, head))
                        {
                            getWaist().hideUntil(tfModel.getHead());
                            hidden.addAll(Arrays.asList(tfModel.getLegs()));
                            itemstack = head;
                        }
                        else
                        {
                            return;
                        }
                    }
                    else if (layerToRender == 2)
                    {
                        if (!TFArmorDyeHelper.areColorsIdentical(chest, head))
                        {
                            hidden.add(tfModel.getHead());
                        }

                        if (!TFArmorDyeHelper.areColorsIdentical(chest, legs))
                        {
                            hidden.addAll(Arrays.asList(tfModel.getLegs()));
                        }
                        else if (!TFArmorDyeHelper.areColorsIdentical(chest, feet))
                        {
                            hidden.addAll(Arrays.asList(tfModel.getFeet()));
                        }
                    }
                    else if (layerToRender == 3)
                    {
                        if (!TFArmorDyeHelper.areColorsIdentical(chest, legs))
                        {
                            getWaist().hideUntil(tfModel.getLegs());
                            hidden.add(tfModel.getHead());
                            itemstack = legs;

                            if (!TFArmorDyeHelper.areColorsIdentical(legs, feet))
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
                        if (!TFArmorDyeHelper.areColorsIdentical(legs, feet))
                        {
                            getWaist().hideUntil(tfModel.getFeet());
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

                    TFRenderHelper.setupRenderLayers(itemstack, getWaist(), hasLightsLayer);

                    for (ModelRenderer model : hidden)
                    {
                        model.isHidden = false;
                    }
                }
                else
                {
                    if (layerToRender == 2)
                    {
                        TFRenderHelper.setupRenderLayers(chest, getWaist(), hasLightsLayer);
                    }
                }

                getWaist().hideUntil();
            }
        }
    }

    protected final TransformerModel getTransformerModel()
    {
        TransformerModel model = TFModelRegistry.getModel(getTransformer());

        if (model == null)
        {
            throw new RuntimeException(String.format("No TransformerModel instance registered for type '%s'!", getTransformer().getName()));
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
        super.setRotationAngles(limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, scale, entity);
        setToInitPose();
        globalSpeed = baseSpeed;
        globalDegree = baseDegree;

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) == getTransformer();
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) == getTransformer();
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) == getTransformer();
            boolean wearingFeet = TFHelper.getTransformerFromArmor(player, 0) == getTransformer();
            float progress = TFHelper.getTransformationTimer(player);

            setupOffsets(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);

            for (AnimationModifier modifier : animModifiers)
            {
                if (modifier.predicate.apply(player))
                {
                    switch (modifier.type)
                    {
                    case SPEED:
                    {
                        globalSpeed *= modifier.factor;
                    }
                    case DEGREE:
                    {
                        globalDegree *= modifier.factor;
                    }
                    }
                }
            }

            if (player.moveForward < 0)
            {
                backwardInverter = -1;
            }
            else
            {
                backwardInverter = 1;
            }

            doActiveAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);

            if (TFHelper.getTransformer(player) == getTransformer())
            {
                if (onGround(player) || player.capabilities.isFlying)
                {
                    doWalkingAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
                    doIdleAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
                }
                else
                {
                    doFallingAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
                }
            }
            else
            {
                doPartialAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
            }

            doTransformationAnimations(player, progress, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs, wearingFeet);
        }
    }

    protected void applyDefaultHittingAnimation(ModelRenderer upperArmR, ModelRenderer upperArmL, ModelRenderer head, ModelRenderer chest, ModelRenderer lowerArmR, ModelRenderer lowerArmL)
    {
        if (onGround > -9990.0F)
        {
            float hitAnimation = onGround;

            float change = MathHelper.sin(MathHelper.sqrt_float(hitAnimation) * PI * 2.0F) * 0.2F;
            chest.rotateAngleY += change;
            head.rotateAngleY -= change;

            upperArmR.rotateAngleY += change * 0.5;
            upperArmL.rotateAngleY += change * 0.5;
            upperArmL.rotateAngleX += change * 0.5;

            lowerArmR.rotateAngleY += change * 0.5;
            lowerArmL.rotateAngleY += change * 0.5;
            lowerArmL.rotateAngleX += change * 0.5;

            hitAnimation = 1.0F - onGround;
            hitAnimation *= hitAnimation;
            hitAnimation *= hitAnimation;
            hitAnimation = 1.0F - hitAnimation;
            float f7 = MathHelper.sin(hitAnimation * PI);
            float f8 = MathHelper.sin(onGround * PI) * -(head.rotateAngleX - 0.7F) * 0.75F;

            float armRXChange = (float) (upperArmR.rotateAngleX - (f7 * 1.2D + f8)) * 0.5F;
            float armRZChange = MathHelper.sin(onGround * PI) * -0.4F * 0.5F;

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
        upperArmL.rotateAngleX -= heldItemLeft * 0.125F;
        upperArmR.rotateAngleX -= heldItemRight * 0.125F;

        lowerArmL.rotateAngleX -= heldItemLeft * 0.0625F;
        lowerArmR.rotateAngleX -= heldItemRight * 0.0625F;
    }

    public Transformer getTransformer()
    {
        return null;
    }

    public ModelRendererTF getWaist()
    {
        return null;
    }

    public void renderArmorPiece(int armorPiece)
    {
    }
}
