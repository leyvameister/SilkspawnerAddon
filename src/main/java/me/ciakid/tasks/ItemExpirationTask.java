package me.ciakid.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.ciakid.Plugin;
import me.ciakid.PluginConfig;
import me.ciakid.utils.Utils;

public class ItemExpirationTask {

	private BukkitRunnable task;

	public void start() {
		task = new BukkitRunnable() {
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					checkInventoryForExpiredItems(player);
				}
			}
		};
		task.runTaskTimer(Plugin.getInstance(), 0, 200L);
	}

	public void stop() {
		if (task != null) {
			task.cancel();
		}
	}

	private void checkInventoryForExpiredItems(Player player) {
		Inventory inventory = player.getInventory();
		for (ItemStack item : inventory.getContents()) {
			if (item != null && item.hasItemMeta() && Utils.hasExpired(item)) {
				inventory.remove(item);
				PluginConfig pc = Plugin.getInstance().getPluginConfig();
				if (pc.shouldShowMessages()) {
					player.sendMessage(Utils.colorize(pc.getRemovalMessage()));
				}
			}
		}
	}
}
