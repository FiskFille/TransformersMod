package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelDisplayPillar;
import fiskfille.tf.client.model.tileentity.ModelTransformiumSeed;
import fiskfille.tf.common.entity.EntityTransformiumSeed;
import fiskfille.tf.common.tileentity.TileEntityTransformiumSeed;

public class RenderTransformiumSeed extends TileEntitySpecialRenderer
{
	private ResourceLocation texture = new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/transformium_seed.png");
	private ModelTransformiumSeed model;
	private ItemRenderer itemRenderer;

	public RenderTransformiumSeed()
	{
		model = new ModelTransformiumSeed();
		itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
	}

	public void renderModelAt(TileEntityTransformiumSeed tileentity, double d, double d1, double d2, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 0.5F, (float)d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		this.bindTexture(texture);
		model.render(new EntityTransformiumSeed(tileentity.getWorldObj()));
		
		GL11.glPopMatrix();
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderModelAt((TileEntityTransformiumSeed)tileentity, d, d1, d2, f);
	} 
}