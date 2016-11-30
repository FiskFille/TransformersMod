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
import fiskfille.tf.common.network.MessageControlPanel;
import fiskfille.tf.common.network.MessageControlPanelSetConfig;
import fiskfille.tf.common.network.MessageGroundBridgeTeleport;
import fiskfille.tf.common.network.MessageHandleStealthTransformation;
import fiskfille.tf.common.network.MessageHandleTransformation;
import fiskfille.tf.common.network.MessageLaserShoot;
import fiskfille.tf.common.network.MessageOpenGui;
import fiskfille.tf.common.network.MessageOpenGuiSimple;
import fiskfille.tf.common.network.MessagePlayerJoin;
import fiskfille.tf.common.network.MessageSendFlying;
import fiskfille.tf.common.network.MessageSetReceivers;
import fiskfille.tf.common.network.MessageTransformDisplayStation;
import fiskfille.tf.common.network.MessageUpdateEnergyState;
import fiskfille.tf.common.network.MessageUpdateFluidState;
import fiskfille.tf.common.network.MessageVehicleNitro;
import fiskfille.tf.common.network.MessageVehicleShoot;

public class TFNetworkManager
{
    public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;

    public static void registerPackets()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("transformersMod");

        registerPacket(MessageHandleTransformation.Handler.class, MessageHandleTransformation.class);
        registerPacket(MessageHandleStealthTransformation.Handler.class, MessageHandleStealthTransformation.class);
        registerPacket(MessagePlayerJoin.Handler.class, MessagePlayerJoin.class);
        registerPacket(MessageVehicleShoot.Handler.class, MessageVehicleShoot.class);
        registerPacket(MessageCloudtrapJetpack.Handler.class, MessageCloudtrapJetpack.class);
        registerPacket(MessageBroadcastState.Handler.class, MessageBroadcastState.class);
        registerPacket(MessageVehicleNitro.Handler.class, MessageVehicleNitro.class);
        registerPacket(MessageLaserShoot.Handler.class, MessageLaserShoot.class);
        registerPacket(MessageSendFlying.Handler.class, MessageSendFlying.class);
        registerPacket(MessageTransformDisplayStation.Handler.class, MessageTransformDisplayStation.class);
        registerPacket(MessageColorArmor.Handler.class, MessageColorArmor.class);
        registerPacket(MessageControlPanel.Handler.class, MessageControlPanel.class);
        registerPacket(MessageControlPanelSetConfig.Handler.class, MessageControlPanelSetConfig.class);
        registerPacket(MessageOpenGuiSimple.Handler.class, MessageOpenGuiSimple.class);
        registerPacket(MessageOpenGui.Handler.class, MessageOpenGui.class);
        registerPacket(MessageGroundBridgeTeleport.Handler.class, MessageGroundBridgeTeleport.class);
        registerPacket(MessageUpdateEnergyState.Handler.class, MessageUpdateEnergyState.class);
        registerPacket(MessageSetReceivers.Handler.class, MessageSetReceivers.class);
        registerPacket(MessageClosePortal.Handler.class, MessageClosePortal.class);
        registerPacket(MessageUpdateFluidState.Handler.class, MessageUpdateFluidState.class);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.SERVER);
    }
}
