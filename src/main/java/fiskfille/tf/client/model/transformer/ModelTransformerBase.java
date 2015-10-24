package fiskfille.tf.client.model.transformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFArmorDyeHelper;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFModelHelper;

public abstract class ModelTransformerBase extends MowzieModelBase
{
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
				setupRenderLayers(head, getVehicle());
			}
			else
			{
				if (!wearingChest)
				{
					if (wearingHead)
					{
						setupRenderLayers(head, getHead());
					}

					if (wearingLegs)
					{
						setupRenderLayers(legs, getRightLeg());
						setupRenderLayers(legs, getLeftLeg());
					}
				}
				else if (!TFArmorDyeHelper.areColorsIdentical(head, chest, legs))
				{
					getWaist().render(0.0625F);
				}
				else
				{
					setupRenderLayers(head, getWaist());
				}
			}
		}
	}
	
	private void setupRenderLayers(ItemStack itemstack, ModelRenderer model)
	{
		if (itemstack != null && TFArmorDyeHelper.isDyed(itemstack) && itemstack.getItem() instanceof ItemTransformerArmor)
		{
			Transformer transformer = ((ItemTransformerArmor)itemstack.getItem()).getTransformer();
			TransformerModel tfModel = TFModelRegistry.getModel(transformer);
			
			
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			Minecraft mc = Minecraft.getMinecraft();
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
		}
		else
		{
			model.render(0.0625F);
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

	public void renderArmorPiece(int armorPiece) {}
}
