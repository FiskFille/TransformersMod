package fiskfille.tf.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class TFParticles
{
	private static Minecraft mc = Minecraft.getMinecraft();
	private static World theWorld = mc.theWorld;

	public static EntityFX spawnParticle(String particleName, double x, double y, double z, double motionX, double motionY, double motionZ)
	{
		if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
		{
			if(theWorld.isRemote)
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
					if (particleName.equals("flame"))
					{
						particle = new EntityTFFlameFX(theWorld, x, y, z, (float)motionX, (float)motionY, (float)motionZ);
					}

					mc.effectRenderer.addEffect((EntityFX)particle);
					return (EntityFX)particle;
				}
			}
		}
		
		return null;
	}
}