package fiskfille.tf.common.packet.base;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.common.packet.PacketBroadcastState;
import fiskfille.tf.common.packet.PacketCloudtrapJetpack;
import fiskfille.tf.common.packet.PacketHandleStealthTransformation;
import fiskfille.tf.common.packet.PacketHandleTransformation;
import fiskfille.tf.common.packet.PacketSendFlying;
import fiskfille.tf.common.packet.PacketTransformersAction;
import fiskfille.tf.common.packet.PacketSyncStates;
import fiskfille.tf.common.packet.PacketVehicleNitro;
import fiskfille.tf.common.packet.PacketVurpSniperShoot;

public class TFPacketManager
{
    public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;
    
    public static void registerPackets()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("transformersMod");
        
        registerPacket(PacketHandleTransformation.Handler.class, PacketHandleTransformation.class);
        registerPacket(PacketHandleStealthTransformation.Handler.class, PacketHandleStealthTransformation.class);
        registerPacket(PacketSyncStates.Handler.class, PacketSyncStates.class);
        registerPacket(PacketTransformersAction.Handler.class, PacketTransformersAction.class);
        registerPacket(PacketCloudtrapJetpack.Handler.class, PacketCloudtrapJetpack.class);
        registerPacket(PacketBroadcastState.Handler.class, PacketBroadcastState.class);
        registerPacket(PacketVehicleNitro.Handler.class, PacketVehicleNitro.class);
        registerPacket(PacketVurpSniperShoot.Handler.class, PacketVurpSniperShoot.class);
        registerPacket(PacketSendFlying.Handler.class, PacketSendFlying.class);
    }
    
    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.SERVER);
    }
}
