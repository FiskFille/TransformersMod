package fiskfille.tf.client.displayable;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.item.ModelBassBlaster;

public class DisplayableBassBlaster extends DisplayableRender
{
	private ModelBassBlaster model = new ModelBassBlaster();
	
	@Override
	public void render(ItemStack itemstack)
	{
		bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/weapons/bass_blaster.png"));
				
		float f = 0.75F;
		float f1 = MathHelper.sin(((float)mc.thePlayer.ticksExisted) / 15.0F) * 0.1F;
		GL11.glScalef(f, f, f);
		GL11.glRotatef((float)mc.thePlayer.ticksExisted * 0.75F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0, 0.95F + f1, 0.1F);
		
		model.render();
	}
}
