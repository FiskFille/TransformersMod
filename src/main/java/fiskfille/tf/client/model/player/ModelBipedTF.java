package fiskfille.tf.client.model.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.transformer.ModelChildBase;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
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
		TFModelHelper.modelBipedMain = this;

		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, partialTicks, entity);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;

			ModelOffset offsets = TFModelHelper.getOffsets(player);

			this.bipedHead.rotationPointY = offsets.headOffsetY;
			this.bipedHeadwear.rotationPointY = offsets.headOffsetY;
			this.bipedHead.rotationPointX = offsets.headOffsetX;
			this.bipedHeadwear.rotationPointX = offsets.headOffsetX;
			this.bipedHead.rotationPointZ = offsets.headOffsetZ;
			this.bipedHeadwear.rotationPointZ = offsets.headOffsetZ;

			ItemStack helm = player.getCurrentArmor(3);
			boolean wearingTransformerHelm = helm != null && helm.getItem() instanceof ItemTransformerArmor;
			ItemStack chest = player.getCurrentArmor(2);
			boolean wearingTransformerChest = chest != null && chest.getItem() instanceof ItemTransformerArmor;
			ItemStack pants = player.getCurrentArmor(1);
			boolean wearingTransformerPants = pants != null && pants.getItem() instanceof ItemTransformerArmor;
			
			bipedHead.showModel = !wearingTransformerHelm;
			bipedHeadwear.showModel = !wearingTransformerHelm;
			bipedEars.showModel = !wearingTransformerHelm;
			
			bipedBody.showModel = !wearingTransformerChest;
			bipedRightArm.showModel = !wearingTransformerChest;
			bipedLeftArm.showModel = !wearingTransformerChest;

			bipedLeftLeg.showModel = !wearingTransformerPants;
			bipedRightLeg.showModel = !wearingTransformerPants;	

			if (this.isChild)
			{
				float f6 = 2.0F;
				GL11.glPushMatrix();
				GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
				GL11.glTranslatef(0.0F, 16.0F * partialTicks, 0.0F);
				this.bipedHead.render(partialTicks);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
				GL11.glTranslatef(0.0F, 24.0F * partialTicks, 0.0F);
				this.bipedRightArm.render(partialTicks);
				this.bipedLeftArm.render(partialTicks);
				this.bipedRightLeg.render(partialTicks);
				this.bipedLeftLeg.render(partialTicks);
				this.bipedHeadwear.render(partialTicks);
				GL11.glPopMatrix();
			}
			else
			{
				this.bipedHead.render(partialTicks);
				this.bipedBody.render(partialTicks);
				this.bipedRightArm.render(partialTicks);
				this.bipedLeftArm.render(partialTicks);
				this.bipedRightLeg.render(partialTicks);
				this.bipedLeftLeg.render(partialTicks);
				this.bipedHeadwear.render(partialTicks);
			}

			if(!(offsets.timesModified > 0))
			{
				offsets.headOffsetX = 0;
				offsets.headOffsetY = 0;
				offsets.headOffsetZ = 0;
			}

			offsets.timesModified = 0;
		}
	}
}