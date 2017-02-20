package fiskfille.tf.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.Constants.NBT;
import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.data.tile.TileDataEnergyContainer;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.energon.power.ReceiverHandler;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.helper.TFTileHelper;

public class TileEntityEmbTest extends TileEntityTF implements IEnergyReceiver
{
    public ReceiverHandler receiverHandler = new ReceiverHandler(this);
    public TileDataEnergyContainer data = new TileDataEnergyContainer(32000);

    @Override
    public void updateEntity()
    {
        if (!data.isInitialized())
        {
            data.initialize(this);
        }

        if (!worldObj.isRemote)
        {
            data.serverTick();
        }

        TileData prevData = TFTileHelper.getTileData(new DimensionalCoords(this));

        if (prevData instanceof TileDataEnergyContainer)
        {
            data = new TileDataEnergyContainer((TileDataEnergyContainer) prevData);
        }
    }

    @Override
    public void invalidate()
    {
        super.invalidate();

        if (!worldObj.isRemote)
        {
            data.kill();
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbt)
    {
        if (nbt.hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            NBTTagCompound config = nbt.getCompoundTag("ConfigDataTF");
            data.storage.readFromNBT(config);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbt)
    {
        if (data.storage.getEnergy() > 0)
        {
            NBTTagCompound config = new NBTTagCompound();
            data.storage.writeToNBT(config);
            nbt.setTag("ConfigDataTF", config);
        }
    }

    @Override
    public float receiveEnergy(float amount, boolean simulate)
    {
        return data.storage.add(amount, simulate);
    }

    @Override
    public float extractEnergy(float amount, boolean simulate)
    {
        return data.storage.remove(amount, simulate);
    }

    @Override
    public float getEnergy()
    {
        return data.getEnergy();
    }

    @Override
    public float getMaxEnergy()
    {
        return data.getMaxEnergy();
    }

    @Override
    public float setEnergy(float energy)
    {
        return data.storage.set(energy);
    }

    @Override
    public float getEnergyUsage()
    {
        return data.storage.getUsage();
    }

    @Override
    public void setEnergyUsage(float usage)
    {
        data.storage.setUsage(usage);
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
}
