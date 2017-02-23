package fiskfille.tf.common.data;

import java.util.Arrays;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants.NBT;

import com.google.common.collect.Maps;

import fiskfille.tf.common.item.ItemMetaBasic;

public class TFWorldData extends WorldSavedData
{
    public static final String KEY = "TFWorld";
    public static TFWorldData instance;
    
    public Map<String, Integer> subItems = Maps.newHashMap();

    public TFWorldData(String s)
    {
        super(s);
    }

    public static TFWorldData load(World world)
    {
        if (!world.isRemote)
        {
            MapStorage storage = world.perWorldStorage;
            TFWorldData data = (TFWorldData) storage.loadData(TFWorldData.class, KEY);

            if (data == null)
            {
                data = new TFWorldData(KEY);
                data.markDirty();
                storage.setData(KEY, data);
            }

            return instance = data;
        }
        else
        {
            return instance = new TFWorldData(KEY);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        NBTTagList list = nbt.getTagList("SubItems", NBT.TAG_COMPOUND);
        
        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = list.getCompoundTagAt(i);
            String name = nbttagcompound.getString("name");
            
            if (Arrays.asList(ItemMetaBasic.iconNames).contains(name))
            {
                subItems.put(name, nbttagcompound.getInteger("id"));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagList list = new NBTTagList();

        for (Map.Entry<String, Integer> e : subItems.entrySet())
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setInteger("id", e.getValue());
            nbttagcompound.setString("name", e.getKey());
            list.appendTag(nbttagcompound);
        }
        
        nbt.setTag("SubItems", list);
    }

    public static TFWorldData get(World world)
    {
        if (instance == null && world.isRemote)
        {
            instance = load(world);
        }

        return instance;
    }
    
    public int getNextAvailableId()
    {
        int id = 0;
        
        while (subItems.containsValue(id))
        {
            ++id;
        }
        
        return id;
    }
}
