package fiskfille.tf.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.main.MainClass;

public class ItemMetaBasic extends Item
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	private final String[] itemNames;
	
	public ItemMetaBasic(String... itemNames)
	{
		super();
		this.itemNames = itemNames;
		this.setCreativeTab(MainClass.tabTransformers);
		this.setHasSubtypes(true);
	}
	
	public void getSubItems(Item par1Item, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < itemNames.length; ++i)
		{
			par3List.add(new ItemStack(this, 1, i));
		}
	}
	
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return itemNames[par1ItemStack.getItemDamage() > itemNames.length ? itemNames.length : par1ItemStack.getItemDamage()];
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return icons[par1 > itemNames.length ? itemNames.length : par1];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[itemNames.length];
		
		for (int i = 0; i < itemNames.length; ++i)
		{
			icons[i] = par1IconRegister.registerIcon(MainClass.modid + ":" + itemNames[i].toLowerCase().replace(' ', '_').replace("'", ""));
		}
	}
}