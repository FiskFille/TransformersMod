package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.ModelCloudtrap;
import fiskfille.tf.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

public class TFModelCloudtrap extends TransformerModel
{
    private ModelCloudtrap model;
    private ModelCloudtrapVehicle vehicle;

    public TFModelCloudtrap()
    {
        model = new ModelCloudtrap();
        vehicle = new ModelCloudtrapVehicle();
    }

    @Override
    public Biped getMainModel()
    {
        return model;
    }

    @Override
    public ModelVehicleBase getVehicleModel()
    {
        return vehicle;
    }

    @Override
    public ModelRenderer getLowerArm()
    {
        return model.lowerArmR;
    }

    @Override
    public ModelRenderer getUpperArm()
    {
        return model.upperArmR;
    }

    @Override
    public ModelRenderer getBody()
    {
        return model.back1;
    }

    @Override
    public ModelRenderer getHead()
    {
        return model.head;
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
        GL11.glTranslatef(0, 0F, 0.25F);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        float scale = 1.3F;
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(0.2F, -0.1F, 0.2F);
    }

    @Override
    public ResourceLocation getTexture(Entity entity)
    {
        return new ResourceLocation(TransformersMod.modid, "textures/models/cloudtrap/cloudtrap.png");
    }
}
