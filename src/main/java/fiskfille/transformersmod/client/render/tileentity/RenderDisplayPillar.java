package fiskfille.transformersmod.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.transformersmod.TransformersAPI;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.client.model.tileentity.ModelDisplayPillar;
import fiskfille.transformersmod.client.model.transformer.TFModelRegistry;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.transformersmod.client.model.transformer.vehicle.ModelVurpVehicle;
import fiskfille.transformersmod.tileentity.TileEntityDisplayPillar;
import fiskfille.transformersmod.transformer.base.Transformer;

public class RenderDisplayPillar extends TileEntitySpecialRenderer
{
	private ResourceLocation texture = new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/display_pillar.png");
	private ModelDisplayPillar model;
	private ItemRenderer itemRenderer;

	public RenderDisplayPillar()
	{
		model = new ModelDisplayPillar();
		itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
	}

	public void renderModelAt(TileEntityDisplayPillar tileentity, double d, double d1, double d2, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.bindTexture(texture);
		model.renderAll();
				
		ItemStack displayItem = tileentity.getDisplayItem();
		
		if (displayItem != null)
		{
			this.bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/" + getTextureFromStack(displayItem)));
			ModelVehicleBase vehicle = getModelFromStack(displayItem);
			
			if (vehicle != null)
			{
				GL11.glRotatef(Minecraft.getMinecraft().thePlayer.ticksExisted, 0.0F, 1.0F, 0.0F);
				GL11.glTranslatef(0.0F, -0.2F/* + (vehicle == skystrike ? 0.15F : 0.0F)*/, 0.0F);
				
				float scale = 0.75F;
				GL11.glScalef(scale, scale, scale);
				vehicle.render();
			}
		}

		GL11.glPopMatrix();
	}

	public ModelVehicleBase getModelFromStack(ItemStack displayItem)
	{
		Transformer transformer = TransformersAPI.getTransformers().get(displayItem.getItemDamage());

		if(transformer != null)
		{
			return TFModelRegistry.getVehicleModel(transformer);
		}
		
		return null;
	}

	public String getTextureFromStack(ItemStack displayItem)
	{
		Transformer transformer = TransformersAPI.getTransformers().get(displayItem.getItemDamage());

		if(transformer != null)
		{
			String name = transformer.getName().toLowerCase().replaceAll(" ", "_");
			return name + "/" + name + ".png";
		}
		
		return null;
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderModelAt((TileEntityDisplayPillar)tileentity, d, d1, d2, f);
	} 
}