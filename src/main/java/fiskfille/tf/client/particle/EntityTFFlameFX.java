package fiskfille.tf.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
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
        
        if (!worldObj.isAirBlock((int) posX, (int) posY, (int) posZ))
        {
            this.setDead();
        }
    }
}