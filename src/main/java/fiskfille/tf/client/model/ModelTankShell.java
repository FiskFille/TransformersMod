package fiskfille.tf.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Tank Shell - gegy1000 Created using Tabula 4.0.0
 */
public class ModelTankShell extends ModelBase
{
    public ModelRenderer shell;
    
    public ModelTankShell()
    {
        this.textureWidth = 16;
        this.textureHeight = 8;
        this.shell = new ModelRenderer(this, 0, 0);
        this.shell.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.shell.addBox(-3.0F, -1.0F, -1.0F, 6, 2, 2);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.shell.render(f5);
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
