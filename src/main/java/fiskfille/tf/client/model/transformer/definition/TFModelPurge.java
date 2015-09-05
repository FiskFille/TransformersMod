package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.ModelPurge;
import fiskfille.tf.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

public class TFModelPurge extends TransformerModel
{
    private ModelPurge model;
    private ModelPurgeVehicle vehicle;

    public TFModelPurge()
    {
        model = new ModelPurge();
        vehicle = new ModelPurgeVehicle();
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
        return model.lowerArm1;
    }

    @Override
    public ModelRenderer getUpperArm()
    {
        return model.upperArmR;
    }

    @Override
    public ModelRenderer getBody()
    {
        return model.chest;
    }

    @Override
    public ModelRenderer getHead()
    {
        return model.head;
    }

    @Override
    public void renderItem(EntityPlayer player, ItemStack stack)
    {
        GL11.glTranslatef(0.05F, -0F, 0.1F);
    }

    @Override
    public void renderCape(EntityPlayer player)
    {
        GL11.glTranslatef(0, -0.2F, 0.1F);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        GL11.glTranslatef(0, -0.3F, 0.1F);
    }

    @Override
    public ResourceLocation getTexture(Entity entity)
    {
        return new ResourceLocation(TransformersMod.modid, "textures/models/purge/purge.png");
    }
}
