package fiskfille.tf.helper;

public class TFEnergyHelper
{
	public static String formatNumber(float f)
	{
		String s = (long)f + "";
		
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
}
