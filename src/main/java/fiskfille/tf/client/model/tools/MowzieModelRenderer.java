package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * @author BobMowzie, gegy1000, FiskFille
 */
@SideOnly(Side.CLIENT)
public class MowzieModelRenderer extends ModelRenderer
{
    public float initRotateAngleX;
    public float initRotateAngleY;
    public float initRotateAngleZ;

    public float initOffsetX;
    public float initOffsetY;
    public float initOffsetZ;

    public float initRotationPointX;
    public float initRotationPointY;
    public float initRotationPointZ;

    public float scaleX = 1.0F;
    public float scaleY = 1.0F;
    public float scaleZ = 1.0F;

    protected boolean compiled;
    protected int displayList;

    public ModelRenderer parent;
    public boolean hasInitPose;

    public MowzieModelRenderer(ModelBase modelBase, String name)
    {
        super(modelBase, name);
    }

    public MowzieModelRenderer(ModelBase modelBase, int x, int y)
    {
        super(modelBase, x, y);

        if (modelBase instanceof MowzieModelBase)
        {
            MowzieModelBase mowzieModelBase = (MowzieModelBase) modelBase;

            mowzieModelBase.addPart(this);
        }
    }

    public MowzieModelRenderer(ModelBase modelBase)
    {
        super(modelBase);
    }

    @Override
    public void addChild(ModelRenderer renderer)
    {
        super.addChild(renderer);

        if (renderer instanceof MowzieModelRenderer)
        {
            ((MowzieModelRenderer) renderer).setParent(this);
        }
    }

    public void postRenderParentChain(float f)
    {
        if (this.parent instanceof MowzieModelRenderer)
        {
            ((MowzieModelRenderer) this.parent).postRenderParentChain(f);
        }
        else if (this.parent != null)
        {
            this.parent.postRender(f);
        }

        this.postRender(f);
    }

    /**
     * Returns the parent of this ModelRenderer
     */
    public ModelRenderer getParent()
    {
        return this.parent;
    }

    /**
     * Sets the parent of this ModelRenderer
     */
    private void setParent(ModelRenderer modelRenderer)
    {
        this.parent = modelRenderer;
    }

    /**
     * Set the initialization pose to the current pose
     */
    public void setInitValuesToCurrentPose()
    {
        this.initRotateAngleX = this.rotateAngleX;
        this.initRotateAngleY = this.rotateAngleY;
        this.initRotateAngleZ = this.rotateAngleZ;

        this.initRotationPointX = this.rotationPointX;
        this.initRotationPointY = this.rotationPointY;
        this.initRotationPointZ = this.rotationPointZ;

        this.initOffsetX = this.offsetX;
        this.initOffsetY = this.offsetY;
        this.initOffsetZ = this.offsetZ;

        this.hasInitPose = true;
    }

    /**
     * Resets the pose to init pose
     */
    public void setCurrentPoseToInitValues()
    {
        if (this.hasInitPose)
        {
            this.rotateAngleX = this.initRotateAngleX;
            this.rotateAngleY = this.initRotateAngleY;
            this.rotateAngleZ = this.initRotateAngleZ;

            this.rotationPointX = this.initRotationPointX;
            this.rotationPointY = this.initRotationPointY;
            this.rotationPointZ = this.initRotationPointZ;

            this.offsetX = this.initOffsetX;
            this.offsetY = this.initOffsetY;
            this.offsetZ = this.initOffsetZ;
        }
    }

    public void setRotationAngles(float x, float y, float z)
    {
        this.rotateAngleX = x;
        this.rotateAngleY = y;
        this.rotateAngleZ = z;
    }

    /**
     * Resets all rotation points.
     */
    public void resetAllRotationPoints()
    {
        this.rotationPointX = this.initRotationPointX;
        this.rotationPointY = this.initRotationPointY;
        this.rotationPointZ = this.initRotationPointZ;
    }

    /**
     * Resets X rotation point.
     */
    public void resetXRotationPoints()
    {
        this.rotationPointX = this.initRotationPointX;
    }

    /**
     * Resets Y rotation point.
     */
    public void resetYRotationPoints()
    {
        this.rotationPointY = this.initRotationPointY;
    }

    /**
     * Resets Z rotation point.
     */
    public void resetZRotationPoints()
    {
        this.rotationPointZ = this.initRotationPointZ;
    }

    /**
     * Resets all rotations.
     */
    public void resetAllRotations()
    {
        this.rotateAngleX = this.initRotateAngleX;
        this.rotateAngleY = this.initRotateAngleY;
        this.rotateAngleZ = this.initRotateAngleZ;
    }

    /**
     * Resets X rotation.
     */
    public void resetXRotations()
    {
        this.rotateAngleX = this.initRotateAngleX;
    }

    /**
     * Resets Y rotation.
     */
    public void resetYRotations()
    {
        this.rotateAngleY = this.initRotateAngleY;
    }

    /**
     * Resets Z rotation.
     */
    public void resetZRotations()
    {
        this.rotateAngleZ = this.initRotateAngleZ;
    }

    /**
     * Copies the rotation point coordinates.
     */
    public void copyAllRotationPoints(MowzieModelRenderer target)
    {
        this.rotationPointX = target.rotationPointX;
        this.rotationPointY = target.rotationPointY;
        this.rotationPointZ = target.rotationPointZ;
    }

    /**
     * Copies X rotation point.
     */
    public void copyXRotationPoint(MowzieModelRenderer target)
    {
        this.rotationPointX = target.rotationPointX;
    }

    /**
     * Copies Y rotation point.
     */
    public void copyYRotationPoint(MowzieModelRenderer target)
    {
        this.rotationPointY = target.rotationPointY;
    }

    /**
     * Copies Z rotation point.
     */
    public void copyZRotationPoint(MowzieModelRenderer target)
    {
        this.rotationPointZ = target.rotationPointZ;
    }

    public void renderWithParents(float partialTicks)
    {
        if (this.parent instanceof MowzieModelRenderer)
        {
            ((MowzieModelRenderer) this.parent).renderWithParents(partialTicks);
        }
        else if (this.parent != null)
        {
            this.parent.render(partialTicks);
        }

        this.render(partialTicks);
    }

    public void setScale(float x, float y, float z)
    {
        this.scaleX = x;
        this.scaleY = y;
        this.scaleZ = z;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float f)
    {
        GlStateManager.pushMatrix();

        if (!this.isHidden)
        {
            if (this.showModel)
            {
                if (!this.compiled)
                {
                    this.compileDisplayList(f);
                }

                GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
                GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);
                GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
                GlStateManager.translate(-this.rotationPointX * f, -this.rotationPointY * f, -this.rotationPointZ * f);
                int i;

                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
                {
                    if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F)
                    {
                        GlStateManager.callList(this.displayList);

                        if (this.childModels != null)
                        {
                            for (i = 0; i < this.childModels.size(); ++i)
                            {
                                this.childModels.get(i).render(f);
                            }
                        }
                    }
                    else
                    {
                        GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
                        GlStateManager.callList(this.displayList);

                        if (this.childModels != null)
                        {
                            for (i = 0; i < this.childModels.size(); ++i)
                            {
                                this.childModels.get(i).render(f);
                            }
                        }

                        GlStateManager.translate(-this.rotationPointX * f, -this.rotationPointY * f, -this.rotationPointZ * f);
                    }
                }
                else
                {
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);

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

                    GlStateManager.callList(this.displayList);

                    if (this.childModels != null)
                    {
                        for (i = 0; i < this.childModels.size(); ++i)
                        {
                            this.childModels.get(i).render(f);
                        }
                    }

                    GlStateManager.popMatrix();
                }

                GlStateManager.translate(-this.offsetX, -this.offsetY, -this.offsetZ);
                GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
            }
        }

        GlStateManager.popMatrix();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void postRender(float f)
    {
        if (!this.isHidden)
        {
            if (this.showModel)
            {
                if (!this.compiled)
                {
                    this.compileDisplayList(f);
                }

                GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
                GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);
                GlStateManager.translate(-this.rotationPointX * f, -this.rotationPointY * f, -this.rotationPointZ * f);

                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
                {
                    if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F)
                    {
                        GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);
                    }
                }
                else
                {
                    GlStateManager.translate(this.rotationPointX * f, this.rotationPointY * f, this.rotationPointZ * f);

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
                }

                GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected void compileDisplayList(float scale)
    {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GlStateManager.glNewList(this.displayList, GL11.GL_COMPILE);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer builder = tessellator.getBuffer();

        for (ModelBox cube : this.cubeList)
        {
            cube.render(builder, scale);
        }

        GlStateManager.glEndList();
        this.compiled = true;
    }

    public void renderWithParentRotations(float partialTicks)
    {
        float x = this.getParentRotX();
        float y = this.getParentRotY();
        float z = this.getParentRotZ();

        this.rotateAngleX -= x;
        this.rotateAngleY -= y;
        this.rotateAngleZ -= z;

        this.render(partialTicks);

        this.rotateAngleX += x;
        this.rotateAngleY += y;
        this.rotateAngleZ += z;
    }

    public float getParentRotX()
    {
        if (this.getParent() instanceof MowzieModelRenderer)
        {
            return ((MowzieModelRenderer) this.getParent()).getParentRotX() + this.rotateAngleX;
        }

        return this.rotateAngleX;
    }

    public float getParentRotY()
    {
        if (this.getParent() instanceof MowzieModelRenderer)
        {
            return ((MowzieModelRenderer) this.getParent()).getParentRotY() + this.rotateAngleY;
        }

        return this.rotateAngleY;
    }

    public float getParentRotZ()
    {
        if (this.getParent() instanceof MowzieModelRenderer)
        {
            return ((MowzieModelRenderer) this.getParent()).getParentRotZ() + this.rotateAngleZ;
        }

        return this.rotateAngleZ;
    }
}
