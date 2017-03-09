package fiskfille.tf.common.energon.power;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;
import fiskfille.tf.helper.TFEnergyHelper;

public class EnergyStorageRelay extends EnergyStorage
{
    protected final TileEntityRelayTower relay;

    public EnergyStorageRelay(TileEntityRelayTower tile)
    {
        super(0);
        relay = tile;
    }

    @Override
    public EnergyStorageRelay copy()
    {
        EnergyStorageRelay storage = new EnergyStorageRelay(relay);

        return storage;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
    }

    @Override
    public float remove(float amount, boolean simulate)
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiversToPower(relay);
        float removed = 0;

        for (ReceiverEntry entry : receivers)
        {
            float f = entry.getReceiver().extractEnergy(amount / receivers.size(), simulate);
            removed += f;
            
            relay.netEnergyTransfer.put(entry.getCoords(), relay.getNetTransfer(entry.getCoords()) - f);
        }

        return removed;
    }

    @Override
    public float add(float amount, boolean simulate)
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiversToPower(relay);
        float added = 0;

        for (ReceiverEntry entry : receivers)
        {
            float f = entry.getReceiver().receiveEnergy(amount / receivers.size(), simulate);
            added += f;
            
            relay.netEnergyTransfer.put(entry.getCoords(), relay.getNetTransfer(entry.getCoords()) + f);
        }

        return added;
    }

    @Override
    public float getEnergy()
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiverDescendants(relay);
        float energy = 0;

        for (ReceiverEntry entry : receivers)
        {
            energy += entry.getReceiver().getEnergy();
        }

        return energy;
    }

    @Override
    public float getMaxEnergy()
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiverDescendants(relay);
        float maxEnergy = 0;

        for (ReceiverEntry entry : receivers)
        {
            maxEnergy += entry.getReceiver().getMaxEnergy();
        }

        return maxEnergy;
    }

    @Override
    public float set(float amount)
    {
        return 0;
    }

    @Override
    public void setUsage(float usage)
    {
        energyUsage = usage;
        lastEnergy = getEnergy();
    }

    @Override
    public float getUsage()
    {
        return energyUsage;
    }

    @Override
    public float calculateUsage()
    {
        float energy = getEnergy();
        energyUsage = energy - lastEnergy;
        lastEnergy = energy;
        
        return energyUsage;
    }
}
