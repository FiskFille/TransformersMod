package fiskfille.tf.client.model.transformer;

import javax.vecmath.Vector3f;

import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.client.model.transformer.ModelChildBase.Biped;
import fiskfille.tf.client.model.transformer.vehicle.ModelVehicleBase;

@SideOnly(Side.CLIENT)
public class TransformerModel
{
    public Biped mainModel;
    public ModelVehicleBase vehicleModel;
    public ModelRenderer arm;
    public ModelRenderer backside;
    public Vector3f itemOffset;
    public Vector3f capeOffset;
    
    public TransformerModel(Biped mainModel, ModelVehicleBase vehicleBase, ModelRenderer arm, ModelRenderer backside)
    {
        this.mainModel = mainModel;
        this.vehicleModel = vehicleBase;
        this.arm = arm;
        this.backside = backside;
        this.itemOffset = new Vector3f(0f, 0.1f, -0.05f);
        this.capeOffset = new Vector3f(0f, 0f, 0f);
    }
}
