package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelDisplayPillar;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;
import fiskfille.tf.common.transformer.base.Transformer;

public class RenderDisplayPillar extends TileEntitySpecialRenderer
{
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/display_pillar.png");
    private ModelDisplayPillar model;
    private ItemRenderer itemRenderer;
    
    public RenderDisplayPillar()
    {
        model = new ModelDisplayPillar();
        itemRenderer = new ItemRenderer(Minecraft.getMinecraft());
    }
    
    public void renderModelAt(TileEntityDisplayPillar displayPillar, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        this.bindTexture(texture);
        model.renderAll();
        
        ItemStack displayItem = displayPillar.getDisplayItem();
        
        if (displayItem != null)
        {
            this.bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/" + getTextureFromStack(displayItem)));
            ModelVehicleBase vehicle = getModelFromStack(displayItem);
            
            if (vehicle != null)
            {
                GL11.glRotatef(Minecraft.getMinecraft().thePlayer.ticksExisted, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.2F/* + (vehicle == skystrike ? 0.15F : 0.0F)*/, 0.0F);
                
                float scale = 0.75F;
                GL11.glScalef(scale, scale, scale);
                vehicle.render();
            }
        }
        
        GL11.glPopMatrix();
    }
    
    public ModelVehicleBase getModelFromStack(ItemStack displayItem)
    {
        Transformer transformer = TransformersAPI.getTransformers().get(displayItem.getItemDamage());
        
        if (transformer != null)
        {
            return TFModelRegistry.getModel(transformer).getVehicleModel();
        }
        
        return null;
    }
    
    public String getTextureFromStack(ItemStack displayItem)
    {
        Transformer transformer = TransformersAPI.getTransformers().get(displayItem.getItemDamage());
        
        if (transformer != null)
        {
            String name = transformer.getName().toLowerCase().replaceAll(" ", "_");
            return name + "/" + name + ".png";
        }
        
        return null;
    }
    
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks)
    {
        renderModelAt((TileEntityDisplayPillar) tileentity, x, y, z, partialTicks);
    }
}