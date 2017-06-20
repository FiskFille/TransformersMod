package fiskfille.tf.common.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TFTextureHelper
{
    public static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private static final Minecraft MINECRAFT = Minecraft.getMinecraft();

    /*public static IIcon energonFlowingIcon;
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
    }*/

    public static ResourceLocation getSkin(String username)
    {
        ResourceLocation texture = AbstractClientPlayer.getLocationSkin("default");

        if (username != null && username.length() > 0)
        {
            texture = AbstractClientPlayer.getLocationSkin(username);
            AbstractClientPlayer.getDownloadImageSkin(texture, username);
        }

        return texture;
    }

    public static boolean isBoundTexture(ResourceLocation resourceLocation)
    {
        ITextureObject texture = MINECRAFT.getTextureManager().getTexture(resourceLocation);

        return texture != null && texture.getGlTextureId() == GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
    }
}
