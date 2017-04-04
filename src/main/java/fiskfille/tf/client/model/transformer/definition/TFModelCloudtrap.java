package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.ModelCloudtrap;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

public class TFModelCloudtrap extends TransformerModel
{
    private ModelCloudtrap model;
    private ModelCloudtrap modelItem;
    private ModelCloudtrapVehicle vehicle;

    public TFModelCloudtrap()
    {
        model = new ModelCloudtrap();
        modelItem = new ModelCloudtrap();
        vehicle = new ModelCloudtrapVehicle();
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
    public ModelRendererTF[] getFeet()
    {
        return new ModelRendererTF[] {model.feetbaseL, model.feetbaseR};
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
        return model.upperArmR;
    }

    @Override
    public ModelRendererTF getBody()
    {
        return model.back1;
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
        GL11.glTranslatef(0.075F, -0.18F, -0.02F);
        GL11.glRotatef(10, 1, 0, 0);
    }

    @Override
    public void renderCape(EntityPlayer player)
    {
        GL11.glRotatef(20, 1, 0, 0);
        GL11.glTranslatef(0, -0.05F, 0.0F);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        float scale = 1.3F;
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(0.2F, -0.1F, 0.2F);
    }

    @Override
    public ResourceLocation getTexture(Entity entity, String suffix)
    {
        return new ResourceLocation(TransformersMod.modid, String.format("textures/models/cloudtrap/cloudtrap%s.png", suffix));
    }

    @Override
    public ModelTransformerBase getItemInventoryModel()
    {
        return modelItem;
    }
}
