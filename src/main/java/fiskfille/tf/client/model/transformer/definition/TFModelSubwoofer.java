package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.ModelSubwoofer;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.stealth.ModelSubwooferStealth;
import fiskfille.tf.client.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

public class TFModelSubwoofer extends TransformerModel
{
    private ModelSubwoofer model;
    private ModelSubwoofer modelItem;
    private ModelSubwooferVehicle vehicle;
    private ModelSubwooferStealth stealth;

    public TFModelSubwoofer()
    {
        model = new ModelSubwoofer();
        modelItem = new ModelSubwoofer();
        vehicle = new ModelSubwooferVehicle();
        stealth = new ModelSubwooferStealth();
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
        return new ModelRendererTF[] {model.legbaseL, model.legbaseR, model.lowerlegL3, model.lowerlegL4, model.lowerlegR3, model.lowerlegR4};
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
        return model.shoulderbaseR;
    }

    @Override
    public ModelRendererTF getBody()
    {
        return model.chestmain3;
    }

    @Override
    public ModelRendererTF getHead()
    {
        return model.head;
    }

    @Override
    public float getFootHeight()
    {
        return 2.5F;
    }

    @Override
    public void renderItem(EntityPlayer player, ItemStack stack)
    {
        GL11.glTranslatef(0.05F, -0F, 0.1F);
    }

    @Override
    public void renderCape(EntityPlayer player)
    {
        GL11.glTranslatef(0.18F, 0F, -0.01F);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        GL11.glTranslatef(0.1F, 0.0F, 0.15F);
    }

    @Override
    public ResourceLocation getTexture(Entity entity, String suffix)
    {
        return new ResourceLocation(TransformersMod.modid, String.format("textures/models/subwoofer/subwoofer%s.png", suffix));
    }

    @Override
    public ModelTransformerBase getItemInventoryModel()
    {
        return modelItem;
    }
}
