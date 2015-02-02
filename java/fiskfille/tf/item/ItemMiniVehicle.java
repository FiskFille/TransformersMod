package fiskfille.tf.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMiniVehicle extends ItemMetaBasic implements IDisplayPillarItem
{
	public ItemMiniVehicle(String... itemNames)
	{
		super(itemNames);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		
	}
}