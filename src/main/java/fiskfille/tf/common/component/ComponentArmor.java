package fiskfille.tf.common.component;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.network.MessageOpenGuiSimple;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

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
        TFNetworkManager.networkWrapper.sendToServer(new MessageOpenGuiSimple(player, TFGui.DISPLAY_STATION_ARMOR.guiId, tile.xCoord, tile.yCoord, tile.zCoord));
    }
}
