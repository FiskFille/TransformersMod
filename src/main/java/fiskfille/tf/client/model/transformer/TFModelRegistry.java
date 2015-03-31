package fiskfille.tf.client.model.transformer;

import java.util.HashMap;
import java.util.Map;

import javax.vecmath.Vector3f;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelRenderer;
import fiskfille.tf.TransformerManager;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.vehicle.ModelCloudtrapVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelPurgeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelSkystrikeVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelSubwooferVehicle;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;
import fiskfille.tf.client.model.transformer.vehicle.ModelVurpVehicle;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

public class TFModelRegistry
{
    private static Map<Transformer, TransformerModel> models = new HashMap<Transformer, TransformerModel>();
    
    public static TransformerModel registerModel(Transformer transformer, Biped model, ModelVehicleBase vehicleModel, ModelRenderer upperArm, ModelRenderer lowerArm, ModelRenderer backside)
    {
        TransformerModel transformerModel = new TransformerModel(model, vehicleModel, upperArm, lowerArm, backside);
        
        models.put(transformer, transformerModel);
        
        return transformerModel;
    }
    
    public static TransformerModel registerModel(Transformer transformer, Biped model, ModelVehicleBase vehicleModel, ModelRenderer upperArm, ModelRenderer lowerArm, ModelRenderer backside, Vector3f itemOffset, Vector3f capeOffset, Vector3f firstPersonArmOffset)
    {
        TransformerModel transformerModel = registerModel(transformer, model, vehicleModel, upperArm, lowerArm, backside);
        
        if(itemOffset != null)
            transformerModel.itemOffset = itemOffset;
        if(capeOffset != null)
            transformerModel.capeOffset = capeOffset;
        if(firstPersonArmOffset != null)
            transformerModel.firstPersonArmOffset = firstPersonArmOffset;
        
        return transformerModel;
    }
    
    public static TransformerModel getModel(Transformer transformer)
    {
        return models.get(transformer);
    }
    
    public static void registerModels()
    {
        ModelCloudtrap modelCloudtrap = new ModelCloudtrap();
        ModelPurge modelPurge = new ModelPurge();
        ModelSkystrike modelSkystrike = new ModelSkystrike();
        ModelSubwoofer modelSubwoofer = new ModelSubwoofer();
        ModelVurp modelVurp = new ModelVurp();
        
        TFModelRegistry.registerModel(TransformerManager.transformerCloudtrap, modelCloudtrap, new ModelCloudtrapVehicle(), null, null, null);
        TFModelRegistry.registerModel(TransformerManager.transformerPurge, modelPurge, new ModelPurgeVehicle(), modelPurge.lowerArm1, modelPurge.lowerArm1, modelPurge.chest, new Vector3f(0.05F, 0F, 0.1F), new Vector3f(0F, -0.2F, 0.1F), new Vector3f(0F, 0.05F, 0.5F));
        TFModelRegistry.registerModel(TransformerManager.transformerSkystrike, modelSkystrike, new ModelSkystrikeVehicle(), modelSkystrike.shoulderR, modelSkystrike.lowerArmR1, modelSkystrike.chest1, new Vector3f(0F, 0.1F, 0.15F), new Vector3f(0F, 0.2F, 0.25F), null);
        TFModelRegistry.registerModel(TransformerManager.transformerSubwoofer, modelSubwoofer, new ModelSubwooferVehicle(), modelSubwoofer.upperArmR, modelSubwoofer.lowerArmR, modelSubwoofer.chestmain3, new Vector3f(0.05F, -0.1F, 0.05F), new Vector3f(0.18F, 0F, -0.01F), new Vector3f(0F, 0.15F, 0.5F));
        TFModelRegistry.registerModel(TransformerManager.transformerVurp, modelVurp, new ModelVurpVehicle(), modelVurp.upperArmR, modelVurp.lowerArmR, modelVurp.torsobase, new Vector3f(0.05F, -0.1F, 0.05F), new Vector3f(0F, -0.2F, 0.1F), new Vector3f(0F, 0.3F, 0.5F));
    }
}
