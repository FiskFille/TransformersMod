package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.helper.TFHelper;

@SideOnly(Side.CLIENT)
public abstract class TransformerModel
{
    public abstract ModelTransformerBase getMainModel();

    public abstract ModelVehicleBase getVehicleModel();

    public abstract ModelRendererTF[] getFeet();

    public abstract ModelRendererTF[] getLegs();

    public abstract ModelRendererTF getLowerArm();

    public abstract ModelRendererTF getUpperArm();

    public abstract ModelRendererTF getBody();

    public abstract ModelRendererTF getHead();

    public abstract ResourceLocation getTexture(Entity entity, String suffix);

    public abstract float getFootHeight();

    public ModelVehicleBase getStealthModel()
    {
        return null;
    }

    public void renderItem(EntityPlayer player, ItemStack stack)
    {
    }

    public void renderCape(EntityPlayer player)
    {
    }

    public void renderFirstPersonArm(EntityPlayer player)
    {
    }

    public boolean hasLightsLayer()
    {
        return false;
    }

    public final ModelVehicleBase getEffectiveVehicleModel(EntityPlayer player)
    {
        if (getStealthModel() != null && TFHelper.getStealthModeTimer(player) > 0 && TFHelper.isFullyTransformed(player))
        {
            return getStealthModel();
        }

        return getVehicleModel();
    }

    public abstract ModelTransformerBase getItemInventoryModel();
}
