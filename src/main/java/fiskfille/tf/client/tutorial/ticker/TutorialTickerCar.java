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

public class TutorialTickerCar extends TutorialTicker
{
    public TutorialObjective accelerate = new TutorialObjective();
    public TutorialObjective nitro = new TutorialObjective();
    public TutorialObjective stealthMode = new TutorialObjective();
    public TutorialObjective strafe = new TutorialObjective();
    public TutorialObjective jump = new TutorialObjective();
    public TutorialObjective shoot = new TutorialObjective();

    @Override
    public void tick(EntityPlayer player)
    {
        if (!accelerate.get())
        {
            if (mc.gameSettings.keyBindForward.getIsKeyPressed())
            {
                accelerate.complete(12);
            }
        }
        else if (!nitro.get())
        {
            if (mc.gameSettings.keyBindForward.getIsKeyPressed() && (mc.gameSettings.keyBindSprint.getIsKeyPressed() || TFKeyBinds.keyBindingNitro.getIsKeyPressed()))
            {
                nitro.complete(10);
            }
        }
        else if (!stealthMode.get() || !strafe.get() || !jump.get())
        {
            if (TFKeyBinds.keyBindingStealthMode.getIsKeyPressed())
            {
                stealthMode.complete(0.25F);
            }

            if (stealthMode.get())
            {
                if (mc.gameSettings.keyBindRight.getIsKeyPressed() || mc.gameSettings.keyBindLeft.getIsKeyPressed())
                {
                    strafe.complete(0.25F);
                }

                if (strafe.get())
                {
                    if (mc.gameSettings.keyBindJump.getIsKeyPressed())
                    {
                        jump.complete(7);
                    }
                }
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
            TutorialHandler.completeTutorial(EnumTutorialType.CAR);
        }
    }

    @Override
    public void render(RenderGameOverlayEvent.Pre event, int width, int height, EntityPlayer player)
    {
        String forwardKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindForward.getKeyCode());
        String backKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindBack.getKeyCode());
        String rightKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindRight.getKeyCode());
        String leftKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindLeft.getKeyCode());
        String nitroKeyBindName1 = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindSprint.getKeyCode());
        String nitroKeyBindName2 = GameSettings.getKeyDisplayString(TFKeyBinds.keyBindingNitro.getKeyCode());
        String rightClickKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindUseItem.getKeyCode());
        String stealthModeKeyBindName = GameSettings.getKeyDisplayString(TFKeyBinds.keyBindingStealthMode.getKeyCode());
        String jumpKeyBindName = GameSettings.getKeyDisplayString(mc.gameSettings.keyBindJump.getKeyCode());

        if (!accelerate.get())
        {
            drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.accelerate.instructions", EnumChatFormatting.GOLD + forwardKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 53, -1, 1.5F);
            drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.accelerate.info2", EnumChatFormatting.GOLD + backKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 40, -1, 1.0F);

            if (accelerate.midCompletion())
            {
                drawCenteredScaledString(StatCollector.translateToLocal("tutorial.accelerate.info1_line1"), width / 2, height / 2 + 30, -1, 1.0F);
                drawCenteredScaledString(StatCollector.translateToLocal("tutorial.accelerate.info1_line2"), width / 2, height / 2 + 40, -1, 1.0F);
                drawCenteredScaledString(StatCollector.translateToLocal("tutorial.accelerate.info3_line1"), width / 2, height / 2 + 50, -1, 1.0F);
                drawCenteredScaledString(StatCollector.translateToLocal("tutorial.accelerate.info3_line2"), width / 2, height / 2 + 60, -1, 1.0F);

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
        else if (!stealthMode.get() || !strafe.get() || !jump.get())
        {
            int yOffset = 0;
            float scale = 1.5F;

            yOffset = stealthMode.get() ? strafe.get() ? -15 : -10 : 0;
            scale = stealthMode.get() ? strafe.get() ? 0.5F : 1.0F : 1.5F;
            drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.stealthmode.instructions", EnumChatFormatting.GOLD + stealthModeKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 40 + yOffset, -1, scale);

            if (stealthMode.get())
            {
                yOffset = strafe.get() ? -10 : 0;
                scale = strafe.get() ? 1.0F : 1.5F;
                drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.stealthmode.strafe.instructions", EnumChatFormatting.GOLD + rightKeyBindName + EnumChatFormatting.RESET, EnumChatFormatting.GOLD + leftKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 40 + yOffset, -1, scale);

                if (strafe.get())
                {
                    yOffset = 0;
                    scale = 1.5F;
                    drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.stealthmode.jump.instructions", EnumChatFormatting.GOLD + jumpKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 40 + yOffset, -1, scale);

                    if (jump.midCompletion())
                    {
                        drawCenteredScaledString(StatCollector.translateToLocal("tutorial.stealthmode.jump.info1_line1"), width / 2, height / 2 + 40, -1, 1.0F);
                        drawCenteredScaledString(StatCollector.translateToLocal("tutorial.stealthmode.jump.info1_line2"), width / 2, height / 2 + 50, -1, 1.0F);
                    }
                }
            }
        }
        else if (!shoot.get())
        {
            drawCenteredScaledString(StatCollector.translateToLocalFormatted("tutorial.shoot.instructions", EnumChatFormatting.GOLD + rightClickKeyBindName + EnumChatFormatting.RESET), width / 2, height / 2 - 40, -1, 1.5F);
        }
    }
}
