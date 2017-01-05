package fiskfille.tf.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterGroundBridge extends Teleporter
{
    public TeleporterGroundBridge(WorldServer world)
    {
        super(world);
    }

    @Override
    public void placeInPortal(Entity entity, double x, double y, double z, float f)
    {

    }

    @Override
    public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float f)
    {
        return false;
    }

    @Override
    public boolean makePortal(Entity entity)
    {
        return false;
    }

    @Override
    public void removeStalePortalLocations(long l)
    {

    }
}
