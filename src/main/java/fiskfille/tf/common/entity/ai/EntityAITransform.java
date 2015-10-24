package fiskfille.tf.common.entity.ai;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.entity.EntityTransformer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class EntityAITransform extends EntityAIBase
{
    private EntityTransformer transformer;
    private Class<? extends Entity> enemy;
    private World world;

    private Entity closestEnemy;

    private int distance;

    public EntityAITransform(EntityTransformer transformer, Class<? extends EntityLivingBase> enemy, int distance)
    {
        this.transformer = transformer;
        this.enemy = enemy;
        world = transformer.worldObj;
        this.distance = distance;
    }

    @Override
    public boolean shouldExecute()
    {
        return true;
    }

    private boolean hasEnemy()
    {
        List entities = world.getEntitiesWithinAABBExcludingEntity(transformer, entityBoundingBox(transformer, distance, distance, distance));

        boolean hasEnemy = false;

        for (Object object : entities)
        {
            if (enemy.isAssignableFrom(object.getClass()) && object.getClass() != transformer.getClass())
            {
                hasEnemy = true;
                closestEnemy = (Entity) object;
                break;
            }
        }

        return hasEnemy;
    }

    private AxisAlignedBB entityBoundingBox(Entity entity, double x, double y, double z)
    {
        double dX = x / 2;
        double dY = y / 2;
        double dZ = z / 2;

        double posX = entity.posX;
        double posY = entity.posY;
        double posZ = entity.posZ;

        return AxisAlignedBB.getBoundingBox(posX - dX, posY - dY, posZ - dZ, posX + dX, posY + dY, posZ + dZ);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return transformer.isEntityAlive();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask()
    {
        closestEnemy = null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        boolean hasEnemy = hasEnemy();

        if (hasEnemy != transformer.isTransformed())
        {
            String end = hasEnemy ? "vehicle" : "robot";
            world.playSoundAtEntity(transformer, TransformersMod.modid + ":transform_" + end, 1.0F, 1.0F);
        }

        transformer.setInVehicleMode(hasEnemy);
    }
}
