package fiskfille.tf.common.capability;

import com.google.common.collect.Maps;
import fiskfille.tf.common.data.TFData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import java.util.Map;

public interface TFPlayerData extends ICapabilitySerializable<NBTTagCompound>
{
    void copy(TFPlayerData props);

    <T> void putData(TFData<T> type, T value);

    <T> T getData(TFData<T> type);

    void setData(Map<TFData, Object> playerData);

    Map<TFData, Object> getData();

    class Implementation implements TFPlayerData
    {
        private Map<TFData, Object> data = this.createDefaultData();

        @Override
        public void copy(TFPlayerData props)
        {
            this.data = props.getData();
        }

        @Override
        public <T> void putData(TFData<T> type, T value)
        {
            this.data.put(type, value);
        }

        @Override
        public <T> T getData(TFData<T> type)
        {
            return (T) this.data.get(type);
        }

        @Override
        public void setData(Map<TFData, Object> data)
        {
            this.data = data;
        }

        @Override
        public Map<TFData, Object> getData()
        {
            return this.data;
        }

        private Map<TFData, Object> createDefaultData()
        {
            Map<TFData, Object> map = Maps.newHashMap();

            for (TFData data : TFData.VALUES)
            {
                map.put(data, data.defaultValue);
            }

            return map;
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return capability == TFCapabilities.PLAYER_DATA_CAP;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            return this.hasCapability(capability, facing) ? TFCapabilities.PLAYER_DATA_CAP.cast(this) : null;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setBoolean("Saved", true);

            TFData.writeToNBT(compound, this.data);

            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound compound)
        {
            if (compound.getBoolean("Saved"))
            {
                this.data = TFData.readFromNBT(compound, this.data);
            }
        }
    }
}
