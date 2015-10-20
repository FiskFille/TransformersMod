package fiskfille.tf.common.energon;

import fiskfille.tf.TransformersAPI;

public class TFEnergonManager
{   
    public static Energon energon;
    public static Energon redEnergon;
    
    public static void registerDefaultEnergonTypes()
    {
        energon = new DefaultEnergon();
        redEnergon = new RedEnergon();
        
        TransformersAPI.registerEnergonType(energon);
        TransformersAPI.registerEnergonType(redEnergon);
    }
}
