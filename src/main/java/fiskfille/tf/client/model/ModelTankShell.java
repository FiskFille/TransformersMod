package fiskfille.tf.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTankShell extends ModelBase
{
    public ModelRenderer shell;

    public ModelTankShell()
    {
        textureWidth = 16;
        textureHeight = 8;
        shell = new ModelRenderer(this, 0, 0);
        shell.setRotationPoint(0.0F, 0.0F, 0.0F);
        shell.addBox(-3.0F, -1.0F, -1.0F, 6, 2, 2);
        shell.rotateAngleY = (float) Math.PI / 2;
    }

    public void render()
    {
        shell.render(0.0625F);
    }
}