package fiskfille.tf;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TFReflection
{
    public static Method renderHandMethod;
    public static Method renderEquippedItemsMethod;
    public static Method getColorMultiplierMethod;
    public static Method setSizeMethod;
    public static Method renderNametagMethod;
    public static Method getArmSwingAnimationEndMethod;
    public static Field mainModelField;
    public static Field splashTextField;
    public static Field thirdPersonDistanceField;

    public static Field isInWebField;
    public static Field fireField;
    public static Field nextStepDistanceField;
    public static Method updateFallStateMethod;
    public static Method canTriggerWalkingMethod;
    public static Method dealFireDamageMethod;
    public static Method getSwimSoundMethod;
    public static Method func_145780_aMethod;
    public static Method func_145775_IMethod;

    @SideOnly(Side.CLIENT)
    public static void client()
    {
        renderHandMethod = getMethod(EntityRenderer.class, "renderHand", "func_78476_b");
        renderEquippedItemsMethod = getMethod(RendererLivingEntity.class, "renderEquippedItems");
        getColorMultiplierMethod = getMethod(RendererLivingEntity.class, "getColorMultiplier", "func_77030_a");
        renderNametagMethod = getMethod(RenderPlayer.class, "func_96449_a");

        mainModelField = getField(RendererLivingEntity.class, "mainModel", "field_77045_g");
        splashTextField = getField(GuiMainMenu.class, "splashText", "field_73975_c");
        thirdPersonDistanceField = getField(EntityRenderer.class, "thirdPersonDistance", "field_78490_B");
    }

    public static void common()
    {
        setSizeMethod = getMethod(Entity.class, "setSize", "func_70105_a");
        getArmSwingAnimationEndMethod = getMethod(EntityLivingBase.class, "getArmSwingAnimationEnd", "func_82166_i");

        isInWebField = getField(Entity.class, "isInWeb", "field_70134_J");
        fireField = getField(Entity.class, "fire", "field_70151_c");
        nextStepDistanceField = getField(Entity.class, "nextStepDistance", "field_70150_b");
        updateFallStateMethod = getMethod(Entity.class, "updateFallState", "func_70064_a");
        canTriggerWalkingMethod = getMethod(Entity.class, "canTriggerWalking", "func_70041_e_");
        dealFireDamageMethod = getMethod(Entity.class, "dealFireDamage", "func_70081_e");
        getSwimSoundMethod = getMethod(Entity.class, "getSwimSound", "func_145776_H");
        func_145780_aMethod = getMethod(Entity.class, "func_145780_a");
        func_145775_IMethod = getMethod(Entity.class, "func_145775_I");
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

    public static void renderEquippedItems(RendererLivingEntity obj, EntityPlayer player, float f)
    {
        try
        {
            renderEquippedItemsMethod.invoke(obj, player, f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int getColorMultiplier(RendererLivingEntity obj, EntityPlayer player, float f, float f1)
    {
        try
        {
            return (Integer) getColorMultiplierMethod.invoke(obj, player, f, f1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public static ModelBase mainModel(RendererLivingEntity obj)
    {
        return (ModelBase) getField(obj, mainModelField);
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

    public static void renderNametag(RenderPlayer obj, EntityLivingBase entity, double x, double y, double z, String username, float p_96449_9_, double p_96449_10_)
    {
        try
        {
            renderNametagMethod.invoke(obj, entity, x, y, z, username, p_96449_9_, p_96449_10_);
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
