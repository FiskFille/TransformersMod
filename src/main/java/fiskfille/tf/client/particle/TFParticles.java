package fiskfille.tf.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;

public class TFParticles
{
    private static Minecraft mc = Minecraft.getMinecraft();
    private static World theWorld = mc.theWorld;

    public static EntityFX spawnParticle(TFParticleType particleType, double x, double y, double z, float motionX, float motionY, float motionZ)
    {
        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            if (theWorld.isRemote)
            {
                int particleSetting = mc.gameSettings.particleSetting;

                if (particleSetting == 1 && theWorld.rand.nextInt(3) == 0)
                {
                    particleSetting = 2;
                }

                double diffX = mc.renderViewEntity.posX - x;
                double diffY = mc.renderViewEntity.posY - y;
                double diffZ = mc.renderViewEntity.posZ - z;

                EntityFX particle = null;
                double maxRenderDistance = 16.0D;

                if (diffX * diffX + diffY * diffY + diffZ * diffZ > maxRenderDistance * maxRenderDistance)
                {
                    return null;
                }
                else if (particleSetting > 1)
                {
                    return null;
                }
                else
                {
                    try
                    {
                        Constructor c = particleType.particleClass.getConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class);
                        particle = (EntityFX) c.newInstance(theWorld, x, y, z, motionX, motionY, motionZ);

                        mc.effectRenderer.addEffect(particle);

                        return particle;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    return null;
                }
            }
        }

        return null;
    }
}
