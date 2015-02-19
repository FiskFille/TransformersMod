package fiskfille.tf.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.main.MainClass;
import fiskfille.tf.main.TFItems;
import fiskfille.tf.model.ModelSkystrike;
import fiskfille.tf.model.item.ModelPurgesKatana;

public class RenderItemPurgesKatana implements IItemRenderer
{
	private ModelPurgesKatana model = new ModelPurgesKatana(); 
	
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type != type.ENTITY && type != type.INVENTORY;
	}
	
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}
	
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MainClass.modid, "textures/models/weapons/purges_katana.png"));
		
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.FIRST_PERSON_MAP)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(0, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(210, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.7F, 0.2F, -0.0F);
			
			float f = 1.0F;
			float f1 = f * 0.75F;
			GL11.glScalef(f, f, f);
			model.render(0.0625F);
			
			float f2 = 0.65F;
			GL11.glScalef(f, f, f2);
			model.renderWheels(0.0625F);
			GL11.glPopMatrix();
		}
		else if (type == ItemRenderType.EQUIPPED)
		{
			GL11.glPushMatrix();
			GL11.glRotatef(10, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(220, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.8F, 0.25F, -0.055F);
			
			float f = 1.0F;
			float f1 = f * 0.75F;
			GL11.glScalef(f, f, f);
			model.render(0.0625F);
			
			float f2 = 0.65F;
			GL11.glScalef(f, f, f2);
			model.renderWheels(0.0625F);
			GL11.glPopMatrix();
		}
	}
}