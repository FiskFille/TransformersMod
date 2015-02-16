package fiskfille.tf.client.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.player.ModelPlayerTF;
import fiskfille.tf.common.playerdata.TFDataManager;
import fiskfille.tf.helper.TFHelper;

@SideOnly(Side.CLIENT)
public class RenderCustomPlayer extends RenderPlayer
{
	public RenderCustomPlayer()
	{
		super();
<<<<<<< HEAD:src/main/java/fiskfille/tf/render/entity/RenderCustomPlayer.java
		this.mainModel = new ModelBipedTF();
=======
		this.mainModel = new ModelPlayerTF(0.0F);
>>>>>>> parent of 52b2aa7... Revert "fix it, gegs":src/main/java/fiskfille/tf/client/render/entity/RenderCustomPlayer.java
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