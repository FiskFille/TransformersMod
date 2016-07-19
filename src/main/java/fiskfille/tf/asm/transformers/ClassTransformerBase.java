package fiskfille.tf.asm.transformers;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public abstract class ClassTransformerBase implements IClassTransformer, Opcodes
{
    public static Logger logger = LogManager.getLogger("Transformers Mod");

    public static final String SIMPLEST_METHOD_DESC = "()V";
    protected final String classPath;
    protected final String unobfClass;

    public ClassTransformerBase(String classPath)
    {
        this.classPath = classPath;
        this.unobfClass = classPath.substring(classPath.lastIndexOf('.') + 1);
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        try
        {
            if (transformedName.equals(classPath))
            {
                logger.log(Level.INFO, "Transformers Mod - Patching Class " + unobfClass + " (" + name + ")");

                ClassReader cr = new ClassReader(bytes);
                ClassNode cn = new ClassNode();
                cr.accept(cn, 0);

                setupMappings();
                boolean success = processFields(cn.fields) && processMethods(cn.methods);
                addInterface(cn.interfaces);

                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                cn.accept(cw);

                logger.log(success ? Level.INFO : Level.ERROR, "Transformers Mod - Patching Class " + unobfClass + (success ? " done" : " FAILED!"));

                writeClassFile(cw, unobfClass + " (" + name + ")");
                return cw.toByteArray();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return bytes;
    }

    public void addInterface(List<String> interfaces)
    {

    }

    public abstract boolean processMethods(List<MethodNode> methods);

    public abstract boolean processFields(List<FieldNode> fields);

    public abstract void setupMappings();

    public void sendPatchLog(String method)
    {
        logger.log(Level.INFO, "\tPatching method " + method + " in " + unobfClass);
    }

    public static void writeClassFile(ClassWriter cw, String name)
    {
        try
        {
            File outDir = new File("debug/");
            outDir.mkdirs();
            DataOutputStream dout = new DataOutputStream(new FileOutputStream(new File(outDir, name + ".class")));
            dout.write(cw.toByteArray());
            dout.flush();
            dout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static MethodNode generateSetter(String className, String methodName, String fieldName, String fieldType)
    {
        MethodNode mn = new MethodNode(ACC_PUBLIC, methodName, "(" + fieldType + ")V", null, null);
        mn.visitCode();
        mn.visitVarInsn(ALOAD, 0);
        int opCode;

        if (fieldType.equals("I") || fieldType.equals("Z"))
        {
            opCode = ILOAD;
        }
        else if (fieldType.equals("L"))
        {
            opCode = LLOAD;
        }
        else if (fieldType.equals("F"))
        {
            opCode = FLOAD;
        }
        else if (fieldType.equals("D"))
        {
            opCode = DLOAD;
        }
        else
        {
            opCode = ALOAD;
        }

        mn.visitVarInsn(opCode, 1);
        mn.visitFieldInsn(PUTFIELD, className, fieldName, fieldType);
        mn.visitInsn(RETURN);
        mn.visitMaxs(2, 2);
        mn.visitEnd();
        return mn;
    }

    public static MethodNode generateGetter(String className, String methodName, String fieldName, String fieldType)
    {
        MethodNode mn = new MethodNode(ACC_PUBLIC, methodName, "()" + fieldType, null, null);
        mn.visitCode();
        mn.visitVarInsn(ALOAD, 0);
        mn.visitFieldInsn(GETFIELD, className, fieldName, fieldType);
        int opCode;

        if (fieldType.equals("I") || fieldType.equals("Z"))
        {
            opCode = IRETURN;
        }
        else if (fieldType.equals("L"))
        {
            opCode = LRETURN;
        }
        else if (fieldType.equals("F"))
        {
            opCode = FRETURN;
        }
        else if (fieldType.equals("D"))
        {
            opCode = DRETURN;
        }
        else
        {
            opCode = ARETURN;
        }

        mn.visitInsn(opCode);
        mn.visitMaxs(1, 1);
        mn.visitEnd();
        return mn;
    }
}
