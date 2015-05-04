package fiskfille.tf.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTankShell extends ModelBase
{
    public ModelRenderer shell;
    
    public ModelTankShell()
    {
        this.textureWidth = 16;
        this.textureHeight = 8;
        this.shell = new ModelRenderer(this, 0, 0);
        this.shell.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shell.addBox(-3.0F, -1.0F, -1.0F, 6, 2, 2);
        this.shell.rotateAngleY = (float) Math.PI / 2;
    }
    
    public void render()
    {
        this.shell.render(0.0625F);
    }
}