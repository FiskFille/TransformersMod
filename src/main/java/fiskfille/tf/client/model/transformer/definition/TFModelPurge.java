package fiskfille.tf.client.model.transformer.definition;

import javax.vecmath.Vector3f;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.client.model.transformer.ModelPurge;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

public class TFModelPurge extends TransformerModel
{
	private ModelPurge model;
	private ModelPurgeVehicle vehicle;

	public TFModelPurge() 
	{
		this.model = new ModelPurge();
		this.vehicle = new ModelPurgeVehicle();
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
		return model.lowerArm1;
	}

	@Override
	public ModelRenderer getUpperArm() 
	{
		return model.lowerArm1;
	}

	@Override
	public ModelRenderer getBackside() 
	{
		return model.chest;
	}

	@Override
	public void renderItem(EntityPlayer player, ItemStack stack)
	{
		GL11.glTranslatef(0.05F, -0F, 0.1F);
	}

	@Override
	public void renderCape(EntityPlayer player)
	{
		GL11.glTranslatef(0, -0.2F, 0.1F);
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer player)
	{
		GL11.glTranslatef(0, 0.05F, 0.5F);
	}
	
	@Override
	public ResourceLocation getTexture()
	{
		return new ResourceLocation(TransformersMod.modid, "textures/models/purge/purge.png");
	}
}
