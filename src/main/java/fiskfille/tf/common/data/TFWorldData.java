package fiskfille.tf.common.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TFWorldData extends WorldSavedData
{
    public static final String KEY = "TF_WORLD_DATA";
    public static Map<Integer, TFWorldData> data;

    private List<ChunkCoordinates> tiles = new ArrayList<ChunkCoordinates>();

    public TFWorldData()
    {
        super(KEY);
    }

    public static TFWorldData load(World world)
    {
        if (!world.isRemote)
        {
            MapStorage storage = world.perWorldStorage;

            TFWorldData data = (TFWorldData) storage.loadData(TFWorldData.class, KEY);

            if (data == null)
            {
                data = new TFWorldData();
                data.markDirty();
                storage.setData(KEY, data);
            }

            TFWorldData.data.put(world.provider.dimensionId, data);

            return data;
        }
        else
        {
            TFWorldData data = new TFWorldData();
            TFWorldData.data.put(world.provider.dimensionId, data);
            return data;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        NBTTagList positionsList = compound.getTagList("TilePositions", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < positionsList.tagCount(); i++)
        {
            NBTTagCompound position = positionsList.getCompoundTagAt(i);

            this.tiles.add(new ChunkCoordinates(position.getInteger("X"), position.getInteger("Y"), position.getInteger("Z")));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        NBTTagList positionsList = new NBTTagList();

        for (ChunkCoordinates coordinates : this.tiles)
        {
            NBTTagCompound position = new NBTTagCompound();
            position.setInteger("X", coordinates.posX);
            position.setInteger("Y", coordinates.posY);
            position.setInteger("Z", coordinates.posZ);

            positionsList.appendTag(compound);
        }

        compound.setTag("TilePositions", compound);
    }

    public void addTile(ChunkCoordinates coordinate)
    {
        this.tiles.add(coordinate);
        this.markDirty();
    }

    public void removeTile(ChunkCoordinates coordinates)
    {
        this.tiles.remove(coordinates);
        this.markDirty();
    }

    public static TFWorldData get(World world)
    {
        TFWorldData data = TFWorldData.data.get(world.provider.dimensionId);

        if (data == null && world.isRemote)
        {
            data = load(world);
        }

        return data;
    }
}
