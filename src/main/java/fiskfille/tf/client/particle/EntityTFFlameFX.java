package fiskfille.tf.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class EntityTFFlameFX extends EntityFX
{
    /**
     * the scale of the flame FX
     */
    private float flameScale;

    public EntityTFFlameFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.motionX = this.motionX * 0.009999999776482582D + motionX;
        this.motionY = this.motionY * 0.009999999776482582D + motionY;
        this.motionZ = this.motionZ * 0.009999999776482582D + motionZ;
        double d6 = x + (rand.nextFloat() - rand.nextFloat()) * 0.05F;
        d6 = y + (rand.nextFloat() - rand.nextFloat()) * 0.05F;
        d6 = z + (rand.nextFloat() - rand.nextFloat()) * 0.05F;
        flameScale = particleScale;
        particleRed = particleGreen = particleBlue = 1.0F;
        particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        noClip = false;
        setParticleTextureIndex(48);
    }

    public void renderParticle(Tessellator tesselator, float x, float y, float z, float r, float g, float b)
    {
        float f6 = (particleAge + x) / particleMaxAge;
        particleScale = flameScale * (1.0F - f6 * f6 * 0.5F);
        super.renderParticle(tesselator, x, y, z, r, g, b);
    }

    public int getBrightnessForRender(float p_70070_1_)
    {
        float f1 = (particleAge + p_70070_1_) / particleMaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }
        else if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        int i = super.getBrightnessForRender(p_70070_1_);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int) (f1 * 15.0F * 16.0F);

        if (j > 240)
        {
            j = 240;
        }

        return j | k << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
        float f1 = (particleAge + p_70013_1_) / particleMaxAge;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        float f2 = super.getBrightness(p_70013_1_);
        return f2 * f1 + (1.0F - f1);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if (particleAge++ >= particleMaxAge)
        {
            setDead();
        }

        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.9599999785423279D;
        motionY *= 0.9599999785423279D;
        motionZ *= 0.9599999785423279D;

        if (onGround)
        {
            motionX *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
        }

        Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        Vec3 vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);

        if (movingobjectposition != null)
        {
            vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        if (!worldObj.isRemote)
        {
            Entity entity = null;
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            EntityLivingBase entitylivingbase = null;

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity1 = (Entity) list.get(j);

                if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || particleAge >= 5))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null)
                    {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }

        if (movingobjectposition != null)
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal)
            {
                setInPortal();
            }
            else
            {
                onImpact(movingobjectposition);
            }
        }
    }

    protected void onImpact(MovingObjectPosition mop)
    {
        //        setDead();

        float f = 0.25F;
        motionX *= f;
        motionY *= f;
        motionZ *= f;
    }
}
