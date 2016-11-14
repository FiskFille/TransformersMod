package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.groundbridge.DataCore;

public class ItemDataCore extends Item
{
	@SideOnly(Side.CLIENT)
	private IIcon overlay;

	public ItemDataCore()
	{
		super();
		setMaxStackSize(1);
		setHasSubtypes(true);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
	{
		list.add(DataCore.get(itemstack.getItemDamage()).getTranslatedName());
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List subItems)
	{
		for (int i = 0; i <= DataCore.indexes; ++i)
		{
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return pass == 1 ? overlay : super.getIcon(stack, pass);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int pass)
    {
        return pass == 1 ? DataCore.get(itemstack.getItemDamage()).getColor() : super.getColorFromItemStack(itemstack, pass);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(TransformersMod.modid + ":data_core");
		overlay = iconRegister.registerIcon(TransformersMod.modid + ":data_core_overlay");
	}
}
