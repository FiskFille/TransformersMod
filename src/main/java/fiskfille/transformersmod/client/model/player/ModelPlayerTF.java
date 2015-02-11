package fiskfille.transformersmod.client.model.player;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.transformersmod.common.item.TFItems;
import fiskfille.transformersmod.common.item.armor.ItemTransformerArmor;

@SideOnly(Side.CLIENT)
public class ModelPlayerTF extends ModelBiped
{
	public ModelPlayerTF()
	{
		this(0.0F);
	}

	public ModelPlayerTF(float modelScale)
	{
		this(modelScale, 0.0F, 64, 32);
	}

	public ModelPlayerTF(float scaleFactor, float p_i1149_2_, int textureWidth, int textureHeight)
	{
		super(scaleFactor, p_i1149_2_, textureWidth, textureHeight);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void adjustPlayerVisibility(EntityPlayer player)
	{
		ItemStack helm = player.getCurrentArmor(3);
		boolean wearingTransformerHelm = helm != null && helm.getItem() instanceof ItemTransformerArmor;
		ItemStack chest = player.getCurrentArmor(2);
		boolean wearingTransformerChest = chest != null && chest.getItem() instanceof ItemTransformerArmor;
		ItemStack pants = player.getCurrentArmor(1);
		boolean wearingTransformerPants = pants != null && pants.getItem() instanceof ItemTransformerArmor;
		
		this.bipedHead.offsetY = wearingTransformerHelm ? 256 : 0;
		this.bipedHeadwear.offsetY = wearingTransformerHelm ? 256 : 0;
		this.bipedBody.offsetY = wearingTransformerChest ? 256 : 0;
		this.bipedRightArm.offsetY = wearingTransformerChest ? 256 : 0;
		this.bipedLeftArm.offsetY = wearingTransformerChest ? 256 : 0;
		this.bipedRightLeg.offsetY = wearingTransformerPants ? 256 : 0;
		this.bipedLeftLeg.offsetY = wearingTransformerPants ? 256 : 0;
	}
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			ItemStack itemstack = player.getHeldItem();
			
			if (itemstack != null && itemstack.getItem() == TFItems.vurpsSniper)
			{
	    		this.setRotation(this.bipedRightArm, -1.3F, bipedHead.rotateAngleY - 0.45F, 0.0F);
	    		this.setRotation(this.bipedLeftArm, -1.2F, bipedHead.rotateAngleY + 0.4F, 0.0F);
	    		this.bipedLeftArm.setRotationPoint(3.0F, 3.0F, -2.5F);
	    	}
	    	else
	    	{
	    		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
	    		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
	    	}
			
			adjustPlayerVisibility(player);
		}
	}
}