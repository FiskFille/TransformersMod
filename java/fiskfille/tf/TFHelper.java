package fiskfille.tf;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.item.TFItems;
import fiskfille.tf.item.armor.ITransformerArmor;
import fiskfille.tf.misc.VehicleType;
import fiskfille.tf.proxy.ClientProxy;

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
	
	public static boolean isPlayerCloudtrap(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) && player.getCurrentArmor(3).getItem() == TFItems.cloudtrapHelmet && player.getCurrentArmor(2).getItem() == TFItems.cloudtrapChestplate && player.getCurrentArmor(1).getItem() == TFItems.cloudtrapLeggings && player.getCurrentArmor(0).getItem() == TFItems.cloudtrapBoots;
	}
	
	public static boolean isPlayerSkystrike(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) && player.getCurrentArmor(3).getItem() == TFItems.skystrikeHelmet && player.getCurrentArmor(2).getItem() == TFItems.skystrikeChestplate && player.getCurrentArmor(1).getItem() == TFItems.skystrikeLeggings && player.getCurrentArmor(0).getItem() == TFItems.skystrikeBoots;
	}
	
	public static boolean isPlayerPurge(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) && player.getCurrentArmor(3).getItem() == TFItems.purgeHelmet && player.getCurrentArmor(2).getItem() == TFItems.purgeChestplate && player.getCurrentArmor(1).getItem() == TFItems.purgeLeggings && player.getCurrentArmor(0).getItem() == TFItems.purgeBoots;
	}
	
	public static boolean isPlayerVurp(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) && player.getCurrentArmor(3).getItem() == TFItems.vurpHelmet && player.getCurrentArmor(2).getItem() == TFItems.vurpChestplate && player.getCurrentArmor(1).getItem() == TFItems.vurpLeggings && player.getCurrentArmor(0).getItem() == TFItems.vurpBoots;
	}
	
	public static boolean isPlayerSubwoofer(EntityPlayer player)
	{
		return hasPlayerFullArmor(player) && player.getCurrentArmor(3).getItem() == TFItems.subwooferHelmet && player.getCurrentArmor(2).getItem() == TFItems.subwooferChestplate && player.getCurrentArmor(1).getItem() == TFItems.subwooferLeggings && player.getCurrentArmor(0).getItem() == TFItems.subwooferBoots;
	}
	
	public static ResourceLocation getPlayerHandTexture(EntityPlayer player)
	{
		if (player.getCurrentArmor(2) != null)
		{
			if (player.getCurrentArmor(2).getItem() == TFItems.skystrikeChestplate) {return new ResourceLocation(TransformersMod.modid, "textures/models/skystrike/skystrike1.png");}
			if (player.getCurrentArmor(2).getItem() == TFItems.purgeChestplate) {return new ResourceLocation(TransformersMod.modid, "textures/models/purge/purge1.png");}
			if (player.getCurrentArmor(2).getItem() == TFItems.vurpChestplate) {return new ResourceLocation(TransformersMod.modid, "textures/models/vurp/vurp1.png");}
			if (player.getCurrentArmor(2).getItem() == TFItems.subwooferChestplate) {return new ResourceLocation(TransformersMod.modid, "textures/models/subwoofer/subwoofer.png");}
			if (player.getCurrentArmor(2).getItem() == TFItems.subwooferChestplate) {return new ResourceLocation(TransformersMod.modid, "textures/models/cloudtrap/cloudtrap.png");}
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public static ModelBiped getPlayerModel(EntityPlayer player, int piece)
	{
		if (player.getCurrentArmor(piece) != null)
		{
			if (player.getCurrentArmor(piece).getItem() == TFItems.skystrikeChestplate) {return ClientProxy.getSkystrike();}
			if (player.getCurrentArmor(piece).getItem() == TFItems.purgeChestplate) {return ClientProxy.getPurge();}
			if (player.getCurrentArmor(piece).getItem() == TFItems.cloudtrapChestplate) {return ClientProxy.getCloudtrap();}
			if (player.getCurrentArmor(piece).getItem() == TFItems.vurpChestplate) {return ClientProxy.getVurp();}
			if (player.getCurrentArmor(piece).getItem() == TFItems.subwooferChestplate) {return ClientProxy.getSubwoofer();}
		}
		return null;
	}
	
	public static boolean isTransformerArmor(EntityPlayer player, Item item)
	{
		return item instanceof ITransformerArmor;
	}
	
	@Deprecated
	public void adjustPlayerVisibility(EntityPlayer player, ModelBiped model)
	{
		boolean flag = false; 
		if(player.worldObj.isRemote)
		{
			flag = Minecraft.getMinecraft().gameSettings.thirdPersonView == 0;
		}
		
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

	public static boolean isPlayerTransformer(EntityPlayer player) 
	{
		if(hasPlayerFullArmor(player))
		{
			Item helmet = player.getCurrentArmor(3).getItem();
			Item chestplate = player.getCurrentArmor(2).getItem();
			Item legs = player.getCurrentArmor(1).getItem();
			Item boots = player.getCurrentArmor(0).getItem();
			
			boolean transformerArmour = helmet instanceof ITransformerArmor && chestplate instanceof ITransformerArmor && legs instanceof ITransformerArmor && boots instanceof ITransformerArmor;
			
			return transformerArmour && helmet.getClass() == chestplate.getClass() && helmet.getClass() == legs.getClass() && helmet.getClass() == boots.getClass();
		}
	
		return false;
	}

	public static boolean isPlayerJet(EntityPlayer player)
	{
		return getTransformerType(player) == VehicleType.JET;
	}
	
	public static boolean isPlayerTank(EntityPlayer player)
	{
		return getTransformerType(player) == VehicleType.TANK;
	}
	
	public static boolean isPlayerCar(EntityPlayer player)
	{
		return getTransformerType(player) == VehicleType.CAR;
	}

	public static VehicleType getTransformerType(EntityPlayer player)
	{
		return isPlayerTransformer(player) ? ((ITransformerArmor)(player.getCurrentArmor(3).getItem())).getVehicleType() : null;
	}
}