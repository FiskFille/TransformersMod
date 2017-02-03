package fiskfille.tf.common.component;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import fiskfille.tf.client.gui.GuiHandlerTF.TFGui;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;

public class ComponentColor extends Component
{
    @Override
    public boolean canLoad(TileEntityDisplayStation tile, int slot)
    {
        ItemStack head = tile.getStackInSlot(0);
        ItemStack chest = tile.getStackInSlot(1);
        ItemStack legs = tile.getStackInSlot(2);
        ItemStack feet = tile.getStackInSlot(3);
        Transformer helmetTransformer = TFHelper.getTransformerFromArmor(head);
        Transformer chestTransformer = TFHelper.getTransformerFromArmor(chest);
        Transformer legsTransformer = TFHelper.getTransformerFromArmor(legs);
        Transformer feetTransformer = TFHelper.getTransformerFromArmor(feet);

        return helmetTransformer != null && helmetTransformer == chestTransformer && chestTransformer == legsTransformer && legsTransformer == feetTransformer && super.canLoad(tile, slot);

    }

    @Override
    public void load(TileEntityDisplayStation tile, int slot, EntityPlayer player)
    {
        TFGui.DISPLAY_STATION_COLOR.open(player, tile);
    }
}
