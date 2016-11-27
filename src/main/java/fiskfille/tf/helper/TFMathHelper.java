package fiskfille.tf.helper;

import net.minecraft.util.MathHelper;

public class TFMathHelper
{
    public static int[] split(int number, int digits)
    {
        int[] increments = new int[digits];
        int[] aint = new int[digits];
        int incr = 1;
        int total = 0;
        
        for (int i = 0; i < digits; ++i)
        {
            increments[i] = incr;
            incr *= 10;
        }
        
        for (int i = digits - 1; i >= 0; --i)
        {
            int j = MathHelper.floor_double((float)number / increments[i]);
            
            aint[i] = j * increments[i] - total;
            total = j * increments[i];
        }
        
        return aint;
    }
}
