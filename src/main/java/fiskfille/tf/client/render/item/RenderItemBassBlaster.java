package fiskfille.tf.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.item.ModelBassBlaster;
import fiskfille.tf.client.model.item.ModelFlamethrower;

public class RenderItemBassBlaster implements IItemRenderer
{
	private ModelBassBlaster model = new ModelBassBlaster(); 
	
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
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/weapons/bass_blaster.png"));
		
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0.8F, 0.6F, 0.2F);
			GL11.glRotatef(-5, 1, 0, 0);
			GL11.glRotatef(210, 0, 0, 1);
			GL11.glRotatef(90, 0, 1, 0);
			
			float f = 1.8F;
			GL11.glScalef(f, f, f);
			model.render();
			GL11.glPopMatrix();
		}
		else if (type == ItemRenderType.EQUIPPED)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0.8F, 0.29F, -0.025F);
			GL11.glRotatef(4, 0, 0, 1);
			GL11.glRotatef(-95, 0, 1, 0);
			GL11.glRotatef(30, 1, 0, 0);
			
			float f = 1.0F;
			GL11.glScalef(f, f, f);
			model.render();
			GL11.glPopMatrix();
		}
	}
}