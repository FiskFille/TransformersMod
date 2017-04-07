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
import fiskfille.tf.common.tick.ClientTickHandler;
import fiskfille.tf.common.transformer.base.Transformer;
import fiskfille.tf.helper.TFHelper;
import fiskfille.tf.helper.TFRenderHelper;
import fiskfille.tf.helper.TFTextureHelper;

@SideOnly(Side.CLIENT)
public class RenderPlayerHand extends RenderPlayer
{
    private Minecraft mc = Minecraft.getMinecraft();
    public Render parent;

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        float partialTicks = ClientTickHandler.renderTick;
        
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

                int pass = shouldRenderPass(player, 1, partialTicks);
                float ticks = player.ticksExisted + partialTicks;
                float scale = 1.2F;
                GL11.glScalef(scale, scale, scale);

                model.renderFirstPersonArm(player);
                GL11.glTranslatef(0.0F, 0.35F, 0.0F);
                TFRenderHelper.setupRenderLayers(player, player.getCurrentArmor(2), upperArm);

                if (pass > 0)
                {
                    TFRenderHelper.setupRenderLayers(player, player.getCurrentArmor(2), upperArm);

                    if ((pass & 240) == 16)
                    {
                        func_82408_c(player, 1, partialTicks);
                        TFRenderHelper.setupRenderLayers(player, player.getCurrentArmor(2), upperArm);
                    }

                    if ((pass & 15) == 15)
                    {
                        bindTexture(TFTextureHelper.RES_ITEM_GLINT);
                        GL11.glEnable(GL11.GL_BLEND);
                        GL11.glColor4f(0.5F, 0.5F, 0.5F, 1);
                        GL11.glDepthFunc(GL11.GL_EQUAL);
                        GL11.glDepthMask(false);

                        for (int i = 0; i < 2; ++i)
                        {
                            float f = 0.76F;
                            float f1 = ticks * (0.001F + i * 0.003F) * 20;
                            float f2 = 0.33333334F;
                            
                            GL11.glDisable(GL11.GL_LIGHTING);
                            GL11.glColor4f(0.5F * f, 0.25F * f, 0.8F * f, 1);
                            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                            GL11.glMatrixMode(GL11.GL_TEXTURE);
                            GL11.glLoadIdentity();
                            GL11.glScalef(f2, f2, f2);
                            GL11.glRotatef(30 - i * 60, 0, 0, 1);
                            GL11.glTranslatef(0, f1, 0);
                            GL11.glMatrixMode(GL11.GL_MODELVIEW);
                            TFRenderHelper.setupRenderLayers(player, player.getCurrentArmor(2), upperArm);
                        }

                        GL11.glColor4f(1, 1, 1, 1);
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_BLEND);
                        GL11.glDepthFunc(GL11.GL_LEQUAL);
                    }

                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
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
