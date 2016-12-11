package fiskfille.tf.common.proxy;

import fiskfille.tf.client.tick.ClientTickHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class CommonProxy
{
    public static ClientTickHandler tickHandler;
    protected Queue<Runnable> tasks = new LinkedBlockingDeque<Runnable>();

    public World getWorld()
    {
        return null;
    }

    public EntityPlayer getPlayer()
    {
        return null;
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

    public float getRenderTick()
    {
        return 0;
    }

    public void queueTask(Runnable task)
    {
        tasks.add(task);
    }

    public void runTasks()
    {
        while (!tasks.isEmpty())
        {
            Runnable task = tasks.poll();
            task.run();
        }
    }
}
