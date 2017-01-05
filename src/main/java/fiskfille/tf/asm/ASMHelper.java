package fiskfille.tf.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class ASMHelper
{
    public static MethodInsnNode divide(String type)
    {
        return new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMHelper.class), "divide", "(" + type.toUpperCase() + type.toUpperCase() + ")" + type.toUpperCase(), false);
    }

    public static int divide(int arg1, int arg2)
    {
        return arg1 / arg2;
    }

    public static float divide(float arg1, float arg2)
    {
        return arg1 / arg2;
    }

    public static double divide(double arg1, double arg2)
    {
        return arg1 / arg2;
    }

    public static MethodInsnNode multiply(String type)
    {
        return new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMHelper.class), "multiply", "(" + type.toUpperCase() + type.toUpperCase() + ")" + type.toUpperCase(), false);
    }

    public static int multiply(int arg1, int arg2)
    {
        return arg1 * arg2;
    }

    public static float multiply(float arg1, float arg2)
    {
        return arg1 * arg2;
    }

    public static double multiply(double arg1, double arg2)
    {
        return arg1 * arg2;
    }

    public static TypeInsnNode cast(String to)
    {
        return new TypeInsnNode(Opcodes.CHECKCAST, to);
    }

    public static MethodInsnNode and()
    {
        return new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMHelper.class), "and", "(ZZ)Z", false);
    }

    public static boolean and(boolean arg1, boolean arg2)
    {
        return arg1 && arg2;
    }

    public static MethodInsnNode or()
    {
        return new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMHelper.class), "or", "(ZZ)Z", false);
    }

    public static boolean or(boolean arg1, boolean arg2)
    {
        return arg1 || arg2;
    }

    public static MethodInsnNode not()
    {
        return new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMHelper.class), "not", "(Z)Z", false);
    }

    public static boolean not(boolean arg)
    {
        return !arg;
    }

    public static MethodInsnNode conditional()
    {
        return new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMHelper.class), "conditional", "(ZLjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
    }

    public static Object conditional(boolean condition, Object arg1, Object arg2)
    {
        return condition ? arg1 : arg2;
    }
}
