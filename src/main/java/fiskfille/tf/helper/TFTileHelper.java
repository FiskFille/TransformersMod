package fiskfille.tf.helper;

import java.util.Map;

import net.minecraft.tileentity.TileEntity;

import com.google.common.collect.Maps;

import fiskfille.tf.common.data.tile.TileData;
import fiskfille.tf.common.item.ItemCSD.DimensionalCoords;
import fiskfille.tf.common.tileentity.IMultiTile;

public class TFTileHelper
{
    private static Map<DimensionalCoords, TileData> tileData = Maps.newHashMap();

    public static TileData getTileData(DimensionalCoords coords)
    {
        return tileData.get(coords);
    }
    
    public static Map<DimensionalCoords, TileData> getTileData()
    {
        return tileData;
    }
    
    public static void clearTileData()
    {
        for (Map.Entry<DimensionalCoords, TileData> e : tileData.entrySet())
        {
            e.getValue().kill();
        }
    }

    public static void putServerData(TileData data)
    {
        putServerData(data.getCoords(), data);
    }

    public static void putServerData(DimensionalCoords coords, TileData data)
    {
        if (data == null)
        {
            tileData.remove(coords);
            return;
        }
        
        tileData.put(coords, data);
    }

    public static int[] getTileBaseOffsets(TileEntity tile, int metadata)
    {
        if (tile instanceof IMultiTile)
        {
            return ((IMultiTile) tile).getBaseOffsets(metadata);
        }

        return new int[] {0, 0, 0};
    }

    public static <T extends TileEntity> T getTileBase(T tile)
    {
        int[] offsets = getTileBaseOffsets(tile, tile != null ? tile.getBlockMetadata() : 0);

        if (offsets[0] != 0 || offsets[1] != 0 || offsets[2] != 0)
        {
            return (T) tile.getWorldObj().getTileEntity(tile.xCoord + offsets[0], tile.yCoord + offsets[1], tile.zCoord + offsets[2]);
        }

        return tile;
    }
}
