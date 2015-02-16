package fiskfille.tf.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.data.TFDataManager;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.model.player.ModelBipedTF;

@SideOnly(Side.CLIENT)
public class RenderCustomPlayer extends RenderPlayer
{
	public RenderCustomPlayer()
	{
		super();
		this.mainModel = new ModelBipedTF(0.0F);
        this.modelBipedMain = (ModelBiped)this.mainModel;
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer player)
	{
		ItemStack currentArmor = player.getCurrentArmor(2);

		if(!TFDataManager.isInVehicleMode(player))
		{
			if (currentArmor != null)
			{
				if (TFHelper.isTransformerArmor(player, currentArmor.getItem()))
				{
//					ITransformerArmor transformerArmor = (ITransformerArmor)currentArmor.getItem();
//					Transformer transformer = transformerArmor.getTransformer();
//					
//					if(transformer != null)
//					{
//						ModelChildBase.Biped model = transformer.getModel();
//						ResourceLocation resourcelocation = new ResourceLocation(transformer.getChestplate().getArmorTexture(currentArmor, player, 3, ""));
//						
//						float f = 1.0F;
//				        GL11.glColor3f(f, f, f);
//				        Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation);
//				        model.onGround = 0.0F;
//				        model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
//				        model.bipedRightArm.render(0.0625F);
//					}
					
					super.renderFirstPersonArm(player);
				}
				else
				{
					super.renderFirstPersonArm(player);
				}
			}
			else
			{
				super.renderFirstPersonArm(player);
			}
		}
	}
}