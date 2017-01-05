package fiskfille.tf.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.common.tileentity.TileEntityRelayTower;

public class RenderItemRelayTower extends RenderItemTileEntity
{
    public RenderItemRelayTower()
    {
        super(new TileEntityRelayTower());
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        float scale = 0.65F;
        GL11.glScalef(scale, scale, scale);

        if (type == ItemRenderType.ENTITY || type == ItemRenderType.INVENTORY)
        {
            if (type == ItemRenderType.INVENTORY)
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
            else
            {
                GL11.glRotatef(180, 0, 1, 0);
            }

            GL11.glTranslatef(-0.5F, -0.9F, -0.5F);
        }
        else if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glTranslatef(0.5F, 0.0F, 0.5F);
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.FIRST_PERSON_MAP)
        {
            GL11.glTranslatef(0.5F, 0.0F, 0.5F);
        }

        TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.0F, 0.0F, 0.0F, 0.0F);
    }
}
