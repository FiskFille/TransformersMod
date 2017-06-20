package fiskfille.tf.client.model.transformer.definition;

import fiskfille.tf.TransformersMod;
import fiskfille.tf.client.model.tools.ModelRendererTF;
import fiskfille.tf.client.model.transformer.ModelSkystrike;
import fiskfille.tf.client.model.transformer.ModelTransformerBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TFModelSkystrike extends TransformerModel
{
    private ModelSkystrike model;
    private ModelSkystrike modelItem;
    private ModelSkystrikeVehicle vehicle;

    public TFModelSkystrike()
    {
        this.model = new ModelSkystrike();
        this.modelItem = new ModelSkystrike();
        this.vehicle = new ModelSkystrikeVehicle();
    }

    @Override
    public ModelTransformerBase getMainModel()
    {
        return this.model;
    }

    @Override
    public ModelVehicleBase getVehicleModel()
    {
        return this.vehicle;
    }

    @Override
    public ModelRendererTF[] getFeet()
    {
        return new ModelRendererTF[] { this.model.feetbaseL1, this.model.feetbaseR1};
    }

    @Override
    public ModelRendererTF[] getLegs()
    {
        return new ModelRendererTF[] { this.model.upperlegL1, this.model.upperlegR1};
    }

    @Override
    public ModelRendererTF getLowerArm()
    {
        return this.model.lowerarmR1;
    }

    @Override
    public ModelRendererTF getUpperArm()
    {
        return this.model.shoulderRbase;
    }

    @Override
    public ModelRendererTF getBody()
    {
        return this.model.torsobase1;
    }

    @Override
    public ModelRendererTF getHead()
    {
        return this.model.headbase;
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
    public ResourceLocation getTexture(Entity entity, String suffix)
    {
        return new ResourceLocation(TransformersMod.MODID, String.format("textures/models/skystrike/skystrike%s.png", suffix));
    }

    @Override
    public ModelTransformerBase getItemInventoryModel()
    {
        return this.modelItem;
    }
}
