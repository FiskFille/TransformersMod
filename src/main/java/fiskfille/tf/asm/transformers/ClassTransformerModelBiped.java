package fiskfille.tf.asm.transformers;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import fiskfille.tf.asm.TFTranslator;
import fiskfille.tf.helper.TFModelHelper;

public class ClassTransformerModelBiped extends ClassTransformerBase
{
    public static String varPlayer;
    public static String varEntity;

    public ClassTransformerModelBiped()
    {
        super("net.minecraft.client.model.ModelBiped");
    }

    @Override
    public boolean processMethods(List<MethodNode> methods)
    {
        boolean flag = false;

        for (MethodNode method : methods)
        {
            if (method.name.equals(TFTranslator.getMappedName("a", "render")) && method.desc.equals(TFTranslator.getMappedName("(Lsa;FFFFFF)V", "(Lnet/minecraft/entity/Entity;FFFFFF)V")))
            {
                InsnList list = new InsnList();

                for (int i = 0; i < method.instructions.size(); ++i)
                {
                    AbstractInsnNode node = method.instructions.get(i);

                    if (node instanceof MethodInsnNode)
                    {
                        MethodInsnNode methodNode = (MethodInsnNode) node;

                        if (methodNode.name.equals(TFTranslator.getMappedName("a", "setRotationAngles")) && methodNode.desc.equals(TFTranslator.getMappedName("(FFFFFFLsa;)V", "(FFFFFFLnet/minecraft/entity/Entity;)V")))
                        {
                            list.add(node);
                            list.add(new VarInsnNode(ALOAD, 0));
                            list.add(new VarInsnNode(ALOAD, 1));
                            list.add(new VarInsnNode(FLOAD, 2));
                            list.add(new VarInsnNode(FLOAD, 3));
                            list.add(new VarInsnNode(FLOAD, 4));
                            list.add(new VarInsnNode(FLOAD, 5));
                            list.add(new VarInsnNode(FLOAD, 6));
                            list.add(new VarInsnNode(FLOAD, 7));
                            list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(TFModelHelper.class), "renderBipedPre", TFTranslator.getMappedName("(Lbhm;Lsa;FFFFFF)V", "(Lnet/minecraft/client/model/ModelBiped;Lnet/minecraft/entity/Entity;FFFFFF)V"), false));
                            continue;
                        }
                    }

                    if (node.getOpcode() == RETURN)
                    {
                        list.add(new VarInsnNode(ALOAD, 0));
                        list.add(new VarInsnNode(ALOAD, 1));
                        list.add(new VarInsnNode(FLOAD, 2));
                        list.add(new VarInsnNode(FLOAD, 3));
                        list.add(new VarInsnNode(FLOAD, 4));
                        list.add(new VarInsnNode(FLOAD, 5));
                        list.add(new VarInsnNode(FLOAD, 6));
                        list.add(new VarInsnNode(FLOAD, 7));
                        list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(TFModelHelper.class), "renderBipedPost", TFTranslator.getMappedName("(Lbhm;Lsa;FFFFFF)V", "(Lnet/minecraft/client/model/ModelBiped;Lnet/minecraft/entity/Entity;FFFFFF)V"), false));
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
