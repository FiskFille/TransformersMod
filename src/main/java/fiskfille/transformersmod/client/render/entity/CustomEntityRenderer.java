package fiskfille.transformersmod.client.render.entity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

public class CustomEntityRenderer extends EntityRenderer
{
	private final Minecraft mc;
	private static Map<EntityPlayer, Float> offsetY = new HashMap<EntityPlayer, Float>();

	public CustomEntityRenderer(Minecraft mc)
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
//		super.updateCameraAndRender(partialTick);
		hurtCameraEffect(partialTick);

		EntityPlayer player = mc.thePlayer;
		
		if (player == null || player.isPlayerSleeping())
		{
			super.updateCameraAndRender(partialTick);
			return;
		}

		GL11.glRotatef(90, 1, 0, 0);

		Float offsetForPlayer = offsetY.get(player);

		if (offsetForPlayer == null)
		{
			offsetForPlayer = 1.62F;
			offsetY.put(player, 1.62F);
		}
		
		player.yOffset -= offsetForPlayer;
		super.updateCameraAndRender(partialTick);
		player.yOffset = 1.62F;
		//Block
	}

	@Override
	public void getMouseOver(float partialTick)
	{
//		super.getMouseOver(partialTick);
		if (mc.thePlayer == null || mc.thePlayer.isPlayerSleeping())
		{
			super.getMouseOver(partialTick);
			return;
		}

		Float offsetY = this.offsetY.get(mc.thePlayer);
		
		if (offsetY == null)
		{
			offsetY = 1.62F;
			this.offsetY.put(mc.thePlayer, 1.62F);
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

	public static void setOffsetY(EntityPlayer player, float f)
	{
		offsetY.put(player, f);
	}

	public static float getOffsetY(EntityPlayer entityPlayer)
	{
		return offsetY.get(entityPlayer);
	}
}
