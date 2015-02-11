package fiskfille.transformersmod.sound;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fiskfille.transformersmod.TransformersMod;

@SideOnly(Side.CLIENT)
public class CarLoopSoundHandler
{
	private static String currentlyPlaying;
	
	public static void loopSound(EntityPlayer player)
	{
		Random rand = new Random();
		World world = player.worldObj;
		
		if (currentlyPlaying == null)
		{
			String s = "car" + (rand.nextInt(5) + 1);
			player.playSound(TransformersMod.modid + ":" + s, 1.0F, 1.0F);
			currentlyPlaying = s;
		}
		
		if (player.ticksExisted % 10 == 0)
		{
			player.playSound(TransformersMod.modid + ":" + currentlyPlaying, 1.0F, 1.0F);
		}
		if (player.ticksExisted % 10 == 9)
		{
			currentlyPlaying = null;
		}
//		else if (currentlyPlaying == "car1")
//		{
//			player.playSound(MainClass.modid + ":car1", 1.0F, 1.0F);
//		}
//		else if (currentlyPlaying == "car2")
//		{
//			player.playSound(MainClass.modid + ":car2", 1.0F, 1.0F);
//		}
//		else if (currentlyPlaying == "car3" && player.ticksExisted % 23 == 0)
//		{
//			player.playSound(MainClass.modid + ":car3", 1.0F, 1.0F);
//		}
//		else if (currentlyPlaying == "car4" && player.ticksExisted % 23 == 0)
//		{
//			player.playSound(MainClass.modid + ":car4", 1.0F, 1.0F);
//		}
//		else if (currentlyPlaying == "car5" && player.ticksExisted % 23 == 0)
//		{
//			player.playSound(MainClass.modid + ":car5", 1.0F, 1.0F);
//		}
		
		System.out.println(currentlyPlaying);
//		currentlyPlaying = null;
	}
	
	private static void reset(EntityPlayer player, int ticks)
	{
		if (player.ticksExisted % ticks == ticks)
		{
			currentlyPlaying = null;
		}
	}
	
	private static void play(EntityPlayer player, int ticks)
	{
		if (player.ticksExisted % ticks == 0)
		{
			player.playSound(TransformersMod.modid + ":" + currentlyPlaying, 1.0F, 1.0F);
		}
	}
}