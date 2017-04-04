package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.ModelPurge;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

public class TFModelPurge extends TransformerModel
{
    private ModelPurge model;
    private ModelPurge modelItem;
    private ModelPurgeVehicle vehicle;

    public TFModelPurge()
    {
        model = new ModelPurge();
        modelItem = new ModelPurge();
        vehicle = new ModelPurgeVehicle();
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
        return new ModelRendererTF[] {model.feetbaseL1, model.feetbaseR1};
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
        return model.chestplate1;
    }

    @Override
    public ModelRendererTF getHead()
    {
        return model.headbase;
    }

    @Override
    public float getFootHeight()
    {
        return 2;
    }

    @Override
    public void renderItem(EntityPlayer player, ItemStack stack)
    {
        GL11.glTranslatef(0.05F, 0.0F, 0.1F);
    }

    @Override
    public void renderCape(EntityPlayer player)
    {
        GL11.glTranslatef(0, -0.2F, 0.1F);
    }

    @Override
    public void renderFirstPersonArm(EntityPlayer player)
    {
        GL11.glTranslatef(0, 0.05F, 0.15F);
    }

    @Override
    public ResourceLocation getTexture(Entity entity, String suffix)
    {
        return new ResourceLocation(TransformersMod.modid, String.format("textures/models/purge/purge%s.png", suffix));
    }

    @Override
    public ModelTransformerBase getItemInventoryModel()
    {
        return modelItem;
    }
}
