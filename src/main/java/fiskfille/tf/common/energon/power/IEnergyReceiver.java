package fiskfille.tf.common.energon.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public interface IEnergyReceiver extends IEnergyContainer
{
    ReceiverHandler getReceiverHandler();

    boolean canReceiveEnergy(TileEntity from);
    
    int getMapColor();

    Vec3 getEnergyInputOffset();
}
