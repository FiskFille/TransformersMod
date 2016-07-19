package fiskfille.tf.helper;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

/**
 * @author gegy1000, FiskFille
 */
@SideOnly(Side.CLIENT)
public class TFModelHelper
{
    /**
     * The main client player's model.
     */
    public static ModelBiped modelBipedMain;

    private static Map<EntityPlayer, ModelOffset> offsets = new HashMap<EntityPlayer, ModelOffset>();

    /**
     * @returns the model offsets for the specified player.
     */
    public static ModelOffset getOffsets(EntityPlayer player)
    {
        ModelOffset modelOffset = offsets.get(player);

        if (modelOffset == null)
        {
            modelOffset = new ModelOffset();
            offsets.put(player, modelOffset);
        }

        return modelOffset;
    }
    
    public static void renderBipedPre(ModelBiped model, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glPushMatrix();

        if (TransformersMod.isBattlegearLoaded)
        {
            mods.battlegear2.client.utils.BattlegearRenderHelper.moveOffHandArm(entity, model, f5);
        }

        setRotationAngles(model, f, f1, f2, f3, f4, f5, entity);
    }

    public static void renderBipedPost(ModelBiped model, Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        GL11.glPopMatrix();
    }

    public static void setRotationAngles(ModelBiped model, float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            ModelOffset offsets = TFModelHelper.getOffsets(player);

            model. bipedHead.rotationPointY = offsets.headOffsetY + (model.isSneak ? 1 : 0);
            model.bipedHeadwear.rotationPointY = offsets.headOffsetY + (model.isSneak ? 1 : 0);
            model.bipedHead.rotationPointX = offsets.headOffsetX;
            model.bipedHeadwear.rotationPointX = offsets.headOffsetX;
            model.bipedHead.rotationPointZ = offsets.headOffsetZ;
            model.bipedHeadwear.rotationPointZ = offsets.headOffsetZ;
        }
    }
}
