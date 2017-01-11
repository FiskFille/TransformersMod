package fiskfille.tf.common.component;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ComponentArmor extends Component
{
    @Override
    public boolean canLoad(TileEntityDisplayStation tile, int slot)
    {
        ItemStack head = tile.getStackInSlot(0);
        ItemStack chest = tile.getStackInSlot(1);
        ItemStack legs = tile.getStackInSlot(2);
        ItemStack feet = tile.getStackInSlot(3);

        return (head != null || chest != null || legs != null || feet != null) && super.canLoad(tile, slot);
    }

    @Override
    public void load(TileEntityDisplayStation tile, int slot, EntityPlayer player)
    {
        player.openGui(TransformersMod.instance, TFGui.DISPLAY_STATION_ARMOR.guiId, player.worldObj, tile.xCoord, tile.yCoord, tile.zCoord);
    }
}
