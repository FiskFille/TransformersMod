package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fiskfille.tf.TransformersAPI;
import fiskfille.tf.client.displayable.Displayable;
import fiskfille.tf.common.tileentity.TileEntityDisplayPedestal;

public class RenderDisplayPedestal extends TileEntitySpecialRenderer
{
    public void renderModelAt(TileEntityDisplayPedestal displayPillar, double x, double y, double z, float partialTicks)
    {
        ItemStack displayItem = displayPillar.getDisplayItem();
        
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);

        if (displayItem != null)
        {
            Displayable displayable = TransformersAPI.getDisplayableFor(displayItem.getItem());

            if (displayable != null)
            {
                GL11.glPushMatrix();
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                displayable.render(displayItem);
                GL11.glDisable(GL12.GL_RESCALE_NORMAL);
                GL11.glColor4f(1, 1, 1, 1);
                GL11.glPopMatrix();
            }
        }

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTicks)
    {
        renderModelAt((TileEntityDisplayPedestal) tileentity, x, y, z, partialTicks);
    }
}
