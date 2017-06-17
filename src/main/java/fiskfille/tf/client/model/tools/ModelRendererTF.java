package fiskfille.tf.client.model.tools;

import com.google.common.collect.Lists;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelRendererTF extends MowzieModelRenderer
{
    private List<ModelRenderer> hideUntil = Lists.newArrayList();

    public ModelRendererTF(ModelBase modelBase, String name)
    {
        super(modelBase, name);
    }

    public ModelRendererTF(ModelBase modelBase, int x, int y)
    {
        super(modelBase, x, y);
    }

    public ModelRendererTF(ModelBase modelBase)
    {
        super(modelBase);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float scale)
    {
        GlStateManager.pushMatrix();

        if (!this.isHidden)
        {
            if (this.showModel)
            {
                if (!this.compiled && this.hideUntil.isEmpty())
                {
                    this.compileDisplayList(scale);
                }

                GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
                GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);
                GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
                GlStateManager.translate(-this.rotationPointX * scale, -this.rotationPointY * scale, -this.rotationPointZ * scale);

                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
                {
                    if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F)
                    {
                        this.renderThis(scale);
                        this.renderChildren(scale);
                    }
                    else
                    {
                        GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
                        this.renderThis(scale);
                        this.renderChildren(scale);
                        GlStateManager.translate(-this.rotationPointX * scale, -this.rotationPointY * scale, -this.rotationPointZ * scale);
                    }
                }
                else
                {
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

                    if (this.rotateAngleZ != 0.0F)
                    {
                        GlStateManager.rotate(this.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (this.rotateAngleY != 0.0F)
                    {
                        GlStateManager.rotate(this.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (this.rotateAngleX != 0.0F)
                    {
                        GlStateManager.rotate(this.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
                    }

                    this.renderThis(scale);
                    this.renderChildren(scale);
                    GlStateManager.popMatrix();
                }

                GlStateManager.translate(-this.offsetX, -this.offsetY, -this.offsetZ);
                GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
            }
        }

        GlStateManager.popMatrix();
    }

    protected void renderThis(float scale)
    {
        if (this.hideUntil.isEmpty())
        {
            GlStateManager.callList(this.displayList);
        }
    }

    protected void renderChildren(float scale)
    {
        if (this.childModels != null)
        {
            for (ModelRenderer childModel : this.childModels)
            {
                ModelRendererTF model = (ModelRendererTF) childModel;
                List<ModelRenderer> list = new ArrayList<>(this.hideUntil);

                if (this.hideUntil.contains(model))
                {
                    list = Lists.newArrayList();
                }

                model.hideUntil = list;
                model.render(scale);
            }
        }
    }

    public void hideUntil(ModelRenderer... modelRenderers)
    {
        if (modelRenderers.length == 0)
        {
            this.hideUntil.clear();

            if (this.childModels != null)
            {
                for (ModelRenderer childModel : this.childModels)
                {
                    ModelRendererTF model = (ModelRendererTF) childModel;
                    model.hideUntil();
                }
            }

            return;
        }

        this.hideUntil.addAll(Arrays.asList(modelRenderers));
    }
}
