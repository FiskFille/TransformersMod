package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.TransformersMod;

public class ItemDyeTF extends Item
{
    public static final String[] dyes = new String[] {"dark_red", "beige", "dark_gray", "pale_green", "pale_brown"};
    public static final int[] dyeColors = new int[] {0, 0, 0, 0, 0};

    protected IIcon[] icons;

    public ItemDyeTF()
    {
        setHasSubtypes(true);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    public static int getDyeColor(int damage)
    {
        return dyeColors[MathHelper.clamp_int(damage, 0, dyeColors.length - 1)];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List subItems)
    {
        for (int i = 0; i < dyes.length; ++i)
        {
            subItems.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return super.getUnlocalizedName() + "." + dyes[MathHelper.clamp_int(itemstack.getItemDamage(), 0, dyeColors.length - 1)];
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
        icons = new IIcon[dyes.length];

        for (int i = 0; i < dyes.length; ++i)
        {
            icons[i] = iconRegister.registerIcon(TransformersMod.modid + ":" + dyes[i] + "_dye");
        }
    }
}
