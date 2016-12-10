package fiskfille.tf.helper;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class TFTextureHelper
{
    public static IIcon energonFlowingIcon;
    public static IIcon energonStillIcon;
    
    public static ResourceLocation getSkin(String username)
    {
        ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;

        if (username != null && username.length() > 0)
        {
            resourcelocation = AbstractClientPlayer.getLocationSkin(username);
            AbstractClientPlayer.getDownloadImageSkin(resourcelocation, username);
        }

        return resourcelocation;
    }
}
