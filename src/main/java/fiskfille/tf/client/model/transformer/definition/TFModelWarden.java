// package fiskfille.tf.client.model.transformer.definition;
//
//import fiskfille.tf.TransformersMod;
//import fiskfille.tf.client.model.transformer.ModelTransformerBase;
//import fiskfille.tf.client.model.transformer.ModelWardenPrime;
//import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
//import fiskfille.tf.client.model.transformer.vehicle.ModelWardenVehicle;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ResourceLocation;
//import org.lwjgl.opengl.GL11;
//
//public class TFModelWarden extends TransformerModel
//{
//    private ModelWardenPrime model;
//    private ModelWardenPrime modelItem;
//    private ModelWardenVehicle vehicle;
//
//    public TFModelWarden()
//    {
//        model = new ModelWardenPrime();
//        modelItem = new ModelWardenPrime();
//        vehicle = new ModelWardenVehicle();
//    }
//
//    @Override
//    public ModelTransformerBase getMainModel()
//    {
//        return model;
//    }
//
//    @Override
//    public ModelVehicleBase getVehicleModel()
//    {
//        return vehicle;
//    }
//
//    @Override
//    public ModelRenderer getLowerArm()
//    {
//        return model.lowerarmR1;
//    }
//
//    @Override
//    public ModelRenderer getUpperArm()
//    {
//        return model.torsoR1;
//    }
//
//    @Override
//    public ModelRenderer getBody()
//    {
//        return model.torsobase;
//    }
//
//    @Override
//    public ModelRenderer getHead()
//    {
//        return model.headbase;
//    }
//
//    @Override
//    public void renderItem(EntityPlayer player, ItemStack stack)
//    {
//    	GL11.glTranslatef(0.15F, -0.1F, 0.1F);
//    }
//
//    @Override
//    public void renderCape(EntityPlayer player)
//    {
//    	GL11.glTranslatef(0, -0.1F, 0.1F);
//    }
//
//    @Override
//    public void renderFirstPersonArm(EntityPlayer player)
//    {
//    	GL11.glTranslatef(0, -0.05F, 0.1F);
//    }
//
//    @Override
//    public ResourceLocation getTexture(Entity entity)
//    {
//        return new ResourceLocation(TransformersMod.modid, "textures/models/warden/warden.png");
//    }
//
//    @Override
//    public String getTextureDir()
//    {
//        return "warden/warden";
//    }
//
//    @Override
//    public ModelTransformerBase getItemInventoryModel()
//    {
//        return modelItem;
//    }
//}
