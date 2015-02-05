package fiskfille.tf.donator;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fiskfille.tf.pastebin.PastebinFileReader;

public class DonatorLoader extends Thread
{
	private Side side;
	
	public void run()
	{
		try 
		{
			List<String> text = PastebinFileReader.readFile("vJ6XLKBZ");
		
			for (String line : text) 
			{
				String[] split = line.split(Pattern.quote(": "));
				Donators.donators.put(UUID.fromString(split[0]), new Money(split[1]));
			}
			
			Donators.doAchievements(side);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void loadDonators()
	{
		side = FMLCommonHandler.instance().getEffectiveSide();
		this.start();
	}
}
