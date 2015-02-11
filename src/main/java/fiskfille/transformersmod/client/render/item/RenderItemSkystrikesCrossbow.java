package fiskfille.transformersmod.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.client.model.item.ModelSkystrikesCrossbow;

public class RenderItemSkystrikesCrossbow implements IItemRenderer
{
	private ModelSkystrikesCrossbow model = new ModelSkystrikesCrossbow(); 
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == type.EQUIPPED || type == type.EQUIPPED_FIRST_PERSON;
	}
	
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}
	
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/weapons/skystrikes_crossbow.png"));
		
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			GL11.glPushMatrix();
//			GL11.glRotatef(110, 0.2F, 6.0F, 4.0F);
//			GL11.glRotatef(165, -2.35F, 0.8F, 0.2F);
//			GL11.glTranslatef(0.3F, -0.3F, -0.45F);
			
			if ((Entity)data[1] instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)data[1];

				if (player.getItemInUseDuration() == 0)
				{
					GL11.glRotatef(7, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-15, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(20, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.4F, 0.3F, -0.4F);
				}
				else
				{
					GL11.glRotatef(0, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-10, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(40, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.8F, -0.2F, -0.1F);
				}
			}
			else
			{
				GL11.glRotatef(7, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-15, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(20, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(0.4F, 0.3F, -0.4F);
			}
			
			GL11.glRotatef(110, 0.2F, 6.0F, 4.0F);
			GL11.glRotatef(165, -2.35F, 0.8F, 0.2F);
			
			float f = 1.0F;
			GL11.glScalef(f, f, f);
			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
		else if (type == ItemRenderType.EQUIPPED)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-45, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(5, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-10, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.15F, 0.5F, -0.6F);
			
			float f = 1.0F;
			GL11.glScalef(f, f, f);
			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
	}
}