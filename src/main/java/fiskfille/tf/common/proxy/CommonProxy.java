package fiskfille.tf.common.proxy;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fiskfille.tf.client.tick.ClientTickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy
{
    public static ClientTickHandler tickHandler;

    public World getWorld(MessageContext ctx)
    {
        return getPlayer(ctx).worldObj;
    }

    public EntityPlayer getPlayer(MessageContext ctx)
    {
        return ctx.getServerHandler().playerEntity;
    }

    public void preInit()
    {

    }

    public void registerRenderInformation()
    {

    }

    public void registerKeyBinds()
    {

    }

    public void registerTickHandlers()
    {

    }
}