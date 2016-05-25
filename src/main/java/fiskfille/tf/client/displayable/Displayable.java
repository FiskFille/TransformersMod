package fiskfille.tf.client.displayable;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class Displayable
{
    protected Minecraft mc = Minecraft.getMinecraft();

    public abstract void render(ItemStack itemstack);

    protected void bindTexture(ResourceLocation resourcelocation)
    {
        mc.getTextureManager().bindTexture(resourcelocation);
    }

    public float getBlockHeight(ItemStack itemstack)
    {
        return 1.0F;
    }
}
