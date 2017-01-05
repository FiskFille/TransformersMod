package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.helper.TFFormatHelper;

public class ItemMachine extends ItemBlock
{
    public ItemMachine(Block block)
    {
        super(block);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemstack)
    {
        String s = super.getItemStackDisplayName(itemstack);

        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("ConfigDataTF", NBT.TAG_COMPOUND))
        {
            s = StatCollector.translateToLocalFormatted("gui.emb.item.configured", s + EnumChatFormatting.ITALIC);
        }

        return s;
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
        TileEntity tile = getTileEntity(player.worldObj);

        if (tile instanceof IEnergyTransmitter)
        {
            IEnergyTransmitter transmitter = (IEnergyTransmitter) tile;
            list.add(StatCollector.translateToLocalFormatted("gui.emb.rate", TFFormatHelper.formatNumber(transmitter.getTransmissionRate())));
        }
    }

    public TileEntity getTileEntity(World world)
    {
        if (field_150939_a.hasTileEntity(0))
        {
            return field_150939_a.createTileEntity(world, 0);
        }

        return null;
    }
}
