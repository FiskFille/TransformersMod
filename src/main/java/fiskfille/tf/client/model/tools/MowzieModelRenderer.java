package fiskfille.tf.client.model.tools;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

    public void postRenderParentChain(float par1)
    {
        if (parent instanceof MowzieModelRenderer)
        {
            ((MowzieModelRenderer) parent).postRenderParentChain(par1);
        }
        else if (parent != null)
        {
            parent.postRender(par1);
        }

        postRender(par1);
    }

    /**
     * Returns the parent of this ModelRenderer
     */
    public ModelRenderer getParent()
    {
        return parent;
    }

    /**
     * Sets the parent of this ModelRenderer
     */
    private void setParent(ModelRenderer modelRenderer)
    {
        parent = modelRenderer;
    }

    /**
     * Set the initialization pose to the current pose
     */
    public void setInitValuesToCurrentPose()
    {
        initRotateAngleX = rotateAngleX;
        initRotateAngleY = rotateAngleY;
        initRotateAngleZ = rotateAngleZ;

        initRotationPointX = rotationPointX;
        initRotationPointY = rotationPointY;
        initRotationPointZ = rotationPointZ;

        initOffsetX = offsetX;
        initOffsetY = offsetY;
        initOffsetZ = offsetZ;

        hasInitPose = true;
    }

    /**
     * Resets the pose to init pose
     */
    public void setCurrentPoseToInitValues()
    {
        if (hasInitPose)
        {
            rotateAngleX = initRotateAngleX;
            rotateAngleY = initRotateAngleY;
            rotateAngleZ = initRotateAngleZ;

            rotationPointX = initRotationPointX;
            rotationPointY = initRotationPointY;
            rotationPointZ = initRotationPointZ;

            offsetX = initOffsetX;
            offsetY = initOffsetY;
            offsetZ = initOffsetZ;
        }
    }

    public void setRotationAngles(float x, float y, float z)
    {
        rotateAngleX = x;
        rotateAngleY = y;
        rotateAngleZ = z;
    }

    /**
     * Resets all rotation points.
     */
    public void resetAllRotationPoints()
    {
        rotationPointX = initRotationPointX;
        rotationPointY = initRotationPointY;
        rotationPointZ = initRotationPointZ;
    }

    /**
     * Resets X rotation point.
     */
    public void resetXRotationPoints()
    {
        rotationPointX = initRotationPointX;
    }

    /**
     * Resets Y rotation point.
     */
    public void resetYRotationPoints()
    {
        rotationPointY = initRotationPointY;
    }

    /**
     * Resets Z rotation point.
     */
    public void resetZRotationPoints()
    {
        rotationPointZ = initRotationPointZ;
    }

    /**
     * Resets all rotations.
     */
    public void resetAllRotations()
    {
        rotateAngleX = initRotateAngleX;
        rotateAngleY = initRotateAngleY;
        rotateAngleZ = initRotateAngleZ;
    }

    /**
     * Resets X rotation.
     */
    public void resetXRotations()
    {
        rotateAngleX = initRotateAngleX;
    }

    /**
     * Resets Y rotation.
     */
    public void resetYRotations()
    {
        rotateAngleY = initRotateAngleY;
    }

    /**
     * Resets Z rotation.
     */
    public void resetZRotations()
    {
        rotateAngleZ = initRotateAngleZ;
    }

    /**
     * Copies the rotation point coordinates.
     */
    public void copyAllRotationPoints(MowzieModelRenderer target)
    {
        rotationPointX = target.rotationPointX;
        rotationPointY = target.rotationPointY;
        rotationPointZ = target.rotationPointZ;
    }

    /**
     * Copies X rotation point.
     */
    public void copyXRotationPoint(MowzieModelRenderer target)
    {
        rotationPointX = target.rotationPointX;
    }

    /**
     * Copies Y rotation point.
     */
    public void copyYRotationPoint(MowzieModelRenderer target)
    {
        rotationPointY = target.rotationPointY;
    }

    /**
     * Copies Z rotation point.
     */
    public void copyZRotationPoint(MowzieModelRenderer target)
    {
        rotationPointZ = target.rotationPointZ;
    }

    public void renderWithParents(float partialTicks)
    {
        if (parent instanceof MowzieModelRenderer)
        {
            ((MowzieModelRenderer) parent).renderWithParents(partialTicks);
        }
        else if (parent != null)
        {
            parent.render(partialTicks);
        }

        render(partialTicks);
    }

    public void setScale(float x, float y, float z)
    {
        scaleX = x;
        scaleY = y;
        scaleZ = z;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float f)
    {
        GL11.glPushMatrix();

        if (!isHidden)
        {
            if (showModel)
            {
                if (!compiled)
                {
                    compileDisplayList(f);
                }

                GL11.glTranslatef(rotationPointX * f, rotationPointY * f, rotationPointZ * f);
                GL11.glTranslatef(offsetX, offsetY, offsetZ);
                GL11.glScalef(scaleX, scaleY, scaleZ);
                GL11.glTranslatef(-rotationPointX * f, -rotationPointY * f, -rotationPointZ * f);
                int i;

                if (rotateAngleX == 0.0F && rotateAngleY == 0.0F && rotateAngleZ == 0.0F)
                {
                    if (rotationPointX == 0.0F && rotationPointY == 0.0F && rotationPointZ == 0.0F)
                    {
                        GL11.glCallList(displayList);

                        if (childModels != null)
                        {
                            for (i = 0; i < childModels.size(); ++i)
                            {
                                ((MowzieModelRenderer) childModels.get(i)).render(f);
                            }
                        }
                    }
                    else
                    {
                        GL11.glTranslatef(rotationPointX * f, rotationPointY * f, rotationPointZ * f);
                        GL11.glCallList(displayList);

                        if (childModels != null)
                        {
                            for (i = 0; i < childModels.size(); ++i)
                            {
                                ((MowzieModelRenderer) childModels.get(i)).render(f);
                            }
                        }

                        GL11.glTranslatef(-rotationPointX * f, -rotationPointY * f, -rotationPointZ * f);
                    }
                }
                else
                {
                    GL11.glPushMatrix();
                    GL11.glTranslatef(rotationPointX * f, rotationPointY * f, rotationPointZ * f);

                    if (rotateAngleZ != 0.0F)
                    {
                        GL11.glRotatef(rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (rotateAngleY != 0.0F)
                    {
                        GL11.glRotatef(rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (rotateAngleX != 0.0F)
                    {
                        GL11.glRotatef(rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
                    }

                    GL11.glCallList(displayList);

                    if (childModels != null)
                    {
                        for (i = 0; i < childModels.size(); ++i)
                        {
                            ((MowzieModelRenderer) childModels.get(i)).render(f);
                        }
                    }

                    GL11.glPopMatrix();
                }

                GL11.glTranslatef(-offsetX, -offsetY, -offsetZ);
                GL11.glScalef(1.0F / scaleX, 1.0F / scaleY, 1.0F / scaleZ);
            }
        }

        GL11.glPopMatrix();
    }

    @SideOnly(Side.CLIENT)
    protected void compileDisplayList(float f)
    {
        displayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(displayList, GL11.GL_COMPILE);
        Tessellator tessellator = Tessellator.instance;

        for (Object cube : cubeList)
        {
            ((ModelBox) cube).render(tessellator, f);
        }

        GL11.glEndList();
        compiled = true;
    }

    public void renderWithParentRotations(float partialTicks)
    {
        float x = getParentRotX();
        float y = getParentRotY();
        float z = getParentRotZ();

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
        if (getParent() instanceof MowzieModelRenderer)
        {
            return ((MowzieModelRenderer) this.getParent()).getParentRotX() + this.rotateAngleX;
        }

        return this.rotateAngleX;
    }

    public float getParentRotY()
    {
        if (getParent() instanceof MowzieModelRenderer)
        {
            return ((MowzieModelRenderer) this.getParent()).getParentRotY() + this.rotateAngleY;
        }

        return this.rotateAngleY;
    }

    public float getParentRotZ()
    {
        if (getParent() instanceof MowzieModelRenderer)
        {
            return ((MowzieModelRenderer) this.getParent()).getParentRotZ() + this.rotateAngleZ;
        }

        return this.rotateAngleZ;
    }
}