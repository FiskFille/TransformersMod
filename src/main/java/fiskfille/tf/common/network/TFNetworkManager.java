package fiskfille.tf.common.network;

import fiskfille.tf.TransformersMod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class TFNetworkManager
{
    public static SimpleNetworkWrapper WRAPPER;
    private static int nextId = 0;

    public static void register()
    {
        WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(TransformersMod.MODID);

        registerPacket(MessageSetPlayerData.Handler.class, MessageSetPlayerData.class);
        registerPacket(MessagePlayerData.Handler.class, MessagePlayerData.class);
        registerPacket(MessagePlayerJoin.Handler.class, MessagePlayerJoin.class);
        registerPacket(MessageBroadcastState.Handler.class, MessageBroadcastState.class);
//        registerPacket(MessageVehicleShoot.Handler.class, MessageVehicleShoot.class);
//        registerPacket(MessageCloudtrapJetpack.Handler.class, MessageCloudtrapJetpack.class);
//        registerPacket(MessageLaserShoot.Handler.class, MessageLaserShoot.class);
        registerPacket(MessageSendFlying.Handler.class, MessageSendFlying.class);
//        registerPacket(MessageColorArmor.Handler.class, MessageColorArmor.class);
//        registerPacket(MessageControlPanelSetConfig.Handler.class, MessageControlPanelSetConfig.class);
//        registerPacket(MessageGroundBridgeTeleport.Handler.class, MessageGroundBridgeTeleport.class);
//        registerPacket(MessageClosePortal.Handler.class, MessageClosePortal.class);
//        registerPacket(MessageOpenGui.Handler.class, MessageOpenGui.class);
//        registerPacket(MessageTileTrigger.Handler.class, MessageTileTrigger.class);
//        registerPacket(MessageSetTileData.Handler.class, MessageSetTileData.class);
//        registerPacket(MessageConnectReceiver.Handler.class, MessageConnectReceiver.class);
//        registerPacket(MessageUpdateArmor.Handler.class, MessageUpdateArmor.class);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        WRAPPER.registerMessage(messageHandler, requestMessageType, nextId++, Side.CLIENT);
        WRAPPER.registerMessage(messageHandler, requestMessageType, nextId++, Side.SERVER);
    }
}
