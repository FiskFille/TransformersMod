package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.ModelVurp;
import fiskfille.tf.client.model.transformer.stealth.ModelVurpStealth;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVurpVehicle;

public class TFModelVurp extends TransformerModel
{
    private ModelVurp model;
    private ModelVurpVehicle vehicle;
    private ModelVurpStealth stealth;
    
    public TFModelVurp()
    {
        this.model = new ModelVurp();
        this.vehicle = new ModelVurpVehicle();
        this.stealth = new ModelVurpStealth();
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
    public Biped getStealthModel()
    {
        return stealth;
    }
    
    @Override
    public ModelRenderer getLowerArm()
    {
        return model.lowerArmR;
    }
    
    @Override
    public ModelRenderer getUpperArm()
    {
        return model.armbaseR1;
    }
    
    @Override
    public ModelRenderer getBody()
    {
        return model.torsobase;
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
}
