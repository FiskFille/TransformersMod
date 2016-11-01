package fiskfille.tf.client.render.tileentity;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelTransmitter;
import fiskfille.tf.common.energon.power.IEnergyReceiver;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;
import fiskfille.tf.helper.TFEnergyHelper;
import fiskfille.tf.helper.TFRenderHelper;
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

public class RenderTransmitter extends TileEntitySpecialRenderer
{
    private ModelTransmitter model = new ModelTransmitter();

    public void render(TileEntityTransmitter transmitter, double x, double y, double z, float partialTicks)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        World world = transmitter.getWorldObj();
        int metadata = 0;

        if (world != null)
        {
            metadata = transmitter.getBlockMetadata();
        }

        if (metadata < 4)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1.0F, -1F, -1F);
            GL11.glRotatef(metadata * 90, 0.0F, 1.0F, 0.0F);

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/transmitter.png"));
            model.render(transmitter, partialTicks);

            bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/transmitter_lights.png"));
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            TFRenderHelper.setLighting(61680);
            model.render(transmitter, partialTicks);
            GL11.glPopMatrix();

            if (world != null)
            {
                Tessellator tessellator = Tessellator.instance;
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_TEXTURE_2D);

                Vec3 outOffset = transmitter.getEnergyOutputOffset();

                if (transmitter.getEnergy() > 0)
                {
                    for (TileEntity tile : transmitter.transmissionHandler.getReceivers())
                    {
                        IEnergyReceiver receiver = (IEnergyReceiver) tile;

                        if (receiver.canReceiveEnergy(transmitter))
                        {
                            Vec3 src = outOffset.addVector(transmitter.xCoord + 0.5F, transmitter.yCoord + 0.5F, transmitter.zCoord + 0.5F);
                            Vec3 dst = receiver.getEnergyInputOffset().addVector(tile.xCoord + 0.5F, tile.yCoord + 0.5F, tile.zCoord + 0.5F);

                            double d = 1F / dst.distanceTo(src);
                            src = Vec3.createVectorHelper(src.xCoord + (dst.xCoord - src.xCoord) * d, src.yCoord + (dst.yCoord - src.yCoord) * d, src.zCoord + (dst.zCoord - src.zCoord) * d);
                            MovingObjectPosition mop = TFEnergyHelper.rayTraceBlocks(world, src, dst);

                            if (mop != null)
                            {
                                dst = mop.hitVec;
                            }

                            double x1 = 0.5F + outOffset.xCoord;
                            double y1 = 0.5F + outOffset.yCoord + (Math.cos((transmitter.animationTimer + partialTicks) / 10) * 2 + 2) / 16;
                            double z1 = 0.5F + outOffset.zCoord;
                            double deltaX = dst.xCoord - transmitter.xCoord;
                            double deltaY = dst.yCoord - transmitter.yCoord;
                            double deltaZ = dst.zCoord - transmitter.zCoord;

                            src = Vec3.createVectorHelper(x1, y1, z1);
                            dst = Vec3.createVectorHelper(deltaX, deltaY, deltaZ);

                            int segments = 128;
                            double width = 1F / 16;
                            double length = src.distanceTo(dst);
                            float[] primaryColour = TFRenderHelper.hexToRGB(0x57ABAF);
                            float[] secondaryColour = TFRenderHelper.hexToRGB(0x7BF2F8);

                            GL11.glPushMatrix();
                            GL11.glTranslated(x + x1, y + y1, z + z1);
                            TFRenderHelper.faceVec(src, dst);

                            for (int i = 0; i < segments; ++i)
                            {
                                double segmentLength = length / segments;
                                double start = i * segmentLength;
                                double end = i * segmentLength + segmentLength;
                                float primaryTimer = (float) Math.cos((float) i / 20 - (transmitter.animationTimer + partialTicks) / 5);
                                float secondaryTimer = 1 - primaryTimer;

                                tessellator.startDrawingQuads();
                                tessellator.setColorRGBA_F(primaryColour[0] * primaryTimer + secondaryColour[0] * secondaryTimer, primaryColour[1] * primaryTimer + secondaryColour[1] * secondaryTimer, primaryColour[2] * primaryTimer + secondaryColour[2] * secondaryTimer, 1);

                                tessellator.addVertex(width, width, end);
                                tessellator.addVertex(width, width, start);
                                tessellator.addVertex(-width, width, start);
                                tessellator.addVertex(-width, width, end);
                                tessellator.addVertex(-width, -width, start);
                                tessellator.addVertex(width, -width, start);
                                tessellator.addVertex(width, -width, end);
                                tessellator.addVertex(-width, -width, end);
                                tessellator.addVertex(-width, width, start);
                                tessellator.addVertex(-width, -width, start);
                                tessellator.addVertex(-width, -width, end);
                                tessellator.addVertex(-width, width, end);
                                tessellator.addVertex(width, -width, end);
                                tessellator.addVertex(width, -width, start);
                                tessellator.addVertex(width, width, start);
                                tessellator.addVertex(width, width, end);

                                if (i == segments - 1)
                                {
                                    tessellator.addVertex(width, -width, end);
                                    tessellator.addVertex(width, width, end);
                                    tessellator.addVertex(-width, width, end);
                                    tessellator.addVertex(-width, -width, end);
                                }
                                else if (i == 0)
                                {
                                    tessellator.addVertex(-width, width, start);
                                    tessellator.addVertex(width, width, start);
                                    tessellator.addVertex(width, -width, start);
                                    tessellator.addVertex(-width, -width, start);
                                }

                                tessellator.draw();
                            }

                            GL11.glPopMatrix();
                        }
                    }
                }

                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glPopMatrix();
            }

            TFRenderHelper.resetLighting();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityTransmitter) tileentity, d, d1, d2, f);
    }
}
