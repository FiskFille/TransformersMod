package fiskfille.tf.asm.transformers;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import fiskfille.tf.asm.ASMHooks;
import fiskfille.tf.asm.ASMHooksClient;
import fiskfille.tf.asm.TFTranslator;

public class ClassTransformerRenderPlayer extends ClassTransformerBase
{
    public static String varPlayer;

    public ClassTransformerRenderPlayer()
    {
        super("net.minecraft.client.renderer.entity.RenderPlayer");
    }

    @Override
    public boolean processMethods(List<MethodNode> methods)
    {
        boolean flag = false;

        for (MethodNode method : methods)
        {
            if (method.name.equals(TFTranslator.getMappedName("a", "renderLivingAt")) && method.desc.equals(TFTranslator.getMappedName("(Lsv;DDD)V", "(Lnet/minecraft/entity/EntityLivingBase;DDD)V")))
            {
                InsnList list = new InsnList();

                for (int i = 0; i < method.instructions.size(); ++i)
                {
                    AbstractInsnNode node = method.instructions.get(i);

                    if (node instanceof MethodInsnNode)
                    {
                        MethodInsnNode methodNode = (MethodInsnNode) node;

                        if (methodNode.getOpcode() == INVOKEVIRTUAL && methodNode.desc.equals(TFTranslator.getMappedName("(Lblg;DDD)V", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V")))
                        {
                            list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ASMHooksClient.class), "applyPlayerRenderTranslation", TFTranslator.getMappedName("(Lbop;Lblg;DDD)V", "(Lnet/minecraft/client/renderer/entity/RenderPlayer;Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V"), false));
                            continue;
                        }
                    }

                    list.add(node);
                }

                method.instructions.clear();
                method.instructions.add(list);
                flag = true;
            }
            else if (method.name.equals(TFTranslator.getMappedName("a", "doRender")) && method.desc.equals(TFTranslator.getMappedName("(Lblg;DDDFF)V", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V")))
            {
                InsnList list = new InsnList();

                for (int i = 0; i < method.instructions.size(); ++i)
                {
                    AbstractInsnNode node = method.instructions.get(i);

                    if (node instanceof LdcInsnNode)
                    {
                        LdcInsnNode ldcNode = (LdcInsnNode) node;

                        if (ldcNode.cst instanceof Double && (Double) ldcNode.cst == 0.125D)
                        {
                            list.add(new VarInsnNode(ALOAD, 1));
                            list.add(node);
                            list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ASMHooks.class), "getScaledSneakOffset", TFTranslator.getMappedName("(Lsa;D)D", "(Lnet/minecraft/entity/Entity;D)D"), false));
                            continue;
                        }
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
    }
}
