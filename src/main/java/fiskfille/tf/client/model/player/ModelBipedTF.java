package fiskfille.tf.client.model.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.helper.ModelOffset;
import fiskfille.tf.helper.TFModelHelper;

@SideOnly(Side.CLIENT)
public class ModelBipedTF extends ModelChildBase.Biped
{
    public ModelBipedTF()
    {
        super();
    }

    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float partialTicks)
    {
        setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, partialTicks, entity);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;

            ModelOffset offsets = TFModelHelper.getOffsets(player);

            bipedHead.rotationPointY = offsets.headOffsetY;
            bipedHeadwear.rotationPointY = offsets.headOffsetY;
            bipedHead.rotationPointX = offsets.headOffsetX;
            bipedHeadwear.rotationPointX = offsets.headOffsetX;
            bipedHead.rotationPointZ = offsets.headOffsetZ;
            bipedHeadwear.rotationPointZ = offsets.headOffsetZ;

            if (isChild)
            {
                float f6 = 2.0F;
                GL11.glPushMatrix();
                GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
                GL11.glTranslatef(0.0F, 16.0F * partialTicks, 0.0F);
                bipedHead.render(partialTicks);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
                GL11.glTranslatef(0.0F, 24.0F * partialTicks, 0.0F);
                bipedRightArm.render(partialTicks);
                bipedLeftArm.render(partialTicks);
                bipedRightLeg.render(partialTicks);
                bipedLeftLeg.render(partialTicks);
                bipedHeadwear.render(partialTicks);
                GL11.glPopMatrix();
            }
            else
            {
                bipedHead.render(partialTicks);
                bipedBody.render(partialTicks);
                bipedRightArm.render(partialTicks);
                bipedLeftArm.render(partialTicks);
                bipedRightLeg.render(partialTicks);
                bipedLeftLeg.render(partialTicks);
                bipedHeadwear.render(partialTicks);
            }
        }
    }
}