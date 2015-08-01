package fiskfille.tf.client.tutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.Maps;

import fiskfille.tf.common.transformer.base.Transformer;

public class TutorialHandler
{	
	public static Map<EnumTutorialType, Boolean> tutorialsCompleted = Maps.newHashMap();
	private static final File directory = new File("tf-tutorials.txt");
	
	public static EnumTutorialType currentTutorial = null;
	public static EnumTutorialType completedTutorial = null;
	
	public static int animationTimer;
	
	public static void init()
	{
		load();
		
		if (tutorialsCompleted.isEmpty())
		{
			for (EnumTutorialType type : EnumTutorialType.values())
			{
				tutorialsCompleted.put(type, false);
			}
		}
	}
	
	public static void completeTutorial(EnumTutorialType type)
	{
		currentTutorial = null;
		tutorialsCompleted.put(type, true);
		save();
		completedTutorial = type;
		animationTimer = 100;
	}
	
	public static void openTutorial(EntityPlayer player, Transformer transformer)
	{
		EnumTutorialType type = transformer.getTutorialType();
		
		if (type != null && !isCompleted(type))
		{
			TutorialHandler.currentTutorial = type;
		}
	}
	
	private static boolean isCompleted(EnumTutorialType type)
	{
		return tutorialsCompleted.get(type);
	}

	public static void tick(EntityPlayer player)
	{
		if (currentTutorial != null)
		{
			currentTutorial.ticker.tick(player);
		}
		
		if (animationTimer > 0)
		{
			--animationTimer;
		}
		if (animationTimer == 0)
		{
			completedTutorial = null;
		}
	}
	
	public static void shoot(EntityPlayer player)
	{
		if (currentTutorial != null)
		{
			currentTutorial.ticker.shoot(player);
		}
	}
	
	public static void save()
	{
		try
		{
			PrintWriter out = new PrintWriter(directory);
			
			if (!directory.exists())
			{
				directory.createNewFile();
			}
			
			String s = "";
			
			for (Map.Entry<EnumTutorialType, Boolean> e : tutorialsCompleted.entrySet())
			{
				s += e.getKey().name() + ":" + e.getValue() + "\n";
			}
			
			out.println(s.substring(0, s.length() - 1));
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void load()
	{
		try
		{
			if (!directory.exists())
			{
				directory.createNewFile();
			}
			
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(directory));
			String s = "";
			
			while ((s = in.readLine()) != null)
			{
				String[] astring = s.split(":");
				String key = astring[0];
				String value = astring[1];
				
				for (EnumTutorialType type : EnumTutorialType.values())
				{
					if (key.equals(type.name()))
					{
						tutorialsCompleted.put(type, Boolean.valueOf(value));
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
