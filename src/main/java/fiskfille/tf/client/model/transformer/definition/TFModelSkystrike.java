package fiskfille.tf.client.model.transformer.definition;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.ModelRendererTF;
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
    public ModelRendererTF[] getFeet()
    {
        return new ModelRendererTF[] {model.feetbaseL1, model.feetbaseR1};
    }

    @Override
    public ModelRendererTF[] getLegs()
    {
        return new ModelRendererTF[] {model.upperlegL1, model.upperlegR1};
    }

    @Override
    public ModelRendererTF getLowerArm()
    {
        return model.lowerarmR1;
    }

    @Override
    public ModelRendererTF getUpperArm()
    {
        return model.shoulderRbase;
    }

    @Override
    public ModelRendererTF getBody()
    {
        return model.torsobase1;
    }

    @Override
    public ModelRendererTF getHead()
    {
        return model.headbase;
    }

    @Override
    public float getFootHeight()
    {
        return 1.25F;
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
