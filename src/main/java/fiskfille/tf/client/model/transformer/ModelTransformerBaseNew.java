package fiskfille.tf.client.model.transformer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.model.AnimationModifier;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

public abstract class ModelTransformerBaseNew extends ModelTransformerBase
{
	private final float baseSpeed;
	private final float baseDegree;
	private AnimationModifier[] animModifiers;

	public float globalSpeed;
	public float globalDegree;
	public int backwardInverter = 1;

	public ModelTransformerBaseNew(float speed, float degree, AnimationModifier... modifiers)
	{
		baseSpeed = speed;
		baseDegree = degree;
		animModifiers = modifiers;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            ItemStack head = player.getCurrentArmor(3);
            ItemStack chest = player.getCurrentArmor(2);
            ItemStack legs = player.getCurrentArmor(1);

            boolean wearingHead = TFHelper.getTransformerFromArmor(player, 3) == getTransformer();
            boolean wearingChest = TFHelper.getTransformerFromArmor(player, 2) == getTransformer();
            boolean wearingLegs = TFHelper.getTransformerFromArmor(player, 1) == getTransformer();
            boolean hasLightsLayer = getTransformerModel().hasLightsLayer();

            getTransformerModel().getVehicleModel().setupRenderState();
            
            if (TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) == 0)
            {
                if (layerToRender == 2)
                {
                	getTransformerModel().getVehicleModel().render(head, hasLightsLayer, false); // TODO: Create display vehicle instance with all pieces
                }
            }
            else
            {
                if (!wearingChest)
                {
                    if (wearingHead)
                    {
                    	TFRenderHelper.setupRenderLayers(head, getHead(), hasLightsLayer);
                    }

                    if (wearingLegs)
                    {
                    	TFRenderHelper.setupRenderLayers(legs, getRightLeg(), hasLightsLayer);
                    	TFRenderHelper.setupRenderLayers(legs, getLeftLeg(), hasLightsLayer);
                    }
                }
                else if (!TFArmorDyeHelper.areColorsIdentical(head, chest, legs))
                {
                    if (layerToRender == 2)
                    {
                        getWaist().render(0.0625F);
                    }
                }
                else
                {
                    if (layerToRender == 2)
                    {
                    	TFRenderHelper.setupRenderLayers(chest, getWaist(), hasLightsLayer);
                    }
                }
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
	
	public void setupOffsets(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs) { }
	
	public void doActiveAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs) { }
	
	public void doWalkingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs) { }
	
	public void doIdleAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs) { }
	
	public void doFallingAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs) { }
	
	public void doPartialAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs) { }
	
	public void doTransformationAnimations(EntityPlayer player, float t, float f, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, boolean wearingHead, boolean wearingChest, boolean wearingLegs) { }
	
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
			float t = TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick);
			float f = 20 - t;
			
			setupOffsets(player, t, f, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs);

			for (AnimationModifier modifier : animModifiers)
			{
				if (modifier.predicate.matches(player))
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

			doActiveAnimations(player, t, f, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs);

			if (wearingChest && wearingHead && wearingLegs)
			{
				if (onGround(player) || player.capabilities.isFlying)
				{
					doWalkingAnimations(player, t, f, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs);
					doIdleAnimations(player, t, f, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs);
				}
				else
				{
					doFallingAnimations(player, t, f, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs);
				}
			}
			else
			{
				doPartialAnimations(player, t, f, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs);
			}
			
			doTransformationAnimations(player, t, f, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, wearingHead, wearingChest, wearingLegs);
		}
	}
}
