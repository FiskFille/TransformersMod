package fiskfille.tf.common.data;

import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.google.common.collect.Maps;

public class TFPlayerData implements IExtendedEntityProperties
{
    public static final String IDENTIFIER = "TFPlayer";

    public Map<TFData, Object> data = createDataMap();

    public static TFPlayerData getData(EntityPlayer player)
    {
        return (TFPlayerData) player.getExtendedProperties(IDENTIFIER);
    }

    private Map<TFData, Object> createDataMap()
    {
        Map<TFData, Object> map = Maps.newHashMap();

        for (TFData data : TFData.VALUES)
        {
            map.put(data, data.defaultValue);
        }

        return map;
    }

    public void onUpdate()
    {
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setBoolean("Saved", true);

        TFData.writeToNBT(nbttagcompound, data);
        compound.setTag(IDENTIFIER, nbttagcompound);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound nbttagcompound = compound.getCompoundTag(IDENTIFIER);

        if (nbttagcompound.getBoolean("Saved"))
        {
            data = TFData.readFromNBT(nbttagcompound, data);
        }
    }

    @Override
    public void init(Entity entity, World world)
    {
        if (entity instanceof EntityPlayer)
        {
        }
    }

    public void copy(TFPlayerData props)
    {
        data = props.data;
    }

    public <T> void putData(TFData<T> type, T value)
    {
        data.put(type, value);
    }

    public <T> T getData(TFData<T> type)
    {
        return (T) data.get(type);
    }
}
