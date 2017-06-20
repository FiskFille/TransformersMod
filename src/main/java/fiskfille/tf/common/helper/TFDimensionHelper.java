package fiskfille.tf.common.helper;

import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Map;

public class TFDimensionHelper
{
    public static Map<Integer, String> DIMENSION_NAMES = Maps.newHashMap();
    public static Integer[] DIMENSION_IDS = new Integer[0];

    public static void travelToDimension(Entity entity, int targetDimension, Teleporter teleporter)
    {
        if (!entity.world.isRemote && !entity.isDead)
        {
            entity.world.profiler.startSection("changeDimension");
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            int currentDimension = entity.dimension;
            WorldServer oldWorld = server.getWorld(currentDimension);
            WorldServer newWorld = server.getWorld(targetDimension);
            entity.dimension = targetDimension;

            if (currentDimension == 1 && targetDimension == 1)
            {
                newWorld = server.getWorld(0);
                entity.dimension = 0;
            }

            entity.world.removeEntity(entity);
            entity.isDead = false;
            entity.world.profiler.startSection("reposition");
            server.getPlayerList().transferEntityToWorld(entity, currentDimension, oldWorld, newWorld, teleporter);
            entity.world.profiler.endStartSection("reloading");
            Entity newEntity = EntityList.newEntity(entity.getClass(), newWorld);

            if (newEntity != null)
            {
                newEntity.copyLocationAndAnglesFrom(entity);
                copyData(newEntity, entity);

                if (currentDimension == 1 && targetDimension == 1)
                {
                    BlockPos spawnPoint = entity.world.getTopSolidOrLiquidBlock(newWorld.getSpawnPoint());
                    newEntity.setLocationAndAngles(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ(), newEntity.rotationYaw, newEntity.rotationPitch);
                }

                newWorld.spawnEntity(newEntity);
            }

            entity.isDead = true;
            entity.world.profiler.endSection();
            oldWorld.resetUpdateEntityTick();
            newWorld.resetUpdateEntityTick();
            entity.world.profiler.endSection();
        }
    }

    private static void copyData(Entity to, Entity from)
    {
        NBTTagCompound tag = from.writeToNBT(new NBTTagCompound());
        tag.removeTag("Dimension");
        to.readFromNBT(tag);
        to.timeUntilPortal = from.timeUntilPortal;
    }

    public static String getDimensionName(int id)
    {
        if (DIMENSION_NAMES.containsKey(id))
        {
            return DIMENSION_NAMES.get(id);
        }

        return "Unknown";
    }
}
