package fiskfille.tf.helper;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

import com.google.common.collect.Lists;

public class TFFormatHelper
{
    public static String formatNumber(float f)
    {
        String s = (long) f + "";

        if (!s.contains("E"))
        {
            String s1 = "";

            for (int i = 0; i < s.length(); ++i)
            {
                s1 += s.charAt(i);

                if ((s.length() - i) % 3 == 1)
                {
                    s1 += ",";
                }
            }

            return s1.substring(0, s1.length() - 1);
        }

        return s;
    }

    public static String formatNumberPrecise(float f)
    {
        String s = formatNumber(f);
        String s1 = ItemStack.field_111284_a.format(f);

        if (s1.contains("."))
        {
            s += s1.substring(s1.lastIndexOf("."));
        }

        return s;
    }

    public static String getConventionalName(String s)
    {
        String s1 = s.replace(" ", "").replace("'", "").replace("/", "").replace("\\", "").replace("_", "").replace("-", "").replace("(", "").replace(")", "");
        return s1.substring(0, 1).toLowerCase() + s1.substring(1);
    }

    public static String getUnconventionalName(String s)
    {
        s = s.toLowerCase();

        for (int i = 0; i < s.length(); ++i)
        {
            if (i > 0 && s.charAt(i - 1) == '_' && i < s.length())
            {
                s = s.substring(0, i) + s.substring(i, i + 1).toUpperCase() + s.substring(i + 1);
            }
        }

        s = s.replace(" ", "").replace("'", "").replace("/", "").replace("\\", "").replace("_", "").replace("-", "").replace("(", "").replace(")", "");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    public static List<String> toString(List<IChatComponent> list)
    {
        List<String> list1 = Lists.newArrayList();
        
        for (IChatComponent component : list)
        {
            list1.add(component == null ? "" : component.getFormattedText());
        }
        
        return list1;
    }
}
