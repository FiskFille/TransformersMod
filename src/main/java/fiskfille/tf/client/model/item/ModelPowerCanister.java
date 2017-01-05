package fiskfille.tf.client.model.item;

import fiskfille.tf.client.model.tools.MowzieModelBase;
import fiskfille.tf.client.model.tools.MowzieModelRenderer;

public class ModelPowerCanister extends MowzieModelBase
{
    public MowzieModelRenderer canister1;
    public MowzieModelRenderer canister2;
    public MowzieModelRenderer canister3;

    public ModelPowerCanister()
    {
        textureWidth = 32;
        textureHeight = 16;
        canister3 = new MowzieModelRenderer(this, 12, 5);
        canister3.setRotationPoint(0.0F, -1.5F, 0.0F);
        canister3.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
        canister2 = new MowzieModelRenderer(this, 12, 0);
        canister2.setRotationPoint(0.0F, -8.5F, 0.0F);
        canister2.addBox(-2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F);
        canister1 = new MowzieModelRenderer(this, 0, 0);
        canister1.setRotationPoint(0.0F, 0.0F, 0.0F);
        canister1.addBox(-1.5F, -10.0F, -1.5F, 3, 10, 3, 0.0F);
        canister1.addChild(canister3);
        canister1.addChild(canister2);

        setInitPose();
    }

    public void render()
    {
        setToInitPose();
        canister1.render(0.0625F);
    }
}
