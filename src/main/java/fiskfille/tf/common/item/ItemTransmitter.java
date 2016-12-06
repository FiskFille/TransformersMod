package fiskfille.tf.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import fiskfille.tf.common.energon.power.IEnergyTransmitter;
import fiskfille.tf.helper.TFEnergyHelper;

public class ItemTransmitter extends ItemBlock
{
    public ItemTransmitter(Block block)
    {
        super(block);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean flag)
    {
        if (field_150939_a instanceof ITileEntityProvider)
        {
            IEnergyTransmitter transmitter = (IEnergyTransmitter) ((ITileEntityProvider) field_150939_a).createNewTileEntity(null, 0);

            list.add(StatCollector.translateToLocalFormatted("gui.emb.rate", TFEnergyHelper.formatNumber(transmitter.getTransmissionRate())));
        }
    }
}
