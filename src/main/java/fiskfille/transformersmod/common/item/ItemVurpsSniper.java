package fiskfille.transformersmod.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import fiskfille.transformersmod.TransformersMod;
import fiskfille.transformersmod.common.packet.PacketVurpSniperShoot;
import fiskfille.transformersmod.common.packet.base.TFPacketManager;
import fiskfille.transformersmod.common.playerdata.TFDataManager;
import fiskfille.transformersmod.helper.TFHelper;
import fiskfille.transformersmod.helper.TFShootManager;

public class ItemVurpsSniper extends ItemSword
{
	public ItemVurpsSniper(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(TransformersMod.transformersTab);
		this.setMaxDamage(1500);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		boolean isCreativeMode = player.capabilities.isCreativeMode;
		
		if (TFHelper.isPlayerVurp(player) && !TFDataManager.isInVehicleMode(player) && (player.inventory.hasItem(TFItems.miniMissile) || isCreativeMode))
		{
			stack.damageItem(2, player);

			if (world.isRemote)
			{
				if (TFShootManager.shotsLeft > 0)
				{
					if (TFShootManager.shootCooldown <= 0)
					{
						TFPacketManager.packetPipeline.sendToServer(new PacketVurpSniperShoot(player));

						TFShootManager.shotsLeft--;

						if (TFShootManager.shotsLeft <= 0)
						{
							TFShootManager.shootCooldown = 20;
							TFShootManager.reloading = true;
						}
					}
				}
				else
				{
					if (!TFShootManager.reloading)
					{
						TFShootManager.shootCooldown = 20;
						TFShootManager.reloading = true;
					}
				}
			}
		}

		return stack;
	}

	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(TransformersMod.modid + ":" + iconString);
	}
}