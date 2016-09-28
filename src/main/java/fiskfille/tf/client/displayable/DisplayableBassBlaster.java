package fiskfille.tf.client.displayable;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.event.ClientEventHandler;
import fiskfille.tf.client.model.item.ModelBassBlaster;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class DisplayableBassBlaster extends Displayable
{
    private ModelBassBlaster model = new ModelBassBlaster();

    @Override
    public void render(ItemStack itemstack)
    {
        bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/weapons/bass_blaster.png"));

        float f = 0.75F;
        float f1 = MathHelper.sin((mc.thePlayer.ticksExisted + ClientEventHandler.renderTick) / 15.0F) * 0.1F;
        GL11.glScalef(f, f, f);
        GL11.glRotatef(mc.thePlayer.ticksExisted * 0.75F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0, 0.95F + f1, 0.1F);

        model.render();
    }
}
