package fiskfille.tf.common.helper;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class TFFormatHelper
{
    public static String formatNumber(float f)
    {
        String s = (long) f + "";

        if (!s.contains("E"))
        {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < s.length(); ++i)
            {
                builder.append(s.charAt(i));

                if ((s.length() - i) % 3 == 1)
                {
                    builder.append(",");
                }
            }

            return builder.substring(0, builder.length() - 1);
        }

        return s;
    }

    public static String formatNumberPrecise(float f)
    {
        String s = formatNumber(f);
        String s1 = ItemStack.DECIMALFORMAT.format(f);

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

    public static List<String> toString(List<ITextComponent> list)
    {
        List<String> list1 = Lists.newArrayList();

        for (ITextComponent component : list)
        {
            list1.add(component == null ? "" : component.getFormattedText());
        }

        return list1;
    }
}
