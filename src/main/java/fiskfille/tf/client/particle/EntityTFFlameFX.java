package fiskfille.tf.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityTFFlameFX extends EntityFX
{
    private float flameScale;

    public EntityTFFlameFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.motionX = this.motionX * 0.009999999776482582D + motionX;
        this.motionY = this.motionY * 0.009999999776482582D + motionY;
        this.motionZ = this.motionZ * 0.009999999776482582D + motionZ;
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
        return 0xF000F0;
    }

    @Override
    public float getBrightness(float partialTicks)
    {
        return 0xF000F0;
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
}
