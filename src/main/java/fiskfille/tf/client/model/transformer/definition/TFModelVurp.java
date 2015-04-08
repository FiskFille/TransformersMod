package fiskfille.tf.client.model.transformer.definition;

import javax.vecmath.Vector3f;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.client.model.transformer.ModelVurp;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVurpVehicle;

public class TFModelVurp extends TransformerModel
{
	private ModelVurp model;
	private ModelVurpVehicle vehicle;

	public TFModelVurp() 
	{
		this.model = new ModelVurp();
		this.vehicle = new ModelVurpVehicle();
	}

	@Override
	public Biped getMainModel() 
	{
		return model;
	}

	@Override
	public ModelVehicleBase getVehicleModel()
	{
		return vehicle;
	}

	@Override
	public ModelRenderer getLowerArm() 
	{
		return model.lowerArmR;
	}

	@Override
	public ModelRenderer getUpperArm() 
	{
		return model.upperArmR;
	}

	@Override
	public ModelRenderer getBackside() 
	{
		return model.torsobase;
	}

	@Override
	public void renderItem(EntityPlayer player, ItemStack stack)
	{
		GL11.glTranslatef(0.05F, -0F, 0.1F);
	}

	@Override
	public void renderCape(EntityPlayer player)
	{
		GL11.glTranslatef(0F, -0.2F, 0.1F);
	}
	
	@Override
	public void renderFirstPersonArm(EntityPlayer player)
	{
		GL11.glTranslatef(0F, 0.3F, 0.5F);
	}
	
	@Override
	public ResourceLocation getTexture()
	{
		return new ResourceLocation(TransformersMod.modid, "textures/models/vurp/vurp.png");
	}
}
