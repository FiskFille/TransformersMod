package fiskfille.tf.client.displayable;

import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.common.tick.ClientTickHandler;
import fiskfille.tf.common.transformer.base.Transformer;

public class DisplayableVehicle extends Displayable
{
    @Override
    public void render(ItemStack itemstack)
    {
        TransformerModel tfModel = getModelFromStack(itemstack);
        ModelVehicleBase vehicle = tfModel.getVehicleModel();

        if (vehicle != null)
        {
            GL11.glRotatef((mc.thePlayer.ticksExisted + ClientTickHandler.renderTick) * 0.5F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.2F, 0.0F);

            float scale = 0.75F;
            GL11.glScalef(scale, scale, scale);
            vehicle.renderDisplayVehicle(itemstack);
        }
    }

    public TransformerModel getModelFromStack(ItemStack displayItem)
    {
        Transformer transformer = TransformersAPI.getTransformers().get(displayItem.getItemDamage());

        if (transformer != null)
        {
            return TFModelRegistry.getModel(transformer);
        }

        return null;
    }
}
