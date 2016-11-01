package fiskfille.tf.helper;

import java.awt.Color;
import java.util.Map;
import java.util.WeakHashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFRenderHelper
{
	private static Minecraft mc = Minecraft.getMinecraft();
	private static float lastBrightnessX;
	private static float lastBrightnessY;
	private static final Map<EntityPlayer, Double> previousMotionY = new WeakHashMap<EntityPlayer, Double>();

	public static IIcon energonFlowingIcon;
	public static IIcon energonStillIcon;

	public static void setLighting(int lighting)
	{
		storeLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (lighting % 65536) / 255.0F, (lighting / 65536) / 255.0F);
	}

	public static void storeLighting()
	{
		lastBrightnessX = OpenGlHelper.lastBrightnessX;
		lastBrightnessY = OpenGlHelper.lastBrightnessY;
	}

	public static void resetLighting()
	{
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
	}

	public static float[] hexToRGB(int hex)
	{
		float r = (float) ((hex & 0xFF0000) >> 16) / 255F;
		float g = (float) ((hex & 0xFF00) >> 8) / 255F;
		float b = (float) (hex & 0xFF) / 255F;
		return new float[]{r, g, b};
	}

	public static int blend(int a, int b, float ratio)
	{
		if (ratio > 1.0F)
		{
			ratio = 1.0F;
		}
		else if (ratio < 0.0F)
		{
			ratio = 0.0F;
		}

		float iRatio = 1.0F - ratio;

		int aA = (a >> 24 & 0xff);
		int aR = ((a & 0xff0000) >> 16);
		int aG = ((a & 0xff00) >> 8);
		int aB = (a & 0xff);

		int bA = (b >> 24 & 0xff);
		int bR = ((b & 0xff0000) >> 16);
		int bG = ((b & 0xff00) >> 8);
		int bB = (b & 0xff);

		int A = (int) ((aA * iRatio) + (bA * ratio));
		int R = (int) ((aR * iRatio) + (bR * ratio));
		int G = (int) ((aG * iRatio) + (bG * ratio));
		int B = (int) ((aB * iRatio) + (bB * ratio));

		return A << 24 | R << 16 | G << 8 | B;
	}

	public static int brighter(int hex, float factor)
	{
		float[] afloat = hexToRGB(hex);

		for (int i = 0; i < afloat.length; ++i)
		{
			afloat[i] = 1 - (1 - afloat[i]) * factor;
		}

		Color color = new Color((int)(afloat[0] * 255), (int)(afloat[1] * 255), (int)(afloat[2] * 255));
		return color.getRGB();
	}

	public static int darker(int hex, float factor)
	{
		float[] afloat = hexToRGB(hex);

		for (int i = 0; i < afloat.length; ++i)
		{
			afloat[i] *= factor;
		}

		Color color = new Color((int)(afloat[0] * 255), (int)(afloat[1] * 255), (int)(afloat[2] * 255));
		return color.getRGB();
	}

	public static void setupRenderLayers(ItemStack itemstack, ModelRenderer model, boolean hasLightsLayer)
	{
		if (itemstack != null && itemstack.getItem() instanceof ItemTransformerArmor)
		{
			Transformer transformer = ((ItemTransformerArmor) itemstack.getItem()).getTransformer();
			TransformerModel tfModel = TFModelRegistry.getModel(transformer);

			if (TFArmorDyeHelper.isDyed(itemstack))
			{
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				float[] afloat = hexToRGB(TFArmorDyeHelper.getPrimaryColor(itemstack));

				GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
				mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_primary.png"));
				model.render(0.0625F);

				afloat = hexToRGB(TFArmorDyeHelper.getSecondaryColor(itemstack));
				GL11.glColor4f(afloat[0], afloat[1], afloat[2], 1);
				mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_secondary.png"));
				model.render(0.0625F);

				GL11.glColor4f(1, 1, 1, 1);
				mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_base.png"));
				model.render(0.0625F);

				if (hasLightsLayer)
				{
					setLighting(61680);
					mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
					model.render(0.0625F);
					TFRenderHelper.resetLighting();
				}
			}
			else
			{
				model.render(0.0625F);

				if (hasLightsLayer)
				{
					setLighting(61680);
					mc.getTextureManager().bindTexture(new ResourceLocation(tfModel.getTextureDirPrefix(), "textures/models/" + tfModel.getTextureDir() + "_lights.png"));
					model.render(0.0625F);
					TFRenderHelper.resetLighting();
				}
			}
		}
	}

	public static float median(float curr, float prev, float partialTicks)
	{
		return prev + (curr - prev) * partialTicks;
	}

	public static double median(double curr, double prev, float partialTicks)
	{
		return prev + (curr - prev) * partialTicks;
	}

	public static void startGlScissor(int x, int y, int width, int height)
	{
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution reso = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		double scaleW = (double) mc.displayWidth / reso.getScaledWidth_double();
		double scaleH = (double) mc.displayHeight / reso.getScaledHeight_double();

		if (width <= 0 || height <= 0)
		{
			return;
		}
		if (x < 0)
		{
			x = 0;
		}
		if (y < 0)
		{
			y = 0;
		}

		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor((int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH)), (int) Math.floor((double) (x + width) * scaleW) - (int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) y * scaleH)) - (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH)));
	}

	public static void endGlScissor()
	{
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

	public static double getMotionY(EntityPlayer player)
	{
		return player == mc.thePlayer ? player.motionY : median(player.posY - player.prevPosY, TFRenderHelper.previousMotionY.containsKey(player) ? TFRenderHelper.previousMotionY.get(player) : 0.0, ClientEventHandler.renderTick);
	}

	public static void updateMotionY(EntityPlayer player)
	{
		TFRenderHelper.previousMotionY.put(player, player.posY - player.prevPosY);
	}

	public static void renderTag(String s, float x, float y, float z)
	{
		RenderManager renderManager = RenderManager.instance;
		FontRenderer fontrenderer = renderManager.getFontRenderer();
		float f2 = -0.02F;
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(mc.thePlayer.rotationYaw + 180, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-mc.thePlayer.rotationPitch, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(-f2, -f2, f2);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		tessellator.startDrawingQuads();
		int i = fontrenderer.getStringWidth(s) / 2;
		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
		tessellator.addVertex(-i - 1, -1.0D, 0.0D);
		tessellator.addVertex(-i - 1, 8.0D, 0.0D);
		tessellator.addVertex(i + 1, 8.0D, 0.0D);
		tessellator.addVertex(i + 1, -1.0D, 0.0D);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
		fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, -1);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public static void faceVec(Vec3 src, Vec3 dst)
	{
		double d0 = dst.xCoord - src.xCoord;
		double d1 = dst.yCoord - src.yCoord;
		double d2 = dst.zCoord - src.zCoord;
		double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		float yaw = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
		float pitch = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
		GL11.glRotated(-yaw, 0, 1, 0);
		GL11.glRotated(pitch, 1, 0, 0);
	}
}
