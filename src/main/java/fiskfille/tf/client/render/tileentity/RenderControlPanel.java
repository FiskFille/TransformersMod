package fiskfille.tf.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.block.BlockGroundBridgeControl;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;

public class RenderControlPanel extends TileEntitySpecialRenderer
{
    private ModelChest model = new ModelChest();

    public void render(TileEntityControlPanel tileentity, double x, double y, double z, float partialTicks)
    {
        int metadata = 0;

        if (tileentity.getWorldObj() != null)
        {
            metadata = tileentity.getBlockMetadata();
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
        GL11.glScalef(1.0F, -1F, -1F);
        GL11.glRotatef(metadata * 90 + 180, 0.0F, 1.0F, 0.0F);
        
//        bindTexture(new ResourceLocation(TransformersMod.modid, "textures/models/tiles/ground_bridge_control_panel.png"));
//        model.render(null, 0, 0, 0, 0, 0, 0.0625F);
        
        Minecraft mc = Minecraft.getMinecraft();
        ItemRenderer itemRenderer = new ItemRenderer(mc);
        
        if (!BlockGroundBridgeControl.isBlockSideOfPanel(metadata))
        {
        	GL11.glTranslatef(0.5F, 0, 0);
        	GL11.glScalef(2, 1, 1);
        	itemRenderer.renderItem(mc.thePlayer, new ItemStack(Blocks.iron_block), 0);
        }
        
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        render((TileEntityControlPanel)tileentity, d, d1, d2, f);
    } 
}
