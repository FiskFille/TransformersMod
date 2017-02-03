package fiskfille.tf.helper;

import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import com.google.common.collect.Maps;

public class TFDimensionHelper
{
    public static Map<Integer, String> dimensionNames = Maps.newHashMap();
    public static Integer[] dimensionIDs = new Integer[0];

    public static void travelToDimension(Entity entity, int dimension, Teleporter teleporter)
    {
        if (!entity.worldObj.isRemote && !entity.isDead)
        {
            entity.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer server = MinecraftServer.getServer();
            int j = entity.dimension;
            WorldServer worldserver = server.worldServerForDimension(j);
            WorldServer worldserver1 = server.worldServerForDimension(dimension);
            entity.dimension = dimension;

            if (j == 1 && dimension == 1)
            {
                worldserver1 = server.worldServerForDimension(0);
                entity.dimension = 0;
            }

            entity.worldObj.removeEntity(entity);
            entity.isDead = false;
            entity.worldObj.theProfiler.startSection("reposition");
            server.getConfigurationManager().transferEntityToWorld(entity, j, worldserver, worldserver1, teleporter);
            entity.worldObj.theProfiler.endStartSection("reloading");
            Entity entity1 = EntityList.createEntityByName(EntityList.getEntityString(entity), worldserver1);

            if (entity1 != null)
            {
                entity1.copyDataFrom(entity, true);

                if (j == 1 && dimension == 1)
                {
                    ChunkCoordinates chunkcoordinates = worldserver1.getSpawnPoint();
                    chunkcoordinates.posY = entity.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
                    entity1.setLocationAndAngles(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, entity1.rotationYaw, entity1.rotationPitch);
                }

                worldserver1.spawnEntityInWorld(entity1);
            }

            entity.isDead = true;
            entity.worldObj.theProfiler.endSection();
            worldserver.resetUpdateEntityTick();
            worldserver1.resetUpdateEntityTick();
            entity.worldObj.theProfiler.endSection();
        }
    }

    public static String getDimensionName(int id)
    {
        if (dimensionNames.containsKey(id))
        {
            return dimensionNames.get(id);
        }

        return "Unknown";
    }
}
