package fiskfille.tf.client.model.transformer;

import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.data.TFDataManager;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

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

            if (TFDataManager.getTransformationTimer(player) == 0)
            {
                if (layerToRender == 2)
                {
                    TFHelper.setupRenderLayers(head, getVehicle(), hasLightsLayer());
                }
            }
            else
            {
                if (!wearingChest)
                {
                    if (wearingHead)
                    {
                        TFHelper.setupRenderLayers(head, getHead(), hasLightsLayer());
                    }

                    if (wearingLegs)
                    {
                        TFHelper.setupRenderLayers(legs, getRightLeg(), hasLightsLayer());
                        TFHelper.setupRenderLayers(legs, getLeftLeg(), hasLightsLayer());
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
                        TFHelper.setupRenderLayers(chest, getWaist(), hasLightsLayer());
                    }
                }
            }
        }
    }

    private void setupRenderLayers(ItemStack itemstack, ModelRenderer model)
    {
        Minecraft mc = Minecraft.getMinecraft();

        if (itemstack != null && itemstack.getItem() instanceof ItemTransformerArmor)
        {
            Transformer transformer = ((ItemTransformerArmor) itemstack.getItem()).getTransformer();
            TransformerModel tfModel = TFModelRegistry.getModel(transformer);

            if (TFArmorDyeHelper.isDyed(itemstack))
            {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                float[] afloat = TFHelper.hexToRGB(TFArmorDyeHelper.getPrimaryColor(itemstack));

                GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_primary.png"));
                model.render(0.0625F);

                afloat = TFHelper.hexToRGB(TFArmorDyeHelper.getSecondaryColor(itemstack));
                GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_secondary.png"));
                model.render(0.0625F);

                GL11.glColor4f(1, 1, 1, 1);
                mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_base.png"));
                model.render(0.0625F);

                if (hasLightsLayer())
                {
                    TFHelper.setLighting(61680);
                    mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
                    model.render(0.0625F);
                }
            }
            else
            {
                model.render(0.0625F);

                if (hasLightsLayer())
                {
                    TFHelper.setLighting(61680);
                    mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
                    model.render(0.0625F);
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

    public ModelRenderer getVehicle()
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

    public void renderArmorPiece(int armorPiece) {}
}
