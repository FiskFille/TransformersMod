package fiskfille.tf.client.render.tileentity;

import fiskfille.tf.client.displayable.DisplayableRender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tileentity.ModelDisplayPillar;
import fiskfille.tf.common.tileentity.TileEntityDisplayPillar;

public class RenderDisplayPillar extends TileEntitySpecialRenderer
{
    private ResourceLocation texture = new ResourceLocation(TransformersMod.modid + ":textures/models/tiles/display_pillar.png");
    private ModelDisplayPillar model = new ModelDisplayPillar();
    
    public void renderModelAt(TileEntityDisplayPillar displayPillar, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        bindTexture(texture);
        model.render();
        
        ItemStack displayItem = displayPillar.getDisplayItem();
        
        if (displayItem != null)
        {
            DisplayableRender displayable = TransformersAPI.getDisplayableFor(displayItem.getItem());
            
            if (displayable != null)
            {
            	displayable.render(displayItem);
            }
        }
        
        GL11.glPopMatrix();
    }    
    
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks)
    {
        renderModelAt((TileEntityDisplayPillar)tileentity, x, y, z, partialTicks);
    }
}