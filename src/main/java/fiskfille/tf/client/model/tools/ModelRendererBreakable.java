package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModelRendererBreakable extends MowzieModelRenderer
{
    private int textureOffsetX;
    private int textureOffsetY;
    private ModelBase baseModel;
    
    public boolean breaking = false;
    public boolean renderBreaking = true;
    protected int[] displayLists = new int[2];
    
    public ModelRendererBreakable(ModelBase modelBase, String name)
    {
        super(modelBase, name);
        baseModel = modelBase;
    }

    public ModelRendererBreakable(ModelBase modelBase, boolean render, int x, int y)
    {
        super(modelBase, x, y);
        setTextureOffset(x, y);
        baseModel = modelBase;
        renderBreaking = render;
    }
    
    public ModelRendererBreakable(ModelBase modelBase, int x, int y)
    {
        this(modelBase, true, x, y);
    }

    public ModelRendererBreakable(ModelBase modelBase)
    {
        super(modelBase);
    }
    
    @Override
    public ModelRenderer setTextureOffset(int x, int y)
    {
        textureOffsetX = x;
        textureOffsetY = y;
        return super.setTextureOffset(x, y);
    }
    
    @Override
    public ModelRenderer addBox(String name, float p_78786_2_, float p_78786_3_, float p_78786_4_, int p_78786_5_, int p_78786_6_, int p_78786_7_)
    {
        name = boxName + "." + name;
        TextureOffset offset = baseModel.getTextureOffset(name);
        setTextureOffset(offset.textureOffsetX, offset.textureOffsetY);
        cubeList.add(new ModelBox(this, textureOffsetX, textureOffsetY, p_78786_2_, p_78786_3_, p_78786_4_, p_78786_5_, p_78786_6_, p_78786_7_, 0.0F).func_78244_a(name));
        return this;
    }

    @Override
    public ModelRenderer addBox(float p_78789_1_, float p_78789_2_, float p_78789_3_, int p_78789_4_, int p_78789_5_, int p_78789_6_)
    {
        cubeList.add(new ModelBoxBreakable(this, textureOffsetX, textureOffsetY, p_78789_1_, p_78789_2_, p_78789_3_, p_78789_4_, p_78789_5_, p_78789_6_, 0.0F));
        return this;
    }

    @Override
    public void addBox(float p_78790_1_, float p_78790_2_, float p_78790_3_, int p_78790_4_, int p_78790_5_, int p_78790_6_, float p_78790_7_)
    {
        cubeList.add(new ModelBoxBreakable(this, textureOffsetX, textureOffsetY, p_78790_1_, p_78790_2_, p_78790_3_, p_78790_4_, p_78790_5_, p_78790_6_, p_78790_7_));
    }
    
    @Override
    public void render(float f)
    {
        displayList = displayLists[breaking ? 1 : 0];
        super.render(f);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    protected void compileDisplayList(float f)
    {
        super.compileDisplayList(f);
        displayLists[0] = displayList;
        displayLists[1] = GLAllocation.generateDisplayLists(1);
        
        boolean prevBreaking = breaking;
        breaking = true;
        GL11.glNewList(displayLists[1], GL11.GL_COMPILE);
        Tessellator tessellator = Tessellator.instance;

        for (Object cube : cubeList)
        {
            if (renderBreaking)
            {
                ((ModelBox) cube).render(tessellator, f);
            }
        }

        breaking = prevBreaking;
        GL11.glEndList();
        
        displayList = displayLists[breaking ? 1 : 0];
    }
}
