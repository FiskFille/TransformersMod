package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

public class ItemMetaBasic extends Item
{
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	public final String[] itemNames;

	public ItemMetaBasic(String... itemNames)
	{
		this.itemNames = itemNames;
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List subItems)
	{
		for (int i = 0; i < itemNames.length; ++i)
		{
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		int damage = stack.getItemDamage();
		return StatCollector.translateToLocal("item." + itemNames[MathHelper.clamp_int(damage, 0, itemNames.length - 1)].toLowerCase().replace(" ", "_").replace("'", "") + ".name");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		return icons[MathHelper.clamp_int(damage, 0, icons.length - 1)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		icons = new IIcon[itemNames.length];

		for (int i = 0; i < itemNames.length; ++i)
		{
			icons[i] = iconRegister.registerIcon(TransformersMod.modid + ":" + itemNames[i].toLowerCase().replaceAll(" ", "_").replaceAll("'", ""));
		}
	}
}
