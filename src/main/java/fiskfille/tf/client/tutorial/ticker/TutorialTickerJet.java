package fiskfille.tf.client.tutorial.ticker;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import fiskfille.tf.client.keybinds.TFKeyBinds;
import fiskfille.tf.client.tutorial.EnumTutorialType;
import fiskfille.tf.client.tutorial.TutorialHandler;
import fiskfille.tf.client.tutorial.TutorialObjective;

public class TutorialTickerJet extends TutorialTicker
{
	public TutorialObjective accelerate = new TutorialObjective();
	public TutorialObjective nitro = new TutorialObjective();
	public TutorialObjective shoot = new TutorialObjective();
	
	@Override
	public void tick(EntityPlayer player)
	{
		if (!accelerate.get())
		{
			if (mc.gameSettings.keyBindForward.getIsKeyPressed())
			{
				accelerate.complete(7);
			}
		}
		else if (!nitro.get())
		{
			if (mc.gameSettings.keyBindForward.getIsKeyPressed() && (mc.gameSettings.keyBindSprint.getIsKeyPressed() || TFKeyBinds.keyBindingNitro.getIsKeyPressed()))
			{
				nitro.complete(10);
			}
		}
		else if (!shoot.get())
		{
			if (mc.gameSettings.keyBindUseItem.getIsKeyPressed())
			{
				shoot.complete(2);
			}
		}
		else
		{
			TutorialHandler.completeTutorial(EnumTutorialType.JET);
		}
	}

	@Override
	public void render(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
	{
		String forwardKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindForward.getKeyCode());
		String nitroKeyBindName1 = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindSprint.getKeyCode());
		String nitroKeyBindName2 = GameSettings.getKeyDisplayString(TFKeyBinds.keyBindingNitro.getKeyCode());
		String rightClickKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindUseItem.getKeyCode());
		
		if (!accelerate.get())
		{
			drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.accelerate.instructions", EnumChatFormatting.GOLD + forwardKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 40, -1, 1.5F);
			
			if (accelerate.midCompletion())
			{
				drawCenteredScaledString(StatCollector.translateToLocal("tutorial.accelerate.info1_line1"), width / 2, height / 2 + 40, -1, 1.0F);
				drawCenteredScaledString(StatCollector.translateToLocal("tutorial.accelerate.info1_line2"), width / 2, height / 2 + 50, -1, 1.0F);
				
				drawBlinkingBox(4, 15, 204, 14, 0xffff00);
				drawScaledString("<", 210, 17, -1, 1F);
			}
		}
		else if (!nitro.get())
		{
			drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.nitro.instructions", EnumChatFormatting.GOLD + nitroKeyBindName1 + EnumChatFormatting.RESET, EnumChatFormatting.GOLD + nitroKeyBindName2 + EnumChatFormatting.RESET), width / 2, height / 2 - 40, -1, 1.5F);
						
			if (nitro.midCompletion())
			{
				drawCenteredScaledString(StatCollector.translateToLocal("tutorial.nitro.info1"), width / 2, height / 2 + 40, -1, 1.0F);
				
				drawBlinkingBox(4, 2, 204, 14, 0xffff00);
				drawScaledString("<", 210, 5, -1, 1F);
				
				if (player.capabilities.isCreativeMode)
				{
					drawCenteredScaledString(StatCollector.translateToLocal("tutorial.nitro.info2"), width / 2, height / 2 + 50, -1, 1.0F);
				}
			}
		}
		else if (!shoot.get())
		{
			drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.shoot.instructions", EnumChatFormatting.GOLD + rightClickKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 40, -1, 1.5F);
		}
	}
}
