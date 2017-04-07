package fiskfille.tf.common.network.base;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.common.network.MessageBroadcastState;
import fiskfille.tf.common.network.MessageClosePortal;
import fiskfille.tf.common.network.MessageCloudtrapJetpack;
import fiskfille.tf.common.network.MessageColorArmor;
import fiskfille.tf.common.network.MessageConnectReceiver;
import fiskfille.tf.common.network.MessageControlPanelSetConfig;
import fiskfille.tf.common.network.MessageGroundBridgeTeleport;
import fiskfille.tf.common.network.MessageLaserShoot;
import fiskfille.tf.common.network.MessageOpenGui;
import fiskfille.tf.common.network.MessagePlayerData;
import fiskfille.tf.common.network.MessagePlayerJoin;
import fiskfille.tf.common.network.MessageSendFlying;
import fiskfille.tf.common.network.MessageSetPlayerData;
import fiskfille.tf.common.network.MessageSetTileData;
import fiskfille.tf.common.network.MessageTileTrigger;
import fiskfille.tf.common.network.MessageVehicleShoot;

public class TFNetworkManager
{
    public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;

    public static void registerPackets()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("transformersMod");

        registerPacket(MessageSetPlayerData.Handler.class, MessageSetPlayerData.class);
        registerPacket(MessagePlayerData.Handler.class, MessagePlayerData.class);
        registerPacket(MessagePlayerJoin.Handler.class, MessagePlayerJoin.class);
        registerPacket(MessageBroadcastState.Handler.class, MessageBroadcastState.class);
        registerPacket(MessageVehicleShoot.Handler.class, MessageVehicleShoot.class);
        registerPacket(MessageCloudtrapJetpack.Handler.class, MessageCloudtrapJetpack.class);
        registerPacket(MessageLaserShoot.Handler.class, MessageLaserShoot.class);
        registerPacket(MessageSendFlying.Handler.class, MessageSendFlying.class);
        registerPacket(MessageColorArmor.Handler.class, MessageColorArmor.class);
        registerPacket(MessageControlPanelSetConfig.Handler.class, MessageControlPanelSetConfig.class);
        registerPacket(MessageGroundBridgeTeleport.Handler.class, MessageGroundBridgeTeleport.class);
        registerPacket(MessageClosePortal.Handler.class, MessageClosePortal.class);
        registerPacket(MessageOpenGui.Handler.class, MessageOpenGui.class);
        registerPacket(MessageTileTrigger.Handler.class, MessageTileTrigger.class);
        registerPacket(MessageSetTileData.Handler.class, MessageSetTileData.class);
        registerPacket(MessageConnectReceiver.Handler.class, MessageConnectReceiver.class);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.SERVER);
    }
}
