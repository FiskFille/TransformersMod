package fiskfille.tf.common.helper;

import fiskfille.tf.client.model.ModelBipedPartial;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gegy1000, FiskFille
 */
@SideOnly(Side.CLIENT)
public class TFModelHelper
{
    public static final ModelBipedPartial LARGE_ARMS = new ModelBipedPartial(false);
    public static final ModelBipedPartial SMALL_ARMS = new ModelBipedPartial(true);

    private static final Map<Entity, ModelOffset> OFFSETS = new HashMap<Entity, ModelOffset>();

    /**
     * @returns the model offsets for the specified entity.
     */
    public static ModelOffset getOffsets(Entity entity)
    {
        return OFFSETS.computeIfAbsent(entity, k -> new ModelOffset(false));
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

            OFFSETS.put(entity, offset);
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
