package fiskfille.tf;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TFReflection
{
    public static Method renderHandMethod;
    public static Method drawSlotMethod;
    public static Field thirdPersonDistanceField;
    
    public static Method setSizeMethod;

    @SideOnly(Side.CLIENT)
    public static void client()
    {
        renderHandMethod = getMethod(EntityRenderer.class, "renderHand", "func_78476_b");
        drawSlotMethod = getMethod(GuiContainer.class, "func_146977_a");
        
        thirdPersonDistanceField = getField(EntityRenderer.class, "thirdPersonDistance", "field_78490_B");
    }

    public static void common()
    {
        setSizeMethod = getMethod(Entity.class, "setSize", "func_70105_a");
    }

    public static Method getMethod(Class clazz, String... names)
    {
        for (String name : names)
        {
            for (Method method : clazz.getDeclaredMethods())
            {
                if (method.getName().equals(name))
                {
                    method.setAccessible(true);
                    return method;
                }
            }
        }

        return null;
    }

    public static Field getField(Class clazz, String... names)
    {
        for (String name : names)
        {
            for (Field field : clazz.getDeclaredFields())
            {
                if (field.getName().equals(name))
                {
                    field.setAccessible(true);
                    return field;
                }
            }
        }

        return null;
    }

    public static Object getField(Object obj, Field field)
    {
        try
        {
            return field.get(obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void renderHand(EntityRenderer obj, float f, int i)
    {
        try
        {
            renderHandMethod.invoke(obj, f, i);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setSize(Entity obj, float f, float f1)
    {
        try
        {
            setSizeMethod.invoke(obj, f, f1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Object getField(Field field, Object owner)
    {
        try
        {
            return field.get(owner);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void setField(Field field, Object owner, Object arg)
    {
        try
        {
            field.set(owner, arg);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Object getMethod(Method method, Object owner, Object... args)
    {
        try
        {
            return method.invoke(owner, args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void invokeMethod(Method method, Object owner, Object... args)
    {
        try
        {
            method.invoke(owner, args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
