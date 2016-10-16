package fiskfille.tf.common.energon.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public interface IEnergyTransmitter extends IEnergyContainer
{
    ReceiverHandler getReceiverHandler();

    boolean isPowering(TileEntity tile);

    boolean canPowerReach(TileEntity tile);

    float getRange();

    Vec3 getEnergyOutputOffset();
}
