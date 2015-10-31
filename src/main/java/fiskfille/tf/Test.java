package fiskfille.tf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Test extends CommandBase
{
	public String getCommandName()
	{
		return "spreadplayers";
	}

	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.spreadplayers.usage";
	}

	public void processCommand(ICommandSender sender, String[] args)
	{
		if (args.length < 6)
		{
			throw new WrongUsageException("commands.spreadplayers.usage", new Object[0]);
		}
		else
		{
			byte b0 = 0;
			int i = b0 + 1;
			double x = func_110666_a(sender, Double.NaN, args[b0]);
			double z = func_110666_a(sender, Double.NaN, args[i++]);
			double d2 = parseDoubleWithMin(sender, args[i++], 0.0D);
			double d3 = parseDoubleWithMin(sender, args[i++], d2 + 1.0D);
			boolean respectTeams = parseBoolean(sender, args[i++]);
			ArrayList selectedPlayers = Lists.newArrayList();

			while (true)
			{
				while (i < args.length)
				{
					String s = args[i++];

					if (PlayerSelector.hasArguments(s))
					{
						EntityPlayerMP[] aentityplayermp = PlayerSelector.matchPlayers(sender, s);

						if (aentityplayermp == null || aentityplayermp.length == 0)
						{
							throw new PlayerNotFoundException();
						}

						Collections.addAll(selectedPlayers, aentityplayermp);
					}
					else
					{
						EntityPlayerMP entityplayermp = MinecraftServer.getServer().getConfigurationManager().func_152612_a(s);

						if (entityplayermp == null)
						{
							throw new PlayerNotFoundException();
						}

						selectedPlayers.add(entityplayermp);
					}
				}

				if (selectedPlayers.isEmpty())
				{
					throw new PlayerNotFoundException();
				}

				sender.addChatMessage(new ChatComponentTranslation("commands.spreadplayers.spreading." + (respectTeams ? "teams" : "players"), new Object[] {Integer.valueOf(selectedPlayers.size()), Double.valueOf(d3), Double.valueOf(x), Double.valueOf(z), Double.valueOf(d2)}));
				this.func_110669_a(sender, selectedPlayers, new Test.Position(x, z), d2, d3, ((EntityLivingBase)selectedPlayers.get(0)).worldObj, respectTeams);
				return;
			}
		}
	}

	private void func_110669_a(ICommandSender sender, List selectedPlayers, Test.Position pos, double x, double z, World world, boolean respectTeams)
	{
		Random random = new Random();
		double d2 = pos.posX - z;
		double d3 = pos.posZ - z;
		double d4 = pos.posX + z;
		double d5 = pos.posZ + z;
		Test.Position[] aposition = this.func_110670_a(random, respectTeams ? this.func_110667_a(selectedPlayers) : selectedPlayers.size(), d2, d3, d4, d5);
		int i = this.func_110668_a(pos, x, world, random, d2, d3, d4, d5, aposition, respectTeams);
		double d6 = this.func_110671_a(selectedPlayers, world, aposition, respectTeams);
		func_152373_a(sender, this, "commands.spreadplayers.success." + (respectTeams ? "teams" : "players"), new Object[] {Integer.valueOf(aposition.length), Double.valueOf(pos.posX), Double.valueOf(pos.posZ)});

		if (aposition.length > 1)
		{
			sender.addChatMessage(new ChatComponentTranslation("commands.spreadplayers.info." + (respectTeams ? "teams" : "players"), new Object[] {String.format("%.2f", new Object[]{Double.valueOf(d6)}), Integer.valueOf(i)}));
		}
	}

	private int func_110667_a(List p_110667_1_)
	{
		HashSet hashset = Sets.newHashSet();
		Iterator iterator = p_110667_1_.iterator();

		while (iterator.hasNext())
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();

			if (entitylivingbase instanceof EntityPlayer)
			{
				hashset.add(entitylivingbase.getTeam());
			}
			else
			{
				hashset.add((Object)null);
			}
		}

		return hashset.size();
	}

	private int func_110668_a(Test.Position pos, double p_110668_2_, World world, Random rand, double minX, double minZ, double maxX, double maxZ, Test.Position[] p_110668_14_, boolean p_110668_15_)
	{
		boolean flag1 = true;
		double d5 = 3.4028234663852886E38D;
		int i;

		for (i = 0; i < 10000 && flag1; ++i)
		{
			flag1 = false;
			d5 = 3.4028234663852886E38D;
			int k;
			Test.Position position1;

			for (int j = 0; j < p_110668_14_.length; ++j)
			{
				Test.Position position = p_110668_14_[j];
				k = 0;
				position1 = new Test.Position();

				for (int l = 0; l < p_110668_14_.length; ++l)
				{
					if (j != l)
					{
						Test.Position position2 = p_110668_14_[l];
						double d6 = position.getDistanceToSq(position2);
						d5 = Math.min(d6, d5);

						if (d6 < p_110668_2_)
						{
							++k;
							position1.posX += position2.posX - position.posX;
							position1.posZ += position2.posZ - position.posZ;
						}
					}
				}

				if (k > 0)
				{
					position1.posX /= (double)k;
					position1.posZ /= (double)k;
					double d7 = (double)position1.getPositionSqrt();

					if (d7 > 0.0D)
					{
						position1.divideBySqrt();
						position.subtract(position1);
					}
					else
					{
						position.randomizePosition(rand, minX, minZ, maxX, maxZ);
					}

					flag1 = true;
				}

				if (position.exceedsRange(minX, minZ, maxX, maxZ))
				{
					flag1 = true;
				}
			}

			if (!flag1)
			{
				Test.Position[] aposition = p_110668_14_;
				int i1 = p_110668_14_.length;

				for (k = 0; k < i1; ++k)
				{
					position1 = aposition[k];

					if (!position1.checkConditions(world))
					{
						position1.randomizePosition(rand, minX, minZ, maxX, maxZ);
						flag1 = true;
					}
				}
			}
		}

		if (i >= 10000)
		{
			throw new CommandException("commands.spreadplayers.failure." + (p_110668_15_ ? "teams" : "players"), new Object[] {Integer.valueOf(p_110668_14_.length), Double.valueOf(pos.posX), Double.valueOf(pos.posZ), String.format("%.2f", new Object[]{Double.valueOf(d5)})});
		}
		else
		{
			return i;
		}
	}

	private double func_110671_a(List selectedPlayers, World world, Test.Position[] pos, boolean respectTeams)
	{
		double d0 = 0.0D;
		int i = 0;
		HashMap hashmap = Maps.newHashMap();

		for (int j = 0; j < selectedPlayers.size(); ++j)
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase)selectedPlayers.get(j);
			Test.Position position;

			if (respectTeams)
			{
				Team team = entitylivingbase instanceof EntityPlayer ? entitylivingbase.getTeam() : null;

				if (!hashmap.containsKey(team))
				{
					hashmap.put(team, pos[i++]);
				}

				position = (Test.Position)hashmap.get(team);
			}
			else
			{
				position = pos[i++];
			}

			entitylivingbase.setPositionAndUpdate((double)((float)MathHelper.floor_double(position.posX) + 0.5F), (double)position.getTopMostBlock(world), (double)MathHelper.floor_double(position.posZ) + 0.5D);
			double d2 = Double.MAX_VALUE;

			for (int k = 0; k < pos.length; ++k)
			{
				if (position != pos[k])
				{
					double d1 = position.getDistanceToSq(pos[k]);
					d2 = Math.min(d1, d2);
				}
			}

			d0 += d2;
		}

		d0 /= (double)selectedPlayers.size();
		return d0;
	}

	private Test.Position[] func_110670_a(Random rand, int amount, double minX, double minZ, double maxX, double maxZ)
	{
		Test.Position[] aposition = new Test.Position[amount];

		for (int j = 0; j < aposition.length; ++j)
		{
			Test.Position position = new Test.Position();
			position.randomizePosition(rand, minX, minZ, maxX, maxZ);
			aposition[j] = position;
		}

		return aposition;
	}

	static class Position
	{
		double posX;
		double posZ;

		Position() {}

		Position(double x, double z)
		{
			this.posX = x;
			this.posZ = z;
		}

		double getDistanceToSq(Test.Position pos)
		{
			double diffX = this.posX - pos.posX;
			double diffZ = this.posZ - pos.posZ;
			return Math.sqrt(diffX * diffX + diffZ * diffZ);
		}

		void divideBySqrt()
		{
			double sqrt = (double)this.getPositionSqrt();
			this.posX /= sqrt;
			this.posZ /= sqrt;
		}

		float getPositionSqrt()
		{
			return MathHelper.sqrt_double(this.posX * this.posX + this.posZ * this.posZ);
		}

		public void subtract(Test.Position pos)
		{
			this.posX -= pos.posX;
			this.posZ -= pos.posZ;
		}

		public boolean exceedsRange(double minX, double minZ, double maxX, double maxZ)
		{
			boolean exceedsRange = false;

			if (this.posX < minX)
			{
				this.posX = minX;
				exceedsRange = true;
			}
			else if (this.posX > maxX)
			{
				this.posX = maxX;
				exceedsRange = true;
			}

			if (this.posZ < minZ)
			{
				this.posZ = minZ;
				exceedsRange = true;
			}
			else if (this.posZ > maxZ)
			{
				this.posZ = maxZ;
				exceedsRange = true;
			}

			return exceedsRange;
		}

		public int getTopMostBlock(World world)
		{
			int x = MathHelper.floor_double(this.posX);
			int z = MathHelper.floor_double(this.posZ);

			for (int y = 256; y > 0; --y)
			{
				if (world.getBlock(x, y, z).getMaterial() != Material.air)
				{
					return y + 1;
				}
			}

			return 257;
		}

		public boolean checkConditions(World world)
		{
			int x = MathHelper.floor_double(this.posX);
			int z = MathHelper.floor_double(this.posZ);
			short y = 256;

			if (y <= 0)
			{
				return false;
			}
			else
			{
				Material material = world.getBlock(x, y, z).getMaterial();
				return !material.isLiquid() && material != Material.fire;
			}
		}

		public void randomizePosition(Random rand, double minX, double minZ, double maxX, double maxZ)
		{
			this.posX = MathHelper.getRandomDoubleInRange(rand, minX, maxX);
			this.posZ = MathHelper.getRandomDoubleInRange(rand, minZ, maxZ);
		}
	}
}