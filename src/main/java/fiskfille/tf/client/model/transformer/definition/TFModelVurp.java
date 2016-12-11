package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.ModelVurp;
import fiskfille.tf.client.model.transformer.stealth.ModelVurpStealth;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVurpVehicle;

public class TFModelVurp extends TransformerModel
{
    private ModelVurp model;
    private ModelVurp modelItem;
    private ModelVurpVehicle vehicle;
    private ModelVurpStealth stealth;

    public TFModelVurp()
    {
        model = new ModelVurp();
        modelItem = new ModelVurp();
        vehicle = new ModelVurpVehicle();
        stealth = new ModelVurpStealth();
    }

    @Override
    public ModelTransformerBase getMainModel()
    {
        return model;
    }

    @Override
    public ModelVehicleBase getVehicleModel()
    {
        return vehicle;
    }

    @Override
    public ModelVehicleBase getStealthModel()
    {
        return stealth;
    }

    @Override
    public ModelRendererTF[] getFeet()
    {
        return new ModelRendererTF[] {model.footbaseL, model.footbaseR};
    }

    @Override
    public ModelRendererTF[] getLegs()
    {
        return new ModelRendererTF[] {model.upperLegL, model.upperLegR};
    }

    @Override
    public ModelRendererTF getLowerArm()
    {
        return model.lowerArmR;
    }

    @Override
    public ModelRendererTF getUpperArm()
    {
        return model.armbaseR1;
    }

    @Override
    public ModelRendererTF getBody()
    {
        return model.torsobase;
    }

    @Override
    public ModelRendererTF getHead()
    {
        return model.head;
    }

    @Override
    public float getFootHeight()
    {
        return 2;
    }

    @Override
    public void renderItem(EntityPlayer player, ItemStack stack)
    {
        GL11.glTranslatef(0.05F, -0F, 0.1F);
    }

    @Override
    public void renderCape(EntityPlayer player)
    {
        GL11.glTranslatef(0F, -0.2F, 0.1F);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        float scale = 1.1F;
        GL11.glScalef(scale, scale, scale);
    }

    @Override
    public ResourceLocation getTexture(Entity entity)
    {
        return new ResourceLocation(TransformersMod.modid, "textures/models/vurp/vurp.png");
    }

    @Override
    public String getTextureDir()
    {
        return "vurp/vurp";
    }

    @Override
    public ModelTransformerBase getItemInventoryModel()
    {
        return modelItem;
    }
}
