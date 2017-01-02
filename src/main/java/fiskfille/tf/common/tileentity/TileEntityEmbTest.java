package fiskfille.tf.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.Constants.NBT;
import cpw.mods.fml.common.network.NetworkRegistry;
import fiskfille.tf.common.energon.power.EnergyStorage;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.network.MessageUpdateEnergyState;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.helper.TFEnergyHelper;

public class TileEntityEmbTest extends TileEntityTF implements IEnergyReceiver
{
    public ReceiverHandler receiverHandler = new ReceiverHandler(this);

    public EnergyStorage storage = new EnergyStorage(32000);

    public float lastUsage;

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        receiverHandler.onUpdate(worldObj);

        if (worldObj.isRemote)
        {
            TFEnergyHelper.applyClientEnergyUsage(this);
        }
        else
        {
            float usage = storage.calculateUsage();

            if (Math.abs(usage - lastUsage) > 0.001F)
            {
                updateClientEnergy();
            }

            lastUsage = usage;
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        receiverHandler.readFromNBT(nbt);
        
        if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            storage.readFromNBT(config);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        receiverHandler.writeToNBT(nbt);
        
        if (storage.getEnergy() > 0)
        {
            NBTTagCompound config = new NBTTagCompound();
            storage.writeToNBT(config);
            nbt.setTag("ConfigDataTF", config);
        }
    }

    @Override
    public float receiveEnergy(float amount)
    {
        return storage.add(amount);
    }

    @Override
    public float extractEnergy(float amount)
    {
        return storage.remove(amount);
    }

    @Override
    public float getEnergy()
    {
        return storage.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return storage.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        storage.setUsage(usage);
    }

    @Override
    public ReceiverHandler getReceiverHandler()
    {
        return receiverHandler;
    }

    @Override
    public boolean canReceiveEnergy(TileEntity from)
    {
        return true;
    }

    @Override
    public Vec3 getEnergyInputOffset()
    {
        return Vec3.createVectorHelper(0, 0.5D, 0);
    }
    
    @Override
    public int getMapColor()
    {
        return 0xFF0000;
    }

    @Override
    public void updateClientEnergy()
    {
        NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord + 0.5F, this.yCoord, this.zCoord + 0.5F, 128);
        TFNetworkManager.networkWrapper.sendToAllAround(new MessageUpdateEnergyState(this), target);
    }
}
