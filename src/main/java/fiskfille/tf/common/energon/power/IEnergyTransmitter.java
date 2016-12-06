package fiskfille.tf.common.energon.power;

import net.minecraft.util.Vec3;

public interface IEnergyTransmitter extends IEnergyContainer
{
    ReceiverHandler getReceiverHandler();

    TransmissionHandler getTransmissionHandler();

    float getTransmissionRate();

    float getRange();

    Vec3 getEnergyOutputOffset();
}
