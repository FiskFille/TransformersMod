package fiskfille.tf.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.item.armor.ItemCloudtrapArmor;
import fiskfille.tf.item.armor.ItemPurgeArmor;
import fiskfille.tf.item.armor.ItemSkystrikeArmor;
import fiskfille.tf.item.armor.ItemSubwooferArmor;
import fiskfille.tf.item.armor.ItemVurpArmor;
import fiskfille.tf.model.tileentity.ModelDisplayPillar;
import fiskfille.tf.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.tf.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.tf.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.tf.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.model.transformer.vehicle.ModelVurpVehicle;
import fiskfille.tf.tileentity.TileEntityDisplayPillar;

public class RenderDisplayPillar extends TileEntitySpecialRenderer
{
	private ResourceLocation texture = new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/display_pillar.png");
	private ModelDisplayPillar model;
	private ModelSkystrikeVehicle skystrike = new ModelSkystrikeVehicle();
	private ModelPurgeVehicle purge = new ModelPurgeVehicle();
	private ModelVurpVehicle vurp = new ModelVurpVehicle();
	private ModelSubwooferVehicle subwoofer = new ModelSubwooferVehicle();
	private ModelCloudtrapVehicle cloudtrap = new ModelCloudtrapVehicle();
	private ItemRenderer itemRenderer;

	public RenderDisplayPillar()
	{
		model = new ModelDisplayPillar();
		itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
	}

	public void renderAModelAt(TileEntityDisplayPillar tileentity, double d, double d1, double d2, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.bindTexture(texture);
		model.renderAll();
				
		if (tileentity.displayItem != null)
		{
			this.bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/" + getTextureFromTileEntity(tileentity)));
			ModelVehicleBase vehicle = getModelFromTileEntity(tileentity);
			
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

	public ModelVehicleBase getModelFromTileEntity(TileEntityDisplayPillar tileEntity)
	{
		if (tileEntity.displayItem.getItemDamage() == 0) {return skystrike;}
		if (tileEntity.displayItem.getItemDamage() == 1) {return purge;}
		if (tileEntity.displayItem.getItemDamage() == 2) {return vurp;}
		if (tileEntity.displayItem.getItemDamage() == 3) {return subwoofer;}
		if (tileEntity.displayItem.getItemDamage() == 4) {return cloudtrap;}
		return null;
	}

	public String getTextureFromTileEntity(TileEntityDisplayPillar tileEntity)
	{
		if (tileEntity.displayItem.getItemDamage() == 0) {return "skystrike/skystrike.png";}
		if (tileEntity.displayItem.getItemDamage() == 1) {return "purge/purge.png";}
		if (tileEntity.displayItem.getItemDamage() == 2) {return "vurp/vurp.png";}
		if (tileEntity.displayItem.getItemDamage() == 3) {return "subwoofer/subwoofer.png";}
		if (tileEntity.displayItem.getItemDamage() == 4) {return "cloudtrap/cloudtrap.png";}
		return null;
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt((TileEntityDisplayPillar)tileentity, d, d1, d2, f);
	} 
}