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

    public EnergyStorageRelay copy()
    {
        EnergyStorageRelay storage = new EnergyStorageRelay(relay);
        
        return storage;
    }
    
    public void toBytes(ByteBuf buf)
    {
    }
    
    public void fromBytes(ByteBuf buf)
    {
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
    }

    public float remove(float amount, boolean simulate)
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiversToPower(relay);
        float removed = 0;
        
        for (ReceiverEntry entry : receivers)
        {
            removed += entry.getReceiver().extractEnergy(amount / receivers.size(), simulate);
        }
        
        return removed;
    }

    public float add(float amount, boolean simulate)
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiversToPower(relay);
        float added = 0;
        
        for (ReceiverEntry entry : receivers)
        {
            added += entry.getReceiver().receiveEnergy(amount / receivers.size(), simulate);
        }
        
        return added;
    }

    public float getEnergy()
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiversToPower(relay);
        float energy = 0;
        
        for (ReceiverEntry entry : receivers)
        {
            energy += entry.getReceiver().getEnergy();
        }
        
        return energy;
    }

    public float getMaxEnergy()
    {
        List<ReceiverEntry> receivers = TFEnergyHelper.getReceiversToPower(relay);
        float maxEnergy = 0;
        
        for (ReceiverEntry entry : receivers)
        {
            maxEnergy += entry.getReceiver().getMaxEnergy();
        }
        
        return maxEnergy;
    }

    public float set(float amount)
    {
        return 0;
    }

    public void setUsage(float usage)
    {
    }

    public float getUsage()
    {
        return 0;
    }

    public float calculateUsage()
    {
        return 0;
    }
}
