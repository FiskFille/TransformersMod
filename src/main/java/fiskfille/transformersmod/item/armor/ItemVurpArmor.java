package fiskfille.transformersmod.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.achievement.TFAchievements;
import fiskfille.transformersmod.helper.TFHelper;
import fiskfille.transformersmod.item.TFItems;
import fiskfille.transformersmod.transformer.base.Transformer;

public class ItemVurpArmor extends ItemTransformerArmor
{
	public ItemVurpArmor(int armorPiece)
	{
		super(TFItems.TRANSFORMERMATERIAL, 4, armorPiece);
		this.setCreativeTab(TransformersMod.transformersTab);
	}

	@Override
	public Transformer getTransformer() 
	{
		return TransformersMod.transformerVurp;
	}
}