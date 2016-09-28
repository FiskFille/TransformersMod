package fiskfille.tf.client.model.transformer;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

public abstract class ModelTransformerBase extends MowzieModelBase
{
    public int layerToRender;

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

            if (TFDataManager.getTransformationTimer(player, ClientEventHandler.renderTick) == 0)
            {
                if (layerToRender == 2)
                {
                	TFRenderHelper.setupRenderLayers(head, getVehicle(player), hasLightsLayer());
                }
            }
            else
            {
                if (!wearingChest)
                {
                    if (wearingHead)
                    {
                    	TFRenderHelper.setupRenderLayers(head, getHead(), hasLightsLayer());
                    }

                    if (wearingLegs)
                    {
                    	TFRenderHelper.setupRenderLayers(legs, getRightLeg(), hasLightsLayer());
                    	TFRenderHelper.setupRenderLayers(legs, getLeftLeg(), hasLightsLayer());
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
                    	TFRenderHelper.setupRenderLayers(chest, getWaist(), hasLightsLayer());
                    }
                }
            }
        }
    }

    public Transformer getTransformer()
    {
        return null;
    }

    public ModelRenderer getWaist()
    {
        return null;
    }

    public ModelRenderer getVehicle(EntityPlayer player)
    {
        return null;
    }

    public ModelRenderer getRightLeg()
    {
        return null;
    }

    public ModelRenderer getLeftLeg()
    {
        return null;
    }

    public ModelRenderer getHead()
    {
        return null;
    }

    public boolean hasLightsLayer()
    {
        return false;
    }

    public void renderArmorPiece(int armorPiece) { }
}
