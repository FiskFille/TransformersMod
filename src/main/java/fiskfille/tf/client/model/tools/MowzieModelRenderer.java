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
    
    private boolean compiled;
    private int displayList;
    
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
            ((MowzieModelRenderer) this.parent).postRenderParentChain(par1);
        }
        else if (parent != null)
        {
            parent.postRender(par1);
        }
        
        this.postRender(par1);
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
        this.parent = modelRenderer;
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
        if(hasInitPose)
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
        if (parent instanceof MowzieModelRenderer)
        {
            ((MowzieModelRenderer) this.parent).renderWithParents(partialTicks);
        }
        else if (parent != null)
        {
            parent.render(partialTicks);
        }
        
        this.render(partialTicks);
    }
    
    public void setScale(float x, float y, float z)
    {
        this.scaleX = x;
        this.scaleY = y;
        this.scaleZ = z;
    }
    
    @SideOnly(Side.CLIENT)
    public void render(float p_78785_1_)
    {
    	GL11.glPushMatrix();
    	
        if (!this.isHidden)
        {
            if (this.showModel)
            {
            	if (!this.compiled)
                {
                    this.compileDisplayList(p_78785_1_);
                }
                
            	float f5 = 0.0625F;
                GL11.glTranslatef(this.rotationPointX * f5, this.rotationPointY * f5, this.rotationPointZ * f5);
                GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
            	GL11.glScalef(scaleX, scaleY, scaleZ);
            	GL11.glTranslatef(-this.rotationPointX * f5, -this.rotationPointY * f5, -this.rotationPointZ * f5);
                int i;
                
                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
                {
                    if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F)
                    {
                        GL11.glCallList(this.displayList);

                        if (this.childModels != null)
                        {
                            for (i = 0; i < this.childModels.size(); ++i)
                            {
                                ((MowzieModelRenderer)this.childModels.get(i)).render(p_78785_1_);
                            }
                        }
                    }
                    else
                    {
                        GL11.glTranslatef(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);
                        GL11.glCallList(this.displayList);

                        if (this.childModels != null)
                        {
                            for (i = 0; i < this.childModels.size(); ++i)
                            {
                                ((MowzieModelRenderer)this.childModels.get(i)).render(p_78785_1_);
                            }
                        }

                        GL11.glTranslatef(-this.rotationPointX * p_78785_1_, -this.rotationPointY * p_78785_1_, -this.rotationPointZ * p_78785_1_);
                    }
                }
                else
                {
                    GL11.glPushMatrix();
                    GL11.glTranslatef(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);

                    if (this.rotateAngleZ != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (this.rotateAngleY != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (this.rotateAngleX != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                    }

                    GL11.glCallList(this.displayList);

                    if (this.childModels != null)
                    {
                        for (i = 0; i < this.childModels.size(); ++i)
                        {
                            ((MowzieModelRenderer)this.childModels.get(i)).render(p_78785_1_);
                        }
                    }

                    GL11.glPopMatrix();
                }
                
                GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
                GL11.glScalef(1.0F / scaleX, 1.0F / scaleY, 1.0F / scaleZ);
            }
        }
        
        GL11.glPopMatrix();
    }
    
    @SideOnly(Side.CLIENT)
    private void compileDisplayList(float p_78788_1_)
    {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(this.displayList, GL11.GL_COMPILE);
        Tessellator tessellator = Tessellator.instance;

        for (int i = 0; i < this.cubeList.size(); ++i)
        {
            ((ModelBox)this.cubeList.get(i)).render(tessellator, p_78788_1_);
        }

        GL11.glEndList();
        this.compiled = true;
    }
}