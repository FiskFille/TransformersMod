package fiskfille.tf.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fiskfille.tf.client.model.transformer.definition.TFModelRegistry;
import fiskfille.tf.client.model.transformer.definition.TransformerModel;
import fiskfille.tf.common.item.armor.ItemTransformerArmor;
import fiskfille.tf.common.transformer.TransformerCloudtrap;
import fiskfille.tf.common.transformer.TransformerPurge;
import fiskfille.tf.common.transformer.TransformerSkystrike;
import fiskfille.tf.common.transformer.TransformerSubwoofer;
import fiskfille.tf.common.transformer.TransformerVurp;
import fiskfille.tf.common.transformer.base.Transformer;

/**
 * @author FiskFille, gegy1000
 */
public class TFHelper
{
	/**
	 * @returns whether the player is wearing the 'Cloudtrap' set.
	 */
	public static boolean isPlayerCloudtrap(EntityPlayer player)
	{
		return getTransformer(player) instanceof TransformerCloudtrap;
	}

	/**
	 * @returns whether the player is wearing the 'Skystrike' set.
	 */
	public static boolean isPlayerSkystrike(EntityPlayer player)
	{
		return getTransformer(player) instanceof TransformerSkystrike;
	}

	/**
	 * @returns whether the player is wearing the 'Purge' set.
	 */
	public static boolean isPlayerPurge(EntityPlayer player)
	{
		return getTransformer(player) instanceof TransformerPurge;
	}

	/**
	 * @returns whether the player is wearing the 'Vurp' set.
	 */
	public static boolean isPlayerVurp(EntityPlayer player)
	{
		return getTransformer(player) instanceof TransformerVurp;
	}

	/**
	 * @returns whether the player is wearing the 'Subwoofer' set.
	 */
	public static boolean isPlayerSubwoofer(EntityPlayer player)
	{
		return getTransformer(player) instanceof TransformerSubwoofer;
	}

	/**
	 * @returns whether the player is wearing a full Transformer set.
	 */
	public static boolean isPlayerTransformer(EntityPlayer player)
	{
		Transformer helmetTransformer = getTransformerFromArmor(player, 3);
		Transformer chestTransformer = getTransformerFromArmor(player, 2);
		Transformer legsTransformer = getTransformerFromArmor(player, 1);
		Transformer feetTransformer = getTransformerFromArmor(player, 0);

		return helmetTransformer != null && helmetTransformer == chestTransformer && chestTransformer == legsTransformer && legsTransformer == feetTransformer;
	}

	/**
	 * @returns the Transformer that the player currently has fully equipped, null when not wearing a full set.
	 */
	public static Transformer getTransformer(EntityPlayer player)
	{
		if (player != null && isPlayerTransformer(player))
		{
			return getTransformerFromArmor(player, 0);
		}

		return null;
	}

	/**
	 * @returns the Transformer that the player is wearing in the specified slot.
	 */
	public static Transformer getTransformerFromArmor(EntityPlayer player, int slot)
	{
		ItemStack currentArmorStack = player.getCurrentArmor(slot);

		if (currentArmorStack != null)
		{
			Item currentArmor = currentArmorStack.getItem();

			if (currentArmor instanceof ItemTransformerArmor)
			{
				return ((ItemTransformerArmor) currentArmor).getTransformer();
			}
		}

		return null;
	}

	/**
	 * @returns the Transformer for the specific armor ItemStack.
	 */
	public static Transformer getTransformerFromArmor(ItemStack itemstack)
	{
		if (itemstack != null)
		{
			Item currentArmor = itemstack.getItem();

			if (currentArmor instanceof ItemTransformerArmor)
			{
				return ((ItemTransformerArmor) currentArmor).getTransformer();
			}
		}

		return null;
	}

	public static void replaceBlock(World world, int x, int y, int z, Block block, Block replacement)
	{
		if (world.getBlock(x, y, z) == block)
		{
			world.setBlock(x, y, z, replacement);
		}
	}
}
