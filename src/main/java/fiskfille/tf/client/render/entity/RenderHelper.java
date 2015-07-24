//package fiskfille.tf.client.render.entity;
//
//import java.util.List;
//import java.util.Map;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.renderer.ItemRenderer;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.texture.TextureManager;
//import net.minecraft.client.renderer.texture.TextureMap;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.IIcon;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.client.MinecraftForgeClient;
//
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL12;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
///**
// * Render helper class for basic render operations and the IModelExtension.
// * 
// * @author iLexiconn
// * @author Gegy1000
// * @since 0.1.0
// */
//@SideOnly(Side.CLIENT)
//public class RenderHelper
//{
//    private static Map<Class<? extends ModelBase>, List<IExtension>> modelExtensions = Maps.newHashMap();
//    private static ResourceLocation glintTexture = new ResourceLocation("textures/misc/enchanted_item_glint.png");
//
//    /**
//     * Registers the given {@link net.ilexiconn.llibrary.client.render.IModelExtension}.
//     * 
//     * @see net.ilexiconn.llibrary.client.render.IModelExtension
//     * @since 0.1.0
//     */
//    public static void registerModelExtension(IModelExtension modelExtension)
//    {
//        registerModelExtension(ModelBiped.class, modelExtension);
//    }
//
//    /**
//     * Registers the given {@link net.ilexiconn.llibrary.client.render.IModelExtension} to a specific model.
//     * 
//     * @see net.ilexiconn.llibrary.client.render.IModelExtension
//     * @since 0.1.0
//     */
//    private static void registerModelExtension(Class<? extends ModelBase> modelClazz, IExtension modelExtension)
//    {
//        List<IExtension> extensionsForModel = modelExtensions.get(modelClazz);
//
//        if (extensionsForModel == null)
//        {
//            extensionsForModel = Lists.newArrayList();
//        }
//
//        extensionsForModel.add(modelExtension);
//
//        modelExtensions.put(modelClazz, extensionsForModel);
//    }
//
//    /**
//     * @return a list of {@link net.ilexiconn.llibrary.client.render.IModelExtension} for the given model class.
//     * @see net.ilexiconn.llibrary.client.render.IModelExtension
//     * @since 0.1.0
//     */
//    public static List<IExtension> getModelExtensionsFor(Class<? extends ModelBase> clazz)
//    {
//        return modelExtensions.get(clazz);
//    }
//}
