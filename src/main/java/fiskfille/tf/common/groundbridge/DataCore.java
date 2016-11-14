package fiskfille.tf.common.groundbridge;

import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class DataCore
{
	public static final DataCore[] dataCores = new DataCore[16];
	public static int indexes = -1;
	
	public static final DataCore spaceBridge = new DataCore("space_bridge", 0x5FEEEE);
	
	private final String id;
	private final int color;
	public final int index;
	
	public DataCore(String s, int i)
	{
		id = s;
		color = i;
		
		dataCores[index = ++indexes] = this;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getTranslatedName()
	{
		return StatCollector.translateToLocal("ground_bridge.upgrade." + id);
	}
	
	public int getColor()
	{
		return color;
	}
	
	public static DataCore get(int index)
	{
		return dataCores[MathHelper.clamp_int(index, 0, indexes)];
	}
}
