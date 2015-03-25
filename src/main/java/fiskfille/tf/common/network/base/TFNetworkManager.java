package fiskfille.tf.common.network.base;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.common.network.MessageBroadcastState;
import fiskfille.tf.common.network.MessageCloudtrapJetpack;
import fiskfille.tf.common.network.MessageHandleStealthTransformation;
import fiskfille.tf.common.network.MessageHandleTransformation;
import fiskfille.tf.common.network.MessageSendFlying;
import fiskfille.tf.common.network.MessageSyncStates;
import fiskfille.tf.common.network.MessageTransformersAction;
import fiskfille.tf.common.network.MessageVehicleNitro;
import fiskfille.tf.common.network.MessageVurpSniperShoot;

public class TFNetworkManager
{
    public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;
    
    public static void registerPackets()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("transformersMod");
        
        registerPacket(MessageHandleTransformation.Handler.class, MessageHandleTransformation.class);
        registerPacket(MessageHandleStealthTransformation.Handler.class, MessageHandleStealthTransformation.class);
        registerPacket(MessageSyncStates.Handler.class, MessageSyncStates.class);
        registerPacket(MessageTransformersAction.Handler.class, MessageTransformersAction.class);
        registerPacket(MessageCloudtrapJetpack.Handler.class, MessageCloudtrapJetpack.class);
        registerPacket(MessageBroadcastState.Handler.class, MessageBroadcastState.class);
        registerPacket(MessageVehicleNitro.Handler.class, MessageVehicleNitro.class);
        registerPacket(MessageVurpSniperShoot.Handler.class, MessageVurpSniperShoot.class);
        registerPacket(MessageSendFlying.Handler.class, MessageSendFlying.class);
    }
    
    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.SERVER);
    }
}
