package fiskfille.tf.client.displayable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.item.ModelFlamethrower;

public class DisplayableFlamethrower extends Displayable
{
    private ModelFlamethrower model = new ModelFlamethrower();
    
    @Override
    public void render(ItemStack itemstack)
    {
        bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/weapons/flame_thrower.png"));
                
        float f = 0.7F;
        float f1 = MathHelper.sin(((float)mc.thePlayer.ticksExisted) / 15.0F) * 0.05F;
        GL11.glScalef(f, f, f);
        GL11.glRotatef((float)mc.thePlayer.ticksExisted * 0.75F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.2F, 0.95F + f1, 0.0F);
        
        model.render();
    }
}
