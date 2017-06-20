package fiskfille.tf.common.event;

import fiskfille.tf.common.capability.TFCapabilities;
import fiskfille.tf.common.capability.TFEntityData;
import fiskfille.tf.common.capability.TFPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonEventHandler
{
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        Entity entity = event.getObject();

        if (entity instanceof EntityPlayer)
        {
            event.addCapability(TFCapabilities.PLAYER_DATA_ID, new TFPlayerData.Implementation());
        }

        if (entity instanceof EntityLivingBase)
        {
            event.addCapability(TFCapabilities.ENTITY_DATA_ID, new TFEntityData.Implementation());
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        TFEntityData data = event.getEntity().getCapability(TFCapabilities.ENTITY_DATA_CAP, null);

        if (data != null)
        {
            data.decrementGroundBridgeCooldown();
        }
    }
}
