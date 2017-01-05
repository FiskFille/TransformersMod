package fiskfille.tf.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.main.MainClass;
import fiskfille.tf.main.TFHelper;
import fiskfille.tf.main.misc.ClientProxy;

@SideOnly(Side.CLIENT)
public class RenderCustomPlayer extends RenderPlayer
{
	public ModelRenderer replacement;
	public ResourceLocation resourceLoc;

	public ModelBiped modelBipedMain;
	private ModelBiped modelArmorChestplate;
	private ModelBiped modelArmor;

	public RenderCustomPlayer()
	{
		super();
		this.modelBipedMain = new ModelBiped();
		this.modelArmorChestplate = new ModelBiped(1.0F);
		this.modelArmor = new ModelBiped(0.5F);
		this.mainModel = this.modelBipedMain;
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer par1EntityPlayer)
	{
		resourceLoc = TFHelper.getPlayerHandTexture(par1EntityPlayer);
		replacement = TFHelper.getPlayerModel(par1EntityPlayer, 2) != null ? TFHelper.getPlayerModel(par1EntityPlayer, 2).bipedRightArm : null;
		
		if (replacement != null)
		{
			float f = 1.0F;

			Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLoc); //try func_110776_a
			
			GL11.glColor4f(f, f, f, 1.0F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			ModelRenderer arm = modelBipedMain.bipedRightArm;
			modelBipedMain.bipedRightArm = replacement;
			
			float rotX = replacement.rotationPointX;
			float rotY = replacement.rotationPointY;
			float rotZ = replacement.rotationPointZ;

			float angX = replacement.rotateAngleX;
			float angY = replacement.rotateAngleY;
			float angZ = replacement.rotateAngleZ;

			replacement.rotationPointX = arm.rotationPointX;
			replacement.rotationPointY = arm.rotationPointY;
			replacement.rotationPointZ = arm.rotationPointZ;

			modelBipedMain.onGround = 0.0F;
			modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, par1EntityPlayer);
			modelBipedMain.bipedRightArm.render(0.0625F);

			modelBipedMain.bipedRightArm = arm;

			replacement.rotationPointX = rotX;
			replacement.rotationPointY = rotY;
			replacement.rotationPointZ = rotZ;

			replacement.rotateAngleX = angX;
			replacement.rotateAngleY = angY;
			replacement.rotateAngleZ = angZ;

			GL11.glDisable(GL11.GL_BLEND);

			GL11.glColor4f(f, f, f, 1.0F);
		}
		
//		float f = 1.0F;
//        GL11.glColor3f(f, f, f);
//        this.modelBipedMain.onGround = 0.0F;
//        this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, par1EntityPlayer);
//        this.modelBipedMain.bipedRightArm.render(0.0625F);
	}
}