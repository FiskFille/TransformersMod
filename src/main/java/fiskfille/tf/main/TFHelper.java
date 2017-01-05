package fiskfille.tf.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.item.ITransformerArmor;
import fiskfille.tf.main.misc.ClientProxy;
import fiskfille.tf.model.ModelCustomPlayer;

public class TFHelper
{
	private static TFHelper instance = new TFHelper();
	
	public static TFHelper getInstance()
	{
		return instance;
	}
	
	private static boolean hasPlayerFullArmor(EntityPlayer player)
	{
		return player != null ? player.getCurrentArmor(0) != null && player.getCurrentArmor(1) != null && player.getCurrentArmor(2) != null && player.getCurrentArmor(3) != null : false;
	}
	
	public static boolean isPlayerSkystrike(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) ? player.getCurrentArmor(3).getItem() == TFItems.skystrikeHelmet && player.getCurrentArmor(2).getItem() == TFItems.skystrikeChestplate && player.getCurrentArmor(1).getItem() == TFItems.skystrikeLeggings && player.getCurrentArmor(0).getItem() == TFItems.skystrikeBoots : false;
	}
	
	public static boolean isPlayerPurge(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) ? player.getCurrentArmor(3).getItem() == TFItems.purgeHelmet && player.getCurrentArmor(2).getItem() == TFItems.purgeChestplate && player.getCurrentArmor(1).getItem() == TFItems.purgeLeggings && player.getCurrentArmor(0).getItem() == TFItems.purgeBoots : false;
	}
	
	public static boolean isPlayerVurp(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) ? player.getCurrentArmor(3).getItem() == TFItems.vurpHelmet && player.getCurrentArmor(2).getItem() == TFItems.vurpChestplate && player.getCurrentArmor(1).getItem() == TFItems.vurpLeggings && player.getCurrentArmor(0).getItem() == TFItems.vurpBoots : false;
	}
	
	public static boolean isPlayerSubwoofer(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) ? player.getCurrentArmor(3).getItem() == TFItems.subwooferHelmet && player.getCurrentArmor(2).getItem() == TFItems.subwooferChestplate && player.getCurrentArmor(1).getItem() == TFItems.subwooferLeggings && player.getCurrentArmor(0).getItem() == TFItems.subwooferBoots : false;
	}
	
	public static ResourceLocation getPlayerHandTexture(EntityPlayer player)
	{
		if (player.getCurrentArmor(2) != null)
		{
			if (player.getCurrentArmor(2).getItem() == TFItems.skystrikeChestplate) {return new ResourceLocation(MainClass.modid, "textures/models/skystrike/skystrike1.png");}
			if (player.getCurrentArmor(2).getItem() == TFItems.purgeChestplate) {return new ResourceLocation(MainClass.modid, "textures/models/purge/purge1.png");}
			if (player.getCurrentArmor(2).getItem() == TFItems.vurpChestplate) {return new ResourceLocation(MainClass.modid, "textures/models/vurp/vurp1.png");}
			if (player.getCurrentArmor(2).getItem() == TFItems.vurpChestplate) {return new ResourceLocation(MainClass.modid, "textures/models/subwoofer/subwoofer.png");}
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public static ModelBiped getPlayerModel(EntityPlayer player, int piece)
	{
		if (player.getCurrentArmor(piece) != null)
		{
			if (player.getCurrentArmor(piece).getItem() == TFItems.skystrikeChestplate) {return ClientProxy.modelSkystrike;}
			if (player.getCurrentArmor(piece).getItem() == TFItems.purgeChestplate) {return ClientProxy.modelPurge;}
			if (player.getCurrentArmor(piece).getItem() == TFItems.vurpChestplate) {return ClientProxy.modelVurp;}
			if (player.getCurrentArmor(piece).getItem() == TFItems.subwooferChestplate) {return ClientProxy.modelSubwoofer;}
		}
		return null;
	}
	
	public static boolean isTransformerArmor(EntityPlayer player, Item item)
	{
		return item instanceof ITransformerArmor;
	}
	
	public void adjustPlayerVisibility(EntityPlayer player, ModelBiped model)
	{
		boolean flag = Minecraft.getMinecraft().gameSettings.thirdPersonView == 0;
		
		if (player.getCurrentArmor(3) != null)
		{
			int i = isTransformerArmor(player, player.getCurrentArmor(3).getItem()) ? 256 : 0;
    		model.bipedHead.offsetY = i;
    		model.bipedHeadwear.offsetY = i;
		}
		else
		{
			model.bipedHead.offsetY = 0;
			model.bipedHeadwear.offsetY = 0;
		}
		if (player.getCurrentArmor(2) != null)
		{
			int i = isTransformerArmor(player, player.getCurrentArmor(2).getItem()) ? 256 : 0;
			model.bipedBody.offsetY = i;
			model.bipedRightArm.offsetY = i;
			model.bipedLeftArm.offsetY = i;
		}
		else
		{
			model.bipedBody.offsetY = 0;
			model.bipedRightArm.offsetY = 0;
			model.bipedLeftArm.offsetY = 0;
		}
		if (player.getCurrentArmor(1) != null)
		{
			int i = isTransformerArmor(player, player.getCurrentArmor(1).getItem()) ? 256 : 0;
			model.bipedRightLeg.offsetY = i;
			model.bipedLeftLeg.offsetY = i;
		}
		else
		{
			model.bipedRightLeg.offsetY = 0;
			model.bipedLeftLeg.offsetY = 0;
		}
		
		model.bipedRightArm.rotateAngleX = 1;
		ClientProxy.modelBipedMain.bipedRightArm.rotateAngleX = 1;
	}
}