package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelTransmitter;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFRenderHelper;

public class RenderTransmitter extends TileEntitySpecialRenderer
{
	private ModelTransmitter model = new ModelTransmitter();

	public void render(TileEntityTransmitter tileentity, double x, double y, double z, float partialTicks)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		World world = tileentity.getWorldObj();
		Vec3 vec32 = Vec3.createVectorHelper(TFRenderHelper.median((float)player.posX, (float)player.prevPosX, partialTicks), TFRenderHelper.median((float)player.posY, (float)player.prevPosY, partialTicks), TFRenderHelper.median((float)player.posZ, (float)player.prevPosZ, partialTicks));
		int metadata = 0;

		if (world != null)
		{
			metadata = tileentity.getBlockMetadata();
		}
		
		if (metadata < 4)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			GL11.glScalef(1.0F, -1F, -1F);
			GL11.glRotatef(metadata * 90, 0.0F, 1.0F, 0.0F);

			bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/transmitter.png"));
			model.render(tileentity, partialTicks);

			bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/transmitter_lights.png"));
			GL11.glDisable(GL11.GL_LIGHTING);
			TFRenderHelper.setLighting(61680);
			model.render(tileentity, partialTicks);
			GL11.glPopMatrix();
			
			if (world != null)
			{
				Tessellator tessellator = Tessellator.instance;
				GL11.glPushMatrix();
				GL11.glTranslatef((float)x, (float)y, (float)z);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_BLEND);
				
				float prevWidth = GL11.glGetFloat(GL11.GL_LINE_WIDTH);

				for (TileEntity tile : tileentity.getTilesToTryPower())
				{
					IEnergyReceiver receiver = (IEnergyReceiver)tile;
					Vec3 vec3 = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);
					Vec3 vec31 = Vec3.createVectorHelper(tileentity.xCoord + 0.5F, tileentity.yCoord + 2.5F, tileentity.zCoord + 0.5F);
					
					double d = 1F / vec3.distanceTo(vec31);
					vec31 = Vec3.createVectorHelper(vec31.xCoord + (vec3.xCoord - vec31.xCoord) * d, vec31.yCoord + (vec3.yCoord - vec31.yCoord) * d, vec31.zCoord + (vec3.zCoord - vec31.zCoord) * d);
					MovingObjectPosition mop = world.rayTraceBlocks(vec31, vec3);
					
					if (mop != null)
					{
						vec3 = mop.hitVec;
					}
					
					double x1 = 0.5F;
					double y1 = 2.5F + (Math.cos((tileentity.animationTimer + partialTicks) / 10) * 2 + 2) / 16;
					double z1 = 0.5F;
					double x2 = vec3.xCoord - tileentity.xCoord;
					double y2 = vec3.yCoord - tileentity.yCoord;
					double z2 = vec3.zCoord - tileentity.zCoord;
					
					int segments = 128;
					float[] afloat = TFRenderHelper.hexToRGB(0x57ABAF);
					float[] afloat1 = TFRenderHelper.hexToRGB(0x7BF2F8);
					float width = 0;
					tessellator.startDrawing(3);
					
					for (int i = 0; i <= segments; ++i)
					{
						float f = (float)i / segments;
						float f1 = 1 - f;
						float f2 = (float)Math.cos((float)i / 20 - (float)(tileentity.animationTimer + partialTicks) / 5);
						float f3 = 1 - f2;
						tessellator.setColorRGBA_F(afloat[0] * f2 + afloat1[0] * f3, afloat[1] * f2 + afloat1[1] * f3, afloat[2] * f2 + afloat1[2] * f3, 1);
						tessellator.addVertex(x1 * f1 + x2 * f, y1 * f1 + y2 * f, z1 * f1 + z2 * f);
						width = Math.max(width, (1F / (float)vec3.distanceTo(vec32)) * 100);
						width = Math.max(width, (1F / (float)vec31.distanceTo(vec32)) * 100);
					}
					
					GL11.glLineWidth(width);
					tessellator.draw();
				}
				
				GL11.glLineWidth(prevWidth);
				
				/**
				 * Debugging tool that outlines the render frustum for this
				 * block.
				 */
//				float f4 = 0.002F;
//				GL11.glDisable(GL11.GL_TEXTURE_2D);
//				AxisAlignedBB aabb = tileentity.getRenderBoundingBox().expand(f4, f4, f4).getOffsetBoundingBox(-tileentity.xCoord, -tileentity.yCoord, -tileentity.zCoord);
//				RenderGlobal.drawOutlinedBoundingBox(aabb, 0xffffff);
//				GL11.glEnable(GL11.GL_TEXTURE_2D);
//				GL11.glColor4f(1, 1, 1, 1);
				
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
			
			TFRenderHelper.resetLighting();
			GL11.glEnable(GL11.GL_LIGHTING);
		}
	}

	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		render((TileEntityTransmitter) tileentity, d, d1, d2, f);
	}
}
