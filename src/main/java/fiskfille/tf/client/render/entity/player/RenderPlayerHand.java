package fiskfille.tf.client.render.entity.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;

@SideOnly(Side.CLIENT)
public class RenderPlayerHand extends RenderPlayer
{
    private Minecraft mc = Minecraft.getMinecraft();
    public Render parent;

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        if (TFHelper.getTransformationTimer(player) == 0)
        {
            Transformer transformer = TFHelper.getTransformerFromArmor(player, 2);

            if (transformer != null)
            {
                TransformerModel model = TFModelRegistry.getModel(transformer);
                ModelTransformerBase mainModel = model.getMainModel();
                ModelRenderer upperArm = model.getUpperArm();

                if (upperArm == null)
                {
                    upperArm = mainModel.bipedRightArm;
                }

                mainModel.onGround = 0.0F;
                mainModel.setToInitPose();

                float scale = 1.2F;
                GL11.glScalef(scale, scale, scale);

                model.renderFirstPersonArm(player);
                GL11.glTranslatef(0.0F, 0.35F, 0.0F);
                TFRenderHelper.setupRenderLayers(player, player.getCurrentArmor(2), upperArm);
            }
            else
            {
                modelBipedMain.bipedRightArm.showModel = true;
                super.renderFirstPersonArm(player);
            }
        }
    }

    public void setParent(Render render)
    {
        parent = render;
    }
}
