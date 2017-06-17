package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;

public class ModelRendererPartial extends MowzieModelRenderer
{
    private int textureOffsetX;
    private int textureOffsetY;
    private ModelBase baseModel;

    public ModelRendererPartial(ModelBase modelBase, String name)
    {
        super(modelBase, name);
        this.baseModel = modelBase;
    }

    public ModelRendererPartial(ModelBase modelBase, int x, int y)
    {
        super(modelBase, x, y);
        this.baseModel = modelBase;
    }

    public ModelRendererPartial(ModelBase modelBase)
    {
        super(modelBase);
        this.baseModel = modelBase;
    }

    @Override
    public ModelRenderer setTextureOffset(int x, int y)
    {
        this.textureOffsetX = x;
        this.textureOffsetY = y;
        return super.setTextureOffset(x, y);
    }

    @Override
    public ModelRenderer addBox(String name, float x, float y, float z, int width, int height, int depth)
    {
        name = this.boxName + "." + name;
        TextureOffset offset = this.baseModel.getTextureOffset(name);
        this.setTextureOffset(offset.textureOffsetX, offset.textureOffsetY);
        this.cubeList.add(new ModelBoxPartial(this, this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F).setBoxName(name));
        return this;
    }

    @Override
    public ModelRenderer addBox(float x, float y, float z, int width, int height, int depth)
    {
        this.cubeList.add(new ModelBoxPartial(this, this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F));
        return this;
    }

    @Override
    public void addBox(float x, float y, float z, int width, int height, int depth, float scale)
    {
        this.cubeList.add(new ModelBoxPartial(this, this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, scale));
    }

    public ModelBoxPartial getBox()
    {
        return (ModelBoxPartial) this.cubeList.get(0);
    }
}
