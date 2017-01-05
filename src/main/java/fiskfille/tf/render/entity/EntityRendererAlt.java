package fiskfille.tf.render.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class EntityRendererAlt extends EntityRenderer
{
	private final Minecraft mc;
	public static float offsetY = 0.0F;
	
	public EntityRendererAlt(Minecraft mc)
	{
		super(mc, mc.getResourceManager());
		this.mc = mc;
	}
	
	public void hurtCameraEffect(float p_78482_1_)
    {
		
    }
	
	@Override
	public void renderWorld(float p_78471_1_, long p_78471_2_)
    {
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glRotatef(90, 0, 1, 0);
		GL11.glRotatef(90, 0, 0, 1);
		super.renderWorld(p_78471_1_, p_78471_2_);
		
		if (mc.mcProfiler.getNameOfLastSection().equals(""))
		{
			
		}
    }

	@Override
	public void updateCameraAndRender(float partialTick)
	{
		hurtCameraEffect(partialTick);
		
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping())
		{
			super.updateCameraAndRender(partialTick);
			return;
		}
		
		GL11.glRotatef(90, 1, 0, 0);
				
		mc.thePlayer.yOffset -= offsetY;
		super.updateCameraAndRender(partialTick);
		mc.thePlayer.yOffset = 1.62F;
	}

	@Override
	public void getMouseOver(float partialTick)
	{
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping())
		{
			super.getMouseOver(partialTick);
			return;
		}
		
		mc.thePlayer.posY += offsetY;
		mc.thePlayer.prevPosY += offsetY;
		mc.thePlayer.lastTickPosY += offsetY;
		super.getMouseOver(partialTick);
		mc.thePlayer.posY -= offsetY;
		mc.thePlayer.prevPosY -= offsetY;
		mc.thePlayer.lastTickPosY -= offsetY;
		
		GL11.glRotatef(90, 1, 0, 0);
	}
}
