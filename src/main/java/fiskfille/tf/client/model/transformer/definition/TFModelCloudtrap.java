package fiskfille.tf.client.model.transformer.definition;

import javax.vecmath.Vector3f;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.client.model.transformer.ModelCloudtrap;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVurpVehicle;

public class TFModelCloudtrap extends TransformerModel
{
	private ModelCloudtrap model;
	private ModelCloudtrapVehicle vehicle;

	public TFModelCloudtrap() 
	{
		this.model = new ModelCloudtrap();
		this.vehicle = new ModelCloudtrapVehicle();
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
		return null;
	}

	@Override
	public ModelRenderer getUpperArm() 
	{
		return null;
	}

	@Override
	public ModelRenderer getBody() 
	{
		return null;
	}
	
	@Override
	public ModelRenderer getHead() 
	{
		return null;
	}

	@Override
	public ResourceLocation getTexture()
	{
		return new ResourceLocation(TransformersMod.modid, "textures/models/cloudtrap/cloudtrap.png");
	}
}
