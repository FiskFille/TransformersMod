package fiskfille.tf.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemColoredTF extends ItemCloth
{
    public ItemColoredTF(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        return field_150939_a.func_149735_b(2, damage);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return super.getUnlocalizedName() + "." + ItemDyeTF.dyes[MathHelper.clamp_int(itemstack.getItemDamage(), 0, ItemDyeTF.dyeColors.length - 1)];
    }
}
