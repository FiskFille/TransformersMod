package fiskfille.tf.client.gui;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.tf.common.fluid.FluidTankTF;
import fiskfille.tf.helper.TFFormatHelper;

@SideOnly(Side.CLIENT)
public class GuiHoverFieldFluid extends GuiHoverField
{
    private FluidTankTF fluidTank;
    
    public GuiHoverFieldFluid(int x, int y, int width, int height, FluidTankTF tank)
    {
        super(x, y, width, height, new ArrayList<String>());
        fluidTank = tank;
    }
    
    public void update(FluidTankTF tank)
    {
        fluidTank = tank;
    }
    
    @Override
    public List<String> getHoverText()
    {
////        FluidStack stack = fluidTank.getFluid();
////        ArrayList text = Lists.newArrayList();
////
////        if (stack != null && stack.amount > 0)
////        {
////            Map<String, Float> ratios = FluidEnergon.getRatios(stack);
////            boolean flag = false;
////
////            for (Map.Entry<String, Float> e : ratios.entrySet())
////            {
////                Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
////                int percent = Math.round(e.getValue() * 100);
////
////                if (percent > 0)
////                {
////                    text.add(colorFormat(energon.getColor()) + I18n.format("gui.energon_processor.content", energon.getTranslatedName(), Math.round(e.getValue() * 100)));
////                    flag = true;
////                }
////            }
////
////            if (flag)
////            {
////                text.add("");
////            }
////            else
////            {
////                text.add(colorFormat(0xbf0000) + I18n.format("gui.energon_processor.unidentified"));
////            }
////        }
////
////        text.add(colorFormat(stack != null ? stack.getFluid().getColor(stack) : -1) + I18n.format("gui.energon_processor.filled", TFFormatHelper.formatNumber(fluidTank.getFluidAmount()), TFFormatHelper.formatNumber(fluidTank.getCapacity())));
//        
//        FluidStack stack = fluidTank.getFluid();
//        List<String> text = Lists.newArrayList();
//
//        if (stack != null && stack.amount > 0)
//        {
//            Map<String, Float> ratios = FluidEnergon.getRatios(stack);
//            boolean flag = false;
//
//            for (Map.Entry<String, Float> e : ratios.entrySet())
//            {
//                Energon energon = TransformersAPI.getEnergonTypeByName(e.getKey());
//                int percent = Math.round(e.getValue() * 100);
//
//                if (percent > 0)
//                {
////                    text.add(GRAY + I18n.format("gui.energon_processor.content", energon.getTranslatedName(), YELLOW.toString() + percent + GRAY));
//                    IChatComponent component = new ChatComponentText(energon.getTranslatedName()).setChatStyle(new ChatStyle().setColor(GRAY));
//                    IChatComponent component1 = new ChatComponentText(percent + "").setChatStyle(new ChatStyle().setColor(YELLOW));
//                    IChatComponent component2 = new ChatComponentTranslation("gui.energon_processor.content", component, component1).setChatStyle(new ChatStyle().setColor(GRAY));
//                    text.add(component2.getFormattedText());
//                    flag = true;
//                }
//            }
//
//            if (flag)
//            {
//                text.add("");
//            }
//            else
//            {
//                IChatComponent component = new ChatComponentTranslation("gui.energon_processor.unidentified").setChatStyle(new ChatStyle().setColor(RED));
//                text.add(component.getFormattedText());
//            }
//        }
//
////        text.add(GRAY + I18n.format("gui.energon_processor.filled", YELLOW + TFFormatHelper.formatNumber(fluidTank.getFluidAmount()) + GRAY, YELLOW + TFFormatHelper.formatNumber(fluidTank.getCapacity()) + GRAY));
//        IChatComponent component = new ChatComponentText(TFFormatHelper.formatNumber(fluidTank.getFluidAmount())).setChatStyle(new ChatStyle().setColor(YELLOW));
//        IChatComponent component1 = new ChatComponentText(TFFormatHelper.formatNumber(fluidTank.getCapacity())).setChatStyle(new ChatStyle().setColor(YELLOW));
//        IChatComponent component2 = new ChatComponentTranslation("gui.energon_processor.filled", component, component1).setChatStyle(new ChatStyle().setColor(GRAY));
//        text.add(component2.getFormattedText());
//        
//        return text;
        
        return TFFormatHelper.toString(fluidTank.format());
    }
}
