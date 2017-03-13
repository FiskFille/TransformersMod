package fiskfille.tf.common.component;

import net.minecraft.entity.player.EntityPlayer;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.network.MessageOpenGui;
import fiskfille.tf.common.network.base.TFNetworkManager;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;

public class ComponentArmor extends Component
{
    @Override
    public void load(TileEntityDisplayStation tile, int slot, EntityPlayer player)
    {
        TFNetworkManager.networkWrapper.sendToServer(new MessageOpenGui(player, TFGui.DISPLAY_STATION_ARMOR.guiId, tile.xCoord, tile.yCoord, tile.zCoord));
    }
}
