package me.ciakid.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import me.ciakid.Plugin;
import me.ciakid.utils.Utils;

public class ItemHeldListener implements Listener {

	@EventHandler
	public void onItemHeld(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		ItemStack heldItem = player.getInventory().getItem(event.getNewSlot());

		if (heldItem != null && heldItem.hasItemMeta() && heldItem.getItemMeta().hasLore()
				&& Utils.hasKeyLore(heldItem)) {

			long loreValue = Utils.getTimestampFromLore(heldItem);

			long currentTimeMillis = System.currentTimeMillis();

			long MAX_VALUE = Utils.hoursToMillis(Plugin.getInstance().getPluginConfig().getExpirationTime());

			double percentage = (double) (currentTimeMillis - loreValue) / MAX_VALUE;

			int progressBarLength = 20;
			String progressBar = Utils.createProgressBar(percentage, progressBarLength);

			player.sendMessage(Utils.colorize("&eEste spawner se destruira en: ") + progressBar);

		}

	}

}
