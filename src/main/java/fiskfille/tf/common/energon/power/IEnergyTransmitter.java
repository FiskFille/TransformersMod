package fiskfille.tf.common.energon.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public interface IEnergyTransmitter extends IEnergyContainer
{
    ReceiverHandler getReceiverHandler();

    TransmissionHandler getTransmissionHandler();

    boolean isPowering(TargetReceiver receiver);

    boolean canPowerReach(TargetReceiver receiver);

    float getRange();

    Vec3 getEnergyOutputOffset();

    boolean isPowering(TileEntity tile);
}
