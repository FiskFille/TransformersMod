package fiskfille.tf.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

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

    @Override
    public void renderParticle(Tessellator tesselator, float x, float y, float z, float r, float g, float b)
    {
        float f6 = (particleAge + x) / particleMaxAge;
        particleScale = flameScale * (1.0F - f6 * f6 * 0.5F);
        super.renderParticle(tesselator, x, y, z, r, g, b);
    }

    @Override
    public int getBrightnessForRender(float partialTicks)
    {
        float age = (particleAge + partialTicks) / particleMaxAge;

        if (age < 0.0F)
        {
            age = 0.0F;
        }
        else if (age > 1.0F)
        {
            age = 1.0F;
        }

        int renderBrightness = super.getBrightnessForRender(partialTicks);
        int j = renderBrightness & 255;
        int k = renderBrightness >> 16 & 255;
        j += (int) (age * 15.0F * 16.0F);

        if (j > 240)
        {
            j = 240;
        }

        return j | k << 16;
    }

    @Override
    public float getBrightness(float partialTicks)
    {
        float age = (particleAge + partialTicks) / particleMaxAge;

        if (age < 0.0F)
        {
            age = 0.0F;
        }
        else if (age > 1.0F)
        {
            age = 1.0F;
        }

        float brightness = super.getBrightness(partialTicks);
        return brightness * age + (1.0F - age);
    }

    @Override
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
    }

    protected void onImpact(MovingObjectPosition mop)
    {
        float f = 0.25F;
        motionX *= f;
        motionY *= f;
        motionZ *= f;
    }
}
