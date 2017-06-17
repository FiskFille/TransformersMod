package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class ModelRendererBreakable extends ModelRendererTF
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
        this.baseModel = modelBase;
    }

    public ModelRendererBreakable(ModelBase modelBase, boolean render, int x, int y)
    {
        super(modelBase, x, y);
        this.baseModel = modelBase;
        this.renderBreaking = render;
    }

    public ModelRendererBreakable(ModelBase modelBase, int x, int y)
    {
        this(modelBase, true, x, y);
    }

    public ModelRendererBreakable(ModelBase modelBase)
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
        this.cubeList.add(new ModelBoxBreakable(this, this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F).setBoxName(name));
        return this;
    }

    @Override
    public ModelRenderer addBox(float x, float y, float z, int width, int height, int depth)
    {
        this.cubeList.add(new ModelBoxBreakable(this, this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F));
        return this;
    }

    @Override
    public void addBox(float x, float y, float z, int width, int height, int depth, float scale)
    {
        this.cubeList.add(new ModelBoxBreakable(this, this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, scale));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float scale)
    {
        this.displayList = this.displayLists[this.breaking ? 1 : 0];
        super.render(scale);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void compileDisplayList(float scale)
    {
        super.compileDisplayList(scale);
        this.displayLists[0] = this.displayList;
        this.displayLists[1] = GLAllocation.generateDisplayLists(1);

        boolean prevBreaking = this.breaking;
        this.breaking = true;

        GlStateManager.glNewList(this.displayLists[1], GL11.GL_COMPILE);

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer builder = tessellator.getBuffer();

        for (ModelBox cube : this.cubeList)
        {
            if (this.renderBreaking)
            {
                cube.render(builder, scale);
            }
        }

        this.breaking = prevBreaking;

        GlStateManager.glEndList();

        this.displayList = this.displayLists[this.breaking ? 1 : 0];
    }
}
