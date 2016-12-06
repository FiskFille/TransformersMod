package fiskfille.tf.waila;

import mcp.mobius.waila.api.IWailaRegistrar;
import fiskfille.tf.common.tileentity.TileEntityControlPanel;
import fiskfille.tf.common.tileentity.TileEntityEnergonProcessor;
import fiskfille.tf.common.tileentity.TileEntityRelayTorch;
import fiskfille.tf.common.tileentity.TileEntityRelayTower;
import fiskfille.tf.common.tileentity.TileEntityTransmitter;

public class WailaRegistrar
{
    public static void wailaCallback(IWailaRegistrar registrar)
    {
        registrar.addConfig("Transformers Mod", "tf.energon_processor");
        registrar.addConfig("Transformers Mod", "tf.transmitter");
        registrar.addConfig("Transformers Mod", "tf.relay_tower");
        registrar.addConfig("Transformers Mod", "tf.relay_torch");
        registrar.addConfig("Transformers Mod", "tf.control_panel");

        registrar.registerBodyProvider(new DataProviderEnergonProcessor(), TileEntityEnergonProcessor.class);
        registrar.registerBodyProvider(new DataProviderTransmitter(), TileEntityTransmitter.class);
        registrar.registerBodyProvider(new DataProviderEnergyContainer("tf.relay_tower", TileEntityRelayTower.class), TileEntityRelayTower.class);
        registrar.registerBodyProvider(new DataProviderEnergyContainer("tf.relay_torch", TileEntityRelayTorch.class), TileEntityRelayTorch.class);
        registrar.registerBodyProvider(new DataProviderControlPanel(), TileEntityControlPanel.class);
    }
}
