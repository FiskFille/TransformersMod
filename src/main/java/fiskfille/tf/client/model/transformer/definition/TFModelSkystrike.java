package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.transformer.ModelSkystrike;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

public class TFModelSkystrike extends TransformerModel
{
    private ModelSkystrike model;
    private ModelSkystrike modelItem;
    private ModelSkystrikeVehicle vehicle;

    public TFModelSkystrike()
    {
        model = new ModelSkystrike();
        modelItem = new ModelSkystrike();
        vehicle = new ModelSkystrikeVehicle();
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
    public ModelRenderer[] getFeet()
    {
        return new ModelRenderer[] {model.feetbaseL1, model.feetbaseR1};
    }
    
    @Override
    public ModelRenderer[] getLegs()
    {
        return new ModelRenderer[] {model.upperlegL1, model.upperlegR1};
    }

    @Override
    public ModelRenderer getLowerArm()
    {
        return model.lowerarmR1;
    }

    @Override
    public ModelRenderer getUpperArm()
    {
        return model.shoulderRbase;
    }

    @Override
    public ModelRenderer getBody()
    {
        return model.torsobase1;
    }

    @Override
    public ModelRenderer getHead()
    {
        return model.headbase;
    }

    @Override
    public void renderItem(EntityPlayer player, ItemStack stack)
    {
    	GL11.glTranslatef(0.15F, -0.1F, 0.1F);
    }

    @Override
    public void renderCape(EntityPlayer player)
    {
    	GL11.glTranslatef(0, -0.1F, 0.1F);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
    	GL11.glTranslatef(0, -0.05F, 0.1F);
    }

    @Override
    public ResourceLocation getTexture(Entity entity)
    {
        return new ResourceLocation(TransformersMod.modid, "textures/models/skystrike/skystrike.png");
    }

    @Override
    public String getTextureDir()
    {
        return "skystrike/skystrike";
    }

    @Override
    public ModelTransformerBase getItemInventoryModel()
    {
        return modelItem;
    }
}
