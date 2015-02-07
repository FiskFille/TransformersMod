package fiskfille.tf.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.model.item.ModelVurpsSniper;

public class RenderItemVurpsSniper implements IItemRenderer
{
	private ModelVurpsSniper model = new ModelVurpsSniper(); 
	
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
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/weapons/sniper.png"));
		
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			GL11.glPushMatrix();
			
			GL11.glTranslatef(0.8F, 0.6F, 0);
			GL11.glRotatef(-10, 1, 0, 0);
			GL11.glRotatef(200, 0, 0, 1);
			GL11.glRotatef(90, 0, 1, 0);
			
			if ((Entity)data[1] instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)data[1];

				if (player.getItemInUseDuration() != 0)
				{
					GL11.glRotatef(-25, 0, 0, 1);
					GL11.glTranslatef(0, 0.2F, 0);
				}
			}
			
			float f = 2.0F;
			GL11.glScalef(f, f, f);
			model.render();
			GL11.glPopMatrix();
		}
		else if (type == ItemRenderType.EQUIPPED)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0.7F, 0.6F, -0F);
			GL11.glRotatef(-135, 0, 0, 1);
			GL11.glRotatef(0, 0, 0, 1);
			GL11.glRotatef(95, 0, 1, 0);
			
			float f = 0.9F;
			GL11.glScalef(f, f, f);
			model.render();
			GL11.glPopMatrix();
		}
	}
}