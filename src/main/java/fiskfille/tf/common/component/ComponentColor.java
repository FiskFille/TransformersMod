package fiskfille.tf.common.component;

import fiskfille.tf.client.gui.GuiColor;
import fiskfille.tf.common.tileentity.TileEntityDisplayStation;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

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

        if (helmetTransformer != null && helmetTransformer == chestTransformer && chestTransformer == legsTransformer && legsTransformer == feetTransformer)
        {
            return super.canLoad(tile, slot);
        }

        return false;
    }

    @Override
    public void load(TileEntityDisplayStation tile, int slot)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiColor(tile));
    }
}
