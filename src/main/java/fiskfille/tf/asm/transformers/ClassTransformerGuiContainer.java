package fiskfille.tf.asm.transformers;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import fiskfille.tf.asm.ASMHooksClient;
import fiskfille.tf.asm.TFTranslator;

public class ClassTransformerGuiContainer extends ClassTransformerMethodProcess
{
    public ClassTransformerGuiContainer()
    {
        super("net.minecraft.client.gui.inventory.GuiContainer", "a", "func_146977_a", "(Laay;)V", "(Lnet/minecraft/inventory/Slot;)V");
    }

    @Override
    public void processMethod(MethodNode method)
    {
        InsnList list = new InsnList();

        for (int i = 0; i < method.instructions.size(); ++i)
        {
            AbstractInsnNode node = method.instructions.get(i);
//            System.out.println(node.getClass() + ", " + node.getOpcode());
            
            if (i == 0)
            {
                list.add(new VarInsnNode(ALOAD, 0));
                list.add(new VarInsnNode(ALOAD, 1));
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ASMHooksClient.class), "renderSlotPre", TFTranslator.getMappedName("(Lbex;Laay;)V", "(Lnet/minecraft/client/gui/inventory/GuiContainer;Lnet/minecraft/inventory/Slot;)V"), false));
            }
            else if (node.getOpcode() == RETURN)
            {
                list.add(new VarInsnNode(ALOAD, 0));
                list.add(new VarInsnNode(ALOAD, 1));
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ASMHooksClient.class), "renderSlotPost", TFTranslator.getMappedName("(Lbex;Laay;)V", "(Lnet/minecraft/client/gui/inventory/GuiContainer;Lnet/minecraft/inventory/Slot;)V"), false));
            }

            list.add(node);
        }

        method.instructions.clear();
        method.instructions.add(list);
    }
}
