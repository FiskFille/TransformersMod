package fiskfille.tf.helper;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.player.ModelBipedPartial;

/**
 * @author gegy1000, FiskFille
 */
@SideOnly(Side.CLIENT)
public class TFModelHelper
{
    public static ModelBipedPartial modelBipedPartial = new ModelBipedPartial();

    private static Map<Entity, ModelOffset> offsets = new HashMap<Entity, ModelOffset>();

    /**
     * @returns the model offsets for the specified entity.
     */
    public static ModelOffset getOffsets(Entity entity)
    {
        ModelOffset modelOffset = offsets.get(entity);

        if (modelOffset == null)
        {
            modelOffset = new ModelOffset(false);
            offsets.put(entity, modelOffset);
        }

        return modelOffset;
    }

    /**
     * Hooks into {@link ModelBiped#render}<br>
     * Called after {@link ModelBiped#setRotationAngles}, but before rendering
     * 
     * @param model
     * @param entity
     * @param limbSwing
     * @param limbSwingAmount
     * @param ticks
     * @param rotationYaw
     * @param rotationPitch
     * @param scale
     */
    public static void renderBipedPre(ModelBiped model, Entity entity, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, float scale)
    {
        ModelOffset offset = TFModelHelper.getOffsets(entity);
        
        if (!offset.isInitialized())
        {
            offset = new ModelOffset(true);
            offset.headOffsetX = model.bipedHead.rotationPointX;
            offset.headOffsetY = model.bipedHead.rotationPointY;
            offset.headOffsetZ = model.bipedHead.rotationPointZ;
            
            offsets.put(entity, offset);
        }
        
        if (offset.isInitialized())
        {
            model.bipedHead.rotationPointY = offset.headOffsetY;
            model.bipedHeadwear.rotationPointY = offset.headOffsetY;
            model.bipedHead.rotationPointX = offset.headOffsetX;
            model.bipedHeadwear.rotationPointX = offset.headOffsetX;
            model.bipedHead.rotationPointZ = offset.headOffsetZ;
            model.bipedHeadwear.rotationPointZ = offset.headOffsetZ;
        }
    }

    /**
     * Hooks into {@link ModelBiped#render}<br>
     * Called after after rendering
     * 
     * @param model
     * @param entity
     * @param limbSwing
     * @param limbSwingAmount
     * @param ticks
     * @param rotationYaw
     * @param rotationPitch
     * @param scale
     */
    public static void renderBipedPost(ModelBiped model, Entity entity, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, float scale)
    {
    }
}
