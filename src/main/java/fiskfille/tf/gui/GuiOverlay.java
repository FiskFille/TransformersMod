package fiskfille.tf.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
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
import fiskfille.tf.config.TFConfig;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.event.CommonEventHandler;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFShootManager;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.misc.TFMotionManager;
import fiskfille.tf.misc.VehicleMotion;
import fiskfille.tf.transformer.base.Transformer;

public class GuiOverlay extends Gui
{
	private Minecraft mc;
	private RenderItem itemRenderer;
	public static final ResourceLocation texture = new ResourceLocation(TransformersMod.modid, "textures/gui/mod_icons.png");
	
	private long lastTime;
	private double lastX;
	private double lastY;
	private double lastZ;

	private double speed;

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
			long time = System.currentTimeMillis();

			long timeDiff = time - lastTime;

			if (timeDiff >= 500)
			{
				double diffX = (player.posX - lastX);
				double diffY = (player.posY - lastY);
				double diffZ = (player.posZ - lastZ);

				speed = (double) (Math.sqrt((diffX * diffX) + (diffY * diffY) + (diffZ * diffZ)) * ((((double)(timeDiff / 500) * 60) * 60) * 2) / 1000);

				lastX = player.posX;
				lastY = player.posY;
				lastZ = player.posZ;

				lastTime = time;
			}

			int nitro = transformedPlayer.getNitro();
			//int speed = (int)(transformedPlayer.getVelocity() * 100);
			
			int i = transformationTimer * 10;

			if (transformationTimer <= 19)
			{
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(0F, 0F, 0F, 0.3F);
				drawTexturedModalRect(5 - i, 17, 0, 0, 202, 12);
				drawTexturedModalRect(5 - i, 30, 0, 0, 202, 12);
				GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
				drawTexturedModalRect(6 - i, 18, 0, 0, (int)(nitro * 1.25F), 10);
                GL11.glColor4f(1F, 0F, 0F, 0.5F);
				drawTexturedModalRect(6 - i, 31, 0, 0, (int)(speed * 1F) > 200 ? 200 : (int)(speed * 1F), 10);
				GL11.glEnable(GL11.GL_TEXTURE_2D);

				drawCenteredString(mc.fontRenderer, StatCollector.translateToLocal("stats.nitro.name"), 106 - i, 19, 0xffffff);
				drawCenteredString(mc.fontRenderer, (int)(TFConfig.useMiles ? speed * 0.621371192 : speed) + (TFConfig.useMiles ? " mph" : " km/h"), 106 - i, 32, 0xffffff);
			}
		}
		else
		{
			lastX = player.posX;
			lastY = player.posY;
			lastZ = player.posZ;
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

				boolean show = true;
				
				if (show)
				{
					int x = 80;

					int j = 20 - TFShootManager.shootCooldown;

					double d = (double)j * 2.5;

					String shotsLeft = "" + TFShootManager.shotsLeft;
					
					if (TFShootManager.shotsLeft <= 0)
					{
						shotsLeft = EnumChatFormatting.RED + shotsLeft;
					}
					
					drawString(mc.fontRenderer, StatCollector.translateToLocal("stats.shots_left.name") + ": " + shotsLeft, 5 - transformationOffsetX, 5, 0xffffff);

					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glColor4f(0F, 0F, 0F, 0.15F);

					int y = 3;
					drawTexturedModalRect(x - transformationOffsetX, y, 0, 0, 52, 12);
					GL11.glColor4f(1F, 0F, 0F, 0.25F);
					drawTexturedModalRect(x + 1 - transformationOffsetX, y + 1, 0, 0, (int)(d), 10);

					GL11.glEnable(GL11.GL_TEXTURE_2D);
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