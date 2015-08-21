package fiskfille.tf.client.particle;

import java.util.List;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityTFFlameFX extends EntityFX
{
    /** the scale of the flame FX */
    private float flameScale;
    
    public EntityTFFlameFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.motionX = this.motionX * 0.009999999776482582D + motionX;
        this.motionY = this.motionY * 0.009999999776482582D + motionY;
        this.motionZ = this.motionZ * 0.009999999776482582D + motionZ;
        double d6 = x + (double) ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        d6 = y + (double) ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        d6 = z + (double) ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.flameScale = this.particleScale;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.noClip = true;
        this.setParticleTextureIndex(48);
    }
    
    public void renderParticle(Tessellator tesselator, float x, float y, float z, float r, float g, float b)
    {
        float f6 = ((float) this.particleAge + x) / (float) this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - f6 * f6 * 0.5F);
        super.renderParticle(tesselator, x, y, z, r, g, b);
    }
    
    public int getBrightnessForRender(float p_70070_1_)
    {
        float f1 = ((float) this.particleAge + p_70070_1_) / (float) this.particleMaxAge;
        
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
        float f1 = ((float) this.particleAge + p_70013_1_) / (float) this.particleMaxAge;
        
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
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
        
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;
        
        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
        
//        if (!worldObj.isAirBlock((int) posX, (int) posY, (int) posZ))
//        {
//            this.setDead();
//        }
        
        
        
        
        
        Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (movingobjectposition != null)
        {
            vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        if (!this.worldObj.isRemote)
        {
            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
//            EntityLivingBase entitylivingbase = this.getThrower();
            EntityLivingBase entitylivingbase = null;

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity1 = (Entity)list.get(j);

                if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.particleAge >= 5))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
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
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal)
            {
                this.setInPortal();
            }
            else
            {
                this.onImpact(movingobjectposition);
            }
        }
    }

    protected void onImpact(MovingObjectPosition mop)
    {
//        if (mop.entityHit != null)
//        {
//            mop.entityHit.setFire(20);
//            
////            if (getThrower() instanceof EntityPlayer)
////            {
////                EntityPlayer player = (EntityPlayer) getThrower();
////                mop.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
////            }
//        }
//        
//        setFire(worldObj, mop.blockX, mop.blockY, mop.blockZ, mop.sideHit);
        this.setDead();
    }
    
    public boolean setFire(World world, int x, int y, int z, int sideHit)
    {
        if (sideHit == 0)
        {
            --y;
        }
        
        if (sideHit == 1)
        {
            ++y;
        }
        
        if (sideHit == 2)
        {
            --z;
        }
        
        if (sideHit == 3)
        {
            ++z;
        }
        
        if (sideHit == 4)
        {
            --x;
        }
        
        if (sideHit == 5)
        {
            ++x;
        }
        
        if (world.isAirBlock(x, y, z))
        {
            world.setBlock(x, y, z, Blocks.fire);
        }
        
        return true;
    }
}