package fiskfille.tf.helper;

import java.util.Locale;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import fiskfille.tf.TransformersMod;
import fiskfille.tf.common.tileentity.TileEntityMachine.EnumIO;

public class TFTextureHelper
{
    public static IIcon energonFlowingIcon;
    public static IIcon energonStillIcon;
    
    public static IIcon[] ioIcons;
    
    public static void onTextureStitch(TextureMap map)
    {
        energonFlowingIcon = map.registerIcon(TransformersMod.modid + ":energon_flow");
        energonStillIcon = map.registerIcon(TransformersMod.modid + ":energon_still");
        
        ioIcons = new IIcon[EnumIO.values().length];
        
        for (EnumIO io : EnumIO.values())
        {
            if (io.ordinal() > 0)
            {
                ioIcons[io.ordinal()] = map.registerIcon(TransformersMod.modid + ":io_" + io.name().toLowerCase(Locale.ENGLISH));
            }
        }
    }

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
