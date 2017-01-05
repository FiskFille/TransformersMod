package fiskfille.tf.asm.transformers;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import fiskfille.tf.asm.ASMHooksClient;
import fiskfille.tf.asm.TFTranslator;

public class ClassTransformerEntity extends ClassTransformerBase
{
    public static String varPlayer;
    public static String varEntity;

    public ClassTransformerEntity()
    {
        super("net.minecraft.entity.Entity");
    }

    @Override
    public boolean processMethods(List<MethodNode> methods)
    {
        boolean flag = false;

        for (MethodNode method : methods)
        {
            if (method.name.equals(TFTranslator.getMappedName("c", "getBrightnessForRender")) && method.desc.equals("(F)I"))
            {
                InsnList list = new InsnList();
                int startIndex = -1;
                int endIndex = -1;

                for (int i = 0; i < method.instructions.size(); ++i)
                {
                    AbstractInsnNode node = method.instructions.get(i);

                    if (i + 9 < method.instructions.size())
                    {
                        AbstractInsnNode endNode = method.instructions.get(i + 9);

                        if (endNode instanceof VarInsnNode && ((VarInsnNode) endNode).var == 6 && endNode.getOpcode() == ISTORE)
                        {
                            startIndex = i;
                            endIndex = i + 9;
                            list.add(new VarInsnNode(ALOAD, 0));
                            list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ASMHooksClient.class), "getBrightnessForRender", "(L" + varEntity + ";)I", false));
                        }
                    }

                    if (i >= startIndex && i < endIndex)
                    {
                        continue;
                    }

                    list.add(node);
                }

                method.instructions.clear();
                method.instructions.add(list);
                flag = true;
            }
        }

        return flag;
    }

    @Override
    public boolean processFields(List<FieldNode> fields)
    {
        return true;
    }

    @Override
    public void setupMappings()
    {
        varPlayer = TFTranslator.getMappedName("yz", "net/minecraft/entity/player/EntityPlayer");
        varEntity = TFTranslator.getMappedName("sa", "net/minecraft/entity/Entity");
    }
}
