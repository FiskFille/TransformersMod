package fiskfille.tf.client.displayable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.item.ModelPurgesKatana;

public class DisplayablePurgesKatana extends Displayable
{
    private ModelPurgesKatana model = new ModelPurgesKatana();

    @Override
    public void render(ItemStack itemstack)
    {
        bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/weapons/purges_katana.png"));

        float f = 1.0F;
        float f1 = MathHelper.sin(mc.thePlayer.ticksExisted / 15.0F) * 0.1F;
        GL11.glScalef(f, f, f);
        GL11.glRotatef(mc.thePlayer.ticksExisted * 0.75F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0, 0.7F + f1, -0.55F);
        GL11.glRotatef(-90, 1, 0, 0);

        model.render();
    }
}
