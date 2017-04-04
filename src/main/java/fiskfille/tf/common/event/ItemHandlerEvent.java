package fiskfille.tf.common.event;

import java.util.Map;

import cpw.mods.fml.common.eventhandler.Event;

public abstract class ItemHandlerEvent extends Event
{
    public static class Init extends ItemHandlerEvent
    {
        public final Map<Class, String> itemHandlers;
        
        public Init(Map<Class, String> handlers)
        {
            super();
            itemHandlers = handlers;
        }
        
        public void registerItemHandler(String modid, Class handlerClass)
        {
            itemHandlers.put(handlerClass, modid);
        }
    }
}
