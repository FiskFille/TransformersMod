package fiskfille.tf.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.item.TFItems;
import fiskfille.tf.common.motion.TFMotionManager;
import fiskfille.tf.common.motion.VehicleMotion;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFShootManager;

public class GuiOverlay extends Gui
{
	private Minecraft mc;
	private RenderItem itemRenderer;
	public static final ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/gui/mod_icons.png");

	public static double speed;

	public GuiOverlay(Minecraft mc)
	{
		super();
		this.mc = mc;
		this.itemRenderer = new RenderItem();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRender(RenderGameOverlayEvent.Pre event)
	{	
		int width = event.resolution.getScaledWidth();
		int height = event.resolution.getScaledHeight();
		EntityPlayer player = mc.thePlayer;

		if (event.type == ElementType.JUMPBAR || event.type == ElementType.HOTBAR)
		{
			renderNitroAndSpeed(event, width, height, player);
			renderKatanaDash(event, width, height, player);
			renderShotsLeft(event, width, height, player);
			//renderCrossbowAmmo(event, width, height, player);
		}
	}

	public void renderNitroAndSpeed(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		VehicleMotion transformedPlayer = TFMotionManager.getTransformerPlayer(player);

		int transformationTimer = TFDataManager.getTransformationTimer(player);

		if (transformedPlayer != null && transformationTimer <= 20)
		{
			int nitro = transformedPlayer.getNitro();

			int i = transformationTimer * 10;

			if (transformationTimer <= 19)
			{
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(0F, 0F, 0F, 0.3F);
				//Speed Outline
				drawTexturedModalRect(5 - i, 3, 0, 0, 202, 12);

				//Nitro Outline
				drawTexturedModalRect(5 - i, 16, 0, 0, 202, 12);
				GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
				//Nitro Bar
				drawTexturedModalRect(6 - i, 4, 0, 0, (int)(nitro * 1.25F), 10);
				GL11.glColor4f(1F, 0F, 0F, 0.5F);
				//Speed Bar
				drawTexturedModalRect(6 - i, 17, 0, 0, (int)(speed) > 200 ? 200 : (int)(speed), 10);
				GL11.glEnable(GL11.GL_TEXTURE_2D);

				drawCenteredString(mc.fontRenderer, StatCollector.translateToLocal("stats.nitro.name"), 106 - i, 5, 0xffffff);
				drawCenteredString(mc.fontRenderer, (int)(TFConfig.useMiles ? speed * 0.621371192 : speed) + (TFConfig.useMiles ? " mph" : " km/h"), 106 - i, 18, 0xffffff);
			}
		}
	}

	public void renderShotsLeft(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		Transformer transformer = TFHelper.getTransformer(player);

		if (transformer != null)
		{
			int transformationTimer = TFDataManager.getTransformationTimer(player);

			int stealthModeTimer = TFDataManager.getStealthModeTimer(player);

			if (transformationTimer <= 20 && (transformer.canShoot(player)))
			{
				int transformationOffsetX = 0;

				if (transformer.hasStealthForce(player) && stealthModeTimer <= 5)
				{
					transformationOffsetX = stealthModeTimer * 25;
				}
				else
				{
					transformationOffsetX = (int)(transformationTimer * 7.5F);
				}

				int x = 80;

				int j = 20 - TFShootManager.shootCooldown;

				double d = (double)j * 2.5;

				String shotsLeft = "" + TFShootManager.shotsLeft;

				if (TFShootManager.shotsLeft <= 0)
				{
					shotsLeft = EnumChatFormatting.RED + shotsLeft;
				}

				drawString(mc.fontRenderer, StatCollector.translateToLocal("stats.shots_left.name") + ": " + shotsLeft, 5 - transformationOffsetX, 32, 0xffffff);

				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(0F, 0F, 0F, 0.15F);

				int y = 30;
				drawTexturedModalRect(x - transformationOffsetX, y, 0, 0, 52, 12);
				GL11.glColor4f(1F, 0F, 0F, 0.25F);
				drawTexturedModalRect(x + 1 - transformationOffsetX, y + 1, 0, 0, (int)(d), 10);

				GL11.glEnable(GL11.GL_TEXTURE_2D);
			}
			else 
			{
				ItemStack heldItem = player.getHeldItem();
			
				if (heldItem != null)
				{
					if (transformationTimer == 20 && (heldItem.getItem() == TFItems.vurpsSniper && TFHelper.isPlayerVurp(player))) //TODO
					{
						int x = 75;

						int j = 20 - TFShootManager.shootCooldown;

						double d = (double)j * 2.5;

						String shotsLeft = "" + TFShootManager.shotsLeft;

						if (TFShootManager.shotsLeft <= 0)
						{
							shotsLeft = EnumChatFormatting.RED + shotsLeft;
						}

						drawString(mc.fontRenderer, StatCollector.translateToLocal("stats.shots_left.name") + ": " + shotsLeft, 3, 5, 0xffffff);

						GL11.glDisable(GL11.GL_TEXTURE_2D);
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
						GL11.glColor4f(0F, 0F, 0F, 0.15F);

						int y = 4;
						drawTexturedModalRect(x, y, 0, 0, 52, 12);
						GL11.glColor4f(1F, 0F, 0F, 0.25F);
						drawTexturedModalRect(x + 1, y + 1, 0, 0, (int)(d), 10);

						GL11.glEnable(GL11.GL_TEXTURE_2D);
						
						if (this.mc.gameSettings.thirdPersonView == 0 && heldItem != null && heldItem.getItem() == TFItems.vurpsSniper && TFDataManager.getZoomTimer(player) > 7)
						{
							GL11.glDisable(GL11.GL_DEPTH_TEST);
					        GL11.glDepthMask(false);
					        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
					        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					        GL11.glDisable(GL11.GL_ALPHA_TEST);
					        this.mc.getTextureManager().bindTexture(new ResourceLocation(TransformersMod.modid, "textures/misc/sniper_scope.png"));
					        Tessellator tessellator = Tessellator.instance;
					        tessellator.startDrawingQuads();
					        tessellator.addVertexWithUV(0.0D, (double)height, -90.0D, 0.0D, 1.0D);
					        tessellator.addVertexWithUV((double)width, (double)height, -90.0D, 1.0D, 1.0D);
					        tessellator.addVertexWithUV((double)width, 0.0D, -90.0D, 1.0D, 0.0D);
					        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
					        tessellator.draw();
					        GL11.glDepthMask(true);
					        GL11.glEnable(GL11.GL_DEPTH_TEST);
					        GL11.glEnable(GL11.GL_ALPHA_TEST);
					        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						}
					}
				}
			}
		}
	}

	public void renderKatanaDash(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		if (player.getHeldItem() != null && player.getHeldItem().getItem() == TFItems.purgesKatana && !TFDataManager.isInVehicleMode(player) && TFHelper.isPlayerPurge(player))
		{
			int x = width / 2 - 26;
			int j = TFItems.purgesKatana.getMaxItemUseDuration(player.getHeldItem()) - player.getItemInUseCount();
			double d = (double)j / 10;

			if (d > 2.0D)
			{
				d = 2.0D;
			}

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(0F, 0F, 0F, 0.15F);

			int y = 0;

			if (TFConfig.purgeDashTop)
				y = 5;
			else
				y = height / 2 + 9;

			drawTexturedModalRect(x, y, 0, 0, 52, 12);
			GL11.glColor4f(1F, 0F, 0F, 0.25F);
			drawTexturedModalRect(x + 1, y + 1, 0, 0, (int)(d * 25), 10);

			GL11.glEnable(GL11.GL_TEXTURE_2D);
		} 
	}

	public void renderCrossbowAmmo(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{

	}
}